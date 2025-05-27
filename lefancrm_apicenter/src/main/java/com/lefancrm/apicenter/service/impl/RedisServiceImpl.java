package com.lefancrm.apicenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.apicenter.dto.UserSessionDto;
import com.lefancrm.apicenter.service.RedisService;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl extends BaseServiceImpl implements RedisService {
	/**
	 * 所有事件的坐标
	 */
	private static final String EVENTS_GEO_LIST = "events-geo-list-";

	/**
	 * 单个事件坐标
	 */
	private static final String EVENT_GEO_LIST = "event-geo-list-";

	/**
	 * 单个订单推送日志
	 */
	private static final String ORDER_PUSH_LOG = "order-push-log-";

	/**
	 * 单个用户session
	 */
	private static final String USER_SESSION_PREFIX = "user-session-";

	/**
	 * 单个用户ID
	 */
	private static final String USER_ID_PREFIX = "user-id-";

    /**
     * 测算数据
     */
    private static final String ESTIMATE_PREFIX = "estimate-id-";

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> stringList;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;

	 @Resource(name = "redisTemplate")
	 private GeoOperations<String, Integer> eventsGeoOps;

	@Resource(name = "redisTemplate")
	private ListOperations<String, Point> eventGeoList;

	@Resource(name = "redisTemplate")
	private ListOperations<String, Point> orderGeoList;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> orderStatusList;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> orderPushLog;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> estimateList;



	@Override
	public void eventGeoAdd(Integer eventId, double lng, double lat) {
		eventsGeoOps.geoAdd(EVENTS_GEO_LIST, new Point(lng, lat), eventId);
		eventGeoList.leftPush(EVENT_GEO_LIST + eventId, new Point(lng, lat));
	}





	 @Override
	 public GeoResults<RedisGeoCommands.GeoLocation<Integer>> eventGeoRadius(double lng, double lat, double radius) {
	 return eventsGeoOps.geoRadius(EVENTS_GEO_LIST, new
					 Circle(new Point(lng, lat), radius),
	 RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance());
	 }

	@Override
	public void eventGeoRemove(Integer eventId) {
		 eventsGeoOps.geoRemove(EVENTS_GEO_LIST, eventId);
	}

	@Override
	public void eventGeoUpdate(Integer eventId, double lng, double lat) {
		eventGeoList.leftPush(EVENT_GEO_LIST + eventId, new Point(lng, lat));
	}

	@Override
	public void orderPushLogAdd(Integer orderId, String pushData) {
		orderPushLog.leftPush(ORDER_PUSH_LOG + orderId, pushData);
	}

	@Override
	public void setUserSession(String userToken, UserSessionDto sessionDto) {
		this.set(USER_SESSION_PREFIX + userToken, JsonUtil.objectToJson(sessionDto), 30 * 24 * 60 * 60);
		this.set(USER_ID_PREFIX + sessionDto.getUserId(), userToken);
	}

	@Override
	public UserSessionDto getUserSession(String userToken) {
		String value = this.get(USER_SESSION_PREFIX + userToken);
		if (value != null) {
			return JsonUtil.jsonToObject(value, UserSessionDto.class);
		}
		return null;
	}

	@Override
	public void removeUserSession(String userToken) {
		this.set(USER_SESSION_PREFIX + userToken, null, 1);
	}

	@Override
	public void removeUserSession(Long userId) {
		String userToken = this.get(USER_ID_PREFIX + userId);
		if (!StringUtils.isEmpty(userToken)) {
			this.removeUserSession(userToken);
			this.set(USER_ID_PREFIX + userId, null, 1);
		}
	}

	@Override
	public void set(String key, String value) {
		valueOps.set(key, value);
	}

	@Override
	public void set(String key, String value, long timeoutInSeconds) {
		valueOps.set(key, value, timeoutInSeconds, TimeUnit.SECONDS);
	}

	@Override
	public String get(String key) {

		return valueOps.get(key);
	}

    @Override
    public <T> void estimateAdd(String keyStr,List<T> list,long timeoutInSeconds) {
        /*List<Object> objects=new ArrayList<Object>();
        for(T t:list){
            Object obj=t;
            objects.add(obj);
        }*/

        String json= JSONObject.toJSONString(list);
        this.set(keyStr, json, timeoutInSeconds);
    }

    @Override
    public void estimateRemove(String keyStr) {
        this.set(keyStr, null, 1);
    }

    @Override
    public <T> List<T> estimateGet(String keyStr,T t){
        String jsonStr=this.get(keyStr);
        List<T> list = null;
        Object listInRedis = null;
        if(jsonStr != null) listInRedis = JSONObject.parseArray(jsonStr, t.getClass());
        if (listInRedis instanceof List) {
            list = (List) listInRedis;
        }
     /*List<T> list= (List<T>) estimateList.getOperations().boundListOps(keyStr);*/
        return list;
    }
}
