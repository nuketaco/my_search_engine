## Getting Started
Hi welcome to my JAVA repository for my_Search_Engine

## Folder Structure
This workspace contains 
`src`: the folder to maintain sources
`lib`: the folder to maintain dependencies
5 .csv files which are needed for the  search engine to work

## project overview
this java program creates a psuedo search engine that checks predetermined files for a user specified word and returns the filenames in accending order based on the ammount of words similar to the one the user has searched for.

## list of classes
search_engine: this program searches for a word in a set of predetermined files 
and returns the number of matches found in each file. this program uses an array to search through 
and ask the user to enter a word to search for.it reads each file line by line and splits the line into words so that it can recognise more than one word at a time. This program then counts the number of word matches for the searchterm and stores these results in a hashmap.it then sorts and prints the results based on the number of matches found in each file.within this program there are 
bufferedreader and filereader classes which read the file as well as the hashmap to store and sort the results. the program uses try statements to auto close the reaser afte the file has been read.

my_search_engineGUI: this is a GUI program that searches through predetermined files for a user inputted searchterm. the program displays a 600x600 window with a text field to let the user enter a keyword along with a serach button to start the search.when the search button is pressed, the program displays a message box showing the keyword that the user is searching for. the program then displays the search results in a text area. this program has three buttons searc,cancel and end. the search button starts the search, the cancel button clears the text and anwser fields. while the end button  closses the program. the program uses a JFrame in order to display the GUI.The searchFiles()method is responsible for searching through the 5 files. it takes the keyword entered by the user splits it into separate words and seacrhes each file for the word. if a file contains the word,the program displays the file name and the ammount of matches it found within that file.

my_search_engineGUI class depends on the search_engine class to perform the search operation itself
mainly the searchFiles() method in my_search_engineGUI calls upon  the search() method in the search_engine class without this class the search operation will not work and cause the program to crash.

## description of core and optional functionaliy
this program has the ability to search through files and find relevant words that are closely matched to the the searchterm provided by the user. it then sorts the files based on wordcount ammount and places them in accending order along with the ammount of words in the files that match the searchterm. the program allows the user to search for more than one word regardless if the word is capitalized or not or if it has symbols.

## features that I would add 
I wouldeve liked to implement a full file chooser system into the GUI rather than the limited 5 file option system in place as well as a proper word spelling error checker.

## links 
github repo: https://github.com/nuketaco/my_search_engine.git
youtube video: 
