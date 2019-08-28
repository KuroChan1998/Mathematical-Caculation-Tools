/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfPaillierEncryption.java
 * @Package com.jzy.gui
 * @Description: Paillier加密算法类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:35:37
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.*;

import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.encyption.EncyptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.PaillierEncryption;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

/**
 * @ClassName: WindowOfPaillierEncryption
 * @Description: Paillier加密算法类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:35:37
 *
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfPaillierEncryption {

    /**
     * @Title: GUIPaillierEncryption
     * @Description: 设置Paillier加密算法模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIPaillierEncryption(JFrame frm) {
        JLabel j11 = new JLabel("9、Paillier加密算法：输入明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文");
        j11.setBounds(50, 300, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(610, 300, 80, 25);
        frm.add(btn);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 1000, 330); // 设置窗口初始位置和大小
                frmHidden.setTitle("Paillier加密算法：输入明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUIPaillierEncryptionHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });
    }

    /**
     * @Title: GUIPaillierEncryptionHidden
     * @Description: 点击进入后的Paillier加密算法模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIPaillierEncryptionHidden(JFrame frm) {
        JLabel j12 = new JLabel("明文pt=");
        j12.setBounds(50, 30, 50, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(100, 30, 700, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JButton btn = new JButton("加密");
        btn.setBounds(810, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("私钥λ=");
        j14.setBounds(50, 60, 80, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(100, 60, 850, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("私钥μ=");
        j15.setBounds(50, 90, 80, 25);
        frm.add(j15);
        JTextField jtf4 = new JTextField(); // 创建文本行组件
        jtf4.setBounds(100, 90, 850, 25); // 左边距，上边距，长，宽
        jtf4.setEditable(false);
        frm.add(jtf4);

        JLabel j16 = new JLabel("公钥n=");
        j16.setBounds(50, 120, 80, 25);
        frm.add(j16);
        JTextField jtf5 = new JTextField(); // 创建文本行组件
        jtf5.setBounds(100, 120, 850, 25); // 左边距，上边距，长，宽
        jtf5.setEditable(false);
        frm.add(jtf5);

        JLabel j17 = new JLabel("公钥g=");
        j17.setBounds(50, 150, 80, 25);
        frm.add(j17);
        JTextField jtf6 = new JTextField(); // 创建文本行组件
        jtf6.setBounds(100, 150, 850, 25); // 左边距，上边距，长，宽
        jtf6.setEditable(false);
        frm.add(jtf6);

        JLabel j18 = new JLabel("密文ct=");
        j18.setBounds(50, 180, 80, 25);
        frm.add(j18);
        JTextField jtf7 = new JTextField(); // 创建文本行组件
        jtf7.setBounds(105, 180, 845, 25); // 左边距，上边距，长，宽
        jtf7.setEditable(false);
        frm.add(jtf7);

        JLabel j19 = new JLabel("解密后的明文d_pt=");
        j19.setBounds(50, 210, 150, 25);
        frm.add(j19);
        JTextField jtf8 = new JTextField(); // 创建文本行组件
        jtf8.setBounds(170, 210, 780, 25); // 左边距，上边距，长，宽
        jtf8.setEditable(false);
        frm.add(jtf8);

        JLabel j110 = new JLabel("秘钥位宽(bit)=");
        j110.setBounds(50, 240, 150, 25);
        frm.add(j110);
        JTextField jtf9 = new JTextField(); // 创建文本行组件
        jtf9.setBounds(140, 240, 150, 25); // 左边距，上边距，长，宽
        frm.add(jtf9);
        JButton btn1 = new JButton("重置秘钥");
        btn1.setBounds(300, 240, 90, 25);
        frm.add(btn1);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                PaillierEncryption paillierEncryption = (PaillierEncryption) EncryptionFactory.getEncryption(EncyptionAlgorithm.Paillier);
                paillierEncryption.setPlainText(jtf1.getText());
                jtf3.setText(PaillierEncryption.getEulerN().toString());
                jtf4.setText(PaillierEncryption.getL1().toString());
                jtf5.setText(PaillierEncryption.getN().toString());
                jtf6.setText(PaillierEncryption.getG().toString());
                jtf7.setText(paillierEncryption.encrypt());
                jtf8.setText(paillierEncryption.decrypt());
            }

        });

        btn1.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf9.getText().equals("") || jtf9.getText() == null) {
                    PaillierEncryption.resetKeys();
                } else {
                    if (StringTest.isLegalPositiveInteger(jtf9.getText())) {
                        if (!StringTest.isLegalIntegerBetweenMinAndMax(jtf9.getText(), BigInteger.valueOf(Encryptions.KEYS_BIT_MIN_LENGTH), BigInteger.valueOf(Encryptions.KEYS_BIT_MAX_LENGTH))) {
                            JOptionPane.showMessageDialog(null, "输入的位宽应在" + Encryptions.KEYS_BIT_MIN_LENGTH + "~" + Encryptions.KEYS_BIT_MAX_LENGTH + "之间");
                            return;
                        }
                        int len = Integer.parseInt(jtf9.getText());
                        PaillierEncryption.resetKeys(len);
                    } else {
                        JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                    }
                }
                jtf3.setText(PaillierEncryption.getEulerN().toString());
                jtf4.setText(PaillierEncryption.getL1().toString());
                jtf5.setText(PaillierEncryption.getN().toString());
                jtf6.setText(PaillierEncryption.getG().toString());
                jtf7.setText("");
                jtf8.setText("");
            }

        });
    }
}
