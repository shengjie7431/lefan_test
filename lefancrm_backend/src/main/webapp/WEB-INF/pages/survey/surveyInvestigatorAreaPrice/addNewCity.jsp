<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">

        </div>
        <form id="editForm" role="form" action="" method="post">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="priceType" value="${priceType}">
            <div style="width: 100%; overflow: auto">
                <table class="table table-hover" style="table-layout: fixed;">
                    <thead>
                    <tr>
                        <th width="80">
                            <c:if test="${priceType ==1}">保司版</c:if>
                            <c:if test="${priceType ==2}">互助版</c:if>
                            ${areaName}
                        </th>
                        <c:forEach items="${taskInfos}" var="item">
                            <th style="width: 100px!important;">${item.infoName}</th>
                            <input type="hidden" name="taskId" id="taskId" value="${item.taskId}">
                            <input type="hidden" name="taskInfoContentId" id="taskInfoContentId" value="${item.taskInfoContentId}">
                            <input type="hidden" name="directionResultTypeId" id="directionResultTypeId" value="${item.directionResultTypeId}">
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <tr>
                        <td>
                            省会
                        </td>
                        <%--<c:forEach items="${taskInfos}" var="item">--%>
                            <%--<td>--%>
                                <%--<input type="text" id = "2_${areaId}_${item.id}_price" name="${areaId}_${item.id}_price"--%>
                                <%--<c:forEach items="${surveyInvestigatorAreaPrice}" var="price">--%>
                                       <%--<c:if test="${item.id == price.taskId && price.cityType ==2}">value="${price.price}"</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--onblur="add(2,'${areaId}','${item.id}','${item.name}')" class="form-control">--%>
                            <%--</td>--%>
                        <%--</c:forEach>--%>
                        <c:forEach items="${taskInfos}" var="item">
                            <td>
                                <input type="text" id = "2_${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price" name="${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price"
                                <c:forEach items="${surveyInvestigatorAreaPrice}" var="price">
                                       <c:if test="${item.taskId == price.taskId && item.taskInfoContentId == price.taskInfoContentId && item.directionResultTypeId == price.directionResultTypeId && price.cityType ==2}">value="${price.price}"</c:if>
                                </c:forEach>
                                       onblur="add(2,'${areaId}','${item.taskId}','${item.taskName}','${item.taskInfoContentId}','${item.taskInfoContentName}','${item.directionResultTypeId}','${item.directionResultTypeName}')" class="form-control">
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            地级市
                        </td>
                        <%--<c:forEach items="${taskInfos}" var="item">--%>
                            <%--<td>--%>
                                <%--<input type="text" id = "3_${areaId}_${item.id}_price" name="${areaId}_${item.id}_price"--%>
                                <%--<c:forEach items="${surveyInvestigatorAreaPrice}" var="price">--%>
                                       <%--<c:if test="${item.id == price.taskId && price.cityType ==3}">value="${price.price}"</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--onblur="add(3,'${areaId}','${item.id}','${item.name}')" class="form-control">--%>
                            <%--</td>--%>
                        <%--</c:forEach>--%>
                        <c:forEach items="${taskInfos}" var="item">
                            <td>
                                <input type="text" id = "3_${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price" name="${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price"
                                <c:forEach items="${surveyInvestigatorAreaPrice}" var="price">
                                       <c:if test="${item.taskId == price.taskId && item.taskInfoContentId == price.taskInfoContentId && item.directionResultTypeId == price.directionResultTypeId && price.cityType ==3}">value="${price.price}"</c:if>
                                </c:forEach>
                                       onblur="add(3,'${areaId}','${item.taskId}','${item.taskName}','${item.taskInfoContentId}','${item.taskInfoContentName}','${item.directionResultTypeId}','${item.directionResultTypeName}')" class="form-control">
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            县级市
                        </td>
                        <%--<c:forEach items="${taskInfos}" var="item">--%>
                            <%--<td>--%>
                                <%--<input type="text" id = "4_${areaId}_${item.id}_price" name="${areaId}_${item.id}_price"--%>
                                <%--<c:forEach items="${surveyInvestigatorAreaPrice}" var="price">--%>
                                       <%--<c:if test="${item.id == price.taskId && price.cityType ==4}">value="${price.price}"</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--onblur="add(4,'${areaId}','${item.id}','${item.name}')" class="form-control">--%>
                            <%--</td>--%>
                        <%--</c:forEach>--%>
                        <c:forEach items="${taskInfos}" var="item">
                            <td>
                                <input type="text" id = "4_${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price" name="${areaId}_${item.taskId}_${item.taskInfoContentId}_${item.directionResultTypeId}_price"
                                <c:forEach items="${surveyInvestigatorAreaPrice}" var="price">
                                       <c:if test="${item.taskId == price.taskId && item.taskInfoContentId == price.taskInfoContentId && item.directionResultTypeId == price.directionResultTypeId && price.cityType ==4}">value="${price.price}"</c:if>
                                </c:forEach>
                                       onblur="add(4,'${areaId}','${item.taskId}','${item.taskName}','${item.taskInfoContentId}','${item.taskInfoContentName}','${item.directionResultTypeId}','${item.directionResultTypeName}')" class="form-control">
                            </td>
                        </c:forEach>
                    </tr>
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
<script>
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

    function refreshSave(){
        $("#save").attr("disabled",true);
        setTimeout(function(){reloadParent()},1000)
        reloadParent();
    }
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

    /**
     *提交金额
     */
    <%--function add(cityType,areaId,taskId,taskName){--%>
        <%--var price = $("#"+cityType+"_"+areaId+"_"+taskId+"_price").val();--%>
        <%--if(!price){--%>
            <%--return;--%>
        <%--}--%>
        <%--ajaxSubmit("${ctx}/baseSurvey/operate",{"cityType":cityType,"areaId":areaId,"taskId":taskId,"taskName":taskName,"surveyCode":"${surveyCode}","btnCode":1100,"price":price},null,null);--%>
    <%--}--%>
    function add(cityType,areaId,taskId,taskName,taskInfoContentId,taskInfoContentName,directionResultTypeId,directionResultTypeName){
        var price = $("#"+cityType+"_"+areaId+"_"+taskId+"_"+taskInfoContentId+"_"+directionResultTypeId+"_price").val();
        if(!price){
            price="";
        }
        ajaxSubmit("${ctx}/baseSurvey/operate",{"cityType":cityType,"areaId":areaId,"taskId":taskId,"taskName":taskName,"taskInfoContentId":taskInfoContentId,"taskInfoContentName":taskInfoContentName,"directionResultTypeId":directionResultTypeId,"directionResultTypeName":directionResultTypeName,"surveyCode":"${surveyCode}","btnCode":1100,"price":price,"priceType":"${priceType}"},null,null);
    }
</script>
</body>
</html>

