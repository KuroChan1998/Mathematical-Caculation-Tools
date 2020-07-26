package com.jzy.demo;

import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.RSAEncryption;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName RSAEncryptionDemo
 * @description 使用示例：实现RSA加密算法 {@link RSAEncryption}
 * @date 2019/11/9 16:14
 **/
public class RSAEncryptionDemo {
    private RSAEncryptionDemo(){}

    public static void main(String[] args) {
        //传入枚举参数RSA，从工厂获得实例
        RSAEncryption rsa1 = (RSAEncryption) EncryptionFactory.getEncryption(EncryptionAlgorithm.RSA);
        rsa1.setPlainText("我是明文");
//        System.out.println(rsa1.encrypt());  //加密得到密文
//        System.out.println(rsa1.decrypt());  //解密得到明文
        //这里通过show()方法直观展示加密情况，也可以使用encrypt()单独输出密文等等
        rsa1.show();

        //重置密钥位宽512bit
        RSAEncryption.resetKeys(512);
        rsa1.setPlainText("使用512bit密钥加密，我是明文");
        rsa1.show();
    }
}
