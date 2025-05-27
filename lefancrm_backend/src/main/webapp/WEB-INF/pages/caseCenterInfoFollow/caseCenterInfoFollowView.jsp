<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>



    </script>
</head>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 140px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

</style>
<body>
<div class="container">
    <form id="editForm" role="form" action="" method="post">
        <input type="hidden" name="id" value="${caseCenterInfo.id}">

        <a href="javascript:caseCenterInfoFollowAdd('${caseCenterInfo.id}');">添加跟踪记录</a>
        <a href="javascript:caseCenterInfoFollowEnd('${caseCenterInfo.id}',2);">结束跟踪</a>
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">姓名</th>
                    <td width="70%">
                        ${caseCenterInfo.caseName}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件编号</th>
                    <td width="70%">
                        ${caseCenterInfo.caseNo}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件类型</th>
                    <td width="70%">
                        <c:if test="${caseCenterInfo.type == 1}">贷款申请</c:if>
                        <c:if test="${caseCenterInfo.type == 2}">代理申请</c:if>
                        <c:if test="${caseCenterInfo.type == 3}">伤残预估</c:if>
                        <c:if test="${caseCenterInfo.type == 4}">其他</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件阶段</th>
                    <td width="70%">
                        <c:if test="${caseCenterInfo.gradationState == 1}"> 洽谈阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 2}"> 评估阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 3}"> 索赔阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 4}"> 结案</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 5}"> 风控部门审核阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 6}"> 诉讼阶段</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">用户电话</th>
                    <td width="70%">
                        ${caseCenterInfo.caseTel}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">创建时间</th>
                    <td width="70%">
                        <fmt:formatDate value="${caseCenterInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <c:if test="${apiRsp.results != null}">
            <c:forEach items="${apiRsp.results}" var="item">
                <div class="stepItem" style="margin-left: 10px">
                    <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                    <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                    <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followBy}<br>内容1：${item.followDesc}<br>下次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div><div class="stepDetail-detail"></div></div>
                </div>
            </c:forEach>
        </c:if>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
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
     * 审核通过
     */
    function editState(id,state,userId){
        ajaxSubmit("${ctx}/agent/editState",{"id":id,"state":state,"userId":userId},reload,"审核成功！","确认通过审核？","审核失败！");
    }

    /**
     * 添加跟踪记录
     */
    function caseCenterInfoFollowAdd(caseId) {
        openDialog({
            frame: true,
            title: "添加跟踪记录",
            height: 450,
            width: 650,
            url: "${ctx}/caseCenterInfoFollow/caseCenterInfoFollowAdd?caseId="+ caseId
        });
    }

    /**
     * 结束跟踪
     */
    function caseCenterInfoFollowEnd(caseId,followType){
        ajaxSubmit("${ctx}/caseCenterInfoFollow/caseCenterInfoFollowEnd",{"caseId":caseId,"followType":followType},reload,"结束跟踪成功！","确认结束跟踪吗？","结束跟踪失败！");
    }
</script>
</body>
</html>