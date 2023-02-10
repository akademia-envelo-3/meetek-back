package pl.envelo.meetek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleFoundException(NotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleArgumentNotFoundException(ArgumentNotValidException ex) {
        ErrorBody errorBody =   new ErrorBody(ex.getMessage());
        errorBody.setValidationErrors(ex.getViolations());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleDuplicateException(DuplicateException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleProcessingException(ProcessingException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleNotAuthorizedUserException(NotAuthorizedUserException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
    }

}