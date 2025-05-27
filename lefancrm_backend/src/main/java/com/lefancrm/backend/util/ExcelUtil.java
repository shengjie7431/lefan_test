package com.lefancrm.backend.util;
import	java.text.SimpleDateFormat;

import com.lefancrm.backend.dto.SurveyCaseDirectionDto;
import com.lefancrm.backend.dto.help.HelpDirectionLine;
import com.lefancrm.backend.dto.help.TemplateHelpData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelUtil {

    public static void main(String[] args) {
        try {
            String abc = "SDFSAD\n" +
                    "\nssssssssssss";
            String bc = "SDFASD\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n";
            if ("\n".equals(abc.substring(abc.length() - 1))){
                System.out.println("相等");
            }
            System.out.println("sss: " + abc.substring(abc.length() - 1));

            String chars = "而苦恼了历史地方拉萨动阀<sdfsdfs>>>>累计亏损幅度暗室逢灯撒打\n发&klsjdfsa;sdsdf";
            System.out.println(chars.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;"));



//            FileOutputStream fileOutputStream = new FileOutputStream("F:\\generate\\report\\test1.xlsx");
//
//            FileInputStream tps = new FileInputStream(new File("F:\\templete\\结构化回复报告模版_v1.0.xlsx"));
//            XSSFWorkbook tempWorkBook = new XSSFWorkbook(tps);
//            // 新建一个Excel的工作空间
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            // 把模板复制到新建的Excel
//            workbook = tempWorkBook;
//            //填充数据
//            XSSFSheet sheet = workbook.getSheetAt(1);
//            XSSFRow row = sheet.getRow(0);
//            XSSFCell cell = row.getCell(1);
//            System.out.println(cell.getStringCellValue());
//            workbook.write(fileOutputStream);
//
//            List<Map<String,Object>> list = new ArrayList<>();
//            Map<String,Object> map =  new HashMap<String,Object>();
//            map.put("team","湖人");
//            map.put("age","34");
//            map.put("firstName","本人");
//            map.put("lastName","勒布朗萨菲罗斯的&发撒<>撒打发士大夫拉萨的飞机拉萨的飞机撒打发撒旦飞洒的方式啦的发撒劳动法sdf" +
//                    "”“ 了多个接口的风格的风格岁的法士大夫撒旦发考试多发撒打发华为认为然后就大概萨拉丁给萨拉地发撒旦欧委会人附近的格兰芬多公文柔哇人口流动那个来打开两个收到了发给岁的法国的数量极其欧文人" +
//                    "的风格十点零分来颠覆国家领导风格山东进入特惠如''\"\"同了解发射东风公司好了发给客户立方结构合理" +
//                    "国士大夫感到反感的是法国了撒打发就是拉到房间里撒旦发就是拉到分居萨拉丁附件我饿将人类世界的放了多久glad给大锅饭大概" +
//                    "劳动法");
//            list.add(map);
//            map = new HashMap<String,Object>();
//            map.put("team","湖人");
//            map.put("age","21");
//            map.put("firstName","戴维斯");
//            map.put("lastName","安东尼");
//            list.add(map);
//            map = new HashMap<String,Object>();
//            map.put("team","凯尔特人");
//            map.put("age","20");
//            map.put("firstName","杰伦");
//            map.put("lastName","布朗");
//            list.add(map);
//            Configuration configuration = new Configuration();
//            //设置编码
//            configuration.setDefaultEncoding("UTF-8");
//
//            //输出文件
//            File outFile = new File("F:\\generate\\test.xls");
//            configuration.setDirectoryForTemplateLoading(new File("F:\\templete\\excel"));
//            //如果输出目标文件夹不存在，则创建
//            if (!outFile.getParentFile().exists()){
//                outFile.getParentFile().mkdirs();
//            }
//            //将模板和数据模型合并生成文件
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
//            //获取模板
//            Template template = configuration.getTemplate("helpv1.ftl");
//            //生成文件
////            Map<String,Object> dataMap =  new HashMap<String,Object>();
////            dataMap.put("reportRemark","啥的发士大夫数量大幅啥的发撒大家发撒劳动法解耦为让我二日文进入路径代李法拉收到今安徽广东拉萨扩大方式拉动房价欧文人家饿哦我瑞金二路我加热炉威认 " +
////                    "登录房间里撒的法律撒旦发拉萨打发撒旦夫士大夫萨的法律是的撒打发撒打发暗室逢灯");
////            dataMap.put("data",list);
////            dataMap.put("data2",list);
////            dataMap.put("data3",list);
//
//            map =  new HashMap<String,Object>();
//            map.put("surveyCompletion","测试是靠近对方啦士大夫撒地方拉萨的房间拉萨的方式啦的发生了反对顺利打开的酷酷酷酷酷酷");
//            map.put("cellValue1","李贤丰");
//            map.put("cellValue2","乐凡");
//            map.put("cellValue3","2019-08-12");
//            map.put("cellValue4","2019-09-18");
//            map.put("cellValue5","否");
//            map.put("cellValue6","申领人编造虚假的事故原因");
//            map.put("cellValue7","经走访住院主治医生了解到：成员 2019 年 7 月 25 日办理入院，入院完善相关检查，于 2019 年 7 月 26 日行经外周静脉置入中心静脉导管术进行化疗，成员第二周期化疗时间拟定于 2019 年 8 月 19 日，于 2019 年 8 月 14 日出院。");
//            map.put("cellValue8","是");
//            map.put("cellValue9","暴力排查实务不可操作");
//            map.put("cellValue10","排查点太多，实际不可操作");
//            map.put("cellValue11","经走访该院门诊了解到：成员 2019 年 7 月 17 日发现左侧颈部一 3cm*4cm 大小质硬包块，就诊于我院颌面外科门诊，行鼻腔镜见鼻咽部新生物，取活检病理示（鼻咽部）非角化型分化癌。进一步完善鼻咽颈部 MRI 提示鼻咽癌，侵犯左侧翼内肌，双侧颈部淋巴结肿大显现，胸部 CT、骨扫描检查未发现远处转移病灶，诊断“鼻咽癌双颈淋巴结转移 T2N2M0 III 期”，今为进一步系统治疗门诊收入我院。\n" +
//                    "经走访病案室了解到：成员共有两次住院记录，分别为 2019 年 7 月 25 日至 2019年 8 月 14 日，2019 年 8 月 19 日至 2019 年 8 月 29 日，因成员上午办理的出院手续，出院未满 15 天，故第二份住院病历未获取。");
//            map.put("data1","");
//            map.put("data2","");
//            map.put("data3","");
//            map.put("data4","");
//            map.put("data5","");
//            map.put("data6","");
//            map.put("data7","");
//            map.put("data8","");
//            map.put("data9","");
//            map.put("data10","");
//            map.put("data11","");
//            map.put("data12","");
//            map.put("data13","");
//            map.put("data14","");
//            map.put("data15","");
//            map.put("data16","");
//            map.put("data17","");
//            map.put("data18","");
//            map.put("data19","");
//            map.put("data20","");
//            map.put("data21","");
//            template.process(map, out);
//
//            //关闭流
//            out.flush();
//            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 生成excel
     * @param data   数据
     * @param templateName   模板文件名称（huzhu.xlsx）
     * @param generateFilePath  生成文件的路径（F:\generate\report\cwt1051577170794002\report）
     * @param generateFileName  生成文件的名称（new99案(测试上海乐凡).xlsx）
     * @param sourcePath        模板文件的源路径  （F:\templete）
     * @return
     */
    public static File generateReportPoi(TemplateHelpData data, String templateName, String generateFilePath, String generateFileName, String sourcePath){
        try {
            FileInputStream template = new FileInputStream(new File(sourcePath + File.separator + templateName));
            File generateFile = new File(generateFilePath + File.separator + generateFileName + ".xlsx");
            if (!generateFile.getParentFile().exists()){
                generateFile.getParentFile().mkdirs();
            }
            FileOutputStream dataFile = new FileOutputStream(generateFile);
            XSSFWorkbook templateWorkBook = new XSSFWorkbook(template);
            XSSFWorkbook workbook = new XSSFWorkbook();
            workbook = templateWorkBook;

            //sheet0  sheet页 从0开始
            //赋值数据  sheet1
            XSSFSheet sheet1 = workbook.getSheetAt(1);
            String cellValue1 = data.getHelpData2().getCellValue1();
            String [] names = cellValue1.split(",");
            for (int i = 0; i < names.length; i++) {
                setCellValue(sheet1,1,i + 3,names[i]);
            }
//            setCellValue(sheet1,1,3,data.getHelpData2().getCellValue1());
            setCellValue(sheet1,2,3,data.getHelpData2().getCellValue2());

            Row rowD = sheet1.getRow(3 - 1);
            Cell cellD = rowD.getCell(3 - 1);
            cellD.setCellValue(new SimpleDateFormat("yyyy-MM-dd").parse(data.getHelpData2().getCellValue3()));

            if (!StringUtils.isEmpty(data.getHelpData2().getCellValue4())){
                Row rowDD = sheet1.getRow(4 - 1);
                Cell cellDD = rowDD.getCell(3 - 1);
                cellDD.setCellValue(new SimpleDateFormat("yyyy-MM-dd").parse(data.getHelpData2().getCellValue4()));
            }

//            setCellValue(sheet1,3,3,data.getHelpData2().getCellValue3());
//            setCellValue(sheet1,4,3,data.getHelpData2().getCellValue4());

            setCellValue(sheet1,5,3,data.getHelpData2().getCellValue5());
            setCellValue(sheet1,6,3,data.getHelpData2().getCellValue6());
            setCellValue(sheet1,7,3,data.getHelpData2().getCellValue7());
            setCellValue(sheet1,8,3,data.getHelpData2().getCellValue8());
            setCellValue(sheet1,9,3,data.getHelpData2().getCellValue9());
            setCellValue(sheet1,10,3,data.getHelpData2().getCellValue10());
            setCellValue(sheet1,11,3,data.getHelpData2().getCellValue11());

            //后面21个sheet页   从 k + 1 个 sheet 页开始循环赋值
            for (int k = 1; k < 22; k++) {
                int index = 0;
                List<HelpDirectionLine> dataSheet = data.getHelpDataLists().getSheetValue(k);
                XSSFSheet sheet =  workbook.getSheetAt(k + 1);
                int line = 2;
                for (HelpDirectionLine helpDirectionLine : dataSheet) {
                    int lastCell = sheet.getRow(0).getPhysicalNumberOfCells();//获取每个sheet页的列数
                    XSSFRow row = sheet.createRow(line ++);//都从第二行开始增加
                    if (k  == 5){//无索引列
                        XSSFCell cell = null;
                        for (int i = 1; i < lastCell + 1; i++) {
                            cell = row.createCell(i - 1,Cell.CELL_TYPE_STRING);
                            cell.setCellValue(helpDirectionLine.getColValue(i));//没有索引列 从第一列开始取值
                        }
                    }else{
                        for (int i = 0; i < lastCell; i++) {
                            XSSFCell cell = null;
                            if (i == 0){//索引列
                                cell = row.createCell(0,Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(++index);
                            }else{
                                cell = row.createCell(i,Cell.CELL_TYPE_STRING);
                                cell.setCellValue(helpDirectionLine.getColValue(i));
                            }
                        }
                    }
                }
            }
            workbook.write(dataFile);
            return generateFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给单元格赋值
     * @param sheet
     * @param i 行 从1开始
     * @param j 列 从1开始
     * @param value
     */
    private static void setCellValue(Sheet sheet,int i,int j,String value){
        Row row = sheet.getRow(i - 1);
        Cell cell = row.getCell(j - 1);
        cell.setCellValue(value);
    }


    /**
     * 根据模板 生成Excel 报告
     * @param data
     * @param templateName  模板名称
     * @param generateFilePath 生成文件路径
     * @param generateFileName 生成文件名称
     * @param sourcePath  原始模板路径
     * @return
     */
    public static File  generateReport(TemplateHelpData data, String templateName, String generateFilePath, String generateFileName, String sourcePath){
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyCompletion",data.getHelpData1().getSurveyCompletion());
        map.put("cellValue1",data.getHelpData2().getCellValue1());
        map.put("cellValue2",data.getHelpData2().getCellValue2());
        map.put("cellValue3",data.getHelpData2().getCellValue3());
        map.put("cellValue4",data.getHelpData2().getCellValue4());
        map.put("cellValue5",data.getHelpData2().getCellValue5());
        map.put("cellValue6",data.getHelpData2().getCellValue6());
        map.put("cellValue7",data.getHelpData2().getCellValue7());
        map.put("cellValue8",data.getHelpData2().getCellValue8());
        map.put("cellValue9",data.getHelpData2().getCellValue9());
        map.put("cellValue10",data.getHelpData2().getCellValue10());
        map.put("cellValue11",data.getHelpData2().getCellValue11());
        map.put("data1",data.getHelpDataLists().getDirections1());
        map.put("data2",data.getHelpDataLists().getDirections2());
        map.put("data3",data.getHelpDataLists().getDirections3());
        map.put("data4",data.getHelpDataLists().getDirections4());
        map.put("data5",data.getHelpDataLists().getDirections5());
        map.put("data6",data.getHelpDataLists().getDirections6());
        map.put("data7",data.getHelpDataLists().getDirections7());
        map.put("data8",data.getHelpDataLists().getDirections8());
        map.put("data9",data.getHelpDataLists().getDirections9());
        map.put("data10",data.getHelpDataLists().getDirections10());
        map.put("data11",data.getHelpDataLists().getDirections11());
        map.put("data12",data.getHelpDataLists().getDirections12());
        map.put("data13",data.getHelpDataLists().getDirections13());
        map.put("data14",data.getHelpDataLists().getDirections14());
        map.put("data15",data.getHelpDataLists().getDirections15());
        map.put("data16",data.getHelpDataLists().getDirections16());
        map.put("data17",data.getHelpDataLists().getDirections17());
        map.put("data18",data.getHelpDataLists().getDirections18());
        map.put("data19",data.getHelpDataLists().getDirections19());
        map.put("data20",data.getHelpDataLists().getDirections20());
        map.put("data21",data.getHelpDataLists().getDirections21());
        //所有key 的value 不能为空 此处做转换
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            if (value == null) {
                map.put(key,"");
            }else{
                String replacement = "&#10;";//换行
                if (value instanceof String){
                    String tempValue = value.toString();
                    tempValue = tempValue.replaceAll("<","&lt;");
                    tempValue = tempValue.replaceAll(">","&gt;");
                    tempValue = tempValue.replaceAll("\n",replacement);
                    map.put(key,tempValue);
                } else if(value instanceof List){
                    List<HelpDirectionLine> lines = (List<HelpDirectionLine>)value;
                    for (HelpDirectionLine line : lines) {
                        Field[] fields = HelpDirectionLine.class.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            try {
                                Object object = field.get(line);
                                if (object == null){
                                    field.set(line,"");
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }else {
                    map.put(key,value);
                }
            }
        }
        File file = createExcel(map,templateName,generateFilePath,generateFileName,sourcePath);
        return file;
    }

    private static File createExcel(Map dataMap,String templateName,String filePath,String fileName,String sourcePath){
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
            File outFile = new File(filePath+File.separator + fileName + ".xlsx");

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
}
