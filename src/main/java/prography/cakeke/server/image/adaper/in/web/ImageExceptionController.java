package prography.cakeke.server.image.adaper.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import prography.cakeke.server.image.exceptions.InvalidFileNameException;
import prography.cakeke.server.image.exceptions.NotSupportedFileFormatException;

@RestControllerAdvice
public class ImageExceptionController {

    @ExceptionHandler(value = InvalidFileNameException.class)
    protected ResponseEntity<String> invalidFileNameException(InvalidFileNameException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = NotSupportedFileFormatException.class)
    protected ResponseEntity<String> notSupportedFileFormatException(
            NotSupportedFileFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
