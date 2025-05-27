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
        <input type="hidden" name="id" value="${surveyConsigner.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">用户姓名</th>
                    <td width="70%">
                        <input type="text" id = "userName" name="userName" value="${surveyConsigner.userName}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位</th>
                    <td width="70%">
                       <input type="text" id = "company" name="company" value="${surveyConsigner.company}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构部门</th>
                    <td width="70%">
                        <select class="form-control" required="required" style="width: 400px;" name="departmentId" id="departmentId">
                            <option value="">请选择</option>
                            <c:forEach items="${surveyConsignorDepartment}" var="item">
                                <option value="${item.id}" <c:if test="${surveyConsigner.departmentId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">联系电话</th>
                    <td width="70%">
                        <input type="text" id = "tel" name="tel" value="${surveyConsigner.tel}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">邮箱</th>
                    <td width="70%">
                        <input type="text" id = "email" name="email" value="${surveyConsigner.email}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位名称</th>
                    <td width="70%">
                        <input type="text" id = "entrustOrgName" name="entrustOrgName" value="${surveyConsigner.entrustOrgName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">认证状态</th>
                    <td width="70%">
                        <select name="authState" class="form-control" style="width: 400px;" required="required">
                            <option value="0" <c:if test="${surveyConsigner.authState==0}">selected="selected" </c:if>>未认证</option>
                            <option value="1" <c:if test="${surveyConsigner.authState==1}">selected="selected" </c:if>>认证中</option>
                            <option value="2" <c:if test="${surveyConsigner.authState==2}">selected="selected" </c:if>>认证通过</option>
                            <option value="3" <c:if test="${surveyConsigner.authState==3}">selected="selected" </c:if>>认证不通过</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">账户状态</th>
                    <td width="70%">
                        <select name="accState" class="form-control" style="width: 400px;" required="required">
                            <option value="0" <c:if test="${surveyConsigner.accState==0}">selected="selected" </c:if>>正常</option>
                            <option value="1" <c:if test="${surveyConsigner.accState==1}">selected="selected" </c:if>>冻结</option>
                            <option value="2" <c:if test="${surveyConsigner.accState==2}">selected="selected" </c:if>>删除</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">抄送邮件集合</th>
                    <td width="70%">
                        <input type="text" id = "makeEmails" name="makeEmails" value="${surveyConsigner.makeEmails}" style="width: 400px;" class="form-control">
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
</script>
</body>
</html>