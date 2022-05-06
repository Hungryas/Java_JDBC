package Week03.services.impl;

import Week03.entities.Book;
import Week03.entities.Client;
import Week03.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public void takeBooks(Client client, List<Book> books) {
        client.getBooks().addAll(books);
    }

    @Override
    public void returnBooks(Client client, List<Book> books) {
        client.getBooks().removeAll(books);
    }
}
