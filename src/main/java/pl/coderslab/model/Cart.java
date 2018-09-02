package pl.coderslab.model;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Product;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.ProductRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
	List<ProductModel> products = new ArrayList<ProductModel>();
	List<Product> prod = new ArrayList<>();
	public double price;
	public int sum;
	public double quantity;
	public double first;
	public double priceCategory;
	public double quantityCategory;
	public double priceCategory0;
	public double quantityCategory0;
	public double first0 ;
	public double priceCategory1;
	public double quantityCategory1;
	public double first1 ;
	public double priceCategory2;
	public double quantityCategory2;
	public double first2 ;
	public double priceCategory3;
	public double quantityCategory3;
	public double first3 ;

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}


	public double getSumPrice() {
		for (int i = 0; i <= products.size(); i++) {
			priceCategory0 = products.get(0).getPrice();
			quantityCategory0 = products.get(0).getQuantity();
			first0 = priceCategory0 * quantityCategory0;
			priceCategory1 = products.get(1).getPrice();
			quantityCategory1 = products.get(1).getQuantity();
			first1 = priceCategory1 * quantityCategory1;
			priceCategory2 = products.get(2).getPrice();
			quantityCategory2 = products.get(2).getQuantity();
			first2 = priceCategory2 * quantityCategory2;
			priceCategory3 = products.get(3).getPrice();
			quantityCategory3 = products.get(3).getQuantity();
			first3 = priceCategory3 * quantityCategory3;

			sum = (int) (first0 + first1 + first2 + first3) ;
		}
		return sum;
	}

	public double getPriceCatgory1() {

		for (int i = 0; i <= products.size(); i++) {
			priceCategory = products.get(0).getPrice();
			quantityCategory = products.get(0).getQuantity();
			first = priceCategory * quantityCategory;
		}
		return first;
	}

	public double getPriceCatgory2() {

		for (int i = 0; i <= products.size(); i++) {
			priceCategory = products.get(1).getPrice();
			quantityCategory = products.get(1).getQuantity();
			first = priceCategory * quantityCategory;
		}
		return first;
	}

	public double getPriceCatgory3() {

		for (int i = 0; i <= products.size(); i++) {
			priceCategory = products.get(2).getPrice();
			quantityCategory = products.get(2).getQuantity();
			first = priceCategory * quantityCategory;
		}
		return first;
	}

	public double getPriceCatgory4() {

		for (int i = 0; i <= products.size(); i++) {
			priceCategory = products.get(3).getPrice();
			quantityCategory = products.get(3).getQuantity();
			first = priceCategory * quantityCategory;
		}
		return first;
	}

	public double getQuantity(){
		for (int i = 0; i <= products.size(); i++) {
			quantityCategory0 = products.get(0).getQuantity();
			quantityCategory1 = products.get(1).getQuantity();
			quantityCategory2 = products.get(2).getQuantity();
			quantityCategory3 = products.get(3).getQuantity();
      quantity = quantityCategory0 + quantityCategory1 + quantityCategory2 +quantityCategory3 ;
		}
		return quantity ;
	}

}
