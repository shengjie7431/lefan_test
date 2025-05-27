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
    <input type="hidden" name="id" value="${surveyBusinessType.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <button class="butList active" onclick="operate('${surveyBusinessType.id}','${surveyCode}','1000',false);">修改</button>
        <button class="butList defuelt" onclick="operate('${surveyBusinessType.id}','${surveyCode}','9999',true);">删除</button>
        <button class="butList defuelt" onclick="operate('${surveyBusinessType.id}','${surveyCode}','2000',false);">名下任务类型</button>
        <button class="butList defuelt" onclick="operate('${surveyBusinessType.id}','${surveyCode}','2100',false);">添加任务类型</button>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>名称</td>
                    <td>${surveyBusinessType.name}</td>
                    <td>类别</td>
                    <td>
                        <c:if test="${surveyBusinessType.type==1}">保险类</c:if>
                        <c:if test="${surveyBusinessType.type==2}">互助类</c:if>
                    </td>
                    <td>描述</td>
                    <td>${surveyBusinessType.remark}</td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${surveyBusinessType.createByName}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyBusinessType.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${surveyBusinessType.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>排序</td>
                    <td colspan="5">${surveyBusinessType.sort}</td>
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
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
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
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }
            else if(btnCode == '2000'){
                height = 600;
                width = 1000;
                title = '名下任务类型';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
            }
            else if(btnCode == '2100'){
                height = 600;
                width = 1000;
                title = '添加任务类型';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
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