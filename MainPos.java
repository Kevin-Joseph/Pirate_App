import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.media.AudioClip;

public class MainPos extends JFrame implements ActionListener {
// Setting up the GUI
	JPanel p = new JPanel();

// Buttons
	JButton submit = new JButton("Submit Order");
	JButton add = new JButton("Add Items");
	JButton remove = new JButton("Remove Items");
	JButton print = new JButton("Print");
	JButton updateTotal = new JButton("Update Total");
	JButton reset = new JButton("Reset");
	JButton changeqty = new JButton("Change Quantity");
	JButton changesize = new JButton("Change Size");
	
// TextFields
	JTextField firstName = new JTextField();
	JTextField lastName = new JTextField();
	JTextField address = new JTextField();
	JTextField city = new JTextField();
	JTextField state = new JTextField();
	JTextField zip = new JTextField();
	JTextField email = new JTextField();
	JTextField phone = new JTextField();
	JTextField coupon = new JTextField();
	JTextField discount = new JTextField();
	JTextField override = new JTextField();

//ComboBoxes
	JComboBox quantity = new JComboBox(), size = new JComboBox();

	DefaultListModel displayItems = new DefaultListModel();
	JList purchaseitem = new JList(displayItems);

	DefaultListModel orderItems = new DefaultListModel();
	JList orderitem = new JList(orderItems);

	DefaultListModel summaryItems = new DefaultListModel();
	JList ordersummary = new JList(summaryItems);

//ScrollPanes
	JScrollPane jsppurchase = new JScrollPane(purchaseitem);
	JScrollPane jsporder = new JScrollPane(orderitem);
	JScrollPane jspsummary = new JScrollPane(ordersummary);

//Labels
	JLabel firstNamel = new JLabel("First Name:"), lastNamel = new JLabel("Last Name: "),
			addressl = new JLabel("Address: "), cityl = new JLabel("City: "), statel = new JLabel("State: "),
			zipl = new JLabel("Zip: "), emaill = new JLabel("Email: "), phonel = new JLabel("Phone: "),
			couponl = new JLabel("Coupon: "), discountl = new JLabel("Discount: "),
			overridel = new JLabel("Override: "), icon, datel = new JLabel("" + java.time.LocalDate.now());
	JLabel SantaHats = new JLabel(), numSantaHats = new JLabel();
	JLabel UglySweaters = new JLabel(), numUglySweaters = new JLabel();
	JLabel Onesies = new JLabel(), numOnesies = new JLabel();
	JLabel orderTotal = new JLabel(), numOrderTotal = new JLabel();
	JLabel selectsize = new JLabel();
	JLabel selectquantity = new JLabel();
	public static BufferedImage image;

//Panels
	MyPanel mp = new MyPanel();
	Order order = new Order();
	Customer customer = new Customer();

//Variables
	int numberOfProducts = 0;
	BigDecimal totalItemPrice = new BigDecimal("0");
	int num_ugly_sweaters, num_santa_hats, num_onesies;
	BigDecimal trackTotal;
	private DecimalFormat noCurrencySymbol = new DecimalFormat("###0.00");
	
//Adding images to the product buttons
	ImageIcon uglySweater_I = new ImageIcon("sweater.jpg");
	JButton uglySweater_B = new JButton(uglySweater_I);
	ImageIcon SantaHat_I = new ImageIcon("santahat.jpg");
	JButton SantaHat_B = new JButton(SantaHat_I);
	ImageIcon Onesie_I = new ImageIcon("onesie.jpg");
	JButton Onesie_B = new JButton(Onesie_I);

//CheckBoxes
	JCheckBox SantaHat_CB = new JCheckBox("Santa Hat");
	JCheckBox uglySweater_CB = new JCheckBox("Ugly Sweater");
 	JCheckBox Onesie_CB = new JCheckBox("Reindeer Onesie");

 	java.applet.AudioClip argh; 
 	
	JFrame f = new JFrame();

//BigDecimal totalItemPrice = new BigDecimal("0");
	public static String newline = System.getProperty("line.separator");

	JRadioButton cashButton = new JRadioButton("Cash");
	JRadioButton creditButton = new JRadioButton("Credit");
	JRadioButton checkButton = new JRadioButton("Check");
	ButtonGroup group = new ButtonGroup();

