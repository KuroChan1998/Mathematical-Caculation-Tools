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
 * Paillier加密算法
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class PaillierEncryption extends CommonEncryption {

    /**
     * 密钥位宽
     */
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;

    /**
     * 1024位大素数p,q
     */
    private static BigInteger p;
    private static BigInteger q;

    /**
     * 私钥，n的欧拉函数值
     */
    private static BigInteger EulerN;

    /**
     * 私钥
     */
    private static BigInteger L_1;

    /**
     * 公钥n
     */
    private static BigInteger n;

    /**
     * 公钥，n^2的简化剩余，n+1
     */
    private static BigInteger g;

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

    /**
     * 随机数r
     */
    private BigInteger r;

    public PaillierEncryption() {
        super();
    }

    public PaillierEncryption(String plainText) {
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

        BigInteger tmp1, tmp2, tmp3;

        for (int i = 0; i < plainText.length(); i++) {
            tmp1 = Method0.calculateMod(digitizedCipherText.get(i), EulerN, n.multiply(n));
            tmp2 = tmp1.subtract(Method0.VALUE_1).divide(n);
            tmp3 = tmp2.multiply(L_1).mod(n);
            decryptedPlainText = decryptedPlainText + (char) (tmp3.intValue());
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

        BigInteger tmp1, tmp2, tmp3;

        for (int i = 0; i < plainText.length(); i++) {
            digitizedPlainText.addElement(BigInteger.valueOf((long) plainText.charAt(i)));
            tmp1 = Method0.calculateMod(g, digitizedPlainText.get(i), n.multiply(n));

            do {
                r = new BigInteger(n.bitLength(), new Random());
                r = r.mod(n);
            } while (BigIntegerTest.ifEqualsZero(r));

            tmp2 = Method0.calculateMod(r, n, n.multiply(n));
            digitizedCipherText.addElement(tmp1.multiply(tmp2).mod(n.multiply(n)));
            tmp3 = digitizedCipherText.get(i).mod(new BigInteger("95"));
            cipherText = cipherText + (char) (tmp3.intValue() + 32);
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

        // 随机bitLength位大素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的bitLength位大素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while ((PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1) || (p.compareTo(q) == 0));

        // 公钥n
        n = p.multiply(q);

        // 公钥g，n^2的简化剩余，n+1
        g = n.add(Method0.VALUE_1);

        // 私钥λ
        EulerN = (p.subtract(Method0.VALUE_1).multiply(q.subtract(Method0.VALUE_1)));

        // 私钥μ
        L_1 = Method0.bezoutSolveQrS11(EulerN, n);
    }

    @Override
    public void show() {
        System.out.println("随机生成" + keysBitLength + "位大素数p=" + p);
        System.out.println("随机生成" + keysBitLength + "位大素数q=" + q);
        System.out.println(2 * keysBitLength + "位公钥n=p*q=" + n);
        System.out.println("进而生成公钥g=" + g);
        System.out.println("进而生成私钥λ=" + EulerN);
        System.out.println("进而生成私钥μ=" + L_1);

        // 单字符加密
        System.out.println("加密得到密文：" + encrypt());
        System.out.println("解密密文得到的明文：" + decrypt());
    }

    public Vector<BigInteger> getDigitizedCipherText() {
        return digitizedCipherText;
    }

    public Vector<BigInteger> getDigitizedPlainText() {
        return digitizedPlainText;
    }

    public static BigInteger getEulerN() {
        return EulerN;
    }

    public static BigInteger getG() {
        return g;
    }

    public static int getKeysBitLength() {
        return keysBitLength;
    }

    public static BigInteger getL1() {
        return L_1;
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

    public BigInteger getR() {
        return r;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
