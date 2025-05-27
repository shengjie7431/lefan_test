<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

    <style>
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
        .dalog {
            display: none;
            margin: 0 auto;
            /*margin-top: 20%;*/
            width: 508px;
            height: 418px;
            line-height: 20px;
            text-align: center;
            border: 1px solid rgba(187, 187, 187, 1);
            font-size: 14px;
            z-index: 1001;
            background-color: #fff;
        }

        .dalog .d-title {
            width: 100%;
            height: 20px;
            line-height: 20px;
            padding: 30px 0;
            color: rgba(16, 16, 16, 1);
            font-size: 16px;
            text-align: center;
        }

        .dalog .d-cell {
            width: 90%;
            margin: 0 auto;
            padding: 10px;
            display: flex;
        }

        .dalog .d-cell .d-cell__label {
            display: inline-block;
            width: 150px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input {
            position: relative;
            display: inline-block;
            padding-left: 10px;
            width: 230px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            display: flex;
        }
        .dalog .d-cell .d-cell__input--disabled input{
            background-color: rgba(179,179,179,0.25);
        }

        .dalog .d-cell .d-cell__input input {
            display: inline-block;
            width: 190px;
            /*padding: 0 10px;*/
            /*padding-right: 40px;*/
            height: 28px;
            line-height: 28px;
            border: 1px solid #bbb;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input span {
            position: absolute;
            right: 10px;
            display: inline-block;
            width: 16px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: center;
        }

        .dalog .d-cell .d-cell__textarea {
            display: inline-block;
            padding-left: 10px;
            width: 190px;
            height: 104px;
            font-size: 14px;
        }

        .dalog .d-cell .d-cell__textarea textarea {
            padding: 10px;
            width: 190px;
            height: 82px;
            line-height: 24px;
            border: 1px solid #bbb;
            font-size: 14px;
        }

        .dalog .d-btns {
            width: 320px;
            margin: 0 auto;
            padding-top: 30px;
            display: flex;
            justify-content: space-between;
        }

        .dalog .d-btns .d-btn {
            width: 130px;
            height: 34px;
            line-height: 34px;
            border: 1px solid #bbb;
            background-color: #fff;
            cursor: pointer;
        }

        .dalog .d-btns .d-btn:hover {
            box-shadow: 0px 0px 10px #bbb;
        }

        .dalog .d-btns .d-btn__active {
            background-color: #3ba9ff;
            color: #fff;
        }

        .dalog__1 {
            /* display: block; */
        }


        .btn-types {
            display: flex;
            justify-content: flex-end;
            background-color: #fff;
            margin-right: 10px;
        }

        .btn-types .btn-type {
            width: 140px;
            height: 32px;
            line-height: 32px;
            text-align: center;
            border: 1px solid #bbb;
            cursor: pointer;
        }

        .btn-types .btn-type.active {
            border: 1px solid #3BA9FF;
            background-color: #3BA9FF;
            color: #fff;
        }
        .paramTime1,.paramTime2{
            display: none;
        }
        .btn-area{
            width: 100%;
            padding: 0 2%;
            margin: 0 auto;
            display: flex;
            position: fixed;
            bottom: 0;
            left: 0;
            z-index: 99;
            background-color: rgba(255,255,255,0.6);
        }
        .btn-area-left{
            width: 60%;
        }
        .btn-area-right{
            width: 40%;
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }
        .btn-cell{
            padding: 6px 0;
            width: 100%;
            display: flex;
            align-items: center;
        }
        .btn-label{
            padding-right: 6px;
        }
        .paramTime{
            width: 170px;
            margin-left: 10px;
        }

        .lf-btns{
            width: 300px;
            display: flex;
            justify-content: flex-end;
        }
        .lf-btns .lf-btn{
            width: 140px;
            height: 32px;
            line-height: 32px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            background-color: #fff;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
        .poi-no{
            pointer-events: none;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info" style="margin-bottom: 90px">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/list?menuCode=${menuCode}" method="post">
                    <%--多选--%>
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="btnCode" id="btnCode" value="${btnCode}">
                    <div class="form-group">
                        保险公司:
                        <div>
                            <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        是否阳性:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="isSun"  class="form-control">
                            <option value=""  <c:if test="${isSun == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isSun == '0'}">selected="selected" </c:if> >否</option>
                            <option value="1" <c:if test="${isSun == '1'}">selected="selected" </c:if> >是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        阳性奖励:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="haveSunMoney"  class="form-control">
                            <option value=""  <c:if test="${haveSunMoney == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${haveSunMoney == '0'}">selected="selected"</c:if> >无</option>
                            <option value="1" <c:if test="${haveSunMoney == '1'}">selected="selected"</c:if> >有</option>
                        </select>
                    </div>
                    <div class="form-group">
                        平台复审通过时间：
                        <input name="reviewStartTime" type="text" value="${reviewStartTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="reviewEndTime" type="text" value="${reviewEndTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover" >
            <thead>
            <tr>
                <th class="th-checkbox">
                    <input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">
                </th>
                <th width="100">调查编号</th>
                <th width="200">保险公司</th>
                <th width="100">被调查人</th>
                <th width="100">业务类型</th>
                <th width="100">委托时间</th>
                <th width="100">平台复审通过时间</th>
                <th width="100">案件截止时间</th>
                <th width="100">案件时效</th>
                <th width="100">是否阳性</th>
                <th width="100">阳性奖励</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>

                    <td>
                        <c:if test="${item.isPayEntrustFee == 0}">
                            <input type="checkbox" name="list-checkbox" class="list-checkbox" value="${item.id}">
                            <input type="hidden" value="${item.entrustOrgId}" name="entrustOrgIdList" />
                            <input type="hidden" value="${item.entrustOrgName}" name="entrustOrgNameList" />
                        </c:if>
                    </td>

                    <td><a href="javascript:void(0);" onclick="info(${item.id})">${item.surveyRiskCase.surveyNo}</a></td>
                    <td>${item.entrustOrgName}</td>
                    <td>${item.surveyRiskCase.surveyPerson}</td>
                    <td>
                        <c:if test="${item.subServiceId ==null}">${item.servicesName}</c:if>
                        <c:if test="${item.subServiceId !=null}">
                            <c:if test="${item.subServiceId ==2}">核对调阅类</c:if>
                            <c:if test="${item.subServiceId ==3}">一般检索类</c:if>
                            <c:if test="${item.subServiceId ==4}">特殊检索类</c:if>
                            <c:if test="${item.subServiceId ==5}">疑难侦察类</c:if>
                            <c:if test="${item.subServiceId ==6}">攻坚侦察类</c:if>
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.entrustReportStartDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.agingDay}天</td>
                    <td>
                        <c:if test="${item.isSun == 0}">否</c:if>
                        <c:if test="${item.isSun == 1}">是</c:if>
                    </td>
                    <td>${item.sunMoney}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="btn-area">
              <div class="btn-area-left">
                  <div class="btn-cell">
                      <div class="btn-label">保司终审通过时间:</div>
                      <div class="btn-types" data-type="1">
                          <div class="btn-type active" data-id="0">与平台复审时间一致</div>
                          <div class="btn-type" data-id="1">自定义时间</div>
                          <input type="text" class="layui-input paramTime paramTime1" readonly id="oprTime" placeholder="请选择日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                      </div>
                  </div>
                  <%--<div class="btn-cell">
                      <div class="btn-label">保司终审通过时间:</div>
                      <div class="btn-types" data-type="2">
                          <div class="btn-type active" data-id="0">与平台复审时间一致</div>
                          <div class="btn-type" data-id="1">自定义时间</div>
                          <input type="text" class="layui-input paramTime paramTime2" readonly id="oprTime" placeholder="请选择日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                      </div>
                  </div>--%>
              </div>
            <div class="btn-area-right">
                <div class="lf-btns">
                    <div class="lf-btn active" data-type="2">提交</div>
                </div>
            </div>

        </div>
    </div><!--panel-info-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
    $('.btn-types').on('click','.btn-type', function () {
        var _this = $(this)
        var _id = _this.attr('data-id')
        var _pType = _this.parent().attr('data-type')
        if (!_this.hasClass('active')){
            _this.addClass('active').siblings().removeClass('active')
        }
        if (_id == '1'){
            _this.siblings('.paramTime').show()
        } else if (_id == '0'){
            _this.siblings('.paramTime').hide()
            $("#oprTime").val('')
        }
    })
    layui.use('layer',function () {
        layer = layui.layer
    })

    $('.lf-btn').click(function () {
        var _id = $('#id').val(),_state = $('#state').val()
        var _this = $(this)
        if (_this.hasClass('active')){
            var ids = []
            $('.table-hover .list-checkbox').map(function (cur) {
                var _this = $(this)
                if (_this.is(':checked')){
                    ids.push(_this.val())
                }
            })
            if (!ids.length){
                layer.msg('至少选中一条数据', {
                    time: 2000,
                    icon: 5
                })
                return;
            }

            // _this.addClass('poi-no')
            // setTimeout(function () {
            //     _this.removeClass('poi-no')
            // },4000)

            var params = {
                ids: ids.join(','),
                oprTime : $("#oprTime").val(),
                btnCode : $("#btnCode").val()
            }
            var _flag = true
            layer.confirm('确定提交？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                if (_flag){
                    _flag = false
                    submitInfo(params);
                }
                layer.closeAll()
            }, function(){
                _flag = true
            });

        }else{

        }
    })

    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var name = $(this).attr("data-select-name");
            if(name){
                $("#" + name).val($(this).val());
            }
        })
    });

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
    $(document).ready(function () {
        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }
        //默认选中 select
        $(".select-checkbox").each(function () {
            var value = $(this).attr("data-select-values");
            if (value) {
                $(this).val(value.replace(/\s*/g, '').split(','));
            } else {
                $(this).val("");
            }
        })
        $(".select-checkbox").UCFormSelect();
        $(".UCSelect[name=entrustOrgIdsChk]").width(320);

        $('.btn-1').off("click");
        $('.btn-1').click(function(e){
            $(".UCSelect").find('.SelectVal').removeClass('over');
            $(".UCSelect").find("select").UCFormSelect('close');
            $("#batchOperateBtn").click();
        });

        $(document).off('mousedown');
        $(document).bind('mousedown', function (e) {
            var Event = e.target;
            $('.UCSelect .SelectBox').each(function () {
                var Select = $(this).parents(".UCSelect").find("select").get(0);
                var Events = $(Event).parents(".UCSelect").find("select").get(0);
                if (!(Event && Select && Select == Events)) {
                    if( $(this).parents(".UCSelect").find('.SelectVal').hasClass("over")){
                        $("#batchOperateBtn").click();
                    }
                    $(this).parents(".UCSelect").find('.SelectVal').removeClass('over');
                    $(this).parents(".UCSelect").find("select").UCFormSelect('close');
                }
            });
        });
    })

    $(document).ready(function(){
//        $('.singleSelect').select2();
        $("#check-btn").on('change',function(){
            $("input[name='list-checkbox']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });

    var info = function(id){
        var width = $(document.body).outerWidth()*0.98;
        var height = $(document).outerHeight()*0.98;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}",
            load:true
        });
    }

    function submitInfo(params){
        //idArr长度过长，传值（前端报错）
        $.ajax({
            url:'${ctx}/survey/case//operate',
            type:"POST",
            data : params,
            success:function(res,param){
                var res= JSON.parse(res)
                if (res.isSuccess){
                    $('.lf-btn').addClass('poi-no')
                    layer.msg('成功', {
                        time: 1500,
                        icon: 1
                    },function(){
                        reload()
                    })

                }else{
                    layer.msg('失败', {
                        time: 1500,
                        icon: 2
                    })
                }
            }
        });
    }

</script>
</body>
</html>
