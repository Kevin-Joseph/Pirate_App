import java.util.Vector;

public class Order {
	
	protected Customer customer;
	protected String date;
	protected Vector<Product> products = new Vector<Product>();
	protected double total;
	protected boolean hasDiscount;
	protected double discount;
	
	//*****************************************************Constructor
	public Order() {
		date ="" + java.time.LocalDate.now();
		total = 0.0;
		hasDiscount = false;
		discount = 0.0;
	}
	
	//****************************************************Getters
	public double getTotal() {
		return total;
	}
	public boolean checkHasDisount() {
		return hasDiscount;
	}
	public double getDiscount() {
		return discount;
	}
	
	//*****************************************************Setters
	public void setCustomer(Customer c) {
		customer = c;
	}
	
	//add item to products vector
	public void addProduct(Product p) {
		products.add(p);
	}
	public void removeProduct(int index) {
		products.remove(index);
	}
	
	//might need a getIndex function.
	
	public void setTotal(double d) {
		total = d;
	}
	public void setHasDiscount(boolean b) {
		hasDiscount = b;
	}
	public void setDisount(double d) {
		discount = d;
	}

}
