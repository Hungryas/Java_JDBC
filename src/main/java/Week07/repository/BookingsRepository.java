package Week07.repository;

import Week07.model.Booking;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingsRepository {
    Booking create(Booking booking) throws SQLException;

    Booking update(Booking booking) throws SQLException;

    void delete(String id) throws SQLException ;

    Booking getBy(String id) throws SQLException;

    List<Booking> getBookings(LocalDateTime checkInFrom, LocalDateTime checkInTo,
                              LocalDateTime checkOutFrom, LocalDateTime checkOutTo) throws SQLException ;
}
