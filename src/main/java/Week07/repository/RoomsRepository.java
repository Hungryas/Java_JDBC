package Week07.repository;

import Week07.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomsRepository {
    Room create(Room room) throws SQLException;

    Room update(Room room) throws SQLException ;

    void delete(String id) throws SQLException ;

    Room getBy(String id) throws SQLException ;

    List<Room> getRooms(String roomNumber, Integer floor, String roomType, Integer price) throws SQLException ;
}