import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecommendationQuiz {	
	
	static JFrame f = new JFrame("Recommendation Quiz");
	
	/*Genre tracking*/
    private static String[] genreNames = {"Fantasy", "Horror", "Romance", "Adventure", "Mystery", "Historical", "Non-Fiction", "Drama", "Sci-Fi"};
    private static String genreResult;

	/*Region tracking*/
    private static String[] regionNames = {"Europe", "Latin America", "South-West Asia / North Africa", "West/East/South Africa", "South Asia", "East Asia", "South East Asia / Oceania", "Cental Asia"};
    private static String regionResult;
	
    /*Reading age range tracking*/
    private static String[] ageRanges = {"<15", "15 - 19", "20 - 29", "30 - 45", "46 - 65", ">65"};
    private static String ageRangeResult;
    
    /*Story length tracking*/
    private static String[] storyLengths = {"Short Story", "Novella", "Novel", "Epic"};
    private static String storyLengthResult;


    RecommendationQuiz() {
        f.setSize(420, (int)(420 * 1.5));

        // Important: Here is where we add the scrollable panel
        JScrollPane scrollPane = new JScrollPane(createContentPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(scrollPane);

        f.setBackground(new Color(0, 242, 206));
        f.setVisible(true);
    }

    /* Method Name: createContentPanel 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 31, 2023
	 * Modified Date: ????????????????
	 * Description: ???
	 * @Parameters: n/a
	 * @Return Value: JPanel
	 * Data Type: ???? 
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */    
    static JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        /*Title Panel*/
        JLabel title = new JLabel("Recommendation Quiz", SwingConstants.CENTER);
        title.setFont(new Font("Sans Serif", Font.BOLD, 25));
        contentPanel.add(title);
        JLabel description = new JLabel("Please take your time in answering a few questions.", SwingConstants.CENTER);
        description.setFont(new Font("SansSerif", Font.PLAIN, 16));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(description);        
        
        /*Question 1: Genre*/
        JLabel question1 = new JLabel("Which genre best represents your taste in literature?");
        JCheckBox[] genres = new JCheckBox[genreNames.length];
        ButtonGroup genreGroup = new ButtonGroup();        
        JButton submitButton = new JButton("Confirm choice");
        JPanel genrePanel = questionPanel(question1, genres, genreGroup, submitButton, genreNames);
        
        contentPanel.add(genrePanel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < genres.length; i++) {
                    if (genres[i].isSelected()) {
                        genreResult = genres[i].getText();
                        break;
                    }
                }

                if (genreResult != null) {
                    for (int j = 0; j < genres.length; j++) {
                        if (!genres[j].getText().equals(genreResult))
                            genres[j].setVisible(false);
                    }
                }

                System.out.println("Survey result: " + genreResult);
            }
        });

        /*Question 2: Region*/
        JLabel question2 = new JLabel("Which region would you like to see some folk tales from?");
        JCheckBox[] regions = new JCheckBox[regionNames.length];
        ButtonGroup regionGroup = new ButtonGroup();
        JButton submitButton2 = new JButton("Confirm choice");
        JPanel regionPanel = questionPanel(question2, regions, regionGroup, submitButton2, regionNames);
        
        contentPanel.add(regionPanel);
        submitButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < regions.length; i++) {
                    if (regions[i].isSelected()) {
                        regionResult = regions[i].getText();
                        break;
                    }
                }

                if (regionResult != null) {
                    for (int j = 0; j < regions.length; j++) {
                        if (!regions[j].getText().equals(regionResult))
                            regions[j].setVisible(false);
                    }
                }

                System.out.println("Survey result: " + regionResult);
            }
        });

        /*Question 3: Age Range*/
        JLabel question3 = new JLabel("What age range represents the stories you read?");
        JCheckBox[] ranges = new JCheckBox[ageRanges.length];
        ButtonGroup rangeGroup = new ButtonGroup();
        JButton submitButton3 = new JButton("Confirm choice");
        JPanel ageRangePanel = questionPanel(question3, ranges, rangeGroup, submitButton3, ageRanges);

        contentPanel.add(ageRangePanel);
        submitButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < ranges.length; i++) {
                    if (ranges[i].isSelected()) {
                        ageRangeResult = ranges[i].getText();
                        break;
                    }
                }

                if (ageRangeResult != null) {
                    for (int j = 0; j < ranges.length; j++) {
                        if (!ranges[j].getText().equals(ageRangeResult))
                        	ranges[j].setVisible(false);
                    }
                }

                System.out.println("Survey result: " + ageRangeResult);
            }
        });
        
        /*Question 4: Story Length*/
        JLabel question4 = new JLabel("Which best represents the lengths of the stories you like to read?");
        JCheckBox[] lengths = new JCheckBox[storyLengths.length];
        ButtonGroup lengthGroup = new ButtonGroup();
        JButton submitButton4 = new JButton("Confirm choice");
        JPanel storyLengthPanel = questionPanel(question4, lengths, lengthGroup, submitButton4, storyLengths);

        contentPanel.add(storyLengthPanel);
        submitButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < lengths.length; i++) {
                    if (lengths[i].isSelected()) {
                        storyLengthResult = lengths[i].getText();
                        break;
                    }
                }

                if (storyLengthResult != null) {
                    for (int j = 0; j < lengths.length; j++) {
                        if (!lengths[j].getText().equals(storyLengthResult))
                        	lengths[j].setVisible(false);
                    }
                }

                System.out.println("Survey result: " + storyLengthResult);
            }
        });    
        return contentPanel;
    }
    
    /* Method Name: questionPanel 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 30, 2023
	 * Modified Date: ????????????????
	 * Description: ???
	 * @Parameters: ??????
	 * @Return Value: JPanel
	 * Data Type: ???? 
	 * Dependencies: ?????
	 * Throws/Exceptions: ????
	 */
    static JPanel questionPanel(JLabel question, JCheckBox[] choices, ButtonGroup choiceGroup, JButton submitButton, String[] choiceNames) {
    	JPanel questionPanel = new JPanel();
    	questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS)); // vertically stack the components
        questionPanel.add(question);
        for (int i = 0; i < choices.length; i++) {
            choices[i] = new JCheckBox(choiceNames[i]);
            choiceGroup.add(choices[i]);
            questionPanel.add(choices[i]);
        }
        questionPanel.add(submitButton);
    	return questionPanel;
    }
}
