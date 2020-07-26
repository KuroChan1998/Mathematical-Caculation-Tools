package com.jzy.demo;

import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.GoldwasserMicaliBinaryEncryption;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName GoldwasserMicaliBinaryEncryptionDemo
 * @description 使用示例：GoldwasserMicali加密算法（对二进制加密） {@link GoldwasserMicaliBinaryEncryption}
 * @date 2019/11/9 16:14
 **/
public class GoldwasserMicaliBinaryEncryptionDemo {
    private GoldwasserMicaliBinaryEncryptionDemo(){}

    public static void main(String[] args) {
        //传入枚举参数RSA，从工厂获得实例
        GoldwasserMicaliBinaryEncryption gmb = (GoldwasserMicaliBinaryEncryption) EncryptionFactory.getEncryption(EncryptionAlgorithm.GoldwasserMicali);
        gmb.setPlainText("1010101111");
        gmb.show();

        //重置密钥位宽512bit
        GoldwasserMicaliBinaryEncryption.resetKeys(512);
        gmb.setPlainText("10101011111010101111");
        gmb.show();
    }

}
