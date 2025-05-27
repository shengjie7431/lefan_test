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
        <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
            <input type="hidden" name="corporationId" value="${applyCorporationId}">
            <input type="hidden" name="billingEnumId" value="${billingEnumId}">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" id="roleIds" name="roleIds" value="${roleIds}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">名称</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${bullingEnums}" var="item">
                <tr>
                    <td>
                        <input type="checkbox" name="buss" value="${item.enumCode}"
                                <c:if test="${type == 1 }">
                                    <c:forEach items="${corporationEnums}" var="my">
                                        <c:if test="${my.billingEnumId == item.enumCode }">
                                            checked="checked"
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${type == 2 }">
                                    <c:forEach items="${enumItems}" var="my">
                                        <c:if test="${my.billingItemId == item.enumCode }">
                                            checked="checked"
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                        />
                    </td>
                    <td>
                        ${item.enumName}
                    </td>
                    </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <%--<div class="form-group">--%>
                <%--<table class="table">--%>
                    <%--<tbody>--%>

                    <%--<tr>--%>
                        <%--<th width="30%" class="active">产品名称</th>--%>
                        <%--<td width="70%">--%>
                            <%--<select name="billingEnumId" class="form-control" required="required">--%>
                                <%--<option value="">全部</option>--%>
                                <%--<c:forEach items="${bullingEnums}" var="item">--%>
                                    <%--<option <c:if test="${billingEnumId == item.enumCode}">selected="selected" </c:if> value="${item.enumCode}" >${item.enumName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<th width="30%" class="active">项目名称</th>--%>
                        <%--<td width="70%">--%>
                            <%--<select name="billingItemId" class="form-control" required="required">--%>
                                <%--<option value="">全部</option>--%>
                                <%--<c:forEach items="${bullingItems}" var="item">--%>
                                    <%--<option <c:if test="${billingItemId == item.enumCode}">selected="selected" </c:if> value="${item.enumCode}" >${item.enumName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--</tbody>--%>
                <%--</table>--%>
            <%--</div>--%>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
                <%--<button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>--%>
            </div>
        </form>
    </div>
</div>


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
</script>
</body>
</html>