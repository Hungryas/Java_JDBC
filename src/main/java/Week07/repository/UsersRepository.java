package Week07.repository;

import Week07.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersRepository {
    User create(User user);

    User update(User user);

    void delete(String id);

    User getBy(String id);

    List<User> getUsers(String firstName, String lastName, String middleName) throws SQLException;
}
