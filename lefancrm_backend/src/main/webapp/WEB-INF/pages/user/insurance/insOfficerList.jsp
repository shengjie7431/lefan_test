<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <%--<div class="panel-heading">--%>
            <%--<div class="pin">--%>
                <%--<form class="form-inline" role="form" action="${ctx}/insUserAddress/insUserAddressList" method="post">--%>
                   <%--<div class="form-group">--%>
                    <%--&lt;%&ndash; <input type="hidden" name="userId" value="${userId}">--%>
                     <%--<input type="hidden" name="orgId" value="${orgId}">&ndash;%&gt;--%>
                    <%--</div>--%>
                    <%--<div class="btn-group">--%>
                        <%--&nbsp; &nbsp;<button onclick="addInsUserAddress(${userId})" type="button" class="btn btn-default">添加保险员</button>&nbsp; &nbsp;--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</div>--%>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="50"></th>
                <th width="200">姓名</th>
                <th width="200">电话</th>
              <%--  <th width="150">机构名称</th>
                <th width="150">用户名称</th>
                <th width="150">订单类型</th>
                <th width="150">订单金额</th>
                <th width="150">订单状态</th>
                <th width="150">付款时间</th>
                <th width="150">下单时间</th>--%>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${user}" var="item">
                <tr>
                    <td>

                    </td>
                    <td>${item.user_name}
                    </td>
                    <td>
                    ${item.user_tel}
                    </td>
                    <td>
                        <a href="javascript:addInsOfficer('${managerUserId}','${item.user_id}','${item.user_tel}','${item.user_name}');">添加保险员</a>
                      <%--  <a href="javascript:withdrawalsOnline('${item.id}');">删除辖区</a>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <%--<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/orderInfoList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>--%><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function addInsOfficer(managerUserId,userId,userPhone,userName){
        ajaxSubmit("${ctx}/userInsurance/addInsOfficer",{"userId":userId,"parentId":managerUserId,"userPhone":userPhone,"userName":userName},reloadParent,"添加成功","确认添加该保险员吗？");
    }
</script>
</body>
</html>
