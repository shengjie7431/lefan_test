<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>伤者信息</title>
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
                <th width="20%" class="active">姓名</th>
                <td width="80%">
                    ${apiRsp.results.userName}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">性别</th>
                <td width="80%">
                    ${apiRsp.results.sex==0?'男':'女'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">年龄</th>
                <td width="80%">
                    ${apiRsp.results.age}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">客户电话</th>
                <td width="80%">
                    ${apiRsp.results.userPhone}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">家庭住址</th>
                <td width="80%">${apiRsp.results.province}${apiRsp.results.city}${apiRsp.results.district}${apiRsp.results.familyAddress}</td>
            </tr>
            <tr>
                <th width="20%" class="active">户籍性质</th>
                <td width="80%">
                    ${apiRsp.results.households==0?'农村':'城镇'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">工作单位</th>
                <td width="80%">
                    ${apiRsp.results.jobCompany}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">月收入</th>
                <td width="80%">
                    ${apiRsp.results.income}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">有无被抚养人</th>
                <td width="80%">
                    ${apiRsp.results.dependants==0?'无':'有'}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">联系人</th>
                <td width="80%">
                    ${apiRsp.results.linkUser}
                </td>
            </tr>
            <tr>
                <th width="20%" class="active">联系人电话</th>
                <td width="80%">
                    ${apiRsp.results.linkTel}
                </td>
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

   function edit(){
       openDialog({
           frame:true,
           title:"编辑伤者信息",
           height:400,
           width:1000,
           url:"${ctx}/cci/injuredInformationEdit?parentId=0&id=${id}&userName=${apiRsp.results.userName}&sex=${apiRsp.results.sex}&age=${apiRsp.results.age}&userPhone=${apiRsp.results.userPhone}&familyAddress=${apiRsp.results.familyAddress}&households=${apiRsp.results.households}&jobCompany=${apiRsp.results.jobCompany}&income=${apiRsp.results.income}&dependants=${apiRsp.results.dependants}&linkUser=${apiRsp.results.linkUser}&linkTel=${apiRsp.results.linkTel}"
       });
   }
</script>
</body>
</html>
