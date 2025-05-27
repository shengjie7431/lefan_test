<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="main">
    <div class="main-boy">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="table-title">新增价格</div>
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/add" method="post">
                    <input type="hidden" name="surveyCode" value="franchisee">
                    <%--<button onclick="province()" type="button" class="btn btn-default">非直辖市</button>--%>
                    <%--<button onclick="provinceCity()" type="button" class="btn btn-default">直辖市</button>--%>
                    <%--<br><br>--%>
                    <div class="form-group">
                        <select id = "priceType" name="priceType" class="form-control" >
                            <option value="1" >保司版</option>
                            <option value="2" >互助版</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id = "type" name="type" class="form-control" onchange="selectType()" >
                            <option value="1" >非直辖市</option>
                            <option value="2" >直辖市</option>
                        </select>
                    </div>
                    <div class="form-group" id="provinceCityDiv" style="display: none">
                        <select id = "provinceCityIdNew" name="provinceCityIdNew" onchange="selectArea2()" class="form-control" style="width: 200px" required="required">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <c:if test="${area.cityType==1}">
                                    <option value="${area.areaId}">${area.areaName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group" id="provinceDiv">
                        <select id = "provinceIdNew" name="provinceIdNew" onchange="selectArea3()" class="form-control" style="width: 200px" required="required">
                            <option value="0">全部</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <c:if test="${area.cityType==0}">
                                    <option value="${area.areaId}">${area.areaName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="submit" onclick="return addNew('investigatorAreaPrice',needId,'1300',2,cityType,needName)">
                    </div>
                </form>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
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
        reloadParent();
    }

    function refreshSave(){
        reloadParent();
    }
    var addNew = function(surveyCode,parentId,btnCode,type,cityType,parentName){
        var priceType = $("#priceType").val();
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1050,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId+"&btnCode="+btnCode+"&type="+type+"&cityType="+cityType+"&parentName="+parentName+"&priceType="+priceType
        });
        return false;
    }

    function selectType(){
        var type = $("#type").val();
        if(type ==1){
            province();
        }
        if(type ==2){
            provinceCity();
        }
    }
    function province(){
        $("#provinceDiv").show();
        $("#provinceCityDiv").hide();
        selectArea3();
    }
    function provinceCity(){
        $("#provinceDiv").hide();
        $("#provinceCityDiv").show();
        selectArea2();
    }

    var needId =0;//添加价格需要的id
    var cityType = null;
    var areaId = null;
    function selectArea2(){
        var orgProvinceId = $("#provinceCityIdNew").val();
        <%--if(orgProvinceId == 0){--%>
            <%--needId=orgProvinceId;//添加价格需要的id--%>
            <%--cityType = null;--%>
            <%--return;--%>
        <%--}--%>
        <%--ajaxSubmit("${ctx}/user/role/selectByAreaId",{"areaId":orgProvinceId},function(v,e,p){--%>
            <%--//判断是不是直辖市--%>
            <%--if(e.data.results != null){--%>
                <%--var val = e.data.results[0];--%>
                <%--$("#areaTypeId").val(val.areaId);--%>
                <%--$("#areaName").val(val.areaName);--%>
                <%--needId=val.areaId;--%>
                <%--cityType =val.cityType;--%>
                <%--return;--%>
            <%--}--%>
        <%--});--%>
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceCityIdNew").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgProvinceId;//添加价格需要的id
        needName = val;
        cityType = 3;
    }

    var needName = "全部非直辖市";
    function selectArea3(){
        var orgProvinceId = $("#provinceIdNew").val();
        if(orgProvinceId == 0){
            needId=orgProvinceId;//添加价格需要的id
            needName="全部非直辖市";
            cityType = null;
            return;
        }
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceIdNew").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgProvinceId;//添加价格需要的id
        needName = val;
        cityType = null;
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>