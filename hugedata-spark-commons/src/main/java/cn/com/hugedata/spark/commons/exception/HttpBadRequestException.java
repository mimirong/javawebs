package cn.com.hugedata.spark.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HttpBadRequestException extends RuntimeException {

    private static final long serialVersionUID = -3902455882633510699L;

    public HttpBadRequestException() {
        super();
    }

    public HttpBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpBadRequestException(String message) {
        super(message);
    }

    public HttpBadRequestException(Throwable cause) {
        super(cause);
    }

}
