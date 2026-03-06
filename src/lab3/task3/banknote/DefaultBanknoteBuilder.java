package lab3.task3.banknote;

import java.util.ArrayList;
import java.util.List;

public class DefaultBanknoteBuilder implements BanknoteBuilder {

    private final List<Banknote> banknotes = new ArrayList<>();
    private final char symbol;

    public DefaultBanknoteBuilder(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public BanknoteBuilder nominal(int nominal, int count) {
        for (int i = 0; i < count; i++) {
            banknotes.add(new Banknote(nominal, symbol));
        }
        return this;
    }

    @Override
    public List<Banknote> build() {
        List<Banknote> result = new ArrayList<>(banknotes);
        banknotes.clear();
        return result;
    }
}
