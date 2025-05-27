<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%--<link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">--%>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .dalogs{
            display: none;
            position: absolute;
            top: 5%;
            left: 5%;
            width: 90%;
            min-height: 400px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
            padding: 20px;
        }

        .dalogsAdd{
            width: 600px;
            left: 30%;
        }
        .dalogsTip{
            width: 300px;
            height: 200px;
        }
        .viewdesc{
            cursor: pointer;
            text-decoration: underline;
            color: #3BA9FF;
        }
        .aColor{
            cursor: pointer;
            text-decoration: underline;
            color: #3BA9FF;
        }
        .reason, .reasonRead {
            width: 200px;
            height: 40px;
            line-height: 40px;
            margin:  20px auto;
            background-color: #3BA9FF;
            color: #fff;
            text-align: center;
            cursor: pointer;
        }
        .layui-input-block{
            width: 90%;
            margin: 0 auto;
        }
        .signBar, .signBar2{
            width: 80px;
            height: 26px;
            line-height: 26px;
            color: #fff;
            background-color: #3ba9ff;
            text-align: center;
            cursor: pointer;
        }
        .activeY{
            background-color: #fed36e!important;
        }
        .activeZ{
            background-color: rgba(41,214,49,0.35)!important;
        }
        .activeW{
            background-color: rgba(236,128,126,0.4)!important;
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
                <form class="form-inline" role="form" action="${ctx}/staff/popup?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="menuCode" id="menuCode" value="${menuCode}"/>
                    <input type="hidden" name="caseCode" id="caseCode" value="${caseCode}"/>
                    <input type="hidden" name="scoreRole" id="scoreRole" value="${scoreRole}"/>
                    <input type="hidden" name="btnCode" id="btnCode" value="${btnCode}"/>
                    <%--<input type="hidden" name="caseState" id="caseState" value="${caseState}"/>
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" value="${entrustOrgIds}"/>
                    <input type="hidden" name="orgCaseState" id="orgCaseState" value="${orgCaseState}"/>
                    <input type="hidden" name="surveyState" id="surveyState" value="${surveyState}"/>
                    <input type="hidden" name="surveyOrgId" id="surveyOrgId" value="${surveyOrgId}"/>
                    <input type="hidden" name="surveyUserId" id="surveyUserId" value="${surveyUserId}"/>
                    <input type="hidden" name="startTime" id="startTime" value="${startTime}"/>
                    <input type="hidden" name="endTime" id="endTime" value="${endTime}"/>--%>
                    <input type="hidden" name="searchType" id="searchType" value="${searchType}"/>
                    <input type="hidden" name="roleCode" id="roleCode" value="${roleCode}"/>

                    <input type="hidden" name="isManager" id="isManager" value="${isManager}"/>
                    <input type="hidden" name="organManager" id="organManager" value="${organManager}"/>
                    <input type="hidden" name="surveyUser" id="surveyUser" value="${surveyUser}"/>
                    <input type="hidden" name="orgAttr" id="orgAttr" value="${orgAttr}"/>

                    <input type="hidden" name="surveyCode" id="surveyCode" value="${surveyCode}"/>
                    <input type="hidden" name="staffPerformanceId" id="staffPerformanceId" value="${staffPerformanceId}"/>
                    <input type="hidden" name="userId" id="userId" value="${userId}"/>
                    <input type="hidden" name="id" id="id" value="${id}"/>

                    <div class="form-group">
                        快捷查询:<input style="width: 350px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
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
                <th width="100">案件编号</th>
                <th width="100">被调查人</th>
                <th width="100">互助平台</th>
                <c:if test="${btnCode == 'case'}">
                    <th width="100">分配任务类型</th>
                    <th width="100">分派调查员日期</th>
                    <th width="100">提交审核日期</th>
                    <th width="100">调查员截止日期</th>
                    <th width="100">调查员时效</th>
                    <th width="100">超期考核绩效</th>
                    <th width="100">调查总积分</th>
                    <c:if test="${orgAttr == 2}">
                        <th width="100">阳性总积分</th>
                        <th width="100">合计积分</th>
                    </c:if>
                    <c:if test="${orgAttr == 1}">
                        <th width="100">阳性奖励</th>
                        <th width="100">合计积分</th>
                    </c:if>
                    <th width="100">操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody class="class-list info-1129">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr class="${item.staffOpinionState == 1 ? 'activeZ' : ''} ${item.staffOpinionState == 0 ? 'activeW' : ''}">
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.surveyInfoId},${item.assignCaseId},'${item.investigatorCaseId}','${item.surveyUserName}','${item.staffOpinion}')" class="aColor">${item.surveyNo}</a>
                    </td>
                    <td>${item.surveyPerson}</td>
                    <td>${item.entrustOrgName}</td>
                    <c:if test="${btnCode == 'case'}">
                        <td>
                            <a data-value="<c:forEach items="${item.tasks}" var="task" >${task.taskName}&nbsp;</c:forEach>"
                               title="<c:forEach items="${item.tasks}" var="task" >${task.taskName}&nbsp;</c:forEach>" class="viewdesc">查看详情</a>
                        </td>
                        <td width="100"><fmt:formatDate value="${item.userAssignDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.userCreportDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.userEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="100">
                            <c:if test="${item.userDays == 0}">
                                <label>${item.userDays}天</label>
                            </c:if>
                            <c:if test="${item.userDays < 0}">
                                <label style="color: red">${item.userDays.toString().replace('-', '')}天</label>
                            </c:if>
                            <c:if test="${item.userDays > 0}">
                                <label style="color: #00ee00">${item.userDays}天</label>
                            </c:if>
                        </td>
                        <td width="100">${item.overdueAgingRate}</td>
                        <td width="100">
                            <c:if test="${item.assessScore != item.userBaseScore}">
                                <span style="color: red;text-decoration: line-through;">${item.assessScore}</span>&nbsp;&nbsp;
                            </c:if>
                            <span>${item.userBaseScore}</span>
                        </td>

                        <c:if test="${orgAttr == 2}">
                            <td width="100">
                                <c:if test="${item.assessSunScore != item.userSunScore}">
                                    <span style="color: red;text-decoration: line-through;">${item.assessSunScore}</span>&nbsp;&nbsp;
                                </c:if>
                                <span>${item.userSunScore}</span>
                            </td>
                            <td width="100">
                                <c:if test="${(item.assessScore + item.assessSunScore) != item.userScore}">
                                    <span style="color: red;text-decoration: line-through;">${item.assessScore + item.assessSunScore}</span>&nbsp;&nbsp;
                                </c:if>
                                <span>${item.userScore}</span>
                            </td>
                        </c:if>
                        <c:if test="${orgAttr == 1}">
                            <td width="100">
                                <span>${item.sunMoneyBs}</span>
                            </td>
                            <td width="100">
                                <c:if test="${item.assessScore != item.userBaseScore}">
                                    <span style="color: red;text-decoration: line-through;">${item.assessScore}</span>&nbsp;&nbsp;
                                </c:if>
                                <span>${item.userBaseScore}</span>
                            </td>
                        </c:if>
                        <td width="100">
                            <c:if test="${(item.staffOpinion == '' || item.staffOpinion == null) && (roleCode == 'surveyUser-step' || roleCode == 'organManager-step')}">
                                <div class="signBar" data-id="${item.investigatorCaseId}">填写意见</div>
                            </c:if>
                            <%--<c:if test="${(item.staffOpinion != '' && item.staffOpinion != null) &&  (roleCode == 'surveyUser-step' || roleCode == 'organManager-step' || roleCode == 'superiorManager-step')}">
                                <div class="signBar2" onclick="info(${item.surveyInfoId},${item.assignCaseId},'${item.investigatorCaseId}','${item.surveyUserName}','${item.staffOpinion}')">查看意见</div>
                            </c:if>--%>
                            <c:if test="${item.staffOpinion != '' && item.staffOpinion != null}">
                                <c:if test="${roleCode == 'surveyUser-step' || roleCode == 'organManager-step'}">
                                    <div class="signBar" data-id="${item.investigatorCaseId}" data-reason="${item.staffOpinion}">查看意见</div>
                                </c:if>
                                <c:if test="${roleCode == 'superiorManager-step'}">
                                    <div class="signBar2" onclick="info(${item.surveyInfoId},${item.assignCaseId},'${item.investigatorCaseId}','${item.surveyUserName}','${item.staffOpinion}')">查看意见</div>
                                </c:if>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <%--<c:if test="${(staffPerformancePersonnel.organOpinion == '' || staffPerformancePersonnel.organOpinion == null) && roleCode == 'surveyUser-step' && surveyUser}">
        <div class="reason">对积分有异议？填写意见</div>
    </c:if>
    <c:if test="${(staffPerformancePersonnel.organOpinion != '' && staffPerformancePersonnel.organOpinion != null) && roleCode == 'surveyUser-step' && surveyUser}">
        <div class="reason">查看意见</div>
    </c:if>
    <c:if test="${(staffPerformancePersonnel.organOpinion != '' && staffPerformancePersonnel.organOpinion != null) && roleCode != 'surveyUser-step' && surveyUser}">
        <div class="reasonRead">查看意见</div>
    </c:if>--%>
    <input type="hidden" value="${staffPerformancePersonnel.id}" id="obj-id">
    <input type="hidden" value="${staffPerformancePersonnel.organOpinion}" id="obj-organOpinion">

    <div class="dalogs dalogsTip">
        <div class="close" onclick="javascript:$('.dalogsTip').hide()">×</div>
        <div class="d_content" style="padding: 20px;overflow: auto;height: 100%"></div>
    </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/staff/popup?menuCode=${menuCode}&caseCode=${caseCode}&searchStr=${searchStr}&btnCode=${btnCode}&caseState=${caseState}&entrustOrgIds=${entrustOrgIds}&orgCaseState=${orgCaseState}&surveyState=${surveyState}&surveyOrgId=${surveyOrgId}&surveyUserId=${surveyUserId}&startTime=${startTime}&endTime=${endTime}&searchType=${searchType}&scoreRole=${scoreRole}&surveyCode=${surveyCode}&staffPerformanceId=${staffPerformanceId}&userId=${userId}&orgAttr=${orgAttr}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->
