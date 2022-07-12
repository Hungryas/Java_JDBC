package Week07.repository.impl;

import Week07.exception.RequiredFieldMissedException;
import Week07.exception.repository.BookingNotFoundException;
import Week07.model.Booking;
import Week07.model.rowMapper.BookingRowMapper;
import Week07.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookingsRepositoryImpl implements BookingsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Создает в базе сущность Booking.
    @Override
    public Booking create(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings VALUES (?, ?, ?, ?, ?) ON CONFLICT (id) DO " +
                "UPDATE SET check_in=?, check_out=?, user_id=?, room_id=?";
        jdbcTemplate.update(query,
                booking.getId(),
                Timestamp.valueOf(booking.getCheckIn()),
                Timestamp.valueOf(booking.getCheckOut()),
                booking.getUser().getId(),
                booking.getRoom().getId(),
                Timestamp.valueOf(booking.getCheckIn()),
                Timestamp.valueOf(booking.getCheckOut()),
                booking.getUser().getId(),
                booking.getRoom().getId());
        return booking;
    }

    // Обновляет в базе сущность Booking.
    // Сделать все необходимые проверки на то, что id не существует в базе / проч
    @Override
    public Booking update(Booking booking) {
        validate(booking);
        checkExist(booking.getId());
        String query = "UPDATE bookings SET check_in=?, check_out=?, user_id=?, room_id=? WHERE id=?";
        jdbcTemplate.update(query,
                Timestamp.valueOf(booking.getCheckIn()),
                Timestamp.valueOf(booking.getCheckOut()),
                booking.getUser().getId(),
                booking.getRoom().getId(),
                booking.getId());
        return booking;
    }

    // Удаляет из базы сущность по идентификатору.
    // В случае, если сущность не найдена, выкинуть BookingNotFoundException
    @Override
    public void delete(String id) throws SQLException {
        checkExist(id);
        String query = "DELETE FROM bookings WHERE id = ?";
        if (jdbcTemplate.update(query, id) == 0) throw new BookingNotFoundException(id);
    }

    // Возвращает сущность по идентификатору.
    // В случае, если сущность не найдена, выкинуть BookingNotFoundException.
    // Вернуть связанные с бронированием User и Room, поместить их в сущность.
    @Override
    public Booking getBy(String id) throws SQLException {
        String query = "SELECT * FROM bookings WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new BookingRowMapper(jdbcTemplate), id);
        } catch (DataAccessException e) {
            throw new BookingNotFoundException(id, e);
        }
    }

    // Возвращает бронирования с возможностью фильтрации по дате заезда/выезда.
    // Вернуть для каждой сущности связанные с бронированием User и Room.
    @Override
    public List<Booking> getBookings(LocalDateTime checkInFrom, LocalDateTime checkInTo,
                                     LocalDateTime checkOutFrom, LocalDateTime checkOutTo) {
        String query = "SELECT * FROM bookings WHERE check_in BETWEEN ? AND ? AND check_out BETWEEN ? AND ?";
        List<Booking> bookingList = jdbcTemplate.query(query, new BookingRowMapper(jdbcTemplate),
                Timestamp.valueOf(checkInFrom), Timestamp.valueOf(checkInTo),
                Timestamp.valueOf(checkOutFrom), Timestamp.valueOf(checkOutTo));
        if (bookingList.isEmpty()) {
            System.out.println("The search hasn't given any results.");
            return null;
        }
        return bookingList;
    }

    private void validate(Booking booking) {
        if (booking.getId() == null) throw new RequiredFieldMissedException("ID");
        if (booking.getCheckIn() == null) throw new RequiredFieldMissedException("Check In");
        if (booking.getCheckOut() == null) throw new RequiredFieldMissedException("Check Out");
        if (booking.getUser().getId() == null) throw new RequiredFieldMissedException("User ID");
        if (booking.getRoom().getId() == null) throw new RequiredFieldMissedException("Room ID");
    }

    private void checkExist(String id) {
        String checking = "SELECT COUNT(*) FROM bookings WHERE id=?";
        Integer rowNumber = jdbcTemplate.queryForObject(checking, Integer.class, id);
        if (rowNumber != null && rowNumber == 0) {
            throw new BookingNotFoundException(id);
        }
    }
}