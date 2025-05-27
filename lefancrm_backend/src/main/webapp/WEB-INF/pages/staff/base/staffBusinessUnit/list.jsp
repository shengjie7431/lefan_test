<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>事业部管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>事业部管理列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/staff/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <div class="form-group">
                        名称:
                        <input name="name" type="text" value="${name}" style="width: 150px" class="form-control">
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
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>
                        <a href="javascript:edit('${item.id}');">编辑</a>
                        <!--<a href="javascript:operate('${item.id}','${surveyCode}','9999',true);">删除</a>-->
                        <a href="javascript:fitCompany('${item.id}','${surveyCode}','1000','设置公司');">设置公司</a>
                        <a href="javascript:fitCompany('${item.id}','${surveyCode}','2000','名下公司');">名下公司</a>
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
            <jsp:param name="requestUrl" value="${ctx}/staff/list?surveyCode=${surveyCode}&name=${name}" />
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


    var fitCompany = function(businessUnitId,surveyCode,btnCode,title){
        openDialog({
            frame:true,
            title:title,
            height:800,
            width:1500,
            url:"${ctx}/staff/popup?businessUnitId="+businessUnitId+"&surveyCode="+surveyCode+"&btnCode="+btnCode,
            load:true
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
