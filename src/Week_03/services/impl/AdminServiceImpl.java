package Week_03.services.impl;

import Week_03.entities.Admin;
import Week_03.entities.Client;
import Week_03.services.AdminService;

public class AdminServiceImpl implements AdminService {
    @Override
    public void ban(Admin admin, Client client) {
        admin.ban(client);
    }

    @Override
    public void unban(Admin admin, Client client) {
        admin.unban(client);
    }
}
