<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>


    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${surveyConsignorPrice.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">委托方名称</th>
                    <td width="70%">
                        <input type="text" id="enturyName" name="enturyName" value="${surveyConsignorPrice.enturyName}" class="form-control" readonly>
                        <%--<input type="hidden" id="enturyId" name="enturyId" value="${surveyConsignorPrice.enturyId}" >--%>
                        <%--<input type="button" value="选  择" onclick="selectConsignor('consignor')"  style="width: 78px;  height: 32px;"/>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">区域类别</th>
                    <td width="70%">
                        <select name="areaType" id ="areaType" class="form-control" required="required" onchange="toBuild()">
                            <option value="3" <c:if test="${surveyConsignorPrice.areaType==3}">selected="selected" </c:if>>区</option>
                            <option value="2" <c:if test="${surveyConsignorPrice.areaType==2}">selected="selected" </c:if>>市</option>
                            <option value="1" <c:if test="${surveyConsignorPrice.areaType==1}">selected="selected" </c:if>>省</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">委托方区域</th>
                    <td width="70%" class="form-inline">
                        <div class="form-group" id="provinceDiv" style="display: none">
                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyConsignorPrice.provinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group" id="cityDiv" style="display: none">
                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" >
                            <c:forEach items="${cityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyConsignorPrice.cityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>

                        </div>
                        <div class="form-group" id="districtDiv" style="display: none">
                            区 <select id = "districtId" name="districtId" style="width: 90px;" onclick="selectAreaDistrict()" class="form-control" >
                            <c:forEach items="${districtApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyConsignorPrice.districtId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <input type="hidden" id="areaTypeId" name="areaTypeId" value="">
                        <input type="hidden" id="areaName" name="areaName" value="">
                        <input type="hidden" id="province" name="province" value="">
                        <input type="hidden" id="city" name="city" value="">
                        <input type="hidden" id="district" name="district" value="">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">任务名称</th>
                    <td width="70%">
                        <input type="text" id="taskName" name="taskName" value="${surveyConsignorPrice.taskName}" class="form-control" readonly>
                        <%--<input type="hidden" id="taskId" name="taskId" value="${surveyConsignorPrice.taskId}">--%>
                        <%--<input type="button" value="选  择" onclick="selectTaskInfo('taskInfo')"  style="width: 78px;  height: 32px;"/>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">任务价格</th>
                    <td width="70%">
                        <input type="number"  step="0.01" id = "taskPrice" name="taskPrice" value="${surveyConsignorPrice.taskPrice}"  class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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

    var selectConsignor = function(surveyCode){
        openDialog({
            frame:true,
            title:"选择委托方机构",
            height:500,
            width:850,
            url:"${ctx}/surveyConsignor/selectConsignor?surveyCode="+surveyCode
        });
    }

    var selectTaskInfo = function(surveyCode){
        openDialog({
            frame:true,
            title:"选择任务类型",
            height:500,
            width:850,
            url:"${ctx}/surveyTaskInfo/selectTaskInfo?surveyCode="+surveyCode
        });
    }

    function selectArea(){
        var orgProvinceId = $("#provinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#cityId option").remove();
            $("#cityId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
        });

        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceId").find("option:selected").text();
        $("#areaName").val(val);
    }
    function  selectAreaCity(){
        var orgCityId = $("#cityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
        });
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgCityId);
        var val = $("#cityId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function  selectAreaDistrict(){
        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaTypeId").val(orgDistrictId);
        var val = $("#districtId").find("option:selected").text();
        $("#areaName").val(val);
    }

    toBuild();
    function toBuild(){
        var objS = document.getElementById("areaType");
        var areaType = objS.options[objS.selectedIndex].value;
        if(areaType == 1){
            $("#provinceDiv").show();
            $("#cityDiv").hide();
            $("#cityId option").remove();
            $("#districtDiv").hide();
            $("#districtId option").remove();

            $("#areaTypeId").val($("#provinceId").val());
            var val = $("#provinceId").find("option:selected").text();
            $("#areaName").val(val);
        }else if(areaType == 2){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").hide();
            $("#districtId option").remove();

            $("#areaTypeId").val($("#cityId").val());
            var val = $("#cityId").find("option:selected").text();
            $("#areaName").val(val);
        }else if(areaType == 3){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").show();

            $("#areaTypeId").val($("#districtId").val());
            var val = $("#districtId").find("option:selected").text();
            $("#areaName").val(val);
        }

    }

    function goBack(){
        var val = $("#provinceId").find("option:selected").text();
        var val1 = $("#cityId").find("option:selected").text();
        var val2 = $("#districtId").find("option:selected").text();
        $("#province").val(val);
        $("#city").val(val1);
        $("#district").val(val2);
    }
</script>
</body>
</html>