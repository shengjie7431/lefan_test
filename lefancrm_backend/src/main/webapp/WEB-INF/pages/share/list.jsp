<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/share/list" method="post">
                    <div class="form-group">
                        案件编号: <input name="caseCode" type="text"  value="${caseCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件类型: <select name="caseType"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1">个人调解</option>
                        <option value="2">交警调解</option>
                        <option value="3">法院调解</option>
                        <option value="4">保险公司调解</option>
                        <option value="5">专调</option>
                    </select>
                    </div>
                    <div class="form-group">
                       案件状态: <select name="state"  class="form-control">
                            <option value="" >全部</option>
                            <option value="0">待审核</option>
                            <option value="1">审核通过待调解</option>
                            <option value="2">驳回</option>
                            <option value="3">调解中</option>
                            <option value="4">调解方案已确认</option>
                            <option value="5">调解成功</option>
                            <option value="6">调解失败</option>
                        </select>
                        </div>
                    <div class="form-group">
                        发起人类型: <select name="launchType"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1">原告/律师发起</option>
                        <option value="2">保险公司</option>
                        <option value="3">法院调解员</option>
                        <option value="4">交警队</option>
                    </select>
                    </div>
                        <%----%>
                    <div class="form-group">
                        发起人姓名: <input name="launchName" type="text"  value="${launchName}" class="form-control">
                        </div>
                    <div class="form-group">
                        用户姓名: <input name="userName" type="text"  value="${userName}" class="form-control">
                        </div>

                    <div class="form-group">
                        手机号码: <input name="userPhone" type="text"  value="${userPhone}" class="form-control">
                        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        车牌号: <input name="carNumber" type="text"  value="${carNumber}" class="form-control">
                        </div>
                    &nbsp;&nbsp;
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
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
                <th width="80">案件编号</th>
                <th width="120">类型</th>
                <th width="120">发起人类型</th>
                <th width="90">发起人姓名</th>
                <th width="100">用户姓名</th>
                <th width="100">手机号码</th>
                <th width="150">是否交通事故</th>
                <th width="160">保险公司名称</th>
                <th width="200">案件状态</th>
                <th width="80">车牌号</th>
                <th width="120">保险员姓名</th>
                <th width="100">驳回原因</th>
                <th width="200">创建时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseCode}</td>
                    <td>
                        <c:if test="${item.caseType == 1}">个人调解</c:if>
                        <c:if test="${item.caseType == 2}">交警调解</c:if>
                        <c:if test="${item.caseType == 3}">法院调解</c:if>
                        <c:if test="${item.caseType == 4}">保险公司调解</c:if>
                        <c:if test="${item.caseType == 5}">专调</c:if>
                    </td>
                    <td>
                        <c:if test="${item.launchType == 1}">原告/律师发起</c:if>
                        <c:if test="${item.launchType == 2}">保险公司</c:if>
                        <c:if test="${item.launchType == 3}">法院调解员</c:if>
                        <c:if test="${item.launchType == 4}">交警队</c:if>
                    </td>
                    <td>${item.launchName}</td>
                    <td>${item.userName}</td>
                    <td>${item.userPhone}</td>
                    <c:if test="${item.isTraffic == 0}">
                        <td>否</td>
                    </c:if>
                    <c:if test="${item.isTraffic == 1}">
                        <td>是</td>
                    </c:if>
                    <td>${item.icName}</td>
                    <td>
                        <c:if test="${item.state == 0}">待审核</c:if>
                        <c:if test="${item.state == 1}">审核通过待调解</c:if>
                        <c:if test="${item.state == 2}">驳回</c:if>
                        <c:if test="${item.state == 3}">调解中</c:if>
                        <c:if test="${item.state == 4}">调解方案已确认</c:if>
                        <c:if test="${item.state == 5}">调解成功</c:if>
                        <c:if test="${item.state == 6}">调解失败</c:if>
                        <c:if test="${item.state == 7}">已开票</c:if>
                    </td>
                    <td>${item.carNumber}</td>
                    <td>${item.inOfficerName}</td>
                    <td>${item.reson}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <!--<a href="javascript:checkInsOfficer('${item.id}');">分配保险员</a>&nbsp-->
                        <c:if test="${item.state==0}"><a href="javascript:examine('${item.id}','${item.createBy}',
                        '${item.userPhone}','${item.inOfficerPhone}','${item.otherPhone}','${item.userName}','${item.inOfficerName}','${item.otherName}','<fmt:formatDate value="${item.accidentTime}" pattern="yyyy年MM月dd日 hh:mm"/>','${item.carNumber}')">审核</a></c:if>
                        <c:if test="${item.state==0}"><a href="javascript:reson('${item.id}','${item.createBy}','${item.userPhone}','${item.userName}')">驳回</a></c:if>
                        <a href="javascript:insOfficerList('${item.icId}','${item.id}')">分配保险员</a>
                        <a href="javascript:fileMid('${item.id}')">资料查看</a>
                        <a href="javascript:reportList('${item.id}')">调解方案</a>
                        <a href="javascript:indemnity('${item.id}')">赔偿方案</a>
                        <a target='_blank' href="http://ddrapi.shlefan.com/sftp/files/shareCase/${item.caseCode}/${item.caseCode}.pdf">调解报告</a>
                        <a href="javascript:details('${item.id}')">详情</a>
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
            <jsp:param name="requestUrl" value="${ctx}/share/list?caseCode=${caseCode}&caseType=${caseType}&state=${state}&launchType=${launchType}&launchName=${launchName}&userName=${userName}&userPhone=${userPhone}&carNumber=${carNumber}" />
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
</script>
</body>
</html>
