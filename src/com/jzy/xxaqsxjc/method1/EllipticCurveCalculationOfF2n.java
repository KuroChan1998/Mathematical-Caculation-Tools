package com.jzy.xxaqsxjc.method1;

import java.util.ArrayList;

import com.jzy.exception.ploynomial.EllipticCurveTooLargeKException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;

/**
 * F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class EllipticCurveCalculationOfF2n {

    /**
     * 规定计算k点的输入k的最大值
     */
    public static final int MAX_K = 1 << 14;

    /**
     * 特征多项式
     */
    private Polynomial curvePolynomial;

    /**
     * 椭圆曲线y^2+xy=x^3+a2*x^2+a6系数a2
     */
    private Polynomial a2;

    /**
     * 椭圆曲线y^2+xy=x^3+a2*x^2+a6系数a6
     */
    private Polynomial a6;

    /**
     * 构造器
     * <p>传入特征多项式、系数a2、a6生成椭圆曲线对象<br>
     *
     * @param curvePolynomial 特征多项式
     * @param a2              系数a2
     * @param a6              系数a6
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public EllipticCurveCalculationOfF2n(Polynomial curvePolynomial, Polynomial a2, Polynomial a6) {
        this.curvePolynomial = (Polynomial) curvePolynomial.clone();
        this.a2 = a2;
        this.a6 = a6;
    }

//    public static void main(String[] args) throws PolyDivideByZeroException {
//
//        Polynomial curvePolynomial = new Polynomial(new int[]{
//                1, 0, 1, 1, 1, 0, 0, 0, 1
//        });
//
////      Polynomial curvePolynomial=new Polynomial(new int[] {1,1,0,1});
//        EllipticCurveCalculationOfF2n ecc = new EllipticCurveCalculationOfF2n(curvePolynomial,
//                new Polynomial(),
//                new Polynomial());
//        int[] a = {1, 0, 0, 1};
//        int[] b = {
//                0, 1, 1, 1, 0, 1, 1, 1
//        };
//
////      int []a= {0,1};
////      int []b= {1,1,1};
//        Polynomial[] p1 = {new Polynomial(a), new Polynomial(b)};
//        ArrayList<Polynomial[]> rs = ecc.kPointSet(p1, 34);
//
//        for (int i = 0; i < rs.size(); i++) {
//            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
//            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
//            System.out.println();
//        }
//
//        System.out.println(ecc.kPoint(p1, 10000)[0]);
//        System.out.println(ecc.kPoint(p1, 10000)[1]);
//    }

    /**
     * 计算k倍的p1
     * <p>e.g. 4(x^2+x,x)-->返回计算结果(px,py)<br>
     *
     * @param p1 输入多项式点
     * @param k  k倍的p1的k
     * @return k倍的p1
     * @throws PolyDivideByZeroException
     * @throws EllipticCurveTooLargeKException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial[] kPoint(Polynomial[] p1, int k) throws PolyDivideByZeroException, EllipticCurveTooLargeKException {    // kP1
        if (k > MAX_K) {
            throw new EllipticCurveTooLargeKException("输入的k大于了" + MAX_K);
        }
        Polynomial[] ap = p1;
        if (k >= 2) {
            ap = pointAdd(p1, p1);
            if (k >= 3) {
                for (int i = 0; i < k - 2; i++) {
                    ap = pointAdd(p1, ap);
                }
            }
        }

        return ap;
    }

    /**
     * k倍点的所有计算结果集
     * <p>e.g. k=4,p1=(x^2+x,x)<br>
     * -->返回计算结果1p1,2p1,3p1,4p1构成的ArrayList
     *
     * @param p1 输入多项式点
     * @param k  k倍的p1的k
     * @return 所有k倍的p1的集合
     * @throws PolyDivideByZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public ArrayList<Polynomial[]> kPointSet(Polynomial[] p1, int k) throws PolyDivideByZeroException {    // kP1的所有计算结果集
        ArrayList<Polynomial[]> rs = new ArrayList<>();
        Polynomial[] ap;
        rs.add(p1);
        if (k >= 2) {
            ap = pointAdd(p1, p1);
            rs.add(ap);
            if (k >= 3) {
                for (int i = 0; i < k - 2; i++) {
                    ap = pointAdd(p1, ap);
                    rs.add(ap);
                    if (PolynomialTest.ifEqualZero(ap[0]) && PolynomialTest.ifEqualZero(ap[1])) {
                        return rs;
                    }
                }
            }
        }
        return rs;
    }

    /**
     * 点p1和点p2之和
     * <p>p1+p2的结果点p3<br>
     *
     * @param p1 输入多项式点p1
     * @param p2 输入多项式点p2
     * @return 点p1与点p2之和
     * @throws PolyDivideByZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial[] pointAdd(Polynomial[] p1, Polynomial[] p2) throws PolyDivideByZeroException {
        Polynomial[] resultPoint = new Polynomial[2];
        Polynomial l;

        if (p1[0].equals(p2[0]) && !p1[1].equals(p2[1])) {
            return new Polynomial[]{new Polynomial(), new Polynomial()};
        }

        if (p1[0].equals(p2[0]) && p1[1].equals(p2[1])) {
            l = (p1[0].multiply(p1[0]).add(p1[1])).multiply(Method1.polynomialBezoutSolveQrS11(p1[0], curvePolynomial));
        } else {
            l = (p1[1].add(p2[1])).multiply(Method1.polynomialBezoutSolveQrS11(p1[0].add(p2[0]), curvePolynomial));
        }

        l = l.mod(curvePolynomial);
        resultPoint[0] = l.multiply(l).add(l).add(p1[0]).add(p2[0]).add(a2);
        resultPoint[0] = resultPoint[0].mod(curvePolynomial);
        resultPoint[1] = l.multiply(p1[0].add(resultPoint[0])).add(resultPoint[0]).add(p1[1]);
        resultPoint[1] = resultPoint[1].mod(curvePolynomial);

        return resultPoint;
    }

    public Polynomial getA2() {
        return a2;
    }

    public void setA2(Polynomial a2) {
        this.a2 = a2;
    }

    public Polynomial getA6() {
        return a6;
    }

    public void setA6(Polynomial a6) {
        this.a6 = a6;
    }

    public Polynomial getCurvePolynomial() {
        return curvePolynomial;
    }

    public void setCurvePolynomial(Polynomial curvePolynomial) {
        this.curvePolynomial = curvePolynomial;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
