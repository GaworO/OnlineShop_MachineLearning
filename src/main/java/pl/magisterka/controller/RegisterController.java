package pl.magisterka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.magisterka.entity.User;
import pl.magisterka.model.Cart;
import pl.magisterka.model.UserModel;
import pl.magisterka.repository.UserRepository;

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
		System.out.println(user.getPassword());
		this.repoUser.save(user);
		System.out.println(user.getPassword());
		uModel.setUser(user);
		session().setAttribute("user", user);
		session().setAttribute("name", user.getUsername());
		return "redirect:/shopuser/home";
	}

}
