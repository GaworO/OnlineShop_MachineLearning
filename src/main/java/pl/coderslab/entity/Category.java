package pl.coderslab.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name may not be empty")
	private String name;
	@Column(name = "mean")
	private double  mean ;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Product> products = new ArrayList<Product>();
	public Category() {}
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
	public Category(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getMean() {
		return mean;
	}

	public double setMean(double mean) {
		return this.mean = mean;
	}




}
