package Week03;

import Week03.controller.LibraryAPIService;
import Week03.controller.impl.LibraryAPIServiceImpl;
import Week03.entities.Admin;
import Week03.entities.Book;
import Week03.entities.Client;
import Week03.services.AdminService;
import Week03.services.ClientService;
import Week03.services.impl.AdminServiceImpl;
import Week03.services.impl.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        LibraryAPIService libraryAPIService = new LibraryAPIServiceImpl(adminService, clientService);

        Admin admin = new Admin("Админ",
                "Админов",
                "Админович",
                "7888888888");
        Client client = new Client("Иван",
                "Иванов",
                "Иванович",
                "7999999999");

        List<Book> books = new ArrayList<>();
        books.add(new Book("Евгений Онегин", "Пушкин A.C."));
        books.add(new Book("Братья Карамазовы", "Достоевский Ф.М."));

        libraryAPIService.ban(admin, client);
        libraryAPIService.takeBooks(client, books);
        libraryAPIService.unban(admin, client);
        libraryAPIService.takeBooks(client, books);
        System.out.println(client.getBooks());
        libraryAPIService.returnBooks(client, books);
        System.out.println(client.getBooks());
    }
}
