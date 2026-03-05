package lab3.task1;

import java.util.function.Predicate;

public interface Query<T> {
    void showAll();

    void selectAll(Predicate<T> pred);
}
