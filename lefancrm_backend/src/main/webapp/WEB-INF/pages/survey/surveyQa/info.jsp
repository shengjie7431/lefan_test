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
        <c:if test="${surveyQa.questionState ==1}">
            <c:if test="${surveyQa.questionType ==2}">
                <button class="butList active" onclick="operate('${surveyQa.id}','${surveyCode}','1100',false);">回复问题</button>
            </c:if>
        </c:if>
        <c:if test="${surveyQa.questionType ==1}">
            <button class="butList active" onclick="operate('${surveyQa.id}','${surveyCode}','1000',false);">修改</button>
        </c:if>
        <c:if test="${surveyQa.isTop == 0 || surveyQa.isTop == null}">
            <button class="butList defuelt" onclick="operate('${surveyQa.id}','${surveyCode}','1200',true);">置顶</button>
        </c:if>
        <c:if test="${surveyQa.isTop == 1}">
            <button class="butList defuelt" onclick="operate('${surveyQa.id}','${surveyCode}','1201',true);">取消置顶</button>
        </c:if>
        <button class="butList defuelt" onclick="operate('${surveyQa.id}','${surveyCode}','9999',true);">删除</button>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>问题</td>
                    <td colspan="5">${surveyQa.question}</td>
                </tr>
                <tr>
                    <td>答案</td>
                    <td colspan="5">${surveyQa.answer}</td>
                </tr>
                <tr>
                    <td>类型</td>
                    <td>
                        <c:if test="${surveyQa.questionType == 1}">系统</c:if>
                        <c:if test="${surveyQa.questionType == 2}">用户</c:if>
                    </td>
                    <td>是否置顶</td>
                    <td>
                        <c:if test="${surveyQa.isTop == 0 || surveyQa.isTop == null}">否</c:if>
                        <c:if test="${surveyQa.isTop == 1}">是</c:if>
                    </td>
                    <td>问题标签</td>
                    <td>
                        <c:forEach items="${surveyQas}" var="dto">
                            <c:if test="${dto.enumCode == surveyQa.questionLabel}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>问题状态</td>
                    <td>
                        <c:if test="${surveyQa.questionState ==1}">未回复</c:if>
                        <c:if test="${surveyQa.questionState ==2}">已回复</c:if>
                    </td>
                    <td>提问者</td>
                    <td>${surveyQa.questionUserName}</td>
                    <td>提问时间</td>
                    <td><fmt:formatDate value="${surveyQa.questionTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>

                <tr>
                    <td>回答者</td>
                    <td>${surveyQa.answerUserName}</td>
                    <td>回答时间</td>
                    <td colspan="3"><fmt:formatDate value="${surveyQa.answerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
            }else if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url="${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
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