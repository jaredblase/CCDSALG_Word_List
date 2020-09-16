import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The MyReader class has one method that takes in an input stream (such as a text file)
 * and retrieves all the words as tokens using spaces as the separator.
 */
public class MyReader {
    /**
     * Reads the characters from a file stream and returns all the word tokens.
     * @param stream the file input stream to be read.
     * @param size the size of the file.
     * @return the word tokens in a String[].
     * @throws IOException if an IO error occurs.
     */
    public static String[] processText(FileInputStream stream, int size) throws IOException {
        byte[] text = new byte[size];                               // byte array to store the whole stream
        stream.read(text);

        String words = new String(text, StandardCharsets.UTF_8);
        words = words.replaceAll("[\\W\\d]", " ");       // converts all non-word characters to spaces
        words = words.trim();                                       // removes excess space before and after the String

        return words.split("\\s+");                            // returns the tokens, using space as the separator
    }
}
