/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  Jacobi.java   
 * @Package xxaqsxjc.method0   
 * @Description: 雅可比符号的计算 
 * @author: JinZhiyun    
 * @date:   2019年3月14日 下午10:43:44   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
package xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.Scanner;

/**   
 * @ClassName:  Jacobi   
 * @Description: 雅可比符号的计算
 * @author: JinZhiyun
 * @date:   2019年3月14日 下午12:52:32   
 *     
 * @Copyright: 2019 CyborgKuroChan Inc. All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class Jacobi {
	
	/**   
	 * @Fields a,b,c,d: 大整数常量，便于后边代码调用
	 */   
	private static final BigInteger a = new BigInteger("0");
	private static final BigInteger b = new BigInteger("1");
	private static final BigInteger c = new BigInteger("-1");
	private static final BigInteger d = new BigInteger("2");

	/**   
	 * @Title: Cacul_Jacobi   
	 * @Description: 雅可比符号的计算Jacobi(q/p)，返回1或0或-1     
	 * @param: @param q
	 * @param: @param p
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int Cacul_Jacobi(BigInteger q, BigInteger p) {
		if ((q.mod(p)).compareTo(a) == 0) {
			return 0;
		}
		if (q.compareTo(b) == 0) {
			return 1;
		}
		if (q.compareTo(c) == 0) {
			if (((p.subtract(b)).mod(d)).compareTo(a) == 0) {
				return 1;
			} else {
				return -1;
			}
		}
		if ((q.mod(d)).compareTo(a) != 0) {
			if (((((p.subtract(b)).divide(d)).multiply((q.subtract(b)).divide(d))).mod(d)).compareTo(a) == 0) {
				return Cacul_Jacobi(p.mod(q), q);
			} else {
				return (-1) * (Cacul_Jacobi(p.mod(q), q));
			}
		} else {
			BigInteger k = new BigInteger("0");
			while ((q.mod(d)).compareTo(a) == 0) {
				k = k.add(b);
				q = q.divide(d);
			}
			if ((k.mod(d)).compareTo(a) == 0) {
				return Cacul_Jacobi(q, p);
			} else {
				if (((((p.multiply(p)).subtract(b)).divide(new BigInteger("8"))).mod(d)).compareTo(a) == 0) {
					return Cacul_Jacobi(q, p);
				} else {
					return (-1) * (Cacul_Jacobi(q, p));
				}
			}
		}
	}

	public static void main(String[] args) {
		BigInteger q, p;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("请输入正整数q：");
			if (scan.hasNextBigInteger()) {
				q = scan.nextBigInteger();
			} else {
				System.out.println("发生错误！");
				scan.close();
				return;
			}
			System.out.print("请输入正整数p：");
			if (scan.hasNextBigInteger()) {
				p = scan.nextBigInteger();
			} else {
				System.out.println("发生错误！");
				scan.close();
				return;
			}

			System.out.println("Jacobi(q/p)=" + Jacobi.Cacul_Jacobi(q, p));
		}

	}

}
