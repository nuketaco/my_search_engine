import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.awt.Font;

public class search_browserGUI extends JFrame implements ActionListener 
{
   private JLabel label;
   private JTextField textField;
   private JButton button;
   private JTextArea textArea;
   private JFileChooser fileChooser;

   public search_browserGUI() 
   {
      // Set the title and layout of the window
      super("Word Search");
      setLayout(null);
      getContentPane().setBackground(new Color(0, 206, 209)); // Set the background color to silver

      // Create the label and text field for entering the word to search for
      label = new JLabel("Enter the word to be searched:");
      label.setBounds(10, 10, 200, 30);
      label.setFont(new Font("consolas", Font.BOLD, 11)); // Change the font of the label
      add(label);

      textField = new JTextField();
      textField.setBounds(220, 10, 150, 30);
      textField.setFont(new Font("consolas", Font.PLAIN, 14)); // Change the font of the text field
      add(textField);

      // Create the button for starting the search
      button = new JButton("Search");
      button.setBounds(380, 10, 100, 30);
      button.setFont(new Font("consolas", Font.BOLD, 14)); // Change the font of the button
      button.addActionListener(this);
      add(button);

      // Create the text area for displaying the results
      textArea = new JTextArea();
      textArea.setBounds(10, 50, 470, 200);
      textArea.setEditable(false);
      textArea.setFont(new Font("consolas", Font.PLAIN, 14)); // Change the font of the text area
      add(textArea);

      // Create the file chooser for selecting the files to search
      fileChooser = new JFileChooser();
      fileChooser.setMultiSelectionEnabled(true);

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
            // Get the selected files
            File[] files = fileChooser.getSelectedFiles();

            // Search for the word in each file
            StringBuilder sb = new StringBuilder();
            for (File f1 : files) 
            {
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

               // Append the results to the string builder
               sb.append(f1.getName()).append(": ");
               if (cnt == 0) 
               {
                  sb.append("Word not found!\n");
               } 
               
               else 
               {
                  sb.append("Word: ").append(wrd).append(" found! wordCount: ").append(cnt).append("\n");
               }
            }

            // Display the results in the text area
            textArea.setText(sb.toString());
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