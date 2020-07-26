package com.jzy.exception.encryption;

import com.jzy.exception.InputException;

/**
 * 输入串没有仅仅含有0,1的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class NotBinaryStringException extends InputException {
    public NotBinaryStringException() {
        super();
    }

    public NotBinaryStringException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
