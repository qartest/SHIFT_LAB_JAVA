package qartest.work.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qartest.work.error.exception.NotFound;
import qartest.work.error.exception.SaveProblem;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFound ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumError(HttpMessageNotReadableException ex) {

        if (ex.getCause() instanceof InvalidFormatException) {
            return new ResponseEntity<>(new ErrorResponse("VALIDATION_ERROR"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SaveProblem.class)
    public ResponseEntity<ErrorResponse> saveError(SaveProblem ex) {

        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
