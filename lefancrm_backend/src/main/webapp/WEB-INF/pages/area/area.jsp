<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑角色信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/zTree_v3/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
<div class="main" style="width: 400px">
<%--<div class="container">--%>
    <form id="editForm" role="form" class="form-horizontal" action="${ctx}/area/updateAreaState" method="post">
        <input type="hidden" name="areaIds" id="areaIds" >
        <div class="main-top" style="width: 400px"><h3>区域列表&nbsp;
            <small>[ <a id="toggleBtn" href="#"  onclick="return false;">展开/折叠</a> ]</small>
        </h3></div>

        <div class="panel panel-info">
            <div class="content_wrap">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree">正在加载菜单...</ul>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
<%--</div>--%>
 </div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<%-- <script type="text/javascript" src="${ctx}/js/zTree_v3/jquery-1.4.4.min.js"></script> --%>

<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree_v3/jquery.ztree.exedit-3.5.js"></script>

<script type="text/javascript">
    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert('区域显示成功');
        }else{
            alert(apiRsp.msg);
        }
        ajaxSubmit("${ctx}/org/treeDataPub?type=2",{},loadZTree,null,"",loadZTree);
//        reloadParent();
    }

    $(function(){

        $("#editForm").bind('submit', function(event) {

            checkAllNodesId();

            ajaxFormSubmit(this,returnCallback,null,null,returnCallback);

            event.preventDefault();

        });

        ajaxSubmit("${ctx}/org/treeDataPub?type=2",{},loadZTree,null,"",loadZTree);

    });


    function checkAllNodesId(){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var checkedNodes = treeObj.getCheckedNodes(true);
        var str = '';
        $(checkedNodes).each(function(index,node){
            str += node.id+",";
        });
        $("#areaIds").val(str);
    }


    function unCheckAllNodesId(event, param){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        $(param.data).each(function(i,obj){
            var tId = obj.areaId;
            var node = treeObj.getNodeByParam("id", tId, null);
            node.checked  = true;
        });


    }

    function loadRoleMenu(){
        ajaxSubmit("${ctx}/area/getShowArea",{},unCheckAllNodesId,null,"",unCheckAllNodesId);
    }


</script>


<script type="text/javascript">

    var setting = {
        view: {
            selectedMulti: true,
            expandSpeed: "normal"
        },

        /* 	async: {
         enable: true,
         url:"${ctx}/system/menu/treeData",
         autoParam:["id", "name=n", "level=lv"],
         type: "get"
         }, */

        check: {
            enable: true,
            chkStyle: "checkbox"
        },
        data: {
            type: "expandAll",
            simpleData: {
                enable: true
            }
        }
    };
    function loadZTree(event, param){
        $.fn.zTree.init($("#treeDemo"), setting,param.data);
        $("#toggleBtn").bind("click", {type:"expandAll"}, expandNode);
        loadRoleMenu();
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

</script>
</body>
</html>