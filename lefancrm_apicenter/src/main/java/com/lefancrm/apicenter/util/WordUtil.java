package com.lefancrm.apicenter.util;

import com.lefancrm.apicenter.dto.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lixianfeng on 2019/7/2.
 */
public class WordUtil {
    /**
     * @Desc：生成word文件
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param filePath 文件生成的目标路径，例如：D:/wordFile/
     * @param fileName 生成的文件名称，例如：test.doc
     * @param sourcePath 原始模板路径
     */
    @SuppressWarnings("unchecked")
    public static File createWord(Map dataMap,String templateName,String filePath,String fileName,String sourcePath){
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一放至 com.lun.template 包下面
//            configuration.setClassForTemplateLoading(WordUtil.class,sourcePath);
            configuration.setDirectoryForTemplateLoading(new File(sourcePath));

            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath+File.separator + fileName + ".doc");

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("name","lxf");
        map.put("sex",null);
        map.put("age",null);
        map.put("address","");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if (map.get(key) == null) {
                map.put(key,"111");
            }
        }
        iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key:[ "+key+" ]" + map.get(key).toString());
        }



        String str = "'";
//        Pattern p = Pattern.compile("^.*[\'\"<>#&;$].*$");
        Pattern p = Pattern.compile("^.*[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？].*$");
        Matcher m = p.matcher(str);
        System.out.println(m.matches());
        if (m.matches()){
            System.out.println("不匹配");
        }else{
            System.out.println("匹配");
        }

        map =  new HashMap<String,Object>();
        map.put("entrustOrgName","上海乐凡金融信息服务有限公司");
        map.put("surveyUserName","张子凡");
        map.put("surveyCno","CWT7889846461");
        map.put("entrustDate","2019年7月2日");
        map.put("safeTypeStr","调查出险");
        map.put("safeDate","2019年7月2日");
        map.put("surveyUserDate","2019年7月2日");
        map.put("surveyItem", "调查事项");
        map.put("surveyUserAddress", "上海市浦东新区三林");
        map.put("sexStr", "男");
        map.put("idNumber", "46431316792316978643");

        map.put("directions",TestDirection.getTestDirections());
        map.put("username","李贤丰");
        map.put("test","手写签名测试");
        String url1 = getImageStr("C:\\Users\\liuting\\Desktop\\image\\3.png");
        String url2 = getImageStr("C:\\Users\\liuting\\Desktop\\image\\2.jpg");
        map.put("url1",url1);
        map.put("url2",url2);

//        createWord(map, "20200107.ftl", "F:\\templete\\word", "手写签名测试","F:\\templete\\word");
//        File sourceFile = new File("F:\\generate\\11.docx");
//        File copyFile= new File("F:\\generate\\22.doc");
//        try {
//            if (copyFile.exists()) {
//                copyFile.delete();
//            }
//            copyFileUsingFileStreams(sourceFile,copyFile);
////            Files.copy(sourceFile.toPath(),copyFile.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        map.put("entrustOrgName","众安在线");
        map.put("param1","众安在线");
        map.put("param2","众安在线");
        map.put("param3","众安在线");
        map.put("param4","众安在线");
        map.put("param5","众安在线");
        map.put("param6","众安在线");
        map.put("param7","众安在线");
        map.put("param8","众安在线");
        map.put("param9","众安在线");
        map.put("param10","众安在线");
        map.put("param11","众安在线");
        map.put("param12","众安在线");
        map.put("param13","众安在线");
        map.put("param14","众安在线");
        map.put("param15","众安在线");
        map.put("param16","众安在线");
        map.put("param17","众安在线");
        createWord(map,"垫付结算单-model.ftl","F:\\templete\\word","结算单测试","F:\\templete\\word");
    }


    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }


