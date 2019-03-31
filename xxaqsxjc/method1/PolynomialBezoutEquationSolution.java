/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  PolynomialBezoutEquationSolution.java   
 * @Package xxaqsxjc.method1   
 * @Description: 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 * @author: JinZhiyun    
 * @date:   2019年3月15日 上午10:10:57   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 

package xxaqsxjc.method1;

/**   
 * @ClassName:  PolynomialBezoutEquationSolution   
 * @Description: 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 * @author: JinZhiyun
 * @date:   2019年3月15日 上午10:10:27   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class PolynomialBezoutEquationSolution {
	private final int Bezout_solve_qr_size = 1000000;
	private boolean flag;
	public int j;
	
	/**   
	 * @Fields s,t,q,r : 辅助数组   
	 */  
	public Polynomial[] s;
	public Polynomial[] t;
	public Polynomial[] q;
	public Polynomial[] r;
	
	/**   
	 * @Fields result_index : 结果值在数组s,t中的索引值
	 */  
	public int result_index = 0;
	
	/**   
	 * @Fields result_s : 求出的系数多项式s 
	 */
	public Polynomial result_s = new Polynomial();
	
	/**   
	 * @Fields result_st : 求出的系数多项式s和t  
	 */ 
	public Polynomial[] result_st = new Polynomial[2];

	/**   
	 * @Title:  PolynomialBezoutEquationSolution   
	 * @Description:  类构造函数，用以初始化 
	 * @param:    
	 * @throws   
	 */  
	public PolynomialBezoutEquationSolution() {
		s = new Polynomial[Bezout_solve_qr_size];
		t = new Polynomial[Bezout_solve_qr_size];
		q = new Polynomial[Bezout_solve_qr_size];
		r = new Polynomial[Bezout_solve_qr_size];
		j = 0;
		flag = true;
	}

	/**   
	 * @Title: PolynomialBezout_solve_qr_st   
	 * @Description:计算出系数s,t    
	 * @param: @param x
	 * @param: @param y
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial[]      
	 * @throws   
	 */ 
	public Polynomial[] PolynomialBezout_solve_qr_st(Polynomial x, Polynomial y) throws PolyDivideException {
		if (flag == false) {
			j = 0;
			flag = true;
		}
		s[1] = new Polynomial(0);//1
		s[2] = new Polynomial();//0
		t[1] = new Polynomial();
		t[2] = new Polynomial(0);
		r[j] = x;
		r[j + 1] = y;
		q[j + 2] = r[j].divide(r[j + 1]);
		r[j + 2] = r[j].mod(r[j + 1]);

		s[j + 3] = s[j + 1].sub(q[j + 2].multiply(s[j + 2]));
		t[j + 3] = t[j + 1].sub(q[j + 2].multiply(t[j + 2]));
		x = r[j + 1];
		y = r[j + 2];

		if (y.equals(new Polynomial())) {
			flag = false;
			result_st[0] = s[j + 2];
			result_st[1] = t[j + 2];
			return result_st;
		} else {
			j += 1;
			PolynomialBezout_solve_qr_st(x, y);
		}
		return result_st;
	}
	
	/**   
	 * @Title: PolynomialBezout_solve_qr_s_11   
	 * @Description: 计算出系数多项式s（x的逆元，只为正）
	 * @param: @param x
	 * @param: @param y
	 * @param: @return
	 * @param: @throws PolyDivideException      
	 * @return: Polynomial      
	 * @throws   
	 */ 
	public Polynomial PolynomialBezout_solve_qr_s_11(Polynomial x, Polynomial y) throws PolyDivideException {
		return PolynomialBezout_solve_qr_st(x,y)[0];
	}
	
	public static void main(String[] args) throws PolyDivideException {
		// TODO Auto-generated method stub
		int []a= {0};
		int []b= {1,1,0,1};
		Polynomial pa = new Polynomial(a);
		Polynomial pb = new Polynomial(b);
		System.out.println(new PolynomialBezoutEquationSolution().PolynomialBezout_solve_qr_s_11(pa, pb));
	}

}
