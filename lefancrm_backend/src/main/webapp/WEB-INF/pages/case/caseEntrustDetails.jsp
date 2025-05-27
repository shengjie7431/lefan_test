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
<div class="main administrator">
    <div class="main-top">
        <%--<h3>案件详情</h3>--%>
    </div><!--main-top-->


            <table class="table" style="margin-left: 10px">
                <tr>
                    <td width="20%"  class="active">案件类型：</td>
                    <td width="80%" colspan="1">
                        <c:if test="${caseEntrust.type==1}">贷款申请</c:if>
                        <c:if test="${caseEntrust.type==2}">代理申请</c:if>
                        <c:if test="${caseEntrust.type==3}">伤残预估</c:if>
                        <c:if test="${caseEntrust.type==4}">其他</c:if></td>
                </tr>
                <c:if test="${caseEntrust.entrustState == 1}">
                <tr>
                    <td width="20%"  class="active">预计可赔金额：</td>
                    <td width="80%" colspan="1">${caseEntrust.estimatedAmount}元</td>
                </tr>
                <tr>
                    <td width="20%"  class="active">收费比例：</td>
                    <td width="80%" colspan="1">${caseEntrust.chargePro}%
                    </td>
                </tr>
                <tr>
                    <td width="20%"  class="active">收费金额：</td>
                    <td width="80%" colspan="1">${caseEntrust.chargeMoney}元</td>
                </tr>
                </c:if>
                <tr>
                    <td width="20%"  class="active">状态：</td>
                    <c:if test="${caseEntrust.entrustState ==1}"><td width="80%" colspan="1">同意委托</td></c:if>
                    <c:if test="${caseEntrust.entrustState ==2}"><td width="80%" colspan="1">虚假信息</td></c:if>
                    <c:if test="${caseEntrust.entrustState ==3}"><td width="80%" colspan="1">继续跟进</td></c:if>
                    <c:if test="${caseEntrust.entrustState ==4}"><td width="80%" colspan="1">放弃</td></c:if>
                </tr>
                <c:if test="${caseEntrust.entrustState == 3 || caseEntrust.entrustState == 4}">
                <tr>
                    <td width="20%"  class="active">面谈内容：</td>
                    <td width="80%" colspan="1">${caseEntrust.speakContent}</td>
                </tr>
                </c:if>
                <c:if test="${caseEntrust.entrustState == 2 || caseEntrust.entrustState == 3 || caseEntrust.entrustState == 4}">
                <tr>
                    <td width="20%" class="active">原因：</td>
                    <td width="80%" colspan="1">${caseEntrust.failReason}</td>
                </tr>
                </c:if>
                <tr>
                    <td width="20%" class="active">创建时间：</td>
                    <td width="80%" colspan="1"><fmt:formatDate value="${caseEntrust.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </table>
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
