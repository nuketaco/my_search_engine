import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class search_browser extends JFrame implements ActionListener 
{
   private JLabel label;
   private JTextField textField;
   private JButton button;
   private JTextArea textArea;
   private JFileChooser fileChooser;

   public search_browser() 
   {
      // Set the title and layout of the window
      super("Word Search");
      setLayout(null);

      // Create the label and text field for entering the word to search for
      label = new JLabel("Enter the word to be searched:");
      label.setBounds(10, 10, 200, 30);
      add(label);

      textField = new JTextField();
      textField.setBounds(220, 10, 150, 30);
      add(textField);

      // Create the button for starting the search
      button = new JButton("Search");
      button.setBounds(380, 10, 100, 30);
      button.addActionListener(this);
      add(button);

      // Create the text area for displaying the results
      textArea = new JTextArea();
      textArea.setBounds(10, 50, 470, 200);
      textArea.setEditable(false);
      add(textArea);

      // Create the file chooser for selecting the file to search
      fileChooser = new JFileChooser();

      // Set the size and location of the window
      setSize(500, 300);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public void actionPerformed(ActionEvent e) 
   {
      if (e.getSource() == button) 
      {
         // Get the word to search for from the text field
         String wrd = textField.getText();

         // Show the file chooser dialog
         int result = fileChooser.showOpenDialog(this);
         
         if (result == JFileChooser.APPROVE_OPTION) 
         {
            // Get the selected file
            File f1 = fileChooser.getSelectedFile();

            // Search for the word in the file
            int cnt = 0;
            String s;
            String[] buffer;
            
            try (FileReader fr = new FileReader(f1); BufferedReader bfr = new BufferedReader(fr)) 
            {
               while ((s = bfr.readLine()) != null) 
               {
                  buffer = s.split(" ");
                  
                  for (String chr : buffer) 
                  {
                     if (chr.equals(wrd)) 
                     {
                        cnt++;
                     }
                  }
               }
            } 
            
            catch (IOException ex) 
            {
               ex.printStackTrace();
            }

            // Display the results in the text area
            if (cnt == 0) 
            {
               textArea.setText("Word not found!");
            } 
            
            else 
            {
               textArea.setText("Word: " + wrd + " found! Count: " + cnt);
            }
         }
      }
   }

   public static void main(String[] args)
    {
      SwingUtilities.invokeLater(new Runnable() 
      {
         public void run() 
         {
            new search_browserGUI().setVisible(true);
         }
      });
   }
}
