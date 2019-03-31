/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  WindowOfPolynomial.java   
 * @Package gui   
 * @Description: 多项式计算图形类 
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午12:41:45   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import someTest.Test;
import xxaqsxjc.method1.PolyDivideException;
import xxaqsxjc.method1.Polynomial;
import xxaqsxjc.method1.PolynomialBezoutEquationSolution;
import xxaqsxjc.method1.PolynomialCaculation;

/**   
 * @ClassName:  WindowOfPolynomial   
 * @Description: 多项式计算图形类   
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午12:41:45   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfPolynomial {
	/**   
	 * @Fields p1, p2 : 把多项式定义成成员变量便于监听事件中修改访问 
	 */   
	private static Polynomial p1 = new Polynomial();
	private static Polynomial p2 = new Polynomial();

	/**   
	 * @Title: resetP1   
	 * @Description: 置多项式p1为0  
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP1() {
		p1 = new Polynomial();
	}

	/**   
	 * @Title: resetP2   
	 * @Description: 置多项式p1为0   
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP2() {
		p2 = new Polynomial();
	}

	/**   
	 * @Title: GUIPolynomial   
	 * @Description: 设置多项式计算图形模块
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIPolynomial(JFrame frm) {
		JLabel j11 = new JLabel("12、多项式计算大全");
		j11.setBounds(50, 390, GUIWindow.FrameWidth, 25);
		frm.add(j11);

		JButton btn = new JButton("进入");
		btn.setBounds(180, 390, 80, 25);
		frm.add(btn);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmHidden = new JFrame();
				frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 1150, 600); // 设置窗口初始位置和大小
				frmHidden.setTitle("多项式计算大全"); // 设置标题
				frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

				GUIPolynomialHidden(frmHidden);

				frmHidden.setVisible(true); // 显示窗口
			}
		});
	}

	/**   
	 * @Title: GUIPolynomialHidden   
	 * @Description: 点击进入后的多项式计算图形模块  
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIPolynomialHidden(JFrame frm) {
		JLabel j11 = new JLabel("输入两个多项式p1,p2:");
		j11.setBounds(50, 30, 200, 25);
		frm.add(j11);

		JLabel j12 = new JLabel("p1=");
		j12.setBounds(50, 60, 50, 25);
		frm.add(j12);
		JTextField jtf1 = new JTextField();// 创建文本行组件
		jtf1.setBounds(80, 60, 600, 25); // 左边距，上边距，长，宽
		jtf1.setEditable(false);
		jtf1.setText(p1.toString());
		frm.add(jtf1);

		JLabel j13 = new JLabel("向p1加上x的?次方");
		j13.setBounds(700, 60, 200, 25);
		frm.add(j13);
		JTextField jtf2 = new JTextField(); // 创建文本行组件
		jtf2.setBounds(810, 60, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf2);

		JButton btnp1 = new JButton("添加");
		btnp1.setBounds(890, 60, 80, 25);
		frm.add(btnp1);

		JButton btnp2 = new JButton("p1清零");
		btnp2.setBounds(980, 60, 80, 25);
		frm.add(btnp2);

		JLabel j14 = new JLabel("p2=");
		j14.setBounds(50, 90, 50, 25);
		frm.add(j14);
		JTextField jtf3 = new JTextField();// 创建文本行组件
		jtf3.setBounds(80, 90, 600, 25); // 左边距，上边距，长，宽
		jtf3.setEditable(false);
		jtf3.setText(p1.toString());
		frm.add(jtf3);

		JLabel j15 = new JLabel("向p2加上x的?次方");
		j15.setBounds(700, 90, 200, 25);
		frm.add(j15);
		JTextField jtf4 = new JTextField(); // 创建文本行组件
		jtf4.setBounds(810, 90, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf4);

		JButton btnp3 = new JButton("添加");
		btnp3.setBounds(890, 90, 80, 25);
		frm.add(btnp3);

		JButton btnp4 = new JButton("p2清零");
		btnp4.setBounds(980, 90, 80, 25);
		frm.add(btnp4);

		JLabel j16 = new JLabel("p1+p2=");
		j16.setBounds(50, 120, 50, 25);
		frm.add(j16);
		JTextField jtf5 = new JTextField(); // 创建文本行组件
		jtf5.setBounds(100, 120, 700, 25); // 左边距，上边距，长，宽
		jtf5.setEditable(false);
		frm.add(jtf5);
		JButton btnp5 = new JButton("计算");
		btnp5.setBounds(820, 120, 80, 25);
		frm.add(btnp5);

		JLabel j17 = new JLabel("p1-p2=");
		j17.setBounds(50, 150, 50, 25);
		frm.add(j17);
		JTextField jtf6 = new JTextField(); // 创建文本行组件
		jtf6.setBounds(100, 150, 700, 25); // 左边距，上边距，长，宽
		jtf6.setEditable(false);
		frm.add(jtf6);
		JButton btnp6 = new JButton("计算");
		btnp6.setBounds(820, 150, 80, 25);
		frm.add(btnp6);

		JLabel j18 = new JLabel("p1*p2=");
		j18.setBounds(50, 180, 50, 25);
		frm.add(j18);
		JTextField jtf7 = new JTextField(); // 创建文本行组件
		jtf7.setBounds(100, 180, 700, 25); // 左边距，上边距，长，宽
		jtf7.setEditable(false);
		frm.add(jtf7);
		JButton btnp7 = new JButton("计算");
		btnp7.setBounds(820, 180, 80, 25);
		frm.add(btnp7);

		JLabel j19 = new JLabel("p1/p2=");
		j19.setBounds(50, 210, 50, 25);
		frm.add(j19);
		JTextField jtf8 = new JTextField(); // 创建文本行组件
		jtf8.setBounds(100, 210, 700, 25); // 左边距，上边距，长，宽
		jtf8.setEditable(false);
		frm.add(jtf8);
		JButton btnp8 = new JButton("计算");
		btnp8.setBounds(820, 210, 80, 25);
		frm.add(btnp8);

		JLabel j110 = new JLabel("p1 mod p2=");
		j110.setBounds(50, 240, 150, 25);
		frm.add(j110);
		JTextField jtf9 = new JTextField(); // 创建文本行组件
		jtf9.setBounds(120, 240, 700, 25); // 左边距，上边距，长，宽
		jtf9.setEditable(false);
		frm.add(jtf9);
		JButton btnp9 = new JButton("计算");
		btnp9.setBounds(840, 240, 80, 25);
		frm.add(btnp9);

		JLabel j111 = new JLabel("p1的幂：p1^");
		j111.setBounds(50, 270, 150, 25);
		frm.add(j111);
		JTextField jtf90 = new JTextField(); // 创建文本行组件
		jtf90.setBounds(130, 270, 80, 25); // 左边距，上边距，长，宽
		frm.add(jtf90);
		JLabel j1110 = new JLabel("=");
		j1110.setBounds(215, 270, 50, 25);
		frm.add(j1110);
		JTextField jtf10 = new JTextField(); // 创建文本行组件
		jtf10.setBounds(225, 270, 700, 25); // 左边距，上边距，长，宽
		jtf10.setEditable(false);
		frm.add(jtf10);
		JButton btnp10 = new JButton("计算");
		btnp10.setBounds(945, 270, 80, 25);
		frm.add(btnp10);

		JLabel j112 = new JLabel("(p1,p2)=");
		j112.setBounds(50, 300, 150, 25);
		frm.add(j112);
		JTextField jtf11 = new JTextField(); // 创建文本行组件
		jtf11.setBounds(105, 300, 700, 25); // 左边距，上边距，长，宽
		jtf11.setEditable(false);
		frm.add(jtf11);
		JButton btnp11 = new JButton("计算");
		btnp11.setBounds(825, 300, 80, 25);
		frm.add(btnp11);

		JLabel j113 = new JLabel("[p1,p2]=");
		j113.setBounds(50, 330, 150, 25);
		frm.add(j113);
		JTextField jtf12 = new JTextField(); // 创建文本行组件
		jtf12.setBounds(105, 330, 700, 25); // 左边距，上边距，长，宽
		jtf12.setEditable(false);
		frm.add(jtf12);
		JButton btnp12 = new JButton("计算");
		btnp12.setBounds(825, 330, 80, 25);
		frm.add(btnp12);

		JLabel j114 = new JLabel("p1是否为不可约多项式(1是，0不是):");
		j114.setBounds(50, 360, 250, 25);
		frm.add(j114);
		JTextField jtf13 = new JTextField(); // 创建文本行组件
		jtf13.setBounds(265, 360, 100, 25); // 左边距，上边距，长，宽
		jtf13.setEditable(false);
		frm.add(jtf13);

		JLabel j115 = new JLabel("p1是否为本原多项式(1是，0不是):");
		j115.setBounds(390, 360, 250, 25);
		frm.add(j115);
		JTextField jtf14 = new JTextField(); // 创建文本行组件
		jtf14.setBounds(590, 360, 100, 25); // 左边距，上边距，长，宽
		jtf14.setEditable(false);
		frm.add(jtf14);

		JButton btnp13 = new JButton("计算");
		btnp13.setBounds(710, 360, 80, 25);
		frm.add(btnp13);

		JLabel j1160 = new JLabel("多项式贝祖等式系数求解(s(x)*p1(x)+t(x)p2(x)=( p1(x),p2(x) )");
		j1160.setBounds(50, 390, 500, 25);
		frm.add(j1160);

		JButton btnp15 = new JButton("计算");
		btnp15.setBounds(400, 390, 80, 25);
		frm.add(btnp15);

		JLabel j116 = new JLabel("s(x)=");
		j116.setBounds(50, 420, 150, 25);
		frm.add(j116);
		JTextField jtf15 = new JTextField(); // 创建文本行组件
		jtf15.setBounds(85, 420, 700, 25); // 左边距，上边距，长，宽
		jtf15.setEditable(false);
		frm.add(jtf15);

		JLabel j117 = new JLabel("t(x)=");
		j117.setBounds(50, 450, 150, 25);
		frm.add(j117);
		JTextField jtf16 = new JTextField(); // 创建文本行组件
		jtf16.setBounds(85, 450, 700, 25); // 左边距，上边距，长，宽
		jtf16.setEditable(false);
		frm.add(jtf16);

		JButton btnp16 = new JButton("一键清屏");
		btnp16.setBounds(500, 480, 100, 25);
		frm.add(btnp16);

		btnp1.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf2.getText())) {
					if (new BigInteger(jtf2.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf2.getText());
						p1 = p1.add(new Polynomial(deg));
						jtf1.setText(p1.toString());
					}
				}
			}
		});

		btnp2.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP1();
				jtf1.setText(p1.toString());
			}
		});

		btnp3.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf4.getText())) {
					if (new BigInteger(jtf4.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf4.getText());
						p2 = p2.add(new Polynomial(deg));
						jtf3.setText(p2.toString());
					}
				}
			}
		});

		btnp4.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP2();
				jtf3.setText(p2.toString());
			}
		});

		btnp5.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial = p1.add(p2);
				jtf5.setText(polynomial.toString());
			}
		});

		btnp6.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial = p1.sub(p2);
				jtf6.setText(polynomial.toString());
			}
		});

		btnp7.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial = p1.multiply(p2);
				jtf7.setText(polynomial.toString());
			}
		});

		btnp8.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial;
				if (p2.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "除式不能为0");
				} else {
					try {
						polynomial = p1.divide(p2);
						jtf8.setText(polynomial.toString());
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp9.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial;
				if (p2.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "除式不能为0");
				} else {
					try {
						polynomial = p1.mod(p2);
						jtf9.setText(polynomial.toString());
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp10.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf90.getText())) {
					if (Integer.parseInt(jtf90.getText()) > Polynomial.Max_Deg || Polynomial.pow(p1,
							Integer.parseInt(jtf90.getText())).effectiveMaxDeg > Polynomial.Max_Deg) {
						JOptionPane.showMessageDialog(null, "请输入较小些的幂次");
					} else {
						Polynomial polynomial = Polynomial.pow(p1, Integer.parseInt(jtf90.getText()));
						jtf10.setText(polynomial.toString());
					}
				}
			}
		});

		btnp11.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial;
				if (p1.equals(new Polynomial()) || p2.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "p1或p2不能为0");
				} else {
					try {
						polynomial = PolynomialCaculation.maxPolyCommonFactor(p1, p2);
						jtf11.setText(polynomial.toString());
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp12.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial polynomial;
				if (p1.equals(new Polynomial()) || p2.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "p1或p2不能为0");
				} else {
					try {
						polynomial = PolynomialCaculation.minPolyCommonMultiple(p1, p2);
						jtf12.setText(polynomial.toString());
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp13.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				int r1, r2;
				if (p1.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "p1不能为0");
				} else {
					try {
						r1 = PolynomialCaculation.ifPrimPolynomial(p1);
						r2 = PolynomialCaculation.ifReduciblePolynomial(p1);
						jtf13.setText(Integer.toString(r1));
						jtf14.setText(Integer.toString(r2));
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp15.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				Polynomial[] polynomial;
				if (p1.equals(new Polynomial()) || p2.equals(new Polynomial())) {
					JOptionPane.showMessageDialog(null, "p1或p2不能为0");
				} else {
					try {
						PolynomialBezoutEquationSolution polynomialBezoutEquationSolution = new PolynomialBezoutEquationSolution();
						polynomial = polynomialBezoutEquationSolution.PolynomialBezout_solve_qr_st(p1, p2);
						jtf15.setText(polynomial[0].toString());
						jtf16.setText(polynomial[1].toString());
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnp16.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP1();
				resetP2();

				jtf1.setText(p1.toString());
				jtf3.setText(p2.toString());
				jtf2.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				jtf7.setText("");
				jtf8.setText("");
				jtf9.setText("");
				jtf90.setText("");
				jtf10.setText("");
				jtf11.setText("");
				jtf12.setText("");
				jtf13.setText("");
				jtf14.setText("");
				jtf15.setText("");
				jtf16.setText("");
			}
		});
	}
}
