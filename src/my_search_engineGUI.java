//date 05/04/2023
//author: filip kruk
//program name: mysearchengineGUI
//program description: this program creates a GUI for the search engine

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class my_search_engineGUI extends JFrame
{
    //this creates an array of filenames that the program will search through
    private static final String[] f_names = {"video games.csv", "movies.csv", "programing languages.csv", "mobiles.csv", "top tech companies.csv"};
    private JTextField searchwordArea;
    private JTextArea resultsArea;

    public my_search_engineGUI() 
    {
        super("my search engine");//this creates a new JFrame with the title my search engine

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this sets the default behaviour to exit the program when the window is closed
        setSize(600, 600);//sets the size of the window to 600x600

        // Set the background color and font for the text field and text area
        Color turquoise = new Color(64, 224, 208);
        Font consolas14 = new Font("Consolas", Font.BOLD, 14);

        searchwordArea = new JTextField();//this creates a new text field to enter the word to be searched for
        searchwordArea.setBackground(turquoise);//sets the background color of the text field to turquoise
        searchwordArea.setFont(consolas14);//sets the font to consolas 14

        resultsArea = new JTextArea();//this creates a new text area to display the results of the search
        resultsArea.setEditable(false);//this makes the text area uneditable
        resultsArea.setBackground(turquoise);
        resultsArea.setFont(consolas14);

        JButton searchButton = new JButton("Search");//this creates a new button to search for the word
        searchButton.addActionListener(new ActionListener()//adds an action listener to the search button to detect user input
        {
            // Create a message box to display the search term
            public void actionPerformed(ActionEvent e)//this method will search for the word
            {
                
                String message = "You are searching for the word: " + searchwordArea.getText();//this code  creates a messagebox  to display the word that the user is searching for
                JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);//
                JDialog dialog = pane.createDialog(null, "Search Message");//
                
                // Set the size of the message box
                dialog.setSize(500, 200);
                dialog.setVisible(true);     
                searchFiles();
            }
        });
        searchButton.setBackground(Color.GREEN); // Set the background color of the  searchbutton to green
        searchButton.setFont(consolas14);// set the font of the searchbutton to consolas14

        JButton cancelButton = new JButton("Cancel");//creats a new button to cancel the search and clear the text fields
        cancelButton.addActionListener(new ActionListener() // add an action listener to the cancel button to detect user input
        {
            public void actionPerformed(ActionEvent e)// this method clears the text fields 
            {
                searchwordArea.setText("");
                resultsArea.setText("");
                
            }
        });
        cancelButton.setBackground(Color.orange); // set the background color of the cancelbutton to orange
        cancelButton.setFont(consolas14);// set the font of the cancelbutton to consolas14

        JButton endButton = new JButton("End");// this creates a new button to end the program
        endButton.addActionListener(new ActionListener()//adds an action listener to the end button to detect user input
        {
            public void actionPerformed(ActionEvent e)//this method will close the program
            {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the program?", "Confirm Exit", JOptionPane.YES_NO_OPTION);//this creates a message box asking the user  if they  want to end the program
            
                if (choice == JOptionPane.YES_OPTION)//if the user clicks yes the program will close 
                {
                    dispose(); // closes the JFrame
                }
            }
        });
        endButton.setBackground(Color.red); // set the background color of the endbutton to red
        endButton.setFont(consolas14);// set the font of the endbutton to consolas14
        
        JPanel buttonPanel = new JPanel(new FlowLayout());// create a new panel with FlowLayout
        buttonPanel.add(searchButton); // add the search button to the panel
        buttonPanel.add(cancelButton);//add the cancel button
        buttonPanel.add(endButton);//add the end button
        
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Enter the word to be searched :"), BorderLayout.WEST);
        searchPanel.add(searchwordArea, BorderLayout.CENTER);//adds the searchwordarea to the center of the searchpanel
        searchPanel.add(buttonPanel, BorderLayout.EAST);//adds the buttonpanel to the left of the searchpanel

        // Set the background color of the main panel to turquoise
        JPanel mainPanel = new JPanel(new BorderLayout());//creates a new panel
        mainPanel.setBackground(turquoise);
        mainPanel.add(searchPanel, BorderLayout.NORTH);//adds the searchpanel to the top of the mainpanel
        mainPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);//adds the resultsarea to the bottom of the mainpanel
        getContentPane().add(mainPanel);//

        // Set the background color of the JFrame to turquoise
        getContentPane().setBackground(turquoise);
    }

    //this method checks through the files and returns the results
    private void searchFiles() 
    {
        //split the text inside the searchwordArea into an array of words so that each word is recognised as separate
        String[] searchword = searchwordArea.getText().trim().toLowerCase().split("\\s+");
        Map<String, Integer> results = new HashMap<>();
        
        //this creates a for loop that will searchh through each file in the array of filenames and return the results
        for (String filename : f_names) 
        {
            int wordCount = 0;
            
            //this try statement will try to find the file and if it cant it will give back an error
            try 
            {
                File file1 = new File(filename);//this creates a new file object
                Scanner scanner1 = new Scanner(file1);//scans an incoming file
                
                while (scanner1.hasNextLine())// this while loop will check if there is more input to be read
                {
                    String line = scanner1.nextLine().toLowerCase();//this convertsthe input to lowercase
                    boolean WordsFound = true;// a boolean that will be used to check if the words are found
                    
                    for (String term : searchword)//this for loop checks if the word that is being looked for is in the file
                    {
                        if (!line.contains(term)) //this if statement checks if the word is in the file array
                        {
                            WordsFound = false;// if the chosen word is not in the aray the bollean will return false
                            break;//this will break the loop
                        }
                    }
                    
                    if (WordsFound)//if words have been found in the files 
                    {
                        wordCount++;// increments the wordcount
                    }
                }
                scanner1.close();//closes the scanner
            } 
            
            catch (FileNotFoundException ex)//this catch stament is used to catch the error if a file is not found
            {
                ex.printStackTrace();// this prints the error
                JOptionPane.showMessageDialog(this, "File not found: " + filename, "Error", JOptionPane.ERROR_MESSAGE);//this displays a mesage box with the error
            }
            
            if (wordCount > 0)// this if statement checks if the wordcount is bigger than 0
            {
                results.put(filename, wordCount);// if the result is bigger than 0 it will add the filename and the wordcount to the results
            }
        }
    
        if (results.isEmpty())// this if statement checks if the results are empty
        {
            resultsArea.setText("No matches found.");// if the are no results this message will be printed
        } 
        
        else// otherwise the results will be printed 
        {
            StringBuilder sb = new StringBuilder();//this creates a new stringbuilder object called sb so that the string can be edited
            sb.append("these Matches have been found in the following files:\n");//adds this text to the string builder
            results.entrySet().stream()//
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())//this sorts the hashmpa results by value
                    .forEach(entry -> sb.append(String.format("%s (%d matches)\n", entry.getKey(), entry.getValue())));// adds the ammoount of matches to the string builder
            resultsArea.setText(sb.toString());//this adds the result to the resultarea
        }
    }

    public static void main(String[] args)// main method
    {
        SwingUtilities.invokeLater(new Runnable()//this code is needed as the GUI is written in swing
        {
            public void run()// this will run the program
            {
                new my_search_engineGUI().setVisible(true);// this makes the GUI visable on screen
            }
        });
    }
}//end of class