<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <style>
        .td-item{
            display: flex;
            align-items: center;
            padding: 4px 0;
        }
        .first{
            color: #1E9FFF;
            display: inline-block;
            width: 50%;
            cursor: pointer;
        }
        .second{
            color: #000;
            display: inline-block;
            width: 50%;
        }
        .second-cell{
            width: 100%;
            height: 20px;
            text-align: left;
        }
        table tr td{
            vertical-align: middle!important;
        }
        .viewChannelDesc{
            cursor: pointer;
        }
        .panel-info{
            margin-bottom: 60px;
        }
        .pagePosi{
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            padding:6px 0;
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
                <form class="form-inline" role="form" action="${ctx}/survey/channel/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="states" id="states" />
                    <div class="form-group">
                        审核状态：<select name="stateChk"  class="select form-control select-checkbox" data-select-name="states"  data-select-values="${states}" multiple >
                            <option value="1">签报审核驳回</option>
                            <option value="2">签报待审核</option>
                            <option value="4">待发起付费审核</option>
                            <option value="5">付费待审核</option>
                            <option value="6">付费审核驳回</option>
                            <option value="3">付费审核通过</option>
                        </select>
                        机构负责人：<input style="width: 200px" name="orgUserName" type="text" value="${orgUserName}"  class="form-control">
                    </div>
                    <div class="form-group">
                        付款状态：
                        <select name="isProPay" class="form-control">
                            <option value="-1">全部</option>
                            <option value="0" <c:if test="${isProPay == 0}">selected="selected"</c:if> >未申请付款</option>
                            <option value="1" <c:if test="${isProPay == 1}">selected="selected"</c:if>>已申请付款</option>
                            <option value="2" <c:if test="${isProPay == 2}">selected="selected"</c:if>>已付款</option>
                        </select>
                    </div>
                    <div class="form-group">
                        申请时间：
                        <input name="startTime" type="text" value="${startTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endTime" type="text" value="${endTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp;
                    <c:if test="${orgRole}">
<%--                        <button onclick="app()" type="button" class="btn btn-default">新增</button>--%>
                    </c:if>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>关联案件编号</th>
                    <th>关联方向</th>
                    <th>渠道金额</th>
                    <th>审核状态</th>
                    <th>审核通过时间</th>
                    <th>付款状态</th>
                    <th>备注</th>
                    <th>机构负责人</th>
                    <th>申请时间</th>
                    <th>收款人姓名</th>
                    <th>开户行</th>
                    <th>支行</th>
                    <th>银行账号</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td colspan="2" >
                        <c:forEach items="${item.surveyInfos}" var="info">
                           <div class="td-item">
                               <div class="first" data-id="${info.surveyInfoId}" data-role="${oprRole ? 1 : 2}">${info.surveyNo}</div>
                               <div class="second">
                                   <c:forEach items="${info.directions}" var="direction">
                                       <div class="second-cell"> ${direction.surveyDirectionName}</div>
                                   </c:forEach>
                               </div>
                           </div>
                        </c:forEach>
                    </td>
                    <td>${item.chnannelMoney}</td>
                    <td>
                        <c:if test="${item.state == 1}">签报审核驳回</c:if>
                        <c:if test="${item.state == 2}">签报待审核</c:if>
                        <c:if test="${item.state == 4}">待发起付费审核</c:if>
                        <c:if test="${item.state == 5}">付费待审核</c:if>
                        <c:if test="${item.state == 6}">付费审核驳回</c:if>
                        <c:if test="${item.state == 3}">付费审核通过</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.reviewerTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <c:if test="${item.isProPay == 0}">未申请付款</c:if>
                        <c:if test="${item.isProPay == 1}">已申请付款</c:if>
                        <c:if test="${item.isProPay == 2}">已付款</c:if>
                    </td>
                    <td>
                        <a class="viewChannelDesc" data-id="${item.id}">查看备注</a>
                        <input type="hidden" value="${item.channelDesc}" id="channelDesc${item.id}">
                    </td>
                    <td>${item.surveyUserName}</td>
                    <td><fmt:formatDate value="${item.operationTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.payeeUserName}</td>
                    <td>${item.bankDeposit}</td>
                    <td>${item.bankBranch}</td>
                    <td>${item.bankNo}</td>
                    <td>
                        <c:if test="${item.state == 1}">
                            <c:if test="${orgRole}">
                                <a href="javascript:void(0);" onclick="app(${item.id})">编辑</a>
                                <a href="javascript:void(0);">
                                    <c:if test="${item.rejectDesc != null}">
                                        <a class="viewReason" style="cursor: pointer" data-id="${item.id}">查看驳回原因</a>
                                    </c:if>
                                    <input type="hidden" value="${item.rejectDesc}" id="rejectDesc${item.id}">
                                </a>
                                <a href="javascript:void(0);" onclick="operate(${item.id},'del',true,this)">删除</a>
                            </c:if>
                        </c:if>
                        <c:if test="${item.state == 2}">
                            <c:if test="${orgRole}">
                                <a href="javascript:void(0);" onclick="operate(${item.id},'del',true,this)">删除</a>
                            </c:if>
                            <c:if test="${oprRole}">
                                <a href="javascript:void(0);" onclick="operate(${item.id},'two-yes',true,this)">签报审核通过</a>
                                <a href="javascript:void(0);" onclick="operate(${item.id},'two-no',false,this)">驳回</a>
                            </c:if>
                        </c:if>
                        <c:if test="${item.state == 4 || item.state == 6}">
                            <c:if test="${orgRole}">
                                <a href="javascript:void(0);" onclick="operate(${item.id},'app',true,this)">关联方向并提交审核</a>
                                <c:if test="${item.state == 6}">
                                    <a href="javascript:void(0);">
                                        <a class="viewReason" style="cursor: pointer" data-id="${item.id}">查看驳回原因</a>
                                        <input type="hidden" value="${item.rejectDesc}" id="rejectDesc${item.id}">
                                    </a>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${item.state == 5}">
                            <c:if test="${oprRole}">
                                <a href="javascript:void(0);" onclick="operate(${item.id},'review',true,this)">付费审核通过</a>
                                <a href="javascript:void(0);" onclick="operate(${item.id},'review',false,this)">驳回</a>
                            </c:if>
                        </c:if>
<%--                        <a style="color: red" href="javascript:void(0);" onclick="operate(${item.id},'generate',true,this)">测试生成付款记录</a>--%>
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
            <jsp:param name="requestUrl" value="${ctx}/survey/channel/list?states=${states}&orgUserName=${orgUserName}&startTime=${startTime}&endTime=${endTIme}&isProPay=${isProPay}" />
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
    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var selName = $(this).attr("name");
            var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
            var name = $(this).attr("data-select-name");
            if(name){
                $("#" + name).val($(this).val());
            }
        })
    });

    $(".select-checkbox").each(function () {
        var value = $(this).attr("data-select-values");
        if (value) {
            $(this).val(value.replace(/\s*/g, '').split(','));
        } else {
            $(this).val("");
        }
    })
    $(".select-checkbox").UCFormSelect();
    $(".UCSelect[name=stateChk]").width(320);


    $('.btn-1').off("click");
    $('.btn-1').click(function(e){
        $(".UCSelect").find('.SelectVal').removeClass('over');
        $(".UCSelect").find("select").UCFormSelect('close');
        $("#batchOperateBtn").click();
    });

    $('.first').click(function (e) {
        var _this = $(this);
        var dataRole = _this.attr("data-role");
        var url = "${ctx}/survey/case/info?menuCode=all-list&id="+_this.attr("data-id")
        if (dataRole == 2){//没有审核权限。则近机构的详情页面
            url = "${ctx}/survey/case/info?menuCode=org-review-list&id="+_this.attr("data-id");
        }
        parent.addTab("案件信息",url,true);
    })








    layui.use('layer',function () {
        layer = layui.layer
    });

    $('.viewReason').click(function () {
        var _this = $(this)
        var id = _this.attr("data-id");
        layer.alert($('#rejectDesc' + id).val(),{ title: '驳回原因'})
    })
    $('.viewChannelDesc').click(function () {
        var _this = $(this)
        var id = _this.attr("data-id");
        var desctext = $('#channelDesc' + id).val() || '无'
        layer.alert(desctext,{ title: '备注'})
    })
    function app(id) {
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"新增",
            height:height,
            width:800,
            url:"${ctx}/survey/channel/info" + (id ? "?id=" + id : ""),
            load:true
        });
    }
    function operate(id,btnCode,ajax,_this){
        if (btnCode == 'app'){
            app(id)
            return;
        }
        if(ajax){
            var url = "${ctx}/survey/channel/operate",data = {"id":id,"btnCode":btnCode,"reviewType" : "yes"};
            var title = btnCode == 'del' ? "确定删除" : "确认审核通过";
            layer.confirm(title, {
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
            layer.prompt({title:"请输入拒绝原因（必填项）",formType:2},function (text,index) {
                var url = "${ctx}/survey/channel/operate",param = {"id":id,"btnCode":btnCode,"rejectDesc" : text,"reviewType" : "veto"};
                layer.close(index)
                $.ajax({
                    url:url,
                    type:"post",
                    data :param,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            },function () {
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
            })
        }
    }
</script>
</body>
</html>
