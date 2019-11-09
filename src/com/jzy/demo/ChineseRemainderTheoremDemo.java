package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.ChineseRemainderTheorem;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName ChineseRemainderTheoremDemo
 * @description 使用示例：中国剩余定理求解，方程组：x同余b 模 m {@link ChineseRemainderTheorem}
 * @date 2019/11/9 15:39
 **/
public class ChineseRemainderTheoremDemo {
    private ChineseRemainderTheoremDemo() {
    }

    public static void main(String[] args) {
        demo();
    }
    
    public static void demo() {
        int size;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入方程个数：");
        if (scan.hasNextInt()) {
            size = scan.nextInt();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        BigInteger[] b = new BigInteger[size];
        BigInteger[] m = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            BigInteger tmp;
            System.out.println("请输入方程x同余b" + (i + 1) + "(mod m" + (i + 1) + ")的系数b" + (i + 1) + ",m" + (i + 1) + ":");
            if (scan.hasNextBigInteger()) {
                tmp = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            b[i] = tmp;
            if (scan.hasNextBigInteger()) {
                tmp = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            m[i] = tmp;
        }
        scan.close();

        BigInteger []result=Method0.solveChineseRemainderTheorem(b, m);
        System.out.println(
                "该同余方程组的解为：x=" + result[0] + "+" + result[1] + "q(q为整数)");
    }
}
