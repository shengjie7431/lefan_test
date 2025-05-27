<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">

    <script>

    </script>
    <style>
        .Presentation {
            width: 100%;
            padding: 20px;
        }

        textarea {
            width: 80%;
            height: 80px;
            border: none;
            resize: none;
        }

        table {
            border-collapse: collapse;
            margin-top: 10px;
            width: 100%;
        }

        #table-nei {
            margin: auto;
        }

        .title {
            color: #555;
            text-align: center;
        }

        .back-color-1 {
            background: #d9edf7;
        }

        .back-color-2 {
            background: #f0f3f8;
        }

        .border-1 {
            border: 1px solid #a9e1f4;
        }

        .border-2 {
            border: 1px solid #e3e6eb;
        }

        .padding-2 {
            padding-left: 10px;
        }

        .padding-3 {
            padding-left: 20px;
        }

        .padding-1 {
            padding: 10px 20px;
        }

        .text-color-1 {
            color: #00a0e9;
        }

        .text-align-1 {
            text-align: center;
        }

        .text-align-2 {
            text-align: right;
        }

        .table-title {
            font-weight: bold;
        }

        .row-10 {
            width: 10%;
        }

        .row-15 {
            width: 15%;
        }

        .row-20 {
            width: 20%;
        }

        .row-26 {
            width: 26.66%;
        }

        .row-25 {
            width: 25%;
        }

        .row-12 {
            width: 12.5%;
        }

        .row-30 {
            width: 30%;
        }

        .height-1 {
            height: 55px;
        }

        .input-1 {
            width: 100%;
            height: 100%;
            border: none;
        }

        .input-2 {
            width: 80px;
            border: none;
            border-bottom: 1px solid #555;
            text-align: center;
        }

        .input-3 {
            width: 280px;
            border: none;
            border-bottom: 1px solid #555;
        }

        .select-1 {
            border: none;
        }
    </style>
    <style>
        .items {
            width: 1100px;
            padding: 0 40px;
            background-color: #f5f7fa;
            display: flex;
        }

        .item {
            display: flex;
            align-items: center;
            width: 255px;
            height: 50px;
            line-height: 50px;
        }

        .item .item-index {
            width: 20px;
            height: 20px;
            line-height: 16px;
            border-radius: 50%;
            border: 2px solid #999;
            color: #999;
            font-size: 13px;
            text-align: center;
        }

        .item .item-title {
            padding-left: 20px;
            padding-right: 40px;
            color: #999;
            font-size: 15px;
        }
        .item .item-icon{
            width: 8px;
            height: 8px;
            border-right: 1px solid #bbb;
            border-bottom: 1px solid #bbb;
            transform: rotate(-45deg)
        }
        div.active .item-index{
            color: #333;
            border-color: #333;
        }
        div.active .item-title{
            color: #333;
        }
    </style>
