package pl.magisterka.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name may not be empty")
	private String name;
	@Length(max=1000, message = "Description may not be longer than 1000 characters")
	private String description;

	private double evaluation;
	private double price;
	private int quantity = 0;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Category> categories = new ArrayList<>();
	public Product() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEvaluation() {
		return evaluation;
	}
		public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Product(String name, String description, double evaluation, double price, int quantity) {
		super();
		this.name = name;
		this.description = description;
		this.evaluation = evaluation;
		this.price = price;
		this.quantity = quantity;
		}

	@Override
	public String toString() {
		return "Product{" +
						"id=" + id +
						", name='" + name + '\'' +
						", description='" + description + '\'' +
						", evaluation=" + evaluation +
						", price=" + price +
						", quantity=" + quantity +
						", categories=" + categories +
						'}';
	}

}
