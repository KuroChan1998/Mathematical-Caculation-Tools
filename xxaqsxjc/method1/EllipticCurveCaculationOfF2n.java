/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  EllipticCurveCaculationOfF2n.java   
 * @Package xxaqsxjc.method1   
 * @Description: F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等    
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午2:31:54   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package xxaqsxjc.method1;

import java.util.ArrayList;

/**   
 * @ClassName:  EllipticCurveCaculationOfF2n   
 * @Description: F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算等    
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午2:31:07   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class EllipticCurveCaculationOfF2n {
	/**   
	 * @Fields curvePolynomial : 多项式   
	 */   
	public Polynomial curvePolynomial;
	
	/**   
	 * @Fields curvePara_a2 : 系数a2   
	 */   
	public Polynomial curvePara_a2 = new Polynomial();
	
	/**   
	 * @Fields curvePara_a6 : 系数a6
	 */   
	public Polynomial curvePara_a6 = new Polynomial();

	/**   
	 * @Title:  EllipticCurveCaculationOfF2n   
	 * @Description:   构造器 
	 * @param:  @param curvePolynomial  
	 * @throws   
	 */  
	public EllipticCurveCaculationOfF2n(Polynomial curvePolynomial, Polynomial curvePara_a2, Polynomial curvePara_a6) {
		this.curvePolynomial = (Polynomial) curvePolynomial.clone();
		this.curvePara_a2 = curvePara_a2;
		this.curvePara_a6 = curvePara_a6;
	}

	/**   
	 * @Title: pointAdd   
	 * @Description: 点p1和点p2之和     
	 * @param: @param p1
	 * @param: @param p2
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial[]      
	 * @throws   
	 */
	public Polynomial[] pointAdd(Polynomial[] p1, Polynomial[] p2) throws PolyDivideException {
		Polynomial[] resultPoint = new Polynomial[2];
		Polynomial l;
		if (p1[0].equals(p2[0]) && !p1[1].equals(p2[1])) {
			return new Polynomial[] { new Polynomial(), new Polynomial() };
		}

		if (p1[0].equals(p2[0]) && p1[1].equals(p2[1])) {
			l = (p1[0].multiply(p1[0]).add(p1[1])).multiply(
					new PolynomialBezoutEquationSolution().PolynomialBezout_solve_qr_s_11(p1[0], curvePolynomial));
		} else {
			l = (p1[1].add(p2[1])).multiply(new PolynomialBezoutEquationSolution()
					.PolynomialBezout_solve_qr_s_11(p1[0].add(p2[0]), curvePolynomial));
		}
		l = l.mod(curvePolynomial);
		resultPoint[0] = l.multiply(l).add(l).add(p1[0]).add(p2[0]).add(curvePara_a2);
		resultPoint[0] = resultPoint[0].mod(curvePolynomial);
		resultPoint[1] = l.multiply(p1[0].add(resultPoint[0])).add(resultPoint[0]).add(p1[1]);
		resultPoint[1] = resultPoint[1].mod(curvePolynomial);
		return resultPoint;
	}

	/**   
	 * @Title: kPointCacul   
	 * @Description: p1的k倍  
	 * @param: @param p1
	 * @param: @param k
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial[]      
	 * @throws   
	 */
	public Polynomial[] kPointCacul(Polynomial[] p1, int k) throws PolyDivideException { // kP1
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
	 * @Title: kPointSetCacul   
	 * @Description: p1的<=k倍的所有计算结果集     
	 * @param: @param p1
	 * @param: @param k
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: ArrayList<Polynomial[]>      
	 * @throws   
	 */
	public ArrayList<Polynomial[]> kPointSetCacul(Polynomial[] p1, int k) throws PolyDivideException { // kP1的所有计算结果集
		ArrayList<Polynomial[]> rs = new ArrayList<Polynomial[]>();
		Polynomial[] ap = new Polynomial[2];
		rs.add(p1);
		if (k >= 2) {
			ap = pointAdd(p1, p1);
			rs.add(ap);
			if (k >= 3) {
				for (int i = 0; i < k - 2; i++) {
					ap = pointAdd(p1, ap);
					rs.add(ap);
					if (ap[0].equals(new Polynomial()) && ap[1].equals(new Polynomial())) {
						return rs;
					}
				}
			}
		}
		return rs;
	}

	public static void main(String[] args) throws PolyDivideException {
		// TODO Auto-generated method stub
		Polynomial curvePolynomial = new Polynomial(new int[] { 1, 0, 1, 1, 1, 0, 0, 0, 1 });
//		Polynomial curvePolynomial=new Polynomial(new int[] {1,1,0,1});
		EllipticCurveCaculationOfF2n ecc = new EllipticCurveCaculationOfF2n(curvePolynomial,new Polynomial(),new Polynomial());
		int[] a = { 1, 0, 0, 1 };
		int[] b = { 0, 1, 1, 1, 0, 1, 1, 1 };
//		int []a= {0,1};
//		int []b= {1,1,1};
		Polynomial[] p1 = { new Polynomial(a), new Polynomial(b) };
		ArrayList<Polynomial[]> rs = ecc.kPointSetCacul(p1, 34);
		for (int i = 0; i < rs.size(); i++) {
			System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
			System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
			System.out.println();
		}

		System.out.println(ecc.kPointCacul(p1, 24)[0]);
		System.out.println(ecc.kPointCacul(p1, 24)[1]);
	}

}
