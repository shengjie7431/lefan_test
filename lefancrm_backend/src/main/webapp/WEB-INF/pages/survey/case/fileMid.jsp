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
                                <input type="button" class="list-group-item" onclick="onBack('${dto.id}','${e.id}','${e.catalogName}')" value="${e.catalogName}">
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <div class="col-sm-10 " id="result">
            <div class="row panel panel-info" id="uploadDiv">
                <div>
                    <input type="hidden" id="surveyId" value="${dto.surveyId}">
                    <input type="hidden" id="surveyInfoId" value="${dto.id}">
                    <input type="hidden" id="surveyCno" value="${dto.surveyCno}">
                    <input type="hidden" id="catalogId" value="${catalogId}">
                    <input type="hidden" id="catalogName"  value="${catalogName}">
                    <input type="hidden" id="fileNum" value="">
                </div>
                <div style="display: none">
                    <input type="hidden" id="img" name="img" value="" />
                    <div id="materialImgsDiv" style="display: none"></div>
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
                    <%--<input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/survey/uploadFileFTP?modelType=material"><br>--%>
                    <input style="display: none;" id="materialFileupload" onclick="setDataURL()" type="file" name="file" multiple /><br>
                </div>
                <div style="display: none;" class="col-sm-1 col-sm-offset-9" id="uploadButton"><button id="btnUpload" type="button" onclick="uploadImg111()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认上传</button></div>
            </div>
            <div class="row panel panel-info">
                <div class="col-sm-12 row" id="f" style="margin:5px 0;display: flex;flex-wrap: wrap;">
                    <c:forEach items="${surveyCaseFiles}" var="file" varStatus="st">
                        <div class="col-sm-3">
                            <%--<img id="infImg" src="${file.commonFile.filePath}" width="100" height="100" onclick="openImgs(${st.index})"/><br/>--%>
                                <%--&lt;%&ndash;<fmt:formatDate value="${file.commonFile.createTime}" pattern="yyyy-MM-dd HH:mm"/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="checkbox" name="files" value="${file.commonFile.filePath}">&ndash;%&gt;--%>
                            <%--<br/>--%>
                            <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>


                            <c:if test="${file.fileType == 1}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/word.png" />
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                            <c:if test="${file.fileType == 2}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${file.commonFile.filePath}" />
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                            <c:if test="${file.fileType == 3}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/rar.jpg" /></a>
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                            <c:if test="${file.fileType == 4}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/excel.png" />
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                            <c:if test="${file.fileType == 5}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/pdf.jpg" /></a>
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                            <c:if test="${file.fileType == 6}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/txt.png" />
                                <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <div style="display: none" id="div_files_paths">
                    <c:forEach items="${surveyCaseFiles}" var="file" varStatus="st">
                        <span data-file-path="${file.commonFile.filePath}"></span>
                    </c:forEach>
                </div>
            </div>
        </div>
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

    function onBack(surveyInfoId,catalogId,enumName){
        $("#btnUpload").removeAttr("disabled");
        surveyInfoId = $("#surveyInfoId").val();
        ajaxSubmit("${ctx}/survey/case/fileMid",{"surveyInfoId":surveyInfoId,"catalogId":catalogId==null?null:(catalogId),"viewType":"treeClick"},function(v,e,p){
            if(e.data.code=='0000'){
//                var files = e.data.results.surveyCaseFiles;
//                var vl = "";
//                for(var i = 0; i < files.length; i++){
//                    var json = files[i];
//                    vl += "<div class='col-sm-3'>";
//                    vl += "<img src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+")'/>";
//                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    vl += " </div>";
//                }
//
//                $("#surveyInfoId").val(surveyInfoId);
//                $("#catalogId").val(catalogId);
//                $("#catalogName").val(enumName);
//                $("#materialImgsDiv").html("");
//                $("#f").html(vl);
//                //不是具体分类时，上传div隐藏
//                if(catalogId ==null){
//                    $("#uploadDiv").hide();
//                }else{
//                    $("#uploadDiv").show();
//                }

                var files = e.data.results.surveyCaseFiles;
                var vl = "";
                var htmlFiles = "";
                for(var i = 0; i < files.length; i++){
                    var json = files[i];
                    console.log(json);
                    vl += "<div class='col-sm-3'>";
                    var ext = json.fileType;
                    if(ext == 1){
                        vl += "<img src='${ctx}/img/word.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>"
                    }else if(ext == 2){
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>"
                    }else if(ext == 3){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/rar.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>";
                    }else if(ext == 4){
                        vl += "<img src='${ctx}/img/excel.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>"
                    }else if(ext == 5){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/pdf.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>";
                    }else if(ext == 6) {
                        vl += "<img src='${ctx}/img/txt.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>"
                    }else{//都按照图片处理
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span>"
                    }
//                    vl += "<img src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/>";
//                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='files' value='"+json.filePath+"'>";
                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    vl += " </div>";

                    htmlFiles += "<span data-file-path='"+json.filePath+"'></span>"
                }
                $("#catalogId").val(catalogId);
                $("#materialImgsDiv").html("");
                $("#f").html(vl);

                $("#div_files_paths").html(htmlFiles);

                //不是具体分类时，上传div隐藏
                if(catalogId ==null){
                    $("#uploadDiv").hide();
                }else{
                    $("#uploadDiv").show();
                }

            }else{
            }
//            $("#div_pro").hide();
        })
    }
    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            files = [];
            alert("上传成功");
            onBack($("#surveyInfoId").val(),$("#catalogId").val(),$("#catalogName").val());
            reload();
        }else{
            alert(apiRsp.msg);return;
        }
    }
    var files = [];
    function uploadImg111(){
        if(files.length == 0){
            alert("请选择文件");return;
        }
        $("#files").val(JSON.stringify(files));
        $("#btnUpload").attr("disabled","true");
        clearInterval(aa);
        clearInterval(bb);
        $.ajax({
            url:'${ctx}/survey/case/fileMidOK',
            type:"POST",
            data : {"files":JSON.stringify(files)},
            success:function(res,param){
                files = [];
                // alert("上传成功");
                $("#div_pro").html("");
//                step = 0;
//                successNum = 0;
                onBack($("#surveyInfoId").val(),$("#catalogId").val());
            }
        });
    }

    function openImgs(index){
        var surveyInfoId = $("#surveyInfoId").val();
        var catalogId = $("#catalogId").val();
        openDialog({
            frame:true,
            title:"查看图片",
            height:500,
            width:750,
            url:"${ctx}/survey/case/fileMidView?index="+index+"&surveyInfoId="+surveyInfoId+"&catalogId="+catalogId
        });
    }

    function setDataURL(){
        files = [];
        successNum=0;
        step = 0;
        $("#btnUpload").attr("disabled","true");
        $("#materialFileupload").on("change",chgUpload);
        $('#materialFileupload').fileupload({
            url : "${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=" + $("#surveyCno").val(),
            done: function (e, data) {
                domNum = random(80,90);
                var r  = data.result;
                if(r.success == 'false'){
                    alert(r.message);
                    $("#div_pro").html("");
                    return;
                }
                var file = r.surveyFile;
                if($("#catalogId").val() == null || !$("#catalogId").val()){
                    $("#catalogId").val(8);
                    $("#catalogName").val("理赔申请资料");
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
                $("#materialImgsDiv").append("<div class='col-sm-3'><img width='80' height='80' src='" + file.filePath + "' /></div>");
                value += file.filePath + ","
                $("#img").val(value);
                $("#materialImgsDiv").show();
                $("#div_pro").show();
//                pro.update(parseInt((files.length / fileNum * 100)))

                if($("#fileNum").val() == successNum){
                    uploadImg111();
                    return;
                } else {
                    aa = setInterval(function(){
                        if($("#fileNum").val() == successNum){
                            return;
                        }
                        if (step >= domNum){
                            clearInterval(aa)
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
                        if($("#fileNum").val() == successNum){
                            return;
                        }
                        if (step >= domNum){
                            clearInterval(bb)
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
                console.log("success" + successNum);
                $('#asd' + successNum).css({
                    'width': '100%',
                    'backgroundColor':'#c0e2a0'
                })
                $('#asd' + successNum).text("100%");

                successNum ++ ;
                if($("#fileNum").val()  == successNum){
                    $("#btnUpload").attr("disabled",false);
                }
            }
        });
    }

    //材料凭证
    var value = "";
    var successNum = 0;
    var step = 0;
    var bb = null;
    var aa = null;
    function random(lower, upper) {
        return Math.floor(Math.random() * (upper - lower+1)) + lower;
    }
    var domNum = random(80,90);
//    var uploadFile = $("#materialFileupload");
    function chgUpload(){
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

    var divs = [];
    function del(){
        var chks = document.getElementsByName('chknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                divs.push($("#div_" + chk.id));
            }
        }
        for(var i = 0 ; i < divs.length ;i++){
            divs[i].remove();
        }
        $("#img").val(value);
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
</body>
</html>