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
    <form id="editForm" role="form" action="${ctx}/cci/injuredInformationAdd" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>姓名</th>
                    <td width="80%">
                        <input type="text" id = "userName" name="userName" value="${userName}" required="required"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>性别</th>
                    <td width="80%">
                        <select name="sex" class="form-control" style="width: 400px;" required="required" >
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>年龄</th>
                    <td width="80%">
                        <input type="number" id = "age" name="age" value="${age}" required="required"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>客户电话</th>
                    <td width="80%">
                        <input type="number" id = "userPhone" name="userPhone" required="required" value="${userPhone}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>家庭住址</th>
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
                            市 <select id = "cityId" name="cityId" required="required"  style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                            <option value="0">请选择</option>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "districtId" name="districtId" required="required" style="width: 90px;" class="form-control">
                            <option value="0">请选择</option>
                        </select>
                        </div>
                        <input type="text" id = "familyAddress" name="familyAddress" required="required" value="${familyAddress}" placeholder="具体地址"  style="width: 400px;" class="form-control">
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>户籍性质</th>
                    <td width="80%">
                        <select name="households" required="required" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${households==0}">selected="selected" </c:if>>农村</option>
                            <option value="1" <c:if test="${households==1}">selected="selected" </c:if>>城镇</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">工作单位</th>
                    <td width="80%">
                        <input type="text" id = "jobCompany" name="jobCompany" value="${jobCompany}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">月收入</th>
                    <td width="80%">
                        <input type="text" id = "income" name="income" value="${income}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">有无被抚养人</th>
                    <td width="80%">
                        <select name="dependants" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${dependants==0}">selected="selected" </c:if>>无</option>
                            <option value="1" <c:if test="${dependants==1}">selected="selected" </c:if>>有</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人</th>
                    <td width="80%">
                        <input type="text" id = "linkUser" name="linkUser" value="${linkUser}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人电话</th>
                    <td width="80%">
                        <input type="number" id = "linkTel" name="linkTel" value="${linkTel}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
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