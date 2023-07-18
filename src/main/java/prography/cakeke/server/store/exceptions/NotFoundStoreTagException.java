package prography.cakeke.server.store.exceptions;

public class NotFoundStoreTagException extends RuntimeException {
    public NotFoundStoreTagException() {
        super("해당하는 가게 태그가 없습니다.");
    }
}
