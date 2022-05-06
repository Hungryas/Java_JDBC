package Week07.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    private String id;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private List<Booking> bookings = new ArrayList<>();

    public User(String id, String phone, String email, String firstName, String lastName, String middleName) {
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.phone = phone;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
