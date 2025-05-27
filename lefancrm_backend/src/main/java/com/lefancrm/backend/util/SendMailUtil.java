/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lefancrm.backend.util;

import com.lefancrm.backend.dto.survey.SurveyEmailInfoDTO;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送电子邮件
 */
public class SendMailUtil {
    private static final String from = "claim_investigate2@zhengyanjr.com";
    private static final String fromName = "测试";
    private static final String charSet = "utf-8";
    private static final String username = "claim_investigate2@zhengyanjr.com";
    private static final String password = "Sw13816361856";//Sw13816361856
    private static final String authPassword = "";//Sw13816361856856




//    private static final String from = "zqarron@163.com";
//    private static final String fromName = "测试";
//    private static final String charSet = "utf-8";
//    private static final String username = "zqarron@163.com";
//    private static final String password = "";//
//    private static final String authPassword = "YIAGBZAAIICSIOQU";//

    private static Map<String, String> hostMap = new HashMap<String, String>();

    static {
        // 126
        hostMap.put("smtp.126", "smtp.126.com");
        // qq
        hostMap.put("smtp.qq", "smtp.qq.com");

//        hostMap.put("smtp.163", "220.181.12.14");
        // sina
        hostMap.put("smtp.sina", "smtp.sina.com.cn");
        // tom
        hostMap.put("smtp.tom", "smtp.tom.com");
        // 263
        hostMap.put("smtp.263", "smtp.263.net");
        // yahoo
        hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");
        // hotmail
        hostMap.put("smtp.hotmail", "smtp.live.com");
        // gmail
        hostMap.put("smtp.gmail", "smtp.gmail.com");
        hostMap.put("smtp.port.gmail", "465");


        // 163
        hostMap.put("smtp.163", "smtp.163.com");
        hostMap.put("smtp.port.163", "465");
        //网易企业邮箱
        hostMap.put("smtp.zhengyanjr", "smtphz.qiye.163.com");
        hostMap.put("smtp.port.zhengyanjr", "465");
    }

    /**
     * 获取主机
     * @param email
     * @return
     * @throws Exception
     */
    public static String getHost(String email) throws Exception {
        Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
        Matcher matcher = pattern.matcher(email);
        String key = "unSupportEmail";
        if (matcher.find()) {
            key = "smtp." + matcher.group(1);
        }
        System.out.println(key);
        if (hostMap.containsKey(key)) {
            return hostMap.get(key);
        } else {
            throw new Exception("unSupportEmail");
        }
    }

    /**
     * 获取端口
     * @param email
     * @return
     * @throws Exception
     */
    public static int getSmtpPort(String email) throws Exception {
        Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
        Matcher matcher = pattern.matcher(email);
        String key = "unSupportEmail";
        if (matcher.find()) {
            key = "smtp.port." + matcher.group(1);
        }
        if (hostMap.containsKey(key)) {
            return Integer.parseInt(hostMap.get(key));
        } else {
            return 25;
        }
    }

    /**
     * 发送模板邮件
     *
     * @param toMailAddr   收信人地址
     * @param subject      email主题
     * @param templatePath 模板地址
     * @param map          模板map
     */
    public static void sendFtlMail(String toMailAddr, String subject,
                                   String templatePath, Map<String, Object> map) {
        sendFtlMail(new String[]{toMailAddr}, subject, templatePath, map,null);
    }


    public static void sendFtlMail(String toMailAddr, String subject,
                                   String templatePath, Map<String, Object> map,File file) {
        sendFtlMail(new String[]{toMailAddr}, subject, templatePath, map,file);
    }

