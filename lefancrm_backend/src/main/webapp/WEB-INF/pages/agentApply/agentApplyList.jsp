<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 代理申请列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改代理信息",
                height:400,
                width:800,
                url:"${ctx}/agent/toEdit?id="+id
            });
        }

        var updateReson = function(id){
            openDialog({
                frame:true,
                title:"驳回代理信息",
                height:400,
                width:600,
                url:"${ctx}/agent/toReson?id="+id
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>代理申请列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/agent/list" method="post">
                    <div class="form-group">
                        用户姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                        代理类型: <select name="agentType" class="form-control">
                        <option value="0">全部</option>
                        <option value="1" <c:if test="${agentType == 1}">selected="selected" </c:if>>交通事故索赔</option>
                        <option value="2" <c:if test="${agentType == 2}">selected="selected" </c:if>>工伤事故索赔</option>
                        <option value="3" <c:if test="${agentType == 3}">selected="selected" </c:if>>寿险索赔</option>
                        <option value="4" <c:if test="${agentType == 4}">selected="selected" </c:if>>车辆损失索赔</option>
                        <option value="5" <c:if test="${agentType == 5}">selected="selected" </c:if>>保险拒赔</option>
                        <option value="6" <c:if test="${agentType == 6}">selected="selected" </c:if>>意外保险</option>
                        <option value="7" <c:if test="${agentType == 7}">selected="selected" </c:if>>其他侵权</option>
                        <option value="8" <c:if test="${agentType == 8}">selected="selected" </c:if>>援助服务</option>
                    </select>
                        代理状态: <select name="state" class="form-control">
                        <option value="0">全部</option>
                        <option value="1" <c:if test="${state == 1}">selected="selected" </c:if>>待审核</option>
                        <option value="2" <c:if test="${state == 2}">selected="selected" </c:if>>审核通过</option>
                        <option value="3" <c:if test="${state == 3}">selected="selected" </c:if>>驳回</option>
                        <option value="4" <c:if test="${state == 4}">selected="selected" </c:if>>已受理</option>
                        <option value="10" <c:if test="${state == 10}">selected="selected" </c:if>>材料收集中</option>
                        <option value="11" <c:if test="${state == 11}">selected="selected" </c:if>>诉前调解</option>
                        <option value="12" <c:if test="${state == 12}">selected="selected" </c:if>>申请鉴定</option>
                        <option value="13" <c:if test="${state == 13}">selected="selected" </c:if>>鉴定中</option>
                        <option value="14" <c:if test="${state == 14}">selected="selected" </c:if>>待立案</option>
                        <option value="15" <c:if test="${state == 15}">selected="selected" </c:if>>已立案</option>
                        <option value="16" <c:if test="${state == 16}">selected="selected" </c:if>>开庭</option>
                        <option value="17" <c:if test="${state == 17}">selected="selected" </c:if>>已调解/判决</option>
                        <option value="18" <c:if test="${state == 18}">selected="selected" </c:if>>已上诉</option>
                        <option value="19" <c:if test="${state == 19}">selected="selected" </c:if>>补充证据</option>
                        <option value="20" <c:if test="${state == 20}">selected="selected" </c:if>>赔偿款已支付</option>
                        <option value="21" <c:if test="${state == 21}">selected="selected" </c:if>>贷款已还款</option>
                        <option value="22" <c:if test="${state == 22}">selected="selected" </c:if>>结案</option>
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
                <th  width="100">案件编号</th>
                <th width="100">用户姓名</th>
                <th width="100">用户手机号</th>
                <th width="100">推广人姓名</th>
                <th width="150">事故发生地</th>
                <th width="150">代理类型</th>
                <th width="150">代理申请状态</th>
                <th width="150">创建时间</th>
                <th width="150">驳回原因</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.agentNo}
                    </td>
                    <td>${item.userName}
                    </td>
                    <td>${item.userPhone}</td>
                    <td>${item.promoterName}</td>
                    <td>
                        ${item.accidentProvince}${item.accidentCity}${item.accidentDistrict}${item.accidentAddress}
                    </td>
                    <td><c:if test="${item.agentType == 1}">交通事故索赔</c:if>
                        <c:if test="${item.agentType == 2}">工伤事故索赔</c:if>
                        <c:if test="${item.agentType == 3}">寿险索赔</c:if>
                        <c:if test="${item.agentType == 4}">车辆损失索赔</c:if>
                        <c:if test="${item.agentType == 5}">保险拒赔</c:if>
                        <c:if test="${item.agentType == 6}">意外保险</c:if>
                        <c:if test="${item.agentType == 7}">其他侵权</c:if>
                        <c:if test="${item.agentType == 8}">援助服务</c:if>
                    </td>
                    <td><c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">驳回</c:if>
                        <c:if test="${item.state == 4}">已受理</c:if>
                        <c:if test="${item.state == 10}">材料收集中</c:if>
                        <c:if test="${item.state == 11}">诉前调解</c:if>
                        <c:if test="${item.state == 12}">申请鉴定</c:if>
                        <c:if test="${item.state == 13}">鉴定中</c:if>
                        <c:if test="${item.state == 14}">待立案</c:if>
                        <c:if test="${item.state == 15}">已立案</c:if>
                        <c:if test="${item.state == 16}">开庭</c:if>
                        <c:if test="${item.state == 17}">已调解/判决</c:if>
                        <c:if test="${item.state == 18}">已上诉</c:if>
                        <c:if test="${item.state == 19}">补充证据</c:if>
                        <c:if test="${item.state == 20}">赔偿款已支付</c:if>
                        <c:if test="${item.state == 21}">贷款已还款</c:if>
                        <c:if test="${item.state == 22}">结案</c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                            ${item.reson}
                    </td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <a  href="javascript:void(0)" onclick="del(${item.id})">删除</a>
                        <c:if test="${item.state == 1}">
                            <a  href="javascript:void(0)" onclick="editState(${item.id},2,${item.userId})">审核通过</a>
                            <a  href="javascript:void(0)" onclick="updateReson(${item.id})">驳回</a>
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
            <jsp:param name="requestUrl" value="${ctx}/agent/list?userName=${userName}&agentType=${agentType}&state=${state}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    function del(id){
        ajaxSubmit("${ctx}/agent/del",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
    }
    function editState(id,state,userId){
        ajaxSubmit("${ctx}/agent/editState",{"id":id,"state":state,"userId":userId},reload,"审核成功！","确认通过审核？","审核失败！");
    }
</script>
</body>
</html>
