<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
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

</style>
<body>
<div class="main">
    <input type="hidden" id="menuCode" value="${menuCode}" />
    <div class="title">
        <c:if test="${dto.surveyState == 1}">
            <c:if test="${dto.creportState == 0}">
                <button class="butList active" onclick="operate(${dto.id},'upload',false)">上传报告</button>
            </c:if>
            <c:if test="${dto.creportState == 1}">
                <button class="butList active" onclick="operate(${dto.id},'upload',false)">更新报告</button>
            </c:if>
            <button class="butList active" onclick="operate(${dto.id},'report-commit',true)">提交审核</button>
            <button class="butList active" onclick="operate(${dto.id},'direction',false)">增加调查方向</button>
        </c:if>
    </div>
    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>调查编号</td>
                    <td>${dto.surveyRiskCase.surveyNo}</td>
                    <td>被调查人</td>
                    <td>${dto.surveyRiskCase.surveyPerson}</td>
                    <td>联系号码</td>
                    <td>${dto.surveyRiskCase.surveryPersonTel}</td>
                </tr>
                <tr>
                    <td>领域</td>
                    <td>${dto.surveyRiskCaseInfo.surveyBusName}</td>
                    <td>任务类型</td>
                    <td colspan="3">
                        <c:forEach items="${dto.tasks}" var="item">
                            ${item.taskName}&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>业务类型</td>
                    <td>${dto.surveyRiskCaseInfo.servicesName}</td>
                    <td>截至日期</td>
                    <td><fmt:formatDate value="${dto.surveyEndTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>状态</td>
                    <td><span style="color: red;">${dto.surveyStateName}</span></td>
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
                    <td>任务描述</td>
                    <td colspan="6">${dto.surveyAssignOrg.orgTaskRemark}</td>
                </tr>
                <tr>
                    <td>调查基本信息</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyInfo}</td>
                </tr>
                <tr>
                    <td>调查事项</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyItem}</td>
                </tr>
                <tr>
                    <td>审核原因</td>
                    <td colspan="5">
                        ${dto.surveyRemark}
                    </td>
                </tr>
                <c:if test="${false}">
                    <tr>
                        <td>结算方式</td>
                        <td>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 1}">一口价</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 2}">基本费+减损</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 3}">任务</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 4}">任务+减损</c:if>
                        </td>
                        <td>结算详情</td>
                        <td colspan="4">
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 1}">(${dto.surveyTaskMoney})元</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 2}">基本费(${dto.surveyTaskMoney})，减损描述(${dto.surveryReLoossesRemark})</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 3}">任务(${dto.surveyCaseDirections.size()})个</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 4}">任务(${dto.surveyCaseDirections.size()})个，减损描述(${dto.surveryReLoossesRemark})</c:if>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <div class="table-title">任务详情</div>
            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                <thead>
                <th>调查员名称</th>
                <th>机构</th>
                <th>已分配任务类型</th>
                <th>状态</th>
                <th>报告信息</th>
                </thead>
                <tbody>
                <c:forEach items="${dto.surveyInvestigatorCases}" var="item">
                    <tr>
                        <td>${item.surveyUserName}</td>
                        <td>${item.surveyFranchisee.name}</td>
                        <td>
                            <c:forEach items="${item.tasks}" var="task">
                                ${task.taskName}&nbsp;
                            </c:forEach>
                        </td>
                        <td>
                                ${item.surveyStateName}
                        </td>
                        <td>
                            <%--<c:if test="${item.creportState == 0}"><span style="color: red;">未上传</span></c:if>--%>
                            <%--<c:if test="${item.creportState == 1 || item.creportState == 2}">--%>
                                <%--<a onclick="onlinePreview('${item.commonFile.filePath}')" target="_blank">预览/下载</a>--%>
                            <%--</c:if>--%>
                            <c:if test="${item.creportId == ''}">未上传</c:if>
                            <c:if test="${item.creportId != ''}">
                                <a onclick="onlinePreview('${item.commonFile.filePath}')" target="_blank">预览/下载</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${dto.surveyUserType == 1}">
                <div class="table-title">调查方向列表</div>
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                    <th>机构</th>
                    <th>区域</th>
                    <th>任务类型</th>
                    <th>任务子类</th>
                    <th>方向名称</th>
                    <th>方向内容</th>
                    <th>附件</th>
                    <th>操作</th>
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
                            <td><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles')">详情</a></td>
                            <td>
                                <c:if test="${dto.id == item.surveyInvestigatorCaseId && dto.surveyState == 1}">
                                    <a href="javascript:void(0);" onclick="delDirection(${dto.id},${item.id},'deldirection',true)">删除</a>
                                    <a href="javascript:void(0);" onclick="updDirection(${dto.id},${item.id},'updDirection',false)">修改</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
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
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    var operate = function(id,btnCode,ajax){
        var height = 400,width = 900;
        if(ajax){
            if(btnCode == 'report-commit'){
                <%--if("${dto.creportState}" == 0 && "${dto.surveyFranchisee.isSpecial}" == 0){--%>
                    <%--alert("报告未上传");return;--%>
                <%--}--%>
            }
            var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == 'follow'){
                title = "添加跟踪";
                url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";
            }else if(btnCode == 'files'){

            }else if(btnCode == 'taskDetails'){
                title = "任务详情"
                url = "${ctx}/survey/case/info?id=${dto.surveyRiskCaseInfo.id}&menuCode=survey-list";
            }else{
                width = 800;
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 'upload' || btnCode == 'primary'){
                    url += "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}"
                }
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

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

    var delDirection = function(id,directionId,btnCode,ajax){
        var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"directionId" : directionId,"btnCode":btnCode};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                alert(e.data.msg);
                location.reload();
            })
        }
    }

    var updDirection = function(id,directionId,btnCode,ajax){
        var height = 400,width = 800;
        title = "操作";
        url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId;
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }
</script>
</body>
</html>
