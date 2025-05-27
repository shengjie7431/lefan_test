<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .stepList {
            margin: 0 32px;
        }

        .stepItem {
            display: flex;
        }

        .stepDate {
            width: 340px;
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
</head>
<body>

        <div class="main">
            <div class="title">
                <button class="butList active" onclick="getreport('${dto.id}','${dto.caseNo}','${dto.negotiateState}')">报价审核</button>
                <button class="butList defuelt" onclick="selectFileMid('${dto.caseNo}')">单证信息</button>
                <button class="butList defuelt" onclick="selectCaseDetails('${dto.type}','${dto.caseId}',1,'${dto.caseNo}')">查看进度</button>
            </div>
            <div class="main-boy">
                <div>
                    <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td>姓名</td>
                            <td>${dto.caseName}</td>
                            <td>案件编号</td>
                            <td>${dto.caseNo}</td>
                            <td>事故发生地</td>
                            <td>${dto.address}</td>
                        </tr>
                        <tr>
                            <td>案件类型</td>
                            <td>${dto.caseTypeName}</td>
                            <td>案件阶段</td>
                            <td>${dto.gradationStateName}</td>
                            <td>案件状态</td>
                            <td>
                                <c:if test="${dto.negotiateState == null ||  dto.negotiateState == 0}">未审核</c:if>
                                <c:if test="${dto.negotiateState == 1}">审核通过</c:if>
                                <c:if test="${dto.negotiateState == 2}">审核未通过</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>联系电话</td>
                            <td>${dto.caseTel}</td>
                            <td>创建时间</td>
                            <td><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>案件状态变更时间</td>
                            <td><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                        <tr>
                            <td >驳回原因</td>
                            <td colspan="5">${dto.negotiateReason}</td>
                        </tr>
                        </tbody>
                    </table>
                    <c:if test="${caseFollows != null}">
                        <c:forEach items="${caseFollows}" var="item">
                            <div class="stepItem" style="margin-left: 10px">
                                <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                                <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followBy}<br>内容：${item.followDesc}<br>下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div><div class="stepDetail-detail"></div></div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${caseFollows.size() == 0}">
                        <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                            暂无跟踪记录
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function selectPaymentEstimateApply(type,caseId){
        openDialog({
            frame:true,
            title:"案件理赔测算历史记录",
            height:700,
            width:1000,
            url:"${ctx}/case/risk/selectPaymentEstimateApply?type="+type+"&caseId="+caseId
        });
    }

    var getreport = function(id,caseNo,negotiateState){
        openDialog({
            frame:true,
            title:"报价审核",
            height:600,
            width:1000,
            url:"${ctx}/case/risk/paymentEstimateReportList?id=${payEstimateInquiry.paymentEstimateId}&caseNo="+caseNo+"&caseId="+id+"&negotiateState="+negotiateState,
            load : true
        });
    }

    function selectFileMid(caseNo){
        openDialog({
            frame:true,
            title:"查看单证",
            height:600,
            width:1000,
            url:"${ctx}/case/selectFileMid?caseNo="+caseNo
        });
    }
    function selectCaseDetails(type,caseId,caseType,caseNo){
        openDialog({
            frame:true,
            title:"查看进度",
            height:500,
            width:1000,
            url:"${ctx}/case/selectCaseDetails?type="+type+"&caseId="+caseId+"&caseType="+caseType+"&caseNo="+caseNo
        });
    }
</script>
</body>
</html>
