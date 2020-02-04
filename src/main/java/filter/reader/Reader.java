package filter.reader;

import filter.utils.Encoder;
import filter.writer.WordWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Reader {
    private static final String DATA = "C:\\Users\\kasper084\\Desktop\\data.txt";
    private static final String VOCABULARY = "C:\\Users\\kasper084\\Desktop\\vocabulary.txt";
    private static final String CHARACTERS_REGEX = ",|\\(|\\)|\\'|!";
    private static final String REPLACEMENT = " ";
    private static final String REGEX = "\\s+";
    private static List<String> shortWords = new ArrayList<>();

    private WordWriter writer = new WordWriter();

    private String readData(String path) {
        String data = null;
        try {
            data = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> getAllWords(String path) {
        List<String> words;
        String data = readData(path);
        if (data.contains(CHARACTERS_REGEX)) {
            data = data.replaceAll(CHARACTERS_REGEX, REPLACEMENT);
        }
        words = Arrays.asList(data.split(REGEX));
        return words;
    }

    public void countWords() {
        int num = 0;
        int swears = 0;
        int smallWord = 0;
        for (String word : getAllWords(DATA)) {
            num++;
            if (isWordBad(word)) {
                swears++;
                writer.write(word);
            } else if (isWordBad(word) || word.length() < 3) {
                smallWord++;
                shortWords.add(word);
            }
        }
        System.out.println("Total num of words: " + num);
        System.out.println("Total without short and swear words: " + (num - (smallWord + swears)));
    }

    private boolean isWordBad(String word) {
        List<String> badWordsList;
        String data = readData(VOCABULARY);
        data = data.replaceAll(CHARACTERS_REGEX, REPLACEMENT);
        badWordsList = Arrays.asList(data.split(REGEX));
        return badWordsList.contains(Encoder.encode(word));
    }
}