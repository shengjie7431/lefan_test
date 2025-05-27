<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>通知消息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
            height: 70px;
            padding: 10px 2%;
            border-bottom: 1px solid #BBBBBB;
        }

        .header .title {
            font-size: 20px;
        }

        .header .already-all {
            /*height: 30px;*/
            line-height: 30px;
            padding: 4px 16px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            font-size:14px;
        }

        .msg-content {
            width: 100%;
        }

        .msg-content .cell {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            padding: 10px 2%;
            font-size: 14px;
            color: #333;
            border-bottom: 1px solid #BBBBBB;
        }

        .msg-content .cell .content {
            width: 80%;
        }

        .msg-content .cell .content .content-type {
            width: 100%;
            font-weight: 600;
            padding-bottom: 4px;
        }

        .msg-content .cell .content .content-type span {
            display: inline-block;
            width: 10px;
            height: 10px;
            margin-right: 10px;
            border-radius: 50%;
            background-color: #E51C23;
        }

        .msg-content .cell .content .content-text {
            padding-left: 20px;
        }

        .msg-content .cell .content .content-text span {
            display: inline-block;
            color: #3BA9FF;
            cursor: pointer;
        }

        .msg-content .cell .data {
            width: 15%;
            text-align: right;
        }

        .msg-content .already .content .content-type {
            color: rgba(51, 51, 51, 0.77)
        }

        .msg-content .already .content .content-type span {
            background-color: #bbb;
        }

        .msg-content .already .content .content-text {
            color: rgba(51, 51, 51, 0.77)
        }
    </style>
</head>
<body>
<div class="main administrator">

    <div class="header">
        <div class="title">通知中心</div>
        <a href="#" onclick="operate('${item.id}','${item.msgUrl}','${surveyCode}',2000,true)"><div class="already-all">全部已读</div></a>
    </div>
    <div class="msg-content">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <c:forEach items="${apiRsp.results}" var="item">
            <div <c:if test="${item.isRead ==0}"> class="cell"</c:if>
                 <c:if test="${item.isRead ==1}"> class="cell already"</c:if>
                >
                <div class="content">
                    <div class="content-type"><span></span>${item.msgTitle}</div>
                    <div class="content-text">${item.msgContent}，点击<span><a href="#" onclick="operate('${item.id}','${item.msgUrl}','${surveyCode}',1000,false)">查看详情</a></span></div>
                </div>
                <div class="data"><fmt:formatDate value="${item.fromTime}" pattern="yyyy-MM-dd HH:mm"/></div>
            </div>
        </c:forEach>
    </div>

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&toUserId=${toUserId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }

    function operate(id,msgUrl,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            if(btnCode == '2000'){
                var url1 = "${ctx}/baseSurvey/operate",param = {"surveyCode":surveyCode,"btnCode":btnCode, "toUserId":${toUserId}};
                //更新单条数据：已读
                if(confirm('是否确认？')) {
                    ajaxSubmit(url1, param, function (v, e, p) {
                        location.reload();
                    })
                }
            }
        }else{
            if(btnCode == '1000'){
                var url1 = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
                //更新单条数据：已读
                ajaxSubmit(url1,param,function(v,e,p){})
                //弹窗显示详情
                height = 800;
                width = $(document.body).outerWidth();;
                title = '详情';
                url = "${ctx}/" + msgUrl
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url,
                load:true
            });
        }
    }

</script>
</body>
</html>
