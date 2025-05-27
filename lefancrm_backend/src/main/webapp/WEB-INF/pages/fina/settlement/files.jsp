<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2020/12/4
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">
    <style>
        .main {
            width: 98%;
            margin: 0 auto
        }

        .lf-bars {
            padding: 10px 0;
            width: 100%;
            display: flex;
            border-bottom: 1px solid #bbb;
        }

        .lf-bars .lf-bar {
            width: 150px;
            height: 40px;
            line-height: 40px;
            background-color: rgba(59, 169, 255, 0.4);
            color: #fff;
            text-align: center;
            margin-right: 16px;
            cursor: pointer;
        }

        .lf-bars .lf-bar.active {
            background-color: #3BA9FF;
        }

        .m-content {
            padding: 10px 0;
            width: 100%;
            display: flex
        }

        .m-content .left {
            width: 200px;
            margin-right: 20px;
            height: 100%;
            overflow: auto
        }

        .m-content .right {
            width: 100%;
            height: 100%;
            overflow: auto
        }

        .menu-bars {
            width: 162px;
            border-top: 1px solid #bbb;
        }

        .menu-bars .menu-bar {
            padding: 10px 0;
            width: 160px;
            border: 1px solid #bbb;
            border-top: none;
            text-align: center;
            cursor: pointer;
        }

        .menu-bars .menu-bar.active {
            background-color: #3BA9FF;
            color: #fff;

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

        .files div.file-add {
            width: 100px;
            height: 100px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 48px;
            margin-top: 19px;
            display: inline-block;
            background: #aaa;
            height: 60px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 60px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }

        .file-title {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            background-color: rgba(187, 187, 187, 0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .file-del {
            position: absolute;
            top: 2px;
            right: 2px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .lf-btns {
            padding-top: 10px;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .lf-btns .lf-btn {
            width: 180px;
            height: 40px;
            line-height: 40px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 40px;
            cursor: pointer;
        }

        .lf-btns .lf-btn.active {
            background-color: #3BA9FF;
            color: #fff;
        }
        .lf-none{
            width: 100%;
            color: #666;
            padding: 10px 0 ;
        }
    </style>
</head>
<body>
<input type="hidden" value="${opr}" id="opr">
<input type="hidden" value="${settlementId}" id="settlementId">
<input type="hidden" value="${btnCode}" id="btnCode">
<input type="hidden" value="${keyId}" id="keyId">
<input type="hidden" value="${keyCode}" id="keyCode">

<div class="main">
    <%--<div class="m-header">--%>
        <%--<div class="lf-bars">--%>
            <%--<div class="lf-bar active" data-id="1">委托附件（20）</div>--%>
            <%--<div class="lf-bar" data-id="2">垫付资料收集（20）</div>--%>
            <%--<div class="lf-bar" data-id="3">签约资料（2）</div>--%>
            <%--<div class="lf-bar" data-id="4">确认到账录音（1）</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="m-content">
        <div class="left">
            <div class="menu-bars">
            </div>
        </div>
        <div class="right">
            <div class='files' id="uploadFiles" data-id="1">
                <c:if test="${opr == 'edit'}">
                    <div class="file-add uploadFile" lay-data="{id:1}">
                        <div class="icon-add v-p-upload"></div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <c:if test="${opr == 'edit'}">
    <div class="m-bottom">

        <div class="lf-btns">
            <div class="lf-btn" data-id="1">提交并关闭
            </div>
            <div class="lf-btn active" data-id="2">提交并分派机构
            </div>
        </div>
    </div>
    </c:if>
</div>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>
<script>
    $(function () {
        var isEdit = $('#opr').val() == 'edit' ? true : false

        var _heightH = $('.m-header').length ?  $('.m-header').height() : 0
        var _heightB = $('.m-bottom').length ?  $('.m-bottom').height() : 0

        var _height = $(document).height() - _heightH - _heightB -40

        $('.m-content').height(_height)

        var menuFiles = []

        var btnCode = $('#btnCode').val()
        if (btnCode && btnCode == 'repay-files'){
            $('.main .left').hide()
            $.ajax({
                url: '${ctx}/fina/pub/ajaxData',
                data: {
                    dataType: 'get-files',
                    keyId: $('#keyId').val(),
                    keyCode: $('#keyCode').val()

                },
                success:function (res) {
                    var res= JSON.parse(res)
                    if (res.isSuccess){

                        var _htmlFiles = ''
                        var files = res.results[0].files
                        if (files.length){
                            files.map(function (cur) {
                                _htmlFiles += setFileHtml(cur)
                            })
                            $('.files').append(_htmlFiles)
                            viewer = new Viewer(document.getElementById('uploadFiles'));
                        }else {
                            _htmlFiles = '<div class="lf-none">暂无数据</div>'
                            $('.files').append(_htmlFiles)

                        }

                    }else{

                    }

                }
            })
        }else {
            $.ajax({
                url: '${ctx}/fina/settlement/ajaxData',
                data: {
                    dataType: 'get-settlement-files',
                    settlementId: $('#settlementId').val()
                },
                success:function (res) {
                    var res= JSON.parse(res)
                    if (res.isSuccess){
                        menuFiles = res.results
                        setMenu(res.results)
                    }else{

                    }

                }
            })
        }


        function setMenu(list){
            var _html = ''
            list.map(function (cur, i) {
                var _active = ''
                if (i == 0) {
                    _active = 'active'
                }
                _html += '<div class="menu-bar ' + _active + '" data-id="' + cur.enumId + '" data-index="' + i + '">'+
                    '<div>'+ cur.enumName +'</div>'+
                    '<div>('+ cur.fileSettlements.length +')</div>'+
                    '</div>'
            })
            var _htmlFiles = ''
            var files = list[0].fileSettlements
            if (files.length){
                files.map(function (cur) {
                    _htmlFiles += setFileHtml(cur)
                })
                $('.files').append(_htmlFiles)
                viewer = new Viewer(document.getElementById('uploadFiles'));

            }else {
                _htmlFiles = '<div class="lf-none">暂无数据</div>'
                $('.files').append(_htmlFiles)

            }

            $('.menu-bars').append(_html)
        }
        $('.menu-bars').on('click', '.menu-bar', function () {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
                var i = _this.attr('data-index')
                var _htmlFiles = ''
                var files = menuFiles[i].fileSettlements
                var len =  $('.files .file').length
                if (files.length){
                    files.map(function (cur) {
                        _htmlFiles += setFileHtml(cur)
                    })
                    $('.files .file').detach()
                    $('.files .lf-none').detach()

                    $('.files').append(_htmlFiles)
                }else {
                    _htmlFiles = '<div class="lf-none">暂无数据</div>'
                    $('.files .file').detach()
                    $('.files .lf-none').detach()

                    $('.files').append(_htmlFiles)

                }
                if (len){
                    viewer.destroy()
                }
                if (files.length) {
                    viewer = new Viewer(document.getElementById('uploadFiles'));
                }
            }
        })

        $('.lf-bars').on('click', '.lf-bar', function () {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
                var _id = _this.attr('data-id')
            }
        })

        layui.use(['upload', 'layer'], function () {
            layer = layui.layer
            var upload = layui.upload;
            var uploadFile = upload.render({
                elem: '.uploadFile',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                data: {
                    'modelType': 'feeAddBill',
                    'id': $("#id").val()
                },
                multiple: true,
                accept: 'images', //只能上传图片
                acceptMime: 'image/*',
                before: function (obj) {
                    layer.load();
                },
                done: function (res) {
                    console.log("res:::" + JSON.stringify(res))
                    fileLen = $('.files[data-id="' + this.id + '"] .file').length
                    layer.closeAll('loading')
                    if (res.success == 'true') {
                        putSessionFiles(res.surveyFile);
                    } else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone: function (obj) {
                    if (fileLen) {
                        viewer.destroy()
                    }

                    viewer = new Viewer(document.getElementById('jq21'));
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
        });

        function setFileHtml(surveyFile) {
            var _htmlDel = ''
            if (isEdit){
                _htmlDel = '<div class="file-del"></div>'
            }
            var _html = ' <div class="file">\n' +
                '                        <img src="' + surveyFile.filePath + '" class="image"></img>\n' +
                '<div class="file-title">' + surveyFile.fileName + '</div>\n' + _htmlDel+
            '                    </div>'
           return _html
        }

        $('.lf-btn').click(function () {
            var _this = $(this)
            if (_this.attr('data-id') == 2){
                var ids = [],pid = ''
                var list = $('.layui-table-fixed .checkBoxH.active')

                list.map(function (cur) {
                    var _id = $(this).attr('data-id')
                    ids.push(_id)
                    if ($(this).attr('data-pid')){
                        pid = $(this).attr('data-pid')
                    }
                })

                if (list.length ==1){
                    if (!list.eq(0).attr('data-pid')){
                        pid = $(this).attr('data-id')
                    }
                }
                if (!ids.length) {
                    layer.msg('至少选中一条数据', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }

                var _flag = true
                layer.confirm('确认操作？',['取消','确定'],function () {
                    if (_flag){
                        _flag = false
                        var loadIndex = layer.load()
                        $.ajax({
                            url : "${ctx}/fina/settlement/operate",
                            type: 'post',
                            data : {
                                btnCode : "generate-settlement-order",
                                ids : ids.join(","),
                                finaParentId: pid
                            },
                            success : function(res){
                                var res = JSON.parse(res);
                                if (res.isSuccess){
                                    layer.msg('成功', {
                                        time: 2000,
                                        icon: 1
                                    },function(){
                                        // layer.closeAll()
                                        location.reload()
                                    })
                                }else{
                                    layer.close(loadIndex)
                                    layer.msg(res.msg, {
                                        time: 2000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    }
                }, function () {
                    _flag = true
                })
            }else if (_this.attr('data-id') == 1){
                layer.close(openIndex)
            }
        })
    })
</script>
</body>
</html>
