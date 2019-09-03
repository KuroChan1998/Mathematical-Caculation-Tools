package com.jzy.xxaqsxjc.method1;

import java.math.BigInteger;

import java.util.ArrayList;

import com.jzy.exception.ploynomial.EllipticCurveTooLargeKException;
import com.jzy.util.BigIntegerTest;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class EllipticCurveCalculationOfFp {

    /**
     * 规定计算k点的输入k的最大值
     */
    public static final int MAX_K = 1 << 14;

    /**
     * 素数p
     */
    private BigInteger p;

    /**
     * 椭圆曲线y^2=x^3+a4*x+a6的系数a4
     */
    private BigInteger a4;

    /**
     * 椭圆曲线y^2=x^3+a4*x+a6的系数a6
     */
    private BigInteger a6;

    /**
     * 构造器
     * <p>传入素数p、系数a4、a6生成椭圆曲线对象<br>
     *
     * @param p  素数p
     * @param a4 系数a4
     * @param a6 系数a6
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public EllipticCurveCalculationOfFp(BigInteger p, BigInteger a4, BigInteger a6) {
        this.p = p;
        this.a4 = a4;
        this.a6 = a6;
    }

    /**
     * 计算k倍的p1
     * <p>e.g. 4(x^2+x,x)-->返回计算结果(px,py)<br>
     *
     * @param p1 输入点p1
     * @param k  k倍的p1的k
     * @return k倍的p1
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public BigInteger[] kPoint(BigInteger[] p1, int k) throws EllipticCurveTooLargeKException {    // kP1
        if (k > MAX_K) {
            throw new EllipticCurveTooLargeKException("输入的k大于了" + MAX_K);
        }
        BigInteger[] ap = p1;
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
     * <p>e.g. k=4,p1=(2,3)<br>
     * -->返回计算结果1p1,2p1,3p1,4p1构成的ArrayList
     *
     * @param p1 输入点p1
     * @param k  k倍的p1的k
     * @return 所有k倍的p1的集合
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public ArrayList<BigInteger[]> kPointSet(BigInteger[] p1, int k) {    // kP1的所有计算结果集
        ArrayList<BigInteger[]> rs = new ArrayList<>();
        BigInteger[] ap;

        rs.add(p1);

        if (k >= 2) {
            ap = pointAdd(p1, p1);
            rs.add(ap);

            if (k >= 3) {
                for (int i = 0; i < k - 2; i++) {
                    ap = pointAdd(p1, ap);
                    rs.add(ap);

                    if (BigIntegerTest.ifEqualsZero(ap[0]) && BigIntegerTest.ifEqualsZero(ap[1])) {
                        return rs;
                    }
                }
            }
        }

        return rs;
    }

//    public static void main(String[] args) {
//        EllipticCurveCalculationOfFp eccfp = new EllipticCurveCalculationOfFp(new BigInteger("100823"),
//                new BigInteger("3"),
//                new BigInteger("7"));
//        BigInteger[] p1 = {new BigInteger("5"), new BigInteger("101")};
//        ArrayList<BigInteger[]> rs = eccfp.kPointSet(p1, 27);
//
//        for (int i = 0; i < rs.size(); i++) {
//            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
//            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
//            System.out.println();
//        }
//
//        System.out.println(eccfp.kPoint(p1, 10)[0]);
//        System.out.println(eccfp.kPoint(p1, 10)[1]);
//        System.out.println(eccfp.ordFp());
//    }

    /**
     * 计算椭圆曲线的阶
     *
     * @return 椭圆曲线的阶
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public BigInteger ordFp() {
        BigInteger sum = p.add(Method0.VALUE_1);

        for (BigInteger i = Method0.VALUE_0; i.compareTo(p) < 0; i = i.add(Method0.VALUE_1)) {
            BigInteger right = (i.multiply(i).multiply(i)).add(i.multiply(a4)).add(a6);

            sum = sum.add(BigInteger.valueOf(Method0.legendre(right, p)));

            // System.out.println("x="+i+",y^2="+right.mod(p)+" "+BigInteger.valueOf(Legendre.legendre(right, p)));
        }

        return sum;
    }

    /**
     * 点p1和点p2之和
     * <p>p1+p2的结果点p3<br>
     *
     * @param p1 输入点p1
     * @param p2 输入点p2
     * @return 点p1与点p2之和
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public BigInteger[] pointAdd(BigInteger[] p1, BigInteger[] p2) {
        BigInteger[] resultPoint = new BigInteger[2];
        BigInteger l;

        if (p1[0].equals(p2[0]) && !p1[1].equals(p2[1])) {
            return new BigInteger[]{Method0.VALUE_0, Method0.VALUE_0};
        }

        if (p1[0].equals(p2[0]) && p1[1].equals(p2[1])) {
            l = (p1[0].multiply(p1[0])
                    .multiply(new BigInteger("3"))
                    .add(a4)).multiply(Method0.bezoutSolveQrS11(Method0.VALUE_2.multiply(p1[1]), p));
        } else {
            l = (p2[1].subtract(p1[1])).multiply(Method0.bezoutSolveQrS11(p2[0].subtract(p1[0]), p));
        }

        l = l.mod(p);
        resultPoint[0] = l.multiply(l).subtract(p1[0]).subtract(p2[0]);
        resultPoint[0] = resultPoint[0].mod(p);
        resultPoint[1] = l.multiply(p1[0].subtract(resultPoint[0])).subtract(p1[1]);
        resultPoint[1] = resultPoint[1].mod(p);

        return resultPoint;
    }

    public BigInteger getA4() {
        return a4;
    }

    public void setA4(BigInteger a4) {
        this.a4 = a4;
    }

    public BigInteger getA6() {
        return a6;
    }

    public void setA6(BigInteger a6) {
        this.a6 = a6;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
