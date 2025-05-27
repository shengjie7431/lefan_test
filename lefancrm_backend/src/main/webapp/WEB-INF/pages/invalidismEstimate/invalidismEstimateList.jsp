<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>伤残等级预估列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateReson = function(id){
            openDialog({
                frame:true,
                title:"操作",
                height:750,
                width:1200,
                url:"${ctx}/invalidism/toReport?id="+id+"&op=view"
            });
        }
        var file = function(id){
            openDialog({
                frame:true,
                title:"伤残等级预估图片资料",
                height:400,
                width:600,
                url:"${ctx}/invalidism/queryFile?id="+id
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 伤残等级预估列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/invalidism/list" method="post">
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                        用户姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                        事故性质: <select name="accidentType" class="form-control">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${accidentType == 1}">selected="selected" </c:if>>交通事故</option>
                        <option value="2" <c:if test="${accidentType == 2}">selected="selected" </c:if>>工伤事故</option>
                        <option value="3" <c:if test="${accidentType == 3}">selected="selected" </c:if>>意外事故</option>
                    </select>
                        预估状态: <select name="state" class="form-control">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${state == 1}">selected="selected" </c:if>>待预估</option>
                        <option value="2" <c:if test="${state == 2}">selected="selected" </c:if>>已预估</option>
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
                <th  width="150">用户姓名</th>
                <th  width="150">用户手机号</th>
                <th  width="150">推广人姓名</th>
                <th  width="150">事故发生地</th>
                <th  width="150">事故性质</th>
                <th  width="150">预估状态</th>
                <th  width="150">案件编号</th>
                <th  width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}
                    </td>
                    <td>${item.userPhone}</td>
                    <td>${item.promoterName}</td>
                    <td>
                            ${item.accidentProvince}${item.accidentCity}${item.accidentDistrict}${item.accidentAddress}
                    </td>
                    <td><c:if test="${item.accidentType == 1}">交通事故</c:if>
                        <c:if test="${item.accidentType == 2}">工伤事故</c:if>
                        <c:if test="${item.accidentType == 3}">意外事故</c:if>
                    </td>
                    <td><c:if test="${item.state == 1}">待预估</c:if>
                        <c:if test="${item.state == 2}">已预估</c:if>
                    </td>
                    <td>
                        ${item.caseNo}
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:updateReson('${item.id}');">操作</a>
                        <%--<a  href="javascript:void(0)" onclick="del(${item.id})">删除</a>--%>
                        <%--<a  href="javascript:void(0)" onclick="file(${item.id})">查看伤残资料</a>--%>
                        <%--<c:if test="${item.state == 1}">--%>
                            <%--<a  href="javascript:void(0)" onclick="updateReson(${item.id})">预估回复</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.state == 2}">--%>
                            <%--<a  href="javascript:void(0)" onclick="updateReson(${item.id})">查看回复</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.turnStatus == 0}">--%>
                            <%--<a href="javascript:forward('${item.id}','${item.userName}','${item.userPhone}',--%>
                                <%--'${item.accidentProvince}','${item.accidentProvinceId}','${item.accidentCity}','${item.accidentCityId}','${item.accidentDistrict}',--%>
                                <%--'${item.accidentDistrictId}','${item.accidentAddress}',null,'${item.userId}')">转办案件中心</a>--%>
                        <%--</c:if>--%>
                    </td>
                </tr>
            </c:forEach>
          <%-- <tr>
                 <td colspan="10">
                     <c:if test="${page > 1}">
                         <a href="${ctx}/account/orderInfoList?page=${page-1}">上一页</a>&nbsp;
                     </c:if>
                     <a href="${ctx}/account/orderInfoList?page=${page+1}">下一页</a>
                 </td>
             </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/invalidism/list?userName=${userName}&accidentType=${accidentType}&state=${state}&caseNo=${caseNo}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function del(id){
        ajaxSubmit("${ctx}/invalidism/del",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
    }
    function forward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress,dangerTime,userId){
        openDialog({
            frame:true,
            title:"转办案件中心",
            height:500,
            width:1000,
            url:"${ctx}/caseApply/forward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                    "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress+"" +
                    "&dangerTime="+dangerTime+"&isDangerTime="+true+"&type="+1+"&userId="+userId
        });
    }
</script>
</body>
</html>
