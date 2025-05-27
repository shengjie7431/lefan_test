<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>理赔测算报告</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <%--<h3>理赔测算报告</h3>--%>
    </div><!--main-top-->
    <c:if test="${paymentEstimateReportDtos.size() > 0}">
        <div class="panel panel-info">

            <div class="panel-heading">
                <div class="pin">
                    <%--form class="form-inline" role="form" action="${ctx}/paymentEstimate/paymentEstimateReportList" method="post">
                        <div class="form-group">
                            <input type="hidden" name="id" value="${id}">
                        </div>
                    </form>--%>
                        <input type="hidden" name="paymentId" id="paymentId" value="${id}">
                        <input type="hidden" name="caseNo" id="caseNo" value="${caseNo}">
                        <input type="hidden" name="caseId" id="caseId" value="${caseId}">
                </div>
            </div>

            <table class="table table-hover">
                <thead>
                <div class="form-group">
                    <span style="font-size: 20px">事故责任: </span>
               <select  id = "accidentLiability" name="accidentLiability"  class="form-control" style="width: 400px">
                    <option value="0" >请选择</option>
                    <option <c:if test="${apply.myAccidentLiability == 1}">selected="selected"</c:if> value="1" >全部责任</option>
                    <option <c:if test="${apply.myAccidentLiability == 2}">selected="selected"</c:if>value="2">主要责任</option>
                    <option <c:if test="${apply.myAccidentLiability == 3}">selected="selected"</c:if>value="3">同等责任</option>
                    <option <c:if test="${apply.myAccidentLiability == 4}">selected="selected"</c:if>value="4">次要责任</option>
                    <option <c:if test="${apply.myAccidentLiability == 5}">selected="selected"</c:if>value="5">无责任</option>
                    <option <c:if test="${apply.myAccidentLiability == 6}">selected="selected"</c:if>value="6">责任无法认定</option>
                </select>
                </div>
                <c:if test="${apply.caseType == 1}">
                    <tr>
                        <div class="form-group">
                            <span style="font-size: 20px">贷款资金方: </span>
                            <select required="required"  id = "loanFund" name="loanFund"  class="form-control" style="width: 400px">
                                <option value="" >请选择</option>
                                <option <c:if test="${apply.loanFund == 1}">selected="selected"</c:if>value="1">苏宁</option>
                                <option <c:if test="${apply.loanFund == 2}">selected="selected"</c:if>value="2">乐凡</option>
                            </select>
                        </div>
                    </tr>
                </c:if>
                <tr>
                    <div class="form-group">
                        <span style="font-size: 20px">伤者现状描述: </span>
                        <textarea readonly class="form-control" name="injuredDesc">${apply.injuredDesc}</textarea>
                    </div>
                </tr>
                <tr>
                    <th width="100">赔偿项目名称</th>
                    <th width="150">实际损失</th>
                    <th width="150">实际损失审核金额</th>
                    <th width="150">交强险可赔偿金额</th>
                    <th width="150">商业险可赔偿金额</th>
                    <th width="150">肇事方可赔偿金额</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${paymentEstimateReportDtos}" var="item" varStatus="s">
                    <tr>
                        <td>${item.paymentProject}</td>
                        <td>${item.medicalFee}</td>
                        <c:if test="${s.isLast()}">
                            <c:if test="${item.checkMedicalFee!=null}">
                            <td><input type="number" id="${item.id}" name='checkMedicalFee'value="${item.checkMedicalFee}" class="form-control"  step="0.01" onblur="isNumber(this);" disabled></td>
                        </c:if>
                        <c:if test="${item.checkMedicalFee==null}">
                            <td><input type="number" id="${item.id}" name='checkMedicalFee'value="${item.medicalFee}" class="form-control"  step="0.01" onblur="isNumber(this);" disabled></td>
                        </c:if>

                        </c:if>
                        <c:if test="${!s.isLast()}">
                            <c:if test="${item.checkMedicalFee!=null}">
                                <td><input type="number" id="${item.id}" name='checkMedicalFee'value="${item.checkMedicalFee}" class="form-control"  step="0.01" onblur="isNumber(this);"></td>
                            </c:if>
                            <c:if test="${item.checkMedicalFee==null}">
                                <td><input type="number" id="${item.id}" name='checkMedicalFee'value="${item.medicalFee}" class="form-control"  step="0.01" onblur="isNumber(this);"></td>
                            </c:if>
                        </c:if>
                        <td>${item.compulsoryInsuranceFee}</td>
                        <td>${item.commercialInsuranceFee}</td>
                        <td>${item.causeTroubleFee}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="modal-footer">
                <button type="button"  onclick="groBack(1)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"> 查看单证</button>
                <c:if test="${negotiateState == null || negotiateState == 0}">
                    <c:if test="${mType == null || mType != 1}">
                        <button type="button"  onclick="groBack(2)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"> 保存</button>
                        <button type="button"  onclick="groBack(3)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 审核通过</button>
                        <button type="button"  onclick="groBack(4)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off">退回</button>
                    </c:if>
                </c:if>
            </div>
        </div><!--panel-info-->
    </c:if>
    <c:if test="${paymentEstimateReportDtos.size()==0}">
        <span style="color: red; padding-left: 26px;">暂无数据</span>
    </c:if>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var scenariosDelete = function(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete?id="+id,{},function(){location.reload();},"删除成功","确认此条用户案例删除吗？",null);
    }*/
    function scenariosDelete(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete",{"id":id},reload,"删除成功","确认删除该条用户案例吗？");
    }

    function isNumber(obj) {
        obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
        if( obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value= parseFloat(obj.value);
        }

      /*  var checkMedicalFee = $("input[name='checkMedicalFee']:eq(0)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(1)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(2)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(3)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(4)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(5)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(6)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(7)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(8)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(9)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(10)").val();
        var checkMedicalFee = $("input[name='checkMedicalFee']:eq(11)").val();*/
        var number = 0;
        $(function(){
            $("input[name='checkMedicalFee']").each(function(index,item){
                    if($(this).val() != null && $(this).val() != ''){
                        if(index != 10){
                            number += parseFloat($(this).val());
                        }
                    }
              }
            );
        });
        $("input[name='checkMedicalFee']:eq(10)").val(number);
    }
    function groBack(type){
        if(type == 1){
            selectFileMid('${caseNo}');
        }else if(type == 2){
            var accidentLiability = $("#accidentLiability").val();
            if(accidentLiability == 0 || accidentLiability =="0"){
                alert("请选择事故责任！");
                return false;
            }
            var loanFund = $("#loanFund").val();
            var paymentId=$("#paymentId").val();
            var text = '[';
            $(function(){
                $("input[name='checkMedicalFee']").each(function(index,item){
                            var value = $(this).val();
                            var id = $(this).attr('id');
                            if(index == 10){
                                text+=  '{ "id":"'+id+'" , "value":"'+value+'" }]';
                            }else {
                                text+=  '{ "id":"'+id+'" , "value":"'+value+'" },';
                            }
                        }
                );
            });
            ajaxSubmit("${ctx}/case/risk/paymentEstimateReportEdit",{"list":text,"accidentLiability":accidentLiability,"loanFund" : loanFund,"paymentId":paymentId},function(v,e,p){
                alert(e.data.msg);
//                reloadParent();
                reload();
            })
        }else if(type == 3){
            editRiskState('${caseId}',1);
        }else if(type == 4){
            updateReson('${caseId}',2);
        }
    }

   function selectFileMid(caseNo){
       openDialog({
           frame:true,
           title:"查看单证",
           height:500,
           width:900,
           url:"${ctx}/case/selectFileMid?caseNo="+caseNo
       });
   }

   function editRiskState(id,state){
       if(confirm('确定审核通过？')){
           ajaxSubmit("${ctx}/case/risk/negotiateState",{"id":id,"state":state},function(v,e,p){
               alert(e.data.msg);
               reloadParent();
           })
       }

   }
   var updateReson = function(id,state){
       openDialog({
           frame:true,
           title:"驳回风控案件",
           height:400,
           width:600,
           url:"${ctx}/case/risk/negotiateReason?id="+id+"&state="+state,
           load : true
       });
   }
</script>
</body>
</html>
