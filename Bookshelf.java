package library;
/***********************************************************************************
 * Name: Zainab Siddiqui 
 * Date: December 29, 2023 
 * Last Modified: January 24 2024
 * Description: Build a personalized and customizable bookshelf for user to store 
  				saved stories
 ***********************************************************************************/
import mains.*;
import user.User;
import story.Book;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Bookshelf {
	
	/*Globals*/
	public static JFrame bookshelfFrame = new JFrame("Tales Around the World - Bookshelf"); // frame for bookshelf
	private static JTextField renameBookshelf; // to rename bookshelf
	private static JTextField addText; // to add quote/message
	private static JComboBox<String> organizeOptns = new JComboBox<>(); // organize bookshelf
	private JLabel lblorganize = new JLabel(); // organize label
	private LinkedList<Book> bookshelfBooks = new LinkedList<>(); // List to store books on the bookshelf
	public String filename = "bookshelf.txt"; // file to store saved books on shelf to
	public ArrayList<String> titlesList = new ArrayList<>();

	/*Constructor*/
	public Bookshelf() {
		
		// Set bookshelf frame
		Tales.setFrame(bookshelfFrame);
		bookshelfFrame.getContentPane().setBackground(new Color(23, 53, 17));
		bookshelfFrame.setSize(Tales.screenW, Tales.screenH);
		bookshelfFrame.setBackground(new Color(220, 242, 206));
		bookshelfFrame.getContentPane().setLayout(null);

		// Set rename text field
		renameBookshelf = new JTextField();
		renameBookshelf.setHorizontalAlignment(SwingConstants.CENTER);
		renameBookshelf.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(220, 242, 206)));
		renameBookshelf.setBackground(new Color(23, 53, 17));
		renameBookshelf.setForeground(new Color(255, 255, 255));
		renameBookshelf.setFont(new Font("Segoe Print", Font.BOLD, 18));
		renameBookshelf.setText(User.username + "'s Bookshelf");
		renameBookshelf.setBounds(430, 29, 488, 50);
		renameBookshelf.setColumns(10);
		bookshelfFrame.getContentPane().add(renameBookshelf);

		// Set add message/quote text field
		addText = new JTextField();
		addText.setHorizontalAlignment(SwingConstants.CENTER);
		addText.setText("\"A book is a gift you can open again and again\" ~ Garrison Keillor");
		addText.setForeground(new Color(0, 128, 0));
		addText.setFont(new Font("Segoe Print", Font.BOLD, 18));
		addText.setColumns(10);
		addText.setBounds(200, 800, 890, 40);
		bookshelfFrame.getContentPane().add(addText);

		// Set organize bookshelf label and options
		lblorganize.setHorizontalAlignment(SwingConstants.CENTER);
		lblorganize.setText("Organize");
		lblorganize.setIcon(new ImageIcon(Bookshelf.class.getResource("/library/organize.png")));
		lblorganize.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 15));
		lblorganize.setToolTipText("Organize");
		lblorganize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblorganize.setName("");
		lblorganize.setBounds(1330, 33, 111, 30);
		bookshelfFrame.getContentPane().add(lblorganize);
		organizeOptns.setBackground(new Color(255, 255, 255));
		organizeOptns.setBounds(1290, 30, 185, 35);
		bookshelfFrame.getContentPane().add(organizeOptns);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(1290, 0, 10, 1000);
		bookshelfFrame.getContentPane().add(separator);

		// Set Home button
		JButton returnHome = new JButton("Home");
		returnHome.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 15));
		returnHome.setBounds(1330, 800, 125, 40);
		bookshelfFrame.getContentPane().add(returnHome);
		returnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Homepage(Tales.username);
			}
		}); // return to homepage to find more books to add
		
		loadSavedBooks();

	    // Set Delete button
		JButton btnDeleteBook = new JButton("Delete Book");
        btnDeleteBook.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 15));
        btnDeleteBook.setBounds(1315, 750, 150, 40);
        bookshelfFrame.getContentPane().add(btnDeleteBook);
        btnDeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBookPrompt();
                bookshelfFrame.repaint();
            }
        });
             
		setBookshelf();
		displayOnShelves();
		organize(lblorganize, filename);		
	} // end Bookshelf constructor
     
	/* Method Name: addToBookshelf 
	 * Author: Zainab Siddiqui
	 * Creation Date: January 12 2024
	 * Modified Date: January 19 2024
	 * Description: adds a book object to the bookshelf
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: Book
	 * Throws/Exceptions: n/a
	 */ 
	public LinkedList<Book> addToBookshelf(Book book) {
		bookshelfBooks.add(book);
		displayOnShelves();
		return bookshelfBooks;
	}
	
	/* Method Name: displayOnShelves
	 * Author: Zainab Siddiqui
	 * Creation Date: January 13 2024
	 * Modified Date: January 22 2024
	 * Description: displays the book button for the book object and sets it on the shelf (max 7 per shelf)
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: Book
	 * Throws/Exceptions: n/a
	 */ 
	public void displayOnShelves() {
	    int xOffset = 130;
	    int yOffset = 120;
	    int booksPerShelf = 6;
	    int countBooks = 0; // number of books saved
	    for (Book book : bookshelfBooks) {
	            JButton bookButton = book.getBookButtonCopy(); // get the button for the book object
	            bookButton.setBounds(xOffset, yOffset, 150, 180);
	            bookshelfFrame.getContentPane().add(bookButton); // set on shelves
	            xOffset += 180;
	            countBooks++;
	    	
	            if (countBooks >= booksPerShelf) { // if there are more than 5 books on one shelf
	                yOffset += 200; // move to next row
	                xOffset = 130;
	                countBooks = 0;
	            }
	    	
	        }
	    
	    bookshelfFrame.setVisible(true);
		bookshelfFrame.repaint();
		Homepage.homepageFrame.repaint();
	} // end displayOnShelves()
  
	/* Method Name: loadSavedBooks 
	 * Author: Zainab Siddiqui
	 * Creation Date: January 12 2024
	 * Modified Date: January 24 2024
	 * Description: loads saved books to bookshelf
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: Book, MySQL
	 * Throws/Exceptions: SQLException if error in loading
	 */ 
	   private void loadSavedBooks() {
	        try (Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
	             Statement stat = con.createStatement()) {

	            String query = "SELECT * FROM STORIES WHERE Saved = 1";
	            ResultSet rs = stat.executeQuery(query);

	            while (rs.next()) {
	                Book savedBook = new Book(rs.getString("Title"));
	            	savedBook.setTitle(rs.getString("Title"));
	                savedBook.setGenre(rs.getString("Genre"));
	                savedBook.setAge(rs.getString("Age"));
	                savedBook.setRating(rs.getString("Rating"));
	                savedBook.setRegion(rs.getString("Region"));
	                savedBook.setLength(rs.getString("Length"));
	                savedBook.setLanguage(rs.getString("Language"));
	                savedBook.setSaved(rs.getBoolean("Saved")); // true
				    savedBook.setTotal(rs.getInt("Total"));
				    savedBook.setUserRating(rs.getFloat("UserRating"));
				    savedBook.setStoryText(rs.getString("StoryText"));
				    savedBook.setStoryOriginalText(rs.getString("StoryOriginalText"));

	                // Add the book to the bookshelfBooks list
	                bookshelfBooks.add(savedBook);     
	            }

	            // Close resources
	            rs.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error loading saved books", e.toString(), JOptionPane.ERROR_MESSAGE);
	        }
	    }
	  
	   /* Method Name: deleteBookPrompt 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 20 2024
		 * Modified Date: January 24 2024
		 * Description: deleted selected books from bookshelf
		 * @Return Value: void
		 * Data Type: n/a
		 * Dependencies: Book, removeBookshelf(), findBooksbyTitle()
		 * Throws/Exceptions: erorr message if no books in bookshelf
		 */ 
	   private void deleteBookPrompt() {	
		 
		   String[] bookshelfTitles = new String[bookshelfBooks.size()];
		   int i = 0;
		   for (Book book : bookshelfBooks) {
			   bookshelfTitles[i] = book.getTitle();
			   System.out.println(bookshelfTitles[i]); // works test
			   i++;
		   }
		    if (bookshelfBooks.size() > 0) { // Check if there are books in the bookshelf
		    	
		    	String selectedTitle = (String) JOptionPane.showInputDialog(
		    	    bookshelfFrame,
		    	    "Select a book to delete:",
		    	    "Delete Book",
		    	    JOptionPane.QUESTION_MESSAGE,
		    	    null,
		    	    bookshelfTitles, // Use the array of titles
		    	    bookshelfTitles[0]// Default selection, you can change this as needed
		    	);

		        if (selectedTitle != null) {
		            // Confirm deletion
		        	Book selectedBook = findBookByTitle(selectedTitle); 
		            int confirmDelete = JOptionPane.showConfirmDialog(bookshelfFrame,
		                    "Are you sure you want to delete " + selectedBook.getTitle() + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

		            if (confirmDelete == JOptionPane.YES_OPTION) {
		                // Remove the selected book from the bookshelf
		                removeFromBookshelf(selectedBook);
		           
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(bookshelfFrame, "There are no books in the bookshelf.", "No Books",
		                JOptionPane.INFORMATION_MESSAGE);
		    }
		   
		} // end deleteBookPrompt()
	   
	   /* Method Name: findBooksByTitle 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 20 2024
		 * Modified Date: January 23 2024
		 * Description: find the book title that corresponds with the book
		 * @Return Value: Book/null - 
		 * Data Type: Book
		 * Dependencies: Book
		 * Throws/Exceptions: return null if no books
		 */ 
	   private Book findBookByTitle(String title) {
		    for (Book book : bookshelfBooks) {
		        if (book.getTitle().equals(title)) {
		            return book;
		        }
		    }
		    return null;
		} // end findByBookTitle
	   
	   /* Method Name: removeFromBookshelf 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 20 2024
		 * Modified Date: January 23 2024
		 * Description: deleted selected books from bookshelf
		 * @Return Value: void
		 * Data Type: n/a
		 * Dependencies: displayOnShelves(), MySQL
		 * Throws/Exceptions: SQLException
		 */ 
	   public void removeFromBookshelf(Book book) {
	   
	    	bookshelfBooks.remove(book);
	    	displayOnShelves();
	        try (Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword)) {
	            String updateQuery = "UPDATE STORIES SET Saved = 0 WHERE Title = ?";
	            try (PreparedStatement stat = con.prepareStatement(updateQuery)) {
	                stat.setString(1, book.getTitle());
	                stat.executeUpdate(); // update query
	            }
	           
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating Saved status", e.toString(), JOptionPane.ERROR_MESSAGE);
	        }
	   } // end removeFromBookshelf
	   
	 /* Method Name: setBookshelf 
	 * Author: Zainab Siddiqui
	 * Creation Date: January 18 2024
	 * Modified Date: January 20 2024
	 * Description: import bookshelf image + set size
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: none
	 * Throws/Exceptions: n/a
	 */ 
	public void setBookshelf() {
		ImageIcon originalIcon = new ImageIcon(Bookshelf.class.getResource("/library/book-shelves.png"));
		int labelWidth = 1325;
		int labelHeight = 750;
		ImageIcon scaledIcon = getScaledIcon(originalIcon, labelWidth, labelHeight);
		JLabel bookshelfImage = new JLabel(scaledIcon);
		bookshelfImage.setLocation(0, 90);
		bookshelfImage.setSize(1325, 750);
		bookshelfFrame.getContentPane().add(bookshelfImage);
		bookshelfFrame.setVisible(true);
	} // end setBookshelf
	
	 /* Method Name: organize 
	 * Author: Zainab Siddiqui
	 * Creation Date: January 15 2024
	 * Modified Date: January 20 2024
	 * Description: organize bookshelf by user's selected category
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: printSorted(), sortCurrentlyReading(), customCateogry()
	 * Throws/Exceptions: none
	 */ 
	public void organize(JLabel lblorganize, String filename) {

		// Set combo box
		organizeOptns.addItem(" ");
		organizeOptns.addItem("Favourites First");
		organizeOptns.addItem("Sort by Genre");
		organizeOptns.addItem("Sort by Region");
		organizeOptns.addItem("Sort by Story Length");
		organizeOptns.addItem("Sort by Currently Reading");
		organizeOptns.addItem("Add Category");
		bookshelfFrame.getContentPane().add(organizeOptns);

		// For each item selected
		organizeOptns.addActionListener(e -> {
			lblorganize.setVisible(false);
			String selectedOption = (String) organizeOptns.getSelectedItem();
			switch (selectedOption) {
			case "Favourites First":
				sortFavourites();
				break;
			case "Sort by Genre":
				printSorted("Genre");
				break;
			case "Sort by Region":
				printSorted("Region");
				break;
			case "Sort by Story Length":
				printSorted("Length");
				break;
			case "Sort by Currently Reading":
				sortCurrentlyReading();
				break;
			case "Add Category":
				customCategory();
				break;
			default: // nothing chosen
				lblorganize.setVisible(true);
				organizeOptns.setSelectedItem(" ");
			}
		});
	} // end organize
	
	 /* Method Name: printSorted 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 15 2024
		 * Modified Date: January 24 2024
		 * Description: print the organized books to the console
		 * @Return Value: void
		 * Data Type: n/a
		 * Dependencies: MySQL
		 * Throws/Exceptions: Exception e - if erorr with database
		 */ 
	public static void printSorted(String sortCriteria) {
		try {
            Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
            Statement stat = con.createStatement();

            String query = "SELECT * FROM STORIES WHERE Saved = 1 ORDER BY " + sortCriteria + ", Title";
            ResultSet rs = stat.executeQuery(query);
        	// headings
            System.out.println("\nBookshelf sorted by: " + sortCriteria);
            System.out.printf("%-50s %-20s %-30s %-10s%n", "Title", "Genre", "Region", "Story Length");
            System.out.println();
            while (rs.next()) {
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String region = rs.getString("Region");
                String length = rs.getString("Length");
                System.out.printf("%-50s %-20s %-30s %-10s%n", title, genre, region, length);  
            }
            System.out.println();
            // Close resources
            rs.close();
            stat.close();
            con.close();
          
            JOptionPane.showMessageDialog(null, "Books sorted by " + sortCriteria, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
       e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sorting books", e.toString(), JOptionPane.ERROR_MESSAGE);
        }
	 } // end printSorted()
	
	 /* Method Name: customCategory() 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 12 2024
		 * Modified Date: January 20 2024
		 * Description: future - allow user to add their own organization
		 * @Return Value: void
		 * Data Type: n/a
		 * Dependencies: printSorted(), sortCurrentlyReading(), customCateogry()
		 * Throws/Exceptions: none
		 */ 
	 private static void customCategory() {
		    String categoryName = JOptionPane.showInputDialog("Category Name:"); // input cateogry name for custom category
		    String message = "We are working hard to get the category '" + categoryName + "' in.";
		    JOptionPane.showMessageDialog(null, message, "Note", JOptionPane.INFORMATION_MESSAGE);
	} // end customCategory()

	 public void sortFavourites() {
		   try {
		        Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
		        Statement stat = con.createStatement();

		        // Check if there are any books marked as favorites
		        String checkQuery = "SELECT COUNT(*) AS count FROM stories WHERE Favourite = 1";
		        ResultSet checkRs = stat.executeQuery(checkQuery);
		        checkRs.next();
		        int count = checkRs.getInt("count");
            if (count > 0) { // there are books saved to Favourite
               Favourites.findFavouritesSaved();

                // Close resources
               checkRs.close();
            } else {
                // No books are marked as favorites, inform the user
                System.out.println("Save a book to Favourites first!");
            }

            // Close resources
            checkRs.close();
            stat.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* Method Name: printSortedFavourite() 
		 * Author: Zainab Siddiqui
		 * Creation Date: January 20 2024
		 * Modified Date: January 23 2024
		 * Description: future - display sorted in display
		 * @Return Value: void
		 * Data Type: n/a
		 * Dependencies: printSorted(), sortCurrentlyReading(), customCateogry()
		 * Throws/Exceptions: none
		 */ 
    public static void printSortedFavourite() {
		try {
            Connection con = DriverManager.getConnection(Tales.conPath, Tales.dbUsername, Tales.dbPassword);
            Statement stat = con.createStatement();
          
            ResultSet rs = stat.executeQuery("SELECT Title FROM STORIES WHERE Saved = 1 AND Favourite = 1 ORDER BY Favourite DESC");
        	// headings
            System.out.println("\nBookshelf sorted by: Favourites");
            System.out.println("Title");
            while (rs.next()) {
                String title = rs.getString("Title");
                boolean fav = rs.getBoolean("Favourite");
                System.out.print(title);  
            }
            System.out.println();
            rs.close();
            stat.close();
            con.close();
          
            JOptionPane.showMessageDialog(null, "Books sorted by Favourites - check console", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
       e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sorting books", e.toString(), JOptionPane.ERROR_MESSAGE);
        }
	 } // end printSortedFavourite()
	 
	 /* Method Name: sortCurrentlyReading
	 * Author: Zainab Siddiqui
	 * Creation Date: January 12 2024
	 * Modified Date: January 20 2024
	 * Description: future - allow user to sort by currently reading books
	 * @Return Value: none
	 * Data Type: void
	 * Dependencies: Book
	 * Throws/Exceptions: n/a
	 */ 
	public static void sortCurrentlyReading() {
		   String categoryName = JOptionPane.showInputDialog("Category Name:"); // input cateogry name for custom category
		   String message = "We are working hard to get the category '" + categoryName + "' in.";
		   JOptionPane.showMessageDialog(null, message, "Note", JOptionPane.INFORMATION_MESSAGE);
		} // end sortCurrentlyReading()
	
	/* Method Name: getScaledIcon
	 * Author: Zainab Siddiqui
	 * Creation Date: January 12 2024
	 * Modified Date: January 21 2024
	 * Description: scale iamge
	 * @Return Value: the scaled image icon
	 * Data Type: ImageIcon
	 * Dependencies: Image
	 * Throws/Exceptions: none
	 */ 
	public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}

	public static void main(String[] args) {
		new Bookshelf();
	}

} // end Bookshelf