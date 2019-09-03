package com.jzy.xxaqsxjc.encyption;

import java.math.BigInteger;

import java.util.Vector;

import com.jzy.xxaqsxjc.encyption.executor.BinaryEncryptionExecutor;

/**
 * 二进制加密类，拥有把字符串形式明文（此明文为由0、1组成的字符串，表示二进制串）每一个字符加密成一个大整数的特性
 * 抽象类用以继承，实现二进制加密执行器接口{@link BinaryEncryptionExecutor}
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public abstract class BinaryEncryption extends Encryption implements BinaryEncryptionExecutor {

    /**
     * 存储密文，向量中每一个大整数对于明文的一个字符（0或1）
     */
    protected Vector<BigInteger> cipherText = new Vector<>();

    /**
     * 默认构造器
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public BinaryEncryption() {
        this.plainText = "";
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
    }

    /**
     * 构造器入参明文，设置plainText变量
     *
     * @param plainText
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public BinaryEncryption(String plainText) {
        this.plainText = plainText;
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
    }

    /**
     * 加密方法，将明文每一个字符对应加密成一个大整数，向上参见BinaryEncryptionExecutor接口
     *
     * @return 大整数构成的Vector，对应每一个明文字符
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public abstract Vector<BigInteger> encrypt();

    public Vector<BigInteger> getCipherText() {
        return cipherText;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
