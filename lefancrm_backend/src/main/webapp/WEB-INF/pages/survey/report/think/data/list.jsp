<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据录入</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>数据录入 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/think/data/list" method="post">
                    <div class="form-group">
                        月份:
                        <input name="thinkTime" type="text" value="${thinkTime}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <c:if test="${csManager}">
                            <input type="button" onclick="operate(1,'ceshi-generate',true,this)" class="btn btn-default" value="测试生成记录"></input>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">月份</th>
                <th >机构经理</th>
                <th >操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><fmt:formatDate value="${item.thinkTime}" pattern="yyyy-MM"/>
                        <input type="hidden" value='<fmt:formatDate value="${item.thinkTime}" pattern="yyyy-MM"/>' id="itemMonth${item.id}" />
                    </td>
                    <td>
                        <c:forEach items="${item.managerUsers}" var="oItem">
                            <span <c:if test="${oItem.state != 1}">style="color: green" </c:if><c:if test="${oItem.state == 1}">style="color: #FFA500" </c:if>>
                                    ${oItem.userName}
                            </span>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.id})">处理</a>
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
            <jsp:param name="requestUrl" value="${ctx}/think/data/list?thinkTime=${thinkTime}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script>

    var info = function(thinkDataId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/think/data/info?thinkDataId=" + thinkDataId + "&itemMonth=" + $("#itemMonth" + thinkDataId).val()
        });
    }

    function operate(thinkDataId,btnCode,ajax,_this){
        if(ajax){
            var url = "${ctx}/think/data/operate",data = {"thinkDataId":thinkDataId,"btnCode":btnCode};
            layer.confirm("确定", {
                btn: ['是','否'] //按钮
            }, function(){
                $('.layui-layer-btn .layui-layer-btn0').css({
                    'pointer-events': 'none'
                })
                $.ajax({
                    url:url,
                    type:"post",
                    data :data,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })

                        } else{
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            }, function(){

            });
        }else {

        }
    }

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    layui.use(['table','jquery','layer','soulTable'],function () {
        var table = layui.table,
            $ = layui.jquery,
            soulTable = layui.soulTable

        layer = layui.layer
    })

</script>
</body>
</html>
