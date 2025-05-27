<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function onBack(id,enumId){
//            $("#f").remove();
          /*  $.ajax({
                url: '${ctx}/case/selectFileMidByEnum?id='+id+"&enumId="+enumId,
                method:'POST',
                success: function(res){
                    console.log(res);
                }
            })*/
            ajaxSubmit("${ctx}/share/selectFilesAddress",{"id":id,"catalogId":enumId},function(v,e,p){
                if(e.data.code==='0000'){

                    var vl = "";
                    for(var i = 0; i < e.data.results.length; i++){
                        var json = e.data.results[i];
                         vl += "<div class='col-sm-3'>";
                         vl += "<a href='"+json.filePath+"' target='_blank'><img id='infImg' src='"+json.filePath+"' width='100' height='100'><br/></a>";
                         vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'>";
                         vl += " </div>";
                    }
                    $("#f").html(vl);
                    if(! e.data.results.length) $('#btnSubmit').addClass('hidden')
                    else $('#btnSubmit').removeClass('hidden')
                }else{
                }
            })
        }
    </script>
</head>
<body>
<div class="main">
    <div class="row">

        <div class="panel panel-info col-sm-2">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul class="list-group">
                       <c:forEach items="${menuList}" var="e">
                               <%--<input type="button" class="list-group-item" onclick="" value="${e.enumName}"><br/>--%>
                            <input type="button" class="list-group-item" onclick="onBack(${id},${e.enumCode})" value="${e.enumName}">
                       </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <form method="post" action = "${ctx}/download" id="editFrom">
            <div class="col-sm-10 " id="result">
                <div class="row panel panel-info" id = "files">
                    <div class="col-sm-12 row" id="f" style="margin:5px 0">
                        <c:forEach items="${addressList}" var="file">
                            <div class="col-sm-3">
                                <a href="${file.filePath}" target="_blank"><img id="infImg" src="${file.filePath}" width="100" height="100"><br/></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="files" value="${file.filePath}">
                            </div>
                        </c:forEach>

                    </div>

                    <div class="row">
                        <div class="col-sm-1 col-sm-offset-9" id="btnSubmit"><button type="submit"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 下载单证</button></div>
                    </div>

                </div>
                </div>

        </form>
        </div>
    </div>
</div><!--main end-->
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