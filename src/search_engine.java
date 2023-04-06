//date 05/04/2023
//author filip kruk c21420324
//program name: search_engine
//program description: this program searches for a word in a file and returns the number of matches

import java.io.*;//
import java.util.*;

public class search_engine
{
   private static final String[] f_names ={ " video games.csv", "movies.csv", " programing languages.csv", "mobiles.csv", "top tech companies.csv" };//this creates an array of filenames that the program will search through

   //main method
   public static void main(String[] args) throws IOException 
   {
        //this code  creates a scanner to read the user input
        try (Scanner inputScanner = new Scanner(System.in))
        {
            System.out.println("Enter a word to search for: ");//this code  prompts the user to enter a word to search for
            String searchword = inputScanner.next().toLowerCase();//this code reads the user input and converts it to lowercase

            //this code  creates a hashmap to store the results of the search
            Map<String, Integer> results = new HashMap<>();
            for(String filename: f_names)
            {
                //this code  counts the number of word matches
                int wordcount = 0;
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;

                //this code reads the file line by line
                while((line = reader.readLine()) != null)
                {
                    //this code  splits the line into words so that more than one word can be recognised within a search
                    String[] words = line.toLowerCase().split("\\s+");
                    for(String word : words )
                    {
                        //this if statement  checks if the word matches the word being searched for
                        if(word.equals(searchword))
                        {
                            wordcount++;
                        }
                    }
                }
                reader.close();//closes the reader

                if(wordcount > 0)//this if statement  checks if the word was found in the file
                {
                    results.put(filename, wordcount);
                }
            }

            if(results.isEmpty())//this if statement  checks if the results are empty
            {
                System.out.println("No matches found");
            }

            else//this else statement  prints the results
            {
                System.out.println("Matches found in the following files: ");
                results.entrySet().stream()// this sorts the results by the number of matches
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s (%d matches)%n", entry.getKey(), entry.getValue()));
            }
        }
    }
}//end public class search_engine