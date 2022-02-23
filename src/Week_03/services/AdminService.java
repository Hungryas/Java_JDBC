package Week_03.services;

import Week_03.entities.Admin;
import Week_03.entities.Client;

public interface AdminService {
    void ban(Admin admin, Client client);

    void unban(Admin admin, Client client);
}
