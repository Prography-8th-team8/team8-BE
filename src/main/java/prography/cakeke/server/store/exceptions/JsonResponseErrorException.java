package prography.cakeke.server.store.exceptions;

public class JsonResponseErrorException extends RuntimeException {
    public JsonResponseErrorException() {super("JSON을 읽어들이는 도중 오류가 발생했습니다.");}
}
