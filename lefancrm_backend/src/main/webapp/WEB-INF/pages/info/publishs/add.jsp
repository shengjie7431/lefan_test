<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
<form id="editForm" role="form" action="${ctx}/info/publishs/save" method="post">
    <input type="hidden" name="isPublish" value="1">
    <div class="form-group">
        <table class="table">
            <tbody>
                <tr>
                    <th width="20%" class="active">姓名</th>
                    <td width="80%">
                        <input type="text" id="userName" name="userName" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">手机号</th>
                    <td width="80%">
                        <input type="text" id="userTel" name="userTel" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">身份证号</th>
                    <td width="80%">
                        <input type="text" id="userCardid" name="userCardid" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">协助保司</th>
                    <td width="100%" style="display: flex;">
                        <input type="hidden" id="safeCompanys" name="safeCompanys">
                        <textarea placeholder="请选择......" rows="2" id="safeCompanyNames" name="safeCompanyNames"
                                  style="width: 400px;display: inline-block; background-color:rgba(0, 0, 0, 0.07);"
                                  class="form-control" required="required" onfocus="this.blur();"></textarea>
                        <%--<input type="text" id="safeCompanyNames" name="safeCompanyNames"--%>
                               <%--style="width: 400px;display: inline-block; background-color: #d3d3d3;"--%>
                               <%--class="form-control" required="required" onfocus="this.blur();">--%>
                        <input type="button" value="选  择" onclick="selectCompany(null,'add')"  style="width: 78px;  height: 32px;margin-top: 21px;margin-left: 5px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">其他信息</th>
                    <td width="80%">
                        <textarea  id="userOtherInfo" name="userOtherInfo" style="width: 400px; height: 150px" class="form-control"></textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">问题描述</th>
                    <td width="80%">
                        <textarea  id="problemRemark" name="problemRemark" required="required" style="width: 400px; height: 150px" class="form-control"></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 发布</button>
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

    function selectCompany(id,choose){
        openDialog({
            frame:true,
            title:"选择",
            height:500,
            width:900,
            url:"${ctx}/info/publishs/selectCompany?choose=" + choose
        });
    }

    //材料凭证
    var value = "";
    $('#lawFileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
            if(ext == "txt"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/txt.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "docx" || ext == "doc"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/word.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "xls" || ext == "xlsx"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/excel.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "pdf"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/pdf.jpg' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + p
                athImg + ',';
            }else{
                $("#uploadDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }
            $("#uploadPaths").val(value);
            $("#uploadDiv").show();

        }
    });

</script>
</body>
</html>
