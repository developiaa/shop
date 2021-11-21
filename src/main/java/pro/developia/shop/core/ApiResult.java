package pro.developia.shop.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResult<T> {

    @JsonIgnore
    private Code code;

    @JsonProperty("m")
    private T message;

    @JsonProperty("d")
    private T data;

    @JsonProperty("ts")
    private long timestamp;


    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResult<T> code(@NotNull Code code) {
        this.code = code;
        return this;
    }

    public ApiResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResult<T> message(T message) {
        this.message = message;
        return this;
    }

    public ApiResult<T> httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public static <T> ApiResult<T> ok() {
        return ok(null, null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return ok(data, (T) null);
    }

    public static <T> ApiResult<T> ok(T data, T message) {
        return with(data).code(Code.SUCCESS).message(message).httpStatus(HttpStatus.OK);
    }

    private static <T> ApiResult<T> with(T data) {
        ApiResult<T> response = new ApiResult<>();
        response.data = data;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public static <T> ApiResult<T> fail(@NotNull Code code) {
        return with((T) null).code(code).httpStatus(code.getHttpStatus());
    }

    public static <T> ApiResult<T> fail(@NotNull Code code, T message) {
        return with((T) null).code(code).message(message).httpStatus(code.getHttpStatus());
    }

    public static <T> ApiResult<T> fail(Code code, T message, HttpStatus httpStatus) {
        return with((T) null).code(code).message(message).httpStatus(httpStatus);
    }


}
