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
        .div_source_ok{
            display: inline-block;
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
<input type="hidden" name="id" value="${surveyFranchisee.id}">
<input type="hidden" name="surveyCode" value="${surveyCode}">
<div class="title">
    <button class="butList active" onclick="operate('${surveyFranchisee.id}','${surveyCode}','1000',false);">修改</button>
    <button class="butList active" onclick="operate('${surveyFranchisee.id}','areaInformation','2000',false);">片区信息</button>
    <button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','9999',true);">删除</button>
    <button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','1500',false);">名下人员</button>
    <button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','1600',false);">添加人员</button>
    <button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','5000',false);">设置终审人员</button>
    <button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','6000',false);">调查区域</button>
    <%--<c:if test="${surveyFranchisee.type==1}">--%>
        <%--<button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','1700',true);">修改为加盟</button>--%>
    <%--</c:if>--%>
    <%--<c:if test="${surveyFranchisee.type==2}">--%>
        <%--<button class="butList defuelt" onclick="operate('${surveyFranchisee.id}','${surveyCode}','1800',true);">修改为自营</button>--%>
    <%--</c:if>--%>

</div>

<div class="main-boy">
    <div>
        <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <tr>
                <td>调查方名称</td>
                <td>${surveyFranchisee.name}</td>
                <td>调查方code</td>
                <td>${surveyFranchisee.code}</td>
                <td>调查方类别(互助)</td>
                <td>
                    <div class="div_source_ok" id="type1">
                        <c:if test="${surveyFranchisee.type==1}">直营</c:if>
                        <c:if test="${surveyFranchisee.type==2}">合伙</c:if>
                        <c:if test="${surveyFranchisee.type==3}">合作</c:if>
                        <a onclick="selectFranchiseeType(1)"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
                    </div>
                    <div class="div_source_ok" id="type2" style="display: none;" >
                        <select name="franchiseeType" id="franchiseeType" class="form-control" style="width: 100px">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${surveyFranchisee.type==1}">selected="selected" </c:if>>直营</option>
                            <option value="2" <c:if test="${surveyFranchisee.type==2}">selected="selected" </c:if>>合伙</option>
                            <option value="3" <c:if test="${surveyFranchisee.type==3}">selected="selected" </c:if>>合作</option>
                        </select>
                    </div>
                    <div class="div_source_ok" id="type3" style="display: none">
                        <input type="submit" value="确定" onclick="updateFranchiseeType('${surveyFranchisee.id}','${surveyCode}','1900');" class="btn" style="display: inline-flex" />
                    </div>
                </td>
            </tr>
            <tr>
<%--                <td>调查方级别</td>--%>
<%--                <td>--%>
<%--                    <c:if test="${surveyFranchisee.level==1}">一级</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.level==2}">二级</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.level==3}">三级</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.level==4}">四级</c:if>--%>
<%--                </td>--%>
<%--                <td>区域类别</td>--%>
<%--                <td>--%>
<%--                    <c:if test="${surveyFranchisee.areaType==1}">省</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.areaType==2}">市</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.areaType==3}">区</c:if>--%>
<%--                </td>--%>
                <td>调查方类别(保司)</td>
                <td>
                    <div class="div_source_ok" id="type4">
                        <c:if test="${surveyFranchisee.insuranceType!=null && surveyFranchisee.insuranceType==1}">直营</c:if>
                        <c:if test="${surveyFranchisee.insuranceType!=null && surveyFranchisee.insuranceType==2}">合伙</c:if>
                        <c:if test="${surveyFranchisee.insuranceType!=null && surveyFranchisee.insuranceType==3}">合作</c:if>
                        <a onclick="selectFranchiseeType(2)"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
                    </div>
                    <div class="div_source_ok" id="type5" style="display: none;" >
                        <select name="insuranceType" id="insuranceType" class="form-control" style="width: 100px">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${surveyFranchisee.insuranceType==1}">selected="selected" </c:if>>直营</option>
                            <option value="2" <c:if test="${surveyFranchisee.insuranceType==2}">selected="selected" </c:if>>合伙</option>
                            <option value="3" <c:if test="${surveyFranchisee.insuranceType==3}">selected="selected" </c:if>>合作</option>
                        </select>
                    </div>
                    <div class="div_source_ok" id="type6" style="display: none">
                        <input type="submit" value="确定" onclick="updateFranchiseeType('${surveyFranchisee.id}','${surveyCode}','1900');" class="btn" style="display: inline-flex" />
                    </div>
                </td>
                <td>申请人</td>
                <td>${surveyFranchisee.appUser}</td>
                <td>审批人</td>
                <td>${surveyFranchisee.apvUser}</td>
            </tr>
