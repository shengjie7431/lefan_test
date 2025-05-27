<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托方报价列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .frame{
            border-right:1px solid #000000;
            border-left:1px solid #000000;
            border-top:1px solid #000000;
            border-bottom:1px solid #000000;
        }
        .pageSizeContent{
            display: none!important;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>委托方报价列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info" style="margin: 0 2px 15px 2px;">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                   <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <%--<div class="form-group">--%>
                        <%--机构类别:--%>
                        <%--<select name="surveyCode"  class="form-control">--%>
                            <%--<option value="commonAreaPrice"  <c:if test="${surveyCode == 'commonAreaPrice'}">selected="selected" </c:if> >委托方  </option>--%>
                            <%--<option value="investigatorAreaPrice"  <c:if test="${surveyCode == 'investigatorAreaPrice'}">selected="selected" </c:if> >调查方  </option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                   <div class="form-group">
                       区域名称:<input name="areaName" type="text" value="${areaName}" class="form-control">
                   </div>
                    <div class="form-group">
                        区域类别:
                        <select name="cityType"  class="form-control">
                            <option value=""  <c:if test="${cityType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="2" <c:if test="${cityType == '2'}">selected="selected" </c:if> >省会</option>
                            <option value="3" <c:if test="${cityType == '3'}">selected="selected" </c:if> >地级市</option>
                            <option value="4" <c:if test="${cityType == '4'}">selected="selected" </c:if> >县级市</option>
                            <option value="5" <c:if test="${cityType == '5'}">selected="selected" </c:if> >市区</option>
                            <option value="6" <c:if test="${cityType == '6'}">selected="selected" </c:if> >郊区</option>
                        </select>
                    </div>
                    <div class="form-group">
                        价格类别:
                        <select name="priceType"  class="form-control">
                            <option value="1"  <c:if test="${priceType == '1'}">selected="selected" </c:if> >保司版</option>
                            <option value="2"  <c:if test="${priceType == '2'}">selected="selected" </c:if> >互助版</option>
                        </select>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--任务类型:--%>
                        <%--<select name="taskId" class="form-control">--%>
                            <%--<option value="">全部</option>--%>
                            <%--<c:forEach items="${taskInfos}" var="item">--%>
                                <%--<option <c:if test="${taskId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover"  style="border-right:1px solid #000000;border-bottom:1px solid #000000">
            <thead  id="eee" style="background-color: #fff;z-index: 10000;">
                <tr class="frame">
                    <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">省市</th>
                    <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">地域</th>
                    <c:forEach items="${taskInfos}" var="item">
                        <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">${item.infoName}
                            <span style="color: #b3aaaa">(元)</span>
                        </th>
                    </c:forEach>
                    <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>

                </tr>
            </thead>
            <tbody class="class-list" id="frame">
            <c:forEach items="${surveyCommonAreaPrice2Dtos}" var="item">
                <tr class="frame">
                    <td width="4%" class="frame"  style="vertical-align:middle; text-align: center;">${item.areaName}</td>
                    <td width="4%" class="frame">
                        <c:if test="${item.cityType==2}">省会</c:if>
                        <c:if test="${item.cityType==3}">地级市</c:if>
                        <c:if test="${item.cityType==4}">县级市</c:if>
                        <c:if test="${item.cityType==5}">市区</c:if>
                        <c:if test="${item.cityType==6}">郊区</c:if>
                    </td>
                    <c:forEach items="${taskInfos}" var="taskItem">
                        <td width="4%" class="frame">
                            <c:forEach items="${item.priceses}" var="priceItem">
                                <c:if test="${priceItem.taskId == taskItem.taskId && priceItem.taskInfoContentId == taskItem.taskInfoContentId && priceItem.directionResultTypeId == taskItem.directionResultTypeId}">
                                    ${priceItem.price}
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <td width="4%" class="frame" style="vertical-align:middle;">
                            <%--<a href="javascript:info('${item.areaId}','${item.taskId}','${item.cityType}','${surveyCode}');">详情</a>--%>
                        <a href="javascript:updatePrice('${surveyCode}','${item.areaId}','1300','${item.cityType}','${item.areaName}','${priceType}');">修改</a><br>
                        <a href="javascript:deletePrice('${item.areaId}','${surveyCode}','9999','${priceType}')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%--<table class="table table-hover"  style="border-right:1px solid #000000;border-bottom:1px solid #000000">
            <thead  id="eee" style="background-color: #fff;z-index: 10000;">
            <tr class="frame">
                <th width="7.9%" class="frame" style="vertical-align:middle; text-align: center;">省市</th>
                <th width="7.9%" class="frame" style="vertical-align:middle; text-align: center;">地域</th>
                <c:forEach items="${taskInfos}" var="item">
                    <th width="7.9%" class="frame" style="vertical-align:middle; text-align: center;">${item.name}
                        <span style="color: #b3aaaa">(元)</span>
                    </th>
                </c:forEach>
                <th width="7.9%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>

            </tr>
            </thead>
            <tbody class="class-list" id="frame">
            <c:forEach items="${surveyCommonAreaPrice2Dtos}" var="item">
                <tr class="frame">
                    <td width="7.9%" class="frame"  style="vertical-align:middle; text-align: center;">${item.areaName}</td>
                    <td width="7.9%" class="frame">
                        <c:if test="${item.cityType==2}">省会</c:if>
                        <c:if test="${item.cityType==3}">地级市</c:if>
                        <c:if test="${item.cityType==4}">县级市</c:if>
                        <c:if test="${item.cityType==5}">市区</c:if>
                        <c:if test="${item.cityType==6}">郊区</c:if>
                    </td>
                    <c:forEach items="${taskInfos}" var="taskItem">
                        <td width="7.9%" class="frame">
                            <c:if test="${taskItem.id==18}">${item.taskPriceMZ}</c:if>
                            <c:if test="${taskItem.id==17}">${item.taskPriceQY}</c:if>
                            <c:if test="${taskItem.id==16}">${item.taskPriceSC}</c:if>
                            <c:if test="${taskItem.id==15}">${item.taskPriceTJ}</c:if>
                            <c:if test="${taskItem.id==14}">${item.taskPriceKC}</c:if>
                            <c:if test="${taskItem.id==13}">${item.taskPriceYL}</c:if>
                            <c:if test="${taskItem.id==12}">${item.taskPriceBL}</c:if>
                            <c:if test="${taskItem.id==11}">${item.taskPriceSD}</c:if>
                            <c:if test="${taskItem.id==10}">${item.taskPriceYB}</c:if>
                            <c:if test="${taskItem.id==9 }">${item.taskPriceZF}</c:if>
                        </td>
                    </c:forEach>
                    <td width="7.9%" class="frame" style="vertical-align:middle;">
                        &lt;%&ndash;<a href="javascript:info('${item.areaId}','${item.taskId}','${item.cityType}','${surveyCode}');">详情</a>&ndash;%&gt;
                        <a href="javascript:updatePrice('${surveyCode}','${item.areaId}','1300','${item.areaType}','${item.areaName}');">修改</a><br>
                        <a href="javascript:deletePrice('${item.areaId}','${surveyCode}','9999')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>--%>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&areaName=${areaName}&taskId=${taskId}&cityType=${cityType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    if ($('#eee').css('position') != 'fixed'){
        s_top =  $('.table-hover').position().top
    }
    $(document).scroll( function() {
        var scrollTop=$(this).scrollTop();
        if (scrollTop > s_top - 70){
            $('#eee').css({
                position: 'fixed',
                top: 0
            })
        } else {
            $('#eee').css({
                position: 'static'
            })
        }
    } );
    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:800,
            width:1200,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var info = function(id,taskId,cityType,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/info?areaId="+id+"&taskId="+taskId+"&surveyCode="+surveyCode+"&cityType="+cityType,
            load:true
        });
    }

    //合并单元格
    mc(0,${surveyCommonAreaPrice2Dtos.size()},0);
    function mc(startRow, endRow, col) {
        var tb = document.getElementById("frame");
        let tableCellLength = tb.rows[0].cells.length;
        for (let i = startRow; i < endRow; i++) {
            if (tb.rows[startRow].cells[col].innerHTML == tb.rows[i + 1].cells[col].innerHTML) {
                //合并最后一列相同的行
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[tableCellLength-1]);
                tb.rows[startRow].cells[tableCellLength-1].rowSpan = (tb.rows[startRow].cells[tableCellLength-1].rowSpan | 0) + 1;
                //合并第col列相同的行
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
            }else{
                mc(i + 1, endRow, col)
            }
        }
    }

    function updatePrice(surveyCode,parentId,btnCode,cityType,parentName,priceType){
        var areaType =0;
        if(cityType ==5 || cityType ==6){
            areaType=1;//代表直辖市
        }else if(cityType ==2 || cityType ==3 || cityType ==4){
            areaType=0;////代表非直辖市
        }
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1500,
            url:"${ctx}/baseSurvey/edit?surveyCode="+surveyCode+"&parentId="+parentId+"&btnCode="+btnCode+"&areaType="+areaType+"&parentName="+parentName+"&priceType="+priceType
        });
        return false;
    }

    function deletePrice(areaId,surveyCode,btnCode,priceType) {
        ajaxSubmit("${ctx}/baseSurvey/operate",{"areaId":areaId,"btnCode":btnCode,"surveyCode":surveyCode,"priceType":priceType},reload,"删除成功！","确认删除？","删除失败！");
    }
</script>
</body>
</html>
