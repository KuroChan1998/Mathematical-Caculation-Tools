/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  Demo4.java   
 * @Package gui   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午5:28:42   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package gui;

/**   
 * @ClassName:  Demo4   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午5:28:42   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Demo4 {
  MyJPanel mp;
  int index;
  ImageIcon[] imgs = {
          new ImageIcon("src/picture/1.jpg"),
          new ImageIcon("src/picture/2.jpg"),
          new ImageIcon("src/picture/3.jpg"),
          new ImageIcon("src/picture/4.jpg"),
          new ImageIcon("src/picture/5.jpg"),
          new ImageIcon("src/picture/6.jpg"),
          new ImageIcon("src/picture/7.jpg"),
          new ImageIcon("src/picture/8.jpg"),
      };

  public Demo4(JFrame frm) {
      mp = new MyJPanel();;
      frm.add(mp);
      frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
//      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frm.setTitle("窗口");
      frm.setVisible(true);
      Timer timer = new Timer(500,new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              mp.repaint();
          }
      });
      timer.start();
  }

  public static void main(String[] args) {
      new Demo4(new JFrame());
  }
  class MyJPanel extends JPanel{
      @Override
      public void paint(Graphics g) {
          super.paint(g);
          g.drawImage(imgs[index%imgs.length].getImage(), 10, 10,400,400,this);
          index++;
      }
  }
}
