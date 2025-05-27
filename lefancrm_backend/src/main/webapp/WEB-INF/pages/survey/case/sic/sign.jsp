<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    .div_remark{
        /*position: fixed;*/
        bottom: 0px;
        width: 100%;
        height: auto;
        background-color: #fff;
        box-shadow: 0-2px 3px #eee;
        margin-top: 20px;
        text-align: center;
    }
</style>

<body>
<%--<div class="container">--%>
    <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post" style="padding: 10px;">
        <%--  --%>
            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                <thead>
                <th  width="150">区域</th>
                <th width="100">任务类型</th>
                <th width="100">任务子类</th>
                <th width="100">方向名称</th>
                <th>方向内容</th>
                <th width="100">操作</th>
                </thead>
                <tbody>
                <c:forEach items="${dto.surveyCaseDirections}" var="item">
                    <tr>
                        <td>${item.areaName}</td>
                        <td><span style="background-color: ${item.taskColor};color: #fff">&nbsp;&nbsp;${item.taskName}&nbsp;&nbsp;</span></td>
                        <td><span style="background-color: ${item.newColor};color: #fff">&nbsp;&nbsp;${item.newName}&nbsp;&nbsp;</span></td>
                        <td>${item.directionName}</td>
                        <td>${item.directionText}</td>
                        <td>
                            <a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles')">查看附件(${item.surveyCaseDirectionFiles.size()})</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="div_remark">
                <input type="hidden" value="${menuCode}" name="menuCode">
                <input type="hidden" value="${btnCode}" name="btnCode">
                <input type="hidden" value="${oprType}" name="oprType">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:closeDialog();">关闭</button>
                <c:if test="${oprType == 'org-opr'}">
                    <button type="button" onclick="opr(${dto.id},'${btnCode}','org-back',false)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon"></span>退回</button>
                    <button type="button" onclick="opr(${dto.id},'${btnCode}','org-ok',true)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon"></span>确认阳性并提交平台审核</button>
                </c:if>
                <c:if test="${oprType == 'lefan-opr'}">
                    <button type="button" onclick="opr(${dto.id},'${btnCode}','lefan-back',false)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon"></span>退回</button>
                    <button type="button" onclick="opr(${dto.id},'${btnCode}','lefan-ok',true)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon"></span>确认阳性</button>
                </c:if>
            </div>
    </form>
<%--</div>--%>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var opr = function(id,btnCode,signType,ajax){
        if (ajax){
            var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode,"signType":signType};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    window.parent.parent.reload();
                })
            }
        } else {
            var title = null,url = null;
            height = 200;width = 700;
            title = '退回原因';
            url = "${ctx}/survey/case/sic/back?id=" + id + "&btnCode=" + btnCode + "&signType=" + signType;
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url,
                load:true
            });
        }
    }

    var directionFiles = function(id,btnCode){
        var height = 500,width = 800;
        var title = "附件详情";
        var url = "${ctx}/survey/case/sic/directionFiles?directionId=" + id;
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