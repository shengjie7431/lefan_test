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
            <div class="keys">
                <div class="key color1">
                    <div class="key-num">${numberDto.caseNumber}</div>
                    <div class="key-desc">案件总数</div>
                    <div class="key-img bg1"></div>
                </div>
                <div class="key color2">
                    <div class="key-num">${numberDto.caseLoan}</div>
                    <div class="key-desc">乐赔宝</div>
                    <div class="key-img bg2"></div>
                </div>
                <div class="key color3">
                    <div class="key-num">${numberDto.caseAgent}</div>
                    <div class="key-desc">索赔通</div>
                    <div class="key-img bg3"></div>
                </div>
            </div>
            <hr/>
            <div style="text-align: right">
                <a class="title-a" onclick="openlist(100)" href="javascript:void(0);">洽谈阶段(<span class="title-span">${numberDto.oprNumber}</span>)</a>
                <a class="title-a" onclick="openlist(101)" href="javascript:void(0);">评估阶段(<span class="title-span">${numberDto.assNumber}</span>)</a>
                <a class="title-a" onclick="openlist(102)" href="javascript:void(0);">索赔阶段(<span class="title-span">${numberDto.claimNumber}</span>)</a>
                <a class="title-a" onclick="openlist(103)" href="javascript:void(0);">诉讼阶段(<span class="title-span">${numberDto.legalNumber}</span>)</a>
                <a class="title-a" onclick="openlist(104)" href="javascript:void(0);">已结案(<span class="title-span">${numberDto.closeNumber}</span>)</a>
            </div>
        <div class="content">
            <c:if test="${choose == 1}">
                <c:if test="${caseUserRole.assessor}">
                    <div class="bar" onclick="openlist(1)">
                        <div class="bar-in">
                            <div class="bar-title">评估待接收</div>
                            <div class="bar-num">${numberDto.pgdjs}</div>
                        </div>
                    </div>
                    <div class="bar"  onclick="openlist(2)">
                        <div class="bar-in">
                            <div class="bar-title">待提交报告</div>
                            <div class="bar-num">${numberDto.pgdtjbg}</div>
                        </div>
                    </div>

                    <div class="bar" onclick="openlist(3)">
                        <div class="bar-in">
                            <div class="bar-title">待提交贷款</div>
                            <div class="bar-num">${numberDto.pgdtjdk}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(4)">
                        <div class="bar-in">
                            <div class="bar-title">待投保</div>
                            <div class="bar-num">${numberDto.pgdtb}</div>
                        </div>
                    </div>
                    <%--<div class="bar" onclick="openlist(5)">--%>
                        <%--<div class="bar-in">--%>
                            <%--<div class="bar-title">待保险费支付</div>--%>
                            <%--<div class="bar-num">${numberDto.pgdcwzf}</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="bar" onclick="openlist(6)">
                        <div class="bar-in">
                            <div class="bar-title">放款待确认</div>
                            <div class="bar-num">${numberDto.pgdqr}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(7)">
                        <div class="bar-in">
                            <div class="bar-title">待确认服务费</div>
                            <div class="bar-num">${numberDto.pgqrfwf}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${caseUserRole.riskSuper}">
                    <div class="bar" onclick="openlist(8)">
                        <div class="bar-in">
                            <div class="bar-title">待测算</div>
                            <div class="bar-num">${numberDto.pgdcs}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${caseUserRole.assessorManager}">
                    <div class="bar" onclick="openlist(9)">
                        <div class="bar-in">
                            <div class="bar-title">待预估</div>
                            <div class="bar-num">${numberDto.pgdyg}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${caseUserRole.assessorManager || caseUserRole.riskSuper}">
                    <div class="bar" onclick="openlist(10)">
                        <div class="bar-in">
                            <div class="bar-title">评估审核中</div>
                            <div class="bar-num">${numberDto.pgshz}</div>
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${choose == 2}">
                <c:if test="${caseUserRole.claims}">
                    <div class="bar" onclick="openlist(20)">
                        <div class="bar-in">
                            <div class="bar-title">索赔待接收</div>
                            <div class="bar-num">${numberDto.spdjs}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(21)">
                        <div class="bar-in">
                            <div class="bar-title">索赔材料收集中</div>
                            <div class="bar-num">${numberDto.spclsjz}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(22)">
                        <div class="bar-in">
                            <div class="bar-title">待鉴定</div>
                            <div class="bar-num">${numberDto.spdjd}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(28)">
                        <div class="bar-in">
                            <div class="bar-title">待制作预案报告</div>
                            <div class="bar-num">${numberDto.spdzzyabg}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(23)">
                        <div class="bar-in">
                            <div class="bar-title">调解中</div>
                            <div class="bar-num">${numberDto.sptjz}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(24)">
                        <div class="bar-in">
                            <div class="bar-title">待提交结案报告</div>
                            <div class="bar-num">${numberDto.spdtjja}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(25)">
                        <div class="bar-in">
                            <div class="bar-title">待收取服务费</div>
                            <div class="bar-num">${numberDto.spdhk}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(26)">
                        <div class="bar-in">
                            <div class="bar-title">待结案</div>
                            <div class="bar-num">${numberDto.spdja}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${caseUserRole.claimsManager}">
                    <div class="bar" onclick="openlist(27)">
                        <div class="bar-in">
                            <div class="bar-title">索赔审核中</div>
                            <div class="bar-num">${numberDto.spshz}</div>
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${choose == 3}">
                <c:if test="${caseUserRole.legal}">
                    <div class="bar" onclick="openlist(30)">
                        <div class="bar-in">
                            <div class="bar-title">诉讼待接收</div>
                            <div class="bar-num">${numberDto.ssdjs}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(31)">
                        <div class="bar-in">
                            <div class="bar-title">材料收集中</div>
                            <div class="bar-num">${numberDto.ssclsjz}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(32)">
                        <div class="bar-in">
                            <div class="bar-title">待制作预案报告</div>
                            <div class="bar-num">${numberDto.ssdzzyabg}</div>
                        </div>
                    </div>

                    <div class="bar" onclick="openlist(33)">
                        <div class="bar-in">
                            <div class="bar-title">待立案</div>
                            <div class="bar-num">${numberDto.ssdla}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(34)">
                        <div class="bar-in">
                            <div class="bar-title">待提交结案报告</div>
                            <div class="bar-num">${numberDto.ssdtjjabg}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(35)">
                        <div class="bar-in">
                            <div class="bar-title">待收取服务费</div>
                            <div class="bar-num">${numberDto.ssdhk}</div>
                        </div>
                    </div>
                    <div class="bar" onclick="openlist(36)">
                        <div class="bar-in">
                            <div class="bar-title">待结案</div>
                            <div class="bar-num">${numberDto.ssdja}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${caseUserRole.legalManager}">
                    <div class="bar" onclick="openlist(37)">
                        <div class="bar-in">
                            <div class="bar-title">诉讼审核中</div>
                            <div class="bar-num">${numberDto.ssshz}</div>
                        </div>
                    </div>
                </c:if>
            </c:if>
        </div>

    </div>
