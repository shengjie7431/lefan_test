<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件审核列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件审核列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseApplication/caseApplicationInfoList" method="post">
                    <input type="hidden" name="numberType" value="${numberType}">
                    <div class="form-group">
                        案件类型:
                        <select name="type"  class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${type == 1}">selected="selected" </c:if>>代理申请</option>
                            <option value="2" <c:if test="${type == 2}">selected="selected" </c:if>>垫付申请</option>
                        </select>
                    </div>
                    <div class="form-group">
                        审核状态:
                        <select name="state"  class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${state == 1}">selected="selected" </c:if>>待审核</option>
                            <option value="2" <c:if test="${state == 2}">selected="selected" </c:if>>审核通过</option>
                            <option value="3" <c:if test="${state == 3}">selected="selected" </c:if>>驳回</option>
                        </select>
                    </div>
                    <div class="form-group">
                        支付状态:
                        <select name="isFined"  class="form-control">
                            <option value=""  <c:if test="${isFined == -1}">selected="selected" </c:if>>全部</option>
                            <option value="0" <c:if test="${isFined == 0}">selected="selected" </c:if>>已扣罚</option>
                            <option value="1" <c:if test="${isFined == 1}">selected="selected" </c:if>>已支付</option>
                        </select>
                    </div>
                    <div class="form-group">
                        是否生产案件:
                        <select name="isTestcase"  class="form-control">
                            <option value="-1">全部</option>
                            <option value="0" <c:if test="${isTestcase == 0}">selected="selected" </c:if>>是</option>
                            <option value="1" <c:if test="${isTestcase == 1}">selected="selected" </c:if>>否</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        推广人姓名:<input name="promoterName" type="text" value="${promoterName}" class="form-control">
                    </div>
                    <div class="form-group">
                        推广人电话:<input name="promoterPhone" type="text" value="${promoterPhone}" class="form-control">
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
                <th width="100">案件编号</th>
                <th width="100">案件类型</th>
                <th width="150">案件申请人</th>
                <th width="150">用户电话</th>
                <th width="150">审核状态</th>
                <th width="150">推广人</th>
                <th width="150">推广人手机号</th>
                <th width="150">支付状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.type == 1}"> 代理申请</c:if>
                        <c:if test="${item.type == 2}"> 垫付申请</c:if>
                    </td>
                    <td>${item.userName}</td>
                    <td>${item.userPhone}</td>

                    <td><c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">驳回</c:if>
                        <c:if test="${item.state == 4}">已受理</c:if>
                        <c:if test="${item.state == 10}">材料收集中</c:if>
                        <c:if test="${item.state == 11}">诉前调解</c:if>
                        <c:if test="${item.state == 12}">申请鉴定</c:if>
                        <c:if test="${item.state == 13}">鉴定中</c:if>
                        <c:if test="${item.state == 14}">待立案</c:if>
                        <c:if test="${item.state == 15}">已立案</c:if>
                        <c:if test="${item.state == 16}">开庭</c:if>
                        <c:if test="${item.state == 17}">已调解/判决</c:if>
                        <c:if test="${item.state == 18}">已上诉</c:if>
                        <c:if test="${item.state == 19}">补充证据</c:if>
                        <c:if test="${item.state == 20}">赔偿款已支付</c:if>
                        <c:if test="${item.state == 21}">贷款已还款</c:if>
                        <c:if test="${item.state == 22}">结案</c:if>
                    </td>
                    <td>${item.promoterName}</td>
                    <td>${item.promoterPhone}</td>
                    <td>
                        <c:if test="${item.isFined == null}"> </c:if>
                        <c:if test="${item.isFined == 0}"> 已扣罚</c:if>
                        <c:if test="${item.isFined == 1}"> 已支付</c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td><a href="javascript:caseApplicationView('${item.caseNo}','${item.type}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/caseApplication/caseApplicationInfoList?userName=${userName}&state=${state}&caseNo=${caseNo}&isFined=${isFined}&type=${type}&promoterName=${promoterName}&promoterPhone=${promoterPhone}&numberType=${numberType}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var caseApplicationView = function(caseNo,type){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:500,
            width:1000,
            url:"${ctx}/caseApplication/caseApplicationView?caseNo="+caseNo+"&type="+type,
            load:true
        });
    }

</script>
</body>
</html>
