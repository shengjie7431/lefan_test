<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <style>
        .files {
            /*margin-top: 10px;*/
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            overflow: auto;
        }

        .files .file {
            width: 150px;
            height: 150px;
            border: 1px solid #d0c9c9;
            border-radius: 6px;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
            overflow: hidden;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }
        a{
            color: #3ba9ff;
        }
        .remark{
            font-size: 26px;
            color: red;
            font-weight: bold;
            display: none;
        }
        #imgBox img{
            display: block;
            width: 80%;
            margin: 30px auto;
        }
        #fullPage{
            display: none;
            background: rgba(0,0,0,0.9);
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 19930428;
        }
        #fullPage img{
            display: block;
            width: 100%;
        }
        #canvas{
            width: 100%;
            height: 100%;
            background: none;
            display: block;
        }
    </style>

    <input type="hidden" id="payImgUrl" value="${payImgUrl}">
</head>

<body>
<div class='files' id="uploadFiles">
</div>
<div id="fullPage">
    <canvas id="canvas"></canvas>
</div>
</body>

<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/viewImg.js" charset="utf-8"></script>

<script>
    $(function () {

        var imgurls = $('#payImgUrl').val()
        var _html = ''
        if(imgurls != null && imgurls!=''){
            var urlList = imgurls.split(',')
            urlList.map(function (value) {
                if(value){
                    _html += ' <div class="file">' +
                            '<img src="' + value + '" class="image">'+
                            ' </div>'
                }
            })
            $('.files').append(_html)
        }else{
            $('.files').append("暂无数据")
        }

        var wxScale = new WxScale({
            fullPage: document.querySelector("#fullPage"),
            canvas: document.querySelector("#canvas")
        });
        var imgBox=document.querySelectorAll("#uploadFiles img");
        for(var i=0; i<imgBox.length; i++){
            imgBox[i].onclick=function(e){
                wxScale.start(this);   //这里的this指向需要放大的这张图片
            }
        }
        // viewer = new Viewer(document.getElementById('uploadFiles'));

    })
</script>
</html>

