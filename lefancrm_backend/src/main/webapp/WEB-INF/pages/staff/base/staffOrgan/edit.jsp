<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/staff/update" method="post">
        <input type="hidden" name="surveyCode" id="surveyCode" value="${surveyCode}">
        <input type="hidden" name="id" id="id" value="${staffOrgan.id}">
        <input type="hidden" name="organProduct" id="organProduct" value='${staffOrganProductsJson}'>
        <input type="hidden" value='${params.personnelInfosJson}' id="personnelInfosJson">
        <input type="hidden" value='${billListJson}' id="billListJson">
        <input type="hidden" name="organManagerStaffId" id="organManagerStaffId" value="${staffOrgan.organManagerStaffId}">
        <input type="hidden" name="superiorManagerStaffId" id="superiorManagerStaffId" value="${staffOrgan.superiorManagerStaffId}">
        <input type="hidden" name="organAttribute" id="organAttribute" value="${staffOrgan.organAttribute}">

        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">名称</th>
                    <td width="70%">
                        <input type="text" id = "name" name="name" value="${staffOrgan.name}" required="required"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构经理</th>
                    <td width="70%">
                        <div id="organManager"></div>
                        <%--<select class="form-control" required="required" name="organManagerStaffId" id="organManagerStaffId">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${personnelInfos}" var="item">--%>
                                <%--<option value="${item.id}" <c:if test="${staffOrgan.organManagerStaffId == item.id}"> selected="selected" </c:if>>${item.realName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">上级分管总</th>
                    <td width="70%">
                        <div id="superiorManager"></div>

                        <%--<select class="form-control" required="required" name="superiorManagerStaffId" id="superiorManagerStaffId">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${personnelInfos}" var="item">--%>
                                <%--<option value="${item.id}" <c:if test="${staffOrgan.superiorManagerStaffId == item.id}"> selected="selected" </c:if>>${item.realName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构属性</th>
                    <td width="70%">
                        <div id="superiorManager2"></div>

                        <%--<select class="form-control" required="required" name="superiorManagerStaffId" id="superiorManagerStaffId">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${personnelInfos}" var="item">--%>
                        <%--<option value="${item.id}" <c:if test="${staffOrgan.superiorManagerStaffId == item.id}"> selected="selected" </c:if>>${item.realName}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">类别</th>
                    <td width="70%">
                       <%-- <div id="organType"></div>--%>
                       <select class="form-control" required="required" name="organType" id="organType">
                        <option value="">请选择</option>
                           <option value="1" <c:if test="${staffOrgan.organType == 1}"> selected="selected" </c:if>>直营</option>
                           <option value="2" <c:if test="${staffOrgan.organType == 2}"> selected="selected" </c:if>>合伙</option>
                           <option value="3" <c:if test="${staffOrgan.organType == 3}"> selected="selected" </c:if>>加盟</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">关联开票产品</th>
                    <td width="70%">
                        <div id="superiorManager3"></div>

                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">每刻报销是否可以修改公司/机构</th>
                    <td width="70%">
                        <select class="form-control" required="required" name="isCanModify" id="isCanModify">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${staffOrgan.isCanModify == 1}"> selected="selected" </c:if>>是</option>
                            <option value="0" <c:if test="${staffOrgan.isCanModify == 0}"> selected="selected" </c:if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">核算收入是否等于支出</th>
                    <td width="70%">
                        <select class="form-control" required="required" name="accOutEqual" id="accOutEqual">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${staffOrgan.accOutEqual == 1}"> selected="selected" </c:if>>是</option>
                            <option value="0" <c:if test="${staffOrgan.accOutEqual == 0}"> selected="selected" </c:if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">每刻报销总经理审核警戒线</th>
                    <td>
                        <input type="number" class="form-control" name="warnMoney" value="${staffOrgan.warnMoney}" />
                        <div style="position: absolute;    right: 60px;    top: 440px;    font-size: inherit;">元</div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">状态</th>
                    <td width="70%">
                        <%-- <div id="organType"></div>--%>
                        <select class="form-control" required="required" name="state" id="state">
                            <option value="0" <c:if test="${staffOrgan.state == 0}"> selected="selected" </c:if>>启用</option>
                            <option value="1" <c:if test="${staffOrgan.state == 1}"> selected="selected" </c:if>>停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">绩效发放方式</th>
                    <td width="70%">
                        <%-- <div id="organType"></div>--%>
                        <select class="form-control" required="required" name="performance" id="performance">
                            <option value="0" <c:if test="${staffOrgan.performance == 0}"> selected="selected" </c:if>>绩效发放</option>
                            <option value="1" <c:if test="${staffOrgan.performance == 1}"> selected="selected" </c:if>>工资条发放</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" name="btnUpload" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $("[name=btnUpload]").attr("disabled","disabled");
        ajaxFormSubmit(this,reloadParent,null,null,null);
        setTimeout(function(){
            $("[name=btnUpload]").removeAttr("disabled");
        },3000)
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    function selectName(organId,btnCode,obj){
        var result = obj.value;
        if(result ==null || result==""){
            return;
        }
        ajaxSubmit("${ctx}/staff/selectStaffInfoByRelationId",{"organId":organId,"surveyCode":'${surveyCode}',"btnCode":btnCode,"name":result},function(v,e,p){
            if(e.data.results != null){
                alert("名称不可重复！");
                $("[name=btnUpload]").attr("disabled","disabled");
                return;
            }else{
                $("[name=btnUpload]").removeAttr("disabled");
            }
        });
    }

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    layui.use(['jquery','xmSelect'], function () {
        var $ = layui.jquery,
            xmSelect = layui.xmSelect;
        var demo1 = xmSelect.render({
            el: '#organManager',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            on: function(data){
                console.log(data)
                var arr = data.arr;
                var value = arr.length  ? arr[0].value : ''
                if (data.change.length){
                    $('#organManagerStaffId').val(value)
                }
            },
            radio: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
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
            el: '#superiorManager',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            on: function(data){
                var arr = data.arr;
                var value = arr.length  ? arr[0].value : ''
                if (data.change.length){
                    $('#superiorManagerStaffId').val(value)
                }
            },
            radio: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
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
            el: '#superiorManager2',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            on: function(data){
                var arr = data.arr;
                var value = arr.length  ? arr[0].value : ''
                if (data.change.length){
                    $('#organAttribute').val(value)
                }
            },
            radio: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [{
                name: '业务主营',
                value: '1'
            },{
                name: '后援管理',
                value: '2'
            },{
                name: '业务管理部门',
                value: '3'
            },{
                name: '业务销售',
                value: '4'
            }]
        })
        var pIdJson = $('#organProduct').val()
        pIdJson = pIdJson ? JSON.parse(pIdJson) : []
        var pIds = []

        console.log('-p-',pIdJson)

        pIdJson.map(function(item){
            pIds.push(item.productEnumId)
        })
        var demo4 = xmSelect.render({
            el: '#superiorManager3',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            on: function(data){
                var arr = data.arr;
                // var value = arr.length  ? arr[0].value : ''
                // if (data.change.length){
                //     $('#organProduct').val(value)
                // }
                if (arr.length){
                    var newValue = []
                    arr.map(function (item) {
                        newValue.push(item.value)
                    })
                    $('#organProduct').val(newValue.join(','))
                }else{
                    $('#organProduct').val('')
                }
            },
            // radio: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })


        var personnelInfosJson = $("#personnelInfosJson").val();
        personnelInfosJson = JSON.parse(personnelInfosJson);
        filterJson(demo1, personnelInfosJson,'id','realName',false, false)

        filterJson(demo2, personnelInfosJson,'id','realName',false, false)

        var billListJson = $("#billListJson").val();
        billListJson = JSON.parse(billListJson);
        filterJson(demo4, billListJson,'enumCode','enumName',false, false)

        demo1.setValue([$('#organManagerStaffId').val()])
        demo2.setValue([$('#superiorManagerStaffId').val()])
        demo3.setValue([$('#organAttribute').val()])

        demo4.setValue(pIds)
        $('#organProduct').val(pIds.join(','))



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
</script>
</body>
</html>