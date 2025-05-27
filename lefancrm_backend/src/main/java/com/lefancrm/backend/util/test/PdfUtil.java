package com.lefancrm.backend.util.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PdfUtil {
    private static String separator = File.separator;//文件夹路径分格符

    public static void main(String[] args) {
//        createApplyPdf();
//        convert();
//        addTextMark("D:\\test\\test.pdf", "D:\\test\\test2.pdf");
        Map<String,Object> dataMap =  new HashMap<String,Object>();
        dataMap.put("entrustOrgName","众安在线");
        dataMap.put("param1","张三");
        dataMap.put("param2","KLP010231238123");
        dataMap.put("param18","2020年12月22日");
//        String applyPdf = createApplyPdf(dataMap, "垫付结算单-test.xml", "垫付结算单-test.docx", false);
        String applyPdf = createApplyPdf(dataMap, "test-2.xml", "test-2.docx", true);
        System.out.println(applyPdf);

//        Document document = new Document();
//        document.loadFromFile(applyPdf);
//        document.saveToFile("E:\\generate-test\\pdf\\123.pdf", FileFormat.PDF);
//        document.close();
    }


    //=========================================生成申请表pdf===================================

    /**
     * freemark生成word----docx格式
     * @param dataMap 数据源
     * @param documentXmlName  document.xml模板的文件名
     * @param docxTempName   docx模板的文件名
     * @param generatePdf  是否生成pdf
     * @return 生成的文件路径
     */
    public static String createApplyPdf(Map<String,Object> dataMap,String documentXmlName,String docxTempName,Boolean generatePdf) {
        ZipOutputStream zipout = null;//word输出流
        File tempPath = null;//docx格式的word文件路径
        try {
            //freemark根据模板生成内容xml
            //================================获取 document.xml 输入流================================
            ByteArrayInputStream documentInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, documentXmlName, "E:\\generate-test\\model");
            ByteArrayInputStream documentXmlRelsInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, "document.xml.rels", "E:\\generate-test\\model");
            //================================获取 document.xml 输入流================================
            //获取主模板docx
            File docxFile = new File("E:\\generate-test\\model" + File.separator + docxTempName);

            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();

            //输出word文件路径和名称
            String fileName = "applyWord_" + System.currentTimeMillis() + ".docx";
//            String outPutWordPath = System.getProperty("java.io.tmpdir").replaceAll(separator + "$", "") + separator + fileName;
            String outPutWordPath = "E:\\generate-test\\word\\" + fileName;
            tempPath = new File("E:\\generate-test\\word\\" + fileName);
            //如果输出目标文件夹不存在，则创建
            if (!tempPath.getParentFile().exists()) {
                tempPath.mkdirs();
            }
            //docx文件输出流
            zipout = new ZipOutputStream(new FileOutputStream(tempPath));

            //循环遍历主模板docx文件,替换掉主内容区，也就是上面获取的document.xml的内容
            //------------------覆盖文档------------------
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                zipout.putNextEntry(new ZipEntry(next.getName()));
                if ("word/document.xml".equals(next.getName())) {
                    //写入填充数据后的主数据信息
                    if (documentInput != null) {
                        while ((len = documentInput.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        documentInput.close();
                    }
                } else if (next.getName().indexOf("document.xml.rels") > 0){
                    if (documentXmlRelsInput != null) {
                        while ((len = documentXmlRelsInput.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        documentXmlRelsInput.close();
                    }
                }else {//不是主数据区的都用主模板的
                    while ((len = is.read(buffer)) != -1) {
                        zipout.write(buffer, 0, len);
                    }
                    is.close();
                }
//                if (next.toString().indexOf("media") < 0) {
//
//                }
            }
            //------------------覆盖文档------------------
            zipout.close();//关闭
            if (generatePdf){
                    //----------------word转pdf--------------
                return convertDocx2Pdf(outPutWordPath);
            }
            return outPutWordPath;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(zipout!=null){
                    zipout.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "";
    }

    /**
     * word（docx）转pdf
     * @param wordPath  docx文件路径
     * @return  生成的带水印的pdf路径
     */
    public static String convertDocx2Pdf(String wordPath) {
        OutputStream os = null;
        InputStream is = null;
        try {
            is = new FileInputStream(new File(wordPath));
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(is);
            Mapper fontMapper = new IdentityPlusMapper();
            fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
            fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
            fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
            fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
            fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
            fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
            fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
            fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
            fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
            fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
            fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
            fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
            fontMapper.put("等线", PhysicalFonts.get("SimSun"));
            fontMapper.put("等线 Light", PhysicalFonts.get("SimSun"));
            fontMapper.put("华文琥珀", PhysicalFonts.get("STHupo"));
            fontMapper.put("华文隶书", PhysicalFonts.get("STLiti"));
            fontMapper.put("华文新魏", PhysicalFonts.get("STXinwei"));
            fontMapper.put("华文彩云", PhysicalFonts.get("STCaiyun"));
            fontMapper.put("方正姚体", PhysicalFonts.get("FZYaoti"));
            fontMapper.put("方正舒体", PhysicalFonts.get("FZShuTi"));
            fontMapper.put("华文细黑", PhysicalFonts.get("STXihei"));
            fontMapper.put("宋体扩展",PhysicalFonts.get("simsun-extB"));
            fontMapper.put("仿宋_GB2312",PhysicalFonts.get("FangSong_GB2312"));
            fontMapper.put("新細明體",PhysicalFonts.get("SimSun"));
            //解决宋体（正文）和宋体（标题）的乱码问题
            PhysicalFonts.put("PMingLiU", PhysicalFonts.get("SimSun"));
            PhysicalFonts.put("新細明體", PhysicalFonts.get("SimSun"));


            mlPackage.setFontMapper(fontMapper);

            //输出pdf文件路径和名称
            String fileName = "pdfNoMark_" + System.currentTimeMillis() + ".pdf";
//            String pdfNoMarkPath = System.getProperty("java.io.tmpdir").replaceAll(separator + "$", "") + separator + fileName;
            String pdfNoMarkPath = "E:\\generate-test\\pdf\\" + fileName;
            os = new java.io.FileOutputStream(pdfNoMarkPath);

            //docx4j  docx转pdf
            FOSettings foSettings = Docx4J.createFOSettings();
            foSettings.setWmlPackage(mlPackage);
            Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

            is.close();//关闭输入流
            os.close();//关闭输出流

            return pdfNoMarkPath;
            //添加水印
//            return addTextMark(pdfNoMarkPath);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(is != null){
                    is.close();
                }
                if(os != null){
                    os.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }finally {
            File file = new File(wordPath);
            if(file!=null&&file.isFile()&&file.exists()){
//                file.delete();
            }
        }
        return "";
    }

    /**
     * 添加水印图片
     * @param inPdfPath 无水印pdf路径
     * @return 生成的带水印的pdf路径
     */
    private static String addTextMark(String inPdfPath) {
        PdfStamper stamp = null;
        PdfReader reader = null;
        try {
            //输出pdf带水印文件路径和名称
            String fileName = "pdfMark_" + System.currentTimeMillis() + ".pdf";
            String outPdfMarkPath = System.getProperty("java.io.tmpdir").replaceAll(separator + "$", "") + separator + fileName;

            //添加水印
            reader = new PdfReader(inPdfPath, "PDF".getBytes());
            stamp = new PdfStamper(reader, new FileOutputStream(new File(outPdfMarkPath)));
            PdfContentByte under;
            int pageSize = reader.getNumberOfPages();// 原pdf文件的总页数
            //水印图片
            ClassPathResource resource = new ClassPathResource("template" + File.separator + "downLoad" + File.separator + "mark.png");
            File file = resource.getFile();
            Image image = Image.getInstance(file.getPath());
            for (int i = 1; i <= pageSize; i++) {
                under = stamp.getUnderContent(i);// 水印在之前文本下
                image.setAbsolutePosition(100, 210);//水印位置
                under.addImage(image);
            }
            stamp.close();// 关闭
            reader.close();//关闭

            return outPdfMarkPath;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (stamp != null) {
                    stamp.close();
                }
                if (reader != null) {
                    reader.close();//关闭
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            //删除生成的无水印pdf
            File file = new File(inPdfPath);
            if (file != null && file.exists() && file.isFile()) {
                file.delete();
            }
        }
        return "";
    }
}