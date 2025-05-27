<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" href="${ctx}/js/zTree_v3/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/css/icon.css" type="text/css">
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
                <div class="panel-heading">
                    <div class="pin">
                        <form class="form-inline" role="form" action="${ctx}/org/outCC" method="post">
                            跟进开始时间： <input id="startDate" name="startDate" type="text" value="${startDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            跟进结束时间： <input id="endDate" name="endDate" type="text" value="${startDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <input id="orgId" name="id" type="hidden" value="${orgId}"/>
                            <div class="form-group">
                                CC姓名: <select id="ccUser" class="form-control">
                                <option value="" >请选择人员</option>
                            </select>
                            </div>
                                <button id="Btn" type="submit" class="btn btn-default" >导出</button><%--onclick="out()--%>
                                <button id="queryBtn" type="button" class="btn btn-default" onclick="query('','')">查询</button>
                        </form>

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="100">CC组别</th>
                        <th width="100">CC姓名</th>
                        <th width="100">拜访客户数&nbsp;<a><span class="iconfont xe61f" onclick="query(2,'')"></span></a>&nbsp;<a><span class="iconfont xe602" onclick="query(1,'')"></span></a></th>
                        <th width="100">目标客户数</th>
                        <th width="100">意向客户数</th>
                        <th width="100">签约客户数&nbsp;<a><span class="iconfont xe61f" onclick="query('',2)"></span></a>&nbsp;<a><span class="iconfont xe602" onclick="query('',1)"></span></a></th>
                        <th width="150">案件成交总额</th>
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
			url:"${ctx}/org/treeData?orgType=1",
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
//		edit: {
//			enable: true,
//			editNameSelectAll: false,
//			showRemoveBtn: false,
//			showRenameBtn: true
//		},
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
        $("table tbody tr").remove();

        $.ajax({
            url: '${ctx}/org/queryCCListByOrgId?orgId='+treeNode.id+'&startDate='+$("#startDate").val()+'&endDate='+$("#endDate").val()+'&userName='+$("#ccUser").val(),
            method:'POST',
            success: function(res){
                var json = JSON.parse(res);
                console.info(json);
                $("#orgId").val(json.orgId);
                $("#startDate").val(json.startDate);
                $("#endDate").val(json.endDate);
                if(json.results != null){
                    $.each(json.results, function(idx, obj){
                        var data = "<tr id='t'>";
                        data += "<td>"+obj.orgName+"</td>";
                        data += "<td>"+obj.userName+"</td>";
                        data += "<td>"+obj.visitNum+"</td>";
                        data += "<td>"+obj.targetNum+"</td>";
                        data += "<td>"+obj.intentionNum+"</td>";
                        data += "<td>"+obj.signNum+"</td>";
                        data += "<td>"+obj.saleAmount+"</td>";
                        data += "</tr>";
                        $("#tbd").append(data);
                    });
                    $("#Btn").attr("disabled", false);
                    if(json.results == '' || json.results.length == 0){
                        //$("#Btn").hide();
                        alert("暂无数据!");
                        $("#Btn").attr("disabled", true);
                    }
                }else{

                    alert("暂无数据!");

                    $("#Btn").attr("disabled", true);

                }

                // 添加操作人员下拉列表显示
                userList(json.orgId);
              /*  $("#ccUser").val(json.userName);*/
                $("#ccUser").val("("+json.orgName+")"+json.userName);
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
            url: '${ctx}/org/ccTreeDataOrg',
            method:'POST',
           success: function(res){
               var json = JSON.parse(res);
               $("#orgId").val(json.orgId);
               $("#startDate").val(json.startDate);
               $("#endDate").val(json.endDate);
               if(json.results != null){
                   $.each(json.results, function(idx, obj){
                       var data = "<tr id='t'>";
                       data += "<td>"+obj.orgName+"</td>";
                       data += "<td>"+obj.userName+"</td>";
                       data += "<td>"+obj.visitNum+"</td>";
                       data += "<td>"+obj.targetNum+"</td>";
                       data += "<td>"+obj.intentionNum+"</td>";
                       data += "<td>"+obj.signNum+"</td>";
                       data += "<td>"+obj.saleAmount+"</td>";
                       data += "</tr>";
                       $("#tbd").append(data);
                   });
                   $("#Btn").attr("disabled", false);
                   if(json.results == '' || json.results.length == 0){
                       $("#Btn").attr("disabled", true);
                   }
               }else{
                   $("#Btn").attr("disabled", true);

               }

               // 添加下拉列表显示
               userList(json.orgId);
               $("#ccUser").val(json.userName);
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
            url:"${ctx}/org/queryUser?id="+id+"&orgParentid="+(orgParentid == -1 ? '': orgParentid)+"&orgType="+orgType
        });
    }
    //************************** 查询函数 ************************************
    var query = function(visitNum,signNum){
        var s = $("#startDate").val();
        console.info($("#startDate").val());
        $("table tbody tr").remove();
        $.ajax({
            url: '${ctx}/org/queryCCListByOrgId',
            method:'POST',
            data:'orgId='+ $("#orgId").val()+'&startDate='+$("#startDate").val()+'&endDate='+$("#endDate").val()+'&userName='+$("#ccUser").val()+'&orderByVisitNum='+visitNum+'&orderBySignNum='+signNum,//+'&orderByVisitNum='+$("#orderByVisitNum").val()+'&orderBySignNum='+$("#orderBySignNum").val(),
            success: function(res){
                var json = JSON.parse(res);
                $("#orgId").val(json.orgId);
                $("#startDate").val(json.startDate);
                $("#endDate").val(json.endDate);
                if(json.results != null){
                    $.each(json.results, function(idx, obj){
                        var data = "<tr id='t'>";
                        data += "<td>"+obj.orgName+"</td>";
                        data += "<td>"+obj.userName+"</td>";
                        data += "<td>"+obj.visitNum+"</td>";
                        data += "<td>"+obj.targetNum+"</td>";
                        data += "<td>"+obj.intentionNum+"</td>";
                        data += "<td>"+obj.signNum+"</td>";
                        data += "<td>"+obj.saleAmount+"</td>";
                        data += "</tr>";
                        $("#tbd").append(data);
                    });
                    $("#Btn").attr("disabled", false);
                    if(json.results == '' || json.results.length == 0){
                        alert("暂无数据!");
                        $("#Btn").attr("disabled", true);
                    }
                }else{
                    alert("暂无数据!");
                    $("#Btn").attr("disabled", true);
                }
                // 添加下拉列表回显
                $("#ccUser").val(json.userName);
            }
        })
    }

    function userList(orgId){
        $("#ccUser").empty();
        var first = "<option value='' >请选择人员</option>"
        $("#ccUser").append(first);
        $.ajax({
            url: '${ctx}/org/queryUserListByOrgId',
            method:'POST',
            data:'orgId='+orgId,
            success: function(res){
                var json = JSON.parse(res);
                if(json.results != null){
                    $.each(json.results, function(idx, obj){
                        /*<option value="" >请选择人员</option>*/
                        if(obj.userName != null){
                            var data = "";
                            data += "<option value='"+obj.userName+"'>"+obj.userName+"("+obj.orgName+")"+"</option>";
                            $("#ccUser").append(data);
                        }
                    });
                }
            }
        })
    }




/*function query(){}*/

</script>
</body>
</html>
