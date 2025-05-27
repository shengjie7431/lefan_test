<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script src="${ctx}/js/progress.js"></script>
    <style>
        .list-group-item{
            width: 100%;
        }

        .pro {
            width: 500px;
            margin: 10px auto;

        }

        #bar-warp{
            width:500px;
            height:30px;
            border:1px solid green;
        }
        #bar{
            width:0px;
            height:30px;
            background:green;
        }

        .file-add {
            width: 148px;
            height: 148px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 74px;
            margin-top: 34px;
            display: inline-block;
            background: #aaa;
            height: 80px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 80px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }
    </style>
</head>
<body>
<div class="main">
    <div class="row">
        <div class="panel panel-info col-sm-2">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul class="list-group">
                            <input type="button" class="list-group-item" onclick="onBack('${surveyInfoId}',null,'')" value="全部目录">
                            <c:forEach items="${fileCatalogs}" var="e">
                                <input type="button" class="list-group-item" onclick="onBack('${surveyInfoId}','${e.id}','${e.catalogName}')" value="${e.catalogName}">
                            </c:forEach>
                        </ul>

                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <form id="uploadForm" method="post" action="${ctx}/survey/case/fileMidOK">
            <input type="hidden" name="files" id="files" value="" />
        </form>
<%--        <form method="post" action = "${ctx}/survey/download" id="editFrom">--%>
            <div class="col-sm-10 " id="result">
                <div class="row panel panel-info" id="uploadDiv" style="display: none">
                    <div>
                        <input type="hidden" id="surveyId" value="${surveyId}">
                        <input type="hidden" id="surveyInfoId" value="${surveyInfoId}">
                        <input type="hidden" id="catalogId" value="${catalogId}">
                        <input type="hidden" id="fileNum" value="">
                    </div>
                    <div style="display: none;">
                        <input type="hidden" id="img" name="img" value="" />
                        <div id="materialImgsDiv"></div><br/>
                        <%--<div id="deleteDiv" style="display: none">--%>
                        <%--<input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>--%>
                        <%--</div>--%>
                    </div>
                    <div id="div_pro" style="display: flex;flex-wrap: wrap;justify-content: space-between;width: 96%;padding: 10px 2%;    max-height: 300px;    overflow: auto;    margin-bottom: 20px;">

                        <%--<div class="pro"></div>--%>

                    </div>
                    <div>
                        <div class="file-add" onclick="$('#materialFileupload').click();">
                            <div class="icon-add"></div>
                        </div>
                        <%--<input type="file" name="file" id="file" onchange="upPic()" multiple>--%>
                        <input style="display: none;" id="materialFileupload" type="file" name="file" onclick="clearParams()" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=${surveyCno}"><br>
                    </div>
                    <div style="width: 90%;" id="div_file_show"></div>
                    <div style="display: none;" class="col-sm-1 col-sm-offset-9" id="uploadButton"><button id="btnUpload" type="button" onclick="uploadImg()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认上传</button></div>
                    <%--<div>--%>
                    <%--<div id="bar-warp">--%>
                    <%--<div id="bar"></div>--%>
                    <%--<span id="precent"></span><br/>--%>
                    <%--<div id="succ"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="row panel panel-info">
                    <div class="col-sm-12 row" id="f" style="margin:5px 0;display: flex;flex-wrap: wrap;">
                        <c:forEach items="${surveyCaseFiles}" var="file" varStatus="st">
                            <div class="col-sm-3">
                                <c:if test="${file.fileType == 1}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/word.png" />
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                <c:if test="${file.fileType == 2}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${file.commonFile.filePath}" />
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                <c:if test="${file.fileType == 3}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/rar.jpg" /></a>
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                <c:if test="${file.fileType == 4}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/excel.png" />
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                <c:if test="${file.fileType == 5}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/pdf.jpg" /></a>
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                <c:if test="${file.fileType == 6}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/txt.png" />
                                    <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                </c:if>
                                    <%--<img id="infImg" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')"--%>
                                    <%--<c:if test="${file.fileType == 1}">src="${ctx}/img/word.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 3}">src="${ctx}/img/rar.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 4}">src="${ctx}/img/excel.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 5}">src="${ctx}/img/pdf.jpg"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 2}">src="${file.commonFile.filePath}"</c:if>--%>
                                    <%--/>--%>
                                <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <%--<input type="checkbox" name="files" value="${file.commonFile.filePath}">--%>
                            </div>
                        </c:forEach>
                    </div>
                    <%--FTP导出，需要压缩打包，暂未实现--%>
                    <%--<c:if test="${surveyCaseFiles.size() > 0 && surveyCaseFiles != null}">--%>
                    <%--<div class="row">--%>
                    <%--<div class="col-sm-1 col-sm-offset-9" id="btnSubmit"><button type="submit"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 下载单证</button></div>--%>
                    <%--</div>--%>
                    <%--</c:if>--%>
                </div>

                <div class="row panel panel-info">
                    <div>
                        <div class="file-add" onclick="$('#processFileUpload').click();">
                            <div class="icon-add"></div>
                        </div>
                    </div>
                    <form id="processFileForm" action="${ctx}/sftp/survey/uploadSftp" enctype="multipart/form-data" method="post" target="upload_iframe">
                        <input type="hidden" name="modelType" value="material" />
                        <input type="hidden" name="surveyCno" value="${surveyCno}" />
                        <input id="processFileUpload" type="file" name="file" multiple onchange="processFileForm()">
                        <input type="submit" value="提交" id="btnProcess" />
                    </form>
                    <iframe name="upload_iframe" width="0" height="0" frameborder="0"></iframe>
                    <label id="fff"></label>
                    <div style="width: 400px;height: 20px;border: 1px solid #eee;">
                        <div id='proBar' style="width: 0; height: 20px;background-color: #c0e2a0"></div>
                    </div>
                </div>
            </div>
