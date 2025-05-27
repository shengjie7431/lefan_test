<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/hobo/case/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <div class="form-group">
                        快捷查询：<input style="width: 310px" name="searchStr" placeholder="客户名称、部门、姓名、身份证号、手机号码" type="text" value="${searchStr}"  class="form-control">
                    </div>
                    <div class="form-group">
                        车牌号：<input   name="carNo" type="text" value="${carNo}"  class="form-control">
                    </div>
                    <div class="form-group">
                        产品名称：<input  name="proName" type="text" value="${proName}"  class="form-control">
                    </div>
                    <div class="form-group">
                        委托时间：
                        <input name="entrustTimeStart" type="text" value="${entrustTimeStart}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="entrustTimeEnd" type="text" value="${entrustTimeEnd}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp;
                    <button onclick="importData()" type="button" class="btn btn-default">导入</button>
                    <button class="btn btn-default"><a href="${ctx}/hobo/case/exportData?exportType=1&searchStr=${searchStr}">导出</a></button>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>客户名称</th>
                <th>部门</th>
                <th>姓名</th>
                <th>身份证号</th>
                <th>手机号</th>
                <th>车牌号</th>
                <th>产品名称</th>
                <th>产品单价</th>
                <th>委托时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td >${item.insureName}</td>
                    <td>${item.insureDeptName}</td>
                    <td>${item.name}</td>
                    <td>${item.idCard}</td>
                    <td>${item.tel}</td>
                    <td>${item.carNo}</td>
                    <td>${item.proName}</td>
                    <td>${item.proPrice}</td>
                    <td><fmt:formatDate value="${item.entrustTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.id})">详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/hobo/case/list?searchStr=${searchStr}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${ctx}/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
    <script src="${ctx}/js/layui/layui.js"></script>
</div>


<script>
    function importData(){
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"导入",
            height:height,
            width:800,
            url:"${ctx}/hobo/case/info?oprType=import",
            load:true
        });
    }

    function exportData(){
        var _href = '${ctx}/hobo/case/exportData?exportType=1'
        var aLink = document.createElement('a');
        aLink.href= _href
        aLink.dispatchEvent(new MouseEvent('click', {
            bubbles: true,
            cancelable: true,
            view: window
        }));
        setTimeout(function () {
            _this.removeAttr('disabled')
        },5000)
    }

    var info = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:"${ctx}/hobo/case/info?id=" + id + "&oprType=detail",
            load:true
        });
    }

    function operate(id,btnCode,ajax,_this){
        if(ajax){
            var url = "${ctx}/survey/check/operate",data = {"id":id,"btnCode":btnCode};
            layer.confirm("确定删除", {
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

</script>
</body>
</html>
