<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>意外险激活</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baokuAccident/edit" method="post">
        <div class="form-group">
            <table class="table">
               <%-- <input type="hidden" name="userId" value="${userId}"/>
                <input type="hidden" name="orderId" value="${orderId}"/>--%>
                <input type="hidden" name="orderCode" value="${orderCode}"/>
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>保险保单号</th>
                    <td width="80%"><input type="text" class="form-control" name="invoiceNumber" placeholder="请输入保险保单号单号" required="required"></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>起保时间</th>
                    <td width="80%">

                        <input name="startDate" type="text"  class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
               <%-- <tr>
                    <th class="active"><strong class="necessary">*</strong>物流公司</th>
                    <td><input type="text" class="form-control" id="logisticCompany" name="logisticCompany" placeholder="请输入物流公司" required="required"></td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary">*</strong>联系人</th>
                    <td><input type="text" class="form-control" id="linkMan" name="linkMan" placeholder="请输入联系人" required="required"></td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary">*</strong>联系电话</th>
                    <td><input type="text" class="form-control" id="linkTel" name="linkTel" placeholder="请输入联系电话" required="required"></td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary">*</strong>送货地址</th>
                    <td><input type="text" class="form-control" id="userAddress" name="userAddress" placeholder="请输入送货地址" required="required"></td>
                </tr>--%>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
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
<script src="${ctx}/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function(){
        $("#editForm").bind('submit', function(event) {
            ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
            event.preventDefault();
        });
    });
    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert('激活成功!');
        }else{
            alert('激活失败!');
        }
        reloadParent();
    }
</script>
</body>
</html>