<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>案件详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 140px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

</style>
<body>
<div class="container">
    <div class="main-top">
        <%--<h3>案件详情</h3>--%>
    </div><!--main-top-->

        <%--<c:if test="${caseDetailsDto.loanApplication !=null}">--%>

            <%--<c:forEach items="${caseDetailsDto.details}" var="item">--%>
                <%--<div class="stepItem" style="margin-left: 10px">--%>
                    <%--<div class="stepDate"><jsp:setProperty name="dateValue" property="time" value="${item.followTime}"/><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/></div>--%>
                    <%--<div class="stepDot"><div class="stepDot-dot finished"></div></div>--%>
                    <%--<div class="stepDetail"><div class="stepDetail-title">${item.caseStateStr}<br>(${item.followDesc})<br>--%>
                    <%--<c:if test="${item.list.size()>0}"><a href="javascript:showFiles('${item.id}');">查看上传资料</a></c:if>--%>
                    <%--</div><div class="stepDetail-detail"></div></div>--%>
                <%--</div>--%>
            <%--</c:forEach>--%>
        <%--</c:if>--%>
        <%--<c:if test="${caseDetailsDto.agentApply !=null}">--%>
            <%--<table class="table"  style="margin-left: 10px">--%>

                <%--<tr>--%>
                    <%--<td class="active" colspan="3">案件进度：</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
            <c:forEach items="${caseDetailsDto.details}" var="item">
                <div class="stepItem" style="margin-left: 10px">
                    <div class="stepDate"><jsp:setProperty name="dateValue" property="time" value="${item.followTime}"/><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                    <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                    <div class="stepDetail"><div class="stepDetail-title">${item.caseStateStr}<br>(${item.followDesc})</div><div class="stepDetail-detail"></div></div>
                </div>
            </c:forEach>
        <%--</c:if>--%>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function allotCaseManager(caseId,userId,nickName){
       if(confirm('确认分配吗？')){
           ajaxSubmit("${ctx}/case/allotCaseManager",{"caseId":caseId,"userId":userId,"nickName":nickName},function(v,e,p){
               if(e.data.code==='0000'){
                    alert("分配成功");
                   parent.location.reload();
               }else{
                   alert("分配失败");
               }
           })
       }
   }

   function showFiles(id){
       openDialog({
           frame:true,
           title:"",
           height:500,
           width:900,
           url:"${ctx}/case/caseFilesShow?id="+id
       });
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
