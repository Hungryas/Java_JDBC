package Week07.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Booking {
    private String id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private User user;
    private Room room;

    public Booking(String id, LocalDateTime checkIn, LocalDateTime checkOut, User user, Room room) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.user = user;
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id != null && Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
