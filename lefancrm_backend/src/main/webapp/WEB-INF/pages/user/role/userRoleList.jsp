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
	</style>
</head>
<body>

<div class="main">

    <div class="main-top"><h3>机构列表&nbsp;
        <small>[ <a id="toggleBtn" href="#"  onclick="return false;">展开/折叠</a> ]</small>
    </h3></div>
    <div class="row">
        <div class="panel panel-info col-sm-2">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree">正在加载机构...</ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <div class="col-sm-10 " id="result">
            <div class="row panel panel-info">
                <div class="panel-heading">
                    <div class="pin">
                        <div class="form-group">
                        </div>
                    </div>
                </div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="100">名称</th>
                        <th width="100">电话</th>
                        <th width="100">联系人</th>
                        <th width="100">联系人电话</th>
                        <th width="200">机构地址</th>
                        <th width="60">审核状态</th>
                        <th width="150">操作</th>
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
//		view: {
//			addHoverDom: addHoverDom,
//			removeHoverDom: removeHoverDom,
//			selectedMulti: true,
//			expandSpeed: "normal"
//		},
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
        $("#t").remove();
        $.ajax({
            url: '${ctx}/user/role/queryOrgById?id='+treeNode.id,
            method:'POST',
            success: function(res){
                var json = JSON.parse(res);
                var data = "<tr id='t'>";
                data += "<td>"+json.orgName+"</td>";
                data += "<td>"+json.orgTel+"</td>";
                data += "<td>"+json.linkName+"</td>";
                data += "<td>"+json.linkTel+"</td>";
                data += "<td>"+json.orgAddress+"</td>";
                var states = "";
                var caozuo = "";
                if(json.state == 1){
                    states = "待审核";
                    caozuo +="<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,1)'>审核通过</a>";
                    caozuo +=" ";
                    caozuo +="<a  href='javascript:void(0)' onclick='updateState("+json.id+",3,2)'>审核不通过</a>";
                }else if(json.state == 2){
                    states = "审核通过";
//                    caozuo += "<a  href='javascript:void(0)'' onclick='queryCase("+json.id+")'>查看案件</a>";
//                    caozuo +=" ";
                    caozuo += "<a  href='javascript:void(0)' onclick='queryUser("+json.id+",null,"+json.orgType+")'>查看用户</a>";
//                    caozuo +=" ";
//                    caozuo += "<a  href='javascript:void(0)'' onclick='toDisUser("+json.id+")'>分配用户</a>";
//                    caozuo +=" ";
//                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",4,3)'>禁用</a>";
                }else if(json.state == 3){
                    states = "审核不通过";
                    caozuo +=" ";
                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,5)'>重新审核</a>";
                }else if(json.state == 4){
                    states = "禁用";
                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,4)'>启用</a>";
                }
                data += "<td>"+states+"</td>";
                data += "<td>"+caozuo+"</td>";
                data += "</tr>";
                $("#tbd").append(data);
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
            url: '${ctx}/user/role/treeDataUserRole',
            method:'POST',
            success: function(res){
               var json = JSON.parse(res);
               var data = "<tr id='t'>";
               data += "<td>"+json.orgName+"</td>";
                    data += "<td>"+json.orgTel+"</td>";
                    data += "<td>"+json.linkName+"</td>";
                    data += "<td>"+json.linkTel+"</td>";
                    data += "<td>"+json.orgAddress+"</td>";
                    var states = "";
                    var caozuo = "";
                if(json.state == 1){
                    states = "待审核";
                    caozuo +="<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,1)'>审核通过</a>";
                    caozuo +=" ";
                    caozuo +="<a  href='javascript:void(0)' onclick='updateState("+json.id+",3,2)'>审核不通过</a>";
                }else if(json.state == 2){
                    states = "审核通过";
//                    caozuo += "<a  href='javascript:void(0)'' onclick='queryCase("+json.id+")'>查看案件</a>";
//                    caozuo +=" ";
                    caozuo += "<a  href='javascript:void(0)' onclick='queryUser("+json.id+","+json.orgParentid+","+json.orgType+")'>查看用户</a>";
//                    caozuo +=" ";
//                    caozuo += "<a  href='javascript:void(0)'' onclick='toDisUser("+json.id+")'>分配用户</a>";
//                    caozuo +=" ";
//                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",4,3)'>禁用</a>";
                }else if(json.state == 3){
                    states = "审核不通过";
                    caozuo +=" ";
                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,5)'>重新审核</a>";
                }else if(json.state == 4){
                    states = "禁用";
                    caozuo += "<a  href='javascript:void(0)' onclick='updateState("+json.id+",2,4)'>启用</a>";
                }
                    data += "<td>"+states+"</td>";
                    data += "<td>"+caozuo+"</td>";
                    data += "</tr>";
                    $("#tbd").append(data);
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



</script>
</body>
</html>
