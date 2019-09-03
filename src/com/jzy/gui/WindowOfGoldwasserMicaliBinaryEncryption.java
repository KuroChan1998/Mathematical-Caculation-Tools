package com.jzy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jzy.xxaqsxjc.encyption.Encryptions;
import com.jzy.xxaqsxjc.encyption.EncryptionAlgorithm;
import com.jzy.xxaqsxjc.encyption.algorithm.GoldwasserMicaliBinaryEncryption;
import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.encyption.factory.EncryptionFactory;

/**
 * GoldwasserMicali加密算法（对二进制加密）图形类
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class WindowOfGoldwasserMicaliBinaryEncryption {
    /**
     * @Title: GUIGoldwasserMicaliBinaryEncryption
     * @Description: 设置GoldwasserMicali加密算法（对二进制加密） 图形模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIGoldwasserMicaliBinaryEncryption(JFrame frm) {
        JLabel j11 = new JLabel("10、GoldwasserMicali加密算法：输入只含有0、1的二进制串明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文");
        j11.setBounds(50, 330, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(800, 330, 80, 25);
        frm.add(btn);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 1000, 600); // 设置窗口初始位置和大小
                frmHidden.setTitle("GoldwasserMicali加密算法：输入只含有0、1的二进制串明文pt，输出随机生成的公私钥，密文，以及解密密文后得到的明文"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUIGoldwasserMicaliBinaryEncryptionHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });
    }

    /**
     * @Title: GUIGoldwasserMicaliBinaryEncryptionHidden
     * @Description: 点击进入后的GoldwasserMicali加密算法（对二进制加密） 图形模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIGoldwasserMicaliBinaryEncryptionHidden(JFrame frm) {
        JLabel j12 = new JLabel("明文pt=");
        j12.setBounds(50, 30, 50, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(100, 30, 700, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JButton btn = new JButton("加密");
        btn.setBounds(810, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("私钥p=");
        j14.setBounds(50, 60, 80, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(100, 60, 850, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("私钥q=");
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

        JLabel j17 = new JLabel("公钥x=");
        j17.setBounds(50, 150, 80, 25);
        frm.add(j17);
        JTextField jtf6 = new JTextField(); // 创建文本行组件
        jtf6.setBounds(100, 150, 850, 25); // 左边距，上边距，长，宽
        jtf6.setEditable(false);
        frm.add(jtf6);

        JLabel j18 = new JLabel("密文ct=");
        j18.setBounds(50, 180, 80, 25);
        frm.add(j18);
        JTextArea jta1 = new JTextArea();
        jta1.setEditable(false);
        JScrollPane jsp1 = new JScrollPane(jta1);
        jsp1.setBounds(105, 180, 845, 300);
        frm.add(jsp1);

        JLabel j19 = new JLabel("解密后的明文d_pt=");
        j19.setBounds(50, 490, 150, 25);
        frm.add(j19);
        JTextField jtf7 = new JTextField(); // 创建文本行组件
        jtf7.setBounds(170, 490, 780, 25); // 左边距，上边距，长，宽
        jtf7.setEditable(false);
        frm.add(jtf7);

        JLabel j110 = new JLabel("秘钥位宽(bit)=");
        j110.setBounds(50, 520, 150, 25);
        frm.add(j110);
        JTextField jtf8 = new JTextField(); // 创建文本行组件
        jtf8.setBounds(140, 520, 150, 25); // 左边距，上边距，长，宽
        frm.add(jtf8);
        JButton btn1 = new JButton("重置秘钥");
        btn1.setBounds(300, 520, 90, 25);
        frm.add(btn1);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!StringTest.isOnlyContain01(jtf1.getText())) {
                    JOptionPane.showMessageDialog(null, "仅能输入含0或1的字符串！");
                } else {
                    GoldwasserMicaliBinaryEncryption binaryEncryption = (GoldwasserMicaliBinaryEncryption) EncryptionFactory.getEncryption(EncryptionAlgorithm.GoldwasserMicali);
                    binaryEncryption.setPlainText(jtf1.getText());
                    jtf3.setText(GoldwasserMicaliBinaryEncryption.getP().toString());
                    jtf4.setText(GoldwasserMicaliBinaryEncryption.getQ().toString());
                    jtf5.setText(GoldwasserMicaliBinaryEncryption.getN().toString());
                    jtf6.setText(GoldwasserMicaliBinaryEncryption.getX().toString());
                    binaryEncryption.encrypt();
                    jta1.setText(binaryEncryption.showCipherText());
                    jtf7.setText(binaryEncryption.decrypt());
                }
            }

        });

        btn1.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf8.getText().equals("") || jtf8.getText() == null) {
                    GoldwasserMicaliBinaryEncryption.resetKeys();
                } else {
                    if (StringTest.isLegalPositiveInteger(jtf8.getText())) {
                        if (!StringTest.isLegalIntegerBetweenMinAndMax(jtf8.getText(), BigInteger.valueOf(Encryptions.KEYS_BIT_MIN_LENGTH), BigInteger.valueOf(Encryptions.KEYS_BIT_MAX_LENGTH))) {
                            JOptionPane.showMessageDialog(null, "输入的位宽应在" + Encryptions.KEYS_BIT_MIN_LENGTH + "~" + Encryptions.KEYS_BIT_MAX_LENGTH + "之间");
                            return;
                        }
                        int len = Integer.parseInt(jtf8.getText());
                        GoldwasserMicaliBinaryEncryption.resetKeys(len);
                    } else {
                        JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                    }
                }
                jtf3.setText(GoldwasserMicaliBinaryEncryption.getP().toString());
                jtf4.setText(GoldwasserMicaliBinaryEncryption.getQ().toString());
                jtf5.setText(GoldwasserMicaliBinaryEncryption.getN().toString());
                jtf6.setText(GoldwasserMicaliBinaryEncryption.getX().toString());
                jta1.setText("");
                jtf7.setText("");
            }

        });
    }
}
