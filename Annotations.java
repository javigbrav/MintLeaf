/***********************************************************************************
 * Author: Javiera Garrido
 * Date: December 20, 2023 
 * Last Modified: 22 Jan, 2024
 * Description: creates a frame to interact with the annotations
 ***********************************************************************************/

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Annotations extends JFrame {
	/* Global variables */
	private static JTextArea textArea;
    private JButton buttonSave;
    private JButton buttonSee;
    private static String username;
    private static String story;
    
    // setters for username and story
    public static void setUsername (String username1) {username = username1;}
    public static void setStory(String story1) {story = story1;}
    
    public Annotations() {
        super("Annotation");
        initComponents();
    }

    private void initComponents() {
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        buttonSave = new JButton ("Save Annotation"); 
        buttonSee = new JButton ("See Annotation History");
        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonSee);
        add (buttonPanel, BorderLayout.SOUTH);
        
        buttonSave.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = textArea.getText();
                addAnnotation (username, story, content); //adds the annotation to the database
			}
        });
        buttonSee.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				seeAnnotations();
			}
        });
    }
    
	/* Method Name: seeAnnotations
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 22, 2024
	 * Modified Date: January 22, 2024
	 * Description: allows the user to see all annotations that has made in the past in this story
	 * @Parameters: n/a
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: mySQL
	 * Throws/Exceptions: error when connecting to mysql 
	 */  
    public void seeAnnotations () {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
	    	Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM annotations WHERE username like '" + username
					+ "' and story like '" + story + "'");
			while(rs.next()) {
				textArea.append("\n");
				textArea.append(rs.getString("annotation"));
			}
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Finding annotations");
			System.err.print(e);
		}			
    }    
    
    /* Method Name: addAnnotations
	 * Author: Javiera Garrido Bravo
	 * Creation Date: January 20, 2024
	 * Modified Date: January 22, 2024
	 * Description: allows the user to add an annotation to a snippet of the story
	 * @Parameters: String user: user that uses the application, String storyName: name of the story
  	 * String annotation: value that's going to be saved
	 * @Return Value: n/a
	 * Data Type: void
	 * Dependencies: mySQL
	 * Throws/Exceptions: error when connecting to mysql 
	 */  
    public static void addAnnotation(String user, String storyName, String annotation) {
		try {
	 		/** When doing this locally, swap "MintLeaf" with your local password**/
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "MintLeaf");
			Statement stat = con.createStatement();
			String insertQuery = "INSERT INTO Annotations(username, story, annotation) VALUES('" + user + "', '" + storyName + "', '"+ annotation + "')";
			stat.execute(insertQuery);
			textArea.append("\nAnnotation saved!");
			
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Saving annotation");
			System.err.print(e);
		}
		
    }
}