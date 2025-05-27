<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>保险公司列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
    </style>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            text-align: center;
            border-left: 1px solid #ddd;
        }

    </style>
</head>
<body>
<div class="main administrator">
<div class="main-top">
    <h3>保险公司列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
</div><!--main-top-->
<div class="panel panel-info">
<div class="panel-heading">
    <div class="pin">
        <form class="form-inline" role="form" action="${ctx}/info/publishs/selectCompany?id=${publishsId}&choose=${choose}" method="post">
            <div class="form-group">
                <label class="title">公司名称:</label>
                <input name="safeName" type="text"  value="${safeName}" class="form-control">
            </div>
            <div class="form-group">
                <label class="title">联系人:</label>
                <input name="safeUser" type="text"  value="${safeUser}" class="form-control">
            </div>
            <div class="form-group">
                <label class="title">联系电话:</label>
                <input name="safeTel" type="text"  value="${safeTel}" class="form-control">
            </div>
            <div class="btn-group">
                <button id="batchOperateBtnTo" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
            </div>
        </form>
    </div>
</div>
<div class="table-content">
    <form id="editForm" role="form" action="${ctx}/info/publishs/selectCompanyOK" method="post">
        <input type="hidden" value="${publishsId}" name="publishsId" />
        <input type="hidden" value="${choose}" name="choose">
        <table class="table table-striped">
            <thead>
            <tr>
                <th th width="80"><input type="checkbox" id="all" >全选</th>
                <th style="min-width:100px">公司名称</th>
                <th style="min-width:100px">联系人</th>
                <th style="min-width:100px">联系电话</th>
                <%--<th style="min-width:100px">创建时间</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><input type="checkbox" value="${item.id}" name="safeCompanys">
                        <input type="hidden" value="${item.safeName}" id="safeName_${item.id}">
                    </td>
                    <td>${item.safeName}</td>
                    <td>${item.safeUser}</td>
                    <td>${item.safeTel}</td>
                    <%--<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>--%>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10" style="text-align: right;margin-right: 20px;">
                    <c:if test="${choose == 'add'}">
                        <input type="button" class="btn btn-default" onclick="companyOK()" value="确认选择" />
                    </c:if>
                    <c:if test="${choose == 'upd'}">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">确定选择</button>
                    </c:if>
                    <c:if test="${choose == 'del'}">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">确定移除</button>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</div><!--panel-info-->
<div class="main-bottom">
    <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
        <jsp:param name="paginationObjectName" value="apiRsp" />
        <jsp:param name="pageNoName" value="" />
        <jsp:param name="requestUrl" value="${ctx}/info/publishs/selectCompany?id=${publishsId}&choose=${choose}&safeName=${safeName}&safeTel=${safeTel}&safeUser=${safeUser}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>
</div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='safeCompanys']").prop("checked",this.checked);
        })
    });

    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    function companyOK(){
        var safeCompanys = [];
        var safeCompanyNames = [];
        $("input:checkbox[name='safeCompanys']:checked").each(function() { // 遍历name=safeCompanys的多选框
            safeCompanys.push($(this).val());
            safeCompanyNames.push($("#safeName_" + $(this).val()).val())
        });
        parent.$("#safeCompanys").val(safeCompanys);
        parent.$("#safeCompanyNames").val(safeCompanyNames);
        closeDialog();
    }
</script>
</body>
</html>
