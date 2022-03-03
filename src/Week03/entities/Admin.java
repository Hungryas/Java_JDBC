package Week03.entities;

/**
 * User of Admin group of Library.
 */
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
