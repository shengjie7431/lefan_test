<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
    </style>
    <script>
        function goBack(){
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);

            $("#billingEnum").attr("disabled",false);
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

        function selectArea1(){
            var shiId = $("#shiId").val();
            var quId = $("#quId").val();
            var orgProvinceId = $("#provinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#cityId option").remove();
                $("#cityId").append("<option value=''>请选择</option>");
                console.log(e.data.results);
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    if(val.areaId == shiId){
                        $("#cityId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }else{
                        $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }
                }
                $("#districtId option").remove();
                $("#districtId").append("<option value=''>请选择</option>");
                selectAreaCity1(shiId,quId);
            });

        }

        function  selectAreaCity1(shiId,quId){
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":shiId},function(v,e,p){
                $("#districtId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    if(val.areaId == quId){
                        $("#districtId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }else {
                        $("#districtId").append("<option value='" + val.areaId + "'>" + val.areaName + "</option>");
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/billingApply/billingApplySave" method="post">
        <input type="hidden" name="id" value="${billingApplyInfo.id}">
        <input type="hidden" id="shenId">
        <input type="hidden" id="shiId">
        <input type="hidden" id="quId">
        <input type="hidden" id="staffPersonnelInfoDtoList" value='${staffPersonnelInfoDtoList}'>
        <input type="hidden" id="meritUserId" name="meritUserId" value="${billingApplyInfo.meritUserId}">
        <input type="hidden" id="meritUserName" name="meritUserName" value="">
        <div class="form-group">
            <table class="table">
                <tbody>
                <%--<tr>
                    <th width="20%" class="active">产品类型</th>
                    <td width="80%">
                        <select name="productType" id="productType" class="form-control" style="width: 400px;" required="required" onchange="selectOrgInfo()">
                            <c:forEach items="${products}" var="item">
                                <option value="${item.id}" <c:if test="${billingApplyInfo.businessType == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">业务来源</th>
                    <td width="80%">
                        <select name="orgId" id="orgId" class="form-control" style="width: 400px;" required="required">
                            <c:forEach items="${orgInfoDtos}" var="item">
                                <option value="${item.id}" <c:if test="${billingApplyInfo.orgId == item.id}"> selected="selected" </c:if>>${item.orgName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>--%>
                <tr>
                    <th width="20%" class="active">开票对象</th>
                    <td width="80%">
                        <input type="text" id="companyName" name="companyName" value="${billingApplyInfo.companyName}" style="width: 320px; display: inline-block;" class="form-control" required="required" placeholder="可输入">
                        <input type="hidden" id="companyId" name="companyId">
                        <input type="button" value="选  择" onclick="selectCompanyName()"  style="width: 78px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票公司</th>
                    <td width="80%">
                        <select name="businessType" id="businessType" class="form-control" style="width: 400px;" required="required" onchange="selectCorporation()()">
                            <option value="">请选择</option>
                            <c:forEach items="${billCompanys}" var="item">
                                <option value="${item.id}" <c:if test="${billingApplyInfo.businessType == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收入归属机构</th>
                    <td width="80%">
                        <select name="staffOrgId" id="staffOrgId" class="form-control" style="width: 400px;" required="required" onchange="selectBillingEnum()">
                            <option value="">请选择</option>
                            <c:forEach items="${staffOrganDtoList}" var="item">
                                <option value="${item.id}" <c:if test="${billingApplyInfo.staffOrgId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票产品</th>
                    <td width="80%">
                        <select name="billingEnum" id="billingEnum" class="form-control" style="width: 400px;" required="required" onchange="selectBillingItem()">
                            <c:forEach items="${bullingEnums}" var="item">
                                <option value="${item.billingEnumId}" <c:if test="${billingApplyInfo.billingEnum == item.billingEnumId}"> selected="selected" </c:if>>${item.billingEnumName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票项目</th>
                    <td width="80%">
                        <select name="billingItem" id="billingItem" class="form-control" style="width: 400px;">
                            <c:forEach items="${bullingItems}" var="item">
                                <option value="${item.billingItemId}" <c:if test="${billingApplyInfo.billingItem == item.billingItemId}"> selected="selected" </c:if>>${item.billingItemName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件编号</th>
                    <td width="80%">
                        <input type="text" id="caseNo" name="caseNo" value="${billingApplyInfo.caseNo}" style="width: 400px;" class="form-control" readonly="true">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件标题</th>
                    <td width="80%">
                        <input type="text" id="caseTitle" name="caseTitle" value="${billingApplyInfo.caseTitle}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票金额</th>
                    <td width="80%">
                        <input type="number" step="0.01" id="billingMoney" name="billingMoney" value="${billingApplyInfo.billingMoney}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">税率</th>
                    <td width="80%">
                        <input type="number" step="0.01" id="taxRate" name="taxRate" value="${billingApplyInfo.taxRate}" style="width: 400px;float: left" class="form-control" required="required">
                        <span style="width: 50px;height: 50px;vertical-align: -webkit-baseline-middle;position: absolute;position: relative;left: -38px;top: 5px;font-size: 14px">%</span>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">开票类型</th>
                    <td width="80%">
                        <select name="billingType" class="form-control" style="width: 400px;" required="required">
                            <option value="1" <c:if test="${billingApplyInfo.billingType==1}">selected="selected" </c:if>>专票</option>
                            <option value="2" <c:if test="${billingApplyInfo.billingType==2}">selected="selected" </c:if>>普票</option>
                            <option value="3" <c:if test="${billingApplyInfo.billingType==3}">selected="selected" </c:if>>电子普票</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">发票收件人</th>
                    <td width="80%">
                        <input type="text" id="recipientsName" name="recipientsName" value="${billingApplyInfo.recipientsName}" style="width: 320px;  display: inline-block;" class="form-control" required="required">
                        <input type="button" value="选  择" onclick="selectRecipient()"  style="width: 78px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收件人电话</th>
                    <td width="80%">
                        <input type="text" id="recipientsPhone" name="recipientsPhone" value="${billingApplyInfo.recipientsPhone}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收件人地址</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                                <c:forEach items="${apiRsp.results}" var="area">
                                    <option value="${area.areaId}" <c:if test="${billingApplyInfo.provinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                                </c:forEach>
                            </select>
                            <div id="div_city"></div>
                            <div id="div_district"></div>
                        </div>
                        <div class="form-group">
                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" required="required">
                                <%--<option value="${billingApplyInfo.cityId}">${billingApplyInfo.city}</option>--%>
                                    <c:forEach items="${cityApiRsp.results}" var="area">
                                        <option value="${area.areaId}" <c:if test="${billingApplyInfo.cityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                                    </c:forEach>
                                </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "districtId" name="districtId" style="width: 90px;" class="form-control" required="required">
                                    <%--<option value="${billingApplyInfo.districtId}">${billingApplyInfo.district}</option>--%>
                                    <c:forEach items="${districtApiRsp.results}" var="area">
                                        <option value="${area.areaId}" <c:if test="${billingApplyInfo.districtId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                                    </c:forEach>
                                </select>
                        </div>
                    </td>
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">收件人详细地址</th>
                    <td  width="80%">
                        <input type="text" id="address" name="address" value="${billingApplyInfo.address}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr style="display: none;">
<%--                    <th width="20%" class="active">绩效所属人员</th>--%>
<%--                    <td  width="80%">--%>
<%--                        <input type="text" id="meritName" name="meritName" value="${billingApplyInfo.meritName}" style="width: 400px;" class="form-control" required="required">--%>
<%--                    </td>--%>
                    <th width="20%" class="active">绩效所属人员</th>
                    <td width="80%">
                        <div id="superiorManager2" style="width:400px" ></div>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">被保险人姓名</th>
                    <td  width="80%">
                        <input type="text" id="insuredName" name="insuredName" value="${billingApplyInfo.insuredName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">车牌号</th>
                    <td  width="80%">
                        <input type="text" id="carNo" name="carNo" value="${billingApplyInfo.carNo}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">伤者姓名</th>
                    <td  width="80%">
                        <input type="text" id="woundedName" name="woundedName" value="${billingApplyInfo.woundedName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">开票对象</th>--%>
                    <%--<td  width="80%">--%>
                        <%--<span style="position:absolute;overflow:hidden;width:400px;height:32px;">--%>
                            <%--<select name="companyId" class="form-control"  onclick="qlcTrainS('qlc_zdz1')" id="qlc_zdz1" style="height:30px;outline:0;" required="required">--%>
                                <%--<c:forEach items="${companys}" var="item">--%>
                                    <%--<option value="${item.companyName}" <c:if test="${billingApplyInfo.companyId == item.id}"> selected="selected" </c:if>>${item.companyName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</span>--%>
                        <%--<span style="position:absolute;margin-top:1px;margin-left:1px;padding-left:6px;width:344px;height:28px;border-radius:5px;">--%>
                            <%--<input type="text" name="companyName" id="qlc_zdz" class="companyName" value="${billingApplyInfo.companyName}" style="width:80%;height:24px;border:0pt;border-radius:5px;outline:0"  required="required">--%>
                        <%--</span>--%>
                    <%--</td>--%>
                <%--</tr>--%>

                <tr>
                    <th width="20%" class="active">保单号</th>
                    <td  width="80%">
                        <input type="text" id="policyNo" name="policyNo" value="${billingApplyInfo.policyNo}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">报案号</th>
                    <td  width="80%">
                        <input type="text" id="reportNo" name="reportNo" value="${billingApplyInfo.reportNo}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">备注</th>
                    <td  width="80%">
                        <input type="textarea" id="remark" name="remark" value="${billingApplyInfo.remark}" style="width: 400px;" class="form-control">
                    </td>
                </tr>

                <%--<tr>--%>
                    <%--<th width="20%" class="active">发票单号</th>--%>
                    <%--<td  width="80%">--%>
                        <%--<input type="text" id="billingCodes" name="billingCodes" style="width: 400px;" class="form-control">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">发票凭证</th>--%>
                    <%--<td>--%>
                        <%--<input required id="billingAdPic" type="hidden" name="billingImgs">--%>
                        <%--<img id="billingInfImg" width="80" height="80">--%>
                        <%--<input id="billingFileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${billingApplyInfo.caseNo}">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th width="20%" class="active">材料凭证</th>
                    <td>
                        <%--<input required id="materialAdPic" type="hidden" name="materialImgs">--%>
                        <%--<img id="materialInfImg" width="80" height="80">--%>
                        <%--<input id="materialFileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${billingApplyInfo.caseNo}">--%>
                        <c:forEach items="${apiRsp3.results}" var="item">
                            <c:if test="${item.fileType == 'gif' || item.fileType == 'jpg' ||item.fileType == 'jpeg' ||item.fileType == 'bmp' ||item.fileType == 'png' }">
                                <img src="${item.materialImgs}" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'txt'}">
                                <img src="../img/txt.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'doc' || item.fileType == 'docx'}">
                                <img src="../img/word.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'xls' || item.fileType == 'xlsx'}">
                                <img src="../img/excel.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'pdf'}">
                                <img src="../img/pdf.jpg" width="75;" height="75;" class="picToBig">
                            </c:if>
                        </c:forEach>

                        <div>
                            <input type="hidden" id="materialImgs" name="materialImgs" value="" />
                            <div id="materialImgsDiv" style="display: none"></div>
                            <div id="deleteDiv" style="display: none">
                                <input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>
                            </div>
                        </div>
                        <div>
                            <input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        $('.btn-success').attr('disabled',true)
        event.preventDefault();
        // if (!demo1.getValue('value').length){
        //     alert('【绩效所属人员】必须！')
        //     $('.btn-success').removeAttr('disabled')
        // }else{
        //     ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        // }
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
    });

    $("#div_city").on("click",function(event,id){
        selectArea1(id);
    });

    $("#div_district").on("click",function(event,did){
        selectAreaCity1(did)
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

    function qlcTrainS(idName) {
        var arrValue=document.getElementById(idName).options[document.getElementById(idName).selectedIndex].value;
        $("#"+idName+"").parent('span').next('span').children('input.companyName').val(arrValue)
    }

    //材料凭证
    var value = "";
    var id = 0;
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            id = id + 1;
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            var vSrc = "";
            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
//                $("#materialImgsDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
            var v = 'http://ddrapi.shlefan.com/sftp/files/' + pathImg;
            if(ext == "txt"){
                vSrc = "../img/txt.png";
            }else if(ext == "docx" || ext == "doc"){
                vSrc = "../img/word.png";
            }else if(ext == "xls" || ext == "xlsx"){
                vSrc = "../img/excel.png";
            }else if(ext == "pdf"){
                vSrc = "../img/pdf.jpg";
            }else{
                vSrc = "http://ddrapi.shlefan.com/sftp/files/" + pathImg;
            }
            $("#materialImgsDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");
            value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';

            $("#materialImgs").val(value);
            $("#materialImgsDiv").show();
            $("#deleteDiv").show();

        }
    });


    var divs = [];
    function del(){
        var chks = document.getElementsByName('chknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                divs.push($("#div_" + chk.id));
            }
        }
        for(var i = 0 ; i < divs.length ;i++){
            divs[i].remove();
        }
        $("#materialImgs").val(value);
    }

    var selectRecipient = function(){
        openDialog({
            frame:true,
            title:"选择收件人",
            height:500,
            width:800,
            url:"${ctx}/billingApply/selectRecipient?type=billing"
        });
    }

    var selectCompanyName = function(){
        openDialog({
            frame:true,
            title:"选择开票对象",
            height:500,
            width:800,
            url:"${ctx}/billingApply/selectCompanyName?type=billing"
        });
    }

    function selectOrgInfo(){
        var productTypeId = $("#productType").val();
        if(!productTypeId){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"productTypeId":productTypeId,btnCode:1000},function(v,e,p){
            $("#orgId option").remove();
            $("#orgId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#orgId").append("<option value='" + val.id + "'>" + val.orgName + "</option>");
            }
        });
    }
    function selectCorporation(){
        var corporationId = $("#businessType").val();
        if(!corporationId){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"corporationId":corporationId,btnCode:5800},function(v,e,p){
            var val=e.data.results.taxRate;

            $("#taxRate").val(val);
        });
    }
    function selectBillingEnum(){
        var organId = $("#staffOrgId").val();
        if(!organId){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"organId":organId,btnCode:1100},function(v,e,p){
            $("#billingEnum option").remove();
            $("#billingEnum").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#billingEnum").append("<option value='" + val.productEnumId + "'>" + val.productEnumName + "</option>");
            }
        });
    }

    function selectBillingItem(){
        /* var businessTypeId = $("#businessType").val();
         if(!businessTypeId){
             return;
         }*/
        var billingEnum = $("#billingEnum").val();
        if(!billingEnum){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"billingEnum":billingEnum,btnCode:1200},function(v,e,p){
            $("#billingItem option").remove();
            $("#billingItem").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#billingItem").append("<option value='" + val.billingItemId + "'>" + val.billingItemName + "</option>");
            }
            toBuildCaseNo();
        });

    }
    /*function selectBillingEnum(){
        var businessTypeId = $("#businessType").val();
        if(!businessTypeId){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"businessTypeId":businessTypeId,btnCode:1100},function(v,e,p){
            $("#billingEnum option").remove();
            $("#billingEnum").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#billingEnum").append("<option value='" + val.billingEnumId + "'>" + val.billingEnumName + "</option>");
            }
        });
    }

    function selectBillingItem(){
        var businessTypeId = $("#businessType").val();
        if(!businessTypeId){
            return;
        }
        var billingEnum = $("#billingEnum").val();
        if(!billingEnum){
            return;
        }
        ajaxSubmit("${ctx}/billingApply/selectInfoByRelationId",{"businessTypeId":businessTypeId,"billingEnum":billingEnum,btnCode:1200},function(v,e,p){
            $("#billingItem option").remove();
            $("#billingItem").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#billingItem").append("<option value='" + val.billingItemId + "'>" + val.billingItemName + "</option>");
            }
        });

    }*/

    /*
     * 根据选择的类目 生成案件编号
     */
    var caseNo="";
    function toBuildCaseNo(){
        var objS = document.getElementById("billingEnum");
        var billingEnum = objS.options[objS.selectedIndex].value;
        $.ajax({
            url:'${ctx}/billingApply/toBuildCaseNo?billingEnum='+billingEnum,
            type:"Get",
            success:function(res,param){
                $("#caseNo").val(param.data.results);
                caseNo=param.data.results;
            }
        });
    }

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    layui.use(['jquery','xmSelect'], function () {
        var $ = layui.jquery,
            xmSelect = layui.xmSelect;

         demo1 = xmSelect.render({
            el: '#superiorManager2',
            theme: {
                color: '#3BA9FF',
            },
            on: function (data) {
                var arr = data.arr;
                var value = arr.length ? arr[0].value : ''
                var name = arr.length ? arr[0].name : ''
                if (data.change.length) {
                    $('#meritUserId').val(value)
                    $('#meritUserName').val(name)
                }
            },
            radio: true,
            filterable: true,
            filterDone: function (val, list) {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html += '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: []
        })

        var personnelInfosJson = $("#staffPersonnelInfoDtoList").val();
        personnelInfosJson = JSON.parse(personnelInfosJson);
        var addJson = [{
            userId: -1,
            realName: '姜敏'
        },{
            userId: -2,
            realName: '张文娟'
        },{
            userId: -3,
            realName: '周印忠'
        },{
            userId: -4,
            realName: '刘志博'
        },{
            userId: -5,
            realName: '刘必强'
        },{
            userId: -6,
            realName: '帅华斌'
        },{
            userId: -7,
            realName: '任国仙'
        },{
            userId: -8,
            realName: '柏松'
        },{
            userId: -9,
            realName: '姚嘉诚'
        }]
        personnelInfosJson = personnelInfosJson.concat(addJson)
        filterJson(demo1, personnelInfosJson, 'userId', 'realName', false, false)
        demo1.setValue([$("#meritUserId").val()])
        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                }
                if (selected) {
                    Object.assign(param, {selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }
    });
</script>
</body>
</html>