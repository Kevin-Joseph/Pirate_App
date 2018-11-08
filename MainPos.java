import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


//java.time.LocalDate.now();

public class MainPos extends JFrame implements ActionListener {
	
	JPanel p = new JPanel();
	
	JButton submit = new JButton("Submit Order");
	JButton add = new JButton("Add Items");
	JButton remove = new JButton("Remove Items");
	JButton print = new JButton("Print");
	
	JTextField firstlastName = new JTextField();
	JTextField lastName = new JTextField();
	JTextField address = new JTextField();
	JTextField state = new JTextField();
	JTextField zip = new JTextField();
	JTextField email = new JTextField();
	JTextField phone = new JTextField();
	JTextField coupon = new JTextField();
	JTextField discount = new JTextField();
	JTextField override = new JTextField();
	
	JComboBox quantity = new JComboBox(), 
			type = new JComboBox();
	
	DefaultListModel items = new DefaultListModel();
	JList purchaseitem = new JList(items);
	JList orderitem = new JList();
	JList ordersummary = new JList();
	
	JScrollPane jsppurchase = new JScrollPane(purchaseitem);
	JScrollPane jsporder = new JScrollPane(orderitem);
	JScrollPane jspsummary = new JScrollPane(ordersummary);
	
	JLabel firstlastNamel = new JLabel("<html><font color='blue'>First Name: </font></html>"), lastNamel = new JLabel("lastName: "), 
			addressl= new JLabel("Address: "), statel = new JLabel("state: "), 
			zipl = new JLabel("zip: "), emaill = new JLabel("email: "), phonel = new JLabel("Phone: "),
			couponl = new JLabel("Coupon: "), discountl = new JLabel("Discount: "), 
			overridel = new JLabel("Override: "), icon, datel = new JLabel("" + java.time.LocalDate.now());
    public static BufferedImage image;
   // Graphics g;
    MyPanel mp = new MyPanel();

	
	
