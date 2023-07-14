package prography.cakeke.server.store.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import prography.cakeke.server.store.exceptions.NotAllowedLocationException;
import prography.cakeke.server.store.exceptions.NotFoundStoreException;
import prography.cakeke.server.store.exceptions.NotFoundStoreTagException;

@RestControllerAdvice
public class StoreExceptionController {

    @ExceptionHandler(value = NotFoundStoreException.class)
    protected ResponseEntity<String> notFoundStoreException(NotFoundStoreException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = NotAllowedLocationException.class)
    protected ResponseEntity<String> notAllowedLocationException(NotAllowedLocationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = NotFoundStoreTagException.class)
    protected ResponseEntity<String> notFoundStoreTagException(NotFoundStoreTagException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}