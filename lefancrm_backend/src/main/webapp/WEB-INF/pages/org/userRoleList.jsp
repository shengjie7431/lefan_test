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
                        <form class="form-inline" role="form"  method="post" action="${ctx}/org/out"><%--action="${ctx}/case/caseCenterInfoList"--%>
                            跟进开始时间： <input id="startDate" name="startDate" type="text" value="${startDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            跟进结束时间： <input id="endDate" name="endDate" type="text" value="${endDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <input id="orgId" type="hidden" name="id" value="${orgId}"/>
                                <button id="Btn" type="submit" class="btn btn-default" >导出</button><%--onclick="out()--%>
                                <button id="queryBtn" type="button" class="btn btn-default" onclick="query('','')">查询</button>
                        </form>



                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="100">CC组别</th>
                       <%-- <th width="100">CC姓名</th>--%>
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

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script>
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
//			enable: false,
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
//			beforeDrag: beforeDrag,
//			beforeEditName: beforeEditName,
//			beforeRemove: beforeRemove,
//			beforeRename: beforeRename,
//			onRemove: onRemove,
//			onRename: onRename,
            onAsyncSuccess:onAsyncSuccess,
            onClick : zTreeOnClick
		}
	};

    function zTreeOnClick(e, treeId, treeNode){
        $("table tbody tr").remove();
        $.ajax({
            url: '${ctx}/org/queryOrgById?id='+treeNode.id+'&startDate='+$("#startDate").val()+'&endDate='+$("#endDate").val(),
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
                    $("#Btn").attr("disabled", true);
                    alert("暂无数据!");
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

    function onAsyncSuccess(){
       $.ajax({
            url: '${ctx}/org/treeDataOrg',
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
           }
       })
    }
	var log, className = "dark";


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




    //************************** 查询函数 ************************************
    var query = function(visitNum,signNum){
        $("table tbody tr").remove();
        $.ajax({
            url: '${ctx}/org/queryOrgById',
            method:'POST',
            data:'id='+ $("#orgId").val()+'&startDate='+$("#startDate").val()+'&endDate='+$("#endDate").val()+'&orderByVisitNum='+visitNum+'&orderBySignNum='+signNum,//+'&orderByVisitNum='+$("#orderByVisitNum").val()+'&orderBySignNum='+$("#orderBySignNum").val(),
            success: function(res){
                var json = JSON.parse(res);
                $("#orgId").val(json.orgId);
                $("#startDate").val(json.startDate);
                $("#endDate").val(json.endDate);
                if(json.results != null){
                    $.each(json.results, function(idx, obj){
                        var data = "<tr id='t'>";
                        data += "<td>"+obj.orgName+"</td>";
                        data += "<td>"+obj.visitNum+"</td>";
                        data += "<td>"+obj.targetNum+"</td>";
                        data += "<td>"+obj.intentionNum+"</td>";
                        data += "<td>"+obj.signNum+"</td>";
                        data += "<td>"+obj.saleAmount+"</td>";
                        data += "</tr>";
                        $("#tbd").append(data);
                    });
                   /* $("#Btn").show();*/
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
            }
        })
    }
</script>
</body>
</html>
