package pl.magisterka.model;

import pl.magisterka.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	List<ProductModel> products = new ArrayList<ProductModel>();
	List<Product> prod = new ArrayList<Product>();
	public 	double price ;
	public 	double price1 ;
	public double quantity;
	public Cart() {}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}


public double getProductPrice() {
		for (ProductModel product : products){
			price = product.getPrice();
		}
		return price ;
	}

	public double getSumPrice() {
		for (ProductModel product : products){
			price1 += product.getPrice();
		}
		return price1 ;
	}


	public double getQuantity(){
		for (Product pro : prod){
			quantity = pro.getQuantity();
		}
		return quantity;
	}



}