    public static void sendFtlMail(String[] toMailAddr, String subject,
                                   String templatePath, Map<String, Object> map,File file) {
        HtmlEmail hemail = new HtmlEmail();
        try {
            hemail.setHostName(getHost(from));
            hemail.setSmtpPort(getSmtpPort(from));
            hemail.setCharset(charSet);
            hemail.addTo(toMailAddr);
            hemail.setFrom(from, fromName);
            if (StringUtils.isEmpty(authPassword)){//如果授权码是NULL 则用密码登录
                hemail.setAuthentication(username, password);
            }else{
                hemail.setAuthentication(username, authPassword);
            }
            hemail.setSubject(subject);//邮件标题
            if (file != null){
                hemail.attach(file);
            }

            hemail.setStartTLSEnabled(false);
            hemail.setSSLOnConnect(true);
            hemail.setSslSmtpPort(hemail.getSmtpPort());

            hemail.setMsg("请查收！");
            hemail.send();
            System.out.println("email send true!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("email send error!");
        }
    }

    public static Boolean sendEmail(String subject, SurveyEmailInfoDTO emailInfo,File file){
        System.out.println(emailInfo.toString());
        HtmlEmail hemail = new HtmlEmail();
        try {
            if (StringUtils.isEmpty(emailInfo.getEmailAddress())){
                return false;
            }
            if (StringUtils.isEmpty(emailInfo.getToEmailAddress())){
                return false;
            }
            hemail.setHostName(getHost(emailInfo.getEmailAddress()));
            hemail.setSmtpPort(getSmtpPort(emailInfo.getEmailAddress()));
            hemail.setCharset(charSet);
            hemail.addTo(emailInfo.getToEmailAddress());
            hemail.setFrom(emailInfo.getEmailAddress(), emailInfo.getEmailUserName());
            if (StringUtils.isEmpty(emailInfo.getEmailAuthPassword())){//如果授权码是NULL 则用密码登录
                hemail.setAuthentication(emailInfo.getEmailAddress(), emailInfo.getEmailPassword());
            }else{
                hemail.setAuthentication(emailInfo.getEmailAddress(), emailInfo.getEmailAuthPassword());
            }
            hemail.setSubject(subject);//邮件标题
            if (file != null){
                hemail.attach(file);
            }

            hemail.setStartTLSEnabled(false);
            hemail.setSSLOnConnect(true);
            hemail.setSslSmtpPort("465");

            hemail.setMsg("请查收！");
            hemail.send();
            System.out.println("email send true!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("email send error!");
        }
        return false;
    }


    public static Boolean sendEmail(File file,String subject, String content, String formAddress, String fromUserName, String formAddressPwd, String toAddress, String ... cc){
        HtmlEmail hemail = new HtmlEmail();
        try {
            if (StringUtils.isEmpty(formAddress)){
                return false;
            }
            if (StringUtils.isEmpty(toAddress)){
                return false;
            }
            hemail.setHostName(getHost(formAddress));
            hemail.setSmtpPort(getSmtpPort(formAddress));
            hemail.setCharset(charSet);
            hemail.addTo(toAddress);
            if (cc.length > 0){
                if ("".equals(cc[0])) {

                }else{
                    hemail.addCc(cc);//抄送邮箱
                }
            }
            hemail.setFrom(formAddress, fromUserName);
            hemail.setAuthentication(formAddress,formAddressPwd);
            hemail.setSubject(subject);//邮件标题
            if (file != null){
                hemail.attach(file);
            }

            hemail.setStartTLSEnabled(false);
            hemail.setSSLOnConnect(true);
            hemail.setSslSmtpPort("465");

            hemail.setMsg(StringUtils.isEmpty(content) ? "请查收！" : content);
            hemail.send();
            System.out.println("email send true!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("email send error!");
        }
        return false;
    }




    public static Boolean sendEmail1(String subject, SurveyEmailInfoDTO emailInfo,File file){
//        System.out.println(emailInfo.toString());
        HtmlEmail hemail = new HtmlEmail();
        try {
            if (StringUtils.isEmpty("zqarron@163.com")){
                return false;
            }
            if (StringUtils.isEmpty("892464537@qq.com")){
                return false;
            }
            hemail.setHostName(getHost("zqarron@163.com"));
            hemail.setSmtpPort(getSmtpPort("zqarron@163.com"));
            hemail.setCharset(charSet);
            hemail.addTo("892464537@qq.com");
            hemail.setFrom("zqarron@163.com", "李贤丰");
            if (StringUtils.isEmpty("YIAGBZAAIICSIOQU")){//如果授权码是NULL 则用密码登录
                hemail.setAuthentication("zqarron@163.com", "woshirencai");
            }else{
                hemail.setAuthentication("zqarron@163.com", "YIAGBZAAIICSIOQU");
            }
            hemail.setSubject(subject);//邮件标题
            if (file != null){
                hemail.attach(file);
            }
            hemail.setMsg("请查收！");
            hemail.send();
            System.out.println("email send true!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("email send error!");
        }
        return false;
    }


    private static String getFilePath() {
        String path = getAppPath(SendMailUtil.class);
        path = path + File.separator + "mailtemplate" + File.separator;
        path = path.replace("\\", "/");
        System.out.println(path);
        return path;
    }

    private static String getFileName(String path) {
        path = path.replace("\\", "/");
        System.out.println(path);
        return path.substring(path.lastIndexOf("/") + 1);
    }

    //	@SuppressWarnings("unchecked")
    public static String getAppPath(Class<?> cls) {
        // 检查用户传入的参数是否为空
        if (cls == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        ClassLoader loader = cls.getClassLoader();
        // 获得类的全名，包括包名
        String clsName = cls.getName() + ".class";
        // 获得传入参数所在的包
        Package pack = cls.getPackage();
        String path = "";
        // 如果不是匿名包，将包名转化为路径
        if (pack != null) {
            String packName = pack.getName();
            // 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
            if (packName.startsWith("java.") || packName.startsWith("javax.")) {
                throw new IllegalArgumentException("不要传送系统类！");
            }
            // 在类的名称中，去掉包名的部分，获得类的文件名
            clsName = clsName.substring(packName.length() + 1);
            // 判定包名是否是简单包名，如果是，则直接将包名转换为路径，
            if (packName.indexOf(".") < 0) {
                path = packName + "/";
            } else {// 否则按照包名的组成部分，将包名转换为路径
                int start = 0, end = 0;
                end = packName.indexOf(".");
                while (end != -1) {
                    path = path + packName.substring(start, end) + "/";
                    start = end + 1;
                    end = packName.indexOf(".", start);
                }
                path = path + packName.substring(start) + "/";
            }
        }
        // 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
        java.net.URL url = loader.getResource(path + clsName);
        // 从URL对象中获取路径信息
        String realPath = url.getPath();
        // 去掉路径信息中的协议名"file:"
        int pos = realPath.indexOf("file:");
        if (pos > -1) {
            realPath = realPath.substring(pos + 5);
        }
        // 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
        pos = realPath.indexOf(path + clsName);
        realPath = realPath.substring(0, pos - 1);
        // 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
        if (realPath.endsWith("!")) {
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        }
        /*------------------------------------------------------------
         ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径
		  中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
		  的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
		  中文及空格路径 
		-------------------------------------------------------------*/
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("realPath----->" + realPath);
        return realPath;
    }

    // private static File getFile(String path){
    // File file =
    // SendMail.class.getClassLoader().getResource("mailtemplate/test.ftl").getFile();
    // return file;
    // }
    //

    public static void main(String[] args) {
        // HtmlEmail hemail = new HtmlEmail();
        // try {
        // hemail.setHostName("smtp.exmail.qq.com");
        // hemail.setCharset("utf-8");
        // hemail.addTo("fly.1206@qq.com");
        // hemail.setFrom("zhoujunfeng@et-bank.com", "周俊峰");
        // hemail.setAuthentication("zhoujunfeng@et-bank.com", "31415926@aa");
        // hemail.setSubject("sendemail test!");
        // hemail.setMsg("<a href=\"http://www.google.cn\">谷歌</a><br/>");
        // hemail.send();
        // System.out.println("email send true!");
        // } catch (Exception e) {
        // e.printStackTrace();
        // System.out.println("email send error!");
        // }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subject", "测试标题");
        map.put("content", "测试 内容");
        String templatePath = "mailtemplate/test.ftl";
        sendFtlMail("892464537@qq.com", "sendemail test!", templatePath, map,new File("F:\\新建文件夹\\测试附件.txt"));
//        sendEmail1("测试", null,new File("F:\\新建文件夹\\测试附件.txt"));

        // System.out.println(getFileName("mailtemplate/test.ftl"));
    }

}