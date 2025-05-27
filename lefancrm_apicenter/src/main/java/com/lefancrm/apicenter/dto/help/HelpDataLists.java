package com.lefancrm.apicenter.dto.help;

import java.util.List;

public class HelpDataLists {
    private List<HelpDirectionLine> directions1;
    private List<HelpDirectionLine> directions2;
    private List<HelpDirectionLine> directions3;
    private List<HelpDirectionLine> directions4;
    private List<HelpDirectionLine> directions5;
    private List<HelpDirectionLine> directions6;
    private List<HelpDirectionLine> directions7;
    private List<HelpDirectionLine> directions8;
    private List<HelpDirectionLine> directions9;
    private List<HelpDirectionLine> directions10;
    private List<HelpDirectionLine> directions11;
    private List<HelpDirectionLine> directions12;
    private List<HelpDirectionLine> directions13;
    private List<HelpDirectionLine> directions14;
    private List<HelpDirectionLine> directions15;
    private List<HelpDirectionLine> directions16;
    private List<HelpDirectionLine> directions17;
    private List<HelpDirectionLine> directions18;
    private List<HelpDirectionLine> directions19;
    private List<HelpDirectionLine> directions20;
    private List<HelpDirectionLine> directions21;

    public List<HelpDirectionLine> getDirections1() {
        return directions1;
    }

    public void setDirections1(List<HelpDirectionLine> directions1) {
        this.directions1 = directions1;
    }

    public List<HelpDirectionLine> getDirections2() {
        return directions2;
    }

    public void setDirections2(List<HelpDirectionLine> directions2) {
        this.directions2 = directions2;
    }

    public List<HelpDirectionLine> getDirections3() {
        return directions3;
    }

    public void setDirections3(List<HelpDirectionLine> directions3) {
        this.directions3 = directions3;
    }

    public List<HelpDirectionLine> getDirections4() {
        return directions4;
    }

    public void setDirections4(List<HelpDirectionLine> directions4) {
        this.directions4 = directions4;
    }

    public List<HelpDirectionLine> getDirections5() {
        return directions5;
    }

    public void setDirections5(List<HelpDirectionLine> directions5) {
        this.directions5 = directions5;
    }

    public List<HelpDirectionLine> getDirections6() {
        return directions6;
    }

    public void setDirections6(List<HelpDirectionLine> directions6) {
        this.directions6 = directions6;
    }

    public List<HelpDirectionLine> getDirections7() {
        return directions7;
    }

    public void setDirections7(List<HelpDirectionLine> directions7) {
        this.directions7 = directions7;
    }

    public List<HelpDirectionLine> getDirections8() {
        return directions8;
    }

    public void setDirections8(List<HelpDirectionLine> directions8) {
        this.directions8 = directions8;
    }

    public List<HelpDirectionLine> getDirections9() {
        return directions9;
    }

    public void setDirections9(List<HelpDirectionLine> directions9) {
        this.directions9 = directions9;
    }

    public List<HelpDirectionLine> getDirections10() {
        return directions10;
    }

    public void setDirections10(List<HelpDirectionLine> directions10) {
        this.directions10 = directions10;
    }

    public List<HelpDirectionLine> getDirections11() {
        return directions11;
    }

    public void setDirections11(List<HelpDirectionLine> directions11) {
        this.directions11 = directions11;
    }

    public List<HelpDirectionLine> getDirections12() {
        return directions12;
    }

    public void setDirections12(List<HelpDirectionLine> directions12) {
        this.directions12 = directions12;
    }

    public List<HelpDirectionLine> getDirections13() {
        return directions13;
    }

    public void setDirections13(List<HelpDirectionLine> directions13) {
        this.directions13 = directions13;
    }

    public List<HelpDirectionLine> getDirections14() {
        return directions14;
    }

    public void setDirections14(List<HelpDirectionLine> directions14) {
        this.directions14 = directions14;
    }

    public List<HelpDirectionLine> getDirections15() {
        return directions15;
    }

    public void setDirections15(List<HelpDirectionLine> directions15) {
        this.directions15 = directions15;
    }

    public List<HelpDirectionLine> getDirections16() {
        return directions16;
    }

    public void setDirections16(List<HelpDirectionLine> directions16) {
        this.directions16 = directions16;
    }

    public List<HelpDirectionLine> getDirections17() {
        return directions17;
    }

    public void setDirections17(List<HelpDirectionLine> directions17) {
        this.directions17 = directions17;
    }

    public List<HelpDirectionLine> getDirections18() {
        return directions18;
    }

    public void setDirections18(List<HelpDirectionLine> directions18) {
        this.directions18 = directions18;
    }

    public List<HelpDirectionLine> getDirections19() {
        return directions19;
    }

    public void setDirections19(List<HelpDirectionLine> directions19) {
        this.directions19 = directions19;
    }

    public List<HelpDirectionLine> getDirections20() {
        return directions20;
    }

    public void setDirections20(List<HelpDirectionLine> directions20) {
        this.directions20 = directions20;
    }

    public List<HelpDirectionLine> getDirections21() {
        return directions21;
    }

    public void setDirections21(List<HelpDirectionLine> directions21) {
        this.directions21 = directions21;
    }

    public void setSheetLinesData(String sheetName,List<HelpDirectionLine> lines){
        switch (sheetName){
            case "面访患病成员及申请人": setDirections1(lines); break;
            case "走访就诊医疗机构": setDirections2(lines); break;
            case "居住地医疗机构排查": setDirections3(lines); break;
            case "社保排查": setDirections4(lines); break;
            case "商保排查": setDirections5(lines); break;
            case "体检机构排查": setDirections6(lines); break;
            case "事故地点排查": setDirections7(lines); break;
            case "事故处理机构排查": setDirections8(lines); break;
            case "面访患病成员家属": setDirections9(lines); break;
            case "走访出生医疗机构": setDirections10(lines); break;
            case "工作地医疗机构排查": setDirections11(lines); break;
            case "出险地医疗机构排查": setDirections12(lines); break;
            case "户籍所在地医疗机构排查": setDirections13(lines); break;
            case "走访街道办事处或村委会": setDirections14(lines); break;
            case "走访居住地": setDirections15(lines); break;
            case "走访户籍所在地": setDirections16(lines); break;
            case "走访工作单位": setDirections17(lines); break;
            case "走访疾控防疫中心": setDirections18(lines); break;
            case "走访公检法等机关单位": setDirections19(lines); break;
            case "走访鉴定机构": setDirections20(lines); break;
            case "走访材料出具机构": setDirections21(lines); break;
            default:
                break;
        }
    }
}
