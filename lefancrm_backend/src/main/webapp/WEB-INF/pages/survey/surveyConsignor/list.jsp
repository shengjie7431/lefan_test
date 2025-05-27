<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托方机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>委托方机构列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       单位简称:<input name="company" type="text" value="${company}" class="form-control">
                   </div>
                    <div class="form-group">
<%--                        区域类别:--%>
<%--                        <select name="areaType"  class="form-control">--%>
<%--                            <option value=""  <c:if test="${areaType == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                            <option value="1" <c:if test="${areaType == '1'}">selected="selected" </c:if> >省</option>--%>
<%--                            <option value="2" <c:if test="${areaType == '2'}">selected="selected" </c:if> >市</option>--%>
<%--                            <option value="3" <c:if test="${areaType == '3'}">selected="selected" </c:if> >区</option>--%>
<%--                        </select>--%>
                        商务属性：
                        <select name="businessAttr" class="form-control">
                            <option value=""  <c:if test="${businessAttr == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${businessAttr==1}">selected="selected" </c:if>>市场一部（郑哲）</option>
                            <option value="2" <c:if test="${businessAttr==2}">selected="selected" </c:if>>市场二部（曹刘强）</option>
                            <option value="3" <c:if test="${businessAttr==3}">selected="selected" </c:if>>互助</option>
                            <option value="4" <c:if test="${businessAttr==4}">selected="selected" </c:if>>正言金融（郑哲）</option>
                            <option value="5" <c:if test="${businessAttr==5}">selected="selected" </c:if>>市场三部（韩正栋）</option>
                        </select>

                    </div>
                    <div class="form-group">
<%--                        委托方类别:--%>
<%--                        <select name="type"  class="form-control">--%>
<%--                            <option value=""  <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                            <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >合作伙伴</option>--%>
<%--                            <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >散户</option>--%>
<%--                        </select>--%>
                        公司属性：
                        <select name="orgAttrTemp" class="form-control">
                            <option value=""  <c:if test="${orgAttrTemp == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${orgAttrTemp == 1}">selected="selected" </c:if>>保险公司</option>
                            <option value="2" <c:if test="${orgAttrTemp == 2}">selected="selected" </c:if>>互助机构</option>
                            <option value="3" <c:if test="${orgAttrTemp == 3}">selected="selected" </c:if>>保险公司(反欺诈)</option>
                        </select>
                    </div>
                    <div class="form-group">
                        机构状态:
                        <select name="orgState"  class="form-control">
                            <option value=""  <c:if test="${orgState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${orgState == '0'}">selected="selected" </c:if> >启用</option>
                            <option value="1" <c:if test="${orgState == '1'}">selected="selected" </c:if> >停用</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
<%--                <th width="80">code</th>--%>
                <%--<th width="150">委托方名称</th>--%>
                <th width="150">单位简称</th>
<%--                <th width="150">单位全称</th>--%>
                <%--<th width="250">委托方说明</th>--%>
<%--                <th width="80">区域类别</th>--%>
<%--                <th width="80">委托方区域</th>--%>
<%--                <th width="80">委托方类别</th>--%>
                <th width="80">公司属性</th>
                <th width="80">时效设置</th>
                <th width="80">报告模板</th>
                <th width="80">商务属性</th>
                <th width="80">开票公司</th>
                <th width="80">邮件上限值</th>
                <th width="80">报告命名规则</th>
                <th width="80">机构状态</th>
                <th width="80">申请人</th>
                <th width="80">审批人</th>
                <th width="80">创建人</th>
                <th width="100">创建时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
<%--                    <td>${item.code}</td>--%>
                    <td>${item.company}</td>
<%--                    <td>${item.name}</td>--%>
                    <%--<td>${item.remark}</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${item.areaType==1}">省</c:if>--%>
<%--                        <c:if test="${item.areaType==2}">市</c:if>--%>
<%--                        <c:if test="${item.areaType==3}">区</c:if>--%>
<%--                    </td>--%>
<%--                    <td>${item.areaName}</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${item.type==1}">合作伙伴</c:if>--%>
<%--                        <c:if test="${item.type==2}">散户</c:if>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${item.orgState==0}">启用</c:if>--%>
<%--                        <c:if test="${item.orgState==1}">停用</c:if>--%>
<%--                    </td>--%>
                    <td>
                        <c:if test="${item.orgAttr==1}">保险公司<c:if test="${item.sharp == 1}">(反欺诈)</c:if></c:if>
                        <c:if test="${item.orgAttr==2}">互助机构</c:if>
                    </td>
                    <td>
                        <c:if test="${item.efficiencyAttr==1}">工作日</c:if>
                        <c:if test="${item.efficiencyAttr==2}">自然日</c:if>
                    </td>
                    <td>${item.modelName}</td>
                    <td>
                        <c:if test="${item.businessAttr == 1}">市场一部（郑哲）</c:if>
                        <c:if test="${item.businessAttr == 2}">市场二部（曹刘强）</c:if>
                        <c:if test="${item.businessAttr == 3}">互助</c:if>
                        <c:if test="${item.businessAttr == 4}">正言金融（郑哲）</c:if>
                        <c:if test="${item.businessAttr == 5}">市场三部（韩正栋）</c:if>
                    </td>
                    <td>${item.billCompanyName}</td>
                    <td>${item.attrMaxSize}</td>
                    <td>${item.ruleName}</td>
                    <td>
                        <c:if test="${item.orgState==0}">启用</c:if>
                        <c:if test="${item.orgState==1}">停用</c:if>
                    </td>
                    <td>${item.appUser}</td>
                    <td>${item.apvUser}</td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&company=${company}&areaType=${areaType}&type=${type}&orgState=${orgState}" />
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
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:800,
            width:1500,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