//    private static final String sourcePath = "F:\\templete";

    /**
     *
     * @param data      数据
     * @param type  1乐凡模板   2正言模板    3中德安联模板  4中宏模板
     * @param templateName      模板名称
     * @param generateFilePath  生成的文件路径
     * @param generateFileName  生成的文件名称
     * @return
     */
    public static String  generateReport(TemplateData data, int type, String templateName, String generateFilePath, String generateFileName, String sourcePath){

        String surveyEncoder64 = "",managerEncoder64 = "";
        if (type == 1){//乐凡模板 根据调查员签名照片 生成。
            String  surveyUserImg = data.getSurveyUserImg() == null ? "" : data.getSurveyUserImg(),managerUserImg = data.getLfUserImg() == null ? "" : data.getLfUserImg();
            if (!"".equals(surveyUserImg) && !"".equals(managerUserImg)){
                templateName = templateName.substring(0,templateName.lastIndexOf(".")) + "-spot.ftl";//签名模板，与非签名模板后带有-spot
//                surveyUserImg = "https://ddrapi.shlefan.com/sftp/files/test//ddr/cno/cwt1051578360084097/material/2.jpg";
//                managerUserImg = "https://ddrapi.shlefan.com/sftp/files/test//ddr/cno/cwt1051578360084097/material/2.jpg";
                surveyUserImg = surveyUserImg.replace("https://ddrapi.shlefan.com/sftp/","/mnt/sftp/");
                managerUserImg = managerUserImg.replace("https://ddrapi.shlefan.com/sftp/","/mnt/sftp/");
                if ("F:\\templete".equals(sourcePath)){//本地环境
                    surveyUserImg = "F:\\generate\\100x100\\2.jpg";
                    managerUserImg = "F:\\generate\\100x100\\3.jpg";
                }
                File surveyFile = new File(surveyUserImg);
                File managerFile = new File(managerUserImg);
                if (surveyFile.exists()){
                    surveyEncoder64 = getImageStr(surveyFile.getPath());
                }
                if (managerFile.exists()){
                    managerEncoder64 = getImageStr(managerFile.getPath());
                }
            }
        }
        Map<String,Object> map =  new HashMap<String,Object>();
        if (type == 1 || type == 6 || type == 7){
            TemplateDataLF template = data.getTemplateDataLF();
            map.put("entrustOrgName",template.getEntrustOrgName());
            map.put("entrustUserName",template.getEntrustUserName());
            map.put("surveyUserName","".equals(surveyEncoder64) ? template.getSurveyUserName() : surveyEncoder64);
            map.put("trusteeOrgName",template.getTrusteeOrgName());
            map.put("surveyCno",template.getSurveyCno());
            map.put("entrustDate",template.getEntrustDate());
            map.put("safeTypeStr",template.getSafeTypeStr());
            map.put("safeDate",template.getSafeDate());
            map.put("surveyUserDate",template.getSurveyUserDate());
            map.put("surveyUserAddress",template.getSurveyUserAddress());
            map.put("surveyItem",template.getSurveyItem());
            map.put("accidentInfo",template.getAccidentInfo());
            map.put("sexStr",template.getSexStr());
            map.put("nation",template.getNation());
            map.put("idNumber",template.getIdNumber());
            map.put("numberTypeStr",template.getNumberTypeStr());
            map.put("directions",template.getDirections());
            map.put("directionResult",template.getDirectionResult());
            map.put("fileMidInfo",template.getFileMidInfo());
            map.put("surveyManagerName","".equals(managerEncoder64) ? template.getSurveyManagerName() : managerEncoder64);
            map.put("userSignDate", template.getUserSignDate());
            map.put("directionNames", template.getDirectionNames());
        }else if (type == 2){
            TemplateDataZY template = data.getTemplateDataZY();
            map.put("entrustOrgName",template.getEntrustOrgName());
            map.put("entrustUserName",template.getEntrustUserName());
            map.put("trusteeOrgName",template.getTrusteeOrgName());
            map.put("surveyCno",template.getSurveyCno());
            map.put("entrustDate",template.getEntrustDate());
            map.put("safeTypeStr",template.getSafeTypeStr());
            map.put("safeDate",template.getSafeDate());
            map.put("safeUserDate",template.getSafeUserDate());
            map.put("safeUserAddress",template.getSafeUserAddress());
            map.put("surveyItem",template.getSurveyItem());
            map.put("accidentInfo",template.getAccidentInfo());
            map.put("sexStr",template.getSexStr());
            map.put("nation",template.getNation());
            map.put("idNumber",template.getIdNumber());
            map.put("numberTypeStr",template.getNumberTypeStr());
            map.put("domicileAddress",template.getDomicileAddress());
            map.put("directions",template.getDirections());
            map.put("directionResult",template.getDirectionResult());
            map.put("directionBasis", template.getDirectionBasis());
            map.put("names", "".equals(surveyEncoder64) ? template.getNames() : surveyEncoder64);
            map.put("qfUserName", template.getQfUserName());
            map.put("datestr", template.getDatestr());
            map.put("directionNames", template.getDirectionNames());
        }else if (type == 3){
            TemplateDataZD template = data.getTemplateDataZD();
            map.put("trusteeOrgName",template.getTrusteeOrgName());
            map.put("entrustDate",template.getEntrustDate());
            map.put("entrustUserName",template.getEntrustUserName());
            map.put("endDate",template.getEndDate());
            map.put("surveyUserName","".equals(surveyEncoder64) ? template.getSurveyUserName() : surveyEncoder64);
            map.put("idNumber",template.getIdNumber());
            map.put("numberTypeStr",template.getNumberTypeStr());
            map.put("claimsNo",template.getClaimsNo());
            map.put("safeDate",template.getSafeDate());
            map.put("surveyUserDate",template.getSurveyUserDate());
            map.put("surveyItem",template.getSurveyItem());
            map.put("directions",template.getDirections());
            map.put("totalMoney",template.getTotalMoney());
            map.put("directionInfo",template.getDirectionInfo());
            map.put("directionResult",template.getDirectionResult());
            map.put("fileMidInfo",template.getFileMidInfo());
            map.put("userSignDate",template.getUserSignDate());
            map.put("surveyManagerName",template.getSurveyManagerName());
            map.put("managerSignDate", template.getManagerSignDate());
        }else if (type == 4){
            TemplateDataZH template = data.getTemplateDataZH();
            map.put("claimsNo",template.getClaimsNo());
            map.put("entrustOrgName",template.getEntrustOrgName());
            map.put("trusteeOrgName",template.getTrusteeOrgName());
            map.put("entrustUserName",template.getEntrustUserName());
            map.put("entrustDate",template.getEntrustDate());
            map.put("endDate",template.getEndDate());
            map.put("policyNo",template.getPolicyNo());
            map.put("safeDate",template.getSafeDate());
            map.put("surveyPerson",template.getSurveyPerson());
            map.put("surveyReason",template.getSurveyReason());
            map.put("directions",template.getDirections());
            map.put("surveyDemand",template.getSurveyDemand());
            map.put("directionName",template.getDirectionName());
            map.put("directionInfo",template.getDirectionInfo());
            map.put("directionResult",template.getDirectionResult());
            map.put("isUseLetter",template.getIsUseLetter());
            map.put("fileMidSize",template.getFileMidSize());
            map.put("directionNameSize",template.getDirectionNameSize());
            map.put("surveyUserName","".equals(surveyEncoder64) ? template.getSurveyUserName() : surveyEncoder64);
            map.put("userSignDate",template.getUserSignDate());

            map.put("departmentName",template.getAnnexCom());
            map.put("annexCom",template.getAnnexCom());
            map.put("annexCity",template.getAnnexCity());
            map.put("annexType",template.getAnnexType());
            map.put("annexPrice",template.getAnnexPrice());
            map.put("annexRiskPrice",template.getAnnexRiskPrice());
            map.put("annexEndOne",template.getAnnexEndOne());
            map.put("annexEndTwo",template.getAnnexEndTwo());
            map.put("annexEndThree",template.getAnnexEndThree());
            map.put("annexEndFour",template.getAnnexEndFour());
            map.put("annexEndFive",template.getAnnexEndFive());
            map.put("annexEndSix",template.getAnnexEndSix());
            map.put("annexIsEndSixRefund",template.getAnnexIsEndSixRefund());
            map.put("annexEndSeven",template.getAnnexEndSeven());
            map.put("annexIsEndSevenRefund",template.getAnnexIsEndSevenRefund());
            map.put("annexIsFinish",template.getAnnexIsFinish());
            map.put("annexTuneOne",template.getAnnexTuneOne());
            map.put("annexTuneTwo",template.getAnnexTuneTwo());
            map.put("annexTuneThree",template.getAnnexTuneThree());
            map.put("annexTuneFour",template.getAnnexTuneFour());
            map.put("annexAddress",template.getAnnexAddress());
            map.put("annexOutpatient",template.getAnnexOutpatient());
            map.put("annexDepart",template.getAnnexDepart());
            map.put("annexDoctor",template.getAnnexDoctor());
            map.put("annexUnit",template.getAnnexUnit());
            map.put("annexLiveAddress",template.getAnnexAddress());
            map.put("annexMedical",template.getAnnexMedical());
            map.put("annexPolice",template.getAnnexPolice());
            map.put("annexSameTrade",template.getAnnexSameTrade());
            map.put("annexRemark",template.getAnnexRemark());
        }

        //所有key 的value 不能为空 此处做转换
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            if (("surveyUserName".equals(key) || "surveyManagerName".equals(key)) && type == 1){//如果是乐凡模板的 调查员签名 或者 终审人员签名 则 不需要将转换出来的64位码转义。否则生成签名后 文件无法打开
                continue;
            }
            if (value == null) {
                map.put(key,"");
            }else{
                String replacement = "<w:br/>";
                //2019年9月18日 16点05分BUG 将<>转译。  否则生成的WORD打不开; 同时内容换行。
                if (value instanceof String){
                    value = LFStringUtil.replacePrint(((String) value));//去掉最后的换行
                    String tempValue = value.toString().replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n",replacement);
//                    tempValue = tempValue.replaceAll("<","&lt;");
//                    tempValue = tempValue.replaceAll(">","&gt;");
//                    tempValue = tempValue.replaceAll("\n",replacement);
                    map.put(key,tempValue);
                } else if(value instanceof List){
                    try {
                        List<SurveyCaseDirectionDto> directionDtos = (List<SurveyCaseDirectionDto>) value;
                        for (SurveyCaseDirectionDto directionDto : directionDtos) {
                            directionDto.setDirectionName(LFStringUtil.replacePrint(directionDto.getDirectionName()));
                            String tempName = directionDto.getDirectionName().replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n",replacement);
                            directionDto.setDirectionName(tempName);
                            directionDto.setDirectionText(LFStringUtil.replacePrint(directionDto.getDirectionText()));
                            String tempText = directionDto.getDirectionText().replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n",replacement);
                            directionDto.setDirectionText(tempText);
                            directionDto.setDirectionInfo(LFStringUtil.replacePrint(directionDto.getDirectionInfo()));
                            String tempInfo = directionDto.getDirectionInfo().replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n",replacement);
                            directionDto.setDirectionInfo(tempInfo);
                        }
                        map.put(key,directionDtos);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    map.put(key,value);
                }
            }
        }
        File file = createWord(map,templateName,generateFilePath,generateFileName,sourcePath);
        if (file == null){
            return null;
        }
        return file.getPath();
    }


    /**
     * 获得图片的Base64编码
     * @param imgFile
     * @return
     * @Author
     */
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            //注：FileInputStream.available()方法可以从输入流中阻断由下一个方法调用这个输入流中读取的剩余字节数
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
