package bo.com.digitalharbor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.com.digitalharbor.dto.StudentDto;
import bo.com.digitalharbor.entity.Student;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.exception.ValidationException;
import bo.com.digitalharbor.mapper.ClassMapper;
import bo.com.digitalharbor.mapper.StudentMapper;
import bo.com.digitalharbor.repo.StudentRepo;
import bo.com.digitalharbor.serv.StudentServ;

@Service
public class StudentImpl implements StudentServ {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private StudentRepo repo;

	@Override
	public List<StudentDto> filter(StudentDto value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> query = cb.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.isNotNull(root.get("studentId")));
		if (value.getFirstName() != null && !value.getFirstName().isEmpty()) {
			predicates.add(cb.like(cb.upper(root.get("firstName")), value.getFirstName().toUpperCase().trim()));
		}
		if (value.getLastName() != null && !value.getLastName().isEmpty()) {
			predicates.add(cb.like(cb.upper(root.get("lastName")), value.getLastName().toUpperCase().trim()));
		}
		query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return StudentMapper.toDto(em.createQuery(query).getResultList());
	}

	@Override
	@Transactional(readOnly = true)
	public StudentDto findById(Long id) throws ServiceException {
		if (id == null) {
			throw new ValidationException("Id is required");
		}
		if (id <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}
		Student student = repo.findByStudentId(id).orElse(null);
		if (student == null) {
			throw new ValidationException("Student with Id " + id + "was not found");
		}
		StudentDto dto = StudentMapper.to(student);
		if (student.getRegistrationList() == null) {
			return dto;
		}
		student.getRegistrationList().forEach(row -> {
			dto.add(ClassMapper.to(row.getClazz()));
		});
		return dto;
	}

	@Override
	public StudentDto create(StudentDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getFirstName() == null) {
			throw new ValidationException("First name is required");
		}
		if (value.getLastName() == null) {
			throw new ValidationException("Lastname is required");
		}
		return StudentMapper.to(repo.save(StudentMapper.to(value)));
	}

	@Override
	public StudentDto update(StudentDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getStudentId() == null) {
			throw new ValidationException("Id is required");
		}
		if (value.getStudentId() <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}
		if (value.getFirstName() == null) {
			throw new ValidationException("First name is required");
		}
		if (value.getLastName() == null) {
			throw new ValidationException("Lastname is required");
		}
		Student entity = repo.findByStudentId(value.getStudentId()).orElse(null);
		if (entity == null) {
			throw new ValidationException("Student with Id " + value.getStudentId() + "was not found");
		}
		StudentMapper.to(value, entity);
		return StudentMapper.to(repo.save(entity));
	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new ValidationException("Id is required");
		}
		if (id <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}
		repo.deleteById(id);
	}

}
