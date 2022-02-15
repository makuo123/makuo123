package com.stock.exception;

public class BizException extends BaseUncheckedException{

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(int code, Throwable cause) {
        super(code, cause);
    }

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizException(int code, String format, Object... args) {
        super(code, format, args);
    }
}
