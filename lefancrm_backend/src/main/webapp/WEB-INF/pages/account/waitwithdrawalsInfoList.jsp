<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广用户待结算列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>推广用户待结算列表<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/waitWithdrawalsInfoList" method="post">
                    <div class="form-group">
                      <%--  机构名称: <select name="orgId" >
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}" >${item1.orgName}</option>
                        </c:forEach>

                    </select>--%>
                        <select id="batchOperateType" class="form-control">
                            <option value="createRecord">生成结算单</option>
                        </select>
                    </div>
                   <%-- <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn1" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>--%>
                   <%-- <div class="btn-group">


                        <select id="batchOperateType" class="form-control">
                            <option value="createRecord">生成结算单</option>
                        </select>
                    </div>--%>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">确认</button> &nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>
                <th width="150">用户名</th>
                <th width="150">提现金额</th>
                <th width="150">提现状态</th>
                <th width="150">结算状态</th>
                <th width="150">交易备注</th>
                <th width="150">时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}"></td>
                    <td>${item.userName}</td>
                    <td>${item.money}</td>
                    <td>
                        <c:if test="${item.state == 1}">待提现</c:if>
                        <c:if test="${item.state == 2}">提现中</c:if>
                        <c:if test="${item.state == 3}">提现成功</c:if>
                    </td>
                    <td>
                        <c:if test="${item.accountsState == 1}">待结算</c:if>
                        <c:if test="${item.accountsState == 2}">结算中</c:if>
                        <c:if test="${item.accountsState == 3}">已结算</c:if>
                    </td>
                    <td>${item.tradeDesc}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${item.state == 1}"><a href="javascript:withdrawalsOnline('${item.id}');">在线提现</a></c:if>
                        <c:if test="${item.state == 1}"><a href="javascript:withdrawalsUnline('${item.id}');">线下提现</a></c:if>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/waitWithdrawalsInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/waitWithdrawalsInfoList?page=${page+1}">下一页</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div><!--panel-info-->

    <%--<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>--%><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function withdrawalsUnline(id){
        ajaxSubmit("${ctx}/account/withdrawalsUnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }
    function withdrawalsOnline(id){
        ajaxSubmit("${ctx}/account/withdrawalsOnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }
    function batchOperate(batchOperateType,idArr){
        var operateTitle="";
        var url="";
        if(batchOperateType=="createRecord"){
            operateTitle="结算单生成";
            url="${ctx}/account/createRecord";
        }
        ajaxSubmit(url,{withdrawalsArray:idArr.join(",")},reload,"生成结算单成功","确认生成结算单吗");
    }
    /*全选 取消全选*/
    var check_btn = document.getElementById("check-btn");
    var check_name = document.getElementsByName("list-checkbox");
    check_btn.onclick = function(){
        for(var i=1; i<=check_name.length; i+=1){
            if(check_name[i-1].checked){
                check_name[i-1].checked = false;
            }else{
                check_name[i-1].checked = true;
            }
        }
    };
    $("#batchOperateBtn").click(function(){
        var batchOperateType=$("#batchOperateType").val();
        var check_name = document.getElementsByName("list-checkbox");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(batchOperateType!="" && idArr.length>0){
            batchOperate(batchOperateType,idArr);
        }
    });
</script>
</body>
</html>
