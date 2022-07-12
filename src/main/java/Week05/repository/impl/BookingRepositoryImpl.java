package Week05.repository.impl;

import Week05.entity.Booking;
import Week05.repository.BookingRepository;

import java.util.HashSet;
import java.util.Set;

public class BookingRepositoryImpl implements BookingRepository {
    private static final Set<Booking> bookings = new HashSet<>();

    public BookingRepositoryImpl() {
    }

    // аналогично RoomRepository, создает новое бронирование, если такого id нет и
    // обновляет, если бронирование с таким идентификатором существует
    @Override
    public Booking save(Booking booking) {
        bookings.remove(booking);
        bookings.add(booking);
        System.out.printf("Booking with ID %s is saved in repository.\n", booking.getId());
        return booking;
    }

    // удаляет бронирование из поля bookings, если такого нет -- ничего не происходит
    @Override
    public void delete(Booking booking) {
        if (bookings.remove(booking)) {
            System.out.printf("Booking with ID %s is deleted from repository.\n", booking.getId());
        }
    }

    // возвращает бронирование по идентификатору, если такого нет -- вернуть null
    @Override
    public Booking getBy(String id) {
        for (Booking booking : bookings)
            if (booking.getId().equals(id)) return booking;
        return null;
    }

    // возвращает все бронирования
    public Set<Booking> getAll() {
        return bookings;
    }
}
