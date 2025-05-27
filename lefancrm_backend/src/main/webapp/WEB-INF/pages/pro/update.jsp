<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改商品分类</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/proCategory/update" method="post">
        <input type="hidden" name="id" value="${id}">
	<div class="form-group">
		<table class="table">
	        <tbody>
	          <tr>
	            <th width="20%" class="active"><strong class="necessary">*</strong>分类标题</th>
	            <td width="80%"><input type="text" class="form-control" name="catName" value="${catName}" placeholder="请输入分类标题" required="required"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>分类描述</th>
	            <td><input type="text" class="form-control" id="catDesc" name="catDesc" value="${catDesc}" placeholder="请输入分类描述" ></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>分类排序</th>
	            <td><input type="text" class="form-control" id="catSort" name="catSort" value="${catSort}" placeholder="请输入分类排序" ></td>
	          </tr>
              <tr>
	            <th class="active"><strong class="necessary">*</strong>分类类别</th>
	            <td>
	            <select class="form-control"  name="catType" id="catType" required="required">
                    <option value="1">会员卡</option>
                    <option value="2">实物商品</option>
                    <option value="3">会员卡实物</option>
				</select>
	            </td>
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
    var catType = '${catType}';
    if(catType!=''){
        $("#catType").val(catType);
    }

});


function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('商品分类修改成功');
	}else{
		alert(apiRsp.msg);
	}
	reloadParent();
}

</script>
</body>
</html>