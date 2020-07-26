package com.jzy.demo;

import com.jzy.xxaqsxjc.method1.EllipticCurveCalculationOfFp;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName EllipticCurveCalculationOfFpDemo
 * @description 使用示例：Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等 {@link EllipticCurveCalculationOfFp}
 * @date 2019/11/9 16:44
 **/
public class EllipticCurveCalculationOfFpDemo {
    private EllipticCurveCalculationOfFpDemo(){}

    public static void main(String[] args) {
        //传入椭圆曲线参数，创建实例
        EllipticCurveCalculationOfFp eccfp = new EllipticCurveCalculationOfFp(new BigInteger("100823"), new BigInteger("3"), new BigInteger("7"));
        //点P1
        BigInteger[] p1 = {new BigInteger("5"), new BigInteger("101")};
        //计算P1、2P1、3P1、...kP1
        ArrayList<BigInteger[]> rs = eccfp.kPointSet(p1, 27);
        for (int i = 0; i < rs.size(); i++) {
            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
            System.out.println();
        }
        //计算10P1
        System.out.println(eccfp.kPoint(p1, 10)[0]);
        System.out.println(eccfp.kPoint(p1, 10)[1]);
        //计算当前椭圆曲线的阶
        System.out.println(eccfp.ordFp());
    }
}
