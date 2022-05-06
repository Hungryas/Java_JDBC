package Week05.service.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking with specified ID is not found");
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}
