import java.io.*;
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
                    wordList.add(word);
                }
            }

            try (FileWriter writer = new FileWriter(new File("WORDS.txt"))) {   // write to WORDS.txt
                for(BST.Node node : wordList.inOrder()) {
                    writer.write(node.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            wordList.destroy();     // removes the tree from memory
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error! " + filename + " cannot be read!");
        }
    }
}