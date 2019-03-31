/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  GoldwasserMicaliBinaryEncryption.java   
 * @Package EncyptionAlgorithm   
 * @Description: GoldwasserMicali加密算法（对二进制加密）
 * @author: JinZhiyun    
 * @date:   2019年3月15日 下午7:05:51   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package EncyptionAlgorithm;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import xxaqsxjc.method0.CommonFactorMultiple;
import xxaqsxjc.method0.Legendre;
import xxaqsxjc.method0.PrimeTest;

/**   
 * @ClassName:  GoldwasserMicaliBinaryEncryption   
 * @Description: GoldwasserMicali加密算法（对二进制加密）  
 * @author: JinZhiyun
 * @date:   2019年3月15日 下午7:01:50   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class GoldwasserMicaliBinaryEncryption {
	private int security_para_k = 20;
	private BigInteger p;// 私钥p(1024位）
	private BigInteger q;// 私钥q(1024位）
	private BigInteger y;// 干扰
	private Vector<BigInteger> ct = new Vector<BigInteger>();// 存储密文
	String d_pt;// 解密后的明文

	public BigInteger n;// 公钥n(2048位)
	public BigInteger x;// 公钥x(512位)
	String pt;// 明文

	
	/**  
	 * @Title:  getP <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getP() {
		return p;
	}

	/**  
	 * @Title:  setP <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setP(BigInteger p) {
		this.p = p;
	}

	/**  
	 * @Title:  getQ <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getQ() {
		return q;
	}

	/**  
	 * @Title:  setQ <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setQ(BigInteger q) {
		this.q = q;
	}

	/**  
	 * @Title:  getN <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getN() {
		return n;
	}

	/**  
	 * @Title:  setN <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setN(BigInteger n) {
		this.n = n;
	}

	/**  
	 * @Title:  getX <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getX() {
		return x;
	}

	/**  
	 * @Title:  setX <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setX(BigInteger x) {
		this.x = x;
	}

	/**   
	 * @Title:  GoldwasserMicaliBinaryEncryption   
	 * @Description:  构造器完成随机生成公钥和私钥等初始化工作       
	 * @param:  @param pt  
	 * @throws   
	 */  
	public GoldwasserMicaliBinaryEncryption(String pt) {
		// 明文pt
		this.pt = pt;
		// 随机生成1024位大素数p
		do {
			p = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(p, security_para_k) != 1);

		// 随机生成不等于p的1024位大素数q
		do {
			q = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(q, security_para_k) != 1 || p.compareTo(q) == 0);

		// 公钥n
		n = p.multiply(q);

		// 公钥x
		do {
			x = new BigInteger(512, new Random());
		} while (Legendre.Cacul_Legendre(x, p) != -1 || Legendre.Cacul_Legendre(x, q) != -1);

		d_pt = "";
	}

	/**   
	 * @Title: GoldwasserMicali_encrypt   
	 * @Description: 加密返回密文 ，将每个0或1加密成一个大整数
	 * @param: @return      
	 * @return: Vector<BigInteger>      
	 * @throws   
	 */
	public Vector<BigInteger> GoldwasserMicali_encrypt() {
		BigInteger tmp1, tmp2, tmp3;
		for (int i = 0; i < pt.length(); i++) {
			do {
				y = new BigInteger(n.bitLength(), new Random());
				y = y.mod(n);
			} while (CommonFactorMultiple.max_common_factor_xy(y, n).compareTo(new BigInteger("1")) != 0);
			tmp1 = y.mod(n);
			if (pt.charAt(i) == '0') {
				ct.addElement(tmp1.multiply(tmp1).mod(n));
			} else {
				tmp2 = x.mod(n);
				tmp3 = tmp1.multiply(tmp1).mod(n);
				ct.addElement(tmp2.multiply(tmp3).mod(n));
			}
		}
		return ct;
	}

	/**   
	 * @Title: GoldwasserMicali_decrypt   
	 * @Description: 返回解密密文后得到的明文，必须先调用 GoldwasserMicali_encrypt()即加密后在调用次方法     
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String GoldwasserMicali_decrypt() {
		for (int i = 0; i < pt.length(); i++) {
			if (Legendre.Cacul_Legendre(ct.get(i), p) == 1) {
				d_pt = d_pt + "0";
			} else if (Legendre.Cacul_Legendre(ct.get(i), p) == -1) {
				d_pt = d_pt + "1";
			} else {
				System.out.println("Error!");
				d_pt = d_pt + "#";
			}
		}
		return d_pt;
	}

	/**   
	 * @Title: show   
	 * @Description: 显示函数测试用     
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	public void show() {
		System.out.println("随机生成1024位大素数p（私钥）=" + p);
		System.out.println("随机生成1024位大素数q（私钥）=" + q);
		System.out.println("2048位公钥n=p*q=" + n);
		System.out.println("随机生成公钥x=" + x);
		GoldwasserMicali_encrypt();
		System.out.println("加密得到密文:");
		GoldwasserMicali_encrypt();
		System.out.println(toStringShowCt());
		System.out.println("解密密文得到的明文：" + GoldwasserMicali_decrypt());
	}

	/**   
	 * @Title: toStringShowCt   
	 * @Description: 美化密文输出格式   
	 * @param: @param ct
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String toStringShowCt() {
		String re = "";
		for (int i = 0; i < ct.size(); i++) {
			re += "C" + i + "=" + ct.get(i);
			if (i != ct.size() - 1) {
				re += '\n';
			}
		}
		return re;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		boolean flag;
		String str1;
		do {
			flag = false;
			System.out.print("请输入明文消息：");
			if (scan.hasNextLine()) {
				str1 = scan.nextLine();
			} else {
				System.out.println("发生错误！");
				scan.close();
				return;
			}
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) != '0' && str1.charAt(i) != '1') {
					System.out.println("仅能输入含0或1的字符串！");
					flag = true;
					break;
				}
			}
		} while (flag);
		scan.close();

		GoldwasserMicaliBinaryEncryption gmbe1 = new GoldwasserMicaliBinaryEncryption(str1);
		gmbe1.show();
		
		

	}

}
