package filter.writer;

import java.io.*;

public class WordWriter {
    private static final String PATH = "C:\\Users\\kasper084\\Desktop\\swear.txt";

    public void write(String word) {
        try (FileOutputStream zero = new FileOutputStream(PATH)) {
            byte[] wordBytes = word.getBytes();
            zero.write(wordBytes);
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