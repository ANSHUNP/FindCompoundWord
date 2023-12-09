import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Question {

    public static void main(String[] args) {
        String fileName = "Input_01.txt";

        long startTime = System.currentTimeMillis();

        Set<String> wordsSet = readWordsFromFile(fileName);
        String CompoundWord = LongestWord(wordsSet);
        String secondCompoundWord = SecondLongestWord(wordsSet);

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        System.out.println("Longest Compound Word: " + CompoundWord);
        System.out.println("Second Longest Compound Word: " + secondCompoundWord);
        System.out.println("Time taken to process file " + fileName + ": " + timeTaken + " milliseconds");
    }

    private static Set<String> readWordsFromFile(String fileName) {
        Set<String> wordsSet = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordsSet.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return wordsSet;
    }

    private static boolean isCompoundWord(String word, Set<String> wordsSet) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (wordsSet.contains(prefix) && (wordsSet.contains(suffix) || isCompoundWord(suffix, wordsSet))) {
                return true;
            }
        }
        return false;
    }

    private static String LongestWord(Set<String> wordsSet) {
        String CompoundWord = "";
        for (String word : wordsSet) {
            if (isCompoundWord(word, wordsSet) && word.length() > CompoundWord.length()) {
                CompoundWord = word;
            }
        }
        return CompoundWord;
    }

    private static String SecondLongestWord(Set<String> wordsSet) {
        String CompoundWord = LongestWord(wordsSet);
        wordsSet.remove(CompoundWord); 
        return LongestWord(wordsSet);
    }
}
