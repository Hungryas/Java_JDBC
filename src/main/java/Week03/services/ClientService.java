package Week03.services;

import Week03.entities.Book;
import Week03.entities.Client;

import java.util.List;

public interface ClientService {
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
