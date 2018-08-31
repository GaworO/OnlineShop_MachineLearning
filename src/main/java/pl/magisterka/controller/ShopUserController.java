package pl.magisterka.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.magisterka.entity.Category;
import pl.magisterka.entity.Order;
import pl.magisterka.entity.OrderedItem;
import pl.magisterka.entity.Product;
import pl.magisterka.entity.User;
import pl.magisterka.model.Cart;
import pl.magisterka.model.ProductModel;
import pl.magisterka.model.UserModel;
import pl.magisterka.repository.CategoryRepository;
import pl.magisterka.repository.OrderRepository;
import pl.magisterka.repository.OrderedItemRepository;
import pl.magisterka.repository.ProductRepository;
import pl.magisterka.repository.UserRepository;

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


		sum = userModel.getCart().getSumPrice();

		quantity = userModel.getCart().getQuantity();


//		result = sum/quantity;
//		System.out.print(result);
		System.out.println(sum);
		System.out.println(quantity);

		JSONObject obj;
		org.json.simple.JSONObject inputs;
		JSONObject global;
		List<JSONObject> json = new ArrayList<JSONObject>();

		inputs = new JSONObject();
		global = new JSONObject();
		obj = new JSONObject();
		global.put("Inputs" , (inputs.put("input1", obj)));



		JSONArray names = new JSONArray();
		names.add( "mean");
		names.add( "categ_0");
		names.add( "categ_1");
		names.add( "categ_2");
		names.add( "categ_3");
		names.add( "cluster");


		obj.put("ColumnNames", names);


		JSONArray values = new JSONArray();
		values.add(category.setMean(result));
		values.add(userModel.getCart().getProductPrice());
		values.add(category.getCluster());

		obj.put("Values", values);

		JSONArray myArray = new JSONArray();
		obj.put("GlobalParameters" , myArray );

		json.add(inputs);
		json.add(global);

//		Gson gson = new Gson();
//	  gson.toJson(json);

		try {
			FileWriter file = new FileWriter("data.json");
			file.write(inputs.toJSONString());
			System.out.print("Sucessfully copied object to JSON ");
			file.flush();
			file.close();

		} catch (IOException e){
			e.printStackTrace();
		}


		model.addAttribute("products", userModel.getCart().getProducts());

		Rest.readJson("C:\\\\Users\\\\Ola\\\\Desktop\\\\MediaShop-master\\\\src\\\\main\\\\resources\\\\data.json");
//		File file = new File("C:/Users/Ola/Desktop/MediaShop-master/src/main/resources/api.txt").getAbsolutePath();


		Rest.readApiInfo("C:\\\\Users\\\\Ola\\\\Desktop\\\\MediaShop-master\\\\src\\\\main\\\\resources\\\\api.txt");
//
//		String[] myarray = {"data.json" , zdanie };
//		Rest.main(myarray);
		return "shop/cart";
	}


//	https://stackoverflow.com/questions/10604507/pca-implementation-in-java
//	//create points in a double array
//	double[][] pointsArray = new double[][] {
//					new double[] { -1.0, -1.0 },
//					new double[] { -1.0, 1.0 },
//					new double[] { 1.0, 1.0 } };
//
//	//create real matrix
//	RealMatrix realMatrix = MatrixUtils.createRealMatrix(pointsArray);
//
////create covariance matrix of points, then find eigen vectors
////see https://stats.stackexchange.com/questions/2691/making-sense-of-principal-component-analysis-eigenvectors-eigenvalues
//
//	Covariance covariance = new Covariance(realMatrix);
//	RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
//	EigenDecomposition ed = new EigenDecomposition(covarianceMatrix);



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
