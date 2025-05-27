<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <script>

    </script>
</head>
<body>
<div class="main">
    <input type="hidden" name="id" value="${caseEntrustInput.id}">
    <div class="title">
        <c:if test="${caseEntrustInput.checkState == 0}">
            <button class="butList active" onclick="operate('${caseEntrustInput.id}',1000, true)">审核通过</button>
            <button class="butList active" onclick="operate('${caseEntrustInput.id}',1100, false)">驳回</button>
        </c:if>
        <c:if test="${caseEntrustInput.checkState == 2}">
            <button class="butList defuelt" onclick="">已驳回</button>
            <button class="butList active" onclick="operate('${caseEntrustInput.id}',1000, true)">审核通过</button>
        </c:if>
        <c:if test="${caseEntrustInput.checkState == 1}">
            <button class="butList defuelt" onclick="">已通过</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>用户姓名</td>
                    <td>${caseEntrustInput.injuredPerson}</td>
                    <td>用户电话</td>
                    <td>${caseEntrustInput.injuredTel}</td>
                    <td>案件类型</td>
                    <td>
                        <c:if test="${caseEntrustInput.agentType == 1}">交通事故索赔</c:if>
                        <c:if test="${caseEntrustInput.agentType == 2}">工伤事故索赔</c:if>
                    </td>
                </tr>
                <tr>
                    <td>所在省</td>
                    <td>${caseEntrustInput.accidentProvince}</td>
                    <td>所在市</td>
                    <td>${caseEntrustInput.accidentCity}</td>
                    <td>所在县</td>
                    <td>${caseEntrustInput.accidentDistrict}</td>
                </tr>
                <tr>
                    <td>案件状态</td>
                    <td>
                        <c:if test="${caseEntrustInput.checkState == 0}">待审核</c:if>
                        <c:if test="${caseEntrustInput.checkState == 1}">审核通过</c:if>
                        <c:if test="${caseEntrustInput.checkState == 2}">驳回</c:if>
                    </td>
                    <td>事故发生地</td>
                    <td colspan="3">${caseEntrustInput.accidentAddress}</td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${caseEntrustInput.createBy}</td>
                    <td>创建时间</td>
                    <td colspan="3"><fmt:formatDate value="${caseEntrustInput.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <c:if test="${caseEntrustInput.checkState == 3}">
                        <td>驳回原因</td>
                        <td colspan="5">${caseEntrustInput.reson}</td>
                    </c:if>
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

    function operate(id,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1600'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id
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
</body>
</html>