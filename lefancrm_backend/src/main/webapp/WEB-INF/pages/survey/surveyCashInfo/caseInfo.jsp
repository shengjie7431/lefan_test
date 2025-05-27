<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">

    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .table-title{
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }
    table.spec-info tr td:nth-of-type(2n + 1) {
        width: 13%;
    }
    table.spec-info tr td:nth-of-type(2n) {
        width: 20%;
        word-break: break-all;
    }
</style>

<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 250px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

    .input-2 {
        width: 80px;
        border: none;
        border-bottom: 1px solid #555;
        text-align: center;
    }

    .div_source_ok{
        display: inline-block;
     }
</style>
<body>
<div class="main">
    <input type="hidden" id="menuCode" value="${menuCode}" />
    <%--<div class="title">--%>
        <%--<button class="butList active" onclick="operate(${dto.id},'files',false)">查看材料</button>--%>
        <%--<button class="butList active" onclick="operate(${dto.id},'follow',false)">查看进度</button>--%>
    <%--</div>--%>
    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>调查编号</td>
                    <td>${dto.surveyRiskCase.surveyNo}</td>
                    <td>被调查人</td>
                    <td>
                        ${dto.surveyRiskCase.surveyPerson}</td>
                    <td>联系号码</td>
                    <td>${dto.surveyRiskCase.surveryPersonTel}</td>
                </tr>
                <tr>
                    <td>领域</td>
                    <td>${dto.surveyRiskCaseInfo.surveyBusName}</td>
                    <td>任务类型</td>
                    <td colspan="3">
                        <c:forEach items="${dto.surveyTaskTypes}" var="item">
                            ${item.taskName}&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>业务类型</td>
                    <td>${dto.surveyRiskCaseInfo.servicesName}</td>
                    <td>案件截至日期</td>
                    <td>
                        <fmt:formatDate value="${dto.surveyRiskCaseInfo.endTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>状态</td>
                    <td>${dto.surveyRiskCaseInfo.surveyStateName}</td>
                </tr>
                <tr>
                    <td>委托人名称</td>
                    <td>${dto.surveyRiskCase.entrustUserName}</td>
                    <td>委托人机构名称</td>
                    <td colspan="3">${dto.surveyRiskCase.entrustOrgName}</td>
                </tr>
                <tr>
                    <td>保险合同编号</td>
                    <td>${dto.surveyRiskCase.policyNo}</td>
                    <td>理赔编号</td>
                    <td>${dto.surveyRiskCase.claimsNo}</td>
                    <td>理赔申请金额</td>
                    <td>${dto.surveyRiskCase.claimsMoney}</td>
                </tr>
                <tr>
                    <td>证件类型</td>
                    <td>
                        <c:if test="${dto.surveyRiskCase.idType ==1}">身份证</c:if>
                        <c:if test="${dto.surveyRiskCase.idType ==2}">驾驶证</c:if>
                    </td>
                    <td>证件号码</td>
                    <td colspan="3">${dto.surveyRiskCase.idNumber}</td>
                </tr>
                <tr>
                    <td>是否经典案例</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.isClassic== 0}">否</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.isClassic== 1}">是</c:if>
                    </td>
                    <td>是否阳性</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.isSun == 0}">否</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.isSun == 1}">是</c:if>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>是否寄送</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.isSendReport== 0}"><span style="color: red;">未寄送</span></c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.isSendReport== 1}">已寄送</c:if>
                    </td>
                    <td>基本费是否结算</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.price1IsCalc == 0}"><span style="color: red;">未结算</span></c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.price1IsCalc == 1}">已结算</c:if>
                    </td>
                    <td>减损奖励是否结算</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.price2IsCalc == 0}"><span style="color: red;">未结算</span></c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.price2IsCalc == 1}">已结算</c:if>
                    </td>
                </tr>
                <tr>
                    <td>调查基本信息</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyInfo}</td>
                </tr>
                <tr>
                    <td>调查事项</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyItem}</td>
                </tr>
                </tbody>
            </table>

            </br>
            <div class="table-title">调查方向列表</div>
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                    <th>机构</th>
                    <th>区域</th>
                    <th>任务类型</th>
                    <th>任务子类</th>
                    <th>方向名称</th>
                    <th>方向内容</th>
                    <th>价格</th>
                    <th>附件</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dto.surveyCaseDirections}" var="item">
                        <tr>
                            <td>${item.surveyInvestigatorCase.surveyFranchisee.name}</td>
                            <td>${item.areaName}</td>
                            <td>${item.taskName}</td>
                            <td>${item.newName}</td>
                            <td>${item.directionName}</td>
                            <td>${item.directionText}</td>
                            <td>
                                ${item.surveyMoney}
                            </td>
                            <td><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles')">详情</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script>
    <%--var operate = function(id,btnCode,ajax){--%>
        <%--var height = 400,width = 900;--%>
        <%--if(ajax){--%>
            <%--var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode};--%>
            <%--if(confirm('是否确认？')){--%>
                <%--ajaxSubmit(url,param,function(v,e,p){--%>
                    <%--alert(e.data.msg);--%>
                    <%--location.reload();--%>
                <%--})--%>
            <%--}--%>
        <%--}else{--%>
            <%--var title = null,url = null;--%>
            <%--if(btnCode == 'follow'){--%>
                <%--title = "添加跟踪";--%>
                <%--url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";--%>
            <%--}else if(btnCode == 'files'){--%>
                <%--title = "查看资料";--%>
                <%--height = 600;--%>
                <%--url = "${ctx}/survey/case/fileMidView?surveyInfoId=" + id + "&surveyId=${dto.surveyRiskCaseInfo.surveyId}&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";--%>
            <%--}--%>
            <%--openDialog({--%>
                <%--frame:true,--%>
                <%--title:title,--%>
                <%--height:height,--%>
                <%--width:width,--%>
                <%--url:url--%>
            <%--});--%>
        <%--}--%>
    <%--}--%>
    var directionFiles = function(id,btnCode){
        var height = 500,width = 800;
        title = "附件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/directionFiles?directionId=" + id
        });
    }
</script>
</body>
</html>
