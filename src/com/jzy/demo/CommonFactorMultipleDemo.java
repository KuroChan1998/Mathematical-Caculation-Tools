package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.CommonFactorMultiple;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName CommonFactorMultipleDemo
 * @description 使用示例：最大公因数和最小公倍数的计算 {@link CommonFactorMultiple}
 * @date 2019/11/9 15:48
 **/
public class CommonFactorMultipleDemo {
    private CommonFactorMultipleDemo() {
    }

    public static void main(String[] args) {
        BigInteger x=new BigInteger("100");
        BigInteger y=new BigInteger("120");
        //x，y的最大公因数
        System.out.println(Method0.maxCommonFactorXY(x,y));
        //x，y的最小公倍数
        System.out.println(Method0.minCommonMultipleXY(x,y));

    }

    public static void demo() {
        BigInteger x;
        BigInteger[] s1 = new BigInteger[1000];
        BigInteger[] s2 = new BigInteger[1000];
        int size = 0;
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入整数x（0为终止）：");
        if (scan.hasNextBigInteger()) {
            x = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        if (x.toString() == "0") {
            scan.close();
            return;
        }
        while (x.toString() != "0") {
            s1[size] = x;
            s2[size] = x;
            size++;
            System.out.print("请输入整数x(x=0终止输入):");
            if (scan.hasNextBigInteger()) {
                x = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            if (x.toString() == "0" && size == 1) {
                System.out.println("至少输入两个数");
                System.out.print("请输入整数x(x=0终止输入):");
                x = scan.nextBigInteger();
            }
        }
        scan.close();

        for (int i = 0; i < size; i++) {
            System.out.print(s1[i]);
            if (i == size - 1) {
                break;
            }
            System.out.print(",");
        }
        System.out.println("的最大公因数是：" + CommonFactorMultiple.maxCommonFactorAll(s1, size, 0));

        for (int i = 0; i < size; i++) {
            System.out.print(s2[i]);
            if (i == size - 1) {
                break;
            }
            System.out.print(",");
        }
        System.out.println("的最小公倍数是：" + CommonFactorMultiple.minCommonMultipleAll(s2, size, 0));

    }
}
