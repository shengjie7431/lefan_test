<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>客户信息列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>客户信息列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/cci/list" method="post">
                    <div class="form-group">
                        跟进状态: <select name="caseProgress"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1">初访</option>
                        <option value="2">洽谈中</option>
                        <option value="3">待签约</option>
                        <option value="4">已签约</option>
                        <option value="5">暂时搁置</option>
                        <option value="6">已放弃</option>
                    </select>
                    </div>
                    <div class="form-group">
                       案件来源: <select name="caseSource"  class="form-control">
                            <option value="" >全部</option>
                            <option value="0">医院</option>
                            <option value="1">小程序</option>
                            <option value="2">工作室</option>
                            <option value="3">保司</option>
                            <option value="4">交警</option>
                            <option value="5">陌拜</option>
                            <option value="6">护工</option>
                            <option value="7">转介</option>
                            <option value="8">其他</option>
                        </select>
                        </div>
                        <%----%>
                    <div class="form-group">
                        所属部门: <input name="orgId" type="text"   class="form-control">
                        </div>
                    <div class="form-group">
                        负责人: <input name="ccId" type="text"  class="form-control">
                        </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                    <div class="btn-group">
                        <button id="test" onclick="addCustomer()" type="button" class="btn btn-default">新增</button>
                    </div>
                    <div class="btn-group">
                        <button id="download" onclick="download111()" type="button" class="btn btn-default">下载模板</button>
                    </div>
                    <div class="btn-group">
                        <span class="btn btn-success fileinput-button">
                            <i class="glyphicon glyphicon-plus">
                            </i>
                            <span>导入</span>
                        <input id="fileupload" type="file"  name="fileupload" multiple data-url="${ctx}/readExcel">
                        </span>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">客户名称</th>
                <th width="100">客户电话</th>
                <th width="120">客户类型</th>
                <th width="90">跟进状态</th>
                <th width="90">案件来源</th>
                <th width="100">实际跟进时间</th>
                <th width="100">下次跟进时间</th>
                <th width="80">负责人</th>
                <th width="100">负责部门</th>
                <th width="150">最新修改时间</th>
                <th width="200">最新跟进记录</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td><a href="${ctx}/cci/index?id=${item.id}&name=${item.userName}&familyAddress=${item.familyAddress}&ccName=${item.ccName}&caseProgress=${item.caseProgress}"> ${item.userName==null?'无数据':item.userName}</a></td>
                    <td>${item.userPhone}</td>
                    <td>
                        <c:if test="${item.isIntention!=null}">
                        <c:if test="${item.isIntention == 0}">非意向</c:if>
                        <c:if test="${item.isIntention == 1}">意向</c:if>
                        </c:if>
                        <c:if test="${item.isIntention==null}">无数据</c:if>
                    </td>
                    <td>
                        <c:if test="${item.caseProgress!=null}">
                        <c:if test="${item.caseProgress == 1}">初访</c:if>
                        <c:if test="${item.caseProgress == 2}">洽谈中</c:if>
                        <c:if test="${item.caseProgress == 3}">待签约</c:if>
                        <c:if test="${item.caseProgress == 4}">已签约</c:if>
                        <c:if test="${item.caseProgress == 5}">暂时搁置</c:if>
                        <c:if test="${item.caseProgress == 6}">已放弃</c:if>
                        </c:if>
                        <c:if test="${item.caseProgress==null}">无数据</c:if>
                    </td>
                    <td>
                        <c:if test="${item.caseSource!=null}">
                        <c:if test="${item.caseSource == 0}">医院</c:if>
                        <c:if test="${item.caseSource == 1}">小程序</c:if>
                        <c:if test="${item.caseSource == 2}">工作室</c:if>
                        <c:if test="${item.caseSource == 3}">保司</c:if>
                        <c:if test="${item.caseSource == 4}">交警</c:if>
                        <c:if test="${item.caseSource == 5}">陌拜</c:if>
                        <c:if test="${item.caseSource == 6}">护工</c:if>
                        <c:if test="${item.caseSource == 7}">转介</c:if>
                        <c:if test="${item.caseSource == 8}">其他</c:if>
                        </c:if>
                        <c:if test="${item.caseSource==null}">无数据</c:if>
                    </td>
                    <td>
                        <c:if test="${item.followTime!=null}">
                        <fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd"/>
                        </c:if>
                        <c:if test="${item.followTime==null}">无数据</c:if>
                    </td>
                    <td>
                        <c:if test="${item.nextFollowTime!=null}">
                        <fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd"/>
                        </c:if>
                        <c:if test="${item.nextFollowTime==null}">无数据</c:if>
                    </td>
                    <td>${item.ccName==null?'无数据':item.ccName}</td>
                    <td>${item.orgName==null?'无数据':item.orgName}</td>
                    <td>
                        <c:if test="${item.updateTime!=null}">
                        <fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:if>
                        <c:if test="${item.updateTime==null}">无数据</c:if>
                    </td>
                    <td>${item.followDesc==null?'无数据':item.followDesc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/cci/list?caseProgress=${caseProgress}&caseSource=${caseSource}&orgId=${orgId}&ccId=${ccId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
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

   var editor1;
   KindEditor.ready(function(K) {
       editor1 = K.create('textarea[name="content"]', {
           cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
           uploadJson : '${ctx}/uploadFileForKindEditor'
       });
   });

   $('#fileupload').fileupload({
       done: function (e, data) {
           var r  = data.result;
           if (r.code == 200){
                alert("导入成功！");
           }else {
               alert("上传失败，请重试111");
           }
       }
   });

   function download111(){
       <%--window.location.href="${ctx}/js/template.xlsx";--%>
       window.open("${ctx}/js/template.xlsx");
   }

    function addCustomer(){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:400,
            width:1000,
            url:"${ctx}/cci/injuredInformationAddView?parentId=0"
        });
    }

</script>
</body>
</html>
