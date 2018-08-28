package pl.coderslab.model;

import pl.coderslab.entity.User;

public class UserModel {
	private User user;
	private Cart cart;
	private String username;
	private String password;
	private String email;
	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public UserModel() {}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserModel(User user, Cart cart) {
		super();
		this.user = user;
		this.cart = cart;
	}
	public void addToCart(ProductModel productModel) {
		this.cart.getProducts().add(productModel);
	}
	
	
}
