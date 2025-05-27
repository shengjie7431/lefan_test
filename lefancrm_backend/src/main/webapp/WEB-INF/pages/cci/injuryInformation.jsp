<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>伤情信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="btn-group">
        <button id="test" onclick="edit()" type="button" class="btn btn-default">编辑</button>
    </div>
    <div class="form-group">
        <table class="table">
            <tbody>
            <tr>
                <th width="20%" class="active">伤情名称</th>
                <td width="80%">
                    ${apiRsp.results.injuryName}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">已用医疗费</th>
                <td width="80%">
                    ${apiRsp.results.usedMedicalFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">欠的医疗费</th>
                <td width="80%">
                    ${apiRsp.results.oweMedicalFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">还需医疗费</th>
                <td width="80%">
                    ${apiRsp.results.neededMedicalFee}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">筹资方式</th>
                <td width="80%">
                    <c:if test="${apiRsp.results.financingType==0}">自费</c:if>
                    <c:if test="${apiRsp.results.financingType==1}">保司</c:if>
                    <c:if test="${apiRsp.results.financingType==2}">道救救助基金垫付</c:if>
                    <c:if test="${apiRsp.results.financingType==3}">其他</c:if>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">就诊医院</th>
                <td width="80%">
                    ${apiRsp.results.visHospital}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否住院</th>
                <td width="80%">
                    ${apiRsp.results.isInhospital==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否手术</th>
                <td width="80%">
                    ${apiRsp.results.isOperation==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">科室</th>
                <td width="80%">
                    ${apiRsp.results.hospitalDepartments}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">床位号</th>
                <td width="80%">
                    ${apiRsp.results.bedNumber}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">住院号</th>
                <td width="80%">
                    ${apiRsp.results.hospitalNumber}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">主治医生</th>
                <td width="80%">
                    ${apiRsp.results.doctor}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">医生联系电话</th>
                <td width="80%">
                    ${apiRsp.results.doctorTel}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">床位护士</th>
                <td width="80%">
                    ${apiRsp.results.nurse}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">护士联系电话</th>
                <td width="80%">
                    ${apiRsp.results.nurseTel}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">其他信息</th>
                <td width="80%">
                    ${apiRsp.results.otherDesc}
                </td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            <tr>
                <th width="20%"></th>
                <td width="80%"></td>
            </tr>
            </tbody>
        </table>
    </div>


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

   function edit(a){
       openDialog({
           frame:true,
           title:"编辑伤情信息",
           height:400,
           width:1000,
           url:"${ctx}/cci/injuryInformationEdit?id=${id}&injuryName=${apiRsp.results.injuryName}&usedMedicalFee=${apiRsp.results.usedMedicalFee}&oweMedicalFee=${apiRsp.results.oweMedicalFee}&neededMedicalFee=${apiRsp.results.neededMedicalFee}&financingType=${apiRsp.results.financingType}&visHospital=${apiRsp.results.visHospital}&isInhospital=${apiRsp.results.isInhospital}&isOperation=${apiRsp.results.isOperation}&hospitalDepartments=${apiRsp.results.hospitalDepartments}&bedNumber=${apiRsp.results.bedNumber}&hospitalNumber=${apiRsp.results.hospitalNumber}&doctor=${apiRsp.results.doctor}&doctorTel=${apiRsp.results.doctorTel}&nurse=${apiRsp.results.nurse}&nurseTel=${apiRsp.results.nurseTel}&otherDesc=${apiRsp.results.otherDesc}"
       });
   }
</script>
</body>
</html>
