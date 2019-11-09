package com.jzy.xxaqsxjc.encyption.algorithm;

import com.jzy.exception.InputException;
import com.jzy.exception.encryption.NotBinaryStringException;
import com.jzy.exception.encryption.NotEncryptedException;
import com.jzy.util.BigIntegerTest;
import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.encyption.BinaryEncryption;
import com.jzy.xxaqsxjc.encyption.Encryption;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * GoldwasserMicali加密算法（对二进制加密）
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class GoldwasserMicaliBinaryEncryption extends BinaryEncryption {

    /**
     * 密钥位宽
     */
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;
    /**
     * 私钥p(1024位）
     */
    private static BigInteger p;

    /**
     * 私钥q(1024位）
     */
    private static BigInteger q;

    /**
     * 公钥n(2048位)
     */
    private static BigInteger n;

    /**
     * 公钥x(512位)
     */
    private static BigInteger x;

    static {
        resetKeys();
    }

    public GoldwasserMicaliBinaryEncryption() {
    }

    public GoldwasserMicaliBinaryEncryption(String plainText) {
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

        for (int i = 0; i < plainText.length(); i++) {
            if (Method0.legendre(cipherText.get(i), p) == 1) {
                decryptedPlainText = decryptedPlainText + "0";
            } else if (Method0.legendre(cipherText.get(i), p) == -1) {
                decryptedPlainText = decryptedPlainText + "1";
            } else {
                System.out.println("Error!");
                decryptedPlainText = decryptedPlainText + "#";
            }
        }

        isEncrypted = false;

        return decryptedPlainText;
    }

    /**
     * 加密方法，参见父类{@link BinaryEncryption}
     *
     * @return
     * @throws NotBinaryStringException
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    @Override
    public Vector<BigInteger> encrypt() throws NotBinaryStringException {
        if (!StringTest.isOnlyContain01(plainText)) {
            throw new NotBinaryStringException("输入明文串还有非0、1的字符！");
        }

        resetAllTextsExceptPlainText();

        BigInteger y;    // 干扰
        BigInteger tmp1, tmp2, tmp3;

        for (int i = 0; i < plainText.length(); i++) {
            do {
                y = new BigInteger(n.bitLength(), new Random());
                y = y.mod(n);
            } while (!BigIntegerTest.ifEqualsOne(Method0.maxCommonFactorXY(y, n)));

            tmp1 = y.mod(n);

            if (plainText.charAt(i) == '0') {
                cipherText.addElement(tmp1.multiply(tmp1).mod(n));
            } else {
                tmp2 = x.mod(n);
                tmp3 = tmp1.multiply(tmp1).mod(n);
                cipherText.addElement(tmp2.multiply(tmp3).mod(n));
            }
        }

        isEncrypted = true;

        return cipherText;
    }

    @Override
    protected void resetAllTexts() {
        this.plainText = "";
        resetAllTextsExceptPlainText();
    }

    @Override
    protected void resetAllTextsExceptPlainText() {
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
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

        // 随机生成bitLength位大素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的bitLength位大素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while ((PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1) || (p.compareTo(q) == 0));

        // 公钥n
        n = p.multiply(q);

        // 公钥x
        do {
            x = new BigInteger(bitLength / 2, new Random());
        } while ((Method0.legendre(x, p) != -1) || (Method0.legendre(x, q) != -1));
    }

    @Override
    public void show() {
        System.out.println("随机生成" + keysBitLength + "位大素数p（私钥）=" + p);
        System.out.println("随机生成" + keysBitLength + "位大素数q（私钥）=" + q);
        System.out.println(2 * keysBitLength + "位公钥n=p*q=" + n);
        System.out.println("随机生成公钥x=" + x);
        encrypt();
        System.out.println("加密得到密文:");
        encrypt();
        System.out.println(showCipherText());
        System.out.println("解密密文得到的明文：" + decrypt());
    }

    /**
     * 将密文向量直观的显示,如：
     * <p>C1=12345<br>
     * c2=54321<br>
     * C3=10000<br>
     * ....
     *
     * @return 显示的字符串
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public String showCipherText() {
        String re = "";

        for (int i = 0; i < cipherText.size(); i++) {
            re += "C" + i + "=" + cipherText.get(i);

            if (i != cipherText.size() - 1) {
                re += '\n';
            }
        }

        return re;
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

    public static BigInteger getX() {
        return x;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
