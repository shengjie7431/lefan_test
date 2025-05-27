<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>报案列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>报案列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseApply/list" method="post">
                    <div class="form-group">
                        案件状态: <select name="state"  class="form-control">
                        <option value="" >全部</option>
                        <option value="0">报案</option>
                        <option value="1">受理</option>
                        <option value="2">转案件中心</option>
                    </select>
                    </div>
                    <div class="form-group">
                       是否垫付: <select name="isNeedAdvance"  class="form-control">
                            <option value="" >全部</option>
                            <option value="0">不需要</option>
                            <option value="1">需要</option>
                        </select>
                        </div>
                        <%----%>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        姓名: <input name="userName" type="text"  value="${userName}" class="form-control">
                        </div><br><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        电话: <input name="phone" type="text"  value="${phone}" class="form-control">
                        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        车牌号: <input name="carNumber" type="text"  value="${carNumber}" class="form-control">
                        </div>
                    <div class="form-group">
                        救治医院: <input name="cureHospital" type="text"  value="${cureHospital}" class="form-control">
                        </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addCase()" type="button" class="btn btn-default">添加新案件</button>&nbsp; &nbsp;
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
                <th width="110">事故省</th>
                <th width="110">事故市</th>
                <th width="110">事故区</th>
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
                    <td>${item.caseProvince}</td>
                    <td>${item.caseCity}</td>
                    <td>${item.caseDistrict}</td>
                    <td><c:if test="${item.isNeedAdvance == 0}">不需要</c:if>
                        <c:if test="${item.isNeedAdvance == 1}">需要</c:if></td>
                    <td><fmt:formatDate value="${item.dangerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.state!=2&&(item.caseNo ==null || item.caseNo =='')&& item.state != 3}">
                        <a href="javascript:forward('${item.id}','${item.userName}','${item.phone}','${item.caseProvince}','${item.caseProvinceId}','${item.caseCity}','${item.caseCityId}'
                        ,'${item.caseDistrict}','${item.caseDistrictId}','${item.caseAddress}','<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>');">转办案件中心</a>
                            <a href="javascript:updateState('${item.id}',3);">放弃案件</a>
                            <a href="javascript:selectCaseDetails('${item.id}');">客服跟踪记录</a>&nbsp;
                            <a href="javascript:showOperatorFollowInfoEdit('${item.id}');">客服跟踪</a>
                        </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/caseApply/list?userName=${userName}&phone=${phone}&state=${state}&carNumber=${carNumber}&cureHospital=${cureHospital}&caseNo=${caseNo}&isNeedAdvance=${isNeedAdvance}" />
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
            url:"${ctx}/caseApply/toAdd"
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
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
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
</script>
</body>
</html>
