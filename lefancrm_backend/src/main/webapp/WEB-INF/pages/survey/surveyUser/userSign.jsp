<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

</head>
<body>

<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="userId" value="${userId}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="surveyCode" value="userSign" />

        <div class="form-group">
            <table class="table">
                <tbody>
                    <tr>
                    <tr>
                        <th width="30%" class="active">手写签名</th>
                        <td>
                            <input required id="adPic" type="hidden" name="proFile">
                            <c:if test="${surveyUserSign.signImg!=null}">
                                <img  width="80" height="80" src="${surveyUserSign.signImg}" class="picToBig">
                            </c:if>
                            <img id="infImg" width="80" height="80" src="" class="picToBig" style="display: none">
                            <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=userSign">
                        </td>
                    </tr>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>

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
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    function validFile(btnCode){
        if(btnCode == 'upload' || btnCode == 'primary'){
            var path = $("#path").val();
            if(!path){
                alert("请上传报告");return false;
            }
        }else if(btnCode == 'direction' || btnCode == 'successDirection'){
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);
            $("#files").val(JSON.stringify(files));
            if(btnCode == 'successDirection'){
                $('#successDirection').val('success');
            }else{
                $('#successDirection').val(null);
            }
        }else if(btnCode == 'case-return'){
            var reasonType ="";
            $("div[name='reasons']").each(function(i, obj){
                if($(obj).attr("class") == 'd-type active'){
                    reasonType = $(obj).attr('data-code');
                }
            });
            if(reasonType == 1){
                $("#reason").val("不在我负责的区域范围");
            }else if(reasonType == 2){
                $("#reason").val("工作量饱和，来不及调查");
            }else if(reasonType == 3){
                var reasonThree = $("#reasonThree").val()
                if(!reasonThree){
                    alert("请输入退回原因");return false;
                }
                $("#reason").val(reasonThree);
            }
        }else if (btnCode == 'primary-veto') {
            var vetos = [];
            $("input:checkbox[name='chkIds']:checked").each(function() { // 遍历name
                var id = $(this).val();
                var type = $(this).attr("data-type");
                var opinion = $("#opinion" + id + "" + type).val()
                vetos.push({
                    id : id,
                    type : type,
                    opinion : opinion
                });
            });
            if (vetos.length == 0){
                alert("未选中任何数据");
                return false;
            }
            $("#vetos").val(JSON.stringify(vetos));
        }
        return true;
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            reloadParent();
        }else{
            alert(apiRsp.msg);return;
        }

    }
    var onlinePreview = function(path){
        var last = path.lastIndexOf(".");
        var ext = path.substr(last + 1);
        if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx'){
            path = "https://view.officeapps.live.com/op/view.aspx?src=" + path;
        }
        openDialog({
            frame:true,
            title:"预览",
            height:700,
            width:800,
            url:path
        });
    }

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var file=r.surveyFile;
            $("#infImg").show()
            $("#infImg").attr("src",file.filePath);
            $("#adPic").val(file.filePath);
        }
    });


</script>
</body>
</html>
