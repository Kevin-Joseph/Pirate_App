import java.awt.Color;

public class Eye_Patches extends Product{
	
	protected String material;
	protected char size;
	
	//**********************************************Constructor
	public Eye_Patches() {
		name = "Enter Name";
		type = "Enter Type";
		price = 0.0;
		availability = false;
		color = Color.GRAY;
		salestax = 0.0; //could be static
		material = "Leather";
		size = 'n';
	}

	//**********************************************Getters
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public boolean getAvailability() {
		return false;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public double getSalesTax() {
		return salestax;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public char getSize() {
		return size;
	}
	
	
	//**************************************************Setters
	@Override
	public void setName(String s) {
		name = s;
	}

	@Override
	public void setType(String s) {
		type = s;
	}

	@Override
	public void setPrice(double d) {
		price = d;
	}

	@Override
	public void setAvailability(boolean b) {
		availability = b;
	}

	@Override
	public void setColor(Color c) {
		color = c;
	}

	@Override
	public void setSalesTax(double d) {
		salestax = d;
	}
	
	public void setMaterial(String s) {
		material = s;
	}
	
	public void setSize(char c) {
		size = c;
	}

}
