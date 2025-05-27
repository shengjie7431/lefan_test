    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
        .frame{
            border-right:1px solid #000000;
            border-left:1px solid #000000;
            border-top:1px solid #000000;
            border-bottom:1px solid #000000;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${surveyConsignor.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <input type="hidden" name="priceType" id="priceType" value="${priceType}">
    <div class="title">
        <button class="butList active"  onclick="operate('${surveyConsignor.id}','consignorDepartment','2000',false);">部门信息</button>
        <button class="butList active"  onclick="operate('${surveyConsignor.id}','consignorBillSubject','2000',false);">开票对象</button>
        <button class="butList active"  onclick="operate('${surveyConsignor.id}','${surveyCode}','1000',false);">修改</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','9999',true);">删除</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','1300',false);">名下人员</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','1400',false);">添加人员</button>
        <c:if test="${surveyConsignor.type==1}">
<%--            <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','1700',true);">修改为散户</button>--%>
        </c:if>
        <c:if test="${surveyConsignor.type==2}">
<%--            <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','1800',true);">修改为合作伙伴</button>--%>
        </c:if>
        <c:if test="${surveyConsignor.isCredit==0}">
<%--            <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','2100',true);">修改为授信</button>--%>
        </c:if>
        <c:if test="${surveyConsignor.isCredit==1}">
<%--            <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','2200',true);">修改为非授信</button>--%>
        </c:if>
        <%--<button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','3100',false);">邮箱管理</button>--%>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','3200',false);">报告命名规则</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','5000',false);">设置终审人员</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','6000',false);">理赔原件回寄地址</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','pact',false);">合同协议</button>
        <button class="butList defuelt" onclick="operate('${surveyConsignor.id}','${surveyCode}','7000',false);">委托方区域</button>
    </div>

    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>单位简称</td>
                    <td>${surveyConsignor.company}</td>
                    <td>单位全称</td>
                    <td>${surveyConsignor.name}</td>
                    <td>code</td>
                    <td>${surveyConsignor.code}</td>
                </tr>
