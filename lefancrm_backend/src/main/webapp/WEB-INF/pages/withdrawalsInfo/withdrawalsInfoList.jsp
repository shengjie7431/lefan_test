<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>提现审核列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>提现审核列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/withdrawalsInfo/withdrawalsInfoList?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="mtype" value="${mtype}">
                    <div class="form-group">
                        机构名称:
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="item1">
                                <option <c:if test="${orgId == item1.id}">selected="selected" </c:if> value="${item1.id}" >${item1.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        提现单号:<input name="widraCode" type="text" value="${widraCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        提现状态:
                        <select name="state"  class="form-control">
                            <c:if test="${mtype == 1}">
                                <option value="1">待提现</option>
                            </c:if>
                            <c:if test="${mtype == 2}">
                                <option value="">全部</option>
                                <option value="2">提现中</option>
                                <option value="3">提现成功</option>
                            </c:if>
                            <c:if test="${mtype == 11}">
                                <option value="">全部</option>
                                <option value="1">待提现</option>
                                <option value="2">提现中</option>
                                <option value="3">提现成功</option>
                            </c:if>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">提现单号</th>
                <th width="100">用户名</th>
                <th width="150">提现金额</th>
                <th width="150">提现状态</th>
                <th width="150">微信号</th>
                <th width="100">电话</th>
                <th width="100">机构名称</th>
                <th width="150">交易备注</th>
                <th width="150">提现时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.widraCode}</td>
                    <td>${item.userName}</td>
                    <td>${item.money}</td>
                    <td>
                        <c:if test="${item.state == 1}"> 待提现</c:if>
                        <c:if test="${item.state == 2}"> 提现中</c:if>
                        <c:if test="${item.state == 3}"> 提现完成</c:if>
                    </td>
                    <td>${item.wechatId}</td>
                    <td>${item.userTel}</td>
                    <td>${item.orgName}</td>
                    <td>${item.tradeDesc}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>

                    <td>
                        <c:if test="${mtype == 1}">
                            <c:if test="${item.state == 1}">
                                <a href="javascript:editWithdrawalsState('${item.id}');">审核通过</a>
                            </c:if>
                            <a href="javascript:withdrawalsDetails('${item.userId}');">收支明细</a>
                        </c:if>
                        <c:if test="${mtype == 2}">
                            <a href="javascript:withdrawalsView('${item.id}');">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/withdrawalsInfo/withdrawalsInfoList?orgId=${orgId}&widraCode=${widraCode}&state=${state}&mtype=${mtype}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var caseCenterInfoFollowView = function(caseId){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:500,
            width:800,
            url:"${ctx}/caseCenterInfoFollow/caseCenterInfoFollowView?caseId="+caseId
        });
    }

    var withdrawalsDetails = function(userId){
        openDialog({
            frame:true,
            title:"收支明细记录",
            height:600,
            width:1000,
            url:"${ctx}/withdrawalsInfo/withdrawalsDetails?userId="+userId,
            load:true
        });
    }

    /**
     * 审核提现申请
     */
    function editWithdrawalsState(id){
        ajaxSubmit("${ctx}/withdrawalsInfo/editWithdrawalsState",{"id":id},reload,"审核成功！","确认审核通过？","审核失败！");
    }

    function withdrawalsOnline(id){
        ajaxSubmit("${ctx}/withdrawalsInfo/withdrawalsOnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }

    var withdrawalsView= function(id){
        openDialog({
            frame:true,
            title:"提现详情",
            height:700,
            width:1100,
            url:"${ctx}/withdrawalsInfo/withdrawalsView?withdrawalsId="+id,
            load:true
        });
    }
</script>
</body>
</html>
