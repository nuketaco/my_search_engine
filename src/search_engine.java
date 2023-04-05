//date 05/04/2023
//author filip kruk c21420324
//program name: search_engine
//program description: this program searches for a word in a file and returns the number of matches
import java.io.*;
import java.util.*;

public class search_engine
{
   private static final String[] f_names ={ "games.csv", "IMBD Top 250 Movies.csv", "Langauges.csv", "mobiles.csv", "Top 50 US Tech Companies 2022-2023" };

   public static void main(String[] args) throws IOException
   {
        try (Scanner inputScanner = new Scanner(System.in))
        {
            System.out.println("Enter a word to search for: ");
            String searchword = inputScanner.next().toLowerCase();

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
                reader.close();

                if(wordcount > 0)
                {
                    results.put(filename, wordcount);
                }
            }

            if(results.isEmpty())
            {
                System.out.println("No matches found");
            }

            else
            {
                System.out.println("Matches found in the following files: ");
                results.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s (%d matches)%n", entry.getKey(), entry.getValue()));
            }
        }
    }
}//end public class search_engine