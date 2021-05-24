package bo.com.digitalharbor.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.dto.RegistrationDto;
import bo.com.digitalharbor.dto.StudentDto;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.serv.ClassServ;
import bo.com.digitalharbor.serv.RegistrationServ;
import bo.com.digitalharbor.serv.StudentServ;

@Controller
@RequestMapping("/app/registration")
public class RegistrationCtrl {

	@Autowired
	private RegistrationServ serv;
	@Autowired
	private StudentServ studentServ;
	@Autowired
	private ClassServ classServ;

	@GetMapping("/list")
	public String list(Model model) throws ServiceException {
		RegistrationDto value = new RegistrationDto();
		model.addAttribute("registration", value);
		model.addAttribute("list", serv.filter(value));
		return "registration/list";
	}

	@GetMapping("/new")
	public String neww(Model model) {
		return "registration/new";
	}

	@PostMapping("/list")
	public String list(RegistrationDto value, Model model) throws ServiceException {
		model.addAttribute("list", serv.filter(value));
		model.addAttribute("registration", value);
		return "registration/list";
	}

	@ModelAttribute("studentList")
	public List<StudentDto> studentList() throws ServiceException {
		return studentServ.filter(new StudentDto());
	}

	@ModelAttribute("classList")
	public List<ClassDto> classList() throws ServiceException {
		return classServ.filter(new ClassDto());
	}

	private Map<String, String> validate(RegistrationDto value) {
		Map<String, String> errors = new HashMap<>();
		if (value.getStudentId() == null) {
			errors.put("studentId", "Student is required");
		}
		if (value.getClassCode() == null) {
			errors.put("classCode", "Class is required");
		}
		if (value.getDate() == null) {
			errors.put("date", "Date is required");
		}
		if (value.getScore() == null) {
			errors.put("score", "Score is required");
			return errors;
		}
		if (value.getScore().intValue() <= 50) {
			errors.put("score", "Score most be greater than 50 ");
		}
		if (value.getScore().intValue() > 100) {
			errors.put("score", "Score most be less than or equals to 100");
		}
		return errors;
	}

	@PostMapping("/new")
	public String create(RegistrationDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "registration/new";
		}
		serv.create(value);
		return "redirect:/app/registration/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) throws ServiceException {
		RegistrationDto entity = serv.findById(id);
		model.addAttribute("registration", entity);
		return "registration/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id, RegistrationDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "registration/edit";
		}
		serv.update(value);
		return "redirect:/app/registration/list";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") long id, Model model) throws ServiceException {
		RegistrationDto entity = serv.findById(id);
		model.addAttribute("registration", entity);
		return "registration/info";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) throws ServiceException {
		serv.delete(id);
		return "redirect:/app/registration/list";
	}

}
