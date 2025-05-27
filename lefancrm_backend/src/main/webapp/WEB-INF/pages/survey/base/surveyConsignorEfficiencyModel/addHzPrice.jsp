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

        <form id="editForm" role="form" action="" method="post">
            <input type="hidden" id="surveyCode" name="surveyCode" value="${surveyCode}">
            <input type="hidden" id="modelId" name="modelId" value="${modelId}"> <%--模板id--%>
            <input type="hidden" name="type" value="${type}"> <%--保司、互助--%>

            <div style="width: 100%; overflow: auto">
                <table class="table table-hover" style="table-layout: fixed;">
                    <thead>
                    <tr>
                        <th></th>
                        <c:forEach items="${services}" var="item">
                            <th class="table-td">${item.name}</th>
                        </c:forEach>
                        <c:forEach items="${services}" var="item">
                            <c:forEach items="${item.surveyServiceSubTypes}" var="item2">
                                <th class="table-td">${item2.serviceSubName}</th>
                            </c:forEach>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                        <c:forEach items="${areaCategories}" var="itemAreaCategories">
                            <tr>
                                <td>
                                    ${itemAreaCategories.name}
                                </td>
                                <c:forEach items="${services}" var="item">
                                    <td class="table-td">
                                        <input type="number" class="form-control" data-area-id="${itemAreaCategories.id}" data-service-id="${item.id}"
                                            <c:forEach items="${itemAreaCategories.modelInfos}" var="info">
                                                <c:if test="${info.serviceId == item.id}">
                                                    value="${info.days}"
                                                </c:if>
                                            </c:forEach>
                                        />
                                    </td>
                                </c:forEach>
                                <c:forEach items="${services}" var="item">
                                    <c:forEach items="${item.surveyServiceSubTypes}" var="item2">
                                        <td class="table-td">
                                            <input type="number" class="form-control" data-sub-service-id="${item2.id}" data-area-id="${itemAreaCategories.id}"
                                                <c:forEach items="${itemAreaCategories.modelInfos}" var="info">
                                                    <c:if test="${info.subServiceId == item2.id}">
                                                        value="${info.days}"
                                                    </c:if>
                                                </c:forEach>
                                            />
                                        </td>
                                    </c:forEach>
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
<script>

    $(function(){
        $('.class-list').on('blur','input',function(){
            var _this = $(this);
            var modelId = $("#modelId").val();
            var surveyCode = $("#surveyCode").val();

            var days = _this.val();
            var serviceId = _this.attr('data-service-id')
            var subServiceId =_this.attr('data-sub-service-id')
            var areaCategoriesId =_this.attr('data-area-id');
            ajaxSubmit("${ctx}/baseSurvey/operate",{"modelId":modelId,"surveyCode":surveyCode,"serviceId":serviceId,
                "btnCode":1000,"days":days,"subServiceId":subServiceId,"areaCategoriesId":areaCategoriesId},null,null);
        })
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

</script>
</body>
</html>
