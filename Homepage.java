import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***********************************************************************************
 * Author: Zainab Siddiqui / Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 9, 2024
 * Last Modified by: Fardin Abbassi
 * Description: A user's home page in the application
 ***********************************************************************************/
public class Homepage {
	
	private User profile;
	private Library browse;
	private Community community;
	
	JLabel label = new JLabel();
	static JFrame homepageFrame = new JFrame("Tales Around the World - HOME");
	

	Homepage(String username){
		label.setBounds(20, 420, Tales.screenW, 35);
		label.setFont(new Font(null, Font.BOLD, 30));
		label.setText("ADD WHATEVER YOU HAVE FOR HOMEPAGE HERE JAVIERA :)");
		
		// title
		JTextArea title = new JTextArea("Tales Around The World");
		title.setFont(new Font("SansSerif", Font.PLAIN, 20));
		title.setLineWrap(true);
		title.setWrapStyleWord(true);
		title.setBounds(30, 20, 150, 50);
		title.setOpaque(false);
		title.setEditable(false);			
		
    
		// set search bar panel
		JPanel searchBarPanel = new JPanel();
		searchBarPanel.setLayout(new FlowLayout());
	        JTextField searchField = new JTextField(20);
	        searchField.setText("Search for stories here"); // Set the initial placeholder text
        	searchField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				 // Clear the placeholder text when the field is focused
				if (searchField.getText().equals("Search for stories here")) {searchField.setText("");}
			}
			@Override
			public void focusLost(FocusEvent e) {
			 	// Restore the placeholder text if the field is not focused and empty
				if (searchField.getText().isEmpty()) {searchField.setText("Search for stories here");}
			}
	        });
	
	        JButton searchButton = new JButton("Search");
	        searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchTerm = searchField.getText();
				if (!searchTerm.equals("Search for stories here")) {
					/**Will add more later**/
					System.out.println("Performing search for: " +  searchTerm);	
				    }
				else {
					/**Will add more later**/
					System.out.println("Check");
				}	            	
		}});
	        searchBarPanel.add(searchField);
	        searchBarPanel.add(searchButton);
	        searchBarPanel.setBounds(title.getX() + title.getWidth(), 20, 300, 35);
	        searchBarPanel.setOpaque(false);
		
		
		// set frame
		homepageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepageFrame.setSize(Tales.screenW, Tales.screenH);
		homepageFrame.getContentPane().setLayout(null);
		homepageFrame.getContentPane().setBackground(new Color(220, 242, 206));
		homepageFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
		homepageFrame.setBackground(new Color(220, 242, 206));
		homepageFrame.setForeground(Color.black);
		
		// add to frame
		homepageFrame.add(label);
		homepageFrame.add(title);
		homepageFrame.add(searchBarPanel);
	}
}
