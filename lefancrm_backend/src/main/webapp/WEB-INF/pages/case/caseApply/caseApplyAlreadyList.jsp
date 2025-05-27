<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>咨询案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>咨询案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                </th>
                <th width="120">案件编号</th>
                <th width="120">姓名</th>
                <th width="90">手机号</th>
                <th width="130">报案状态</th>
                <th width="160">案件来源</th>
                <th width="100">车牌号</th>
                <th width="200">救治医院</th>
                <th width="160">伤者信息</th>
                <th width="120">他方是</th>
                <th width="120">我方是</th>
                <th width="130">是否垫付</th>
                <th width="200">事故时间</th>
                <th width="200">报案时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>${item.userName}</td>
                    <td>${item.phone}</td>
                    <td><c:if test="${item.state == 0}">报案</c:if>
                        <c:if test="${item.state == 1}">受理</c:if>
                        <c:if test="${item.state == 2}">转案件中心</c:if>
                        <c:if test="${item.state == 3}">已放弃</c:if>
                    </td>
                    <td>
                        <c:if test="${item.caseSources == 'lefanmp'}">小程序</c:if>
                        <c:if test="${item.caseSources == 'baidu'}">百度商桥咨询</c:if>
                        <c:if test="${item.caseSources == 'tydl'}">统一代理</c:if>
                        <c:if test="${item.caseSources == 'autohome'}">汽车之家</c:if>
                        <c:if test="${item.caseSources == 'tel'}">400电话咨询</c:if>
                        <c:if test="${item.caseSources == 'wechat'}">微信公众号咨询</c:if>
                    </td>
                    <td>${item.carNumber}</td>
                    <td>${item.cureHospital}</td>
                    <td><c:if test="${item.injuryType == 0}">我撞了人</c:if>
                        <c:if test="${item.injuryType == 1}">我被撞了</c:if></td>
                    <td><c:if test="${item.otherType == 0}">机动车</c:if>
                        <c:if test="${item.otherType == 1}">非机动车</c:if></td>
                    <td><c:if test="${item.weType == 0}">机动车</c:if>
                        <c:if test="${item.weType == 1}">非机动车</c:if></td>
                    <td><c:if test="${item.isNeedAdvance == 0}">不需要</c:if>
                        <c:if test="${item.isNeedAdvance == 1}">需要</c:if></td>
                    <td><fmt:formatDate value="${item.dangerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:caseApplyView('${item.id}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/caseApply/caseApplyList?userName=${userName}&phone=${phone}&state=${state}&caseNo=${caseNo}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var addCase = function(){
        openDialog({
            frame:true,
            title:"添加新案件",
            height:700,
            width:800,
            url:"${ctx}/caseApply/caseApplyAdd"
        });
    }

    function selectCaseDetails(applyId){
        openDialog({
            frame:true,
            title:"客服跟踪记录",
            height:500,
            width:1000,
            url:"${ctx}/caseApply/followList?applyId="+applyId
        });
    }

    function showOperatorFollowInfoEdit(applyId){
        openDialog({
            frame:true,
            title:"添加跟踪记录",
            height:500,
            width:1000,
            url:"${ctx}/caseApply/showOperatorFollowInfoEdit?applyId="+applyId
        });
    }

    function updateState(id,state){
        ajaxSubmit("${ctx}/caseApply/editState",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
    }
   function forward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress,dangerTime){
       openDialog({
           frame:true,
           title:"转办案件中心",
           height:500,
           width:1000,
           url:"${ctx}/caseApply/forward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                   "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress+"" +
                   "&dangerTime="+dangerTime+"&isDangerTime="+false
       });
   }

    var caseApplyView = function(id){
        openDialog({
            frame:true,
            title:"案件详情",
            height:600,
            width:1000,
            url:"${ctx}/caseApply/caseApplyView?id="+id
        });
    }

    var caseApplyAlreadyList = function(state){
        openDialog({
            frame:true,
            title:"案件详情",
            height:700,
            width:1000,
            url:"${ctx}/caseApply/caseApplyAlreadyList?state="+state
        });
    }
</script>
</body>
</html>
