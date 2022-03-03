package Week04.entities;

import java.util.UUID;

public class User {
    private final String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private UserStatus status;

    public User(String firstName, String lastName, String middleName, String phone, String email) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.status = UserStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User %s:\nname=%s %s %s\nphone=%s\nemail=%s\nstatus=%s\n".formatted(id,
                lastName,
                firstName,
                middleName,
                phone,
                email,
                status);
    }
}