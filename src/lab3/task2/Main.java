package lab3.task2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    /*
    Завдання 2. Реалізуйте клас «Людина»(ім’я, прізвище, дата народження).
    Визначити тип Frequency - перерахування (enum) зі значеннями Weekly,
    Monthly, Yearly. Визначте класи Article( автор статті(«Людина»), назва статті,
    рейтинг статті) та Magazine( назва журналу, поле типу Frequency з інформацією
    про періодичність виходу журналу, дата виходу журналу, тираж журналу,
    список статей у журналі(Article[])).
    Створіть декілька різних журналів. Виведіть інформацію про статті х
    максимальною кількістю сторінок, що публікують у створених журналах.
     */
    public static void main(String[] args) {
        Person p1 = new Person("Ivan", "Petrenko", LocalDate.of(1990, 5, 10));
        Person p2 = new Person("Anna", "Shevchenko", LocalDate.of(1985, 3, 22));

        Article a1 = new Article(p1, "Java Basics", 5);
        Article a2 = new Article(p2, "Machine Learning", 9);
        Article a3 = new Article(p1, "Neural Networks", 7);

        Magazine m1 = new Magazine(
                "Tech Today",
                Frequency.Monthly,
                LocalDate.of(2024, 5, 1),
                1000,
                new Article[]{a1, a2}
        );

        Magazine m2 = new Magazine(
                "AI World",
                Frequency.Weekly,
                LocalDate.of(2024, 5, 5),
                2000,
                new Article[]{a3}
        );

        Magazine[] magazines = {m1, m2};

        Article maxArticle =
                Arrays.stream(magazines)
                        .flatMap(m -> Arrays.stream(m.getArticles()))
                        .max(Comparator.comparingInt(Article::getRating))
                        .orElse(null);
//        Article maxArticle = null;
//        for (Magazine m : magazines) {
//            for (Article a : m.getArticles()) {
//
//                if (maxArticle == null || a.getRating() > maxArticle.getRating()) {
//                    maxArticle = a;
//                }
//            }
//        }

        System.out.println("Article with max rating:");
        System.out.println(maxArticle);
    }
}