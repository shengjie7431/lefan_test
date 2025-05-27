<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/loan/loanApplicationUpdateInfo" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <input name="id" value="${id}" hidden="true"/>
                    <th width="20%" class="active">用户姓名</th>
                    <td width="80%"><input name="userName" value="${userName}" class="form-control" style="width: 170px"/></td>
                </tr>

                <tr>
                    <th class="active"><strong class="necessary"> </strong>手机号码</th>
                    <td><input name="userPhone" maxlength="11" value="${userPhone}" class="form-control" style="width: 170px"/></td>
                </tr>
                <tr>
                    <th width="20%" class="active">贷款类型</th>
                    <td width="80%">
                        <select id = "loanPurpose" name="loanPurpose"  style="width: 170px;" class="form-control">
                            <option value="1" <c:if test="${loanPurpose == 1}">selected="selected" </c:if>>医疗费垫付</option>
                            <option value="2" <c:if test="${loanPurpose == 2}">selected="selected" </c:if>>赔偿款垫付</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">交通事故</th>
                    <td width="80%">
                        <select id = "isTrafficAccident" name="isTrafficAccident"  style="width: 170px;" class="form-control">
                            <option value="0" <c:if test="${isTrafficAccident == 0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${isTrafficAccident == 1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong>贷款金额</th>
                    <td><input name="loanMoney" value="${loanMoney}" class="form-control" style="width: 170px"/></td>
                </tr>
                <tr>
                    <th width="20%" class="active">事故所在地</th>
                    <td width="30%" class="form-inline">
                        <div class="form-group">
                        <select id = "accidentProvince" name="accidentProvince"   onclick="selectArea()" class="form-control">
                            <c:forEach items="${provinceList.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == accidentProvince}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                        <select id = "accidentCity" name="accidentCity" onclick="selectAreaCity()" class="form-control">
                            <c:forEach items="${cityList.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == accidentCity}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                        <select id = "accidentDistrict" name="accidentDistrict" class="form-control">
                            <c:forEach items="${districtList.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == accidentDistrict}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="active"><strong class="necessary"> </strong>详细地址</th>
                    <td><textarea name="accidentAddress" style="height: 100px;width: 250px" class="form-control">${accidentAddress}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
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
    $("#editForm").bind('submit', function(event) {
//        $("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
    function selectArea(){
        var accidentProvince = $("#accidentProvince").val();
        if(accidentProvince == 0){
            return;
        }
        ajaxSubmit("${ctx}/org/selectArea",{"parentId":accidentProvince},function(v,e,p){
            $("#accidentCity option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#accidentCity").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }

        })
    }
    function  selectAreaCity(){
        var accidentCity = $("#accidentCity").val();
        if(accidentCity == 0){
            return;
        }
        ajaxSubmit("${ctx}/org/selectArea",{"parentId":accidentCity},function(v,e,p){
            $("#accidentDistrict option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#accidentDistrict").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }

        })
    }
</script>
</body>
</html>