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
        <form class="form-inline" role="form" action="${ctx}/infoSafeCompany/list" method="post">
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
                <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                <button onclick="add()" type="button" class="btn btn-default">添加公司</button>
            </div>
        </form>
    </div>
</div>
<div class="table-content">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="min-width:100px">公司名称</th>
            <th style="min-width:100px">联系人</th>
            <th style="min-width:100px">联系电话</th>
            <th style="min-width:100px">创建时间</th>
            <th style="min-width:100px">操作</th>

        </tr>
        </thead>
        <tbody class="class-list">
        <c:forEach items="${apiRsp.results}" var="item">
            <tr>
                <td>${item.safeName}</td>
                <td>${item.safeUser}</td>
                <td>${item.safeTel}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                <td><a href="javascript:info('${item.id}');"> 查看</a>
                    <a href="javascript:companyUser('${item.id}');"> 查看用户</a>
                    <a href="javascript:selectAllUser('${item.id}');"> 分配用户</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div><!--panel-info-->
<div class="main-bottom">
    <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
        <jsp:param name="paginationObjectName" value="apiRsp" />
        <jsp:param name="pageNoName" value="" />
        <jsp:param name="requestUrl" value="${ctx}/infoSafeCompany/list?safeName=${safeName}&safeTel=${safeTel}&safeUser=${safeUser}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>
</div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>

    var add = function(){
        openDialog({
            frame:true,
            title:"添加保险公司",
            height:700,
            width:900,
            url:"${ctx}/infoSafeCompany/edit"
        });
    }

    /**
     * 提交申请
     */
    function billingApplyDelete(id){
        ajaxSubmit("${ctx}/billingApply/billingApplyDelete",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
    }

    /**
     * 详情
     */
    var info = function(id) {
        openDialog({
            frame:true,
            title:"查看详情",
            height:800,
            width:1000,
            url:"${ctx}/infoSafeCompany/info?id="+id,
            load:true
        });
    }

    /**
     * 分配用户
     */
    var selectAllUser = function(id){
        openDialog({
            frame:true,
            title:"分配用户",
            height:750,
            width:1000,
            url:"${ctx}/infoSafeCompany/selectAllUser?safeCompanyId="+id
        });
    }

    /**
     * 查看用户
     */
    var companyUser = function(id){
        openDialog({
            frame:true,
            title:"查看用户",
            height:750,
            width:1000,
            url:"${ctx}/infoSafeCompany/companyUser?safeCompanyId="+id
        });
    }

</script>
</body>
</html>
