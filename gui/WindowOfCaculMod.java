/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  WindowOfCaculMod.java   
 * @Package gui   
 * @Description: 最小非负余数和绝对值最小余数计算图形类    
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午12:19:01   
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
import javax.swing.JTextField;

import xxaqsxjc.method0.CaculMod;
import someTest.Test;
/**   
 * @ClassName:  WindowOfCaculMod   
 * @Description: 最小非负余数和绝对值最小余数计算图形类   
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午12:19:01   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfCaculMod {
	/**
	 * @Title: GUICaculMod 
	 * @Description: 最小非负余数和绝对值最小余数计算
	 * @param: @param frm 
	 * @return: void 
	 * @throws
	 */
	public static void GUICaculMod(JFrame frm) {
		JLabel j11 = new JLabel("2、最小非负余数和绝对值最小余数计算 ：b^n mod m=?，请输入b,n,m");
		j11.setBounds(50, 90, GUIWindow.FrameWidth, 25);
		frm.add(j11);

		JButton btn = new JButton("进入");
		btn.setBounds(470, 90, 80, 25);
		frm.add(btn);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmHidden = new JFrame();
				frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 900, 150); // 设置窗口初始位置和大小
				frmHidden.setTitle("最小非负余数和绝对值最小余数计算 ：b^n mod m=?，请输入b,n,m"); // 设置标题
				frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

				GUICaculModHidden(frmHidden);

				frmHidden.setVisible(true); // 显示窗口
			}
		});
	}
	
	/**   
	 * @Title: GUICaculModHidden   
	 * @Description: 点击进入后的最小非负余数和绝对值最小余数计算模块     
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */ 
	public static void GUICaculModHidden(JFrame frm) {
		JLabel j12 = new JLabel("b=");
		j12.setBounds(50, 30, 20, 25);
		frm.add(j12);
		JTextField jtf1 = new JTextField();// 创建文本行组件
		jtf1.setBounds(70, 30, 150, 25); // 左边距，上边距，长，宽
		frm.add(jtf1);

		JLabel j13 = new JLabel("n=");
		j13.setBounds(250, 30, 20, 25);
		frm.add(j13);
		JTextField jtf2 = new JTextField(); // 创建文本行组件
		jtf2.setBounds(270, 30, 150, 25); // 左边距，上边距，长，宽
		frm.add(jtf2);

		JLabel j14 = new JLabel("m=");
		j14.setBounds(450, 30, 20, 25);
		frm.add(j14);
		JTextField jtf3 = new JTextField(); // 创建文本行组件
		jtf3.setBounds(470, 30, 150, 25); // 左边距，上边距，长，宽
		frm.add(jtf3);

		JButton btn = new JButton("计算");
		btn.setBounds(650, 30, 80, 25);
		frm.add(btn);

		JLabel j15 = new JLabel("最小非负余数=");
		j15.setBounds(50, 60, 90, 25);
		frm.add(j15);
		JTextField jtf4 = new JTextField(); // 创建文本行组件
		jtf4.setBounds(140, 60, 150, 25); // 左边距，上边距，长，宽
		jtf4.setEditable(false);
		frm.add(jtf4);

		JLabel j16 = new JLabel("绝对值最小余数=");
		j16.setBounds(320, 60, 110, 25);
		frm.add(j16);
		JTextField jtf5 = new JTextField(); // 创建文本行组件
		jtf5.setBounds(430, 60, 150, 25); // 左边距，上边距，长，宽
		jtf5.setEditable(false);
		frm.add(jtf5);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf1.getText()) && Test.isLegalInteger(jtf2.getText())
						&& Test.isLegalInteger(jtf3.getText())) {
					BigInteger b = new BigInteger(jtf1.getText());
					BigInteger n = new BigInteger(jtf2.getText());
					BigInteger m = new BigInteger(jtf3.getText());
					BigInteger r1 = CaculMod.cacul_repet_mod(b, n, m);
					BigInteger r2 = CaculMod.transfer_absmin_rem(r1, m);
					jtf4.setText(r1.toString());
					jtf5.setText(r2.toString());
				}
			}
		});
	}
}
