import java.applet.Applet;
import java.applet.AudioClip;
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
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
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
	JLabel hats = new JLabel(), numHats = new JLabel();
	JLabel eyePatches = new JLabel(), numEyePatches = new JLabel();
	JLabel telescopes = new JLabel(), numTelescopes = new JLabel();
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
	int num_eye_patches, num_hats, num_telescopes;
	BigDecimal trackTotal;
	private DecimalFormat noCurrencySymbol = new DecimalFormat("###0.00");
//Adding images to the product buttons
	ImageIcon eyePatch_I = new ImageIcon("eyePatchGuy.jpg");
	JButton eyePatch_B = new JButton(eyePatch_I);
	ImageIcon hat_I = new ImageIcon("pirateHatGuy.jpg");
	JButton hat_B = new JButton(hat_I);
	ImageIcon telescope_I = new ImageIcon("pirateTelescope.jpg");
	JButton telescope_B = new JButton(telescope_I);

//CheckBoxes
	JCheckBox pirateHat_CB = new JCheckBox("Pirate hat");
	JCheckBox eyePatch_CB = new JCheckBox("Eye Patch");
	JCheckBox telescope_CB = new JCheckBox("Telescope");

	JFrame f = new JFrame();

	//BigDecimal totalItemPrice = new BigDecimal("0");

	public static String newline = System.getProperty("line.separator");
	

    JRadioButton cashButton = new JRadioButton("Cash");
    JRadioButton creditButton = new JRadioButton("Credit");
    JRadioButton checkButton = new JRadioButton("Check");
    ButtonGroup group = new ButtonGroup();
    
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    @SuppressWarnings("deprecation")
	AudioClip christmasSound; 



	public static void main(String[] args) {

		MainPos app = new MainPos();
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
		app.setTitle("SSDUKE Pirate Emporium");
		app.setVisible(true);
	}
	
	
	public void playSound(String filename){

        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
//}

	

	@SuppressWarnings({ "unchecked", "deprecation" })
	MainPos() {
		
		URL christmasURL;
		try {
			christmasURL = new URL("file:xmas.wav");
			christmasSound = Applet.newAudioClip(christmasURL);
			
			

		} catch (MalformedURLException frack) {
			frack.printStackTrace();
		}
		
		//playSound("C:\\Users\\Kevin\\Documents\\Eclipse_Java\\Final_Project\\xmas.wav");
		christmasSound.play();
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

	    //Group the radio buttons.
	    group.add(cashButton);
	    group.add(creditButton);
	    group.add(checkButton);


	    //Register a listener for the radio buttons.
	    cashButton.addActionListener(this);
	    creditButton.addActionListener(this);
	    checkButton.addActionListener(this);


		trackTotal = new BigDecimal("0");
		num_eye_patches = 0;
		num_hats = 0;
		num_telescopes = 0;
		selectsize.setText("Select Size:");
		selectsize.setFont(new Font("Calambri", Font.BOLD, 12));
		selectsize.setBounds(25 + 50, 280, 100, 100);
		onBackground.add(selectsize);

		selectquantity.setText("Select Quantity:");
		selectquantity.setFont(new Font("Calambri", Font.BOLD, 12));
		selectquantity.setBounds(250 + 60, 280, 100, 100);
		onBackground.add(selectquantity);

		eyePatches.setFont(new Font("Courier New", Font.BOLD, 26));
		eyePatches.setForeground(Color.BLUE.darker());
		eyePatches.setText("EYE PATCHES");
		eyePatches.setBounds(730 - 30, 70 + 260, 200, 24);

		numEyePatches.setFont(new Font("Courier New", Font.BOLD, 26));
		numEyePatches.setForeground(Color.BLUE.darker());
		numEyePatches.setText("-0-");
		numEyePatches.setBounds(790 - 30, 100 + 260, 200, 24);
		onBackground.add(eyePatches);
		onBackground.add(numEyePatches);

		hats.setFont(new Font("Courier New", Font.BOLD, 26));
		hats.setForeground(Color.BLUE.darker());
		hats.setText("HATS");
		hats.setBounds(775 - 30, 130 + 270, 200, 24);
		numHats.setFont(new Font("Courier New", Font.BOLD, 24));
		numHats.setForeground(Color.BLUE.darker());
		numHats.setText("-0-");
		numHats.setBounds(790 - 30, 160 + 270, 200, 24);
		onBackground.add(hats);
		onBackground.add(numHats);

		telescopes.setFont(new Font("Courier New", Font.BOLD, 26));
		telescopes.setForeground(Color.BLUE.darker());
		telescopes.setText("TELESCOPES");
		telescopes.setBounds(735 - 30, 190 + 280, 200, 24);
		numTelescopes.setFont(new Font("Courier New", Font.BOLD, 24));
		numTelescopes.setForeground(Color.BLUE.darker());
		numTelescopes.setText("-0-");
		numTelescopes.setBounds(790 - 30, 210 + 280, 200, 24);
		onBackground.add(telescopes);
		onBackground.add(numTelescopes);

		orderTotal.setFont(new Font("Courier New", Font.BOLD, 30));
		orderTotal.setForeground(Color.BLUE.darker());
		orderTotal.setText("ORDER TOTAL");
		orderTotal.setBounds(730 - 30, 540 - 10, 200, 24);
		numOrderTotal.setFont(new Font("Courier New", 1, 24));
		numOrderTotal.setForeground(Color.BLUE.darker());
		numOrderTotal.setText("$-0-");
		numOrderTotal.setBounds(775 - 30, 560, 200, 24);
		onBackground.add(orderTotal);
		onBackground.add(numOrderTotal);

		JPanel p = new JPanel();
		p.setLayout(null);

		JLabel perryImg = new JLabel();

// Code to add a button with only an image
		onBackground.add(eyePatch_B);
		eyePatch_B.setBounds(25, 75, 200, 200);
		eyePatch_B.addActionListener(this);

		onBackground.add(hat_B);
		hat_B.setBounds(610 - 315, 75, 200, 200);
		hat_B.addActionListener(this);

		onBackground.add(telescope_B);
		telescope_B.setBounds(820 - 275, 75, 200, 200);
		telescope_B.addActionListener(this);
// Code to add a button with only an image ^^^^^^^

// adding check boxes
		eyePatch_CB.setMnemonic(KeyEvent.VK_E);
		eyePatch_CB.setSelected(false);
		onBackground.add(eyePatch_CB);
		eyePatch_CB.setBounds(450 - 375, 280, 85, 25);

		pirateHat_CB.setMnemonic(KeyEvent.VK_P);
		pirateHat_CB.setSelected(false);
		onBackground.add(pirateHat_CB);
		pirateHat_CB.setBounds(655 - 315, 280, 85, 25);

		telescope_CB.setMnemonic(KeyEvent.VK_T);
		telescope_CB.setSelected(false);
		onBackground.add(telescope_CB);
		telescope_CB.setBounds(900 - 300, 280, 100, 25);

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

		String arr[] = { "Eye Patch", "Pirate Hat", "Telescope" };
		for (int i = 0; i < arr.length; i++) {
			displayItems.addElement(new displayItems(arr[i]));
		}

	} // end of constructor

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == eyePatch_B) { // ****************************eye patch button
			eyePatch_CB.setSelected(true);
			pirateHat_CB.setSelected(false);
			telescope_CB.setSelected(false);

		} else if (e.getSource() == hat_B) { // ****************************pirate hat button
			eyePatch_CB.setSelected(false);
			pirateHat_CB.setSelected(true);
			telescope_CB.setSelected(false);
		} else if (e.getSource() == telescope_B) { // ****************************telescope button
			eyePatch_CB.setSelected(false);
			pirateHat_CB.setSelected(false);
			telescope_CB.setSelected(true);
		} else if (e.getSource() == add) { // ****************************add button
			if (eyePatch_CB.isSelected() == true) {
				Eye_Patches newItem = new Eye_Patches();
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
						"[" + newItem.getNumberToOrder() + "] Eye Patch---" + sz + " " + newItem.getMaterial()) + " $"
						+ totalItemPrice.toPlainString());

				num_eye_patches += quantity.getSelectedIndex() + 1;
				numEyePatches.setText("" + num_eye_patches);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				eyePatch_CB.setSelected(false);
			} else if (pirateHat_CB.isSelected() == true) {
				Pirate_Hats newItem = new Pirate_Hats();
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
						"[" + newItem.getNumberToOrder() + "] Pirate Hat---" + sz + " " + newItem.getMaterial()) + " $"
						+ totalItemPrice.toPlainString());

				num_hats += quantity.getSelectedIndex() + 1;
				numHats.setText("" + num_hats);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				pirateHat_CB.setSelected(false);
			} else if (telescope_CB.isSelected() == true) {
				Telescopes newItem = new Telescopes();
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
						"[" + newItem.getNumberToOrder() + "] Telescope---" + sz + " " + newItem.getMagnification())
						+ "X $" + totalItemPrice.toPlainString());

				num_telescopes += quantity.getSelectedIndex() + 1;
				numTelescopes.setText("" + num_telescopes);
				trackTotal = trackTotal.add(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
				telescope_CB.setSelected(false);
			}

		} else if (e.getSource() == remove) { // ****************************remove button
			@SuppressWarnings("unused")
			Product removeItem = order.products.get(orderitem.getSelectedIndex());
			String nm = removeItem.getName();
			int item = 0;
			switch (nm) {
			case ("Eye Patch"):
				item = 0;
				break;
			case ("Telescope"):
				item = 1;
				break;
			case ("Pirate Hat"):
				item = 2;
				break;
			}
			totalItemPrice = new BigDecimal("0");
			if (item == 0) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_eye_patches = num_eye_patches - removeItem.getNumberToOrder();
				numEyePatches.setText("" + num_eye_patches);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			} else if (item == 1) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_telescopes -= removeItem.getNumberToOrder();
				numTelescopes.setText("" + num_telescopes);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			} else if (item == 2) {
				totalItemPrice = totalItemPrice
						.add(removeItem.calcTotal(removeItem.getNumberToOrder(), removeItem.getSize(), totalItemPrice));
				totalItemPrice = totalItemPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				num_hats -= removeItem.getNumberToOrder();
				numHats.setText("" + num_hats);
				trackTotal = trackTotal.subtract(totalItemPrice);
				numOrderTotal.setText("$" + trackTotal.toPlainString());
			}

			order.products.remove(orderitem.getSelectedIndex());
			orderItems.removeElementAt(orderitem.getSelectedIndex());

		} else if (e.getSource() == changesize) { // ****************************************change size button

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
							"[" + editItem.getNumberToOrder() + "] Eye Patch---" + sz + " " + editItem.getMaterial())
							+ " $" + totalItemPrice2.toPlainString());
			orderItems.remove(orderitem.getSelectedIndex() + 1);
			trackTotal = trackTotal.add(totalItemPrice2);
			numOrderTotal.setText("$" + trackTotal.toPlainString());
			
			
			
			
		} else if (e.getSource() == changeqty) {
			int qty = quantity.getSelectedIndex() + 1;
			// ****************************************************************get item to
			// edit
			Product editItem = order.products.get(orderitem.getSelectedIndex());
			// Product tempItem = order.products.get(orderitem.getSelectedIndex());
			
			String nm = editItem.getName();
			int item = 0;
			switch (nm) {
			case ("Eye Patch"):
				item = 0;
				break;
			case ("Telescope"):
				item = 1;
				break;
			case ("Pirate Hat"):
				item = 2;
				break;
			}
			
			if(item == 0) {
				num_eye_patches = num_eye_patches - editItem.getNumberToOrder();
				numEyePatches.setText("" + num_eye_patches);
				
				num_eye_patches = num_eye_patches + qty;
				numEyePatches.setText("" + num_eye_patches);
			}else if(item == 1) {
				num_telescopes -= editItem.getNumberToOrder();
				numTelescopes.setText("" + num_telescopes);
				
				num_telescopes += qty;
				numTelescopes.setText("" + num_telescopes);
			}else if(item == 2) {
				num_hats -= editItem.getNumberToOrder();
				numHats.setText("" + num_hats);
				
				num_hats += qty;
				numHats.setText("" + num_hats);
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
							"[" + editItem.getNumberToOrder() + "] Eye Patch---" + sz + " " + editItem.getMaterial())
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
			
			if(cashButton.isSelected()) {
				order.setPaymentMethod("Cash");
			}else if(creditButton.isSelected()) {
				order.setPaymentMethod("Credit");
			}else if(checkButton.isSelected()) {
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
			ta.append("\n\nEye Patches purchased: " + num_eye_patches);
			ta.append("\nPirate Hats purchased: " + num_hats);
			ta.append("\nTelescopes purchased: " + num_telescopes);
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
				JOptionPane.showMessageDialog(null, "Thank you for your busienss. Your order has been placed", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
				String st; 
				for(int i = 0; i < orderItems.getSize(); i++) {
					
				}
				
				customer.setFirstName(firstName.getText().trim());
				customer.setLastName(lastName.getText().trim());
				customer.setAddress(address.getText().trim());
				customer.setCity(city.getText().trim());
				customer.setState(state.getText().trim());
				customer.setZip(Integer.parseInt(zip.getText().trim()));
				customer.setPhoneNum(phone.getText().trim());
				customer.setEmail(email.getText().trim());
				writeUsingFileWriter( "" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) 
									+ "/" + cal.get(Calendar.YEAR) + "  " + cal.get(Calendar.HOUR_OF_DAY) 
									+ ":" + cal.get(Calendar.MINUTE) + " " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM")
									+ newline + newline + customer.getFirstName() + " " + customer.getLastName() + newline +
									 customer.getAddress() + newline + customer.getCity() + ", " + customer.getState() + " " +
									 customer.getZip() + newline + customer.getPhoneNum() + newline + customer.getEmail() + newline + newline);
				for(int i = 0; i < orderItems.getSize(); i++) {
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
				if (temp.equals("12345")) {
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
            fr = new FileWriter(file, true); //add (file, true); // to append the file
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*
	private static void writeUsingFileWriter(String data) {
		File file = new File("FileWriter.txt");
		FileWriter fr = null;
		try {
			fr = new FileWriter(file);
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
	*/

} // end of class
