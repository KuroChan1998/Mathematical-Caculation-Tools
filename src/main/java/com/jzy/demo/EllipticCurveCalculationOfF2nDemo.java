package com.jzy.demo;

import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.xxaqsxjc.method1.EllipticCurveCalculationOfF2n;
import com.jzy.xxaqsxjc.method1.Polynomial;

import java.util.ArrayList;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName EllipticCurveCalculationOfF2nDemo
 * @description 使用示例：F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等 {@link EllipticCurveCalculationOfF2n}
 * @date 2019/11/9 16:45
 **/
public class EllipticCurveCalculationOfF2nDemo {
    private EllipticCurveCalculationOfF2nDemo() {
    }

    public static void main(String[] args) throws PolyDivideByZeroException {

        Polynomial curvePolynomial = new Polynomial(new int[]{
                1, 0, 1, 1, 1, 0, 0, 0, 1
        });

        EllipticCurveCalculationOfF2n ecc = new EllipticCurveCalculationOfF2n(curvePolynomial,
                new Polynomial(),
                new Polynomial());
        int[] a = {1, 0, 0, 1};
        int[] b = {
                0, 1, 1, 1, 0, 1, 1, 1
        };

        Polynomial[] p1 = {new Polynomial(a), new Polynomial(b)};
        ArrayList<Polynomial[]> rs = ecc.kPointSet(p1, 34);

        for (int i = 0; i < rs.size(); i++) {
            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
            System.out.println();
        }

        System.out.println(ecc.kPoint(p1, 100)[0]);
        System.out.println(ecc.kPoint(p1, 100)[1]);
    }
}
