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
    <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="btnCode" value="${btnCode}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="consignorId" value="${consignorId}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">委托人姓名</th>
                    <td width="70%">
                        ${userName}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位简称</th>
                    <td width="70%">
                        ${surveyConsignor.company}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位全称</th>
                    <td width="70%">
                       ${surveyConsignor.name}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">委托部门</th>
                    <td width="70%">
                        <select class="form-control" style="display: inline-flex" name="departmentId" id="departmentId" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${surveyConsignor.surveyConsignorDepartment}" var="item">
                                <option value="${item.id}" <c:if test="${surveyConsigner.departmentId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">邮箱</th>
                    <td width="70%">
                        <input type="text" id = "email" name="email" class="form-control" value="${surveyConsigner.email}"  required="required">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">抄送邮件集合</th>
                    <td width="70%">
                        <input type="text" id = "makeEmails" name="makeEmails" class="form-control" value="${surveyConsigner.makeEmails}">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位区域类别</th>
                    <td width="70%">
                        <c:if test="${surveyConsignor.areaType ==1}">省</c:if>
                        <c:if test="${surveyConsignor.areaType ==2}">市</c:if>
                        <c:if test="${surveyConsignor.areaType ==3}">区</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位区域名称</th>
                    <td width="70%">
                        ${surveyConsignor.areaName}
                    </td>
                </tr>
                </tr>
                <tr>
                    <th width="30%" class="active">委托方类别</th>
                    <td width="70%">
                        <c:if test="${surveyConsignor.type ==1}">合作伙伴</c:if>
                        <c:if test="${surveyConsignor.type ==2}">散户</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">材料凭证</th>
                    <td>
                        <div>
                            <input type="hidden" id="materialImgs" name="materialImgs" value="" />
                            <div id="materialImgsDiv" style="display: none"></div>
                            <div id="deleteDiv" style="display: none">
                                <input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>
                            </div>
                        </div>
                        <div>
                            <input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=entrust"><br>
                            <%--<input type="file" multiple>--%>
                        </div>

                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" onclick="goBack()"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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

    //材料凭证
    var value = "";
    var id = 0;
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            id = id + 1;
            var r  = data.result;
            var file = r.surveyFile;
            var vSrc = "";
            var v = file.filePath;
            if(file.fileExt == "txt"){
                vSrc = "${ctx}/img/txt.png";
            }else if(file.fileExt == "docx" || file.fileExt == "doc"){
                vSrc = "${ctx}/img/word.png";
            }else if(file.fileExt == "xls" || file.fileExt == "xlsx"){
                vSrc = "${ctx}/img/excel.png";
            }else if(file.fileExt == "pdf"){
                vSrc = "${ctx}/img/pdf.jpg";
            }else{
                vSrc = file.filePath;
            }
            $("#materialImgsDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");
            value += file.filePath+ ',';

            $("#materialImgs").val(value);
            $("#materialImgsDiv").show();
            $("#deleteDiv").show();

        }
    });


    var divs = [];
    function del(){
        var chks = document.getElementsByName('chknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                divs.push($("#div_" + chk.id));
            }
        }
        for(var i = 0 ; i < divs.length ;i++){
            divs[i].remove();
        }
        $("#materialImgs").val(value);
    }
</script>
</body>
</html>