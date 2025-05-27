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
    <form id="editForm" role="form" action="${ctx}/positionLevel/positionLevelUpdate" method="post">
        <input type="hidden" name="id" id="id" value="${positionLevel.id}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">职级代码</th>
                    <td width="70%">
                        <input type="text" id = "levelCode" name="levelCode" value="${positionLevel.levelCode}"  style="width: 400px;" class="form-control" readonly="true">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">职级描述</th>
                    <td width="70%">
                        <input type="text" id = "levelDesc" name="levelDesc" value="${positionLevel.levelDesc}" style="width: 400px;" class="form-control" readonly="true">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">基本工资</th>
                    <td width="70%">
                        <input type="text" id = "baseWages" name="baseWages" value="${positionLevel.baseWages}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">考评工资</th>
                    <td width="70%">
                        <input type="text" id = "evaWages" name="evaWages" value="${positionLevel.evaWages}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">父级</th>
                    <td width="70%">
                        <select name="parentId" id ="parentId" class="form-control">
                            <c:forEach items="${positionLevelList}" var="item1">
                                <option value="${item1.id}" <c:if test="${positionLevel.parentId == item1.id}">selected="selected" </c:if>>${item1.levelCode}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">管理佣金指标</th>
                    <td width="70%">
                        <input type="text" id = "managerComrateId" name="managerComrateId" value="${positionLevel.managerComrateId}" style="width: 400px;" class="form-control" readonly="true">
                        <button type="button" class="btn btn-default" data-dismiss="modal"  onclick="javascript:choiceMCI();">选择管理佣金指标</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
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
     * 选择“管理佣金指标”
     */
    var choiceMCI = function(){
        var id = $("#id").val();
        var levelCode = $("#levelCode").val();
        var levelDesc = $("#levelDesc").val();
        var baseWages = $("#baseWages").val();
        var evaWages = $("#evaWages").val();
        var parentId = $("#parentId").val();
        openDialog({
            frame:true,
            title:"佣金指标数据",
            height:550,
            width:1200,
            url:"${ctx}/positionLevel/choiceManagerComrateInfo?levelCode=" +levelCode+"&levelDesc="+levelDesc
                    +"&levelDesc="+levelDesc+"&baseWages="+baseWages+"&parentId="+parentId+"&evaWages="+evaWages+"&id="+id
        });
    }
</script>
</body>
</html>