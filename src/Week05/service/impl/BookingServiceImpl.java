package Week05.service.impl;

import Week05.entity.Booking;
import Week05.entity.Room;
import Week05.repository.BookingRepository;
import Week05.service.BookingService;
import Week05.service.RoomService;
import Week05.service.exceptions.BookingNotFoundException;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

import java.util.List;
import java.util.Set;

public record BookingServiceImpl(BookingRepository bookingRepository,
                                 RoomService roomService) implements BookingService {

    // получить бронирование по идентификатору, если не найдено -- выкинуть BookingNotFoundException
    @Override
    public Booking getBy(String id)
            throws BookingNotFoundException {
        for (Booking booking : bookingRepository.getAll()) {
            if (booking.getId().equals(id)) return booking;
        }
        throw new BookingNotFoundException();
    }

    // здесь нужно с помощью bookingRepository создать бронирование
    // проверить, что checkIn, checkOut, guest и room != null -- иначе выкинуть RequiredFieldMissedException
    // проверить, что переданная комната существует -- иначе выкинуть RoomNotFoundException
    // обновить комнату, переданную в запросе на создание бронирования, добавив это самое
    // бронирование в поле bookings комнаты
    @Override
    public Booking createBooking(Booking booking)
            throws RequiredFieldMissedException, RoomNotFoundException {
        if (booking.getCheckIn() == null) throw new RequiredFieldMissedException("Field \"CheckIn Date\" is missed");
        if (booking.getCheckOut() == null) throw new RequiredFieldMissedException("Field \"CheckOut Date\" is missed");
        if (booking.getGuest() == null) throw new RequiredFieldMissedException("Field \"Guest\" is missed");
        if (booking.getRoom() == null) throw new RequiredFieldMissedException("Field \"Room\" is missed");
        bookingRepository.save(booking);
        if (booking.getRoom() == null) throw new RoomNotFoundException();
        booking.getRoom().addBooking(booking);
        System.out.printf("Booking with ID %s is created for Room %s.\n", booking.getId(), booking.getRoom().getRoomNumber());
        return booking;
    }

    // проверить, что заполнено поле id -- иначе выкинуть RequiredFieldMissedException
    // проверить, что такое бронирование существует -- иначе выкинуть BookingNotFoundException
    // обновить с помощью bookingRepository данные бронирования
    // если переданное поле room не равно null, проверить, что комната с таким id существует --
    // иначе выкинуть RoomNotFoundException
    // обновить данные комнат, удалив бронирование из прежней комнаты и поместив его в новую
    @Override
    public Booking updateBooking(String id, Booking booking)
            throws RequiredFieldMissedException, BookingNotFoundException, RoomNotFoundException {
        if (bookingRepository.getBy(id) == null) throw new BookingNotFoundException();
        if (booking.getId() == null) throw new RequiredFieldMissedException("Field \"Booking ID\" is missed");
        Room newRoom = bookingRepository.getBy(id).getRoom();
        if (booking.getRoom() != null && roomService.getBy(newRoom.getId()) == null)
            throw new RoomNotFoundException();
        // удаляем бронирование из комнаты
        search:
        for (List<Room> typeList : roomService.getRoomsGroupByType().values()) {
            for (Room room : typeList) {
                if (!room.getBookings().isEmpty()) {
                    for (Booking in : room.getBookings()) {
                        if (in.equals(booking)) {

                            Set<Booking> newBookings = room.getBookings();
                            newBookings.remove(booking);
                            room.setBookings(newBookings);
                            break search;
                        }
                    }
                }
            }
        }
        // добавляем бронирование в другую
        newRoom.addBooking(booking);
        System.out.printf("Booking with ID %s is moved to Room %s.\n", booking.getId(), newRoom.getRoomNumber());
        return booking;
    }

    // удалить бронирование с помощью bookingRepository из комнаты в которой оно было
    @Override
    public void deleteBooking(Booking booking) {
        String bookingId = booking.getId();
        bookingRepository.getBy(bookingId).getRoom().removeBooking(booking);
        bookingRepository.delete(booking);
    }
}