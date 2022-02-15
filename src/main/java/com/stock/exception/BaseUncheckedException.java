package com.stock.exception;

import cn.hutool.core.util.StrUtil;

public class BaseUncheckedException extends RuntimeException implements BaseException{
    private static final long serialVersionUID = -778887391066124051L;
    private String message;
    private int code;

    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    public BaseUncheckedException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseUncheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String format, Object... args) {
        super(StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args));
        this.code = code;
        this.message = StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args);
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
