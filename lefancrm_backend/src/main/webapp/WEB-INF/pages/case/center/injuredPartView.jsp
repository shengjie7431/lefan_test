<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>受伤部位</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <form id="editForm" role="form" action="${ctx}/case/center/injuredPartSubmit">
        <input type="hidden" id="ids" name="ids" value="${ids}">
        <input type="hidden" id="id" name="id" value="${id}">
        <div class="form-group" style="margin-left: 16px">
            <table class="table">
                <tbody>
                <tr>
                    <th width="153" colspan="6"><input type="checkbox" id="all" >全选</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <tr>
                    <td width="16%" >
                        <input type="checkbox" value="2" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==2}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="背部" id="name_2" />
                        背部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="3" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                                <c:if test="${item ==3}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="腰部" id="name_3" />
                        腰部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="4" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                                <c:if test="${item ==4}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="臀部" id="name_4" />
                        臀部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="5" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                                <c:if test="${item ==5}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="头颅" id="name_5" />
                        头颅
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="6" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==6}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="面部" id="name_6" />
                        面部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="7" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==7}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="颈部" id="name_7" />
                        颈部
                    </td>
                </tr>
                <tr>
                    <td width="16%">
                        <input type="checkbox" value="8" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==8}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="胸部" id="name_8" />
                        胸部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="9" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==9}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="上肢" id="name_9" />
                        上肢
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="10" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==10}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="腹部" id="name_10" />
                        腹部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="11" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==11}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="会阴部" id="name_11" />
                        会阴部
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="12" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==12}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="手" id="name_12" />
                        手
                    </td>
                    <td width="16%">
                        <input type="checkbox" value="13" name="buss"
                        <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==13}">checked="checked"</c:if>
                        </c:forEach>
                                >
                        <input type="hidden" value="下肢" id="name_13" />
                        下肢
                    </td>
                </tr>
                <tr>
                    <td colspan="6">
                        <input type="checkbox" value="14" name="buss"
                            <c:forEach items="${apply.injuredPart}" var="item">
                               <c:if test="${item ==14}">checked="checked"</c:if>
                            </c:forEach>
                        >
                        <input type="hidden" value="足" id="name_14" />
                        足
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button id="batchOperateBtn" type="submit" onclick="return businessOK()" class="btn btn-success loading-btn" data-loading-text="Loading...">确定提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
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

        });
    });

    function businessOK(){
        var injuredPart = [];
        var injuredPartStr = [];
        $("input:checkbox[name='buss']:checked").each(function() { // 遍历name=safeCompanys的多选框
            injuredPart.push($(this).val());
            injuredPartStr.push($("#name_" + $(this).val()).val())
        });
        parent.$("#injuredPartStr").val(injuredPartStr);
        parent.$("#injuredPart").val(injuredPart);

        closeDialog();
    }

</script>
</body>
</html>
