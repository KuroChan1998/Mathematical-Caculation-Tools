package com.jzy.util;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName MyInput
 * @Author JinZhiyun
 * @Description 提供不同的命令行输入方式
 * @Date 2019/8/25 21:28
 * @Version 1.0
 **/
public class MyInput {
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
