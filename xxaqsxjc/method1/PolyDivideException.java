/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  PolyDivideException.java   
 * @Package xxaqsxjc.method1   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: JinZhiyun    
 * @date:   2019年3月16日 下午1:15:47   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package xxaqsxjc.method1;

/**   
 * @ClassName:  PolyDivideException   
 * @Description: 自定义多项式异常处理类
 * @author: JinZhiyun
 * @date:   2019年3月16日 下午1:15:47   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
 
public class PolyDivideException extends Exception {
	private static final long serialVersionUID = 1L;

	public PolyDivideException() {
	}

	public PolyDivideException(String message) {
		super(message);
	}
}
