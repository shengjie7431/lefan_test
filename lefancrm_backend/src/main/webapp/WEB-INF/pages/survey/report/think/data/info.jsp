<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据录入</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">
    <style>
        *{
            color: #101010;
        }
        .main{
            width: 99%;
            margin: 0 auto;
        }
        .header{
            position: fixed;
            top: 0;
            width: 100%;
            padding: 5px 0;
            z-index: 99;
            background-color: #fff;
        }
        .header-bars{
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            border: 1px solid #eee;
        }
        .header-bars .header-bar{
            flex: 1;
            text-align: center;
            /* height: 40px; */
            padding: 14px 0;
            border-right: 1px solid #eee;
            cursor: pointer;
        }
        .header-bars .header-bar:last-of-type{
            border-right: none;
        }
        .header-bars .header-bar.active{
            color: #fff;
            border-right: none;
            background: #3ba9ff;
        }
        .content{
            margin-top: 60px;
        }
        .i-table {
            display: none;
            width: 100%;
            margin: 10px auto;
        }

        .i-table .i-thead {
            padding: 12px 0;
            display: flex;
            width: 100%;
            background: #f2f2f2;
            border-bottom: 1px solid #eee;
        }

        .i-table .i-tbody {
            width: 100%;
            padding: 10px 0;
        }

        .i-table .i-thead .i-td {
            width: 10%;
            height: 20px;
            line-height: 20px;
            display: flex;
            align-items: center;
        }

        /* .costTypes .i-table .i-thead .i-td .i-td-text {
            height: 20px;
           line-height: 20px;
        } */
        .i-table .i-tr {
            padding: 5px 0;
            display: flex;
            width: 100%;
        }

        .i-table .i-tr .i-td {
            width: 6%;
            text-align: left;
        }

        .i-table .i-thead .i-td.wid15,
        .i-table .i-tr .i-td.wid15 {
            width: 15%;
            padding-left: 1%;
        }

        .i-table .i-thead .i-td.wid13,
        .i-table .i-tr .i-td.wid13 {
            width: 13%;
            padding-left: 1%;
        }

        .i-table .i-thead .i-td.wid12,
        .i-table .i-tr .i-td.wid12 {
            width: 12%;
            padding-left: 1%;
        }

        .i-table .i-tbody .i-tr{
            border-bottom: 1px solid #eee;
        }
        .i-table .i-tbody .i-tr .i-td {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        .delCostType,
        .delBear {
            color: #3BA9FF;
            cursor: pointer;
        }

        .i-table .files {
            display: flex;
            flex-wrap: wrap;
            margin-top: 0
        }

        .i-table .files .file {
            width: 36px;
            height: 36px;
            border: 1px solid #d0c9c9;
            margin-bottom: 10px;
            margin-right: 10px;
            position: relative;
        }
        .i-table .files .file img {
            width: 100%;
            height: 100%;
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

        .file-title {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 14px;
            line-height: 14px;
            font-size: 10px;
            background-color: rgba(187, 187, 187, 0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .file-del {
            position: absolute;
            top: -8px;
            right: -9px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .i-table .files div.file-add {
            width: 36px;
            height: 36px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 10px;
            margin-right: 10px;
        }

        .i-table .icon-add {
            margin-left: 17px;
            margin-top: 5px;
            display: inline-block;
            background: #aaa;
            height: 24px;
            position: relative;
            width: 1px;
        }

        .i-table .icon-add:after {
            background: #aaa;
            content: "";
            height: 24px;
            left: 0;
            position: absolute;
            top: 0;
            width: 1px;
            transform: rotateZ(90deg);
        }
        .color1{
            color: #7b7373;
            pointer-events: none;

        }
        .layui-input-inline {
            width: 92% !important;
            padding: 5px 0;
        }

        .layui-input-inline span{
            display: inline-block;
            height: 20px;
            line-height: 20px;
            padding: 6px 0;
        }
        .layui-input-inline input,
        .layui-input-inline textarea {
            margin: 0 auto;
            padding: 0 5%;
            width: 70%;
            height: 30px;
            line-height: 30px;
            border:1px solid #bbb;
        }
        .operateBar{
            cursor: pointer;
        }
        .poi-no{
            pointer-events: none;
        }
        .lf-none{
            width: 100%;
            height: 40px;
            text-align: center;
            color: #BBBBBB;
            font-size: 14px;
        }
        .activeColor{
            color: #3BA9FF!important;
            pointer-events: auto!important;

        }


    </style>
</head>
<body>
<div class="main">
    <input type="hidden" name="thinkDataId" value="${thinkDataId}" id="thinkDataId" />
    <input type="hidden" name="roleOne" value="${roleOne}" id="roleOne" />
    <input type="hidden" name="roleTwo" value="${roleTwo}" id="roleTwo" />
    <input type="hidden" name="roleThree" value="${roleThree}" id="roleThree" />
    <input type="hidden" name="isCurMonth" value="${isCurMonth}" id="isCurMonth" />

    <div class="header">
        <div class="header-bars">
            <div class="header-bar active" data-id="1">业务主营-阳性率奖励、阳性率扣减
            </div>
            <div class="header-bar" data-id="2">业务管理、业务销售-业务核算收入
            </div>
            <div class="header-bar" data-id="3">后援管理-部门核算收入
            </div>

        </div>
    </div>
    <div class="content">
        <div class="i-table i-table1" data-id="1" style="display: block">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text ">机构</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">产品类型</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">理赔阳性率奖励</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">理赔阳性率扣减</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">上传附件</span>
                </div>
                <div class="i-td wid13">
                    <span class="i-td-text ">状态</span>
                </div>
                <div class="i-td wid12">
                    <span class="i-td-text ">操作</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
        <div class="i-table i-table2" data-id="2">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text ">机构</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">产品类型</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">业务核算收入</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">上传附件</span>
                </div>
                <div class="i-td wid13">
                    <span class="i-td-text ">状态</span>
                </div>
                <div class="i-td wid12">
                    <span class="i-td-text ">操作</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
        <div class="i-table i-table3" data-id="3">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text ">机构</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">部门核算收入</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">上传附件</span>
                </div>
                <div class="i-td wid13">
                    <span class="i-td-text ">状态</span>
                </div>
                <div class="i-td wid12">
                    <span class="i-td-text ">操作</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
        <div class="i-table i-table4" data-id="4">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text ">公司</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">营业外回款收入</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">营业外支出</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text ">企业所得税</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>

<script>
    $(function(){
        layui.use(['form', 'laydate', 'jquery', 'upload', 'layer'], function () {
            var form = layui.form,
                laydate = layui.laydate,
                $ = layui.jquery,
                layer = layui.layer,
                upload = layui.upload;

            getData(1)
            function getData(id){
                $.ajax({
                    url: '${ctx}/think/data/ajaxData',
                    data: {
                        thinkDataId:$('#thinkDataId').val(),
                        dataType: id
                    },
                    success: function (res) {
                        var res = JSON.parse(res)
                        setTable(id,res.results)
                    }
                })
            }

            // roleOne 机构经理
            // roleTwo 分管总
            // roleThree 财务
            var isCurMonth = $('#isCurMonth').val() === 'true' ? true : false
            var roleOne = $('#roleOne').val() === 'true' ? true : false
            var roleTwo = $('#roleTwo').val() === 'true' ? true : false
            var roleThree = $('#roleThree').val() === 'true' ? true : false
            //1 机构经理 2分管总 3财务

            if (roleThree){
                var _htmlH = ' <div class="header-bar" data-id="4">乐凡集团-营业外回款收入、营业外支出、企业所得税\n' +
                    '        </div>'
                $('.header-bars').append(_htmlH)
            }
            $('.header-bars').on('click','.header-bar',function(){
                var _this = $(this)
                if (!_this.hasClass('active')){
                    _this.addClass('active')
                    _this.siblings().removeClass('active')
                    var _id = _this.attr('data-id')
                    $('.i-table[data-id="'+_id+'"]').show().siblings().hide()
                    getData(_id)
                }

            })

            $('.i-table').on('blur','.input-edit',function () {
                var _this = $(this)
                var oldValue = _this.attr('data-oldValue') || ''
                if (_this.val() ==  oldValue){
                    return;
                }
                if (!_this.val() || _this.val() && !checkPapers('money', _this.val())) {
                    layer.msg('请输入小数点两位内的数字', {
                        time: 2000,
                        icon: 7
                    })
                    return
                }
                $.ajax({
                    url: '${ctx}/think/data/operate',
                    data: {
                        thinkDataId:$('#thinkDataId').val(),
                        btnCode: 'save-money',
                        oprId:_this.attr('data-id'),
                        colStr:_this.attr('data-name'),
                        value:_this.val()
                    },
                    success: function (res) {
                        var res = JSON.parse(res);
                        if (res.isSuccess) {
                            _this.attr('data-oldValue',_this.val())
                            layer.msg('成功', {
                                time: 1000,
                                icon: 1
                            }, function () {

                            })
                        } else {
                            layer.close(loadIndex)
                            layer.msg('失败', {
                                time: 1000,
                                icon: 2
                            })
                        }

                    }
                })
            })

            $('.i-table').on('click','.operateBar',function () {
                var _this = $(this)
                _this.addClass('poi-no')
                setTimeout(function () {
                    _this.removeClass('poi-no')
                },2000)
                var state = _this.attr('data-state')
                var btnCode = ''
                if (!isCurMonth){
                    return;
                }
                if (state == 1 && roleOne){
                    btnCode = 'commit'
                }
                if (state == 2 && roleTwo){
                    btnCode = 'commit-pass'
                }
                if (state == 1 && roleOne || state == 2 && roleTwo){
                    $.ajax({
                        url: '${ctx}/think/data/operate',
                        data: {
                            thinkDataId:$('#thinkDataId').val(),
                            btnCode: btnCode,
                            oprId:_this.attr('data-id'),
                        },
                        success: function (res) {
                            var res = JSON.parse(res);
                            _this.removeClass('poi-no')
                            if (res.isSuccess) {
                                layer.msg('成功', {
                                    time: 1000,
                                    icon: 1
                                }, function () {
                                    var _id = $('.header-bars .header-bar.active').attr('data-id')
                                    getData(_id)
                                })
                            } else {
                                layer.close(loadIndex)
                                layer.msg('失败', {
                                    time: 1000,
                                    icon: 2
                                })
                            }

                        }
                    })
                }

            })

            function queryType(param) {
                return Object.prototype.toString.call(param).slice(8,-1)
            }
            fileInfo = {}

            function setTable(id,results){
                if (id == 1){
                    var _html=''
                    var len = 1
                    results.map(function(item){
                        var _html1 = '',_html2='',_html3='',fileHtml='',delHtml=''
                        var classOpr = ''

                        if (item.orgProducts && item.orgProducts.length){
                            len = item.orgProducts.length
                            item.orgProducts.map(function(param){
                                var productName = param.productName !== null ? param.productName : ''
                                var input1 = param.claimSunAddmony !== null  ? param.claimSunAddmony : ''
                                var input2 = param.claimSunSubmoney !== null ? param.claimSunSubmoney : ''

                                _html1 += '<div class="layui-input-inline"><span>'+productName+'</span></div>\n'

                                if ((item.state == 1 && roleOne || item.state == 2 && roleTwo )&& isCurMonth  ){
                                    _html2 += '<div class="layui-input-inline">\n' +
                                        ' <input type="number" class="input1-1 input-edit" data-name="claimSunAddmony" value="'+input1+'" data-oldValue="'+input1+'" data-id="'+param.id+'">\n' +
                                        ' </div>\n'
                                    _html3 += '<div class="layui-input-inline">\n' +
                                        ' <input type="number" class="input1-2 input-edit" data-name="claimSunSubmoney" value="'+input2+'" data-oldValue="'+input2+'"  data-id="'+param.id+'">\n' +
                                        ' </div>\n'
                                }else {
                                    _html2 += '<div class="layui-input-inline">\n' +
                                        ' <span>'+input1+'</span>\n' +
                                        ' </div>\n'
                                    _html3 += '<div class="layui-input-inline">\n' +
                                        ' <span>'+input2+'</span>\n' +
                                        ' </div>\n'
                                }
                            })
                        }
                        var uploadFilesClass = 'files'+id+'-' + item.id
                        if ((item.state == 1 && roleOne || item.state == 2 && roleTwo )&& isCurMonth){
                            fileHtml += '<div class="file-add uploadFileAdd">\n' +
                                '<div class="icon-add v-p-upload"></div>\n' +
                                '</div>\n'
                            delHtml =   '<div class="file-del"></div>'
                            classOpr = 'activeColor'

                        }
                        fileInfo[uploadFilesClass] = item.files.length || 0
                        if (item.files && item.files.length){
                            item.files.map(function(cur){
                                var curC = Object.assign({state:item.state},cur)
                                fileHtml += setFileHtml(curC);
                            })
                        }
                        var stateName = '',operateName=''
                        if (item.state == 1){
                            if(roleOne) {
                                stateName = '待提交审核'
                                operateName = '提交审核'
                            }
                        }else if (item.state == 2){
                            if(roleOne){
                                stateName = '待分管总审核'
                                operateName= ''
                            }
                            if (roleTwo){
                                stateName = '待分管总审核'
                                operateName= '提交审核'
                            }
                        }else if (item.state == 3){
                            stateName = '审核通过'
                            operateName=''
                        }

                        _html += '  <div class="i-tr" data-id="1">\n' +
                            '                    <div class="i-td wid15">\n' +
                            '                            <div class="layui-input-inline"><span>'+item.orgName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html1+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html2+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html3+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            '                            <div class=\'files\' id="' + uploadFilesClass + '" data-id="'+item.id+'">\n' +
                            fileHtml+
                            '                            </div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid13">\n' +
                            '                            <div class="layui-input-inline"><span>'+stateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid12">\n' +
                            '                            <div class="layui-input-inline"><span class="color1 operateBar '+classOpr+'" data-state="'+item.state+'" data-id="'+item.id+'">'+operateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '               </div>'


                    })
                    if (!results.length){
                        _html = '<div class="lf-none">暂无数据</div>'
                    }
                    $('.i-table[data-id="'+id+'"] .i-tbody').html(_html)
                    for (var key in fileInfo){
                        if (fileInfo[key]){
                            window['viewer'+key] = new Viewer(document.getElementById(key));
                        }
                    }
                    bindUplod('uploadFileAdd')
                }else if (id == 2){
                    var _html=''
                    var len = 1
                    results.map(function(item){
                        var _html1 = '',_html2='',fileHtml='',delHtml=''
                        var classOpr = ''

                        if (item.orgProducts && item.orgProducts.length){
                            len = item.orgProducts.length
                            item.orgProducts.map(function(param){
                                var productName = param.productName !== null ? param.productName : ''
                                var input1 = param.busAccMony !== null  ? param.busAccMony : ''

                                _html1 += '<div class="layui-input-inline"><span>'+productName+'</span></div>\n'
                                if ((item.state == 1 && roleOne || item.state == 2 && roleTwo) && isCurMonth){
                                    _html2 += '<div class="layui-input-inline">\n' +
                                        ' <input type="text" class="input1-1 input-edit" data-name="busAccMony" value="'+input1+'" data-oldValue="'+input1+'" data-id="'+param.id+'">\n' +
                                        ' </div>\n'
                                }else {
                                    _html2 += '<div class="layui-input-inline">\n' +
                                        ' <span>'+input1+'</span>\n' +
                                        ' </div>\n'
                                }
                            })
                        }
                        var uploadFilesClass = 'files'+id+'-' + item.id
                        if ((item.state == 1 && roleOne || item.state == 2 && roleTwo) && isCurMonth){
                            fileHtml += '<div class="file-add uploadFileAdd2">\n' +
                                '<div class="icon-add v-p-upload"></div>\n' +
                                '</div>\n'
                            delHtml =   '<div class="file-del"></div>'
                            classOpr = 'activeColor'
                        }
                        fileInfo[uploadFilesClass] = item.files.length || 0
                        if (item.files && item.files.length){
                            item.files.map(function(cur){
                                var curC = Object.assign({state:item.state},cur)
                                fileHtml += setFileHtml(curC);
                            })
                        }
                        var stateName = '',operateName='提交审核'
                        if (item.state == 1){
                            if(roleOne) {
                                stateName = '待提交审核'
                                operateName = '提交审核'
                            }
                        }else if (item.state == 2){
                            if(roleOne){
                                stateName = '待分管总审核'
                                operateName= ''
                            }
                            if (roleTwo){
                                stateName = '待分管总审核'
                                operateName= '提交审核'
                            }
                        }else if (item.state == 3){
                            stateName = '审核通过'
                            operateName=''
                        }
                        _html += '  <div class="i-tr" data-id="1">\n' +
                            '                    <div class="i-td wid15">\n' +
                            '                            <div class="layui-input-inline"><span>'+item.orgName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html1+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html2+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            '                            <div class=\'files\' id="' + uploadFilesClass + '" data-id="'+item.id+'">\n' +

                            fileHtml+
                            '                            </div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid13">\n' +
                            '                            <div class="layui-input-inline"><span>'+stateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid12">\n' +
                            '                            <div class="layui-input-inline"><span class="color1 operateBar '+classOpr+'" data-state="'+item.state+'" data-id="'+item.id+'">'+operateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '               </div>'


                    })
                    if (!results.length){
                        _html = '<div class="lf-none">暂无数据</div>'
                    }
                    $('.i-table[data-id="'+id+'"] .i-tbody').html(_html)
                    for (var key in fileInfo){
                        if (fileInfo[key]){
                            window['viewer'+key] = new Viewer(document.getElementById(key));
                        }
                    }
                    bindUplod('uploadFileAdd2')
                }else if (id == 3){
                    var _html=''
                    results.map(function(item){
                        var _html1 = '',fileHtml='',delHtml=''
                        var classOpr = ''

                        var input1 = item.depAccMony !== null  ? item.depAccMony : ''
                        if ((item.state == 1 && roleOne || item.state == 2 && roleTwo) && isCurMonth){
                            _html1 = '<div class="layui-input-inline">\n' +
                                ' <input type="text" class="input1-1 input-edit" data-name="depAccMony" value="'+input1+'" data-oldValue="'+input1+'" data-id="'+item.id+'">\n' +
                                ' </div>\n'
                        }else {
                            _html1 = '<div class="layui-input-inline">\n' +
                                ' <span>'+input1 +'</span>\n' +
                                ' </div>\n'
                        }
                        var uploadFilesClass = 'files'+id+'-' + item.id
                        if ((item.state == 1 && roleOne || item.state == 2 && roleTwo) && isCurMonth){
                            fileHtml += '<div class="file-add uploadFileAdd3">\n' +
                                '<div class="icon-add v-p-upload"></div>\n' +
                                '</div>\n'
                            delHtml =   '<div class="file-del"></div>'
                            classOpr = 'activeColor'
                        }

                        fileInfo[uploadFilesClass] = item.files.length || 0
                        if (item.files && item.files.length){
                            item.files.map(function(cur){
                                var curC = Object.assign({state:item.state},cur)
                                fileHtml += setFileHtml(curC);
                            })
                        }
                        var stateName = '',operateName='提交审核'
                        if (item.state == 1){
                            stateName = '待提交审核'
                            operateName= '提交审核'
                        }else if (item.state == 2){
                            if(roleOne){
                                stateName = '待分管总审核'
                                operateName= ''
                            }
                            if (roleTwo){
                                stateName = '待分管总审核'
                                operateName= '提交审核'
                            }
                        }else if (item.state == 3){
                            stateName = '审核通过'
                            operateName=''
                        }

                        _html += '  <div class="i-tr" data-id="1">\n' +
                            '                    <div class="i-td wid15">\n' +
                            '                            <div class="layui-input-inline"><span>'+item.orgName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            _html1+
                            '                       </div>\n' +
                            '                       <div class="i-td wid15">\n' +
                            '                            <div class=\'files\' id="' + uploadFilesClass + '" data-id="'+item.id+'">\n' +

                            fileHtml+
                            '                            </div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid13">\n' +
                            '                            <div class="layui-input-inline"><span>'+stateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '                       <div class="i-td wid12">\n' +
                            '                            <div class="layui-input-inline"><span class="color1 operateBar '+classOpr+'" data-state="'+item.state+'" data-id="'+item.id+'">'+operateName+'</span></div>\n' +
                            '                       </div>\n' +
                            '               </div>'


                    })
                    if (!results.length){
                        _html = '<div class="lf-none">暂无数据</div>'
                    }
                    $('.i-table[data-id="'+id+'"] .i-tbody').html(_html)
                    for (var key in fileInfo){
                        if (fileInfo[key]){
                            window['viewer'+key] = new Viewer(document.getElementById(key));
                        }
                    }
                    bindUplod('uploadFileAdd3')
                }else if (id == 4){
                    var _html='',_html1 = '',_html2='',_html3=''
                    if (roleThree){
                        results.map(function(item){
                            var input1 = item.lefanInMony !== null  ? item.lefanInMony : ''
                            var input2 = item.lefanOutMony !== null  ? item.lefanOutMony : ''
                            var input3 = item.lefanTaxMony !== null  ? item.lefanTaxMony : ''

                            if (isCurMonth){
                                _html1 = '<div class="layui-input-inline">\n' +
                                    ' <input type="text" class="input4-1 input-edit" data-name="lefanInMony" value="'+input1+'" data-oldValue="'+input1+'" data-id="'+item.id+'">\n' +
                                    ' </div>\n'
                                _html2 = '<div class="layui-input-inline">\n' +
                                    ' <input type="text" class="input4-2 input-edit" data-name="lefanOutMony" value="'+input2+'" data-oldValue="'+input2+'" data-id="'+item.id+'">\n' +
                                    ' </div>\n'
                                _html3 = '<div class="layui-input-inline">\n' +
                                    ' <input type="text" class="input4-3 input-edit" data-name="lefanTaxMony" value="'+input3+'" data-oldValue="'+input3+'" data-id="'+item.id+'">\n' +
                                    ' </div>\n'
                            }else {
                                _html1 = '<div class="layui-input-inline">\n' +
                                    ' <span>'+input1+'</span>\n' +
                                    ' </div>\n'
                                _html2 = '<div class="layui-input-inline">\n' +
                                    ' <span>'+input2 +'</span>\n' +
                                    ' </div>\n'
                                _html3 = '<div class="layui-input-inline">\n' +
                                    ' <span>'+input3 +'</span>\n' +
                                    ' </div>\n'
                            }
                            _html += '  <div class="i-tr" data-id="1">\n' +
                                '                    <div class="i-td wid15">\n' +
                                '                            <div class="layui-input-inline"><span>'+item.orgName+'</span></div>\n' +
                                '                       </div>\n' +
                                '                       <div class="i-td wid15">\n' +
                                _html1+
                                '                       </div>\n' +
                                '                       <div class="i-td wid15">\n' +
                                _html2+
                                '                       </div>\n' +
                                '                       <div class="i-td wid15">\n' +
                                _html3+
                                '                       </div>\n' +
                                '               </div>'


                        })
                        if (!results.length){
                            _html = '<div class="lf-none">暂无数据</div>'
                        }
                    }else {
                        _html = '<div class="lf-none">暂无权限</div>'
                    }

                    $('.i-table[data-id="'+id+'"] .i-tbody').html(_html)
                }


            }

            var newFiles = []
            function bindUplod(name){
                upload.render({
                    elem: '.'+name,
                    url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                    data: {
                        modelType: 'thinkData',
                        fileType: 'details'
                    },
                    multiple: true,
                    accept: 'file', //只能上传图片
                    // acceptMime: 'image/*',
                    before: function (obj) {
                        var _id = $(this.item).parent().attr('id')
                        layer.load();
                        fileLen = $('#'+_id+' .file').length
                    },
                    done: function (res) {
                        newFiles.push({
                            filePath: res.surveyFile.filePath
                        })
                        layer.closeAll('loading')
                        if (res.success == 'true') {
                            var curC = res.surveyFile
                            curC = Object.assign({state: 0},curC)
                            setFileHtml(curC, $(this.item).parent().attr('id'),true);
                        } else {
                            layer.alert('导入出错', {
                                icon: 2
                            })
                        }
                    },
                    allDone: function (obj) {
                        var _id = $(this.item).parent().attr('id')
                        if (fileLen) {
                            window['viewer'+_id].destroy()
                        }
                        window['viewer'+_id] = new Viewer(document.getElementById(_id));

                        $.ajax({
                            url: '${ctx}/think/data/operate',
                            data: {
                                thinkDataId:$('#thinkDataId').val(),
                                btnCode: 'upload-file',
                                oprId: $(this.item).parent().attr('data-id'),
                                files:JSON.stringify(newFiles)
                            },
                            success: function (res) {
                                newFiles =[]
                                var res = JSON.parse(res);
                                if (res.isSuccess) {
                                    res.results.files.map(function (item) {
                                        $('.files img').map(function (img) {
                                            if ($(this).attr('src') == item.filePath){
                                                $(this).siblings().attr('data-id',item.id)
                                            }
                                        })
                                    })
                                    layer.msg('附件保存成功', {
                                        time: 1000,
                                        icon: 1
                                    }, function () {

                                    })
                                } else {
                                    layer.msg('失败', {
                                        time: 1000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    },
                    error: function (index, upload) {
                        layer.closeAll('loading')
                    }
                });
            }
            function putSessionFiles(surveyFile, id) {
                if(!surveyFile){
                    return;
                }
                var _html = ' <div class="file" data-type="new">\n' +
                    '                        <img src="' + surveyFile.filePath + '" class="image"></img>\n' +
                    // '                        <div class="file-del"></div>'+
                    '                    </div>'
                $('#' + id).append(_html)
            }
            function setFileHtml(surveyFile,idName,isNewFlag) {
                if(!surveyFile){
                    return;
                }
                var _htmlDel = ''
                var isNew = ''
                var isNewFlag = isNewFlag || false
                if (isNewFlag){
                    isNew = 'data-type="new"'
                }
                var _fileId = surveyFile.id ||''

                if (surveyFile.state != 3){
                    _htmlDel = '<div class="file-del" data-id="'+_fileId+'"></div>'
                }

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
                }else{
                    _htmlName = ''
                }
                var _html = ' <div class="file" '+isNew+' >' +
                    _htmlFile+
                    _htmlName+ _htmlDel+
                    ' </div>'
                if (idName){
                    $('#' + idName).append(_html)
                }else{
                    return _html
                }
            }

            $('.content').on('click', '.file-del', function () {
                var _this = $(this)
                _this.addClass('poi-no')
                setTimeout(function () {
                    _this.removeClass('poi-no')
                },3000)
                $.ajax({
                    url: "${ctx}/think/data/operate",
                    type: 'post',
                    data: {
                        btnCode: 'delete-file',
                        thinkDataId: $('#thinkDataId').val(),
                        fileId: _this.attr('data-id') || ''
                    },
                    success: function (res) {
                        _this.removeClass('poi-no')
                        var res = JSON.parse(res);
                        if (res.isSuccess) {
                            layer.msg('成功', {
                                time: 1000,
                                icon: 1
                            }, function () {
                                var _id = _this.parents('.files').attr('id')
                                _this.parent().detach()
                                window['viewer'+_id].destroy()
                                if ($('#'+_id+' .file').length) {
                                    window['viewer'+_id] = new Viewer(document.getElementById(_id));
                                }
                            })
                        } else {
                            layer.msg('失败', {
                                time: 1000,
                                icon: 2
                            })
                        }
                    }
                })
            })

        })
        //正则匹配
        var checkPapers = function (parama, paramb) {
            var map = new Map([
                ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
                ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
                ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
                ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
                ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
                ['moneyor4', /^(\-|\+)?\d+(\.\d{1,4})?$/],
                ['email', /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
                ['num_0_1', /^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
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
