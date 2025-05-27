<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 合作伙伴管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改合作伙伴信息",
                height:600,
                width:800,
                url:"${ctx}/customer/queryById?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加合作伙伴信息",
                height:600,
                width:800,
                url:"${ctx}/customer/toAdd"
            });
        }

        var del = function(id,deleteFlag){
            ajaxSubmit("${ctx}/customer/edit",{"id":id,"deleteFlag":deleteFlag},reload,"删除成功！","确定删除操作！","操作失败");
        }

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 合作伙伴列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/customer/list" method="post">
                    <div class="form-group">
                        名称:<input name="name" type="text" value="${name}" class="form-control">
                        联系人:<input name="linkUser" type="text" value="${linkUser}" class="form-control">
                        电话:<input name="phoneNumber" type="text" value="${phoneNumber}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default" class="btn btn-default">添加合作伙伴</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">名称</th>
                <th width="150">公司logo</th>
                <th width="150">电话</th>
                <th width="150">联系人</th>
                <th width="150">详细链接</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><a href="${item.icon}" target="_blank"><img src="${item.icon}" width="75px" height="75px"></a></td>
                    <td> ${item.phoneNumber}</td>
                    <td>${item.linkUser}</td>
                    <td>${item.detailedLink}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
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
            <jsp:param name="requestUrl" value="${ctx}/customer/list?name=${name}&linkUser=${linkUser}&phoneNumber=${phoneNumber}" />
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
