<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件分配列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件分配列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->


    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseCenterInfoAllot/caseCenterInfoAllotList" method="post">
                    <div class="form-group">
                        案件类型: <select name="type"  class="form-control">
                        <option value="" <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>
                        <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >贷款申请</option>
                        <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >代理申请</option>
                        <option value="3" <c:if test="${type == '3'}">selected="selected" </c:if> >伤残预估</option>
                        <option value="4" <c:if test="${type == '4'}">selected="selected" </c:if> >其他</option>
                    </select>
                    </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        案件阶段: <select name="gradationState"  class="form-control">
                        <option value="" <c:if test="${gradationState == ''}">selected="selected" </c:if> >全部</option>
                        <option value="1" <c:if test="${gradationState == '1'}">selected="selected" </c:if> >洽谈阶段</option>
                        <option value="2" <c:if test="${gradationState == '2'}">selected="selected" </c:if> >评估阶段</option>
                        <option value="3" <c:if test="${gradationState == '3'}">selected="selected" </c:if> >索赔阶段</option>
                        <option value="4" <c:if test="${gradationState == '4'}">selected="selected" </c:if> >结案</option>
                        <option value="5" <c:if test="${gradationState == '5'}">selected="selected" </c:if> >风控部门审核阶段</option>
                        <option value="6" <c:if test="${gradationState == '6'}">selected="selected" </c:if> >诉讼阶段</option>
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
                    &nbsp;&nbsp;
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        申请人姓名: <input name="caseName" type="text"  value="${caseName}" class="form-control">
                    </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        申请人电话: <input name="caseTel" type="text"  value="${caseTel}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">案件编号</th>
                <th width="120">类型</th>
                <th width="90">阶段</th>
                <th width="90">状态</th>
                <th width="300">案件标题</th>
                <th width="100">申请人姓名</th>
                <th width="80">申请人电话</th>
                <th width="80">被分配人</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.type == 1}">贷款申请</c:if>
                        <c:if test="${item.type == 2}">代理申请</c:if>
                        <c:if test="${item.type == 3}">伤残预估</c:if>
                        <c:if test="${item.type == 4}">其他</c:if>
                    </td>
                    <td>
                        <c:if test="${item.gradationState == 1}">
                            <span style="color: #FF4500">洽谈阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 2}">
                            <span style="color: #66CD00">评估阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 3}">
                            <span style="color: #66CD00">索赔阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 4}">
                            <span style="color: #1E90FF">结案</span>
                        </c:if>
                        <c:if test="${item.gradationState == 5}">
                            <span style="color: #1E90FF">风控部门审核阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 6}">
                            <span style="color: #1E90FF">诉讼阶段</span>
                        </c:if>
                    </td>
                    <td>${item.caseStateStr}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>${item.caseTel}</td>
                    <td>${item.orgUserName}</td>
                    <td><a href="javascript:caseCenterInfoAllotView('${item.id}');">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/caseCenterInfoAllot/caseCenterInfoAllotList?gradationState=${gradationState}&caseName=${caseName}&caseNo=${caseNo}&caseState=${caseState}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var caseCenterInfoAllotView = function(id){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:600,
            width:1000,
            url:"${ctx}/caseCenterInfoAllot/caseCenterInfoAllotView?id="+id,
            load : true
        });
    }

</script>
</body>
</html>
