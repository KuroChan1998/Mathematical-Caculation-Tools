/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  WindowOfPrimitiveRoot.java   
 * @Package gui   
 * @Description: 计算最小原根图形类  
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午12:29:17   
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

import xxaqsxjc.method0.PrimitiveRoot;
import someTest.Test;
import xxaqsxjc.method0.PrimeTest;

/**   
 * @ClassName:  WindowOfPrimitiveRoot   
 * @Description: 计算最小原根图形类  
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午12:29:17   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfPrimitiveRoot {
	/**   
	 * @Title: GUIPrimitiveRoot   
	 * @Description: 设置计算最小原根模块
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIPrimitiveRoot(JFrame frm) {
		JLabel j11 = new JLabel("6、模p原根的计算：输入p，返回p的最小原根");
		j11.setBounds(50, 210, GUIWindow.FrameWidth, 25);
		frm.add(j11);

		JButton btn = new JButton("进入");
		btn.setBounds(320, 210, 80, 25);
		frm.add(btn);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmHidden = new JFrame();
				frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 500, 150); // 设置窗口初始位置和大小
				frmHidden.setTitle("模p原根的计算：输入p，返回p的最小原根"); // 设置标题
				frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

				GUIPrimitiveRootHidden(frmHidden);

				frmHidden.setVisible(true); // 显示窗口
			}
		});
	}
	
	/**   
	 * @Title: GUIPrimitiveRootHidden   
	 * @Description: 点击进入后的计算最小原根模块
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	public static void GUIPrimitiveRootHidden(JFrame frm) {
		JLabel j12 = new JLabel("p=");
		j12.setBounds(50, 30, 20, 25);
		frm.add(j12);
		JTextField jtf1 = new JTextField();// 创建文本行组件
		jtf1.setBounds(70, 30, 250, 25); // 左边距，上边距，长，宽
		frm.add(jtf1);

		JButton btn = new JButton("计算");
		btn.setBounds(340, 30, 80, 25);
		frm.add(btn);

		JLabel j14 = new JLabel("p的最小原根=");
		j14.setBounds(50, 60, 80, 25);
		frm.add(j14);
		JTextField jtf3 = new JTextField(); // 创建文本行组件
		jtf3.setBounds(135, 60, 250, 25); // 左边距，上边距，长，宽
		jtf3.setEditable(false);
		frm.add(jtf3);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Test.isLegalInteger(jtf1.getText())) {
					BigInteger p = new BigInteger(jtf1.getText());
					if (PrimeTest.SolovayStassen(p, 15) == 0) {
						JOptionPane.showMessageDialog(null, "p必须是奇素数！");
					} else {
						BigInteger re = PrimitiveRoot.min_prim_root(p);
						jtf3.setText(re.toString());
					}
				}
			}

		});
	}
}
