/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: EllipticCurveCalculationOfFp.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午2:27:48
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method1;

import java.math.BigInteger;
import java.util.ArrayList;

import com.jzy.exception.ploynomial.EllipticCurveTooLargeKException;
import com.jzy.util.BigIntegerTest;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * @ClassName: EllipticCurveCalculationOfFp
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午2:26:00
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class EllipticCurveCalculationOfFp {
    /**
     * @Fields MAX_K : 规定计算k点的最大k值
     */
    public static final int MAX_K = 1 << 14;

    /**
     * @Fields p : 素数p
     */
    private BigInteger p;

    /**
     * @Fields a4 : 椭圆曲线y^2=x^3+a4*x+a6的参数a4
     */
    private BigInteger a4;

    /**
     * @Fields a6 : 椭圆曲线y^2=x^3+a4*x+a6的参数a6
     */
    private BigInteger a6;

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
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

    /**
     * @throws
     * @Title: EllipticCurveCalculationOfFp
     * @Description: 构造器
     * @param: @param p
     * @param: @param a4
     * @param: @param a6
     */
    public EllipticCurveCalculationOfFp(BigInteger p, BigInteger a4, BigInteger a6) {
        this.p = p;
        this.a4 = a4;
        this.a6 = a6;
    }

    /**
     * @throws
     * @Title: pointAdd
     * @Description: 点p1和点p2之和
     * @param: @param p1
     * @param: @param p2
     * @param: @return
     * @return: BigInteger[]
     */
    public BigInteger[] pointAdd(BigInteger[] p1, BigInteger[] p2) {
        BigInteger[] resultPoint = new BigInteger[2];
        BigInteger l;
        if (p1[0].equals(p2[0]) && !p1[1].equals(p2[1])) {
            return new BigInteger[]{Method0.VALUE_0, Method0.VALUE_0};
        }

        if (p1[0].equals(p2[0]) && p1[1].equals(p2[1])) {
            l = (p1[0].multiply(p1[0]).multiply(new BigInteger("3")).add(a4))
                    .multiply(Method0.bezoutSolveQrS11(Method0.VALUE_2.multiply(p1[1]), p));
        } else {
            l = (p2[1].subtract(p1[1]))
                    .multiply(Method0.bezoutSolveQrS11(p2[0].subtract(p1[0]), p));
        }
        l = l.mod(p);
        resultPoint[0] = l.multiply(l).subtract(p1[0]).subtract(p2[0]);
        resultPoint[0] = resultPoint[0].mod(p);
        resultPoint[1] = l.multiply(p1[0].subtract(resultPoint[0])).subtract(p1[1]);
        resultPoint[1] = resultPoint[1].mod(p);
        return resultPoint;
    }

    /**
     * @throws
     * @Title: kPoint
     * @Description: 点p1的k倍
     * @param: @param p1
     * @param: @param k
     * @param: @return
     * @return: BigInteger[]
     */
    public BigInteger[] kPoint(BigInteger[] p1, int k) { // kP1
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
     * @throws
     * @Title: kPointSet
     * @Description: p1的 <=k倍的所有计算结果集
     * @param: @param p1
     * @param: @param k
     * @param: @return
     * @return: ArrayList<BigInteger       [       ]>
     */
    public ArrayList<BigInteger[]> kPointSet(BigInteger[] p1, int k) { // kP1的所有计算结果集
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

    /**
     * @throws
     * @Title: ordFp
     * @Description: 椭圆曲线的阶
     * @param: @return
     * @return: BigInteger
     */
    public BigInteger ordFp() {
        BigInteger sum = p.add(Method0.VALUE_1);
        for (BigInteger i = Method0.VALUE_0; i.compareTo(p) < 0; i = i.add(Method0.VALUE_1)) {
            BigInteger right = (i.multiply(i).multiply(i)).add(i.multiply(a4)).add(a6);
            sum = sum.add(BigInteger.valueOf(Method0.legendre(right, p)));
            //System.out.println("x="+i+",y^2="+right.mod(p)+" "+BigInteger.valueOf(Legendre.legendre(right, p)));
        }
        return sum;

    }

    public static void main(String[] args) {
        EllipticCurveCalculationOfFp eccfp = new EllipticCurveCalculationOfFp(new BigInteger("100823"), new BigInteger("3"), new BigInteger("7"));
        BigInteger[] p1 = {new BigInteger("5"), new BigInteger("101")};
        ArrayList<BigInteger[]> rs = eccfp.kPointSet(p1, 27);
        for (int i = 0; i < rs.size(); i++) {
            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
            System.out.println();
        }
        System.out.println(eccfp.kPoint(p1, 10)[0]);
        System.out.println(eccfp.kPoint(p1, 10)[1]);
        System.out.println(eccfp.ordFp());
    }

}
