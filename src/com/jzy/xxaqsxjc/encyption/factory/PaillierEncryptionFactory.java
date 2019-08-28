package com.jzy.xxaqsxjc.encyption.factory;

import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.algorithm.PaillierEncryption;

/**
 * @ClassName PaillierEncryptionFactory
 * @Author JinZhiyun
 * @Description Paillier加密类工厂
 * @Date 2019/8/27 8:25
 * @Version 1.0
 **/
public class PaillierEncryptionFactory implements EncryptionBuilder {
    private volatile static PaillierEncryption encryption;

    public PaillierEncryptionFactory() {
    }

    public Encryption getInstance() {
        if (encryption == null) {
            synchronized (PaillierEncryptionFactory.class) {
                if (encryption == null) {
                    encryption = new PaillierEncryption();
                }
            }
        }
        return encryption;
    }
}
