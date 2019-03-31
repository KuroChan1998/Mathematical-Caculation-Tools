/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  BezoutEquationSolution.java   
 * @Package xxaqsxjc.method0   
 * @Description:  贝祖等式求解：s*x+t*y=(x,y)，求出s,t 
 * @author: JinZhiyun    
 * @date:   2019年3月14日 下午4:35:05   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
package xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.Scanner;

/**   
 * @ClassName:  BezoutEquationSolution   
 * @Description:  贝祖等式求解：s*x+t*y=(x,y)，求出s,t
 * @author: JinZhiyun
 * @date:   2019年3月14日 下午3:26:01   
 *     
 * @Copyright: 2019 CyborgKuroChan Inc. All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class BezoutEquationSolution {
	private final int Bezout_solve_qr_size = 1000000;
	private boolean flag;
	public int j;
	
	/**   
	 * @Fields s,t,q,r : 辅助数组   
	 */   
	public BigInteger[] s;
	public BigInteger[] t;
	public BigInteger[] q;
	public BigInteger[] r;
	
	/**   
	 * @Fields result_index : 结果值在数组s,t中的索引值
	 */   
	public int result_index = 0;
	
	/**   
	 * @Fields result_s : 求出的系数s 
	 */   
	public BigInteger result_s = new BigInteger("0");
	
	/**   
	 * @Fields result_st : 求出的系数s和t  
	 */   
	public BigInteger[] result_st = new BigInteger[2];

	/**   
	 * @Title:  BezoutEquationSolution   
	 * @Description: 类构造函数，用以初始化
	 * @param:    
	 * @throws   
	 */  
	public BezoutEquationSolution() {
		s = new BigInteger[Bezout_solve_qr_size];
		t = new BigInteger[Bezout_solve_qr_size];
		q = new BigInteger[Bezout_solve_qr_size];
		r = new BigInteger[Bezout_solve_qr_size];
		j = 0;
		flag = true;
	}

	/**   
	 * @Title: Bezout_solve_qr_index   
	 * @Description: 计算出 result_index（结果值在数组s,t中的索引值）
	 * @param: @param x
	 * @param: @param y
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public int Bezout_solve_qr_index(BigInteger x, BigInteger y) {
		if (flag == false) {
			j = 0;
			flag = true;
		}
		s[1] = new BigInteger("1");
		s[2] = new BigInteger("0");
		t[1] = new BigInteger("0");
		t[2] = new BigInteger("1");
		r[j] = x;
		r[j + 1] = y;
		q[j + 2] = r[j].divide(r[j + 1]);
		r[j + 2] = r[j].mod(r[j + 1]);

		s[j + 3] = s[j + 1].subtract(q[j + 2].multiply(s[j + 2]));
		t[j + 3] = t[j + 1].subtract(q[j + 2].multiply(t[j + 2]));
		x = r[j + 1];
		y = r[j + 2];

		if (y.toString() == "0") {
			flag = false;
			result_index = j + 2;
			return result_index;
		} else {
			j += 1;
			Bezout_solve_qr_index(x, y);
		}
		return result_index;
	}

	/**   
	 * @Title: Bezout_solve_qr_s_11   
	 * @Description: 计算出系数s（x的逆元，只为正）
	 * @param: @param x
	 * @param: @param y
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */ 
	public BigInteger Bezout_solve_qr_s_11(BigInteger x, BigInteger y) {
		if (flag == false) {
			j = 0;
			flag = true;
		}
		s[1] = new BigInteger("1");
		s[2] = new BigInteger("0");
		t[1] = new BigInteger("0");
		t[2] = new BigInteger("1");
		r[j] = x;
		r[j + 1] = y;
		q[j + 2] = r[j].divide(r[j + 1]);
		r[j + 2] = r[j].mod(r[j + 1]);

		s[j + 3] = s[j + 1].subtract(q[j + 2].multiply(s[j + 2]));
		t[j + 3] = t[j + 1].subtract(q[j + 2].multiply(t[j + 2]));
		x = r[j + 1];
		y = r[j + 2];

		if (y.toString() == "0") {
			flag = false;
			BigInteger tmp = new BigInteger("0");
			if (s[j + 2].compareTo(tmp) >= 0) {
				result_s = s[j + 2];
				return result_s;
			} else {
				result_s = s[j + 2].add(r[1]);
				return result_s;
			}

		} else {
			j += 1;
			Bezout_solve_qr_s_11(x, y);
		}
		return result_s;
	}

	/**   
	 * @Title: Bezout_solve_qr_s_1   
	 * @Description: 计算出系数s（x的逆元，可以负）  
	 * @param: @param x
	 * @param: @param y
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */ 
	public BigInteger Bezout_solve_qr_s_1(BigInteger x, BigInteger y) { // x的逆元，可以负
		if (flag == false) {
			j = 0;
			flag = true;
		}
		s[1] = new BigInteger("1");
		s[2] = new BigInteger("0");
		t[1] = new BigInteger("0");
		t[2] = new BigInteger("1");
		r[j] = x;
		r[j + 1] = y;
		q[j + 2] = r[j].divide(r[j + 1]);
		r[j + 2] = r[j].mod(r[j + 1]);

		s[j + 3] = s[j + 1].subtract(q[j + 2].multiply(s[j + 2]));
		t[j + 3] = t[j + 1].subtract(q[j + 2].multiply(t[j + 2]));
		x = r[j + 1];
		y = r[j + 2];

		if (y.toString() == "0") {
			flag = false;
			result_s = s[j + 2];
			return result_s;
		} else {
			j += 1;
			Bezout_solve_qr_s_1(x, y);
		}
		return result_s;
	}

	/**   
	 * @Title: Bezout_solve_qr_st   
	 * @Description: 计算出系数s,t   
	 * @param: @param x
	 * @param: @param y
	 * @param: @return      
	 * @return: BigInteger[]      
	 * @throws   
	 */ 
	public BigInteger[] Bezout_solve_qr_st(BigInteger x, BigInteger y) { 
		if (flag == false) {
			j = 0;
			flag = true;
		}
		s[1] = new BigInteger("1");
		s[2] = new BigInteger("0");
		t[1] = new BigInteger("0");
		t[2] = new BigInteger("1");
		r[j] = x;
		r[j + 1] = y;
		q[j + 2] = r[j].divide(r[j + 1]);
		r[j + 2] = r[j].mod(r[j + 1]);

		s[j + 3] = s[j + 1].subtract(q[j + 2].multiply(s[j + 2]));
		t[j + 3] = t[j + 1].subtract(q[j + 2].multiply(t[j + 2]));
		x = r[j + 1];
		y = r[j + 2];

		if (y.toString() == "0") {
			flag = false;
			result_st[0] = s[j + 2];
			result_st[1] = t[j + 2];
			return result_st;
		} else {
			j += 1;
			Bezout_solve_qr_st(x, y);
		}
		return result_st;
	}

	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws   
	 */ 
	public static void main(String[] args) {
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

		System.out.println("最大公因数为：" + CommonFactorMultiple.max_common_factor_xy(x, y));
		BezoutEquationSolution bes = new BezoutEquationSolution();
		BigInteger[] tmp1 = bes.Bezout_solve_qr_st(x, y);
		System.out.println("s=" + tmp1[0] + ",t=" + tmp1[1]);
		System.out.println("正数s=" + bes.Bezout_solve_qr_s_11(x, y));
		System.out.println(x+" "+y);
	}

}
