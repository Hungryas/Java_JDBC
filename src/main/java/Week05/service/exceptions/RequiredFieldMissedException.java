package Week05.service.exceptions;

public class RequiredFieldMissedException extends Throwable {
    public RequiredFieldMissedException() {
        super("Required field is missed");
    }

    public RequiredFieldMissedException(String message) {
        super(message);
    }
}
