<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/caseApply/forwardSubmit" method="post">
        <input name="id" value="${id}" hidden="true">
        <input name="userName" value="${userName}" hidden="true">
        <input name="phone" value="${phone}" hidden="true">
        <input name="caseProvince" value="${caseProvince}" hidden="true">
        <input name="caseProvinceId" value="${caseProvinceId}" hidden="true">
        <input name="caseCity" value="${caseCity}" hidden="true">
        <input name="caseCityId" value="${caseCityId}" hidden="true">
        <input name="caseDistrict" value="${caseDistrict}" hidden="true">
        <input name="caseDistrictId" value="${caseDistrictId}" hidden="true">
        <input name="caseAddress" value="${caseAddress}" hidden="true">
        <input name="userId" value="${userId}" hidden="true">
        <c:if test="${dangerTime!=null&&dangerTime!=''&&dangerTime!='null'}">
        <input name="dangerTime" value="${dangerTime}" hidden="true">
        </c:if>
        <c:if test="${dangerTime==null||dangerTime==''||dangerTime=='null'}">
            <input name="type" value="${type}" hidden="true">
        </c:if>
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">案件类型</th>
                    <td width="80%">
                        <select id = "caseType" name="caseType" style="width: 170px;" class="form-control" onchange="choiceCaseType()">
                            <option value="1">医疗费垫付</option>
                            <option value="2">赔偿款垫付</option>
                            <option value="3">代办理赔</option>
                        </select>
                    </td>
                </tr>
                <tr id="tr_isTrafficAccident">
                    <th width="20%" class="active">交通事故</th>
                    <td width="80%">
                        <select id = "isTrafficAccident" name="isTrafficAccident"  style="width: 170px;" class="form-control">
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </td>
                </tr>
                <tr id="tr_loanMoney">
                    <th class="active"><strong class="necessary"> </strong>申请金额</th>
                    <td><input name="loanMoney" value="${loanMoney}" type="number" class="form-control" style="width: 170px"/></td>
                </tr>
                <tr id="tr_agentType" style="display: none">
                    <th width="20%" class="active">代理类型</th>
                    <td width="80%">
                        <select id = "agentType" name="agentType"  style="width: 170px;" class="form-control">
                            <option value="1">交通事故索赔</option>
                            <option value="2">工伤事故索赔</option>
                            <option value="3">寿险索赔</option>
                            <option value="4">车辆损失索赔</option>
                            <option value="5">保险拒赔</option>
                            <option value="6">意外保险</option>
                            <option value="7">其他侵权</option>
                            <option value="8">援助服务</option>
                        </select>
                    </td>
                </tr>
                <tr id="tr_claimIndemnityDesc" style="display: none">
                    <th class="active"><strong class="necessary"> </strong>详细需求</th>
                    <td><textarea name="claimIndemnityDesc" style="height: 100px;width: 250px" class="form-control"></textarea></td>
                </tr>
                <c:if test="${isDangerTime}">
                    <tr>
                        <th class="active"><strong class="necessary"> </strong>出险时间</th>
                        <td><input style="width: 180px" name="dangerTime" type="text" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly></td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>


<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
//        $("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    function choiceCaseType(){
        var a = $("#caseType").val();
        if(a==1 || a==2){
            $("#tr_isTrafficAccident").show();
            $("#tr_loanMoney").show();
            $("#tr_agentType").hide();
            $("#tr_claimIndemnityDesc").hide();
        }else{
            $("#tr_isTrafficAccident").hide();
            $("#tr_loanMoney").hide();
            $("#tr_agentType").show();
            $("#tr_claimIndemnityDesc").show();
        }
    }

</script>
</body>
</html>