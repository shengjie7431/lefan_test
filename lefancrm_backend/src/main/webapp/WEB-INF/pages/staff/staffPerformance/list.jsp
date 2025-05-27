<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>绩效管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .class-list a{
            color: #428bca;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>绩效管理列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/staff/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="type" value="${type}">
                    <div class="form-group">
                        月份:
                        <input name="workTime" type="text" value="${workTime}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <c:if test="${type=='info' && isHr}">
                            &nbsp; &nbsp;<button onclick="edit('')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-content">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="100">月份</th>
                    <th width="100">状态</th>
                    <c:if test="${(type=='info' && !organManager && !surveyUser) || showPassWord || isHr}">
                        <th width="100">查询密码</th>
                    </c:if>
                    <th width="100">人事专员</th>
                    <th width="150">分管总一审</th>
                    <th width="300">机构经理</th>
                    <th width="150">分管总二审</th>
                    <th width="100">人事主管</th>
                    <th width="100">总部人员</th>
                    <th width="100">完成时间</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>${item.workTime}</td>
                        <td>${item.performanceStateName}</td>
                        <c:if test="${(type=='info' && !organManager && !surveyUser) || showPassWord || isHr}">
                            <td>${item.queryPassword}</td>
                        </c:if>
                        <td>${item.hrName}</td>
                        <td>
                            <c:forEach items="${item.firstSuperiorManagerList}" var="sItem">
                            <span <c:if test="${sItem.state == 1}">style="color: green" </c:if><c:if test="${sItem.state == 0}">style="color: #FFA500" </c:if>>
                                    ${sItem.userName}
                            </span>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${item.organManagerList}" var="oItem">
                            <span <c:if test="${oItem.state == 1}">style="color: green" </c:if><c:if test="${oItem.state == 0}">style="color: #FFA500" </c:if>>
                                    ${oItem.userName}
                            </span>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${item.superiorManagerList}" var="sItem">
                            <span <c:if test="${sItem.state == 1}">style="color: green" </c:if><c:if test="${sItem.state == 0}">style="color: #FFA500" </c:if>>
                                    ${sItem.userName}
                            </span>
                            </c:forEach>
                        </td>
                        <td>${item.hrManageName}</td>
                        <td>${item.generalManagerName}</td>
                        <td><fmt:formatDate value="${item.generalManagerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <!--<td><a href="javascript:edit('${item.id}');">编辑</a></td>-->
                        <td>
                            <c:if test="${type=='view' }">
                                <a href="javascript:info('${item.id}','${item.workTime}','view');">查看</a>&nbsp&nbsp
                            </c:if>
                            <c:if test="${type=='info' }">
                                <c:if test="${organManager}">
                                    <c:if test="${hrManager || isHr || superManager}">
                                        <a href="javascript:info('${item.id}','${item.workTime}');">处理</a>&nbsp&nbsp
                                    </c:if>
                                    <c:if test="${!hrManager && !isHr && !superManager}">
                                        <a href="javascript:info('${item.id}','${item.workTime}','organManager');">处理</a>&nbsp&nbsp
                                    </c:if>
                                </c:if>
                                <c:if test="${!organManager}">
                                    <a href="javascript:info('${item.id}','${item.workTime}');">处理</a>&nbsp&nbsp
                                </c:if>
                                <c:if test="${item.performanceState == 0}">
                                    <a href="javascript:operate('${item.id}','9999',true);">删除</a>
                                </c:if>
                            </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/staff/list?surveyCode=${surveyCode}&workTime=${workTime}&type=${type}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<script src="${ctx}/js/layui/layui.js"></script>
<script>

    var layer = ''
    layui.use("layer",function () { layer = layui.layer})
    var info = function(id,workTime,type){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;

        if (!type){
            //首次被加载 清空查询条件
            sessionStorage.removeItem('param')
            sessionStorage.removeItem('gzNew')
            sessionStorage.removeItem('sort_field')
            openDialog({
                frame:true,
                title:workTime+"  绩效详情",
                height:height,
                width:width,
                url:"${ctx}/staff/info?id="+id+"&surveyCode="+'${surveyCode}',
                withCredentials: true
            });
        }else if(type && type == 'view'){
                layer.prompt({title:"请输入查询密码（若遗忘密码请联系人事人员）"},function (text,index) {
                    var $ = layui.$
                    $.ajax({
                        url:'${ctx}/staff/operate',
                        type:"post",
                        data :{
                            id:id,
                            btnCode:"passWord",
                            operateCode:'performance',
                            queryPassword: text
                        },
                        success:function(res){
                            res = JSON.parse(res)
                            if (res.isSuccess){
                                layer.close(index)
                                sessionStorage.removeItem('param')
                                sessionStorage.removeItem('gzNew')
                                sessionStorage.removeItem('sort_field')
                                openDialog({
                                    frame:true,
                                    title:workTime+"  绩效详情",
                                    height:height,
                                    width:width,
                                    url:"${ctx}/staff/info?id="+id+"&surveyCode="+'${surveyCode}'+ '&type=view',
                                    withCredentials: true
                                });
                            }else {
                                layer.msg('密码错误，请重试',{time:2000,icon:5})
                            }
                        }
                    });
                })
        }else if(type && type == 'organManager'){
            layer.prompt({title:"请输入查询密码（若遗忘密码请联系人事人员）"},function (text,index) {
                var $ = layui.$
                $.ajax({
                    url:'${ctx}/staff/operate',
                    type:"post",
                    data :{
                        id:id,
                        btnCode:"passWord",
                        operateCode:'performance',
                        queryPassword: text
                    },
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.close(index)
                            sessionStorage.removeItem('param')
                            sessionStorage.removeItem('gzNew')
                            sessionStorage.removeItem('sort_field')
                            openDialog({
                                frame:true,
                                title:workTime+"  绩效详情",
                                height:height,
                                width:width,
                                url:"${ctx}/staff/info?id="+id+"&surveyCode="+'${surveyCode}',
                                withCredentials: true
                            });
                        }else {
                            layer.msg('密码错误，请重试',{time:2000,icon:5})
                        }
                    }
                });
            })
        }
    }
</script>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    $(document).ready(function () {
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 40;
        if($(window.parent).width() > 1366){
            height_doc = height_doc - 28;
        }
        $(".table-content").height(height_doc).css({
            overflow: 'auto'
        });
    })


    var edit = function(id){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/staff/edit?id="+id+"&surveyCode="+'${surveyCode}'
        });
    }
    function operate(id,btnCode,ajax){
        if(ajax){
            var url = "${ctx}/staff/operate",param = {"id":id,"btnCode":btnCode,"operateCode":'performance'};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {

        }
    }
</script>
</body>
</html>
