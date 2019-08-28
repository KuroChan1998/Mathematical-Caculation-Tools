package com.jzy.xxaqsxjc.encyption.factory;

import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.algorithm.RSAEncryption;

/**
 * @ClassName RSAEncryptionFactory
 * @Author JinZhiyun
 * @Description RSA加密类工厂
 * @Date 2019/8/26 21:51
 * @Version 1.0
 **/
public class RSAEncryptionFactory implements EncryptionBuilder{
    private volatile static RSAEncryption encryption;

    public RSAEncryptionFactory() {
    }

    public Encryption getInstance() {
        if (encryption == null) {
            synchronized (RSAEncryptionFactory.class) {
                if (encryption == null) {
                    encryption = new RSAEncryption();
                }
            }
        }
        return encryption;
    }
}
