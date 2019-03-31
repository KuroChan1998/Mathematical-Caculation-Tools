/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  WindowOfRSAEncryption.java   
 * @Package gui   
 * @Description: RSA加密算法 图形类    
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午12:33:39   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import EncyptionAlgorithm.RSAEncryption;

/**   
 * @ClassName:  WindowOfRSAEncryption   
 * @Description: RSA加密算法 图形类  
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午12:33:39   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfRSAEncryption {
	
	/**   
	 * @Title: GUIRSAEncryption   
	 * @Description: 设置RSA加密算法 图形模块   
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIRSAEncryption(JFrame frm) {
		JLabel j11 = new JLabel("8、RSA加密算法：输入明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文");
		j11.setBounds(50, 270, GUIWindow.FrameWidth, 25);
		frm.add(j11);

		JButton btn = new JButton("进入");
		btn.setBounds(590, 270, 80, 25);
		frm.add(btn);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmHidden = new JFrame();
				frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 1000, 280); // 设置窗口初始位置和大小
				frmHidden.setTitle("RSA加密算法：输入明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文"); // 设置标题
				frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

				GUIRSAEncryptionHidden(frmHidden);

				frmHidden.setVisible(true); // 显示窗口
			}
		});
	}

	/**   
	 * @Title: GUIRSAEncryptionHidden   
	 * @Description: 点击进入后的RSA加密算法 图形模块    
	 * @param: @param frm      
	 * @return: void      
	 * @throws   
	 */
	public static void GUIRSAEncryptionHidden(JFrame frm) {
		JLabel j12 = new JLabel("明文pt=");
		j12.setBounds(50, 30, 50, 25);
		frm.add(j12);
		JTextField jtf1 = new JTextField();// 创建文本行组件
		jtf1.setBounds(100, 30, 700, 25); // 左边距，上边距，长，宽
		frm.add(jtf1);

		JButton btn = new JButton("加密");
		btn.setBounds(810, 30, 80, 25);
		frm.add(btn);

		JLabel j14 = new JLabel("私钥d=");
		j14.setBounds(50, 60, 80, 25);
		frm.add(j14);
		JTextField jtf3 = new JTextField(); // 创建文本行组件
		jtf3.setBounds(100, 60, 850, 25); // 左边距，上边距，长，宽
		jtf3.setEditable(false);
		frm.add(jtf3);

		JLabel j15 = new JLabel("公钥n=");
		j15.setBounds(50, 90, 80, 25);
		frm.add(j15);
		JTextField jtf4 = new JTextField(); // 创建文本行组件
		jtf4.setBounds(100, 90, 850, 25); // 左边距，上边距，长，宽
		jtf4.setEditable(false);
		frm.add(jtf4);

		JLabel j16 = new JLabel("公钥e=");
		j16.setBounds(50, 120, 80, 25);
		frm.add(j16);
		JTextField jtf5 = new JTextField(); // 创建文本行组件
		jtf5.setBounds(100, 120, 850, 25); // 左边距，上边距，长，宽
		jtf5.setEditable(false);
		frm.add(jtf5);

		JLabel j17 = new JLabel("密文ct=");
		j17.setBounds(50, 150, 80, 25);
		frm.add(j17);
		JTextField jtf6 = new JTextField(); // 创建文本行组件
		jtf6.setBounds(105, 150, 845, 25); // 左边距，上边距，长，宽
		jtf6.setEditable(false);
		frm.add(jtf6);

		JLabel j18 = new JLabel("解密后的明文d_pt=");
		j18.setBounds(50, 180, 150, 25);
		frm.add(j18);
		JTextField jtf7 = new JTextField(); // 创建文本行组件
		jtf7.setBounds(170, 180, 780, 25); // 左边距，上边距，长，宽
		jtf7.setEditable(false);
		frm.add(jtf7);

		btn.addActionListener(new ActionListener() { // 按钮响应事件
			@Override
			public void actionPerformed(ActionEvent e) {
				RSAEncryption rsaEncryption = new RSAEncryption(jtf1.getText());
				jtf3.setText(rsaEncryption.getD().toString());
				jtf4.setText(rsaEncryption.getN().toString());
				jtf5.setText(rsaEncryption.getE().toString());
				jtf6.setText(rsaEncryption.RSA_encrypt());
				jtf7.setText(rsaEncryption.RSA_decrypt());
			}

		});
	}
}
