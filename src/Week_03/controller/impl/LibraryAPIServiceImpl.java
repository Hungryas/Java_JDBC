package Week_03.controller.impl;

import Week_03.controller.LibraryAPIService;
import Week_03.entities.Admin;
import Week_03.entities.Book;
import Week_03.entities.Client;
import Week_03.services.AdminService;
import Week_03.services.ClientService;

import java.util.List;

public class LibraryAPIServiceImpl implements LibraryAPIService {
    private final AdminService adminService;
    private final ClientService clientService;

    public LibraryAPIServiceImpl(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @Override
    public void ban(Admin admin, Client client) {
        adminService.ban(admin, client);
        System.out.printf("Admin %s successfully banned Client %s\n", admin.getPhone(), client.getPhone());
    }

    @Override
    public void unban(Admin admin, Client client) {
        adminService.unban(admin, client);
        System.out.printf("Admin %s successfully unbanned Client %s\n", admin.getPhone(), client.getPhone());
    }

    @Override
    public void takeBooks(Client client, List<Book> books) {
        if (!client.getBanned()) {
            clientService.takeBooks(client, books);
            System.out.printf("Client %s took books %s\n", client.getPhone(), books.toString());
        }
        else System.out.printf("Client %s can't take books because he's banned\n", client.getPhone());
    }

    @Override
    public void returnBooks(Client client, List<Book> books) {
        clientService.returnBooks(client, books);
        System.out.printf("Client %s returned books %s\n", client.getPhone(), books.toString());
    }
}
