package Week05.api.impl;

import Week05.api.BookingAPIService;
import Week05.entity.Booking;
import Week05.service.BookingService;
import Week05.service.exceptions.BookingNotFoundException;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

public record BookingAPIServiceImpl(BookingService bookingService) implements BookingAPIService {

    @Override
    public Booking createBooking(Booking booking) {
        try {
            return bookingService.createBooking(booking);
        } catch (RequiredFieldMissedException | RoomNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking updateBooking(String id, Booking Booking) {
        try {
            return bookingService.updateBooking(id, Booking);
        } catch (RequiredFieldMissedException | BookingNotFoundException | RoomNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking getBooking(String id) {
        try {
            return bookingService.getBy(id);
        } catch (BookingNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBooking(Booking Booking) {
        bookingService.deleteBooking(Booking);
    }
}
