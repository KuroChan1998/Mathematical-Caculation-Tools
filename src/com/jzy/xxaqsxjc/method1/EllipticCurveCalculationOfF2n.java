/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: EllipticCurveCalculationOfF2n.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午2:31:54
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.EllipticCurveTooLargeKException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;

import java.util.ArrayList;

/**
 * @ClassName: EllipticCurveCalculationOfF2n
 * @Description: F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午2:31:07
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class EllipticCurveCalculationOfF2n {
    /**
     * @Fields MAX_K : 规定计算k点的最大k值
     */
    public static final int MAX_K = 1 << 14;

    /**
     * @Fields curvePolynomial : 多项式
     */
    private Polynomial curvePolynomial;

    /**
     * @Fields a2 : 系数a2
     */
    private Polynomial a2;

    /**
     * @Fields a6 : 系数a6
     */
    private Polynomial a6;

    public Polynomial getCurvePolynomial() {
        return curvePolynomial;
    }

    public void setCurvePolynomial(Polynomial curvePolynomial) {
        this.curvePolynomial = curvePolynomial;
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

    /**
     * @throws
     * @Title: EllipticCurveCalculationOfF2n
     * @Description: 构造器
     * @param: @param curvePolynomial
     */
    public EllipticCurveCalculationOfF2n(Polynomial curvePolynomial, Polynomial a2, Polynomial a6) {
        this.curvePolynomial = (Polynomial) curvePolynomial.clone();
        this.a2 = a2;
        this.a6 = a6;
    }

    /**
     * @throws
     * @Title: pointAdd
     * @Description: 点p1和点p2之和
     * @param: @param p1
     * @param: @param p2
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial[]
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

    /**
     * @throws
     * @Title: kPoint
     * @Description: p1的k倍
     * @param: @param p1
     * @param: @param k
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial[]
     */
    public Polynomial[] kPoint(Polynomial[] p1, int k) throws PolyDivideByZeroException { // kP1
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
     * @return java.util.ArrayList<com.jzy.xxaqsxjc.method1.Polynomial   [   ]>
     * @author JinZhiyun
     * @Description k倍的所有计算结果集
     * @Date 10:39 2019/8/28
     * @Param [p1, k]
     **/
    public ArrayList<Polynomial[]> kPointSet(Polynomial[] p1, int k) throws PolyDivideByZeroException { // kP1的所有计算结果集
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

    public static void main(String[] args) throws PolyDivideByZeroException {
        // TODO Auto-generated method stub
        Polynomial curvePolynomial = new Polynomial(new int[]{1, 0, 1, 1, 1, 0, 0, 0, 1});
//		Polynomial curvePolynomial=new Polynomial(new int[] {1,1,0,1});
        EllipticCurveCalculationOfF2n ecc = new EllipticCurveCalculationOfF2n(curvePolynomial, new Polynomial(), new Polynomial());
        int[] a = {1, 0, 0, 1};
        int[] b = {0, 1, 1, 1, 0, 1, 1, 1};
//		int []a= {0,1};
//		int []b= {1,1,1};
        Polynomial[] p1 = {new Polynomial(a), new Polynomial(b)};
        ArrayList<Polynomial[]> rs = ecc.kPointSet(p1, 34);
        for (int i = 0; i < rs.size(); i++) {
            System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
            System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
            System.out.println();
        }

        System.out.println(ecc.kPoint(p1, 10000)[0]);
        System.out.println(ecc.kPoint(p1, 10000)[1]);
    }

}
