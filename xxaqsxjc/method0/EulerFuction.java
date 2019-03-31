/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  EulerFuction.java   
 * @Package xxaqsxjc.method0   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月14日 下午10:41:49   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
package xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.Scanner;

/**   
 * @ClassName:  EulerFuction   
 * @Description: 欧拉函数的计算  
 * @author: JinZhiyun
 * @date:   2019年3月14日 下午10:40:30   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class EulerFuction {
	/**   
	 * @Title: Euler   
	 * @Description: 欧拉函数值的计算     
	 * @param: @param n
	 * @param: @return      
	 * @return: BigInteger      
	 * @throws   
	 */ 
	public static BigInteger Euler(BigInteger n) {
		BigInteger size = new BigInteger("0");
		BigInteger i = new BigInteger("1");
		while (i.compareTo(n) <= 0) {
			if (CommonFactorMultiple.max_common_factor_xy(i, n).compareTo(new BigInteger("1")) == 0) {
				size = size.add(new BigInteger("1"));
			}
			i = i.add(new BigInteger("1"));
		}
		return size;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger n;
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入正整数n：");
		if (scan.hasNextBigInteger()) {
			n = scan.nextBigInteger();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		scan.close();
		System.out.println("n的欧拉函数值为：" + EulerFuction.Euler(n));
	}

}