<script type="text/html" id="view-or-edit">
    <div class="layui-form">
        <div class="layui-form-item layui-form-text" style="margin-top: 15px">
            <div class="layui-input-block">
                <textarea name="reason-textarea" placeholder="请输入内容" class="layui-textarea" style="height: 150px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary close-reason">取消</button>
                <button class="layui-btn layui-btn-normal submit-reason">保存并关闭</button>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="view-or-edit2">
    <div class="layui-form">
        <div class="layui-form-item layui-form-text" style="margin-top: 15px">
            <div class="layui-input-block">
                <textarea name="case-reason-textarea" placeholder="请输入内容" class="layui-textarea" style="height: 150px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary close-case">取消</button>
                <button class="layui-btn layui-btn-normal submit-case">保存并关闭</button>
            </div>
        </div>
    </div>
</script>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--<script src="${ctx}/js/jquery-1.8.2.min.js"></script>--%>
<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    var layer = ''
    layui.use(['layer', 'jquery'],function () {
        layer = layui.layer
        var new_$ = jQuery = layui.$

        $('.reason').click(function () {
            openReasonEdit()
        })
        $('body').on('click','.signBar',function () {
            var _this = $(this)
            var reasonIndex = ''
            var title = '意见'
            reasonIndex = layer.open({
                type: 1,
                title: title,
                area: ['500px', '300px'],
                content: $('#view-or-edit2').html()
            });
            $('textarea[name=case-reason-textarea]').val(_this.attr('data-reason'))
            $('.submit-case').click(function () {
                operateCase({
                    operateCode: 'performance',
                    btnCode:"investigatorCaseOpinion",
                    id: $('#id').val(),
                    surveyInvestigatorCaseId: _this.attr('data-id'),
                    staffOpinion:$('textarea[name=case-reason-textarea]').val()
                })
                layer.close(reasonIndex)
            })
            $('.close-case').click(function () {
                layer.close(reasonIndex)
            })
        })
        function operateCase(param) {
            new_$.ajax({
                url:'${ctx}/staff/operate',
                type:"post",
                data :param,
                success:function(res){
                    res = JSON.parse(res)
                    $('.lf-import button').removeAttr('disabled')
                    if (res.isSuccess){
                        layer.msg('操作成功', {
                            time: 1500,
                            icon: 1
                        },function () {
                           location.reload()
                        })
                    } else{
                        layer.msg(res.msg, {
                            time: 2000,
                            icon: 2
                        })
                    }
                }
            });
        }

        $('.reasonRead').click(function () {
            layer.confirm( ''+ $('#obj-organOpinion').val() +'', {
                title: '查看意见',
                btn: ['知道了'] //按钮
            },function(index){
                layer.close(index)
            });
        })
        function openReasonEdit() {
            var reasonIndex = ''
            var title = '意见'
            reasonIndex = layer.open({
                type: 1,
                title: title,
                area: ['500px', '300px'],
                content: $('#view-or-edit').html()
            });
            $('textarea[name=reason-textarea]').val($('#obj-organOpinion').val())
            $('.submit-reason').click(function () {
                var backReason = $('textarea[name=reason-textarea]').val()
                if (backReason){
                    operatePerson({
                        roleCode: 'surveyUser-step',
                        operateCode: 'performance',
                        btnCode:"back",
                        id: $('#id').val(),
                        staffPerformancePersonnelId: $('#obj-id').val(),
                        backReason:backReason
                    },1)
                }else {
                    operatePerson({
                        roleCode: 'surveyUser-step',
                        operateCode: 'performance',
                        btnCode:"removeBack",
                        id: $('#id').val(),
                        staffPerformancePersonnelId: $('#obj-id').val(),
                    },2)
                }
                layer.close(reasonIndex)
            })
            $('.close-reason').click(function () {
                layer.close(reasonIndex)
            })
        }

        function operatePerson(param) {
            new_$.ajax({
                url:'${ctx}/staff/operate',
                type:"post",
                data :param,
                success:function(res){
                    res = JSON.parse(res)
                    $('.lf-import button').removeAttr('disabled')
                    if (res.isSuccess){
                        layer.msg('操作成功', {
                            time: 2000,
                            icon: 1
                        },function () {
                            console.log(param)
                            $('#obj-organOpinion').val(param.backReason)
                            if (param.backReason){
                                $('.reason').text('查看意见')
                            } else{
                                $('.reason').text('对积分有异议？填写意见')
                            }
                        })
                    } else{
                        layer.msg(res.msg, {
                            time: 2000,
                            icon: 2
                        })
                    }
                }
            });
        }
    })


    function showReLoossesRemark(type,obj){
        var dialog = $(".dalogsTip");
        if(dialog.is(":hidden")){
            dialog.show();
        }else{
            dialog.hide();
        }
        var position = $(obj).position();
        var left = position.left;
        if (type ==2 ){
            left = left - 360
        }else{
            left = left + 120
        }
        $(".dalogsTip").offset({
            left: left,
            top: $(obj).parents('.info-1129').position().top + position.top
        });
        var content = $(obj).attr("data-value");
        if(content != ''){
            $(".dalogsTip .d_content").html(content);
        }else{
            $(".dalogsTip .d_content").html("暂无数据");
        }
    }
    //
    // $('.info-1129').on('mouseover','.viewdesc',function () {
    //     var _this = this
    //     var content = $(this).attr("data-value") || "暂无数据";
    //     layer.tips(content, _this, {
    //         tips: [2, '#666'],
    //         time: 2000
    //     });
    // })

    var info = function(id,assignCaseId,investigatorCaseId,surveyUserName,staffOpinion){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var orgAttr = $("#orgAttr").val();
        var surveyUser =$("#surveyUser").val();
        var organManager =$("#organManager").val();
        var isManager =$("#isManager").val();
        var roleCode = $("#roleCode").val();
        var menuCode ='';
        var url = '';
        if(surveyUser == "true"){
            menuCode = 'dcy-list';
            url = "${ctx}/survey/case/sic/info?id=" + investigatorCaseId + "&menuCode="+menuCode+"&reStateStr="+'';
        }
        if(organManager == "true"){
            menuCode= 'org-review-list';
            url = "${ctx}/survey/case/info?id=" + id + "&menuCode=" +menuCode+"&fromName=pointsDetails&assignOrgId="+assignCaseId+"&showFrom=staff&surveyUserName="+surveyUserName+"&staffOpinion="+staffOpinion+"&investigatorCaseIdS="+investigatorCaseId+"&roleCode="+roleCode+"&btnCode="+'${btnCode}'
        }
        if(isManager == "true"){
            menuCode= 'all-list';
            if(orgAttr == 1){
                //平台复审（保司）
                menuCode= 'survey-list';
            }else if(orgAttr == 2){
                //平台复审（互助）
                menuCode= 'help-review';
            }
            url = "${ctx}/survey/case/info?id=" + id + "&menuCode=" +menuCode+"&fromName=pointsDetails&assignOrgId="+assignCaseId+"&showFrom=staff&surveyUserName="+surveyUserName+"&staffOpinion="+staffOpinion+"&investigatorCaseIdS="+investigatorCaseId+"&roleCode="+roleCode+"&btnCode="+'${btnCode}'
        }
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:url,
            load:true
        });
    }
</script>
</body>
</html>
