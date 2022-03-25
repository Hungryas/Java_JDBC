package Week05.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Бронирование.
 */
public class Booking {
    private final String id;
    private Room room;
    private Guest guest;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking(Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        this.id = UUID.randomUUID().toString();
        this.room = room;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(room.getId(), booking.room.getId()) &&
                Objects.equals(guest, booking.guest) &&
                Objects.equals(checkIn, booking.checkIn) &&
                Objects.equals(checkOut, booking.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guest, checkIn, checkOut);
    }

    @Override
    public String toString() {
        return "\nBooking{ID=%s, Room %s, %s, checkIn=%s, checkOut=%s}"
                .formatted(id, room.getRoomNumber(), guest, checkIn, checkOut);
    }
}