</body>
<script>
    function openlist(type){
        var url = "";
        var title = "";
        if(type == 1){
            url = "${ctx}/case/center/list?menuType=5";
            title  = "评估待接收";
        }else if(type == 2){
            url = "${ctx}/case/center/list?menuType=10&pgChoose=1";
            title  = "待提交报告";
        }else if(type == 3){
            url = "${ctx}/case/center/list?menuType=10&pgChoose=2";
            title  = "待提交贷款";
        }else if(type == 4){
            url = "${ctx}/case/center/list?menuType=20&pgChoose=3";
            title  = "待投保";
        }else if(type == 5){
            url = "${ctx}/case/center/list?menuType=21&pgChoose=4";
            title  = "待保险费支付";
        }else if(type == 6){
            url = "${ctx}/case/center/list?menuType=25&pgChoose=5";
            title  = "放款待确认";
        }else if(type == 7){
            url = "${ctx}/case/center/list?menuType=27&pgChoose=6";
            title  = "待确认服务费";
        }else if(type == 8){
            url = "${ctx}/case/risk/queryCaseNegotiateState?negotiateState=0";
            title  = "待测算";
        }else if(type == 9){
            url = "${ctx}/invalidism/list?state=1";
            title  = "待预估";
        }else if(type == 10){
            if("${caseUserRole.assessorManager}"){
                url = "${ctx}/case/center/list?menuType=15&listState=2";
            }
            if("${caseUserRole.riskSuper}"){
                url = "${ctx}/case/center/list?menuType=15&listState=4";
            }
            if("${caseUserRole.assessorManager && caseUserRole.riskSuper}"){
                url = "${ctx}/case/center/list?menuType=15";
            }
            title = "评估审核中";
        }else if(type == 20){
            url = "${ctx}/case/center/list?menuType=55";
            title = "索赔待接收";
        }else if(type == 21){
            url = "${ctx}/case/center/list?menuType=30&spChoose=1";
            title = "索赔材料收集中";
        }else if(type == 22){
            url = "${ctx}/case/center/list?menuType=30&spChoose=2";
            title = "待鉴定";
        }else if(type == 28){
            url = "${ctx}/case/center/list?menuType=30&spChoose=7";
            title = "待制作预案报告";
        }else if(type == 23){
            url = "${ctx}/case/center/list?menuType=30&spChoose=3";
            title = "调解中";
        }else if(type == 24){
            url = "${ctx}/case/center/list?menuType=30&spChoose=4";
            title = "待提交结案报告";
        }else if(type == 25){
            url = "${ctx}/case/center/list?menuType=30&spChoose=5";
            title = "待还款";
        }else if(type == 26){
            url = "${ctx}/case/center/list?menuType=30&spChoose=6";
            title = "待结案";
        }else if(type == 27){
            url = "${ctx}/case/center/list?menuType=35";
            title = "索赔审核中";
        }else if(type == 30){
            url = "${ctx}/case/center/list?menuType=555";
            title = "诉讼待接收";
        }else if(type == 31){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=1";
            title = "材料收集中";
        }else if(type == 32){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=2";
            title = "待制作预案报告";
        }else if(type == 33){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=3";
            title = "待立案";
        }else if(type == 34){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=4";
            title = "待提交结案报告";
        }else if(type == 35){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=5";
            title = "待还款";
        }else if(type == 36){
            url = "${ctx}/case/center/list?menuType=45&ssChoose=6";
            title = "待结案";
        }else if(type == 37){
            url = "${ctx}/case/center/list?menuType=50";
            title = "诉讼审核中";
        }else if(type == 100){
            url = "${ctx}/case/center/list?menuType=1&gradationState=1";
            title = "洽谈列表";
        }else if(type == 101){
            url = "${ctx}/case/center/list?menuType=1&gradationState=2";
            title = "评估列表";
        }else if(type == 102){
            url = "${ctx}/case/center/list?menuType=1&gradationState=3";
            title = "索赔列表";
        }else if(type == 103){
            url = "${ctx}/case/center/list?menuType=1&gradationState=6";
            title = "诉讼列表";
        }else if(type == 104){
            url = "${ctx}/case/center/list?menuType=1&gradationState=4";
            title = "已结案列表";
        }else{
            return;
        }
        parent.addTab(title,url,true);
    }

</script>
</html>