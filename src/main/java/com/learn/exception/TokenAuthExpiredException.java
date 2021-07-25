package com.learn.exception;

/**
 * token 验证过期异常
 *
 * @author wangxl
 * @date 2021/7/24 17:18
 */
public class TokenAuthExpiredException extends RuntimeException {
    public TokenAuthExpiredException() {
        super();
    }

    public TokenAuthExpiredException(String message) {
        super(message);
    }

    public TokenAuthExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenAuthExpiredException(Throwable cause) {
        super(cause);
    }

    protected TokenAuthExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}