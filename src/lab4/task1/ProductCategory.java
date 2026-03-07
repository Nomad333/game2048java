package lab4.task1;

public enum ProductCategory {

    ELECTRONICS("Електроніка"),
    CLOTHING("Одяг"),
    FOOD("Продукти харчування"),
    BOOKS("Книги"),
    HOME("Товари для дому"),
    TOYS("Іграшки"),
    SPORTS("Спорт"),
    BEAUTY("Краса"),
    AUTOMOTIVE("Автотовари"),
    OTHER("Інше");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
