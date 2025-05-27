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
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">委托方名称</th>
                    <td width="70%">
                        <input type="text" id="enturyName" name="enturyName" value="${enturyName}" class="form-control" readonly>
                        <input type="hidden" id="enturyId" name="enturyId" value="${enturyId}">
                        <%--<input type="button" value="选  择" onclick="selectConsignor('consignor')"  style="width: 78px;  height: 32px;"/>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">区域类别</th>
                    <td width="70%">
                        <select name="areaType" id ="areaType" class="form-control"required="required" onchange="toBuild()">
                            <option value="3" >区</option>
                            <option value="2" >市</option>
                            <option value="1" >省</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">区域</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group" id="provinceDiv">
                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}">${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group" id="cityDiv">
                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" >
                            <option value="">请选择</option>
                        </select>

                        </div>
                        <div class="form-group" id="districtDiv">
                            区 <select id = "districtId" name="districtId" style="width: 90px;" onclick="selectAreaDistrict()" class="form-control" >

                            <option value="">请选择</option>

                        </select>
                        </div>
                        <input type="hidden" id="areaId" name="areaId" value="">
                        <input type="hidden" id="areaName" name="areaName" value="">
                        <input type="hidden" id="province" name="province" value="">
                        <input type="hidden" id="city" name="city" value="">
                        <input type="hidden" id="district" name="district" value="">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">任务名称</th>
                    <td width="70%">
                        <input type="text" id="taskName" name="taskName"  style="width: 420px; display: inline-block;" class="form-control" readonly>
                        <input type="hidden" id="taskId" name="taskId">
                        <input type="button" value="选  择" onclick="selectTaskInfo('taskInfo')"  style="width:58px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">任务价格</th>
                    <td width="70%">
                        <input type="number"  step="0.01" id = "price" name="price"  class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        $("#areaId").val(orgProvinceId);
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
        $("#areaId").val(orgCityId);
        var val = $("#cityId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function  selectAreaDistrict(){
        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaId").val(orgDistrictId);
        var val = $("#districtId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function toBuild(){
        var objS = document.getElementById("areaType");
        var areaType = objS.options[objS.selectedIndex].value;
        if(areaType == 1){
            $("#provinceDiv").show();
            $("#cityDiv").hide();
            $("#cityId option").remove();
            $("#districtDiv").hide();
            $("#districtId option").remove();
        }else if(areaType == 2){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").hide();
            $("#districtId option").remove();
        }else if(areaType == 3){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").show();
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

<%--<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>--%>
<%--<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>列表</title>--%>
    <%--<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="main administrator">--%>
    <%--<div class="main-top">--%>
        <%--<h3>列表</h3>--%>
    <%--</div><!--main-top-->--%>

    <%--<div class="panel panel-info">--%>

        <%--<div class="panel-heading">--%>
            <%--<div class="pin">--%>
                <%--<div class="form-group">--%>
                    <%--<label class="title">任务类型:</label>--%>
                    <%--<select name="taskId" class="form-control">--%>
                        <%--<option value="">全部</option>--%>
                        <%--<c:forEach items="${taskInfos}" var="item">--%>
                            <%--<option value="${item.id}" >${item.name}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<form id="editForm" role="form" action="" method="post">--%>
            <%--<input type="hidden" name="surveyCode" value="${surveyCode}">--%>
            <%--<input type="hidden" name="consignorId" value="${consignorId}">--%>
            <%--<input type="hidden" name="consignorName" value="${consignorName}">--%>
            <%--<input type="hidden" name="btnCode" value="${btnCode}">--%>
            <%--<table class="table table-hover">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th width="80">地区</th>--%>
                    <%--<c:forEach items="${taskInfos}" var="item">--%>
                        <%--<th width="30">${item.name}价格</th>--%>
                    <%--</c:forEach>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody class="class-list">--%>
                <%--<c:forEach items="${apiRsp.results}" var="itemArea">--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                                <%--${itemArea.areaName}--%>
                        <%--</td>--%>
                        <%--<c:forEach items="${taskInfos}" var="item">--%>
                            <%--<td>--%>
                                <%--<input type="text" id = "${itemArea.areaId}_${item.id}_price" name="${itemArea.areaId}_${item.id}_price" onblur="add('${itemArea.areaId}','${item.id}','${item.name}')" class="form-control">--%>
                            <%--</td>--%>
                        <%--</c:forEach>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--<tr>--%>
                    <%--<td colspan="${taskInfos.size()+1}">--%>
                        <%--<button onclick="return refreshSave();" type="button" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</form>--%>
    <%--</div><!--panel-info-->--%>

<%--</div><!--main end-->--%>

<%--<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>--%>
<%--<script>--%>
    <%--var editor1;--%>
    <%--KindEditor.ready(function(K) {--%>
        <%--editor1 = K.create('textarea[name="content"]', {--%>
            <%--cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',--%>
            <%--uploadJson : '${ctx}/uploadFileForKindEditor'--%>
        <%--});--%>
    <%--});--%>

    <%--$("#editForm").bind('submit', function(event) {--%>
        <%--//$("#content").text(editor1.html());--%>
        <%--ajaxFormSubmit(this,reloadParent,null,null,reloadParent);--%>
        <%--event.preventDefault();--%>
    <%--});--%>

    <%--/**--%>
     <%--* 关闭dialog--%>
     <%--*/--%>
    <%--function closeDialog(){--%>
        <%--var closeBtn = $("#diglog_close_btn");--%>
        <%--if(closeBtn.size() == 0){--%>
            <%--closeBtn = $("#diglog_close_btn",window.parent.document);--%>
        <%--}--%>
        <%--closeBtn.click();--%>
    <%--}--%>

    <%--function refreshSave(){--%>
<%--//        location.reload();--%>
        <%--reloadParent();--%>
    <%--}--%>
    <%--/**--%>
     <%--*提交金额--%>
     <%--*/--%>
    <%--function add(areaId,taskId,taskName){--%>
        <%--var price = $("#"+areaId+"_"+taskId+"_price").val();--%>
        <%--if(!price){--%>
            <%--return;--%>
        <%--}--%>
        <%--ajaxSubmit("${ctx}/baseSurvey/operate",{"areaId":areaId,"taskId":taskId,"taskName":taskName,"surveyCode":"${surveyCode}","consignorId":"${consignorId}","btnCode":"${btnCode}","price":price,"consignorName":"${consignorName}"},null,null);--%>
    <%--}--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
