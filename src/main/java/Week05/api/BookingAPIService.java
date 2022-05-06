package Week05.api;

import Week05.entity.Booking;

public interface BookingAPIService {
    Booking createBooking(Booking booking);
    Booking updateBooking(String id, Booking Booking);
    Booking getBooking(String id);
    void deleteBooking(Booking Booking);
}