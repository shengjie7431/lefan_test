package com.lefancrm.apicenter.util;
import com.lefancrm.apicenter.util.emailSender.MailSenderInfo;
import com.lefancrm.apicenter.util.emailSender.SimpleMailSender;

/**
 * Created by Jani on 2017/10/20.
 */
public class EmailSender {
    public static void main(String[] args){
        System.out.println(DecimalUtil.twoDecimalTOFourFromFive(((float)2 / 7) * 1200));


        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.ym.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("service@sh-lefan.com");
        mailInfo.setPassword("shlefan.com123");//您的邮箱密码
        mailInfo.setFromAddress("service@sh-lefan.com");
        mailInfo.setToAddress("jani@sh-lefan.com");
        mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");

        mailInfo.setContent("<h2>\n" +
                "\t<span style=\"font-size:14px;\">一、免费电话咨询</span> \n" +
                "</h2>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<img src=\"https://openapi.shlefan.com/pic/images/1/20170412/default/803e228dca5f4658b922ff07748ea3eb.png\" alt=\"\" /> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:14px;\">指导保险理赔政策，理赔所需单证、伤残等级预估、理赔款测算。</span> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:14px;\"><strong>二、免费伤残等级预估</strong></span> \n" +
                "</p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<span style=\"font-size:24px;\"><img src=\"https://openapi.shlefan.com/pic/images/1/20170519/default/dd933287000f4b108aa650ace8a25530.png\" alt=\"\" /><br />\n" +
                "</span> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:14px;line-height:1.5;\">您只需要将您的医学影像片、检查报告单准备好，我们会帮助您对伤残等级进行专业、客观的预估。</span> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:14px;\"><strong><br />\n" +
                "</strong></span> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:14px;\"><strong>三、免费赔偿金额测算</strong></span> \n" +
                "</p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<span style=\"font-size:18px;\"><img src=\"https://openapi.shlefan.com/pic/images/1/20170412/default/9a2eb867fa9b44ac990eb59112fcb869.png\" alt=\"\" /><br />\n" +
                "</span> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"font-size:18px;\"><span style=\"font-size:14px;\">您</span><span style=\"line-height:1.5;font-size:14px;\">只需要将您的医学影像片、检查报告单准备好，我们会帮助您客观测算本次事故可以获得的赔偿项目及赔偿金额。</span></span> \n" +
                "</p>");
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);//发送文体格式
        sms.sendHtmlMail(mailInfo);//发送html格式
    }
}
