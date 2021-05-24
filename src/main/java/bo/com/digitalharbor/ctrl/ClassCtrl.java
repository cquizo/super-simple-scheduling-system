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

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.exception.ServiceException;
import bo.com.digitalharbor.serv.ClassServ;

@Controller
@RequestMapping("/app/class")
public class ClassCtrl {

	@Autowired
	private ClassServ serv;

	@GetMapping("/list")
	public String list(Model model) throws ServiceException {
		ClassDto value = new ClassDto();
		model.addAttribute("class", value);
		model.addAttribute("list", serv.filter(value));
		return "class/list";
	}

	@GetMapping("/new")
	public String neww(Model model) {
		return "class/new";
	}

	@PostMapping("/list")
	public String list(ClassDto value, Model model) throws ServiceException {
		model.addAttribute("list", serv.filter(value));
		model.addAttribute("class", value);
		return "class/list";
	}

	private Map<String, String> validate(ClassDto value) {
		Map<String, String> errors = new HashMap<>();
		if (value.getTitle().isEmpty()) {
			errors.put("title", "Title is required");
		}
		if (value.getDescription().isEmpty()) {
			errors.put("description", "Description is required");
		}
		return errors;
	}

	@PostMapping("/new")
	public String create(ClassDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "class/new";
		}
		serv.create(value);
		return "redirect:/app/class/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) throws ServiceException {
		ClassDto entity = serv.findById(id);
		model.addAttribute("class", entity);
		return "class/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id, ClassDto value, Model model) throws ServiceException {
		Map<String, String> errors = this.validate(value);
		if (!errors.isEmpty()) {
			model.addAttribute("error", errors);
			return "class/edit";
		}
		serv.update(value);
		return "redirect:/app/class/list";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") long id, Model model) throws ServiceException {
		ClassDto entity = serv.findById(id);
		model.addAttribute("class", entity);
		return "class/info";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) throws ServiceException {
		serv.delete(id);
		return "redirect:/app/class/list";
	}

}
