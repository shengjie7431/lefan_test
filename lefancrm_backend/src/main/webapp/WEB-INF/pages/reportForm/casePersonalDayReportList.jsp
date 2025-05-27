<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人业务案件报表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            text-align: center;
            border-left: 1px solid #ddd;
        }
    </style>
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>个人业务案件报表</h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/casePersonalDayReportList" method="post">
                    <%--<input type="hidden" name="type" value="${type}">--%>
                    <div class="form-group">
                        <label class="title">报表类型:</label>
                        <select name="type" id="type" class="form-control" onclick="selectType()">
                        <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >日报表</option>
                        <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >月报表</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label class="title">机构名称:</label>
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" id="startDate">
                        <label class="title">日期：</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group" id="endDate">
                        --  <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly>
                    </div>
                    <div class="form-group" id="month">
                        <label class="title">年月:</label>
                        <input name="month" type="text"  value="${month}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">主管姓名:</label>
                        <input name="directorName" type="text"  value="${directorName}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">业务员姓名:</label>
                        <input name="salesmanName" type="text"  value="${salesmanName}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">涉残:</label>
                        <select name="isDisability" id="isDisability" class="form-control">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${isDisability == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isDisability == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">机构合计:</label>
                        <select name="isSum" id="isSum" class="form-control">
                            <option value="0" <c:if test="${isSum == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isSum == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/casePersonalReportExport?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&directorName=${directorName}&salesmanName=${salesmanName}&month=${month}&type=${type}&isDisability=${isDisability}&isSum=${isSum}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="table table-striped">
            <thead>
            <tr>
                <th style="min-width:160px" rowspan="2">机构名称</th>
                <c:if test="${isSum !=1}">
                    <th style="min-width:100px" rowspan="2">主管</th>
                    <th style="min-width:100px" rowspan="2">业务员</th>
                    <c:if test="${isDisability !=''}">
                        <th style="min-width:100px" rowspan="2">是否涉残</th>
                    </c:if>
                </c:if>
                <th style="min-width:480px" colspan="4">代理案件</th>
                <th style="min-width:600px" colspan="5">贷款案件</th>
                <th style="min-width:100px" rowspan="2">毁约案件数量</th>
                <th style="min-width:200px" colspan="2">新增案件数</th>
                <th style="min-width:460px" colspan="4">合计数</th>
                <th style="min-width:100px" rowspan="2">录入案件数</th>
                <th style="min-width:100px" rowspan="2">目标客户数</th>
                <th style="min-width:100px" rowspan="2">潜在客户数量</th>
                <th style="min-width:100px; text-decoration:line-through" rowspan="2">意向客户数量</th>
                <th style="min-width:100px" rowspan="2">签约客户数</th>
                <th style="min-width:120px" rowspan="2">目标转潜在转化率</th>
                <%--<th style="min-width:120px" rowspan="2">潜在转意向转化率</th>--%>
                <th style="min-width:120px" rowspan="2">潜在转签约转化率</th>
            </tr>
            <tr>
                <th style="min-width:120px">签单数</th>
                <th style="min-width:120px">签约服务费</th>
                <th style="min-width:120px">审核服务费</th>
                <th style="min-width:120px">预收服务费</th>
                <th style="min-width:120px">签单数</th>
                <th style="min-width:120px">签约服务费</th>
                <th style="min-width:120px">审核服务费</th>
                <th style="min-width:120px">通道费</th>
                <th style="min-width:120px">预收服务费</th>
                <th style="min-width:100px">新增案件数量</th>
                <th style="min-width:100px">新增服务费</th>
                <th style="min-width:100px">合计签单数</th>
                <th style="min-width:120px">合计签约服务费</th>
                <th style="min-width:120px">合计审核服务费</th>
                <th style="min-width:120px">合计预收服务费</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.orgName}</td>
                    <c:if test="${isSum !=1}">
                        <td>${item.directorName}</td>
                        <td>${item.salesmanName}</td>
                        <c:if test="${isDisability !=''}">
                            <c:if test="${item.isDisability == 0}">
                                <td>否</td>
                            </c:if>
                            <c:if test="${item.isDisability == 1}">
                                <td>是</td>
                            </c:if>
                            <c:if test="${item.isDisability == null}">
                                <td>--</td>
                            </c:if>
                        </c:if>

                    </c:if>
                    <td>${item.agentSignNum}</td>
                    <td>${item.agentServiceMoney == null ? 0.00: item.agentServiceMoney}</td>
                    <td>${item.okAgentServiceMoney == null ? 0.00: item.okAgentServiceMoney}</td>
                    <td>${item.advanceAgentServiceMoney == null ? 0.00: item.advanceAgentServiceMoney}</td>
                    <td>${item.loanSignNum}</td>
                    <td>${item.loanServiceMoney == null ? 0.00: item.loanServiceMoney}</td>
                    <td>${item.okLoanServiceMoney == null ? 0.00: item.okLoanServiceMoney}</td>
                    <td>${item.loanChannelMoney == null ? 0.00: item.loanChannelMoney}</td>
                    <td>${item.advanceLoanServiceMoney == null ? 0.00: item.advanceLoanServiceMoney}</td>
                    <td>${item.destroyCaseNum}</td>
                    <td>${item.newCaseNum}</td>
                    <td>${item.newServiceMoney == null ? 0.00: item.newServiceMoney}</td>
                    <td>${item.totalSignNum}</td>
                    <td>${item.totalServiceMoney == null ? 0.00: item.totalServiceMoney}</td>
                    <td>${item.totalOkServiceMoney == null ? 0.00: item.totalOkServiceMoney}</td>
                    <td>${item.totalAdServiceMoney == null ? 0.00: item.totalAdServiceMoney}</td>
                    <td>${item.caseInputNum}</td>
                    <td>${item.caseTargetNum}</td>
                    <td>${item.casePotentialNum}</td>
                    <td style="text-decoration:line-through">${item.caseIntentionNum == null ? 0: item.caseIntentionNum}</td>
                    <td>${item.caseSignNum}</td>
                    <c:if test="${item.targetToPotRate == 0}">
                        <td>0</td>
                    </c:if>
                    <c:if test="${item.targetToPotRate != 0}">
                        <td>${item.targetToPotRate == null ? "0": item.targetToPotRate}%</td>
                    </c:if>
                    <%--<td>${item.potToInteRate == null ? 0.00: item.potToInteRate}</td>--%>
                    <c:if test="${item.inteToSignRate == 0}">
                        <td>0</td>
                    </c:if>
                    <c:if test="${item.inteToSignRate != 0}">
                        <td>${item.inteToSignRate == null ? "0": item.inteToSignRate}%</td>
                    </c:if>

                </tr>
            </c:forEach>
            <c:forEach items="${allSumList}" var="item">
                <tr style="color: red">
                    <td>${item.orgName}</td>
                    <c:if test="${isSum !=1}">
                        <td>${item.directorName}</td>
                        <td>${item.salesmanName}</td>
                        <c:if test="${isDisability !=''}">
                            <c:if test="${item.isDisability == 0}">
                                <td>否</td>
                            </c:if>
                            <c:if test="${item.isDisability == 1}">
                                <td>是</td>
                            </c:if>
                            <c:if test="${item.isDisability == null}">
                                <td>--</td>
                            </c:if>
                        </c:if>
                        <%--<c:if test="${isDisability ==null}">--%>
                            <%--<td>-</td>--%>
                        <%--</c:if>--%>
                    </c:if>
                    <td>${item.agentSignNum}</td>
                    <td>${item.agentServiceMoney == null ? 0.00: item.agentServiceMoney}</td>
                    <td>${item.okAgentServiceMoney == null ? 0.00: item.okAgentServiceMoney}</td>
                    <td>${item.advanceAgentServiceMoney == null ? 0.00: item.advanceAgentServiceMoney}</td>
                    <td>${item.loanSignNum}</td>
                    <td>${item.loanServiceMoney == null ? 0.00: item.loanServiceMoney}</td>
                    <td>${item.okLoanServiceMoney == null ? 0.00: item.okLoanServiceMoney}</td>
                    <td>${item.loanChannelMoney == null ? 0.00: item.loanChannelMoney}</td>
                    <td>${item.advanceLoanServiceMoney == null ? 0.00: item.advanceLoanServiceMoney}</td>
                    <td>${item.destroyCaseNum}</td>
                    <td>${item.newCaseNum}</td>
                    <td>${item.newServiceMoney == null ? 0.00: item.newServiceMoney}</td>
                    <td>${item.totalSignNum}</td>
                    <td>${item.totalServiceMoney == null ? 0.00: item.totalServiceMoney}</td>
                    <td>${item.totalOkServiceMoney == null ? 0.00: item.totalOkServiceMoney}</td>
                    <td>${item.totalAdServiceMoney == null ? 0.00: item.totalAdServiceMoney}</td>
                    <td>${item.caseInputNum}</td>
                    <td>${item.caseTargetNum}</td>
                    <td>${item.casePotentialNum}</td>
                    <td style="text-decoration:line-through">${item.caseIntentionNum == null ? 0: item.caseIntentionNum}</td>
                    <td>${item.caseSignNum}</td>
                    <c:if test="${item.targetToPotRate == 0}">
                        <td>0</td>
                    </c:if>
                    <c:if test="${item.targetToPotRate != 0}">
                        <td>${item.targetToPotRate == null ? "0": item.targetToPotRate}%</td>
                    </c:if>
                        <%--<td>${item.potToInteRate == null ? 0.00: item.potToInteRate}</td>--%>
                    <c:if test="${item.inteToSignRate == 0}">
                        <td>0</td>
                    </c:if>
                    <c:if test="${item.inteToSignRate != 0}">
                        <td>${item.inteToSignRate == null ? "0": item.inteToSignRate}%</td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
        <%--<div id="myCharts" style="width: 100%;height:300px;">--%>

        <%--</div>--%>
    </div><!--panel-info-->
    </table>