	public static void main(String[] args) {

		MainPos app = new MainPos();
		//Create a class object.
	
		
		app.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);
		app.pack();
		app.setResizable(true);
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setFont(new Font("Courier New", 1, 14));
		
		try {
			image = ImageIO.read(new File("duke_on_horse1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		app.setIconImage(image);
		app.setTitle("The Christmas Store");
		app.setVisible(true);
	}


	@SuppressWarnings("unchecked")
	MainPos() {
		File deck = new File ("C:\\Users\\macke\\eclipse-workspace\\Pirate_App-Kevin-Tangents\\extras\\DeckTheHalls_wav.AU");
		PlaySound(deck);
		
		{
			try{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(deck));
				clip.start();
				
				//Thread.sleep(clip.getMicrosecondLength()/1000);
				
		}catch(Exception e)
			{
			}
			}
		
		URL arghURL;
		try {
			// This syntax is older but works well:
			arghURL = new URL("file:argh.wav");
			argh = Applet.newAudioClip(arghURL);

		} catch (MalformedURLException frack) {
			frack.printStackTrace();
		}

		JLabel onBackground = new JLabel();

		cashButton.setMnemonic(KeyEvent.VK_B);
		cashButton.setActionCommand("Cash");
		cashButton.setSelected(true);
		onBackground.add(cashButton);
		cashButton.setBounds(980, 200 + 290, 100, 30);

		creditButton.setMnemonic(KeyEvent.VK_C);
		creditButton.setActionCommand("Credit");
		onBackground.add(creditButton);
		creditButton.setBounds(1080, 200 + 290, 100, 30);

		checkButton.setMnemonic(KeyEvent.VK_D);
		checkButton.setActionCommand("Check");
		onBackground.add(checkButton);
		checkButton.setBounds(1180, 200 + 290, 100, 30);

		// Group the radio buttons.
		group.add(cashButton);
		group.add(creditButton);
		group.add(checkButton);

		// Register a listener for the radio buttons.
		cashButton.addActionListener(this);
		creditButton.addActionListener(this);
		checkButton.addActionListener(this);

		trackTotal = new BigDecimal("0");
		num_ugly_sweaters = 0;
		num_santa_hats = 0;
		num_onesies = 0;
		selectsize.setText("Select Size:");
		selectsize.setFont(new Font("Calambri", Font.BOLD, 12));
		selectsize.setBounds(25 + 50, 280, 100, 100);
		onBackground.add(selectsize);

		selectquantity.setText("Select Quantity:");
		selectquantity.setFont(new Font("Calambri", Font.BOLD, 12));
		selectquantity.setBounds(250 + 60, 280, 100, 100);
		onBackground.add(selectquantity);

		UglySweaters.setFont(new Font("Courier New", Font.BOLD, 26));
		UglySweaters.setForeground(Color.RED.darker());
		UglySweaters.setText("UGLY SWEATERS");
		UglySweaters.setBounds(700, 70 + 260, 200, 24);

		numUglySweaters.setFont(new Font("Courier New", Font.BOLD, 26));
		numUglySweaters.setForeground(Color.RED.darker());
		numUglySweaters.setText("-0-");
		numUglySweaters.setBounds(700, 100 + 260, 200, 24);
		onBackground.add(UglySweaters);
		onBackground.add(numUglySweaters);

		SantaHats.setFont(new Font("Courier New", Font.BOLD, 26));
		SantaHats.setForeground(Color.RED.darker());
		SantaHats.setText("SANTA HATS");
		SantaHats.setBounds(700, 130 + 270, 200, 24);
		numSantaHats.setFont(new Font("Courier New", Font.BOLD, 24));
		numSantaHats.setForeground(Color.RED.darker());
		numSantaHats.setText("-0-");
		numSantaHats.setBounds(700, 160 + 270, 200, 24);
		onBackground.add(SantaHats);
		onBackground.add(numSantaHats);

		Onesies.setFont(new Font("Courier New", Font.BOLD, 26));
		Onesies.setForeground(Color.RED.darker());
		Onesies.setText("ONESIES");
		Onesies.setBounds(700, 190 + 280, 200, 24);
		numOnesies.setFont(new Font("Courier New", Font.BOLD, 24));
		numOnesies.setForeground(Color.RED.darker());
		numOnesies.setText("-0-");
		numOnesies.setBounds(700, 220 + 280, 200, 24);
		onBackground.add(Onesies);
		onBackground.add(numOnesies);

		orderTotal.setFont(new Font("Courier New", Font.BOLD, 30));
		orderTotal.setForeground(Color.RED.darker());
		orderTotal.setText("ORDER TOTAL");
		orderTotal.setBounds(730 - 30, 540 - 10, 200, 24);
		numOrderTotal.setFont(new Font("Courier New", 1, 24));
		numOrderTotal.setForeground(Color.RED.darker());
		numOrderTotal.setText("$-0-");
		numOrderTotal.setBounds(700, 560, 200, 24);
		onBackground.add(orderTotal);
		onBackground.add(numOrderTotal);

		JPanel p = new JPanel();
		p.setLayout(null);

		JLabel perryImg = new JLabel();

// Code to add a button with only an image
		onBackground.add(uglySweater_B);
		uglySweater_B.setBounds(25, 75, 200, 200);
		uglySweater_B.addActionListener(this);

		onBackground.add(SantaHat_B);
		SantaHat_B.setBounds(610 - 315, 75, 200, 200);
		SantaHat_B.addActionListener(this);

		onBackground.add(Onesie_B);
		Onesie_B.setBounds(820 - 275, 75, 200, 200);
		Onesie_B.addActionListener(this);
// Code to add a button with only an image ^^^^^^^

// adding check boxes
		uglySweater_CB.setMnemonic(KeyEvent.VK_E);
		uglySweater_CB.setSelected(false);
		onBackground.add(uglySweater_CB);
		uglySweater_CB.setBounds(450 - 375, 280, 115, 25);

		SantaHat_CB.setMnemonic(KeyEvent.VK_P);
		SantaHat_CB.setSelected(false);
		onBackground.add(SantaHat_CB);
		SantaHat_CB.setBounds(655 - 315, 280, 85, 25);

		Onesie_CB.setMnemonic(KeyEvent.VK_T);
		Onesie_CB.setSelected(false);
		onBackground.add(Onesie_CB);
		Onesie_CB.setBounds(900 - 300-15, 280, 130, 25);

		ImageIcon background = new ImageIcon("atlas.jpg"); // Gradient-blue-1.jpg
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
		firstName.setBounds(1050, 25 + 50, 200, 30);
		firstNamel.setBounds(980, 25 + 50, 100, 30);

		p.add(lastName);
		onBackground.add(lastNamel);
		lastName.setBounds(1050, 25 + 90, 200, 30);
		lastNamel.setBounds(980, 25 + 90, 100, 30);

		p.add(address);
		onBackground.add(addressl);
		address.setBounds(1050, 25 + 90 + 40, 200, 30);
		addressl.setBounds(980, 25 + 90 + 40, 100, 30);

		p.add(city);
		onBackground.add(cityl);
		city.setBounds(1050, 155 + 40, 200, 30);
		cityl.setBounds(980, 155 + 40, 100, 30);

		p.add(state);
		onBackground.add(statel);
		state.setBounds(1050, 155 + 80, 200, 30);
		statel.setBounds(980, 155 + 80, 100, 30);

		p.add(zip);
		onBackground.add(zipl);
		zip.setBounds(1050, 155 + 80 + 40, 200, 30);
		zipl.setBounds(980, 155 + 80 + 40, 50, 30);

		p.add(email);
		onBackground.add(emaill);
		email.setBounds(1050, 155 + 80 + 80, 200, 30);
		emaill.setBounds(980, 155 + 80 + 80, 50, 30);

		p.add(coupon);
		onBackground.add(couponl);
		coupon.setBounds(1050, 100 + 265, 200, 30);
		couponl.setBounds(980, 100 + 265, 100, 30);

		p.add(discount);
		onBackground.add(discountl);
		discount.setBounds(1050, 150 + 250, 200, 30);
		discountl.setBounds(980, 150 + 250, 100, 30);

		p.add(override);
		onBackground.add(overridel);
		override.setBounds(1050, 200 + 238, 200, 30);
		overridel.setBounds(980, 200 + 238, 100, 30);

		onBackground.add(jsppurchase);
		jsppurchase.setBounds(525, 500, 175, 150);
		jsppurchase.setVisible(false);

		onBackground.add(jsporder);
		jsporder.setBounds(150, 350, 375, 200);

		onBackground.add(jspsummary);
		jspsummary.setBounds(400, 400, 275, 250);
		jspsummary.setVisible(false);

		onBackground.add(updateTotal);
		updateTotal.setBounds(1090, 550 - 10, 125, 25);
		updateTotal.addActionListener(this);

		onBackground.add(submit);
		submit.setBounds(1090, 580, 125, 25);
		onBackground.add(add);
		add.setBounds(150 + 40, 550, 140, 25);

		onBackground.add(remove);
		remove.setBounds(340, 550, 140, 25);

		onBackground.add(changeqty);
		changeqty.setBounds(190, 580, 140, 25);

		onBackground.add(changesize);
		changesize.setBounds(340, 580, 140, 25);

// ****************************************Quantity combo box
		for (int i = 1; i < 11; i++) {
			quantity.addItem(i);
		}
		onBackground.add(quantity);
		quantity.setBounds(350 + 60, 315, 125, 30);

// ****************************************Size combo box
		size.addItem("S");
		size.addItem("M");
		size.addItem("L");
		onBackground.add(size);
		size.setBounds(100 + 60, 315, 125, 30);
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
		changesize.addActionListener(this);
		changeqty.addActionListener(this);

		String arr[] = { "Ugly Sweater", "Santa Hat", "Onesie" };
		for (int i = 0; i < arr.length; i++) {
			displayItems.addElement(new displayItems(arr[i])); }
			

	} // end of constructor


	private void PlaySound(File deck) {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == uglySweater_B) {// ****************************eye patch button
			uglySweater_CB.setSelected(true);
			SantaHat_CB.setSelected(false);
			Onesie_CB.setSelected(false);

		} else if (e.getSource() == SantaHat_B) { // ****************************pirate hat button
			uglySweater_CB.setSelected(false);
			SantaHat_CB.setSelected(true);
			Onesie_CB.setSelected(false);
		} else if (e.getSource() == Onesie_B) { // ****************************telescope button
			uglySweater_CB.setSelected(false);
			SantaHat_CB.setSelected(false);
			Onesie_CB.setSelected(true);
		} else if (e.getSource() == add) { // ****************************add button
			if (uglySweater_CB.isSelected() == true) {
				Ugly_Sweater newItem = new Ugly_Sweater();
				BigDecimal basePrice = newItem.getPrice();
				basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
// BigDecimal totalItemPrice = new BigDecimal("0");
				int selectSize = size.getSelectedIndex();
				char chSize = 'S';
				switch (selectSize) {
				case 0:
					chSize = 'S';
					break;
				case 1:
					chSize = 'M';
					break;
				case 2:
					chSize = 'L';
					break;
				}
				newItem.setSize(chSize);
				newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
				String sz = "Small";
				switch (chSize) {
				case 'S':
					sz = "Small";
					break;
				case 'M':
					sz = "Medium";
					break;
				case 'L':
					sz = "Large";
					break;
				}

				totalItemPrice = totalItemPrice
						.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				order.products.add(newItem);
				orderItems.addElement(new displayItems(
						"[" + newItem.getNumberToOrder() + "] Ugly Sweater---" + sz + " " + newItem.getMaterial()) + " $"
						+ totalItemPrice.toPlainString());

				num_ugly_sweaters += quantity.getSelectedIndex() + 1;
				numUglySweaters.setText("" + num_ugly_sweaters);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				uglySweater_CB.setSelected(false);
			} else if (SantaHat_CB.isSelected() == true) {
				Santa_Hats newItem = new Santa_Hats();
				BigDecimal basePrice = newItem.getPrice();
				basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal totalItemPrice = new BigDecimal("0");
				int selectSize = size.getSelectedIndex();
				char chSize = 'S';
				switch (selectSize) {
				case 0:
					chSize = 'S';
					break;
				case 1:
					chSize = 'M';
					break;
				case 2:
					chSize = 'L';
					break;
				}
				newItem.setSize(chSize);
				newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
				String sz = "Small";
				switch (chSize) {
				case 'S':
					sz = "Small";
					break;
				case 'M':
					sz = "Medium";
					break;
				case 'L':
					sz = "Large";
					break;
				}

				totalItemPrice = totalItemPrice
						.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				order.products.add(newItem);
				orderItems.addElement(new displayItems(
						"[" + newItem.getNumberToOrder() + "] Santa Hat---" + sz + " " + newItem.getMaterial()) + " $"
						+ totalItemPrice.toPlainString());

				num_santa_hats += quantity.getSelectedIndex() + 1;
				numSantaHats.setText("" + num_santa_hats);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				SantaHat_CB.setSelected(false);
				
			} else if (Onesie_CB.isSelected() == true) {
				Onesies newItem = new Onesies();
				BigDecimal basePrice = newItem.getPrice();
				basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal totalItemPrice = new BigDecimal("0");
				int selectSize = size.getSelectedIndex();
				char chSize = 'S';
				switch (selectSize) {
				case 0:
					chSize = 'S';
					break;
				case 1:
					chSize = 'M';
					break;
				case 2:
					chSize = 'L';
					break;
				}
				newItem.setSize(chSize);
				newItem.setNumberToOrder(quantity.getSelectedIndex() + 1);
				String sz = "Small";
				switch (chSize) {
				case 'S':
					sz = "Small";
					break;
				case 'M':
					sz = "Medium";
					break;
				case 'L':
					sz = "Large";
					break;
				}

				totalItemPrice = totalItemPrice
						.add(newItem.calcTotal(newItem.getNumberToOrder(), chSize, new BigDecimal("0")));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				order.products.add(newItem);
				orderItems.addElement(new displayItems(
						"[" + newItem.getNumberToOrder() + "] Onesie---" + sz + " " + newItem.getMagnification())
						+ "X $" + totalItemPrice.toPlainString());

				num_onesies += quantity.getSelectedIndex() + 1;
				numOnesies.setText("" + num_onesies);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				Onesie_CB.setSelected(false);
			}

		} else if (e.getSource() == remove && orderitem.isSelectionEmpty()==false) { // ****************************remove button
			@SuppressWarnings("unused")
			Product removeItem = order.products.get(orderitem.getSelectedIndex());
			String nm = removeItem.getName();
			argh.play();
			int item = 0;
			switch (nm) {
			case ("Ugly Sweater"):
				item = 0;
				break;
			case ("Onesie"):
				item = 1;
				break;
			case ("Santa Hat"):
				item = 2;
				break;
			}
			totalItemPrice = new BigDecimal("0");
			if (item == 0) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_ugly_sweaters = num_ugly_sweaters - removeItem.getNumberToOrder();
				numUglySweaters.setText("" + num_ugly_sweaters);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			} else if (item == 1) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_onesies -= removeItem.getNumberToOrder();
				numOnesies.setText("" + num_onesies);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			} else if (item == 2) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_santa_hats -= removeItem.getNumberToOrder();
				numSantaHats.setText("" + num_santa_hats);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			}

			order.products.remove(orderitem.getSelectedIndex());
			orderItems.removeElementAt(orderitem.getSelectedIndex());

		} else if (e.getSource() == changesize && orderitem.isSelectionEmpty()==false) { // ****************************************change size button

			int selectSize = size.getSelectedIndex();
			char chSize = 'S';
			switch (selectSize) {
			case 0:
				chSize = 'S';
				break;
			case 1:
				chSize = 'M';
				break;
			case 2:
				chSize = 'L';
				break;
			}
			// ****************************************************************get the size
			String sz = "Small";
			switch (chSize) {
			case 'S':
				sz = "Small";
				break;
			case 'M':
				sz = "Medium";
				break;
			case 'L':
				sz = "Large";
				break;
			}
			// ****************************************************************get item to
			// edit
			Product editItem = order.products.get(orderitem.getSelectedIndex());
			// Product tempItem = order.products.get(orderitem.getSelectedIndex());

			char tempCh = order.products.get(orderitem.getSelectedIndex()).getSize();

			editItem.setSize(chSize);

			BigDecimal totalItemPrice2 = new BigDecimal("0");

			totalItemPrice2 = totalItemPrice2
					.add(editItem.calcTotal(editItem.getNumberToOrder(), tempCh, totalItemPrice2));
			totalItemPrice2 = totalItemPrice2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			trackTotal = trackTotal.subtract(totalItemPrice2);
			totalItemPrice2 = totalItemPrice2.subtract(totalItemPrice2);
			totalItemPrice2 = totalItemPrice2
					.add(editItem.calcTotal(editItem.getNumberToOrder(), chSize, totalItemPrice2));
			totalItemPrice2 = totalItemPrice2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			order.products.get(orderitem.getSelectedIndex()).setSize(chSize);

			orderItems.add(orderitem.getSelectedIndex(),
					new displayItems(
							"[" + editItem.getNumberToOrder() + "] Ugly Sweater---" + sz + " " + editItem.getMaterial())
							+ " $" + totalItemPrice2.toPlainString());
			orderItems.remove(orderitem.getSelectedIndex() + 1);
			trackTotal = trackTotal.add(totalItemPrice2);
			numOrderTotal.setText("$" + trackTotal.toPlainString());

		} else if (e.getSource() == changeqty && orderitem.isSelectionEmpty()==false) {
			int qty = quantity.getSelectedIndex() + 1;
			// ****************************************************************get item to
			// edit
			Product editItem = order.products.get(orderitem.getSelectedIndex());
			// Product tempItem = order.products.get(orderitem.getSelectedIndex());

			String nm = editItem.getName();
			int item = 0;
			switch (nm) {
			case ("Ugly Sweater"):
				item = 0;
				break;
			case ("Onesie"):
				item = 1;
				break;
			case ("Santa Hat"):
				item = 2;
				break;
			}

			if (item == 0) {
				num_ugly_sweaters = num_ugly_sweaters - editItem.getNumberToOrder();
				numUglySweaters.setText("" + num_ugly_sweaters);

				num_ugly_sweaters = num_ugly_sweaters + qty;
				numUglySweaters.setText("" + num_ugly_sweaters);
			} else if (item == 1) {
				num_onesies -= editItem.getNumberToOrder();
				numOnesies.setText("" + num_onesies);

				num_onesies += qty;
				numOnesies.setText("" + num_onesies);
			} else if (item == 2) {
				num_santa_hats -= editItem.getNumberToOrder();
				numSantaHats.setText("" + num_santa_hats);

				num_santa_hats += qty;
				numSantaHats.setText("" + num_santa_hats);
			}

			char tempCh = order.products.get(orderitem.getSelectedIndex()).getSize();

			BigDecimal totalItemPrice2 = new BigDecimal("0");

			totalItemPrice2 = totalItemPrice2
					.add(editItem.calcTotal(editItem.getNumberToOrder(), editItem.getSize(), totalItemPrice2));
			totalItemPrice2 = totalItemPrice2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			trackTotal = trackTotal.subtract(totalItemPrice2);
			totalItemPrice2 = totalItemPrice2.subtract(totalItemPrice2);
			editItem.setNumberToOrder(qty);

			totalItemPrice2 = totalItemPrice2
					.add(editItem.calcTotal(editItem.getNumberToOrder(), editItem.getSize(), totalItemPrice2));
			totalItemPrice2 = totalItemPrice2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			order.products.get(orderitem.getSelectedIndex()).setNumberToOrder(qty);

			String sz = "Small";
			switch (editItem.getSize()) {
			case 'S':
				sz = "Small";
				break;
			case 'M':
				sz = "Medium";
				break;
			case 'L':
				sz = "Large";
				break;
			}
			orderItems.add(orderitem.getSelectedIndex(),
					new displayItems(
							"[" + editItem.getNumberToOrder() + "] Ugly Sweater---" + sz + " " + editItem.getMaterial())
							+ " $" + totalItemPrice2.toPlainString());
			orderItems.remove(orderitem.getSelectedIndex() + 1);
			trackTotal = trackTotal.add(totalItemPrice2);
			numOrderTotal.setText("$" + trackTotal.toPlainString());

		} else if (e.getSource() == submit) {
			customer.setFirstName(firstName.getText().trim());
			customer.setLastName(lastName.getText().trim());
			customer.setAddress(address.getText().trim());
			customer.setCity(city.getText().trim());
			customer.setState(state.getText().trim());
			customer.setZip(Integer.parseInt(zip.getText().trim()));
			customer.setPhoneNum(phone.getText().trim());
			customer.setEmail(email.getText().trim());

			if (cashButton.isSelected()) {
				order.setPaymentMethod("Cash");
			} else if (creditButton.isSelected()) {
				order.setPaymentMethod("Credit");
			} else if (checkButton.isSelected()) {
				order.setPaymentMethod("Check");
			}

			f.setSize(400, 600);
			f.setLocationRelativeTo(this);
			f.setTitle("Review Order");
			f.setVisible(true);
			JPanel review = new JPanel();
			JPanel reviewBtns = new JPanel();
			JTextArea ta = new JTextArea();
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

			for (int i = 0; i < order.products.size(); i++) {
				ta.append(order.products.get(i).toString() + "\n");
			}
			ta.append("\n\nUgly Sweaters purchased: " + num_ugly_sweaters);
			ta.append("\nSanta Hats purchased: " + num_santa_hats);
			ta.append("\nOnesies purchased: " + num_onesies);
			ta.append("\n\nTotal order amount: $" + trackTotal.toPlainString() + "\n");
			ta.append("\nPayment Method: " + order.getPaymentMethod());

			review.add(reviewBtns, BorderLayout.SOUTH);

			printBtn.addActionListener(action -> {
				PrintPreviewPanel ppp = new PrintPreviewPanel(trackTotal, order.products);
// submit print job:
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(ppp);

				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException x_x) {
						System.out.println("Error printing: " + x_x);
					}
				}

			});

			doneBtn.addActionListener(action -> {
				f.dispose();
			});
			orderBtn.addActionListener(action -> {
				f.dispose();

				Calendar cal = new GregorianCalendar();
				JOptionPane.showMessageDialog(null, "Thank you for your busienss. Your order has been placed",
						"InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				String st;
				for (int i = 0; i < orderItems.getSize(); i++) {

				}

				customer.setFirstName(firstName.getText().trim());
				customer.setLastName(lastName.getText().trim());
				customer.setAddress(address.getText().trim());
				customer.setCity(city.getText().trim());
				customer.setState(state.getText().trim());
				customer.setZip(Integer.parseInt(zip.getText().trim()));
				customer.setPhoneNum(phone.getText().trim());
				customer.setEmail(email.getText().trim());
				writeUsingFileWriter("" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/"
						+ cal.get(Calendar.YEAR) + "  " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE)
						+ " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM") + newline + newline
						+ customer.getFirstName() + " " + customer.getLastName() + newline + customer.getAddress()
						+ newline + customer.getCity() + ", " + customer.getState() + " " + customer.getZip() + newline
						+ customer.getPhoneNum() + newline + customer.getEmail() + newline + newline);
				for (int i = 0; i < orderItems.getSize(); i++) {
					writeUsingFileWriter(order.products.get(i).toString() + newline);
				}
				writeUsingFileWriter(newline + "Payment Method: " + order.getPaymentMethod() + newline);
				writeUsingFileWriter(newline + "Order total: $" + trackTotal + newline + newline);

			});

		} else if (e.getSource() == print) {
			customer.setFirstName(firstName.getText().trim());
			customer.setLastName(lastName.getText().trim());
			customer.setAddress(address.getText().trim());
			customer.setCity(city.getText().trim());
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
					} catch (PrinterException x_x) {
						System.out.println("Error printing: " + x_x);
					}
				}

			});
		} else if (e.getSource() == updateTotal) {
			BigDecimal disc = new BigDecimal("0");
			disc = disc.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			if (override.getText().isEmpty() == false) {

				trackTotal = new BigDecimal(override.getText().trim());
				numOrderTotal.setText("$" + trackTotal.toPlainString());

			} else if (coupon.getText().isEmpty() == false) {
				String temp = coupon.getText().trim();
				System.out.println(temp);
				if (temp.equals("merryxmas")) {
					System.out.println("in coupon");

// disc = new BigDecimal(".25");
					trackTotal = trackTotal.multiply(new BigDecimal("1").subtract(new BigDecimal(".25")));
					trackTotal = trackTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					numOrderTotal.setText("$" + trackTotal.toPlainString());
				}
			} else if (discount.getText().isEmpty() == false) {

				trackTotal = trackTotal.multiply(new BigDecimal("1").subtract(new BigDecimal(".25")));
				trackTotal = trackTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				numOrderTotal.setText("$" + trackTotal.toPlainString());
			}

		}

	}// End of action listener

	class displayItems {
		String itemlastName = "";

		displayItems(String itemlastName) {
			this.itemlastName = itemlastName;
		}

		public String toString() {
			return itemlastName;
		}

	}

	private static void writeUsingFileWriter(String data) {

		File file = new File("FileWriter.txt");
		FileWriter fr = null;

		try {
			fr = new FileWriter(file, true); // add (file, true); // to append the file
			fr.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * private static void writeUsingFileWriter(String data) { File file = new
	 * File("FileWriter.txt"); FileWriter fr = null; try { fr = new
	 * FileWriter(file); fr.write(data); } catch (IOException e) {
	 * e.printStackTrace(); } finally { // close resources try { fr.close(); } catch
	 * (IOException e) { e.printStackTrace(); } } }
	 */

} // end of class