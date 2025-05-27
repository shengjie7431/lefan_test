<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>员工管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table-fixed-columns.css">c
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
            position: relative;
        }
        .table-thead{
            padding: 30px 0;
        }
        .fixed-table-toolbar {
            display: none !important;
        }
        .th-inner,
        .fht-cell, table td {
            width: 160px;
            text-align: center;
        }
        .panel{
            margin-bottom: 0!important;
        }
        .class-list a{
            color: #428bca;
        }
        .title_sort{
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }

        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }

        .layui-table-body {
            overflow-y: overlay;
        }

        label.layui-form-label {
            width: auto;
            padding: 6px 0;
            margin-bottom: 0;
        }

        .selectMul {
            width: 160px;
        }

        .layui-inline{
            padding:0  10px;
            margin-bottom: 10px;
        }
        .paramTime{
            width: 180px;
            height: 32px;
            line-height: 32px;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>员工管理列表 <small>共<span class="sum"></span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info" style="border: none">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" >
                   <input type="hidden" name="surveyCode" id="surveyCode" value="${surveyCode}">
                    <input type="hidden" id="order" value="${order}"  name="order" />
                    <input type="hidden" id="colSortType" value="${colSortType}" name="colSortType" />
                   <%--<div class="form-group">
                       工号:
                       <input name="jobNo" type="text" value="${jobNo}" style="width: 150px" class="form-control">
                   </div>
                    <div class="form-group">
                        姓名:
                        <input name="realName" type="text" value="${realName}" style="width: 150px" class="form-control">
                    </div>
                    <div class="form-group">
                        手机号:
                        <input name="userTel" type="text" value="${userTel}" style="width: 150px" class="form-control">
                    </div>
                    <div class="form-group">
                        身份证号:
                        <input name="idCard" type="text" value="${idCard}" style="width: 150px" class="form-control">
                    </div>--%>
                    <div class="form-group">
                        快捷查询:<input style="width: 350px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入工号，姓名，身份证号，联系方式" class="form-control">
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">社保缴纳公司:</label>
                        <div class="layui-input-inline">
                            <div id="sCompanys" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">成本归属公司:</label>
                        <div class="layui-input-inline">
                            <div id="companys" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">机构/部门:</label>
                        <div class="layui-input-inline">
                            <div id="organs" class="selectMul"></div>
                        </div>

                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">科室:</label>
                        <div class="layui-input-inline">
                            <div id="departments" class="selectMul"></div>
                        </div>

                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">小组:</label>
                        <div class="layui-input-inline">
                            <div id="teams" class="selectMul"></div>
                        </div>

                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">岗位:</label>
                        <div class="layui-input-inline">
                            <div id="jobPosts" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">关系:</label>
                        <div class="layui-input-inline">
                            <div id="relation" class="selectMul"></div>
                        </div>

                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入职时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input paramTime" readonly id="startTime"
                                   placeholder="请选择日期" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">离职时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input paramTime" readonly id="endTime"
                                   placeholder="请选择日期">
                        </div>
                    </div>

<%--                    <div class="layui-inline">--%>
<%--                        状态:--%>
<%--                        <select name="staffState" class="form-control">--%>
<%--                            <option value="">全部</option>--%>
<%--                            <option value="1" <c:if test="${staffState == '1'}">selected="selected" </c:if>>试用期员工</option>--%>
<%--                            &lt;%&ndash;<option value="2" <c:if test="${staffState == '2'}">selected="selected" </c:if>>调整人员</option>&ndash;%&gt;--%>
<%--                            <option value="3" <c:if test="${staffState == '3'}">selected="selected" </c:if>>离职待结算</option>--%>
<%--                            <option value="4" <c:if test="${staffState == '4'}">selected="selected" </c:if>>转正人员</option>--%>
<%--                            <option value="5" <c:if test="${staffState == '5'}">selected="selected" </c:if>>在职</option>--%>
<%--                            <option value="6" <c:if test="${staffState == '6'}">selected="selected" </c:if>>已离职</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>

                    <div class="layui-inline">
                        <label class="layui-form-label">状态:</label>
                        <div class="layui-input-inline">
                            <div id="staffStates" class="selectMul"></div>
                        </div>
                    </div>

                    <div class="layui-inline">
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="button" class="btn btn-default">查询</button>
                        <button onclick="edit()" type="button" class="btn btn-default">添加</button>
                        <button id="exportBtn" type="button" class="btn btn-default">导出</button>
                    </div>
                    </div>
                    <div class="layui-inline">
                    <button type="button" lay-submit="" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import" id="importOther"><i class="layui-icon"></i>导入</button>
                    </div>


                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
            </table>
        </div>

    </div>

</div><!--main end-->
<input type="hidden" value='${params.companysJson}' id="companysJson">
<input type="hidden" value='${params.organsJson}' id="organsJson">
<input type="hidden" value='${params.departmentsJson}' id="departmentsJson">
<input type="hidden" value='${params.teamsJson}' id="teamsJson">
<input type="hidden" value='${params.jobPostsJson}' id="jobPostsJson">
<input type="hidden" value='${params.levelsJson}' id="levelsJson">

<script type="text/html" id="barDemo">
    <a class=" layui-btn layui-btn-xs layui-btn" lay-event="edit" >编辑</a>
    <a class=" layui-btn layui-btn-xs layui-btn-danger" lay-event="operate" >删除</a>
    <a class=" layui-btn layui-btn-xs layui-btn-normal" lay-event="list" >变更历史</a>
</script>


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--<script src="${ctx}/js/jquery-1.8.2.min.js"></script>--%>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js"></script>


<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['table', 'util', 'xmSelect', 'laydate','jquery'], function () {
        var table = layui.table,
            util = layui.util,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            $ = layui.jquery



        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ // 按 enter
                // 查询方法();
                $("#batchOperateBtn").click();
            }
        }

        var cols = [
            [{
                field: 'realName',
                width: 160, align: 'center',
                fixed: 'left',
                title: '姓名',
                style: 'color: #3BA9FF',
                event: 'edit'
            },{
                field: 'userTel',
                width: 160, align: 'center',
                title: '手机号',
            },{
                field: 'jobNo',
                width: 160, align: 'center',
                title: '工号',
            },{
                field: 'idCard',
                width: 180, align: 'center',
                title: '身份证号',
            },{
                field: 'socialSecurityCompany',
                width: 200, align: 'center',
                title: '社保缴纳公司',
            },{
                field: 'budgetCompanyName',
                width: 200, align: 'center',
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 160, align: 'center',
                title: '机构/部门',
            },{
                field: 'department',
                width: 160, align: 'center',
                title: '科室',
            },{
                field: 'team',
                width: 160, align: 'center',
                title: '小组',
            },{
                field: 'postAppellationName',
                width: 160, align: 'center',
                title: '职务称谓',
            },{
                field: 'jobPost',
                width: 160, align: 'center',
                title: '岗位',
            },{
                field: 'postRankName',
                width: 160, align: 'center',
                title: '岗位职级',
            },{
                field: 'team',
                width: 160, align: 'center',
                title: '小组',
            },{
                field: 'surveyLevelName',
                width: 160, align: 'center',
                title: '调查员等级',
            },{
                field: 'basePay',
                width: 130, align: 'center',
                title: '基本工资',
            },{
                field: 'fixedPerfPay',
                width: 130, align: 'center',
                title: '固定绩效',
            },{
                field: 'assesPerfPay',
                width: 130, align: 'center',
                title: '考核绩效',
            },{
                field: 'managePerfPay',
                width: 130, align: 'center',
                title: '岗位津贴',
            },{
                field: 'managePerfPaySize',
                width: 200, align: 'center',
                title: '保险考核绩效（按量）',
            },{
                field: 'managePerfPaySizeHz',
                width: 200, align: 'center',
                title: '互助考核绩效（按量）',
            },{
                field: 'travelAllowancePay',
                width: 150, align: 'center',
                title: '驻外补贴',
            },{
                field: 'pensionBase',
                width: 150, align: 'center',
                title: '养老保险基数',
            },{
                field: 'pensionCompanyRate',
                width: 150, align: 'center',
                title: '养老保险公司比例',
            },{
                field: 'pensionPersonalRate',
                width: 150, align: 'center',
                title: '养老保险个人比例',
            },{
                field: 'medicalBase',
                width: 150, align: 'center',
                title: '医疗保险基数',
            },{
                field: 'medicalCompanyRate',
                width: 150, align: 'center',
                title: '医疗保险公司比例',
            },{
                field: 'medicalPersonalRate',
                width: 150, align: 'center',
                title: '医疗保险个人比例',
            },{
                field: 'upmBase',
                width: 150, align: 'center',
                title: '失业保险基数',
            },{
                field: 'upmCompanyRate',
                width: 150, align: 'center',
                title: '失业保险公司比例',
            },{
                field: 'upmPersonalRate',
                width: 150, align: 'center',
                title: '失业保险个人比例',
            },{
                field: 'isaBase',
                width: 150, align: 'center',
                title: '工伤保险基数',
            },{
                field: 'isaCompanyRate',
                width: 150, align: 'center',
                title: '工伤保险公司比例',
            },{
                field: 'birthBase',
                width: 150, align: 'center',
                title: '生育保险基数',
            },{
                field: 'birthCompanyRate',
                width: 150, align: 'center',
                title: '生育保险公司比例',
            },{
                field: 'fundPay',
                width: 150, align: 'center',
                title: '公积金基数',
            },{
                field: 'fundPayCompanyRate',
                width: 150, align: 'center',
                title: '公积金公司比例',
            },{
                field: 'fundPayPersonalRate',
                width: 150, align: 'center',
                title: '公积金个人比例',
            },{
                field: 'payAddress',
                width: 200, align: 'center',
                title: '社保公积金缴纳地',
            },{
                field: 'staffState',
                width: 140, align: 'center',
                title: '状态',
                templet: function (d) {
                    var  text = ''
                    if (d.staffState == 1){
                        text = '试用期员工'
                    } else if (d.staffState == 3){
                        text = '离职待结算'
                    } else if (d.staffState == 4){
                        text = '转正人员'
                    } else if (d.staffState == 5){
                        text = '在职'
                    } else if (d.staffState == 6){
                        text = '已离职'
                    }
                    return text
                }
            },{
                field: 'relation',
                width: 120, align: 'center',
                title: '关系',
                templet: function (d) {
                    var  text = ''
                    if (d.relation == 1){
                        text = '全职'
                    } else if (d.relation == 2){
                        text = '兼职'
                    } else if (d.relation == 3){
                        text = '退休返聘'
                    } else if (d.relation == 4){
                        text = '实习生'
                    } else if (d.relation == 5){
                        text = '合伙'
                    } else if (d.relation == 6){
                        text = '合伙+兼职'
                    } else if (d.relation == 7){
                        text = '合伙(发固定绩效)'
                    }
                    return text
                }
            },{
                field: 'entryTime',
                width: 150, align: 'center',
                title: '入职时间',
                sort: true,
                templet: function (d) {
                    return d.entryTime ? util.toDateString(d.entryTime, 'yyyy-MM-dd') : ''
                }
            },{
                field: 'regularTime',
                width: 150, align: 'center',
                title: '转正时间',
                sort: true,
                templet: function (d) {
                    return d.regularTime ? util.toDateString(d.regularTime, 'yyyy-MM-dd') : ''
                }
            },{
                field: 'quitTime',
                width: 150, align: 'center',
                title: '离职时间',
                sort: true,
                templet: function (d) {
                    return d.quitTime ? util.toDateString(d.quitTime, 'yyyy-MM-dd') : ''
                }

            },{
                field: 'quitCost',
                width: 120, align: 'center',
                title: '离职成本',
            },{
                field: 'createBy',
                width: 120, align: 'center',
                title: '创建人',
            },{
                field: 'createTime',
                width: 200, align: 'center',
                title: '创建时间',
                sort: true,
                templet: function (d) {
                    return d.createTime ? util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },

            {
                field: 'extTel',
                width: 120, align: 'center',
                title: '分机号'
            },{
                field: 'officePlace',
                width: 120, align: 'center',
                title: '办公地点'
            },{
                field: 'remark',
                width: 120, align: 'center',
                title: '备注'
            },{
                field: 'trialTime',
                width: 120, align: 'center',
                title: '试用期'
            },{
                field: 'jobLevel',
                width: 120, align: 'center',
                title: '岗位职别'
            },{
                field: 'education',
                width: 120, align: 'center',
                title: '学历'
            },{
                field: 'graduationSchool',
                width: 120, align: 'center',
                title: '毕业院校'
            },{
                field: 'graduationTime',
                width: 120, align: 'center',
                title: '毕业时间',
                sort: true,
                templet: function (d) {
                    return d.graduationTime ? util.toDateString(d.graduationTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'major',
                width: 120, align: 'center',
                title: '所学专业'
            },{
                field: 'bankNo',
                width: 120, align: 'center',
                title: '银行卡号'
            },{
                field: 'bankName',
                width: 120, align: 'center',
                title: '开户行'
            },{
                field: 'contractCompany',
                width: 120, align: 'center',
                title: '合同公司'
            },{
                field: 'contractType',
                width: 120, align: 'center',
                title: '合同类型'
            },{
                field: 'firstContractBeginTime',
                width: 120, align: 'center',
                title: '首次合同起始日',
                sort: true,
                templet: function (d) {
                    return d.firstContractBeginTime ? util.toDateString(d.firstContractBeginTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'firstContractEndTime',
                width: 120, align: 'center',
                title: '首次合同到期日',
                sort: true,
                templet: function (d) {
                    return d.firstContractEndTime ? util.toDateString(d.firstContractEndTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'nowContractBeginTime',
                width: 120, align: 'center',
                title: '现合同起始日',
                sort: true,
                templet: function (d) {
                    return d.nowContractBeginTime ? util.toDateString(d.nowContractBeginTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'nowContractEndTime',
                width: 120, align: 'center',
                title: '现合同到期日',
                sort: true,
                templet: function (d) {
                    return d.nowContractEndTime ? util.toDateString(d.nowContractEndTime, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'contractTerm',
                width: 120, align: 'center',
                title: '合同期限'
            },{
                field: 'renewNum',
                width: 120, align: 'center',
                title: '续约次数'
            },{
                field: 'emergencyContactName',
                width: 120, align: 'center',
                title: '紧急联系人姓名'
            },{
                field: 'emergencyContactRelation',
                width: 120, align: 'center',
                title: '紧急联系人关系'
            },{
                field: 'emergencyContactTel',
                width: 120, align: 'center',
                title: '紧急联系人电话'
            },{
                field: 'familyName',
                width: 120, align: 'center',
                title: '姓名（家人）'
            },{
                field: 'familyRelation',
                width: 120, align: 'center',
                title: '关系（家人）'
            },{
                field: 'familySex',
                width: 120, align: 'center',
                title: '性别（家人）'
            },{
                field: 'familyBirthday',
                width: 120, align: 'center',
                title: '生日（家人）',
                sort: true,
                templet: function (d) {
                    return d.familyBirthday ? util.toDateString(d.familyBirthday, 'yyyy-MM-dd HH:mm:ss') : ''
                }
            },{
                field: 'familyTel',
                width: 120, align: 'center',
                title: '电话（家人）'
            },{
                field: 'familyIdcardName',
                width: 120, align: 'center',
                title: '身份证姓名'
            },{
                width: 200, align: 'center',
                title: '操作',
                align: 'center',
                fixed: 'right',
                toolbar: '#barDemo'
            }
            ]]
        var param = {
            dataCode: 'personnelInfo',
            surveyCode:'personnelInfo',
            searchStr: $('input[name=searchStr]').val(),
            companyIds: $('select[name=companyIds]').val(),
            staffState:$('select[name=staffState]').val()
        }
        setTable(cols, param)

        function setTable(_cols, param) {
            var _h = $('.main-top').outerHeight() + $('.panel-info').outerHeight() + 50
            var fullH = 'full-' + _h
            table.render({
                id: "test",
                elem: '#test',
                even: true,
                cols: _cols,
                height: fullH,
                drag: false,
                page: true,
                limit: 15,
                limits: [15,20,30,40,50],
                url: '${ctx}/staff/getDetail',
                where: param,
                autoSort: false,
                parseData: function (res) {
                    $('.sum').text(res.count)
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results,
                    }
                },
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                done: function (res) {
                    sessionStorage.setItem('yg',JSON.stringify(res.data))
                }
            })
            table.on('tool(test)', function (obj) {
                curObj = obj
                if (obj.event == 'edit') {
                    edit(obj.data.id, 'update')
                } else if (obj.event == 'operate') {
                    operate(obj.data.id, $("#surveyCode").val(),'9999',true)
                } else if (obj.event == 'list') {
                    list(obj.data.id,'personnelInfoLog')
                }

            })
            table.on('sort(test)', function(obj) { //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                console.log(obj.field); //当前排序的字段名
                console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
                console.log(this); //当前排序的 th 对象
                var paramOrder = {
                    order: '',
                    colSortType: ''
                }
                if (obj.field == 'entryTime'){
                    paramOrder.order = 1
                } else if (obj.field == 'quitTime'){
                    paramOrder.order = 2
                } else if (obj.field == 'createTime'){
                    paramOrder.order = 3
                }
                if (obj.type == 'asc'){
                    paramOrder.colSortType = 1
                } else if (obj.type == 'desc'){
                    paramOrder.colSortType = 2
                }
                Object.assign(param, paramOrder)
                table.reload('test', {
                    url: '${ctx}/staff/getDetail',
                    where: param,
                });
            })

            $('#exportBtn').click(function (e) {
                var startTime = $('#startTime').val()
                startTime = startTime.split('~')
                var endTime = $('#endTime').val()
                endTime = endTime.split('~')
                Object.assign(param, {
                    order: '',
                    colSortType: '',
                    searchStr: $('input[name=searchStr]').val(),
                    socialSecurityCompanyIds: demo0.getValue('valueStr'),
                    staffState:$('select[name=staffState]').val(),
                    companyIds: demo1.getValue('valueStr'),
                    organIds: demo2.getValue('valueStr'),
                    departmentIds: demo3.getValue('valueStr'),
                    jobPostIds: demo5.getValue('valueStr'),
                    staffStates: demo55.getValue('valueStr'),
                    teamIds: demo4.getValue('valueStr'),
                    relation: demo6.getValue('valueStr'),
                    entryTimeStart: startTime[0] ? startTime[0].trim() : '',
                    entryTimeEnd:startTime[1] ? startTime[1].trim() : '',
                    quitTimeStart:endTime[0] ? endTime[0].trim() : '',
                    quitTimeEnd: endTime[1] ? endTime[1].trim() :''
                })
                var url =  '${ctx}/staff/downLoad?surveyCode=personnelInfo&companyIds='+param.companyIds+'&socialSecurityCompanyIds='+param.socialSecurityCompanyIds+'&organIds='+param.organIds+'&departmentIds='+param.departmentIds+'&teamIds='+param.teamIds+'&entryTime='+param.entryTimeStr+
                        '&entryTimeStart='+param.entryTimeStart+'&entryTimeEnd='+param.entryTimeEnd+'&quitTimeStart='+param.quitTimeStart+'&quitTimeEnd='+param.quitTimeEnd
                    +'&searchStr='+param.searchStr+'&relation='+param.relation
                    // +'&staffState='+param.staffState
                    +'&jobPostIds='+param.jobPostIds+'&staffStates='+param.staffStates
                var aLink = document.createElement('a');
                aLink.href=url
                aLink.dispatchEvent(new MouseEvent('click', {
                    bubbles: true,
                    cancelable: true,
                    view: window
                }));

            })

        }
        $('#batchOperateBtn').click(function (e) {
            var startTime = $('#startTime').val()
            startTime = startTime.split('~')
            var endTime = $('#endTime').val()
            endTime = endTime.split('~')
            Object.assign(param, {
                order: '',
                colSortType: '',
                searchStr: $('input[name=searchStr]').val(),
                // companyIds: $('select[name=companyIds]').val(),
                staffState:$('select[name=staffState]').val(),
                // page: '1',
                // pageSize: '15',
                socialSecurityCompanyIds: demo0.getValue('valueStr'),
                companyIds: demo1.getValue('valueStr'),
                organIds: demo2.getValue('valueStr'),
                departmentIds: demo3.getValue('valueStr'),
                jobPostIds: demo5.getValue('valueStr'),
                staffStates: demo55.getValue('valueStr'),
                teamIds: demo4.getValue('valueStr'),
                relation: demo6.getValue('valueStr'),
                entryTimeStart: startTime[0] ? startTime[0].trim() : '',
                entryTimeEnd:startTime[1] ? startTime[1].trim() : '',
                quitTimeStart:endTime[0] ? endTime[0].trim() : '',
                quitTimeEnd: endTime[1] ? endTime[1].trim() :'',
            })

            $('.table-content').empty()
            $('.table-content').append('<table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">\n' +
                '            </table>')
            // table.render(tableRender)
            setTable(cols, param)

            <%--table.reload('test', {--%>
                <%--url: '${ctx}/staff/getDetail',--%>
                <%--where: param,--%>
            // });

        })


        // $('body').on('click', '#diglog_close_btn', function(){
        //     updateObj()
        // })
        var demo0 = xmSelect.render({
            el: '#sCompanys',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo1 = xmSelect.render({
            el: '#companys',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo2 = xmSelect.render({
            el: '#organs',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo3 = xmSelect.render({
            el: '#departments',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo4 = xmSelect.render({
            el: '#teams',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo5 = xmSelect.render({
            el: '#jobPosts',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo55 = xmSelect.render({
            el: '#staffStates',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo6 = xmSelect.render({
            el: '#relation',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [{
                value: 1,
                name: '全职'
            },{
                value: 2,
                name: '兼职'
            },{
                value: 3,
                name: '退休返聘'
            },{
                value: 4,
                name: '实习生'
            },{
                value: 5,
                name: '合伙'
            },{
                value: 6,
                name: '合伙+兼职'
            },{
                value: 7,
                name: '合伙(发固定绩效)'
            }]
        })

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
                    Object.assign(param, {selected: true})
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

        var companysJson = $("#companysJson").val();
        companysJson = JSON.parse(companysJson);
        filterJson(demo0, companysJson, 'id', 'name', false, false)

        var companysJson = $("#companysJson").val();
        companysJson = JSON.parse(companysJson);
        filterJson(demo1, companysJson, 'id', 'name', false, false)

        var organsJson = $("#organsJson").val();
        organsJson = JSON.parse(organsJson);
        filterJson(demo2, organsJson, 'id', 'name', false, false)

        var departmentsJson = $("#departmentsJson").val();
        departmentsJson = JSON.parse(departmentsJson);
        filterJson(demo3, departmentsJson, 'id', 'name', false, false)

        var teamsJson = $("#teamsJson").val();
        teamsJson = JSON.parse(teamsJson);
        filterJson(demo4, teamsJson, 'id', 'name', false, false)

        var jobPostsJson = $("#jobPostsJson").val();
        jobPostsJson = JSON.parse(jobPostsJson);
        filterJson(demo5, jobPostsJson, 'id', 'name', false, false)

        var stateJson = [{"id" : 1,"name" : "试用期员工"},{"id" : 2,"name" : "调整人员"},{"id" : 3,"name" : "离职待结算"},{"id" : 4,"name" : "转正人员"},{"id" : 5,"name" : "在职"},{"id" : 6,"name" : "已离职"}]
        filterJson(demo55, stateJson, 'id', 'name', false, false)
        var s = [1,2,3,4,5];
        demo55.setValue(s)

        startTime = laydate.render({
            elem: '#startTime',
            range: '~',
            done: function (value, date) {
            }
        });
        endTime = laydate.render({
            elem: '#endTime',
            range: '~',
            done: function (value, date) {
            }
        });


    })


    function updateObj() {
        $.ajax({
            url: '${ctx}/staff/getDetail',
            data: {
                dataCode: 'personnelInfo',
                surveyCode:'personnelInfo',
                searchStr:curObj.data.jobNo,
            },
            type: 'post',
            success: function (x,e) {
                var res = e.data
                if (res.isSuccess){
                    curObj.update(res.results[0])
                }
            }
        })

    }

    var edit = function(id,type){
        var title = '添加'
        var url = "${ctx}/staff/edit?id="+id+"&surveyCode="+'${surveyCode}' +'&type=add'
        if (type == 'update'){
            title = '编辑'
            url = "${ctx}/staff/edit?id="+id+"&surveyCode="+'${surveyCode}' +'&type=edit'
        }
        openDialog({
            frame:true,
            title:title,
            height:800,
            width:1000,
            url:url
        });
    }

    var list = function(id,surveyCode){
        var width = $(document.body).outerWidth()*0.9;
        var height = $(document).outerHeight()*0.9;
        openDialog({
            frame:true,
            title:"变更历史",
            height:height,
            width:width,
            url:"${ctx}/staff/list?staffPersonnelInfoId="+id+"&id="+id+"&surveyCode="+surveyCode
        });
    }

    function operate(id,surveyCode,btnCode,ajax){
        if(ajax){
            var url = "${ctx}/staff/operate",param = {"id":id,"btnCode":btnCode,"operateCode":surveyCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {

        }
    }

    layui.use('upload',function () {
        var upload = layui.upload;

        upload.render({
            elem: '#importOther',
            url: '${ctx}/staff/export', //改成您自己的上传接口
            data: {
                exportType: 'personnelInfo'
            },
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                console.log('before', obj)
                layer.load(); //上传loading
            },
            done: function (res,e,upload) {
                layer.closeAll('loading')
                console.log("res", res)

                if (res.isSuccess) {
                    // layer.msg(res.results.message, {time: 2000})
                    layer.alert(res.message,{icon:1},function () {
                        location.reload()
                    })

                } else {
                    // layer.msg('导入出错', {time: 2000})
                    layer.alert('导入出错',{icon:2})
                }
            },
            error: function (index, upload) {
                layer.closeAll('loading')
                console.log('error', index, upload)
            }
        })
    })
</script>



</body>
</html>
