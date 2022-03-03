package Week03.controller;

import Week03.entities.Admin;
import Week03.entities.Book;
import Week03.entities.Client;

import java.util.List;

public interface LibraryAPIService {
    void ban(Admin admin, Client client);
    void unban(Admin admin, Client client);
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
