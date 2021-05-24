package bo.com.digitalharbor.serv;

import java.util.List;

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.exception.ServiceException;

public interface ClassServ {

	List<ClassDto> filter(ClassDto value) throws ServiceException;

	ClassDto findById(Long id) throws ServiceException;

	ClassDto create(ClassDto value) throws ServiceException;

	ClassDto update(ClassDto value) throws ServiceException;

	void delete(Long id) throws ServiceException;

}
