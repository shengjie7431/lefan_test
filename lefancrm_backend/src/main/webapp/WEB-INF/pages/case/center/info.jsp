<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 250px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

</style>
<body>

        <div class="main">
            <div class="title">
                <button class="butList defuelt" onclick="view(1,'${dto.id}','${dto.caseNo}','view')">查看报价</button>
                <button class="butList defuelt" onclick="view(2,'${dto.id}','${dto.caseNo}','view')">查看单证</button>
                <button class="butList defuelt" onclick="view(3,'${dto.id}','${dto.caseNo}','view')">查看进度</button>
                <button class="butList defuelt" onclick="view(4,'${dto.id}','${dto.caseNo}','view')">测算报告</button>
                <button class="butList defuelt" onclick="view(5,'${dto.id}','${dto.caseNo}','view')">伤残报告</button>
                <c:if test="${menuType == 1}">
                    <c:if test="${dto.gradationState == 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <c:if test="${dto.gradationState == 3}">
                        <c:if test="${dto.type != 2}">
                            <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                            <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                        </c:if>
                        <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                        <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                    </c:if>
                    <c:if test="${dto.gradationState == 6 || dto.gradationState == 4}">
                        <c:if test="${dto.type != 2}">
                            <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                            <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                        </c:if>
                        <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                        <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                        <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 11}">
                    <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                    <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                </c:if>
                <c:if test="${menuType == 111}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                    <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                </c:if>
                <c:if test="${menuType == 1111}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                    <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                    <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                </c:if>

                <c:if test="${menuType == 2 && (caseUserRole.claims || caseUserRole.claimsManager)}">
                    <button class="butList active" onclick="operate('${dto.id}','1100',false)">紧急代扣</button>
                    <button class="butList active" onclick="operate('${dto.id}','11082',false)">编辑银行卡</button>
                </c:if>
                <c:if test="${menuType == 53}">
                    <c:if test="${dto.orgUserId != null}">
                        <button class="butList active" onclick="caseCenterInfoAllotAdd('${dto.id}','${dto.gradationState}')">改派</button>
                    </c:if>
                    <button class="butList active" onclick="operate('${dto.id}','5300',false)">分配机构</button>
                    <%--<button class="butList active" onclick="operate('${dto.id}','5301',false)">分配工作室</button>--%>
                    <button class="butList active" onclick="selectOperator('${dto.id}','${dto.operatorName}','${dto.orgId}')">修改业务员</button>
                    <c:if test="${caseUserRole.customerManager}">
                        <button class="butList active" onclick="operate('${dto.id}','9999',true)">删除案件</button>
                        <c:if test="${dto.isTestcase != 1}">
                            <button class="butList active" onclick="operate('${dto.id}','9998',true)">转测试案件</button>
                        </c:if>
                        <c:if test="${dto.type == 2}">
                            <button class="butList active" onclick="operate('${dto.id}','6868',true)">转贷款案件</button>
                        </c:if>
                        <c:if test="${dto.type == 1}">
                            <button class="butList active" onclick="operate('${dto.id}','6969',true)">转代理案件</button>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 5 && caseUserRole.assessor && dto.gradationState == 2 && dto.caseState == 1}">
                    <button class="butList active" onclick="operate('${dto.id}','1109',true)">接收</button>
                    <button class="butList active" onclick="operate('${dto.id}','1110',false)">拒绝</button>
                </c:if>
                <c:if test="${menuType == 55 && caseUserRole.claims && dto.gradationState == 3 && dto.caseState == 1}">
                    <button class="butList active" onclick="operate('${dto.id}','1109',true)">接收</button>
                    <button class="butList active" onclick="operate('${dto.id}','1110',false)">拒绝</button>
                </c:if>
                <c:if test="${menuType == 555 && caseUserRole.legal && dto.gradationState == 6 && dto.caseState == 1}">
                    <button class="butList active" onclick="operate('${dto.id}','1109',true)">接收</button>
                    <button class="butList active" onclick="operate('${dto.id}','1110',false)">拒绝</button>
                </c:if>
                <c:if test="${menuType == 15 && caseUserRole.assessorManager && dto.listState == 2}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <button class="butList active" onclick="operate('${dto.id}','1102',true)">审核通过</button>
                    <button class="butList active" onclick="operate('${dto.id}','1103',false)">退回</button>
                </c:if>
                <c:if test="${menuType == 15 && caseUserRole.riskSuper && dto.listState == 4}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <button class="butList active" onclick="operate('${dto.id}','1104',true)">审核通过</button>
                    <button class="butList active" onclick="operate('${dto.id}','1105',false)">退回</button>
                </c:if>
                <c:if test="${menuType == 20}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <c:if test="${dto.listState == 12}">
                        <button class="butList active" onclick="operate('${dto.id}','1107',false)">投保</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 21}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <c:if test="${dto.listState == 14}">
                        <button class="butList active" onclick="operate('${dto.id}','11081',true)">保险费支付</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 25}">
                    <c:if test="${dto.type != 2}">
                        <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                        <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                    </c:if>
                    <c:if test="${dto.listState == 16}">
                        <button class="butList active" onclick="operate('${dto.id}','1108',false)">放款确认</button>
                        <button class="butList active" onclick="operate('${dto.id}','11082',false)">编辑银行卡</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 27 && (caseUserRole.assessor || caseUserRole.assessorManager)}">
                    <c:if test="${dto.defineState != 1}">
                        <button class="butList active" onclick="operate('${dto.id}','11085',false)">确认服务费</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 104 && dto.repay != 1 && dto.handOutFlag == 2}" >
                    <button class="butList active" onclick="haldleRepayCaseCenterInfo('${dto.id}')">操作还款</button>
                </c:if>
                <c:if test="${menuType == 105}">

                </c:if>
                <c:if test="${menuType == 103}">
                    <button class="butList active" onclick="updCaseCenterInfoConfirmRepay('${dto.id}')">确认还款</button>
                </c:if>
                <c:if test="${menuType == 26 && dto.assessFlowState != 2}">
                    <c:if test="${dto.gradationState == 2}">
                        <button class="butList active" onclick="operate('${dto.id}','2601',false)">提交状态</button>
                    </c:if>
                    <button class="butList active" onclick="follow('${dto.id}','add','2101','PG')">添加跟踪</button>
                    <button class="butList active" onclick="follow('${dto.id}','stop','2100','PG')">结束跟踪</button>
                </c:if>
                <c:if test="${menuType == 36 && dto.claimFlowState != 2}">
                    <c:if test="${dto.gradationState == 3}">
                        <button class="butList active" onclick="operate('${dto.id}','3601',false)">提交状态</button>
                    </c:if>
                    <button class="butList active" onclick="follow('${dto.id}','add','2101','SP')">添加跟踪</button>
                    <button class="butList active" onclick="follow('${dto.id}','stop','2100','SP')">结束跟踪</button>
                </c:if>
                <c:if test="${menuType == 51 && dto.legalFlowState != 2}">
                    <c:if test="${dto.gradationState == 6}">
                        <button class="butList active" onclick="operate('${dto.id}','5101',false)">提交状态</button>
                    </c:if>
                    <button class="butList active" onclick="follow('${dto.id}','add','2101','SS')">添加跟踪</button>
                    <button class="butList active" onclick="follow('${dto.id}','stop','2100','SS')">结束跟踪</button>
                </c:if>
                <c:if test="${menuType == 52 && dto.customerFlowState != 2}">
                    <button class="butList active" onclick="follow('${dto.id}','add','2101','KF')">添加跟踪</button>
                    <button class="butList active" onclick="follow('${dto.id}','stop','2100','KF')">结束跟踪</button>
                </c:if>
                <c:if test="${menuType == 10 || menuType == 30 || menuType == 35|| menuType == 45 || menuType == 50}">
                    <c:if test="${(dto.listState == 888 || dto.listState == 6 || dto.listState == 10 || dto.listState == 11) && (caseUserRole.assessor) }">
                        <c:if test="${dto.type != 2}">
                            <button class="butList active" onclick="report(1,'${dto.id}','${dto.caseNo}','edit')">公估报告</button>
                            <button class="butList active" onclick="report(2,'${dto.id}','${dto.caseNo}','edit')">评估报告</button>
                        </c:if>
                        <button class="butList active" onclick="operate('${dto.id}','1101',true)">提交审核</button>
                    </c:if>

                    <c:if test="${dto.listState == 8  && caseUserRole.assessor}">
                        <c:if test="${dto.type != 2}">
                            <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                            <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                        </c:if>
                        <button class="butList active" onclick="operate('${dto.id}','1106',true)">提交贷款</button>
                        <button class="butList active" onclick="operate('${dto.id}','11061',false)">再次审核</button>
                    </c:if>

                    <c:if test="${dto.gradationState == 3}">
                        <c:if test="${dto.type != 2}">
                            <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                            <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                        </c:if>
                        <%--<c:if test="${dto.listState == 3001 && (menuType == 35 || menuType == 50)}">--%>
                            <%--<button class="butList active" onclick="operate('${dto.id}','3002',true)">通过审核</button>--%>
                            <%--<button class="butList active" onclick="operate('${dto.id}','3003',false)">退回审核</button>--%>
                        <%--</c:if>--%>

                        <c:if test="${dto.listState == 777 && caseUserRole.claims}">
                            <button class="butList active" onclick="operate('${dto.id}','77701',true)">材料收集完成</button>
                        </c:if>
                        <c:if test="${(dto.listState == 77701 || dto.listState == 240202) && caseUserRole.claims}">
                            <button class="butList active" onclick="operate('${dto.id}','77702',false)">鉴定</button>
                        </c:if>
                        <c:if test="${(dto.listState == 77702 || dto.listState == 26) && caseUserRole.claims}">
                            <button class="butList active" onclick="report(3,'${dto.id}','${dto.caseNo}','edit')">索赔预案</button>
                            <button class="butList active" onclick="operate('${dto.id}','1111',true)">提交审核</button>
                        </c:if>
                        <%-- 索赔预案风控待审核 --%>
                        <c:if test="${dto.listState == 22 && caseUserRole.claimsManager}">
                            <c:if test="${menuType == 35}">
                                <button class="butList active" onclick="report(3,'${dto.id}','${dto.caseNo}','audit')">索赔预案</button>
                                <button class="butList active" onclick="operate('${dto.id}','1112',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','1113',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${dto.listState == 24 && caseUserRole.claims}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <button class="butList active" onclick="operate('${dto.id}','2401',false)">调解</button>
                        </c:if>
                        <c:if test="${dto.listState == 2402 && caseUserRole.claims}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <%-- 如果是免鉴定的案子 则可以转诉讼 也可以重新鉴定,  如果是鉴定的案子只能转诉讼 --%>
                            <c:if test="${dto.appraiseType == 0}">
                                <button class="butList active" onclick="operate('${dto.id}','240202',true)">重新鉴定</button>
                            </c:if>
                        </c:if>
                        <c:if test="${(dto.listState == 2401 || dto.listState == 32) && caseUserRole.claims}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <button class="butList active" onclick="report(4,'${dto.id}','${dto.caseNo}','edit')">结案报告</button>
                            <button class="butList active" onclick="operate('${dto.id}','1114',true)">提交审核</button>
                        </c:if>
                        <c:if test="${dto.listState == 28 && caseUserRole.claimsManager}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <c:if test="${menuType == 35}">
                                <button class="butList active" onclick="report(4,'${dto.id}','${dto.caseNo}','audit')">结案报告</button>
                                <button class="butList active" onclick="operate('${dto.id}','1115',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','1116',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${dto.listState == 30 && caseUserRole.claims}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <button class="butList active" onclick="operate('${dto.id}','1117',false)">收取服务费</button>
                        </c:if>
                        <c:if test="${(dto.listState == 34 || dto.listState == 40) && caseUserRole.claims}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <button class="butList active" onclick="operate('${dto.id}','1118',true)">发起结案</button>
                        </c:if>
                        <c:if test="${dto.listState == 36 && caseUserRole.claimsManager}">
                            <button class="butList defuelt" onclick="report(3,'${dto.id}','${dto.caseNo}','view')">索赔预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <c:if test="${menuType == 35}">
                                <button class="butList active" onclick="operate('${dto.id}','1119',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','1120',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${menuType == 30 && (dto.listState != 3001 && dto.listState != 3004)}">
                            <button class="butList active" onclick="operate('${dto.id}','3001',false)">解约</button>
                        </c:if>
                        <button class="butList active" onclick="operate('${dto.id}','11111',true)">转诉讼</button>
                        <button class="butList active" onclick="operate('${dto.id}','11082',false)">编辑银行卡</button>
                    </c:if>
                    <c:if test="${dto.gradationState == 6}">
                        <c:if test="${dto.type != 2}">
                            <button class="butList defuelt" onclick="report(1,'${dto.id}','${dto.caseNo}','view')">公估报告</button>
                            <button class="butList defuelt" onclick="report(2,'${dto.id}','${dto.caseNo}','view')">评估报告</button>
                        </c:if>
                        <%--<c:if test="${dto.listState == 3001 && (menuType == 35 || menuType == 50)}">--%>
                            <%--<button class="butList active" onclick="operate('${dto.id}','3002',true)">通过审核</button>--%>
                            <%--<button class="butList active" onclick="operate('${dto.id}','3003',false)">退回审核</button>--%>
                        <%--</c:if>--%>
                        <c:if test="${dto.listState == 666 && caseUserRole.legal}">
                            <button class="butList active" onclick="operate('${dto.id}','66601',true)">材料收集完成</button>
                        </c:if>
                        <c:if test="${(dto.listState == 66601 || dto.listState == 6660202) && caseUserRole.legal}">
                            <button class="butList active" onclick="report(5,'${dto.id}','${dto.caseNo}','edit')">诉讼预案</button>
                            <button class="butList active" onclick="operate('${dto.id}','66602',true)">提交审核</button>
                            <%--<a href="javascript:operate('${dto.id}','11113',true);">转索赔</a>--%>
                        </c:if>
                        <c:if test="${dto.listState == 66602 && caseUserRole.legalManager}">
                            <c:if test="${menuType == 50}">
                                <button class="butList active" onclick="report(5,'${dto.id}','${dto.caseNo}','audit')">诉讼预案</button>
                                <button class="butList active" onclick="operate('${dto.id}','6660201',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','6660202',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${dto.listState == 6660201 && caseUserRole.legal}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <button class="butList active" onclick="operate('${dto.id}','666020101',false)">立案</button>
                        </c:if>
                        <c:if test="${(dto.listState == 666020101 || dto.listState == 32) && caseUserRole.legal}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <button class="butList active" onclick="report(4,'${dto.id}','${dto.caseNo}','edit')">结案报告</button>
                            <button class="butList active" onclick="operate('${dto.id}','1114',false)">提交审核</button>
                        </c:if>
                        <c:if test="${dto.listState == 28 && caseUserRole.legalManager}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <c:if test="${menuType == 50}">
                                <button class="butList active" onclick="report(4,'${dto.id}','${dto.caseNo}','audit')">结案报告</button>
                                <button class="butList active" onclick="operate('${dto.id}','1115',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','1116',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${dto.listState == 30 && caseUserRole.legal}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <button class="butList active" onclick="operate('${dto.id}','1117',false)">收取服务费</button>
                            <c:if test="${dto.closeCaseType == 0}">
                                <%--<button class="butList active" onclick="operate('${dto.id}','3401',true)">转索赔</button>--%>
                            </c:if>
                        </c:if>
                        <c:if test="${(dto.listState == 34 || dto.listState == 40) && caseUserRole.legal}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <%--  阶段结案不能发起结案,只能转索赔  --%>
                            <c:if test="${dto.closeCaseType == 1}">
                                <button class="butList active" onclick="operate('${dto.id}','1118',true)">发起结案</button>
                            </c:if>
                            <c:if test="${dto.closeCaseType == 0}">
                                <%--<button class="butList active" onclick="operate('${dto.id}','3401',true)">转索赔</button>--%>
                            </c:if>
                        </c:if>
                        <c:if test="${dto.listState == 36 && caseUserRole.claimsManager}">
                            <button class="butList defuelt" onclick="report(5,'${dto.id}','${dto.caseNo}','view')">诉讼预案</button>
                            <button class="butList defuelt" onclick="report(4,'${dto.id}','${dto.caseNo}','view')">结案报告</button>
                            <c:if test="${menuType == 50}">
                                <button class="butList active" onclick="operate('${dto.id}','1119',true)">通过审核</button>
                                <button class="butList active" onclick="operate('${dto.id}','1120',false)">退回审核</button>
                            </c:if>
                        </c:if>
                        <c:if test="${menuType == 45 && (dto.listState != 3001 && dto.listState != 3004)}">
                            <button class="butList active" onclick="operate('${dto.id}','3001',false)">解约</button>
                        </c:if>
                        <button class="butList active" onclick="operate('${dto.id}','11082',false)">编辑银行卡</button>
                        <button class="butList active" onclick="operate('${dto.id}','3401',true)">转索赔</button>
                    </c:if>
                </c:if>
                <c:if test="${menuType == 56}">
                    <c:if test="${caseUserRole.customer && dto.listState == 3001}">
                        <button class="butList active" onclick="operate('${dto.id}','3004',true)">通过审核</button>
                        <button class="butList active" onclick="operate('${dto.id}','3003',false)">退回审核</button>
                    </c:if>
                    <c:if test="${caseUserRole.marketingManager && dto.listState == 3004}">
                        <button class="butList active" onclick="operate('${dto.id}','3002',true)">通过审核</button>
                        <button class="butList active" onclick="operate('${dto.id}','3003',false)">退回审核</button>
                    </c:if>
                </c:if>
            </div>
            <div class="main-boy">
                <div>
                    <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <c:if test="${dto.isTestcase ==1}">
                            <tr>
                                <td colspan="6" style="color: #FF4500">测试案件</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>姓名</td>
                            <td>
                                <div id="caseName1" style="padding: 0">
                                    ${dto.caseName}
                                    <c:if test="${menuType == 27 && (caseUserRole.assessor || caseUserRole.assessorManager)}">
                                        <a onclick="selectCaseName()"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                    </c:if>
                                </div>
                                <div id="caseName2" style="display: none;padding: 0 ">
                                    <input type="text"  id="caseName" name="caseName" value="${dto.caseName}" class="form-control" style="width: 120px;display:inline-block">
                                    <a onclick="updateCaseName('${dto.id}')">提交</a>
                                </div>
                            </td>
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
                            <td>当前经办人</td>
                            <td>${dto.orgUserName}</td>
                            <td>推广员</td>
                            <td>${dto.salesmanName}</td>
                            <td>所属机构</td>
                            <td>${dto.orgName}</td>
                        </tr>
                        <c:if test="${menuType == 53 || menuType == 56}">
                            <tr>
                                <td>评估员</td>
                                <td>${dto.assessName}</td>
                                <td>索赔员</td>
                                <td>${dto.claimantName}</td>
                                <td>诉讼员</td>
                                <td>${dto.legalUserName}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>业务员</td>
                            <td>${dto.operatorName}</td>
                            <td>签约时间</td>
                            <td><fmt:formatDate value="${dto.agreeSignTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>业务员跟踪状态</td>
                            <td>${dto.operatorFlowName}</td>
                        </tr>
                        <tr>
                            <td>客服跟踪状态</td>
                            <td>${dto.customerFlowName}</td>
                            <td>评估员跟踪状态</td>
                            <td>${dto.assessFlowName}</td>
                            <c:if test="${dto.gradationState != 6}">
                                <td>索赔员跟踪状态</td>
                                <td>${dto.claimFlowName}</td>
                            </c:if>
                            <c:if test="${dto.gradationState == 6}">
                                <td>诉讼员跟踪状态</td>
                                <td>${dto.legalFlowName}</td>
                            </c:if>
                        </tr>
                        <c:if test="${menuType == 27}">
                            <tr style="font-weight: bold;">
                                <td>服务费金额</td>
                                <td>${dto.defineAmount}元</td>
                                <td>确认状态</td>
                                <td>
                                     ${dto.defineStateName}
                                </td>
                                <td>确认时间</td>
                                <td><fmt:formatDate value="${dto.defineDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${menuType == 104}">
                            <tr>
                                <td>支付凭证</td>
                                <td colspan="5"><img src="${dto.repayImg}" width="75px;" height="75px;"></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>驳回原因</td>
                            <td colspan="5">${dto.operReason}</td>
                        </tr>
                        </tbody>
                    </table>
                    <c:if test="${caseFollows != null}">
                        <c:forEach items="${caseFollows}" var="item">
                            <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                                <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                                <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followBy}
                                    【
                                    <c:if test="${item.gradationState  == 0}">业务员</c:if>
                                    <c:if test="${item.gradationState  == 1}">客服</c:if>
                                    <c:if test="${item.gradationState  == 2}">评估员</c:if>
                                    <c:if test="${item.gradationState  == 3}">索赔员</c:if>
                                    <c:if test="${item.gradationState  == 4}"></c:if>
                                    <c:if test="${item.gradationState  == 5}"></c:if>
                                    <c:if test="${item.gradationState  == 6}">诉讼员</c:if>】
                                    <br>内容：${item.followDesc}<br>
                                    <c:if test="${item.followType != 2}">
                                        下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </c:if>
                                </div><div class="stepDetail-detail"></div></div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${caseFollows.size() == 0}">
                        <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                            暂无跟踪记录
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

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
                   if(btnCode == '9999'){
                       reloadParent();
                   }else{
                       location.reload();
                   }
               })
           }
       }else{
           var title = null,url = null;
           if(btnCode == '1100'){
               title = '紧急代扣';
               url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode
           }else if(btnCode == '1103' || btnCode == '1105' || btnCode == '11061' || btnCode == '1110' || btnCode == '1113' || btnCode == '1116' || btnCode == '1120' || btnCode == '6660202' || btnCode == '3001' || btnCode == '3003'){
               title = '原因';
               url = '${ctx}/case/center/back?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1107'){
               height = 200;
               width = 500;
               title = '投保';
               url = '${ctx}/case/center/insured?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1108'){
               title = '放款确认';
               url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode;
           }else if(btnCode == '1117'){
               height = 500;
               width = 800;
               title = '收取服务费';
               url = '${ctx}/case/center/start?id='+id+"&passType=passSuning&btnCode="+btnCode;
           }else if(btnCode == '11082'){
               title = '编辑银行卡';
               url = "${ctx}/case/center/editBankCardInfo?caseId="+id+"&btnCode="+btnCode;
           }else if(btnCode == '5300'){
               height = 600;
               width = 1000;
               title = '分配机构';
               url = "${ctx}/case/center/selectOrgInfo?caseId="+id+"&btnCode="+btnCode;
           }else if(btnCode == '5301'){
               height = 600;
               width = 1000;
               title = '分配工作室';
               url = "";
               return;
           }else if(btnCode == '11085'){
               height = 200;
               width = 500;
               title = '确认服务费';
               url = '${ctx}/case/center/okServiceFee?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '2601' || btnCode == '3601' || btnCode == '5101'){
               //评估阶段提交状态
               title = '提交案件状态';
               url = '${ctx}/case/center/commitState?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '77702'){
               height = 200;
               width = 500;
               title = '鉴定';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '2401'){
               height = 200;
               width = 500;
               title = '调解';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1114'){
               height = 200;
               width = 500;
               title = '结案';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '666020101'){
               height = 300;
               width = 600;
               title = '立案';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
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
   function view(type,caseId,caseNo,op){
       if(type == 1){
           viewEstimateInquiry(caseId)
       }else if(type ==2){
           selectFileMid(caseNo,caseId);
       }else if(type ==3){
           selectCaseDetails('${dto.type}','${dto.caseId}',caseNo,1);
       }else if(type ==4){
           selectReport('${dto.id}',caseNo);
       }else if(type ==5){
           updateReson('${dto.id}',caseNo);
       }
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
            url = "${ctx}/case/report/caseMediationLegal?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }
        openDialog({
            frame:true,
            title:title,
            height:900,
            width:1000,
            url:url
        });
    }
   function commitState(){

   }
   function follow(id,type,btnCode,choose){
       openDialog({
           frame: true,
           title: "添加跟踪记录",
           height: 450,
           width: 650,
           url: "${ctx}/caseCenterInfoFollow/caseCenterInfoFollowAdd?caseId="+ id+"&choose="+choose+"&type="+type
       });
       <%--if(type == 'add'){--%>
           <%--openDialog({--%>
               <%--frame: true,--%>
               <%--title: "添加跟踪记录",--%>
               <%--height: 450,--%>
               <%--width: 650,--%>
               <%--url: "${ctx}/caseCenterInfoFollow/caseCenterInfoFollowAdd?caseId="+ id+"&choose="+choose--%>
           <%--});--%>
       <%--}else if(type == 'stop'){--%>
           <%--var url = "${ctx}/case/center/operate",param = {"id":id,"btnCode":btnCode,"choose":choose};--%>
           <%--if(confirm('是否确认？')){--%>
               <%--ajaxSubmit(url,param,function(v,e,p){--%>
                   <%--alert(e.data.msg);--%>
                   <%--location.reload();--%>
               <%--})--%>
           <%--}--%>
       <%--}--%>
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
   function selectFileMid(caseNo,caseId){
       openDialog({
           frame:true,
           title:"查看单证",
           height:600,
           width:1000,
           url:"${ctx}/case/selectFileMid?caseNo="+caseNo+"&caseId="+caseId
       });
   }
   function selectCaseDetails(type,caseId,caseNo,caseType){
       openDialog({
           frame:true,
           title:"查看进度",
           height:500,
           width:1000,
           url:"${ctx}/case/selectCaseDetails?type="+type+"&caseId="+caseId+"&caseType="+caseType+"&caseNo="+caseNo
       });
   }
   function selectReport(id,caseNo){
       openDialog({
           frame:true,
           title:"测算报告",
           height:600,
           width:1000,
           url:"${ctx}/case/risk/paymentEstimateReportList?caseNo="+caseNo+"&caseId="+id+"&mType=" +1,
           load : true
       });
   }

   function updateReson(id,caseNo){
       openDialog({
           frame:true,
           title:"伤残报告",
           height:650,
           width:1100,
           url:"${ctx}/invalidism/toReport?caseNo="+caseNo+"&caseId="+id+"&op=viewNo"
       });
   }

   var haldleRepayCaseCenterInfo = function(id){
       openDialog({
           frame:true,
           title:"操作还款",
           height:500,
           width:800,
           url:"${ctx}/case/haldleRepayCaseCenterInfo?id="+id
       });
   }

   var caseCenterInfoAllotAdd = function(caseId,gradationState){
       openDialog({
           frame:true,
           title:"改派",
           height:500,
           width:800,
           url:"${ctx}/caseCenterInfoAllot/caseCenterInfoAllotAdd?caseId="+caseId+"&gradationState="+gradationState+"&type=2"
       });
   }

   var selectOperator = function(caseId,operatorName,orgId){
       openDialog({
           frame:true,
           title:"修改业务员",
           height:500,
           width:800,
           url:"${ctx}/caseCenterInfoAllot/selectOperator?caseId="+caseId+"&operatorName="+operatorName+"&orgId="+orgId
       });
   }
   /**
    * 确认还款
    */
   function updCaseCenterInfoConfirmRepay(id){
       ajaxSubmit("${ctx}/case/updCaseCenterInfoConfirmRepay",{"id":id},reload,"确认成功！","确认通过还款？","确认失败！");
   }

   function selectCaseName() {
       $("#caseName2").show();
       $("#caseName1").hide();
   }
   function updateCaseName(id) {
       var caseName = document.getElementById("caseName").value;
       ajaxSubmit("${ctx}/case/center/operate",{"id":id,"btnCode":9997,"caseName":caseName},reload,"修改成功！","确认修改？","修改失败！");
   }
</script>
</body>
</html>
