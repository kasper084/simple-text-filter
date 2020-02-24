package filter.writer;

import java.io.*;

public class WordWriter {
    private static final String PATH_TO_FILE = "src/file/swear.txt";

    public void write(String word) {

        try (FileWriter fileWriter = new FileWriter(PATH_TO_FILE)) {
            fileWriter.write(word);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static String getPATH() {
        return PATH_TO_FILE;
    }
}