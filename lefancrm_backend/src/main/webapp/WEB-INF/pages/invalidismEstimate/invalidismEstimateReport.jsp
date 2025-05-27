<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function add(val){
          var param = $("#invalidismGrade").val();
          var paramTo = $("#invalidismGradeTo").val();
            if(param.length > 0){
                param = param + ","+ val;
                paramTo = paramTo + ","+ val+"级";
            }else{
                param = val;
                paramTo =  val+"级";
            }
            $("#invalidismGrade").val(param);
            $("#invalidismGradeTo").val(paramTo);
            return false;
        }
        function del(val){
            var param = $("#invalidismGrade").val();
            var paramTo = $("#invalidismGradeTo").val();
            if(param.length > 0){
                var flag = param.indexOf(val);
                if(flag != -1){
                    var params = param.split(",");
                    param = "";
                    paramTo = "";
                    var index = -1;
                    for(var i = 0; i < params.length; i++){
                        if(params[i] == val && index != -1){
                            if(param == ""){
                                param = param + params[i];
                                paramTo = paramTo + params[i] + "级";
                            }else{
                                param = param + "," + params[i];
                                paramTo = paramTo + "," + params[i] + "级";
                            }
                        }else if(params[i] != val){
                            if(param == ""){
                                paramTo = paramTo + params[i] + "级";
                                param = param + params[i];
                            }else{
                                param = param + "," + params[i];
                                paramTo = paramTo + "," + params[i] + "级";
                            }
                        }else{
                            index = 0;
                        }
                    }
                    $("#invalidismGrade").val(param);
                    $("#invalidismGradeTo").val(paramTo);
                }
            }
            return false;
        }

    </script>
    <style>

    </style>
</head>
<body>
<div class="container">
    <c:if test="${report !=null}">
    <form id="editForm" role="form" action="${ctx}/invalidism/report" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}">
                <tr>
                    <th width="20%" class="active">伤情诊断</th>
                    <td width="80%">
                        <textarea id = "injuryDiagnose" name="injuryDiagnose" style="width: 400px;height: 90px" class="form-control">${report.injuryDiagnose}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否手术</th>
                    <td width="80%">
                        是<input type="radio"  name="isOperation" style="width: 50px;" value="1" <c:if test="${report.isOperation == 1}"> checked="checked" </c:if>>
                        否<input type="radio"  name="isOperation" style="width: 50px;" value="0" <c:if test="${report.isOperation == 0}"> checked="checked" </c:if>>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">评残依据</th>
                    <td width="80%">
                        <textarea id = "reportBasis" name="reportBasis" style="width: 400px;height: 90px" class="form-control">${report.reportBasis}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">预估伤残等级</th>
                    <td width="80%">
                        一级 <button onclick="return add(1)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(1)"class="btn btn-default btn-xs btn-primary">—</button>
                        二级 <button onclick="return add(2)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(2)"class="btn btn-default btn-xs btn-primary">—</button>
                        三级 <button onclick="return add(3)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(3)"class="btn btn-default btn-xs btn-primary">—</button>
                        四级 <button onclick="return add(4)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(4)"class="btn btn-default btn-xs btn-primary">—</button>
                        五级 <button onclick="return add(5)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(5)"class="btn btn-default btn-xs btn-primary">—</button></br></br>
                        六级 <button onclick="return add(6)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(6)"class="btn btn-default btn-xs btn-primary">—</button>
                        七级 <button onclick="return add(7)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(7)"class="btn btn-default btn-xs btn-primary">—</button>
                        八级 <button onclick="return add(8)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(8)"class="btn btn-default btn-xs btn-primary">—</button>
                        九级 <button onclick="return add(9)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(9)"class="btn btn-default btn-xs btn-primary">—</button>
                        十级 <button onclick="return add(10)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(10)"class="btn btn-default btn-xs btn-primary">—</button></br>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">已选伤残等级</th>
                    <td width="80%">
                        <%--<textarea id = "invalidismGrade"  style="width: 400px;height: 90px" readonly>${report.invalidismGrade}</textarea>--%>
                        <textarea class="form-control" id = "invalidismGradeTo"  style="width: 400px;height: 90px" readonly >${report.invalidismGrade}</textarea>
                        <input type="hidden" value="${report.invalidismGrade}" name="invalidismGrade" id = "invalidismGrade">
                    </td>

                </tr>
                <tr>
                    <th width="20%" class="active">备注</th>
                    <td width="80%">
                        <textarea id = "reportDesc" name="reportDesc" style="width: 400px;height: 90px" class="form-control">${report.reportDesc}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <c:if test="${op == 'viewNo'}">
                <button type="button" onclick="file(${id})" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"> 单证信息</button>
            </c:if>
            <c:if test="${op == 'view'}">
                <button type="button" onclick="file(${id})" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"> 单证信息</button>
                <button type="button" onclick="updateReson(${id})"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"> 预估报告查看</button>
                <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            </c:if>
        </div>
    </form>
    </c:if>
    <c:if test="${report ==null}">
        <span style="color: red;">暂无数据</span>
    </c:if>
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
    $(document).ready(function(){
        var val = $("#invalidismGradeTo").val();
        if(val == null || val == ""){
            return;
        }
        var arr = val.split(",");
        val = "";
        for(var i = 0;i<arr.length;i++){
            val = val + arr[i] + "级";
            if(i != arr.length - 1){
                val += ",";
            }
        }
        $("#invalidismGradeTo").val(val);
    });

    var updateReson = function(id){
        openDialog({
            frame:true,
            title:"预估报告",
            height:650,
            width:1000,
            url:"${ctx}/invalidism/toReport?id="+id
        });
    }
    var file = function(id){
        openDialog({
            frame:true,
            title:"伤残等级预估图片资料",
            height:650,
            width:1000,
            url:"${ctx}/invalidism/queryFile?id="+id
        });
    }
</script>
</body>
</html>