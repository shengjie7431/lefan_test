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
    <input type="hidden" name="id" value="${surveyQa.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">

        <c:if test="${surveyPunish.isExec == 0}">
            <button class="butList active" onclick="operate('${surveyPunish.id}','${surveyCode}','1200',true);">执行</button>
        </c:if>
        <c:if test="${surveyPunish.isExec == 1}">
            <button class="butList defuelt">已执行</button>
        </c:if>
        <button class="butList defuelt" onclick="operate('${surveyPunish.id}','${surveyCode}','9999',true);">删除</button>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>调查人员姓名</td>
                    <td>${surveyPunish.surveyUserName}</td>
                    <td>处罚人</td>
                    <td>${surveyPunish.dealUserName}</td>
                    <td>是否执行</td>
                    <td>
                        <c:if test="${surveyPunish.isExec == 0}">未执行</c:if>
                        <c:if test="${surveyPunish.isExec == 1}">已执行</c:if>
                    </td>
                </tr>
                <tr>
                    <td>处罚是由</td>
                    <td colspan="5">${surveyPunish.remark}</td>
                </tr>

                <tr>
                    <td>创建人</td>
                    <td>${surveyPunish.createBy}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyPunish.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>修改时间</td>
                    <td><fmt:formatDate value="${surveyPunish.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
            if (btnCode == '1100') {
                height = 500;
                width = 700;
                title = '问题回复';
                url = "${ctx}/surveyQa/answer?id=" + id + "&btnCode=" + btnCode +"&surveyCode="+surveyCode
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