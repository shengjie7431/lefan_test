<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>收件人</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>收件人 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApply/selectRecipient" method="post">
                    <input type="hidden" id="type" name="type" value="${type}" >
                    <div class="form-group">
                        收件人姓名: <input name="recipientsName" type="text"  value="${recipientsName}"  class="form-control"/>
                        收件人电话: <input name="recipientsPhone" type="text"  value="${recipientsPhone}" class="form-control"/>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="100">收件人姓名</th>
                <th width="150">收件人电话</th>
                <th width="80">省</th>
                <th width="80">市</th>
                <th width="80">区</th>
                <th width="200">详细地址</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.recipientsName}</td>
                    <td>${item.recipientsPhone}</td>
                    <td>${item.province}</td>
                    <td>${item.city}</td>
                    <td>${item.district}</td>
                    <td>${item.address}</td>
                    <th>
                        <a href="javascript:allot('${item.id}','${type}');">选择</a>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/billingApply/selectRecipient?recipientsName=${recipientsName}&recipientsPhone=${recipientsPhone}&type=${type}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function allot(id,type){
        $.ajax({
            url:'${ctx}/billingApply/selectRecipientInfo?id='+id,
            type:"Get",
            success:function(res,param){

                var item=param.data.results;
                for(var i=0;i<item.length;i++){
                    if(type =="billing") {
                        parent.document.getElementById("recipientsName").value = item[0].recipientsName;
                        parent.document.getElementById("recipientsPhone").value = item[0].recipientsPhone;
                        parent.document.getElementById("province").value = item[0].province;
                        parent.document.getElementById("city").value = item[0].city;
                        parent.document.getElementById("district").value = item[0].district;
                        parent.document.getElementById("provinceId").value = item[0].provinceId;
                        parent.document.getElementById("shenId").value = item[0].provinceId;
                        parent.document.getElementById("shiId").value = item[0].cityId;
                        parent.document.getElementById("quId").value = item[0].districtId;

                        parent.$("#div_city").trigger("click");
                        parent.document.getElementById("address").value = item[0].address;
                    }
                    if(type =="law"){
                        console.log(item[0]);
                        console.log(item[0].recipientsName);
                        parent.document.getElementById("toName").value = item[0].recipientsName;
                        parent.document.getElementById("toTel").value = item[0].recipientsPhone;

                        parent.document.getElementById("toProvinceId").value = item[0].provinceId;
                        parent.document.getElementById("toProvince").value = item[0].province;
                        parent.document.getElementById("toCity").value = item[0].city;
                        parent.document.getElementById("toDistrict").value = item[0].district;

                        parent.document.getElementById("shenId").value = item[0].provinceId;
                        parent.document.getElementById("shiId").value = item[0].cityId;
                        parent.document.getElementById("quId").value = item[0].districtId;

                        parent.$("#div_city").trigger("click");
                        parent.document.getElementById("toAddress").value = item[0].address;
                    }

                }
                closeDialog();
            }
        });
    }

</script>
</body>
</html>
