<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改机构信息",
                height:1000,
                width:600,
                url:"${ctx}/org/toEdit?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加机构信息",
                height:1000,
                width:600,
                url:"${ctx}/org/toAdd"
            });
        }

        var toDisUser = function(id,name){
            openDialog({
                frame:true,
                title:"分配用户",
                height:750,
                width:1000,
                url:"${ctx}/org/toDisUser?id="+id
            });
        }

        var updateState = function(id,state,type){
            var val = "";
            var toVal ="";
            if(type == 1){
                val = "确定审核通过操作？"
                toVal = "审核成功！"
            }else if(type == 3){
                val = "确定禁用此机构操作？"
                toVal = "禁用机构成功！"
            }else if(type == 2){
                val = "确定审核不通过操作？"
                toVal = "审核不通过成功！"
            }else if(type == 4){
                val = "确定启用此机构操作？"
                toVal = "启用机构成功！"
            }else if(type == 5){
                val = "确定重新审核操作？"
                toVal = "审核成功！"
            }
            ajaxSubmit("${ctx}/org/editState",{"orgId":id,"state":state},reload,toVal,val,"操作失败");
        }

        var queryCase = function(id){
            openDialog({
                frame:true,
                title:"案件信息",
                height:700,
                width:1000,
                url:"${ctx}/org/queryCase?id="+id
            });
        }
        var queryUser = function(id){
            openDialog({
                frame:true,
                title:"机构用户信息",
                height:600,
                width:1200,
                url:"${ctx}/org/queryUser?id="+id
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 机构列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/org/list" method="post">
                    <div class="form-group">
                        机构名称:<input name="orgName" type="text" value="${orgName}" class="form-control">
                        联系人:<input name="linkName" type="text" value="${linkName}" class="form-control">
                        机构状态: <select name="state" class="form-control">
                        <option value="0">全部</option>
                        <option value="1" <c:if test="${state == 1}">selected="selected" </c:if>>待审核</option>
                        <option value="2" <c:if test="${state == 2}">selected="selected" </c:if>>审核通过</option>
                        <option value="3" <c:if test="${state == 3}">selected="selected" </c:if>>审核不通过</option>
                        <option value="4" <c:if test="${state == 4}">selected="selected" </c:if>>禁用</option>
                    </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default" class="btn btn-default">添加机构信息</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">机构名称</th>
                <th width="150">机构电话</th>
                <th width="150">联系人</th>
                <th width="150">联系人电话</th>
                <th width="150">机构所在地址</th>
                <th width="150">机构审核状态</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orgName}
                    </td>
                    <td>${item.orgTel}</td>
                    <td>${item.linkName}</td>
                    <td>
                            ${item. linkTel}
                    </td>
                    <td>
                            ${item.orgProvince}${item.orgCity}${item.orgDistrict}${item.orgAddress}
                    </td>
                    <td>
                        <c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">审核不通过</c:if>
                        <c:if test="${item.state == 4}">禁用</c:if>
                    </td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <c:if test="${item.state == 1}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2,1)">审核通过</a>
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},3,2)">审核不通过</a>
                        </c:if>
                        <c:if  test="${item.state == 2}">
                            <a  href="javascript:void(0)" onclick="queryCase(${item.id})">查看案件</a>
                            <a  href="javascript:void(0)" onclick="queryUser(${item.id})">查看用户</a>
                            <a  href="javascript:void(0)" onclick="toDisUser(${item.id})">分配用户</a>
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},4,3)">禁用</a>
                        </c:if>
                        <c:if  test="${item.state == 4}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2,4)">启用</a>
                        </c:if>
                        <c:if  test="${item.state == 3}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2,5)">重新审核</a>
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
            <jsp:param name="requestUrl" value="${ctx}/org/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    /* var getreport = function(id){
     openDialog({
     frame:true,
     title:"查看测算报告",
     height:1000,
     width:800,
     url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
     });
     }*/
</script>
</body>
</html>
