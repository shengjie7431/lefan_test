<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>

<div class="main">
    <div class="title">
        <button class="butList defuelt" onclick="view(1,'${dto.id}','${dto.caseNo}','view')">查看报价</button>
        <button class="butList defuelt" onclick="view(2,'${dto.id}','${dto.caseNo}','view')">查看单证</button>
        <button class="butList defuelt" onclick="haldleRepayCaseCenterInfo('${dto.id}')">操作还款</button>
    </div>
    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>姓名</td>
                    <td>${dto.caseName}</td>
                    <td>案件编号</td>
                    <td>${dto.caseNo}</td>
                    <td>事故发生地</td>
                    <td>${dto.address}</td>
                </tr>
                <tr>
                    <td>案件类型</td>
                    <td>${dto.caseTypeName}</td>
                    <td>案件阶段</td>
                    <td>${dto.gradationStateName}</td>
                    <td>案件状态</td>
                    <td>${dto.listStateName}</td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td>${dto.caseTel}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>案件状态变更时间</td>
                    <td><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td >驳回原因</td>
                    <td colspan="5">${dto.operReason}</td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var haldleRepayCaseCenterInfo = function(id){
        openDialog({
            frame:true,
            title:"操作还款",
            height:500,
            width:800,
            url:"${ctx}/case/haldleRepayCaseCenterInfo?id="+id
        });
    }

    /**
     *
     * @param id        案件中心ID
     * @param btnCode    按钮CODE,与list_state一一对应
     * @param ajax       是否ajax请求,  true 弹出提示框   false 弹出界面
     */
    function operate(id,btnCode,ajax){
        var height = 400,width = 800;
        if(!validate(btnCode)){
            return;
        }
        if(ajax){
            var url = "${ctx}/case/center/operate",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == '1100'){
                title = '紧急代扣';
                url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode
            }else if(btnCode == '1103' || btnCode == '1105' || btnCode == '11061' || btnCode == '1110' || btnCode == '1113' || btnCode == '1116' || btnCode == '1120'){
                title = '原因';
                url = '${ctx}/case/center/back?id='+id+"&btnCode="+btnCode;
            }else if(btnCode == '1107'){
                title = '投保';
                url = '${ctx}/case/center/insured?id='+id+"&btnCode="+btnCode;
            }else if(btnCode == '1108'){
                title = '放款确认';
                url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode;
            }else if(btnCode == '1117'){
                title = '发起代扣';
                url = '${ctx}/case/center/start?id='+id+"&passType=passSuning&btnCode="+btnCode;
            }else if(btnCode == '11082'){
                title = '编辑银行卡';
                url = "${ctx}/case/center/editBankCardInfo?caseId="+id+"&btnCode="+btnCode;
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }
    function validate(btnCode){
        if(btnCode == 1100 || btnCode == 1108){
            //验证银行卡信息是否已存在
        }else if(btnCode == 1101){
            //验证公估报告 评估报告是否已填写
            if('${dto.assessmentReportId}' == ''){
                alert('公估报告未填写');return false;
            }
            if('${dto.riskReportId}' == ''){
                alert('评估报告未填写');return false;
            }
        }else if(btnCode == 1111){
            //验证索赔预案是否已填写
            if('${dto.mediationReportId}' == ''){
                alert('索赔预案未填写');return false;
            }
        }else if(btnCode == 1114){
            //验证结案报告是否已填写
            if('${dto.closeReportId}' == ''){
                alert('结案报告未填写');return false;
            }
        }else if(btnCode == 11112){
            //诉讼预案是否已填写
        }
        return true;
    }
    function report(type,caseId,caseNo,op){
        var title = null,url = null;
        if(type == 1){
            title = "公估报告";
            url = "${ctx}/case/report/editAssessmentReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 2){
            title = "评估报告";
            url = "${ctx}/case/report/editRiskControl?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 3){
            title = "索赔预案";
            url = "${ctx}/case/caseMediation?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 4){
            title = "结案报告";
            url = "${ctx}/case/report/editCloseReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 5){
            title = "诉讼预案";
            url = "${ctx}/case/caseMediation?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }
        openDialog({
            frame:true,
            title:title,
            height:900,
            width:1000,
            url:url
        });
    }
    function view(type,caseId,caseNo,op){
        if(type == 1){
            viewEstimateInquiry(caseId)
        }else if(type ==2){
            selectFileMid(caseNo);
        }else if(type ==3){
            selectCaseDetails('${dto.type}','${dto.caseId}',1,caseNo);
        }
    }

    function viewEstimateInquiry(caseId){
        openDialog({
            frame:true,
            title:"查看报价",
            height:600,
            width:1000,
            url:"${ctx}/case/center/viewEstimateInquiry?id="+caseId
        });
    }
    function selectFileMid(caseNo){
        openDialog({
            frame:true,
            title:"查看单证",
            height:600,
            width:1000,
            url:"${ctx}/case/selectFileMid?caseNo="+caseNo
        });
    }
    function selectCaseDetails(type,caseId,caseType,caseNo){
        openDialog({
            frame:true,
            title:"查看进度",
            height:500,
            width:1000,
            url:"${ctx}/case/selectCaseDetails?type="+type+"&caseId="+caseId+"&caseType="+caseType+"&caseNo="+caseNo
        });
    }

</script>
</body>
</html>
