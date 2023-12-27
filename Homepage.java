import java.awt.*;
import javax.swing.*;
/***********************************************************************************
 * Author: Zainab Siddiqui / Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified:
 * Last Modified by: 
 * Description: A user's home page in the application
 ***********************************************************************************/
public class Homepage {
	
	JLabel label = new JLabel();
	JFrame homepageFrame = new JFrame("Tales Around the World - HOME");
	
	Homepage(String username){
		label.setBounds(20, 20, Tales.screenW, 35);
		label.setFont(new Font(null, Font.BOLD, 30));
		label.setText("ADD WHATEVER YOU HAVE FOR HOMEPAGE HERE JAVIERA :)");
		
		// set frame
		homepageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepageFrame.setSize(Tales.screenW, Tales.screenH);
		homepageFrame.getContentPane().setLayout(null);
		homepageFrame.getContentPane().setBackground(new Color(220, 242, 206));
		homepageFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
		homepageFrame.setBackground(new Color(220, 242, 206));
		homepageFrame.setForeground(new Color(255, 255, 255));
		homepageFrame.setVisible(true);
		
		// add to frame
		homepageFrame.add(label);
	}
}
