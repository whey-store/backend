package begin.flywayspringmaven.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private String error;
    private String message;
    private HttpStatus httpStatus;

    public static final String ERROR_ROLE_NOT_FOUND = "ERROR_ROLE_NOT_FOUND";

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
