package pl.magisterka.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.magisterka.entity.Category;
import pl.magisterka.entity.Product;
import pl.magisterka.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@GetMapping("")
	public String category() {
		return "categoryHome";
	}

	@GetMapping("/all")
	public String all(Model model) {
		List<Category> list = categoryRepository.findAll();
		model.addAttribute("categories", list);
		return "categoryAll";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "categoryAdd";
	}
	
	@PostMapping({"/add", "/edit"})
	public String save(@Valid Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "categoryAdd";
		}
		categoryRepository.save(category);
		return "categoryHome";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam Long id, Model model) {
		Category category = categoryRepository.findById(id);
		model.addAttribute("category", category);
		return "categoryEdit";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		categoryRepository.delete(categoryRepository.findById(id));
		return "categoryHome";
	}

	@GetMapping("/products")
	@Transactional
	public String products(@RequestParam Long id, Model model) {
		Category category = categoryRepository.findById(id);
		Hibernate.initialize(category.getProducts());
		List<Product> products = category.getProducts();
		System.out.println(category.getProducts());
		model.addAttribute("products", products);
		return "productAll";
	}

}
