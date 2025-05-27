package com.lefancrm.apicenter.enums;

import org.apache.commons.collections.map.LinkedMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/5/4.   案件报告(公估报告,索赔方案,结案报告)  项目枚举
 */
public enum ReportProjectEnum {
    ASSESSMENT_TYPE(1),//公估报告项目 与测算项目对应
    MEDIATION_TYPE(2),//索赔方案项目与公估项目对应
    MEDIATION_TYPE_PAY(4),//索赔方案与测算项目对应
    CLOSE_TYPE(3),//结案报告项目 与(索赔方案 或者 诉讼方案对应)
    MEDIATION_LEGAL_TYPE(5), //诉讼报告与索赔项目对应
    ;

    private int type;
    //初始化项目
    ReportProjectEnum(int type){
        this.type = type;
        setMap();
    }

    private Map<String,Object> map;

    public void setMap(){
        switch (this.type){
            case 1:
                map = new LinkedMap();
                map.put("医疗费","医疗费");
                map.put("住院伙食补助","医院伙食补助费");
                map.put("后续治疗费","后续治疗费");
                map.put("营养费","营养费");
                map.put("医疗小计","");
                map.put("误工费","误工费");
                map.put("护理费","护理费");
                map.put("交通费","交通费");
                map.put("残疾赔偿金","伤残赔偿金");
                map.put("伤残辅助用具","");
                map.put("丧葬费","");
                map.put("死亡赔偿金","");
                map.put("被抚养人生活费","");
                map.put("精神抚慰金","精神抚慰金");
                map.put("赔款小计","");
                map.put("财物项(车、衣物等)","财物损失费");
                map.put("合计","总计");
                break;
            case 2:
                map = new LinkedMap();
                map.put("医疗费","医疗费");
                map.put("住院伙食补助费","住院伙食补助");
                map.put("营养费","营养费");
                map.put("后续治疗费","后续治疗费");
                map.put("护理费","护理费");
                map.put("误工费","误工费");
                map.put("残疾赔偿金","残疾赔偿金");
                map.put("精神抚慰金","精神抚慰金");
                map.put("被抚养人生活费","被抚养人生活费");
                map.put("死亡赔偿金","死亡赔偿金");
                map.put("丧葬费","丧葬费");
                map.put("交通费","交通费");
                map.put("诉讼费","");
                map.put("残疾器具费","伤残辅助用具");
                map.put("鉴定费","");
                map.put("财产损失","财物项(车、衣物等)");
                map.put("合计损失","合计");
                break;
            case 3:
                map = new LinkedMap();
                map.put("医疗费","医疗费");
                map.put("住院伙食补助","住院伙食补助费");
                map.put("营养费","营养费");
                map.put("后续治疗费","后续治疗费");
                map.put("护理费","护理费");
                map.put("误工费","误工费");
                map.put("残疾赔偿金","残疾赔偿金");
                map.put("精神抚慰金","精神抚慰金");
                map.put("被抚养人生活费","被抚养人生活费");
                map.put("死亡赔偿金","死亡赔偿金");
                map.put("丧葬费","丧葬费");
                map.put("交通费","交通费");
                map.put("诉讼费","诉讼费");
                map.put("残疾器具费","残疾器具费");
                map.put("鉴定费","鉴定费");
                map.put("财产损失","财产损失");
                map.put("合计损失","合计损失");
                break;
            case 4:
                map = new LinkedMap();
                map.put("医疗费","医疗费");
                map.put("住院伙食补助费","医院伙食补助费");
                map.put("营养费","营养费");
                map.put("后续治疗费","后续治疗费");
                map.put("护理费","护理费");
                map.put("误工费","误工费");
                map.put("残疾赔偿金","伤残赔偿金");
                map.put("精神抚慰金","精神抚慰金");
                map.put("被抚养人生活费","");
                map.put("死亡赔偿金","");
                map.put("丧葬费","");
                map.put("交通费","交通费");
                map.put("诉讼费","");
                map.put("残疾器具费","");
                map.put("鉴定费","");
                map.put("财产损失","财物损失费");
                map.put("合计损失","总计");
                break;
            case 5:
                map = new LinkedMap();
                map.put("医疗费","医疗费");
                map.put("住院伙食补助费","住院伙食补助费");
                map.put("营养费","营养费");
                map.put("后续治疗费","后续治疗费");
                map.put("护理费","护理费");
                map.put("误工费","误工费");
                map.put("残疾赔偿金","残疾赔偿金");
                map.put("精神抚慰金","精神抚慰金");
                map.put("被抚养人生活费","被抚养人生活费");
                map.put("死亡赔偿金","死亡赔偿金");
                map.put("丧葬费","丧葬费");
                map.put("交通费","交通费");
                map.put("诉讼费","诉讼费");
                map.put("残疾器具费","残疾器具费");
                map.put("鉴定费","鉴定费");
                map.put("财产损失","财产损失");
                map.put("合计损失","合计损失");
                break;
        }
    }

    public Map<String, Object> getMap(){
        switch (this.type){
            case 1:
                return map;
            case 2:
                return map;
            case 3:
                return map;
            case 4:
                return map;
            case 5:
                return map;
        }
        return new HashMap<>();
    }

}
