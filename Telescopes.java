import java.awt.Color;
import java.math.BigDecimal;

public class Telescopes extends Product{
	
	//protected String material;
	protected char size;
	//protected int size;
	protected int magnification;
	
	//**********************************************Constructor
	@SuppressWarnings("deprecation")
	public Telescopes() {
		name = "Telescope";
		type = "Enter Type";
		price = new BigDecimal("50");
		price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		availability = false;
		color = Color.GRAY;
		salestax = new BigDecimal("0.0825"); //could be static
		salestax = salestax.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		size = 'S';
		magnification = 50;
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
	public BigDecimal getPrice() {
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
	public BigDecimal getSalesTax() {
		return salestax;
	}
	
	public char getSize() {
		return size;
	}
	
	public int getMagnification() {
		return magnification;
	}
	
	public int getNumberToOrder() {
		return numberToOrder;
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
	public void setPrice(BigDecimal d) {
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
	public void setSalesTax(BigDecimal d) {
		salestax = d;
	}
	
	public void setSize(char i) {
		size = i;
	}
	
	public void setMagnification(int i) {
		magnification = i;
	}
	
	public void setNumberToOrder(int num) {
		numberToOrder = num;
	}
	
	//****************************************************************new
	public BigDecimal calcTotal(int numToOrder, char s, BigDecimal disc) {
		BigDecimal total = this.price;
	
		switch(s) {
		case 'S': total = total.add(new BigDecimal("10")); break;
		case 'M': total = total.add(new BigDecimal("20")); break;
		case 'L': total = total.add(new BigDecimal("30")); break;
		}
		
		total = total.multiply(new BigDecimal("1").subtract(disc));
		BigDecimal baseTotal = new BigDecimal(total.toPlainString());
		for(int i = 1; i < numToOrder; i++) {
			total = total.add(baseTotal); 
		}
		total = total.multiply(this.salestax.add(new BigDecimal("1")));
		total = total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		return total;
	}
	
	public String toString() {
		String s = "[" + this.getNumberToOrder() + "]" + this.getName() +
				   "---" + this.getSize() + " $" + 
				   this.calcTotal(this.getNumberToOrder(), this.getSize(), new BigDecimal("0"));
		return s;		
	}
	
}
