package Week03.services;

import Week03.entities.Admin;
import Week03.entities.Client;

public interface AdminService {
    void ban(Admin admin, Client client);
    void unban(Admin admin, Client client);
}
