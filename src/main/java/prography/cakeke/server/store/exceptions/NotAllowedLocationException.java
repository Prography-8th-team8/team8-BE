package prography.cakeke.server.store.exceptions;

public class NotAllowedLocationException extends RuntimeException {
    public NotAllowedLocationException() {
        super("서비스 불가한 지역입니다.");
    }
}