	public static void main(String[] args) {
			
			MainPos app = new MainPos();
			app.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
			app.setExtendedState(JFrame.MAXIMIZED_BOTH);
			app.pack();
			app.setResizable(true);
			app.setDefaultCloseOperation(EXIT_ON_CLOSE);
			app.setFont(new Font("Courier New",1,14));

	        try {
				image = ImageIO.read(new File("duke_on_horse1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			app.setIconImage(image);
			app.setTitle("SSDUKE Pirate Emporium");
			app.setVisible(true);
		}	

	MainPos() {
		JPanel p = new JPanel();
		p.setLayout(null);
		
		ImageIcon background = new ImageIcon("atlas.jpg"); //Gradient-blue-1.jpg
		ImageIcon perry = new ImageIcon("perrythepirate1.jpg"); 

		JLabel onBackground = new JLabel();
		JLabel perryImg = new JLabel();
		
		onBackground.setIcon(background);
		
	
		perryImg.setIcon(perry);
		onBackground.add(perryImg);
		perryImg.setBounds(820, 200, 300, 300);

		onBackground.setBounds(0, 0, 1400, 800);
		p.add(onBackground);
		
			//lastName display
			mp.setBounds(0, 10, 1400, 50);
			onBackground.add(mp);
		
			//p.add(datel);
			onBackground.add(datel);
			datel.setBounds(100, 30, 200, 30);
		
			p.add(firstlastName);
			onBackground.add(firstlastNamel);
			firstlastName.setBounds(125, 75, 100, 30);
			firstlastNamel.setBounds(35, 75, 100, 30);
			
			p.add(lastName);
			onBackground.add(lastNamel);
			lastName.setBounds(125, 107, 175, 30);
			lastNamel.setBounds(35, 107, 100, 30);
			
			p.add(address);
			onBackground.add(addressl);
			address.setBounds(125, 139, 225, 30);
			addressl.setBounds(35, 139, 100, 30);
			
			p.add(state);
			onBackground.add(statel);
			state.setBounds(125, 171, 225, 30);
			statel.setBounds(35, 171, 100, 30);
			
			p.add(zip);
			onBackground.add(zipl);
			zip.setBounds(125, 203, 113, 30);
			zipl.setBounds(35, 203, 50, 30);
			
			p.add(email);
			onBackground.add(emaill);
			email.setBounds(125, 235, 113, 30);
			emaill.setBounds(35, 235, 50, 30);
			
			p.add(phone);
			onBackground.add(phonel);
			phone.setBounds(125, 267, 200, 30);
			phonel.setBounds(35, 267, 100, 30);
			
			p.add(coupon);
			onBackground.add(couponl);
			coupon.setBounds(475, 325, 200, 30);
			couponl.setBounds(400, 325, 100, 30);
			
			p.add(discount);
			onBackground.add(discountl);
			discount.setBounds(475, 357, 200, 30);
			discountl.setBounds(400, 357, 100, 30);
			
			p.add(override);
			onBackground.add(overridel);
			override.setBounds(475, 389, 200, 30);
			overridel.setBounds(400, 389, 100, 30);
			
			onBackground.add(jsppurchase);
			jsppurchase.setBounds(525, 500, 175, 150);
			
			onBackground.add(jsporder);
			jsporder.setBounds(850, 500, 175, 150);
			
			onBackground.add(jspsummary);
			jspsummary.setBounds(1075, 500, 175, 150);
			
			
			onBackground.add(submit);
			submit.setBounds(1075,  655, 125, 25);
			
			onBackground.add(add);
			add.setBounds(580,  655, 125, 25);
			
			onBackground.add(remove);
			//p.add(remove);
			remove.setBounds(850,  655, 125, 25);
			
			onBackground.add(print);
			print.setBounds(1075, 685, 125, 25);
			
			quantity.addItem("Quantity");
			onBackground.add(quantity);
			//p.add(quantity);
			quantity.setBounds(705,525, 125, 30);
			
			type.addItem("Type");
			onBackground.add(type);
			type.setBounds(705, 580, 125, 30);
			
		
		p.setVisible(true);
		
		add(p);
		
		
		

		submit.setVisible(true);
		submit.addActionListener(this);
		add.setVisible(true);
		add.addActionListener(this);
		remove.setVisible(true);
		remove.addActionListener(this);
		print.setVisible(true);
		print.addActionListener(this);
		
}


		public void actionPerformed(ActionEvent e) {
		String arr[] = {"HOOKS",
				"Pirate Hat",
				"Eyepatch",
				"----",
				"----"};
		for(int i=0;i<arr.length;i++) {
			Items c=new Items(arr[i]);
			items.addElement(c);
		}
	}
	
	class Items{
			String itemlastName = "";
			Items(String itemlastName){
				this.itemlastName=itemlastName;
		}
			
	public String toString() {
				return itemlastName;
		}
	
	
		
	}
	
    public void paintComponent(Graphics gr){
        super.paintComponents(gr); 
        gr.drawString("string literal or a string variable", 0,10);
    }
	
	
}

/*
//To draw emporuim lastName with background
class MyPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            File pathToFile = new File("Gradient-blue-1.jpg");
            Image image = ImageIO.read(pathToFile);
       		g.drawImage(image, 0, 0, this);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
		//g.setColor( new Color( 153, 204, 255, 255) );
		g.setColor( new Color( 255, 255, 255, 255) );
		g.setFont( new Font("Stencil", 1, 44) ); //Old English Text MT
		g.drawString("- SS DUKE PIRATE EMPORUIM -", 350, 40);
		
		g.setColor( new Color( 150, 150, 255, 255) );
		g.setColor( new Color( 100, 100, 100, 255) );
		g.setFont( new Font("Stencil", 1, 44) );
		g.drawString("- SS DUKE PIRATE EMPORUIM -", 349, 39);
		
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400); // As suggested by camickr
    }
}
*/