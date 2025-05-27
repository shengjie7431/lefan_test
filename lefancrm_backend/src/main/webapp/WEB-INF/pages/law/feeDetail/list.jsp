<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <title></title>
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
        <h3>退费列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
    <div class="panel-heading">
        <div class="pin">
            <form class="form-inline" role="form" action="${ctx}/law/feeDetail/list" method="post">
                <input type="hidden" name="id" value="${id}">
                <div class="form-group">
                    <label class="title">案件编号:</label>
                    <input name="caseNo" type="text" value="${caseNo}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">委托人姓名:</label>
                    <input name="entrustUserName" type="text" value="${entrustUserName}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">委托人电话:</label>
                    <input name="entrustUserTel" type="text" value="${entrustUserTel}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">退费状态:</label>
                    <select name="type"  class="form-control">
                        <option value=""  <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>
                        <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >正常退费</option>
                        <option value="3" <c:if test="${type == '3'}">selected="selected" </c:if> >退案退费</option>
                    </select>
                </div>
                <div class="btn-group">
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                </div>
            </form>
        </div>
    </div>
    <div class="table-content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="min-width:100px">案件编号</th>
                <th style="min-width:100px">金额</th>
                <th style="min-width:100px">委托人姓名</th>
                <th style="min-width:100px">委托人电话</th>
                <th style="min-width:100px">退费状态</th>
                <th style="min-width:100px">是否确认退费</th>
                <th style="min-width:100px">退费时间</th>
                <th style="min-width:100px">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.money}</td>
                    <td>${item.entrustUserName}</td>
                    <td>${item.entrustUserTel}</td>
                    <td>
                        <c:if test="${item.type == 1}">正常退费</c:if>
                        <c:if test="${item.type == 3}">退案退费</c:if>
                    </td>
                    <td>
                        <c:if test="${item.state == 1}">未确认</c:if>
                        <c:if test="${item.state == 2}">已确认</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.retreatTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <a href="javascript:view('${item.id}');">查看</a>
                    </td>
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
        <jsp:param name="requestUrl" value="${ctx}/law/feeDetail/list?caseNo=${caseNo}&entrustUserName=${entrustUserName}&entrustUserTel=${entrustUserTel}&type=${type}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>
</div><!--main-bottom-->

</div><!--main end-->
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script>
    function view(id){
        var url = "${ctx}/law//feeDetail/view?id="+id;
        openDialog({
            frame:true,
            title:"退费详情",
            height:750,
            width:1200,
            url : url,
            load:true
        });
    }
</script>
</body>
</html>
