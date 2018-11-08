import java.awt.Color;

public abstract class Product {

	protected String name;
	protected String type;
	protected double price;
	protected boolean availability;
	protected Color color;
	protected double salestax; //could be static
	
	public abstract String getName();
	public abstract String getType();
	public abstract double getPrice();
	public abstract boolean getAvailability();
	public abstract Color getColor();
	public abstract double getSalesTax();
	
	public abstract void setName(String s);
	public abstract void setType(String s);
	public abstract void setPrice(double d);
	public abstract void setAvailability(boolean b);
	public abstract void setColor(Color c);
	public abstract void setSalesTax(double d);


}

