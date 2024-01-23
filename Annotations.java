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
    private static JTextArea textArea; //tex area to write in
    private JButton buttonSave;// save button
    private JButton buttonSee; //button to see annotations
    private static String username; //username
    private static String story;// name of the story
	
    public static void setUsername (String username1) {username = username1;} //set username of the user reading the book
    public static void setStory(String story1) {story = story1;} //set name of the story
    
    public Annotations() {
        super("Annotation");
        initComponents();
    }

    private void initComponents() {
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea); //initialize a scroll bar in case annotations exceed the size of the frame
        
        buttonSave = new JButton ("Save Annotation"); //initialize button save
        buttonSee = new JButton ("See Annotation History"); //initialize button see
        
        setLayout(new BorderLayout()); //create layout
        add(scrollPane, BorderLayout.CENTER); //add scrollpane

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dont close everything, only this frame
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonSee); //add buttons to panel
        add (buttonPanel, BorderLayout.SOUTH);//put panel at the bottom
        
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
				seeAnnotations(); //shows all annotations that the user has made in this book 
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "root");
	    		Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM annotations WHERE username like '" + username
					+ "' and story like '" + story + "'");
			while(rs.next()) {
				textArea.append("\n");
				textArea.append(rs.getString("annotation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "root");
			Statement stat = con.createStatement();
			String insertQuery ="INSERT INTO Annotations(username, story, annotation) VALUES('" + user + "', '" + storyName + "', '"+ annotation + "')";
			stat.execute(insertQuery);
			textArea.append("\nAnnotation saved");
			
		} catch (SQLException e) {
			System.err.println("ERROR - CONNECTION UNSUCCESSFUL; Creating Report");
			System.err.print(e);
		}
		
    }

}