<%--                <tr>--%>
<%--                    <td>区域类别</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${surveyConsignor.areaType==1}">省</c:if>--%>
<%--                        <c:if test="${surveyConsignor.areaType==2}">市</c:if>--%>
<%--                        <c:if test="${surveyConsignor.areaType==3}">区</c:if>--%>
<%--                    </td>--%>
<%--                    <td>委托方区域</td>--%>
<%--                    <td>${surveyConsignor.areaName}</td>--%>
<%--                    <td>委托方类别</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${surveyConsignor.type==1}">合作伙伴</c:if>--%>
<%--                        <c:if test="${surveyConsignor.type==2}">散户</c:if>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
<%--                    <td>是否授信</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${surveyConsignor.isCredit==0}">否</c:if>--%>
<%--                        <c:if test="${surveyConsignor.isCredit==1}">是</c:if>--%>
<%--                    </td>--%>
                    <td>报告模板</td>
                    <td>${surveyConsignorModel.modelName}</td>
                    <td>委托方说明</td>
                    <td colspan="3">${surveyConsignor.remark}</td>
                </tr>
                <tr>
                    <td>申请人</td>
                    <td>${surveyConsignor.appUser}</td>
                    <td>审批人</td>
                    <td>${surveyConsignor.apvUser}</td>
                    <td>商务属性</td>
                    <td>
                        <c:if test="${surveyConsignor.businessAttr==1}">市场一部（郑哲）</c:if>
                        <c:if test="${surveyConsignor.businessAttr==2}">市场二部（曹刘强）</c:if>
                        <c:if test="${surveyConsignor.businessAttr==3}">互助</c:if>
                        <c:if test="${surveyConsignor.businessAttr==4}">正言金融（郑哲）</c:if>
                        <c:if test="${surveyConsignor.businessAttr==5}">市场三部（韩正栋）</c:if>
                    </td>
                </tr>
                <tr>
                    <td>公司属性</td>
                    <td>
                        <c:if test="${surveyConsignor.orgAttr==1}">保险公司<c:if test="${surveyConsignor.sharp == 1}">(反欺诈)</c:if></c:if>
                        <c:if test="${surveyConsignor.orgAttr==2}">互助机构</c:if>
                    </td>
                    <td>时效设置</td>
                    <td>
                        <c:if test="${surveyConsignor.efficiencyAttr==1}">工作日</c:if>
                        <c:if test="${surveyConsignor.efficiencyAttr==2}">自然日</c:if>
                    </td>
                    <td>创建人</td>
                    <td>${surveyConsignor.createByName}</td>
                </tr>
                <tr>
                    <td>收件人</td>
                    <td>
                        <c:if test="${surveyConsignor.receiver != null}">
                            ${surveyConsignor.receiver}
                        </c:if>
                    </td>
                    <td>收件人电话</td>
                    <td>
                        <c:if test="${surveyConsignor.receiverTel != null}">
                            ${surveyConsignor.receiverTel}
                        </c:if>
                    </td>
                    <td>收件人地址</td>
                    <td>
                        <c:if test="${surveyConsignor.receiverAddress != null}">
                            ${surveyConsignor.receiverAddress}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyConsignor.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>报告命名规则</td>
                    <td>
                        <c:if test="${reportRuleDto.oneRule !=null}">
                            <c:if test="${reportRuleDto.oneRule ==1}">保单号</c:if>
                            <c:if test="${reportRuleDto.oneRule ==2}">被保险人姓名</c:if>
                            <c:if test="${reportRuleDto.oneRule ==3}">理赔编号</c:if>
                            <c:if test="${reportRuleDto.oneRule ==4}">保险公司名称</c:if>
                            <c:if test="${reportRuleDto.oneRule ==5}">空</c:if>
                            <c:if test="${reportRuleDto.oneRule ==6}">${reportRuleDto.oneRuleStr}</c:if>
                            <c:if test="${reportRuleDto.oneRule ==7}">部门名称</c:if>
                            +
                        </c:if>
                        <c:if test="${reportRuleDto.twoRule !=null}">
                            <c:if test="${reportRuleDto.twoRule ==1}">保单号</c:if>
                            <c:if test="${reportRuleDto.twoRule ==2}">被保险人姓名</c:if>
                            <c:if test="${reportRuleDto.twoRule ==3}">理赔编号</c:if>
                            <c:if test="${reportRuleDto.twoRule ==4}">保险公司名称</c:if>
                            <c:if test="${reportRuleDto.twoRule ==5}">空</c:if>
                            <c:if test="${reportRuleDto.twoRule ==6}">${reportRuleDto.twoRuleStr}</c:if>
                            <c:if test="${reportRuleDto.twoRule ==7}">部门名称</c:if>
                            +
                        </c:if>
                        <c:if test="${reportRuleDto.threeRule !=null}">
                            <c:if test="${reportRuleDto.threeRule ==1}">保单号</c:if>
                            <c:if test="${reportRuleDto.threeRule ==2}">被保险人姓名</c:if>
                            <c:if test="${reportRuleDto.threeRule ==3}">理赔编号</c:if>
                            <c:if test="${reportRuleDto.threeRule ==4}">保险公司名称</c:if>
                            <c:if test="${reportRuleDto.threeRule ==5}">空</c:if>
                            <c:if test="${reportRuleDto.threeRule ==6}">${reportRuleDto.threeRuleStr}</c:if>
                            <c:if test="${reportRuleDto.threeRule ==7}">部门名称</c:if>
                            +
                        </c:if>
                        <c:if test="${reportRuleDto.fourRule !=null}">
                            <c:if test="${reportRuleDto.fourRule ==1}">保单号</c:if>
                            <c:if test="${reportRuleDto.fourRule ==2}">被保险人姓名</c:if>
                            <c:if test="${reportRuleDto.fourRule ==3}">理赔编号</c:if>
                            <c:if test="${reportRuleDto.fourRule ==4}">保险公司名称</c:if>
                            <c:if test="${reportRuleDto.fourRule ==5}">空</c:if>
                            <c:if test="${reportRuleDto.fourRule ==6}">${reportRuleDto.fourRuleStr}</c:if>
                            <c:if test="${reportRuleDto.fourRule ==7}">部门名称</c:if>
                        </c:if>
                    </td>
                    <td>开票公司</td>
                    <td>${surveyConsignor.billCompanyName}</td>
                </tr>
                <tr>
                    <td>机构状态</td>
                    <td>
                        <c:if test="${surveyConsignor.orgState == 0}">启用</c:if>
                        <c:if test="${surveyConsignor.orgState == 1}">停用</c:if>
                    </td>
                    <td>邮件上限值</td>
                    <td colspan="3">
                        ${surveyConsignor.attrMaxSize}
                    </td>
                </tr>
                <tr>
                    <td>垫付兜底方</td>
                    <td>
                        <c:if test="${surveyConsignor.advanceParty != null}">
                            <c:if test="${surveyConsignor.advanceParty == 1}">乐凡兜底</c:if>
                            <c:if test="${surveyConsignor.advanceParty == 2}">委托方兜底</c:if>
                        </c:if>
                    </td>
                    <td>业务类型</td>
                    <td colspan="3">
                        <c:if test="${surveyConsignor.serviceType != null}">
                            <c:if test="${surveyConsignor.serviceType == 1}">垫付</c:if>
                            <c:if test="${surveyConsignor.serviceType == 2}">调查</c:if>
                            <c:if test="${surveyConsignor.serviceType == 3}">垫付+调查</c:if>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>

            <div style="display: none" class="table-title">新增价格</div>
            <div style="display: none" class="panel-heading">
                <div class="pin">
                    <form class="form-inline" role="form" action="${ctx}/baseSurvey/add" method="post">
                        <input type="hidden" name="surveyCode" value="franchisee">
                        <input type="hidden" name="id" value="${surveyConsignor.id}">
                        <input type="hidden" name="name" value="${surveyConsignor.name}">
                        <c:if test="${priceType ==1}">
                            <%--<button onclick="province()" type="button" class="btn btn-default">非直辖市</button>--%>
                            <%--<button onclick="provinceCity()" type="button" class="btn btn-default">直辖市</button>--%>
                            <%--<br><br>--%>
                            <div class="form-group">
                                <select id = "type" name="type" class="form-control" onchange="selectType()" >
                                    <option value="1" >非直辖市</option>
                                    <option value="2" >直辖市</option>
                                </select>
                            </div>
                            <div class="form-group" id="provinceCityDiv" style="display: none">
                                <select id = "provinceCityIdNew" name="provinceCityIdNew" onchange="selectArea2()" class="form-control" style="width: 200px" required="required">
                                    <c:forEach items="${apiRsp.results}" var="area">
                                        <c:if test="${area.cityType==1}">
                                            <option value="${area.areaId}">${area.areaName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group" id="provinceDiv">
                                <select id = "provinceIdNew" name="provinceIdNew" onchange="selectArea3()" class="form-control" style="width: 200px" required="required">
                                    <option value="0">全部</option>
                                    <c:forEach items="${apiRsp.results}" var="area">
                                        <c:if test="${area.cityType==0}">
                                            <option value="${area.areaId}">${area.areaName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="submit" onclick="return addNew('consignorPrice',needId,'${surveyConsignor.id}','1300','${surveyConsignor.name}',2,cityType,needName)">
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>

            <div style="display: none" class="table-title">价格备注</div>
            <div style="display: none" class="panel-heading">
                <div id="div_price_remark_1">
                    <span style="color: red;word-wrap:break-word">${surveyConsignor.priceRemark}</span>
                    <a onclick="updPriceRemark()"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
                </div>
                <div id="div_price_remark_2" style="display: none">
                    <textarea id="priceRemark" name="priceRemark" style="width: 90%" height="40px">${surveyConsignor.priceRemark}</textarea>
                    <input type="submit" value="确定" onclick="submitPriceRemark('${surveyConsignor.id}','${surveyCode}','2300');" class="btn" style="display: inline-flex" />
                </div>
            </div>

            <div class="table-title">特殊价格设置</div>
            <div class="panel-heading">
                <div id="div_medical_money_1">
                    1、住院病历调查，每多一份病历，价格增加${surveyConsignor.medicalMoney}元
                    <a onclick="updMedicalMoney('medical')"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
                </div>
                <div id="div_medical_money_2" style="display: none">
                    1、住院病历调查，每多一份病历，价格增加<input type="number" class="input-2" step="0.01" name="medicalMoney" id="medicalMoney" value="${surveyConsignor.medicalMoney}" />元
                    <input type="submit" value="确定" onclick="submitMedicalMoney('${surveyConsignor.id}','${surveyCode}','4000');" class="btn" style="display: inline-flex" />
                </div>
                <div id="div_province_money_1">
                    2、主省价格不超过${surveyConsignor.mainProvinceMoney}<a onclick="updMedicalMoney('mainProvince')"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>元；跨省时，每个副省价格不超过${surveyConsignor.viceProvinceMoney}<a onclick="updMedicalMoney('viceProvince')"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>元
                </div>
                <div id="div_province_money_2" style="display: none">
                    2、主省价格不超过<input type="number" class="input-2" step="0.01" name="mainProvinceMoney" id="mainProvinceMoney" value="${surveyConsignor.mainProvinceMoney}" />元；跨省时，每个副省价格不超过${surveyConsignor.viceProvinceMoney}元
                    <input type="submit" value="确定" onclick="submitMedicalMoney('${surveyConsignor.id}','${surveyCode}','4100');" class="btn" style="display: inline-flex" />
                </div>
                <div id="div_province_money_3" style="display: none">
                    2、主省价格不超过${surveyConsignor.mainProvinceMoney}元；跨省时，每个副省价格不超过<input type="number" class="input-2" step="0.01" name="viceProvinceMoney" id="viceProvinceMoney" value="${surveyConsignor.viceProvinceMoney}" />元
                    <input type="submit" value="确定" onclick="submitMedicalMoney('${surveyConsignor.id}','${surveyCode}','4200');" class="btn" style="display: inline-flex" />
                </div>
            </div>
            <c:if test="${surveyConsignorPrice != null}">
                <div style="display: none" class="table-title">价格信息
                        <%--<a href="javascript:reload()" style="position:relative;left:20px;bottom: 0px;">刷新</a>--%>
                </div>

                <div style="display: none" class="panel-heading">
                    <table class="table table-hover" style="border-right:1px solid #000000;border-bottom:1px solid #000000">
                        <thead id="eee" style="background-color: #fff;z-index: 10000;width: 1404px">
                        <tr class="frame">
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">省市</th>
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">地域</th>
                            <c:forEach items="${taskInfos}" var="item">
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">${item.infoName}
                                    <span style="color: #b3aaaa">(元)</span>
                                </th>
                            </c:forEach>
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>
                        </tr>
                        </thead>
                        <tbody class="class-list"  id="frame" style="width: 1404px">
                        <c:forEach items="${surveyConsignorPrice}" var="item">
                            <tr>
                                <td width="4%" class="frame" style="vertical-align:middle;">${item.areaName}</td>
                                <td width="4%" class="frame">
                                    <c:if test="${item.cityType==2}">省会</c:if>
                                    <c:if test="${item.cityType==3}">地级市</c:if>
                                    <c:if test="${item.cityType==4}">县级市</c:if>
                                    <c:if test="${item.cityType==5}">市区</c:if>
                                    <c:if test="${item.cityType==6}">郊区</c:if>
                                </td>
                                <c:forEach items="${taskInfos}" var="taskItem">
                                    <td width="4%" class="frame">
                                        <c:forEach items="${item.priceses}" var="priceItem">
                                            <c:if test="${priceItem.taskId == taskItem.taskId && priceItem.taskInfoContentId == taskItem.taskInfoContentId && priceItem.directionResultTypeId == taskItem.directionResultTypeId}">
                                                ${priceItem.price}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </c:forEach>
                                <td width="4%" class="frame"  style="vertical-align:middle;">
                                    <a href="javascript:updatePrice('consignorPrice','${item.areaId}','${item.enturyId}','1300','${item.enturyName}',2,'${item.areaType}','${item.areaName}');">修改</a>
                                    <a href="javascript:deletePrice('${item.enturyId}','${item.areaId}','consignorPrice','9999')">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </c:if>
            <c:if test="${surveyConsignorPrice.size() == 0}">
                <div style="display: none" class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无价格
                </div>
            </c:if>
            <%--<c:if test="${priceType ==1}">
                <c:if test="${surveyConsignorPrice != null}">
                    <div class="table-title">价格信息
                        &lt;%&ndash;<a href="javascript:reload()" style="position:relative;left:20px;bottom: 0px;">刷新</a>&ndash;%&gt;
                    </div>

                    <div class="panel-heading">
                        <table class="table table-hover" style="border-right:1px solid #000000;border-bottom:1px solid #000000">
                            <thead id="eee" style="background-color: #fff;z-index: 10000;width: 1404px">
                            <tr class="frame">
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">省市</th>
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">地域</th>
                                <c:forEach items="${taskInfos}" var="item">
                                    <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">${item.infoName}
                                        <span style="color: #b3aaaa">(元)</span>
                                    </th>
                                </c:forEach>
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>
                            </tr>
                            </thead>
                            <tbody class="class-list"  id="frame" style="width: 1404px">
                            <c:forEach items="${surveyConsignorPrice}" var="item">
                                <tr>
                                    <td width="4%" class="frame" style="vertical-align:middle;">${item.areaName}</td>
                                    <td width="4%" class="frame">
                                        <c:if test="${item.cityType==2}">省会</c:if>
                                        <c:if test="${item.cityType==3}">地级市</c:if>
                                        <c:if test="${item.cityType==4}">县级市</c:if>
                                        <c:if test="${item.cityType==5}">市区</c:if>
                                        <c:if test="${item.cityType==6}">郊区</c:if>
                                    </td>
                                    <c:forEach items="${taskInfos}" var="taskItem">
                                        <td width="4%" class="frame">
                                            <c:forEach items="${item.priceses}" var="priceItem">
                                                <c:if test="${priceItem.taskId == taskItem.taskId && priceItem.taskInfoContentId == taskItem.taskInfoContentId && priceItem.directionResultTypeId == taskItem.directionResultTypeId}">
                                                    ${priceItem.price}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </c:forEach>
                                    <td width="4%" class="frame"  style="vertical-align:middle;">
                                        <a href="javascript:updatePrice('consignorPrice','${item.areaId}','${item.enturyId}','1300','${item.enturyName}',2,'${item.areaType}','${item.areaName}');">修改</a>
                                        <a href="javascript:deletePrice('${item.enturyId}','${item.areaId}','consignorPrice','9999')">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </c:if>
                <c:if test="${surveyConsignorPrice.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无价格
                </div>
            </c:if>
            </c:if>
            <c:if test="${priceType ==2}">
                <c:if test="${areaCategories != null}">
                    <div class="table-title">价格信息
                    </div>
                    <div class="panel-heading">
                        <table class="table table-hover" style="border-right:1px solid #000000;border-bottom:1px solid #000000">
                            <thead id="eee" style="background-color: #fff;z-index: 10000;width: 1404px">
                            <tr class="frame">
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">区域类别</th>
                                <c:forEach items="${taskInfos}" var="item">
                                    <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">${item.infoName}
                                        <span style="color: #b3aaaa">(元)</span>
                                    </th>
                                </c:forEach>
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>
                            </tr>
                            </thead>
                            <tbody class="class-list"  id="frame" style="width: 1404px">
                            <c:forEach items="${areaCategories}" var="item">
                                <tr>
                                    <td width="4%" class="frame" style="vertical-align:middle;">${item.name}</td>
                                    <c:forEach items="${taskInfos}" var="taskItem">
                                        <td width="4%" class="frame">
                                            <c:forEach items="${item.prices}" var="priceItem">
                                                <c:if test="${priceItem.taskId == taskItem.taskId && priceItem.taskInfoContentId == taskItem.taskInfoContentId && priceItem.directionResultTypeId == taskItem.directionResultTypeId}">
                                                    ${priceItem.taskPrice}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </c:forEach>
                                    <td width="4%" class="frame"  style="vertical-align:middle;">
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${areaCategories.size() == 0}">
                    <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                        暂无价格
                    </div>
                </c:if>
            </c:if>--%>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
var s_top = 0

$(document).scroll( function() {
    if ($('#eee').css('position') != 'fixed'){
        s_top =  $('.table-hover').position().top
    } else {
        s_top =  $('.table-hover').position().top - $('#eee .frame').height()
    }
    var scrollTop=$(document).scrollTop();
    if (scrollTop >= s_top){
        $('#eee').css({
            position: 'fixed',
            top: 0
        })
    } else {
        $('#eee').css({
            position: 'static'
        })
    }
} );

$("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    var addNew = function(surveyCode,parentId,consignorId,btnCode,consignorName,type,cityType,parentName){
        var priceType = $("#priceType").val();
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1300,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId+"&consignorId="+consignorId+"&btnCode="+btnCode+"&consignorName="+consignorName+"&type="+type+"&cityType="+cityType+"&parentName="+parentName+"&priceType="+priceType
        });
        return false;
    }

    var add = function(surveyCode,consignorId,consignorName){
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1300,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&consignorName="+consignorName+"&consignorId="+consignorId
        });
    }
    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999') {
                        if(surveyCode =='consignor'){
                            reloadParent();//删除时，需刷新父级
                        }else{
                            location.reload();
                        }
                    }else{
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 600;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }
            else if(btnCode == '1300'){
                height = 650;
                width = 1000;
                title = '委托人';
                url = "${ctx}/surveyConsignor/selectConsigner?id="+id+"&btnCode="+btnCode
            }
            else if(btnCode == '1400'){
                height = 650;
                width = 1000;
                title = '新加人员';
                url = "${ctx}/surveyConsignor/selectUserInfo?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '2000'){
                height = 650;
                width = 1000;
                title = '部门信息';
                url = "${ctx}/baseSurvey/list?consignorId="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '3100'){
                height = 650;
                width = 1000;
                title = '邮箱管理';
                url = "${ctx}/baseSurvey/popup?consignorId="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '3200'){
                height = 550;
                width = 900;
                title = '报告命名规则';
                url = "${ctx}/baseSurvey/popup?surveyConsignorId="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode + "&surveyConsignorName="+'${surveyConsignor.company}'
            }
            else if(btnCode == '5000'){
                height = 650;
                width = 1300;
                title = '设置终审人员';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }else if(btnCode == '6000'){
                height = 650;
                width = 1300;
                title = '';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }else if (btnCode == 'pact'){
                height = 650;
                width = 1300;
                title = '合同协议';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '7000'){
                width = $(document.body).outerWidth();
                height = $(document).outerHeight() - 20;
                // height = 700;
                // width = 1300;
                title = '设置区域';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
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

    function selectPrice(id) {
        $("#price"+"_"+id+"_a").show();
        $("#price"+"_"+id).hide();
    }
    <%--function updatePrice(id) {--%>
        <%--var taskPrice = document.getElementById("taskPrice"+'_'+id).value;--%>
        <%--ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":1200,"surveyCode":'consignorPrice',"taskPrice":taskPrice},reload,"修改成功！","确认修改？","修改失败！");--%>
    <%--}--%>

    var needId =0;//添加价格需要的id
    function selectArea(){
        var orgProvinceId = $("#provinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#cityId option").remove();
            $("#cityId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
        });

        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceId").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgProvinceId;//添加价格需要的id
    }
    function  selectAreaCity(){
        var orgCityId = $("#cityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
        });
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgCityId);
        var val = $("#cityId").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgCityId;//添加价格需要的id
    }

    function selectType(){
        var type = $("#type").val();
        if(type ==1){
            province();
        }
        if(type ==2){
            provinceCity();
        }
    }

    function province(){
        $("#provinceDiv").show();
        $("#provinceCityDiv").hide();
        selectArea3();
    }
    function provinceCity(){
        $("#provinceDiv").hide();
        $("#provinceCityDiv").show();
        selectArea2();
    }

    var cityType = null;
    function selectArea2(){
        var orgProvinceId = $("#provinceCityIdNew").val();
        <%--if(orgProvinceId == 0){--%>
            <%--needId=orgProvinceId;//添加价格需要的id--%>
            <%--cityType = null;--%>
            <%--return;--%>
        <%--}--%>
        <%--ajaxSubmit("${ctx}/user/role/selectByAreaId",{"areaId":orgProvinceId},function(v,e,p){--%>
            <%--//判断是不是直辖市--%>
            <%--if(e.data.results != null){--%>
                <%--var val = e.data.results[0];--%>
                <%--$("#areaTypeId").val(val.areaId);--%>
                <%--$("#areaName").val(val.areaName);--%>
                <%--needId=val.areaId;--%>
                <%--cityType =val.cityType;--%>
                <%--return;--%>
            <%--}--%>
        <%--});--%>
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceCityIdNew").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgProvinceId;//添加价格需要的id
        needName = val;
        cityType = 3;
    }

    var needName = "全部非直辖市";
    function selectArea3(){
        var orgProvinceId = $("#provinceIdNew").val();
        if(orgProvinceId == 0){
            needId=orgProvinceId;//添加价格需要的id
            needName="全部非直辖市";
            cityType = null;
            return;
        }
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceIdNew").find("option:selected").text();
        $("#areaName").val(val);
        needId=orgProvinceId;//添加价格需要的id
        needName = val;
        cityType = null;
    }

    var updatePrice = function(surveyCode,parentId,consignorId,btnCode,consignorName,type,cityType,parentName){
        if(cityType ==1 ){
            cityType=3;//代表直辖市
        }else if(cityType ==0 ){
            cityType=2;////代表非直辖市
        }
        var priceType = $("#priceType").val();
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1300,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId+"&consignorId="+consignorId+"&btnCode="+btnCode+"&consignorName="+consignorName+"&type="+type+"&cityType="+cityType+"&parentName="+parentName+"&priceType="+priceType+"&id="+'${surveyConsignor.id}'
        });
        return false;
    }

    function deletePrice(enturyId,areaId,surveyCode,btnCode) {
        var priceType = $("#priceType").val();
        ajaxSubmit("${ctx}/baseSurvey/operate",{"enturyId":enturyId,"areaId":areaId,"btnCode":btnCode,"surveyCode":surveyCode,"priceType":priceType},reload,"删除成功！","确认删除？","删除失败！");
    }

    //合并单元格
    mc(0,${surveyConsignorPrice ==null ?0:surveyConsignorPrice.size()},0);
    function mc(startRow, endRow, col) {
        var tb = document.getElementById("frame");
        let tableCellLength = tb.rows[0].cells.length;
        for (let i = startRow; i < endRow; i++) {
            if (tb.rows[startRow].cells[col].innerHTML == tb.rows[i + 1].cells[col].innerHTML) {
                //合并最后一列相同的行
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[tableCellLength-1]);
                tb.rows[startRow].cells[tableCellLength-1].rowSpan = (tb.rows[startRow].cells[tableCellLength-1].rowSpan | 0) + 1;
                //合并第col列相同的行
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
            }else{
                mc(i + 1, endRow, col)
            }
        }
    }

    function updPriceRemark(){
        $("#div_price_remark_1").hide();
        $("#div_price_remark_2").show();
    }

    function submitPriceRemark(id,surveyCode,btnCode) {
        var priceRemark = $("#priceRemark").val();
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":btnCode,"surveyCode":surveyCode,"priceRemark":priceRemark},reload,"提交成功！","确认提交？","提交失败！");
    }

    function updMedicalMoney(type){
        if(type == 'medical'){
            $("#div_medical_money_1").hide();
            $("#div_medical_money_2").show();
        }else if(type == 'mainProvince'){
            $("#div_province_money_1").hide();
            $("#div_province_money_2").show();
        }else if(type == 'viceProvince'){
            $("#div_province_money_1").hide();
            $("#div_province_money_3").show();
        }
    }

    function submitMedicalMoney(id,surveyCode,btnCode) {
        var money = "";
        if(btnCode == '4000'){
            money = $("#medicalMoney").val();
        }else if(btnCode == '4100'){
            money = $("#mainProvinceMoney").val();
        }else if(btnCode == '4200'){
            money = $("#viceProvinceMoney").val();
        }
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":btnCode,"surveyCode":surveyCode,"money":money},reload,"提交成功！","确认提交？","提交失败！");
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>