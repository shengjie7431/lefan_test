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
<body>
<div class="main">
    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>姓名</td>
                    <td>${surveyInvestigator.realName}</td>
                    <td>联系方式</td>
                    <td>${surveyInvestigator.tel}</td>
                    <td>所属机构</td>
                    <td>${surveyInvestigator.orgName}</td>
                </tr>
                <tr>
                    <td>总分值</td>
                    <td colspan="5">${scores}</td>
                </tr>
                </tbody>
            </table>

            <br>
            <div class="table-title">案件列表</div>
            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                <thead>
                <th width="25%">案件编号</th>
                <th width="20%">被调查人</th>
                <th width="45%">保险公司</th>
                <%--<th width="8%">区域</th>--%>
                <%--<th width="10%">任务类型</th>--%>
                <%--<th width="10%">任务子类</th>--%>
                <%--<th width="8%">方向名称</th>--%>
                <%--<th width="32%">方向内容</th>--%>
                <th width="10%">分值</th>
                <%--<th width="5%">附件</th>--%>
                </thead>
                <tbody>
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>${item.surveyNo}</td>
                        <td>${item.surveyPerson}</td>
                        <td>${item.entrustOrgName}</td>
                        <%--<td>${item.areaName}</td>--%>
                        <%--<td>${item.taskName}</td>--%>
                        <%--<td>${item.newName}</td>--%>
                        <%--<td>${item.directionName}</td>--%>
                        <%--<td>${item.directionText}</td>--%>
                        <td>${item.score}</td>
                        <!--<td><a href="javascript:void(0);" onclick="directionFiles(${item.directionId},'directionFiles')">详情</a></td>-->
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
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    var directionFiles = function(id,btnCode){
        var height = 500,width = 800;
        var title = "附件详情";
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
