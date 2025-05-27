<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>


    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/staff/update" method="post">
            <input type="hidden" name="businessUnitId" id="businessUnitId" value="${businessUnitId}">
            <input type="hidden" name="companyId" id="companyId" value="${companyId}">
            <input type="hidden" name="surveyCode" value="budgetCompanyOrgan">
            <input type="hidden" id="roleIds" name="roleIds" value="${roleIds}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <table class="table table-hover">
                <c:if test="${btnCode ==1000}">
                    <thead>
                        <tr>
                            <th width="80"><input type="checkbox" id="all" >全选</th>
                            <th width="100">机构/部门名称</th>
                        </tr>
                    </thead>
                    <tbody class="class-list">
                        <c:forEach items="${organs}" var="item">
                            <tr>
                                <td>
                                    <input type="checkbox" name="buss" value="${item.id}"
                                        <c:forEach items="${companyOrgans}" var="my">
                                            <c:if test="${my.organId == item.id }">checked="checked"</c:if>
                                        </c:forEach>/>
                                </td>
                                <td>${item.name}</td>
                            </tr>
                            </c:forEach>
                        <tr>
                            <td colspan="10">
                                <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                            </td>
                        </tr>
                    </tbody>
                </c:if>


                <c:if test="${btnCode ==2000}">
                    <thead>
                    <tr>
                        <th width="100">机构/部门名称</th>
<%--                        <th width="100">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${companyOrgans}" var="item">
                        <tr>
                            <td>
                                ${item.organName}
                            </td>
                           <%-- <td>
                                <a href="javascript:fitDepartment('${item.organId}','organ','1000','设置科室');">设置科室</a>
                                <a href="javascript:fitDepartment('${item.organId}','organ','2000','名下科室');">名下科室</a>
                            </td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
            </table>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </div>
        </form>
    </div>
</div>


</div><!--main end-->

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {
            $("#batchOperateBtn").attr("disabled",true);
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
        $("#roleIds").val(idArr);
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

    var fitDepartment = function(organId,surveyCode,btnCode,title){
        var companyId = $("#companyId").val();
        openDialog({
            frame:true,
            title:title,
            height:600,
            width:1300,
            url:"${ctx}/staff/popup?companyId="+companyId+"&organId="+organId+"&surveyCode="+surveyCode+"&btnCode="+btnCode,
            load:true
        });
    }
</script>
</body>
</html>