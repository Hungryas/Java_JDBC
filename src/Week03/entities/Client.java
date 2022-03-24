package Week03.entities;

import java.util.ArrayList;
import java.util.List;

public class Client extends AbstractUser {
    private Boolean banned = false;
    private final List<Book> books = new ArrayList<>();

    public Client(String firstName, String lastName, String middleName, String phone) {
        super(firstName, lastName, middleName, phone);
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Boolean getBanned() {
        return banned;
    }
}
