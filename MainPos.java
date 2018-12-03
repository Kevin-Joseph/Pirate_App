import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainPos extends JFrame implements ActionListener {
	
	JPanel p = new JPanel();
	
	JButton submit = new JButton("Submit Order");
	JButton add = new JButton("Add Items");
	JButton remove = new JButton("Remove Items");
	JButton print = new JButton("Print");
	JButton updateTotal = new JButton("Update Total");
	
	
	
	
	JTextField firstName = new JTextField();
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
			size = new JComboBox();
	
	DefaultListModel displayItems = new DefaultListModel();
	JList purchaseitem = new JList(displayItems);

	DefaultListModel orderItems = new DefaultListModel();
	JList orderitem = new JList(orderItems);
	
	DefaultListModel summaryItems = new DefaultListModel();
	JList ordersummary = new JList(summaryItems);
	
	JScrollPane jsppurchase = new JScrollPane(purchaseitem);
	JScrollPane jsporder = new JScrollPane(orderitem);
	JScrollPane jspsummary = new JScrollPane(ordersummary);
	
	JLabel firstNamel = new JLabel("<html><font color='blue'>First Name: </font></html>"), lastNamel = new JLabel("lastName: "), 
			addressl= new JLabel("Address: "), statel = new JLabel("state: "), 
			zipl = new JLabel("zip: "), emaill = new JLabel("email: "), phonel = new JLabel("Phone: "),
			couponl = new JLabel("Coupon: "), discountl = new JLabel("Discount: "), 
			overridel = new JLabel("Override: "), icon, datel = new JLabel("" + java.time.LocalDate.now());
	
	JLabel hats = new JLabel(), numHats = new JLabel();
	JLabel eyePatches = new JLabel(), numEyePatches = new JLabel();
	JLabel telescopes = new JLabel(), numTelescopes = new JLabel();
	JLabel orderTotal = new JLabel(), numOrderTotal = new JLabel();
    
	public static BufferedImage image;

    MyPanel mp = new MyPanel();
    
	Order order = new Order();
	Customer customer = new Customer();

	int numberOfProducts = 0;

	ImageIcon eyePatch_I = new ImageIcon("eyePatch.jpg"); 
	JButton eyePatch_B = new JButton(eyePatch_I);
	ImageIcon hat_I = new ImageIcon("pirateHat.jpg"); 
	JButton hat_B = new JButton(hat_I);
	ImageIcon telescope_I = new ImageIcon("telescope.jpg"); 
	JButton telescope_B = new JButton(telescope_I);
	
    JCheckBox pirateHat_CB = new JCheckBox("Pirate hat");
    JCheckBox eyePatch_CB = new JCheckBox("Eye Patch");
    JCheckBox telescope_CB = new JCheckBox("Telescope");

	private DecimalFormat noCurrencySymbol = new DecimalFormat("###0.00");
	
	int num_eye_patches, num_hats, num_telescopes;
	BigDecimal trackTotal;
	
	JFrame f = new JFrame();

	BigDecimal totalItemPrice = new BigDecimal("0");

	
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

	@SuppressWarnings("unchecked")
	MainPos() {
		
		JLabel onBackground = new JLabel();
		
		
		
		onBackground.add(updateTotal);
		updateTotal.setBounds(1075, 250, 125, 25);
		updateTotal.addActionListener(this);

		trackTotal = new BigDecimal("0");
		num_eye_patches = 0; num_hats = 0; num_telescopes = 0;
		
		eyePatches.setFont( new Font("Courier New", 2, 24) );
		eyePatches.setText("EYE PATCHES");
		eyePatches.setBounds(1075, 300, 200, 24);
		numEyePatches.setFont( new Font("Courier New", 1, 24) );
		numEyePatches.setText("-0-");
		numEyePatches.setBounds(1075, 330, 200, 24);
		onBackground.add(eyePatches);
		onBackground.add(numEyePatches);
		
		hats.setFont( new Font("Courier New", 2, 24) );
		hats.setText("HATS");
		hats.setBounds(1075, 370, 200, 24);
		numHats.setFont( new Font("Courier New", 1, 24) );
		numHats.setText("-0-");
		numHats.setBounds(1075, 400, 200, 24);
		onBackground.add(hats);
		onBackground.add(numHats);

		telescopes.setFont( new Font("Courier New", 2, 24) );
		telescopes.setText("TELESCOPES");
		telescopes.setBounds(1075, 440, 200, 24);
		numTelescopes.setFont( new Font("Courier New", 1, 24) );
		numTelescopes.setText("-0-");
		numTelescopes.setBounds(1075, 470, 200, 24);
		onBackground.add(telescopes);
		onBackground.add(numTelescopes);
		

		orderTotal.setFont( new Font("Courier New", 2, 24) );
		orderTotal.setText("ORDER TOTAL");
		orderTotal.setBounds(1075, 510, 200, 24);
		numOrderTotal.setFont( new Font("Courier New", 1, 24) );
		numOrderTotal.setText("$-0-");
		numOrderTotal.setBounds(1075, 540, 200, 24);
		onBackground.add(orderTotal);
		onBackground.add(numOrderTotal);

		
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel perryImg = new JLabel();

		//Code to add a button with only an image 
		onBackground.add(eyePatch_B);
		eyePatch_B.setBounds(450, 75, 200, 200);
		eyePatch_B.addActionListener(this);
		
		onBackground.add(hat_B);
		hat_B.setBounds(450, 300, 200, 200);
		hat_B.addActionListener(this);

		onBackground.add(telescope_B);
		telescope_B.setBounds(450, 525, 200, 200);
		telescope_B.addActionListener(this);
		//Code to add a button with only an image ^^^^^^^
		
		
	    eyePatch_CB.setMnemonic(KeyEvent.VK_E); 
	    eyePatch_CB.setSelected(false);
	    onBackground.add(eyePatch_CB);
	    eyePatch_CB.setBounds(655, 125, 85, 25);
	    
	    pirateHat_CB.setMnemonic(KeyEvent.VK_P); 
	    pirateHat_CB.setSelected(false);
	    onBackground.add(pirateHat_CB);
	    pirateHat_CB.setBounds(655, 400, 85, 25);

	    telescope_CB.setMnemonic(KeyEvent.VK_T); 
	    telescope_CB.setSelected(false);
	    onBackground.add(telescope_CB);
	    telescope_CB.setBounds(655, 625, 85, 25);


		
		
		
		ImageIcon background = new ImageIcon("atlas.jpg"); //Gradient-blue-1.jpg
		ImageIcon perry = new ImageIcon("perrythepirate1.jpg"); 

		
		onBackground.setIcon(background);
		
	
		perryImg.setIcon(perry);
		onBackground.add(perryImg);
		perryImg.setBounds(820, 200, 300, 300);
		perryImg.setVisible(false);

		onBackground.setBounds(0, 0, 1400, 800);
		p.add(onBackground);
		
		
			mp.setBounds(0, 10, 1400, 50);
			onBackground.add(mp);
		
			onBackground.add(datel);
			datel.setBounds(100, 30, 200, 30);
		
			p.add(firstName);
			onBackground.add(firstNamel);
			firstName.setBounds(125, 75, 100, 30);
			firstNamel.setBounds(35, 75, 100, 30);
			
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
			coupon.setBounds(1125, 100, 200, 30);
			couponl.setBounds(1070, 100, 100, 30);
			
			p.add(discount);
			onBackground.add(discountl);
			discount.setBounds(1125, 150, 200, 30);
			discountl.setBounds(1070, 150, 100, 30);
			
			p.add(override);
			onBackground.add(overridel);
			override.setBounds(1125, 200, 200, 30);
			overridel.setBounds(1070, 200, 100, 30);
			
			onBackground.add(jsppurchase);
			jsppurchase.setBounds(525, 500, 175, 150);
			jsppurchase.setVisible(false);
			
			onBackground.add(jsporder);
			jsporder.setBounds(750, 400, 275, 250);
			
			onBackground.add(jspsummary);
			jspsummary.setBounds(1075, 400, 275, 250);
			jspsummary.setVisible(false);
			
			
			onBackground.add(submit);
			submit.setBounds(1075,  655, 125, 25);
			
			onBackground.add(add);
			add.setBounds(750, 350, 125, 25);
			
			onBackground.add(remove);
			remove.setBounds(750, 655, 125, 25);
			
			onBackground.add(print);
			print.setBounds(1075, 685, 125, 25);
			
			//****************************************Quantity combo box
			for(int i = 1; i < 11; i++) {
				quantity.addItem(i);
			}
			onBackground.add(quantity);
			quantity.setBounds(750, 300, 125, 30);
			
			//****************************************Size combo box
			size.addItem("S");
			size.addItem("M");
			size.addItem("L");
			onBackground.add(size);
			size.setBounds(750, 265, 125, 30);
			size.setVisible(true);
			
		
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
		quantity.addActionListener(this);
		
		String arr[] = {"Eye Patch","Pirate Hat","Telescope"};
		for(int i = 0; i < arr.length; i++) {
			displayItems.addElement(new displayItems(arr[i]));
		}
		
		
} //end of constructor


		@SuppressWarnings({ "unchecked", "deprecation" })
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == eyePatch_B) { //****************************eye patch button
				eyePatch_CB.setSelected(true);
				pirateHat_CB.setSelected(false);
				telescope_CB.setSelected(false);

			}else if(e.getSource() == hat_B) { //****************************pirate hat button
				eyePatch_CB.setSelected(false);
				pirateHat_CB.setSelected(true);
				telescope_CB.setSelected(false);
			}else if(e.getSource() == telescope_B) { //****************************telescope button
				eyePatch_CB.setSelected(false);
				pirateHat_CB.setSelected(false);
				telescope_CB.setSelected(true);
			}else if(e.getSource() == add ) { //****************************add button
				if(eyePatch_CB.isSelected() == true) {
					Eye_Patches newItem = new Eye_Patches();
					BigDecimal basePrice = newItem.getPrice();
					basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					//BigDecimal totalItemPrice = new BigDecimal("0");
					int selectSize = size.getSelectedIndex();
					char chSize = 'S';
					switch(selectSize) {
						case 0: chSize = 'S'; break; case 1: chSize = 'M'; break; case 2: chSize = 'L'; break;
					}
					newItem.setSize(chSize);
					newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
					String sz = "Small";
					switch(chSize) {
					case 'S': sz = "Small"; break; case 'M': sz = "Medium"; break; case 'L': sz = "Large"; break;
					}
					
					totalItemPrice = totalItemPrice.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					order.products.add(newItem);
					orderItems.addElement(new displayItems("[" + newItem.getNumberToOrder() + "] Eye Patch---" +
								sz + " " + newItem.getMaterial()) +" $" + totalItemPrice.toPlainString());
					
					num_eye_patches += quantity.getSelectedIndex() + 1;
					numEyePatches.setText("" + num_eye_patches);
					trackTotal = trackTotal.add(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
					eyePatch_CB.setSelected(false);
				}else if(pirateHat_CB.isSelected() == true) {
					Pirate_Hats newItem = new Pirate_Hats();
					BigDecimal basePrice = newItem.getPrice();
					basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					BigDecimal totalItemPrice = new BigDecimal("0");
					int selectSize = size.getSelectedIndex();
					char chSize = 'S';
					switch(selectSize) {
						case 0: chSize = 'S'; break; case 1: chSize = 'M'; break; case 2: chSize = 'L'; break;
					}
					newItem.setSize(chSize);
					newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
					String sz = "Small";
					switch(chSize) {
					case 'S': sz = "Small"; break; case 'M': sz = "Medium"; break; case 'L': sz = "Large"; break;
					}
					
					totalItemPrice = totalItemPrice.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					order.products.add(newItem);
					orderItems.addElement(new displayItems("[" + newItem.getNumberToOrder() + "] Pirate Hat---" +
								sz + " " + newItem.getMaterial()) +" $" + totalItemPrice.toPlainString());
					
					num_hats += quantity.getSelectedIndex() + 1;
					numHats.setText("" + num_hats);
					trackTotal = trackTotal.add(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
					pirateHat_CB.setSelected(false);
				}else if(telescope_CB.isSelected() == true) {
					Telescopes newItem = new Telescopes();
					BigDecimal basePrice = newItem.getPrice();
					basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					BigDecimal totalItemPrice = new BigDecimal("0");
					int selectSize = size.getSelectedIndex();
					char chSize = 'S';
					switch(selectSize) {
						case 0: chSize = 'S'; break; case 1: chSize = 'M'; break; case 2: chSize = 'L'; break;
					}
					newItem.setSize(chSize);
					newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
					String sz = "Small";
					switch(chSize) {
					case 'S': sz = "Small"; break; case 'M': sz = "Medium"; break; case 'L': sz = "Large"; break;
					}
					
					totalItemPrice = totalItemPrice.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					order.products.add(newItem);
					orderItems.addElement(new displayItems("[" + newItem.getNumberToOrder() + "] Telescope---" +
								sz + " " + newItem.getMagnification()) +"X $" + totalItemPrice.toPlainString());
					
					num_telescopes += quantity.getSelectedIndex() + 1;
					numTelescopes.setText("" + num_telescopes);
					trackTotal = trackTotal.add(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
					telescope_CB.setSelected(false);
				}

			}else if(e.getSource() == remove) { //****************************remove button
				@SuppressWarnings("unused")
				Product removeItem = order.products.get(orderitem.getSelectedIndex());
				String nm = removeItem.getName();
				int item = 0;
				switch(nm) {
					case ("Eye Patch"): item = 0; break; case ("Telescope"): item = 1; break; case ("Pirate Hat"): item = 2; break; 
				}
				BigDecimal totalItemPrice = new BigDecimal("0");
				if(item == 0) {
					totalItemPrice = totalItemPrice.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					num_eye_patches = num_eye_patches - removeItem.getNumberToOrder();					
					numEyePatches.setText("" + num_eye_patches);
					trackTotal = trackTotal.subtract(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
				}else if( item == 1) {
					totalItemPrice = totalItemPrice.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					num_telescopes -= removeItem.getNumberToOrder();					
					numTelescopes.setText("" + num_telescopes);
					trackTotal = trackTotal.subtract(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
				}else if(item == 2) {
					totalItemPrice = totalItemPrice.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
					totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					num_hats -= removeItem.getNumberToOrder();					
					numHats.setText("" + num_hats);
					trackTotal = trackTotal.subtract(totalItemPrice);
					numOrderTotal.setText("$" + trackTotal.toPlainString());
				}
				
				order.products.remove(orderitem.getSelectedIndex());
				orderItems.removeElementAt(orderitem.getSelectedIndex());
				
			}else if (e.getSource() == submit) {
				customer.setFirstName(firstName.getText().trim());
				customer.setLastName(lastName.getText().trim());
				customer.setAddress(address.getText().trim());
				customer.setState(state.getText().trim());
				customer.setZip(Integer.parseInt(zip.getText().trim()));
				customer.setPhoneNum(phone.getText().trim());
				customer.setEmail(email.getText().trim());

				f.setSize(400, 600);
				f.setLocationRelativeTo(this);
				f.setTitle("Review Order");
				f.setVisible(true);
				JPanel review = new JPanel();
				JPanel reviewBtns = new JPanel();
				JTextArea ta= new JTextArea();
			    JScrollPane scrollPane = new JScrollPane(ta);
			    scrollPane.setPreferredSize(new Dimension(400, 500));
			    f.add(scrollPane, BorderLayout.NORTH);
				f.add(review, BorderLayout.CENTER);
				
				JButton printBtn = new JButton("Print Receipt");
				JButton doneBtn = new JButton("Done");
				JButton orderBtn = new JButton("Order");
				reviewBtns.add(printBtn, BorderLayout.SOUTH);
				reviewBtns.add(doneBtn, BorderLayout.SOUTH);
				reviewBtns.add(orderBtn, BorderLayout.SOUTH);

				JLabel custInfo = new JLabel(customer.toString());
				custInfo.setSize(400, 200);
			    ta.append(customer.toString());
			    ta.append("\n\nItems to order:\n");
			    
			    for(int i = 0; i < order.products.size(); i++) {
			    	ta.append(order.products.get(i).toString() + "\n");
			    }
			    ta.append("\n\nEye Patches purchased: " + num_eye_patches);
			    ta.append("\nPirate Hats purchased: " + num_hats);
			    ta.append("\nTelescopes purchased: " + num_telescopes);
			    ta.append("\n\nTotal order amount: $" + trackTotal.toPlainString() + "\n");
			    
				review.add(reviewBtns, BorderLayout.SOUTH);
				
				printBtn.addActionListener(action -> {			
					PrintPreviewPanel ppp = new PrintPreviewPanel(trackTotal, order.products);
					// submit print job:
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintable(ppp);
				
					if (job.printDialog()) {
						try {
							job.print();
						} catch(PrinterException x_x) {
							System.out.println("Error printing: " + x_x);
						}
					}
				
				});
				
				doneBtn.addActionListener(action -> {
					f.dispose();
				});
				orderBtn.addActionListener(action -> {
					f.dispose();
					JOptionPane.showMessageDialog(null, "Thank you for your busienss. Your order has been placed", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				});
				
				
			}else if (e.getSource() == print) {
				customer.setFirstName(firstName.getText().trim());
				customer.setLastName(lastName.getText().trim());
				customer.setAddress(address.getText().trim());
				customer.setState(state.getText().trim());
				customer.setZip(Integer.parseInt(zip.getText().trim()));
				customer.setPhoneNum(phone.getText().trim());
				customer.setEmail(email.getText().trim());

				f.setSize(400, 600);
				f.setLocationRelativeTo(this);
				f.setTitle("Review Order");
				f.setVisible(true);
				JPanel review = new JPanel();
				JPanel reviewBtns = new JPanel();
	
				JButton printBtn = new JButton("Print");

				reviewBtns.add(printBtn, BorderLayout.SOUTH);

				PrintPreviewPanel ppp = new PrintPreviewPanel(trackTotal, order.products);
				f.add(ppp, BorderLayout.CENTER);
				f.add(reviewBtns, BorderLayout.SOUTH);
				
				printBtn.addActionListener(action -> {
					// submit print job:
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintable(ppp);
				
					if (job.printDialog()) {
						try {
							job.print();
						} catch(PrinterException x_x) {
							System.out.println("Error printing: " + x_x);
						}
					}
				
				});
			}else if(e.getSource() == updateTotal) {
				BigDecimal disc = new BigDecimal("0");
				disc = disc.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				
				if(override.getText().isEmpty() == false) {
					
					//totalItemPrice = new BigDecimal(override.getText().trim());
					//totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					trackTotal = new BigDecimal(override.getText().trim());
					numOrderTotal.setText("$" + trackTotal.toPlainString());
					
				}else if(coupon.getText().isEmpty() == false) {
					//System.out.println("in coupon");
					String temp = coupon.getText().trim();
					System.out.println(temp);
					if(temp.equals("12345")) {
						System.out.println("in coupon");

					//	disc = new BigDecimal(".25");
						trackTotal = trackTotal.multiply(new BigDecimal("1").subtract(new BigDecimal(".25")));
						trackTotal = trackTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

						numOrderTotal.setText("$" + trackTotal.toPlainString());
					}
				}else if(discount.getText().isEmpty() == false) {
					//String discnt = discount.getText().trim();
					//disc = new BigDecimal(discount.getText().trim());
					trackTotal = trackTotal.multiply(new BigDecimal("1").subtract(new BigDecimal(".25")));
					trackTotal = trackTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					numOrderTotal.setText("$" + trackTotal.toPlainString());
				}

				
				//totalItemPrice = totalItemPrice.multiply(new BigDecimal("1").subtract(disc));
				//totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			}
			
	}//End of action listener
		
		
		
		
		
		
	
	class displayItems{
			String itemlastName = "";
			displayItems(String itemlastName){
				this.itemlastName=itemlastName;
		}
			
	public String toString() {
				return itemlastName;
		}
	
	
		
	}


} //end of class
