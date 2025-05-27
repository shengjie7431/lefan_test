<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" language="javascript">
    var idTmr;
    function method1(tableid) {//整个表格拷贝到EXCEL中
        var curTbl = document.getElementById(tableid);
        var oXL = new ActiveXObject("Excel.Application");
        //创建AX对象excel
        var oWB = oXL.Workbooks.Add();
        //获取workbook对象
        var xlsheet = oWB.Worksheets(1);
        //激活当前sheet
        var sel = document.body.createTextRange();
        sel.moveToElementText(curTbl);
        //把表格中的内容移到TextRange中
        sel.select();
        //全选TextRange中内容
        sel.execCommand("Copy");
        //复制TextRange中内容
        xlsheet.Paste();
        //粘贴到活动的EXCEL中
        oXL.Visible = true;
        //设置excel可见属性

        try {
            var fname = oXL.Application.GetSaveAsFilename("将Table导出到Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
        } catch (e) {
            print("Nested catch caught " + e);
        } finally {
            oWB.SaveAs(fname);

            oWB.Close(savechanges = false);
            //xls.visible = false;
            oXL.Quit();
            oXL = null;
            //结束excel进程，退出完成
            //window.setInterval("Cleanup();",1);
            idTmr = window.setInterval("Cleanup();", 1);

        }
    }
    function Cleanup() {
        window.clearInterval(idTmr);
        CollectGarbage();
    }
</script>
<head>
    <title> 机构案件个人推广报表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 机构案件个人推广报表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/report/caseDayReport" method="post">
                    <div class="form-group">
                        机构名称:
                        <select name="orgId" class="form-control">
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}" <c:if test="${item1.id == orgId}">selected="selected" </c:if> >${item1.orgName}</option>
                        </c:forEach>
                    </select>
                      开始日期： <input name="startTime" type="text" value="${startTime}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        开始日期： <input name="endTime" type="text" value="${endTime}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>


                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
               <%-- <button id="doExcel" onclick="javascript:method1('reportTable')" type="button" class="btn btn-default">导出报表</button>--%>
            </div>
        </div>

        <table class="table table-hover" id="reportTable">
            <thead>
            <tr>
                <th width="150">机构</th>
                <th width="150">姓名</th>
                <th width="150">推广员注册数</th>
                <th width="150">案源有效数</th>
                <th width="150">案件成交数</th>
                <th width="150">理赔款测算数</th>
                <th width="150">伤残预估数</th>
                <th width="150">医疗垫付数</th>
                <th width="150">理赔垫付数</th>
                <th width="150">代办理赔数</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orgName}</td>
                    <td>${item.userName}</td>
                    <td>${item.promotersNum}</td>
                    <td>${item.caseEffectiveNum}</td>
                    <td>${item.caseSignedNum}</td>
                    <td>${item.paymentEstimateNum}</td>
                    <td>${item.invalidismEstimateNum}</td>
                    <td>${item.medicalFeeNum}</td>
                    <td>${item.paymentFeeNum}</td>
                    <td>${item.agentNum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <table class="table table-hover" style="color: #ff0000;" id="reportTable1">
            <thead>
            <tr>
                <th width="150">机构</th>
                <th width="150"></th>
                <th width="150">推广员注册数</th>
                <th width="150">案源有效数</th>
                <th width="150">案件成交数</th>
                <th width="150">理赔款测算数</th>
                <th width="150">伤残预估数</th>
                <th width="150">医疗垫付数</th>
                <th width="150">理赔垫付数</th>
                <th width="150">代办理赔数</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${caseDayReportDtos}" var="itemT">
                <tr>
                    <td>总计</td>
                    <td>${itemT.orgName}</td>
                    <td>${itemT.promotersNum}</td>
                    <td>${itemT.caseEffectiveNum}</td>
                    <td>${itemT.caseSignedNum}</td>
                    <td>${itemT.paymentEstimateNum}</td>
                    <td>${itemT.invalidismEstimateNum}</td>
                    <td>${itemT.medicalFeeNum}</td>
                    <td>${itemT.paymentFeeNum}</td>
                    <td>${itemT.agentNum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/report/caseDayReport?orgId=${orgId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    /*function doExcel(){
        var curTbl = document.getElementById("reportTable");
        var oXL = new ActiveXObject("Excel.Application");
        var oWB = oXL.Workbooks.Add();
        var oSheet = oWB.ActiveSheet;
        var sel = document.body.createTextRange();
        sel.moveToElementText(curTbl);
        sel.select();
        sel.execCommand("Copy");
        oSheet.Paste();
        oXL.Visible = true;
    }*/
</script>
</body>
</html>
