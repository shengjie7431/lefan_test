<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人业务台账列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
    </style>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            text-align: center;
            border-left: 1px solid #ddd;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>个人业务台账列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/casePersonalInfoList" method="post">
                    <div class="form-group">
                        <label class="title">案件编号:</label>
                        <input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">用户姓名:</label>
                        <input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">用户电话:</label>
                        <input name="userTel" type="text" value="${userTel}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">业务员姓名:</label>
                        <input name="salesmanName" type="text" value="${salesmanName}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">录入时间：</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">机构名称:</label>
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">案件类型:</label>
                        <select name="caseType"  class="form-control">
                            <option value=""  <c:if test="${caseType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${caseType == '1'}">selected="selected" </c:if> >垫付案件</option>
                            <option value="2" <c:if test="${caseType == '2'}">selected="selected" </c:if> >代理案件</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">目标客户:</label>
                        <select name="isTarget"  class="form-control">
                            <option value=""  <c:if test="${isTarget == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isTarget == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isTarget == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">签约客户:</label>
                        <select name="isSign"  class="form-control">
                            <option value=""  <c:if test="${isSign == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isSign == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isSign == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">申请金融:</label>
                        <select name="isApplyFinance"  class="form-control">
                            <option value=""  <c:if test="${isApplyFinance == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isApplyFinance == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isApplyFinance == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/casePersonalInfoListExport?orgId=${orgId}&caseNo=${caseNo}&userName=${userName}&userTel=${userTel}&caseType=${caseType}&isTarget=${isTarget}&isSign=${isSign}&isApplyFinance=${isApplyFinance}&startDate=${startDate}&endDate=${endDate}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th style="min-width:100px">案件编号</th>
                    <th style="min-width:100px">案件类型</th>
                    <th style="min-width:100px">用户姓名</th>
                    <th style="min-width:100px">用户电话</th>
                    <th style="min-width:100px">业务员姓名</th>
                    <th style="min-width:120px">是否是目标客户</th>
                    <th style="min-width:100px">是否是签约客户</th>
                    <th style="min-width:100px">是否申请金融</th>
                    <th style="min-width:120px">应收服务费金额</th>
                    <th style="min-width:100px">到账金额</th>
                    <th style="min-width:120px">录入时间</th>
                    <th style="min-width:160px">机构名称</th>
                    <th style="min-width:100px">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td><a href="javascript:casePersonalInfoView('${item.id}');">${item.caseNo}</a></td>
                        <td>
                            <c:if test="${item.caseType == 1}">
                                垫付
                            </c:if>
                            <c:if test="${item.caseType == 2}">
                                代理
                            </c:if>
                        </td>
                        <td>${item.userName}</td>
                        <td>${item.userTel}</td>
                        <td>${item.salesmanName}</td>
                        <td>
                            <c:if test="${item.isTarget == 0 ||item.isTarget == null}">
                                否
                            </c:if>
                            <c:if test="${item.isTarget == 1}">
                                是
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isSign == 0 || item.isSign == null}">
                                否
                            </c:if>
                            <c:if test="${item.isSign == 1}">
                                是
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isApplyFinance == 0 || item.isApplyFinance == null}">
                                否
                            </c:if>
                            <c:if test="${item.isApplyFinance == 1}">
                                是
                            </c:if>
                        </td>
                        <td>${item.receivableServiceMoney == null ? 0.00 : item.receivableServiceMoney}</td>
                        <td>${item.receivedMoney == null ? 0.00 : item.receivedMoney}</td>
                        <td><fmt:formatDate value="${item.intoTime}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.orgName}</td>
                        <td><a href="javascript:casePersonalInfoView('${item.id}');">查看</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/reportForm/casePersonalInfoList?orgId=${orgId}&caseNo=${caseNo}&userName=${userName}&userTel=${userTel}&caseType=${caseType}&isTarget=${isTarget}&isSign=${isSign}&isApplyFinance=${isApplyFinance}&startDate=${startDate}&endDate=${endDate}&salesmanName=${salesmanName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    var casePersonalInfoView = function(id){
        openDialog({
            frame:true,
            title:"查看详情",
            height:650,
            width:1000,
            url:"${ctx}/reportForm/casePersonalInfoView?id="+id
        });
    }
</script>
</body>
</html>
