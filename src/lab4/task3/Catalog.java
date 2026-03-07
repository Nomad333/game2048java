package lab4.task3;

import lab4.task3.publications.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Publication> publications = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(List<Publication> publications) {
        this.publications = publications;
    }

    public void testInit() {
        var b1 = new Book("Mike", "Title1", "Genre1", 5);
        var b2 = new Book("Dan", "Title2", "Genre2", 2);
        publications = new ArrayList<>(List.of(
                b1,
                b2,
                new Newspaper("Title3", LocalDate.now(), List.of("Head1", "Head1")),
                new Almanac("Title4", List.of(b1, b2))
        )
        );
    }

    public <T extends Publication> void add(T value) {
        publications.add(value);
    }

    public void deleteByTitle(String value) {
        var pub = publications.stream()
                .filter(el -> el
                        .getTitle()
                        .equals(value))
                .findFirst()
                .orElse(null);

        if (pub == null) return;

        publications.remove(pub);
    }

    public <T extends Search> List<T> searchByTitle(String title, Class<T> type) {
        return publications.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .filter(el -> title.equals(el.searchByTitle().orElse("")))
                .toList();
    }

    public <T extends Search> List<T> searchByAuthor(String author, Class<T> type) {
        return publications.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .filter(el -> author.equals(el.searchByAuthor().orElse("")))
                .toList();
    }

    public void printPublications() {
        publications.forEach(System.out::println);
    }
}
