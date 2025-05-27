<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>处罚清单</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .title_sort{
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }
        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>处罚清单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                   <div class="form-group">
                       调查人员姓名:<input name="surveyUserName" type="text" value="${surveyUserName}" class="form-control">
                   </div>
                    <div class="form-group">
                        执行状态:
                        <select name="isExec"  class="form-control">
                            <option value=""  <c:if test="${isExec == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isExec == '0'}">selected="selected" </c:if> >未执行</option>
                            <option value="1" <c:if test="${isExec == '1'}">selected="selected" </c:if> >已执行</option>
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
                <th width="100">调查人员姓名</th>
                <th width="200">处罚事由</th>
                <th width="100">处罚人</th>
                <th width="100">执行状态</th>
                <th width="120">
                    <div class="title_sort" data-id="" data-value="" data-field="createTime">
                        <span>时间</span>
                        <div class="icon-sort">
                            <div class="icon-up"></div>
                            <div class="icon-down"></div>
                        </div>
                    </div>
                </th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyUserName}</td>
                    <td>${item.remark}</td>
                    <td>${item.dealUserName}</td>
                    <td>
                        <c:if test="${item.isExec ==0}">未执行</c:if>
                        <c:if test="${item.isExec ==1}">已执行</c:if>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&surveyUserName=${surveyUserName}&isExec=${isExec}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(".title_sort").click(function () {
        var sortField = $(this).attr("data-field");
        var sortType = 'up';
        var $children = $(this).children();
        if ($children.find(".active").html()==undefined){
            $children.find(".icon-up").addClass("active");
        }else {
            var t = $children.find(".active").attr("class");
            if (t.toString().indexOf("up")>0){
                $children.find(".icon-up").removeClass("active");
                $children.find(".icon-down").addClass("active");
                sortType = 'down';
            }else {
                $children.find(".icon-up").addClass("active");
                $children.find(".icon-down").removeClass("active");
                sortType = 'up';
            }
        }
        $("#sortField").val(sortField);
        $("#sortType").val(sortType);
        $('#batchOperateBtn').click();
    });

    $(function () {
        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }
    });

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

</script>
</body>
</html>
