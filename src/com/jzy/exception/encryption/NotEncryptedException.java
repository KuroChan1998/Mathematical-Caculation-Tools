package com.jzy.exception.encryption;

/**
 * 未在解密前加密的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class NotEncryptedException extends RuntimeException {
    public NotEncryptedException() {
    }

    public NotEncryptedException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
