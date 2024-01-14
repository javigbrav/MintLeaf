import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/***********************************************************************************
 * Author: Zainab Siddiqui / Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 13, 2024
 * Last Modified by: Zainab Siddiqui
 * Description: A user's home page in the application
 ***********************************************************************************/

public class Homepage {

    Color mintGreen = new Color(220, 242, 206);
	Color mintGreen2 = new Color(88, 153, 47);
    JMenu menu, libr;
    private JMenu mnUsername;
    JMenuItem fav, user, settings, logOut;
    JMenuBar mb = new JMenuBar();
    JTextArea textArea;
    
    static JFrame homepageFrame = new JFrame("Tales Around the World - HOME");

    Homepage(String username) {
    	
    	// Create JTextArea
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel titlePanel = new JPanel(); //it was being covered by the search bar, so it needs this
        titlePanel.setOpaque(false);
        mb.setMargin(new Insets(30, 5, 0, 0));
        mb.setFont(new Font("Segoe Print", Font.PLAIN, 30));
        mb.setBackground(mintGreen2);

        //create a panel for book buttons with separation from sides, top, and bottom
        JPanel bookPanel = new JPanel(new BorderLayout());
        JPanel topSpace = new JPanel();
        JPanel bottomSpace = new JPanel();
        topSpace.setOpaque(false);
        bottomSpace.setOpaque(false);

        topSpace.setPreferredSize(new Dimension(1, 100)); // Adjust as needed
        bottomSpace.setPreferredSize(new Dimension(1, 75)); // Adjust as needed

        bookPanel.add(topSpace, BorderLayout.NORTH);
        bookPanel.add(bottomSpace, BorderLayout.SOUTH);

        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setOpaque(false);
        rightSpace.setOpaque(false);

        leftSpace.setPreferredSize(new Dimension(100, 1)); // Adjust as needed
        rightSpace.setPreferredSize(new Dimension(100, 1)); // Adjust as needed

        bookPanel.add(leftSpace, BorderLayout.WEST);
        bookPanel.add(rightSpace, BorderLayout.EAST);

        JPanel bookGridPanel = new JPanel();
        bookGridPanel.setLayout(new GridLayout(2, 5, 50, 70)); // Adjust the last two parameters for spacing
        bookGridPanel.setBackground(mintGreen);
        
        bookPanel.add(bookGridPanel, BorderLayout.CENTER);
        bookPanel.setBackground(mintGreen);
        //book buttons
        for (int i = 1; i <= 10; i++){
            JButton bookButton = new JButton("Book " + i);
            final int bookNumber = i; // Final variable to use in the ActionListener
            bookButton.setBackground(mintGreen2);
            bookButton.setPreferredSize(new Dimension(25, 40)); // Adjust size as needed
            bookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle reading from the corresponding text file
                    String fileName = "book" + bookNumber; // will probably need to add private JTextArea textArea;
                    readFile(fileName);
                }
            });
            bookGridPanel.add(bookButton);
        }

        // Set frame
        Tales.setFrame(homepageFrame);
        
        // Set BorderLayout for the main content panel
        homepageFrame.getContentPane().setLayout(new BorderLayout());
        homepageFrame.getContentPane().add(bookPanel, BorderLayout.CENTER);
        homepageFrame.setJMenuBar(mb);
    	
    	Label label_1 = new Label("");
    	mb.add(label_1);
    	label_1.setMaximumSize(new Dimension(20, 32767));
        
        // title
    	JLabel title = new JLabel("Tales Around The World");
    	mb.add(title);
    	title.setBounds(new Rectangle(50, 0, 0, 0));
    	title.setFont(new Font("Impact", Font.PLAIN, 40));
    	title.setBounds(30, 20, 391, 50);
    	title.setOpaque(false);
    	title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	title.addMouseListener(new MouseAdapter() { // return to homepage when you click the title
			@Override
			public void mouseClicked(MouseEvent m) {
				try {
					System.out.println("Redirecting to Home");
					new Homepage(Tales.username);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
    	});
        
        Label label_2 = new Label("");
        label_2.setMaximumSize(new Dimension(100, 32767));
        mb.add(label_2);
        //FILTER SEARCHES
    	
        // set search bar panel
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.setMaximumSize(new Dimension(400, 32767));
        mb.add(searchBarPanel);
        
        searchBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(18);
        searchField.setFont(new Font("MV Boli", Font.PLAIN, 16));
        searchField.setText("Looking for something...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Looking for something...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Looking for something...");
                }
            }
        });
        
        //search button
        JButton searchButton = new JButton("");
        searchButton.setIcon(new ImageIcon("C:\\Users\\kashi\\Downloads\\15028-200.png"));
        searchButton.setFont(new Font("Segoe Print", Font.PLAIN, 18));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                if (!searchTerm.equals("Looking for something...")) {
                    System.out.println("Searching for: " + searchTerm);
                } else {
                    System.out.println("Check");
                }
            }
        });
        //implementing search panel
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);
        searchBarPanel.setOpaque(false);
		
		Label label = new Label("");
		label.setMaximumSize(new Dimension(100, 32767));
		mb.add(label);
		
		// to filter searches - showing results for a specific genre
		JLabel filter = new JLabel("Explore");
		mb.add(filter);
		filter.setHorizontalAlignment(SwingConstants.TRAILING);
		filter.setFont(new Font("MV Boli", Font.PLAIN, 18));
		
		Label label_3 = new Label("");
		label_3.setMaximumSize(new Dimension(900, 32767));
		mb.add(label_3);
        
        //menu
		mnUsername = new JMenu("username");
		mb.add(mnUsername);
		mnUsername.setFont(new Font("Segoe Print", Font.BOLD, 15));
		user = new JMenuItem("Profile");
		libr = new JMenu("My Library");
		fav = new JMenuItem("Favourites");
		settings = new JMenuItem("Settings");
		logOut = new JMenuItem("Log out");
		// set font
		libr.setFont(new Font("Helvetica", Font.BOLD, 15));
		fav.setFont(new Font("Helvetica", Font.BOLD, 15));
		user.setFont(new Font("Dialog", Font.BOLD, 15));
		settings.setFont(new Font("Helvetica", Font.BOLD, 15));
		logOut.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		//implementing action listeners to direct to other pages using the same frame
		libr.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Tales.setFrame(homepageFrame);
		    	new Library();
		        System.out.println("Redirecting to your Library");
		    }
		});
		
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redirecting to your Profile");
                new Profile();
            }
        });
        
         fav.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Redirecting to your Favourites");
                 new Favourites();
             }
         });
         
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redirecting to Settings");
                new Settings();
            }
        });
        
        //adding items to menus
        mnUsername.add(user);
        mnUsername.add(libr);
        libr.add(fav);
        mnUsername.add(settings);
        mnUsername.add(logOut);
        
        Label label_4 = new Label("");
        label_4.setMaximumSize(new Dimension(100, 32767));
        mb.add(label_4);
		        
        // menu choice panel
    	menu = new JMenu(username);
    	
    	// setting menu panel font
    	menu.setFont(new Font("Helvetica", Font.BOLD, 15));
    	
	    //setting up menu
	    menu.setFont(new Font("Segoe Print", Font.PLAIN, 18));
	    menu.setForeground(new Color(255, 255, 255));
        homepageFrame.setVisible(true);
    }

    public void readFile(String fileName) {
    	 try {
             BufferedReader reader = new BufferedReader(new FileReader(fileName));
             StringBuilder content = new StringBuilder();
             String line;
             while ((line = reader.readLine()) != null) {
                 content.append(line).append("\n");
             }
             reader.close();
             textArea.setText(content.toString());
             JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "File Content", JOptionPane.PLAIN_MESSAGE);
         } catch (FileNotFoundException e) {
        	 e.printStackTrace();
             JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
         }
    	 catch (IOException e) {
    		 JOptionPane.showMessageDialog(null, "Error reading the file", e.toString(), JOptionPane.ERROR_MESSAGE);

         }
	}

    // main
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage(Tales.username);
            }
        });
    }
}
