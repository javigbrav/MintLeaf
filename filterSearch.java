package library;
import javax.swing.*;
import mains.Homepage;
import mains.Tales;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class filterSearch {
	JFrame filterFrame = new JFrame();
    String databasePath = "jdbc:mysql://your_database_host:your_database_port/your_database_name";
    String image1Path = "C:\\Users\\kashi\\Downloads\\filter.png";
	Connection con;
	JPanel genrePanel = new JPanel(), agegroupPanel = new JPanel(), ratingPanel = new JPanel(), regionPanel = new JPanel(), lengthPanel = new JPanel(), languagePanel = new JPanel();
	JCheckBox languageEnglishCheckbox;
	
	public filterSearch() {
		filterFrame.setBackground(new Color(255, 255, 255));
		filterFrame.setForeground(new Color(220, 242, 206));
		filterFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(image1Path));
		filterFrame.setLocation(-142, -430);

		// Filter categories
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblGenre.setBounds(10, 50, 112, 19);
		filterFrame.getContentPane().add(lblGenre);

		JLabel lblAgeGroup = new JLabel("Age:");
		lblAgeGroup.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblAgeGroup.setBounds(10, 170, 62, 32);
		filterFrame.getContentPane().add(lblAgeGroup);

		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRegion.setBounds(10, 279, 85, 43);
		filterFrame.getContentPane().add(lblRegion);

		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblLanguage.setBounds(10, 443, 85, 62);
		filterFrame.getContentPane().add(lblLanguage);
		
		// Panels for each filter category
		JPanel genrePanel = new JPanel();
		genrePanel.setBounds(96, 50, 463, 92);
		genrePanel.setLayout(null);
		filterFrame.getContentPane().add(genrePanel);
		
		JPanel agegroupPanel = new JPanel();
		agegroupPanel.setBounds(96, 159, 459, 43);
		agegroupPanel.setLayout(null);
		filterFrame.getContentPane().add(agegroupPanel);
		
		JPanel ratingPanel = new JPanel();
		ratingPanel.setBounds(96, 222, 463, 43);
		ratingPanel.setLayout(null);
		filterFrame.getContentPane().add(ratingPanel);
		
		JPanel regionPanel = new JPanel();
		regionPanel.setBounds(96, 276, 463, 102);
		regionPanel.setLayout(null);
		filterFrame.getContentPane().add(regionPanel);
		
		JPanel lengthPanel = new JPanel();
		lengthPanel.setBounds(96, 399, 463, 32);
		lengthPanel.setLayout(null);
		filterFrame.getContentPane().add(lengthPanel);
		
		JPanel languagePanel = new JPanel();
		languagePanel.setBounds(96, 459, 459, 32);
		languagePanel.setLayout(null);
		filterFrame.getContentPane().add(languagePanel);
		
		// Filter button
		JButton filterButton = new JButton("FILTER");
		filterFrame.getContentPane().add(filterButton);
		
		// Genre options
		
		// Romance
		JCheckBox romanceCheckbox = new JCheckBox("Romance");
		romanceCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		romanceCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		romanceCheckbox.setPreferredSize(new Dimension(100, 25));
		romanceCheckbox.setBounds(6, 59, 93, 23);
		genrePanel.add(romanceCheckbox);
		
		// Horror
		JCheckBox horrorCheckbox = new JCheckBox("Horror");
		horrorCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		horrorCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		horrorCheckbox.setPreferredSize(new Dimension(100, 25));
		horrorCheckbox.setBounds(6, 33, 93, 23);
		genrePanel.add(horrorCheckbox);
		
		// Fantasy
		JCheckBox fantasyCheckbox = new JCheckBox("Fantasy");
		fantasyCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		fantasyCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		fantasyCheckbox.setPreferredSize(new Dimension(100, 25));
		fantasyCheckbox.setBounds(6, 7, 93, 23);
		genrePanel.add(fantasyCheckbox);

		// Adventure
		JCheckBox adventureCheckbox = new JCheckBox("Adventure");
		adventureCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		adventureCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		adventureCheckbox.setPreferredSize(new Dimension(100, 25));
		adventureCheckbox.setBounds(125, 7, 115, 23);
		genrePanel.add(adventureCheckbox);
		
		// Mystery
		JCheckBox mysteryCheckbox = new JCheckBox("Mystery");
		mysteryCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		mysteryCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		mysteryCheckbox.setPreferredSize(new Dimension(100, 25));
		mysteryCheckbox.setBounds(125, 33, 93, 23);
		genrePanel.add(mysteryCheckbox);
		
		// Historical
		JCheckBox historicalCheckbox = new JCheckBox("Historical");
		historicalCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		historicalCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		historicalCheckbox.setPreferredSize(new Dimension(100, 25));
		historicalCheckbox.setBounds(125, 59, 93, 23);
		genrePanel.add(historicalCheckbox);
		
		// Non-fiction
		JCheckBox nonfictionCheckbox = new JCheckBox("Non-Fiction");
		nonfictionCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		nonfictionCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		nonfictionCheckbox.setPreferredSize(new Dimension(100, 25));
		nonfictionCheckbox.setBounds(255, 7, 115, 23);
		genrePanel.add(nonfictionCheckbox);
		
		// Drama
		JCheckBox dramaCheckbox = new JCheckBox("Drama");
		dramaCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		dramaCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		dramaCheckbox.setPreferredSize(new Dimension(100, 25));
		dramaCheckbox.setBounds(255, 33, 85, 23);
		genrePanel.add(dramaCheckbox);
		
		// Science Fiction
		JCheckBox scifiCheckbox = new JCheckBox("Sci-Fi");
		scifiCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		scifiCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		scifiCheckbox.setPreferredSize(new Dimension(100, 25));
		scifiCheckbox.setBounds(255, 59, 85, 23);
		genrePanel.add(scifiCheckbox);
		
		// Fairy Tale
		JCheckBox fairyTaleCheckbox = new JCheckBox("Fairy Tale");
		fairyTaleCheckbox.setBounds(new Rectangle(360, 59, 97, 23));
		fairyTaleCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		genrePanel.add(fairyTaleCheckbox);
		
		// Age Group options
		
		// Child
		JCheckBox ageGroupChildCheckbox = new JCheckBox("Child");
		ageGroupChildCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupChildCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupChildCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupChildCheckbox.setBounds(6, 7, 61, 23);
		agegroupPanel.add(ageGroupChildCheckbox);
		
		// Teen
		JCheckBox ageGroupTeenCheckbox = new JCheckBox("Teen");
		ageGroupTeenCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupTeenCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupTeenCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupTeenCheckbox.setBounds(125, 7, 79, 23);
		agegroupPanel.add(ageGroupTeenCheckbox);
		
		// Adult
		JCheckBox ageGroupAdultCheckbox = new JCheckBox("Adult");
		ageGroupAdultCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupAdultCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupAdultCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupAdultCheckbox.setBounds(255, 7, 85, 23);
		agegroupPanel.add(ageGroupAdultCheckbox);

		// Story Rating options
		JCheckBox ratingCheckbox1 = new JCheckBox("1 star");
		ratingCheckbox1.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox1.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox1.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox1.setBounds(6, 7, 79, 23);
		ratingPanel.add(ratingCheckbox1);
		
		JCheckBox ratingCheckbox2 = new JCheckBox("2 stars");
		ratingCheckbox2.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox2.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox2.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox2.setBounds(86, 7, 85, 23);
		ratingPanel.add(ratingCheckbox2);

		JCheckBox ratingCheckbox3 = new JCheckBox("3 stars");
		ratingCheckbox3.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox3.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox3.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox3.setBounds(173, 7, 85, 23);
		ratingPanel.add(ratingCheckbox3);
		
		JCheckBox ratingCheckbox4 = new JCheckBox("4 stars");
		ratingCheckbox4.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox4.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox4.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox4.setBounds(260, 7, 85, 23);
		ratingPanel.add(ratingCheckbox4);
		
		JCheckBox ratingCheckbox5 = new JCheckBox("5 stars");
		ratingCheckbox5.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox5.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox5.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox5.setBounds(360, 7, 85, 23);
		ratingPanel.add(ratingCheckbox5);
		
		// Story Length options
		
		// Short Story
		JCheckBox chckbxShortStory = new JCheckBox("Short Story");
		chckbxShortStory.setPreferredSize(new Dimension(100, 25));
		chckbxShortStory.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxShortStory.setBounds(new Rectangle(0, 0, 115, 0));
		chckbxShortStory.setActionCommand("");
		chckbxShortStory.setBounds(6, 7, 117, 23);
		lengthPanel.add(chckbxShortStory);
		
		// Novella
		JCheckBox chckbxNovella = new JCheckBox("Novella");
		chckbxNovella.setPreferredSize(new Dimension(100, 25));
		chckbxNovella.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxNovella.setBounds(new Rectangle(0, 0, 115, 0));
		chckbxNovella.setActionCommand("");
		chckbxNovella.setBounds(125, 7, 85, 23);
		lengthPanel.add(chckbxNovella);
		
		// Novel
		JCheckBox chckbxNovel = new JCheckBox("Novel");
		chckbxNovel.setPreferredSize(new Dimension(100, 25));
		chckbxNovel.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxNovel.setBounds(new Rectangle(0, 0, 115, 0));
		chckbxNovel.setActionCommand("");
		chckbxNovel.setBounds(255, 7, 85, 23);
		lengthPanel.add(chckbxNovel);
		
		// Epic
		JCheckBox chckbxEpic = new JCheckBox("Epic");
		chckbxEpic.setPreferredSize(new Dimension(100, 25));
		chckbxEpic.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxEpic.setBounds(new Rectangle(0, 0, 115, 0));
		chckbxEpic.setActionCommand("");
		chckbxEpic.setBounds(360, 7, 85, 23);
		lengthPanel.add(chckbxEpic);
		
		// Region Options
		JCheckBox region1Checkbox = new JCheckBox("Europe");
		region1Checkbox.setBounds(6, 19, 107, 23);
		regionPanel.add(region1Checkbox);
		region1Checkbox.setPreferredSize(new Dimension(100, 25));
		region1Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region2Checkbox = new JCheckBox("Latin America");
		region2Checkbox.setBounds(6, 45, 117, 23);
		regionPanel.add(region2Checkbox);
		region2Checkbox.setPreferredSize(new Dimension(100, 25));
		region2Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region3Checkbox = new JCheckBox("Middle East");
		region3Checkbox.setBounds(125, 45, 115, 23);
		regionPanel.add(region3Checkbox);
		region3Checkbox.setPreferredSize(new Dimension(100, 25));
		region3Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region4Checkbox = new JCheckBox("West/East/South Africa");
		region4Checkbox.setBounds(255, 45, 175, 23);
		regionPanel.add(region4Checkbox);
		region4Checkbox.setPreferredSize(new Dimension(100, 25));
		region4Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region5Checkbox = new JCheckBox("South Asia");
		region5Checkbox.setBounds(125, 19, 115, 23);
		regionPanel.add(region5Checkbox);
		region5Checkbox.setPreferredSize(new Dimension(100, 25));
		region5Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region6Checkbox = new JCheckBox("South East Asia/Oceania");
		region6Checkbox.setBounds(255, 19, 198, 23);
		regionPanel.add(region6Checkbox);
		region6Checkbox.setPreferredSize(new Dimension(100, 25));
		region6Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox region7Checkbox = new JCheckBox("Central Asia");
		region7Checkbox.setBounds(6, 71, 143, 23);
		regionPanel.add(region7Checkbox);
		region7Checkbox.setPreferredSize(new Dimension(100, 25));
		region7Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		// Language Options
		languageEnglishCheckbox = new JCheckBox("English");
		languageEnglishCheckbox.setBounds(6, 7, 85, 23);
		languagePanel.add(languageEnglishCheckbox);
		languageEnglishCheckbox.setPreferredSize(new Dimension(100, 25));
		languageEnglishCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox languageSpanishCheckbox = new JCheckBox("Spanish");
		languageSpanishCheckbox.setBounds(125, 7, 89, 23);
		languagePanel.add(languageSpanishCheckbox);
		languageSpanishCheckbox.setPreferredSize(new Dimension(100, 25));
		languageSpanishCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox languageArabicCheckbox = new JCheckBox("Arabic");
		languageArabicCheckbox.setBounds(255, 7, 85, 23);
		languagePanel.add(languageArabicCheckbox);
		languageArabicCheckbox.setPreferredSize(new Dimension(100, 25));
		languageArabicCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JCheckBox languageSansrkitCheckbox = new JCheckBox("Sanskrit");
		languageSansrkitCheckbox.setBounds(360, 7, 85, 23);
		languagePanel.add(languageSansrkitCheckbox);
		languageSansrkitCheckbox.setPreferredSize(new Dimension(100, 25));
		languageSansrkitCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		// Filter searches when filter button is pressed
		filterButton.setForeground(new Color(255, 255, 255));
		filterButton.setBackground(new Color(88, 153, 47));
		filterButton.setBounds(0, 500, 586, 43);
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Define the categories
			    String[] categories = {"Genre", "Age", "Rating", "Region",  "Length", "Language", "Translate"};

			    // Create a 2D array to store selected options
			    String[][] selectedOptions = new String[categories.length][];

			    // Iterate through each category and get selected options
			    for (int i = 0; i < categories.length; i++) {
			        selectedOptions[i] = getSelectedCheckboxValues(getPanelForCategory(categories[i]));
			        printSelectedValues("Selected " + categories[i] + ": ", selectedOptions[i]);
			    }

			    // Search the database with all selected options
			    searchDatabase(selectedOptions, categories);
			}
		});
		filterFrame.getContentPane().setLayout(null);
		
		JLabel lblLength_2 = new JLabel("Length:");
		lblLength_2.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblLength_2.setBounds(10, 400, 85, 32);
		filterFrame.getContentPane().add(lblLength_2);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRating.setBounds(10, 225, 85, 43);
		filterFrame.getContentPane().add(lblRating);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 150, 714, 8);
		filterFrame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 215, 714, 8);
		filterFrame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 271, 714, 8);
		filterFrame.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 390, 714, 8);
		filterFrame.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 446, 714, 8);
		filterFrame.getContentPane().add(separator_4);
		
		JLabel lblNewLabel = new JLabel("What are you looking for?");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 284, 29);
		filterFrame.getContentPane().add(lblNewLabel);
		
		filterFrame.setLocationRelativeTo(Homepage.filter);
		filterFrame.setSize(600, 580);
		filterFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		filterFrame.setVisible(true);
	}

	private String[] getRatings(String[] selectedRatings) {
		for (int i = 0; i < selectedRatings.length; i++)
			selectedRatings[i] = selectedRatings[i].replaceAll("[^0-9]", "");
		return selectedRatings;
	}
	
	// Utility method to get selected check box values
	private String[] getSelectedCheckboxValues(JPanel checkboxPanel) {
		LinkedList<String> selectedValuesList = new LinkedList<>();
		Component[] components = checkboxPanel.getComponents();

		for (int i = 0; i < components.length; i++) {
			Component component = components[i];
			if (component instanceof JCheckBox) {
				JCheckBox checkbox = (JCheckBox) component;
				if (checkbox.isSelected()) {
					selectedValuesList.add(checkbox.getText());
				}
			}
		}

		return selectedValuesList.toArray(new String[0]);
	}

	private void printSelectedValues(String label, String[] selectedValues) {
	    System.out.print(label);
	    if (selectedValues.length > 0) {
	        System.out.print(selectedValues[0]);
	        for (int i = 1; i < selectedValues.length; i++) {
	            System.out.print(", " + selectedValues[i]);
	        }
	    }
	    System.out.println();
	}
	
	private JPanel getPanelForCategory(String category) {
	    switch (category) {
	        case "Genre":
	            return genrePanel;
	        case "Age":
	            return agegroupPanel;
	        case "Rating":
	        	return ratingPanel;
	        case "Region": 
	        	return regionPanel;
	        case "Length": 
	        	return lengthPanel;
	        case "Language": case "Translate":
	        	return languagePanel;	        	
	        default:
	            throw new IllegalArgumentException("Unknown category: " + category);
	    } // end
	}

	
	private void searchDatabase(String[][] selectedCategories, String[] categories) {
		    if (selectedCategories.length == 0) {
		        return; // no option selected
		    }

		    // Construct the query string based on your database schema
		    StringBuilder query = new StringBuilder("SELECT Title FROM STORIES WHERE ");

		    // Append options for each category to the query
		    for (int i = 0; i < categories.length; i++) {
		        appendOptionsToQuery(query, categories[i], selectedCategories[i]);
		    }

		    // Check if the query is not empty
		    if (query.toString().contains("WHERE ")) {
		        query.setLength(query.lastIndexOf("WHERE "));
		    } else {
		        return; // no options selected
		    }

		    try (Connection connection = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
		         PreparedStatement stat = connection.prepareStatement(query.toString());
		         ResultSet result = stat.executeQuery()) {

		        while (result.next()) {
		            String title = result.getString("Title");
		            System.out.println();
		            System.out.println("Title: " + title);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	private void appendOptionsToQuery(StringBuilder query, String category, String[] options) {
	    if (options.length > 0) {
	        query.append(category).append(" IN ('").append(String.join("', '", options)).append("') AND ");
	    }
	}

}