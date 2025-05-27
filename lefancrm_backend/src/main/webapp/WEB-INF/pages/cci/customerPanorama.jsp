<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>客户信息列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

<table border="1" style="text-align: center;">
<tr style="background-color: #D9D9D9">
    <td>初访日期</td>
    <td colspan="2">
        ${apiRsp.results.firstVisitTime}
    </td>
    <td>CC人员</td>
    <td colspan="2">
        ${apiRsp.results.cci.ccName}
    </td>
    <td>再访时间</td>
    <td colspan="2">
        ${apiRsp.results.nextFollowTime}
    </td>
</tr>

<tr>
    <td rowspan="5">
        伤者个人信息
    </td>
    <td>
<tr>
    <td>姓名</td>
    <td>${apiRsp.results.cci.userName}</td>
    <td>性别</td>
    <td>${apiRsp.results.cci.sex==0?'男':'女'}</td>
    <td>年龄</td>
    <td>${apiRsp.results.cci.age}</td>
    <td>联系电话</td>
    <td>${apiRsp.results.cci.userPhone}</td>
</tr>
<tr>
    <td>家庭住址</td>
    <td colspan="7">${apiRsp.results.cci.province}${apiRsp.results.cci.city}${apiRsp.results.cci.district}${apiRsp.results.cci.familyAddress}</td>
</tr>
<tr>
    <td>户籍性质</td>
    <td colspan="3">
        ${apiRsp.results.cci.households==0?'农村':'城镇'}
    </td>
    <td>有无被抚养人</td>
    <td colspan="3">
        ${apiRsp.results.cci.dependants==0?'无':'有'}
    </td>
</tr>
<tr>
    <td>工作单位</td>
    <td colspan="3">
        ${apiRsp.results.cci.jobCompany}
    </td>
    <td>月收入</td>
    <td colspan="3">
        ${apiRsp.results.cci.income}
    </td>
