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
        <div class="title">
            <button class="butList defuelt" onclick="edit(${infoSafeCompany.id})">修改</button>
            <button class="butList defuelt" onclick="del(${infoSafeCompany.id})">删除</button>
        </div>
        <div class="main-boy">
            <div>
                <div class="table-title">基础信息</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>公司名称</td>
                        <td>${infoSafeCompany.safeName}</td>
                        <td>联系人</td>
                        <td>${infoSafeCompany.safeUser}</td>
                        <td>联系方式</td>
                        <td>${infoSafeCompany.safeTel}</td>
                    </tr>
                    <tr>
                        <td>创建人</td>
                        <td>${infoSafeCompany.createByName}</td>
                        <td>创建时间</td>
                        <td colspan="3"><fmt:formatDate value="${infoSafeCompany.createTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td>更新人</td>
                        <td>${infoSafeCompany.updateByName}</td>
                        <td>更新时间</td>
                        <td colspan="3"><fmt:formatDate value="${infoSafeCompany.updateTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
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

    /**
     * 删除
     */
    function del(id){
        ajaxSubmit("${ctx}/infoSafeCompany/delete",{"id":id},function(){reloadParent();},"删除成功！","确认删除？","删除失败！");
    }

    /**
     * 修改
     */
    var edit = function(id) {
        openDialog({
            frame:true,
            title:"查看详情",
            height:600,
            width:800,
            url:"${ctx}/infoSafeCompany/edit?id="+id,
            load:true
        });
    }
</script>
</body>
</html>
