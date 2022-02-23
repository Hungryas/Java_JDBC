package Week_03.entities;

public class Admin extends AbstractUser {
    public Admin(String firstName, String lastName, String middleName, String phone) {
        super(firstName, lastName, middleName, phone);
    }

    public void ban(Client client) {
        client.setBanned(true);
    }

    public void unban(Client client) {
        client.setBanned(false);
    }
}
