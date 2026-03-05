package lab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    /*
    Завдання1. З заданим текстом необхідно виконати наступне:
    підрахувати кількість, слів та речень у рядку;
    продублювати найдовший рядок у тексті;
    існує масив заборонених слів. Необхідно замінити символи заборонених слів зірочками.
     */
    public static void main(String[] args) {
        var input = "Завдання1. З заданим текстом необхідно виконати наступне.\nРядок2\nРядок3";
        System.out.println("centences: " + countSentences(input));
        System.out.println("words: " + countWords(input));
        System.out.println("duplicate:[" + duplicateLongestLine(input)+"]");
        System.out.println("removed:["+ replaceWords(input, new String[]{"1"}) +"]");
    }

    public static int countWords(String input){
        Pattern pattern = Pattern.compile("\\b\\w+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static int countSentences(String input){
        Pattern pattern = Pattern.compile("[^.!?]+[.!?]");
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static String duplicateLongestLine(String input) {
        String[] lines = input.split("\\n");
        String longest = "";

        for (String line : lines) {
            if (line.length() > longest.length()) {
                longest = line;
            }
        }

        return input + "\n" + longest;
    }

    public static String replaceWords(String input, String[] removeWords){

        for (String word : removeWords) {
            String regex = Pattern.quote(word);
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            String stars = "*".repeat(word.length());

            input = matcher.replaceAll(stars);
        }

        return input;
    }
}
