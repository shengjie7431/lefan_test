<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>价格模板列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>价格模板列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/finaManager/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       名称:<input name="name" type="text" value="${name}" class="form-control">
                   </div>
<%--                    <div class="form-group">--%>
<%--                        业务类别:--%>
<%--                        <select name="type"  class="form-control">--%>
<%--                            <option value=""  <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                            <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >保险类</option>--%>
<%--                            <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >互助类</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        使用对象:
                        <select name="useObj"  class="form-control">
                            <option value=""  <c:if test="${useObj == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${useObj == '1'}">selected="selected" </c:if> >调查方</option>
                            <option value="2" <c:if test="${useObj == '2'}">selected="selected" </c:if> >委托方</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">模板名称</th>
<%--                <th width="250">业务类别</th>--%>
                <th width="250">使用对象</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
<%--                    <td>--%>
<%--                        <c:if test="${item.type == 1}">保险类</c:if>--%>
<%--                        <c:if test="${item.type == 2}">互助类</c:if>--%>
<%--                    </td>--%>
                    <td>${item.useObj == 1 ? '调查方' : '委托方'}</td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:operate('${item.id}','${surveyCode}','3000',false,'${item.type}');">设置区域类别</a>
                        <c:if test="${item.useObj == 1}">
                            <a href="javascript:operate('${item.id}','${surveyCode}','2100',false,'','${item.type}');">设置调查方</a>
<%--                            <a href="javascript:operate('${item.id}','${surveyCode}','2000',false,'','${item.type}');">设置委托方</a>--%>
                        </c:if>
                        <c:if test="${item.useObj == 2}">
                            <a href="javascript:operate('${item.id}','${surveyCode}','2000',false,'','${item.type}');">设置委托方</a>
                        </c:if>
                        <a href="javascript:operate('${item.id}','${surveyCode}','1000',false);">修改</a>
                        <a href="javascript:operate('${item.id}','priceModelDelete','9999',false);">删除</a>
                        <a href="javascript:operate('${item.id}','priceModelCopy','10000',false);">复制模板</a>
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
            <jsp:param name="requestUrl" value="${ctx}/finaManager/list?surveyCode=${surveyCode}&name=${name}&type=${type}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/finaManager/add?surveyCode="+surveyCode
        });
    }

    function operate(id,surveyCode,btnCode,ajax,type,orgAttr){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/finaManager/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/finaManager/edit?id="+id+"&surveyCode="+surveyCode
            }else if(btnCode == '2000'){
                height = 650;
                width = 1000;
                title = '设置委托方';
                url = "${ctx}/finaManager/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type=1"+"&orgAttr="+orgAttr
            }else if(btnCode == '2100'){
                height = 650;
                width = 1000;
                title = '设置调查方';
                url = "${ctx}/finaManager/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type=2"+"&orgAttr="+orgAttr
            }else if(btnCode == '3000'){
                height = $(document).outerHeight() - 20;
                width = $(document.body).outerWidth() - 20;
                title = '设置区域类别';
                url = "${ctx}/finaManager/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&type="+type

            }

            if (btnCode == '9999' || btnCode == '10000'){
                if(confirm('是否确认？')){
                    $.post("${ctx}/finaManager/update",{"id":id,"surveyCode":surveyCode},function (e) {
                        // console.log(e)
                        window.reload();
                    })
                }
            }else {
                openDialog({
                    frame:true,
                    title:title,
                    height:height,
                    width:width,
                    url:url
                });
            }

        }
    }
</script>
</body>
</html>
