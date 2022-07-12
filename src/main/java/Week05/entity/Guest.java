package Week05.entity;

import java.util.Objects;

/**
 * Гость.
 */
public class Guest {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;

    public Guest(String lastName, String firstName, String middleName, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(lastName, guest.lastName)
                && Objects.equals(firstName, guest.firstName)
                && Objects.equals(middleName, guest.middleName)
                && Objects.equals(phone, guest.phone)
                && Objects.equals(email, guest.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, phone, email);
    }

    @Override
    public String toString() {
        return "Guest{%s %s %s, phone='%s', email='%s'}"
                .formatted(lastName, firstName, middleName, phone, email);
    }
}
