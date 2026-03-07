package lab4.task3.publications;

import java.util.Optional;

public class Book implements Publication {
    private String author;
    private String title;
    private String genre;
    private int pages;

    public Book(String author, String title, String genre, int pages) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                '}';
    }

    @Override
    public Optional<String> searchByTitle() {
        return Optional.of(title);
    }

    @Override
    public Optional<String> searchByAuthor() {
        return Optional.of(author);
    }
}
