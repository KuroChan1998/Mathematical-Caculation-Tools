package com.jzy.exception.integer;

/**
 * 输入安全参数数不为正的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class SecurityParameterNonPositiveException extends InputNonPositiveException {
    public SecurityParameterNonPositiveException() {
        super();
    }

    public SecurityParameterNonPositiveException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
