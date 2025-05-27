<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function groBack(){

//            var val = $("#orgId").find("option:selected").text();
            //$("#orgName").attr("value",val);
//            $("#orgName").val(val);
        }

    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/user/userInfoUpdate" method="post">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">用户头像</th>
                    <td>
                        <input required id="adPic" type="hidden" value="${user.img}" name="img">
                        <img id="infImg" src="${user.img}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage">

                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户真实姓名</th>
                    <td width="80%">
                        <input type="text" id = "userName" name="userName" value="${user.userName}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户昵称</th>
                    <td width="80%">
                       <input type="text" id = "nickName" name="nickName" value="${user.nickName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户类型</th>
                    <td width="80%">
                    <select name="userType"style="width: 400px;" class="form-control">
                        <option value="1" <c:if test="${user.userType == 1}">selected="selected" </c:if>>普通用户</option>
                        <option value="2" <c:if test="${user.userType == 2}">selected="selected" </c:if>>机构用户</option>
                        <option value="3" <c:if test="${user.userType == 3}">selected="selected" </c:if>>测试用户</option>
                        <option value="4" <c:if test="${user.userType == 4}">selected="selected" </c:if>>其他</option>
                    </select>
                </td>
                <tr>
                    <th width="20%" class="active">邮箱</th>
                    <td width="80%">
                        <input type="text" id = "email" name="email" value="${user.email}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">职业</th>
                    <td width="80%">
                        <select id = "job" name="job"  style="width: 400px;" class="form-control">
                            <option value="1" >护士</option>
                            <option value="2" >医生</option>
                            <option value="3" >护工</option>
                            <option value="4" >调解组织</option>
                            <option value="5" >其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否是推广人</th>
                    <td width="80%">
                        <select id = "isPromoter" name="isPromoter"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${user.isPromoter == 0}">selected = selected</c:if>>否</option>
                            <option value="1" <c:if test="${user.isPromoter == 1}">selected = selected</c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">性别</th>
                    <td width="80%">
                        男<input type="radio"  name="sex" value="0" style="width: 50px;" <c:if test="${user.sex == 0}">checked = "checked"</c:if>>
                        女<input type="radio"  name="sex" value="1" style="width: 50px;" <c:if test="${user.sex == 1}">checked = "checked"</c:if>>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户地址</th>
                    <td width="80%">
                        <input type="text" id = "userAddress" name="userAddress" value="${user.userAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">所属机构</th>--%>
                    <%--<td width="80%">--%>
                        <%--<select name="orgId" onclick="groBack()" id="orgId" style="width: 400px;" class="form-control" <c:if test="${user.bsRoleId == 1}" >disabled</c:if>>--%>
                            <%--<option value="0">未分配</option>--%>
                            <%--<c:forEach items="${orgs}" var="org">--%>
                                <%--<option value="${org.id}"<c:if test="${user.orgId == org.id}">selected = selected</c:if>>${org.orgName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--<input type="hidden" name="orgName" id="orgName" value="">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" onclick="groBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>