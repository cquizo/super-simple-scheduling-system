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

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.serv.ClassServ;

@RestController
@RequestMapping("/api/class")
public class ClassRestCtrl {

	@Autowired
	private ClassServ serv;

	@GetMapping("/list")
	public List<ClassDto> list(@RequestBody ClassDto value) throws ServiceException {
		return serv.filter(value);
	}

	@GetMapping("/find/{id}")
	public ClassDto find(@PathVariable Long id) throws ServiceException {
		return serv.findById(id);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ClassDto create(@RequestBody ClassDto value) throws ServiceException {
		return serv.create(value);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ClassDto update(@RequestBody ClassDto value, @PathVariable Long id) throws ServiceException {
		value.setCode(id);
		return serv.update(value);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws ServiceException {
		serv.delete(id);
	}
}
