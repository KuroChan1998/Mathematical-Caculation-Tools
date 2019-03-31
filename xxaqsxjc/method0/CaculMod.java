/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  CaculMod.java   
 * @Package xxaqsxjc.method0   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月14日 下午9:32:11   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
package xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.Scanner;

/**   
 * @ClassName:  CaculMod   
 * @Description: 任意余数的计算 
 * @author: JinZhiyun
 * @date:   2019年3月14日 下午12:24:16   
 *     
 * @Copyright: 2019 CyborgKuroChan Inc. All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class CaculMod {

	/**   
	 * @Title: cacul_repet_mod   
	 * @Description: 最小非负余数 
	 * @param: @param b
	 * @param: @param n
	 * @param: @param m
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */ 
	public static BigInteger cacul_repet_mod(BigInteger b, BigInteger n, BigInteger m) {
		return (b.modPow(n, m));
	}

	/**   
	 * @Title: transfer_absmin_rem   
	 * @Description: 就绝对值最小余数   
	 * @param: @param a
	 * @param: @param n
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */ 
	public static BigInteger transfer_absmin_rem(BigInteger a, BigInteger n) {
		if (n.compareTo(new BigInteger("0")) <= 0) {
			System.out.println("error!");
			return new BigInteger("0");
		}
		BigInteger tmp = a.mod(n);
		if (tmp.compareTo(n.divide(new BigInteger("2"))) > 0) {
			tmp = tmp.subtract(n);
		} else if ((tmp.negate()).compareTo(n.divide(new BigInteger("2"))) > 0) {
			tmp = tmp.add(n);
		}
		return tmp;
	}

	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws   
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger b, n, m, a, n1;
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入正整数b：");
		if (scan.hasNextBigInteger()) {
			b = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		System.out.print("请输入正整数n：");
		if (scan.hasNextBigInteger()) {
			n = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		System.out.print("请输入正整数m：");
		if (scan.hasNextBigInteger()) {
			m = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}

		System.out.println(b + "^" + n + "模" + m + "=" + CaculMod.cacul_repet_mod(b, n, m));

		System.out.print("请输入正整数a：");
		if (scan.hasNextBigInteger()) {
			a = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		System.out.print("请输入正整数n：");
		if (scan.hasNextBigInteger()) {
			n1 = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		scan.close();
		System.out.println(a + "模" + n1 + "=" + CaculMod.transfer_absmin_rem(a, n1));
	}

}
