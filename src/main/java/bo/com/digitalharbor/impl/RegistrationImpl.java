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

import bo.com.digitalharbor.dto.RegistrationDto;
import bo.com.digitalharbor.entity.Registration;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.exception.ValidationException;
import bo.com.digitalharbor.mapper.RegistrationMapper;
import bo.com.digitalharbor.repo.ClassRepo;
import bo.com.digitalharbor.repo.RegistrationRepo;
import bo.com.digitalharbor.repo.StudentRepo;
import bo.com.digitalharbor.serv.RegistrationServ;

@Service
public class RegistrationImpl implements RegistrationServ {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private RegistrationRepo repo;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private ClassRepo classRepo;

	@Override
	public List<RegistrationDto> filter(RegistrationDto value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Registration> query = cb.createQuery(Registration.class);
		Root<Registration> root = query.from(Registration.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.isNotNull(root.get("registrationId")));
		if (value.getStudentId() != null) {
			predicates.add(cb.equal(root.get("student").get("studentId"), value.getStudentId()));
		}
		if (value.getClassCode() != null) {
			predicates.add(cb.equal(root.get("clazz").get("code"), value.getClassCode()));
		}
		query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return RegistrationMapper.toDto(em.createQuery(query).getResultList());
	}

	@Override
	@Transactional(readOnly = true)
	public RegistrationDto findById(Long id) throws ServiceException {
		if (id == null) {
			throw new ValidationException("Id is required");
		}
		if (id <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}
		Registration registration = repo.findByregistrationId(id).orElse(null);
		if (registration == null) {
			throw new ValidationException("Registration with Id " + id + "was not found");
		}
		return RegistrationMapper.to(registration);
	}

	@Override
	public RegistrationDto create(RegistrationDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getStudentId() == null) {
			throw new ValidationException("Student is required");
		}
		if (value.getClassCode() == null) {
			throw new ValidationException("Class code is required");
		}
		if (value.getDate() == null) {
			throw new ValidationException("Date is required");
		}
		if (value.getScore() == null) {
			throw new ValidationException("Score is required");
		}
		if (value.getScore() <= 50) {
			throw new ValidationException("Score must be greater than 50");
		}
		if (value.getScore() > 100) {
			throw new ValidationException("Score must be less than or equals to 100");
		}
		Registration entity = RegistrationMapper.to(value);
		entity.setStudent(studentRepo.findByStudentId(value.getStudentId()).orElse(null));
		entity.setClazz(classRepo.findByCode(value.getClassCode()).orElse(null));
		return RegistrationMapper.to(repo.save(entity));
	}

	@Override
	public RegistrationDto update(RegistrationDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getStudentId() == null) {
			throw new ValidationException("Student is required");
		}
		if (value.getClassCode() == null) {
			throw new ValidationException("Class code is required");
		}
		if (value.getDate() == null) {
			throw new ValidationException("Date is required");
		}
		if (value.getScore() == null) {
			throw new ValidationException("Score is required");
		}
		if (value.getScore() <= 50) {
			throw new ValidationException("Score must be greater than 50");
		}
		if (value.getScore() > 100) {
			throw new ValidationException("Score must be less than or equals to 100");
		}
		if (value.getRegistrationId() <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}

		Registration entity = repo.findByregistrationId(value.getRegistrationId()).orElse(null);
		if (entity == null) {
			throw new ValidationException("Registration with Id " + value.getRegistrationId() + "was not found");
		}
		RegistrationMapper.to(value, entity);
		entity.setStudent(studentRepo.findByStudentId(value.getStudentId()).orElse(null));
		entity.setClazz(classRepo.findByCode(value.getClassCode()).orElse(null));
		return RegistrationMapper.to(repo.save(entity));
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
