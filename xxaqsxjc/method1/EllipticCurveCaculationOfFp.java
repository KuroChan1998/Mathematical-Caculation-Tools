/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  EllipticCurveCaculationOfFp.java   
 * @Package xxaqsxjc.method1   
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等   
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午2:27:48   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package xxaqsxjc.method1;

import java.math.BigInteger;
import java.util.ArrayList;

import xxaqsxjc.method0.BezoutEquationSolution;
import xxaqsxjc.method0.Legendre;

/**   
 * @ClassName:  EllipticCurveCaculationOfFp   
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算等  
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午2:26:00   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class EllipticCurveCaculationOfFp {
	/**   
	 * @Fields p : 素数p
	 */   
	public BigInteger p;
	
	/**   
	 * @Fields curvePara_a4 : 椭圆曲线y^2=x^3+a4*x+a6的参数a4
	 */   
	public BigInteger curvePara_a4 = new BigInteger("0");
	
	/**   
	 * @Fields curvePara_a6 : 椭圆曲线y^2=x^3+a4*x+a6的参数a6 
	 */   
	public BigInteger curvePara_a6 = new BigInteger("0");

	/**   
	 * @Title:  EllipticCurveCaculationOfFp   
	 * @Description: 构造器   
	 * @param:  @param p
	 * @param:  @param a4
	 * @param:  @param a6  
	 * @throws   
	 */  
	public EllipticCurveCaculationOfFp(BigInteger p, BigInteger a4, BigInteger a6) {
		this.p = p;
		this.curvePara_a4 = a4;
		this.curvePara_a6 = a6;
	}
         
	/**   
	 * @Title: pointAdd   
	 * @Description: 点p1和点p2之和   
	 * @param: @param p1
	 * @param: @param p2
	 * @param: @return      
	 * @return: BigInteger[]      
	 * @throws   
	 */
	public BigInteger[] pointAdd(BigInteger[] p1, BigInteger[] p2) {
		BigInteger[] resultPoint = new BigInteger[2];
		BigInteger l;
		if (p1[0].equals(p2[0]) && !p1[1].equals(p2[1])) {
			return new BigInteger[] { new BigInteger("0"), new BigInteger("0") };
		}

		if (p1[0].equals(p2[0]) && p1[1].equals(p2[1])) {
			l = (p1[0].multiply(p1[0]).multiply(new BigInteger("3")).add(curvePara_a4))
					.multiply(new BezoutEquationSolution().Bezout_solve_qr_s_11(new BigInteger("2").multiply(p1[1]),p));
		} else {
			l = (p2[1].subtract(p1[1]))
					.multiply(new BezoutEquationSolution().Bezout_solve_qr_s_11(p2[0].subtract(p1[0]), p));
		}
		l = l.mod(p);
		resultPoint[0] = l.multiply(l).subtract(p1[0]).subtract(p2[0]);
		resultPoint[0] = resultPoint[0].mod(p);
		resultPoint[1] = l.multiply(p1[0].subtract(resultPoint[0])).subtract(p1[1]);
		resultPoint[1] = resultPoint[1].mod(p);
		return resultPoint;
	}

	/**   
	 * @Title: kPointCacul   
	 * @Description: 点p1的k倍 
	 * @param: @param p1
	 * @param: @param k
	 * @param: @return      
	 * @return: BigInteger[]      
	 * @throws   
	 */
	public BigInteger[] kPointCacul(BigInteger[] p1, int k) { // kP1
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
	 * @Title: kPointSetCacul   
	 * @Description: p1的<=k倍的所有计算结果集  
	 * @param: @param p1
	 * @param: @param k
	 * @param: @return      
	 * @return: ArrayList<BigInteger[]>      
	 * @throws   
	 */
	public ArrayList<BigInteger[]> kPointSetCacul(BigInteger[] p1, int k) { // kP1的所有计算结果集
		ArrayList<BigInteger[]> rs = new ArrayList<BigInteger[]>();
		BigInteger[] ap = new BigInteger[2];
		rs.add(p1);
		if (k >= 2) {
			ap = pointAdd(p1, p1);
			rs.add(ap);
			if (k >= 3) {
				for (int i = 0; i < k - 2; i++) {
					ap = pointAdd(p1, ap);
					rs.add(ap);
					if (ap[0].equals(new BigInteger("0")) && ap[1].equals(new BigInteger("0"))) {
						return rs;
					}
				}
			}
		}
		return rs;
	}
	
	/**   
	 * @Title: ordFp   
	 * @Description: 椭圆曲线的阶
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */
	public BigInteger ordFp() {
		BigInteger sum=p.add(new BigInteger("1"));
		for (BigInteger i=new BigInteger("0");i.compareTo(p)<0;i=i.add(new BigInteger("1"))) {
			BigInteger right=(i.multiply(i).multiply(i)).add(i.multiply(curvePara_a4)).add(curvePara_a6);
			sum=sum.add(BigInteger.valueOf(Legendre.Cacul_Legendre(right, p)));
			//System.out.println("x="+i+",y^2="+right.mod(p)+" "+BigInteger.valueOf(Legendre.Cacul_Legendre(right, p)));
		}
		return sum;
		
	}
	public static void main(String[] args) {
		EllipticCurveCaculationOfFp eccfp=new EllipticCurveCaculationOfFp(new BigInteger("100823"),new BigInteger("3"),new BigInteger("7"));
		BigInteger [] p1= {new BigInteger("1"),new BigInteger("8811")};
		ArrayList<BigInteger[]> rs = eccfp.kPointSetCacul(p1, 27);
		for (int i = 0; i < rs.size(); i++) {
			System.out.println("x" + (i + 1) + "=" + rs.get(i)[0]);
			System.out.println("y" + (i + 1) + "=" + rs.get(i)[1]);
			System.out.println();
		}
		
		System.out.println(eccfp.kPointCacul(p1, 2)[0]);
		System.out.println(eccfp.kPointCacul(p1, 2)[1]);
		
		System.out.println(eccfp.ordFp());
	}

}
