package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.encyption.executor.BinaryEncryptionExecutor;

import java.math.BigInteger;
import java.util.Vector;

/**
 * @ClassName BinaryEncryption
 * @Author JinZhiyun
 * @Description 二进制加密类
 * @Date 2019/8/27 18:55
 * @Version 1.0
 **/
public abstract class BinaryEncryption extends Encryption implements BinaryEncryptionExecutor {
    protected Vector<BigInteger> cipherText = new Vector<>();// 存储密文

    public Vector<BigInteger> getCipherText() {
        return cipherText;
    }

    /**
     * @return
     * @Author JinZhiyun
     * @Description 默认构造器
     * @Date 21:10 2019/8/26
     * @Param []
     **/
    public BinaryEncryption() {
        this.plainText = "";
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
    }

    /**
     * @throws
     * @Title: RSAEncryption
     * @Description: 构造器完成入参为明文plainText
     * @param: @param plainText
     */
    public BinaryEncryption(String plainText) {
        this.plainText = plainText;
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
    }

    /**
     * @Author JinZhiyun
     * @Description 构造器完成入参
     * @Date 19:36 2019/8/27
     * @Param [plainText, cipherText, decryptedPlainText]
     * @return
     **/
    public BinaryEncryption(String plainText, Vector<BigInteger> cipherText, String decryptedPlainText) {
        this.plainText = plainText;
        this.cipherText = cipherText;
        this.decryptedPlainText = decryptedPlainText;
    }

    /**
     * @Author JinZhiyun
     * @Description 加密返回密文（大数向量形式）
     * @Date 19:10 2019/8/27
     * @Param []
     * @return java.util.Vector<java.math.BigInteger>
     **/
    public abstract Vector<BigInteger> encrypt();
}
