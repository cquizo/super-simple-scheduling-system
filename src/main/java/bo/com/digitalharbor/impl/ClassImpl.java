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

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.entity.Class;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.exception.ValidationException;
import bo.com.digitalharbor.mapper.ClassMapper;
import bo.com.digitalharbor.mapper.StudentMapper;
import bo.com.digitalharbor.repo.ClassRepo;
import bo.com.digitalharbor.serv.ClassServ;

@Service
public class ClassImpl implements ClassServ {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ClassRepo repo;

	@Override
	public List<ClassDto> filter(ClassDto value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Class> query = cb.createQuery(Class.class);
		Root<Class> root = query.from(Class.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.isNotNull(root.get("code")));
		if (value.getTitle() != null && !value.getTitle().isEmpty()) {
			predicates.add(cb.like(cb.upper(root.get("title")), value.getTitle().toUpperCase().trim()));
		}
		if (value.getDescription() != null && !value.getDescription().isEmpty()) {
			predicates.add(cb.like(cb.upper(root.get("description")), value.getDescription().toUpperCase().trim()));
		}
		query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return ClassMapper.toDto(em.createQuery(query).getResultList());
	}

	@Override
	@Transactional(readOnly = true)
	public ClassDto findById(Long id) throws ServiceException {
		if (id == null) {
			throw new ValidationException("Id is required");
		}
		if (id <= 0L) {
			throw new ValidationException("Id is an invalid value");
		}
		Class entity = repo.findByCode(id).orElse(null);
		if (entity == null) {
			throw new ValidationException("Class with Id " + id + "was not found");
		}
		ClassDto dto = ClassMapper.to(entity);
		if (entity.getRegistrationList() == null) {
			return dto;
		}
		entity.getRegistrationList().forEach(row -> {
			dto.add(StudentMapper.to(row.getStudent()));
		});
		return dto;
	}

	@Override
	public ClassDto create(ClassDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getTitle() == null) {
			throw new ValidationException("Title is required");
		}
		if (value.getDescription() == null) {
			throw new ValidationException("Description is required");
		}
		return ClassMapper.to(repo.save(ClassMapper.to(value)));
	}

	@Override
	public ClassDto update(ClassDto value) throws ServiceException {
		if (value == null) {
			throw new ValidationException("Value is required");
		}
		if (value.getCode() == null) {
			throw new ValidationException("Code is required");
		}
		if (value.getCode() <= 0L) {
			throw new ValidationException("Code is an invalid value");
		}
		if (value.getTitle() == null) {
			throw new ValidationException("Title is required");
		}
		if (value.getDescription() == null) {
			throw new ValidationException("Description is required");
		}
		Class entity = repo.findByCode(value.getCode()).orElse(null);
		if (entity == null) {
			throw new ValidationException("Class with Id " + value.getCode() + "was not found");
		}
		ClassMapper.to(value, entity);
		return ClassMapper.to(repo.save(entity));
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
