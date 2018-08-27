package pl.coderslab.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	List<ProductModel> products = new ArrayList<ProductModel>();
	
	public Cart() {}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}
	

}
