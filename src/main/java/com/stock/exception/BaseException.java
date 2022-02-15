package com.stock.exception;

public interface BaseException {
    int BASE_VALID_PARAM = -9;

    String getMessage();

    int getCode();
}