</tr>
</td>
</tr>


    <tr>
        <td rowspan="8">
            伤情信息
        </td>
    <td>
    <tr>
        <td>伤情名称</td>
        <td colspan="8">
            ${apiRsp.results.cii.injuryName}
        </td>
    </tr>
    <tr>
        <td>已用医疗费</td>
        <td colspan="2">
            ${apiRsp.results.cii.usedMedicalFee}
        </td>
        <td>已欠医疗费</td>
        <td colspan="2">
            ${apiRsp.results.cii.oweMedicalFee}
        </td>
        <td>还需医疗费</td>
        <td colspan="2">
            ${apiRsp.results.cii.neededMedicalFee}
        </td>
    </tr>
    <tr>
        <td>筹资方式</td>
        <td colspan="8">
            <c:if test="${apiRsp.results.cii.financingType==0}">自费</c:if>
            <c:if test="${apiRsp.results.cii.financingType==1}">保司</c:if>
            <c:if test="${apiRsp.results.cii.financingType==2}">道救救助基金垫付</c:if>
            <c:if test="${apiRsp.results.cii.financingType==3}">其他</c:if>
        </td>
    </tr>
    <tr>
        <td>就诊医院</td>
        <td colspan="2">
            ${apiRsp.results.cii.visHospital}
        </td>
        <td>是否住院</td>
        <td colspan="2">
            ${apiRsp.results.cii.isInhospital==0?'否':'是'}
        </td>
        <td>是否手术</td>
        <td colspan="2">
            ${apiRsp.results.cii.isOperation==0?'否':'是'}
        </td>
    </tr>
    <tr>
        <td>科室</td>
        <td colspan="2">
            ${apiRsp.results.cii.hospitalDepartments}
        </td>
        <td>床位号</td>
        <td colspan="2">
            ${apiRsp.results.cii.bedNumber}
        </td>
        <td>住院号</td>
        <td colspan="2">
            ${apiRsp.results.cii.hospitalNumber}
        </td>
    </tr>
    <tr>
        <td>主治医生</td>
        <td>
            ${apiRsp.results.cii.doctor}
        </td>
        <td>联系电话</td>
        <td>
            ${apiRsp.results.cii.doctorTel}
        </td>
        <td>床位护士</td>
        <td>
            ${apiRsp.results.cii.nurse}
        </td>
        <td>联系电话</td>
        <td>
            ${apiRsp.results.cii.nurseTel}
        </td>
    </tr>
    <tr>
        <td>其他</td>
        <td colspan="8">
            ${apiRsp.results.cii.otherDesc}
        </td>
    </tr>
    </td>
    </tr>
    <tr>
        <td rowspan="8">
            事故信息
        </td>
        <td>
    <tr>
        <td>事故时间</td>
        <td>
            ${apiRsp.results.accidentDate}
        </td>
        <td>事故地点</td>
        <td colspan="2">
            ${apiRsp.results.cai.province}${apiRsp.results.cai.city}${apiRsp.results.cai.district}${apiRsp.results.cai.accidentAddress}
        </td>
        <td>责任认定</td>
        <td colspan="2">
            <c:if test="${apiRsp.results.cai.accidentCognizance==1}">全部责任</c:if>
            <c:if test="${apiRsp.results.cai.accidentCognizance==2}">主要责任</c:if>
            <c:if test="${apiRsp.results.cai.accidentCognizance==3}">同等责任</c:if>
            <c:if test="${apiRsp.results.cai.accidentCognizance==4}">次要责任</c:if>
            <c:if test="${apiRsp.results.cai.accidentCognizance==5}">无责任</c:if>
            <c:if test="${apiRsp.results.cai.accidentCognizance==6}">责任无法认定</c:if>
        </td>
    </tr>
    <tr>
        <td>事故大队</td>
        <td>
            ${apiRsp.results.cai.policeTeam}
        </td>
        <td>处理交警</td>
        <td colspan="2">
            ${apiRsp.results.cai.policeMan}
        </td>
        <td>联系电话</td>
        <td colspan="2">
            ${apiRsp.results.cai.policeTel}
        </td>
    </tr>
    <tr>
        <td>交强险承保公司</td>
        <td colspan="2">
            ${apiRsp.results.cai.insCompulsory}
        </td>
        <td>商业险承保公司</td>
        <td colspan="4">
            ${apiRsp.results.cai.insCommercial}
        </td>
    </tr>
    <tr>
        <td>三责险限额</td>
        <td colspan="2">
            ${apiRsp.results.cai.threeQuota}
        </td>
        <td>是否购买不计免赔</td>
        <td colspan="4">
            ${apiRsp.results.cai.isDeductibles==0?'否':'是'}
        </td>
    </tr>
    <tr>
        <td>驾驶员姓名</td>
        <td colspan="2">
            ${apiRsp.results.cai.driverName}
        </td>
        <td>联系方式</td>
        <td colspan="4">
            ${apiRsp.results.cai.driverTel}
        </td>
    </tr>
    <tr>
        <td>是否多方事故</td>
        <td colspan="2">
            ${apiRsp.results.cai.isMulti==0?'否':'是'}
        </td>
        <td>是否存在免责情形</td>
        <td colspan="4">
            ${apiRsp.results.cai.isRelief==0?'否':'是'}
        </td>
    </tr>
    <tr>
        <td>其他</td>
        <td colspan="8">
            ${apiRsp.results.cai.otherDesc}
        </td>
    </tr>
    </td>
    </tr>
    <tr>
        <td>是否意向</td>
        <td colspan="4">
                ${apiRsp.results.caseInfo.isIntention==0?'否':'是'}
        </td>
        <td>案件进度</td>
        <td colspan="4">
            <c:if test="${apiRsp.results.caseInfo.caseProgress==1}">初访</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseProgress==2}">洽谈中</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseProgress==3}">待签约</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseProgress==4}">已签约</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseProgress==5}">暂时搁置</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseProgress==6}">已放弃</c:if>
        </td>
    </tr>
    <tr>
        <td>案件来源</td>
        <td colspan="8">
            <c:if test="${apiRsp.results.caseInfo.caseSource==0}">医院</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==1}">小程序</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==2}">工作室</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==3}">保司</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==4}">交警</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==5}">陌拜</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==6}">护工</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==7}">转介</c:if>
            <c:if test="${apiRsp.results.caseInfo.caseSource==8}">其他</c:if>
        </td>
    </tr>
    <tr>
        <td>备注</td>
        <td colspan="8"></td>
    </tr>
</table>


</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/
</script>
</body>
</html>
