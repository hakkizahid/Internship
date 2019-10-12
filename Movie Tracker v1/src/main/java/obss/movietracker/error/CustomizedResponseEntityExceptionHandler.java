package obss.movietracker.error;

import obss.movietracker.error.exception.ConflictError;
import obss.movietracker.error.exception.BadRequestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //Field Empty Error
    @ExceptionHandler(BadRequestError.class)
    public final ResponseEntity<ExceptionResponse> fieldEmptyException(BadRequestError ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    //Conflict Error
    @ExceptionHandler(ConflictError.class)
    public final ResponseEntity<ExceptionResponse> conflictException(ConflictError ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),HttpStatus.CONFLICT.getReasonPhrase());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

}
