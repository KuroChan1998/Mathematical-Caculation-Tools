/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PaillierEncryption.java
 * @Package com.jzy.xxaqsxjc.encyption
 * @Description: Paillier加密算法
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午6:57:00
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.encyption.algorithm;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import com.jzy.exception.InputException;
import com.jzy.exception.encryption.NotEncrytedExcetion;
import com.jzy.util.BigIntegerTest;
import com.jzy.xxaqsxjc.encyption.CommonEncryption;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

/**
 * @ClassName: PaillierEncryption
 * @Description: Paillier加密算法
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午6:55:37
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class PaillierEncryption extends CommonEncryption {
    private static BigInteger p;// 1024位大素数
    private static BigInteger q;// 1024位大素数
    private static BigInteger EulerN;// 私钥
    private static BigInteger L_1;// 私钥
    private static BigInteger n;// 公钥n
    private static BigInteger g;// 公钥，n^2的简化剩余，n+1
    private BigInteger r;// 随机数r
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;//位宽

    private Vector<BigInteger> digitizedPlainText = new Vector<>();// 存储明文的数字化信息
    private Vector<BigInteger> digitizedCipherText = new Vector<>();// 存储密文的数字化信息

    //秘钥在编译生成，利用静态块，提升性能
    static {
        resetKeys();
    }

    public static BigInteger getP() {
        return p;
    }

    public static BigInteger getQ() {
        return q;
    }

    public static BigInteger getEulerN() {
        return EulerN;
    }

    public static BigInteger getL1() {
        return L_1;
    }

    public static BigInteger getN() {
        return n;
    }

    public static BigInteger getG() {
        return g;
    }

    public BigInteger getR() {
        return r;
    }

    public static int getKeysBitLength() {
        return keysBitLength;
    }

    public Vector<BigInteger> getDigitizedPlainText() {
        return digitizedPlainText;
    }

    public Vector<BigInteger> getDigitizedCipherText() {
        return digitizedCipherText;
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

        // 随机1024位大素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的1024位大素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1 || p.compareTo(q) == 0);

        // 公钥n2048位
        n = p.multiply(q);
        // 公钥g，n^2的简化剩余，n+1
        g = n.add(Method0.VALUE_1);
        // 私钥λ
        EulerN = (p.subtract(Method0.VALUE_1).multiply(q.subtract(Method0.VALUE_1)));
        // 私钥μ
        L_1 = Method0.bezoutSolveQrS11(EulerN, n);
    }

    public PaillierEncryption() {
        super();
    }

    public PaillierEncryption(String plainText) {
        super(plainText);
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

    /*
     * 注意这里由于此加密算法的干扰因子r存在，所以每次调用次方法加密结果都不同，但解密的结果仍相同，这就是干扰的意义，算法的安全性体现！
     */
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

    public String decrypt() {
        if (!isEncrypted()) {
            throw new NotEncrytedExcetion("当前对象还未加密，请先加密！");
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

        PaillierEncryption pe1 = new PaillierEncryption(str1);
        pe1.show();
        PaillierEncryption.resetKeys(120);
        pe1.setPlainText("asdadasd");
        System.out.println(pe1.encrypt());
        System.out.println(pe1.encrypt());
        System.out.println(pe1.decrypt());
        PaillierEncryption.resetKeys();
        pe1.show();
    }

}
