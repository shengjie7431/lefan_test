<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .listName, .caseNo, .caseNum, .sunCaseNum{
            color: #3BA9FF;
            cursor: pointer;
        }

        .layui-table-total tr td{
            font-weight:800!important;
            color: #333!important;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/check/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <div class="form-group">
                        清单名称：<input style="width: 200px" name="listName" type="text" value="${listName}"  class="form-control">
                    </div>
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp;
                    <button onclick="app()" type="button" class="btn btn-default">新增</button>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>清单名称</th>
                <th>案件数量</th>
                <th>阳性案件数量(占比)</th>
                <th>减损总额</th>
                <th>委托方确认金额</th>
                <th>调查方确认金额</th>
                <th>赔付金额</th>
                <th>减损奖励金</th>
                <th>总投产比</th>
                <th>结算投产比分界点</th>
                <th>平均案件时效</th>
                <th>操作人</th>
                <th>操作时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td class="listName" data-id="${item.id}">${item.listName}</td>
                    <td class="caseNum" data-id="${item.id}">${item.caseNum}</td>
                    <td class="sunCaseNum" data-id="${item.id}">${item.sunCaseNum}&nbsp;&nbsp;(${item.sunRate}%)</td>
                    <td>${item.derogationMoney}</td>
                    <td>${item.entrustSubmitMoney}</td>
                    <td>${item.surveySubmitMoney}</td>
                    <td>${item.pfAmtStr}</td>
                    <td>${item.jsAmtStr}</td>
                    <td>${item.rate}</td>
                    <td>${item.tcb}</td>
                    <td>${item.caeAvgEff}</td>
                    <td>${item.operateName}</td>
                    <td><fmt:formatDate value="${item.oparateTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <a href="javascript:void(0);" onclick="operate(${item.id},'del',true,this)">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/check/list?listName=${listName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

    <script type="text/html" id="table-content-child-h">
        <div class="table-content-child">
            <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
            </table>
        </div>
    </script>
    <script type="text/html" id="table-content-child-case-h">
        <div class="table-content-child-case">
            <table class="layui-table" id="test-child-case" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
            </table>
        </div>
    </script>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${ctx}/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
    <script src="${ctx}/js/layui/layui.js"></script>
</div>


<script>
    function app(id){
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"新增",
            height:height,
            width:800,
            url:"${ctx}/survey/check/info" + (id ? "?id=" + id : ""),
            load:true
        });
    }

    function operate(id,btnCode,ajax,_this){
        if(ajax){
            var url = "${ctx}/survey/check/operate",data = {"id":id,"btnCode":btnCode};
            layer.confirm("确定删除", {
                btn: ['是','否'] //按钮
            }, function(){
                $('.layui-layer-btn .layui-layer-btn0').css({
                    'pointer-events': 'none'
                })
                $.ajax({
                    url:url,
                    type:"post",
                    data :data,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })

                        } else{
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            }, function(){

            });
        }else {

        }
    }
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    layui.use(['table','jquery','layer','soulTable'],function () {
        var table = layui.table,
            $ = layui.jquery,
            soulTable = layui.soulTable

        layer = layui.layer
        $('.listName').on('click', function () {
            var param = {
                btnCode: 'org',
                id: $(this).attr('data-id')

            }
            getChild(param)
        })
        $('.caseNum').on('click',function () {
            var params = {
                btnCode: 'num',
                id: $(this).attr('data-id')
            }
            getCaseList(params)
        })
        $('.sunCaseNum').on('click',function () {
            var params = {
                btnCode: 'num',
                id: $(this).attr('data-id'),
                isSun: 1
            }
            getCaseList(params)
        })

        function getChild(param){
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '案件信息',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h').html(),
            });
            var _cols_child =  [
                [{
                    field: 'surveyOrgName',
                    minWidth: 150,
                    title: '主调查机构',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'org',
                    totalRowText: '合计'
                }, {
                    field: 'caseNum',
                    minWidth: 120,
                    title: '案件数量',
                    sort: true,
                    totalRow: true
                    // enent : 'cases'
                }, {
                    field: 'sunCaseNum',
                    minWidth: 120,
                    title: '阳性案件数量',
                    sort: true,
                    totalRow: true
                    // enent : 'cases'
                }, {
                    field: 'money1',
                    minWidth: 120,
                    title: '减损金额',
                    sort: true,
                    totalRow: true
                }, {
                    field: 'money2',
                    minWidth: 150,
                    title: '委托方确认金额',
                    sort: true,
                    totalRow: true
                }, {
                    field: 'surveySubmitMoney',
                    minWidth: 150,
                    title: '调查方确认金额',
                    sort: true,
                    totalRow: true
                }, {
                    field: 'eff',
                    minWidth: 120,
                    title: '平均时效',
                    sort: true,
                    templet: function (d) {
                        return d.eff + '天'
                    }
                }, {
                    field: 'sunRate',
                    minWidth: 110,
                    title: '阳性率',
                    sort: true,
                    templet: function (d) {
                        return d.sunRate + '%'
                    }
                }, {
                    field: 'jsl',
                    minWidth: 110,
                    title: '减损率',
                    sort: true,
                    templet: function (d) {
                        return d.jsl + '%'
                    }
                }, {
                    field: 'money3',
                    minWidth: 110,
                    title: '投产比',
                    sort: true,
                    templet: function (d) {
                        return d.money3
                    }
                }, {
                    field: 'ge3wRate',
                    minWidth: 130,
                    title: '3万以上实破率',
                    sort: true,
                    totalRow: false,
                    templet: function (d) {
                        return d.ge3wRate + '%'
                    }
                }, {
                    field: 'ge5wRate',
                    minWidth: 130,
                    title: '5万以上实破率',
                    sort: true,
                    totalRow: false,
                    templet: function (d) {
                        return d.ge5wRate + '%'
                    }
                }, {
                    field: 'effMoney',
                    minWidth: 130,
                    title: '时效考核',
                    sort: true,
                    totalRow: true
                }, {
                    field: 'checkSunMoney',
                    minWidth: 150,
                    title: '阳性率考核',
                    sort: true,
                    totalRow: true
                }]]
            var childTable =  table.render({
                id: "test-child",
                elem: '#test-child',
                cols: _cols_child,
                page: false,
                limit: 10000,
                height: 'full-150',
                drag: false,
                url: '${ctx}//survey/check/operate',
                where: param,
                even: true,
                totalRow: true,
                defaultToolbar:[],
                toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm layui-export" style="float: right;" lay-event="export">导出</a>',
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
            })
            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(childTable,{
                    filename: '导出数据.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
            table.on('tool(test-child)', function (obj) {
                console.log(obj.event)
                if (obj.event == 'org'){
                    var params = {
                        btnCode: 'num',
                        id: param.id,
                        surveyOrgId: obj.data.surveyOrgId,
                        surveyOrgName: obj.data.surveyOrgName,
                    }
                }else if (obj.event == 'sun'){
                    var params = {
                        btnCode: 'num',
                        id: param.id,
                        surveyOrgId: obj.data.surveyOrgId,
                        isSun : 1
                    }
                }else if (obj.event == 'cases'){
                    var params = {
                        btnCode: 'num',
                        id: param.id,
                        surveyOrgId: obj.data.surveyOrgId
                    }
                }
                getCaseList(params)
            })

        }

        function getCaseList(param){
            var _height = $(document).height() * 0.88
            var _width = $(document).width() * 0.96
            layer.open({
                type: 1,
                title: '案件列表',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-case-h').html(),
            });
            var _cols_child =  [
                [{
                    field: 'surveyNo',
                    minWidth: 150,
                    title: '案件编号',
                    templet: function (d) {
                        return '<div class="caseNo" data-id="'+d.surveyInfoId+'">'+d.surveyNo+'</div>'
                    }
                }, {
                    field: 'claimsNo',
                    minWidth: 120,
                    title: '理赔编号',
                }, {
                    field: 'surveyPerson',
                    minWidth: 120,
                    title: '被调查人',
                }, {
                    field: 'surveyOrgName',
                    minWidth: 150,
                    title: '主调查机构',
                }, {
                    field: 'derogationMoney',
                    minWidth: 110,
                    title: '减损金额',
                    sort: true,
                }, {
                    field: 'entrustSubmitMoney',
                    minWidth: 130,
                    title: '委托方确认金额',
                    sort: true,
                }, {
                    field: 'surveySubmitMoney',
                    minWidth: 130,
                    title: '调查方确认金额',
                    sort: true,
                }, {
                    field: 'isSun',
                    minWidth: 110,
                    title: '是否阳性',
                    templet: function (d) {
                        var sumText = ''
                        if (d.isSun == 1){
                            sumText = '是'
                        }else if (d.isSun == 0){
                            sumText = '否'
                        }
                        return sumText
                    }
                }, {
                    field: 'caeEff',
                    minWidth: 120,
                    title: '案件时效',
                    sort: true,
                    templet: function (d) {
                        return d.caeEff + '天'
                    }
                }, {
                    field: 'pfAmt',
                    minWidth: 130,
                    title: '赔付金额',
                    sort: true,
                }, {
                    field: 'jsAmt',
                    minWidth: 130,
                    title: '减损奖励金',
                    sort: true,
                }]]
            var childTable2 =  table.render({
                id: "test-child-case",
                elem: '#test-child-case',
                cols: _cols_child,
                page: false,
                limit: 10000,
                height: 'full-180',
                drag: false,
                url: '${ctx}/survey/check/operate',
                where: param,
                even: true,
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                defaultToolbar:[],
                toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm layui-export2" style="float: right;" lay-event="export">导出</a>',

            })

            $('.layui-export2').off('click').on('click',function (e) {
                var _this = $('.layui-export2')
                _this.attr('disabled',true)
                soulTable.export(childTable2,{
                    filename: param.surveyOrgName + '-案件数据.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })

            $('.table-content-child-case').on('click','.caseNo',function(){
                var _this = $(this)
                var url = "${ctx}/survey/case/info?menuCode=all-list&id="+_this.attr("data-id")
                parent.addTab("案件信息",url,true);
            })

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
    })
</script>
</body>
</html>
