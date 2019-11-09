package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimitiveRoot;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PrimitiveRootDemo
 * @description 使用示例：原根计算 {@link PrimitiveRoot}
 * @date 2019/11/9 16:08
 **/
public class PrimitiveRootDemo {
    private PrimitiveRootDemo(){}

    public static void main(String[] args) {
        BigInteger p=new BigInteger("131");
        System.out.println("p的最小原根是：" + PrimitiveRoot.minPrimitiveRoot(p));
        System.out.println("p的所有原根是：" + PrimitiveRoot.primitiveRoots(p));
    }

    public static void demo() {
        BigInteger p;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("请输入奇素数p：");
            if (scan.hasNextBigInteger()) {
                p = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            while (Method0.violentIfPrime(p) == 0) {
                System.out.println("p不是奇素数!");
                System.out.print("请输入奇素数p：");
                if (scan.hasNextBigInteger()) {
                    p = scan.nextBigInteger();
                } else {
                    System.out.println("发生错误！");
                    scan.close();
                    return;
                }
            }
        } while (Method0.violentIfPrime(p) == 2);
        scan.close();

        System.out.println("p的最小原根是：" + PrimitiveRoot.minPrimitiveRoot(p));
        System.out.println("p的所有原根是：" + PrimitiveRoot.primitiveRoots(p));
    }
}
