/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  WindowOfEllipticCurveCaculationOfF2n.java   
 * @Package gui   
 * @Description:  F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算图形类
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午7:04:39   
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
import xxaqsxjc.method1.EllipticCurveCaculationOfF2n;
import xxaqsxjc.method1.PolyDivideException;
import xxaqsxjc.method1.Polynomial;

/**   
 * @ClassName:  WindowOfEllipticCurveCaculationOfF2n   
 * @Description: F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算图形类
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午7:04:39   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfEllipticCurveCaculationOfF2n {
	/**   
	 * @Fields p : 多项式p  
	 */   
	private static Polynomial p = new Polynomial();

	/**   
	 * @Fields a2 : 系数a2   
	 */   
	private static Polynomial a2 = new Polynomial();
	
	/**   
	 * @Fields a6 : 系数a6   
	 */   
	private static Polynomial a6 = new Polynomial();
	
	/**   
	 * @Title: resetP   
	 * @Description: 置多项式p为0  
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP() {
		p = new Polynomial();
	}
	
	/**   
	 * @Title: resetA2   
	 * @Description: 置系数a2为0  
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetA2() {
		a2 = new Polynomial();
	}
	
	/**   
	 * @Title: resetA6   
	 * @Description: 置系数a6为0 
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetA6() {
		a6 = new Polynomial();
	}
	
	/**   
	 * @Fields p1, p2 : 把多项式定义成成员变量便于监听事件中修改访问 
	 */   
	private static Polynomial[] p1 = { new Polynomial(), new Polynomial() };
	private static Polynomial[] p2 = { new Polynomial(), new Polynomial() };

	/**   
	 * @Title: resetP1X   
	 * @Description: 置P1x为0  
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP1X() {
		p1[0] = new Polynomial();
	}
	
	/**   
	 * @Title: resetP1Y   
	 * @Description: 置P1y为0   
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP1Y() {
		p1[1] = new Polynomial();
	}
	
	/**   
	 * @Title: resetP2X   
	 * @Description: 置P2x为0
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP2X() {
		p2[0] = new Polynomial();
	}
	
	/**   
	 * @Title: resetP2Y   
	 * @Description: 置P2y为0 
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	private static void resetP2Y() {
		p2[1] = new Polynomial();
	}

	/**   
	 * @Title: GUIEllipticCurveCaculationOfF2n   
	 * @Description: 设置F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算图形模块  
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIEllipticCurveCaculationOfF2n(JFrame frm) {
		JLabel j11 = new JLabel("14、F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6上点的计算");
		j11.setBounds(50, 450, GUIWindow.FrameWidth, 25);
		frm.add(j11);

		JButton btn = new JButton("进入");
		btn.setBounds(400, 450, 80, 25);
		frm.add(btn);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmHidden = new JFrame();
				frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 900, 500); // 设置窗口初始位置和大小
				frmHidden.setTitle("F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6上点的计算"); // 设置标题
				frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

				GUIEllipticCurveCaculationOfF2nHidden(frmHidden);

				frmHidden.setVisible(true); // 显示窗口
			}

		});
	}

	/**   
	 * @Title: GUIEllipticCurveCaculationOfF2nHidden   
	 * @Description: 点击进入后的F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算图形模块    
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIEllipticCurveCaculationOfF2nHidden(JFrame frm) {
		JLabel j11 = new JLabel("输入多项式P(x)，系数a2和a6:");
		j11.setBounds(50, 0, 200, 25);
		frm.add(j11);

		JLabel j12 = new JLabel("P(x)=");
		j12.setBounds(50, 30, 50, 25);
		frm.add(j12);
		JTextField jtf1 = new JTextField();// 创建文本行组件
		jtf1.setBounds(85, 30, 350, 25); // 左边距，上边距，长，宽
		jtf1.setEditable(false);
		jtf1.setText(p.toString());
		frm.add(jtf1);

		JLabel j13 = new JLabel("向P(x)加上x的?次方");
		j13.setBounds(470, 30, 200, 25);
		frm.add(j13);
		JTextField jtf2 = new JTextField(); // 创建文本行组件
		jtf2.setBounds(590, 30, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf2);

		JButton btnp1 = new JButton("添加");
		btnp1.setBounds(670, 30, 80, 25);
		frm.add(btnp1);

		JButton btnp2 = new JButton("P(X)清零");
		btnp2.setBounds(760, 30, 100, 25);
		frm.add(btnp2);

		JLabel j1a2 = new JLabel("a2=");
		j1a2.setBounds(50, 60, 50, 25);
		frm.add(j1a2);
		JTextField jtfa2 = new JTextField();// 创建文本行组件
		jtfa2.setBounds(85, 60, 350, 25); // 左边距，上边距，长，宽
		jtfa2.setEditable(false);
		jtfa2.setText(p.toString());
		frm.add(jtfa2);

		JLabel j1a21 = new JLabel("向a2加上x的?次方");
		j1a21.setBounds(470, 60, 200, 25);
		frm.add(j1a21);
		JTextField jtfa21 = new JTextField(); // 创建文本行组件
		jtfa21.setBounds(590, 60, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtfa21);

		JButton btnpa2 = new JButton("添加");
		btnpa2.setBounds(670, 60, 80, 25);
		frm.add(btnpa2);

		JButton btnpa21 = new JButton("a2清零");
		btnpa21.setBounds(760, 60, 100, 25);
		frm.add(btnpa21);

		JLabel j1a6 = new JLabel("a6=");
		j1a6.setBounds(50, 90, 50, 25);
		frm.add(j1a6);
		JTextField jtfa6 = new JTextField();// 创建文本行组件
		jtfa6.setBounds(85, 90, 350, 25); // 左边距，上边距，长，宽
		jtfa6.setEditable(false);
		jtfa6.setText(p.toString());
		frm.add(jtfa6);

		JLabel j1a61 = new JLabel("向a6加上x的?次方");
		j1a61.setBounds(470, 90, 200, 25);
		frm.add(j1a61);
		JTextField jtfa61 = new JTextField(); // 创建文本行组件
		jtfa61.setBounds(590, 90, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtfa61);

		JButton btnpa6 = new JButton("添加");
		btnpa6.setBounds(670, 90, 80, 25);
		frm.add(btnpa6);

		JButton btnpa61 = new JButton("a6清零");
		btnpa61.setBounds(760, 90, 100, 25);
		frm.add(btnpa61);

		JLabel j16 = new JLabel("点P1(P1x,P1y):");
		j16.setBounds(50, 120, 150, 25);
		frm.add(j16);

		JLabel j17 = new JLabel("P1x=");
		j17.setBounds(50, 150, 50, 25);
		frm.add(j17);
		JTextField jtf5 = new JTextField();// 创建文本行组件
		jtf5.setBounds(85, 150, 350, 25); // 左边距，上边距，长，宽
		jtf5.setEditable(false);
		jtf5.setText(p1[0].toString());
		frm.add(jtf5);

		JLabel j18 = new JLabel("向P1x加上x的?次方");
		j18.setBounds(470, 150, 200, 25);
		frm.add(j18);
		JTextField jtf6 = new JTextField(); // 创建文本行组件
		jtf6.setBounds(590, 150, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf6);

		JButton btnp3 = new JButton("添加");
		btnp3.setBounds(670, 150, 80, 25);
		frm.add(btnp3);

		JButton btnp4 = new JButton("P1x清零");
		btnp4.setBounds(760, 150, 100, 25);
		frm.add(btnp4);

		JLabel j19 = new JLabel("P1y=");
		j19.setBounds(50, 180, 50, 25);
		frm.add(j19);
		JTextField jtf7 = new JTextField();// 创建文本行组件
		jtf7.setBounds(85, 180, 350, 25); // 左边距，上边距，长，宽
		jtf7.setEditable(false);
		jtf7.setText(p1[1].toString());
		frm.add(jtf7);

		JLabel j110 = new JLabel("向P1y加上x的?次方");
		j110.setBounds(470, 180, 200, 25);
		frm.add(j110);
		JTextField jtf8 = new JTextField(); // 创建文本行组件
		jtf8.setBounds(590, 180, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf8);

		JButton btnp5 = new JButton("添加");
		btnp5.setBounds(670, 180, 80, 25);
		frm.add(btnp5);

		JButton btnp6 = new JButton("P1y清零");
		btnp6.setBounds(760, 180, 100, 25);
		frm.add(btnp6);

		JLabel j111 = new JLabel("点P2(P2x,P2y):");
		j111.setBounds(50, 210, 150, 25);
		frm.add(j111);

		JLabel j112 = new JLabel("P2x=");
		j112.setBounds(50, 240, 50, 25);
		frm.add(j112);
		JTextField jtf9 = new JTextField();// 创建文本行组件
		jtf9.setBounds(85, 240, 350, 25); // 左边距，上边距，长，宽
		jtf9.setEditable(false);
		jtf9.setText(p2[0].toString());
		frm.add(jtf9);

		JLabel j113 = new JLabel("向P2x加上x的?次方");
		j113.setBounds(470, 240, 200, 25);
		frm.add(j113);
		JTextField jtf10 = new JTextField(); // 创建文本行组件
		jtf10.setBounds(590, 240, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf10);

		JButton btnp7 = new JButton("添加");
		btnp7.setBounds(670, 240, 80, 25);
		frm.add(btnp7);

		JButton btnp8 = new JButton("P2x清零");
		btnp8.setBounds(760, 240, 100, 25);
		frm.add(btnp8);

		JLabel j114 = new JLabel("P2y=");
		j114.setBounds(50, 270, 50, 25);
		frm.add(j114);
		JTextField jtf11 = new JTextField();// 创建文本行组件
		jtf11.setBounds(85, 270, 350, 25); // 左边距，上边距，长，宽
		jtf11.setEditable(false);
		jtf11.setText(p2[1].toString());
		frm.add(jtf11);

		JLabel j115 = new JLabel("向P2y加上x的?次方");
		j115.setBounds(470, 270, 200, 25);
		frm.add(j115);
		JTextField jtf15 = new JTextField(); // 创建文本行组件
		jtf15.setBounds(590, 270, 70, 25); // 左边距，上边距，长，宽
		frm.add(jtf15);

		JButton btnp9 = new JButton("添加");
		btnp9.setBounds(670, 270, 80, 25);
		frm.add(btnp9);

		JButton btnp10 = new JButton("P2y清零");
		btnp10.setBounds(760, 270, 100, 25);
		frm.add(btnp10);

		JLabel j116 = new JLabel("计算：");
		j116.setBounds(50, 300, 50, 25);
		frm.add(j116);

		JLabel j117 = new JLabel("P1+P2=");
		j117.setBounds(50, 330, 150, 25);
		frm.add(j117);
		JTextField jtf16 = new JTextField(); // 创建文本行组件
		jtf16.setBounds(100, 330, 500, 25); // 左边距，上边距，长，宽
		jtf16.setEditable(false);
		frm.add(jtf16);
		JButton btnp11 = new JButton("计算");
		btnp11.setBounds(620, 330, 80, 25);
		frm.add(btnp11);

		JLabel j118 = new JLabel("k倍P1的计算：        k=");
		j118.setBounds(50, 360, 200, 25);
		frm.add(j118);
		JTextField jtf17 = new JTextField(); // 创建文本行组件
		jtf17.setBounds(180, 360, 100, 25); // 左边距，上边距，长，宽
		frm.add(jtf17);
		JLabel j119 = new JLabel("k倍P1=");
		j119.setBounds(300, 360, 100, 25);
		frm.add(j119);
		JTextField jtf18 = new JTextField(); // 创建文本行组件
		jtf18.setBounds(345, 360, 400, 25); // 左边距，上边距，长，宽
		jtf18.setEditable(false);
		frm.add(jtf18);
		JButton btnp12 = new JButton("计算");
		btnp12.setBounds(760, 360, 80, 25);
		frm.add(btnp12);

		btnp1.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf2.getText())) {
					if (new BigInteger(jtf2.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf2.getText());
						p = p.add(new Polynomial(deg));
						jtf1.setText(p.toString());
					}
				}
			}
		});

		btnp2.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP();
				jtf1.setText(p.toString());
			}
		});

		btnpa2.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtfa21.getText())) {
					if (new BigInteger(jtfa21.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtfa21.getText());
						a2 = a2.add(new Polynomial(deg));
						jtfa2.setText(a2.toString());
					}
				}
			}
		});

		btnpa21.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetA2();
				jtfa2.setText(a2.toString());
			}
		});

		btnpa6.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtfa61.getText())) {
					if (new BigInteger(jtfa61.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtfa61.getText());
						a6 = a6.add(new Polynomial(deg));
						jtfa6.setText(a6.toString());
					}
				}
			}
		});

		btnpa61.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetA6();
				jtfa6.setText(a6.toString());
			}
		});

		btnp3.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf6.getText())) {
					if (new BigInteger(jtf6.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf6.getText());
						p1[0] = p1[0].add(new Polynomial(deg));
						jtf5.setText(p1[0].toString());
					}
				}
			}
		});

		btnp4.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP1X();
				jtf5.setText(p1[0].toString());
			}
		});

		btnp5.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf8.getText())) {
					if (new BigInteger(jtf8.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf8.getText());
						p1[1] = p1[1].add(new Polynomial(deg));
						jtf7.setText(p1[1].toString());
					}
				}
			}
		});

		btnp6.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP1Y();
				jtf7.setText(p1[1].toString());
			}
		});

		btnp7.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf10.getText())) {
					if (new BigInteger(jtf10.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf10.getText());
						p2[0] = p2[0].add(new Polynomial(deg));
						jtf9.setText(p2[0].toString());
					}
				}
			}
		});

		btnp8.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP2X();
				jtf9.setText(p2[0].toString());
			}
		});

		btnp9.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf15.getText())) {
					if (new BigInteger(jtf15.getText()).compareTo(BigInteger.valueOf(Polynomial.Max_Deg)) == 1) {
						JOptionPane.showMessageDialog(null, "多项式的最高次数不应该大于65535！");
					} else {
						int deg = Integer.parseInt(jtf15.getText());
						p2[1] = p2[1].add(new Polynomial(deg));
						jtf11.setText(p2[1].toString());
					}
				}
			}
		});

		btnp10.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				resetP2Y();
				jtf11.setText(p2[1].toString());
			}
		});

		btnp11.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				EllipticCurveCaculationOfF2n ellipticCurveCaculationOfF2n = new EllipticCurveCaculationOfF2n(p, a2, a6);
				try {
					Polynomial[] re = ellipticCurveCaculationOfF2n.pointAdd(p1, p2);
					jtf16.setText("(" + re[0].toString() + "," + re[1].toString() + ")");
				} catch (PolyDivideException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnp12.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				EllipticCurveCaculationOfF2n ellipticCurveCaculationOfF2n = new EllipticCurveCaculationOfF2n(p, a2, a6);
				if (new BigInteger(jtf17.getText()).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) == 1) {
					JOptionPane.showMessageDialog(null, "k不应该大于65535！");
				} else {
					int k = Integer.parseInt(jtf17.getText());
					try {
						Polynomial[] re = ellipticCurveCaculationOfF2n.kPointCacul(p1, k);
						jtf18.setText("(" + re[0].toString() + "," + re[1].toString() + ")");
					} catch (PolyDivideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

	}
}
