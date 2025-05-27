package com.lefancrm.apicenter.util.suning;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/4/28.
 */
public class TestMain {
    public static <T> void main(String[] args) throws Exception{
        String [] strs = {};
        Map<String,String> map = new HashMap();
        Arrays.asList(map);
        Double a = 0D;Double b = 1D;
        if (a == b){
            System.out.println(true);
        }
        cParam("1","2","3");

//        for (String key : cMap().keySet()){
//            System.out.println(key);
//        }
//        System.out.println(cMap().get("key"));
//        cOut();
//        toMessage("小白","伊涅斯塔","巴塞罗那","西班牙","1");
//        Map map = new HashMap();
//        map.put("name","李贤丰");
//        map.put("c","a");
//        map.put("age","1");
//        map.put("d1","2");
//        map.put("d2","3");
//        map.put("f1","4");
//        map.put("f2","5");
//        map.put("l1","6");
//        map.put("l2","7");
//        User t = cls(map,User.class);
//        Field[] fields = User.class.getDeclaredFields();
//        for (Field field : fields){
//            field.setAccessible(true);
//            Object value = field.get(t);
//            System.out.println("field="+field.getName()+",value="+value);
//        }
    }

    private static void cParam(String caseId,String caseName,String caseTel){
        Method[] methods = TestMain.class.getDeclaredMethods();
        for (Method method : methods){
            if ("cParam".equals(method.getName())){
                LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
                String[] names = u.getParameterNames(method);
                for (String s : names){
                    System.out.println("参数:"+s);
                }
            }
        }
    }

    private static void returnMap(){

    }

    private static void cOut(){
        try {
            String value = null;
            if (value.equals("")){
                return;
            }
        }catch (Exception e){
            System.out.println("保存");
            cOut();
        }
    }

    private static void toMessage(String ...params){
        StringBuffer sb = new StringBuffer();
        if (params != null){
            for (int i = 0 ; i < params.length - 1; i++){
                if (i == params.length - 2){
                    sb.append(params[i]);
                    break;
                }
                sb.append(params[i] + ",");
            }
        }
        System.out.println(sb.toString());
    }

    private static <T,D extends Temp> T cls(Map map,Class<T> clazz) throws Exception{
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                D d = ((D) new Temp());
                d.setClazz(field.getType());
                D value = ((D) map.get(field.getName()));
                if (d.getClazz().equals(field.getType())){
                    field.set(t, value);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    private static Map<String,Object> cMap(){
        Map map = new HashMap();
        map.put("key","value1");
        map.put("key1","value2");
        map.put("key2","value3");
        map.put("key3","value4");
        return map;
    }



    public static class User{
        private String name;
//        private char c;
        private Integer age;
        private Double d1;
        private double d2;
        private Float f1;
        private float f2;
        private Long l1;
        private long l2;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public char getC() {
//            return c;
//        }
//
//        public void setC(char c) {
//            this.c = c;
//        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getD1() {
            return d1;
        }

        public void setD1(Double d1) {
            this.d1 = d1;
        }

        public double getD2() {
            return d2;
        }

        public void setD2(double d2) {
            this.d2 = d2;
        }

        public Float getF1() {
            return f1;
        }

        public void setF1(Float f1) {
            this.f1 = f1;
        }

        public float getF2() {
            return f2;
        }

        public void setF2(float f2) {
            this.f2 = f2;
        }

        public Long getL1() {
            return l1;
        }

        public void setL1(Long l1) {
            this.l1 = l1;
        }

        public long getL2() {
            return l2;
        }

        public void setL2(long l2) {
            this.l2 = l2;
        }
    }

    public static class Temp extends Object{
        private Class<?> clazz;

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }
    }
}
