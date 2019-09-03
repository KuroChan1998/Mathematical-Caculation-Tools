/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfEulerFuction.java
 * @Package com.jzy.gui
 * @Description: 欧拉函数值的计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:25:05
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
import com.jzy.xxaqsxjc.method0.EulerFunction;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * @ClassName: WindowOfEulerFuction
 * @Description: 欧拉函数值的计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:25:05
 *
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfEulerFuction {

    /**
     * @Title: GUIEulerFuction
     * @Description: 欧拉函数值的计算模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIEulerFuction(JFrame frm) {
        JLabel j11 = new JLabel("4、欧拉函数值的计算：输入n，返回n的欧拉函数值Euler(n)");
        j11.setBounds(50, 150, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(400, 150, 80, 25);
        frm.add(btn);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 500, 150); // 设置窗口初始位置和大小
                frmHidden.setTitle("欧拉函数值的计算：输入n，返回n的欧拉函数值Euler(n)"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUIEulerFuctionHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });
    }

    /**
     * @Title: GUIEulerFuctionHidden
     * @Description: 点击进入后的欧拉函数值的计算模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIEulerFuctionHidden(JFrame frm) {
        JLabel j12 = new JLabel("n=");
        j12.setBounds(50, 30, 20, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(70, 30, 250, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JButton btn = new JButton("计算");
        btn.setBounds(340, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("Euler(n)=");
        j14.setBounds(50, 60, 80, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(110, 60, 250, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalIntegerBetweenMinAndMax(jtf1.getText(), Method0.VALUE_1, EulerFunction.MAX_INPUT_RECOMMENDED)) {
                    BigInteger n = new BigInteger(jtf1.getText());
                    BigInteger Euler_n = Method0.euler(n);
                    jtf3.setText(Euler_n.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "请输入" + Method0.VALUE_1 + "~" + EulerFunction.MAX_INPUT_RECOMMENDED + "内的数！");
                }
            }
        });
    }
}
