<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>人员 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/surveyConsignor/selectUserInfo?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="btnCode" value="${btnCode}">
                    <input type="hidden" name="id" value="${consignorId}">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <div class="form-group">
                        用户姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        联系电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('userInfo')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">用户姓名</th>
                <th width="150">昵称</th>
                <th width="150">联系电话</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.nickName}</td>
                    <td>${item.userTel}</td>
                    <td>
                        <c:if test="${surveyCode =='consignor'}">
                            <a href="javascript:entrustNer('${item.userId}','${item.userName}','consigner','${btnCode}','${consignorId}');">委托人认证</a>
                        </c:if>
                        <c:if test="${surveyCode =='franchisee'}">
                            <a href="javascript:entrustTor('${item.userId}','${item.userName}','investigator','${btnCode}','${consignorId}');">调查员认证</a>
                        </c:if>
                        <a href="javascript:edit('${item.userId}','userInfo');">修改</a>
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
            <jsp:param name="requestUrl" value="${ctx}/surveyConsignor/selectUserInfo?btnCode=${btnCode}&userName=${userName}&surveyCode=${surveyCode}&id=${consignorId}&userTel=${userTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function operate(userId,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"userId":userId,"surveyCode":surveyCode,"btnCode":btnCode,"consignorId":${consignorId}};
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
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

    var entrustNer = function(userId,userName,surveyCode,btnCode,consignorId){
        openDialog({
            frame:true,
            title:"认证",
            height:550,
            width:1000,
            url:"${ctx}/surveyConsignor/entrust?userId="+userId+"&userName="+userName+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&consignorId="+consignorId
        });
    }

    var entrustTor = function(userId,userName,surveyCode,btnCode,consignorId){
        openDialog({
            frame:true,
            title:"认证",
            height:550,
            width:1000,
            url:"${ctx}/surveyFranchisee/entrust?userId="+userId+"&userName="+userName+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&consignorId="+consignorId
        });
    }

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var edit = function(userId,surveyCode){
        openDialog({
            frame:true,
            title:"修改",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/edit?surveyCode="+surveyCode+"&userId="+userId
        });
    }
</script>
</body>
</html>
