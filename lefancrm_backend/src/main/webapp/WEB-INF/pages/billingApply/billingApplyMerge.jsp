<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <div class="btn-group">
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">合并开票</button>
                </div>
            </div>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选" checked>
                </th>
                <th style="min-width:120px">案件编号</th>
                <th style="min-width:150px">案件标题</th>
                <th style="min-width:100px">开票产品</th>
                <th style="min-width:100px">开票项目</th>
                <th style="min-width:100px">开票金额</th>
                <th style="min-width:80px">开票类型</th>
                <th style="min-width:100px">所属公司</th>
                <th style="min-width:70px">开票对象</th>
                <th style="min-width:70px">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${billingApplys}" var="item">
                <tr>
                    <td><input type="checkbox" name="list-checkbox" value="${item.id}" checked></td>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>
                        <c:forEach items="${bullingEnums}" var="dto">
                            <c:if test="${dto.enumCode == item.billingEnum}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${bullingItems}" var="dto">
                            <c:if test="${dto.enumCode == item.billingItem}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${item.billingMoney}</td>
                    <td>
                        <c:if test="${item.billingType == 1}">专票</c:if>
                        <c:if test="${item.billingType == 2}">普票</c:if>
                        <c:if test="${item.billingType == 3}">电子普票</c:if>
                    </td>
                    <td>
                        <c:forEach items="${corporations}" var="dto">
                            <c:if test="${dto.id == item.businessType}">
                                ${dto.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${item.companyName}</td>
                    <td>
                        <a href="javascript:billApplyEdit('${item.id}');">修改</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/suning/billApply/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    /*全选 取消全选*/
    var check_btn = document.getElementById("check-btn");
    var check_name = document.getElementsByName("list-checkbox");
    check_btn.onclick = function(){
        for(var i=1; i<=check_name.length; i+=1){
            if(check_name[i-1].checked){
                check_name[i-1].checked = false;
            }else{
                check_name[i-1].checked = true;
            }
        }
    };
    $("#batchOperateBtn").click(function(){
        var check_name = document.getElementsByName("list-checkbox");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length>0){
            batchOperate(idArr);
        }
    });

    function batchOperate(idArr){
        ajaxSubmit("${ctx}/billingApply/billingApplyMerge",{"idArr":idArr.join(",")},reloadParent,"合并开票成功！","确认合并开票？","合并开票失败！");
    }

    /**
     * 修改
     */
    function billApplyEdit(id) {
        openDialog({
            frame: true,
            title: "修改页面",
            height: 650,
            width: 800,
            url: "${ctx}/billingApply/billApplyEdit?id="+ id
        });
    }
</script>
</body>
</html>
