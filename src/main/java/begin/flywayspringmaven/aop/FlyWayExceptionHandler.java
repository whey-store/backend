package begin.flywayspringmaven.aop;

import begin.flywayspringmaven.common.response.APIResponseError;
import begin.flywayspringmaven.exception.FileInValidException;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FlyWayExceptionHandler {

    /**
     * Handle {@Link NotFoundException}
     *
     * @param e {@Link NotFoundException}
     * @return {@Link ResponseEntity}
     */
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<APIResponseError> handleNotFoundException(NotFoundException e) {
        APIResponseError apiResponseError = APIResponseError.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .error(e.getError())
            .message(e.getMessage())
            .build();
        return new ResponseEntity<>(apiResponseError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle {@Link FileEmptyException}
     *
     * @param e {@Link FileInValidException}
     * @return {@Link ResponseEntity}
     */
    @ExceptionHandler(value = {FileInValidException.class})
    protected ResponseEntity<APIResponseError> handleFileEmptyException(FileInValidException e) {
        APIResponseError apiResponseError = APIResponseError.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .error(e.getError())
            .message(e.getMessage())
            .build();
        return new ResponseEntity<>(apiResponseError, HttpStatus.BAD_REQUEST);
    }
}
