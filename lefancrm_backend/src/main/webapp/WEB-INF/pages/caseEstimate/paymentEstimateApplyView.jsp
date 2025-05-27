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
    <input type="hidden" name="id" value="${paymentEstimateApply.id}">
    <div class="title">

        <button class="butList defuelt" onclick="getreport('${paymentEstimateApply.id}')">查看测算报告</button>
        <c:if test="${paymentEstimateApply.caseId == null && paymentEstimateApply.turnStatus == 0}">
            <button class="butList active" onclick="forward('${paymentEstimateApply.id}','${paymentEstimateApply.userName}','${paymentEstimateApply.userPhone}',
                '${paymentEstimateApply.accidentProvince}','${paymentEstimateApply.accidentProvinceId}','${paymentEstimateApply.accidentCity}',
                '${paymentEstimateApply.accidentCityId}','${paymentEstimateApply.accidentDistrict}',
                '${paymentEstimateApply.accidentDistrictId}','${paymentEstimateApply.accidentAddress}',null,'${paymentEstimateApply.userId}')">转办案件中心</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>用户姓名</td>
                    <td>${paymentEstimateApply.userName}</td>
                    <td>用户电话</td>
                    <td>${paymentEstimateApply.userPhone}</td>
                    <td>案件类型</td>
                    <td>
                        <c:if test="${paymentEstimateApply.caseType == 1}">医疗费垫付</c:if>
                        <c:if test="${paymentEstimateApply.caseType == 2}">理赔款垫付</c:if>
                    </td>
                </tr>
                <tr>
                    <td>所在省</td>
                    <td>${paymentEstimateApply.accidentProvince}</td>
                    <td>所在市</td>
                    <td>${paymentEstimateApply.accidentCity}</td>
                    <td>所在县</td>
                    <td>${paymentEstimateApply.accidentDistrict}</td>
                </tr>
                <tr>
                    <td>事故发生地</td>
                    <td>${paymentEstimateApply.accidentAddress}</td>
                    <td>我方事故责任</td>
                    <td>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 1}">全部责任</c:if>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 2}">主要责任</c:if>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 3}">同等责任</c:if>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 4}">次要责任</c:if>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 5}">无责任</c:if>
                        <c:if test="${paymentEstimateApply.myAccidentLiability == 6}">责任无法认定</c:if>
                    </td>
                    <td>我方责任</td>
                    <td>
                        <c:if test="${paymentEstimateApply.myStatus == 1}">肇事方</c:if>
                        <c:if test="${paymentEstimateApply.myStatus == 2}">受害方</c:if>
                    </td>
                </tr>
                <tr>
                    <td>我方交通状态</td>
                    <td>
                        <c:if test="${paymentEstimateApply.myTarfficStatus == 1}">机动车</c:if>
                        <c:if test="${paymentEstimateApply.myTarfficStatus == 2}">非机动车</c:if>
                        <c:if test="${paymentEstimateApply.myTarfficStatus == 3}">行人</c:if>
                    </td>
                    <td>对方交通状态</td>
                    <td>
                        <c:if test="${paymentEstimateApply.otherTarfficStatus == 1}">机动车</c:if>
                        <c:if test="${paymentEstimateApply.otherTarfficStatus == 2}">非机动车</c:if>
                        <c:if test="${paymentEstimateApply.otherTarfficStatus == 3}">行人</c:if>
                    </td>
                    <td>创建人</td>
                    <td>${paymentEstimateApply.createBy}</td>
                </tr>
                <tr>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${paymentEstimateApply.createTime}" pattern="yyyy-mm-dd HH:MM" /></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${paymentEstimateApply.updateTime}" pattern="yyyy-mm-dd HH:MM" /></td>
                </tr>
                </tbody>
            </table>
        </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
   </div>
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

    var getreport = function(id){
        openDialog({
            frame:true,
            title:"查看测算报告",
            height:500,
            width:900,
            url:"${ctx}/case/risk/paymentEstimateReportList?id="+id+"&mType="+1
        });
    }

    function forward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress,dangerTime,userId){
        openDialog({
            frame:true,
            title:"转办案件中心",
            height:500,
            width:800,
            url:"${ctx}/caseApply/caseApplyForward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                    "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress+"" +
                    "&dangerTime="+dangerTime+"&type="+2+"&userId="+userId
        });
    }
</script>
</body>
</html>