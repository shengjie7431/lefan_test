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
<script>
    function  queryCatAll(){
        var catType = $("#catType").val();
        if(catType == null || catType == ''){
            $("#subType option").remove();
            $("#subType").append("<option value='0'>请选择</option>");
            return;
        }
        ajaxSubmit("${ctx}/newsCategory/queryAll",{"catType":catType},function(v,e,p){
            $("#subType option").remove();
            $("#subType").append("<option value='0'>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#subType").append("<option value='"+val.id+"'>"+val.catName+"</option>");
            }

        })
    }
    function  queryCat(){
        var subType = $("#subType").val();
        $("#catId").val(subType);
    }
</script>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/news/add" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>新闻标题</th>
                    <td width="80%"><input required  name="title" id="title" class="form-control"></td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>新闻类型:</th>
                    <td width="80%">
                        <select id="catType" name="catType"  style="width:120px;" class="form-control" onclick="queryCatAll()">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${item.newsCategory.catType == 1}">selected="selected" </c:if>>新闻动态</option>
                            <option value="2" <c:if test="${item.newsCategory.catType == 2}">selected="selected" </c:if>>服务案例</option>
                            <option value="3" <c:if test="${item.newsCategory.catType == 3}">selected="selected" </c:if>>招聘信息</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>新闻子类型:</th>
                    <td width="80%">
                        <select id = "subType"   style="width:120px;" onclick="queryCat()" class="form-control">
                            <option value="">请选择</option>
                        </select>
                        <input id="catId" name="catId" type="hidden" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>公开时间:</th>
                    <td width="80%">

                        <input name="publishTime" type="text"  class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>新闻展示图</th>
                    <td width="80%"><input required id="adPic" type="hidden" value="${item.newsImg}" name="newsImg">
                        <img id="infImg" src="${item.newsImg}" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=lfOfficial">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>新闻概述</th>
                    <td width="80%">
                        <%--<input type="text" id = "outline" name="outline" class="form-control">--%>
                            <textarea name="outline" id="outline"  class="form-control">${item.outline}</textarea>
                    </td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong><strong class="necessary">*</strong>新闻内容</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="context" id="context"  class="form-control"></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="context"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor?moduleName=lfOfficial'
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
        var outline = $("#outline").val();
        if(outline.length > 100){
            alert("新闻概述长度超出100个字符！");
            return false;
        }
        var title = $("#title").val();
        if(title.length > 75){
            alert("新闻标题长度超出75个字符！");
            return false;
        }
        $("#context").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>