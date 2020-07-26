package com.jzy.demo;

import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.PaillierEncryption;
import com.jzy.xxaqsxjc.encyption.algorithm.RSAEncryption;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PaillierEncryptionDemo
 * @description 使用示例：Paillier加密算法 {@link PaillierEncryption}
 * @date 2019/11/9 16:27
 **/
public class PaillierEncryptionDemo {
    private PaillierEncryptionDemo(){}

    public static void main(String[] args) {
        //传入枚举参数RSA，从工厂获得实例
        PaillierEncryption paillier = (PaillierEncryption) EncryptionFactory.getEncryption(EncryptionAlgorithm.Paillier);
        paillier.setPlainText("我是明文");
        paillier.show();

        //重置密钥位宽512bit
        RSAEncryption.resetKeys(512);
        paillier.setPlainText("使用512bit密钥加密，我是明文");
        paillier.show();
    }
}
