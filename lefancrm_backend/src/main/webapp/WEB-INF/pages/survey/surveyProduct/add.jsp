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
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${surveyProduct.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">名称</th>
                    <td width="70%">
                        <input type="text" id = "name" name="name" value="${surveyProduct.name}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">价格</th>
                    <td width="70%">
                        <input type="text" id = "price" name="price" value="${surveyProduct.price}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">乐凡币</th>
                    <td width="70%">
                        <input type="text" id = "lefanCoin" name="lefanCoin" value="${surveyProduct.lefanCoin}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">商品图片</th>
                    <td>
                        <input required id="adPic" type="hidden" name="proFile">
                        <img id="infImg" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=product">
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active" >调查员级别</th>
                    <td width="80%">
                        <%--<c:forEach items="${surveyProduct.levels}" var="level">--%>
                            <%--<c:if test="${level.levelId == userOrgId}">${level.levelName}--%>
                                <%--<input type="hidden" name="levelId" id="levelId" value="${level.levelId}">--%>
                                <%--<input type="hidden" name="levelName" id="levelName" value="${level.levelName}" required="required"></c:if>--%>
                        <%--</c:forEach>--%>
                        <input id="levelName" name="levelName" class="form-control">
                        <input type="hidden" id="levelId" name="levelId">

                        <input type="button" onclick="backLevel();" value="选择级别">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active" >角色</th>
                    <td width="80%">
                        <input id="roleName" name="roleName" class="form-control">
                        <input type="hidden" id="roleId" name="roleId">

                        <input type="button" onclick="backRole();" value="选择角色">
                    </td>
                </tr>

                <tr>
                    <th class="active"><strong class="necessary"> </strong>详情描述</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea class="form-control" name="content" id="content" style="width: 720px;height: 700px;" > ${surveyProduct.remark}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        $("#content").text(editor1.html());
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

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var file=r.surveyFile;
//            if (r.success == true){
                $("#infImg").attr("src",file.filePath);
                $("#adPic").val(file.filePath);
//            }else {
//                alert("上传失败，请重试111");
//            }
        }
    });

    var backLevel = function(){
        openDialog({
            frame:true,
            title:"调查员级别",
            height:500,
            width:800,
            url:"${ctx}/surveyProduct/selectLevel?surveyCode=level"
        });
    }

    var backRole = function(){
        openDialog({
            frame:true,
            title:"级别list",
            height:500,
            width:800,
            url:"${ctx}/surveyProduct/selectRole"
        });
    }
</script>
</body>
</html>