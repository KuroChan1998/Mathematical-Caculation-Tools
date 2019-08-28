package com.jzy.xxaqsxjc.encyption.factory;

import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.algorithm.GoldwasserMicaliBinaryEncryption;

/**
 * @ClassName GoldwasserMicaliBinaryEncryptionFactory
 * @Author JinZhiyun
 * @Description GoldwasserMicaliBinary加密类工厂
 * @Date 2019/8/27 19:35
 * @Version 1.0
 **/
public class GoldwasserMicaliBinaryEncryptionFactory implements EncryptionBuilder {
    private volatile static GoldwasserMicaliBinaryEncryption encryption;

    public GoldwasserMicaliBinaryEncryptionFactory() {
    }

    public Encryption getInstance() {
        if (encryption == null) {
            synchronized (PaillierEncryptionFactory.class) {
                if (encryption == null) {
                    encryption = new GoldwasserMicaliBinaryEncryption();
                }
            }
        }
        return encryption;
    }
}
