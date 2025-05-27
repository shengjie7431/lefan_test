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
    <title>提现数据</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>提现数据 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="menuCode" value="${menuCode}">
                    <div class="form-group">
                        案件名称: <input name="surveyCaseName" type="text"  value="${surveyCaseName}" class="form-control">
                    </div>
                    <div class="form-group">
                        机构名称: <input name="franchiseeName" type="text"  value="${franchiseeName}" class="form-control">
                    </div>
                    <div class="form-group">
                        调查员姓名: <input name="surveyUserName" type="text"  value="${surveyUserName}" class="form-control">
                    </div>
                    <div class="form-group">
                        提现状态:
                        <select name="cashState" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${cashState == '1'}">selected="selected" </c:if> >未提现</option>
                            <option value="2" <c:if test="${cashState == '2'}">selected="selected" </c:if> >提现中</option>
                            <option value="3" <c:if test="${cashState == '3'}">selected="selected" </c:if> >提现成功</option>
                            <option value="4" <c:if test="${cashState == '4'}">selected="selected" </c:if> >提现失败</option>
                        </select>
                    </div>
                    <div class="form-group">
                        类型:
                        <select name="cashType" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${cashType == '1'}">selected="selected" </c:if> >基本费</option>
                            <option value="2" <c:if test="${cashType == '2'}">selected="selected" </c:if> >减损奖励</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="cash('${surveyCode}')" type="button" class="btn btn-default">提现</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">案件名称</th>
                <th width="150">机构名称</th>
                <th width="100">调查员姓名</th>
                <th width="150">任务价格</th>
                <th width="100">提现状态</th>
                <th width="120">类型</th>
                <th width="100">创建时间</th>
                <%--<th width="100">操作</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyCaseName}</td>
                    <td>${item.franchiseeName}</td>
                    <td>${item.surveyUserName}</td>
                    <td>${item.surveyTaskMoney}</td>
                    <td>
                        <c:if test="${item.cashState == 1}">未提现</c:if>
                        <c:if test="${item.cashState == 2}">提现中</c:if>
                        <c:if test="${item.cashState == 3}">提现成功</c:if>
                        <c:if test="${item.cashState == 4}">提现失败</c:if>
                    </td>
                    <td>
                        <c:if test="${item.cashType == 1}">基本费</c:if>
                        <c:if test="${item.cashType == 2}">减损奖励</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <%--<td>--%>
                        <%--<a href="javascript:info('${item.id}','${surveyCode}');">详情</a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&cashState=${cashState}&franchiseeName=${franchiseeName}&surveyUserName=${surveyUserName}&cashType=${cashType}&menuCode=${menuCode}&surveyCaseName=${surveyCaseName}" />
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

    var cashInfoState ="";
    var cash = function(surveyCode){
        $.ajax({
            url:'${ctx}/surveyCashInfoRecord/cashInfoState',
            type:"Get",
            success:function(res,param){
                cashInfoState = param.data.results;
                nextByCashInfoState(surveyCode,cashInfoState);
            }
        });
    }

    function nextByCashInfoState(surveyCode,cashInfoState) {
        if(cashInfoState == 1 || cashInfoState == 4 ){
            openDialog({
                frame:true,
                title:"可提现数据",
                height:800,
                width:1200,
                url:"${ctx}/baseSurvey/list?surveyCode="+surveyCode+"&menuCode=toBe-cash-list"
            });
        }else if(cashInfoState == 2){
            alert("本月已提现");
            return false;
        }else if(cashInfoState == 3){
            alert("每月1号-7号开放体现");
            return false;
        }else if(cashInfoState == 5){
            var message = confirm("有未确认到账？");
            if(!message){
                return false;
            }else{
                $.ajax({
                    url:'${ctx}/baseSurvey/operate?surveyCode='+surveyCode+'&btnCode=1200',
                    type:"Get",
                    success:function(res,param){
                        openDialog({
                            frame:true,
                            title:"可提现数据",
                            height:800,
                            width:1200,
                            url:"${ctx}/baseSurvey/list?surveyCode="+surveyCode+"&menuCode=toBe-cash-list"
                        });
                    }
                });
            }
        }
    }

</script>
</body>
</html>
