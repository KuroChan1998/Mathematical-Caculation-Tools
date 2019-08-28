/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: GoldwasserMicaliBinaryEncryption.java
 * @Package com.jzy.xxaqsxjc.encyption
 * @Description: GoldwasserMicali加密算法（对二进制加密）
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午7:05:51
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
import com.jzy.exception.encryption.NotBinaryStringException;
import com.jzy.exception.encryption.NotEncrytedExcetion;
import com.jzy.util.BigIntegerTest;
import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.encyption.BinaryEncryption;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

/**
 * @ClassName: GoldwasserMicaliBinaryEncryption
 * @Description: GoldwasserMicali加密算法（对二进制加密）
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午7:01:50
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class GoldwasserMicaliBinaryEncryption extends BinaryEncryption {
    private static BigInteger p;// 私钥p(1024位）
    private static BigInteger q;// 私钥q(1024位）
    private static BigInteger n;// 公钥n(2048位)
    private static BigInteger x;// 公钥x(512位)
    private static int keysBitLength = Encryptions.KEYS_BIT_DEFAULT_LENGTH;//位宽

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

    public static BigInteger getN() {
        return n;
    }

    public static BigInteger getX() {
        return x;
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
     * @Date 19:19 2019/8/27
     * @Param [bitLength]
     **/
    public static void resetKeys(int bitLength) {
        if (!Encryptions.isLegalKeysBitLength(bitLength)) {
            throw new InputException("输入的位宽应在" + Encryptions.KEYS_BIT_MIN_LENGTH + "~" + Encryptions.KEYS_BIT_MAX_LENGTH + "之间");
        }
        //设置位宽
        keysBitLength = bitLength;

        // 随机生成1024位大素数p
        do {
            p = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(p, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1);

        // 随机生成不等于p的1024位大素数q
        do {
            q = new BigInteger(bitLength, Encryptions.PRIME_TEST_SECURITY_PARAMETER, new Random());
        } while (PrimeTest.millerRabin(q, Encryptions.PRIME_TEST_SECURITY_PARAMETER) != 1 || p.compareTo(q) == 0);

        // 公钥n
        n = p.multiply(q);

        // 公钥x
        do {
            x = new BigInteger(bitLength / 2, new Random());
        } while (Method0.legendre(x, p) != -1 || Method0.legendre(x, q) != -1);
    }

    public GoldwasserMicaliBinaryEncryption() {
    }

    public GoldwasserMicaliBinaryEncryption(String plainText) {
        super(plainText);
    }

    public Vector<BigInteger> encrypt() {
        if (!StringTest.isOnlyContain01(plainText)) {
            throw new NotBinaryStringException("输入明文串还有非0、1的字符！");
        }
        resetAllTextsExceptPlainText();
        BigInteger y;// 干扰
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

    public String decrypt() {
        if (!isEncrypted()) {
            throw new NotEncrytedExcetion("当前对象还未加密，请先加密！");
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

    protected void resetAllTexts() {
        this.plainText = "";
        resetAllTextsExceptPlainText();
    }

    protected void resetAllTextsExceptPlainText() {
        this.cipherText = new Vector<>();
        this.decryptedPlainText = "";
        this.isEncrypted = false;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 美化密文输出格式
     * @Date 19:27 2019/8/27
     * @Param []
     **/
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        boolean flag;
        String str1;
        do {
            flag = false;
            System.out.print("请输入明文消息：");
            if (scan.hasNextLine()) {
                str1 = scan.nextLine();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != '0' && str1.charAt(i) != '1') {
                    System.out.println("仅能输入含0或1的字符串！");
                    flag = true;
                    break;
                }
            }
        } while (flag);
        scan.close();

        GoldwasserMicaliBinaryEncryption gmbe1 = new GoldwasserMicaliBinaryEncryption(str1);
        gmbe1.show();
        GoldwasserMicaliBinaryEncryption.resetKeys(511);
        gmbe1.setPlainText("101000000");
        System.out.println(gmbe1.encrypt());

        System.out.println(gmbe1.decrypt());

        gmbe1.setPlainText("1010111100000");
        gmbe1.show();


    }

}
