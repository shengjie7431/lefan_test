<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>费用报销管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>

    <style>
        .class-list a{
            color: #428bca;
        }
        table.table  tbody.class-list tr:hover td{
            background-color: #fff!important;
        }
        table.table  tbody.class-list tr td:hover{
            background-color: #b6e3ef42!important;
            cursor: pointer;
        }
        table.table{
            text-align: center;
        }
        .title_sort{
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }
        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>费用报销管理 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/fee/list" method="post">
                    <input type="hidden" name="menuCode" value="${menuCode}" />
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <div class="form-group">
                        清单名称:
                        <input name="feeReName" type="text" value="${feeReName}" style="width: 150px" class="form-control">
                    </div>
                    <div class="form-group">
                        清单状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="success" class="form-control">
                            <option value="" <c:if test="${success == ''}">selected="selected"</c:if>>全部</option>
                            <option value="0" <c:if test="${success == '0'}">selected="selected"</c:if>>未完成</option>
                            <option value="1" <c:if test="${success == '1'}">selected="selected"</c:if>>已完成</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                            <c:if test="${blogBtn}">
                                &nbsp; &nbsp;<button onclick="edit('')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                            </c:if>
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th style="text-align: center" width="200">清单名称</th>
                <th style="text-align: center" width="80">案件数</th>
                <th style="text-align: center" width="80">报销人数</th>
                <th style="text-align: center" width="80">待提交发票</th>
                <th style="text-align: center" width="80">待机构审核</th>
                <th style="text-align: center" width="80">待财务审核</th>
                <th style="text-align: center" width="80">付款中</th>
                <th style="text-align: center" width="80">待确认到账</th>
                <th style="text-align: center" width="80">报销完成</th>
                <th style="text-align: center" width="80">费用报销合计</th>
                <th style="text-align: center" width="80">清单状态</th>
                <th style="text-align: center" width="80">创建人</th>
                <th style="text-align: center" width="150">
                    <div class="title_sort" data-id="50" data-value="" data-field="createTime">
                        <span>创建时间</span>
                        <div class="icon-sort">
                            <div class="icon-up" ></div>
                            <div class="icon-down" ></div>
                        </div>
                    </div>
                </th>
                <th style="text-align: center" width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td style="text-align: left">${item.feeReName}</td>
                    <td onclick="javascript:openData('${item.reId}','')"><a>${item.caseNum}</a></td>
                    <td onclick="javascript:openData('${item.reId}','')"><a>${item.personNum}</a></td>
                    <td onclick="javascript:openData('${item.reId}','1')"><a>${item.num1}</a></td>
                    <td onclick="javascript:openData('${item.reId}','2')"><a>${item.num2}</a></td>
                    <td onclick="javascript:openData('${item.reId}','3')"><a>${item.num3}</a></td>
                    <td onclick="javascript:openData('${item.reId}','4')"><a>${item.num4}</a></td>
                    <td onclick="javascript:openData('${item.reId}','5')"><a>${item.num5}</a></td>
                    <td onclick="javascript:openData('${item.reId}','6')"><a>${item.num6}</a></td>
                    <td>${item.totalMoney}</td>
                    <td>
                        <c:if test="${item.reState == 0}">未完成</c:if>
                        <c:if test="${item.reState == 1}">已完成</c:if>
                    </td>
                    <td>${item.createBy}</td>
                    <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td onclick="javascript:openData('${item.reId}','');">
                        <a>处理</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/fee/list?menuCode=${menuCode}&feeReName=${feeReName}&success=${success}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    var sortType = $("#sortType").val();
    var sortField= $("#sortField").val();
    if (sortType!=null && sortType !=''){
        $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
    }

    $(".title_sort").click(function () {
        var sortField = $(this).attr("data-field");
        var sortType = 'up';
        var $children = $(this).children();
        if ($children.find(".active").html()==undefined){
            $children.find(".icon-up").addClass("active");
        }else {
            var t = $children.find(".active").attr("class");
            if (t.toString().indexOf("up")>0){
                $children.find(".icon-up").removeClass("active");
                $children.find(".icon-down").addClass("active");
                sortType = 'down';
            }else {
                $children.find(".icon-up").addClass("active");
                $children.find(".icon-down").removeClass("active");
                sortType = 'up';
            }
        }
        $("#sortField").val(sortField);
        $("#sortType").val(sortType);
        $('#batchOperateBtn').click();
    });

    var edit = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"添加",
            height:height,
            width:width,
            url:"${ctx}/fee/add?id="+id+"&menuCode=${menuCode}",
            load: true
        });
    }

    var openData = function(reId,reState){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/fee/list?menuCode=list&searchCode=reimbursement-manager-list&reId="+reId+"&reState=" + reState,
            load: true
        });
    }

</script>
</body>
</html>
