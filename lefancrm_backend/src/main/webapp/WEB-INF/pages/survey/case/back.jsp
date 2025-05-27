<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .lf-btns{
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 100px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form"
          <c:if test="${btnCode == 'org4' || btnCode == 'org2' || btnCode == 'review-help-no'}">action="${ctx}/survey/case/operateAssign"</c:if>
          <c:if test="${btnCode != 'org4' && btnCode != 'org2'}">action="${ctx}/survey/case/operate"</c:if>
          method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}">
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}" />
                <input type="hidden" id="backCaseId" name="backCaseId" value="${backCaseId}" />
                <c:if test="${btnCode == '1301'}">
                    <input type="hidden" id="chkOrgIds" name="chkOrgIds" />
                    <c:forEach items="${orgs}" var="item">
<%--                        初审通过的机构可以退回--%>
                        <c:if test="${item.orgSurveyState == 4}">
                            <tr>
                                <td>机构名称</td>
                                <td>${item.surveyOrgName}</td>
                                <td><input type="checkbox" name="chkOrgId" class="form-control" value="${item.id}"></td>
                            </tr>
                            <tr>
                                <td>退回原因</td>
                                <td colspan="2">
                                    <textarea rows="2" name="opinion${item.id}" class="form-control"></textarea>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${btnCode != '1301'}">
                    <c:if test="${btnCode == 'review-help-no'}">
                        <tr>
                            <th width="20%" class="active">退回类型</th>
                            <td width="80%">
                                <div class="rejectionType lf-btns">
                                    <div class="lf-btn active" data-type="1">驳回</div>
                                    <div class="lf-btn" data-type="2">新增方向</div>
                                </div>
                                <input type="hidden" name="rejectionType" value="1">
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <th width="20%" class="active">退回原因</th>
                        <td width="80%">
                            <textarea rows="4" required="required" name="opinion" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  onclick="return okForm()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认</button>
        </div>
    </form>
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
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });
    $('.lf-btns').on('click','.lf-btn',function () {
        var _this = $(this)
        _this.addClass('active').siblings().removeClass('active')
        $('input[name=rejectionType]').val(_this.attr('data-type'))
    })
    function okForm(){
        var btnCode = $("#btnCode").val();
        if (btnCode == '1301'){
            var orgIds = [];
            $("input:checkbox[name='chkOrgId']:checked").each(function() { // 遍历name=safeCompanys的多选框
                orgIds.push($(this).val());
            });
            if (orgIds.length == 0){
                alert("未选中任何数据");
                return false;
            }
            $("#chkOrgIds").val(orgIds);
        }
        return true;
    }

    $("#editForm").bind('submit', function(event) {

        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>