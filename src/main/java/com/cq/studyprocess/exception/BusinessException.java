package com.cq.studyprocess.exception;

/**
 * 业务异常
 *
 * @author 程崎
 * @since 2022/08/05
 */
public class BusinessException extends RuntimeException {

    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
