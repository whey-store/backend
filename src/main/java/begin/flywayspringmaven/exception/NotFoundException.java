package begin.flywayspringmaven.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends Exception {

    public static final String ERROR_ROLE_NOT_FOUND = "ERROR_ROLE_NOT_FOUND";
    public static final String ERROR_STORE_NOT_FOUND = "ERROR_STORE_NOT_FOUND";
    public static final String ERROR_PRODUCT_NOT_FOUND = "ERROR_PRODUCT_NOT_FOUND";

    private static final long serialVersionUID = 1L;
    private String error;
    private String message;
    private HttpStatus httpStatus;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public NotFoundException(String error, String message, HttpStatus httpStatus) {
        super(message);
        this.error = error;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
