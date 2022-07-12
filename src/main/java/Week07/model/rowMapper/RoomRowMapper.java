package Week07.model.rowMapper;

import Week07.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Room room = new Room();
        room.setId(resultSet.getString("id"));
        room.setRoomNumber(resultSet.getString("room_number"));
        room.setFloor(resultSet.getInt("floor"));
        room.setRoomType(resultSet.getString("room_type"));
        room.setDescription(resultSet.getString("description"));
        room.setPrice(resultSet.getInt("price"));
        return room;
    }
}