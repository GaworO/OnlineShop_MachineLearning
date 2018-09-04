package pl.coderslab.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.apache.commons.math3.linear;
//import javax.transaction.Transactional;
import static java.lang.Math.*;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import pl.coderslab.entity.Category;
import pl.coderslab.entity.Order;
import pl.coderslab.entity.OrderedItem;
import pl.coderslab.entity.Product;
import pl.coderslab.entity.User;
import pl.coderslab.model.Cart;
import pl.coderslab.model.ProductModel;
import pl.coderslab.model.UserModel;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.OrderRepository;
import pl.coderslab.repository.OrderedItemRepository;
import pl.coderslab.repository.ProductRepository;
import pl.coderslab.repository.UserRepository;
import scala.collection.immutable.Vector;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/shopuser")
public class ShopUserController extends SessionedController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	@Autowired
	private CategoryRepository categoryRepository;


	private double sum ;
	private double quantity ;
	private double result ;
	private double category0 ;
	private double category1 ;
	private double category2 ;
	private double category3 ;
	private double category4 ;



	@GetMapping({ "/home", "" })
	public String home(Model model) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		model.addAttribute("cart", "Cart: " + userModel.getCart().getProducts().size() + " items");
		model.addAttribute("productModel", new ProductModel());
		List<Product> products = productRepository.findAll();
		List<ProductModel> list = new ArrayList<ProductModel>();
		for (Product product : products) {
			ProductModel elem = new ProductModel();
			elem.setId(product.getId());
			elem.setName(product.getName());
			elem.setDescription(product.getDescription().split(";"));
			elem.setEvaluation(product.getEvaluation());
			elem.setPrice(product.getPrice());
			elem.setAmount(product.getQuantity());
			elem.setQuantity(0);
			list.add(elem);
		}

		model.addAttribute("products", list);
		return "shop/home";
	}
	
	@GetMapping("/searchCategory")
	public String searchCategory(Model model, @RequestParam Long id){
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		model.addAttribute("cart", "Cart: " + userModel.getCart().getProducts().size() + " items");
		model.addAttribute("productModel", new ProductModel());
		List<Product> products = productRepository.findAll();
		List<ProductModel> list = new ArrayList<ProductModel>();
		for (Product product : products) {
			ProductModel elem = new ProductModel();
			elem.setId(product.getId());
			elem.setName(product.getName());
			elem.setDescription(product.getDescription().split(";"));
			elem.setEvaluation(product.getEvaluation());
			elem.setPrice(product.getPrice());
			elem.setAmount(product.getQuantity());
			elem.setQuantity(0);
			list.add(elem);
		}
		model.addAttribute("products", list);
		return "shop/home";
	}


	@GetMapping("/find")
	@Transactional
	public String find(@RequestParam Long id, Model model) {
		Product product = productRepository.findById(id);
		String[] description = product.getDescription().split(";");
		model.addAttribute("productId", id);
		model.addAttribute("description", description);
		model.addAttribute("product", product);
		model.addAttribute("productModel", new ProductModel());
		return "productFind";
	}

	@PostMapping("/addtocart")
	public String addToCart(@ModelAttribute ProductModel productModel, Model model) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		List<ProductModel> list = userModel.getCart().getProducts();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(productModel.getId())) {
				list.get(i).increase(productModel.getQuantity());
				session().setAttribute("userModel", userModel);
				return "redirect:/shopuser/home";
			}
		}
		userModel.addToCart(productModel);
		session().setAttribute("userModel", userModel);
		return "redirect:/shopuser/home";
	}

	@GetMapping("/cart")
	public String cart(Model model , @ModelAttribute Category category,@ModelAttribute ProductModel productModel) {
		UserModel userModel = new UserModel();
		List<Product> products = productRepository.findAll();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		category0 = userModel.getCart().getPriceCatgory0();
		category1 = userModel.getCart().getPriceCatgory1();
		category2 = userModel.getCart().getPriceCatgory2();
		category3 = userModel.getCart().getPriceCatgory3();
		category4 = userModel.getCart().getPriceCatgory4();


		double[][] pointsArray = new double[][]{
						new double[]{category0, category0},
						new double[]{category0, category1},
						new double[]{category1, category1},
						new double[]{category1, category2},
						new double[]{category2, category2},
						new double[]{category2, category3},
						new double[]{category3, category3},
						new double[]{category3, category4},
						new double[]{category4, category4},
		};

		RealMatrix realMatrix = MatrixUtils.createRealMatrix(pointsArray);
		Covariance covariance = new Covariance(realMatrix);

		RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
		EigenDecomposition ed = new EigenDecomposition(covarianceMatrix);

		int deteminant = (int) ed.getDeterminant();
		System.out.println("____________________________-");
    System.out.println(deteminant);
		BigDecimal roundOff = new BigDecimal(deteminant, MathContext.DECIMAL64);
		System.out.println(roundOff);


		sum = userModel.getCart().getSumPrice();
		quantity = userModel.getCart().getQuantity();
		result = sum / quantity;


		JSONObject obj;
		org.json.simple.JSONObject inputs;
		JSONObject global;
		JSONObject finale;

		inputs = new JSONObject();
		global = new JSONObject();
		obj = new JSONObject();
		finale = new JSONObject();
		global.put("Inputs", inputs);
		inputs.put("input1", obj);


		JSONArray names = new JSONArray();
		names.add("mean");
		names.add("categ_0");
		names.add("categ_1");
		names.add("categ_2");
		names.add("categ_3");
		names.add("categ_4");
		names.add("cluster");


		obj.put("ColumnNames", names);


		JSONArray values1 = new JSONArray();
		values1.add(category.setMean(result));
		values1.add(category0);
		values1.add(category1);
		values1.add(category2);
		values1.add(category3);
		values1.add(category4);
		values1.add(roundOff);


		JSONArray values = new JSONArray();
		values.add(values1);


		obj.put("Values", values);

		JSONObject jsonObject = new JSONObject();
		global.put("GlobalParameters", jsonObject);

		try {
			FileWriter file = new FileWriter("data.json");
			file.write(global.toJSONString());
			System.out.print("Sucessfully copied object to JSON ");
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
			Rest r = new Rest();
			String response = r.rrsHttpPost(global.toJSONString());
			System.out.println("_____________________________________________________________________");
			System.out.println(response);


			model.addAttribute("products", userModel.getCart().getProducts());
			return "shop/cart";
		}

	@GetMapping("/buy")
	public String buy(@RequestParam Long[] id, @RequestParam int[] quantity, Model model , @ModelAttribute Category category) {
		if (session().getAttribute("user") != null) {
			Order order = new Order();
			order.setAmount(0);
			for (int i = 0; i < id.length; i++) {
				Product product = productRepository.findById(id[i]);
				product.setQuantity(product.getQuantity() - quantity[i]);
				productRepository.save(product);
				OrderedItem orderedItem = new OrderedItem();
				orderedItem.setName(product.getName());
				orderedItem.setPrice(product.getPrice());
				orderedItem.setQuantity(quantity[i]);
				orderedItemRepository.save(orderedItem);
				order.addToOrder(orderedItem);
				order.setUser((User) session().getAttribute("user"));
				order.increaseAmount(product.getPrice() * quantity[i]);
				orderRepository.save(order);

			}
			UserModel userModel = (UserModel) session().getAttribute("userModel");
			userModel.setCart(new Cart());
			session().setAttribute("userModel", userModel);
			return "redirect:/shopuser/home";
		} else {
			UserModel userModel = (UserModel) session().getAttribute("userModel");
			session().setAttribute("userModelToRegister", userModel);
			model.addAttribute("msg", "You must be logged in to buy");
			return "home";
		}
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		UserModel userModel = new UserModel();
		if (session().getAttribute("userModel") != null) {
			userModel = (UserModel) session().getAttribute("userModel");
		} else {
			userModel.setCart(new Cart());
		}
		List<ProductModel> list = userModel.getCart().getProducts();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				list.remove(list.get(i));
			}
		}
		return "redirect:/shopuser/cart";
	}

	@GetMapping("/comment")
	@Transactional
	public String comment(@RequestParam Long id, Model model) {
		Product product = productRepository.findById(id);
		model.addAttribute("productId", id);
		return "shop/commentShow";
	}

	@ModelAttribute("availableProducts")
	public List<Product> availableProducts() {
		return productRepository.findAll();
	}

	@ModelAttribute("availableUsers")
	public List<User> availableUsers() {
		return userRepository.findAll();
	}
	
	@ModelAttribute("avaiableCategories")
	public List<Category> availableCategories(){
		return categoryRepository.findAll();
	}

	@GetMapping("login")
	@ResponseBody
	public String login() {
		String str = "<span><a href=\"/login\">Log in</a></span><span><a href=\"/register\">Register</a></span>";
		if (session().getAttribute("user") != null) {
			User user = (User) session().getAttribute("user");
			str = "<span>Logged in as " + user.getUsername() + "</span><span><a href=\"/logout\">Log out</a></span>";
		}
		return str;
	}

}
