import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Product {

	protected String name;
	protected String type;
	protected BigDecimal price;
	protected boolean availability;
	protected Color color;
	protected BigDecimal salestax; //could be static
	protected int numberToOrder;
	
	public abstract BigDecimal calcTotal(int n, char s, BigDecimal disc);
	public abstract String toString();
	
	public abstract String getName();
	public abstract String getType();
	public abstract BigDecimal getPrice();
	public abstract boolean getAvailability();
	public abstract Color getColor();
	public abstract BigDecimal getSalesTax();
	public abstract int getNumberToOrder();
	public abstract char getSize();
	
	public abstract void setName(String s);
	public abstract void setType(String s);
	public abstract void setPrice(BigDecimal d);
	public abstract void setAvailability(boolean b);
	public abstract void setColor(Color c);
	public abstract void setSalesTax(BigDecimal d);
	public abstract void setNumberToOrder(int i);

}

