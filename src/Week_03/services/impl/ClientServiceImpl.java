package Week_03.services.impl;

import Week_03.entities.Book;
import Week_03.entities.Client;
import Week_03.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public void takeBooks(Client client, List<Book> books) {
        client.takeBooks(books);
    }

    @Override
    public void returnBooks(Client client, List<Book> books) {
        client.returnBooks(books);
    }
}
