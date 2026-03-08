package lab5.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BanWordFileChecker {
    private String pathDirectoryForCheck;
    private String pathFileWithBanWords;

    public BanWordFileChecker(String pathDirectoryForCheck, String pathFileWithBanWords) {
        this.pathDirectoryForCheck = pathDirectoryForCheck;
        this.pathFileWithBanWords = pathFileWithBanWords;
    }

    public Map<File, Map<String, Integer>> getBanWordsInFiles() {
        Map<File, Map<String, Integer>> result = new HashMap<>();
        var dir = readPathsInDirectoryForCheck();
        var banWords = readBanWordsFromFile();
        for (File file : dir) {
            var res = getBanWordsInFile(file, banWords);
            result.put(file, res);
        }
        return result;
    }

    public void replaceBanWordsInFiles() {
        var paths = readPathsInDirectoryForCheck();
        for (File file : paths) {
            replaceBanWordsInFile(file);
        }
    }

    public void replaceBanWordsInFile(File file) {
        var text = readTextFromFile(file);
        var banWords = readBanWordsFromFile();
        var replacedText = replaceWords(text, banWords, "*");
        try {
            Files.writeString(Path.of(file.toURI()), replacedText);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void printInfoAboutBanWordsInFiles() {
        var map = getBanWordsInFiles();
        System.out.println("---Info about ban words in files---");
        for (var entry : map.entrySet()) {
            System.out.print("File: " + entry.getKey().getName());
            if (entry.getValue().isEmpty()) System.out.println(" [GOOD]");
            else System.out.println(" [BAD]");
            for (var wordEntry : entry.getValue().entrySet()) {
                System.out.println("  Ban word: " + wordEntry.getKey() + ", Count: " + wordEntry.getValue());
            }
        }

    }

    private String replaceWords(String input, String[] removeWords, String replacement) {
        for (String word : removeWords) {
            String regex = Pattern.quote(word);
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            String stars = replacement.repeat(word.length());

            input = matcher.replaceAll(stars);
        }

        return input;
    }

    private Map<String, Integer> countBanWords(String input, String[] words) {
        Map<String, Integer> result = new HashMap<>();

        for (String word : words) {
            Pattern pattern = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            if (count > 0) {
                result.put(word, count);
            }
        }

        return result;
    }

    private String readTextFromFile(File file) {
        try {
            return Files.readString(Path.of(file.toURI()));
        } catch (IOException e) {
            System.err.println(e);
        }
        return "";
    }

    private Map<String, Integer> getBanWordsInFile(File file, String[] banWords) {
        var text = readTextFromFile(file);
        return countBanWords(text, banWords);
    }

    private String[] readBanWordsFromFile() {
        try {
            return Files.readString(Path.of(pathFileWithBanWords)).split("[,\\r?\\n]+");
        } catch (IOException e) {
            System.err.println(e);
        }
        return new String[0];
    }

    private File[] readPathsInDirectoryForCheck() {
        File directory = new File(pathDirectoryForCheck);
        if (!directory.exists() || !directory.isDirectory()) {
            directory.mkdirs();
        }
        return directory.listFiles();
    }

    public String getPathDirectoryForCheck() {
        return pathDirectoryForCheck;
    }

    public void setPathDirectoryForCheck(String pathDirectoryForCheck) {
        this.pathDirectoryForCheck = pathDirectoryForCheck;
    }

    public String getPathFileWithBanWords() {
        return pathFileWithBanWords;
    }

    public void setPathFileWithBanWords(String pathFileWithBanWords) {
        this.pathFileWithBanWords = pathFileWithBanWords;
    }
}
