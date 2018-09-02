package pl.coderslab.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Product;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("")
	public String product() {
		return "productHome";
	}

	@GetMapping("/all")
	public String all(Model model) {
		List<Product> list = productRepository.findAll();
		model.addAttribute("products", list);
		return "productAll";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		return "productAdd";
	}

	@PostMapping({ "/add", "/edit" })
	public String save(@Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "productAdd";
		}
		productRepository.save(product);
		return "productHome";
	}

	@GetMapping("edit")
	@Transactional
	public String edit(@RequestParam Long id, Model model) {
		Product product = productRepository.findById(id);
		Hibernate.initialize(product.getCategories());
		model.addAttribute("product", product);
		return "productEdit";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		productRepository.delete(productRepository.findById(id));
		return "productHome";
	}

	@ModelAttribute("availableCategories")
	public List<Category> availableCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/setevaluation")
	public String setEvaluation() {
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			productRepository.save(product);
		}
		return "productHome";
	}

}