<%--            <tr>--%>
<%--&lt;%&ndash;                <td>是否特殊机构</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <c:if test="${surveyFranchisee.isSpecial==0}">否</c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <c:if test="${surveyFranchisee.isSpecial==1}">是</c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </td>&ndash;%&gt;--%>

<%--            </tr>--%>
            <tr>
                <td>业务属性</td>
                <td>
                    <c:if test="${surveyFranchisee.busType==1}">互助</c:if>
                    <c:if test="${surveyFranchisee.busType==2}">保险</c:if>
                    <c:if test="${surveyFranchisee.busType==3}">互助+保险</c:if>
                </td>
                <td>机构状态</td>
                <td>
                    <c:if test="${surveyFranchisee.orgState == 0}">启用</c:if>
                    <c:if test="${surveyFranchisee.orgState == 1}">停用</c:if>
                </td>
                <td>关联人事管理-机构/部门名称</td>
                <td>
                    ${surveyFranchisee.departmentName}
                </td>
<%--                <td>父级名称</td>--%>
<%--                <td>${surveyFranchisee.parentName}</td>--%>
<%--                <td>公估业务是否独立开票</td>--%>
<%--                <td>--%>
<%--                    <c:if test="${surveyFranchisee.aloneBill==0}">否</c:if>--%>
<%--                    <c:if test="${surveyFranchisee.aloneBill==1}">是</c:if>--%>
<%--                </td>--%>
            </tr>
<%--            <tr>--%>
<%--                <td>收款人姓名</td>--%>
<%--                <td>${surveyFranchisee.acceptUser}</td>--%>
<%--                <td>收款人身份证号</td>--%>
<%--                <td>${surveyFranchisee.acceptUserIdcard}</td>--%>
<%--                <td>收款人身份证照片</td>--%>
<%--                <td>--%>
<%--                    <c:forEach items="${surveyFranchisee.acceptUserIdcardUrlList}" var="item">--%>
<%--                        <img src="${item}" width="75;" height="75;" class="picToBig">--%>
<%--                    </c:forEach>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>银行卡号</td>--%>
<%--                <td>${surveyFranchisee.bankCard}</td>--%>
<%--                <td>银行名称（含支行）</td>--%>
<%--                <td>${surveyFranchisee.bankName}</td>--%>
<%--                <td>银行卡照片</td>--%>
<%--                <td>--%>
<%--                    <c:forEach items="${surveyFranchisee.bankUrlList}" var="item">--%>
<%--                        <img src="${item}" width="75;" height="75;" class="picToBig">--%>
<%--                    </c:forEach>--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <td>创建人</td>
                <td>${surveyFranchisee.createByName}</td>
                <td>创建时间</td>
                <td><fmt:formatDate value="${surveyFranchisee.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>更新时间</td>
                <td><fmt:formatDate value="${surveyFranchisee.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
<%--            <tr>--%>
<%--&lt;%&ndash;                <td>区域名称</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <td>${surveyFranchisee.areaName}</td>&ndash;%&gt;--%>

<%--            </tr>--%>
            <tr>
