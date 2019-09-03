package com.jzy.app;

import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.RSAEncryption;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

public class Test {
    public static void main(String[] args) {
        //传入枚举参数RSA，从工厂获得实例
        RSAEncryption rsa1 = (RSAEncryption) EncryptionFactory.getEncryption(EncryptionAlgorithm.RSA);
        rsa1.setPlainText("我是明文");
        rsa1.show();
        //重置密钥位宽512bit
        RSAEncryption.resetKeys(512);
        rsa1.setPlainText("使用512bit密钥加密，我是明文");
        rsa1.show();
    }
}
