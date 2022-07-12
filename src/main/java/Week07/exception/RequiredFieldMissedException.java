package Week07.exception;

public class RequiredFieldMissedException extends RuntimeException {
    public RequiredFieldMissedException(String field) {
        super("Field '%s' can't be NULL!".formatted(field));
    }
}
