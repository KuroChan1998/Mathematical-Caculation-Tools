package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.Legendre;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName LegendreDemo
 * @description 使用示例：勒让得符号计算 {@link Legendre}
 * @date 2019/11/9 16:00
 **/
public class LegendreDemo {
    private LegendreDemo() {
    }

    public static void main(String[] args) {
        BigInteger p=new BigInteger("17");
        BigInteger q=new BigInteger("4");
        System.out.println("Legendre(q/p)=" + Method0.legendre(q, p));
    }

    public static void demo() {
        BigInteger q, p;
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("请输入正整数q：");

            if (scan.hasNextBigInteger()) {
                q = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();

                return;
            }

            System.out.print("请输入正整数p：");

            if (scan.hasNextBigInteger()) {
                p = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();

                return;
            }

            while ((PrimeTest.millerRabin(p, 20) != 1) || (p.compareTo(new BigInteger("2")) == 0)) {
                System.out.println("p不为奇素数：");
                System.out.print("请输入正整数p：");

                if (scan.hasNextBigInteger()) {
                    p = scan.nextBigInteger();
                } else {
                    System.out.println("发生错误！");
                    scan.close();

                    return;
                }
            }

            System.out.println("Legendre(q/p)=" + Legendre.legendre(q, p));
        }
    }
}
