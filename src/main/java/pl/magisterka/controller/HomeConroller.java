package pl.magisterka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeConroller extends SessionedController {

	@GetMapping({ "", "/home" })
	public String home(Model m) {
		if (session().getAttribute("user") != null) {
			m.addAttribute("msg", "zalogowano");
		}
		return "home";
	}

}
