package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.Jacobi;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName JacobiDemo
 * @description 使用示例：雅可比符号的计算 {@link Jacobi}
 * @date 2019/11/9 15:57
 **/
public class JacobiDemo {
    private JacobiDemo(){}

    public static void main(String[] args) {
        BigInteger p=new BigInteger("17");
        BigInteger q=new BigInteger("4");
        System.out.println("Jacobi(q/p)=" + Method0.jacobi(q, p));
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

            System.out.println("Jacobi(q/p)=" + Jacobi.jacobi(q, p));
        }

    }
}
