import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input filename: ");
        String filename = input.nextLine();
        File file = new File(filename);

        try (FileInputStream stream = new FileInputStream(file)) {                  // try and open the file as an input stream
            String[] tokens = MyReader.processText(stream, (int) file.length());    // receive the tokens from MyReader
            BST wordList = new BST();                                               // initialize tree

            for (String word : tokens) {
                if (word.length() >= 3) {   // only counts words with length >= 3
                    wordList.insert(word);
                }
            }

            wordList.inOrder();             // display everything in tree
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error! " + filename + " cannot be read!");
        }
    }
}