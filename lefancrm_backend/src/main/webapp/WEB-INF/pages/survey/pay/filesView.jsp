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
    <%--<link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />--%>

    <style>
        .contain {
            display: flex;
            width: 100%;
            height: 100%;
            min-height: 100%;
            overflow: hidden;
        }

        .body-left {
            /* display: none; */
            width: 100%;
            min-width: 100%;
            height: 101%;
            min-height: 100%;
            border-right: 1px solid #bbb;
            /*padding-bottom: 2%;*/
            overflow: auto;
        }

        .body-left-detail {
            display: none;
        }

        .body-left .title {
            padding: 20px 0;
            margin: 0 4%;
            width: 92%;
            /* border-bottom: 1px solid #bbb; */
            font-size: 20px;
            color: #333;
            text-align: left;
        }

        .body-left .title span {
            font-size: 20px;
        }

        .body-left .files {
            padding: 10px 0;
            margin: 0 4%;
            width: 92%;
            display: flex;
            flex-wrap: wrap;
        }

        .body-left .files .file {
            width: 148px;
            height: 148px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position:relative;
        }

        .body-left .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .body-left .files div.file-add {
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

        .body-left-detail .img-detail {
            width: 98%;
            height: 80%;
            position: relative;
            /* float: left; */
            display: inline-block;
            /*padding-top: 40px;*/
        }

        .body-left-detail .img-detail .img-src {
            width: 100%;
            height: 100%;
            overflow: auto;
            border: 1px solid #d0c9c9;
        }

        .body-left-detail .img-detail .img-src img {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;
        }

        .body-left-detail .img-pre {
            /* float: left; */
            position: absolute;
            left: 1%;
            top: 0;
            display: inline-block;
            width: 10%;
            height: 100%;
            background: url('${ctx}/img/report/icon-left.png');
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            cursor: pointer;
            opacity: 0.6;
        }

        .body-left-detail .img-pre:hover {
            opacity: 1;
        }

        .body-left-detail .img-next:hover {
            opacity: 1;
        }

        .body-left-detail .img-next {
            /* float: left; */
            position: absolute;
            right: 1%;
            top: 0;
            display: inline-block;
            width: 10%;
            height: 100%;
            background: url('${ctx}/img/report/icon-right.png');
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            cursor: pointer;
            opacity: 0.6;
        }

        .body-left-detail .img-btns {
            position: absolute;
            bottom: 0px;
            width: 90%;
            min-width: 404px;
            left: 5%;
            display: flex;
            align-items: center;
            justify-content: space-around;
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 4px;
        }

        .body-left-detail .img-btns .img-btn {
            /* width: 130px; */
            height: 40px;
            line-height: 40px;
            padding: 0 10px;
            color: #fff;
            font-size: 16px;
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .body-left-detail .img-btns .img-btn span {
            height: 30px;
            line-height: 30px;
            color: #fff;
            font-size: 14px;
            font-weight: 200;
        }

        .body-left-detail .img-btns .img-btn:hover span {
            height: 30px;
            line-height: 30px;
            color: #fff;
            font-size: 14px;
            font-weight: 600;
        }

        .body-left-detail .img-btns .img-btn img {
            width: 18px;
            height: 18px;
            display: inline-block;
            margin-right: 5px;
            background: none;
        }

        .icon-step .index{
            width: 30px;
            height: 30px;
            line-height: 30px;
            border-radius: 50%;
            color: #fff;
            font-size: 14px;
            background-color: #dfdfdf;
            text-align: center;
        }
        .icon-step div.active{
            background-color: #45B4FE;
        }
        .icon-delete{
            position: absolute;
            top: 1px;
            right: 1px;
            width: 17px;
            height: 17px;
            background: red;
            border-radius: 50%;
        }
        .img-title {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            color: #fff;
            background-color: rgba(28, 28, 28, 0.7);
            font-size: 13px;
            text-align: center;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            cursor: pointer;
        }
        .icon-deleteX{
            display: block;
            color: #fff;
            line-height: 17px;
            text-align: center;
        }
        .img-pages{
            position: fixed;
            top: 100px;
            left: 40%;
            width: 120px;
            height: 40px;
            line-height: 40px;
            border-radius: 4px;
            color: #fff;
            background-color:  rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        .folder{
            display: inline-block;
        }
        .folderPath{
            display: none;
        }



        .dialogs {
            display: none;
            position: absolute;
            top: 30%;
            left: 25%;
            width: 463px;
            height: 251px;
            background-color: #fff;
            border: 1px solid #bbb;
            z-index: 9999;
        }

        .dialogs .d-name {
            padding: 40px 0 28px 0;
            width: 100%;
            text-align: center;
            font-size: 14px;
            color: #101010;
        }

        .dialogs input {
            display: block;
            margin: 0 auto;
            width: 243px;
            padding: 0 10px;
            height: 40px;
            font-size: 14px;
        }

        .dialogs .d-btns {
            display: flex;
            justify-content: space-between;
            margin: 0 auto;
            width: 263px;
            padding-top: 28px;
        }

        .dialogs .d-btns .d-btn-0 {
            width: 114px;
            height: 38px;
            line-height: 40px;
            text-align: center;
            background-color: #fff;
            border: 1px solid #bbb;
            color: #101010;
        }

        .dialogs .d-btns .d-btn-1 {
            width: 114px;
            height: 38px;
            line-height: 40px;
            text-align: center;
            background-color: #3BA9FF;
            border: 1px solid #3BA9FF;
            color: #fff;
        }

        .title-top {
            width: 92%;
            position: absolute;
            top: 0;
            left: 4%;
            display: flex;
            justify-content: space-between;
            z-index: 9999;
            background-color: #fff;
        }

        .title-top .title {
            display: inline-block;
            width: 50% !important;
            margin: 0!important;
        }

        .title span.first {
            font-size: 20px !important;
        }

        .title span.child {
            font-size: 16px !important;
        }

        .title span.icon-child {
            padding: 0 4px !important;
            font-size: 20px !important;
            color: #bbb !important;
        }

        .fileBtns {
            width: 280px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .fileBtn {
            width: 130px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            border: 1px solid #259B24;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            margin-left: 10px;
        }

        .fileBtn span {
            color: #259B24;
            font-size: 14px;
            padding-left: 5px;
        }
        .folder-img{
            width: 100%;
            height: 100%;
            background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG4AAABkCAYAAABnwAWdAAAED0lEQVR4Xu2dUVLaUBSGzyHTxLe6g+IKKisoTkn0TbuC1hUUV6CuQLuC6gpK34QwI90BO9Ad1L4RWjmd1Dq1M0ISuPeSk/y8kpzz3+/LwUwkgQkvlQRYZWqEJohTehBAXNXF7V1LU+6nx0TSFOJtJtpc15qF6I6Jeuz5p1c7fLuuHOvsm2vionh6xizddQZ9rncqkIgOB52gV7ZstvNkitsdTq6JuG07yCr1hehd3eQtFBfFyQkzHa8C1cW+6eQ1PL9Vp4/NueLa17IZ3E+/uwBvpoeM+p2NHTO1yl9lrrjdeNIl5rPyL+Ffwjp9ZC4Ql/SIaV+ZuLup52+Ndjg9aan0a7644eSGiJv6Vs+f+h2/dGfApjkuEJeI6Wau6s1m3Iojf+yq3zr6VFKcCI0HYdBaB1BXPSspLoU3Yz6M3/oXrkC67lNZcQ9XVbg76PiXrqG66FdZcY/wHq5rSsn/3qXXW2WceMFl3jPiyotzcfSb6pEeZMJ8lOcjHuJMUTdZR+SoH26cLyoJcSaBG6qVTl7WhQSIMwTbdJmss2KIM03cUD0ROh2Ewcm8chBnCLTpMiLybRBuzP0/KMSZJm6oHsQZAum6DMS5Jm6oH8QZAum6DMS5Jm6oH8QZAum6DMS5Jm6oH8QZAum6DMS5Jm6on1NxIvSDiM4b1BgZyq++jLAcEMnHogtxJi6VJsLtqn9Jp6iAdPtomBww0Zci+zoTR1SPr8UVgf9022iYjJnodd79nYnLupqdN3BVt4uGyQUTvc+7PojLS8rydlE8GTHzm7xtnIkjktvEC1p5v+ySdwFV2O7vTaE3RdbiUFwaS0bsBYd1ut0pS0Y4mG4zy2dm2s7a9un7jsU9tpb09t5a3uL7H/wVbrlek7gixxa2fY4AxCk9LiAO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsTFxEKeUgNLYmDiIU0pAaWxMHMQpJaA0NiYO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsTFxEKeUgNLYmDiIU0pAaWxMHMQpJaA0NiYO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsVeYuMktEb9Sum71sZcWV/TBmOpJlWwBS4vbjZMeMe2XbD21ibOCuEmXmM9qQ6pkC816xvXcH/5LH0c7+zUdM9PLkq2pFnHY87cWPWl3rriUThQnJ8x0XAtSpVpk9k8BLBT3R17Bx66Xav0awwh97YfBQVb0THFpgb34Z3vGsw8k0swqiPeXI8DcGLNw7yp8kevnbXKJWy4K9rJJAOJs0rVYG+IswrVZGuJs0rVYG+IswrVZGuJs0rVYG+IswrVZGuJs0rVY+zcnJPKDQOgKcwAAAABJRU5ErkJggg==);
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
    <script>

        function folderClick(folderName){
            $("#folderName").val(folderName);
            folder('new');
        }
        function folder(type){
            var folderName = $("#folderName").val();
            if (type == 'new'){
                $(".folder").append("<span class='child'>&nbsp;&nbsp;》" + folderName + "</span>");
                $(".folderPath").append("<span class='child'>"+folderName+"</span>");
                $(".file").remove();
            }else if(type == 'back'){
                var spanSize = $(".folderPath").find("span").length;
                if(spanSize > 0){
                    $(".folder span:last-child").remove();
                    $(".folderPath span:last-child").remove();
                }
            }

            var top = $(".folderPath").find("span").length;
            if(top == 0){
                $(".fileBtn.goback-folder").hide();
            }else{
                $(".fileBtn.goback-folder").show();
            }

            var surveyCno = $("#surveyCno").val().toLowerCase();
            var taskName = $("#taskName").val();
            var folder1 = "";//任务类型 目录
            // window.parent.$("div[name=taskIds]").each(function(){
            //     console.log("class",$(this).attr("class"));
            //     if ($(this).attr("class") == "item active") {
            //         folder1 = $.trim($(this).text());
            //     }
            // });


            var folder2 = $("#directionName").val();//方向名称目录
            var appendPath = "";//子目录
            $(".folderPath span").each(function(){
                appendPath = appendPath + $(this).text() + "/";
            });

            // debugger
            var folder = "" + folder1 + "/" + folder2 + "/" + appendPath;
            var url = "${ctx}/sftp/survey/getFileSftp",param = {"surveyCno":surveyCno,"taskName" : taskName,"folder":folder};
            <%--$.ajax({--%>
            <%--    url:url,--%>
            <%--    data:param,--%>
            <%--    success:function(res,param){--%>
            <%--        var json = JSON.parse(res);--%>
            <%--        var f = json.success;--%>
            <%--        if (f){--%>
            <%--            $("#materialImgsDiv").html("");--%>
            <%--debugger--%>
            <%--            var files = json.files;--%>
            <%--            if(files){--%>
            <%--                for (var i = 0; i < files.length; i++) {--%>
            <%--                    var file = files[i];--%>
            <%--                    // 添加文件夹 及 文件--%>
            <%--                    if (file.folder){//文件--%>
            <%--                        // $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='" + file.filePath + "'/><a class='img-title' title='" + file.fileName + "'> "+ file.fileName +"</a></div>");--%>
            <%--                    } else{//文件夹--%>
            <%--                        $("#materialImgsDiv").append("<div onclick='folderClick(\"" + file.fileName + "\")' class='file' ><div class='folder-img'></div><a class='img-title' title='" + file.fileName + "'> "+ file.fileName + "(" + (file.fileSize) +")" + "</a></div>");--%>
            <%--                    }--%>
            <%--                }--%>
            <%--                for (var i = 0; i < files.length; i++) {--%>
            <%--                    var file = files[i];--%>
            <%--                    // 添加文件夹 及 文件--%>
            <%--                    if (file.folder){//文件--%>
            <%--                        &lt;%&ndash;if(file.fileExt == 'rar' || file.fileExt == 'zip'){&ndash;%&gt;--%>
            <%--                        &lt;%&ndash;    $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><img class='image' data-id='"+(i + 1)+"' src='${ctx}/img/rar.jpg'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");&ndash;%&gt;--%>
            <%--                        &lt;%&ndash;}else{&ndash;%&gt;--%>
            <%--                        &lt;%&ndash;    $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><img class='image' data-id='"+(i + 1)+"' src='" + file.filePath + "'/><a class='img-title' title='" + file.fileName + "'> "+ file.fileName +"</a></div>");&ndash;%&gt;--%>
            <%--                        &lt;%&ndash;}&ndash;%&gt;--%>

            <%--                        if(file.fileExt == 'rar' || file.fileExt == 'zip'){--%>
            <%--                            $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='${ctx}/img/rar.jpg'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");--%>
            <%--                        }else if(file.fileExt == 'pdf'){--%>
            <%--                            $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='${ctx}/img/pdf.jpg'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");--%>
            <%--                        }else if(file.fileExt == 'doc' || file.fileExt == 'docx'){--%>
            <%--                            $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='${ctx}/img/word.png'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");--%>
            <%--                        }else if(file.fileExt == 'xls' || file.fileExt == 'xlsx'){--%>
            <%--                            $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='${ctx}/img/excel.png'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");--%>
            <%--                        }else{--%>
            <%--                            $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+(i + 1)+"' src='" + file.filePath + "'/><a class='img-title' title='" + file.fileName + "' href='"+file.filePath+"' target='_blank'> "+ file.fileName +"</a></div>");--%>
            <%--                        }--%>
            <%--                    } else{//文件夹--%>
            <%--                        // $("#materialImgsDiv").append("<div class='file' id='data-id-"+(i + 1)+"'  data-id='"+(i + 1)+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+(i + 1)+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><div class='folder-img'></div><a class='img-title' title='" + file.fileName + "'> "+ file.fileName + "("+file.fileSize+")" + "</a></div>");--%>
            <%--                    }--%>
            <%--                }--%>
            <%--                imgInit();//绑定图片的事件--%>
            <%--            }--%>
            <%--        }--%>
            <%--        // window.parent.load();--%>
            <%--    }--%>
            <%--});--%>
        }

    </script>
</head>
<body>
<div class="contain">
    <div class="dialogs">
        <div class="d-name">请输入子文件夹名称</div>
        <input type="text" value="" id="folderName">
        <div class="d-btns">
            <div class="d-btn-0">取消</div>
            <div class="d-btn-1">确定</div>
        </div>
    </div>
    <input type="hidden" id="surveyCno" value="${surveyCno}">
    <input type="hidden" id="dataId" value="${dataId}">
    <input type="hidden" id="taskName" value="${taskName}">
    <input type="hidden" id="directionName" value="${direction.directionName}">
    <input type="hidden" id="fileSize" value="">
    <input type="hidden" id="fileList" value="">
    <div class="body-left" id="body-left">
        <div class="title-top">
            <div class="folderPath"></div>
            <div class="title" id="title"><div class="folder"></div></div>
            <div class="fileBtns">
                <div class="fileBtn goback-folder">
                    <svg t="1571794658068" class="icon" viewBox="0 0 1024 1024" version="1.1"
                         xmlns="http://www.w3.org/2000/svg" p-id="6673" width="22" height="22">
                        <path
                                d="M170.666667 204.8h796.444444V910.222222H108.088889v-62.577778H910.222222V267.377778H170.666667l96.711111 102.4-39.822222 51.2L102.4 284.444444 56.888889 238.933333l45.511111-45.511111L227.555556 56.888889l45.511111 45.511111L170.666667 204.8z"
                                p-id="6674" fill="#259B24"></path>
                    </svg>
                    <span>返回上级</span>
                </div>
                <%--                <div class="fileBtn addFile">--%>
                <%--                    <svg t="1571731726122" class="icon" viewBox="0 0 1024 1024" version="1.1"--%>
                <%--                         xmlns="http://www.w3.org/2000/svg" p-id="945" width="26" height="26">--%>
                <%--                        <path--%>
                <%--                                d="M484 443.1V528h-84.5c-4.1 0-7.5 3.1-7.5 7v42c0 3.8 3.4 7 7.5 7H484v84.9c0 3.9 3.2 7.1 7 7.1h42c3.9 0 7-3.2 7-7.1V584h84.5c4.1 0 7.5-3.2 7.5-7v-42c0-3.9-3.4-7-7.5-7H540v-84.9c0-3.9-3.1-7.1-7-7.1h-42c-3.8 0-7 3.2-7 7.1z m396-144.7H521L403.7 186.2c-1.5-1.4-3.5-2.2-5.5-2.2H144c-17.7 0-32 14.3-32 32v592c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V330.4c0-17.7-14.3-32-32-32zM840 768H184V256h188.5l119.6 114.4H840V768z"--%>
                <%--                                fill="#259B24" p-id="946"></path>--%>
                <%--                    </svg>--%>
                <%--                    <span>新建文件夹</span>--%>
                <%--                </div>--%>
            </div>
        </div>

        <%--        <div class="folder"></div>--%>
        <input hidden name="surveyReimbursementFileDtoList" value="${direction.surveyReimbursementFileDtoList.size()}">
        <div class='files' id="materialImgsDiv">

        </div>
    </div>
    <div class="body-left body-left-detail">
        <div class="img-detail">
            <div class="img-src" data-id="1" id="pic1"><img src="" class="image" id="imgOne"/></div>
            <div class="img-btns">
                <div class="img-btn rolateleft"><img src="${ctx}/img/report/icon-rolate-0.png" alt=""><span>向左旋转</span> </div>
                <div class="img-btn rolateright"><img src="${ctx}/img/report/icon-rolate-1.png" alt=""><span>向右旋转</span></div>
                <div class="img-btn img-btn3"><img src="${ctx}/img/report/icon-text.png" alt=""><span>文字识别</span></div>
                <div class="img-btn goback"><img src="${ctx}/img/report/icon-goback.png" alt=""><span>返回上级</span></div>
            </div>
            <div class="img-pre"></div>
            <div class="img-next"></div>
            <div class="img-pages" id="pageNum">
                <div></div>
            </div>

        </div>
    </div>
</div>
</div><!--main end-->
</div>


<div id="dialogId"></div>
<script type="text/javascript">
    var ctx="${ctx}";
</script>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/caseMid/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/caseMid/js/xiangce.js" type="text/javascript"></script>
<script src="${ctx}/js/progress.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui.min.js"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        $('.body-left .files').css({
            'paddingTop': $('.title-top').height() + 10
        })
        $('.addFile').click(function () {
            $('.dialogs').show()
        })
        $('.goback').click(function () {

        })
        $('.goback-folder').click(function () {
            folder('back');
        })
        $('.d-btn-0').click(function () {
            $('.dialogs').hide()
        })
        $('.d-btn-1').click(function () {
            if(!$("#folderName").val()){
                alert("请输入文件夹名称");return;
            }
            folder('new');
            $("#folderName").val("");
            $('.dialogs').hide()
        })
        folder('back');
    })

    // var count = $("input[name=surveyReimbursementFileDtoList]").val();
    var dataId = $("#dataId").val();
    //count=0说明数据库没有凭证内容
    //初始化页面判断缓存中是否有数据 有的话继续渲染到后面 因为有可能编辑里面的内容部分是数据库保存的  部分是后上传为保存的
    var files = sessionStorage.getItem("imgStr");
    if (files!=null && files!=""){
        files = files.split(",");
        for (let i = 0; i <files.length ; i++) {
            if (files[i]!=''){
                var str = "<div class='file'  data-index='"+i+"'  data-id='0'><img class='image' data-id='"+i+"' src='"+files[i]+"'/><a class='img-title'  target='_blank'></a></div>";
                $("#materialImgsDiv").append(str);
            }
        }
    }



    //-------------------
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            files = [];
            alert("上传成功");
            reload();
        }else{
            alert(apiRsp.msg);return;
        }
    }
    var files = [];
    var fileList = [];
    function uploadImg111(){
//        if(files.length == 0){
//            alert("请选择文件");return;
//        }
        $("#files").val(JSON.stringify(files));
        $("#btnUpload").attr("disabled","true");
        clearInterval(aa);
        clearInterval(bb);
        $("#fileList").val(JSON.stringify(fileList));
        alert("上传成功");
        <%--$.ajax({--%>
        <%--url:'${ctx}/survey/case/directionFileMidOK',--%>
        <%--type:"get",--%>
        <%--data : {"files":JSON.stringify(files)},--%>
        <%--success:function(res,param){--%>
        <%--alert("上传成功");--%>

        <%--var f = JSON.parse(res).results;--%>
        <%--for(var i = 0 ; i < f.length ; i ++){--%>
        <%--for(var j = 0 ; j < fileList.length ; j ++){--%>
        <%--if(f[i].filePath = fileList[i].filePath){--%>
        <%--fileList[i]["id"] = f[i].id;--%>
        <%--}--%>
        <%--}--%>
        <%--}--%>
        <%--$("#div_pro").html("");--%>
        <%--$("#fileList").val(JSON.stringify(fileList));--%>
        <%--}--%>
        <%--});--%>
    }

    //获取方向名称
    directionName();
    function directionName(){
        var directionName = $("#directionName").val();
        $("#title").prepend("<div class='icon-step'  style='width: 30px;display: inline-block; margin-right: 8px;'><div class='index active'>1</div></div>"+"<span>查看" +directionName+"附件</span>");
    }


    function openImgs(id,type){
        if(type ==1){
            $("#show").show()
            var fileShow = "";
            for(var i = 0 ; i < fileList.length ; i ++){
                fileShow +='<li><a href="javascript:void(0);">'  + '<img width="90px" height="60px" bigimg='+fileList[i].filePath+'  src='+fileList[i].filePath+'  >' + '</a></li>'
            }
            $("#fileShow").html(fileShow);

            $("#pic1").attr("src",fileList[0].filePath);
            $("#character").attr("href",'javascript:'+'character(\"'+fileList[0].filePath+'\")');
            $("#uploadDiv").hide();
        }else if(type ==2){
            $("#show").hide();
            $("#uploadDiv").show();
        }

    }

    function character(url){
        $.ajax({
            url:'${ctx}/survey/case/operate',
            type:"post",
            data : {"url":url,"btnCode":"character"},
            success:function(res,param){
                var f = JSON.parse(res).results;
                var ee =[];
                window.parent.$("#recognition").show();
                if(f.length == 0){
                    window.parent.$("#recognitionResult").text("未识别到内容");
                }else{
                    for(var i = 0 ; i < f.length ; i ++){
                        ee.push(f[i]);
                    }
                    window.parent.$("#recognitionResult").text(ee);
//                    parent.$("#recognitionResult").click();
                    parent.$("#recognitionResult").keyup();
                }

            }
        });
    }

    //定义图片下标（用于放大展示）
    findIndexNum();
    var fileSize = $("#fileSize").val();
    var indexNum = fileSize || 0;
    function findIndexNum(){
        if(fileList!=null && fileList.length >0){
            indexNum = indexNum*1 + fileList.length*1;
        }
    }

    function setDataURL(){
        files = [];
        successNum=0;
        step = 0;
        $("#btnUpload").attr("disabled","true");
        $("#materialFileupload").on("change",chgUpload);
        //判断是新增、还是修改
        // var taskName = "temporary"; //默认新增
        <%--if('${direction.taskName}'){--%>
        <%--    taskName = '${direction.taskName}';--%>
        <%--}--%>

        var taskName = "";//任务类型 目录
        // window.parent.$("div[name=taskIds]").each(function(){
        //     if ($(this).attr("class") == "item active") {
        //         taskName = $.trim($(this).text());
        //     }
        // });


        var folder = "";
        $(".folderPath span").each(function(){
            folder = folder + $(this).text() + ",";
        })
        var uploadDirectionName = window.parent.$("#uploadDirectionName").val();
        $('#materialFileupload').fileupload({
            url : "${ctx}/sftp/survey/uploadSftp?modelType=direction&surveyCno="+$("#surveyCno").val()+"&directionName=" + uploadDirectionName + "&taskName="+taskName + "&folder=" + folder,
            done: function (e, data) {
                domNum = random(80,90);
                var r  = data.result;
                r = JSON.parse(r);
                if(r.success == 'false'){
                    alert(r.message);
                    return;
                }
                var file = r.surveyFile;
                var sss = r.success;
                var item = {
                    "fileName" : file.fileName,
                    "filePath" : file.filePath
                };
                files.push(item);
                fileList.push(item);
                var fileNum = $("#fileNum").val();
                indexNum = indexNum*1 + 1*1;

                var ext = file.fileExt;
                var path ="";
                if(ext == 'doc'||ext == 'docx'){
                    path= '${ctx}/img/word.png';
                }else if(ext == 'png' || ext == 'jpg'){
                    path = file.filePath;
                }else if(ext == 'xls' || ext == 'xlsx'){
                    path= '${ctx}/img/excel.png';
                }else if(ext == 'zip' || ext == 'rar'){
                    path= '${ctx}/img/rar.jpg';
                }else{
                    path = file.filePath;
                }
                $("#materialImgsDiv").append("<div class='file' id='data-id-"+indexNum+"'  data-id='"+indexNum+"'><div class='icon-delete'><a href='javascript:;' onclick='deleteFile(\""+indexNum+"\",\""+file.filePath+"\","+null+","+null+")' class='icon-deleteX'>X</a></div><img class='image' data-id='"+indexNum+"' src='" + path + "'/><a class='img-title' title='" + file.fileName + "'> "+ file.fileName +"</a></div>");
//                $("#materialImgsDiv").append("<div class='file' data-id='"+indexNum+"'><div class='icon-delete'>X</div><img class='image' data-id='"+indexNum+"' src='" + file.filePath + "'/></div>");
                value += file.filePath + ","
                $("#img").val(value);
                $("#fileList").val(JSON.stringify(fileList));
                $("#materialImgsDiv").show();
                $("#div_pro").show();

                if($("#fileNum").val() == successNum){
                    setTimeout(function(){
                        photoSee();
                    }, 3000)
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

            },
            success : function (e,data){
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


    function ajaxSubmit1(url,data,successCallback,tipTitle,confirmTitle,failCallback){
        if(confirmTitle && !confirm(confirmTitle)){
            return;
        }
        $.ajax({
            url:url,
            data:data,
            success:function(event,param){
                successCallback(this,event,param)
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



    //------------------------------------------------------------------------------------------------------------------
    photoSee1();

    function photoSee1() {
        var dNumber = 0
        var clientHeight = document.documentElement.clientHeight
        $('.contain').height(clientHeight - 20)
        $('.img-src').height(clientHeight - 20)
        $('.img-src .image').height(clientHeight - 20)
        $('.img-detail').height(clientHeight - 20)

        $(window).resize(function () {
            var clientHeight = document.documentElement.clientHeight
            $('.contain').height(clientHeight - 20)
            $('.img-src').height(clientHeight - 100)
            $('.img-src .image').height(clientHeight - 100)
            $('.img-detail').height(clientHeight - 100)
        })
        $('.files .image').each(function (i) {
            var url = $(this).attr('src')
            var width, height, arr = getNaturalWH(url)
            var _width = arr[0],
                _height = arr[1]
            if (1 <= _width / _height) {
                width = 148; //以框的宽度为标准
                height = 148 * (_height / _width);
            } else {
                width = 148 * (_width / _height);
                height = 148; //以框的高度为标准
            }
            var cssArr = {
                'width': width,
                'height': height
            }
            if (width == 148) {
                cssArr = {
                    'width': width,
                    'height': height,
                    'margin-top': (148 - height) / 2
                }
            }
            $(this).css(cssArr)
        })

        function getNaturalWH(src) {
            var image = new Image();
            image.src = src;
            return [image.width, image.height];
        }

        $('.files .image').on('click', function (e) {
            $('.body-left').hide()
            $('.body-left-detail').show()
            var id = $(this).attr('data-id')
            var url = $(this).attr('src')
            $('.img-src').attr('data-id', id)
            $('.img-src img').attr('src', url)
            AutoSize(url)
            $("#pageNum").empty();
            var $lis = $('.files .image')
            var pageNum = Number($('.img-src').attr('data-id'));
            $("#pageNum").append("<div>"+ pageNum + "/" + $lis.length +"</div>")
        })
        $('.goback').on('click', function () {
            $('.body-left').show()
            $('.body-left-detail').hide()
            $('.img-src img').attr({
                'style': ''
            })
            dNumber = 0
        })
        $('.img-btn3').on('click', function () {
            var url = $("#imgOne").attr('src');
            character(url);

        })
        $('.rolateleft').on('click', function () {
            dNumber = dNumber - 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)'
            })

        })
        $('.rolateright').on('click', function () {
            dNumber = dNumber + 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)'
            })
        })

        function AutoSize(src) {
            var maxWidth = $('.img-src').width(),
                maxHeight = $('.img-src').height(),
                width, height, res_height, res_width
            var image = new Image();
            image.src = src;
            if (image.width < maxWidth && image.height < maxHeight) {
                width = image.width;
                height = image.height;
            } else //原图片宽高比例 大于 图片框宽高比例,则以框的宽为标准缩放，反之以框的高为标准缩放
            {
                if (dNumber % 180 != 0) {
                    if (maxWidth / maxHeight <= image.height / image.width) //原图片宽高比例 大于 图片框宽高比例
                    {
                        height = maxWidth; //以框的宽度为标准
                        width = maxWidth * (image.width / image.height);
                        if (width > height) {
                            $('.img-src img').css('margin-left', (maxWidth - width) / 2)
                        }
                    } else { //原图片宽高比例 小于 图片框宽高比例
                        height = maxHeight * (image.height / image.width);
                        width = maxHeight; //以框的高度为标准
                        $('.img-src img').css('margin-left', -(maxHeight - height) / 2 + (maxWidth -
                            height) /
                            2)
                    }

                } else {
                    if (maxWidth / maxHeight <= image.width / image.height) //原图片宽高比例 大于 图片框宽高比例
                    {
                        width = maxWidth; //以框的宽度为标准
                        height = maxWidth * (image.height / image.width);
                        // $('.img-src img').css({
                        //     'margin-left': -(maxHeight - height) / 2 + (maxWidth - height) / 2,
                        // })
                    } else { //原图片宽高比例 小于 图片框宽高比例
                        width = maxHeight * (image.width / image.height);
                        height = maxHeight; //以框的高度为标准
                    }
                }
            }

            if (height < maxHeight) {
                $('.img-src img').css({
                    'margin-top': (maxHeight - height) / 2
                })
            } else {
                $('.img-src img').css({
                    'margin-top': 0
                })
            }

            $('.img-src img').css({
                'width': width,
                'height': height
            })
        }
    }

    //下一张
    $('.img-next').on('click', function (e) {
        var curIndex = Number($('.img-src').attr('data-id'))
        var $lis = $('.files .image')
        if (curIndex != $lis.length) {
            var nextUrl = $lis.eq(curIndex).attr('src')
            $('.img-src').attr('data-id', curIndex + 1)
            $('.img-src img').attr({
                'src': nextUrl,
                'style': ''
            })
            dNumber = 0
            AutoSize(nextUrl)
            $("#pageNum").empty();
            $("#pageNum").append("<div>"+ (curIndex + 1) + "/" + $lis.length +"</div>")
        }
        if (curIndex == $lis.length) {
            var nextUrl = $lis.eq(0).attr('src')
            $('.img-src').attr('data-id', 1)
            $('.img-src img').attr({
                'src': nextUrl,
                'style': ''
            })
            dNumber = 0
            AutoSize(nextUrl)
            $("#pageNum").empty();
            $("#pageNum").append("<div> 1 /" + $lis.length +"</div>")
        }

    });
    //上一张
    $('.img-pre').on('click', function (e) {
        var curIndex = Number($('.img-src').attr('data-id'))
        var $lis = $('.files .image')
        if (curIndex > 1) {
            var nextUrl = $lis.eq(curIndex - 2).attr('src')
            $('.img-src').attr('data-id', curIndex - 1)
            $('.img-src img').attr({
                'src': nextUrl,
                'style': ''
            })
            dNumber = 0
            AutoSize(nextUrl)
            $("#pageNum").empty();
            $("#pageNum").append("<div>"+ (curIndex -1) + "/" + $lis.length +"</div>")
        }
        if (curIndex == 1) {
            alert('已经是第一张了')
        }


    });

    function photoSee() {
        var dNumber = 0
        var clientHeight = document.documentElement.clientHeight
        $('.contain').height(clientHeight)
        $('.img-src').height(clientHeight)
        $('.img-src .image').height(clientHeight)
        $('.img-detail').height(clientHeight)

        $(window).resize(function () {
            var clientHeight = document.documentElement.clientHeight
            $('.contain').height(clientHeight)
            $('.img-src').height(clientHeight)
            $('.img-src .image').height(clientHeight)
            $('.img-detail').height(clientHeight)
        })
        $('.files .image').each(function (i) {
            var url = $(this).attr('src')
            var width, height, arr = getNaturalWH(url)
            var _width = arr[0],
                _height = arr[1]
            if (1 <= _width / _height) {
                width = 148; //以框的宽度为标准
                height = 148 * (_height / _width);
            } else {
                width = 148 * (_width / _height);
                height = 148; //以框的高度为标准
            }
            var cssArr = {
                'width': width,
                'height': height
            }
            if (width == 148) {
                cssArr = {
                    'width': width,
                    'height': height,
                    'margin-top': (148 - height) / 2
                }
            }
            $(this).css(cssArr)
        })

        function getNaturalWH(src) {
            var image = new Image();
            image.src = src;
            return [image.width, image.height];
        }

        $('.files .image').on('click', function (e) {
            $('.body-left').hide()
            $('.body-left-detail').show()
            var id = $(this).attr('data-id')
            var url = $(this).attr('src')
            $('.img-src').attr('data-id', id)
            $('.img-src img').attr('src', url)
            AutoSize(url)

            $("#pageNum").empty();
            var $lis = $('.files .image')
            var pageNum = Number($('.img-src').attr('data-id'));
            $("#pageNum").append("<div>"+ pageNum + "/" + $lis.length +"</div>")
        })
        $('.goback').on('click', function () {
            $('.body-left').show()
            $('.body-left-detail').hide()
            $('.img-src img').attr({
                'style': ''
            })
            dNumber = 0
        })
        $('.img-btn3').on('click', function () {
            var url = $("#imgOne").attr('src');
            character(url);

        })
        $('.rolateleft').on('click', function () {
            dNumber = dNumber - 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)'
            })

        })
        $('.rolateright').on('click', function () {
            dNumber = dNumber + 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)'
            })
        })


    }
    var dNumber = 0;
    function getNaturalWH(src) {
        var image = new Image();
        image.src = src;
        return [image.width, image.height];
    }
    function AutoSize(src) {

        var maxWidth = $('.img-src').width(),
            maxHeight = $('.img-src').height(),
            width, height, res_height, res_width
        var image = new Image();
        image.src = src;
        if (image.width < maxWidth && image.height < maxHeight) {
            width = image.width;
            height = image.height;
        } else //原图片宽高比例 大于 图片框宽高比例,则以框的宽为标准缩放，反之以框的高为标准缩放
        {
            if (dNumber % 180 != 0) {
                if (maxWidth / maxHeight <= image.height / image.width) //原图片宽高比例 大于 图片框宽高比例
                {
                    height = maxWidth; //以框的宽度为标准
                    width = maxWidth * (image.width / image.height);
                    if (width > height) {
                        $('.img-src img').css('margin-left', (maxWidth - width) / 2)
                    }
                } else { //原图片宽高比例 小于 图片框宽高比例
                    height = maxHeight * (image.height / image.width);
                    width = maxHeight; //以框的高度为标准
                    $('.img-src img').css('margin-left', -(maxHeight - height) / 2 + (maxWidth -
                        height) /
                        2)
                }

            } else {
                if (maxWidth / maxHeight <= image.width / image.height) //原图片宽高比例 大于 图片框宽高比例
                {
                    width = maxWidth; //以框的宽度为标准
                    height = maxWidth * (image.height / image.width);
                    // $('.img-src img').css({
                    //     'margin-left': -(maxHeight - height) / 2 + (maxWidth - height) / 2,
                    // })
                } else { //原图片宽高比例 小于 图片框宽高比例
                    width = maxHeight * (image.width / image.height);
                    height = maxHeight; //以框的高度为标准
                }
            }
        }

        if (height < maxHeight) {
            $('.img-src img').css({
                'margin-top': (maxHeight - height) / 2
            })
        } else {
            $('.img-src img').css({
                'margin-top': 0
            })
        }

        $('.img-src img').css({
            'width': width,
            'height': height
        })
    }
    function imgInit(){
        dNumber = 0;
        $('.files .image').each(function (i) {
            var url = $(this).attr('src')
            var width, height, arr = getNaturalWH(url)
            var _width = arr[0],
                _height = arr[1]
            if (1 <= _width / _height) {
                width = 148; //以框的宽度为标准
                height = 148 * (_height / _width);
            } else {
                width = 148 * (_width / _height);
                height = 148; //以框的高度为标准
            }
            var cssArr = {
                'width': width,
                'height': height
            }
            if (width == 148) {
                cssArr = {
                    'width': width,
                    'height': height,
                    'margin-top': (148 - height) / 2
                }
            }
            $(this).css(cssArr)
        })
        $('.files .image').on('click', function (e) {
            $('.body-left').hide()
            $('.body-left-detail').show()
            var id = $(this).attr('data-id')
            var url = $(this).attr('src')
            $('.img-src').attr('data-id', id)
            $('.img-src img').attr('src', url)
            AutoSize(url)

            $("#pageNum").empty();
            var $lis = $('.files .image')
            var pageNum = Number($('.img-src').attr('data-id'));
            $("#pageNum").append("<div>"+ pageNum + "/" + $lis.length +"</div>")
        })

        $('.img-pre').on('click', function (e) {
            alert(2)
            var curIndex = Number($('.img-src').attr('data-id'))
            var $lis = $('.files .image')
            if (curIndex > 1) {
                var nextUrl = $lis.eq(curIndex - 2).attr('src')
                $('.img-src').attr('data-id', curIndex - 1)
                $('.img-src img').attr({
                    'src': nextUrl,
                    'style': ''
                })
                dNumber = 0
                AutoSize(nextUrl)
                $("#pageNum").empty();
                $("#pageNum").append("<div>"+ (curIndex -1) + "/" + $lis.length +"</div>")
            }
            if (curIndex == 1) {
                alert('已经是第一张了')
            }


        })
        $('.img-next').on('click', function (e) {
            alert(2)
            var curIndex = Number($('.img-src').attr('data-id'))
            var $lis = $('.files .image')
            if (curIndex != $lis.length) {
                var nextUrl = $lis.eq(curIndex).attr('src')
                $('.img-src').attr('data-id', curIndex + 1)
                $('.img-src img').attr({
                    'src': nextUrl,
                    'style': ''
                })
                dNumber = 0
                AutoSize(nextUrl)
                $("#pageNum").empty();
                $("#pageNum").append("<div>"+ (curIndex + 1) + "/" + $lis.length +"</div>")
            }
            if (curIndex == $lis.length) {
                var nextUrl = $lis.eq(0).attr('src')
                $('.img-src').attr('data-id', 1)
                $('.img-src img').attr({
                    'src': nextUrl,
                    'style': ''
                })
                dNumber = 0
                AutoSize(nextUrl)
                $("#pageNum").empty();
                $("#pageNum").append("<div> 1 /" + $lis.length +"</div>")
            }

        })

    }


</script>
</body>
</html>