</head>
<body>
<div class="Presentation">
    <form id="editForm" role="form" action="${ctx}/case/report/saveCloseReport" method="post">
    <input type="hidden" name="id" value="${apiRsp.caseCloseReport.id}">
    <input type="hidden" name="caseId" value="${apiRsp.caseCloseReport.caseId}">
    <input type="hidden" name="caseNo" value="${apiRsp.caseCloseReport.caseNo}">
    <input type="hidden" name="noNext" id="noNext" value="">
    <input type="hidden" name="stepCode" id="stepCode" value="${stepCode}">
        <div class="items">
            <div class="item active" id="item1">
                <div class="item-index">1</div>
                <div class="item-title">基本情况</div>
                <div class="item-icon"></div>
            </div>
            <div class="item" id="item2">
                <div class="item-index">2</div>
                <div class="item-title">结案赔偿情况</div>
                <div class="item-icon"></div>
            </div>
            <div class="item" id="item3">
                <div class="item-index">3</div>
                <div class="item-title">结案费用汇总情况</div>
                <div class="item-icon"></div>
            </div>
            <div class="item" id="item4">
                <div class="item-index">4</div>
                <div class="item-title">提交审核</div>
            </div>
        </div>
    <div id="div_1" class="form-group">
        <table class="table">
            <tbody>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-20 padding-2">结案方式</td>
                <td class="border-2 padding-2">
                    <input type="radio" name="closedType" value="1"  <c:if test="${apiRsp.caseCloseReport.closedType == 1}">checked = "checked"</c:if>/>非诉讼调解
                    <input type="radio" name="closedType" value="2"  <c:if test="${apiRsp.caseCloseReport.closedType == 2}">checked = "checked"</c:if>/>诉讼
                </td>
                <td class="back-color-2 border-2 row-20 padding-2">事故赔偿比例</td>
                <td class="border-2 padding-2">
                    <input type="number" step="0" min="0" max="100" class="input-2" name="compensateRate" onblur="compensateRageBlur()" value="${apiRsp.caseCloseReport.compensateRate}" />%
                </td>
            </tr>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-20 padding-2">结案阶段</td>
                <td class="border-2 padding-2">
                    <input type="radio" name="stageType" value="1"  <c:if test="${apiRsp.caseCloseReport.stageType == 1}">checked = "checked"</c:if>/>一审
                    <input type="radio" name="stageType" value="2"  <c:if test="${apiRsp.caseCloseReport.stageType == 2}">checked = "checked"</c:if>/>二审
                </td>
                <td class="back-color-2 border-2 row-20 padding-2"></td>
                <td class="border-2 padding-2">
                </td>
            </tr>
            <tr class="height-1">
                <td class="back-color-2 border-2row-20 padding-2">索赔员</td>
                <td class="border-2 padding-2">
                    <input type="text" class="input-1"  name="claimantName" value="${apiRsp.caseCloseReport.claimantName}"/>
                </td>
                <td class="back-color-2 border-2 row-20 padding-2">索赔开始时间</td>
                <td class="border-2 padding-2">

                    <input type="text" class="input-1" name="claimBeginTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.claimBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                </td>
            </tr>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-20 padding-2">诉讼专员</td>
                <td class="border-2 padding-2">
                    <input type="text" class="input-1"  name="legalUser" value="${apiRsp.caseCloseReport.legalUser}"/>
                </td>
                <td class="back-color-2 border-2 row-20 padding-2">诉讼开始时间</td>
                <td class="border-2 padding-2">
                    <input type="text" class="input-1" name="legalBeginTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.legalBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                </td>
            </tr>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-20 padding-2">代理律师</td>
                <td class="border-2 padding-2">
                    <input type="text" class="input-1"  name="agentLawyer" value="${apiRsp.caseCloseReport.agentLawyer}"/>
                </td>
                <td class="back-color-2 border-2 row-20 padding-2">律师费</td>
                <td class="border-2 padding-2">
                    <input type="text" class="input-1"  name="agentLawyerMoney" value="${apiRsp.caseCloseReport.agentLawyerMoney}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="div_btn_1" class="modal-footer">
        <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
        <button type="submit" onclick="return toValid(2,2)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并下一步</button>
    </div>


    <div id="div_2" style="display: none" class="form-group">
        <table>
            <tbody>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-20 text-align-1">损失项目</td>
                <td class="back-color-2 border-2 row-20 text-align-1">确认损失金额</td>
                <c:if test="${op == 'view' || op == 'audit'}">
                    <td class="back-color-2 border-2 row-20 text-align-1">审核金额</td>
                </c:if>
                <td class="back-color-2 border-2 row-15 text-align-1">交强险</td>
                <td class="back-color-2 border-2 row-15 text-align-1">商业险</td>
                <td class="back-color-2 border-2 row-15 text-align-1">肇事方</td>
            </tr>
            <c:forEach items="${dtos}" var="item">
                <tr class="height-1">
                    <td class="back-color-2 border-2 row-20 padding-2">${item.objectName}</td>
                    <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额" <c:if test="${op == 'edit'}">onblur="blurUpdCloseObjReport(${apiRsp.caseCloseReport.id == null ? 'null' : apiRsp.caseCloseReport.id},${item.id},1,this,${apiRsp.caseCloseReport.caseId},'${apiRsp.caseCloseReport.caseNo}')"</c:if> class="input-1" value="${item.opinionMoney}" /></td>
                    <c:if test="${op == 'audit'}">
                        <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额" onblur="blurUpdCloseObjReport(${apiRsp.caseCloseReport.id == null ? 'null' : apiRsp.caseCloseReport.id},${item.id},2,this,${apiRsp.caseCloseReport.caseId},'${apiRsp.caseCloseReport.caseNo}')" class="input-1" value="${item.insOmpanyMoney}"/></td>
                    </c:if>
                    <c:if test="${op == 'view'}">
                        <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额"  class="input-1" value="${item.insOmpanyMoney}"/></td>
                    </c:if>
                    <td class="border-2 row-15 padding-2">${item.commerMoney}</td>
                    <td class="border-2 row-15 padding-2">${item.compulMoney}</td>
                    <td class="border-2 row-15 padding-2">${item.driverMoney}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="div_btn_2" style="display: none" class="modal-footer">
        <button type="submit" onclick="return toValid(2,1)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>上一步</button>
        <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
        <button type="submit" onclick="return toValid(2,3)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并下一步</button>

    </div>


    <div id="div_3" style="display: none" class="form-group">
        <table>
            <tbody>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-25 padding-2">约定收费方式</td>
                <td colspan="3" class="border-2 row-26 padding-2">
                    <input type="radio" onchange="onblurCharge()" name="chargeType" value="1"  <c:if test="${apiRsp.caseCloseReport.chargeType == 1}">checked = "checked"</c:if>/>固定收费金额<input type="number" step="0.01" class="input-2" id="fixedChargeMoney" name="fixedChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.fixedChargeMoney}">元；<br>
                    <input type="radio" onchange="onblurCharge()" name="chargeType" value="2"  <c:if test="${apiRsp.caseCloseReport.chargeType == 2}">checked = "checked"</c:if>/>比例收费，比例基数<input type="number" step="0.01" class="input-2" id="proportionChargeMoney" name="proportionChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.proportionChargeMoney}">元，收费比例<input type="number" step="0" min="0" max="100" class="input-2" id="proportionChargeRate" name="proportionChargeRate" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.proportionChargeRate}">%<br>
                    <input type="radio" onchange="onblurCharge()" name="chargeType" value="3"  <c:if test="${apiRsp.caseCloseReport.chargeType == 3}">checked = "checked"</c:if>/>固定收费金额<input type="number" step="0.01" class="input-2" id="blendFixedChargeMoney" name="blendFixedChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendFixedChargeMoney}">元；比例收费，比例基数<input type="number" step="0.01" class="input-2" id="blendProportionChargeMoney" name="blendProportionChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendProportionChargeMoney}">元，收费比例<input type="number" step="0" min="0" max="100" class="input-2" id="blendProportionChargeRate" name="blendProportionChargeRate" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendProportionChargeRate}">%
                </td>
            </tr>
            <tr class="height-1">
                <td class="back-color-2 border-2 row-25 padding-2">应收取服务费总金额</td>
                <td class="border-2 row-26 padding-2">
                    <input type="number" step="0.01" class="input-2" onblur="calcAmount()" id="shouldTotalMoney" name="shouldTotalMoney" value="${apiRsp.caseCloseReport.shouldTotalMoney}"/>元
                </td>
                <td class="back-color-2 border-2 row-25 padding-2">实收服务费</td>
                <td class="border-2 row-26 padding-2">
                    <input type="number" step="0.01" class="input-2" id="actualTotalMoney" name="actualTotalMoney" value="${apiRsp.caseCloseReport.actualTotalMoney}">元
                </td>
            </tr>
            <tr style="display: none">
                <td class="back-color-2 border-2 row-25 padding-2">贷款金额</td>
                <td class="border-2 row-26 padding-2">
                    <input type="number" step="0.01" required="required" min="0" class="input-2" name="loanMoney" value="0.00">元
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="div_btn_3" style="display: none" class="modal-footer">
        <button type="submit" onclick="return toValid(2,2)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>上一步</button>
        <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
        <button type="submit" onclick="return toValid(3,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并提交审核</button>
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
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>


