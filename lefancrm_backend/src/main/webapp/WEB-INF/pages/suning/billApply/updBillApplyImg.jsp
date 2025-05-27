<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>



    </script>
</head>

<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/suning/billApply/upd" method="post">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="yhc" value="${yhc}">
        <input type="hidden" name="type" value="${type}">
        <input type="hidden" name="billMoney" value="${billMoney}">
        <input type="hidden" name="operatorType" value="${operatorType}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">发票凭证</th>
                    <td>
                        <%--<input required id="adPic" type="hidden" value="${billingApply.img}" name="img">--%>
                        <%--<img id="infImg" src="${billingApply.img}" width="80" height="80">--%>
                        <input required id="adPic" type="hidden" name="img" value="${img}">
                        <img id="infImg" width="80" height="80" src="${img}">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${caseNo}">
                    </td>

                </tr>
                <tr>
                    <th width="20%" class="active">发票单号</th>
                    <td>
                        <%--<input type="text" id = "billingCode" name="billingCode" value="${billingApply.billingCode}" style="width: 400px;" class="form-control">--%>
                        <input type="text" value="${code}" id = "billingCode" name="billingCode" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">发票金额</th>
                    <td>
                        <c:if test="${operatorType != null && operatorType == 'hc'}">
                            <input type="number"  disabled name="billingMoney"  value="${-billMoney}" max="10000000" style="width: 400px;" class="form-control" required="required">
                        </c:if>
                        <c:if test="${operatorType == null || operatorType == '' || operatorType != 'hc'}">
                            <input type="number"  name="billingMoney"  max="100000" value="${money}" style="width: 400px;" class="form-control" required="required">
                        </c:if>
                    </td>
                </tr>
                <c:if test="${operatorType == null || operatorType == '' || operatorType == 'hc'}">
                    <tr>
                        <th width="20%" class="active">开票时间</th>
                        <td>
                            <input id="createTime" name="createTime" type="text" class="form-control" required="required" style="width: 400px;cursor: auto; background-color:#fff"
                                   value="${createTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <c:if test="${operatorType != null && operatorType == 'upd'}">
                <button type="button"  class="btn btn-default delete-img" data-dismiss="modal" id="diglog_close_btn-js"><span class="glyphicon glyphicon-ok"></span> 删除</button>
            </c:if>
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>


<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

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
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,function (e,res) {
           alert(res.data.msg)
        });
        event.preventDefault();
    });

    $(".delete-img").bind('click', function(event) {
        $.ajax({
            url: '${ctx}/suning/billApply/upd',
            data: {
                id:$("input[name=id]").val(),
                operatorType:'del'
            },
            success: function (res,r) {
                closeDialog();
                parent.location.reload();
            }
        })
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
     * 开票页面
     */
    function uploadBillApplyImg(id,caseNo) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 450,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id + "&caseNo"+caseNo
        });
    }

</script>
</body>
</html>