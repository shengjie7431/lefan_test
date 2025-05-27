<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <script>

    </script>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${caseApplyInfo.id}">
    <div class="title">
        <button class="butList defuelt" onclick="caseApplyToAlready('${caseApplyInfo.id}', 4)">标记已处理</button>
        <button class="butList active" onclick="caseApplyForward('${caseApplyInfo.id}','${caseApplyInfo.userName}','${caseApplyInfo.phone}','${caseApplyInfo.caseProvince}','${caseApplyInfo.caseProvinceId}','${caseApplyInfo.caseCity}','${caseApplyInfo.caseCityId}','${caseApplyInfo.caseDistrict}','${caseApplyInfo.caseDistrictId}','${caseApplyInfo.caseAddress}');">转办案件中心</button>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>用户姓名</td>
                    <td>${caseApplyInfo.userName}</td>
                    <td>用户电话</td>
                    <td>${caseApplyInfo.phone}</td>
                    <td>案件编号</td>
                    <td>${caseApplyInfo.caseNo}</td>
                </tr>
                <tr>
                    <td>是否需要垫付</td>
                    <td>
                        <c:if test="${caseApplyInfo.isNeedAdvance == 0}">否</c:if>
                        <c:if test="${caseApplyInfo.isNeedAdvance == 1}">是</c:if>
                    </td>
                    <td>报案时间</td>
                    <td><fmt:formatDate value="${caseApplyInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>案件来源</td>
                    <td>
                        <c:if test="${caseApplyInfo.caseSources == 'lefanmp'}">小程序</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'baidu'}">百度商桥咨询</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'tydl'}">统一代理</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'autohome'}">汽车之家</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'tel'}">400电话咨询</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'wechat'}">微信公众号咨询</c:if>
                        <c:if test="${caseApplyInfo.caseSources == 'lefanweb'}">官方网站</c:if>
                    </td>
                </tr>
                <tr>
                    <td>报案案件状态</td>
                    <td>
                        <c:if test="${caseApplyInfo.state == 0}">报案</c:if>
                        <c:if test="${caseApplyInfo.state == 1}">受理</c:if>
                        <c:if test="${caseApplyInfo.state == 2}">转案件中心</c:if>
                        <c:if test="${caseApplyInfo.state == 3}">放弃</c:if>
                    </td>
                    <td>车牌号</td>
                    <td>${caseApplyInfo.carNumber}</td>
                    <td>救治医院</td>
                    <td>${caseApplyInfo.cureHospital}</td>
                </tr>
                <tr>
                    <td>伤者信息</td>
                    <td>
                        <c:if test="${caseApplyInfo.injuryType == 0}">我撞了人</c:if>
                        <c:if test="${caseApplyInfo.injuryType == 1}">我被撞了</c:if>
                    </td>
                    <td>他方是</td>
                    <td>
                        <c:if test="${caseApplyInfo.otherType == 0}">机动车</c:if>
                        <c:if test="${caseApplyInfo.otherType == 1}">非机动车</c:if>
                    </td>
                    <td>我方是</td>
                    <td>
                        <c:if test="${caseApplyInfo.weType == 0}">机动车</c:if>
                        <c:if test="${caseApplyInfo.weType == 1}">非机动车</c:if>
                    </td>
                </tr>
                <tr>
                    <td>所在省</td>
                    <td>${caseApplyInfo.caseProvince}</td>
                    <td>所在市</td>
                    <td>${caseApplyInfo.caseCity}</td>
                    <td>所在县</td>
                    <td>${caseApplyInfo.caseDistrict}</td>
                </tr>
                <tr>
                    <td>事故发生地</td>
                    <td>${caseApplyInfo.caseAddress}</td>
                    <td>事故时间</td>
                    <td><fmt:formatDate value="${caseApplyInfo.dangerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td></td>
                    <td></td>
                </tr>


                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    /**
     * 审核通过
     */
    function editState(id,state,userId){
        ajaxSubmit("${ctx}/agent/editState",{"id":id,"state":state,"userId":userId},reload,"审核成功！","确认通过审核？","审核失败！");
    }

    function caseApplyForward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress){
        openDialog({
            frame:true,
            title:"转办案件中心",
            height:450,
            width:700,
            url:"${ctx}/caseApply/caseApplyForward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                    "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress
        });
    }

    /**
     * 标记已处理
     */
    function caseApplyToAlready(id,state){
        ajaxSubmit("${ctx}/caseApply/caseApplyToAlready",{"id":id,"state":state},reload,"处理成功！","确认标记处理？","处理失败！");
    }
</script>
</body>
</html>