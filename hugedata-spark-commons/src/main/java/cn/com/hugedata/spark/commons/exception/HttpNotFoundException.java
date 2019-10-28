package cn.com.hugedata.spark.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HttpNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3902455882633510699L;

    public HttpNotFoundException() {
        super();
    }

    public HttpNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpNotFoundException(String message) {
        super(message);
    }

    public HttpNotFoundException(Throwable cause) {
        super(cause);
    }

}
