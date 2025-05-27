<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
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
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
    <div class="panel-heading">
        <div class="pin">
            <form class="form-inline" role="form" action="${ctx}/law/list" method="post">
                <input type="hidden" name="menuType" value="${menuType}">
                <div class="form-group">
                    <label class="title">案件编号:</label>
                    <input name="caseNo" type="text" value="${caseNo}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">委托人姓名:</label>
                    <input name="entrustUserName" type="text" value="${entrustUserName}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">委托人电话:</label>
                    <input name="entrustUserTel" type="text"  value="${entrustUserTel}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="title">联系人姓名:</label>
                    <input name="linkName" type="text"  value="${linkName}" class="form-control">
                </div>

                <div class="form-group">
                    <label class="title">联系人电话:</label>
                    <input name="linkTel" type="text"  value="${linkTel}" class="form-control">
                </div>
                <br>
                <div class="form-group">
                    <label class="title">委托事项:</label>
                    <select name="caseType"  class="form-control">
                        <option value=""  <c:if test="${caseType == ''}">selected="selected" </c:if> >全部</option>
                        <option value="1" <c:if test="${caseType == '1'}">selected="selected" </c:if> >财产险评估</option>
                        <option value="2" <c:if test="${caseType == '2'}">selected="selected" </c:if> >医疗费用评估</option>
                    </select>
                </div>
                <c:if test="${menuType ==35 || menuType ==30}">
                    <div class="form-group">
                        <label class="title">案件阶段:</label>
                        <select name="stageState"  class="form-control">
                            <option value=""  <c:if test="${stageState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${stageState == '1'}">selected="selected" </c:if> >委托阶段</option>
                            <option value="2" <c:if test="${stageState == '2'}">selected="selected" </c:if> >评估阶段</option>
                            <option value="3" <c:if test="${stageState == '3'}">selected="selected" </c:if> >结案</option>
                            <option value="4" <c:if test="${stageState == '4'}">selected="selected" </c:if> >退案</option>
                        </select>
                    </div>
                </c:if>

                <c:if test="${menuType == 18}">
                    <div class="form-group">
                        <label class="title">方案上传:</label>
                        <select name="isUploadAssessPlan"  class="form-control">
                            <option value=""  <c:if test="${isUploadAssessPlan == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isUploadAssessPlan == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isUploadAssessPlan == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">请款函状态:</label>
                        <select name="isUploadReqmoneyLetter"  class="form-control">
                            <option value=""  <c:if test="${isUploadReqmoneyLetter == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isUploadReqmoneyLetter == '0'}">selected="selected" </c:if> >未上传</option>
                            <option value="1" <c:if test="${isUploadReqmoneyLetter == '1'}">selected="selected" </c:if> >已上传</option>
                            <option value="2" <c:if test="${isUploadReqmoneyLetter == '2'}">selected="selected" </c:if> >催收中</option>
                            <option value="3" <c:if test="${isUploadReqmoneyLetter == '3'}">selected="selected" </c:if> >超期未支付</option>
                            <option value="4" <c:if test="${isUploadReqmoneyLetter == '4'}">selected="selected" </c:if> >已支付</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">是否发送:</label>
                        <select name="isExpress"  class="form-control">
                            <option value=""  <c:if test="${isExpress == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isExpress == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isExpress == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="title">委托时间:</label>
                    <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly>
                    <span>--</span>
                    <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                </div>
                <div class="btn-group">
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    <c:if test="${menuType == 35}">
                        <button onclick="add()" type="button" class="btn btn-default">新增委托</button>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
    <div class="table-content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="min-width:100px">案件编号</th>
                <th style="min-width:100px">委托事项</th>
                <th style="min-width:100px">机构</th>
                <th style="min-width:100px">委托人</th>
                <th style="min-width:100px">委托电话</th>
                <th style="min-width:100px">联系人</th>
                <th style="min-width:100px">联系人电话</th>
                <c:if test="${menuType == 5 || menuType == 1}">
                    <th style="min-width:150px">是否补充信息</th>
                </c:if>
                <c:if test="${menuType == 35 || menuType == 30}">
                    <th>案件阶段</th>
                </c:if>
                <th style="min-width:150px">案件状态</th>
                <c:if test="${menuType == 18}">
                    <th style="min-width:100px">方案是否上传</th>
                    <th style="min-width:100px">请款函状态</th>
                    <th style="min-width:150px">方案/请款函是否发送</th>
                </c:if>
                <th style="min-width:100px">委托时间</th>
                <%--<th style="min-width:100px">更新时间</th>--%>
                <th style="min-width:100px">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.caseType == 1}">财产险评估</c:if>
                        <c:if test="${item.caseType == 2}">医疗费用评估</c:if>
                    </td>
                    <td>${item.orgName}</td>
                    <td>${item.entrustUserName}</td>
                    <td>${item.entrustUserTel}</td>
                    <td>${item.linkName}</td>
                    <td>${item.linkTel}</td>
                    <c:if test="${menuType == 5 || menuType == 1}">
                        <td>
                            <c:if test="${item.isCursupplement == 0}">信息未补充</c:if>
                            <c:if test="${item.isCursupplement == 1}"><span style="color: red">信息补充中</span></c:if>
                            <c:if test="${item.isCursupplement == 2}">信息补充完成</c:if>
                        </td>
                    </c:if>
                    <c:if test="${menuType == 35 || menuType == 30}">
                        <td>
                            <c:if test="${item.stageState == 1}"><span style="color: #ba2bea">委托阶段</span></c:if>
                            <c:if test="${item.stageState == 2}"><span style="color: #66CD00">评估阶段</span></c:if>
                            <c:if test="${item.stageState == 3}"><span style="color: #1E90FF">结案</span></c:if>
                            <c:if test="${item.stageState == 4}"><span style="color: #ff0000">退案</span></c:if>
                        </td>
                    </c:if>
                    <td>
                        <c:if test="${item.retreatState == 0 || item.retreatState == 3}">
                            ${item.flowStateName}
                        </c:if>
                        <c:if test="${item.retreatState == 1}">
                            退案审核中
                        </c:if>
                        <c:if test="${item.retreatState == 2}">
                            已退案
                        </c:if>
                        <%--<c:if test="${item.retreatState == 3}">--%>
                            <%--退案审核不通过--%>
                        <%--</c:if>--%>
                    </td>
                    <c:if test="${menuType == 18}">
                        <td>
                            <c:if test="${item.isUploadAssessPlan == 0}">
                                未上传
                            </c:if>
                            <c:if test="${item.isUploadAssessPlan == 1}">
                                已上传
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isUploadReqmoneyLetter == 0}">
                                未上传
                            </c:if>
                            <c:if test="${item.isUploadReqmoneyLetter == 1}">
                                已上传
                            </c:if>
                            <c:if test="${item.isUploadReqmoneyLetter == 2}">
                                催收中
                            </c:if>
                            <c:if test="${item.isUploadReqmoneyLetter == 3}">
                                超期未支付
                            </c:if>
                            <c:if test="${item.isUploadReqmoneyLetter == 4}">
                                已支付
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isExpress == 0}">
                                未发送
                            </c:if>
                            <c:if test="${item.isExpress == 1}">
                                已发送
                            </c:if>
                        </td>
                    </c:if>
                    <td><fmt:formatDate value="${item.entrustTime}" pattern="yyyy-MM-dd"/></td>
                    <%--<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd"/></td>--%>
                    <td>
                        <a href="javascript:info('${item.id}');">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div><!--panel-info-->
<div class="main-bottom">
    <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
        <jsp:param name="paginationObjectName" value="apiRsp" />
        <jsp:param name="pageNoName" value="" />
        <jsp:param name="requestUrl" value="${ctx}/law/list?caseNo=${caseNo}&entrustUserName=${entrustUserName}&entrustUserTel=${entrustUserTel}&caseType=${caseType}&linkName=${linkName}&linkTel=${linkTel}&stageState=${stageState}&startDate=${startDate}&endDate=${endDate}&menuType=${menuType}&isUploadAssessPlan=${isUploadAssessPlan}&isUploadReqmoneyLetter=${isUploadReqmoneyLetter}&isExpress=${isExpress}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>
</div><!--main-bottom-->

</div><!--main end-->
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function info(id){
        var url = "${ctx}/law/info?id="+id+"&menuType=${menuType}";
        openDialog({
            frame:true,
            title:"委托信息",
            height:750,
            width:1200,
            url : url,
            load:true
        });
    }

    var add = function(){
        openDialog({
            frame:true,
            title:"新增委托",
            height:700,
            width:800,
            url:"${ctx}/law/add"
        });
    }
</script>
</body>
</html>
