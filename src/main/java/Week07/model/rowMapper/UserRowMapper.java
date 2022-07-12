package Week07.model.rowMapper;

import Week07.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setPhone(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        return user;
    }
}