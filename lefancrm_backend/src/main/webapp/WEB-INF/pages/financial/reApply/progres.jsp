<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>每刻报销列表</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">

    <style>
        .main {
            min-width: auto!important;
            width: 98%;
            margin: 0 auto;
        }
        .records {
            width: 96%;
            margin: 0 auto;
        }

        .record-cell {
            width: 100%;
            display: flex;
            /*margin: 1px 0;*/
        }

        .cell-left {
            width:22%;
            color: #666;
        }

        .cell-right {
            min-height: 80px;
            /*padding-bottom: 30px;*/
            width: 77%;
            border-left: 2px solid #3ba9fe;
        }
        .record-cell:last-child .cell-right{
            border-left: none;
            padding-left: 2px;
        }

        /*.record-cell:last-of-type .cell-right{*/
        /*border-left: 2px solid #fff;*/
        /*}*/

        .icon-ricle {
            display: inline-block;
            width: 18px;
            height: 18px;
            color: #fff;
            border-radius: 18px;
            background: #3ba9fe;
            margin-left: -10px;
            text-align: center;
            font-size: 10px;
        }

        .userName {
            display: inline-block;
            width: auto;
            padding: 0 4px;
            color: #333;
        }
        .color1{
            color: #3ba9fe!important;
        }
        .color2{
            color: #19bd43!important;
        }

        .color3{
            color: red!important;
        }
        .color4{
            color: #df9110!important;
        }
        .remark {
            width: 260px;
        }

        .wxui-ddr-btn {
            width: 100%;
            padding: 20px 0;
            background-color: #fff;
            position: fixed;
            bottom: 0;
            z-index: 1000;
        }

        .wxui-ddr-btn button {
            width: 92%;
            margin: 0 auto;
            height: 84px;
            line-height: 84px;
            background-color: #3BA9FF;
            color: #fff;
            font-size: 28px;
        }

        .cell-right-p{
            padding: 6px 0;
            padding-left: 30px;
        }

        .files {
            margin-top: 10px;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            overflow: auto;
        }

        .files .file {
            width: 60px;
            height: 60px;
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
            width: 60px;
            height: 60px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 30px;
            margin-top: 10px;
            display: inline-block;
            background: #aaa;
            height: 40px;
            position: relative;
            width: 2px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 40px;
            left: 0;
            position: absolute;
            top: 0;
            width: 2px;
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


        .main-title {
            margin: 10px auto;
            padding-left: 15px;
            width: 96%;
            overflow: auto;
            font-size: 18px;
            font-weight: bold;
        }

        .main-content {
            width: 96%;
            height: 100%;

            margin: 0 auto;
            overflow: auto;
        }

        .main-right {
            width: 49%;
            height: 100%;

        }
        .layui-form-label{
            text-align: left;
            width: 93px;
        }
        .layui-input, .layui-textarea, .files-content{
            width: 340px!important;
        }
        .poi-no{
            pointer-events: none;
        }
        .lf-none{
            width: 100%;
            color: #666;
            padding: 10px 0;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        @media screen and (min-width:1250px){
            .layui-form-label{
                text-align: left;
                width: 130px;
            }
            .layui-input, .layui-textarea, .files-content{
                width: 360px!important;
            }
        }

    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value="${financialReApplyId}" id="financialReApplyId">
    <div class="main-title"></div>
    <div class="main-content">
        <div class="records">

        </div>
    </div>
</div>

<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>

<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    layui.use(['form', 'xmSelect', 'laydate', 'jquery', 'util', 'layer'], function () {
        var form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            $ = layui.jquery,
            layer = layui.layer,
            util = layui.util;

        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            data: {
                dataType: 're-progres-list',
                financialReApplyId: $('#financialReApplyId').val()
            },
            success: function (res) {
                var res = JSON.parse(res)
                setInfo(res.results)
            }
        })


        function setInfo(finaApplicantTrackListJson) {
            var _html =''
            var list = []

            list = finaApplicantTrackListJson
            if (!list.length){
                _html = '<div class="lf-none">暂无数据</div>'
                $('.records').append(_html)
                return;
            }
            _html = '<div class="record-cell">\n' +
                '                    <div class="cell-left">'+
                '                       <div class="cell-left-p"></div>'+
                '                       <div class="cell-left-p"></div>'+
                '                    </div>\n' +
                '                    <div class="cell-right" style="border-left: 2px dotted #3BA9FF;min-height: 10px;">\n' +
                '                        <div style="width: 100%;">\n' +
                // '                            <div class="icon-ricle"></div>\n' +
                '                            <div class="userName color1"></div>\n' +
                '                            <div class="userName"></div>\n' +
                '                        </div>\n' +
                '                        <div class="cell-right-p">\n'+
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>'
            var indexs = []
            var len = list.length
            list.map(function (cur,index) {
                var _index =len - index
                var _date = cur.progressTime ? util.toDateString(cur.progressTime,'yyyy-MM-dd') : ''
                var _time = cur.progressTime ? util.toDateString(cur.progressTime,'HH:mm:ss') : ''

                var colorClass ='color2',colorClass2='',colorClass3 = ''
                if (cur.progressType == 1){
                    colorClass2 = 'color2'
                }else if (cur.progressType == 2){
                    colorClass2 = 'color3'
                    colorClass = 'color3'
                }else if (cur.progressType == 3){
                    colorClass2 = 'color4'
                }else if (cur.progressType == 4){
                    colorClass2 = 'color4'
                }
                var _htmlFile = '',_htmlB = ''

                if (cur.files && cur.files.length){
                    indexs.push(index)
                    cur.files.map(function (item) {
                        _htmlFile += ' <div class="file">\n' +
                            '                        <img src="' + item.filePath + '" class="image"></img>\n' +
                            '                    </div>'
                    })
                    _htmlB =     '                        <div class="cell-right-p">\n' +
                        '                            <div>凭证：</div>\n' +
                        '                            <div class="files" id="uploadFiles'+index+'" >\n' +
                        _htmlFile+
                        '                            </div>\n' +
                        '                        </div>\n'
                }
                var progressDesc = ''

                if (cur.progressDesc){
                    progressDesc = '                            <div class="userName '+colorClass+'">（'+cur.progressDesc+'）</div>\n'

                }
                if (cur.progressType == 5){
                    progressDesc = ''
                    colorClass3 = 'color3'
                }
                _html += '<div class="record-cell">\n' +
                    '                    <div class="cell-left">'+
                    '                       <div class="cell-left-p">'+_date+'</div>'+
                    '                       <div class="cell-left-p">'+_time+'</div>'+
                    '                    </div>\n' +
                    '                    <div class="cell-right">\n' +
                    '                        <div style="width: 100%;display: inline-block;">\n' +
                    '                            <div class="icon-ricle">'+_index+'</div>\n' +
                    '                            <div class="userName color1">（'+cur.progressUserName+'）</div>\n' +
                    '                            <div class="userName '+colorClass3+'">'+cur.progressName+'</div>\n' +
                    progressDesc +
                    '                        </div>\n' +
                    '                        <div class="cell-right-p '+colorClass2+'">\n' +cur.details+
                    '                        </div>\n' +
                    _htmlB+
                    '                    </div>\n' +
                    '                </div>'

            })

            $('.records').append(_html)
            indexs.map(function (value) {
                window['viewer'+value] = new Viewer(document.getElementById('uploadFiles'+value));
            })

        }

    })
</script>



</body>

</html>