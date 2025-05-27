<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>平台基础金额信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>平台基础金额信息</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/orderInfoList" method="post">
                    <div class="form-group">

                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">押金</th>
                <th width="150">一级推广金额</th>
                <th width="150">二级推广金额</th>
                <th width="150">三级推广金额</th>
                <th width="150">案源使用扣除金额</th>
                <th width="150">提现金额限额</th>
                <th width="150">一级案件成交金额</th>
                <th width="150">二级案件成交金额</th>
                <th width="150">三级案件成交金额</th>
                <th width="150">贷款利率</th>
                <th width="150">可贷款比例</th>
                <th width="150">一级保代公司推广金额</th>
                <th width="150">二级保代公司推广金额</th>
                <th width="150">三级保代公司推广金额</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
               <tr>
                    <td>${distributionBasicDtos.foregiftMoney}</td>
                    <td>${distributionBasicDtos.oneLevelMoney}</td>
                    <td>${distributionBasicDtos.twoLevelMoney}</td>
                    <td>${distributionBasicDtos.threeLevelMoney}</td>
                    <td>${distributionBasicDtos.caseMoney}</td>
                    <td>${distributionBasicDtos.withdrawalsMoney}</td>
                    <td>${distributionBasicDtos.oneCaseMoney}</td>
                    <td>${distributionBasicDtos.twoCaseMoney}</td>
                    <td>${distributionBasicDtos.threeCaseMoney}</td>
                    <td>${distributionBasicDtos.loanInterestRate}</td>
                    <td>${distributionBasicDtos.loanRate}</td>
                   <td>${distributionBasicDtos.oneCompanyMoney}</td>
                   <td>${distributionBasicDtos.twoCompanyMoney}</td>
                   <td>${distributionBasicDtos.threeCompanyMoney}</td>
                   <th>
                     <a  href="javascript:void(0)" onclick="updateInfo(${distributionBasicDtos.id})">编辑</a></th>
                </tr>
          <%--  <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/orderInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/orderInfoList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    var updateInfo = function(id){
        openDialog({
            frame:true,
            title:"修改平台基础金额信息",
            height:500,
            width:600,
            url:"${ctx}/distribution/distributionToUpdate?id="+id
        });
    }
</script>
</body>
</html>
