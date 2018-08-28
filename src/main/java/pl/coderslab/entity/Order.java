package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@ManyToMany
	private List<OrderedItem> items = new ArrayList<OrderedItem>();
	@NotNull
	@ManyToOne
	private User user;
	private double amount;
	private double sum ;
	public Order() {}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void addToOrder(OrderedItem item) {
		this.items.add(item);
	}
	public void increaseAmount(double amount) {
		this.amount += amount;
	}

	public List<OrderedItem> getItems() {
		return items;
	}

	public void setItems(List<OrderedItem> items) {
		this.items = items;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}
