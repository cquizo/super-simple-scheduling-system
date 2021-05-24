package bo.com.digitalharbor.ctrl.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bo.com.digitalharbor.dto.RegistrationDto;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.serv.RegistrationServ;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestCtrl {

	@Autowired
	private RegistrationServ serv;

	@GetMapping("/list")
	public List<RegistrationDto> list(@RequestBody RegistrationDto value) throws ServiceException {
		return serv.filter(value);
	}

	@GetMapping("/find/{id}")
	public RegistrationDto find(@PathVariable Long id) throws ServiceException {
		return serv.findById(id);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistrationDto create(@RequestBody RegistrationDto value) throws ServiceException {
		return serv.create(value);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistrationDto update(@RequestBody RegistrationDto value, @PathVariable Long id) throws ServiceException {
		value.setRegistrationId(id);
		return serv.update(value);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws ServiceException {
		serv.delete(id);
	}
}