</div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/reportForm/casePersonalDayReportList?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&directorName=${directorName}&salesmanName=${salesmanName}&month=${month}&type=${type}&isDisability=${isDisability}&isSum=${isSum}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    var casePersonalDayReportView = function(id,type){
        openDialog({
            frame:true,
            title:"查看详情",
            height:650,
            width:1000,
            url:"${ctx}/reportForm/casePersonalDayReportView?id="+id+"&type="+type
        });
    }

    function selectType(){
        var type = $('#type option:selected').val();
        if(type == 1){
            $('#startDate').show();
            $('#endDate').show();
            $('#month').hide();
        }
        if(type == 2){
            $('#month').show();
            $('#startDate').hide();
            $('#endDate').hide();
        }
    }
    selectType();
</script>

<%--<script type="text/javascript">--%>
    <%--var myChart = echarts.init(document.getElementById('myCharts'));--%>

    <%--// 指定图表的配置项和数据--%>
    <%--var option = {--%>
        <%--title: {--%>
            <%--text: ''--%>
        <%--},--%>
        <%--tooltip: {},--%>
        <%--legend: {--%>
            <%--data:['金额']--%>
        <%--},--%>
        <%--xAxis: {--%>
            <%--data: []// orgName--%>
        <%--},--%>
        <%--yAxis: {},--%>
        <%--series: [{--%>
            <%--name: '合同金额',--%>
            <%--type: 'bar',--%>
            <%--data: [] // saleAmount--%>
        <%--},{--%>
            <%--name: '目标金额',--%>
            <%--type: 'bar',--%>
            <%--data: [] // saleGoal--%>
        <%--},{--%>
            <%--name: '完成率',--%>
            <%--type: 'line',--%>
            <%--data: [] // completionRate--%>
        <%--}]--%>
    <%--};--%>
    <%--// 使用刚指定的配置项和数据显示图表。--%>
    <%--myChart.setOption(option);--%>

    <%--var setting = {--%>
        <%--callback: {--%>
            <%--beforeDrag: beforeDrag,--%>
            <%--beforeEditName: beforeEditName,--%>
            <%--beforeRemove: beforeRemove,--%>
            <%--beforeRename: beforeRename,--%>
            <%--onRemove: onRemove,--%>
            <%--onRename: onRename,--%>
            <%--onAsyncSuccess:onAsyncSuccess,--%>
            <%--onClick : zTreeOnClick--%>
        <%--}--%>
    <%--};--%>
    <%--function beforeDrag(){--%>
        <%--alert(11111);--%>
    <%--}--%>
    <%--function beforeEditName(){--%>
        <%--alert(22222);--%>
    <%--}--%>
    <%--function beforeRemove(){--%>
        <%--alert(3333);--%>
    <%--}--%>
    <%--function beforeRename(){--%>
        <%--alert(4444);--%>
    <%--}--%>
    <%--function onRemove(){--%>
        <%--alert(5555);--%>
    <%--}--%>
    <%--function onRename(){--%>
        <%--alert(6666);--%>
    <%--}--%>
    <%--function onAsyncSuccess(){--%>
        <%--alert(7777);--%>
    <%--}--%>
    <%--function zTreeOnClick(){--%>
        <%--alert(888);--%>
    <%--}--%>

    <%--function filter(treeId, parentNode, childNodes) {--%>
        <%--alert(000);--%>
        <%--if (!childNodes) return null;--%>
        <%--alert(111);--%>
        <%--for (var i=0, l=childNodes.length; i<l; i++) {--%>
            <%--childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');--%>
        <%--}--%>
        <%--alert(222);--%>
        <%--return childNodes;--%>
        <%--alert(333);--%>
    <%--}--%>
<%--</script>--%>
</body>
</html>
