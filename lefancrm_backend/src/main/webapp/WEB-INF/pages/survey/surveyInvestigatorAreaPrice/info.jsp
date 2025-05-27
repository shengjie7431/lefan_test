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
    <input type="hidden" name="id" value="${surveyInvestigatorAreaPrice.areaId}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <button class="butList active" onclick="operate('${surveyInvestigatorAreaPrice.areaId}','${surveyInvestigatorAreaPrice.cityType}','${surveyInvestigatorAreaPrice.taskId}','${surveyCode}','1000',false);">修改</button>
        <button class="butList defuelt" onclick="operate('${surveyInvestigatorAreaPrice.areaId}','${surveyInvestigatorAreaPrice.cityType}','${surveyInvestigatorAreaPrice.taskId}','${surveyCode}','9999',true);">删除</button>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>区域名称</td>
                    <td>${surveyInvestigatorAreaPrice.areaName}</td>
                    <td>任务类型名称</td>
                    <td>${surveyInvestigatorAreaPrice.taskName}</td>
                    <td>区域类别</td>
                    <td>
                        <c:if test="${surveyInvestigatorAreaPrice.cityType==2}">省会</c:if>
                        <c:if test="${surveyInvestigatorAreaPrice.cityType==3}">地级市</c:if>
                        <c:if test="${surveyInvestigatorAreaPrice.cityType==4}">县级市</c:if>
                        <c:if test="${surveyInvestigatorAreaPrice.cityType==5}">市区</c:if>
                        <c:if test="${surveyInvestigatorAreaPrice.cityType==6}">郊区</c:if>
                    </td>
                </tr>
                <tr>
                    <td>区域价格</td>
                    <td colspan="5">${surveyInvestigatorAreaPrice.price}</td>
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

    /**
     *
     */
    function operate(id,cityType,taskId,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"areaId":id,"cityType":cityType,"taskId":taskId,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?areaId="+id+"&surveyCode="+surveyCode+"&taskId="+taskId+"&cityType="+cityType+"&btnCode="+btnCode
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>