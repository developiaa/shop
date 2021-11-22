package pro.developia.shop.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(email + "NotFoundException");
    }
}
