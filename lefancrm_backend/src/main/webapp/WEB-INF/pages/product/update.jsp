<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>添加商品分类</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="container">
	<form id="editForm" role="form" action="${ctx}/product/update" method="post">
        <input type="hidden" name="id" value="${id}">
	<div class="form-group">
		<table class="table">
	        <tbody>
	          <%--<tr>--%>
	            <%--<th width="20%" class="active"><strong class="necessary">*</strong>商品编号</th>--%>
	            <%--<td width="80%"><input type="text" class="form-control" name="productCode" placeholder="请输入商品编号" value="${productCode}" required="required"></td>--%>
	          <%--</tr>--%>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>商品名称</th>
	            <td><input type="text" class="form-control" id="productName" name="productName" value="${productName}" placeholder="请输入商品名称" required="required"></td>
	          </tr>
	          <tr>
	            <th class="active"><strong class="necessary">*</strong>商品价格</th>
	            <td><input type="number" class="form-control" id="productPrice" name="productPrice" value="${productPrice}" placeholder="请输入商品价格" required="required"></td>
	          </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品图片</th>
                  <%--<td><input type="text" class="form-control" id="productPic" name="productPic" value="${productPic}" placeholder="请输入商品图片" required="required"></td>--%>
                  <td width="80%"><input required id="productPic" type="hidden" value="${productPic}" name="productPic">
                      <img id="infImg" src="${productPic}" width="80" height="80">
                      <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=lfOfficial">
                  </td>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品M站图片</th>
                  <td width="80%"><input required id="productMpic" type="hidden" name="productMpic">
                      <img id="infImgPic" src="${productMpic}" width="80" height="80">
                      <input id="fileuploadPic" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=lfOfficial">
                  </td>
                  <%--<td><input type="text" class="form-control" id="productPic" name="productPic" placeholder="请输入商品图片" required="required"></td>--%>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品详情描述</th>
                  <td></td>
              </tr>
              <tr>
                  <td colspan="2">
                      <textarea name="productDis" id="productDis">${productDis}</textarea>
                  </td>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品优惠价格</th>
                  <td><input type="number" class="form-control" id="disPrice" name="disPrice" value="${disPrice}" placeholder="请输入商品优惠价格" required="required"></td>
              </tr>
              <tr>
	            <th class="active"><strong class="necessary">*</strong>商品分类</th>
	            <td>
                    <select class="form-control"  name="productType1" id="productType1" required="required">
                        <option value="">请选择</option>
                        <c:forEach items="${results}" var="item">
                            <option value="${item.id},${item.catType}">${item.catName}</option>
                        </c:forEach>
                        <input type="hidden" name="catType" id="catType" value="${catType}">
                        <input type="hidden" name="catId" id="catId" value="${catId}">
                        <%--<option value="1">虚拟商品</option>--%>
                        <%--<option value="2">实物商品</option>--%>
                    </select>
	            </td>
	          </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品类型</th>
                  <td>
                      <select class="form-control"  name="productType" id="productType" required="required">
                          <option value="">请选择</option>
                          <option value="1">乐驾卡</option>
                          <option value="2">乐行卡</option>
                          <option value="3">乐骑卡</option>
                          <option value="4">及时雨</option>
                          <option value="5">管家卡</option>
                          <%--<option value="1">虚拟商品</option>--%>
                          <%--<option value="2">实物商品</option>--%>
                      </select>
                  </td>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>计量单位</th>
                  <td><input type="text" class="form-control" id="unit" name="unit" value="${unit}" placeholder="请输入计量单位" required="required"></td>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>商品总库存</th>
                  <td><input type="number" class="form-control" id="stock" name="stock" value="${stock}" placeholder="请输入商品总库存" required="required"></td>
              </tr>
              <tr>
                  <th class="active"><strong class="necessary">*</strong>排序</th>
                  <td><input type="text" class="form-control" id="sort" name="sort" value="${sort}" placeholder="请输入排序" required="required"></td>
              </tr>
	        </tbody>
	      </table>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
		<button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" onclick="goSub();"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
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




$(function(){


    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="productDis"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor?moduleName=lfOfficial'
        });
    });

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#productPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });

    $('#fileuploadPic').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImgPic").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#productMpic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });


	$("#editForm").bind('submit', function(event) {
        $("#productDis").text(editor1.html());
		ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
		event.preventDefault();
	});

    var productType = '${catId},${catType}';
    if(productType!=''){
        $("#productType1").val(productType);
    }

    var a = '${productType}';
    if(a!=''){
        $("#productType").val(a);
    }
});


function returnCallback(event,param){
	var apiRsp=getApiJson(param.data);
	if(apiRsp && apiRsp.isSuccess){
		alert('商品修改成功');
	}else{
		alert(apiRsp.msg);
	}
	reloadParent();
}

function goSub(){
    var productType1 = $("#productType1").val();
    if(productType1 ==null || productType1 == ''){
        alert("请选择商品分类！");
        return false;
    }
    $("#catId").val(productType1.split(",")[0]);
    $("#catType").val(productType1.split(",")[1]);
}

</script>
</body>
</html>