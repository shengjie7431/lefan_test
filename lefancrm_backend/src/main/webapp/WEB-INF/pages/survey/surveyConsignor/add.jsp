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
    <style>
        .unit{
            position: absolute;
            top: 17px;
            right: 40px;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">单位简称</th>
                    <td width="70%">
                       <input type="text" id = "company" name="company" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">单位全称</th>
                    <td width="70%">
                        <input type="text" id = "name" name="name" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">委托方说明</th>
                    <td width="70%">
                        <input type="text" id = "remark" name="remark" class="form-control">
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">区域类别</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="areaType" id ="areaType" class="form-control" required="required" onchange="toBuild()">--%>
<%--                            <option value="3" >区</option>--%>
<%--                            <option value="2" >市</option>--%>
<%--                            <option value="1" >省</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="20%" class="active">区域</th>--%>
<%--                    <td width="80%" class="form-inline">--%>
<%--                        <div class="form-group" id="provinceDiv">--%>
<%--                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">--%>
<%--                            <option value="">请选择</option>--%>
<%--                            <c:forEach items="${apiRsp.results}" var="area">--%>
<%--                                <option value="${area.areaId}" <c:if test="${surveyFranchisee.provinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                        </div>--%>
<%--                        <div class="form-group" id="cityDiv">--%>
<%--                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" >--%>
<%--                            <option value="">请选择</option>--%>
<%--                        </select>--%>

<%--                        </div>--%>
<%--                        <div class="form-group" id="districtDiv">--%>
<%--                            区 <select id = "districtId" name="districtId" style="width: 90px;" onclick="selectAreaDistrict()" class="form-control" >--%>

<%--                            <option value="">请选择</option>--%>

<%--                        </select>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" id="areaTypeId" name="areaTypeId" value="">--%>
<%--                        <input type="hidden" id="areaName" name="areaName" value="">--%>
<%--                        <input type="hidden" id="province" name="province" value="">--%>
<%--                        <input type="hidden" id="city" name="city" value="">--%>
<%--                        <input type="hidden" id="district" name="district" value="">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">委托方类别</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="type" class="form-control" required="required">--%>
<%--                            <option value="1" >合作伙伴</option>--%>
<%--                            <option value="2" >散户</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">是否授信</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="isCredit" class="form-control" required="required">--%>
<%--                            <option value="1" >是</option>--%>
<%--                            <option value="0" >否</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <th width="30%" class="active">公司属性</th>
                    <td width="70%">
                        <select name="orgAttr" class="form-control" required="required">
                            <option value="1" >保险公司</option>
                            <option value="2" >互助机构</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">时效设置</th>
                    <td width="70%">
                        <select name="efficiencyAttr" class="form-control">
                            <option value="1" >工作日</option>
                            <option value="2" >自然日</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">报告模板</th>
                    <td width="70%">
                        <select name="modelId" id="modelId" class="form-control" required="required">
                            <c:forEach items="${surveyModelInfoDtos}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">申请人</th>
                    <td width="70%">
                        <input type="text" id = "appUser" name="appUser" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">审批人</th>
                    <td width="70%">
                        <input type="text" id = "apvUser" name="apvUser" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">商务属性</th>
                    <td width="70%">
                        <select name="businessAttr" class="form-control">
                            <option value="1" >市场一部（郑哲）</option>
                            <option value="2" >市场二部（曹刘强）</option>
                            <option value="3" >互助</option>
                            <option value="4" >正言金融（郑哲）</option>
                            <option value="5" >市场三部（韩正栋）</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th width="30%" class="active">市场人员</th>
                    <td width="70%">
                        <select name="marketUserId" class="form-control">
                            <c:forEach items="${busUserRoleDtoList1}" var="item">
                                <option value="${item.userId}">${item.userName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">市场人员分成比例</th>
                    <td width="70%" style="position: relative">
                        <input type="double"  id = "marketRate" name="marketRate" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">开票公司</th>
                    <td width="70%" style="position: relative">
                        <select name="billCompanyId" class="form-control">
                            <c:forEach items="${billCompanys}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">引荐人员</th>
                    <td width="70%">
                        <select name="recommendUserId" class="form-control">
                                <c:forEach items="${busUserRoleDtoList2}" var="item">
                                    <option value="${item.userId}">${item.userName}</option>
                                </c:forEach>
                            </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">引荐人员分成比例</th>
                    <td width="70%" style="position: relative">
                        <input type="double"  id = "recommendRate" name="recommendRate" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构状态</th>
                    <td width="70%">
                        <select name="orgState" class="form-control">
                            <option value="0" >启用</option>
                            <option value="1" >停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">邮件上限值</th>
                    <td width="70%" style="position: relative">
                        <input type="number" step="1" id = "attrMaxSize" name="attrMaxSize" class="form-control" min="1" max="50"><div class="unit">M</div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">是否为粘贴，自动填充信息</th>
                    <td width="70%">
                        <select name="autoFillState" class="form-control" required="required">
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">PDF附件格式</th>
                    <td width="70%">
                        <select name="pdfType" class="form-control" required="required">
                            <option value="1" >所有方向附件集中一个PDF</option>
                            <option value="2" >每个方向附件单独一个PDF</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">垫付兜底方</th>
                    <td width="70%">
                        <select name="advanceParty" class="form-control" required="required">
                            <option value="1">乐凡兜底</option>
                            <option value="2">委托方兜底</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">业务类型</th>
                    <td width="70%">
                        <select name="serviceType" class="form-control" required="required">
                            <option value="1">垫付</option>
                            <option value="2">调查</option>
                            <option value="3">垫付+调查</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" onclick="goBack()"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        ajaxFormSubmit(this,reloadParent,null,null,function(v,e,p){
            alert(e.data.msg);
        });
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
        var val = $("#provinceId").find("option:selected").text()+$("#cityId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function  selectAreaDistrict(){
        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaTypeId").val(orgDistrictId);
        var val = $("#provinceId").find("option:selected").text()+$("#cityId").find("option:selected").text()+$("#districtId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function toBuild(){
        var objS = document.getElementById("areaType");
        var areaType = objS.options[objS.selectedIndex].value;
        if(areaType == 1){
            $("#provinceDiv").show();
            $("#cityDiv").hide();
            $("#districtDiv").hide();
        }else if(areaType == 2){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").hide();
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