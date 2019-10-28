package cn.com.hugedata.spark.commons.service.approval;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: ContentUtil.java
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Zhangshuanghua
* @date 2017年11月10日 上午10:38:51 
* 类说明 
*/
public class ContentUtil {
	public static final Map<Integer, String> DEPT_PY = new HashMap<Integer, String>();  
	  
    static {  
    	DEPT_PY.put(1, "JFJ");  
    	DEPT_PY.put(2, "GTJ");  
    	DEPT_PY.put(3, "GJJ");  
    	DEPT_PY.put(4, "SSJ");  
    	DEPT_PY.put(5, "ZSJ");  
    	DEPT_PY.put(6, "CWJ");  
    	DEPT_PY.put(7, "BGS");  
    	DEPT_PY.put(8, "DJB");  
    }
	
	public static String produceRandom(){
		  SimpleDateFormat simpleDateFormat;

    	  simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    	  Date date = new Date();

    	  String str = simpleDateFormat.format(date);

//    	  Random random = new Random();
//
//    	  int rannum = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
    	  
//    	  return str + rannum;
    	  return str;
	}

}
