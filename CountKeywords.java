/*
 * Name: Count Keywords
 * Author: Leah Boalich
 * Date: September 21, 2024
 * Assignment: Chapter 21 Excercise 3
 * Description:  This program displays how many keywords are used in a java souce code file.  It does not count keywords used in comments or strings.
 */

// Imports
import java.util.*;
import java.io.*;
 
/* Counts the number of keywords and displays result */
public class CountKeywords {
    // Main method, throws exception if file scanner cannot be created
    public static void main(String[] args) throws Exception {
        // Create console input scanner
        Scanner input = new Scanner(System.in);
        // Ask user for file name
        System.out.print("Enter a Java source file: ");
        // Get the user input
        String filename = input.nextLine();
        // Create File object of inputted file 
        File file = new File(filename);
        // If the file exists
        if (file.exists()) {
            // Display the number of keywords
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        }
        // If file does not exist
        else {
            // Display error message
            System.out.println("File " + filename + " does not exist");
        }
        // Close the console input scanner
        input.close();
    }
    // Returns the number of keywords in the given file, throws exception if file scanner cannot be created
    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally", "float", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"};
        // Turn keywordString array into HashSet
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        // Create counter
        int count = 0;
        //  Create input scanner of file
        Scanner input = new Scanner(file);
        // While there is still data to read in the file
        while (input.hasNext()) {
            // If next input is single line comment
            if (input.hasNext("//")) {
                // Consume the comment
                input.nextLine();
                // Move to next while loop
                continue;
            }
            // If next input is multi-line comment
            if (input.hasNext("/\\*")) {
                // Consume the begining portion of comment
                input.next("/\\*");
                // Continue consuming next portion until the end of comment reached
                while(!input.hasNext("\\*/")) {
                    input.next();
                }
                // Consume the end of the comment
                input.next("\\*/");  
                // Move to next while loop    
                continue;
            }
            // If next portion contains a full string
            if (input.hasNext(".*\".*\".*")) {
                // Consume the portion
                input.next();
                // Move to next while loop
                continue;
            }
            // If the next portion contains "
            if (input.hasNext(".*\".*")) {
                // Conusume the portion
                input.next();
                // Continue consumer next portion until the closing " found
                while (!input.hasNext(".*\".*")) {
                    input.next();
                }
                // Consume the closing portion
                input.next(".*\".*");
                // Continue to next while loop
                continue;

            }
            // Assign next portion to word
            String word = input.next();
            // If word is in the HashSet of keywords
            if (keywordSet.contains(word)) {
                // Add to the count
                count++;
            }
        }
        // Close the file input scanner
        input.close();
        // Return the count
        return count;
    }
}
