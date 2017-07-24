package cn.thinkjoy.hsll.util;

import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 * @author yonson
 */
public class JsonUtil {
	
	/**
	 * 对像转化为json字符串
	 * @param obj
	 * @return
	 */
	public static String tranObjectToJsonStr(Object obj){
		
		return JSONObject.toJSONString(obj);
		
	}
	
	/**
	 * json字符串转化为对像
	 * @param jsonStr
	 * @return 
	 * @return
	 */
	public static <T extends Object> T tranjsonStrToObject(String jsonStr,Class<T> clazz){
		
		return (T)JSONObject.parseObject(jsonStr,clazz);
		
	}

}
