package Week_03.controller.impl;

import Week_03.controller.LibraryAPIService;
import Week_03.entities.Admin;
import Week_03.entities.Book;
import Week_03.entities.Client;
import Week_03.services.AdminService;
import Week_03.services.ClientService;

import java.util.List;

/**
 * Implementation of LibraryAPIService.
 * Service of Library management: ban/unban users, take/return books.
 */
public class LibraryAPIServiceImpl implements LibraryAPIService {
    private final AdminService adminService;
    private final ClientService clientService;

    /**
     * Services for management of clients and admins.
     *
     * @param adminService  service for admins methods.
     * @param clientService service for clients methods.
     */
    public LibraryAPIServiceImpl(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    /**
     * Call AdminService for banning a client by an admin.
     *
     * @param admin  user of Admin group
     * @param client user of Client group
     * @see LibraryAPIServiceImpl#unban(Admin, Client)
     */
    @Override
    public void ban(Admin admin, Client client) {
        adminService.ban(admin, client);
        System.out.printf("Admin %s successfully banned Client %s\n", admin.getPhone(), client.getPhone());
    }

    /**
     * Call AdminService for unbanning a client by an admin.
     *
     * @param admin  user of Admin group
     * @param client user of Client group
     * @see LibraryAPIServiceImpl#ban(Admin, Client)
     */
    @Override
    public void unban(Admin admin, Client client) {
        adminService.unban(admin, client);
        System.out.printf("Admin %s successfully unbanned Client %s\n", admin.getPhone(), client.getPhone());
    }

    /**
     * Call ClientService for taking books if a client isn't banned and print a message if client is banned.
     *
     * @param client client who take books
     * @param books  list of taken books
     * @see LibraryAPIServiceImpl#takeBooks(Client, List)
     */
    @Override
    public void takeBooks(Client client, List<Book> books) {
        if (!client.getBanned()) {
            clientService.takeBooks(client, books);
            System.out.printf("Client %s took books %s\n", client.getPhone(), books.toString());
        } else System.out.printf("Client %s can't take books because he's banned\n", client.getPhone());
    }

    /**
     * Call ClientService for return books by a client.
     *
     * @param client client who take books
     * @param books  list of taken books
     * @see LibraryAPIServiceImpl#returnBooks(Client, List) 
     */
    @Override
    public void returnBooks(Client client, List<Book> books) {
        clientService.returnBooks(client, books);
        System.out.printf("Client %s returned books %s\n", client.getPhone(), books.toString());
    }
}
