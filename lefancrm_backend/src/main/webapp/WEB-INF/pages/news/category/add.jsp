<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<script type="text/javascript">
   function add(){
       var catSort = $("#catSort").val();
       catSort = parseInt(catSort) + 1;
       $("#catSort").val(catSort);
       return false;
   }
    function del(){
        var catSort = $("#catSort").val();
        if(catSort <= 1){
            $("#catSort").val(1);
        }else{
            catSort = catSort - 1;
            $("#catSort").val(catSort);
        }
        return false;
    }

 </script>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/newsCategory/add" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">新闻分类名称</th>
                    <td width="80%">
                       <input type="text" id = "catName" name="catName"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">新闻分类类别</th>
                    <td width="80%">
                        <select id = "catType" name="catType"  style="width: 120px;" class="form-control">
                            <option value="1">新闻动态</option>
                            <option value="2">服务案例</option>
                            <option value="3">招聘信息</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">分类排序</th>
                    <td width="80%" class="form-inline">
                        <button onclick="return add()"class="btn btn-default btn-xs btn-primary">+</button>
                        <input type="text" id = "catSort" name="catSort"  style="width: 50px;" class="form-control" value="1">
                        <button onclick="return del()"class="btn btn-default btn-xs btn-primary">—</button>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">新闻分类说明</th>
                    <td width="80%">
                        <input type="text" id = "catDesc" name="catDesc" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
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

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                var val = "<div class='imgWrap'><img  src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' width='100' height='100'>" +
                        "<i class='glyphicon glyphicon-remove'  onclick='deleteImage(this,-1)'></i></div>";
               $("#d1").append(val);
//                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                var img_url = $("#adPic").val();
                if(img_url == null || img_url == ""){
                    img_url = "http://ddrapi.shlefan.com/sftp/files/"+pathImg;
                    $("#adPic").val(img_url);
                }else{
                    img_url += ",http://ddrapi.shlefan.com/sftp/files/"+pathImg;
                    $("#adPic").val(img_url);
                }
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
    function deleteImage(dom,dex){
        var r=confirm("你确定删除此图片!");
        if (r==true) {
            var img_url = $("#adPic").val();
            var arr = img_url.split(",");
            var del = $(dom).siblings('img').attr("src");
            img_url = "";
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] != del) {
                    img_url += arr[i] + ",";
                }
                if (i == (arr.length - 1)) {
                    img_url = img_url.substring(0, img_url.length - 1);
                }
            }
            $(dom).parent().remove();
            $("#adPic").val(img_url);
            alert("删除成功！");
        }
    }
</script>
</body>
</html>