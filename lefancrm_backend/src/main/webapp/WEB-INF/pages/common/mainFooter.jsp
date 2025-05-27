<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
var ctx="${ctx}";

//在线预览
var onlinePreview = function(path){
    var last = path.lastIndexOf(".");
    var ext = path.substr(last + 1);
    if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx'){
        path = "https://view.officeapps.live.com/op/view.aspx?src=" + path;
    }
    openDialog({
        frame:true,
        title:"预览",
        height:600,
        width:800,
        url:path
    });
}
</script>