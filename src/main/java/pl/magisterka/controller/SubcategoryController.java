//package pl.coderslab.controller;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//import javax.validation.Valid;
//
//import org.hibernate.Hibernate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import pl.coderslab.entity.Category;
//import pl.coderslab.entity.Product;
//import pl.coderslab.repository.CategoryRepository;
//import pl.coderslab.repository.ProductRepository;
//
//@Controller
//@RequestMapping("/subcategory")
//public class SubcategoryController {
//
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private ProductRepository productRepository;
//
//	@GetMapping("")
//	public String subcategory() {
//		return "subcategoryHome";
//	}
//
//	@GetMapping("/all")
//	public String all(Model model) {
//		List<Subcategory> list = subcategoryRepository.findAll();
//		model.addAttribute("subcategories", list);
//		return "subcategoryAll";
//	}
//
//	@GetMapping("/add")
//	public String add(Model model) {
//		model.addAttribute("subcategory", new Subcategory());
//		return "subcategoryAdd";
//	}
//
//	@PostMapping({"/add", "/edit"})
//	public String save(BindingResult result) {
//		if (result.hasErrors()) {
//			return "subcategoryAdd";
//		}
//		subcategoryRepository.save(subcategory);
//		return "subcategoryHome";
//	}
//
//	@GetMapping("edit")
//	public String edit(@RequestParam Long id, Model model) {
//		Subcategory subcategory = subcategoryRepository.findById(id);
//		model.addAttribute("subcategory", subcategory);
//		return "subcategoryEdit";
//	}
//
//	@GetMapping("/delete")
//	@Transactional
//	public String delete(@RequestParam Long id) {
//		Subcategory subcategory = subcategoryRepository.findById(id);
//		Hibernate.initialize(subcategory.getProducts());
//		List<Product> products = productRepository.findBySubcategoriesId(id);
//		for (Product product : products) {
//			product.setSubcategories(null);
//		}
//		Category category = subcategory.getCategory();
//		category.setSubcategories(null);
//		subcategoryRepository.delete(subcategory);
//		return "subcategoryHome";
//	}
//
//	@GetMapping("/products")
//	@Transactional
//	public String products(@RequestParam Long id, Model model) {
//		Subcategory subcategory = subcategoryRepository.findById(id);
//		Hibernate.initialize(subcategory.getProducts());
//		List<Product> products = subcategory.getProducts();//productRepository.findBySubcategoriesId(id);
//		System.out.println(subcategory.getProducts());
//		model.addAttribute("products", products);
//		return "productAll";
//	}
//
//	@ModelAttribute("availableCategories")
//	public List<Category> availableCategories() {
//		return categoryRepository.findAll();
//	}
//
////	@GetMapping("/subcategory")
////	@ResponseBody
////	public String subcategories(@RequestParam("id") long id , @ModelAttribute Category category) {
////		JSONObject obj;
////		JSONObject inputs;
////		JSONObject global;
////		List<JSONObject> json = new ArrayList<JSONObject>();
////
////		inputs = new JSONObject();
////		global = new JSONObject();
////		obj = new JSONObject();
////		inputs.put("input1", obj);
////
////		JSONArray names = new JSONArray();
////		names.add("mean");
////		names.add("categ_0");
////		names.add("categ_1");
////		names.add("categ_2");
////		names.add("categ_3");
////		names.add("cluster");
////
////		obj.put("ColumnNames", names);
////
////
////		JSONArray values = new JSONArray();
////		values.add(category.getMean());
////		values.add(categoryRepository.findAll());
////
////
////		inputs.put("ColumnNames", values);
////
////		JSONArray myArray = new JSONArray();
////		global.put("GlobalParameters", myArray);
////
////		json.add(inputs);
////		json.add(global);
////
////		Gson gson = new Gson();
////		return gson.toJson(json);
////	}
//
//
//
//}
