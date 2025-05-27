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
            min-width: 150px!important;
            text-align: center;
            overflow: hidden;
        }
         .th-inner, .fht-cell{
             cursor: pointer;
             min-width: 149px!important;
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
            <input type="hidden" name="priceModelId" value="${priceModelId}"> <%--价格模板id--%>
            <input type="hidden" name="type" value="${type}"> <%--保司、互助--%>

            <div style="width: 100%; overflow: auto">
                <table class="table table-hover" id="table" style="table-layout: fixed;">
                    <thead>
                    <tr>
                        <th >区域类别</th>
                        <c:forEach items="${taskInfos}" var="item">
                            <th title="${item.taskName}">${item.taskName}</th>
                            <input type="hidden" name="taskId" id="taskId" value="${item.id}">
                            <input type="hidden" name="taskInfoContentId" id="taskInfoContentId" value="">
                            <input type="hidden" name="directionResultTypeId" id="directionResultTypeId" value="">
                        </c:forEach>
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
                                        <input type="text" data-v1="price_${item.id}_${itemAreaCategories.id}_null"
                                        <c:forEach items="${itemAreaCategories.prices}" var="price">
                                               <c:if test="${item.id == price.taskId}"> data-v2="price_${price.taskId}_${price.areaCategoriesId}_${price.id}"  value="${price.taskPrice}"</c:if>
                                        </c:forEach>
                                                class="form-control" onblur="add(this)">
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </form>
    </div><!--panel-info-->
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
    //     $("#table").bootstrapTable('destroy').bootstrapTable({
    //         toolbar: "#toolbar",
    //         height: height_doc,
    //         showColumns: true, //是否显示所有的列
    //         // pagination: true,
    //         fixedColumns: true,
    //         fixedNumber: 1,
    //         // theadClasses: 'table-thead',
    //         showHeader: true
    //     })
    //     $(window).resize(function () {
    //         $('#table').bootstrapTable('resetView');
    //
    //     });
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
//        location.reload();
        $("#save").attr("disabled",true);
        setTimeout(function(){reloadParent()},1000)
        reloadParent();
    }

    function add(that) {
        var v1 = $(that).attr("data-v1");
        var v2 = $(that).attr("data-v2");
        var taskId = v1.toString().split("_")[1];
        var areaCategoriesId = v1.toString().split("_")[2];
        var taskPrice = $(that).val();
        var priceId = null;

        if (v2 != undefined){
            priceId = v2.toString().split("_")[3];
        }

         ajaxSubmit("${ctx}/finaManager/operate",{"areaCategoriesId":areaCategoriesId,"taskPrice":taskPrice,"taskId":taskId,"priceId":priceId,"surveyCode":"surveyPriceSet"});

    }
    /**
     *提交金额
     */
    <%--function add(areaCategoriesId,areaCategoriesName,taskId,taskName,taskInfoContentId,taskInfoContentName,directionResultTypeId,directionResultTypeName){--%>
    <%--    var price = $("#"+areaCategoriesId+"_"+taskId+"_"+taskInfoContentId+"_"+directionResultTypeId+"_price").val();--%>
    <%--    if(!price){--%>
    <%--        price="";--%>
    <%--    }--%>
    <%--    ajaxSubmit("${ctx}/finaManager/operate",{"areaCategoriesId":areaCategoriesId,"areaCategoriesName":areaCategoriesName,"taskId":taskId,"taskName":taskName,"taskInfoContentId":taskInfoContentId,"taskInfoContentName":taskInfoContentName,"directionResultTypeId":directionResultTypeId,"directionResultTypeName":directionResultTypeName,"surveyCode":"${surveyCode}","btnCode":"${btnCode}","price":price,"type":"${type}"},null,null);--%>
    <%--}--%>
</script>
</body>
</html>
