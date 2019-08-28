/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: RSAEncryption.java
 * @Package com.jzy.xxaqsxjc.encyption
 * @Description: 实现RSA加密算法
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午6:46:27
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.encyption.algorithm;

import com.jzy.exception.InputException;
import com.jzy.exception.encryption.NotEncrytedExcetion;
import com.jzy.util.BigIntegerTest;
import com.jzy.xxaqsxjc.encyption.CommonEncryption;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * @ClassName: RSAEncryption
 * @Description: 实现RSA加密算法
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午6:45:59
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class RSAEncryption extends CommonEncryption {
    private static BigInteger d; // 私钥d
    private static BigInteger p, q;// 1024位
    private static BigInteger n; // 公钥n，2048位
    private static BigInteger e;// 公钥e
    private static BigInteger EulerN;// 公钥n的欧拉函数值
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;//位宽

    private Vector<BigInteger> digitizedPlainText = new Vector<>();// 存储明文的数字化信息
    private Vector<BigInteger> digitizedCipherText = new Vector<>();// 存储密文的数字化信息

    //秘钥在编译生成，利用静态块，提升性能
    static {
        resetKeys();
    }

    public static BigInteger getD() {
        return d;
    }

    public static BigInteger getP() {
        return p;
    }

    public static BigInteger getQ() {
        return q;
    }

    public static BigInteger getN() {
        return n;
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

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 重置当前所有秘钥，默认p、q为1024bit
     * @Date 19:18 2019/8/26
     * @Param []
     **/
    public static void resetKeys() {
        keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;
        resetKeys(keysBitLength);
    }

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 重置当前所有秘钥，bitLength为p、q秘钥位宽
     * @Date 19:24 2019/8/26
     * @Param [bitLength]
     **/
    public static void resetKeys(int bitLength) {
        if (!Encryptions.isLegalKeysBitLength(bitLength)) {
            throw new InputException("输入的位宽应在" + Encryptions.KEYS_BIT_MIN_LENGTH + "~" + Encryptions.KEYS_BIT_MAX_LENGTH + "之间");
        }
        //设置位宽
        keysBitLength = bitLength;

        // 随机生成素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1 || p.compareTo(q) == 0);

        // 公钥n
        n = p.multiply(q);
        EulerN = (p.subtract(Method0.VALUE_1).multiply(q.subtract(Method0.VALUE_1)));

        // 公钥e
        do {
            e = new BigInteger(EulerN.bitLength(), new Random());
            e = e.mod(EulerN);
        }
        while (!BigIntegerTest.ifEqualsOne(Method0.maxCommonFactorXY(e, EulerN)) || e.compareTo(Method0.VALUE_1) <= 0);

        // 私钥d
        d = Method0.bezoutSolveQrS11(e, EulerN);
    }

    public RSAEncryption() {
        super();
    }

    public RSAEncryption(String plainText) {
        super(plainText);
    }

    /**
     * @throws
     * @Title: RSA_get_math_pt
     * @Description: 返回数字化的明文
     * @param: @return
     * @return: Vector<BigInteger>
     */
    public Vector<BigInteger> getDigitizedPlainText() {
        return digitizedPlainText;
    }

    /**
     * @throws
     * @Title: RSA_get_math_ct
     * @Description: 返回数字化的密文
     * @param: @return
     * @return: Vector<BigInteger>
     */
    public Vector<BigInteger> getDigitizedCipherText() {
        return digitizedCipherText;
    }

    public void resetAllTexts() {
        this.plainText = "";
        resetAllTextsExceptPlainText();
    }

    public void resetAllTextsExceptPlainText() {
        this.cipherText = "";
        this.decryptedPlainText = "";
        this.digitizedCipherText = new Vector<>();
        this.digitizedPlainText = new Vector<>();
        this.isEncrypted = false;
    }

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

    public String decrypt() {
        if (!isEncrypted()) {
            throw new NotEncrytedExcetion("当前对象还未加密，请先加密！");
        }
        BigInteger tmp2;
        for (int i = 0; i < plainText.length(); i++) {
            tmp2 = Method0.calculateMod(digitizedCipherText.get(i), d, n);
            decryptedPlainText = decryptedPlainText + (char) (tmp2.intValue());
        }
        isEncrypted = false;
        return decryptedPlainText;
    }

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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入明文消息：");
        String str1;
        if (scan.hasNextLine()) {
            str1 = scan.nextLine();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        scan.close();

        RSAEncryption rsa1 = new RSAEncryption("sdasd");
        rsa1.show();
        RSAEncryption.resetKeys(25);
        rsa1.setPlainText("1231");
//        rsa1.resetAllTexts();
        System.out.println(rsa1.encrypt());
        System.out.println(rsa1.encrypt());
        System.out.println(rsa1.decrypt());

        rsa1.show();

    }

}
