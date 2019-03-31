/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  PolynomialCaculation.java   
 * @Package xxaqsxjc.method1   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月15日 上午10:06:52   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
package xxaqsxjc.method1;

import java.math.BigInteger;
import java.util.ArrayList;

import xxaqsxjc.method0.PrimeTest;


/**   
 * @ClassName:  PolynomialCaculation   
 * @Description: 提供多项式的一些延展计算， 最大公因式、最小公倍式、不可约、本原多项式判断等
 * @author: JinZhiyun
 * @date:   2019年3月15日 上午10:04:54   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class PolynomialCaculation {
	/**   
	 * @Fields result : 存储最大公因式和最小公倍式   
	 */   
	public static Polynomial result;

	/**   
	 * @Title: maxPolyCommonFactor   
	 * @Description: 最大公因式  
	 * @param: @param p1
	 * @param: @param p2
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial      
	 * @throws   
	 */ 
	public static Polynomial maxPolyCommonFactor(Polynomial p1, Polynomial p2) throws PolyDivideException {
		if (p1.effectiveMaxDeg < p2.effectiveMaxDeg) {
			Polynomial tmp = p2;
			p2 = p1;
			p1 = tmp;
		}
		Polynomial rp = p1.mod(p2);
		if (!rp.equals(new Polynomial()) && rp.effectiveMaxDeg >= 1) {
			p1 = p2;
			p2 = rp;
			maxPolyCommonFactor(p1, p2);
		} else {
			if (rp.equals(new Polynomial())) {
				result = p2;
			} else {
				result = rp;
			}
		}
		return result;
	}

	/**   
	 * @Title: minPolyCommonMultiple   
	 * @Description: 最小公倍式
	 * @param: @param p1
	 * @param: @param p2
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial      
	 * @throws   
	 */ 
	public static Polynomial minPolyCommonMultiple(Polynomial p1, Polynomial p2) throws PolyDivideException {
		return (p1.multiply(p2)).divide(maxPolyCommonFactor(p1, p2));
	}

	/**   
	 * @Title: ifReduciblePolynomial   
	 * @Description: 是否为不可约多项式，1是，0不是 
	 * @param: @param p1
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: int      
	 * @throws   
	 */ 
	public static int ifReduciblePolynomial(Polynomial p1) throws PolyDivideException { 
		int[] a = { 1 };
		if (p1.equals(new Polynomial(a))) {
			return 1;
		}
		for (int i = 2; i <= 1 + p1.effectiveMaxDeg / 2; i++) {
			a = new int[i];
			// a[i-1]=1;
			for (int j = 1 << (i - 1); j < 1 << i; j++) {
				String s = Integer.toBinaryString(j);
				s = new StringBuffer(s).reverse().toString();
				for (int k = 0; k < i; k++) {
					a[k] = Integer.parseInt(String.valueOf(s.charAt(k)));
				}
				if ((p1.mod(new Polynomial(a))).equals(new Polynomial())) {
					return 0;
				}

			}
		}
		return 1;
	}

	/**   
	 * @Title: ifPrimPolynomial   
	 * @Description: 本原多项式判断， 1是，0不是 
	 * @param: @param p1
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: int      
	 * @throws   
	 */ 
	public static int ifPrimPolynomial(Polynomial p1) throws PolyDivideException {
		int deg = (1 << p1.effectiveMaxDeg) - 1;
		int[] one = { 1 };
		int[] factorSet = new int[deg + 1];
		factorSet[deg] = 1;
		if (!(new Polynomial(factorSet)).mod(p1).equals(new Polynomial(one))) {
			return 0;
		}
		ArrayList<BigInteger> set = PrimeTest.prime_factor(new BigInteger(Integer.toString(deg)));
		for (int i = 0; i < set.size(); i++) {
			factorSet = new int[deg / set.get(i).intValue() + 1];
			factorSet[deg / set.get(i).intValue()] = 1;
			Polynomial tmp = new Polynomial(factorSet);
			if (tmp.mod(p1).equals(new Polynomial(one))) {
				return 0;
			}
		}
		return 1;
	}
	
	public static void main(String[] args) throws PolyDivideException {
		int[] a = { 1 };
		int[] b = { 1, 0, 0, 1 };
		Polynomial pa = new Polynomial(a);
		Polynomial pb = new Polynomial(b);
		System.out.println(PolynomialCaculation.maxPolyCommonFactor(pa, pb));
		System.out.println(PolynomialCaculation.minPolyCommonMultiple(pa, pb));
	}

}
