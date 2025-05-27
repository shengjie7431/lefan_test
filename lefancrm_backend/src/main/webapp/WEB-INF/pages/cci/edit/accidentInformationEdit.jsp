<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script>
        function goBack(){
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);
        }
        function selectArea(){
            var orgProvinceId = $("#provinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#cityId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
        function  selectAreaCity(){
            var orgCityId = $("#cityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#districtId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/cci/accidentInformationSave" method="post">
        <input type="hidden" name="customerId" value="${id}">
        <table class="table">
            <tbody>
            <tr>
                <th width="20%" class="active"><strong class="necessary">*</strong>事故时间</th>
                <td width="80%">
                    <input name="accidentDate"  style="width: 400px;" type="text" required="required" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active"><strong class="necessary">*</strong>事故地点</th>
                <td width="80%" class="form-inline">
                    <div class="form-group">
                        省 <select id = "provinceId" name="provinceId" required="required"  style="width: 90px;"  onclick="selectArea()" class="form-control">
                        <option value="0">请选择</option>
                        <c:forEach items="${apiRsp.results}" var="area">
                            <option value="${area.areaId}">${area.areaName}</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="form-group">
                        市 <select id = "cityId" name="cityId"  required="required" style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                        <option value="0">请选择</option>
                    </select>
                    </div>
                    <div class="form-group">
                        区 <select id = "districtId" name="districtId"  required="required" style="width: 90px;" class="form-control">
                        <option value="0">请选择</option>
                    </select>
                    </div>
                    <input type="text" id = "accidentAddress" name="accidentAddress" required="required" value="${accidentAddress}" placeholder="具体地址"  style="width: 400px;" class="form-control">
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">责任认定</th>
                <td width="80%">
                    <select name="accidentCognizance" class="form-control" style="width: 400px;">
                        <option value="1" <c:if test="${accidentCognizance==1}">selected="selected" </c:if>>全部责任</option>
                        <option value="2" <c:if test="${accidentCognizance==2}">selected="selected" </c:if>>主要责任</option>
                        <option value="3" <c:if test="${accidentCognizance==3}">selected="selected" </c:if>>同等责任</option>
                        <option value="4" <c:if test="${accidentCognizance==4}">selected="selected" </c:if>>次要责任</option>
                        <option value="5" <c:if test="${accidentCognizance==5}">selected="selected" </c:if>>无责任</option>
                        <option value="6" <c:if test="${accidentCognizance==6}">selected="selected" </c:if>>责任无法认定</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交警大队</th>
                <td width="80%">
                    <input type="text" id = "policeTeam" name="policeTeam" value="${policeTeam}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">处理交警</th>
                <td width="80%">
                    <input type="text" id = "policeMan" name="policeMan" value="${policeMan}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交警电话</th>
                <td width="80%">
                    <input type="number" id = "policeTel" name="policeTel" value="${policeTel}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交强险承保公司</th>
                <td width="80%">
                    <input type="text" id = "insCompulsory" name="insCompulsory" value="${insCompulsory}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">商业险承保公司</th>
                <td width="80%">
                    <input type="text" id = "insCommercial" name="insCommercial" value="${insCommercial}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">三责险限额</th>
                <td width="80%">
                    <input type="number" id = "threeQuota" name="threeQuota" value="${threeQuota}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否购买不计免赔</th>
                <td width="80%">
                    <select name="isDeductibles" class="form-control" style="width: 400px;">
                        <option value="0" <c:if test="${isDeductibles==0}">selected="selected" </c:if>>否</option>
                        <option value="1" <c:if test="${isDeductibles==1}">selected="selected" </c:if>>是</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">驾驶员姓名</th>
                <td width="80%">
                    <input type="text" id = "driverName" name="driverName" value="${driverName}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">联系电话</th>
                <td width="80%">
                    <input type="number" id = "driverTel" name="driverTel" value="${driverTel}"  style="width: 400px;" class="form-control">
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否多方事故</th>
                <td width="80%">
                    <select name="isMulti" class="form-control" style="width: 400px;">
                        <option value="0" <c:if test="${isMulti==0}">selected="selected" </c:if>>否</option>
                        <option value="1" <c:if test="${isMulti==1}">selected="selected" </c:if>>是</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否存在免责情形</th>
                <td width="80%">
                    <select name="isRelief" class="form-control" style="width: 400px;">
                        <option value="0" <c:if test="${isRelief==0}">selected="selected" </c:if>>否</option>
                        <option value="1" <c:if test="${isRelief==1}">selected="selected" </c:if>>是</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">其他</th>
                <td width="80%">
                    <textarea name="otherDesc" style="width: 400px;height: 100px" class="form-control">${otherDesc}</textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="modal-footer">
            <button type="submit" onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
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
    $("#editForm").bind('submit', function(event) {
//        $("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>