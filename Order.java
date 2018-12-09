import java.util.ArrayList;
import java.util.Vector;

public class Order {
	
	protected Customer customer;
	protected String date;
	
	//Vector<Product> products = new Vector<Product>(5);
	//Vector<Eye_Patches> eye_patches = new Vector<Eye_Patches>();
	
	ArrayList<Product> products = new ArrayList<Product>();
	
	//protected Vector<Product> products = new Vector<Product>();
	
	protected double total;
	protected boolean hasDiscount;
	protected double discount;
	protected String paymentMethod;
	//*****************************************************Constructor
	public Order() {
		date ="" + java.time.LocalDate.now();
		total = 0.0;
		hasDiscount = false;
		discount = 0.0;
	}
	
	//****************************************************Getters
	public double calcTotal() {
		double total = 0;
		
		return total;
	}
	public boolean checkHasDisount() {
		return hasDiscount;
	}
	public double getDiscount() {
		return discount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
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
	
	public void addEye_Patch(Eye_Patches ep) {
		products.add(ep);
	}
	//public void removeProduct(int index) {
	//	products.remove(index);
	//}
	
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
	public void setPaymentMethod(String s) {
		paymentMethod = s;
	}


}
