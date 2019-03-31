/**  
 * All rights Reserved, Designed By CyborgKuroChan
 * @Title:  Test.java   
 * @Package xxaqsxjc.method0   
 * @Description:    提供一些验证输入格式的方法  
 * @author: JinZhiyun    
 * @date:   2019年3月16日 上午8:31:26   
 * @version V1.0 
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package someTest;

import java.util.regex.Pattern;

/**   
 * @ClassName:  Test   
 * @Description: 提供一些验证输入格式的方法   
 * @author: JinZhiyun
 * @date:   2019年3月16日 上午8:30:54   
 *     
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */ 
public class Test {
	/**   
	 * @Title: isOnlyContain01   
	 * @Description: 对GoldwasserMicali加密算法（对二进制加密）的输入子串是否只含0、1的判断    
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public static boolean isOnlyContain01(String str) {
	    Pattern pattern = Pattern.compile("[0-1]*");
	    return pattern.matcher(str).matches(); 
	}
	
	/**   
	 * @Title: isLegalInteger   
	 * @Description: 判断输入是否合法，即非空且是数字   
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */ 
	public static boolean isLegalInteger(String str) {
		return (isInteger(str) && !str.equals(""))? true:false;
	}
	
	/**   
	 * @Title: isInteger   
	 * @Description: 判断输入是否全为数字
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */ 
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
}
