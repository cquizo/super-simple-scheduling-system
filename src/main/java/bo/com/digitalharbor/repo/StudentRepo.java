package bo.com.digitalharbor.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bo.com.digitalharbor.entity.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
	Optional<Student> findByStudentId(Long id);
}
