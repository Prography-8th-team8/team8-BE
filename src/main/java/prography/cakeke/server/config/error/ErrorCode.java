package prography.cakeke.server.config.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {
    /*
     * 404 : not found
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않습니다."),
    
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) { // BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}
