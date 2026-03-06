package lab3.task3;

import lab3.task3.banknote.Banknote;
import lab3.task3.banknote.BanknoteBuilder;
import lab3.task3.exeptions.BalanceExeption;
import lab3.task3.exeptions.NominalExeption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ATM {
    private final List<Banknote> banknotes = new ArrayList<>();
    private final int[] availableNominals;
    private final BanknoteBuilder builder;

    public ATM(int[] availableNominals, BanknoteBuilder builder) {
        this.availableNominals = availableNominals;
        this.builder = builder;
    }

    private void checkNominal(int nominal) {
        var isFound = Arrays.stream(availableNominals).anyMatch(el -> el == nominal);
        if (!isFound) throw new NominalExeption("nominal:" + nominal);
    }

    // внесение денег пользователем
    public void deposit(int nominal, int count) {
        checkNominal(nominal);
        banknotes.addAll(
                builder.nominal(nominal, count).build()
        );
    }

    // снятие денег
    public List<Banknote> withdraw(int amount) {

        List<Banknote> result = new ArrayList<>();

        // сортируем
        banknotes.sort(Comparator.comparingInt(Banknote::getNominal).reversed());

        int remaining = amount;

        for (Banknote b : banknotes) {

            if (b.getNominal() <= remaining) {
                result.add(b);
                remaining -= b.getNominal();
            }

            if (remaining == 0) break;
        }

        if (remaining != 0) {
            throw new BalanceExeption("ba");
        }

        banknotes.removeAll(result);

        return result;
    }
}
