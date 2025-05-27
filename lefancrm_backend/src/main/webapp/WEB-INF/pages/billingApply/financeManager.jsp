<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        * {
            font-family: 'Microsoft Yahei'
        }

        .body {
            max-width: 1200px;
            min-width: 900px;
            height: auto;
            margin: 70px auto;
        }

        .keys {
            max-width: 1200px;
            min-width: 900px;
            display: flex;
            justify-content: space-between;
        }

        .key {
            width: 32%;
            height: 207px;
            border-radius: 4px;
            color: #FFF;
            font-size: 20px;
        }

        .color1 {
            background-color: #3383FF;
        }

        .color2 {
            background-color: #73D13D;
        }

        .color3 {
            background-color: #FFC53D;
        }

        .key-num {
            padding-top: 24px;
            padding-left: 25px;
            height: 81px;
            line-height: 81px;
            font-size: 63px;
            font-weight: bold;
        }

        .key-desc {
            padding-left: 37px;
            height: 30px;
            line-height: 30px;
            font-size: 20px;
        }

        .key-img {
            width: 100%;
            height: 76px;
            background-image: url('${ctx}/img/key-bg1.png');
            background-size: cover;
        }

        .bg1 {
            background-image: url('${ctx}/img/key-bg3.png');
        }

        .bg2 {
            background-image: url('${ctx}/img/key-bg2.png');
        }

        .bg3 {
            background-image: url('${ctx}/img/key-bg1.png');
        }

        .content {
            max-width: 1200px;
            min-width: 900px;
            /* display: flex; */
            /* flex-wrap: wrap; */
            justify-content: space-between;
            margin-top: 35px;
        }

        .bar {
            display:inline-block;
            float: left;
            margin-right: 20px;
            width: 32%;
            /* height: 74px; */
            padding-bottom: 18px;
            cursor: pointer;
        }
        .bar:nth-child(3n){
            margin-right: 0;
        }
        .bar-in{
            width: 82%;
            padding: 28px 9%;
            background-color: #ededed;
            border-radius: 4px;
        }

        .bar-title {
            font-size: 20px;
            font-weight: 600;
            color: #666666;
        }

        .bar-num {
            font-size: 42px;
            font-weight: 600;
            color: #3BA9FF;
        }

        .title-a{
            color: #0000ff;
        }
        .title-span{
            color: red;
        }
    </style>
</head>

<body>
    <div class="body">
        <c:if test="${caseUserRole.isFinancial}">
            <div class="keys">
                <div class="key color1">
                    <div class="key-num">${numberDto.number1}</div>
                    <div class="key-desc">金融未开票</div>
                    <div class="key-img bg1"></div>
                </div>
                <div class="key color2">
                    <div class="key-num">${numberDto.number2}</div>
                    <div class="key-desc">公估未开票</div>
                    <div class="key-img bg2"></div>
                </div>
                <div class="key color3">
                    <div class="key-num">${numberDto.number3}</div>
                    <div class="key-desc">待确认到账</div>
                    <div class="key-img bg3"></div>
                </div>
            </div>

            <div class="content">
                <div class="bar"  onclick="openlist(1)">
                    <div class="bar-in">
                        <div class="bar-title">金融未开票</div>
                        <div class="bar-num">${numberDto.number1}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(2)">
                    <div class="bar-in">
                        <div class="bar-title">公估未开票</div>
                        <div class="bar-num">${numberDto.number2}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(3)">
                    <div class="bar-in">
                        <div class="bar-title">待确认到账</div>
                        <div class="bar-num">${numberDto.number3}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(4)">
                    <div class="bar-in">
                        <div class="bar-title">公估待确认到账</div>
                        <div class="bar-num">${numberDto.type1}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(5)">
                    <div class="bar-in">
                        <div class="bar-title">还款清单</div>
                        <div class="bar-num">${numberDto.type2}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(6)">
                    <div class="bar-in">
                        <div class="bar-title">还款确认</div>
                        <div class="bar-num">${numberDto.type3}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(7)">
                    <div class="bar-in">
                        <div class="bar-title">待提现确认</div>
                        <div class="bar-num">${numberDto.type4}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(8)">
                    <div class="bar-in">
                        <div class="bar-title">待支付项目</div>
                        <div class="bar-num">${numberDto.type6}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(9)">
                    <div class="bar-in">
                        <div class="bar-title">待审核项目</div>
                        <div class="bar-num">${numberDto.type5}</div>
                    </div>
                </div>

            </div>
    </div>
    </c:if>
    <c:if test="${caseUserRole.isFinancialAuditor}">
        <div class="bar" onclick="openlist(9)">
            <div class="bar-in">
                <div class="bar-title">待审核项目</div>
                <div class="bar-num">${numberDto.type5}</div>
            </div>
        </div>
    </c:if>
</body>
<script>
    function openlist(type){
        var url = "";
        var title = "";
        if(type == 1){
            url = "${ctx}/billingApply/billingApplyList?menuType=3&business=11";
            title  = "金融未开票";
        }else if(type == 2){
            url = "${ctx}/billingApply/billingApplyList?menuType=3&business=12";
            title  = "公估未开票";
        }else if(type == 3){
            url = "${ctx}/suning/withholdApply/list?type=3&numberType=11&withholdState=2";
            title  = "待确认到账";
        }else if(type == 4){
            url = "${ctx}/billingApply/billingApplyList?menuType=3&business=13";
            title  = "公估待确认到账";
        }else if(type == 5){
            url = "${ctx}/case/repayCaseCenterInfoList?handOutFlag=2";
            title  = "还款清单";
        }else if(type == 6){
            url = "${ctx}/case/confirmRepayCaseCenterInfoList";
            title  = "还款确认";
        }else if(type == 7){
            url = "${ctx}/withdrawalsInfo/withdrawalsInfoList?mtype=11";
            title  = "待提现确认";
        }else if(type == 8){
            url = "${ctx}/casePayInfo/casePayInfoList?payState=0";
            title  = "待支付项目";
        }else if(type == 9){
            url = "${ctx}/casePayInfo/casePayInfoList?auditState=2";
            title  = "待审核项目";
        }else if(type == 10){
            url = "${ctx}/";
            title = "";
        }else if(type == 11){
            url = "${ctx}/";
            title = "";
        }else if(type == 12){
            url = "${ctx}/";
            title = "";
        }else if(type == 13){
            url = "${ctx}/";
            title = "";
        }else if(type == 14){
            url = "${ctx}/";
            title = "";
        }else if(type == 15){
            url = "${ctx}/";
            title = "";
        }
        parent.addTab(title,url);
    }

</script>
</html>