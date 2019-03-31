/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  RSAEncryption.java   
 * @Package EncyptionAlgorithm   
 * @Description: 实现RSA加密算法  
 * @author: JinZhiyun    
 * @date:   2019年3月15日 下午6:46:27   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package EncyptionAlgorithm;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import xxaqsxjc.method0.BezoutEquationSolution;
import xxaqsxjc.method0.CaculMod;
import xxaqsxjc.method0.CommonFactorMultiple;
import xxaqsxjc.method0.PrimeTest;

/**   
 * @ClassName:  RSAEncryption   
 * @Description: 实现RSA加密算法  
 * @author: JinZhiyun
 * @date:   2019年3月15日 下午6:45:59   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class RSAEncryption {
	private int security_para_k = 20;
	private BigInteger d; // 私钥d
	private BigInteger p, q;// 1024位

	private BigInteger Euler_n;// 公钥n的欧拉函数值
	private Vector<BigInteger> math_pt = new Vector<BigInteger>();// 存储明文的数字化信息
	private Vector<BigInteger> math_ct = new Vector<BigInteger>();// 存储密文的数字化信息
	private String ct; // 密文
	private String d_pt;// 解密后的明文

	public BigInteger n; // 公钥n，2048位
	public BigInteger e;// 公钥e
	public String pt;// 明文

	/**  
	 * @Title:  getD <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getD() {
		return d;
	}

	/**  
	 * @Title:  setD <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setD(BigInteger d) {
		this.d = d;
	}

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
	 * @Title:  getE <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getE() {
		return e;
	}

	/**  
	 * @Title:  setE <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setE(BigInteger e) {
		this.e = e;
	}
	
	/**   
	 * @Title:  RSAEncryption   
	 * @Description:  构造器完成随机生成公钥和私钥等初始化工作 
	 * @param:  @param pt  构造器入参为明文pt
	 * @throws   
	 */  
	public RSAEncryption(String pt) {
		// 随机生成素数p
		do {
			p = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(p, security_para_k) != 1);

		// 随机生成不等于p的素数q
		do {
			q = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(q, security_para_k) != 1 || p.compareTo(q) == 0);

		// 公钥n
		n = p.multiply(q);
		Euler_n = (p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1"))));

		// 公钥e
		do {
			e = new BigInteger(Euler_n.bitLength(), new Random());
			e = e.mod(Euler_n);
		} while (CommonFactorMultiple.max_common_factor_xy(e, Euler_n).compareTo(new BigInteger("1")) != 0
				|| e.compareTo(new BigInteger("1")) <= 0);

		// 私钥d
		BezoutEquationSolution bes1 = new BezoutEquationSolution();
		d = bes1.Bezout_solve_qr_s_11(e, Euler_n);

		// 明文
		this.pt = pt;

		ct = "";
		d_pt = "";
	}

	/**   
	 * @Title: RSA_encrypt   
	 * @Description: 加密返回密文   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String RSA_encrypt() {
		BigInteger tmp1;
		for (int i = 0; i < pt.length(); i++) {
			math_pt.addElement(BigInteger.valueOf((long) pt.charAt(i)));
			math_ct.addElement(CaculMod.cacul_repet_mod(math_pt.get(i), e, n));
			tmp1 = math_ct.get(i).mod(new BigInteger("95"));
			ct = ct + (char) (tmp1.intValue() + 32);
		}
		return ct;
	}

	/**   
	 * @Title: RSA_decrypt   
	 * @Description: 返回解密密文后得到的明文，必须先调用 RSA_encrypt()即加密后在调用次方法
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String RSA_decrypt() {
		BigInteger tmp2;
		if (ct != "") {
			for (int i = 0; i < pt.length(); i++) {
				tmp2 = CaculMod.cacul_repet_mod(math_ct.get(i), d, n);
				d_pt = d_pt + (char) (tmp2.intValue());
			}
		} else {
			System.out.println("请先加密！");
		}
		return d_pt;
	}

	/**   
	 * @Title: RSA_get_math_pt   
	 * @Description: 返回数字化的明文  
	 * @param: @return      
	 * @return: Vector<BigInteger>      
	 * @throws   
	 */
	public Vector<BigInteger> RSA_get_math_pt() {
		return math_pt;
	}

	/**   
	 * @Title: RSA_get_math_ct   
	 * @Description: 返回数字化的密文  
	 * @param: @return      
	 * @return: Vector<BigInteger>      
	 * @throws   
	 */
	public Vector<BigInteger> RSA_get_math_ct() {
		return math_ct;
	}

	/**   
	 * @Title: show   
	 * @Description: 显示函数测试用  
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	public void show() {
		System.out.println("随机生成1024位大素数p=" + p);
		System.out.println("随机生成1024位大素数q=" + q);
		System.out.println("2048位公钥n=p*q=" + n);
		System.out.println("随机生成公钥e=" + e);
		System.out.println("进而生成私钥d=" + d);
		// 单字符加密
		System.out.println("加密得到密文：" + RSA_encrypt());
		System.out.println("解密密文得到的明文：" + RSA_decrypt());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入明文消息：");
		String str1;
		if (scan.hasNextLine()) {
			str1 = scan.nextLine();
		} else {
			System.out.println("发生错误！");
			scan.close();
			return;
		}
		scan.close();

		RSAEncryption rsa1 = new RSAEncryption(str1);
		rsa1.show();
	}

}
