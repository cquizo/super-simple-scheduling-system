package bo.com.digitalharbor.serv;

import java.util.List;

import bo.com.digitalharbor.dto.StudentDto;
import bo.com.digitalharbor.exception.ServiceException;

public interface StudentServ {

	List<StudentDto> filter(StudentDto value) throws ServiceException;

	StudentDto findById(Long id) throws ServiceException;

	StudentDto create(StudentDto value) throws ServiceException;

	StudentDto update(StudentDto value) throws ServiceException;

	void delete(Long id) throws ServiceException;

}
