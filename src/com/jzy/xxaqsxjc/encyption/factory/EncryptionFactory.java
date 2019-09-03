package com.jzy.xxaqsxjc.encyption.factory;

import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;

/**
 * @ClassName EncryptionFactory
 * @Author JinZhiyun
 * @Description 加密类的工厂
 * @Date 2019/8/27 8:22
 * @Version 1.0
 **/
public class EncryptionFactory {
    /**
     * @return com.jzy.xxaqsxjc.encyption.BinaryEncryptionExecutor
     * @Author JinZhiyun
     * @Description 获得加密类实例
     * @Date 18:30 2019/8/27
     * @Param [encyption]
     **/
    public static Encryption getEncryption(EncryptionAlgorithm encryptionAlgorithm) {
        switch (encryptionAlgorithm) {
            case RSA:
                return new RSAEncryptionFactory().getInstance();
            case Paillier:
                return new PaillierEncryptionFactory().getInstance();
            case GoldwasserMicali:
                return new GoldwasserMicaliBinaryEncryptionFactory().getInstance();
            default:
                return null;
        }
    }
}
