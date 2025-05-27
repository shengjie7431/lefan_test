<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <!--<style>
        #modal-content{
            margin: 0;
            width: 100%;
            height: 100%;
        }
        #dialogBoxID{
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }
    </style>-->
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/center/list?menuType=${menuType}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="pgChoose" value="${pgChoose}">
                    <input type="hidden" name="spChoose" value="${spChoose}">
                    <input type="hidden" name="ssChoose" value="${ssChoose}">
                    <div class="form-group">
                        案件类型: <select name="type"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1" <c:if test="${type==1}">selected="selected" </c:if>>乐赔宝</option>
                        <option value="2" <c:if test="${type==2}">selected="selected" </c:if>>索赔通</option>
                        <option value="3" <c:if test="${type==3}">selected="selected" </c:if>>垫无忧</option>
                        <option value="4" <c:if test="${type==4}">selected="selected" </c:if>>幸运宝</option>
                        <option value="5" <c:if test="${type==5}">selected="selected" </c:if>>贷款申请</option>
                        <option value="6" <c:if test="${type==6}">selected="selected" </c:if>>伤残预估</option>
                        <option value="7" <c:if test="${type==7}">selected="selected" </c:if>>其他</option>
                    </select>
                    </div>
                    <c:if test="${menuType == 1 or menuType == 52 or menuType == 53 or menuType == 54}">
                        <div class="form-group">
                            案件阶段: <select name="gradationState"  class="form-control">
                                <option value="" >全部</option>
                                <option value="1" <c:if test="${gradationState==1}">selected="selected" </c:if>>洽谈阶段</option>
                                <option value="2" <c:if test="${gradationState==2}">selected="selected" </c:if>>评估阶段</option>
                                <option value="3" <c:if test="${gradationState==3}">selected="selected" </c:if>>索赔阶段</option>
                                <option value="6" <c:if test="${gradationState==6}">selected="selected" </c:if>>诉讼阶段</option>
                                <option value="4" <c:if test="${gradationState==4}">selected="selected" </c:if>>已结案</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuType == 11 or menuType == 111 or menuType == 1111 or menuType ==10 or menuType ==15 or menuType ==20 or menuType ==21 or menuType ==25 or menuType ==26}">
                        <div class="form-group">
                            案件状态: <select name="caseState"  class="form-control">
                                <option value="" >全部</option>
                                <c:if test="${menuType == 11 or menuType == 111 or menuType == 1111}">
                                    <option value="1" <c:if test="${caseState==1}">selected="selected" </c:if>>待接收</option>
                                </c:if>
                                <option value="2" <c:if test="${caseState==2}">selected="selected" </c:if>>已接收</option>
                                <option value="3" <c:if test="${caseState==3}">selected="selected" </c:if>>已预约</option>
                                <option value="4" <c:if test="${caseState==4}">selected="selected" </c:if>>已放弃</option>
                                <option value="5" <c:if test="${caseState==5}">selected="selected" </c:if>>已签约</option>
                                <option value="6" <c:if test="${caseState==6}">selected="selected" </c:if>>待跟进</option>
                                <option value="7" <c:if test="${caseState==7}">selected="selected" </c:if>>虚假信息</option>
                                <option value="8" <c:if test="${caseState==8}">selected="selected" </c:if>>已受理</option>
                            </select>
                        </div>
                    </c:if>
                    <div class="form-group">
                        案件标题: <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
                    </div>
                    <div class="form-group">
                        是否生产案件:
                        <select name="isTestcase"  class="form-control">
                            <option value="-1" >全部</option>
                            <option value="0" <c:if test="${isTestcase==0}">selected="selected" </c:if>>是</option>
                            <option value="1" <c:if test="${isTestcase==1}">selected="selected" </c:if>>否</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        申请人姓名: <input name="caseName" type="text"  value="${caseName}" class="form-control">
                        </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        申请人电话: <input name="caseTel" type="text"  value="${caseTel}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
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
                <th width="80">案件编号</th>
                <th width="100">类型</th>
                <th width="90">阶段</th>
                <th width="90">状态</th>
                <th width="300">案件标题</th>
                <th width="100">申请人姓名</th>
                <th width="80">申请人电话</th>
                <th width="200">当前状态</th>

                <c:if test="${menuType == 27}">
                    <th width="100">服务费金额</th>
                    <th width="100">确认状态</th>
                    <th width="200">确认时间</th>
                </c:if>
                <c:if test="${menuType == 21}">
                    <th width="200">保证保险费用</th>
                </c:if>
                <c:if test="${menuType == 53}">
                    <th width="200">业务员</th>
                    <th width="120">被分配机构</th>
                    <th width="100">被分配人</th>
                </c:if>
                <th width="200">更新时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr <c:if test="${(menuType == 26 || menuType == 36 || menuType == 52 || 51 == menuType) && item.isShowRed}">style="color: #ff0000;"</c:if>>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.type == 1}">乐赔宝</c:if>
                        <c:if test="${item.type == 2}">索赔通</c:if>
                        <c:if test="${item.type == 3}">垫无忧</c:if>
                        <c:if test="${item.type == 4}">幸运宝</c:if>
                        <c:if test="${item.type == 5}">贷款申请</c:if>
                        <c:if test="${item.type == 6}">伤残预估</c:if>
                        <c:if test="${item.type == 7}">其他</c:if>
                    </td>
                    <td>
                        <c:if test="${item.gradationState == 1}">
                            <span style="color: #FF4500">洽谈业务</span>
                        </c:if>
                        <c:if test="${item.gradationState == 2}">
                            <span style="color: #66CD00">评估阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 3}">
                            <span style="color: #66CD00">索赔阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 4}">
                            <span style="color: #1E90FF">结案</span>
                        </c:if>
                        <c:if test="${item.gradationState == 5}">
                            <span style="color: #1E90FF">风控部门审核阶段</span>
                        </c:if>
                        <c:if test="${item.gradationState == 6}">
                            <span style="color: #1E90FF">诉讼阶段</span>
                        </c:if>
                    </td>
                    <td>${item.caseStateStr}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>${item.caseTel}</td>
                    <td>${item.listStateName}</td>

                    <c:if test="${menuType == 27}">
                        <td width="100">${item.defineAmount}</td>
                        <td width="100">${item.defineStateName}</td>
                        <td width="200"><fmt:formatDate value="${item.defineDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </c:if>
                    <c:if test="${menuType == 21}">
                        <th width="200">${item.insuredAmount}</th>
                    </c:if>
                    <c:if test="${menuType == 53}">
                        <td>${item.operatorName}</td>
                        <td>${item.orgName}</td>
                        <td>${item.orgUserName}</td>
                    </c:if>
                    <td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                         <a href="javascript:info('${item.id}');">操作</a>
                         <%--<a href="javascript:infoNew('${item.id}');">新详情</a>--%>
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
            <jsp:param name="requestUrl" value="${ctx}/case/center/list?menuType=${menuType}&type=${type}&gradationState=${gradationState}&caseState=${caseState}&caseNo=${caseNo}&caseName=${caseName}&caseTel=${caseTel}&caseTitle=${caseTitle}&pgChoose=${pgChoose}&spChoose=${spChoose}&ssChoose=${ssChoose}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   function info(id){
       var width = $(document.body).outerWidth();
       var url = "${ctx}/case/center/info?id="+id+"&menuType=${menuType}";
//       alert($("#tabs").tabs("exists","sss"));
//       addTab('案件信息',url,true);return;
       openDialog({
           frame:true,
           title:"案件信息",
           height:750,
           width:width,
           url : url,
           load:true
       });
   }
</script>
</body>
</html>
