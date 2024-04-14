package begin.flywayspringmaven.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BadRequestException extends Exception{
    public static final String ERROR_INVALID_TOKEN = "ERROR_INVALID_TOKEN";

    private static final long serialVersionUID = 1L;


    private String error;
    private String message;
    private boolean isJson;
    @JsonIgnore
    private boolean isPrintStackTrace;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, boolean isJson) {
        super(message);
        this.message = message;
        this.isJson = isJson;
    }


    public BadRequestException(String error, String message, boolean isJson) {
        super(message);
        this.error = error;
        this.message = message;
        this.isJson = isJson;
    }
}
