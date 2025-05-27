package com.lefancrm.apicenter.service;

import com.lefancrm.apicenter.dto.UserSessionDto;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.List;

public interface RedisService {
	/**
	 * 事件位置添加
	 * 
	 * @param eventId
	 * @param lng
	 * @param lat
	 * @author Daniel
	 */
	void eventGeoAdd(Integer eventId, double lng, double lat);

	/**
	 *
	 * 事件位置删除
	 *
	 * @param eventId
	 * @author Daniel
	 */
	void eventGeoRemove(Integer eventId);


	/**
	 *
	 * 事件位置修改
	 *
	 * @param eventId
	 * @author Daniel
	 */
	void eventGeoUpdate(Integer eventId, double lng, double lat);

	 /**
	 * 事件位置匹配
	 *
	 * @param lng
	 * @param lat
	 * @param radius
	 * @return
	 * @author Daniel
	 */
	 GeoResults<RedisGeoCommands.GeoLocation<Integer>> eventGeoRadius(double lng, double lat, double radius);


	/**
	 * 订单推送日志添加
	 * 
	 * @param orderId
	 * @param pushData
	 * @author Daniel
	 */
	void orderPushLogAdd(Integer orderId, String pushData);

	/**
	 * 设置用户session
	 * 
	 * @param userToken
	 * @param sessionDto
	 * @author Daniel
	 */
	void setUserSession(String userToken, UserSessionDto sessionDto);

	/**
	 * 获取用户session
	 * 
	 * @param userToken
	 * @return
	 * @author Daniel
	 */
	UserSessionDto getUserSession(String userToken);

	/**
	 * 删除用户session
	 * 
	 * @param userToken
	 * @author Daniel
	 */
	void removeUserSession(String userToken);

	void removeUserSession(Long userId);

	void set(String key, String value);

	void set(String key, String value, long timeoutInSeconds);

	String get(String key);

    <T> void estimateAdd(String keyStr,List<T> list,long timeoutInSeconds);

    <T> List<T> estimateGet(String keyStr,T t);

    void estimateRemove(String keyStr);

}
