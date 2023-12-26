/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-12-25
 @Description: Creates a survey, where users can input their preferences to be tracked throughout the program 
***********************************************
*/


import java.awt.*;
import java.awt.event.*;

public class SurveySystem extends Frame {
	/*Genre tracking*/
	private CheckboxGroup storyGenres = new CheckboxGroup();
    private Checkbox[] genres = new Checkbox[9];
    private String[] genreNames = {"Fantasy", "Horror", "Romance", "Adventure", "Mystery", "Historical", "Non-Fiction", "Drama", "Sci-Fi"};
    private String genreResult;

	/*Region tracking*/
	private CheckboxGroup storyRegions = new CheckboxGroup();
	private Checkbox[] regions = new Checkbox[9];
    private String[] regionNames = {"Western Europe", "Eastern Europe", "Latin America", "South-West Asia / North Africa", "West/East/South Africa", "South Asia", "East Asia", "South East Asia / Oceania", "Cental Asia"};
    private String regionResult;
    
    
    
    // using this to end program when needed
    private boolean finished;

    public SurveySystem(Frame f) {
    	/**CURRENT PROBLEM: Need to work on layout; Questions need to be on separate lines**/
        f.setLayout(new FlowLayout());
        Label header = new Label("Welcome to Tales Around The World!");        
        header.setBounds(50,50, 200,200);
        Label header2 = new Label("Before continuing on, please take a few minutes to answer a few questions.");        
        header2.setBounds(75,50, 400,200);

        /*Top of survey*/
        Font font = new Font("Arial", Font.PLAIN, 15);
        f.setFont(font);
        f.add(header);
        f.add(header2);

        /*Question 1: Genre*/
        Label question1 = new Label("Which genre best represents your taste in literature?");
        question1.setBounds(100,400, 2000,10);
        for (int i = 0; i<genres.length; i++) {
        	genres[i] = new Checkbox(genreNames[i], storyGenres, false);
        	f.add(genres[i]);
        }
        Button submitButton = new Button("Confirm choice");
        f.add(question1);
        f.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < genres.length; i++) {
                	if (genres[i].getState()) {
                		genreResult = genres[i].getLabel();
                    	break;
                	}
                }
                
                /** FUTURE ISSUE: Need to take this part at the end so that chosen options will be locked**/
                if (genreResult != null) {
                	for(int j = 0; j<genres.length; j++) {
                		if(genres[j].getLabel() != genreResult)
                			genres[j].setVisible(false);
                	}
                }
                
                // Process the survey result (e.g., store in a database, display a message, etc.)
                System.out.println("Survey result: " + genreResult);
            }
        });
        
        
        
        /*Question 2: Region*/
        Label question2 = new Label("Which region would you like to see some folk tales from?");
        for (int i = 0; i<regions.length; i++) {
        	regions[i] = new Checkbox(regionNames[i], storyGenres, false);
        	f.add(regions[i]);
        }
        
        Button submitButton2 = new Button("Confirm choice");
        f.add(question2);
        f.add(submitButton2);
        Checkbox n = new Checkbox("man", false);
        
        submitButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (int i = 0; i < regions.length; i++) {
                	if (regions[i].getState()) {
                		regionResult = regions[i].getLabel();
                    	break;
                	}
                }
            	
                /** FUTURE ISSUE: Need to take this part at the end so that chosen options will be locked**/
            	if (regionResult != null) {
                	for(int j = 0; j<regions.length; j++) {
                		if(regions[j].getLabel() != regionResult)
                			regions[j].setVisible(false);
                	}
                }

                // Process the survey result (e.g., store in a database, display a message, etc.)
                System.out.println("Survey result: " + regionResult);

                // For simplicity, let's just close the window after submission
            }
        });
        
        
        
        
        // temp stop to method
        Button done = new Button("done");
        f.add(done);
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });     
        
        
        
        
        
        
        f.setTitle("Simple Survey System");
        f.setSize(1000, 200);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new SurveySystem(new Frame());
    }
}
