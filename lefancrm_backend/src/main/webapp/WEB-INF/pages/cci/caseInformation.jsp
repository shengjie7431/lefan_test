<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="btn-group">
        <button onclick="edit('<fmt:formatDate value="${apiRsp.results.crmCustomerFollows.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','<fmt:formatDate value="${apiRsp.results.crmCustomerFollows.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','${apiRsp.results.crmCustomerFollows.followAddress}','${apiRsp.results.crmCustomerFollows.followDesc}');" type="button" class="btn btn-default">编辑</button>
    </div>
    <div class="form-group">
        <table class="table">
            <tbody>
            <tr>
                <th width="20%" class="active">案件来源</th>
                <td width="80%">
                <c:if test="${apiRsp.results.caseSource==0}">医院</c:if>
                <c:if test="${apiRsp.results.caseSource==1}">小程序</c:if>
                <c:if test="${apiRsp.results.caseSource==2}">工作室</c:if>
                <c:if test="${apiRsp.results.caseSource==3}">保司</c:if>
                <c:if test="${apiRsp.results.caseSource==4}">交警</c:if>
                <c:if test="${apiRsp.results.caseSource==5}">陌拜</c:if>
                <c:if test="${apiRsp.results.caseSource==6}">护工</c:if>
                <c:if test="${apiRsp.results.caseSource==7}">转介</c:if>
                <c:if test="${apiRsp.results.caseSource==8}">其他</c:if>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否意向</th>
                <td width="80%">
                    ${apiRsp.results.isIntention==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">案件类型</th>
                <td width="80%">
                    <c:if test="${apiRsp.results.caseType==1}">简易代理</c:if>
                    <c:if test="${apiRsp.results.caseType==2}">案件代理</c:if>
                    <c:if test="${apiRsp.results.caseType==3}">代理+垫付</c:if>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">案件进度</th>
                <td width="80%">
                    <c:if test="${apiRsp.results.caseProgress==1}">初访</c:if>
                    <c:if test="${apiRsp.results.caseProgress==2}">洽谈中</c:if>
                    <c:if test="${apiRsp.results.caseProgress==3}">待签约</c:if>
                    <c:if test="${apiRsp.results.caseProgress==4}">已签约</c:if>
                    <c:if test="${apiRsp.results.caseProgress==5}">暂时搁置</c:if>
                    <c:if test="${apiRsp.results.caseProgress==6}">已放弃</c:if>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">初访日期</th>
                <td width="80%">
                    <fmt:formatDate value="${apiRsp.results.firstVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">案件索赔金额</th>
                <td width="80%">
                    ${apiRsp.results.claimFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">贷款金额</th>
                <td width="80%">
                    ${apiRsp.results.loanFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">案件服务费金额</th>
                <td width="80%">
                    ${apiRsp.results.serviceFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">下次跟进时间</th>
                <td width="80%">
                    <fmt:formatDate value="${apiRsp.results.crmCustomerFollows.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">创建时间</th>
                <td width="80%">
                    <fmt:formatDate value="${apiRsp.results.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">更新时间</th>
                <td width="80%">
                    <fmt:formatDate value="${apiRsp.results.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

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

    function edit(followTime,nextFollowTime,followAddress,followDesc){
        openDialog({
            frame:true,
            title:"编辑案件信息",
            height:400,
            width:1000,
            url:"${ctx}/cci/caseInformationEdit?customerId=${id}&customerName=${name}&caseSource=${apiRsp.results.caseSource}&isIntention=${apiRsp.results.isIntention}&caseType=${apiRsp.results.caseType}&caseProgress=${apiRsp.results.caseProgress}&claimFee=${apiRsp.results.claimFee}&loanFee=${apiRsp.results.loanFee}&serviceFee=${apiRsp.results.serviceFee}&nextTime=${apiRsp.results.nextTime}&createTime=${apiRsp.results.createTime}&updateTime=${apiRsp.results.updateTime}&followTime="+followTime+"&nextFollowTime="+nextFollowTime+"&followAddress="+followAddress+"&followDesc="+followDesc+""
        });
    }
</script>
</body>
</html>
