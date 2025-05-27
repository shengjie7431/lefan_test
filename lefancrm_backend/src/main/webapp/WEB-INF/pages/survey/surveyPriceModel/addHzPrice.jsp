<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table-fixed-columns.css">
    <style>
         table td, table th {
            width: 150px!important;
            text-align: center;
            overflow: hidden;
        }
         .th-inner, .fht-cell{
             cursor: pointer;
             width: 149px!important;
             white-space: inherit!important;

         }
        .pull-right{
            display: none;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <form id="editForm" role="form" action="" method="post">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="priceModelId" id="priceModelId" value="${priceModelId}"> <%--价格模板id--%>
            <input type="hidden" name="type" id="type" value="${type}"> <%--保司、互助--%>

            <div style="width: 100%; overflow: auto">
                <table class="table table-hover" id="table" style="table-layout: fixed;">
                    <thead>
                    <tr>
                        <th >区域类别</th>
                        <c:forEach items="${taskInfos}" var="item">
                            <th title="${item.infoName}">${item.infoName}</th>
                            <input type="hidden" name="taskId" id="taskId" value="${item.taskId}">
                            <input type="hidden" name="taskInfoContentId" id="taskInfoContentId" value="${item.taskInfoContentId}">
                            <input type="hidden" name="directionResultTypeId" id="directionResultTypeId" value="${item.directionResultTypeId}">
                        </c:forEach>
                        <th >深度案件</th>
                        <th >远程调查</th>
                        <th >核对调阅类(首个方向)</th>
                        <th >核对调阅类(后续方向)</th>
                        <th >一般检索类</th>
                        <th >特殊检索类</th>
                        <th >疑难侦察类</th>
                        <th >攻坚侦察类</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                        <c:forEach items="${areaCategories}" var="itemAreaCategories">
                            <tr>
                                <td>
                                    ${itemAreaCategories.name}
                                </td>
                                <c:forEach items="${taskInfos}" var="item">
                                    <td>
                                        <input type="text" id = "${itemAreaCategories.id}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price" name="${itemAreaCategories.id}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price"
                                        <c:forEach items="${itemAreaCategories.prices}" var="price">
                                               <c:if test="${item.taskId == price.taskId && item.taskInfoContentId == price.taskInfoContentId && item.directionResultTypeId == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                        </c:forEach>
                                               onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','${item.taskId}','${item.taskName}','${item.taskInfoContentId}','${item.taskInfoContentName}','${item.directionResultTypeId}','${item.directionResultTypeName}')" class="form-control">
                                    </td>
                                </c:forEach>
                                <%--特殊价格：深度案件--%>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_9999_9999_9999_price" name="${itemAreaCategories.id}_9999_9999_9999_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'9999' == price.taskId && '9999' == price.taskInfoContentId && '9999' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','9999','深度案件','9999','深度案件','9999','深度案件')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10000_10000_10000_price" name="${itemAreaCategories.id}_10000_10000_10000_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10000' == price.taskId && '10000' == price.taskInfoContentId && '10000' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10000','远程调查','10000','远程调查','10000','远程调查')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10001_10001_10001_price" name="${itemAreaCategories.id}_10001_10001_10001_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10001' == price.taskId && '10001' == price.taskInfoContentId && '10001' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10001','核对调阅类(首个方向)','10001','核对调阅类(首个方向)','10001','核对调阅类(首个方向)')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10002_10002_10002_price" name="${itemAreaCategories.id}_10002_10002_10002_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10002' == price.taskId && '10002' == price.taskInfoContentId && '10002' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10002','核对调阅类(后续方向)','10002','核对调阅类(后续方向)','10002','核对调阅类(后续方向)')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10003_10003_10003_price" name="${itemAreaCategories.id}_10003_10003_10003_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10003' == price.taskId && '10003' == price.taskInfoContentId && '10003' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10003','一般检索类','10003','一般检索类','10003','一般检索类')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10004_10004_10004_price" name="${itemAreaCategories.id}_10004_10004_10004_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10004' == price.taskId && '10004' == price.taskInfoContentId && '10004' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10004','特殊检索类','10004','特殊检索类','10004','特殊检索类')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10005_10005_10005_price" name="${itemAreaCategories.id}_10005_10005_10005_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10005' == price.taskId && '10005' == price.taskInfoContentId && '10005' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10005','疑难侦察类','10005','疑难侦察类','10005','疑难侦察类')" class="form-control">
                                </td>
                                <td>
                                    <input type="text" id = "${itemAreaCategories.id}_10006_10006_10006_price" name="${itemAreaCategories.id}_10006_10006_10006_price"
                                    <c:forEach items="${itemAreaCategories.prices}" var="price">
                                           <c:if test="${'10006' == price.taskId && '10006' == price.taskInfoContentId && '10006' == price.directionResultTypeId}">value="${price.taskPrice}"</c:if>
                                    </c:forEach>
                                           onblur="add('${itemAreaCategories.id}','${itemAreaCategories.name}','10006','攻坚侦察类','10006','攻坚侦察类','10006','攻坚侦察类')" class="form-control">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </form>
    </div><!--panel-info-->
    <input type="hidden" id="useObj" value="${priceModel.useObj}">
    <c:if test="${priceModel.useObj == 2}">
        <div class="panel panel-info">
            <input id="chkSurveyEntrustIsRate" <c:if test="${priceModel.surveyEntrustIsRate == 1}">checked</c:if> type="checkbox">非直营机构调查方价格、所有机构核算价格为委托方价格的
            <input id="surveyEntrustRate" type="number" value="${priceModel.surveyEntrustRate}" min="0" step="0.01" style="text-align: right;width: 60px" >%
        </div>
    </c:if>
    <div style="margin-left: 20px">
        <button onclick="refreshSave();" id="save"  type="button" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table-fixed-columns.js"></script>
<script>
    $(function () {
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 15 - 80 -10
        $("#table").bootstrapTable('destroy').bootstrapTable({
            toolbar: "#toolbar",
            height: height_doc,
            showColumns: true, //是否显示所有的列
            // pagination: true,
            fixedColumns: true,
            fixedNumber: 1,
            // theadClasses: 'table-thead',
            showHeader: true
        })
        $(window).resize(function () {
            $('#table').bootstrapTable('resetView');

        });
    })
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

    function refreshSave(){
        if($("#useObj").val() == 2){
            var isRate = $("#chkSurveyEntrustIsRate").prop("checked") == true ? 1 : 0;
            var value = $("#surveyEntrustRate").val();
            ajaxSubmit("${ctx}/baseSurvey/operate",{"surveyCode" : "priceModel","btnCode" : "save-survey-entrust","id" : $("#priceModelId").val(),"type" : $("#type").val(),
                "surveyEntrustIsRate" : isRate,"surveyEntrustRate" : value},null,null);
        }

//        location.reload();
        $("#save").attr("disabled",true);
        setTimeout(function(){reloadParent()},1000)
        reloadParent();
    }
    /**
     *提交金额
     */
    function add(areaCategoriesId,areaCategoriesName,taskId,taskName,taskInfoContentId,taskInfoContentName,directionResultTypeId,directionResultTypeName){
        var price = $("#"+areaCategoriesId+"_"+taskId+"_"+taskInfoContentId+"_"+directionResultTypeId+"_price").val();
        if(!price){
            price="";
        }
        ajaxSubmit("${ctx}/baseSurvey/operate",{"areaCategoriesId":areaCategoriesId,"areaCategoriesName":areaCategoriesName,"taskId":taskId,"taskName":taskName,"taskInfoContentId":taskInfoContentId,"taskInfoContentName":taskInfoContentName,"directionResultTypeId":directionResultTypeId,"directionResultTypeName":directionResultTypeName,"surveyCode":"${surveyCode}","btnCode":"${btnCode}","price":price,"type":"${type}"},null,null);
    }
</script>
</body>
</html>
