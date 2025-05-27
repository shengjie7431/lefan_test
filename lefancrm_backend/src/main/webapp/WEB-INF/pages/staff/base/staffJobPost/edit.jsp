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
    <form id="editForm" role="form" action="${ctx}/staff/update" method="post">
        <input type="hidden" name="surveyCode" id="surveyCode" value="${surveyCode}">
        <input type="hidden" name="id" id="id" value="${staffJobPost.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">名称</th>
                    <td width="70%">
                        <input type="text" id = "name" name="name" value="${staffJobPost.name}"  required="required" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">人力资源成本属性</th>
                    <td width="70%">
                        <%-- <div id="organType"></div>--%>
                        <select class="form-control" required="required" name="costType" id="costType">
                            <option value="1" <c:if test="${staffJobPost.costType == 1}"> selected="selected" </c:if>>主营成本</option>
                            <option value="2" <c:if test="${staffJobPost.costType == 2}"> selected="selected" </c:if>>销售费用</option>
                            <option value="3" <c:if test="${staffJobPost.costType == 3}"> selected="selected" </c:if>>管理费用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">状态</th>
                    <td width="70%">
                        <%-- <div id="organType"></div>--%>
                        <select class="form-control" required="required" name="state" id="state">
                            <option value="0" <c:if test="${staffJobPost.state == 0}"> selected="selected" </c:if>>启用</option>
                            <option value="1" <c:if test="${staffJobPost.state == 1}"> selected="selected" </c:if>>停用</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" name="btnUpload" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $("[name=btnUpload]").attr("disabled","disabled");
        ajaxFormSubmit(this,reloadParent,null,null,null);
        setTimeout(function(){
            $("[name=btnUpload]").removeAttr("disabled");
        },3000)
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

    function selectName(jobPostId,btnCode,obj){
        var result = obj.value;
        if(result ==null || result==""){
            return;
        }
        ajaxSubmit("${ctx}/staff/selectStaffInfoByRelationId",{"jobPostId":jobPostId,"surveyCode":'${surveyCode}',"btnCode":btnCode,"name":result},function(v,e,p){
            if(e.data.results != null){
                alert("名称不可重复！");
                $("[name=btnUpload]").attr("disabled","disabled");
                return;
            }else{
                $("[name=btnUpload]").removeAttr("disabled");
            }
        });
    }

</script>
</body>
</html>