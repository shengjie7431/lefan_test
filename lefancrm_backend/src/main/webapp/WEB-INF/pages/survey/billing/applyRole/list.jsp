<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="roleId" value="${roleId}">
            <input type="hidden" id="ids" name="ids" value="${ids}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">名称</th>
                    <th width="150">角色</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <input type="checkbox" name="buss"
                            <c:if test="${surveyCode =='product' || surveyCode=='corp' || surveyCode =='org'}">
                                value="${item.id}"
                            </c:if>
                            <c:if test="${surveyCode =='enum' || surveyCode=='item'}">
                                value="${item.enumCode}"
                            </c:if>
                            <c:if test="${item.selected}">
                                   checked="checked"
                            </c:if>
                    </td>
                    <td>
                        <c:if test="${surveyCode =='product' || surveyCode=='corp'}">
                            ${item.name}
                        </c:if>
                        <c:if test="${surveyCode =='enum' || surveyCode=='item'}">
                            ${item.enumName}
                        </c:if>
                        <c:if test="${surveyCode =='org'}">
                            ${item.orgName}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${surveyCode =='product' || surveyCode=='corp' || surveyCode =='org'}">
                            <a href="javascrpit:void(0);" onclick="setRole(${item.id},'setrole','${surveyCode}')">设置角色</a>
                        </c:if>
                        <c:if test="${surveyCode =='enum' || surveyCode=='item'}">
                            <a href="javascrpit:void(0);" onclick="setRole(${item.enumCode},'setrole','${surveyCode}')">设置角色</a>
                        </c:if>

                    </td>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/popup?surveyCode=${surveyCode}&name=${name}" />
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
        $("#editForm").bind('submit', function(event) {
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

    function setRole(id,setrole,surveyCode){
        openDialog({
            frame:true,
            title:"设置角色",
            height:500,
            width:750,
            url:"${ctx}/baseSurvey/popup?leftId="+id+"&surveyCode="+setrole+"&searchType="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
