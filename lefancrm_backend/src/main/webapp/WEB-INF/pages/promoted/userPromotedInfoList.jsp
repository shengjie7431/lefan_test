<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户推广认证信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 用户推广认证信息 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/promoted/userPromotedInfoList" method="post">
                    <div class="form-group">
                        姓名:<input name="realName" type="text" value="${realName}" class="form-control">
                        手机号:<input name="phone" type="text" value="${phone}" class="form-control">
                        状态: <select name="state" class="form-control">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${state == 1}">selected="selected" </c:if>>未审核</option>
                        <option value="2" <c:if test="${state == 2}">selected="selected" </c:if>>审核通过</option>
                        <option value="3" <c:if test="${state == 3}">selected="selected" </c:if>>驳回</option>
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
                <%--<th class="th-checkbox"><input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选"></th>--%>
                <th width="100">真实姓名</th>
                <th width="150">手机号</th>
                <th width="150">职业</th>
                <th width="150">所属地</th>
                <th width="200">状态</th>
                <th width="200">创建时间</th>
                <th width="200">驳回原因</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.realName}</td>
                    <td>${item.phone}</td>

                    <td>
                        <c:if test="${item.occupation == 1}">护士</c:if>
                        <c:if test="${item.occupation == 2}">医生</c:if>
                        <c:if test="${item.occupation == 3}">护工</c:if>
                        <c:if test="${item.occupation == 4}">调解组织</c:if>
                        <c:if test="${item.occupation == 5}">业务员</c:if>
                        <c:if test="${item.occupation == 6}">其他</c:if>
                        <c:if test="${item.occupation == 7}">保代公司</c:if>
                    </td>
                    <td>${item.province}${item.city}${item.district}</td>
                    <td>
                        <c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">驳回</c:if>
                    </td>
                    <input type="hidden" value="${item.isOpen}" id="isOpen">
                    <input type="hidden" value="${item.promotedType}" id="promotedType">
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                            ${item.reason}
                    </td>
                    <td>
                        <c:if test="${item.state == 1}">
                            <a href="javascript:promotedUpdatea('${item.id}',2);">审核通过</a>
                            <a href="javascript:bohuiPromoted('${item.id}');">驳回</a>
                            <c:if test="${item.promotedType == 2}"><a href="javascript:file('${item.id}');">查看凭证</a></c:if>
                        </c:if>
                        <%--<c:if test="${item.state == 3}">
                            ${item.reason}
                            &lt;%&ndash; <a href="javascript:promotedUpdatea('${item.id}',2);"><span class="glyphicon glyphicon-remove"></span>审核通过</a>&ndash;%&gt;
                        </c:if>--%>
                        <c:if test="${item.state == 2}">
                            <a href="javascript:queryPromoted('${item.userId}');">查看推广客户</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
           <%-- <tr>
            <td colspan="10">
                <c:if test="${page > 1}">
                    <a href="${ctx}/promoted/userPromotedInfoList?page=${page-1}">上一页</a>&nbsp;
                </c:if>
                <a href="${ctx}/promoted/userPromotedInfoList?page=${page+1}">下一页</a>
            </td>
            </tr>--%>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/promoted/userPromotedInfoList?realName=${realName}&phone=${phone}&state=${state}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function promotedUpdatea(id,state){
        ajaxSubmit("${ctx}/promoted/userPromotedInfoAddOrUpdate",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
    }

   /* var isOpen = function(id,state,isOpen,promotedType){
        if(promotedType == 2 && (isOpen == 0|| isOpen == 1)){
            openDialog({
                frame:true,
                title:"审核",
                height:700,
                width:1000,
                url:"${ctx}/promoted/isOpen?id="+id+"&state="+state+"&isOpen="+isOpen+"&promotedType="+promotedType
            });
        }else{
            ajaxSubmit("${ctx}/promoted/userPromotedInfoAddOrUpdate",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
        }
    }*/

    var file = function(id){
        openDialog({
            frame:true,
            title:"保代公司审核凭证",
            height:400,
            width:600,
            url:"${ctx}/promoted/queryFile?id="+id
        });
    }
    var queryPromoted = function(userId){
        openDialog({
            frame:true,
            title:"推广客户列表",
            height:700,
            width:1000,
            url:"${ctx}/promoted/promotedInfoList?userId="+userId
        });
    }

    var bohuiPromoted = function(id){
        openDialog({
            frame:true,
            title:"驳回",
            height:200,
            width:800,
            url:"${ctx}/promoted/promotedInfoToReject?id="+id
        });
    }
</script>
</body>
</html>
