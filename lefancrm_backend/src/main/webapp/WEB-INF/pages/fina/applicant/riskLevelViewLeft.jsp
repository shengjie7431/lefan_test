<%--
  Created by IntelliJ IDEA.
  User: zhuxia
  Date: 2021/1/4
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <head>
        <title>文字识别</title>
        <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
        <link rel="stylesheet" href="${ctx}/css/viewer.min.css">
        <style>
            .main {
                width: 100%;
                height: 100%;
            }

            .content {
                display: flex;
                justify-content: space-around;
                width: 98%;
                height: 100%;
            }

            .content-left {
                width: 49%;
                height: 100%;
                overflow: auto;
            }

            .content-right {
                width: 49%;
                height: 100%;
                overflow: auto;
                background-color: #eee;
            }
            .files {
                /*margin-top: 10px;*/
                width: 100%;
                display: flex;
                flex-wrap: wrap;
                /*max-height: 330px;*/
                overflow: auto;
            }

            .files .file {
                width: 100px;
                height: 100px;
                border: 1px solid #d0c9c9;
                margin-bottom: 20px;
                margin-right: 20px;
                position: relative;
            }

            .files .file .image {
                width: 100%;
                height: 100%;
                display: block;
                margin: 0 auto;
            }
            .lf-none{
                width: 100%;
                color: #666;
                padding: 10px 0 ;
            }

            .png-text{
                width: 96%;
                margin: 10px auto;
            }
            .files .file .img-file{
                background: url(data:image/png;base64,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);
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                width: 59%;
                height: 59%;
                display: block;
                margin: 0 auto;
                margin-top: 7%;
            }
        </style>
    </head>
<body>
<input type="hidden" value='${allEnumJson}' name="allEnum" id="allEnum" />
<input type="hidden" value='${finaApplicantFilesJson}' name="finaApplicantFiles" id="finaApplicantFiles" />
<div class="main">
    <div class="content">
        <div class='files' id="uploadFiles">
        </div>
    </div>
</div>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>
<script>
    $(function () {
        var allEnum = $('#allEnum').val()
        var finaApplicantFiles = $('#finaApplicantFiles').val()

        if (allEnum){
            allEnum = JSON.parse(allEnum)
        }

        if (finaApplicantFiles){
            finaApplicantFiles = JSON.parse(finaApplicantFiles)
        }

        var _htmlFiles = ''
        var files = finaApplicantFiles
        if (files.length){
            files.map(function (cur) {
                var file = cur.filePath
                var id = file.lastIndexOf('.')
                var type = file.slice(id + 1)
                var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
                if (imgArr.indexOf(type) > -1) {
                    _htmlFiles += ' <div class="file">\n' +
                        '                        <img src="' + cur.filePath + '" class="image"></img>\n' +
                        '                    </div>'

                }


            })

            $('.files').append(_htmlFiles)
            viewer = new Viewer(document.getElementById('uploadFiles'),{
                viewed: function (e) {
                    setText()
                    $(' .viewer-container .viewer-canvas img').css({
                        'margin-left': 'auto'
                    })

                }
            });
        }else {
            _htmlFiles = '<div class="lf-none">暂无数据</div>'
            $('.files').append(_htmlFiles)
        }

        function setText() {
            var ul = $('.viewer-container .viewer-footer .viewer-navbar .viewer-list li')
            var activeLi = $('.viewer-container .viewer-footer .viewer-navbar .viewer-list .viewer-active')
            var i  =ul.index(activeLi)
            var _html = '<div class="lf-none">文本识别中<i class="layui-icon layui-icon-loading"></div>'
            $(parent.document).find('.png-text').html(_html)
            $.ajax({
                url: '${ctx}/fina/applicant/ajaxData',
                data: {
                    dataType: 'risk-level-image-word',
                    finaApplicantFileId : finaApplicantFiles[i].id,
                },
                success:function (res) {
                    var res= JSON.parse(res)
                    if (res.isSuccess){
                        var words = res.results.imageWords
                        if (words){
                            allEnum.map(function (cur) {
                                words = words.replace(eval('/'+cur.enumName+'/g'),'<span style="color:red">'+cur.enumName+'</span>')
                            })
                            words += words
                        }else {
                            words = '<div class="lf-none">暂无数据</div>'
                        }
                        $(parent.document).find('.png-text').html(words)
                    }else{
                        $(parent.document).find('.png-text').html('<div class="lf-none">暂无数据</div>')
                    }

                }
            })

        }


    })
</script>
</body>
</html>