<%--                <td>深度案件价格(保司)</td>--%>
<%--                <td>${surveyFranchisee.deepCasesPrice}</td>--%>
                <td>业务类型</td>
                <td>
                    <c:if test="${surveyFranchisee.serviceType != null}">
                        <c:if test="${surveyFranchisee.serviceType == 1}">垫付</c:if>
                        <c:if test="${surveyFranchisee.serviceType == 2}">调查</c:if>
                        <c:if test="${surveyFranchisee.serviceType == 3}">垫付+调查</c:if>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>


            <%--<div class="table-title">新增价格</div>--%>
            <%--<div class="panel-heading">--%>
                <%--<div class="pin">--%>
                    <%--<form class="form-inline" role="form" action="${ctx}/baseSurvey/add" method="post">--%>
                        <%--<input type="hidden" name="surveyCode" value="franchisee">--%>
                        <%--<input type="hidden" name="id" value="${surveyFranchisee.id}">--%>
                        <%--<input type="hidden" name="name" value="${surveyFranchisee.name}">--%>
                        <%--<div>--%>
                            <%--&lt;%&ndash;<button onclick="setBtn(1)" type="button" class="btn btn-default">省级添加</button>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<button onclick="setBtn(2)" type="button" class="btn btn-default">市级添加</button>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<button onclick="setBtn(3)" type="button" class="btn btn-default">区级添加</button>&ndash;%&gt;--%>
                            <%--<button onclick="province()" type="button" class="btn btn-default">省级添加</button>--%>
                            <%--<button onclick="city()" type="button" class="btn btn-default">市级添加</button>--%>
                            <%--<button onclick="district()" type="button" class="btn btn-default">区级添加</button>--%>
                        <%--</div>--%>
                        <%--<input type="hidden" name="btn" id ="btn" value="btn">--%>
                        <%--<br>--%>
                        <%--<div class="form-group" id="allDiv">--%>
                            <%--<select class="form-control" >--%>
                                <%--<option value="">所有省</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="form-group" id="provinceDiv" style="display: none">--%>
                            <%--省--%>
                            <%--<select id = "provinceId" name="provinceId" onchange="selectArea()" class="form-control" required="required">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<c:forEach items="${apiRsp.results}" var="area">--%>
                                    <%--<option value="${area.areaId}">${area.areaName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="form-group" id="cityDiv"  style="display: none">--%>
                            <%--市--%>
                            <%--<select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" >--%>
                                <%--<option value="">请选择</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="submit" onclick="return setPrice('franchiseePrice',needId,'${surveyFranchisee.id}','1100','${surveyFranchisee.name}',1)">--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>

        <div class="table-title">特殊价格设置</div>
        <div class="panel-heading">
            <div id="div_medical_money_1">
                1、住院病例调查，每多一份病例，价格增加${surveyFranchisee.medicalMoney}元
                <a onclick="updMedicalMoney()"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
            </div>
            <div id="div_medical_money_2" style="display: none">
                1、住院病例调查，每多一份病例，价格增加<input type="number" class="input-2" step="0.01" name="medicalMoney" id="medicalMoney" value="${surveyFranchisee.medicalMoney}" />元
                <input type="submit" value="确定" onclick="submitMedicalMoney('${surveyFranchisee.id}','${surveyCode}','4000');" class="btn" style="display: inline-flex" />
            </div>
            <div id="div_max_medical_money_1">
                    上限价格${surveyFranchisee.maxMedicalMoney}元<a onclick="updMaxMedicalMoney()"><img height="25px" width="25px" style="background-color: #FFFFFF;" src="${ctx}/img/pen.png"></a>
            </div>
            <div id="div_max_medical_money_2" style="display: none">
                上限价格<input type="number" class="input-2" step="0.01" name="maxMedicalMoney" id="maxMedicalMoney" value="${surveyFranchisee.maxMedicalMoney}" />元
                <input type="submit" value="确定" onclick="submitMaxMedicalMoney('${surveyFranchisee.id}','${surveyCode}','4400');" class="btn" style="display: inline-flex" />
            </div>
        </div>
        <div style="display: none">
            <div class="table-title">新增价格</div>
            <div class="panel-heading">
                <div class="pin">
                    <form class="form-inline" role="form" action="${ctx}/baseSurvey/add" method="post">
                        <input type="hidden" name="surveyCode" value="franchisee">
                        <input type="hidden" name="id" value="${surveyFranchisee.id}">
                        <input type="hidden" name="name" value="${surveyFranchisee.name}">
                        <input type="hidden" name="btnNew" id ="btnNew" value="btnNew">
                        <%--<button onclick="province()" type="button" class="btn btn-default">非直辖市</button>--%>
                        <%--<button onclick="provinceCity()" type="button" class="btn btn-default">直辖市</button>--%>
                        <%--<br><br>--%>
                        <div class="form-group">
                            <select id = "priceType" name="priceType" class="form-control" >
                                <option value="1" >保险版</option>
                                <option value="2" >互助版</option>
                            </select>
                        </div>
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
                            <input type="submit" onclick="return setPrice('franchiseePrice',needId,'${surveyFranchisee.id}','1300','${surveyFranchisee.name}',2,cityType,needName)">
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${surveyFranchiseePrice1 != null}">
                <div class="table-title">价格信息(保司版)
                    <%--<a href="javascript:reload()" style="position:relative;left:20px;bottom: 0px;">刷新</a>--%>
                </div>
                <div class="panel-heading">
                    <table class="table table-hover" style="border-right:1px solid #000000;border-bottom:1px solid #000000">
                        <thead  style="background-color: #fff;width: 1404px;height: 139px;display: table-caption;">
                        <tr class="frame" id="eee" style="z-index: 10000;background-color: #fff;width: 1404px">
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
                        <tbody class="class-list" id="frame"  style="width: 1404px">
                        <c:forEach items="${surveyFranchiseePrice1}" var="item">
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
                                    <a href="javascript:updatePrice('franchiseePrice','${item.areaId}','${item.franchiseeId}','1300','${item.franchiseeName}',2,'${item.areaType}','${item.areaName}','${item.priceType}');">修改</a>
                                    <a href="javascript:deletePrice('${item.franchiseeId}','${item.areaId}','franchiseePrice','9999','${item.priceType}')">删除</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </div>
            </c:if>

            <c:if test="${surveyFranchiseePrice1.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无价格(互助版)
                </div>
            </c:if>

            <c:if test="${surveyFranchiseePrice2 != null}">
                <div class="table-title table-title-s">价格信息(互助版)
                        <%--<a href="javascript:reload()" style="position:relative;left:20px;bottom: 0px;">刷新</a>--%>
                </div>
                <div class="panel-heading">
                    <table class="table table-hover2" style="border-right:1px solid #000000;border-bottom:1px solid #000000" id="tab_temp">
                        <thead  style="background-color: #fff;width: 1404px;height: 52px;display: table-caption;">
                        <tr class="frame" id="eee2" style="z-index: 10000;background-color: #fff;width: 1404px">
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">省市</th>
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">地域</th>
                            <c:forEach items="${taskInfosTwo}" var="item">
                                <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">${item.infoName}
                                    <span style="color: #b3aaaa">(元)</span>
                                </th>
                            </c:forEach>
                            <th width="4%" class="frame" style="vertical-align:middle; text-align: center;">操作</th>
                        </tr>
                        </thead>
                        <tbody class="class-list" id="frame1"  style="width: 1404px">
                        <c:forEach items="${surveyFranchiseePrice2}" var="item">
                            <tr>
                                <td width="4%" class="frame" style="vertical-align:middle;" data-value ="${item.areaId}">${item.areaName}</td>
                                <td width="4%" class="frame">
                                    <c:if test="${item.cityType==2}">省会</c:if>
                                    <c:if test="${item.cityType==3}">地级市</c:if>
                                    <c:if test="${item.cityType==4}">县级市</c:if>
                                    <c:if test="${item.cityType==5}">市区</c:if>
                                    <c:if test="${item.cityType==6}">郊区</c:if>
                                </td>
                                <c:forEach items="${taskInfosTwo}" var="taskItem">
                                    <td width="4%" class="frame">
                                        <c:forEach items="${item.priceses}" var="priceItem">
                                            <c:if test="${priceItem.taskId == taskItem.taskId && priceItem.taskInfoContentId == taskItem.taskInfoContentId && priceItem.directionResultTypeId == taskItem.directionResultTypeId}">
                                                ${priceItem.price}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </c:forEach>
                                <td width="4%" class="frame"  style="vertical-align:middle;" data-value ="${item.areaId}">
                                    <a href="javascript:updatePrice('franchiseePrice','${item.areaId}','${item.franchiseeId}','1300','${item.franchiseeName}',2,'${item.areaType}','${item.areaName}','${item.priceType}');">修改</a>
                                    <a href="javascript:deletePrice('${item.franchiseeId}','${item.areaId}','franchiseePrice','9999','${item.priceType}')">删除</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </div>
            </c:if>

            <c:if test="${surveyFranchiseePrice2.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无价格(互助版)
                </div>
            </c:if>
        </div>
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
var s_top2 = 0
$(document).scroll( function() {
    var scrollTop=$(document).scrollTop();
    s_top =  $('.table-hover').position().top
    s_top2 =  $('.table-hover2').position().top

    if (scrollTop >= s_top && scrollTop <= s_top2 - $('#eee .frame').height() - 100 ){
        $('#eee').css({
            position: 'fixed',
            top: 0
        })
        $('#eee2').css({
            position: 'static'
        })
    }else {
        $('#eee').css({
            position: 'static'
        })
        if(scrollTop >= s_top2){
            $('#eee2').css({
                position: 'fixed',
                top: 0
            })
        } else {
            $('#eee2').css({
                position: 'static'
            })
        }
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

    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        if(surveyCode =='franchisee'){
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
                height = 650;
                width = 1000;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }
            else if(btnCode == '1500'){
                height = 700;
                width = 1300;
                title = '名下人员';
                url = "${ctx}/surveyFranchisee/selectInvestigator?id="+id+"&btnCode="+btnCode
            }
            else if(btnCode == '1600'){
                height = 650;
                width = 1300;
                title = '新加人员';
                url = "${ctx}/surveyConsignor/selectUserInfo?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '5000'){
                height = 650;
                width = 1300;
                title = '设置终审人员';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '2000'){
                height = 650;
                width = 1300;
                title = '片区信息';
                url = "${ctx}/baseSurvey/popup?id="+id+"&btnCode="+btnCode+"&surveyCode="+surveyCode
            }
            else if(btnCode == '6000'){
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

    var add = function(surveyCode,franchiseeId,franchiseeName){
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&franchiseeName="+franchiseeName+"&franchiseeId="+franchiseeId
        });
    }

    function selectPrice(id) {
        $("#price"+"_"+id+"_a").show();
        $("#price"+"_"+id).hide();
    }
    <%--function updatePrice(id) {--%>
        <%--var taskPrice = document.getElementById("taskPrice"+'_'+id).value;--%>
        <%--ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":1200,"surveyCode":'franchiseePrice',"taskPrice":taskPrice},reload,"修改成功！","确认修改？","修改失败！");--%>
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

    function setPrice(surveyCode,parentId,franchiseeId,btnCode,franchiseeName,type,cityType,parentName){

        var priceType = $("#priceType").val();
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1300,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId+"&franchiseeId="+franchiseeId+"&btnCode="+btnCode+"&franchiseeName="+franchiseeName+"&type="+type+"&cityType="+cityType+"&parentName="+parentName+"&priceType="+priceType
        });
        return false;
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

    function selectType(){
        var type = $("#type").val();
        if(type ==1){
            province();
        }
        if(type ==2){
            provinceCity();
        }
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

    function selectFranchiseeType(type) {
        if (type == 1){
            $("#type2").show();
            $("#type3").show();
            $("#type1").hide();
        }else if (type == 2){
            $("#type5").show();
            $("#type6").show();
            $("#type4").hide();
        }
    }

    function updateFranchiseeType(id,surveyCode,btnCode) {
        var franchiseeType = document.getElementById("franchiseeType").value;
        var insuranceType = document.getElementById("insuranceType").value;
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":btnCode,"surveyCode":surveyCode,"franchiseeType":franchiseeType,"insuranceType":insuranceType},reload,"修改成功！","确认修改？","修改失败！");
    }

    function updatePrice(surveyCode,parentId,franchiseeId,btnCode,franchiseeName,type,cityType,parentName,priceType){
        if(cityType ==1 ){
            cityType=3;//代表直辖市
        }else if(cityType ==0 ){
            cityType=2;////代表非直辖市
        }
        openDialog({
            frame:true,
            title:"添加",
            height:650,
            width:1300,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&parentId="+parentId+"&franchiseeId="+franchiseeId+"&btnCode="+btnCode+"&franchiseeName="+franchiseeName+"&type="+type+"&cityType="+cityType+"&parentName="+parentName+"&priceType="+priceType
        });
        return false;
    }

    function deletePrice(franchiseeId,areaId,surveyCode,btnCode,priceType) {
        ajaxSubmit("${ctx}/baseSurvey/operate",{"franchiseeId":franchiseeId,"areaId":areaId,"btnCode":btnCode,"surveyCode":surveyCode,"priceType":priceType},reload,"删除成功！","确认删除？","删除失败！");
    }

table_rowspan("#tab_temp",1);
table_rowspan("#tab_temp",13);
function table_rowspan(table_id, table_colnum) {
    table_firsttd = "";
    table_firsttdkey = "";
    table_currenttd = "";
    table_SpanNum = 0;
    colnum_Obj = $(table_id + " tr td:nth-child(" + table_colnum + ")");
    colnum_Obj.each(function (i) {
        if (i == 0) {
            table_firsttd = $(this);
            table_firsttdkey = $(this).attr("data-value");
            table_SpanNum = 1;
        } else {
            table_currenttd = $(this);
            var key = table_currenttd.attr("data-value");
            if (table_firsttd.text() == table_currenttd.text() && key == table_firsttdkey) {
                table_SpanNum++;
                table_currenttd.hide(); //remove();
                table_firsttd.attr("rowspan", table_SpanNum);
            } else {
                table_firsttd = $(this);
                table_firsttdkey = $(this).attr("data-value");
                table_SpanNum = 1;
            }
        }
    });
}

    //合并单元格
    mc(0,${surveyFranchiseePrice1.size()},0);
    <%--mc1(0,${surveyFranchiseePrice2.size()},0);--%>
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

//    function mc1(startRow, endRow, col) {
//        var tb = document.getElementById("frame1");
//        let tableCellLength = tb.rows[0].cells.length;
//        for (let i = startRow; i < endRow; i++) {
//            if (tb.rows[startRow].cells[col].innerHTML == tb.rows[i + 1].cells[col].innerHTML) {
//                //合并最后一列相同的行
//                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[tableCellLength-1]);
//                tb.rows[startRow].cells[tableCellLength-1].rowSpan = (tb.rows[startRow].cells[tableCellLength-1].rowSpan | 0) + 1;
//                //合并第col列相同的行
//                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
//                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
//            }else{
//                mc(i + 1, endRow, col)
//            }
//        }
//    }
    function updMedicalMoney(){
        $("#div_medical_money_1").hide();
        $("#div_medical_money_2").show();
    }
    function updMaxMedicalMoney(){
        $("#div_max_medical_money_1").hide();
        $("#div_max_medical_money_2").show();
    }

    function submitMedicalMoney(id,surveyCode,btnCode) {
        var medicalMoney = $("#medicalMoney").val();
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":btnCode,"surveyCode":surveyCode,"medicalMoney":medicalMoney},reload,"提交成功！","确认提交？","提交失败！");
    }

    function submitMaxMedicalMoney(id,surveyCode,btnCode) {
        var maxMedicalMoney = $("#maxMedicalMoney").val();
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"btnCode":btnCode,"surveyCode":surveyCode,"maxMedicalMoney":maxMedicalMoney},reload,"提交成功！","确认提交？","提交失败！");
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>