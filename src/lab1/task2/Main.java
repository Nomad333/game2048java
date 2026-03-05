package lab1.task2;

import java.util.Random;
import java.util.Scanner;

public class Main {
    /*
    Користувач вгадує число, яке “ загадав ” комп’ютер. Можливі 2 рівні
    складності: на першому рівні число від 1 до 10, на другому від 10 до 100.
    Перехід на другий рівень можливий тільки після завершення першого без
    програшу (якщо гравець бажає продовжувати).
    На початку кожного рівня гравцю даються “ життя ” - 50% від довжини
    діапазону вгадування на першому рівні і 25% - на другому (приклад наведено
    нижче). За кожну помилку гравець штрафується – мінус одне життя.
    Передбачити у грі можливість підказки: «загадане число більше — менше
    твого». Вартість підказки - одне життя (користувач може прийняти підказку у
    разі помилки або відмовитися від неї).
    Перший рівень триває три раунди (на кожному раунді загадується нове
    число), а другий — два раунди. Нові життя нараховуються гравцеві перед
    новим раундом. Очки після рівня для користувача - кількість життів, що
    залишилися, помножені на 5 для першого рівня і на 10 для другого. Результат
    гри, кількість очок або “Програш” вивести в консоль. Якщо користувач програв
    раунд, то комп’ютеру нараховуються очки за раунд у розмірі: початкові життя
    користувача, помножені на 5 для першого рівня та на 10 для другого.
    Кількість очок зберігається та накопичується в раундах та рівнях.
    Проміжні підсумки виводяться після кожного раунду та рівня.
     */

    public static void main(String[] args) {
        int[] minRangesPerLevel = {0, 10};
        int[] maxRangesPerLevel = {10, 100};
        int[] roundsPerLevel = {3, 2};

        int levels = minRangesPerLevel.length;
        GameState state = new GameState();

        boolean isEnd = false;

        do {
            for (int currentLevel = 0; currentLevel < levels; currentLevel++) {
                for (int round = 0; round < roundsPerLevel[currentLevel]; round++) {
                    int range = maxRangesPerLevel[currentLevel] - minRangesPerLevel[currentLevel];
                    int startHealth = calcHealth(range, currentLevel);
                    int targetNum = randInt(minRangesPerLevel[currentLevel], maxRangesPerLevel[currentLevel]);
                    boolean isShowedTip = false;

                    state.setHealth(startHealth);
                    System.out.println("-".repeat(60));

                    while (state.getHealth() > 0) {
                        System.out.printf("\n--- Рiвень %d | Раунд %d |  Життiв: %d ---\n", currentLevel + 1, (round + 1), state.getHealth());
                        System.out.println("Дiапазон: " + minRangesPerLevel[currentLevel] + "-" + maxRangesPerLevel[currentLevel]);
                        System.out.print("Вгадайте число: ");
                        int userNum = getNumberFromInput();

                        if (userNum == targetNum) {
                            System.out.println("⭐ Ви вгадали!");
                            state.fillUserScore(currentLevel);
                            break;
                        } else {
                            System.out.println("Невiрно!");
                        }

                        state.setHealth(state.getHealth() - 1);

                        if (state.getHealth() <= 0) {
                            System.out.println("💀 Закiнчилися життя. Раунд програн!");
                            state.fillEnemyScore(startHealth, currentLevel);
                            break;
                        }

                        if (state.getHealth() > 1 && !isShowedTip) {
                            isShowedTip = true;
                            System.out.println("Хочете пiдсказку? (-1 життя)\n 1-Так");
                            int usNum = getNumberFromInput();
                            if (usNum == 1) {
                                int avg = (maxRangesPerLevel[currentLevel] - minRangesPerLevel[currentLevel]) / 2;
                                if (avg > targetNum) {
                                    System.out.println("Число меньше " + avg);
                                } else {
                                    System.out.println("Число бiльше " + avg);
                                }
                                state.setHealth(state.getHealth() - 1);
                            }
                        }
                    }
                    System.out.println(state);
                }
                if (state.getUserScore() >= state.getEnemyScore()) {
                    System.out.println("Бажаете продовжити?\n1-Так\n2-Перезапуск");
                    int usNum = getNumberFromInput();
                    if (usNum == 1) {
                        System.out.println("Ви перемогли на цьмоу рiвнi!");
                    } else if (usNum == 2) {
                        state.reset();
                        break;
                    } else {
                        isEnd = true;
                        break;
                    }
                } else {
                    System.out.println("Ви програли на цьому рiвнi!");
                    isEnd = true;
                    break;
                }
            }
        } while (!isEnd);
    }

    public static int calcHealth(int range, int level) {
        float k = (float) (level == 0 ? 0.5 : 0.25);
        return (int) (range * k);
    }

    public static int getNumberFromInput() {
        boolean isEntered = false;
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        do {
            try {
                input = scanner.nextInt();

                isEntered = true;
            } catch (Exception ex) {
                System.out.println("Невiрний тип даних!");
                scanner.next();
            }
        } while (!isEntered);
        return input;
    }

    public static int randInt(int min, int max) {
        var rnd = new Random();
        return rnd.nextInt(min, max);
    }
}
