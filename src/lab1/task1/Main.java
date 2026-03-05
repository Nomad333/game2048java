package lab1.task1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
         1. Вантажний літак повинен пролетіти з вантажем з пункту А пункт С
        через пункт В. Ємність бака для палива у літака — Х літрів (значення
        зчитується з файлу). Споживання палива на 1 км залежно від ваги вантажу
        літака таке:
        - до 500 кг - 1 літрів/км;
        - до 1000 кг - 4 літрів/км;
        - до 1500 кг - 7 літрів/км;
        - до 2000 кг - 9 літрів/км;
        - більше 2000 кг – літак не піднімає.
        З файлу вводиться відстань між пунктами А і В і відстань між пунктами В
        і С, а також вага вантажу. Програма повинна розрахувати, яку мінімальну
        кількість палива необхідно для дозаправки літака в пункті В, щоб долетіти з
        пункту А до пункту С. У разі неможливості подолати будь-яку з відстаней —
        програма повинна вивести повідомлення про неможливість польоту за
        введеним маршрутом.
        */
        boolean isEnteredDistances = false;
        Scanner scanner = new Scanner(System.in);
        int[] distances_array;
        do {
            System.out.println("Введiть дистанцii  км,км,...");
            String distances = scanner.nextLine();
            distances_array = Arrays.stream(distances.split(",")).mapToInt(Integer::parseInt).toArray();
            if (distances_array == null) continue;
            isEnteredDistances = true;
        } while (!isEnteredDistances);
        System.out.println(Arrays.toString(distances_array));

        Plane plane = new Plane(500, 500);
        System.out.println("Чи можливий перелiт: " + plane.isCanFly(distances_array));
        System.out.println("Залишок: " + plane.calculateRestValueTank(distances_array) + " Л");
    }
}
