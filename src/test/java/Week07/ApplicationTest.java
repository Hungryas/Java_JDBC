package Week07;

import Week07.config.PostgreSQLContainerInitializer;
import Week07.exception.repository.BookingNotFoundException;
import Week07.exception.repository.RoomNotFoundException;
import Week07.exception.repository.UserNotFoundException;
import Week07.model.Booking;
import Week07.model.Room;
import Week07.model.User;
import Week07.repository.BookingsRepository;
import Week07.repository.RoomsRepository;
import Week07.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.sql.SQLException;
import java.time.LocalDateTime;

@SpringBootTest
@ContextConfiguration(initializers = {PostgreSQLContainerInitializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApplicationTest {
    private final static String TEST_USER_ID = "cd5b6207-6f7e-4b74-89e4-171eecac3367";
    private final static String TEST_PHONE = "+7(999)999-99-99";
    private final static String TEST_EMAIL = "test@mail.com";
    private final static String TEST_FIRST_NAME = "Ivan";
    private final static String TEST_LAST_NAME = "Ivanov";
    private final static String TEST_MIDDLE_NAME = "Ivanovich";
    private final static User TEST_USER = new User(TEST_USER_ID,
            TEST_PHONE,
            TEST_EMAIL,
            TEST_FIRST_NAME,
            TEST_LAST_NAME,
            TEST_MIDDLE_NAME);

    private final static String TEST_ROOM_ID = "05b38979-9997-4cce-bb39-ec02d8ccf8cf";
    private final static String TEST_ROOM_NUMBER = "01";
    private final static Integer TEST_FLOOR = 1;
    private final static String TEST_ROOM_TYPE = "ECONOMY";
    private final static String TEST_DESCRIPTION = "Basic option";
    private final static Integer TEST_PRICE = 100;
    private final static Room TEST_ROOM = new Room(TEST_ROOM_ID,
            TEST_ROOM_NUMBER,
            TEST_FLOOR,
            TEST_ROOM_TYPE,
            TEST_DESCRIPTION,
            TEST_PRICE);

    private final static String TEST_BOOKING_ID = "76b34827-b201-4b0c-a3a1-75277a088a53";
    private final static LocalDateTime TEST_CHECKIN = LocalDateTime.of(2022, 4, 24, 12, 0);
    private final static LocalDateTime TEST_CHECKOUT = LocalDateTime.of(2022, 4, 25, 10, 40);
    private final static Booking TEST_BOOKING = new Booking(TEST_BOOKING_ID,
            TEST_CHECKIN,
            TEST_CHECKOUT,
            TEST_USER,
            TEST_ROOM);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private BookingsRepository bookingsRepository;


    @Test
    @BeforeEach
    public void shouldCreateEntries() throws SQLException {
        Assertions.assertEquals(TEST_USER, usersRepository.create(TEST_USER));
        Assertions.assertEquals(TEST_ROOM, roomsRepository.create(TEST_ROOM));
        Assertions.assertEquals(TEST_BOOKING, bookingsRepository.create(TEST_BOOKING));
    }

    @Test
    public void shouldUpdateUser() {
        String newPhone = "+7(999)000-00-00";
        TEST_USER.setPhone(newPhone);
        Assertions.assertEquals(newPhone, usersRepository.update(TEST_USER).getPhone());
    }

    @Test
    public void shouldUpdateRoom() throws SQLException {
        String newDescription = "Basic service";
        TEST_ROOM.setDescription(newDescription);
        Assertions.assertEquals(newDescription, roomsRepository.update(TEST_ROOM).getDescription());
    }

    @Test
    public void shouldUpdateBooking() throws SQLException {
        LocalDateTime newCheckIn = TEST_CHECKIN.minusHours(12);
        TEST_BOOKING.setCheckIn(newCheckIn);
        Assertions.assertEquals(newCheckIn, bookingsRepository.update(TEST_BOOKING).getCheckIn());
    }

    @Test
    public void shouldGetObjectLists() throws SQLException {
        Assertions.assertNotNull(usersRepository.getUsers(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MIDDLE_NAME));
        Assertions.assertNotNull(roomsRepository.getRooms(TEST_ROOM_NUMBER, TEST_FLOOR, TEST_ROOM_TYPE, TEST_PRICE));
        Assertions.assertNotNull(bookingsRepository.getBookings(
                TEST_CHECKIN.minusDays(1), TEST_CHECKIN.plusDays(1),
                TEST_CHECKOUT.minusDays(1), TEST_CHECKOUT.plusDays(1)));
    }

    @Test
    @AfterTestClass
    public void shouldDeleteObjects() throws SQLException {
        bookingsRepository.delete(TEST_BOOKING_ID);
        Assertions.assertThrows(BookingNotFoundException.class, () -> bookingsRepository.getBy(TEST_BOOKING_ID));
        roomsRepository.delete(TEST_ROOM_ID);
        Assertions.assertThrows(RoomNotFoundException.class, () -> roomsRepository.getBy(TEST_ROOM_ID));
        usersRepository.delete(TEST_USER_ID);
        Assertions.assertThrows(UserNotFoundException.class, () -> usersRepository.getBy(TEST_USER_ID));
    }
}