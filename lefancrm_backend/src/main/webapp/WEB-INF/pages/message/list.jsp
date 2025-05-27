<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>通知消息列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>消息列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/message/list" method="post">
                    <div class="form-group">
                        发送时间: 
                        <select name="sendTime"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1" <c:if test="${sendTime == 1}">selected="selected" </c:if>>今天</option>
                        <option value="2" <c:if test="${sendTime == 2}">selected="selected" </c:if>>昨天</option>
                        <option value="3" <c:if test="${sendTime == 3}">selected="selected" </c:if>>本周</option>
                        <option value="4" <c:if test="${sendTime == 4}">selected="selected" </c:if>>本月</option>
                        <span ></span>
                    </select>
                    </div>
                    <div class="form-group">
                       信息读取状态: <select name="isRead"  class="form-control">
                            <option value="" <c:if test="${empty isRead}">selected="selected" </c:if>>全部</option>
                            <option value="0" <c:if test="${isRead == '0'}">selected="selected" </c:if>>未读</option>
                            <option value="1" <c:if test="${isRead == '1'}">selected="selected" </c:if>>已读</option>

                        </select>
                        </div>
                    <div class="form-group">
                        信息是否删除: <select name="deleteFlag"  class="form-control">
                        <option value="" <c:if test="${empty deleteFlag}">selected="selected" </c:if> >全部</option>
                        <option value="0" <c:if test="${deleteFlag == '0'}">selected="selected" </c:if>>未删除</option>
                        <option value="1" <c:if test="${deleteFlag == '1'}">selected="selected" </c:if>>已删除</option>

                    </select>
                    </div>

                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                    <div class="btn-group">
                        <button onclick="addMessage()" type="button" class="btn btn-default">新增</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">发送时间</th>
                <th width="80">发送人</th>
                <th width="80">接收人</th>
                <th width="120">信息发送状态</th>
                <th width="120">信息内容</th>
                <th width="120">信息读取</th>
                <th width="120">信息删除</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>
                        <fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.sendName}</td>
                    <td>${item.receiveName}</td>
                    <td>
                        <%--<c:if test="${item.isSend == 0}">待发送</c:if>
                        <c:if test="${item.isSend == 1}">已发送</c:if>--%>

                        <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                        <c:choose>
                            <c:when test="${nowDate-item.sendTime.time > 0}">
                                <span>已发送</span>
                            </c:when>
                            <c:otherwise>
                                <span>待发送</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${item.content}</td>
                    <td>
                        <c:if test="${item.isRead == 0}">未读</c:if>
                        <c:if test="${item.isRead == 1}">已读</c:if>

                    </td>
                    <td>
                        <c:if test="${item.deleteFlag == 0}">未删除</c:if>
                        <c:if test="${item.deleteFlag == 1}">已删除</c:if>

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
            <jsp:param name="requestUrl" value="${ctx}/message/list?sendTime=${sendTime}&isRead=${isRead}&deleteFlag=${deleteFlag}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/


   function details(id){
   openDialog({
   frame:true,
   title:"案件详情",
   height:500,
   width:1000,
   url:"${ctx}/share/details?id="+id
   });
   }

   function reson(id,userId,userPhone,userName){
       openDialog({
           frame:true,
           title:"驳回案件申请",
           height:500,
           width:1000,
           url:"${ctx}/share/viewReson?id="+id+"&userId="+userId+"&userPhone="+userPhone+"&userName="+userName
       });
   }

   function examine(id,userId,userPhone,inOfficerPhone,otherPhone,userName,inOfficerName,otherName,accidentTime,carNumber){
       if(userPhone==null||userPhone===''){
           alert('伤者方不能为空');
           return;
       }
       if(inOfficerPhone==null||inOfficerPhone===''){
           alert('保险员不能为空');
           return;
       }
       if(otherPhone==null||otherPhone===''){
           alert('肇事方不能为空');
           return;
       }
       ajaxSubmit("${ctx}/share/examine",{"id":id,"userId":userId,"userPhone":userPhone,"inOfficerPhone":inOfficerPhone,"otherPhone":otherPhone
       ,"userName":userName,"inOfficerName":inOfficerName,"otherName":otherName,"accidentTime":accidentTime,"carNumber":carNumber},reload,"审核成功","确认通过审核吗？");
   }

   function reportList(id){
       openDialog({
           frame:true,
           title:"共享理赔调解方案",
           height:500,
           width:1000,
           url:"${ctx}/share/viewMediationProgram?caseId="+id
       });
   }
   function indemnity(id){
       openDialog({
           frame:true,
           title:"共享理赔赔偿方案",
           height:500,
           width:1000,
           url:"${ctx}/share/indemnityProgram?caseId="+id
       });
   }
   function fileMid(id){
       openDialog({
           frame:true,
           title:"资料查看",
           height:500,
           width:1000,
           url:"${ctx}/share/shareDataView?id="+id
       });
   }
   function insOfficerList(icId,id){
       openDialog({
           frame:true,
           title:"分配保险员",
           height:500,
           width:1000,
           url:"${ctx}/share/queryInsOfficerList?icId="+icId+"&id="+id
       });
   }
   var addMessage = function(){
       openDialog({
           frame:true,
           title:"新增信息",
           height:740,
           width:600,
           url:"${ctx}/message/toAddMessage"
       });
   }
</script>
</body>
</html>
