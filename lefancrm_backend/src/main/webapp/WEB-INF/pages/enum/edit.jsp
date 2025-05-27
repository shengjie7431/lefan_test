<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>编辑枚举信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/common/enum/save" method="post">
	<input type="hidden" name="id" value="${enumDto.id}">
	<input type="hidden" name="parentId" value="${parentId}">
	<div class="form-group">
		<table class="table">
	        <tbody>
	          <tr>
	            <th width="20%" class="active"><strong class="necessary">*</strong>枚举Code</th>
	            <td width="80%"><input type="text" class="form-control" name="enumCode" value="${enumDto.enumCode}" placeholder="请输入Code" required="required"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary"> </strong>枚举名称</th>
	            <td><input type="text" class="form-control" id="enumName" name="enumName" value="${enumDto.enumName}"  ></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary"> </strong>枚举说明</th>
	            <td><input type="text" class="form-control" id="enumText" name="enumText" value="${enumDto.enumText}" ></td>
	          </tr>
	        </tbody>
	      </table>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>		
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">


$(function(){
	$("#editForm").bind('submit', function(event) {
		ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
		event.preventDefault();
	});	
	
	var isShow_val = '${menuDto.isShow}';
	if(isShow_val!=''){
		$("#isShow").val(isShow_val);
	}
});


function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('枚举保存成功');
	}else{
		alert(apiRsp.msg);
	}
	reloadParent();
}

</script>
</body>
</html>