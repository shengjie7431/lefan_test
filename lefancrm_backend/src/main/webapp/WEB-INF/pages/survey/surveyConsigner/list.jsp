<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托方认证列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>委托人认证列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       委托方姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                   </div>
                    <div class="form-group">
                        委托方电话:<input name="tel" type="text" value="${tel}" class="form-control">
                    </div>
                    <div class="form-group">
                        单位:<input name="company" type="text" value="${company}" class="form-control">
                    </div>
                    <div class="form-group">
                        认证状态:
                        <select name="authState"  class="form-control">
                            <option value=""  <c:if test="${authState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${authState == '0'}">selected="selected" </c:if> >未认证</option>
                            <option value="1" <c:if test="${authState == '1'}">selected="selected" </c:if> >认证中</option>
                            <option value="2" <c:if test="${authState == '2'}">selected="selected" </c:if> >认证通过</option>
                            <option value="3" <c:if test="${authState == '3'}">selected="selected" </c:if> >认证未通过</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <%--&nbsp; &nbsp;<button onclick="add('${surveyCode}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;--%>
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">委托方姓名</th>
                <th width="100">委托方电话</th>
                <th width="150">单位简称</th>
                <th width="150">单位全称</th>
                <th width="150">委托部门</th>
                <th width="80">认证状态</th>
                <th width="80">账户状态</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.tel}</td>
                    <td>${item.company}</td>
                    <td>${item.entrustOrgName}</td>
                    <td>${item.departmentNames}</td>
                    <td>
                        <c:if test="${item.authState==0}">未认证</c:if>
                        <c:if test="${item.authState==1}">认证中</c:if>
                        <c:if test="${item.authState==2}">认证通过</c:if>
                        <c:if test="${item.authState==3}">认证不通过</c:if>
                    </td>
                    <td>
                        <c:if test="${item.accState==0}">正常</c:if>
                        <c:if test="${item.accState==1}">冻结</c:if>
                        <c:if test="${item.accState==2}">删除</c:if>
                        <span class="row-actions">
                            <c:if test="${item.accState!=0}"><a href="javascript:operate('${item.id}','${surveyCode}','1700',true);"><span class="glyphicon glyphicon-ok"></span>启用</a></c:if>
                            <c:if test="${item.accState==0}"><a href="javascript:operate('${item.id}','${surveyCode}','1800',true);"><span class="glyphicon glyphicon-remove"></span>冻结</a></c:if>
                        </span>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&userName=${userName}&company=${company}&tel=${tel}&authState=${authState}" />
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

    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
                        location.reload();
                    }
                })
            }
        }else {

        }
    }

</script>
</body>
</html>
