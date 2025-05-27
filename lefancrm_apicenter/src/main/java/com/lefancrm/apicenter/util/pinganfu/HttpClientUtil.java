package com.lefancrm.apicenter.util.pinganfu;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title: HttpClientUtil</p>
 * <p>Description: </p>
 * <p>Company: www.yqb.com</p>
 *
 * @author lixia
 * @version 1.0
 * @date 2017年12月6日上午10:38:24
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final Charset CHARSET_ISO8859_1 = Charset
            .forName("iso8859_1");

    private static final String PROPERTY_TYPE = "Content-Type";
    private static final String PROPERTY = "application/x-www-form-urlencoded";

    /**
     * <p>Title: execute</p>
     * <p>Description: </p>
     *
     * @param url    http://jk-bis-stg.dmzstg.pingan.com.cn:7080/bis/service
     * @param xmlStr
     * @return
     */
    public static String execute(String url, String xmlStr) throws Exception {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) (new URL(url).openConnection());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw e;
        }

        DataOutputStream out = null;
        try {
            conn.setConnectTimeout(120 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setRequestProperty(PROPERTY_TYPE, PROPERTY);
            conn.connect();

            byte[] data = xmlStr.getBytes();
            out = new DataOutputStream(conn.getOutputStream());
            out.write(org.apache.commons.codec.binary.Base64
                    .encodeBase64(data));
            out.flush();
            out.close();
            logger.info(conn.getResponseCode() + ">>"
                    + conn.getResponseMessage());

            byte[] bytes = org.apache.commons.codec.binary.Base64
                    .decodeBase64(toByteArray(conn.getInputStream()));

            String ret = "";
            if (bytes == null || bytes.length == 0) {
                System.out.println("time out");
            } else {

                // 返回乱码用这个
                ret = new String(bytes, "UTF-8");
//				String code = getNodeValue(ret, "returnCode");
//				System.out.println("------------------:" + code);
                logger.info(ret);
            }
            conn.disconnect();
            return ret;
        } catch (Exception e) {
            throw e;
        }
    }


    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int len;
        while ((len = is.read(data)) != -1) {
            out.write(data, 0, len);
            out.flush();
        }
        return out.toByteArray();
    }

    public static String executeGet(String scheme, String host, int port, String path, Map<String, String> paramMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {

            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        String strResult = null;

        try {
            URI uri = new URIBuilder()
                    .setScheme(scheme)
                    .setHost(host)
                    .setPort(port)
                    .setPath(path).setParameters(parameters).build();

            HttpGet httpGet = new HttpGet(uri);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity(),Constants.CHARSET);//获得返回的结果
                System.out.println(strResult);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strResult;

    }

    public static String executeGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String strResult = null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity(), Constants.CHARSET);//获得返回的结果
                System.out.println(strResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }

    public static String executePost(String uri, Map<String, String> paramMap) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(uri);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {

            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        String strResult = null;

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parameters, Consts.UTF_8));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity(),Constants.CHARSET);//获得返回的结果
                logger.info("post请求返回信息为：{}",strResult);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }

    public static void main(String[] args) {
        String uri="https://test-mzone.stg.yqb.com/mzone-http/bind_card/bind_card_entrance_for_outer";
        Map<String, String> map = new HashMap<String, String>();

        map.put("cancelUrl", "");
        map.put("requestMsg","qpiPPL38yc/j/Ip4P0z9L56xEHf38GfbDNqUeMUyHtolsRvkBpTf3YDENN+ytrQjYu64wKiX7oReFwAsmRGLYbr0lCMLNGhHdfOoD5trDRfGXxGzhy3GB5hwu3O07h8LXWyGqWTFHXy+4GubLVp9TAWU/moGbf/Vj8WxiV9f5BFz/KKU1ATqLTCAnqB6RHTJqS0+lanJKHByYJFDjMHNotZZlh1RIFy95Q1CCZx8kMFRVprOmPj4YtBgqs0Ac5+VLL0JwshPVqQdvszyHMoa+mKn6abtjIOhH/4Go2GdfwpobXGun/xAANVdWFRLHCH0PYjjJ6tZdttxlYngdY1BTQ==");

        map.put("extfield", "");
        map.put("requestNo", "");
        map.put("returnUrl", "https://bind_card/bind_card_entrance_for_outer");
        map.put("version","1.0");
        map.put("merchantNo", "900000112256");

        try {
            HttpClientUtil.executePost(uri, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
