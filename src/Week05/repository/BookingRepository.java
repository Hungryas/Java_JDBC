package Week05.repository;

import Week05.entity.Booking;

import java.util.Set;

/**
 * Репозиторий бронирований.
 */
public interface BookingRepository {
    Booking save(Booking booking);
    void delete(Booking booking);
    Booking getBy(String id);
    Set<Booking> getAll();
}
