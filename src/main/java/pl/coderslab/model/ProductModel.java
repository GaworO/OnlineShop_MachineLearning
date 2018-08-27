package pl.coderslab.model;

public class ProductModel {
	private Long id;
	private String name;
	private double evaluation;
	private int quantity;
	private double price;
	private String[] description;
	private int amount;
	public ProductModel() {}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}
	public String[] getDescription() {
		return description;
	}
	public void setDescription(String[] description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void increase(int quantity) {
		this.quantity += quantity;
	}
	public void splitDescription(String description) {
		this.description = description.split(";");
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
}
