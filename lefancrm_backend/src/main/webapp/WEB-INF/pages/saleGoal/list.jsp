<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>业绩目标列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>业绩目标列表 <small>共<span>1</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                    <div class="btn-group">
                        <button id="test" onclick="addSaleGoal()" type="button" class="btn btn-default">新增业绩目标</button>
                    </div>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">年度</th>
                <th width="120">业绩目标</th>
                <th width="90">新增时间</th>
                <th width="90">修改时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.year}</td>
                    <td>
                        ￥${item.saleGoal}
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="#" onclick="updateSaleGoal(${item.year})">修改</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/

    function addSaleGoal(){

        if(!${isAdd}){
            openDialog({
                frame:true,
                title:"新增业绩目标设置",
                height:500,
                width:1300,
                url:"${ctx}/sale/goal/index"
            });
        }else{
            alert("今年业绩目标已经创建");
        }
    }
   function updateSaleGoal(year){
       openDialog({
           frame:true,
           title:"修改业绩目标设置",
           height:400,
           width:1300,
           url:"${ctx}/sale/goal/index?year="+year
       });
   }
</script>
</body>
</html>
