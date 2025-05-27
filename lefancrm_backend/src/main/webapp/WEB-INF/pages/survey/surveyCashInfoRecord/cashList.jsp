<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
    </style>
</head>
<body>

<div class="main">

<div class="main-boy">
    <div class="table-title">银行卡信息
        <c:if test="${surveyBankCard !=null}">
            <a href="javascript:void(0)" onclick="editBank('${surveyBankCard.id}','bankCard')">修改</a>
        </c:if>
    </div>
    <c:if test="${surveyBankCard !=null}">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>银行卡号</td>
                    <td>${surveyBankCard.cardNo}</td>
                    <td>开户行</td>
                    <td>${surveyBankCard.bankName}</td>
                    <td>开户支行</td>
                    <td>${surveyBankCard.bankBranchName}</td>
                </tr>
                <tr>
                    <td>持卡人名称</td>
                    <td>${surveyBankCard.holderName}</td>
                    <td>银行卡图片</td>
                    <td colspan="3"><img src="${surveyBankCard.cardImg}" width="75px;" height="75px;" class="picToBig"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${surveyBankCard ==null}">
        <button class="butList active" onclick="addBank('bankCard');">绑定银行卡</button>
    </c:if>

        <br>
        <div class="table-title">提现数据</div>
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="menuCode" value="${menuCode}">
                    <div class="form-group">
                        案件名称: <input name="surveyCaseName" type="text"  value="${surveyCaseName}" class="form-control">
                    </div>
                    <div class="form-group">
                        机构名称: <input name="franchiseeName" type="text"  value="${franchiseeName}" class="form-control">
                    </div>
                    <div class="form-group">
                        调查员姓名: <input name="surveyUserName" type="text"  value="${surveyUserName}" class="form-control">
                    </div>
                    <div class="form-group">
                        类型:
                        <select name="cashType" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${cashType == '1'}">selected="selected" </c:if> >基本费</option>
                            <option value="2" <c:if test="${cashType == '2'}">selected="selected" </c:if> >减损奖励</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="80"><input type="checkbox" id="all" >全选</th>
                <th width="150">案件名称</th>
                <th width="150">机构名称</th>
                <th width="100">调查员姓名</th>
                <th width="150">任务价格</th>
                <th width="120">类型</th>
                <th width="100">创建时间</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <input type="checkbox" value="${item.id}" name="buss">
                    </td>
                    <td>${item.surveyCaseName}</td>
                    <td>${item.franchiseeName}</td>
                    <td>${item.surveyUserName}</td>
                    <td>${item.surveyTaskMoney}</td>
                    <td>
                        <c:if test="${item.cashType == 1}">基本费</c:if>
                        <c:if test="${item.cashType == 2}">减损奖励</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <button id="11" type="submit" onclick="operateList()" class="btn btn-default">确定提交</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>

</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    var addBank = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var editBank = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/edit?surveyCode="+surveyCode+"&id="+id
        });
    }

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    var operateList =function(){
        var check_name = document.getElementsByName("buss");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length>0){
            batchOperate(idArr);
        }
    }
    function batchOperate(idArr){
        <%--var url = null,param = null;--%>
        <%--url = "${ctx}/baseSurvey/operate";param = {"idList":idArr.join(","),"btnCode":1100,"surveyCode":"cashInfoRecord"};--%>
        <%--ajaxSubmit(url,param,function(v,e,p){--%>
            <%--alert(e.data.msg);--%>
            <%--location.reload();--%>
        <%--})--%>

        var url="${ctx}/baseSurvey/popup?idList=" + idArr.join(",") + "&surveyCode=cashInfoRecord" + "&btnCode=1100";
        openDialog({
            frame:true,
            title:"详情",
            height:700,
            width:1000,
            url:  url,
            load:true
        });


    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>