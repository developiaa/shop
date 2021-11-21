package pro.developia.shop.core;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum Code {
    SUCCESS(0, HttpStatus.OK, (String) null),
    BAD_REQUEST(1, HttpStatus.BAD_REQUEST, "common.error.bad_request"),
    ILLEGAL_STATE(2, HttpStatus.BAD_REQUEST, "common.error_illegal_state"),
    UNAUTHENTICATED(3, HttpStatus.UNAUTHORIZED, (String)null),
    UNAUTHORIZED(4, HttpStatus.FORBIDDEN, (String)null),
    NOT_FOUND(5, HttpStatus.NOT_FOUND, (String)null),
    TIMEOUT(990, HttpStatus.REQUEST_TIMEOUT, (String)null),
    ERROR(999, HttpStatus.INTERNAL_SERVER_ERROR, "common.error.server_error");


    private int id;
    private HttpStatus httpStatus;
    private String defaultMessageCode;

    Code(int id, HttpStatus httpStatus, String defaultMessageCode) {
        this.id = id;
        this.httpStatus = httpStatus;
        this.defaultMessageCode = defaultMessageCode;
    }
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @JsonValue
    public int getId() {
        return this.id;
    }
}
