<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
	<link rel="stylesheet" href="${ctx}/js/zTree_v3/zTreeStyle/zTreeStyle.css" type="text/css">
	<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}

    #t td input{
        display: inline-block;
        width: 100px;
    }
	</style>
</head>
<body>

<div class="main" style="width: 2500px">

    <div class="main-top"><h3>机构列表&nbsp;
        <small>[ <a id="toggleBtn" href="#"  onclick="return false;">展开/折叠</a> ]</small>
    </h3></div>
    <div class="row">
        <div class="panel panel-info col-sm-2" style="width: 230px">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree">正在加载机构...</ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <div class="col-sm-10 " id="result" style="width:2000px">
            <div class="row panel panel-info">
                <div class="panel-heading">
                    <div class="pin">
                        <div class="form-group">
                        </div>
                    </div>
                </div>
                <table class="table table-hover" style="width:1970px">
                    <thead>
                    <tr>
                        <th width="1000">姓名</th>
                        <th width="1000">业绩汇总(元)</th>
                        <th width="600">一月</th>
                        <th width="600">二月</th>
                        <th width="600">三月</th>
                        <th width="600">四月</th>
                        <th width="600">五月</th>
                        <th width="600">六月</th>
                        <th width="600">七月</th>
                        <th width="600">八月</th>
                        <th width="600">九月</th>
                        <th width="600">十月</th>
                        <th width="600">十一月</th>
                        <th width="600">十二月</th>
                    </tr>
                    </thead>
                    <tbody class="class-list" id="tbd">

                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div><!--main end-->


<%-- <script src="${ctx}/js/jquery.min.js" type="text/javascript"></script> --%>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>
	
