package com.lefancrm.backend.util;

import com.aspose.words.BreakType;
import com.lefancrm.backend.dto.feere.SurveyInvestigatorReInfoDto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lefancrm.backend.dto.SurveyPayInfoAjaxFinancialData;
import com.lefancrm.backend.dto.financial.FinancialFileDto;
import com.lefancrm.backend.dto.financial.FinancialReProgresDto;
import com.lefancrm.backend.dto.financial.FinancialReApply;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.stream.Collectors;
import com.lefancrm.backend.dto.financial.*;

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


    public static File generates(String filePath, FinancialReApply data){
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
        file = new File(filePath + File.separator + "日常费用报销"+data.getReNo()+".pdf");
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
            Paragraph paragraph = new Paragraph("日常费用报销"+data.getReNo(),font1);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph = new Paragraph(data.getCompanyTitle()+"                         "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(data.getApplyTime()),font2);
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

            PdfPCell cell = new PdfPCell(new Phrase("姓名",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getApplyUserName(),font2));
            cell.setColspan(2);
            table.addCell(cell);

//            cell = new PdfPCell(new Phrase("社保缴纳公司",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getSocialSecurityCompany(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);

            cell = new PdfPCell(new Phrase("组织架构所属部门",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getOrgan(),font2));
            cell.setColspan(2);
            table.addCell(cell);

//            cell = new PdfPCell(new Phrase("成本归属公司",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getBudgetCompanyName(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);




//            cell = new PdfPCell(new Phrase("公司抬头",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(data.getCompanyTitle(),font2));
//            cell.setColspan(2);
//            table.addCell(cell);

            cell = new PdfPCell(new Phrase("收款账户",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getPayeeNo(),font2));
            cell.setColspan(2);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase("收款人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getPayeeName(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("银行",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getBankName().toString(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("支行",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getBranchBank(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("事由",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getReReasons(),font2));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("备注",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(data.getApplyDesc(),font2));
            cell.setColspan(2);
            table.addCell(cell);

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

//            String fileNames = data.getFiles().stream().map(FinancialFileDto :: getFileName).collect(Collectors.joining(";"));
//            cell = new PdfPCell(new Phrase("附件",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase(fileNames,font2));
//            cell.setColspan(2);
//            table.addCell(cell);
         if(data.getFinancialCostDetails().size()!=0){
             cell = new PdfPCell(new Phrase("费用明细",font2));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
             cell.setRowspan(data.getFinancialCostDetails().size());
             table.addCell(cell);


//            cell = new PdfPCell(new Phrase("费用类型",font2));
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("金额",font2));
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.disableBorderSide(Rectangle.LEFT);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("备注",font2));
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.disableBorderSide(Rectangle.LEFT);
//            table.addCell(cell);

             for (FinancialCostDetails progress : data.getFinancialCostDetails()) {
                 if(progress.getCostDesc()==null){
                     progress.setCostDesc("");
                 }
                 String progressDesc = progress.getCostTypeName();
                 cell = new PdfPCell(new Phrase(progressDesc,font2));
                 table.addCell(cell);
                 String costMoney = String.valueOf(progress.getCostMoney());
                 cell = new PdfPCell(new Phrase(costMoney,font2));
                 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.disableBorderSide(Rectangle.LEFT);
                 table.addCell(cell);
//                String costDesc = progress.getCostDesc();
//                cell = new PdfPCell(new Phrase(costDesc,font2));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.disableBorderSide(Rectangle.LEFT);
//                table.addCell(cell);

             }
         }

           if(data.getFinancialCostBearList().size()!=0){
               cell = new PdfPCell(new Phrase("承担费用公司部门",font2));
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
               cell.setRowspan(data.getFinancialCostBearList().size());
               table.addCell(cell);

               for (FinancialCostBear progress : data.getFinancialCostBearList()) {
                   // 第一列：部门名称
                   if(progress.getCostDesc()==null){
                       progress.setCostDesc("");
                   }
                   String progressDesc = progress.getUnderdepartment()+"(备注:"+progress.getCostDesc()+")";
                   cell = new PdfPCell(new Phrase(progressDesc, font3));
                   table.addCell(cell);


                   // 第二列：金额（右对齐）
                   String costMoney = String.valueOf(progress.getShareCost());
                   cell = new PdfPCell(new Phrase(costMoney, font3));
                   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   cell.disableBorderSide(Rectangle.LEFT);
                   table.addCell(cell);
               }
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

            double totalAmount = data.getFinancialCostDetails().stream()
                    .mapToDouble(FinancialCostDetails::getCostMoney)
                    .sum();


            // 将数字金额转换为中文大写
            String amountInChinese = convertToChineseAmount(totalAmount);
            // 创建一个新的段落用于显示大写金额与总金额
            Paragraph chineseAmountParagraph = new Paragraph("总金额:"+totalAmount+"             "+"大写金额: " + amountInChinese, font2);
            chineseAmountParagraph.setAlignment(Paragraph.ALIGN_RIGHT); // 右对齐
            document.add(chineseAmountParagraph);
             // 添加一些间距
            document.add(new Paragraph(" "));
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }
    private static String convertToChineseAmount(double amount) {
        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unit = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};
        String[] decimalUnit = {"角", "分"};

        String amountStr = String.format("%.2f", amount);
        String[] parts = amountStr.split("\\.");
        String integerPart = parts[0];
        String decimalPart = parts.length > 1 ? parts[1] : "00";

        StringBuilder result = new StringBuilder();

        // 处理整数部分
        if (!integerPart.equals("0")) {
            for (int i = 0; i < integerPart.length(); i++) {
                int num = integerPart.charAt(i) - '0';
                int pos = integerPart.length() - i - 1;
                result.append(digit[num]).append(unit[pos]);
            }
            result.append("元");
        }

        // 处理小数部分
        if (decimalPart.equals("00")) {
            result.append("整");
        } else {
            for (int i = 0; i < decimalPart.length(); i++) {
                int num = decimalPart.charAt(i) - '0';
                if (num != 0) {
                    result.append(digit[num]).append(decimalUnit[i]);
                }
            }
        }

        return result.toString();
    }

    public static File generatess(String filePath, SurveyInvestigatorReInfoDto surveyInvestigatorReInfoDto, Map map){
            File file = new File(filePath);
            if (!file.exists() && !file.isDirectory()){
                file.mkdirs();
            }
            file = new File(filePath + File.separator +"费用报销清单打印.pdf");
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
                Paragraph paragraph = new Paragraph("费用报销清单打印",font1);
                paragraph.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(paragraph);

//                paragraph = new Paragraph("111111111111111111111",font2);
//                paragraph.setAlignment(Paragraph.ALIGN_LEFT);
//                paragraph.setIndentationLeft(30);// 左缩进
//            paragraph.setIndentationRight(6);// 右缩进
//            paragraph.setFirstLineIndent(6);// 首行缩进
//            paragraph.setSpacingBefore(5f);// 上留白
//            paragraph.setSpacingAfter(5f);// 下留白
//            paragraph.setLeading(5f);// 行间距
//                document.add(paragraph);

                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(510);
                int [] widths = {100,310,100};
                table.setWidths(widths);
                table.setLockedWidth(true);
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setBorder(1);
                table.getDefaultCell().setPadding(5f);
//            table.setWidthPercentage(100);

                PdfPCell cell = new PdfPCell(new Phrase("调查员",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getSurveyUserName(), font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("公司",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("江苏乐凡保险公估有限公司",font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("调查机构",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getSurveyOrgName(),font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("清单名称",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getReName(),font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("报销状态",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getReStateStr(),font2));
                cell.setColspan(2);
                table.addCell(cell);


                cell = new PdfPCell(new Phrase("关联案件数",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getTotalCaseNum()),font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("费用报销合计",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getTotalMoney().toString(),font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("件均报销金额",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getAvgMoney()+"元/件",font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("件均环比上月",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(surveyInvestigatorReInfoDto.getHuanbiMoney()+"%",font2));
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("机构件均",font2));
                table.addCell(cell);
                String formattedAvgMoney = String.format("%.2f", surveyInvestigatorReInfoDto.getOrgAvgMoney());
                cell = new PdfPCell(new Phrase(formattedAvgMoney+"元/件",font2));
                cell.setColspan(2);
                table.addCell(cell);

//                cell = new PdfPCell(new Phrase("市内交通费",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getCityinDrivingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
////
//                cell = new PdfPCell(new Phrase("病史费（含复印费）",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getMedicalHistoryMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("住院排查费用",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getTroubleshootingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("门诊排查费用",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getOpcTroubleshootingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("体检报告打印费",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getPrintingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("住宿费",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getAccommodatioMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("跨地市交通费（汽车、火车、飞机）",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getCrossDrivingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("跨地市交通费（自驾）",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getSelfDrivingMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("其他费用",font2));
//                table.addCell(cell);
//                cell = new PdfPCell(new Phrase(String.valueOf(surveyInvestigatorReInfoDto.getOtherMoney()),font2));
//                cell.setColspan(2);
//                table.addCell(cell);


                int count1 = 0;
                Double value = (Double) map.get("cityinDrivingMoney");
                if (value  != 0) {
                    count1++;
                }
                Double value1 = (Double) map.get("medicalHistoryMoney");
                if (value1  != 0) {
                    count1++;
                }
                Double value2 = (Double) map.get("accommodatioMoney");
                if (value2  != 0) {
                    count1++;
                }
                cell = new PdfPCell(new Phrase("发票",font2));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(count1);
                table.addCell(cell);

                if (value  != 0) {
                    cell = new PdfPCell(new Phrase("交通费", font2));
                    table.addCell(cell);
                    String costMoney = String.valueOf(map.get("cityinDrivingMoney"));
                    cell = new PdfPCell(new Phrase(costMoney, font2));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.disableBorderSide(Rectangle.LEFT);
                    table.addCell(cell);
                }
                if (value1  != 0) {
                    cell = new PdfPCell(new Phrase("病案调阅费", font2));
                    table.addCell(cell);
                    String costMoney = String.valueOf(map.get("medicalHistoryMoney"));
                    cell = new PdfPCell(new Phrase(costMoney, font2));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.disableBorderSide(Rectangle.LEFT);
                    table.addCell(cell);
                }
                if (value2  != 0) {
                    cell = new PdfPCell(new Phrase("住宿费", font2));
                    table.addCell(cell);
                    String costMoney = String.valueOf(map.get("accommodatioMoney"));
                    cell = new PdfPCell(new Phrase(costMoney, font2));
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
}
