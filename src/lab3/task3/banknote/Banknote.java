package lab3.task3.banknote;

public class Banknote {
    private final int nominal;
    private final char symbol;

    public Banknote(int nominal, char symbol) {
        this.nominal = nominal;
        this.symbol = symbol;
    }

    public int getNominal() {
        return nominal;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "" + nominal + symbol;
    }
}
