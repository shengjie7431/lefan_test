package com.lefancrm.apicenter.util.pinganfu;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title: ConvertUtils</p>
 * <p>Description: </p>
 * <p>Company: www.yqb.com</p> 
 * @author	lixia
 * @date	2017年12月6日下午7:29:10
 * @version 1.0
 */
public class ConvertUtils {

	public static <T> Map<String,String> convertBean2Map(T bean){
		Map<String, String> map = new HashMap<String, String>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key+"", beanMap.get(key)+"");
			}
		}
		return map;
	}

	public static <T> T map2Bean(Map<String,Object> map,T bean){
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}


}
