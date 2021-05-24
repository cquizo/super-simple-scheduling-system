package bo.com.digitalharbor.serv;

import java.util.List;

import bo.com.digitalharbor.dto.RegistrationDto;
import bo.com.digitalharbor.exception.ServiceException;

public interface RegistrationServ {

	List<RegistrationDto> filter(RegistrationDto value) throws ServiceException;

	RegistrationDto findById(Long id) throws ServiceException;

	RegistrationDto create(RegistrationDto value) throws ServiceException;

	RegistrationDto update(RegistrationDto value) throws ServiceException;

	void delete(Long id) throws ServiceException;

}
