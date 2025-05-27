<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>部门人员列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>部门人员数据</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/popup" method="post">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" id="departmentId" name="departmentId" value="${departmentId}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="100">用户姓名</th>
                    <th width="150">联系电话</th>
                    <th width="150">认证状态</th>
                    <th width="150">账户状态</th>
                    <th width="80">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${consigneres}" var="item">
                    <tr>
                        <td>${item.userName}</td>
                        <td>${item.tel}</td>
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
                            <a href="javascript:operate('${item.id}','consigner',2000,true);">移除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/popup?btnCode=${btnCode}&realName=${realName}&surveyCode=${surveyCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode,"departmentId" : $("#departmentId").val()};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '2000') {
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

</script>
</body>
</html>
