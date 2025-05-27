<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>时效模板</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>渠道费用列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                   <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <input type="hidden" name="type" value="${type}">
                   <div class="form-group">
                       名称:<input name="name" type="text" value="${name}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}','${type}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">模板名称</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>

                        <a href="javascript:operate('${item.id}','${surveyCode}','3000',false,'${type}');">设置区域类别</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}',2000,false,'${type}');">设置调查方</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}',1000,false,'${type}');">修改</a>
<%--                        <a href="javascript:operate('${item.id}','${surveyCode}',1001,false,'${type}');">创建副本</a>--%>
                        <a href="javascript:operate('${item.id}','${surveyCode}',9999,true,'${type}');">删除</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}','copy',true,'${type}');">复制模板</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&name=${name}&type=${type}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode,type){
        openDialog({
            frame:true,
            title:"添加",
            width : 1500,
            height : 750,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&type="+type
        });
    }

    function operate(id,surveyCode,btnCode,ajax,type){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode,"type":type};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                width = 1000;
                height = 750;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type="+type
            }else if(btnCode == '1001'){
                width = 1000;
                height = 750;
                title = '副本';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type="+type
            }
            else if(btnCode == '2000'){
                height = 650;
                width = 1000;
                title = '设置调查方';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type="+type
            }else if(btnCode == '3000'){
                height = $(document).outerHeight() - 20;
                width = $(document.body).outerWidth() - 20;
                title = '设置区域类别';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type="+type

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
