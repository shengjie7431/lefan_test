<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2020/12/3
  Time: 16:26
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
        .layui-form-content{
            width: 600px;
            margin: 30px auto;
        }

        .lf-bars {
            width: 100%;
            display: flex;
        }

        .lf-bars .lf-bar {
            width: 160px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            cursor: pointer;
        }

        .lf-bars .lf-bar.active {
            background-color: #3BA9FF;
            color: #fff;
        }
        .submit-btn2, .submit-btn3{
            display: none;
        }
        .layui-btn{
            margin-right: 16px;
        }

        .files {
            margin-top: 10px;
            width: 360px;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            overflow: auto;
        }

        .files .file {
            width: 70px;
            height:70px;
            border: 1px solid #d0c9c9;
            margin-bottom: 16px;
            margin-right: 16px;
            position: relative;
            overflow: hidden;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }
        audio{
            display: none;
            width: 100% !important;
        }
        .files .file .audio{
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            width: 63%;
            height: 60%;
            display: block;
            margin: 0 auto;
            margin-top: 7%;
        }
        .files .file .audio-play{
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
        .files .file .audio-pause{
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

        .files div.file-add {
            width: 70px;
            height: 70px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 16px;
            margin-right: 16px;
        }

        .icon-add {
            margin-left: 34px;
            margin-top: 15px;
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

        .poi-no{
            pointer-events: none;
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
        .disabled{
            background-color: #f6f6f6;
        }
    </style>
</head>
<body>
<input type="hidden" value="${dto.id}" name="finaInfoId" id="finaInfoId" />
<input type="hidden" value="${btnCode}" name="btnCode" id="btnCode" />
<input type="hidden" value="${haveAccount}" name="haveAccount" id="haveAccount" />
<input type="hidden" value="${confirmType}" name="confirmType" id="confirmType" />

<input type="hidden" value="${dto.finaConfirmAccount.accountMoney}" name="accountMoneyY" id="accountMoneyY" />
<input type="hidden" value="${dto.finaConfirmAccount.accountTime}" name="accountTimeY" id="accountTimeY" />
<input type="hidden" value='${finaFiles}' name="finaFiles" id="finaFiles" />


<div class="layui-form layui-form-content">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">确认方式</label>
            <div class="layui-input-inline" style="width: 340px;">
                <div class="lf-bars">
                    <div class="lf-bar active" data-id="1" style="border-right: none;">电话确认</div>
                    <div class="lf-bar " data-id="2">现场确认</div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item checkByPhone">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">到账金额</label>
            <div class="layui-input-inline">
                <input type="number" class="layui-input repay-cur" id="accountMoney" value="${dto.finaApplicantMoney.actualMoney}" placeholder="" autocomplete="off" style="width: 360px" max="200">
            </div>
        </div>
    </div>
    <div class="layui-form-item checkByPhone">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">到账时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input paramTime" readonly id="accountTime" placeholder="请选择日期" style="width: 360px">
            </div>
        </div>
    </div>
    <div class="layui-form-item checkByPhone">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">上传凭证</label>
            <div class="layui-input-inline">
                <div class='files' id="jq21">
                    <c:if test="${haveAccount == false}">
                        <div class="file-add uploadFile" >
                            <div class="icon-add v-p-upload"></div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item checkByNow" style="display: none">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">垫付员</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input repay-no" value="${dto.surveyUserName}" autocomplete="off" style="width: 360px;background-color:#eee;" disabled>
            </div>
        </div>
    </div>
    <c:if test="${haveAccount == false}">
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left:0;margin-top: 30px;text-align: center">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" data-type="1" style="width: 100px;">提交</button>
                <button class="layui-btn layui-btn-normal submit-btn" data-type="2" style="width: 150px;display: none">分派至调查员</button>
            </div>
        </div>
    </c:if>

</div>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>

<script>

    var haveAccount = $('#haveAccount').val()
    var confirmType = $('#confirmType').val()

    var disabled = haveAccount == 'true' ? true : false
    if (disabled){
        $('#accountMoney').val($('#accountMoneyY').val()).attr('disabled',true).addClass('disabled')
        var newTime = $('#accountTimeY').val()
        $('#accountTime').val(dateFormat(newTime, 'yyyy-MM-dd')).attr('disabled',true).addClass('disabled')
        $('.lf-bar').addClass('poi-no')
        $('.lf-bar[data-id="'+confirmType+'"]').addClass('active').siblings().removeClass('active')

        if (confirmType == 1){
            $('.checkByPhone').show()
            $('.checkByNow').hide()

        }else if (confirmType == 2){
            $('.checkByPhone').hide()
            $('.checkByNow').show()
        }
        var finaFiles = $('#finaFiles').val()
        finaFiles = JSON.parse(finaFiles)
        var _htmlFiles = ''
        var len = 0
        finaFiles.map(function (cur) {
            var _htmlFile = '<img src="' + cur.filePath + '" class="image"></img>'
            var _htmlName =  '<div class="file-title">' + cur.fileName + '</div>\n'

            var file = cur.filePath
            var id = file.lastIndexOf('.')
            var type = file.slice(id + 1)
            var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
            var fileArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg','xls','xlsx','doc','docx','pdf','ppt','pptx','zip','rar','7z','CAB','ARJ','LZH','TAR','GZ','ACE','UUE','BZ2','JAR','ISO']
            if (imgArr.indexOf(type) == -1) {
                _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                var _htmlTarget = ''
                if (fileArr.indexOf(type) == -1) {
                    _htmlTarget ='target="_blank"'
                }
                _htmlName = '<a class="file-title" '+_htmlTarget+'href="'+cur.filePath+'" download="'+cur.filePath+'" title="'+cur.fileName+'">' + cur.fileName + '</a>\n'
            }else{
                len++
            }
            var _html = ' <div class="file">' +
                _htmlFile+
                _htmlName+
                ' </div>'
            _htmlFiles +=_html
        })
        $('.files').append(_htmlFiles)
        if (len > 0){
            viewer = new Viewer(document.getElementById('jq21'));
        }
    }

    layui.use(['jquery', 'layer', 'util','laydate', 'upload'], function () {
        var layer = layui.layer,
            $ = layui.$,
            laydate= layui.laydate,
            upload = layui.upload,
            util = layui.util;

        <%--$.ajax({--%>
            <%--url: '${ctx}/fina/settlement/ajaxData',--%>
            <%--type: 'post',--%>
            <%--data: {--%>
                <%--dataType: 'get-repay-app-data',--%>
                <%--finaInfoId: $('#finaInfoId').val()--%>
            <%--},--%>
            <%--success: function (res) {--%>
                <%--var res = JSON.parse(res)--%>
                <%--$('.repay-cur').val(res.results.notRepayMoney)--%>

            <%--}--%>
        <%--})--%>

        var initDate = getDate('today')
        var accountTime = laydate.render({
            elem: '#accountTime',
            value: initDate[0],
            max: initDate[0]
        });

        $('.repay-cur').blur(function () {
            var repayCurMoney = Number($(this).val())
            if (!checkPapers('money',repayCurMoney)){
                layer.alert('请输入正确格式的还款金额,已修改', {
                    icon: 5
                })
                var newMoney = Math.round(repayCurMoney * 100) / 100
                $(this).val(newMoney)
            }
        })

        $('.lf-bars').on('click', '.lf-bar', function () {
            var _this = $(this)
            var _id = _this.attr('data-id')
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }
            if (_id == 1){
                $('.submit-btn[data-type=1]').show()
                $('.submit-btn[data-type=2]').hide()
                $('.checkByPhone').show()
                $('.checkByNow').hide()

            }else if (_id == 2){
                $('.submit-btn[data-type=1]').hide()
                $('.submit-btn[data-type=2]').show()
                $('.checkByPhone').hide()
                $('.checkByNow').show()
            }
        })

        $('.submit-btn').click(function () {
            var _this = $(this)
            var files= []
            $('.files .file').map(function (i,cur) {
                if ($(cur).find('img').length > 0){
                    files.push({
                        filePath: $(cur).find('img').attr('src')
                    })
                }else  if ($(cur).find('a').length > 0){
                    files.push({
                        filePath: $(cur).find('a').attr('href')
                    })
                }
            })

            var confirmType = $('.lf-bar.active').attr('data-id'),
                param =  {
                    btnCode: 'confirm-account',
                    finaInfoId: $('#finaInfoId').val(),
                    confirmType: confirmType
                }
            if (confirmType == 1){
                Object.assign(param,{
                    accountMoney:$('#accountMoney').val(),
                    accountTime:$('#accountTime').val(),
                    files: JSON.stringify(files)
                })
                if (!param.accountMoney){
                    layer.alert('请输入到账金额！', {
                        icon: 5
                    })
                    return;
                }
                if (!param.accountTime){
                    layer.alert('请选择到账时间！', {
                        icon: 5
                    })
                    return;
                }
                if (!files.length){
                    layer.alert('请上传附件！', {
                        icon: 5
                    })
                    return;
                }
            }
            _this.addClass('poi-no')

            $.ajax({
                url: '${ctx}/fina/applicant/operate',
                type: 'post',
                data:param,
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
                'modelType': 'applicantAccount',
                'pathId': $("#finaInfoId").val()
            },
            multiple: true,
            accept: 'file', //只能上传图片
            // acceptMime: 'audio/*',
            before: function (obj) {
                layer.load();
                fileLen = $('.files .file').length
            },
            done: function (res) {
                layer.closeAll('loading')
                if (res.success == 'true') {
                    setFileHtml(res.surveyFile);
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
                if (fileLen || $('.files .file').length) {
                    viewer = new Viewer(document.getElementById('jq21'));
                }
            },
            error: function (index, upload) {
                layer.closeAll('loading')
            }
        });

        $('.files').on('click','.file-del',function () {
            $(this).parent().detach()
            viewer.destroy()
            if ($('#jq21 .file').length){
                viewer = new Viewer(document.getElementById('jq21'));
            }
        })

        // $('.files').on('click','.audio',function () {
        //     var _this = $(this)
        //     var id = _this.attr('data-id')
        //    if (_this.hasClass('audio-pause') || (!_this.hasClass('audio-pause') && !_this.hasClass('audio-play'))){
        //        _this.addClass('audio-play')
        //        _this.removeClass('audio-pause')
        //        document.getElementById(id).play()
        //    }else if (_this.hasClass('audio-play')){
        //        _this.addClass('audio-pause')
        //        _this.removeClass('audio-play')
        //        document.getElementById(id).pause()
        //    }
        // })

        function putSessionFiles(surveyFile) {
            var len = $('.files .file').length
            var _html = ' <div class="file">\n' +
                    '<audio controls id="audio'+len+'">\n' +
                    '  <source src="'+surveyFile.filePath+'" type="audio/ogg">\n' +
                    '  <source src="'+surveyFile.filePath+'" type="audio/mpeg">\n' +
                    '  <embed surveyFile.filePath src="'+surveyFile.filePath+'" />'+
                    '</audio>'+
                '<div target="_blank" data-href="' + surveyFile.filePath + '" class="image audio " data-id="audio'+len+'"></div>\n' +
                '<div class="file-title">' + surveyFile.fileName + '</div>\n' +
                '                        <div class="file-del"></div>'
            '                    </div>'
            $('.files').append(_html)
        }

        function setFileHtml(surveyFile) {
            var _htmlDel = ''
            var _fileId = surveyFile.id ||''
            _htmlDel = '<div class="file-del" data-id="'+_fileId+'"></div>'
            var _htmlFile = '<img src="' + surveyFile.filePath + '" class="image"></img>'
            var _htmlName =  '<div class="file-title">' + surveyFile.fileName + '</div>\n'

            var file = surveyFile.filePath
            var id = file.lastIndexOf('.')
            var type = file.slice(id + 1)
            var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
            var fileArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg','xls','xlsx','doc','docx','pdf','ppt','pptx','zip','rar','7z','CAB','ARJ','LZH','TAR','GZ','ACE','UUE','BZ2','JAR','ISO']
            if (imgArr.indexOf(type) == -1) {
                _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                var _htmlTarget = ''
                if (fileArr.indexOf(type) == -1) {
                    _htmlTarget ='target="_blank"'
                }
                _htmlName = '<a class="file-title" '+_htmlTarget+'href="'+surveyFile.filePath+'" download="'+surveyFile.filePath+'" title="'+surveyFile.fileName+'">' + surveyFile.fileName + '</a>\n'
            }
            var _html = ' <div class="file">' +
                _htmlFile+
                _htmlName+ _htmlDel+
                ' </div>'
            $('.files').append(_html)
        }


    })

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
            vals = [y + '-' + m + '-' + d, y + '-' + m + '-' + d]
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

</script>
</body>
</html>
