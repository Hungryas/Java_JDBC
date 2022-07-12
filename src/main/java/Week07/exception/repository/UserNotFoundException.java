package Week07.exception.repository;

public class UserNotFoundException extends RepositoryException {
    public UserNotFoundException(String id) {
        this(id, null);
    }

    public UserNotFoundException(String id, Throwable cause) {
        super("User with ID %s is not found!".formatted(id), cause);
    }
}