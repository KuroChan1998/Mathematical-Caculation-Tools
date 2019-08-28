package com.jzy.exception.encryption;

/**
 * @ClassName NotEncrytedExcetion
 * @Author JinZhiyun
 * @Description 未在解密前加密异常
 * @Date 2019/8/26 21:18
 * @Version 1.0
 **/
public class NotEncrytedExcetion extends RuntimeException{
    public NotEncrytedExcetion() {
    }

    public NotEncrytedExcetion(String message) {
        super(message);

    }
}
