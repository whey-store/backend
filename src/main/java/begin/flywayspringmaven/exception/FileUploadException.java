package begin.flywayspringmaven.exception;

public class FileUploadException extends SpringBootFileUploadException{
    public FileUploadException (String message) {
        super(message);
    }
}
