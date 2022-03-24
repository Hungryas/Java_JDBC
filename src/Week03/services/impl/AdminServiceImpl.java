package Week03.services.impl;

import Week03.entities.Admin;
import Week03.entities.Client;
import Week03.services.AdminService;

public class AdminServiceImpl implements AdminService {
    @Override
    public void ban(Admin admin, Client client) {
        client.setBanned(true);
    }

    @Override
    public void unban(Admin admin, Client client) {
        client.setBanned(false);
    }
}
