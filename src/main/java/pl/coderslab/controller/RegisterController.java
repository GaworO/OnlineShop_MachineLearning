package pl.coderslab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.User;
import pl.coderslab.model.Cart;
import pl.coderslab.model.UserModel;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController extends SessionedController{
	@Autowired
	UserRepository repoUser;
	
	@GetMapping("")
	public String register(Model m) {
		User user=new User();
		user.setEnabled(false);
		m.addAttribute("user",user );
		
		return "register";
	}

	@PostMapping("")
	public String registerPost(@Valid User user, BindingResult result, Model model) {
		UserModel uModel;
		if(session().getAttribute("userModel")!=null){
			uModel = (UserModel) session().getAttribute("userModel");
		}
		else {
			uModel = new UserModel();
			uModel.setCart(new Cart());		
		}
		session().setAttribute("userModel", uModel);
		if(result.hasErrors()) {
			return "register";
		}
		if(repoUser.findByEmail(user.getEmail())!=null) {
			model.addAttribute("msg", "Email " + user.getEmail() + " is already registered");
			return "home";
		}
//		this.repoUser.save(user);
		uModel.setUser(user);
		session().setAttribute("user", user);
		session().setAttribute("name", user.getUsername());
		return "redirect:/home";
	}

}
