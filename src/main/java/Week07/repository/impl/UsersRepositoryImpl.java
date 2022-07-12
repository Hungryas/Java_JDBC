package Week07.repository.impl;

import Week07.exception.RequiredFieldMissedException;
import Week07.exception.repository.UserNotFoundException;
import Week07.model.Booking;
import Week07.model.User;
import Week07.model.rowMapper.UserRowMapper;
import Week07.repository.BookingsRepository;
import Week07.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookingsRepository bookingsRepository;

    // Создает пользователя.
    @Override
    public User create(User user) {
        String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)  ON CONFLICT (id) DO " +
                "UPDATE SET phone=?, email=?, first_name=?, last_name=?, middle_name=?";
        jdbcTemplate.update(query,
                user.getId(),
                user.getPhone(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhone(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName());
        return user;
    }

    // Обновляет пользователя.
    // Сделать все необходимые проверки на то, что id не существует в базе / проч
    @Override
    public User update(User user) {
        validate(user);
        checkExist(user.getId());
        String query = "UPDATE users SET phone=?, email=?, first_name=?, last_name=?, middle_name=? WHERE id=?";
        jdbcTemplate.update(query,
                user.getPhone(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getId());
        return user;
    }

    // Удаляет пользователя по id.
    // В случае, если сущность не найдена, выкинуть UserNotFoundException
    @Override
    public void delete(String id) {
        String query = "DELETE FROM users WHERE id=?";
        if (jdbcTemplate.update(query, id) == 0) throw new UserNotFoundException(id);
    }

    // Возвращает пользователя по id.
    // В случае, если сущность не найдена, выкинуть UserNotFoundException.
    // Вернуть связанные с пользователем Booking. Поместить их в сущность.
    @Override
    public User getBy(String id) {
        String query = "SELECT * FROM users WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
        } catch (DataAccessException e) {
            throw new UserNotFoundException(id, e);
        }
    }

    // Возвращает пользователей соответствующих переданным параметрам.
    // Для каждой сущности выводить список связанных с ней бронирований.
    @Override
    public List<User> getUsers(String firstName, String lastName, String middleName) throws SQLException {
        String query = "SELECT * FROM users WHERE first_name=? AND last_name=? AND middle_name=?";
        List<User> userList = jdbcTemplate.query(query, new UserRowMapper(), firstName, lastName, middleName);
        if (userList.isEmpty()) {
            System.out.println("The search hasn't given any results.");
            return null;
        }
        for (User user : userList) {
            query = "SELECT * FROM bookings WHERE user_id=?";
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query, user.getId());
            List<Booking> bookings = new ArrayList<>();
            while (sqlRowSet.next()) {
                String id = sqlRowSet.getString("id");
                bookings.add(bookingsRepository.getBy(id));
            }
            user.setBookings(bookings);
        }
        return userList;
    }

    private void validate(User user) {
        if (user.getId() == null) throw new RequiredFieldMissedException("ID");
        if (user.getPhone() == null) throw new RequiredFieldMissedException("Phone");
        if (user.getEmail() == null) throw new RequiredFieldMissedException("Email");
        if (user.getFirstName() == null) throw new RequiredFieldMissedException("First Name");
        if (user.getLastName() == null) throw new RequiredFieldMissedException("Last Name");
    }

    private void checkExist (String id) {
        String checking = "SELECT COUNT(*) FROM users WHERE id=?";
        Integer rowNumber = jdbcTemplate.queryForObject(checking, Integer.class, id);
        if (rowNumber != null && rowNumber == 0) {
            throw new UserNotFoundException(id);
        }
    }
}