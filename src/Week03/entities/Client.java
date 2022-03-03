package Week03.entities;

import java.util.ArrayList;
import java.util.List;

public class Client extends AbstractUser {
    private Boolean banned = false;
    private final List<Book> books = new ArrayList<>();

    public Client(String firstName, String lastName, String middleName, String phone) {
        super(firstName, lastName, middleName, phone);
    }

    public void takeBooks(List<Book> books) {
        this.books.addAll(books);
    }

    public void returnBooks(List<Book> books) {
        this.books.removeAll(books);
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public String getBooks() {
        return books.toString();
    }

    public Boolean getBanned() {
        return banned;
    }
}
