<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .list-group-item{
            width: 120px;
        }
    </style>
    <script>
        function onBack(caseId,fileType,fileName,btnCode){
            ajaxSubmit("${ctx}/law/selectLawFile",{"caseId":caseId,"fileType":fileType,"viewType":"treeClick"},function(v,e,p){
                if(e.data.code=='0000'){
                    var vl = "";
                    for(var i = 0; i < e.data.results.length; i++){
                        var json = e.data.results[i];
                         vl += "<div class='col-sm-3'>";
                         vl += "" + (json.documentType == 2 ? "<a href='"+json.filePath+"' target='_blank'><img src='../img/word.png' width='100' height='100' onclick='openImgs("+i+","+fileType+")'></a><br/>" : "<img id='infImg' src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+","+fileType+")'><br/>") + "";
                         vl += "" + (json.createTimeName == null ? "" : json.createTimeName) + "<br/>";
                         vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='files' value='"+json.filePath+"'>";
                         vl += " </div>";
                    }

                    $("#fileType").val(fileType);
                    $("#caseId").val(caseId);
                    $("#fileName").val(fileName);
                    $("#btnCode").val(btnCode);
                    //不是具体分类时，上传div隐藏
                    if(fileType ==1){
                        $("#uploadDiv").show();
                    }
                    if(fileType !=1){
                        $("#uploadDiv").hide();
                    }
                    $("#f").html(vl);
                    if(! e.data.results.length) $('#btnSubmit').addClass('hidden')
                    else $('#btnSubmit').removeClass('hidden')
                }else{
                }
            })
        }

        function formatData(time,str){
            Date.prototype.Format = function(fmt) { //author: meizz
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "HH+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if(/(y+)/.test(fmt))
                    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                            .substr(4 - RegExp.$1.length));
                for(var k in o)
                    if(new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1,
                                (RegExp.$1.length == 1) ? (o[k]) :
                                        (("00" + o[k])
                                                .substr(("" + o[k]).length)));
                return fmt;
            }
            return(new Date(time)).Format(str)
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
                            <%--<input type="button" class="list-group-item" onclick="onBack('${id}','','全部')" value="全部">--%>
                            <input type="button" class="list-group-item" onclick="onBack('${id}',1,'委托材料',1499)" value="委托材料">
                            <input type="button" class="list-group-item" onclick="onBack('${id}',2,'评估计划',1500)" value="评估计划">
                            <input type="button" class="list-group-item" onclick="onBack('${id}',3,'请款函附件',1501)" value="请款函附件">
                            <input type="button" class="list-group-item" onclick="onBack('${id}',4,'评估报告',1504)" value="评估报告">
                        </ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <form method="post" action = "${ctx}/download" id="editFrom">
            <div class="col-sm-10 " id="result">
                <div class="row panel panel-info" id="uploadDiv" hidden="hidden">
                    <div>
                        <input type="hidden" id="id" value="${id}">
                        <input type="hidden" id="btnCode" value="${btnCode}">
                        <input type="hidden" id="lawFileDtos" value="${lawFileDtos}">
                    </div>
                    <div>
                        <input required id="uploadPaths" type="hidden" value="${img}" name="img">
                        <img hidden="hidden" id="infImg2" src="${img}" width="80" height="80">
                    </div>
                    <div>
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage"><br>
                    </div>

                    <div class="col-sm-1 col-sm-offset-9" id="uploadButton"><button type="button" onclick="uploadImg()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 上传单证</button></div>

                </div>
                <div class="row panel panel-info" id = "files">
                    <div class="col-sm-12 row" id="f" style="margin:5px 0">
                        <c:forEach items="${lawFileDtos}" var="file" varStatus="st">
                            <div class="col-sm-3">
                                <c:if test="${file.documentType == 3}">
                                    <img id="infImg" src="${file.commonFile.filePath}" width="100" height="100" onclick="openImgs(${st.index},${file.fileType})"/><br/>
                                </c:if>
                                <c:if test="${file.documentType == 2}">
                                    <a href="${file.commonFile.filePath}" target="_blank"><img src="../img/word.png" width="100" height="100"></a><br/>
                                </c:if>
                                <c:if test="${file.documentType == 1}">
                                    <a href="${file.commonFile.filePath}" target="_blank"><img src="../img/pdf.jpg" width="100" height="100"></a><br/>
                                </c:if>
                                    <fmt:formatDate value="${file.commonFile.createTime}" pattern="yyyy-MM-dd HH:mm"/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="files" value="${file.commonFile.filePath}">
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${lawFileDtos.size() > 0 && lawFileDtos != null}">
                        <div class="row">
                            <div class="col-sm-1 col-sm-offset-9" id="btnSubmit"><button type="submit"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 下载单证</button></div>
                        </div>
                    </c:if>

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
                $("#infImg2").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#uploadPaths").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#infImg2").show();
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

    function uploadImg(){
        var id = $("#id").val();
        var btnCode = $("#btnCode").val();
        var uploadPaths = $("#uploadPaths").val();
        $.ajax({
            url:'${ctx}/law/operate?id='+id+'&btnCode='+btnCode+'&uploadPaths='+uploadPaths,
            type:"Get",
            success:function(res,param){
                window.location.reload();
//                $("#uploadDiv").html();
//                onBack(caseNo,caseId,catalogId,catalogName);
            }
        });
    }

    function openImgs(index,fileType){
        var id = $("#id").val();
        openDialog({
            frame:true,
            title:"查看图片",
            height:500,
            width:900,
            url:"${ctx}/law/lawFileShow?index="+index+"&id="+id+"&fileType="+fileType
        });
    }
</script>
</body>
</html>