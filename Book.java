package LogIn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Book {
	JButton book;
	Book (String bookName){
		book = new JButton (bookName);
		book.setBackground(new Color(158, 30, 30));
        book.setPreferredSize(new Dimension(25, 40)); // Adjust size as needed
        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle reading from the corresponding text file
                //String fileName = "book" + bookNumber; // will probably need to add private JTextArea textArea;
            	Homepage.readFile(bookName);
            }
        });
	}
}
