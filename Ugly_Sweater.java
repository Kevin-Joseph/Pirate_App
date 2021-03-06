import java.awt.Color;
import java.math.BigDecimal;

public class Ugly_Sweater extends Product{

	protected char size;

	
	//**********************************************Constructor
	@SuppressWarnings("deprecation")
	public Ugly_Sweater() {
		name = "Ugly Sweater";
		type = "Enter Type";
		price = new BigDecimal("20.0");
		price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		availability = false;
		color = Color.GRAY;
		salestax = new BigDecimal("0.0825"); //could be static
		salestax = salestax.setScale(4, BigDecimal.ROUND_HALF_EVEN);
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
	
	public void setSize(char c) {
		size = c;
	}
	
	public void setNumberToOrder(int num) {
		numberToOrder = num;
	}
	
	
	//****************************************************************new
	public BigDecimal calcTotal(int numToOrder, char s, BigDecimal disc) {
		BigDecimal total = this.price;
	
		switch(s) {
		case 'S': total = total.add(new BigDecimal("1")); break;
		case 'M': total = total.add(new BigDecimal("2")); break;
		case 'L': total = total.add(new BigDecimal("3")); break;
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
				   "---" + this.getSize() +  " $" + 
				   this.calcTotal(this.getNumberToOrder(), this.getSize(), new BigDecimal("0"));
		return s;		
	}

	@Override
	public String getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

}//End of class
