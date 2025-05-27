package com.lefancrm.apicenter.util;

import com.lefancrm.base.dto.ApiRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

public class ConvertToBeanUtil {
    public static <T> T toBean(ApiRequest apiRequest,T t) throws Exception{
        BeanUtils.populate(t, apiRequest);
        return t;
    }
    public static <T> T toBean(ApiRequest apiRequest,Class<T> clazz) throws Exception{
        T t = clazz.newInstance();
        BeanUtils.populate(t,apiRequest);
        return t;
    }
    /**
     * 转换  类
     * 支持Long 、Integer、Double、Date，其他类型待添加
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBeanFromApiRequest(ApiRequest request,Class<T> clazz){
        T  t = null;
        String msg = "";
        try {
            t = clazz.newInstance();
            Field [] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                Object value = request.get(field.getName());
                field.setAccessible(true);
                if (!StringUtils.isEmpty(value)){
                    msg = "fieldName:" + field.getName() + ",value:" + value;
                    if ((Long.class).equals(field.getType())){
                        field.set(t,Long.valueOf(value.toString()));
                    }else if ((Integer.class).equals(field.getType())){
                        field.set(t,Integer.valueOf(value.toString()));
                    }else if ((String.class).equals(field.getType())){
                        field.set(t,String.valueOf(value.toString()));
                    }else if((Double.class).equals(field.getType())){
                        field.set(t,Double.valueOf(value.toString()));
                    }else if ((Date.class).equals(field.getType())){
                        field.set(t,DateUtils.parseDate(value.toString(),"yyyy-MM-dd"));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(msg);
        }
        return t;
    }

    /**
     * 转换  对象
     *持Long 、Integer、Double、Date，其他类型待添加
     * @param request
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T toBeanFromApiRequest(ApiRequest request,T data){
        String msg = "";
        try {
            if (data != null){
                Field [] fields = data.getClass().getDeclaredFields();
                for (Field field : fields){
                    Object value = request.get(field.getName());
                    field.setAccessible(true);
                    //说明是把数据库中原本有的数据改为NULL
                    if (StringUtils.isEmpty(value)){
                        if(request.containsKey(field.getName())){
                            field.set(data,null);
                        }
                        continue;
                    }
                    msg = "field:" + field + ",value:" + value;
                    if (!StringUtils.isEmpty(value)){
                        if ((Long.class).equals(field.getType())){
                            field.set(data,Long.valueOf(value.toString()));
                        }else if ((Integer.class).equals(field.getType())){
                            field.set(data,Integer.valueOf(value.toString()));
                        }else if ((String.class).equals(field.getType())){
                            field.set(data,String.valueOf(value.toString()));
                        }else if((Double.class).equals(field.getType())){
                            field.set(data,Double.valueOf(value.toString()));
                        }else if ((Date.class).equals(field.getType())){
                            field.set(data,DateUtils.parseDate(value.toString(),"yyyy-MM-dd"));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(msg);
        }
        return data;
    }

    /**
     * 转换  对象
     *持Long 、Integer、Double、Date，其他类型待添加
     * @param request
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T toBeanFromApiRequestSuper(ApiRequest request,T data){
        String msg = "";
        try {
            if (data != null){
//                Field [] fields = data.getClass().getDeclaredFields();
                Field [] fields = data.getClass().getSuperclass().getDeclaredFields();
                for (Field field : fields){
                    Object value = request.get(field.getName());
                    field.setAccessible(true);
                    //说明是把数据库中原本有的数据改为NULL
                    if (StringUtils.isEmpty(value)){
                        if(request.containsKey(field.getName())){
                            field.set(data,null);
                        }
                        continue;
                    }
                    msg = "field:" + field + ",value:" + value;
                    if (!StringUtils.isEmpty(value)){
                        if ((Long.class).equals(field.getType())){
                            field.set(data,Long.valueOf(value.toString()));
                        }else if ((Integer.class).equals(field.getType())){
                            field.set(data,Integer.valueOf(value.toString()));
                        }else if ((String.class).equals(field.getType())){
                            field.set(data,String.valueOf(value.toString()));
                        }else if((Double.class).equals(field.getType())){
                            field.set(data,Double.valueOf(value.toString()));
                        }else if ((Date.class).equals(field.getType())){
                            field.set(data,DateUtils.parseDate(value.toString(),"yyyy-MM-dd"));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(msg);
        }
        return data;
    }

    /**
     *   晚点再优化    只适合当前子类  父类  单继承转换
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBeanSuperFromApiRequest(ApiRequest request,Class<T> clazz){
        T  t = null;
        try {
            t = clazz.newInstance();
            Field [] fields = clazz.getDeclaredFields();
            //当前类的
            for (Field field : fields){
                Object value = request.get(field.getName());
                field.setAccessible(true);
                if (!StringUtils.isEmpty(value)){
                    if ((Long.class).equals(field.getType())){
                        field.set(t,Long.valueOf(value.toString()));
                    }else if ((Integer.class).equals(field.getType())){
                        field.set(t,Integer.valueOf(value.toString()));
                    }else if ((String.class).equals(field.getType())){
                        field.set(t,String.valueOf(value.toString()));
                    }else if((Double.class).equals(field.getType())){
                        field.set(t,Double.valueOf(value.toString()));
                    }else if ((Date.class).equals(field.getType())){
                        field.set(t,DateUtils.parseDate(value.toString(),"yyyy-MM-dd"));
                    }
                }
            }
            //父类
            Class superClass = clazz.getSuperclass();
            if (superClass != null){
                fields = superClass.getDeclaredFields();
                for (Field field : fields){
                    Object value = request.get(field.getName());
                    field.setAccessible(true);
                    if (!StringUtils.isEmpty(value)){
                        if ((Long.class).equals(field.getType())){
                            field.set(t,Long.valueOf(value.toString()));
                        }else if ((Integer.class).equals(field.getType())){
                            field.set(t,Integer.valueOf(value.toString()));
                        }else if ((String.class).equals(field.getType())){
                            field.set(t,String.valueOf(value.toString()));
                        }else if((Double.class).equals(field.getType())){
                            field.set(t,Double.valueOf(value.toString()));
                        }else if ((Date.class).equals(field.getType())){
                            field.set(t,DateUtils.parseDate(value.toString(),"yyyy-MM-dd"));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 构建信息
     * @param clazz  转换的结果对象类型
     * @param d      需要转换的对象
     * @param <T>
     * @param <D>
     * @return
     */
    public static <T,D> T buildInfo(Class<T> clazz ,D d){
        T t =  null;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                Field[] fields1 = d.getClass().getDeclaredFields();
                for(Field field1 : fields1){
                    field1.setAccessible(true);
                    if (field.getName().equals(field1.getName()) && field.getType() == field1.getType()){
                        Object value = field1.get(d);
                        field.set(t,value);
                    }
                }
            }

            //父类  只适合单继承
            Class superClass = clazz.getSuperclass();
            if (superClass != null){
                fields = superClass.getDeclaredFields();
                for (Field field : fields){
                    field.setAccessible(true);
                    Field[] fields1 = d.getClass().getDeclaredFields();
                    for(Field field1 : fields1){
                        field1.setAccessible(true);
                        if (field.getName().equals(field1.getName()) && field.getType() == field1.getType()){
                            Object value = field1.get(d);
                            field.set(t,value);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return t;
    }

    public static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }
}
