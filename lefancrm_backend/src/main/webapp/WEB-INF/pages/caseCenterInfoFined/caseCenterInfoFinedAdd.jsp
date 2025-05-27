<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/caseCenterInfoFined/caseCenterInfoFinedSave" method="post">
        <input type="hidden" name="caseId" value="${caseId}">
        <input type="hidden" name="caseType" value="${caseType}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">是否支付:</th>
                    <td width="70%">
                        <input type="radio"  name="isFined" value="1" style="width: 50px;" onclick="hiddenFinedType()">支付
                        <input type="radio"  name="isFined" value="0" style="width: 50px;" checked onclick="showFinedMoney()">不支付
                    </td>
                </tr>
                <tr id="hiddenFinedType" >
                    <th width="30%" class="active">扣款选项:</th>
                    <td width="70%">
                        <select name="finedType" id="finedType" class="form-control" onchange="onChangeFinedType()" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${commonFineEnumDto}" var="item" >
                                <option value="${item.id}" >${item.fineEnumName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr id="hiddenFinedMoney">
                    <th width="30%" class="active">扣款金额:</th>
                    <td width="70%">
                        <input type="text" id = "finedMoney" name="finedMoney" value="${finedMoney}" style="width: 400px;" class="form-control" readonly="true">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
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

    function hiddenFinedType(){
        $("#hiddenFinedType").hide();
        $("#finedType").val('');
        $("#hiddenFinedMoney").hide();
        $("#finedType").attr("required",null);
    }

    function showFinedMoney(){
        $("#hiddenFinedType").show();
        $("#hiddenFinedMoney").show();
        $("#finedMoney").val('');
        $("#finedType").attr("required","required");
    }

    function onChangeFinedType(){
        var objS = document.getElementById("finedType");
        var finedType = objS.options[objS.selectedIndex].value;

        if(finedType == ''){
            $("#finedMoney").val('');
        }
        $.ajax({
            url:'${ctx}/caseCenterInfoFined/seachInfoByFinedType?finedType='+finedType,
            type:"Get",
            success:function(res,param){
                $("#finedMoney").val(param.data.fineMoney);

            }
        });
    }
</script>
</body>
</html>