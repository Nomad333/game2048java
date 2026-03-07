package lab3.task3;

import lab3.task3.banknote.DefaultBanknoteBuilder;
import lab3.task3.exeptions.BalanceException;
import lab3.task3.exeptions.BanknoteAmountExeption;
import lab3.task3.exeptions.NominalExeption;
import lab3.task3.exeptions.WithdrawException;

public class Main {
    /*
    Завдання 3. Створіть клас “Банк”, якому належить мережа АТМ
    банкоматів (має бути поле, що визначає кількість банкоматів). Визначити
    операції ініціалізації мережі банкоматів, тобто створення банкоматів та

    завантаження туди певної суми коштів. Розробіть метод визначення загальної
    суми грошей, що знаходиться у мережі банкоматів.
    Розробіть клас “АТМ банкомат”. Сума, що є в наявності у банкоматі, має
    бути подана певними номіналами банкнот (1, 2, 5, 10, 20, 50 100, 200, 500
    гривень). До кожного номіналу банкнот визначено кількість купюр даного
    номіналу. Крім цього, банкомат зберігає у своїх полях максимальну для видачі
    суму, а також максимальну кількість банкнот, яка може бути видана через
    віконце видачі готівки.
    Реалізуйте метод ініціалізації банкомату (завантаження грошей до
    банкомату), метод ручного введення якоїсь суми через купюроприймач, метод
    зняття грошей у банкоматі. При визначенні номіналів банкнот під час видачі
    виходимо з мінімізації їхньої кількості. Для реалізації завдання побудуйте
ієрархію класів-винятків користувача.
     */
    public static void main(String[] args) throws NominalExeption, BalanceException, BanknoteAmountExeption, WithdrawException {
        int atmCount = 5;
        char symbol = '$';
        var nominals = new int[]{1, 2, 5, 10, 20, 50, 100, 200, 500};
        var banknotesPerATM = new DefaultBanknoteBuilder(symbol)
                .put(1, 50)
                .put(2, 50)
                .put(5, 10)
                .put(50, 20)
                .build();

        Bank bank = new Bank(atmCount, new DefaultBanknoteBuilder(symbol), nominals, banknotesPerATM);
        System.out.println(bank);
        System.out.println(bank.getAtms().get(0).withdraw(1000));
        System.out.println(bank.getAtms().get(0));
    }
}
