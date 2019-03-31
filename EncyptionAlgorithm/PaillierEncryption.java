/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  PaillierEncryption.java   
 * @Package EncyptionAlgorithm   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月15日 下午6:57:00   
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
import xxaqsxjc.method0.PrimeTest;

/**   
 * @ClassName:  PaillierEncryption   
 * @Description: Paillier加密算法
 * @author: JinZhiyun
 * @date:   2019年3月15日 下午6:55:37   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class PaillierEncryption {
	private int security_para_k = 20;
	private BigInteger p;// 1024位大素数
	private BigInteger q;// 1024位大素数
	private BigInteger Euler_n;// 私钥
	private BigInteger L_1;// 私钥
	private Vector<BigInteger> math_pt = new Vector<BigInteger>();// 存储明文的数字化信息
	private Vector<BigInteger> math_ct = new Vector<BigInteger>();// 存储密文的数字化信息
	private String ct; // 密文
	private String d_pt;// 解密后的明文

	public BigInteger n;// 公钥n
	public BigInteger g;// 公钥，n^2的简化剩余，n+1
	public BigInteger r;// 随机数r
	public String pt;// 明文

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
	 * @Title:  getEuler_n <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getEuler_n() {
		return Euler_n;
	}

	/**  
	 * @Title:  setEuler_n <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setEuler_n(BigInteger euler_n) {
		Euler_n = euler_n;
	}

	/**  
	 * @Title:  getL_1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getL_1() {
		return L_1;
	}

	/**  
	 * @Title:  setL_1 <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setL_1(BigInteger l_1) {
		L_1 = l_1;
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
	 * @Title:  getG <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public BigInteger getG() {
		return g;
	}

	/**  
	 * @Title:  setG <BR>  
	 * @Description: please write your description <BR>  
	 * @return: BigInteger <BR>  
	 */
	public void setG(BigInteger g) {
		this.g = g;
	}

	/**   
	 * @Title:  PaillierEncryption   
	 * @Description:    构造器完成随机生成公钥和私钥等初始化工作    
	 * @param:  @param pt  
	 * @throws   
	 */  
	public PaillierEncryption(String pt) {
		// 明文
		this.pt = pt;

		// 随机1024位大素数p
		do {
			p = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(p, security_para_k) != 1);

		// 随机生成不等于p的1024位大素数q
		do {
			q = new BigInteger(1024, security_para_k, new Random());
		} while (PrimeTest.MillerRabin(q, security_para_k) != 1 || p.compareTo(q) == 0);

		// 公钥n2048位
		n = p.multiply(q);
		// 公钥g，n^2的简化剩余，n+1
		g = n.add(new BigInteger("1"));
		// 私钥λ
		Euler_n = (p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1"))));
		// 私钥μ
		BezoutEquationSolution bes1 = new BezoutEquationSolution();
		L_1 = bes1.Bezout_solve_qr_s_11(Euler_n, n);

		ct = "";
		d_pt = "";
	}

	/**   
	 * @Title: Paillier_encrypt   
	 * @Description: 加密返回密文  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String Paillier_encrypt() {
		BigInteger tmp1, tmp2, tmp3;
		for (int i = 0; i < pt.length(); i++) {
			math_pt.addElement(BigInteger.valueOf((long) pt.charAt(i)));
			tmp1 = CaculMod.cacul_repet_mod(g, math_pt.get(i), n.multiply(n));
			do {
				r = new BigInteger(n.bitLength(), new Random());
				r = r.mod(n);
			} while (r.compareTo(new BigInteger("0")) == 0);
			tmp2 = CaculMod.cacul_repet_mod(r, n, n.multiply(n));
			math_ct.addElement(tmp1.multiply(tmp2).mod(n.multiply(n)));

			tmp3 = math_ct.get(i).mod(new BigInteger("95"));
			ct = ct + (char) (tmp3.intValue() + 32);
		}
		return ct;
	}

	/**   
	 * @Title: Paillier_decrypt   
	 * @Description: 返回解密密文后得到的明文，必须先调用 Paillier_encrypt()即加密后在调用次方法  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String Paillier_decrypt() {
		BigInteger tmp1, tmp2, tmp3;
		for (int i = 0; i < pt.length(); i++) {
			tmp1 = CaculMod.cacul_repet_mod(math_ct.get(i), Euler_n, n.multiply(n));
			tmp2 = tmp1.subtract(new BigInteger("1")).divide(n);
			tmp3 = tmp2.multiply(L_1).mod(n);
			d_pt = d_pt + (char) (tmp3.intValue());
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
		System.out.println("随机生成1024位大素数p=" + p);
		System.out.println("随机生成1024位大素数q=" + q);
		System.out.println("2048位公钥n=p*q=" + n);
		System.out.println("进而生成公钥g=" + g);
		System.out.println("进而生成私钥λ=" + Euler_n);
		System.out.println("进而生成私钥μ=" + L_1);
		// 单字符加密
		System.out.println("加密得到密文：" + Paillier_encrypt());
		System.out.println("解密密文得到的明文：" + Paillier_decrypt());
	}

	/**   
	 * @Title: Paillier_get_math_pt   
	 * @Description: 返回数字化的明文   
	 * @param: @return      
	 * @return: Vector<BigInteger>      
	 * @throws   
	 */
	public Vector<BigInteger> Paillier_get_math_pt() {
		return math_pt;
	}

	/**   
	 * @Title: Paillier_get_math_ct   
	 * @Description: 返回数字化的密文  
	 * @param: @return      
	 * @return: Vector<BigInteger>      
	 * @throws   
	 */
	public Vector<BigInteger> Paillier_get_math_ct() {
		return math_ct;
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

		PaillierEncryption pe1 = new PaillierEncryption(str1);
		pe1.show();
	}

}
