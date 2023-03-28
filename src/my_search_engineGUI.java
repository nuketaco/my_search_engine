import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;

public class my_search_engineGUI
{
    public static void main(String[] args) 
    {
        // Create a new JFrame object
        JFrame frame = new JFrame("My Search Browser");

        // Create a new JPanel object
        JPanel panel = new JPanel();

        // Set the background color of the panel to cyan
        panel.setBackground(Color.CYAN);

        // Create a new JLabel object
        JLabel label = new JLabel("Enter search term:");

        // Create a new JButton object
        JButton searchButton = new JButton("Search");

        // Create a new JTextField object
        JTextField textField = new JTextField(30);

        // Create a new JTextArea object
        JTextArea textArea = new JTextArea(10, 30);

        // Create a new JButton object
        JButton button = new JButton("Choose Files");

        //create a new JcomboBox object
        JComboBox<String> comboBox = new JComboBox<>(new String[] {"All", "Any"});


        

       // Add an ActionListener to the button
       button.addActionListener(new ActionListener() 
       {
            public void actionPerformed(ActionEvent e) 
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                int result = fileChooser.showOpenDialog(frame);
                
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    File[] files = fileChooser.getSelectedFiles();
                    String[] searchTerms = textField.getText().split("\\s+");
                    String searchMode = (String) comboBox.getSelectedItem();
                    textArea.setText("");
                    SearchEngine searchEngine = new SearchEngine();
                    searchEngine.search(files, searchTerms, searchMode, textArea);
                }
            }   
        });
    

        // Add the label, text field, and button to the panel
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(textArea);
        panel.add(comboBox);
        panel.add(searchButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set the size of the frame
        frame.setSize(500, 100);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }
}