//Zainab
package library;

import javax.swing.*;
import mains.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class filterSearch {
	JFrame filterFrame = new JFrame();
	String databasePath = "jdbc:mysql://your_database_host:your_database_port/your_database_name";
	String image1Path = "C:\\Users\\kashi\\Downloads\\filter.png";
	Connection con;
	JPanel genrePanel = new JPanel(), agegroupPanel = new JPanel(), ratingPanel = new JPanel(),
			regionPanel = new JPanel(), lengthPanel = new JPanel(), languagePanel = new JPanel();
	String[] categories = { "Genre", "Age", "Rating", "Region", "Length", "Language", "Translate" }; // Define the
																										// categories
	String[][] selectedOptions = new String[categories.length][]; // to store selected options
	JCheckBox[] checkboxes = new JCheckBox[40]; // array of 40 checkboxes (options for filter)

	public filterSearch() {
		filterFrame.setBackground(new Color(255, 255, 255));
		filterFrame.setForeground(new Color(220, 242, 206));
		filterFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(image1Path));
		filterFrame.setLocation(-142, -430);

		// Panels for each filter category
		JPanel genrePanel = new JPanel();
		genrePanel.setBounds(10, 50, 566, 92);
		genrePanel.setLayout(null);
		filterFrame.getContentPane().add(genrePanel);

		JPanel agegroupPanel = new JPanel();
		agegroupPanel.setBounds(10, 159, 566, 43);
		agegroupPanel.setLayout(null);
		filterFrame.getContentPane().add(agegroupPanel);

		JPanel ratingPanel = new JPanel();
		ratingPanel.setBounds(10, 222, 566, 43);
		ratingPanel.setLayout(null);
		filterFrame.getContentPane().add(ratingPanel);

		JPanel regionPanel = new JPanel();
		regionPanel.setBounds(10, 276, 566, 85);
		regionPanel.setLayout(null);
		filterFrame.getContentPane().add(regionPanel);

		JPanel lengthPanel = new JPanel();
		lengthPanel.setBounds(10, 383, 566, 32);
		lengthPanel.setLayout(null);
		filterFrame.getContentPane().add(lengthPanel);

		JPanel languagePanel = new JPanel();
		languagePanel.setBounds(10, 438, 450, 62);
		languagePanel.setLayout(null);
		filterFrame.getContentPane().add(languagePanel);

		// Filter categories
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblGenre.setBounds(10, 9, 112, 19);
		genrePanel.add(lblGenre);

		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRegion.setBounds(10, 7, 85, 33);
		regionPanel.add(lblRegion);

		JLabel lblLength = new JLabel("Length:");
		lblLength.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblLength.setBounds(10, 2, 85, 32);
		lengthPanel.add(lblLength);

		// Story Length options

		// Short Story
		JCheckBox chckbxShortStory = new JCheckBox("Short Story");
		chckbxShortStory.setBounds(109, 7, 100, 23);
		lengthPanel.add(chckbxShortStory);
		chckbxShortStory.setPreferredSize(new Dimension(100, 25));
		chckbxShortStory.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxShortStory.setActionCommand("");

		// Novella
		JCheckBox chckbxNovella = new JCheckBox("Novella");
		chckbxNovella.setBounds(233, 7, 85, 23);
		lengthPanel.add(chckbxNovella);
		chckbxNovella.setPreferredSize(new Dimension(100, 25));
		chckbxNovella.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxNovella.setActionCommand("");

		// Novel
		JCheckBox chckbxNovel = new JCheckBox("Novel");
		chckbxNovel.setBounds(350, 7, 85, 23);
		lengthPanel.add(chckbxNovel);
		chckbxNovel.setPreferredSize(new Dimension(100, 25));
		chckbxNovel.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxNovel.setActionCommand("");

		// Epic
		JCheckBox chckbxEpic = new JCheckBox("Epic");
		chckbxEpic.setBounds(462, 7, 85, 23);
		lengthPanel.add(chckbxEpic);
		chckbxEpic.setPreferredSize(new Dimension(100, 25));
		chckbxEpic.setFont(new Font("Dialog", Font.PLAIN, 14));
		chckbxEpic.setActionCommand("");

		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRating.setBounds(10, -3, 85, 43);
		ratingPanel.add(lblRating);

		// Genre options

		// Romance
		JCheckBox romanceCheckbox = new JCheckBox("Romance");
		romanceCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		romanceCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		romanceCheckbox.setPreferredSize(new Dimension(100, 25));
		romanceCheckbox.setBounds(109, 59, 93, 23);
		genrePanel.add(romanceCheckbox);

		// Horror
		JCheckBox horrorCheckbox = new JCheckBox("Horror");
		horrorCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		horrorCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		horrorCheckbox.setPreferredSize(new Dimension(100, 25));
		horrorCheckbox.setBounds(109, 33, 93, 23);
		genrePanel.add(horrorCheckbox);

		// Fantasy
		JCheckBox fantasyCheckbox = new JCheckBox("Fantasy");
		fantasyCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		fantasyCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		fantasyCheckbox.setPreferredSize(new Dimension(100, 25));
		fantasyCheckbox.setBounds(109, 7, 93, 23);
		genrePanel.add(fantasyCheckbox);

		// Adventure
		JCheckBox adventureCheckbox = new JCheckBox("Adventure");
		adventureCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		adventureCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		adventureCheckbox.setPreferredSize(new Dimension(100, 25));
		adventureCheckbox.setBounds(228, 7, 115, 23);
		genrePanel.add(adventureCheckbox);

		// Mystery
		JCheckBox mysteryCheckbox = new JCheckBox("Mystery");
		mysteryCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		mysteryCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		mysteryCheckbox.setPreferredSize(new Dimension(100, 25));
		mysteryCheckbox.setBounds(228, 33, 93, 23);
		genrePanel.add(mysteryCheckbox);

		// Historical
		JCheckBox historicalCheckbox = new JCheckBox("Historical");
		historicalCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		historicalCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		historicalCheckbox.setPreferredSize(new Dimension(100, 25));
		historicalCheckbox.setBounds(228, 59, 93, 23);
		genrePanel.add(historicalCheckbox);

		// Non-fiction
		JCheckBox nonfictionCheckbox = new JCheckBox("Non-Fiction");
		nonfictionCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		nonfictionCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		nonfictionCheckbox.setPreferredSize(new Dimension(100, 25));
		nonfictionCheckbox.setBounds(358, 7, 115, 23);
		genrePanel.add(nonfictionCheckbox);

		// Drama
		JCheckBox dramaCheckbox = new JCheckBox("Drama");
		dramaCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		dramaCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		dramaCheckbox.setPreferredSize(new Dimension(100, 25));
		dramaCheckbox.setBounds(358, 33, 85, 23);
		genrePanel.add(dramaCheckbox);

		// Science Fiction
		JCheckBox scifiCheckbox = new JCheckBox("Sci-Fi");
		scifiCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		scifiCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		scifiCheckbox.setPreferredSize(new Dimension(100, 25));
		scifiCheckbox.setBounds(358, 59, 85, 23);
		genrePanel.add(scifiCheckbox);

		// Fairy Tale
		JCheckBox fairyTaleCheckbox = new JCheckBox("Fairy Tale");
		fairyTaleCheckbox.setBounds(new Rectangle(463, 59, 97, 23));
		fairyTaleCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		genrePanel.add(fairyTaleCheckbox);

		// Age Group options

		// Child
		JCheckBox ageGroupChildCheckbox = new JCheckBox("Child");
		ageGroupChildCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupChildCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupChildCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupChildCheckbox.setBounds(109, 11, 61, 23);
		agegroupPanel.add(ageGroupChildCheckbox);

		// Young Adult
		JCheckBox ageGroupTeenCheckbox = new JCheckBox("Young Adult");
		ageGroupTeenCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupTeenCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupTeenCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupTeenCheckbox.setBounds(209, 11, 108, 23);
		agegroupPanel.add(ageGroupTeenCheckbox);

		// Adult
		JCheckBox ageGroupAdultCheckbox = new JCheckBox("Adult");
		ageGroupAdultCheckbox.setPreferredSize(new Dimension(100, 25));
		ageGroupAdultCheckbox.setFont(new Font("Dialog", Font.PLAIN, 14));
		ageGroupAdultCheckbox.setBounds(new Rectangle(0, 0, 115, 0));
		ageGroupAdultCheckbox.setBounds(358, 11, 85, 23);
		agegroupPanel.add(ageGroupAdultCheckbox);

		JLabel lblAgeGroup = new JLabel("Age:");
		lblAgeGroup.setBounds(10, 2, 62, 32);
		agegroupPanel.add(lblAgeGroup);
		lblAgeGroup.setFont(new Font("Segoe Print", Font.BOLD, 15));

		// Story Rating options
		JCheckBox ratingCheckbox1 = new JCheckBox("1");
		ratingCheckbox1.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox1.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox1.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox1.setBounds(109, 7, 79, 23);
		ratingPanel.add(ratingCheckbox1);

		JCheckBox ratingCheckbox2 = new JCheckBox("2");
		ratingCheckbox2.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox2.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox2.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox2.setBounds(194, 7, 85, 23);
		ratingPanel.add(ratingCheckbox2);

		JCheckBox ratingCheckbox3 = new JCheckBox("3");
		ratingCheckbox3.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox3.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox3.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox3.setBounds(284, 7, 85, 23);
		ratingPanel.add(ratingCheckbox3);

		JCheckBox ratingCheckbox4 = new JCheckBox("4");
		ratingCheckbox4.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox4.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox4.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox4.setBounds(374, 7, 85, 23);
		ratingPanel.add(ratingCheckbox4);

		JCheckBox ratingCheckbox5 = new JCheckBox("5");
		ratingCheckbox5.setPreferredSize(new Dimension(100, 25));
		ratingCheckbox5.setFont(new Font("Dialog", Font.PLAIN, 14));
		ratingCheckbox5.setBounds(new Rectangle(0, 0, 115, 0));
		ratingCheckbox5.setBounds(464, 7, 85, 23);
		ratingPanel.add(ratingCheckbox5);

		// Region Options
		JCheckBox region1Checkbox = new JCheckBox("Europe");
		region1Checkbox.setBounds(109, 7, 107, 23);
		regionPanel.add(region1Checkbox);
		region1Checkbox.setPreferredSize(new Dimension(100, 25));
		region1Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region2Checkbox = new JCheckBox("Latin America");
		region2Checkbox.setBounds(109, 33, 117, 23);
		regionPanel.add(region2Checkbox);
		region2Checkbox.setPreferredSize(new Dimension(100, 25));
		region2Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region3Checkbox = new JCheckBox("Middle East");
		region3Checkbox.setBounds(232, 33, 115, 23);
		regionPanel.add(region3Checkbox);
		region3Checkbox.setPreferredSize(new Dimension(100, 25));
		region3Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region4Checkbox = new JCheckBox("West/East/South Africa");
		region4Checkbox.setBounds(362, 33, 175, 23);
		regionPanel.add(region4Checkbox);
		region4Checkbox.setPreferredSize(new Dimension(100, 25));
		region4Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region5Checkbox = new JCheckBox("South Asia");
		region5Checkbox.setBounds(232, 7, 115, 23);
		regionPanel.add(region5Checkbox);
		region5Checkbox.setPreferredSize(new Dimension(100, 25));
		region5Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region6Checkbox = new JCheckBox("South East Asia/Oceania");
		region6Checkbox.setBounds(362, 7, 198, 23);
		regionPanel.add(region6Checkbox);
		region6Checkbox.setPreferredSize(new Dimension(100, 25));
		region6Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox region7Checkbox = new JCheckBox("Central Asia");
		region7Checkbox.setBounds(109, 59, 143, 23);
		regionPanel.add(region7Checkbox);
		region7Checkbox.setPreferredSize(new Dimension(100, 25));
		region7Checkbox.setFont(new Font("Dialog", Font.PLAIN, 14));

		// Language Options
		JCheckBox languageCheckbox1 = new JCheckBox("English");
		languageCheckbox1.setSelected(true);
		languageCheckbox1.setBounds(470, 460, 85, 23);
		languageCheckbox1.setPreferredSize(new Dimension(100, 25));
		languageCheckbox1.setFont(new Font("Dialog", Font.PLAIN, 14));
		filterFrame.getContentPane().add(languageCheckbox1);

		JCheckBox languageCheckbox2 = new JCheckBox("Spanish");
		languageCheckbox2.setSize(100, 23);
		languageCheckbox2.setLocation(109, 7);
		languagePanel.add(languageCheckbox2);
		languageCheckbox2.setPreferredSize(new Dimension(100, 25));
		languageCheckbox2.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox languageCheckbox3 = new JCheckBox("Arabic");
		languageCheckbox3.setSize(85, 23);
		languageCheckbox3.setLocation(233, 7);
		languagePanel.add(languageCheckbox3);
		languageCheckbox3.setPreferredSize(new Dimension(100, 25));
		languageCheckbox3.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox languageCheckbox4 = new JCheckBox("Sanskrit");
		languageCheckbox4.setSize(85, 23);
		languageCheckbox4.setLocation(350, 7);
		languagePanel.add(languageCheckbox4);
		languageCheckbox4.setPreferredSize(new Dimension(100, 25));
		languageCheckbox4.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox languageCheckbox5 = new JCheckBox("Turkish");
		languageCheckbox5.setSize(85, 23);
		languageCheckbox5.setLocation(350, 33);
		languagePanel.add(languageCheckbox5);
		languageCheckbox5.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox languageCheckbox6 = new JCheckBox("Urdu");
		languageCheckbox6.setSize(85, 23);
		languageCheckbox6.setLocation(109, 33);
		languagePanel.add(languageCheckbox6);
		languageCheckbox6.setFont(new Font("Dialog", Font.PLAIN, 14));

		JCheckBox languageCheckbox7 = new JCheckBox("Mandarin");
		languageCheckbox7.setSize(85, 23);
		languageCheckbox7.setLocation(232, 33);
		languagePanel.add(languageCheckbox7);
		languageCheckbox7.setFont(new Font("Dialog", Font.PLAIN, 14));

		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setSize(85, 32);
		lblLanguage.setLocation(10, 2);
		languagePanel.add(lblLanguage);
		lblLanguage.setFont(new Font("Segoe Print", Font.BOLD, 15));

		// Initialize panels
		JPanel[] panels = { genrePanel, agegroupPanel, ratingPanel, regionPanel, lengthPanel, languagePanel };

		// Initialize check boxes
		JCheckBox[] checkboxes = { romanceCheckbox, horrorCheckbox, fantasyCheckbox, adventureCheckbox, mysteryCheckbox,
				historicalCheckbox, nonfictionCheckbox, dramaCheckbox, scifiCheckbox, fairyTaleCheckbox,
				ageGroupChildCheckbox, ageGroupTeenCheckbox, ageGroupAdultCheckbox, ratingCheckbox1, ratingCheckbox2,
				ratingCheckbox3, ratingCheckbox4, ratingCheckbox5, chckbxShortStory, chckbxNovella, chckbxNovel,
				chckbxEpic, region1Checkbox, region2Checkbox, region3Checkbox, region4Checkbox, region5Checkbox,
				region6Checkbox, region7Checkbox, languageCheckbox1, languageCheckbox2, languageCheckbox3,
				languageCheckbox4, languageCheckbox5, languageCheckbox6, languageCheckbox7 };

		// Store labels / table headers
		JLabel[] columns = { lblGenre, lblAgeGroup, lblRating, lblRegion, lblLength, lblLanguage };

		// Filter button
		JButton filterButton = new JButton("SEARCH");
		filterButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
		filterFrame.getContentPane().add(filterButton);
		// Filter searches when filter button is pressed
		filterButton.setForeground(new Color(255, 255, 255));
		filterButton.setBackground(new Color(88, 153, 47));
		filterButton.setBounds(299, 515, 120, 30);
		filterButton.addActionListener(new ActionListener() {

			// When filter button is pressed:
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = false;

				// Check if at least one checkbox (other than languageCheckbox1) is selected
				for (JCheckBox checkBox : checkboxes) {
					if (checkBox != languageCheckbox1 && checkBox.isSelected()) {
						selected = true;
						break; // No need to continue checking if one checkbox (other than languageCheckbox1)
								// is selected
					}
				}

				if (!selected) {
					filterFrame.dispose();
					// Display an error message
//			    JOptionPane.showMessageDialog(filterFrame, "Help us find what you're looking for! Please select at least one filter.", "Filter Error", JOptionPane.ERROR_MESSAGE);
				} else {
					// At least one checkbox (other than languageCheckbox1) is selected, proceed
					// with generating the query and searching the database
					String query = getSelectedFilters(panels, checkboxes);

					// Print the generated query (for debugging purposes)
					System.out.println(query);

					// Execute the search only if the query is not empty
					searchDatabase(query);

					// Save filter options
					// saveFilters(); // when the user reloads, their previous filters will be there
					// and they can change them

				}

				// Close frame after applying filters
				filterFrame.dispose();

			}

		});

		// Clear filters button
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(88, 153, 47)));
		clearButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
		clearButton.setForeground(new Color(88, 153, 47));
		clearButton.setBackground(new Color(255, 255, 255));
		clearButton.setBounds(166, 515, 120, 30);
		filterFrame.getContentPane().add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkboxes.length; i++) {
					if (checkboxes[i] != languageCheckbox1) {
						checkboxes[i].setSelected(false);
					}
				}
			}
		});

		filterFrame.getContentPane().setLayout(null);

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
		separator_3.setBounds(0, 372, 714, 8);
		filterFrame.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(-30, 426, 714, 8);
		filterFrame.getContentPane().add(separator_4);

		JLabel lblNewLabel = new JLabel("What are you looking for?");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 284, 29);
		filterFrame.getContentPane().add(lblNewLabel);

		filterFrame.setLocationRelativeTo(Homepage.filter);
		filterFrame.setSize(600, 595);
		filterFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		filterFrame.setVisible(true);
	}

	private String getSelectedFilters(JPanel[] panels, JCheckBox[] selected) {
		StringBuilder queryBuilder = new StringBuilder("SELECT Title FROM STORIES WHERE");
		int selectedIndex = 0;
		boolean isOnlyFilter = true;

		for (int i = 0; i < panels.length; i++) {
			Component[] panelComponents = panels[i].getComponents();
			boolean isOnlyCheckboxInPanel = true;

			for (int j = 0; j < panelComponents.length; j++) {
				Component component = panelComponents[j];

				if (component instanceof JCheckBox) {
					JCheckBox checkBox = (JCheckBox) component;

					if (checkBox.isSelected()) {
						selected[selectedIndex++] = checkBox;

						// Build query string
						if (!isOnlyFilter) {
							if (isOnlyCheckboxInPanel) {
								queryBuilder.append(" AND");
							} else {
								queryBuilder.append(" OR");
							}
						} else {
							isOnlyFilter = false;
						}

						for (Component labelComponent : panelComponents) {
							if (labelComponent instanceof JLabel) {
								JLabel label = (JLabel) labelComponent;
								queryBuilder.append(" ").append(label.getText().replace(':', ' ').trim()).append(" = '")
										.append(checkBox.getText()).append("'");
								System.out
										.println(label.getText().replace(':', ' ').trim() + ": " + checkBox.getText());
							}
						}

						isOnlyCheckboxInPanel = false;
					}
				}
			}
		}

		return queryBuilder.toString();
	}

	private void searchDatabase(String query) {
		try (Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword)) {
			PreparedStatement prep = con.prepareStatement(query);
			try (ResultSet res = prep.executeQuery()) {
				if (!res.isBeforeFirst())
					System.out.println("Hmm...couldn't find anything.");

				while (res.next()) {
					String title = res.getString("Title");
					System.out.println(title);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new filterSearch();
	}
}