<script type="text/javascript">
	
	var setting = {
		async: {
			enable: true,
			url:"${ctx}/user/role/treeData?orgType=1",
			autoParam:["id", "name=n", "level=lv"],
			dataFilter: filter,
			type: "get"
		},
		edit: {
			enable: false,
			editNameSelectAll: false,
			showRemoveBtn: false,
			showRenameBtn: true
		},
		data: {
			type: "expandAll",
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename,
            onAsyncSuccess:onAsyncSuccess,
            onClick : zTreeOnClick
		}
	};

    function zTreeOnClick(e, treeId, treeNode){
        $("#tbd").empty();
        $.ajax({
            url: '${ctx}/sale/goal/userInfoList?orgId='+treeNode.id+'&year='+${year},
            method:'POST',
            success: function(res){
                var json = JSON.parse(res);
                if(json==null){
//                    alert('机构下无用户');
                    return;
                }
                for(var i =0;i<json.length;i++){
                    var entity = json[i];
                    var data = "<tr id='t'>";
                    data += "<td>"+entity.name+"</td>";
                    if(entity.total==null){
                        data += "<td>￥0.00</td>";
                    }else{
                        data += "<td>"+entity.total+"</td>";
                    }
                    var id = entity.id;
                    data += "<td><input type='text' id='text1"+id+"' value='"+(entity.january==null?'':entity.january)+"' onkeypress='number("+entity.id+"1)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"1' alt='"+entity.name+"' onclick='ok(1,"+id+"1,"+id+","+entity.january+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'></img></td>";
                    data += "<td><input type='text' id='text2"+id+"' value='"+(entity.february==null?'':entity.february)+"' onkeypress='number("+entity.id+"2)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'/>" +
                            "<img id='"+entity.id+"2' alt='"+entity.name+"' onclick='ok(2,"+id+"2,"+id+","+entity.february+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text3"+id+"' value='"+(entity.march==null?'':entity.march)+"' onkeypress='number("+entity.id+"3)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"3' alt='"+entity.name+"' onclick='ok(3,"+id+"3,"+id+","+entity.march+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text4"+id+"' value='"+(entity.april==null?'':entity.april)+"' onkeypress='number("+entity.id+"4)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"4' alt='"+entity.name+"' onclick='ok(4,"+id+"4,"+id+","+entity.april+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text5"+id+"' value='"+(entity.may==null?'':entity.may)+"' onkeypress='number("+entity.id+"5)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"5' alt='"+entity.name+"' onclick='ok(5,"+id+"5,"+id+","+entity.may+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text6"+id+"' value='"+(entity.june==null?'':entity.june)+"' onkeypress='number("+entity.id+"6)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"6' alt='"+entity.name+"' onclick='ok(6,"+id+"6,"+id+","+entity.june+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text7"+id+"' value='"+(entity.july==null?'':entity.july)+"' onkeypress='number("+entity.id+"7)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"7' alt='"+entity.name+"' onclick='ok(7,"+id+"7,"+id+","+entity.july+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text8"+id+"' value='"+(entity.august==null?'':entity.august)+"' onkeypress='number("+entity.id+"8)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"8' alt='"+entity.name+"' onclick='ok(8,"+id+"8,"+id+","+entity.august+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text9"+id+"' value='"+(entity.september==null?'':entity.september)+"' onkeypress='number("+entity.id+"9)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"9' alt='"+entity.name+"' onclick='ok(9,"+id+"9,"+id+","+entity.september+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text10"+id+"' value='"+(entity.october==null?'':entity.october)+"' onkeypress='number("+entity.id+"10)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"10' alt='"+entity.name+"' onclick='ok(10,"+id+"10,"+id+","+entity.october+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text11"+id+"' value='"+(entity.november==null?'':entity.november)+"' onkeypress='number("+entity.id+"11)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"11' alt='"+entity.name+"' onclick='ok(11,"+id+"11,"+id+","+entity.november+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text12"+id+"' value='"+(entity.december==null?'':entity.december)+"' onkeypress='number("+entity.id+"12)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+entity.id+"12' alt='"+entity.name+"' onclick='ok(12,"+id+"12,"+id+","+entity.december+","+treeNode.id+")' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "</tr>";
                    $("#tbd").append(data);
                }
            }
        })
    }
    var updateInfo = function(id){
        openDialog({
            frame:true,
            title:"修改机构信息",
            height:1000,
            width:800,
            url:"${ctx}/org/toEdit?id="+id
        });
    }
    var toDisUser = function(id,name){
        openDialog({
            frame:true,
            title:"分配用户",
            height:700,
            width:1000,
            url:"${ctx}/org/toDisUser?id="+id
        });
    }

    var updateState = function(id,state,type){
        var val = "";
        var toVal ="";
        if(type == 1){
            val = "确定审核通过操作？"
            toVal = "审核成功！"
        }else if(type == 3){
            val = "确定禁用此机构操作？"
            toVal = "禁用机构成功！"
        }else if(type == 2){
            val = "确定审核不通过操作？"
            toVal = "审核不通过成功！"
        }else if(type == 4){
            val = "确定启用此机构操作？"
            toVal = "启用机构成功！"
        }else if(type == 5){
            val = "确定重新审核操作？"
            toVal = "审核成功！"
        }
        ajaxSubmit("${ctx}/org/editState",{"orgId":id,"state":state},reload,toVal,val,"操作失败");
    }
    function onAsyncSuccess(){





       $.ajax({
            url: '${ctx}/sale/goal/userInfoList?orgId=1&year='+${year},
            method:'POST',
            success: function(res){
               var json = JSON.parse(res);
                if(json==null){
//                    alert('机构下无用户');
                    return;
                }
                for(var i =0;i<json.length;i++){
                    var entity = json[i];
                    var data = "<tr id='t'>";
                    data += "<td>"+entity.name+"</td>";
                    if(entity.total==null){
                        data += "<td>￥0.00</td>";
                    }else{
                        data += "<td>"+entity.total+"</td>";
                    }
                    var id = entity.id;
                    data += "<td><input type='text' id='text1"+id+"' value='"+(entity.january==null?'':entity.january)+"' onkeypress='number("+id+"1)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"1' alt='"+entity.name+"' onclick='ok(1,"+id+"1,"+id+","+entity.january+",1)' src='${ctx}/img/ok.png' style='display:none;'></img></td>";
                    data += "<td><input type='text' id='text2"+id+"' value='"+(entity.february==null?'':entity.february)+"' onkeypress='number("+id+"2)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'/>" +
                            "<img id='"+id+"2' alt='"+entity.name+"' onclick='ok(2,"+id+"2,"+id+","+entity.february+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text3"+id+"' value='"+(entity.march==null?'':entity.march)+"' onkeypress='number("+id+"3)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"3' alt='"+entity.name+"' onclick='ok(3,"+id+"3,"+id+","+entity.march+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text4"+id+"' value='"+(entity.april==null?'':entity.april)+"' onkeypress='number("+id+"4)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"4' alt='"+entity.name+"' onclick='ok(4,"+id+"4,"+id+","+entity.april+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text5"+id+"' value='"+(entity.may==null?'':entity.may)+"' onkeypress='number("+id+"5)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"5' alt='"+entity.name+"' onclick='ok(5,"+id+"5,"+id+","+entity.may+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text6"+id+"' value='"+(entity.june==null?'':entity.june)+"' onkeypress='number("+id+"6)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"6' alt='"+entity.name+"' onclick='ok(6,"+id+"6,"+id+","+entity.june+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text7"+id+"' value='"+(entity.july==null?'':entity.july)+"' onkeypress='number("+id+"7)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"7' alt='"+entity.name+"' onclick='ok(7,"+id+"7,"+id+","+entity.july+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text8"+id+"' value='"+(entity.august==null?'':entity.august)+"' onkeypress='number("+id+"8)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"8' alt='"+entity.name+"' onclick='ok(8,"+id+"8,"+id+","+entity.august+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text9"+id+"' value='"+(entity.september==null?'':entity.september)+"' onkeypress='number("+id+"9)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"9' alt='"+entity.name+"' onclick='ok(9,"+id+"9,"+id+","+entity.september+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text10"+id+"' value='"+(entity.october==null?'':entity.october)+"' onkeypress='number("+id+"10)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"10' alt='"+entity.name+"' onclick='ok(10,"+id+"10,"+id+","+entity.october+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text11"+id+"' value='"+(entity.november==null?'':entity.november)+"' onkeypress='number("+id+"11)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"11' alt='"+entity.name+"' onclick='ok(11,"+id+"11,"+id+","+entity.november+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "<td><input type='text' id='text12"+id+"' value='"+(entity.december==null?'':entity.december)+"' onkeypress='number("+id+"12)' onkeyup='filterInput()' onchange='filterInput()' onbeforepaste='filterPaste()' onpaste='return false' style='ime-mode: disabled' class='form-control'>" +
                            "<img id='"+id+"12' alt='"+entity.name+"' onclick='ok(12,"+id+"13,"+id+","+entity.december+",1)' src='${ctx}/img/ok.png' style='display:none;'/></td>";
                    data += "</tr>";
                    $("#tbd").append(data);
                }
                }
        })
    }
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		alert("treeId:"+treeId);
		return false;
	}
	
	function beforeEditName(treeId, treeNode) {
        openDialog({
            frame:true,
            title:"修改机构信息",
            height:700,
            width:800,
            url:"${ctx}/org/toEdit?id="+treeNode.id
        });
        return false;
	}
	
	function beforeRemove(treeId, treeNode) {
//		className = (className === "dark" ? "":"dark");
//		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//		zTree.selectNode(treeNode);
//
//		if(treeNode.depth==0){
//			alert("默认菜单,不能删除.");
//			return false;
//		}
//
//		if(treeNode.isParent){
//			alert("该菜单有子菜单,不能直接删除.");
//			return false;
//		}

//		return confirm("确认删除菜单[" + treeNode.name + "] 吗？");
        ajaxSubmit("${ctx}/org/editState",{"orgId":treeNode.id,"state":4},reload,"你确定禁用此机构？","操作成功",null);
	}
	
	function onRemove(e, treeId, treeNode) {
		ajaxSubmit("${ctx}/system/menu/delete?id="+treeNode.id,{},null,"删除成功","",null);
		return false;
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {

		return false;
	}
	
	function onRename(e, treeId, treeNode, isCancel) {
		//var data = "id="+treeNode.id+"&menuName="+treeNode.name;
		//ajaxSubmit("${ctx}/system/menu/save?"+data,{},null,"修改成功","",null);
		return false;
	}
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var childDepth = parseInt(treeNode.depth)+1;
			var params = "pid="+treeNode.id+"&depth="+childDepth;
//			alertDialog("添加菜单",params);
            openDialog({
                frame:true,
                title:"添加机构信息",
                height:700,
                width:800,
                url:"${ctx}/org/toAdd"
            });
			return false;
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var type = setting.data.type;
		if (type == "expandAll") {
			zTree.expandAll(true);
			setting.data.type="collapseAll";
		}else{
			zTree.expandAll(false);
			setting.data.type="expandAll";
		}
	}
	
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		$("#toggleBtn").bind("click", {type:"expandAll"}, expandNode);
		
		//setTimeout(initMenuCss, 500);
		
	});
	
	
	function ajaxCallback(event,param){
		alert("操作成功");
	}
	
	function alertDialog(dialogTitle,params){
		openDialog({
			frame:true,
			title:dialogTitle,
		    height:300,
		    width:700,
			url:"${ctx}/system/menu/edit?"+params
		});
	}
	
	
	function initMenuCss(){
		
		$("#toggleBtn").click();
		
	/* 	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var treeArry =  treeObj.transformToArray(treeObj.getNodes());
		$(treeArry).each(function(index,node){
			if(node.id==56){
				//alert(treeObj.setting.font);
			}
		});  */
		
	}

    var updateState = function(id,state,type){
        var val = "";
        var toVal ="";
        if(type == 1){
            val = "确定审核通过操作？"
            toVal = "审核成功！"
        }else if(type == 3){
            val = "确定禁用此机构操作？"
            toVal = "禁用机构成功！"
        }else if(type == 2){
            val = "确定审核不通过操作？"
            toVal = "审核不通过成功！"
        }else if(type == 4){
            val = "确定启用此机构操作？"
            toVal = "启用机构成功！"
        }else if(type == 5){
            val = "确定重新审核操作？"
            toVal = "审核成功！"
        }
        ajaxSubmit("${ctx}/org/editState",{"orgId":id,"state":state},reload,toVal,val,null);
        alert("操作成功！");
        window.location.reload();

    }
    var queryCase = function(id){
        openDialog({
            frame:true,
            title:"案件信息",
            height:700,
            width:1000,
            url:"${ctx}/org/queryCase?id="+id
        });
    }
        var queryUser = function(id,orgParentid,orgType){
        openDialog({
            frame:true,
            title:"机构用户信息",
            height:700,
            width:1000,
            url:"${ctx}/user/role/queryUser?id="+id+"&orgParentid="+(orgParentid == -1 ? '': orgParentid)+"&orgType="+orgType
        });
    }



    function number(a)
    {
        $('#'+a).css('display','inline');
        var char = String.fromCharCode(event.keyCode)
        var re = /[0-9]/g
        event.returnValue = char.match(re) != null ? true : false
    }

    function filterInput()
    {
        if (event.type.indexOf("key") != -1)
        {
            var re = /37|38|39|40/g
            if (event.keyCode.toString().match(re)) return false
        }
        event.srcElement.value = event.srcElement.value.replace(/[^0-9]/g, "")
    }

    function filterPaste()
    {
        var oTR = this.document.selection.createRange()
        var text = window.clipboardData.getData("text")
        oTR.text = text.replace(/[^0-9]/g, "")
    }


    function ok(textId,imgId,userId,saleGoal,orgId){
        var name = $("#"+imgId).attr("alt")
        var addOrReduce = 0;  //加还是减
        var year = ${year};       //那一年
        var textValue= $("#text"+textId+userId).val() //填写的参数
        var number = 0;
        //saleGoal 上一次业绩目标
        //orgId 机构ID
        //userId 用户ID
        //textId 月份
        if(saleGoal!=null){
            if(textValue==saleGoal){
                $('#'+imgId).css('display','none');
                return;
            }
            if(textValue>saleGoal){
                addOrReduce = 0;
//                alert("新的数字大于旧的数字，加法："+(textValue-saleGoal))
                number = textValue-saleGoal;
            }else{
                addOrReduce = 1;
//                alert("新的数字小于旧的数字，减法："+(saleGoal-textValue));
                number = saleGoal-textValue;
            }
        }else{
            //如果旧的数字为null说明没有填写过。默认就是加
            addOrReduce = 0;
            number = textValue;
//            alert("默认添加业绩目标："+textValue);
        }

        $.ajax({
            url: '${ctx}/sale/goal/update/user?addOrReduce='+addOrReduce+'&year='+year+'&month='+textId+'&saleGoal='+number+'&objectId='+userId+'&objectName='+name+'&parentId='+orgId+'',
            method:'POST',
            success: function(res){
                var json = JSON.parse(res);
                console.log(json);
                if(json.code==="0000"){
                    $('#'+imgId).css('display','none');
                }
                }
        })

    }

</script>
</body>
</html>
