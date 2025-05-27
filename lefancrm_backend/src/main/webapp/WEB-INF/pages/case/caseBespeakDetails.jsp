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
                        <c:if test="${caseBespeak.type==1}">贷款申请</c:if>
                        <c:if test="${caseBespeak.type==2}">代理申请</c:if>
                        <c:if test="${caseBespeak.type==3}">伤残预估</c:if>
                        <c:if test="${caseBespeak.type==4}">其他</c:if>
                    </td>
                </tr>
                <tr>
                    <td width="20%"  class="active">碰面时间：</td>
                    <td width="80%" colspan="1"><fmt:formatDate value="${caseBespeak.meetTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td width="20%"  class="active">碰面地点：</td>
                    <td width="80%" colspan="1">${caseBespeak.meetAddress}</td>
                </tr>
                <tr>
                    <td width="20%"  class="active">碰面备注：</td>
                    <td width="80%" colspan="1">${caseBespeak.meetContent}</td>
                </tr>
                <tr>
                    <td width="20%"  class="active">创建时间：</td>
                    <td width="80%" colspan="1"><fmt:formatDate value="${caseBespeak.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
