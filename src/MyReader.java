import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyReader {
    public static String[] processText(FileInputStream stream, int size) throws IOException {
        byte[] text = new byte[size];
        stream.read(text);

        String words = new String(text, StandardCharsets.UTF_8);
        words = words.replaceAll("\\W", " ");
        words = words.trim();

        return words.split("\\s+");
    }
}
