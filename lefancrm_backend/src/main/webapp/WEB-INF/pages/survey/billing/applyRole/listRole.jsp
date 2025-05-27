<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>角色列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="leftId" value="${leftId}">
                    <input type="hidden" name="searchType" value="${searchType}">
                    <input type="hidden" name="noPageIndex" value="${noPageIndex}">
                    <div class="form-group">
                        角色名:<input name="roleName" type="text" value="${roleName}" class="form-control">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>
        <form id="editFormTwo" role="form" action="${ctx}/baseSurvey/operate" method="post">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="leftId" value="${leftId}">
            <input type="hidden" name="searchType" value="${searchType}">
            <input type="hidden" name="noPageIndex" value="${noPageIndex}">
            <input type="hidden" id="ids" name="ids" value="${ids}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">角色名</th>
                    <th width="100">角色描述</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>
                            <input type="checkbox" name="buss" value="${item.id}"
                            <c:if test="${item.selected}">
                                   checked="checked"
                            </c:if>
                        </td>
                        <td>${item.roleName}</td>
                        <td>${item.roleDesc}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="okSubmit" type="submit" class="btn btn-default">确定提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?roleName=${roleName}&surveyCode=${surveyCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editFormTwo").bind('submit', function(event) {
            _checkRole();
            ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
            event.preventDefault();
        });

        do_list_checkbox();
    });

    function _checkRole(){
        var check_name = document.getElementsByName("buss");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        $("#ids").val(idArr);
    }

    function do_list_checkbox(){
        var checkValue = "${userRoleIds}";
        $("input[name=buss]").each(function (){
            var indexCode = checkValue.indexOf(","+$(this).val()+",");
            if(indexCode>-1){
                $(this).attr("checked","checked");
            }
        });
    }
</script>
</body>
</html>
