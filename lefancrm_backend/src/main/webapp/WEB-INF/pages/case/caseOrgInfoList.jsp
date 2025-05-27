<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件分配</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件分配 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/center/selectOrgInfo" method="post">
                    <div class="form-group">
                        机构名称: <input name="orgName" type="text"  value="${orgName}"  class="form-control"/>
                        机构电话: <input name="orgTel" type="text"  value="${orgTel}" class="form-control"/>
                        <input name="caseId" type="text"  value="${caseId}" hidden="true"/>
                        <input name="btnCode" type="text"  value="${btnCode}" hidden="true"/>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="200">机构名称</th>
                <th width="150">机构电话</th>
                <th width="100">联系人</th>
                <th width="150">联系人电话</th>
                <th width="80">省</th>
                <th width="80">市</th>
                <th width="80">区</th>
                <th width="300">详细地址</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orgName}</td>
                    <td>${item.orgTel}</td>
                    <td>${item.linkName}</td>
                    <td>${item.linkTel}</td>
                    <td>${item.orgProvince}</td>
                    <td>${item.orgCity}</td>
                    <td>${item.orgDistrict}</td>
                    <td>${item.orgAddress}</td>
                    <th>
                        <a href="javascript:allotCaseOrg('${caseId}','${item.id}','${item.orgName}','${btnCode}');">分配</a>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/case/center/selectOrgInfo?orgName=${orgName}&orgTel=${orgTel}&caseId=${caseId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function allotCaseOrg(caseId,orgId,orgName,btnCode){
       if(confirm('确认分配吗？')){
           ajaxSubmit("${ctx}/case/center/operate",{"id":caseId,"orgId":orgId,"orgName":orgName,"btnCode":btnCode},function(v,e,p){
               if(e.data.code==='0000'){
                    alert("分配成功");
                   parent.location.reload();
               }else{
                   alert("分配失败");
               }
           })
       }
   }

  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/
</script>
</body>
</html>
