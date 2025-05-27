<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票项目列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>开票项目列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="enumCode" value="${enumCode}">
                    <input type="hidden" name="parentId" value="${parentId}">
                   <div class="form-group">
                       名称:<input name="enumName" type="text" value="${enumName}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}','${parentId}')" type="button" class="btn btn-default">添加</button>
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">名称</th>
                <th width="100">说明</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.enumName}</td>
                    <td>${item.enumText}</td>
                    <td>${item.createBy}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <%--<td><a href="javascript:edit('${item.id}','${surveyCode}');">修改</a></td>--%>
                    <td>
                        <a href="javascript:operate('${item.id}','${surveyCode}','1000',false);">修改</a>
                        <a href="javascript:operate('${item.id}','${surveyCode}','9999',true);">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&enumName=${enumName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode,parentId){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId
        });
    }

    function operate(id,surveyCode,btnCode,ajax) {
        var height = 400, width = 800;
        if (ajax) {
            var url = "${ctx}/baseSurvey/operate", param = {"id": id, "surveyCode": surveyCode, "btnCode": btnCode};
            if (confirm('是否确认？')) {
                ajaxSubmit(url, param, function (v, e, p) {
                    location.reload();
                })
            }
        } else {
            var title = null, url = null;
            if (btnCode == '1000') {
                height = 650;
                width = 1000;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id=" + id + "&surveyCode=" + surveyCode
            }
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
            });
        }
    }
</script>
</body>
</html>