<%--        </form>--%>
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
<script>
    function processFileForm(){
        $("#btnProcess").click();
        var eventFun = function(){
            $.ajax({
                type: 'GET',
                url: '${ctx}/sftp/survey/process',
                data: {},
                dataType: 'json',
                success : function(e,data){
                    data = data.data;
                    console.log("success-data",data);
                    $("#fff").html("上传进度:" + data.rate + '%');

                    $("#proBar").width(data.rate + '%')
                    if(data.rate === 100){
                        alert("上传完成");
                        clearInterval(intId);
                    }
                },
                fail : function(data){
                    console.log("fail-data",data);
                    window.clearInterval(intId);
                    alert("失败");
                }
            });
        };
        var intId = setInterval(eventFun,500);
    }

    <%--function subimtBtn(){--%>
    <%--    $('#processFileForm').form('submit', {--%>
    <%--        url: "${ctx}/sftp/survey/uploadSftp",--%>
    <%--        onSubmit: function () {//表单提交前的回调函数--%>

    <%--        },--%>
    <%--        success: function (e,data) {--%>
    <%--            console.log("success",e,data);--%>
    <%--        },--%>
    <%--        fail : function (data) {--%>
    <%--            console.log("fail",data);--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>


</script>

<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    var onlinePreview = function(path){
        var last = path.lastIndexOf(".");
        var ext = path.substr(last + 1);
        if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx' || ext == 'pdf'){
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

    var upPic = function(){
//        var pic=document.getElementsByTagName('input')[0].files[0];
        var files = document.getElementById("file").files;
        var size = 0;
        var html = "";
        for(var i = 0 ; i < files.length ; i ++){
            var f = files[i];
            console.log(f);
            size += f.size;
//            html += "<div>" + f.name + "(" + (f.size/1024/1024).toFixed(1) +"M)<span style='color:green;' id='fileProcess_'" + i + "></span></div>"
            html += "<div id='show_div_'"+ i +">" + f.name + "(" + (f.size/1024/1024).toFixed(1) +"M)<span id='span'_" + i + ">sdfsdfs</span></div>"
        }
        alert($("#span_0").text);
        $("#div_file_show").html(html);
        var size = size / 1024 + 5;
        var secSize = 2;
        for(var i = 0 ; i < files.length ; i ++){
            var file = document.getElementById("file").files[i];
            var fd=new FormData();
            var xhr=new XMLHttpRequest();
            xhr.open('post','${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=${surveyCno}',true);
            xhr.onreadystatechange=function (){
                //readystate为4表示请求已完成并就绪
                if(this.readyState==4){
//                    document.getElementById('precent').innerHTML=this.responseText;
                    //在进度条下方插入百分比
                    $("#show_div_" + i).html("上传完成");
                }
            }

            xhr.upload.onprogress=function (ev){
                console.log(ev.loaded,ev.total);
                //如果ev.lengthComputable为true就可以开始计算上传进度
                //上传进度 = 100* ev.loaded/ev.total
                if(ev.lengthComputable){
                    var precent=100 * ev.loaded/ev.total;
                    if(precent > 95){
                        $("#show_div_" + i).html("95%");
                    }else{
                        $("#show_div_" + i).html(precent + "%");
                    }
//                    console.log(precent);
                    //更改进度条，及百分比
//                    document.getElementById('bar').style.width=precent+'%';
//                    document.getElementById('precent').innerHTML=Math.floor(precent)+'%';
                }
            }
            fd.append('file',file);
            xhr.send(fd);
        }
    }


    function onBack(surveyInfoId,catalogId){
        if(files.length > 0){
            alert("附件上传未确认，请确认");return;
        }
        ajaxSubmit("${ctx}/survey/case/fileMid",{"surveyInfoId":surveyInfoId,"catalogId":catalogId==null?null:Number(catalogId),"viewType":"treeClick"},function(v,e,p){
            if(e.data.code=='0000'){
                var files = e.data.results.surveyCaseFiles;
                var vl = "";
                for(var i = 0; i < files.length; i++){
                    var json = files[i];
                    vl += "<div class='col-sm-3'>";
                    var ext = json.fileType;
                    if(ext == 1){
                        vl += "<img src='${ctx}/img/word.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else if(ext == 2){
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else if(ext == 3){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/rar.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else if(ext == 4){
                        vl += "<img src='${ctx}/img/excel.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else if(ext == 5){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/pdf.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else if(ext == 6) {
                        vl += "<img src='${ctx}/img/txt.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }else{//都按照图片处理
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span>"
                    }
//                    vl += "<img src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/>";
//                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='files' value='"+json.filePath+"'>";
                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    vl += " </div>";
                }
                $("#catalogId").val(catalogId);
                $("#materialImgsDiv").html("");
                $("#f").html(vl);

                //不是具体分类时，上传div隐藏
                if(catalogId ==null){
                    $("#uploadDiv").hide();
                }else{
                    $("#uploadDiv").show();
                }
            }else{
            }

        })
    }

    var files = [];
    var filesCopy =[];


    $("#uploadForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });

    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            files = [];
            // alert("上传成功");
            onBack($("#surveyInfoId").val(),Number($("#catalogId").val()));
            reload();
        }else{
            alert(apiRsp.msg);return;
        }
    }
    function uploadImg(){
        if(files.length == 0){
            alert("请选择文件");return;
        }
        $("#btnUpload").attr("disabled","true");
        $("#files").val(JSON.stringify(files));
//        successNum = 0;
//        step = 0;
        clearInterval(aa);
        clearInterval(bb);
        $("#uploadForm").submit();

//        $(this).find(":submit").attr("disabled","true");
        <%--$.ajax({--%>
        <%--url:'${ctx}/survey/case/fileMidOK?files=' + JSON.stringify(files),--%>
        <%--type:"Get",--%>
        <%--success:function(res,param){--%>
        <%--files = [];--%>
        <%--alert("上传成功");--%>
        <%--onBack($("#surveyInfoId").val(),$("#catalogId").val());--%>
        <%--}--%>
        <%--});--%>
    }

    function random(lower, upper) {
        return Math.floor(Math.random() * (upper - lower+1)) + lower;
    }

    //材料凭证
    var value = "";
    var successNum = 0;
    var step = 0;
    var bb = null;
    var aa = null;
    function clearParams(){
        files = []
        successNum=0
        step = 0
    }
    var domNum = random(80,90);
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            domNum = random(80,90);
            var r  = data.result;
            if(r.success == 'false'){
                alert(r.message);
                return;
            }
            var file = r.surveyFile;
            if($("#catalogId").val() == null || !$("#catalogId").val()){
                $("#catalogId").val(8);
                $("#catalogName").val("调查资料");
            }
            var item = {
                "surveyId" : $("#surveyId").val(),
                "surveyInfoId" : $("#surveyInfoId").val(),
                "catalogId" : Number($("#catalogId").val()),
                "catalogName" : $("#catalogName").val(),
                "fileName" : file.fileName,
                "filePath" : file.filePath
            };
            files.push(item);
            var fileNum = $("#fileNum").val();
            $("#materialImgsDiv").html("");
//            $("#materialImgsDiv").append("<div class='col-sm-3'><img width='80' height='80' src='" + file.filePath + "' /></div>");
            $("#materialImgsDiv").append("<div class='col-sm-3'>共" + fileNum + "个文件已完成:" + files.length + "上传&nbsp;&nbsp;&nbsp;</div>");
            value += file.filePath + ","
            $("#img").val(value);
            $("#materialImgsDiv").show();
            $("#div_pro").show();
//            pro.update(parseInt((files.length / fileNum * 100)))


            step = 0;

            if($("#fileNum").val() == successNum){
                uploadImg();
                return;
            } else {
                aa = setInterval(function(){
                    if($("#fileNum").val() == successNum){
                        return;
                    }
                    if (step >= domNum){
                        clearInterval(aa)
                        console.log("done" + step + '---' + successNum);
                    } else {
                        step = step + 5
                    }
                    $('#asd' + successNum).css({
                        'width': step + '%',
                        'backgroundColor':'#c0e2a0'
                    })
                    $('#asd' + successNum).text(step + "%");

                },1500);
            }
        },
        progress : function (e,data){
        },
        progressall : function(e,data){
            var progress = parseInt(data.loaded / data.total * 100, 10);

            if(progress > 70){
                progress = random(80,90);
            }

            if($("#fileNum").val() == successNum){
                return;
            } else {
                bb = setInterval(function(){
                    console.log("progress:"+$("#fileNum").val() + "[file]" + successNum);
                    if($("#fileNum").val() == successNum){
                        return;
                    }
                    if (step >= domNum){
                        clearInterval(bb)
                        console.log("progressall" + step + '---' + domNum);
                    } else {
                        step = step + 5
                    }
                    $('#asd' + successNum).css({
                        'width': step + '%',
                        'backgroundColor':'#c0e2a0'
                    })
                    $('#asd' + successNum).text("上传中" + step + "%");

                },1500);
            }


//            $("#asda" + successNum).html("<span style='display: inline-block;'>正在上传</span>");
//            $("#asda" + successNum).append("<span style='width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right'>" +
//            "<span id='asd"+successNum+"' style='background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;'>"+progress+"%</span></span>" );

//            $('#asd' + successNum).css({
//                'width': progress + '%',
//                'backgroundColor':'#c0e2a0'
//            })
//            $('#asd' + successNum).text("上传中"+progress + "%");
        },
        success : function (e,data){
//            $("#asda" + successNum).html("<span  style='display: inline-block;'>上传完成</span>");
//            $("#asda" + successNum).append("<span style='width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right'>" +
//            "<span id='asd"+successNum+"' style='background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;'>100%</span></span>" );
//            $('#asd' + successNum).css('width', progress + '%');
            console.log("success",e,data);

            $('#asd' + successNum).css({
                'width': '100%',
                'backgroundColor':'#c0e2a0'
            })
            $('#asd' + successNum).text("100%");

            successNum ++ ;
            if($("#fileNum").val()  == successNum){
                $("#btnUpload").attr("disabled",false);
            }
        },
        fail : function(e,data){
            console.log("fail:",e,data);
            alert("上传失败，请重新上传");

        }
    });

    var uploadFile = $("#materialFileupload");
    uploadFile.on("change",upload);
    function upload(){
        $("#btnUpload").attr("disabled",true);
        var f = this.files;
        $("#fileNum").val(f.length);
        var html = "";
        for(var i = 0 ; i < f.length ; i ++){

            html += '<div style="width:45%;overflow: hidden;padding: 10px;font-size: 12px;border: 1px solid #eee;margin-bottom: 10px;"> <span style="display:inline-block;overflow: hidden; width: 98%;text-overflow: ellipsis;white-space: nowrap;">'  + f[i].name + '</span>' + '<span id="asda'+i+'" style="width:100%;position:relative;height: 33px;display:block"><span style="display: inline-block;">上传进度</span><span style="width:100%;background-color:#eee;height:16px;display: inline-block;"><span id="asd'+i+'" style="background-color:#eee;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;">0%</span></span></span></div>'

//            html += '<div style="width:45%;overflow: hidden;padding: 10px;font-size: 12px;border: 1px solid #eee;">' +
//                    '<span style="display:inline-block;overflow: hidden; width: 98%;text-overflow: ellipsis;white-space: nowrap;">' + f[i].name + '</span>' +
//                    '<span id="asda'+i+'" style="width:100%;position:relative;height: 33px;display:block">' +
//                    '<span style="width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right"><span id="asd'+i+' style="background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;">0%</span></span></span></div>';
        }
        $("#div_pro").html(html);
    }

    var pro = new progress({
        width : 500,//进度条宽度
        height: 30,//进度条高度
        bgColor : "#3E4E5E",//背景颜色
        proColor : "#009988",//前景颜色
        fontColor : "#FFFFFF",//显示字体颜色
        val : 0,//默认值
        text:"当前进度为#*val*#%",//显示文字信息
        showPresent : true,
        completeCallback:function(val){
            console.log('已完成');
        },
        changeCallback:function(val){
            console.log('当前进度为'+val+'%');
        }
    });
    //    document.getElementsByClassName('pro')[0].appendChild(pro.getBody());

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

    function openImgs(index,ext,filePath){
        var surveyInfoId = $("#surveyInfoId").val();
        var catalogId = $("#catalogId").val();
        if(ext == 2){
            openDialog({
                frame:true,
                title:"查看图片",
                height:500,
                width:750,
                url:"${ctx}/survey/case/fileMidView?index="+index+"&surveyInfoId="+surveyInfoId+"&catalogId="+catalogId
            });
        }else if(ext == 1){//word
            onlinePreview(filePath)
        }else if(ext == 3){//rar

        }else if(ext == 4){//excel
            onlinePreview(filePath)
        }else if(ext == 5){//pdf
            onlinePreview(filePath)
        }else if(ext == 6){//pdf
            onlinePreview(filePath)
        }
    }
</script>
</body>
</html>