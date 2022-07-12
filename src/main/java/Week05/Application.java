package Week05;

import Week05.api.BookingAPIService;
import Week05.api.RoomAPIService;
import Week05.api.impl.BookingAPIServiceImpl;
import Week05.api.impl.RoomAPIServiceImpl;
import Week05.entity.Booking;
import Week05.entity.Guest;
import Week05.entity.Room;
import Week05.entity.RoomType;
import Week05.repository.BookingRepository;
import Week05.repository.RoomRepository;
import Week05.repository.impl.BookingRepositoryImpl;
import Week05.repository.impl.RoomRepositoryImpl;
import Week05.service.BookingService;
import Week05.service.RoomService;
import Week05.service.impl.BookingServiceImpl;
import Week05.service.impl.RoomServiceImpl;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        RoomRepository roomRepository = new RoomRepositoryImpl();
        RoomService roomService = new RoomServiceImpl(roomRepository);
        RoomAPIService roomAPIService = new RoomAPIServiceImpl(roomService);

        BookingRepository bookingRepository = new BookingRepositoryImpl();
        BookingService bookingService = new BookingServiceImpl(bookingRepository, roomService);
        BookingAPIService bookingAPIService = new BookingAPIServiceImpl(bookingService);

        // Тест createRoom
        Room room01 = new Room("01", 0, RoomType.BASEMENT, "Cheap and cheerful", 100);
        roomAPIService.createRoom(room01);
        Room room11 = new Room("11", 1, RoomType.ECONOMY, "Basic option", 200);
        roomAPIService.createRoom(room11);
        Room room21 = new Room("21", 2, RoomType.LUX, "Ocean view", 500);
        roomAPIService.createRoom(room21);
        Room room22 = new Room("22", 2, RoomType.LUX, "Ocean view", 500);
        roomAPIService.createRoom(room22);

        Guest guest1 = new Guest("Milner", "James", "Philip",
                "+44 (999) 999-99-90", "milner@mail.gb");
        Guest guest2 = new Guest("Henderson", "Jordan", "Brian",
                "+44 (999) 999-99-91", "henderson@mail.gb");

        // Тест updateRoom, getRoom
        Room updatingRoom = new Room("22", 2, RoomType.LUX, "Mountain view", 550);
        roomAPIService.updateRoom(room22.getId(), updatingRoom);
        System.out.println(roomAPIService.getRoom(room22.getId()));

        // Тест createBooking
        Booking booking1 = new Booking(room01, guest1, LocalDate.parse("2022-03-22"), LocalDate.parse("2022-03-23"));
        bookingAPIService.createBooking(booking1);
        Booking booking2 = new Booking(room21, guest2, LocalDate.parse("2022-03-22"), LocalDate.parse("2022-03-23"));
        bookingAPIService.createBooking(booking2);

        // Тест updateBooking, getBooking
        booking1.setRoom(room22);
        bookingAPIService.updateBooking(booking1.getId(), booking1);
        System.out.println(bookingAPIService.getBooking(booking1.getId()));

        // deleteBooking
        bookingAPIService.deleteBooking(booking1);

        // Финальный вывод
        System.out.println(bookingRepository.getAll());
        System.out.println(roomRepository.getAll());

        // Тест getRoomsGroupedByType
        System.out.println(roomService.getRoomsGroupByType());
    }
}
