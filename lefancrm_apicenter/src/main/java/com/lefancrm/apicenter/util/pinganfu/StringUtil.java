package com.lefancrm.apicenter.util.pinganfu;

import org.apache.commons.httpclient.NameValuePair;

import java.math.BigDecimal;
import java.util.*;

public class StringUtil {

    /**
     * 将字符串分割然后以键值对存入 LinkHashMap
     * 
     * @param s
     * @return
     */
    public static Map<String, String> splitStringToMap(String s) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        String[] temp = splitRequest(s); // 将字符串拆分成一维数组
        for (int i = 0; i < temp.length; i++) {
            String[] arr = temp[i].split("="); // 继续分割并存到另一个一临时的一维数组当中
            String value;
            if (arr.length == 1) {
                value = "";
            } else if (arr.length > 2) {
                value = temp[i].substring(arr[0].length() + 1);
            } else {
                value = arr[1];
            }
            map.put(arr[0], value);
        }
        return map;
    }
    
    /**
     * 将字符串分割然后以键值对存入 LinkHashMap
     * 
     * @param s
     * @return
     */
    public static Map<String, String[]> splitStringToMapArray(String s) {
        LinkedHashMap<String, String[]> map = new LinkedHashMap<String, String[]>();
        String[] temp = splitRequest(s); // 将字符串拆分成一维数组
        for (int i = 0; i < temp.length; i++) {
            String[] arr = temp[i].split("="); // 继续分割并存到另一个一临时的一维数组当中
            String value;
            if (arr.length == 1) {
                value = "";
            } else if (arr.length > 2) {
                value = temp[i].substring(arr[0].length() + 1);
            } else {
                value = arr[1];
            }
            value = value.replace("[", "").replace("]", "").replace("{", "").replace("}", "");
            String[] itemList = value.split(",");
            map.put(arr[0], itemList);
        }
        return map;
    }

    /**
     * Map转NameValuePair[]
     * 
     * @param map
     * @return
     */
    public static NameValuePair[] mapToNameValuePair(Map<String, String> map) {
        NameValuePair[] para = new NameValuePair[map.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            para[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return para;
    }

    /**
     * Map转NameValuePair[]
     * 
     * @param map
     * @return
     */
    /**
     * Map转NameValuePair[]
     * 
     * @param map
     * @return
     */
    public static NameValuePair[] mapArrayToNameValuePair(Map<String, String[]> map) {
        List<String[]> list = new ArrayList<String[]>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                list.add(new String[]{entry.getKey(), value});
            }
        }
        NameValuePair[] para = new NameValuePair[list.size()];
        int i = 0;
        for(String[] nameValue : list){
            para[i++] = new NameValuePair(nameValue[0], nameValue[1]);
        }
        return para;
    }

    /**
     * 将NameValuePairs数组转变为字符串
     * 
     * @param nameValues
     * @return
     */
    public static String nameValuePairToString(NameValuePair[] nameValues) {
        if (nameValues == null || nameValues.length == 0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < nameValues.length; i++) {
            NameValuePair nameValue = nameValues[i];
            if(nameValue == null){
                continue;
            }
            
            if (i == 0) {
                buffer.append(nameValue.getName() + "=" + nameValue.getValue());
            } else {
                buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
            }
        }

        return buffer.toString();
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String mapArrayToString(Map<String, String[]> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String[] values = params.get(key);
            StringBuffer value = new StringBuffer();
            if (values[0].indexOf("commodityNo=")!=-1) {//判断字段值是产品信息，yfj
                value.append("[");
                for (int j = 0; j < values.length; j++) {
                    String val = values[j];
                    value.append("{");
                    value.append(val);
                    if (j == values.length - 1) {
                        value.append("}");
                    } else {
                        value.append("},");
                    }
                }
                value.append("]");
            } else {
                value.append(values[0]);
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key.replace("[]", "") + "=" + value.toString();
            } else {
                prestr = prestr + key.replace("[]", "") + "=" + value.toString() + "&";
            }
        }

        return prestr;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String mapToString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }



    public static String[] splitRequest(String request) {
        if (request == null || request.length() == 0) {
            return null;
        }
        if (!request.contains("[")) {
            return request.split("&");
        }
        StringBuffer sb = new StringBuffer();
        int i = request.indexOf("=");
        String key = request.substring(0, i);
        sb.append(key).append("=");
        request = request.substring(i + 1, request.length());
        String value = "";
        int j = 0;
        if (request.startsWith("[")) {
            int l = 0;
            int r = 0;
            for (j = 0; j < request.length(); j++) {
                if (request.charAt(j) == '[') {
                    l++;
                }
                if (request.charAt(j) == ']') {
                    r++;
                }
                if (l != 0 && l == r) {
                    break;
                }
            }
            j++;
        } else {
            j = request.indexOf("&");
        }
        value = request.substring(0, j);
        sb.append(value);
        if(j < request.length()){
            request = request.substring(j + 1, request.length());
        }else{
            request = "";
        }
        String[] reqs = splitRequest(request);
        if (reqs != null) {
            String[] retReqs = Arrays.copyOf(reqs, reqs.length + 1);
            retReqs[retReqs.length - 1] = sb.toString();
            return retReqs;
        } else {
            String[] retReqs = new String[1];
            retReqs[0] = sb.toString();
            return retReqs;
        }
    }



}

class Test {
    private List<SubClass> list;
    private String s;
    private Date d;
    private BigDecimal b;
    private boolean i;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public boolean isI() {
        return i;
    }

    public void setI(boolean i) {
        this.i = i;
    }

    public List<SubClass> getList() {
        return list;
    }

    public void setList(List<SubClass> list) {
        this.list = list;
    }

}

class SubClass {
    private String a;
    private int b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

}
