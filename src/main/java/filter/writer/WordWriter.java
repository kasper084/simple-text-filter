package filter.writer;

import java.io.*;

public class WordWriter {
    private static final String PATH = "src/file/swear.txt";

    public void write(String word) {
        try (FileWriter fileWriter = new FileWriter(PATH)) {
            fileWriter.write(word);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPATH() {
        return PATH;
    }
}