package bo.com.digitalharbor.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.com.digitalharbor.dto.StudentDto;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.serv.StudentServ;

@Controller
@RequestMapping("/app/student")
public class StudentCtrl {

	@Autowired
	private StudentServ serv;

	@GetMapping("/list")
	public String list(Model model) throws ServiceException {
		StudentDto value =new StudentDto();
		model.addAttribute("list", serv.filter(value));
		model.addAttribute("student", value);
		return "student/list";
	}

	@GetMapping("/new")
	public String neww(Model model) {
		return "student/new";
	}

	@PostMapping("/list")
	public String list(StudentDto value, Model model) throws ServiceException {
		model.addAttribute("list", serv.filter(value));
		model.addAttribute("student", value);
		return "student/list";
	}

	private Map<String, String> validate(StudentDto value) {
		Map<String, String> errors = new HashMap<>();
		if (value.getFirstName().isEmpty()) {
			errors.put("firstName", "First Name is required");
		}
		if (value.getLastName().isEmpty()) {
			errors.put("lastName", "Last Name is required");
		}
		return errors;
	}

	@PostMapping("/new")
	public String create(StudentDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "student/new";
		}
		serv.create(value);
		return "redirect:/app/student/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) throws ServiceException {
		StudentDto entity = serv.findById(id);
		model.addAttribute("student", entity);
		return "student/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id, StudentDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "student/edit";
		}
		serv.update(value);
		return "redirect:/app/student/list";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") long id, Model model) throws ServiceException {
		StudentDto entity = serv.findById(id);
		model.addAttribute("student", entity);
		return "student/info";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) throws ServiceException {
		serv.delete(id);
		return "redirect:/app/student/list";
	}

}
