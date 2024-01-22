package StoryInteraction;

import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Annotations extends JFrame {
    private static JTextArea textArea;
    private JButton buttonSave;
    private JButton buttonSee;
    private static String username;
    private static String story;
//    private static Connection con;
//    private PreparedStatement preparedStatement;
    
    public static void setUsername (String username1) {username = username1;}
    public static void setStory(String story1) {story = story1;}
    public static String getUsername () {return username;}
    public static String getStory() {return story;}
    
    public Annotations() {
        super("Annotation");
        initComponents();
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://mintleafdb", "root", "root");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle connection failure
//        }
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
        
        add (buttonSave, BorderLayout.SOUTH);
        add (buttonSee, BorderLayout.SOUTH);
        
        buttonSave.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content = textArea.getText();
                //addAnnotation (content);
                //System.out.println("Text: " + content);
                addAnnotation (username, story, content);
			}
        });
        buttonSee.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
    }
//    private void saveAnnotation(String annotationText) {
//        
//        // SQL query to insert data into the annotations table
//        String insertQuery = "INSERT INTO annotations (story, username, annotation_text) VALUES ('"+story+"', '" +username+"', '" + annotationText+"')";
//
//        try {
//            preparedStatement = con.prepareStatement(insertQuery);
//            // Assuming you have a 'storyName' and 'username' variable representing your data
//            preparedStatement.setString(1, story);
//            preparedStatement.setString(2, username);
//            preparedStatement.setString(3, annotationText);
//            preparedStatement.executeUpdate();
//            System.out.println("Annotation saved to database!");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    public void seeAnnotations () {
    	
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mintleafdb", "root", "root");
	    	Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Annotations");
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
    }
    
    public void addAnnotation(String selectedText) {
        textArea.append("\n[Annotation]: \"" + selectedText + "\"\n");
        
    }
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

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new Annotations();
//        });
//    }
}
