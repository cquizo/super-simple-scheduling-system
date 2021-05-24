package bo.com.digitalharbor.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bo.com.digitalharbor.entity.Class;

@Repository
public interface ClassRepo extends CrudRepository<Class, Long> {
	Optional<Class> findByCode(Long id);
}
