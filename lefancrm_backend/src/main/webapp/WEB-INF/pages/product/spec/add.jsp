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

</script>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/productSpec/add" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden"  name="productId"  value="${productId}">
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>规格名称</th>
                    <td width="80%"><input required  name="specName"  class="form-control"></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>规格展示图片</th>
                    <td width="80%"><input required id="adPic" type="hidden" value="${item.specImg}" name="specImg">
                        <img id="infImg" src="${item.specImg}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=lfOfficial">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>价格</th>
                    <td width="80%">
                        <input type="text" id = "specPrice" name="specPrice" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>成本价</th>
                    <td width="80%">
                        <input type="text" id = "specCost" name="specCost" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>库存</th>
                    <td width="80%">
                        <input type="text" id = "specStock" name="specStock" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>销量</th>
                    <td width="80%">
                        <input type="text" id = "specSales" name="specSales" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交
            </button>
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