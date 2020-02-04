package filter.reader;

import filter.utils.Encoder;
import filter.writer.WordWriter;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class WordReader {
    private static final String DATA = "data.txt";
    private static final String VOCABULARY = "vocabulary.txt";
    private static final String CHARACTERS = ",|!|\\(|\\)|\\.";
    private static final String REPLACEMENT = " ";
    private static final String REGEX = "\\s+";

    private List<String> shortWords = new ArrayList<>();
    private Map<String, Integer> mapOfWords = new HashMap<>();

    private WordWriter writer = new WordWriter();

    private String readData(String path) {
        File file = new File(Objects.requireNonNull(Objects.requireNonNull(WordReader.class.getClassLoader().getResource(path)).getFile()));
        String data = null;
        try {
            data = Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> getAllWords(String path) {
        List<String> words;
        String data = readData(path);
        data = data.replaceAll(CHARACTERS, REPLACEMENT);
        words = Arrays.asList(data.split(REGEX));
        return words;
    }

    public void countWords() {
        int num = 0;
        int swears = 0;
        int smallWord = 0;
        List<String> words = getAllWords(DATA);
        for (String word : words) {
            mapOfWords.put(word, num);
            num++;
            if (isWordBad(word)) {
                swears++;
                writer.write(Encoder.encode(word));
            } else if (word.length() < 3) {
                smallWord++;
                shortWords.add(word);
            }
        }
        System.out.println("Total num of words: " + num);
        System.out.println("Total without short and swear words: " + (num - (smallWord + swears)));
        System.out.println("All short words: " + "\n" + shortWords);
    }

    private boolean isWordBad(String word) {
        String some = getAllWords(VOCABULARY).stream()
                .filter(s -> Encoder.decode(s).equals(word)).findAny().orElse(null);
        return word.equals(some);
    }

    public void findMostRepeatedWords() {
        System.out.println("Most used words by descending order:");
        System.out.println(mapOfWords.keySet());
    }
}