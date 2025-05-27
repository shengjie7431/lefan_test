<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .td-item{
            display: flex;
            align-items: center;
            padding: 4px 0;
        }
        .first{
            display: inline-block;
            width: 100%;
            white-space: nowrap;
            min-height: 17px;
        }
        .color1{
            color:#059119;
        }
        .color2{
            color:#D70D0D;
        }
        .viewReason{
            color: #1E9FFF;
            cursor: pointer;
            padding-left: 4px;
        }
        .second{
            color: #000;
            display: inline-block;
            width: 50%;
        }
        .second-cell{
            width: 100%;
            height: 20px;
            text-align: left;
        }
        table tr td{
            vertical-align: middle!important;
        }
        .viewChannelDesc{
            cursor: pointer;
        }
        .panel-info{
            margin-bottom: 60px;
        }
        .pagePosi{
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            padding:6px 0;
        }


        .layui-table-main .layui-table-cell{
            height: auto;
        }

        .surveyCell{
          display: flex;
            height: 28px;
        }

        .surveyCellName{
            display: inline-block;
            width: 130px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;

        }
        .surveyCellText{
            display: inline-block;
            max-width: 80px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .oprBtns{
            display: flex;
            flex-wrap: wrap;
        }
        .oprBtn{
            display: inline-block;
            padding: 0 6px;
            color: #3ba9ff;
        }
    </style>
</head>
<body>
<div class="table-content">
    <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
    </table>
</div>

    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>--%>
    <script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<%--    <script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>--%>
    <script src="${ctx}/js/layui/layui.js"></script>
<%--</div>--%>

<script>

    layui.use([ 'table', 'laydate', 'layer', 'util'], function () {
        var table = layui.table,
            layer = layui.layer,
            util = layui.util,
            $ = layui.$;

        //重载表格数据
        function reloadTable(_cols) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
            )
            setTable(_cols)
        }
        var _cols = [
            [{
                field: 'surveyCaseNo',
                minWidth: 150,
                title: '案件编号',
                style: 'color:#3BA9FF;',
                event: 'case'
            }, {
                field: 'entrustOrgName',
                minWidth: 120,
                title: '保险公司',
            },{
                field: 'surveyPerson',
                minWidth: 120,
                title: '被调查人',
            },{
                field: 'orgSurveyStateName',
                minWidth: 120,
                title: '机构案件状态',
            },{
                field: 'createTime',
                minWidth: 140,
                title: '分派机构时间',
                templet: function (d) {
                    var _html = d.createTime ? util.toDateString(d.createTime, 'yyyy年MM月dd日') : ''
                    return _html
                }
            },{
                field: 'cases',
                minWidth: 130,
                title: '调查员',
                templet: function (d) {
                    var _html = ''
                    var cases = d.cases
                    var len = cases.length
                    for (var i =0; i<len;i++){
                        var _color = ''
                        if (cases[i].surveyState == 1){
                            _color = 'color2'
                        }else {
                            _color = 'color1'

                        }
                        _html += '<div class="surveyCell">' +
                            '<div class="surveyCellName '+_color+'" title="'+cases[i].surveyUserName+'">'+cases[i].surveyUserName+'</div>'+
                            '</div>'
                    }
                    return _html
                }
            },{
                field: 'cases',
                minWidth: 130,
                title: '任务状态',
                templet: function (d) {
                    var _html = ''
                    var cases = d.cases
                    var len = cases.length
                    for (var i =0; i<len;i++){
                        var _color = ''
                        if (cases[i].surveyState == 1){
                            _color = 'color2'
                        }else {
                            _color = 'color1'

                        }
                        _html += '<div class="surveyCell">' +
                            '<div class="surveyCellName '+_color+'" title="'+cases[i].surveyStateName+'">'+cases[i].surveyStateName+'</div>'+
                            '</div>'
                    }
                    return _html
                }
            },{
                field: 'cases',
                minWidth: 130,
                title: '调查员反馈',
                templet: function (d) {
                    var _html = ''
                    var cases = d.cases
                    var len = cases.length
                    for (var i =0; i<len;i++){
                        var _color = ''
                        if (cases[i].surveyState == 1){
                            _color = 'color2'
                        }else {
                            _color = 'color1'

                        }
                        var replyContent = cases[i].replyContent || ''
                        _html += '<div class="surveyCell">' +
                            '<div class="surveyCellText '+_color+'" title="'+replyContent+'">'+replyContent+'</div>'

                         if (cases[i].replyCommonFiles && cases[i].replyCommonFiles.length)  {
                             if (cases[i].replyType == 3){
                                 _html += '<div class="oprBtn" lay-event="opr3-view" data-id="'+cases[i].replyId+'">(查看)</div>'
                             }else if (cases[i].replyType == 4){
                                 _html += '<div class="oprBtn" lay-event="opr4-view" data-id="'+cases[i].replyId+'">(查看)</div>'
                             }
                         }
                         _html +=  '</div>'

                    }
                    return _html
                }
            },{
                field: 'orgEndTime',
                minWidth: 140,
                title: '机构截止时间',
                templet: function (d) {
                    var _html = d.orgEndTime ? util.toDateString(d.orgEndTime, 'yyyy年MM月dd日') : ''
                    return _html
                }
            },{
                field: 'residueDays',
                minWidth: 120,
                title: '机构时效',
                templet: function (d) {
                    return '剩余'+d.residueDays+'天'
                }
            },{
                field: '',
                minWidth:200,
                title: '快速回复',
                templet: function () {
                    var _html = '<div class="oprBtns"><div class="oprBtn" lay-event="opr1">今天可回销</div>'+
                        '<div class="oprBtn" lay-event="opr2">明天可回销</div>'+
                        '<div class="oprBtn" lay-event="opr3">申请延期</div>'+
                        '<div class="oprBtn" lay-event="opr4">其他回复</div></div>'

                    return _html
                }
            },
            ]
        ]

        setTable(_cols)

        function setTable(_cols) {
            var myTable = table.render({
                id: "test",
                elem: '#test',
                even: true,
                cols: _cols,
                page: false,
                limit: 2000,
                height: 'full-50',
                drag: false,
                url: '${ctx}/survey/case/orgCaseRemindList',
                where: {},
                parseData: function (res) {
                    if (res.results.length){
                        return {
                            "code": res.isSuccess ? 0 : 1,
                            "msg": res.msg,
                            "count": res.count,
                            "data": res.results,
                        }
                    }else{
                        reloadParent()
                    }

                },
                done: function (res) {
                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'case') {
                    info(obj.data.surveyInfoId,obj.data.id)
                }else if (obj.event == 'opr1') {
                    operate(obj.data.id,1,'')
                }else if (obj.event == 'opr2') {
                    operate(obj.data.id,2,'')
                }else if (obj.event == 'opr3') {
                    operate(obj.data.id,3,'')
                }else if (obj.event == 'opr4') {
                    operate(obj.data.id,4,'')
                }else if (obj.event == 'opr3-view'){
                    operate(obj.data.id,3,$(this).attr('data-id'))
                }else if (obj.event == 'opr4-view'){
                    operate(obj.data.id,4,$(this).attr('data-id'))
                }
            })
        }
    })

    var _flag = true
    function operate(orgCaseId,type,replyId) {
        var width = $(document.body).outerWidth() * 0.8;
        var height = $(document).outerHeight() - 20;
        if (type == 1 || type == 2){
            layer.confirm('确认提交？', {
                btn: ['是', '否'] //按钮
            }, function () {
                if (!_flag){
                    return;
                }
                _flag = false;
                $.ajax({
                    url: "${ctx}/survey/case/operate",
                    type: "post",
                    data: {
                        orgCaseId:orgCaseId,
                        type: type ,
                        btnCode: 'everyCaseRemind'
                    },
                    success: function (res) {
                        res = JSON.parse(res);
                        if (res.code == '0000'){
                            layer.msg(res.msg, {
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            },function () {
                                location.href='${ctx}/survey/case/back?btnCode=fastReply'
                            });
                        }
                    }
                });
            });
        }
        if (type == 3){
            openDialog({
                frame:true,
                title:"申请延期",
                height:height,
                width:width,
                url:"${ctx}/survey/case/sic/operateView?id="+orgCaseId+"&type="+type+"&btnCode=extension-time&roleCode=orgManager&operateType=caseRemind&replyId="+replyId
            });
        }
        if (type == 4){
            openDialog({
                frame:true,
                title:"其他回复",
                height:height,
                width:width,
                url:"${ctx}/survey/case/sic/operateView?id="+orgCaseId+"&type="+type+"&btnCode=otherReply&roleCode=orgManager&operateType=caseRemind&replyId="+replyId
            });
        }
    }

    function info(surveyInfoId,surveyAssignCaseId) {
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title="详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=org-review-list&fromName=pointsDetails&assignOrgId="+surveyAssignCaseId,
            load:true
        });
    }
</script>
</body>
</html>
