package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.encyption.executor.CommonEncryptExecutor;

/**
 * 通用加密类，拥有把字符串形式明文加密成字符串形式密文的特性，抽象类用以继承，实现通用加密执行器接口{@link CommonEncryptExecutor}
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public abstract class CommonEncryption extends Encryption implements CommonEncryptExecutor {

    /**
     * 密文，字符串形式
     */
    protected String cipherText;

    /**
     * 默认构造器
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public CommonEncryption() {
        this.plainText = "";
        this.cipherText = "";
        this.decryptedPlainText = "";
    }

    /**
     * 构造器入参明文，设置plainText变量
     *
     * @param plainText 明文
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public CommonEncryption(String plainText) {
        this.plainText = plainText;
        this.cipherText = "";
        this.decryptedPlainText = "";
    }

    /**
     * 加密方法，将明文加密成字符串形式的密文，向上参见CommonEncryptExecutor接口
     *
     * @return 字符串形式的密文
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public abstract String encrypt();

    public String getCipherText() {
        return cipherText;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
