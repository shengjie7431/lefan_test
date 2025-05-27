<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>保险公司</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>保险公司 <small>共<span>${commonOrgInfoDtos.size()}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/center/popup" method="post">
                    <input type="hidden" id="type" name="type" value="${type}">
                    <input type="hidden" id="code" name="code" value="${code}">
                    <div class="form-group">
                        名称: <input name="cOrgName" type="text"  value="${cOrgName}"  class="form-control"/>
                        <div class="btn-group">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="80">名称</th>
                <th width="50">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${commonOrgInfoDtos}" var="item">
                <tr>
                    <td>
                        ${item.cOrgName}
                    </td>
                    <th>
                        <a href="javascript:allot('${item.id}','${item.cOrgName}','${type}');">选择</a>
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
            <jsp:param name="requestUrl" value="${ctx}/billingApply/selectCompanyName?companyName=${companyName}&type=${type}&name=${name}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function allot(id,cOrgName,type){
        if(type ==1){
            parent.document.getElementById("insuranceCompany").value = cOrgName;
            parent.document.getElementById("insuranceCompanyId").value = id;
        }
        if(type ==2){
            parent.document.getElementById("insuranceCompany2").value = cOrgName;
            parent.document.getElementById("insuranceCompany2Id").value = id;
        }
        closeDialog();


    }

</script>
</body>
</html>
