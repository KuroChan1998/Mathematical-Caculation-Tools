package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.CalculateMod;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName CalculateModDemo
 * @description 使用示例：模重复平方计算余数；绝对值最小余数、最小非负余数 {@link CalculateMod}
 * @date 2019/11/9 15:27
 **/
public class CalculateModDemo {
    private CalculateModDemo(){}

    public static void main(String[] args) {
        BigInteger b=new BigInteger("11");
        BigInteger n=new BigInteger("101");
        BigInteger m=new BigInteger("5656");
        System.out.println(b + "^" + n + "模" + m + "=" + Method0.calculateMod(b, n, m));
        System.out.println("绝对值最小余数为" + Method0.calculateAbsMinMod(b, n, m));
    }

    public static void demo() {

        BigInteger b, n, m, a, n1;
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入正整数b：");

        if (scan.hasNextBigInteger()) {
            b = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();

            return;
        }

        System.out.print("请输入正整数n：");

        if (scan.hasNextBigInteger()) {
            n = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();

            return;
        }

        System.out.print("请输入正整数m：");

        if (scan.hasNextBigInteger()) {
            m = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();

            return;
        }

        System.out.println(b + "^" + n + "模" + m + "=" + Method0.calculateMod(b, n, m));
    }
}
