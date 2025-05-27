package com.lefancrm.apicenter.util.bdaiPUtils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jani on 2019/7/24.
 */
public class CharacterRecognitionUtils {

    private CharacterRecognitionUtils() {}
    private static CharacterRecognitionUtils characterRecognitionUtils=null;
    //设置APPID/AK/SK
    public static final String APP_ID = "16869271";
    public static final String API_KEY = "588qfB74FiAVwVaFo521zE3q";
    public static final String SECRET_KEY = "42CAMiexhWVrEaGNOc7GmgXqSKbwCcTo";

    //静态工厂方法
    public static CharacterRecognitionUtils getInstance() {
        if (characterRecognitionUtils == null) {
            characterRecognitionUtils = new CharacterRecognitionUtils();
        }
        return characterRecognitionUtils;
    }

    //本地识别文字
    public   JSONObject aipRecognition(String url) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口百度接口
        String path = url;
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        return  res;
    }
    //远程识别文字
    public   JSONObject aipRecognitionUrl(String url) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口百度接口
        String path = url;
        JSONObject res = client.basicGeneralUrl(path, options);
        return  res;
    }
}
