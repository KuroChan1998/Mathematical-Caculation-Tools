package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.EulerFunction;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName EulerFunctionDemo
 * @description 使用示例：欧拉函数的计算 {@link EulerFunction}
 * @date 2019/11/9 15:54
 **/
public class EulerFunctionDemo {
    private EulerFunctionDemo() {
    }

    public static void main(String[] args) {
        BigInteger n=new BigInteger("17");
        //17的欧拉函数值
        System.out.println("n的欧拉函数值为：" + Method0.euler(n));
    }

    public static void demo() {
        BigInteger n;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入正整数n：");
        if (scan.hasNextBigInteger()) {
            n = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        scan.close();
        System.out.println("n的欧拉函数值为：" + EulerFunction.euler(n));
    }
}
