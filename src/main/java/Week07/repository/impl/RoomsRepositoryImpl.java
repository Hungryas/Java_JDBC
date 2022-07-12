package Week07.repository.impl;

import Week07.exception.RequiredFieldMissedException;
import Week07.exception.repository.RoomNotFoundException;
import Week07.model.Booking;
import Week07.model.Room;
import Week07.model.rowMapper.RoomRowMapper;
import Week07.repository.BookingsRepository;
import Week07.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomsRepositoryImpl implements RoomsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookingsRepository bookingsRepository;

    // Создает в базе сущность.
    @Override
    public Room create(Room room) throws SQLException {
        validate(room);
        String query = "INSERT INTO rooms VALUES (?, ?, ?, ?, ?, ?)  ON CONFLICT (id) DO " +
                "UPDATE SET room_number=?, floor=?, room_type=?, description=?, price=?";
        jdbcTemplate.update(query,
                room.getId(),
                room.getRoomNumber(),
                room.getFloor(),
                room.getRoomType(),
                room.getDescription(),
                room.getPrice(),
                room.getRoomNumber(),
                room.getFloor(),
                room.getRoomType(),
                room.getDescription(),
                room.getPrice());
        return room;
    }

    // Обновляет в базе сущность.
    // Сделать все необходимые проверки на то, что id не существует в базе / проч
    @Override
    public Room update(Room room) {
        validate(room);
        checkExist(room.getId());
        String query = "UPDATE rooms SET room_number=?, floor=?, room_type=?, description=?, price=? WHERE id=?";
        jdbcTemplate.update(query,
                room.getRoomNumber(),
                room.getFloor(),
                room.getRoomType(),
                room.getDescription(),
                room.getPrice(),
                room.getId());
        return room;
    }

    // Удаляет из базы сущность по идентификатору.
    // В случае, если сущность не найдена, выкинуть RoomNotFoundException
    @Override
    public void delete(String id) throws SQLException {
        String query = "DELETE FROM rooms WHERE id=?";
        if (jdbcTemplate.update(query, id) == 0) throw new RoomNotFoundException(id);
    }

    // Вернуть комнату по id.
    // В случае, если сущность не найдена, выкинуть RoomNotFoundException.
    // Вернуть связанные с комнатой Booking. Поместить их в сущность.
    @Override
    public Room getBy(String id) throws SQLException {
        String query = "SELECT * FROM rooms WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(query, new RoomRowMapper(), id);
        } catch (DataAccessException e) {
            throw new RoomNotFoundException(id, e);
        }
    }

    // Осуществляет фильтрацию по полям в аргументах функции.
    // Для каждой сущности выводить список связанных с ней бронирований.
    @Override
    public List<Room> getRooms(String roomNumber, Integer floor, String roomType, Integer price) throws SQLException {
        String query = "SELECT * FROM rooms WHERE room_number=? AND floor=? AND room_type=? AND price=?";
        List<Room> roomList = jdbcTemplate.query(query, new RoomRowMapper(), roomNumber, floor, roomType, price);
        if (roomList.isEmpty()) {
            System.out.println("The search hasn't given any results.");
            return null;
        }
        for (Room room : roomList) {
            query = "SELECT * FROM bookings WHERE room_id=?";
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query, room.getId());
            List<Booking> bookings = new ArrayList<>();
            while (sqlRowSet.next()) {
                String id = sqlRowSet.getString("id");
                bookings.add(bookingsRepository.getBy(id));
            }
            room.setBookings(bookings);
        }
        return roomList;
    }

    private void validate(Room room) {
        if (room.getId() == null) throw new RequiredFieldMissedException("ID");
        if (room.getRoomNumber() == null) throw new RequiredFieldMissedException("Room Number");
        if (room.getFloor() == null) throw new RequiredFieldMissedException("Floor");
        if (room.getRoomType() == null) throw new RequiredFieldMissedException("Room Type");
        if (room.getPrice() == null) throw new RequiredFieldMissedException("Price");
    }

    private void checkExist (String id) {
        String checking = "SELECT COUNT(*) FROM rooms WHERE id=?";
        Integer rowNumber = jdbcTemplate.queryForObject(checking, Integer.class, id);
        if (rowNumber != null && rowNumber == 0) {
            throw new RoomNotFoundException(id);
        }
    }
}