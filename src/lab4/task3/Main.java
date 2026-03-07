package lab4.task3;

import lab4.task3.publications.Book;
import lab4.task3.publications.Publication;

public class Main {
    /*
     Розробити програму «Каталог бібліотеки». У бібліотеці можуть
    зберігатися книги, газети та альманахи (збірки творів кількох авторів).
    Для книги у нас має бути автор книги, назва книги, жанр, кількість сторінок.
    Для газети має бути назва газети, дата виходу номера, перелік основних
    заголовків газети. Характеристиками альманаху є назва альманаху та перелік
    творів (книг), які у ньому надруковані. Розробити інтерфейсний клас із
    необхідним переліком віртуальних абстрактних методів. Для каталогу
    передбачити можливість тестової ініціалізації, додавання об'єкта
    конкретного типу, додавання об'єкта випадкового типу, видалення об'єкта
    за назвою, виведення всього каталогу на екран, пошук за назвою книги або
    газети, пошук за автором.
     */
    public static void main(String[] args) {
        var b2 = new Book("Alex", "Title5", "Genre2", 2);
        Catalog catalog = new Catalog();
        catalog.testInit();
        catalog.add(b2);
        catalog.deleteByTitle("Title5");

        System.out.println("res: " + catalog.searchByAuthor("Dan", Book.class));
        System.out.println("res: " + catalog.searchByTitle("Title1", Publication.class));

        catalog.printPublications();
    }
}
