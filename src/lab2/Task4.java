package lab2;

import java.util.regex.Pattern;

public class Task4 {
    /*
    Завдання 4. За допомогою методу matches() класу Pattern перевірте слова
     вхідного рядку на відповідність деякому шаблону, згідно якого слова мають
      починатись з великої англійської літери, після чого має йти 1-8 символів
      англійського алфавіту, після чого має йти “tion”. А завершуватись слово
      має розділовим знаком (,.!:;). Приклад рядків, які мають належати такому
      шаблону: Situation:  Motivation,  Action!  Obligation.
     */
    public static void main(String[] args) {
        String input = "Situation: Motivation, Action! Obligation. Test.";

        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z]{1,8}tion[,.!:;]$");

        String[] words = input.split(" ");

        for (String word : words) {
            if (pattern.matcher(word).matches()) {
                System.out.println(word + " -> підходить");
            } else {
                System.out.println(word + " -> не підходить");
            }
        }
    }
}
