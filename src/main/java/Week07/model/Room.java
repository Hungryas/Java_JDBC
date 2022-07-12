package Week07.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Room {
    private String id;
    private String roomNumber;
    private Integer floor;
    private String roomType;
    private String description;
    private Integer price;
    private List<Booking> bookings = new ArrayList<>();

    public Room(String id, String roomNumber, Integer floor, String roomType, String description, Integer price) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = roomType;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id != null && Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
