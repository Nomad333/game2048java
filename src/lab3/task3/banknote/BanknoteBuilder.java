package lab3.task3.banknote;

import java.util.List;

public interface BanknoteBuilder extends Builder<List<Banknote>> {
    BanknoteBuilder nominal(int nominal, int count);
}