package lab4.task3.publications;

import java.util.List;
import java.util.Optional;

public class Almanac implements Publication {
    private String title;
    private List<Book> books;

    public Almanac(String title, List<Book> books) {
        this.title = title;
        this.books = books;
    }

    public String getTitle() {
        return title;
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Almanac{" +
                "title='" + title + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public Optional<String> searchByTitle() {
        return Optional.of(title);
    }

    @Override
    public Optional<String> searchByAuthor() {
        return Optional.empty();
    }
}
