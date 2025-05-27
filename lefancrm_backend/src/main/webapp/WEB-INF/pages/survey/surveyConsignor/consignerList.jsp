<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托人列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>名下人员 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/surveyConsignor/selectConsigner" method="post">
                    <input type="hidden" name="btnCode" value="${btnCode}">
                    <input type="hidden" name="id" value="${consignorId}">
                    <div class="form-group">
                        用户姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        联系电话:<input name="tel" type="text" value="${tel}" class="form-control">
                    </div>
<%--                    <div class="form-group">--%>
<%--                        部门:--%>
<%--                        <select name="departmentId" class="form-control">--%>
<%--                            <option value="">全部</option>--%>
<%--                            <c:forEach items="${departments}" var="item">--%>
<%--                                <option <c:if test="${departmentId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </div>--%>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">用户姓名</th>
                <th width="150">联系电话</th>
                <th width="100">部门</th>
                <th width="150">认证状态</th>
                <th width="150">账户状态</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.tel}</td>
                    <td>${item.departmentNames}</td>
                    <td>
                        <c:if test="${item.authState==0}">未认证</c:if>
                        <c:if test="${item.authState==1}">认证中</c:if>
                        <c:if test="${item.authState==2}">认证通过</c:if>
                        <c:if test="${item.authState==3}">认证不通过</c:if>
                    </td>
                    <td>
                        <c:if test="${item.accState==0}">正常</c:if>
                        <c:if test="${item.accState==1}">冻结</c:if>
                        <c:if test="${item.accState==2}">删除</c:if>
                    </td>
                    <td>
                        <a href="javascript:info('${item.id}','consigner');">详情</a>
                        <a href="javascript:operate('${item.id}','consigner','${btnCode}',true);">移除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/surveyConsignor/selectConsigner?btnCode=${btnCode}&userName=${userName}&tel=${tel}&id=${consignorId}&departmentId=${departmentId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
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
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
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

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
