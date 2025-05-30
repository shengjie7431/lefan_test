package com.lefancrm.backend.util;

import com.aspose.words.BreakType;
import com.lefancrm.backend.dto.financial.FinancialReApply;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lefancrm.backend.dto.SurveyPayInfoAjaxFinancialData;
import com.lefancrm.backend.dto.financial.FinancialFileDto;
import com.lefancrm.backend.dto.financial.FinancialReProgresDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class PDFFinaancial {
    private static Font headfont ;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小

    private static BaseFont bfChinese;
    static{
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 15, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 18, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 15, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "E:\\mnt";
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
        file = new File(filePath + File.separator + "测试" + ".pdf");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        document.addTitle("Title@sample");
        document.addAuthor("Author@rensanning");
        document.addSubject("Subject@iText sample");
        document.addKeywords("Keywords@iText");
        document.addCreator("Creator@iText");
        document.setMargins(10, 20, 30, 40);
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document,new FileOutputStream(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        document.open();
        try {
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            Font font2 = new Font(bfChinese, 10, Font.NORMAL);
            Font font3 = new Font(bfChinese,10,Font.BOLD);
            Paragraph paragraph = new Paragraph("段落1",font1);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
            Image image = Image.getInstance("E:\\mnt\\北京航天总医院\\1.jpg");
            image.setAlignment(Image.MIDDLE);
            image.scaleToFit(1000, 300);
            document.add(image);
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        toPdf("E:\\mnt\\天津医科大学肿瘤医院排查（出险医院）\\排查材料\\","E:\\mnt\\多张图片1.pdf");
    }



    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "G:\\image\\";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "G:\\hebing.pdf";
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setPageSize(new Rectangle(PageSize.A4.getWidth() + 20,PageSize.A4.getHeight()));
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");
            document.setMargins(10, 20, 10, 10);
            // 写入PDF文档
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document,fos);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            document.open();
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            Paragraph paragraph = new Paragraph("方向文件夹1",font1);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph.setSpacingAfter(5f);// 下留白
            document.add(paragraph);
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png") || file1.getName().endsWith(".jpg") || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg") || file1.getName().endsWith(".tif")) {
                    if ("病案首页 (1).jpg".equals(file1.getName())){
                        paragraph = new Paragraph("方向文件夹2",font1);
                        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
                        paragraph.setSpacingAfter(5f);// 下留白
                        document.add(paragraph);
                    }
                    imagePath = imageFolderPath + file1.getName();
                    System.out.println(file1.getName());
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
                    image = Image.getInstance(imagePath);
                    image.scaleToFit(PageSize.A4.getWidth(), img.getHeight());
                    // 添加图片到文档
                    document.add(image);
                }
            }
            // 关闭文档
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static File generate(String filePath,SurveyPayInfoAjaxFinancialData data){
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
        file = new File(filePath + File.separator + data.getTitle() + ".pdf");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        document.addTitle("Title@sample");
        document.addAuthor("Author@rensanning");
        document.addSubject("Subject@iText sample");
        document.addKeywords("Keywords@iText");
        document.addCreator("Creator@iText");
        document.setMargins(10, 20, 30, 40);
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document,new FileOutputStream(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        document.open();
        try {
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            Font font2 = new Font(bfChinese, 10, Font.NORMAL);
            Font font3 = new Font(bfChinese,10,Font.BOLD);
            Paragraph paragraph = new Paragraph(data.getTitle(),font1);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph(data.getLittleTitle() + "                     申请日期：" + data.getApplyTime(),font2);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph.setIndentationLeft(30);// 左缩进
//            paragraph.setIndentationRight(6);// 右缩进
//            paragraph.setFirstLineIndent(6);// 首行缩进
//            paragraph.setSpacingBefore(5f);// 上留白
//            paragraph.setSpacingAfter(5f);// 下留白
//            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            PdfPTable table = new PdfPTable(3);
            table.setTotalWidth(510);
            int [] widths = {100,310,100};
            table.setWidths(widths);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.getDefaultCell().setPadding(5f);
//            table.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(new Phrase("编号",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getSno(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("提单人/借单人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getApplyUserName(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("承担部门",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getDepartments(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("事由",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getReason(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("金额",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getMoney().toString(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("付款时间",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getPayTime(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("收款人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getReceiveUserName(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("收款账户",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getReceiveUserAcc(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("备注",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getRemark(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            String fileNames = data.getFiles().stream().map(FinancialFileDto :: getFileName).collect(Collectors.joining(";"));
            cell = new PdfPCell(new Phrase("附件",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(fileNames,font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("审批流程",font2));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(data.getProgress().size());
            table.addCell(cell);
            for (FinancialReProgresDto progress : data.getProgress()) {
                String progressDesc = "(" + progress.getProgressUserName() + ")" + progress.getProgressName() + "(" + progress.getProgressDesc() + ")";
                cell = new PdfPCell(new Phrase(progressDesc,font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(progress.getProgressTime()),font2));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.disableBorderSide(Rectangle.LEFT);
                table.addCell(cell);
            }

            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }

//    public static File generates(String filePath, FinancialReApply data){
//        File file = new File(filePath);
//        if (!file.exists() && !file.isDirectory()){
    
//            file.mkdirs();
//        }
//        file = new File(filePath + File.separator + "每刻报销打印.pdf");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Document document = new Document();
//        document.setPageSize(PageSize.A4);
//        document.addTitle("Title@sample");
//        document.addAuthor("Author@rensanning");
//        document.addSubject("Subject@iText sample");
//        document.addKeywords("Keywords@iText");
//        document.addCreator("Creator@iText");
//        document.setMargins(10, 20, 30, 40);
//        PdfWriter writer = null;
//        try {
//            writer = PdfWriter.getInstance(document,new FileOutputStream(file));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
//        document.open();
//        try {
//            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
//            Font font2 = new Font(bfChinese, 10, Font.NORMAL);
//            Font font3 = new Font(bfChinese,10,Font.BOLD);
//            Paragraph paragraph = new Paragraph(data.getTitle(),font1);
//            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(paragraph);
//
//            paragraph = new Paragraph(data.getLittleTitle() + "                     申请日期：" + data.getApplyTime(),font2);
//            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
//            paragraph.setIndentationLeft(30);// 左缩进
////            paragraph.setIndentationRight(6);// 右缩进
////            paragraph.setFirstLineIndent(6);// 首行缩进
////            paragraph.setSpacingBefore(5f);// 上留白
////            paragraph.setSpacingAfter(5f);// 下留白
////            paragraph.setLeading(5f);// 行间距
//            document.add(paragraph);
//
//            PdfPTable table = new PdfPTable(3);
//            table.setTotalWidth(510);
//            int [] widths = {100,310,100};
//            table.setWidths(widths);
//            table.setLockedWidth(true);
//            table.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.getDefaultCell().setBorder(1);
//            table.getDefaultCell().setPadding(5f);
////            table.setWidthPercentage(100);
//
//            PdfPCell cell = new PdfPCell(new Phrase("编号",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getSno(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("提单人/借单人",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getApplyUserName(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("承担部门",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getDepartments(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("事由",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getReason(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("金额",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getMoney().toString(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("付款时间",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getPayTime(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("收款人",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getReceiveUserName(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("收款账户",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getReceiveUserAcc(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("备注",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getRemark(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            String fileNames = data.getFiles().stream().map(FinancialFileDto :: getFileName).collect(Collectors.joining(";"));
//            cell = new PdfPCell(new Phrase("附件",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(fileNames,font2));
//            cell.setColspan(2);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("审批流程",font2));
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//            cell.setRowspan(data.getProgress().size());
//            table.addCell(cell);
//            for (FinancialReProgresDto progress : data.getProgress()) {
//                String progressDesc = "(" + progress.getProgressUserName() + ")" + progress.getProgressName() + "(" + progress.getProgressDesc() + ")";
//                cell = new PdfPCell(new Phrase(progressDesc,font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(progress.getProgressTime()),font2));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.disableBorderSide(Rectangle.LEFT);
//                table.addCell(cell);
//            }
//
//            paragraph = new Paragraph();
//            paragraph.add(table);
//            paragraph.setIndentationLeft(6);// 左缩进
//            paragraph.setIndentationRight(6);// 右缩进
//            paragraph.setFirstLineIndent(6);// 首行缩进
//            paragraph.setSpacingBefore(5f);// 上留白
//            paragraph.setSpacingAfter(5f);// 下留白
//            paragraph.setLeading(5f);// 行间距
//            document.add(paragraph);
//
//            document.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return file;
//    }
}
