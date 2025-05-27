<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <!--<style>
        #modal-content{
            margin: 0;
            width: 100%;
            height: 100%;
        }
        #dialogBoxID{
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }
    </style>-->
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${list.size()}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/info/publishs/list?menuType=${menuType}" method="post">
                    <div class="form-group">
                        姓名: <input name="userName" type="text"  value="${userName}" class="form-control">
                        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        手机号: <input name="userTel" type="text"  value="${userTel}" class="form-control">
                        </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        身份证号: <input name="userCardid" type="text"  value="${userCardid}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        <c:if test="${menuType == 2}">
                            <button onclick="add()" type="button" class="btn btn-default">新增</button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="100">姓名</th>
                <th width="100">手机号</th>
                <th width="100">身份证号</th>
                <th width="100">创建时间</th>
                <th width="90">是否发布</th>
                <th width="100">操作</th>

            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>${item.userCardid}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.isPublish == 0}">
                            否
                        </c:if>
                        <c:if test="${item.isPublish == 1}">
                            是
                        </c:if>
                    </td>
                    <td>
                        <a href="javascript:info('${item.id}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/info/publishs/list?menuType=${menuType}&userName=${userName}&userTel=${userTel}&userCardid=${userCardid}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   function info(id){
       var url = "${ctx}/info/publishs/info?id="+id+"&menuType=${menuType}";
       openDialog({
           frame:true,
           title:"信息查看",
           height:750,
           width:1200,
           url : url,
           load:true
       });
   }

   var add = function(){
       openDialog({
           frame:true,
           title:"新增",
           height:700,
           width:1000,
           url:"${ctx}/info/publishs/add"
       });
   }
</script>
</body>
</html>
