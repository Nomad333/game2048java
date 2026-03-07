package lab4.task3.publications;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Newspaper implements Publication {
    private String title;
    private LocalDate releaseDate;
    private List<String> headlines;

    public Newspaper(String title, LocalDate releaseDate, List<String> headlines) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.headlines = headlines;
    }

    public String getTitle() {
        return title;
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", headlines=" + headlines +
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
