package pl.magisterka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.magisterka.entity.User;
import pl.magisterka.model.Cart;
import pl.magisterka.model.UserModel;
import pl.magisterka.repository.UserRepository;

@Controller
public class LoginController extends SessionedController {

	@Autowired
	UserRepository repoUser;

	@GetMapping("/login")
	public String login(Model m) {
		return "login";
	}

	@PostMapping("login")
	public String loginPost(@RequestParam String email, @RequestParam String password, Model m) {
		UserModel uModel;
		if (session().getAttribute("userModel") != null) {
			uModel = (UserModel) session().getAttribute("userModel");
		} else {
			uModel = new UserModel();
			uModel.setCart(new Cart());
		}
		session().setAttribute("userModel", uModel);
		User u = this.repoUser.findByEmail(email);
		if (u == null || !(u.isPasswordCorrect(password))) {
			m.addAttribute("msg", "Invalid login data");
			return "home";
		} else {
			session().setAttribute("user", u);
			uModel.setUser(u);
			session().setAttribute("userModel", uModel);
			session().setAttribute("name", u.getUsername());
		}
		return "redirect:/shopuser/home";
	}

	@GetMapping("/logout")
	public String logout() {
		session().invalidate();
		return "redirect:/shopuser/home";
	}
}