<script type="text/javascript">
    $(function () {
        $('.singleSelect').select2();
    });

    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });

    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp){
            if(apiRsp.results.noNext == 1 || apiRsp.results.noNext == 3){
                reloadParent();
            }else if(apiRsp.results.noNext == 2){
                var url = "${ctx}/case/report/editCloseReport?caseId="+'${caseId}'+"&caseNo="+'${caseNo}'+"&op="+'${op}'+"&type=44"+"&stepCode="+apiRsp.results.stepCode+"&noNext="+apiRsp.results.noNext;
                location.replace(url);
            }
        }else{
            alert(apiRsp.msg);return;
        }
    }

    var stepCode=$("#stepCode").val();
    if(stepCode == 1){
        $("#div_1").show();
        $("#div_btn_1").show();
        $("#div_2").hide();
        $("#div_btn_2").hide();
        $("#div_3").hide();
        $("#div_btn_3").hide();

        $("#item1").attr("class", "item active");
        $("#item2").attr("class", "item");
        $("#item3").attr("class", "item");
        $("#item4").attr("class", "item");
    }else if(stepCode == 2) {
        $("#div_2").show();
        $("#div_btn_2").show();
        $("#div_1").hide();
        $("#div_btn_1").hide();
        $("#div_3").hide();
        $("#div_btn_3").hide();

        $("#item2").attr("class", "item active");
        $("#item1").attr("class", "item");
        $("#item3").attr("class", "item");
        $("#item4").attr("class", "item");
    }else if(stepCode == 3) {
        $("#div_3").show();
        $("#div_btn_3").show();
        $("#div_1").hide();
        $("#div_btn_1").hide();
        $("#div_2").hide();
        $("#div_btn_2").hide();

        $("#item3").attr("class", "item active");
        $("#item1").attr("class", "item");
        $("#item2").attr("class", "item");
        $("#item4").attr("class", "item");
    }

    function toValid(noNext,stepCode){
        $("#noNext").val(noNext);//关闭页面、进入下一个页面
        $("#stepCode").val(stepCode);//回调函数返回 （进入第stepCode页面）
        return true;
    }

    function onBack(){
        return true;
    }
    function refreshSave(){
        location.reload();
    }
    function blurUpdCloseObjReport(reportId,id,type,obj,caseId,caseNo){
        if(${op == 'view'}){
            return;
        }
        ajaxSubmit("${ctx}/case/report/updCaseCloseObjReport?reportId="+reportId+"&id=" + id + "&type=" + type + "&value=" + obj.value+"&caseId="+caseId+"&caseNo="+caseNo,null,function(v,e,p){

        })
    }

    function onblurCharge(op){
        if(${op == 'view'}){
            return;
        }
        var a = $("input[name='chargeType']:checked").val();
        if(a == 1){
            if(!$('#fixedChargeMoney').val()){
                $('#fixedChargeMoney').val(0);
            }
            var fixedChargeMoney = $('#fixedChargeMoney').val();
            $('#shouldTotalMoney').val(fixedChargeMoney);
        }else if(a == 2){
            if(!$('#proportionChargeMoney').val()){
                $('#proportionChargeMoney').val(0);
            }
            var proportionChargeMoney = $('#proportionChargeMoney').val();
            if(!$('#proportionChargeRate').val()){
                $('#proportionChargeRate').val(0);
            }
            var proportionChargeRate = $('#proportionChargeRate').val();
            var value = (proportionChargeMoney * proportionChargeRate / 100).toFixed(2);
            $('#shouldTotalMoney').val(value);
        }else if(a == 3){
            if(!$('#blendFixedChargeMoney').val()){
                $('#blendFixedChargeMoney').val(0);
            }
            var blendFixedChargeMoney = $('#blendFixedChargeMoney').val();
            if(!$('#blendProportionChargeMoney').val()){
                $('#blendProportionChargeMoney').val(0);
            }
            var blendProportionChargeMoney = $('#blendProportionChargeMoney').val();
            if(!$('#blendProportionChargeRate').val()){
                $('#blendProportionChargeRate').val(0);
            }
            var blendProportionChargeRate = $('#blendProportionChargeRate').val();
            var value = (Number(blendFixedChargeMoney) + Number(blendProportionChargeMoney * blendProportionChargeRate /100)).toFixed(2);
            $('#shouldTotalMoney').val(value);
        }
        calcAmount();
    }

    function onblurByReportMoney(){
        if(${op == 'view'}){
            return;
        }
        var lawyerMoney = Number($('#lawyerMoney').val() == false ? 0 : $('#lawyerMoney').val());
        var appraisalMoney = Number($('#appraisalMoney').val() == false ? 0 : $('#appraisalMoney').val());
        var litigateMoney = Number($('#litigateMoney').val() == false ? 0 : $('#litigateMoney').val());
        var claimMoney = Number($('#claimMoney').val() == false ? 0 : $('#claimMoney').val());
        var otherMoney = Number($('#otherMoney').val() == false ? 0 : $('#otherMoney').val());
        $('#closedReportDeMoney').val((lawyerMoney + appraisalMoney + litigateMoney + claimMoney + otherMoney).toFixed(2));
    }

    /**
     *事故责任比例 光标离开 调用保存方法
     * @param obj
     */
    function compensateRageBlur(){
        if(${op == 'view'}){
            return;
        }
        ajaxFormSubmit($("#editForm"),function(v,e,p){location.reload();},null,null,function(v,e,p){
            location.reload();
        });
    }

    function calcAmount(){
        if(${op == 'view'}){
            return;
        }
        var shouldTotalMoney = $('#shouldTotalMoney').val();
        var preMoney = $('#preMoney').val();
        $('#surplusTotalMoney').val((Number(shouldTotalMoney) - Number(preMoney)).toFixed(2));
    }

    function setValueDeductedLoanMoney(type){
        if(${op == 'view'}){
            return;
        }
        if(type == 1){
            var value = Number($('#preMoney').val() == false ? 0 : $('#preMoney').val());
            $('#deductedLoanMoney').val(value.toFixed(2));
        }else{
            $('#deductedLoanMoney').val(0);
        }
    }
</script>
</body>