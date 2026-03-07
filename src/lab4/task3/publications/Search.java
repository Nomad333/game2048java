package lab4.task3.publications;

import java.util.Optional;

public interface Search {
    public Optional<String> searchByTitle();

    public Optional<String> searchByAuthor();
}
