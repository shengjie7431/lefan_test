<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票对象</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>开票对象 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApply/selectCompanyName" method="post">
                    <input type="hidden" id="type" name="type" value="${type}">
                    <div class="form-group">
                        <c:if test="${type == 'survey'}">
                            名称: <input name="name" type="text"  value="${name}"  class="form-control"/>
                        </c:if>
                        <c:if test="${type == 'billing' || type == 'law'}">
                            名称: <input name="companyName" type="text"  value="${companyName}"  class="form-control"/>
                        </c:if>
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
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <c:if test="${type == 'billing' || type == 'law'}">
                            ${item.companyName}
                        </c:if>
                        <c:if test="${type == 'survey'}">
                            ${item.name}
                        </c:if>
                    </td>
                    <th>
                        <a href="javascript:allot('${item.id}','${type}');">选择</a>
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
    function allot(id,type){
        $.ajax({
            url:'${ctx}/billingApply/selectCompanyNameInfo?id='+id+'&type='+type,
            type:"Get",
            success:function(res,param){
                var item=param.data.results;
                for(var i=0;i<item.length;i++){
                    if(type =="billing"){
                        parent.document.getElementById("companyName").value = item[0].companyName;
                        parent.document.getElementById("companyId").value = item[0].id;
                        parent.document.getElementById("businessType").value = item[0].billCompanyId;
                    }
                    if(type =="law"){
                        parent.document.getElementById("toOrg").value = item[0].companyName;
//                        parent.document.getElementById("companyId").value = item[0].id;
                    }
                    if(type =="survey"){
                        parent.document.getElementById("companyName").value = item[0].name;
                        parent.document.getElementById("companyId").value = item[0].id;
                        parent.document.getElementById("businessType").value = item[0].billCompanyId;
                    }
                }
                parent.selectCorporation();
                closeDialog();
            }
        });
    }

</script>
</body>
</html>
