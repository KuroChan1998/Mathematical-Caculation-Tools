package com.jzy.xxaqsxjc.encyption.algorithm;

import java.math.BigInteger;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import com.jzy.exception.InputException;
import com.jzy.exception.encryption.NotEncryptedException;
import com.jzy.util.BigIntegerTest;
import com.jzy.xxaqsxjc.encyption.CommonEncryption;
import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

/**
 * 实现RSA加密算法
 * see more about RSA: https://en.wikipedia.org/wiki/RSA_(cryptosystem)
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class RSAEncryption extends CommonEncryption {

    /**
     * 密钥位宽
     */
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;

    /**
     * 私钥d
     */
    private static BigInteger d;

    /**
     * 1024位私钥P
     */
    private static BigInteger p, q;

    /**
     * 2048位公钥n
     */
    private static BigInteger n;

    /**
     * 公钥e
     */
    private static BigInteger e;

    /**
     * 公钥n的欧拉函数值
     */
    private static BigInteger EulerN;

    static {
        resetKeys();
    }

    /**
     * 存储明文的数字化信息
     */
    private Vector<BigInteger> digitizedPlainText = new Vector<>();

    /**
     * 存储密文的数字化信息
     */
    private Vector<BigInteger> digitizedCipherText = new Vector<>();

    public RSAEncryption() {
        super();
    }

    public RSAEncryption(String plainText) {
        super(plainText);
    }

    /**
     * 解密方法，参见父类{@link Encryption}
     *
     * @return
     * @throws NotEncryptedException
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    @Override
    public String decrypt() throws NotEncryptedException {
        if (!isEncrypted()) {
            throw new NotEncryptedException("当前对象还未加密，请先加密！");
        }

        BigInteger tmp2;

        for (int i = 0; i < plainText.length(); i++) {
            tmp2 = Method0.calculateMod(digitizedCipherText.get(i), d, n);
            decryptedPlainText = decryptedPlainText + (char) (tmp2.intValue());
        }

        isEncrypted = false;

        return decryptedPlainText;
    }

    /**
     * 加密方法，参见父类{@link CommonEncryption}
     *
     * @return
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    @Override
    public String encrypt() {
        resetAllTextsExceptPlainText();

        BigInteger tmp1;

        for (int i = 0; i < plainText.length(); i++) {
            digitizedPlainText.addElement(BigInteger.valueOf((long) plainText.charAt(i)));
            digitizedCipherText.addElement(Method0.calculateMod(digitizedPlainText.get(i), e, n));
            tmp1 = digitizedCipherText.get(i).mod(new BigInteger("95"));
            cipherText = cipherText + (char) (tmp1.intValue() + 32);
        }

        isEncrypted = true;

        return cipherText;
    }

    @Override
    public void resetAllTexts() {
        this.plainText = "";
        resetAllTextsExceptPlainText();
    }

    @Override
    public void resetAllTextsExceptPlainText() {
        this.cipherText = "";
        this.decryptedPlainText = "";
        this.digitizedCipherText = new Vector<>();
        this.digitizedPlainText = new Vector<>();
        this.isEncrypted = false;
    }

    /**
     * 重置当前所有秘钥，默认p、q为1024bit
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static void resetKeys() {
        keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;
        resetKeys(keysBitLength);
    }

    /**
     * 重置当前所有秘钥，传入bitLength为p、q秘钥位宽
     *
     * @param bitLength p、q秘钥位宽
     * @throws InputException
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static void resetKeys(int bitLength) throws InputException {
        if (!Encryptions.isLegalKeysBitLength(bitLength)) {
            throw new InputException("输入的位宽应在" + Encryptions.KEYS_BIT_MIN_LENGTH + "~"
                    + Encryptions.KEYS_BIT_MAX_LENGTH + "之间");
        }

        // 设置位宽
        keysBitLength = bitLength;

        // 随机生成素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while ((PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1) || (p.compareTo(q) == 0));

        // 公钥n
        n = p.multiply(q);
        EulerN = (p.subtract(Method0.VALUE_1).multiply(q.subtract(Method0.VALUE_1)));

        // 公钥e
        do {
            e = new BigInteger(EulerN.bitLength(), new Random());
            e = e.mod(EulerN);
        } while (!BigIntegerTest.ifEqualsOne(Method0.maxCommonFactorXY(e, EulerN))
                || (e.compareTo(Method0.VALUE_1) <= 0));

        // 私钥d
        d = Method0.bezoutSolveQrS11(e, EulerN);
    }

    @Override
    public void show() {
        System.out.println("随机生成" + keysBitLength + "位大素数p=" + p);
        System.out.println("随机生成" + keysBitLength + "位大素数q=" + q);
        System.out.println(2 * keysBitLength + "位公钥n=p*q=" + n);
        System.out.println("随机生成公钥e=" + e);
        System.out.println("进而生成私钥d=" + d);

        // 单字符加密
        System.out.println("加密得到密文：" + encrypt());
        System.out.println("解密密文得到的明文：" + decrypt());
    }

    public static BigInteger getD() {
        return d;
    }

    public Vector<BigInteger> getDigitizedCipherText() {
        return digitizedCipherText;
    }

    public Vector<BigInteger> getDigitizedPlainText() {
        return digitizedPlainText;
    }

    public static BigInteger getE() {
        return e;
    }

    public static BigInteger getEulerN() {
        return EulerN;
    }

    public static int getKeysBitLength() {
        return keysBitLength;
    }

    public static BigInteger getN() {
        return n;
    }

    public static BigInteger getP() {
        return p;
    }

    public static BigInteger getQ() {
        return q;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
