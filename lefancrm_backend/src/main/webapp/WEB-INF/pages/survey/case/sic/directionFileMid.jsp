<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
<%--<script src="${ctx}/js/progress.js"></script>--%>

    <style>
        .list-group-item{
            width: 100%;
        }

        .pro {
            width: 500px;
            margin: 10px auto;

        }
    </style>
</head>
<body>
<div class="main">
    <div class="row">
        <div class="col-sm-10 " id="result">
            <div class="row panel panel-info" id="uploadDiv">
                <div class="col-sm-12 row" id="f" style="margin:5px 0;display: flex;flex-wrap: wrap;">
                    <c:forEach items="${dto.surveyCaseDirections[0].surveyCaseDirectionFiles}" var="file" varStatus="st">
                        <div class="col-sm-3">
                            <img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},1)" src="${file.commonFile.filePath}" />
                            <br/><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <input type="hidden" id="img" name="img" value="" />
                    <div id="materialImgsDiv" style="display: none"></div>
                </div>
                <div id="div_pro" style="display: flex;flex-wrap: wrap;justify-content: space-between;width: 96%;padding: 10px 2%;    max-height: 300px;    overflow: auto;    margin-bottom: 20px;">

                    <%--<div class="pro"></div>--%>

                </div>
                <div>
                    <input id="materialFileupload" onclick="setDataURL()" type="file" name="file" multiple /><br>
                </div>
                <div class="col-sm-1 col-sm-offset-9" id="uploadButton"><button id="btnUpload" type="button" onclick="uploadImg111()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认上传</button></div>
            </div>



            <div class="row panel panel-info" style="display: none" id="show">
                <div class="detail_context_pic_top">
                    <!-- <div class="imgContainer"><img src="" alt="" id="pic1" curindex="0" /></div> -->
                    <img src="" alt="" id="pic1" curindex="0" />
                    <a id="preArrow" href="javascript:void(0)" class="contextDiv" title="上一张"><span id="preArrow_A"></span></a>
                    <a id="nextArrow" href="javascript:void(0)" class="contextDiv" title="下一张"><span id="nextArrow_A"></span></a>
                    <div class="tools">
                        <a href="javascript:;" class="rotateAW" title ="向左旋转"></a>
                        <a href="javascript:;" class="rotateCW" title ="向右旋转"></a>
                        <a href="javascript:;" class="magnify" title ="文字识别"></a>
                        <a href="javascript:openImgs('',2)" class="magnify" title ="返回上级"></a>
                    </div>
                </div>
                <!--图片轮播-->
                <div class="detail_context_pic_bot">
                    <div class="detail_picbot_left"> <a href="javascript:void(0)" id="preArrow_B"><img src="${ctx}/caseMid/img/left1.jpg" title="上一个" /></a> </div>
                    <div class="detail_picbot_mid">
                        <ul>
                            <c:forEach items="${dto.surveyCaseDirections[0].surveyCaseDirectionFiles}" var="file">
                                <li>
                                    <a href='javascript:void(0);'>
                                        <img width='90px' height='60px' bigimg='${file.commonFile.filePath}'
                                            src='${file.commonFile.filePath}'/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="detail_picbot_right"> <a href="javascript:void(0)" id="nextArrow_B"><img src="${ctx}/caseMid/img/right1.jpg" title="下一个" /></a> </div>
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
<script src="${ctx}/caseMid/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/caseMid/js/xiangce.js" type="text/javascript"></script>

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
                var files = e.data.results.surveyCaseFiles;
                var vl = "";
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
                alert("上传成功");
                $("#div_pro").html("");
//                step = 0;
//                successNum = 0;
                onBack($("#surveyInfoId").val(),$("#catalogId").val());
            }
        });
    }

    function openImgs(index,type){
        if(type ==1){
            $("#show").show();
            $("#uploadDiv").hide();
        }else if(type ==2){
            $("#show").hide();
            $("#uploadDiv").show();
        }

    }

    function setDataURL(){
        files = [];
        successNum=0;
        step = 0;
//        $("#btnUpload").attr("disabled","true");
        $("#materialFileupload").on("change",chgUpload);
        $('#materialFileupload').fileupload({
            url : "${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=" + $("#surveyCno").val(),
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
                var i = 0;
//                $("#materialImgsDiv").append("<div class='col-sm-3'><img width='80' height='80' src='" + file.filePath + "' /></div>");
                $("#materialImgsDiv").append("<div class='col-sm-3'><img style='cursor: pointer' width='100' height='100' onclick='openImgs(${dto.surveyCaseDirections[0].surveyCaseDirectionFiles.size() + i},1)' src='" + file.filePath + "' /></div>");
                i=i+1;
                value += file.filePath + ","
                $("#img").val(value);
                $("#materialImgsDiv").show();
                $("#div_pro").show();
//                pro.update(parseInt((files.length / fileNum * 100)))

                if($("#fileNum").val() == successNum){
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