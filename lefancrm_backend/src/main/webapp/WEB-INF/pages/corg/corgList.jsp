<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 组织列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改组织信息",
                height:600,
                width:800,
                url:"${ctx}/corg/queryOrgById?id="+id
            });
        }
        var addinfo = function(cOrgType){
            openDialog({
                frame:true,
                title:"添加组织信息",
                height:600,
                width:800,
                url:"${ctx}/corg/toAdd?cOrgType="+cOrgType
            });
        }

        var toDisUser = function(id,cOrgType){
            openDialog({
                frame:true,
                title:"分配用户",
                height:750,
                width:1000,
                url:"${ctx}/corg/queryNotDisUser?cOrgType="+cOrgType+"&cOrgId="+id
            });
        }

        var updateState = function(id,state,type){
            var val = "";
            var toVal ="";
            if(type == 1){
                val = "确定审核通过操作？"
                toVal = "审核成功！"
            }else if(type == 3){
                val = "确定禁用此组织操作？"
                toVal = "禁用组织成功！"
            }else if(type == 2){
                val = "确定审核不通过操作？"
                toVal = "审核不通过成功！"
            }else if(type == 4){
                val = "确定启用此组织操作？"
                toVal = "启用组织成功！"
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
        var queryUser = function(id,cOrgType){
            openDialog({
                frame:true,
                title:"组织用户信息",
                height:600,
                width:1200,
                url:"${ctx}/corg/queryCOrgUserList?cOrgId="+id+"&cOrgType="+cOrgType
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 组织列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/corg/list" method="post">
                    <div class="form-group">
                        组织名称:<input name="cOrgName" type="text" value="${cOrgName}" class="form-control">
                        联系人:<input name="linkName" type="text" value="${linkName}" class="form-control">
                        联系人电话:<input name="lineTel" type="text" value="${lineTel}" class="form-control">
                       <input type="hidden" name="cOrgType" value="${cOrgType}">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo(${cOrgType})" type="button" class="btn btn-default" class="btn btn-default">添加组织</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">组织名称</th>
                <th width="150">联系人</th>
                <th width="150">联系人电话</th>
                <th width="150">所在地址</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.cOrgName}</td>
                    <td>${item.linkName}</td>
                    <td> ${item.lineTel}</td>
                    <td>${item.province}${item.city}${item.district}${item.address} </td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <a  href="javascript:void(0)" onclick="queryUser(${item.id},${item.cOrgType})">查看组织用户</a>
                        <a  href="javascript:void(0)" onclick="toDisUser(${item.id},${item.cOrgType})">分配组织用户</a>
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
            <jsp:param name="requestUrl" value="${ctx}/corg/list?cOrgType=${cOrgType}&cOrgName=${cOrgName}&linkName=${linkName}&lineTel=${lineTel}" />
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
