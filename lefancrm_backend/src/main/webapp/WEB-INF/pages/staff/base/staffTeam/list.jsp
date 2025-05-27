<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>小组管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>小组管理列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/staff/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}" />
                    <div class="form-group">
                        名称:
                        <input name="name" type="text" value="${name}" style="width: 150px" class="form-control" >
                    </div>
                    <div class="form-group">
                        状态:
                        <select name="state" class="form-control" >
                            <option value="-1" >全部</option>
                            <option value="0" <c:if test="${state==0}">selected="selected" </c:if>>启用</option>
                            <option value="1" <c:if test="${state==1}">selected="selected" </c:if>>停用</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="edit('')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">名称</th>
                <th width="100">状态</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <c:if test="${item.state==0}">
                        <td>启用</td>
                    </c:if>
                    <c:if test="${item.state==1}">
                        <td>停用</td>
                    </c:if>
                    <td>
                        <a href="javascript:edit('${item.id}');">编辑</a>
                        <!--<a href="javascript:operate('${item.id}','${surveyCode}','9999',true);">删除</a>-->
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
            <jsp:param name="requestUrl" value="${ctx}/staff/list?surveyCode=${surveyCode}&name=${name}&state=${state}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>

    var edit = function(id){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/staff/edit?id="+id+"&surveyCode="+'${surveyCode}'
        });
    }

    function operate(id,surveyCode,btnCode,ajax){
        if(ajax){
            var url = "${ctx}/staff/operate",param = {"id":id,"btnCode":btnCode,"operateCode":surveyCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {

        }
    }

</script>
</body>
</html>
