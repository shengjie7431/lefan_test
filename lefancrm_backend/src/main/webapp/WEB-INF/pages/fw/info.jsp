<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>

</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/fw/operate" method="post">
        <input type="hidden" name="btnCode" value="save">
        <input type="hidden" name="id" value="${surveyFwCase.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">客户名称</th>
                    <td width="80%">
                        <input type="text" id="khmc" name="khmc" value="${surveyFwCase.khmc}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票名称</th>
                    <td width="80%">
                        <input type="text" id="kpmc" name="kpmc" value="${surveyFwCase.kpmc}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">支付日期</th>
                    <td width="80%">
                        <input name="zfrq" autocomplete="off" type="text" class="form-control" placeholder="支付日期" style="cursor: auto; background-color:#fff"
                               value="<fmt:formatDate value="${surveyFwCase.zfrq}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'}) ">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">姓名</th>
                    <td width="80%">
                        <input type="text"  name="xm" value="${surveyFwCase.xm}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">手机号码</th>
                    <td width="80%">
                        <input type="text" name="sjhm" value="${surveyFwCase.sjhm}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">身份证号码</th>
                    <td width="80%">
                        <input type="text" name="sfzhm" value="${surveyFwCase.sfzhm}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收款账号</th>
                    <td width="80%">
                        <input type="text" name="skzh" value="${surveyFwCase.skzh}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开户银行</th>
                    <td width="80%">
                        <input type="text" name="khyh" value="${surveyFwCase.khyh}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收入金额</th>
                    <td width="80%">
                        <input type="number" step="0.01" name="srje" value="${surveyFwCase.srje}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">金额</th>
                    <td width="80%">
                        <input type="number" step="0.01" name="je" value="${surveyFwCase.je}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票金额</th>
                    <td width="80%">
                        <input type="number" step="0.01" name="kpje" value="${surveyFwCase.kpje}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">供应商</th>
                    <td width="80%">
                        <input type="text" name="gys" value="${surveyFwCase.gys}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">咨询服务公司</th>
                    <td width="80%">
                        <select name="fwgs" class="form-control"  required="required">
                            <option value="公估" <c:if test="${surveyFwCase.fwgs=='公估'}">selected="selected" </c:if>>公估</option>
                            <option value="健康" <c:if test="${surveyFwCase.fwgs=='健康'}">selected="selected" </c:if>>健康</option>
                            <option value="金融" <c:if test="${surveyFwCase.fwgs=='金融'}">selected="selected" </c:if>>金融</option>
                            <option value="乐欲" <c:if test="${surveyFwCase.fwgs=='乐欲'}">selected="selected" </c:if>>乐欲</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        $('.btn-success').attr('disabled',true)
        event.preventDefault();
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
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
</script>
</body>
</html>