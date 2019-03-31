/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  Zgsydl.java   
 * @Package xxaqsxjc.method0   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月15日 下午5:41:56   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.Scanner;

/**   
 * @ClassName:  Zgsydl   
 * @Description: 中国剩余定理求解
 * @author: JinZhiyun
 * @date:   2019年3月15日 下午5:39:27   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class Zgsydl {
	public static BigInteger[] M1;
	public static BigInteger[] M11;

	/**   
	 * @Title: zgsydl_solve   
	 * @Description: 中国剩余定理求解，方程组：x同余b 模 m（多个方程时系数存储在数组b[]，m[]中）m，返回值[]x，即解为x=x[0]+x[1]*q(q为整数)
	 * @param: @param b
	 * @param: @param m
	 * @param: @return      
	 * @return: BigInteger[]      
	 * @throws   
	 */
	public BigInteger[] zgsydl_solve(BigInteger[] b, BigInteger[] m) {
		M1 = new BigInteger[m.length];
		M11 = new BigInteger[m.length];
		BigInteger[] x = new BigInteger[2];
		x[0] = new BigInteger("0");
		BigInteger multi_m = new BigInteger("1");
		for (BigInteger i : m) {
			multi_m = multi_m.multiply(i);
		}
		x[1] = multi_m;

		BezoutEquationSolution bes = new BezoutEquationSolution();
		for (int i = 0; i < m.length; i++) {
			M1[i] = multi_m.divide(m[i]);
			M11[i] = bes.Bezout_solve_qr_s_11(M1[i], m[i]);
			x[0] = x[0].add(b[i].multiply(M1[i]).multiply(M11[i]));
		}
		return x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size;
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入方程个数：");
		if (scan.hasNextInt()) {
			size = scan.nextInt();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		BigInteger[] b = new BigInteger[size];
		BigInteger[] m = new BigInteger[size];
		for (int i = 0; i < size; i++) {
			BigInteger tmp;
			System.out.println("请输入方程x同余b" + (i + 1) + "(mod m" + (i + 1) + ")的系数b" + (i + 1) + ",m" + (i + 1) + ":");
			if (scan.hasNextBigInteger()) {
				tmp = scan.nextBigInteger();
			} else {
				System.out.println("发生错误！");
				scan.close();
				return;
			}
			b[i] = tmp;
			if (scan.hasNextBigInteger()) {
				tmp = scan.nextBigInteger();
			} else {
				System.out.println("发生错误！");
				scan.close();
				return;
			}
			m[i] = tmp;
		}
		scan.close();

		Zgsydl zgsydl=new Zgsydl();
		System.out.println(
				"该同余方程组的解为：x=" + zgsydl.zgsydl_solve(b, m)[0] + "+" + zgsydl.zgsydl_solve(b, m)[1] + "q(q为整数)");
	}

}
