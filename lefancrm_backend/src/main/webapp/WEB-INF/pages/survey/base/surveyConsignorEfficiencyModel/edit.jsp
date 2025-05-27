<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .container-edit{
            position: relative;
            width: 98%;
            padding: 0;
            margin: 0 auto;
        }
        .dalogs-0108 {
            position: relative;
            width: 98%;
            margin: 0 auto;
            overflow: hidden;
            background-color: #fff;
        }

        .dalogs-0108 .close-bar {
            position: absolute;
            right: 20px;
            top: 16px;
            width: 20px;
            height: 20px;
        }

        .dalogs-0108 .close {
            margin-right: 10px;
            width: 2px;
            height: 20px;
            background: #6b6b6b;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
            display: inline-block;
        }

        .dalogs-0108 .close:after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 2px;
            height: 20px;
            background: #6b6b6b;
            -webkit-transform: rotate(270deg);
            -moz-transform: rotate(270deg);
            -o-transform: rotate(270deg);
            -ms-transform: rotate(270deg);
            transform: rotate(270deg);
        }

        .dalogs-0108 .d-title {
            width: 100%;
            font-size: 14px;
            font-weight: bold;
            text-align: center;
            border-bottom: 1px solid #eee;
        }

        .dalogs-0108 .d-title .text {
            height: 40px;
            line-height: 40px;
            font-size: 14px;
            text-align: center;
        }

        .dalogs-0108 .dalogs-content {
            width: 100%;
            margin: 0 auto;

        }

        .dalogs-0108 .dalogs-content .dc-p {
            display: block;
            margin: 20px auto;
            width: 90%;
            height: 30px;
            line-height: 30px;
        }

        .dalogs-0108 .dalogs-content .form-groups {
            width: 96%;
            margin: 0 auto;
            padding: 20px 0;
        }

        .dalogs-0108 .dalogs-content .form-group {
            overflow: auto;
            margin: 0;
            padding: 10px 0;
            /* display: flex; */
        }

        .label-btns {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-btns .label-btn {
            padding: 0 12px;
            margin: 5px 20px;
            width: 150px;
            height: 36px;
            line-height: 36px;
            color: #3BA9FF;
            border: 1px solid #3BA9FF;
            background-color: #fff;
            font-size: 14px;
            text-align: center;
            cursor: pointer;
        }

        .label-btns .label-btn.active {
            color: #fff;
            background-color: #3BA9FF;

        }

        .table-tr {
            width: 100%;
            display: flex;
            padding: 10px 0;
        }

        .table-tr .table-td {
            display: flex;
            align-items: center;
            width: 40%;
        }
        .operate{
            padding-left: 10px;
            display: inline-block;
        }
        .operate svg {
            margin-right: 6px;
        }

        .table-tr .table-td input {
            width: 100%;
            height: 30px;
            text-align: center;
        }

        .table-tr .table-td {
            padding: 0 1%;
            width: 11%;
        }

        /*.table-tr .table-td:nth-of-type(6n+2) {*/
            /*width: 36%;*/
            /*justify-content: center;*/
        /*}*/

        /*.table-tr .table-td:nth-of-type(6n+3) {*/
            /*width: 26%;*/
        /*}*/

        /*.table-tr .table-td:first-of-type input {*/
            /*width: 80%;*/
        /*}*/
    </style>
</head>
<body>
<div class="container-edit">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <c:if test="${btnCode ==1000}">
            <input type="hidden" name="id" id="id" value="${surveyConsignorEfficiencyModel.id}">
        </c:if>
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="type" value="${type}">
        <input type="hidden" name="btnCode" value="${btnCode}">
        <input type="hidden" name="infos"  id="infos">

        <div class="dalogs-0108">

            <div class="dalogs-content">
                <div class="form-groups">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">模版名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" class="form-control"
                                   <c:if test="${btnCode ==1000}">value="${surveyConsignorEfficiencyModel.name}" </c:if>
                            >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">时效单位</label>
                        <div class="col-sm-10">
                            <select name="efficiencyAttr" id="efficiencyAttr" class="form-control">
                                <option value="1" <c:if test="${surveyConsignorEfficiencyModel.efficiencyAttr==1}">selected="selected" </c:if>>工作日</option>
                                <option value="2" <c:if test="${surveyConsignorEfficiencyModel.efficiencyAttr==2}">selected="selected" </c:if>>自然日（除国庆、春节）</option>
                            </select>
                        </div>
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <label class="col-sm-6 control-label">考核时效设置</label>--%>
<%--                    </div>--%>
<%--                    <div class="form-group form-group-up">--%>
<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td"></div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">${item.name}</div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">${item2.serviceSubName}</div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>

<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td">直辖市市区</div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">--%>
<%--                                    <input type="text" name="consignorEfficiency" data-city="5" data-service="${item.id}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                            <c:if test="${infos.cityType == 5 && infos.serviceId == item.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    >--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">--%>
<%--                                        <input type="text" name="consignorEfficiency" data-city="5" data-service="${item.id}" data-sub-service="${item2.id}" data-sub-servicename="${item2.serviceSubName}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 5 && infos.subServiceId != null && infos.subServiceId == item2.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                        >--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>

<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td">直辖市郊区</div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">--%>
<%--                                    <input type="text" name="consignorEfficiency" data-city="6" data-service="${item.id}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 6 && infos.serviceId == item.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    >--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">--%>
<%--                                        <input type="text" name="consignorEfficiency" data-city="6" data-service="${item.id}" data-sub-service="${item2.id}" data-sub-servicename="${item2.serviceSubName}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 6 && infos.subServiceId != null&& infos.subServiceId == item2.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                        >--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td">省会</div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">--%>
<%--                                    <input type="text" name="consignorEfficiency" data-city="2" data-service="${item.id}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                           <c:if test="${infos.cityType == 2 && infos.serviceId == item.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    >--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">--%>
<%--                                        <input type="text" name="consignorEfficiency" data-city="2" data-service="${item.id}" data-sub-service="${item2.id}" data-sub-servicename="${item2.serviceSubName}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 2  && infos.subServiceId != null&& infos.subServiceId == item2.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                        >--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td">地级市</div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">--%>
<%--                                    <input type="text" name="consignorEfficiency" data-city="3" data-service="${item.id}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                           <c:if test="${infos.cityType == 3 && infos.serviceId == item.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    >--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">--%>
<%--                                        <input type="text" name="consignorEfficiency" data-city="3" data-service="${item.id}" data-sub-service="${item2.id}" data-sub-servicename="${item2.serviceSubName}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 3&& infos.subServiceId != null && infos.subServiceId == item2.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                        >--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                        <div class="table-tr">--%>
<%--                            <div class="table-td">县级市</div>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <div class="table-td">--%>
<%--                                    <input type="text" name="consignorEfficiency" data-city="4" data-service="${item.id}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                           <c:if test="${infos.cityType == 4 && infos.serviceId == item.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    >--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
<%--                            <c:forEach items="${services}" var="item">--%>
<%--                                <c:forEach items="${item.surveyServiceSubTypes}" var="item2">--%>
<%--                                    <div class="table-td">--%>
<%--                                        <input type="text" name="consignorEfficiency" data-city="4" data-service="${item.id}" data-sub-service="${item2.id}" data-sub-servicename="${item2.serviceSubName}"--%>
<%--                                        <c:forEach items="${surveyConsignorEfficiencyModel.efficiencyModelInfos}" var="infos">--%>
<%--                                               <c:if test="${infos.cityType == 4 && infos.subServiceId != null&& infos.subServiceId == item2.id}">value="${infos.days}"</c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                        >--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
            </div>

        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" onclick="return toValid()" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function(){
        var efficiencyModelId = $("#id").val();

        if(efficiencyModelId){
            $('.form-group-up').on('blur','input',function(){
                var _this = $(this)
                var days = _this.val()
                var cityType = _this.attr('data-city')
                var serviceId =_this.attr('data-service')
                var subServiceId =_this.attr('data-sub-service')
                ajaxSubmit("${ctx}/baseSurvey/operate",{"efficiencyModelId":efficiencyModelId,"cityType":cityType,"serviceId":serviceId,"surveyCode":"${surveyCode}","btnCode":1000,"days":days,"subServiceId":subServiceId},null,null);
            })
        }
    })
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
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

    function toValid(){
        var info = [];
        $("input[name='consignorEfficiency']").each(function() {
            var day = $(this).val();
            var cityType = $(this).attr('data-city');
            var serviceId = $(this).attr('data-service');
            var subServiceId = $(this).attr('data-sub-service');
            var subServiceName = $(this).attr('data-sub-servicename');
            var item = {
                "days" : day,
                "cityType" : cityType,
                "serviceId" : serviceId,
                "subServiceId":subServiceId,
                "subServiceName":subServiceName
            };
            info.push(item);
        });
        $("#infos").val(JSON.stringify(info));
        return true;
    }

    function updateInfo(efficiencyModelId,cityType,serviceId){
        var param = $(this)
        var days = param.val()
        if(!days){
            days="";
        }
        ajaxSubmit("${ctx}/baseSurvey/operate",{"efficiencyModelId":efficiencyModelId,"cityType":cityType,"serviceId":serviceId,"surveyCode":"${surveyCode}","btnCode":1000,"days":days},null,null);

    }
</script>
</body>
</html>