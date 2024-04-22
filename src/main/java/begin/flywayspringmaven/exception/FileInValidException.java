package begin.flywayspringmaven.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Setter
public class FileInValidException extends Exception {

    public static final String ERROR_FILE_IN_VALID = "ERROR_FILE_IN_VALID";

    private static final long serialVersionUID = 1L;
    private String error;
    private String message;
    private HttpStatus httpStatus;

    public FileInValidException() {
        super();
    }

    public FileInValidException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public FileInValidException(String error, String message, HttpStatus httpStatus) {
        super(message);
        this.error = error;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
