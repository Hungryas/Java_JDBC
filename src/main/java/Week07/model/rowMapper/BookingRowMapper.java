package Week07.model.rowMapper;

import Week07.model.Booking;
import Week07.model.Room;
import Week07.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRowMapper implements RowMapper<Booking> {
    private JdbcTemplate jdbcTemplate;

    public BookingRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Booking mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        final Booking booking = new Booking();
        booking.setId(resultSet.getString("id"));
        booking.setCheckIn(resultSet.getTimestamp("check_in").toLocalDateTime());
        booking.setCheckOut(resultSet.getTimestamp("check_out").toLocalDateTime());

        String userId = resultSet.getString("user_id");
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new UserRowMapper(), userId);
        booking.setUser(user);

        String roomId = resultSet.getString("room_id");
        Room room = jdbcTemplate.queryForObject("SELECT * FROM rooms WHERE id=?", new RoomRowMapper(), roomId);
        booking.setRoom(room);
        return booking;
    }
}
