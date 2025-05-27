<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托人列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>委托人数据</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="entrustOrgId" value="${entrustOrgId}">
            <input type="hidden" id="consignerIds" name="consignerIds" value="${consignerIds}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="50"><input type="checkbox" id="all" >全选</th>
                    <th width="100">用户姓名</th>
                    <th width="100">联系电话</th>
                    <th width="100">所在部门</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${consigneres}" var="item">
                    <tr>
                        <td>
<%--                            <c:if test="${item.departmentId ==null}">--%>
                                <input type="checkbox" value="${item.id}" name="consigners" >
                                <input type="hidden" value="${item.userName}" id="name_${item.id}" />
<%--                            </c:if>--%>
                        </td>
                        <td>${item.userName}</td>
                        <td>${item.tel}</td>
                        <td>${item.departmentNames}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit"  class="btn btn-default">确定提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/popup?btnCode=${btnCode}&realName=${realName}&surveyCode=${surveyCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='consigners']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {
            _checkRole();
            ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
            event.preventDefault();
        });

        do_list_checkbox();
    });

    function _checkRole(){
        var check_name = document.getElementsByName("consigners");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        $("#consignerIds").val(idArr);
    }

    function do_list_checkbox(){
        var checkValue = "${userRoleIds}";
        $("input[name=consigners]").each(function (){
            var indexCode = checkValue.indexOf(","+$(this).val()+",");
            if(indexCode>-1){
                $(this).attr("checked","checked");
            }
        });
    }

</script>
</body>
</html>
