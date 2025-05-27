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

            var checked = [];
            $('input:checkbox:checked').each(function() {
                checked.push($(this).val());
            });
            if(checked == null || checked == ""){
                alert("请选择机构角色！");
                return false;
            }
            return true;
        }

    </script>
    <style>
        .myColSm3{
            width: 30%;!important;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
   <form id="editForm" role="form" class="form-horizontal" action="${ctx}/user/addUser" method="post">
        <div class="form-group">
            <table class="table" >
                <tbody>
                <tr>
                    <th width="20%" class="active">用户头像</th>
                    <td>
                        <input required id="adPic" type="hidden" value="${newUser.img}" name="img">
                        <img id="infImg" src="${newUser.img}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户真实姓名</th>
                    <td width="80%">
                        <input type="text" id = "userName" name="userName" value="${newUser.userName}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户类型</th>
                    <td width="80%">
                        <select name="userType" style="width: 400px;" class="form-control" id = "userType">
                        <option value="1" <c:if test="${newUser.userType == 1}">selected </c:if>>普通用户</option>
                        <option value="2" <c:if test="${newUser.userType == 2}">selected </c:if>>机构用户</option>
                        <option value="3" <c:if test="${newUser.userType == 3}">selected </c:if>>测试用户</option>
                        <option value="4" <c:if test="${newUser.userType == 4}">selected </c:if>>其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户昵称</th>
                    <td width="80%">
                       <input type="text" id = "nickName" name="nickName"  style="width: 400px;" class="form-control" value="${newUser.nickName}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户电话</th>
                    <td width="80%">
                        <input type="text" id = "userTel" name="userTel" style="width: 400px;" class="form-control" value="${newUser.userTel}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">邮箱</th>
                    <td width="80%">
                        <input type="text" id = "email" name="email"  style="width: 400px;" class="form-control" value="${newUser.email}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户状态</th>
                    <td width="80%">
                        <select id = "userState" name="userState"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${newUser.userState == 0}">selected </c:if>>正常</option>
                            <option value="1" <c:if test="${newUser.userState == 1}">selected </c:if>>禁用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">性别</th>
                    <td id="sex" width="80%">
                        男<input type="radio"  name="sex" value="0" style="width: 50px;" checked <c:if test="${newUser.sex == 0}">checked </c:if>>
                        女<input type="radio"   name="sex" value="1" style="width: 50px;" <c:if test="${newUser.sex == 1}">checked </c:if>>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户地址</th>
                    <td width="80%">
                        <input type="text" id = "userAddress" name="userAddress" style="width: 400px;" class="form-control" value="${newUser.userAddress}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active" >所属机构</th>
                    <td width="80%">
                        <%--<select name="orgId" id="orgId" style="width: 400px;" class="form-control">--%>
                            <c:forEach items="${apiRsp.results}" var="org">
                                <%--<option value="${org.id}">${org.orgName}</option>\--%>
                                <c:if test="${org.id == userOrgId}">${org.orgName}
                                    <input type="hidden" name="orgId" id="orgId" value="${org.id}">
                                    <input type="hidden" name="orgName" id="orgName" value="${org.orgName}"></c:if>
                            </c:forEach>
                        <%--</select>--%>
                        <input type="button" onclick="backOrg();" value="选择机构">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">机构角色</th>
                    <td width="80%">
                        <div class="form-group">
                            <div class="col-sm-10 row">
                                <c:forEach items="${bsInfo}" var="bs">
                                    <div class="col-sm-3 myColSm3">
                                        <input type="checkbox" name = "roles" value="${bs.id}">${bs.roleName}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  onclick="return groBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>

      <%-- <form class="form-horizontal" id="editForm" role="form" action="${ctx}/user/setOrgAdmin" method="post">
           <input type="hidden" name="userId" value="${userId}">
           <div class="form-group">
               <label  class="col-sm-2 control-label" >机构角色</label>
               <div class="col-sm-10 row">
                   <c:forEach items="${bsInfo}" var="bs">
                       <div class="col-sm-3 myColSm3">
                           <input type="checkbox" name = "roles" value="${bs.id}">${bs.roleName}
                       </div>
                   </c:forEach>
               </div>
           </div>
           <div class="modal-footer">
               <button type="submit" onclick="return onBack();" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
           </div>
       </form>--%>
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

    var backOrg = function(){
        var img = $("#adPic").val();
        var userName = $("#userName").val();
        var userType = $("#userType").val();
        var nickName = $("#nickName").val();
        var userTel = $("#userTel").val();
        var email = $("#email").val();
        var userState = $("#userState").val();
        var sex = $('#sex input[name="sex"]:checked ').val();
        var userAddress = $("#userAddress").val();
        openDialog({
            frame:true,
            title:"",
            height:600,
            width:550,
            url:"${ctx}/org/treeList?img="+img+"&userName="+userName+"&userType="+userType
                    +"&nickName="+nickName+"&userTel="+userTel+"&email="+email
                    +"&userState="+userState+"&sex="+sex+"&userAddress="+userAddress+"&type=1"
        });
    }
</script>
</body>
</html>