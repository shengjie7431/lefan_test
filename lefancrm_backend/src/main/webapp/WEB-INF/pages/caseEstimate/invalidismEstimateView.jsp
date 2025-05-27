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
    <input type="hidden" name="id" value="${invalidismEstimate.id}">
    <div class="title">

        <button class="butList defuelt" onclick="file('${invalidismEstimate.id}')">查看伤残资料</button>
        <button class="butList defuelt" onclick="updateReson('${invalidismEstimate.id}')">查看回复</button>
        <c:if test="${invalidismEstimate.turnStatus == 0}">
            <button class="butList active" onclick="forward('${invalidismEstimate.id}','${invalidismEstimate.userName}','${invalidismEstimate.userPhone}',
                '${invalidismEstimate.accidentProvince}','${invalidismEstimate.accidentProvinceId}','${invalidismEstimate.accidentCity}',
                '${invalidismEstimate.accidentCityId}','${invalidismEstimate.accidentDistrict}',
                '${invalidismEstimate.accidentDistrictId}','${invalidismEstimate.accidentAddress}',null,'${invalidismEstimate.userId}')">转办案件中心</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>用户姓名</td>
                    <td>${invalidismEstimate.userName}</td>
                    <td>用户电话</td>
                    <td>${invalidismEstimate.userPhone}</td>
                    <td>预估状态</td>
                    <td>
                        <c:if test="${invalidismEstimate.state == 1}">待预估</c:if>
                        <c:if test="${invalidismEstimate.state == 2}">已预估</c:if>
                    </td>
                </tr>
                <tr>
                    <td>所在省</td>
                    <td>${invalidismEstimate.accidentProvince}</td>
                    <td>所在市</td>
                    <td>${invalidismEstimate.accidentCity}</td>
                    <td>所在县</td>
                    <td>${invalidismEstimate.accidentDistrict}</td>
                </tr>
                <tr>
                    <td>事故发生地</td>
                    <td>${invalidismEstimate.accidentAddress}</td>
                    <td>事故性质</td>
                    <td>
                        <c:if test="${invalidismEstimate.accidentType == 1}">交通事故</c:if>
                        <c:if test="${invalidismEstimate.accidentType == 2}">工伤事故</c:if>
                        <c:if test="${invalidismEstimate.accidentType == 3}">意外事故</c:if>
                    </td>
                    <td>创建人</td>
                    <td>${invalidismEstimate.createBy}</td>
                </tr>
                <tr>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${invalidismEstimate.createTime}" pattern="yyyy-mm-dd HH:MM" /></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${invalidismEstimate.updateTime}" pattern="yyyy-mm-dd HH:MM" /></td>
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

    var file = function(id){
        openDialog({
            frame:true,
            title:"伤残等级预估图片资料",
            height:550,
            width:800,
            url:"${ctx}/invalidism/queryFile?id="+id
        });
    }

    var updateReson = function(id){
        openDialog({
            frame:true,
            title:"伤残等级预估回复",
            height:750,
            width:900,
            url:"${ctx}/invalidism/toReport?id="+id
        });
    }

    function forward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress,dangerTime,userId){
        openDialog({
            frame:true,
            title:"转办案件中心",
            height:550,
            width:800,
            url:"${ctx}/caseApply/caseApplyForward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                    "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress+"" +
                    "&dangerTime="+dangerTime+"&type="+1+"&userId="+userId
        });
    }
</script>
</body>
</html>