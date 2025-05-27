<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2020/12/7
  Time: 9:26
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
        .records {
            width: 96%;
            margin: 20px auto;
        }

        .record-cell {
            width: 100%;
            display: flex;
            margin: 1px 0;
        }

        .cell-left {
            width: 30%;
            max-width: 200px;
            color: #666;
        }

        .cell-right {
            min-height: 80px;
            /*padding-bottom: 30px;*/
            width: 70%;
            border-left: 2px solid #3ba9fe;
        }

        /*.record-cell:last-of-type .cell-right{*/
        /*border-left: 2px solid #fff;*/
        /*}*/

        .icon-ricle {
            display: inline-block;
            width: 16px;
            height: 16px;
            border-radius: 16px;
            background: #3ba9fe;
            margin-left: -9px;
        }

        .userName {
            display: inline-block;
            width: 200px;
            padding: 0 20px;
            color: #3ba9fe;
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

        .cell-right-p {
            padding: 6px 0;
            padding-left: 46px;
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

        .main {
            width: 100%;
            display: flex;
        }

        .main-left {
            width: 49%;
            height: 100%;
            overflow: auto;
            border-right: 1px solid #bbb
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
<input type="hidden" value="${btnCode}" id="btnCode">
<input type="hidden" value="${dataUrge}" id="dataUrge">

<input type="hidden" value="${finaInfoId}" id="finaInfoId">
<input type="hidden" value='${finaApplicantTrackListJson}' id="finaApplicantTrackListJson">



<div class="main">
    <div class="main-left">
        <div class="main-title"></div>
        <div class="main-content">
            <div class="records">

            </div>
        </div>
    </div>
    <div class="main-right">
        <div class="main-title"></div>
        <div class="main-content">
            <div class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label label-input" >跟踪内容</label>
                            <div class="layui-input-inline ">
                                <textarea class="layui-textarea trackDesc" placeholder="请输入跟踪内容"
                                          style="height: 150px;resize:none;"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label label-input" >下一次跟踪时间</label>
                            <div class="layui-input-inline ">
                                <input type="text" class="layui-input nextTrackTime" readonly id="nextTrackTime"
                                       placeholder="请选择日期" >
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label label-input" >上传附件</label>
                            <div class="layui-input-inline files-content" >
                                <div class='files' id="uploadFiles">
                                    <div class="file-add uploadFile">
                                        <div class="icon-add v-p-upload"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 140px;margin-top: 30px">
                        <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                        <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">确定</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['upload', 'layer', 'laydate', 'util','xmSelect'], function () {
        var upload = layui.upload,
            laydate = layui.laydate,
            layer = layui.layer,
            xmSelect = layui.xmSelect,
            util = layui.util;

        var fullH = $(parent.document).height() * 0.9 - 70
        $('.main').height(fullH)
        $('.main-content').height(fullH - 46)


        var btnCode = $('#btnCode').val(),
            dataUrge = $('#dataUrge').val(),
            finaInfoId = $('#finaInfoId').val()

        var titleArray = [
            {
                id: 1,
                listTitle: '跟踪历史',
                addTitle:'添加跟踪'
            }
        ]

        var initDate = getDate('today')


        var finaApplicantTrackListJson = $('#finaApplicantTrackListJson').val()
        finaApplicantTrackListJson = JSON.parse(finaApplicantTrackListJson)
        setInfo(finaApplicantTrackListJson)

        if (btnCode == 'get-applicant-track') {
            nextTrackTime = laydate.render({
                elem: '#nextTrackTime',
                value: initDate[0],
                min: initDate[0],
                type: 'datetime',
                format: 'yyyy-MM-dd HH:mm:ss',
                trigger: 'click'
            });
            $('.main-left .main-title').html(titleArray[0].listTitle)
            $('.main-right .main-title').html(titleArray[0].addTitle)
        }

        function setInfo(finaApplicantTrackListJson) {
            var _html =''
            var list = []

            if (btnCode == 'get-applicant-track'){
                list = finaApplicantTrackListJson
                if (!list.length){
                    _html = '<div class="lf-none">暂无数据</div>'
                    $('.records').append(_html)
                    return;
                }
                list.map(function (cur,index) {
                    var _date = cur.trackFtime ? util.toDateString(cur.createTime,'yyyy-MM-dd HH:mm') : ''
                    var _htmlFile = ''
                    cur.finaFiles.map(function (item) {
                        _htmlFile += ' <div class="file">\n' +
                            '                        <img src="' + item.filePath + '" class="image"></img>\n' +
                            '                    </div>'
                    })
                    _html += '<div class="record-cell">\n' +
                        '                    <div class="cell-left">'+_date+'</div>\n' +
                        '                    <div class="cell-right">\n' +
                        '                        <div>\n' +
                        '                            <div class="icon-ricle"></div>\n' +
                        '                            <div class="userName">（'+cur.trackUserName+'）</div>\n' +
                        '                        </div>\n' +
                        '                        <div class="cell-right-p">\n' +cur.trackDesc+
                        '                        </div>\n' +
                        '                        <div class="cell-right-p">\n' +
                        '                            <div>凭证：</div>\n' +
                        '                            <div class="files" id="uploadFiles'+index+'" >\n' +
                        _htmlFile+
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                </div>'

                })
            }

            $('.records').append(_html)
            for(var i =0;i<list.length;i++){
                if (list[i].finaFiles.length){
                    viewer = new Viewer(document.getElementById('uploadFiles'+i));
                }
                if (list[i].oneFiles && list[i].oneFiles.length){
                    viewer = new Viewer(document.getElementById('uploadFilesOne'+i));
                }
            }

        }


        $('.submit-btn').click(function () {
            var _this = $(this)

            var params = {}

            if (btnCode == 'get-applicant-track') {
                var files= []
                $('#uploadFiles .file').map(function (i,cur) {
                    files.push({
                        filePath: $(cur).find('img').attr('src')
                    })
                })
                params = {
                    btnCode: 'applicant-track-save',
                    finaInfoId: $('#finaInfoId').val(),
                    trackDesc: $('.trackDesc').val(),
                    files: JSON.stringify(files),
                    nextTrackTime: $('.nextTrackTime').val()
                }
                if (!params.trackDesc){
                    layer.alert('请输入跟踪内容！', {
                        icon: 5
                    })
                    return;
                }


                if (!params.nextTrackTime){
                    layer.alert('请选择下一次跟踪时间！', {
                        icon: 5
                    })
                    return;
                }

                if (!files.length) {
                    layer.alert('请输入上传附件！', {
                        icon: 5
                    })
                    return;
                }
            }

            _this.addClass('poi-no')
            $.ajax({
                url: '${ctx}/fina/applicant/operate',
                type: 'post',
                data: params,
                success: function (res) {
                    var res = JSON.parse(res)
                    if (res.isSuccess){
                        layer.msg('成功', {
                            time: 1000,
                            icon: 1
                        },function(){
                            parent.location.reload()
                        })
                    }else{
                        _this.removeClass('poi-no')
                        layer.msg(res.msg, {
                            time: 1000,
                            icon: 2
                        })
                    }
                }
            })
        })
        $('.close-btn').click(function () {
            closeDialog()
        })

        var uploadFile = upload.render({
            elem: '.uploadFile',
            url: '${ctx}/fina/files/uploadSftp', //改成您自己的上传接口
            data: {
                modelType: 'applicantTrack',
                pathId: $('#finaInfoId').val()
            },
            multiple: true,
            accept: 'images', //只能上传图片
            acceptMime: 'image/*',
            before: function (obj) {
                layer.load();
                fileLen = $('#uploadFiles .file').length
            },
            done: function (res) {
                layer.closeAll('loading')
                if (res.success == 'true') {
                    putSessionFiles(res.surveyFile,$(this.item).parent().attr('id'));
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

                viewer = new Viewer(document.getElementById('uploadFiles'));
            },
            error: function (index, upload) {
                layer.closeAll('loading')
            }
        });

        $('#uploadFiles').on('click','.file-del',function () {
            $(this).parent().detach()
            viewer.destroy()
            if ($('#uploadFiles .file').length){
                viewer = new Viewer(document.getElementById('uploadFiles'));
            }
        })



        function putSessionFiles(surveyFile,id) {
            var _html = ' <div class="file">\n' +
                '                        <img src="' + surveyFile.filePath + '" class="image"></img>\n' +
                // '<div class="file-title">' + surveyFile.fileName + '</div>\n' +
                '                        <div class="file-del"></div>'
            '                    </div>'
            $('#'+id).append(_html)
        }

        function getDate(id) {
            var today = new Date(),
                y = today.getFullYear(),
                m = today.getMonth() + 1,
                d = today.getDate(),
                w = today.getDay(),
                millisecond = 1000 * 60 * 60 * 24;
            if (id == 'upMonth') {
                var y1 = y,
                    m1 = m
                if (m == 1) {
                    y1 = y - 1
                    m1 = 12
                } else {
                    m1 = m - 1
                }
                m1 = PrefixInteger(m1, 2)
                var days1 = new Date(y1, m1, 0).getDate()
                vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
            } else if (id == 'yesterday') {
                var yesterDay = new Date(today.getTime() - millisecond);
                vals = [dateFormat(yesterDay), dateFormat(yesterDay)]
            } else if (id == 'today') {
                var m1 = PrefixInteger(m, 2)
                var d1 = PrefixInteger(d, 2)
                vals = [y + '-' + m1 + '-' + d1 +' 00:00:00', y + '-' + m1 + '-' + d1]
            } else if (id == 'curWeek') {
                var minusDay = w != 0 ? w - 1 : 6;
                var monday = new Date(today.getTime() - (minusDay * millisecond));
                var sunday = new Date(monday.getTime() + (6 * millisecond));
                vals = [dateFormat(monday), y + '-' + m + '-' + d]
            } else if (id == 'curMonth') {
                vals = [y + '-' + m + '-01', y + '-' + m + '-' + d]
            } else if (id == 'all') {
                vals = ['2019-02-27', y + '-' + m + '-' + d]
            }
            return vals
        }

        function dateFormat(time, format) {
            var t = new Date(time);
            var format = format || 'yyyy-MM-dd'
            var tf = function (i) {
                return (i < 10 ? '0' : '') + i
            };
            return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
                switch (a) {
                    case 'yyyy':
                        return tf(t.getFullYear());
                        break;
                    case 'MM':
                        return tf(t.getMonth() + 1);
                        break;
                    case 'mm':
                        return tf(t.getMinutes());
                        break;
                    case 'dd':
                        return tf(t.getDate());
                        break;
                    case 'HH':
                        return tf(t.getHours());
                        break;
                    case 'ss':
                        return tf(t.getSeconds());
                        break;
                }
            })
        };

        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }
        function closeDialog(){
            var closeBtn = $("#diglog_close_btn");
            if(closeBtn.length == 0){
                closeBtn = $("#diglog_close_btn",window.parent.document);
            }
            closeBtn.click();
        }

        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                }
                if (selected) {
                    Object.assign(param, {
                        selected: true
                    })
                }
                if (cur.checked){
                    Object.assign(param, {
                        selected: true
                    })
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }
        //正则匹配
        var checkPapers = function (parama, paramb) {
            var map = new Map([
                ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
                ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
                ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
                ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
                ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
                ['integer', /^[0-9]\d*$/],
                ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
                ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
            ])

            if (map.get(parama).test(paramb)) {
                return true
            } else {
                return false
            }
        }


    })
</script>
</body>
</html>
