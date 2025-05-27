<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/message/addMessage" method="post">
        <input type="hidden" name="id" value="${infoDetail.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary"> </strong>信息内容</th>
                    <td width="80%"> <textarea class="form-control" name="content" id="content" style="width: 400px;height: 130px;" ></textarea></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary"> </strong>发送状态</th>
                    <td width="80%"> <select name="sendState" id="sendState"  class="form-control" onchange="changeDisplay()">
                        <option value="0">定时发送</option>
                        <option value="1">立即发送</option>

                    </select></td>
                </tr>
                <tr id="sendTimeTr">
                    <th width="20%" class="active"><strong class="necessary"> </strong>定时发送时间</th>
                    <td width="80%"> <input name="sendTime" type="text"  value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly class="form-control"></td>
                </tr>
                <tr id="reciveName">
                    <th width="20%" class="active"><strong class="necessary"> </strong>接收者</th>
                    <td width="80%">
                        <div class="row" style="display: flex">
                            <div class="col-md-12">
                                <div class="form-group row">
                                   <label  class="col-sm-2 control-label" ></label>
                                    <div class="col-sm-10 row">
                                        <div>
                                            <input type="button" value="全选" onclick="selectUser(1)"/>  &nbsp;
                                            <input type="button" value="全不选" onclick="selectUser(0)"/>  &nbsp;
                                        <%--<input type="button" value="反选" onclick="reverseSelect()"/>--%>
                                        </div>
                                        <div style="">
                                        <c:forEach items="${results}" var="item">
                                            <div style="width:20%;float: left;"><input type="checkbox" name = "userIds" value="${item.userId}">${item.userName}</div>
                                        </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="modal-footer">
            <button onclick="return onBack();" type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
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
   /* var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });*/

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val(r.imgUrl);
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

   function changeDisplay(){
       var flag = $("#sendState").val();
       if(flag == 1){
            $("#sendTimeTr").hide();
       }else{
           $("#sendTimeTr").show();
       }
   }
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
           alert("请选择消息接收者！");
           return false;
       }
       return true;
   }

    function selectUser(state){
       if(state == 1){//全选
           $('[name = userIds]:checkbox').prop("checked",true);
       }else{
           $('[name = userIds]:checkbox').prop("checked",false);
       }
   }
//    function reverseSelect(){
//        $("[name = userIds]:checkbox").each(function(){
//            $(this).attr("checked", !($(this).attr("checked")));
//        });
//    }
</script>
</body>
</html>