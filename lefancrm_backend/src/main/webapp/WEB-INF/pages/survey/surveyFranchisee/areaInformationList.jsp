<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>片区信息</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
<%--        <h3>名下片区 <small>共<span>${apiRsp.count}</span>个</small></h3>--%>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/popup" method="post">
                    <input type="hidden" id="franchiseeId" name="id" value="${id}">
                    <input type="hidden" id="surveyCode" name="surveyCode" value="areaInformation">
                    <input type="hidden" id="btnCode" name="btnCode" value="2000">
                    <div class="form-group">
                        片区名称:<input id="surveyAreaName" name="surveyAreaName" type="text" value="${surveyAreaName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp; <button onclick="operation('${id}','2000',false)" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
                <tr>
                    <th width="80">所属机构</th>
                    <th width="100">片区名称</th>
                    <th width="150">创建人</th>
                    <th width="150">创建时间</th>
                    <th width="150">操作</th>
                </tr>
            </thead>
            <tbody class="class-list">
                <c:forEach items="${surveyOrgAreaList}" var="item">
                    <tr>
                        <td>${item.surveyOrgName}</td>
                        <td><a href="javascript:void(0)" onclick="operation('${item.id}','6000',false)">${item.surveyAreaName}</a></td>
                        <td>${item.createBy}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                            <a href="javascript:void(0)" onclick="operation('${item.id}','3000',false)">添加人员</a>
                            <a href="javascript:void(0)" onclick="operation('${item.id}','4000',false)">修改</a>
                            <a href="javascript:void(0)" onclick="operation('${item.id}','5000',true)">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<%--    <div class="main-bottom">--%>
<%--        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">--%>
<%--            <jsp:param name="paginationObjectName" value="apiRsp" />--%>
<%--            <jsp:param name="pageNoName" value="" />--%>
<%--            <jsp:param name="requestUrl" value="${ctx}/surveyFranchisee/selectInvestigator?btnCode=${btnCode}&realName=${realName}&tel=${tel}&id=${franchiseeId}" />--%>
<%--            <jsp:param name="refreshDiv" value="" />--%>
<%--        </jsp:include>--%>
<%--    </div><!--main-bottom-->--%>

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    // $("#editForm").bind('submit', function(event) {
    //     //$("#content").text(editor1.html());
    //     ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
    //     event.preventDefault();
    // });

    function  operation(id,btnCode,ajax) {
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"surveyCode":"delAreaInformation","id":id,"deleteFlag":"1"};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
//                        reloadParent();//删除时，需刷新父级
                    location.reload();
                })
            }
        }else{
             if(btnCode == '2000'){
                    title="添加";
                    height=350;
                    width=600;
                    url="${ctx}/baseSurvey/add?surveyCode=areaInformation&franchiseeId="+id;
            }else if(btnCode == '3000'){
                var franchiseeId=$("#franchiseeId").val();
                title="添加人员";
                height=750;
                width=1000;
                url="${ctx}/baseSurvey/add?surveyCode=addAreaPersonnel&franchiseeId="+franchiseeId+"&category=add&areaId="+id;
            }else if(btnCode == '6000'){
                var franchiseeId=$("#franchiseeId").val();
                title="查看人员";
                height=750;
                width=1000;
                url="${ctx}/baseSurvey/add?surveyCode=addAreaPersonnel&franchiseeId="+franchiseeId+"&category=select&areaId="+id;
            }else if(btnCode == '4000'){
                    title="修改";
                    height=350,
                    width=900,
                    url="${ctx}/baseSurvey/edit?surveyCode=areaInformationSelectOne&id="+id;
            }
                openDialog({
                    frame:true,
                    title:title,
                    height:height,
                    width:width,
                    load:true,
                    url: url
                });
            }
    }
</script>
</body>
</html>
