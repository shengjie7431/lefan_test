<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function onBack(){
//            var val = $("#roles").val();
//            if(val == null || val == ""){
//                alert("请选择角色类型");
//                return false;
//            }
            var checked = [];
            $('input:checkbox:checked').each(function() {
                checked.push($(this).val());
            });
            if(checked == null || checked == ""){
                alert("请选择角色类型！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">

    <form class="form-horizontal" id="editForm" role="form" action="${ctx}/user/setOrgAdmin" method="post">
        <input type="hidden" name="userId" value="${userId}">
        <div class="form-group row">
            <label  class="col-sm-2 control-label" >机构角色</label>
            <div class="col-sm-10 row">
                <c:forEach items="${bsInfo}" var="bs">
                    <div class="col-sm-3">
                        <c:set var="index" value="-1"></c:set>
                        <c:forEach items="${roles}" var="role">
                            <c:if test="${bs.id == role.roleId}">
                                <c:set var="index" value="1"></c:set>
                            </c:if>
                        </c:forEach>
                        <input type="checkbox" name = "roles" <c:if test="${index > -1}">checked="checked"</c:if> value="${bs.id}">${bs.roleName}
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" onclick="return onBack();" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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