package prography.cakeke.server.store.exceptions;

public class NotFoundStoreException extends RuntimeException {
    public NotFoundStoreException() {
        super("해당하는 가게가 없습니다.");
    }
}
