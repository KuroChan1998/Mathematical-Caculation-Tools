package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.BezoutEquationSolution;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName BezoutEquationSolutionDemo
 * @description 贝祖等式求解示例 {@link BezoutEquationSolution}
 * @date 2019/11/9 15:20
 **/
public class BezoutEquationSolutionDemo {
    private BezoutEquationSolutionDemo(){}

    public static void main(String[] args) {
        //x=12
        BigInteger x=new BigInteger("12");
        //y=10
        BigInteger y=new BigInteger("10");
        //求最大公因数
        System.out.println("最大公因数为：" + Method0.maxCommonFactorXY(x, y));

        //求备贝祖等式系数
        BigInteger[] tmp1 = Method0.bezoutSolveQrSt(x, y);
        System.out.println("s=" + tmp1[0] + ",t=" + tmp1[1]);
        //求非负整数系数s
        System.out.println("正数s=" + Method0.bezoutSolveQrS11(x, y));
    }

    public static void demo() {
        BigInteger x, y;
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入正整数x：");

        if (scan.hasNextBigInteger()) {
            x = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();

            return;
        }

        System.out.print("请输入正整数y：");

        if (scan.hasNextBigInteger()) {
            y = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();

            return;
        }

        scan.close();
        System.out.println("最大公因数为：" + Method0.maxCommonFactorXY(x, y));

        BigInteger[] tmp1 = Method0.bezoutSolveQrSt(x, y);

        System.out.println("s=" + tmp1[0] + ",t=" + tmp1[1]);
        System.out.println("正数s=" + Method0.bezoutSolveQrS11(x, y));
    }
}
