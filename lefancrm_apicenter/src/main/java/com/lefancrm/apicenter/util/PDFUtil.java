package com.lefancrm.apicenter.util;

import com.lefancrm.apicenter.model.CaseAssessmentObjReport;
import com.lefancrm.apicenter.model.CaseAssessmentReport;
import com.lefancrm.apicenter.model.CaseRiskControl;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Created by DELL on 2017/9/12.
 */
public class PDFUtil {

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


    public PDFUtil() {

    }
    private static int maxWidth = 520;
    public static File createCaseRiskControlPDF(String reportPathUrl,String pdfName,CaseRiskControl caseRiskControl){
        String filePath = reportPathUrl + pdfName + File.separator;
        File file = new File(filePath);
        try {
            if (!file.exists() && !file.isDirectory()){
                file.mkdirs();
            }
            file = new File(filePath+"JR_"+pdfName+".pdf");
            file.createNewFile();
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");
            document.setMargins(10, 20, 30, 40);
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file));
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            document.open();
            generateCaseRiskControlPDF(document,caseRiskControl);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static File createCaseAssessmentReportPDF(String reportPathUrl,String pdfName,Map<String,Object> map){
        String filePath = reportPathUrl + pdfName + File.separator;
        File file = new File(filePath);
        try {
            if (!file.exists() && !file.isDirectory()){
                file.mkdirs();
            }
            file = new File(filePath+"GG_" + pdfName + ".pdf");
            file.createNewFile();
            Document document = new Document();
            document = new Document();
            document.setPageSize(PageSize.A4);
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");
            document.setMargins(10, 20, 30, 40);
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file));
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            document.open();
            generateCaseAssessmentReportPDF(document, map);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return file;
    }
    public static void main1(String[] args) {
        CaseRiskControl caseRiskControl = new CaseRiskControl();
        caseRiskControl.setCaseNo("DF0128050951");
        createCaseRiskControlPDF("F:/maven/test/","评估报告", caseRiskControl);


        Map<String,Object> map = new HashMap<String,Object>();
        CaseAssessmentReport caseAssessmentReport = new CaseAssessmentReport();
        caseAssessmentReport.setCaseNo("DF0128050951");
        map.put("caseAssessmentReport",caseAssessmentReport);
        List<CaseAssessmentObjReport> caseAssessmentObjReports = new ArrayList<CaseAssessmentObjReport>();
        String [] projectNames = {"医疗费","住院伙食补助","营养费","后续治疗费","小计","误工费","护理费","交通费","残疾赔偿金","伤残辅助用具","丧葬费","死亡赔偿金","被抚养人生活费","精神抚慰金","小计","财物项(车、衣物等)","合计"};
        for (String name : projectNames){
            CaseAssessmentObjReport caseAssessmentObjReport = new CaseAssessmentObjReport();
            caseAssessmentObjReport.setProjectName(name);
            caseAssessmentObjReports.add(caseAssessmentObjReport);
        }
        map.put("caseAssessmentObjReports", caseAssessmentObjReports);
        createCaseAssessmentReportPDF("F:/maven/test/","公估报告", map);
    }

    /**
     * 生成评估报告PDF
     * @param document
     * @param caseRiskControl
     */
    private static void generateCaseRiskControlPDF(Document document,CaseRiskControl caseRiskControl){
        try {
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            Font font2 = new Font(bfChinese, 10, Font.NORMAL);
            Font font3 = new Font(bfChinese,10,Font.BOLD);
            Paragraph paragraph = new Paragraph("“人伤全无忧”保险评估报告",font1);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph("评估编号: ",font2);
            Chunk chunk = new Chunk(caseRiskControl.getCaseNo(),font2);
            chunk.setUnderline(0.1f, -2f);
            paragraph.add(chunk);
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(paragraph);

            paragraph = new Paragraph("致：长安责任保险股份有限公司",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);


            paragraph = new Paragraph("借款人信息",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase("身份信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("姓名",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getUserName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("性别",font2));
            table.addCell(cell);
            String value = null;
            if ("1".equals(caseRiskControl.getSex()+"")){
                value = "男";
            }else if ("2".equals(caseRiskControl.getSex()+"")){
                value = "女";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("身份证号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getIdCard(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("联系电话",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getUserTel(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("身份证地址",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getIdAddress(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("现居住地址",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getResidentialAddress(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("工作信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("工作单位名称",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getEmployeName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("职位",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getEmployePosition(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("工作单位地址",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getEmployeAddress(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("现工作单位年限",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getWorkYear(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("月税后工资",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((caseRiskControl.getWages() == null ? "0" : caseRiskControl.getWages()) + "元",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("家庭信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("婚姻情况",font2));
            table.addCell(cell);
            value = null;
            if ("1".equals(caseRiskControl.getMaritalStatus()+"")){
                value = "已婚";
            }else if ("2".equals(caseRiskControl.getMaritalStatus()+"")){
                value = "未婚";
            }else if ("3".equals(caseRiskControl.getMaritalStatus()+"")){
                value = "离异";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("其他贷款及月供",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getOtherLoan(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("配偶姓名",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getSpouseName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("配偶电话",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getSpouseTel(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("紧急联系人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getEgyLinkman(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("紧急联系人电话",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseRiskControl.getEgyLinkmanTel(),font2));
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            paragraph = new Paragraph("借贷信息",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
            PdfPTable table1 = new PdfPTable(5);
            table1.setTotalWidth(510);
            table1.setLockedWidth(true);
            table1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.getDefaultCell().setBorder(1);
            table1.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell(new Phrase("借贷信息",font3));
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setRowspan(3);
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("借款用途",font2));
            table1.addCell(cell1);
            value = null;
            if ("1".equals(caseRiskControl.getLoanPurpose()+"")){
                value = "医疗费垫付";
            }else if ("2".equals(caseRiskControl.getLoanPurpose()+"")){
                value = "赔偿款垫付";
            }else if ("3".equals(caseRiskControl.getLoanPurpose()+"")){
                value = "其他";
            }
            cell1 = new PdfPCell(new Phrase(value));
            cell1.setColspan(3);
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("借款金额",font2));
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase((caseRiskControl.getLoanMoney() == null ? "0" : caseRiskControl.getLoanMoney()) + "元",font2));
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("借款周期",font2));
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase((caseRiskControl.getLendingCycle() == null ? "  " : caseRiskControl.getLendingCycle()) + "个月",font2));
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("还款方式",font2));
            table1.addCell(cell1);
            cell1 = new PdfPCell(new Phrase(caseRiskControl.getRepaymentMethod(),font2));
            cell1.setColspan(3);
            table1.addCell(cell1);

            paragraph = new Paragraph();
            paragraph.add(table1);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            paragraph = new Paragraph("保险公估信息",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
            PdfPTable table2 = new PdfPTable(4);
            table2.setTotalWidth(510);
            table2.setLockedWidth(true);
            table2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.getDefaultCell().setBorder(1);
            table2.setWidthPercentage(100);
            PdfPCell cell2 = new PdfPCell(new Phrase("人伤事故是否真实",font2));
            table2.addCell(cell2);
            value = null;
            if ("1".equals(caseRiskControl.getIsReal()+"")){
                value = "真实";
            }else if ("0".equals(caseRiskControl.getIsReal()+"")){
                value = "不真实";
            }
            cell2 = new PdfPCell(new Phrase(value,font2));
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase("是否属于保险事故责任",font2));
            table2.addCell(cell2);
            value = null;
            if ("1".equals(caseRiskControl.getIsInsurance()+"")){
                value = "属于";
            }else if ("0".equals(caseRiskControl.getIsInsurance()+"")){
                value = "不属于";
            }else{
                value = "  ";
            }
            cell2 = new PdfPCell(new Phrase(value,font2));
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase("是否存在拒赔或免赔行为",font2));
            table2.addCell(cell2);
            value = null;
            if ("1".equals(caseRiskControl.getIsExclusions()+"")){
                value = "存在";
            }else if ("0".equals(caseRiskControl.getIsExclusions()+"")){
                value = "不存在";
            }else{
                value = "  ";
            }
            Phrase phrase = new Phrase(value.concat("("+ (caseRiskControl.getExclusionsDesc() == null ? "  " : caseRiskControl.getExclusionsDesc()) + ")"),font2);
//            phrase.add(new Chunk("("+caseRiskControl.getExclusionsDesc() == null ? "  " : caseRiskControl.getExclusionsDesc() +")",font2));
            cell2 = new PdfPCell(phrase);
            cell2.setColspan(3);
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase("保险理赔金额评估",font2));
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase(caseRiskControl.getAssessmentAmount() == null ? "  " : caseRiskControl.getAssessmentAmount() + "",font2));
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase("保险理赔结案周期评估",font2));
            table2.addCell(cell2);
            cell2 = new PdfPCell(new Phrase((caseRiskControl.getAssessmentCycle() == null ? "  " : caseRiskControl.getAssessmentCycle()) + "个月",font2));
            table2.addCell(cell2);
            paragraph = new Paragraph("",font2);
            paragraph.add(table2);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            paragraph = new Paragraph("风险评估意见",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
            PdfPTable table3 = new PdfPTable(4);
            table3.setTotalWidth(510);
            table3.setLockedWidth(true);
            table3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.getDefaultCell().setBorder(1);
            table3.setWidthPercentage(100);
            PdfPCell cell3 = new PdfPCell(new Phrase("借贷人信息是否真实",font2));
            table3.addCell(cell3);
            value = null;
            if ("1".equals(caseRiskControl.getIsRealMan()+"")){
                value = "真实";
            }else if ("0".equals(caseRiskControl.getIsRealMan()+"")){
                value = "不真实";
            }else{
                value = "  ";
            }
            cell3 = new PdfPCell(new Phrase(value,font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("事由的场景是否真实",font2));
            table3.addCell(cell3);
            value = null;
            if ("1".equals(caseRiskControl.getIsRealReason()+"")){
                value = "真实";
            }else if ("0".equals(caseRiskControl.getIsRealReason()+"")){
                value = "不真实";
            }else{
                value = "  ";
            }
            cell3 = new PdfPCell(new Phrase(value,font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("借贷人信息是否符合条件",font2));
            table3.addCell(cell3);
            value = null;
            if ("1".equals(caseRiskControl.getIsSatisfy()+"")){
                value = "符合";
            }else if ("0".equals(caseRiskControl.getIsSatisfy()+"")){
                value = "不符合";
            }else{
                value = "  ";
            }
            cell3 = new PdfPCell(new Phrase(value,font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("申请金额/评估可保险理赔金额",font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase(caseRiskControl.getSettlementMoney() == null ? null : caseRiskControl.getSettlementMoney() + "",font2));
            table3.addCell(cell3);

            cell3 = new PdfPCell(new Phrase("乐凡金融匹配借贷产品",font2));
            table3.addCell(cell3);
            value = null;
            if ("1".equals(caseRiskControl.getMatchingProduct())){
                value = "人伤全无忧保证贷款";
            }else if ("2".equals(caseRiskControl.getMatchingProduct())){
                value = "人伤全无忧担保结算";
            }else if ("3".equals(caseRiskControl.getMatchingProduct())){
                value = "人伤全无忧资金垫付";
            }else{
                value = "  ";
            }
            cell3 = new PdfPCell(new Phrase(value,font2));
            cell3.setColspan(3);
            table3.addCell(cell3);


            cell3 = new PdfPCell(new Phrase("乐凡金融匹配借贷产品",font2));
            table3.addCell(cell3);
            Paragraph paragraph1 = new Paragraph("金额",font2);
            Chunk chunk1 = new Chunk(caseRiskControl.getLefanProposalMoney() == null ? "0" : caseRiskControl.getLefanProposalMoney() + "",font2);
            paragraph1.add(chunk1);
            paragraph1.add("万元");
            paragraph1.add("                  ");
            paragraph1.add("周期:");
            chunk1 = new Chunk(caseRiskControl.getLefanProposalCycle() == null ? "0" : caseRiskControl.getLefanProposalCycle() + "",font2);
            paragraph1.add(chunk1);
            paragraph1.add("个月");
            cell3 = new PdfPCell(paragraph1);
            cell3.setColspan(3);
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("资金放款账户信息",font2));
            cell3.setRowspan(3);
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("账户名",font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase(caseRiskControl.getAdvanceName(),font2));
            cell3.setColspan(2);
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("开户行",font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase(caseRiskControl.getAdvanceBank(),font2));
            cell3.setColspan(2);
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("账户号",font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase(caseRiskControl.getAdvanceAccount(),font2));
            cell3.setColspan(2);
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase("乐凡金融风控其他意见",font2));
            table3.addCell(cell3);
            cell3 = new PdfPCell(new Phrase(caseRiskControl.getRiskOpinion(),font2));
            cell3.setColspan(3);
            table3.addCell(cell3);
            paragraph = new Paragraph("",font2);
            paragraph.add(table3);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            PdfPTable table4 = new PdfPTable(6);
            table4.setTotalWidth(510);
            table4.setLockedWidth(true);
            table4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.getDefaultCell().setBorder(1);
            table4.setWidthPercentage(100);
            PdfPCell cell4 = new PdfPCell(new Phrase("风控专员签名",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getRiskCommissioner(),font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase("风控专员手机",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getRiskCommissionerTel(),font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase("审核日期",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getCheckTime() == null ? null : DateUtils.DateToStr(caseRiskControl.getCheckTime(),"yyyy-MM-dd"),font2));
            table4.addCell(cell4);

            cell4 = new PdfPCell(new Phrase("风控负责人签名",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getRiskHead(),font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase("风控负责人手机",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getRiskHeadTel(),font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase("签发日期",font2));
            table4.addCell(cell4);
            cell4 = new PdfPCell(new Phrase(caseRiskControl.getIssueTime() == null ? null : DateUtils.DateToStr(caseRiskControl.getIssueTime(),"yyyy-MM-dd"),font2));
            table4.addCell(cell4);
            paragraph = new Paragraph("",font2);
            paragraph.add(table4);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            paragraph = new Paragraph("加盖上海乐凡金融信息服务有限公司业务专用章",font2);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 生成公估报告PDF
     * @param document
     * @param map
     */
    private static void generateCaseAssessmentReportPDF(Document document,Map<String,Object> map){
        try {
            CaseAssessmentReport caseAssessmentReport = (CaseAssessmentReport)map.get("caseAssessmentReport");
            List<CaseAssessmentObjReport> caseAssessmentObjReports = (List<CaseAssessmentObjReport>)map.get("caseAssessmentObjReports");
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            Font font2 = new Font(bfChinese, 10, Font.NORMAL);
            Font font3 = new Font(bfChinese,10,Font.BOLD);
            Paragraph paragraph = new Paragraph("“人伤全无忧”保险公估报告",font1);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph("公估编号: ",font2);
            Chunk chunk = new Chunk(caseAssessmentReport.getCaseNo(),font2);
            chunk.setUnderline(0.1f, -2f);
            paragraph.add(chunk);
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(paragraph);

            paragraph = new Paragraph("致：上海乐凡金融信息服务有限公司",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);


            paragraph = new Paragraph("案件基本信息审核",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase("当事人基本信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("被保险人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsurantName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("身份证号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsurantId(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("车牌号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getCarNumber(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("手机号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsurantTel(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤者/死者姓名",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("身份证号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredId(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤者/死者方代理人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredAgentName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("身份证号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredAgentId(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("与伤者关系",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getRelationship(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("手机号码",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredAgentTel(),font2));
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("事故信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(8);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故发生时间",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAccidentTime() == null ? null : DateUtils.DateToStr(caseAssessmentReport.getAccidentTime(),"yyyy-MM-dd"),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故发生地点",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAccidentAddress(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故处理交警大队",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getTrafficPolice(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故处理交警",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getPoliceName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故交警大队联系方式",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getPoliceTel(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故认定证书是否已出具",font2));
            table.addCell(cell);
            String value = null;
            if ("1".equals(caseAssessmentReport.getIsIdentification())){
                value = "是";
            }else{
                value = "否";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故责任认定",font2));
            table.addCell(cell);
            //
//            paragraph = new Paragraph("预计被保险人承担" + caseAssessmentReport.getInsurantResponsibility() == null ? "  " : caseAssessmentReport.getInsurantResponsibility(),font2);
//            paragraph.add("责任，伤/死者承担" + caseAssessmentReport.getInjuredResponsibility() == null ? "  " : caseAssessmentReport.getInjuredResponsibility());
//            paragraph.add("责任");
            String name = "预计被保险人承担".concat((caseAssessmentReport.getInsurantResponsibility() == "" ? "  " : caseAssessmentReport.getInsurantResponsibility())).concat("责任,伤/死者承担").concat(
                    (caseAssessmentReport.getInjuredResponsibility() == null ? "  " : caseAssessmentReport.getInjuredResponsibility()).concat("责任"));
            Phrase phrase =  new Phrase(name,font2);
//            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("交强险垫付通知书/事故证明是否已出具",font2));
            cell.setRowspan(2);
            table.addCell(cell);
            value = "否";
            if ("1".equals(caseAssessmentReport.getIsAdvanceNotice() + "")){
                value = "是";
            }else if("0".equals(caseAssessmentReport.getIsAdvanceNotice() + "")){
                value = "否";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph("预计被保险人承担" + (caseAssessmentReport.getInsurantAccidentProof() == null ? "  " : caseAssessmentReport.getInsurantAccidentProof()),font2);
            paragraph.add("责任，伤/死者承担" + (caseAssessmentReport.getInjuredAccidentProof() == null ? "  " : caseAssessmentReport.getInjuredAccidentProof()));
            paragraph.add("责任");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故经过",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAccidentDesc(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);



            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("就诊信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(8);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("就诊医院",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getHospital(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("床位主管医生",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getDoctor(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("联系方式",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getDoctorTel(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("主要诊断",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getMainDiagnosis(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("既往病史",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getIllnessHistory(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("已、拟做手术名称",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getOperationName(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("已产生医疗费用",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getMedicalFee() == null ? null : caseAssessmentReport.getMedicalFee() + "",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("还需医疗费用",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getStillMedicalFee() == null ? null : caseAssessmentReport.getStillMedicalFee() + "",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤者方支付医疗费用金额",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInjuredMedicalFee() == null ? null : caseAssessmentReport.getInjuredMedicalFee() + "",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("被保险人方支付医疗费用金额",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsurantMedicalFee() == null ? null : caseAssessmentReport.getInsurantMedicalFee() + "",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("是否需要二次手术",font2));
            table.addCell(cell);
            value = null;
            if ("1".equals(caseAssessmentReport.getIsAgainOperation() + "")){
                value = "是";
            }else if("0".equals(caseAssessmentReport.getIsAgainOperation() + "")){
                value = "否";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("二次手术预估医疗费用",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAgainOperationFee() == null ? null : caseAssessmentReport.getAgainOperationFee() + "",font2));
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);



            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("保险承保信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(4);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("肇事车投保公司",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsuranceCompany(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("交强险",font2));
            table.addCell(cell);
            value = null;
            if ("1".equals(caseAssessmentReport.getIsCompulInsurance() + "")){
                value = "是";
            }else if("0".equals(caseAssessmentReport.getIsCompulInsurance() + "")){
                value = "否";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("商业三者险",font2));
            table.addCell(cell);
            paragraph = new Paragraph("投保",font2);
            Chunk chunk1 = new Chunk(caseAssessmentReport.getCommerInsurance() == null ? "0" : caseAssessmentReport.getCommerInsurance() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("万；50万不计免赔 ：");
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsDeductible() + "")){
                value = "有";
            }else if("0".equals(caseAssessmentReport.getIsDeductible() + "")){
                value = "无";
            }
            paragraph.add(value);
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("保单特别约定",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getPolicyAgreement(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);



            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("历史赔付信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(4);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("有无交强险垫付",font2));
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsCompulAdvance() + "")){
                value = "有";
            }else if("0".equals(caseAssessmentReport.getIsCompulAdvance() + "")){
                value = "无";
            }
            paragraph = new Paragraph(value + "(已垫付",font2);
            chunk1 = new Chunk(caseAssessmentReport.getCompulAdvanceFee() == null ? "0" : caseAssessmentReport.getCompulAdvanceFee() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元)");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("有无商业险预赔",font2));
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsCommerAdvance() + "")){
                value = "有";
            }else if("0".equals(caseAssessmentReport.getIsCommerAdvance() + "")){
                value = "无";
            }
            paragraph = new Paragraph(value + "(已预赔",font2);
            chunk1 = new Chunk(caseAssessmentReport.getCommerAdvanceFee() == null ? "0" : caseAssessmentReport.getCommerAdvanceFee() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元)");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("有无路救基金垫付",font2));
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsRoadRef() + "")){
                value = "有";
            }else if("0".equals(caseAssessmentReport.getIsRoadRef() + "")){
                value = "无";
            }
            paragraph = new Paragraph(value + "(已垫付",font2);
            chunk1 = new Chunk(caseAssessmentReport.getRoadRefFee() == null ? "0" : caseAssessmentReport.getRoadRefFee() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元)");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("有无其他赔付记录",font2));
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsOtherAdvance() + "")){
                value = "有";
            }else if("0".equals(caseAssessmentReport.getIsOtherAdvance() + "")){
                value = "无";
            }
            paragraph = new Paragraph(value + "(已赔付",font2);
            chunk1 = new Chunk(caseAssessmentReport.getOtherAdvanceFee() == null ? "0" : caseAssessmentReport.getOtherAdvanceFee() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元)");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);



            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("定损信息",font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("肇事车定损金额",font2));
            table.addCell(cell);
            paragraph = new Paragraph("",font2);
            chunk1 = new Chunk(caseAssessmentReport.getAccidentCarAmount() == null ? "0" : caseAssessmentReport.getAccidentCarAmount() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("三者车定损金额",font2));
            table.addCell(cell);
            paragraph = new Paragraph("",font2);
            chunk1 = new Chunk(caseAssessmentReport.getThreeCarAmount() == null ? "0" : caseAssessmentReport.getThreeCarAmount() + "",font2);
            paragraph.add(chunk1);
            paragraph.add("元");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("调查信息", font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("是否发起风险调查",font2));
            table.addCell(cell);
            value = null;
            if ("1".equals(caseAssessmentReport.getIsOtherAdvance() + "")){
                value = "调查";
            }else if("0".equals(caseAssessmentReport.getIsOtherAdvance() + "")){
                value = "未调查";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("调查结果",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getSurveyDesc(),font2));
            cell.setColspan(3);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            paragraph = new Paragraph("案件赔偿及保险理赔",font3);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);

            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("是否属于保险事故责任",font2));
            cell.setColspan(2);
            table.addCell(cell);
            value = null;
            if ("1".equals(caseAssessmentReport.getIsInsuranceAccident() + "")){
                value = "属于";
            }else if("0".equals(caseAssessmentReport.getIsInsuranceAccident() + "")){
                value = "不属于";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("是否存在保险拒赔或免赔行为",font2));
            cell.setColspan(2);
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsClaimsRejected() + "")){
                value = "存在";
            }else if("0".equals(caseAssessmentReport.getIsClaimsRejected() + "")){
                value = "不存在";
            }
            paragraph = new Paragraph(value + "(" + (caseAssessmentReport.getClaimsRejected() == null ? "  " : caseAssessmentReport.getClaimsRejected()),font2);
            paragraph.add(")");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("车架号核对是否正确",font2));
            cell.setColspan(2);
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsFrameNumber() + "")){
                value = "正确";
            }else if("0".equals(caseAssessmentReport.getIsFrameNumber() + "")){
                value = "有误";
            }
            paragraph = new Paragraph(value + "(" + (caseAssessmentReport.getFrameNumber() == null ? "  " : caseAssessmentReport.getFrameNumber()),font2);
            paragraph.add(")");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("行驶证、驾驶证是否有效",font2));
            cell.setColspan(2);
            table.addCell(cell);
            value = "无";
            if ("1".equals(caseAssessmentReport.getIsEffectiveCarNumber() + "")){
                value = "有效";
            }else if("0".equals(caseAssessmentReport.getIsEffectiveCarNumber() + "")){
                value = "无效";
            }
            paragraph = new Paragraph(value + "(" + (caseAssessmentReport.getEffectiveCarNumber() == null ? "  " : caseAssessmentReport.getEffectiveCarNumber()),font2);
            paragraph.add(")");
            phrase =  new Phrase();
            phrase.add(paragraph);
            cell = new PdfPCell(phrase);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤残/死亡赔偿金适用标准",font2));
            cell.setColspan(2);
            table.addCell(cell);
            value = "";
            if ("1".equals(caseAssessmentReport.getCompensationStandard() + "")){
                value = "城镇";
            }else if("2".equals(caseAssessmentReport.getCompensationStandard() + "")){
                value = "农村";
            }
            cell = new PdfPCell(new Phrase(value,font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setPhrase(new Phrase("伤残等级/三期评估", font3));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setRowspan(4);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤残等级",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getDisabilityGrade() == null ? "  " : caseAssessmentReport.getDisabilityGrade() + "级",font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("误工期限",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getDowntime() == null ? "0" : caseAssessmentReport.getDowntime() + "天",font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("护理期限",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getNursingTime() == null ? "0" : caseAssessmentReport.getNursingTime() + "天",font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("营养期限",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getNutritionTime() == null ? "0" : caseAssessmentReport.getNutritionTime() + "天",font2));
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("事故责任赔偿比例分担",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("被保险人承担赔偿比例" + (caseAssessmentReport.getInsurantResponsibilityRate() == null ? "  " : caseAssessmentReport.getInsurantResponsibilityRate()) + "%",font2));
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("伤/死者承担赔偿比例" + (caseAssessmentReport.getInjuredResponsibilityRate() == null ? "  " : caseAssessmentReport.getInjuredResponsibilityRate()) + "%",font2));
            cell.setColspan(2);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            table = new PdfPTable(5);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("赔偿项目",font2));
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("核损金额",font2));
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("核损依据",font2));
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("保险理赔金额",font2));
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("交强险",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("商业险",font2));
            table.addCell(cell);
            for (CaseAssessmentObjReport objReport : caseAssessmentObjReports){
                cell = new PdfPCell(new Phrase(objReport.getProjectName(),font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(objReport.getCheckAmount() == null ? null : objReport.getCheckAmount() + "",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(objReport.getCheckBasis(),font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(objReport.getCommerAmount() == null ? null : objReport.getCommerAmount() + "",font2));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(objReport.getCompulAmount() == null ?  null : objReport.getCompulAmount() + "",font2));
                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase("案件赔偿及保险理赔评估金额说明：",font2));
            cell.setColspan(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getInsuranceClaimsDesc(),font2));
            cell.setColspan(5);
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(5f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);


            table = new PdfPTable(6);
            table.setTotalWidth(510);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell(new Phrase("保险公估人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAssessorName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("公估执业证号",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAssessorCertificate(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("公估日期",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAssessmentDate() == null ? null : DateUtils.DateToStr(caseAssessmentReport.getAssessmentDate(),"yyyy-MM-dd"),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("公估审核人",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAuditorName(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("审核人公估执业证号",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAuditorCertificate(),font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("签发日期",font2));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(caseAssessmentReport.getAssessmentIssueDate() == null ? null : DateUtils.DateToStr(caseAssessmentReport.getAssessmentIssueDate(),"yyyy-MM-dd"),font2));
            table.addCell(cell);
            paragraph = new Paragraph();
            paragraph.add(table);
            paragraph.setIndentationLeft(6);// 左缩进
            paragraph.setIndentationRight(6);// 右缩进
            paragraph.setFirstLineIndent(6);// 首行缩进
            paragraph.setSpacingBefore(0f);// 上留白
            paragraph.setSpacingAfter(5f);// 下留白
            paragraph.setLeading(5f);// 行间距
            document.add(paragraph);

            paragraph = new Paragraph("加盖江苏乐凡赔偿保险公估有限公司业务专用章",font2);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            document.close();
        }catch (Exception e){

        }
    }

    public static String imgToPdf(Map<String,List<String>> files,String pdfPath){
        if (files.size() == 0) {
            return "";
        }
        try {
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            // 创建文档
            Document document = new Document();
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

            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                document.newPage();
//                System.out.println("是否新页：" + document.newPage());
                String key = iterator.next();
                Paragraph paragraph = new Paragraph(key,font1);
                paragraph.setAlignment(Paragraph.ALIGN_LEFT);
                paragraph.setSpacingAfter(5f);// 下留白
                document.add(paragraph);
                // 读取图片流
                BufferedImage img = null;
                // 实例化图片
                Image image = null;
                List<String> imgs = files.get(key);
                for (String imgPath : imgs) {
                    // 读取图片流
                    img = ImageIO.read(new File(imgPath));
                    image = Image.getInstance(imgPath);
                    image.scaleToFit(PageSize.A4.getWidth(), img.getHeight());
                    // 添加图片到文档
                    document.add(image);
                }
            }
            // 关闭文档
            document.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfPath;
    }

    public static void main(String[] args) {
        //获取所有的非图片文件
        List<String> attrs = new ArrayList<>();
        //所有的图片文件压缩成一个pdf
        String imgPdfPath = "E:\\mnt\\案件编号\\direction\\"+"测试"+"(附件).pdf";
        Map<String,List<String>> tempMap = new HashMap<>();//目录对应的图片集合
        File temp = new File("E:\\mnt\\案件编号\\direction");//方向目录
        if (temp.exists())
        {
            File[] names = temp.listFiles();//所有的方向名称
            for (File name : names) {
                if (name.isDirectory()){
                    File[] sonFoder = name.listFiles();//所有方向名称对应的子文件夹/老案件也可能没有文件夹直接对应的文件
                    List<String> imgs = new ArrayList<>();
                    for (File file1 : sonFoder) {
                        if (file1.isFile() && !checkImg(file1)) {//是文件并且不是图片
                            attrs.add(file1.getPath());
                        }
                        else if (file1.isFile() && checkImg(file1)){//是文件并且是图片
                            imgs.add(file1.getPath());
                            tempMap.put(name.getName(),imgs);//方向名称对于的图片集合
                        }
                        else if (file1.isDirectory()) {//是文件夹
                            imgs = new ArrayList<>();
                            File[] files = file1.listFiles();//所有方向名称子文件夹的文件
                            for (File file2 : files) {
                                if (file2.isFile() && !checkImg(file2)) {//是文件不是图片
                                    attrs.add(file2.getPath());
                                }
                                else if (file2.isFile() && checkImg(file2)){//是文件是图片
                                    imgs.add(file2.getPath());
                                    tempMap.put(name.getName() + "-" + file1.getName(),imgs);//方向名称对于的图片集合
                                }
                            }
                        }
                    }
                }
            }
        }
        imgPdfPath = PDFUtil.imgToPdf(tempMap,imgPdfPath);
    }

    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    public static boolean checkImg(File file){
        try {
            java.awt.Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }
}
