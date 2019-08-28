package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.encyption.executor.CommonEncryptExecutor;

/**
 * @ClassName BinaryEncryptionExecutor
 * @Author JinZhiyun
 * @Description 通用加密类
 * @Date 2019/8/26 18:34
 * @Version 1.0
 **/
public abstract class CommonEncryption extends Encryption implements CommonEncryptExecutor {
    protected String cipherText; // 密文

    public String getCipherText() {
        return cipherText;
    }

    /**
     * @return
     * @Author JinZhiyun
     * @Description 默认构造器
     * @Date 21:10 2019/8/26
     * @Param []
     **/
    public CommonEncryption() {
        this.plainText = "";
        this.cipherText = "";
        this.decryptedPlainText = "";
    }

    /**
     * @throws
     * @Title: RSAEncryption
     * @Description: 构造器完成入参为明文plainText
     * @param: @param plainText
     */
    public CommonEncryption(String plainText) {
        this.plainText = plainText;
        this.cipherText = "";
        this.decryptedPlainText = "";
    }

    /**
     * @Author JinZhiyun
     * @Description 构造器完成入参
     * @Date 19:30 2019/8/27
     * @Param [plainText, cipherText, decryptedPlainText]
     * @return
     **/
    public CommonEncryption(String plainText, String cipherText, String decryptedPlainText) {
        this.plainText = plainText;
        this.cipherText = cipherText;
        this.decryptedPlainText = decryptedPlainText;
    }

    /**
     * @throws
     * @Title: encrypt
     * @Description: 加密返回密文（字符串形式）
     * @param: @return
     * @return: String
     */
    public abstract String encrypt();

}
