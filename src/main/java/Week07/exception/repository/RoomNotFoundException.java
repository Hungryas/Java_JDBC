package Week07.exception.repository;

public class RoomNotFoundException extends RepositoryException {
    public RoomNotFoundException(String id) {
        this(id, null);
    }

    public RoomNotFoundException(String id, Throwable cause) {
        super("Room with ID %s is not found!".formatted(id), cause);
    }
}
