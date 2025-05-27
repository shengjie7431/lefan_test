package com.lefancrm.apicenter.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianfeng on 2019/7/3.
 */
public class TestDirection {
    private String directionName = "dfasd";
    private String directionInfo = "directionInfo";

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(String directionInfo) {
        this.directionInfo = directionInfo;
    }

    public TestDirection(String directionName, String directionInfo) {
        this.directionName = directionName;
        this.directionInfo = directionInfo;
    }

    public static List<TestDirection> getTestDirections(){
        List<TestDirection> list = new ArrayList<TestDirection>();
        list.add(new TestDirection("去华山医院调查","调查人员至华东医院病案室，调阅被保人住院号515987的住院病史。经核实病史首页患者信息与被保人一致。\n" +
                "出入院时间：2019年4月26日-2019年4月30日\n" +
                "出入院诊断：右侧甲状腺恶性肿瘤\n" +
                "主诉： 体检发现甲状腺结节6月余\n" +
                "现病史：患者于6月余前，体检时发现甲状腺右叶不规则结节，建议进步检查，患者自觉不局部疼痛或压迫感，到外院就诊，查B超提示甲状腺右叶结节，建议手术或随访复查，1周前我院复查甲状腺B超，提示甲状腺右叶实质性结节伴钙化，细针穿刺结果：甲状腺乳头状癌，现为进步手术治疗，门诊拟右侧甲状腺结节收入。\n" +
                "既往史： 否认。\n" +
                "重要检、化验报告：\n" +
                "手术日期：2019年4月28日\n" +
                "手术名称：右侧甲状腺癌根治术+左侧甲状腺此全切除+甲状腺峡部切除术\n" +
                "病理诊断：甲状腺乳头状癌"));
        list.add(new TestDirection("去龙华医院调查","查该院电脑，发现被保人2019年1月曾因甲状腺恶性肿瘤住院（病史详见附件）。"));
        list.add(new TestDirection("去三林医院调查","撒旦飞洒地方了我如我euro为u人为立方公里大风给都发给你了大幅改进了"));
        list.add(new TestDirection("去儿童医学中心调查","杀杀杀的发生地方撒旦飞洒地方看见我二级微弱据了解大发噶拉的风格的浪费国家领导的分裂国家的风格我i人他"));
        return list;
    }
}
