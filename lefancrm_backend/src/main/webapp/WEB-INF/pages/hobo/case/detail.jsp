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
        <input type="hidden" name="btnCode" value="save">
        <input type="hidden" name="id" value="${dto.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">客户名称</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="insureName" name="insureName" value="${dto.insureName}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">部门</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="insureDeptName" name="insureDeptName" value="${dto.insureDeptName}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">姓名</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="name" name="name" value="${dto.name}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">身份证号</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="idCard" name="idCard" value="${dto.idCard}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">手机号</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="tel" name="tel" value="${dto.tel}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">车牌号</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="carNo" name="carNo" value="${dto.carNo}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">产品名称</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="proName" name="proName" value="${dto.proName}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">产品单价</th>
                    <td width="80%">
                        <input type="text" readonly style="cursor: default" id="proPrice" name="proPrice" value="${dto.proPrice}"  class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">产品内容</th>
                    <td width="80%">
                        <textarea style="width: 883px; height: 208px;cursor: default" readonly>${dto.proText}</textarea>
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active">委托日期</th>
                    <td width="80%">
                        <input readonly name="entrustTime" autocomplete="off" type="text" class="form-control" placeholder="委托日期" style="cursor: default; background-color:#fff"
                               value="<fmt:formatDate value="${dto.entrustTime}" pattern="yyyy-MM-dd"/>">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
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