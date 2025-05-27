import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.util.HttpClientUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lefancrm.apicenter.service.RedisService;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SpringTest {
	private static String appId2 = "wx737e361255f28485";
	private static String appSecret2 = "0cc208e330ff2fd1a7602d1363314523";


	public static void main(String[] args) {
		try {
			getAccess_token();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static String getAccess_token() throws Exception{
		Jedis jedis = new Jedis("139.196.30.32",6379);
		jedis.auth("shlefan.com123");
		if (!jedis.exists("weChatToken")) {
			String access_token_json="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId2+"&secret="+appSecret2+"";
			Map<String,String> map=new HashMap<String,String>();
			String str = HttpClientUtils.httpPost(access_token_json,map);
			JSONObject accessTokenJsonObj = JSON.parseObject(str);
			String accessToken = accessTokenJsonObj.get("access_token").toString();
			jedis.set("weChatToken",accessToken);
			jedis.expire("weChatToken",3);

			System.out.println(jedis.get("weChatToken"));
			Thread.sleep(3032);
			System.out.println(jedis.get("weChatToken"));
		}else{
			return jedis.get("weChatToken");
		}
		return null;
	}
}
