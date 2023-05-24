package prography.cakeke.server.config.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;


/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {
    /*
     * 1000 : 요청 성공
     */
    SUCCESS(HttpStatus.OK, 1000, "요청에 성공하였습니다."),

    /*
     * 2000 : Request 오류
     */
    ERROR_EXIST_NAME(HttpStatus.BAD_REQUEST,2000,"중복된 쿠폰이름입니다."),

    /*
     * 3000 : Response 오류
     */
    FAILED_TO_SEARCH_COUPON(HttpStatus.BAD_REQUEST, 3000, "해당하는 쿠폰 아이디가 없습니다."),

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
