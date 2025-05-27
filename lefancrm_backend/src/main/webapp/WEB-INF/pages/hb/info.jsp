<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${id}">
    <div class="title">
        <button class="butList defuelt" onclick="hbOrgUpdate('${id}');">修改</button>
        <button class="butList active" onclick="hbOrgDelete('${id}');">删除</button>
        <button class="butList defuelt" onclick="hbUserList('${id}');">名下人员</button>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>客户名称</td>
                    <td>${hbOrgInfoDto.hbOrgName}</td>
                    <td>客户状态</td>
                    <td>  <c:if test="${hbOrgInfoDto.hbOrgStatus == 1}"> 启用</c:if>
                        <c:if test="${hbOrgInfoDto.hbOrgStatus == 2}"> 停用</c:if></td>
                    <td>code</td>
                    <td>${hbOrgInfoDto.id}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    function hbOrgDelete(id){
        ajaxSubmit("${ctx}/hb/hbOrgDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
    var hbOrgUpdate = function(id){
        openDialog({
            frame:true,
            title:"核保机构信息修改",
            height:830,
            width:1250,
            url:"${ctx}/hb/hbOrgListToEdit?id="+id
        });
    }
    var hbUserList = function(id){
        openDialog({
            frame:true,
            title:"名下人员",
            height:800,
            width:1200,
            url:"${ctx}/hb/hbUserList?hbOrgId="+id
        });
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>