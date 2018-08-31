package pl.magisterka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.magisterka.entity.User;
import pl.magisterka.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public String user() {
		return "userHome";
	}

	@GetMapping("/all")
	public String all(Model model) {
		List<User> list = userRepository.findAll();
		model.addAttribute("users", list);
		return "userAll";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "userAdd";
	}

	@PostMapping({ "/add", "/edit" })
	public String save(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "userAdd";
		}
		userRepository.save(user);
		return "userHome";
	}

	@GetMapping("edit")
	public String edit(@RequestParam Long id, Model model) {
		User user = userRepository.findById(id);
		model.addAttribute("user", user);
		return "userEdit";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		userRepository.delete(userRepository.findById(id));
		return "userHome";
	}

	@GetMapping("/disable")
	public String disable(@RequestParam Long id) {
		User user = userRepository.findById(id);
		user.disable();
		userRepository.save(user);
		return "userHome";
	}

	@GetMapping("/enable")
	public String ensable(@RequestParam Long id) {
		User user = userRepository.findById(id);
		user.enable();
		userRepository.save(user);
		return "userHome";
	}



}
