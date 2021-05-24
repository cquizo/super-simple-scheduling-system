package bo.com.digitalharbor.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bo.com.digitalharbor.entity.Registration;

@Repository
public interface RegistrationRepo extends CrudRepository<Registration, Long> {
	Optional<Registration> findByregistrationId(Long id);
}
