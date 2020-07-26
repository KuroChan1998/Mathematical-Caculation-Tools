package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * 输入大数不为正的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class InputNonPositiveException extends InputException {
    public InputNonPositiveException() {
        super();
    }

    public InputNonPositiveException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
