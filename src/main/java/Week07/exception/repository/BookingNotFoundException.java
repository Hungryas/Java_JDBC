package Week07.exception.repository;

public class BookingNotFoundException extends RepositoryException {
    public BookingNotFoundException(String id) {
        this(id, null);
    }

    public BookingNotFoundException(String id, Throwable cause) {
        super("Booking with ID %s is not found!".formatted(id), cause);
    }
}
