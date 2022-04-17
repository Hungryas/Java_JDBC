package Week05.service.impl;

import Week05.entity.Booking;
import Week05.entity.Room;
import Week05.repository.BookingRepository;
import Week05.service.BookingService;
import Week05.service.RoomService;
import Week05.service.exceptions.BookingNotFoundException;
import Week05.service.exceptions.RequiredFieldMissedException;
import Week05.service.exceptions.RoomNotFoundException;

public record BookingServiceImpl(BookingRepository bookingRepository,
                                 RoomService roomService) implements BookingService {

    // получить бронирование по идентификатору, если не найдено -- выкинуть BookingNotFoundException
    @Override
    public Booking getBy(String id) {
        return bookingRepository.getAll().stream().filter(booking -> booking.getId().equals(id)).findAny().
                orElseThrow(BookingNotFoundException::new);
    }

    // здесь нужно с помощью bookingRepository создать бронирование
    // проверить, что checkIn, checkOut, guest и room != null -- иначе выкинуть RequiredFieldMissedException
    // проверить, что переданная комната существует -- иначе выкинуть RoomNotFoundException
    // обновить комнату, переданную в запросе на создание бронирования, добавив это самое
    // бронирование в поле bookings комнаты
    @Override
    public Booking createBooking(Booking booking) throws RequiredFieldMissedException {
        validateBooking(booking);
        bookingRepository.save(booking);
        booking.getRoom().addBooking(booking);
        System.out.printf("Booking with ID %s is created for Room %s.\n",
                booking.getId(), booking.getRoom().getRoomNumber());
        return booking;
    }

    // проверить, что заполнено поле id -- иначе выкинуть RequiredFieldMissedException
    // проверить, что такое бронирование существует -- иначе выкинуть BookingNotFoundException
    // обновить с помощью bookingRepository данные бронирования
    // если переданное поле room не равно null, проверить, что комната с таким id существует --
    // иначе выкинуть RoomNotFoundException
    // обновить данные комнат, удалив бронирование из прежней комнаты и поместив его в новую
    @Override
    public Booking updateBooking(String id, Booking booking) throws RequiredFieldMissedException {
        validateBooking(booking);
        if (bookingRepository.getBy(id) == null) throw new BookingNotFoundException();
        // удаляем бронирование из комнаты
        Room oldRoom = roomService.getRoomsGroupByType().values().stream()
                .map(list -> list.get(0)).filter(room -> room.getBookings().contains(booking))
                .findFirst().orElseThrow(BookingNotFoundException::new);
        oldRoom.removeBooking(booking);
        // добавляем бронирование в другую
        Room newRoom = bookingRepository.getBy(id).getRoom();
        newRoom.addBooking(booking);
        System.out.printf("Booking with ID %s is moved to Room %s.\n", booking.getId(), newRoom.getRoomNumber());
        return booking;
    }

    // удалить бронирование с помощью bookingRepository из комнаты, в которой оно было
    @Override
    public void deleteBooking(Booking booking) {
        String bookingId = booking.getId();
        bookingRepository.getBy(bookingId).getRoom().removeBooking(booking);
        bookingRepository.delete(booking);
    }

    // проверка полей и существования комнаты
    private void validateBooking(Booking booking) throws RequiredFieldMissedException {
        if (booking.getId() == null) throw new RequiredFieldMissedException("Field \"Booking ID\" is missed");
        if (booking.getRoom() == null) throw new RequiredFieldMissedException("Field \"Room\" is missed");
        if (booking.getGuest() == null) throw new RequiredFieldMissedException("Field \"Guest\" is missed");
        if (booking.getCheckIn() == null) throw new RequiredFieldMissedException("Field \"CheckIn Date\" is missed");
        if (booking.getCheckOut() == null) throw new RequiredFieldMissedException("Field \"CheckOut Date\" is missed");
        if (roomService.getBy(booking.getRoom().getId()) == null) throw new RoomNotFoundException();
    }
}