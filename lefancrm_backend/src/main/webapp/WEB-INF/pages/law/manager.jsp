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
            background-image: url('../img/key-bg1.png');
            background-size: cover;
        }

        .bg1 {
            background-image: url('../img/key-bg3.png');
        }

        .bg2 {
            background-image: url('../img/key-bg2.png');
        }

        .bg3 {
            background-image: url('../img/key-bg1.png');
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
        <c:if test="${lawUserRole.isComplex || lawUserRole.isAssessSuper || lawUserRole.isAssessManager}">
            <div class="keys">
                <div class="key color1">
                    <div class="key-num">${numberDto.number1}</div>
                    <div class="key-desc">委托案件数</div>
                    <div class="key-img bg1"></div>
                </div>
                <div class="key color2">
                    <div class="key-num">${numberDto.number2}</div>
                    <div class="key-desc">接收案件数</div>
                    <div class="key-img bg2"></div>
                </div>
                <div class="key color3">
                    <div class="key-num">${numberDto.number3}</div>
                    <div class="key-desc">退案数</div>
                    <div class="key-img bg3"></div>
                </div>
            </div>
        </c:if>
        <div class="content">
            <c:if test="${lawUserRole.isComplex}">
                <div class="bar" onclick="openlist(4)">
                    <div class="bar-in">
                        <div class="bar-title">催收案件</div>
                        <div class="bar-num">${numberDto.type4}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(6)">
                    <div class="bar-in">
                        <div class="bar-title">评估计划未寄送</div>
                        <div class="bar-num">${numberDto.type6}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(7)">
                    <div class="bar-in">
                        <div class="bar-title">评估报告未寄送</div>
                        <div class="bar-num">${numberDto.type7}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${lawUserRole.isAssess}">
                <div class="bar" onclick="openlist(2)">
                    <div class="bar-in">
                        <div class="bar-title">评估员未接收</div>
                        <div class="bar-num">${numberDto.type2}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(3)">
                    <div class="bar-in">
                        <div class="bar-title">评估超时效案件</div>
                        <div class="bar-num">${numberDto.type3}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(5)">
                    <div class="bar-in">
                        <div class="bar-title">评估计划未完成</div>
                        <div class="bar-num">${numberDto.type5}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${lawUserRole.isAssessSuper}">
                <div class="bar"  onclick="openlist(1)">
                    <div class="bar-in">
                        <div class="bar-title">委托审核中</div>
                        <div class="bar-num">${numberDto.type1}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(9)">
                    <div class="bar-in">
                        <div class="bar-title">未分派</div>
                        <div class="bar-num">${numberDto.type9}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${lawUserRole.isAssessSuper || lawUserRole.isAssessManager}">
                <div class="bar" onclick="openlist(8)">
                    <div class="bar-in">
                        <div class="bar-title">审核中</div>
                        <div class="bar-num">${numberDto.type8}</div>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${lawUserRole.isAssess}">
            <div style="text-align: right">
                <a class="title-a" onclick="openlist(10)" href="javascript:void(0);">计划信息未确认(<span class="title-span">${numberDto.title1}</span>)</a>
                <a class="title-a" onclick="openlist(11)"  href="${ctx}/law/list?menuType=18&flowState=6&isExpress=1">计划完成(<span class="title-span">${numberDto.title2}</span>)</a>
                <a class="title-a" onclick="openlist(12)"  href="${ctx}/law/list?menuType=20&flowState=8&isReport=0">待制作报告(<span class="title-span">${numberDto.title3}</span>)</a>
                <a class="title-a" onclick="openlist(13)"  href="${ctx}/law/list?menuType=20&isOkInfo2=0">报告信息未确认(<span class="title-span">${numberDto.title4}</span>)</a>
                <a class="title-a" onclick="openlist(15)"  href="${ctx}/law/list?menuType=20&flowState=8&isReport=1">报告待提交审核(<span class="title-span">${numberDto.title10}</span>)</a>
                <a class="title-a" onclick="openlist(14)"  href="${ctx}/law/list?menuType=20&flowState=12">待结案(<span class="title-span">${numberDto.title5}</span>)</a>
            </div>
        </c:if>
    </div>
</body>
<script>
    function openlist(type){
        var url = "";
        var title = "";
        if(type == 1){
            url = "${ctx}/law/list?menuType=5&flowState=2";
            title  = "委托审核中";
        }else if(type == 2){
            url = "${ctx}/law/list?menuType=15";
            title  = "评估员未接收";
        }else if(type == 3){
            url = "${ctx}/law/list?menuType=20&isUploadReqmoneyLetter=3";
            title  = "评估超时案件";
        }else if(type == 4){
            url = "${ctx}/law/list?menuType=18&isUploadReqmoneyLetter=2";
            title  = "催收案件";
        }else if(type == 5){
            url = "${ctx}/law/list?menuType=18&condition=1";
            title  = "评估计划未完成";
        }else if(type == 6){
            url = "${ctx}/law/list?menuType=1&stageState=2&isUploadAssessPlan=1&isUploadReqmoneyLetter=1&isExpress=0";
            title  = "评估计划未寄送";
        }else if(type == 7){
            url = "${ctx}/law/list?menuType=1&flowState=12&isSendReport=0";
            title  = "评估报告未寄送";
        }else if(type == 8){
            url = "${ctx}/law/list?menuType=25";
            title  = "审核中";
        }else if(type == 9){
            url = "${ctx}/law/list?menuType=10";
            title  = "未分派";
        }else if(type == 10){
            url = "${ctx}/law/list?menuType=18&flowState=6&isOkInfo=0";
            title = "计划信息未确认";
        }else if(type == 11){
            url = "${ctx}/law/list?menuType=18&flowState=6&isExpress=1";
            title = "计划完成";
        }else if(type == 12){
            url = "${ctx}/law/list?menuType=20&flowState=8&isReport=0";
            title = "制作报告";
        }else if(type == 13){
            url = "${ctx}/law/list?menuType=20&isOkInfo2=0";
            title = "待确认案件";
        }else if(type == 14){
            url = "${ctx}/law/list?menuType=20&flowState=12";
            title = "待结案";
        }else if(type == 15){
            url = "${ctx}/law/list?menuType=20&flowState=8&isReport=1";
            title = "报告待提交审核";
        }
        parent.addTab(title,url);
    }

</script>
</html>