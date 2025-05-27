<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>事故信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="btn-group">
        <button id="test" onclick="edit('')" type="button" class="btn btn-default">编辑</button>
    </div>
    <div class="form-group">
        <table class="table">
            <tbody>
            <tr>
                <th width="20%" class="active">事故时间</th>
                <td width="80%">
                    <fmt:formatDate value="${apiRsp.results.accidentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">事故地点</th>
                <td width="80%">
                    ${apiRsp.results.province}${apiRsp.results.city}${apiRsp.results.district}${apiRsp.results.accidentAddress}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">责任认定</th>
                <td width="80%">
                    <c:if test="${apiRsp.results.accidentCognizance==1}">全部责任</c:if>
                    <c:if test="${apiRsp.results.accidentCognizance==2}">主要责任</c:if>
                    <c:if test="${apiRsp.results.accidentCognizance==3}">同等责任</c:if>
                    <c:if test="${apiRsp.results.accidentCognizance==4}">次要责任</c:if>
                    <c:if test="${apiRsp.results.accidentCognizance==5}">无责任</c:if>
                    <c:if test="${apiRsp.results.accidentCognizance==6}">责任无法认定</c:if>
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交警大队</th>
                <td width="80%">
                    ${apiRsp.results.policeTeam}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">处理交警</th>
                <td width="80%">
                    ${apiRsp.results.policeMan}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交警电话</th>
                <td width="80%">
                    ${apiRsp.results.policeTel}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">交强险承保公司</th>
                <td width="80%">
                    ${apiRsp.results.insCompulsory}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">商业险承保公司</th>
                <td width="80%">
                    ${apiRsp.results.insCommercial}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">三责险限额</th>
                <td width="80%">
                    ${apiRsp.results.threeQuota}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否购买不计免赔</th>
                <td width="80%">
                    ${apiRsp.results.isDeductibles==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">驾驶员姓名</th>
                <td width="80%">
                    ${apiRsp.results.driverName}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">联系电话</th>
                <td width="80%">
                    ${apiRsp.results.driverTel}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否多方事故</th>
                <td width="80%">
                    ${apiRsp.results.isMulti==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">是否存在免责情形</th>
                <td width="80%">
                    ${apiRsp.results.isRelief==0?'否':'是'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">其他</th>
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
           title:"编辑事故信息",
           height:400,
           width:1000,
           url:"${ctx}/cci/accidentInformationEdit?parentId=0&id=${id}&accidentDate=${apiRsp.results.accidentDate}&accidentAddress=${apiRsp.results.accidentAddress}&accidentCognizance=${apiRsp.results.accidentCognizance}&policeTeam=${apiRsp.results.policeTeam}&policeMan=${apiRsp.results.policeMan}&policeTel=${apiRsp.results.policeTel}&insCompulsory=${apiRsp.results.insCompulsory}&insCommercial=${apiRsp.results.insCommercial}&threeQuota=${apiRsp.results.threeQuota}&isDeductibles=${apiRsp.results.isDeductibles}&driverName=${apiRsp.results.driverName}&driverTel=${apiRsp.results.driverTel}&isMulti=${apiRsp.results.isMulti}&isRelief=${apiRsp.results.isRelief}&otherDesc=${apiRsp.results.otherDesc}"
       });
   }
</script>
</body>
</html>
