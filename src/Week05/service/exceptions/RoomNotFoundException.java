package Week05.service.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Room with specified ID is not found");
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
