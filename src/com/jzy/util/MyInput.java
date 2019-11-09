package com.jzy.util;

import java.math.BigInteger;

import java.util.Scanner;

/**
 * 提供不同的命令行输入方式
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class MyInput {
    private MyInput(){}

    /**
     * 命令行输入一个大整数
     *
     * @return 输入的大整数
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static BigInteger inputBigInteger() {
        BigInteger n = new BigInteger("0");
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入正整数n：");

        if (scan.hasNextBigInteger()) {
            n = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
        }

        scan.close();

        return n;
    }

    /**
     * 命令行输入一个字符串
     *
     * @return 输入的字符串
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static String inputString() {
        String str = "";
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextLine()) {
            str = scan.nextLine();
        } else {
            System.out.println("发生错误！");
        }

        scan.close();

        return str;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
