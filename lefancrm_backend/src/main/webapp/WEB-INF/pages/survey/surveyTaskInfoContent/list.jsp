<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>任务子类名称列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>任务子类名称列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="taskInfoId" value="${taskInfoId}">
                    <div class="form-group">
                        子类名称:<input name="name" type="text" value="${name}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}','${taskInfoId}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="152">任务类型</th>
                <th width="150">任务子类名称</th>
                <th width="80">排序</th>
                <th width="80">颜色</th>
                <th width="80">创建人</th>
                <th width="80">是否显示</th>
                <%--<th width="120">创建时间</th>--%>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.taskInfoName}</td>
                    <td>${item.name}</td>
                    <td>${item.sort}</td>
                    <td><span style="background-color: ${item.color}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                    <td>${item.createBy}</td>
                    <td>
                        <c:if test="${item.showState == 0 }">否</c:if>
                        <c:if test="${item.showState == 1 }">是</c:if>
                    </td>
                    <%--<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
                    <td>
                        <a href="javascript:operate('${item.id}','${surveyCode}','1000',false);">修改</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}','9999',true);">删除</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}','2000',false);">设置方向结果</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&name=${name}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode,taskInfoId){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&taskInfoId="+taskInfoId
        });
    }

    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999') {
//                        reloadParent();//删除时，需刷新父级
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode+"&taskInfoId="+${taskInfoId};
            }
            else if(btnCode == '2000'){
                height = 500;
                width = 800;
                title = '设置';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode;
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }
</script>
</body>
</html>
