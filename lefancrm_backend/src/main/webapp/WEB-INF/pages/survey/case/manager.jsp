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

        @media screen and (min-width: 1180px) {
            .bar:nth-child(3n){
                margin-right: 0;
            }
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
        <c:if test="${dto.lfZhuguan || dto.lfNeiqin || dto.lfZhongshen}">
            <div class="keys">
                <div class="key color1">
                    <div class="key-num">${dto.entrustNum5}</div>
                    <div class="key-desc">委托阶段</div>
                    <div class="key-img bg1"></div>
                </div>
                <div class="key color2">
                    <div class="key-num">${dto.surveyNum5}</div>
                    <div class="key-desc">调查阶段</div>
                    <div class="key-img bg2"></div>
                </div>
                <div class="key color3">
                    <div class="key-num">${dto.lfNum5}</div>
                    <div class="key-desc">已结案</div>
                    <div class="key-img bg3"></div>
                </div>
            </div>
        </c:if>
        <div class="content">
            <c:if test="${dto.entrustRole || dto.entrustAgentRole}"><!-- 委托人权限 -->
                <div class="bar" onclick="openlist(1)">
                    <div class="bar-in">
                        <div class="bar-title">退回案件</div>
                        <div class="bar-num">${dto.entrustNum1 == null ? 0 : dto.entrustNum1}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(2)">
                    <div class="bar-in">
                        <div class="bar-title">保司待审核</div>
                        <div class="bar-num">${dto.entrustNum2 == null ? 0 : dto.entrustNum2}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.lfZhuguan || dto.lfNeiqin || dto.lfZhongshen}"> <!-- 乐凡 -->
                <c:if test="${dto.lfZhuguan || dto.lfNeiqin}">
                    <div class="bar" onclick="openlist(3)">
                        <div class="bar-in">
                            <div class="bar-title">委托待受理</div>
                            <div class="bar-num">${dto.lfNum1 == null ? 0 : dto.lfNum1}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(4)">
                        <div class="bar-in">
                            <div class="bar-title">平台待分派</div>
                            <div class="bar-num">${dto.lfNum2 == null ? 0 : dto.lfNum2}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${dto.lfZhongshen}">
                    <div class="bar" onclick="openlist(5)">
                        <div class="bar-in">
                            <div class="bar-title">终审</div>
                            <div class="bar-num">${dto.lfNum3 == null ? 0 : dto.lfNum3}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${dto.lfUpload}">
                    <div class="bar" onclick="openlist(9)">
                        <div class="bar-in">
                            <div class="bar-title">报告待上传</div>
                            <div class="bar-num">${dto.add2 == null ? 0 : dto.add2}</div>
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${dto.surveyRole}">
                <div class="bar"  onclick="openlist(6)">
                    <div class="bar-in">
                        <div class="bar-title">待接收</div>
                        <div class="bar-num">${dto.surveyNum1 == null ? 0 : dto.surveyNum1}</div>
                    </div>
                </div>
                <div class="bar" onclick="openlist(7)">
                    <div class="bar-in">
                        <div class="bar-title">调查中</div>
                        <div class="bar-num">${dto.surveyNum2 == null ? 0 : dto.surveyNum2}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.orgChushen || dto.orgZhongshen}">
                <div class="bar"  onclick="openlist(8)">
                    <div class="bar-in">
                        <div class="bar-title">待协助处理</div>
                        <div class="bar-num">${dto.add1 == null ? 0 : dto.add1}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.orgChushen}">
                <div class="bar"  onclick="openlist(10)">
                    <div class="bar-in">
                        <div class="bar-title">机构待分派</div>
                        <div class="bar-num">${dto.add3 == null ? 0 : dto.add3}</div>
                    </div>
                </div>
                <div class="bar"  onclick="openlist(11)">
                    <div class="bar-in">
                        <div class="bar-title">机构初审</div>
                        <div class="bar-num">${dto.add4 == null ? 0 : dto.add4}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.orgZhongshen}">
                <div class="bar"  onclick="openlist(12)">
                    <div class="bar-in">
                        <div class="bar-title">初审中</div>
                        <div class="bar-num">${dto.add5 == null ? 0 : dto.add5}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.lfZhuguan}">
                <div class="bar"  onclick="openlist(13)">
                    <div class="bar-in">
                        <div class="bar-title">拒接审核中</div>
                        <div class="bar-num">${dto.add6 == null ? 0 : dto.add6}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${dto.lfZhongshen}">
                <div class="bar"  onclick="openlist(14)">
                    <div class="bar-in">
                        <div class="bar-title">调度审核中</div>
                        <div class="bar-num">${dto.add7 == null ? 0 : dto.add7}</div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</body>
<script>
    function openlist(type){
        var url = "";
        var title = "";
        if(type == 1){
            url = "${ctx}/survey/case/list?menuCode=my-list&surveyState=6";
            title  = "退回案件";
        }else if(type == 2){
            url = "${ctx}/survey/case/list?menuCode=entrust-list";
            title  = "保司待审核";
        }else if(type == 3){
            url = "${ctx}/survey/case/list?menuCode=check-list";
            title  = "委托待受理";
        }else if(type == 4){
            url = "${ctx}/survey/case/list?menuCode=assign-list";
            title  = "平台待分派";
        }else if(type == 5){
            url = "${ctx}/survey/case/list?menuCode=survey-list";
            title  = "终审";
        }else if(type == 6){
            url = "${ctx}/survey/case/sic/list?menuCode=dcy-list&surveyState=0";
            title  = "待接收";
        }else if(type == 7){
            url = "${ctx}/survey/case/sic/list?menuCode=dcy-list";
            title  = "调查中";
        }else if(type == 8){
            url = "${ctx}/survey/case/listBackCase?menuCode=back-case-list";
            title  = "待协助处理";
        }else if(type == 9){
            url = "${ctx}/survey/case/list?menuCode=survey-list";
            title  = "报告待上传";
        }else if(type == 10){
            url = "${ctx}/survey/case/listAssign?menuCode=assign-org-list";
            title  = "机构待分派";
        }else if(type == 11){
            url = "${ctx}/survey/case/sic/list?menuCode=report-list";
            title  = "机构初审";
        }else if(type == 12){
            url = "${ctx}/survey/case/listAssign?menuCode=org-review-list";
            title  = "初审中";
        }else if(type == 13){
            url = "${ctx}/survey/case/sic/list?menuCode=refuse-list";
            title  = "拒接审核中";
        }else if(type == 14){
            url = "${ctx}/survey/case/list?menuCode=dispatch-opr-list";
            title  = "调度审核中";
        }
        parent.addTab(title,url);
    }

</script>
</html>