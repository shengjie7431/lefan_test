<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<style>
    .ellipsis {
        overflow: hidden; /*自动隐藏文字*/
        text-overflow: ellipsis;/*文字隐藏后添加省略号*/
        white-space: nowrap;/*强制不换行*/
        width: 16em;/*不允许出现半汉字截断*/
    }
</style>
<head>
    <title>提现列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>提现列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <div class="form-group">
                        提现单号: <input name="cashInfoCode" type="text"  value="${cashInfoCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人姓名: <input name="applyUserName" type="text"  value="${applyUserName}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人电话: <input name="applyUserTel" type="text"  value="${applyUserTel}" class="form-control">
                    </div>
                    <div class="form-group">
                        提现状态:
                        <select name="cashState" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${cashState == '1'}">selected="selected" </c:if> >待结算</option>
                            <option value="2" <c:if test="${cashState == '2'}">selected="selected" </c:if> >支付中</option>
                            <option value="3" <c:if test="${cashState == '3'}">selected="selected" </c:if> >已支付</option>
                            <option value="4" <c:if test="${cashState == '4'}">selected="selected" </c:if> >支付失败</option>
                        </select>
                    </div>
                    <div class="form-group">
                        到账状态:
                        <select name="confirmAccountState" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${confirmAccountState == '1'}">selected="selected" </c:if> >未到账</option>
                            <option value="2" <c:if test="${confirmAccountState == '2'}">selected="selected" </c:if> >已到账</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        交易渠道:
                        <select name="paySource" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${paySource == '1'}">selected="selected" </c:if> >第三方支付</option>
                            <option value="2" <c:if test="${paySource == '2'}">selected="selected" </c:if> >福优</option>
                            <option value="3" <c:if test="${paySource == '3'}">selected="selected" </c:if> >微信企业付款</option>
                            <option value="4" <c:if test="${paySource == '4'}">selected="selected" </c:if> >乐凡企业打款</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">提现名称</th>
                <th width="150">提现单号</th>
                <th width="100">申请人</th>
                <th width="150">申请人电话</th>
                <th width="100">提现金额</th>
                <th width="100">实际到账金额</th>
                <th width="80">提现状态</th>
                <th width="80">到账状态</th>
                <th width="100">交易渠道</th>
                <th width="180">机构名称</th>
                <th width="150">申请时间</th>
                <%--<th width="150">创建时间</th>--%>
                <th width="50">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <div class="ellipsis" title="${item.cashInfoName}">
                            ${item.cashInfoName}
                        </div>
                    </td>
                    <td>${item.cashInfoCode}</td>
                    <td>${item.applyUserName}</td>
                    <td>${item.applyUserTel}</td>
                    <td>${item.caseAmount}</td>
                    <td>${item.realAmount}</td>
                    <td>
                        <c:if test="${item.cashState == 1}">待结算</c:if>
                        <c:if test="${item.cashState == 2}">支付中</c:if>
                        <c:if test="${item.cashState == 3}">已支付</c:if>
                        <c:if test="${item.cashState == 4}">支付失败</c:if>
                    </td>
                    <td>
                        <c:if test="${item.confirmAccountState == 1}">未到账</c:if>
                        <c:if test="${item.confirmAccountState == 2}">已到账</c:if>
                    </td>
                    <td>
                        <c:if test="${item.paySource == 1}">第三方支付</c:if>
                        <c:if test="${item.paySource == 2}">福优</c:if>
                        <c:if test="${item.paySource == 3}">微信企业付款</c:if>
                        <c:if test="${item.paySource == 4}">乐凡企业打款</c:if>
                    </td>
                    <td>${item.franchiseeName}</td>

                    <td><fmt:formatDate value="${item.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <%--<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                    <td>
                        <a href="javascript:info('${item.id}','${surveyCode}');">详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&cashState=${cashState}&cashInfoCode=${cashInfoCode}&applyUserName=${applyUserName}&applyUserTel=${applyUserTel}&confirmAccountState=${confirmAccountState}&paySource=${paySource}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:800,
            width:1200,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
