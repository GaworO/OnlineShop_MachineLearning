package pl.coderslab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.Order;
import pl.coderslab.repository.OrderRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("")
	public String home() {
		return "orderHome";
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Order> list = orderRepository.findAll();
		model.addAttribute("orders", list);
		return "orderAll";
	}

}
