import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

//To draw emporuim name with background
class MyPanel extends JPanel {
    
    JLabel date = new JLabel("" + java.time.LocalDate.now());
    String strDate = "" + java.time.LocalDate.now();
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       // try {
         //   File pathToFile = new File("background_3.jpg");
           // Image image = ImageIO.read(pathToFile);
       		//g.drawImage(image, 0, 0, this);

        //} catch (IOException ex) {
          //  ex.printStackTrace();
        //}
        
		//g.setColor( new Color( 153, 204, 255, 255) );
		g.setColor(Color.GREEN.darker() );
		g.setFont( new Font("Stencil", 1, 44) ); //Old English Text MT
		g.drawString("- THE CHRISTMAS STORE -", 350, 40);
		
		g.setColor(Color.RED.darker());
		g.setColor(Color.RED.darker());
		g.setFont( new Font("Stencil", 1, 44) );
		g.drawString("- THE CHRISTMAS STORE -", 349, 39);
		
		g.setColor(Color.GREEN.darker());
		g.setFont( new Font("Stencil", 0, 20) );
		g.drawString(strDate, 100, 35);
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400); // As suggested by camickr
    }
}