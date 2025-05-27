<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function goBack(){
            var val = $("#accidentProvinceId").find("option:selected").text();
            var val1 = $("#accidentCityId").find("option:selected").text();
            var val2 = $("#accidentDistrictId").find("option:selected").text();
            $("#accidentProvince").val(val);
            $("#accidentCity").val(val1);
            $("#accidentDistrict").val(val2);

            var valDom = $("#domicileProvinceId").find("option:selected").text();
            var valDom1 = $("#domicileCityId").find("option:selected").text();
            var valDom2 = $("#domicileDistrictId").find("option:selected").text();
            $("#domicileProvince").val(valDom);
            $("#domicileCity").val(valDom1);
            $("#domicileDistrict").val(valDom2);

            var valLive = $("#liveProvinceId").find("option:selected").text();
            var valLive1 = $("#liveCityId").find("option:selected").text();
            var valLive2 = $("#liveDistrictId").find("option:selected").text();
            $("#liveProvince").val(valLive);
            $("#liveCity").val(valLive1);
            $("#liveDistrict").val(valLive2);

            var valUnit = $("#unitProvinceId").find("option:selected").text();
            var valUnit1 = $("#unitCityId").find("option:selected").text();
            var valUnit2 = $("#unitDistrictId").find("option:selected").text();
            $("#unitProvince").val(valUnit);
            $("#unitCity").val(valUnit1);
            $("#unitDistrict").val(valUnit2);
        }
        function selectArea(){
            var orgProvinceId = $("#accidentProvinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#accidentCityId option").remove();
                $("#accidentCityId").append("<option value=''>请选择</option>");
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#accidentCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
                $("#accidentDistrictId option").remove();
                $("#accidentDistrictId").append("<option value=''>请选择</option>");
            })
        }
        function  selectAreaCity(){
            var orgCityId = $("#accidentCityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#accidentDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#accidentDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            })
        }

        function selectAreaDomicile(){
            var domicileProvinceId = $("#domicileProvinceId").val();
            if(domicileProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":domicileProvinceId},function(v,e,p){
                $("#domicileCityId option").remove();
                $("#domicileCityId").append("<option value=''>请选择</option>");
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#domicileCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
                $("#domicileDistrictId option").remove();
                $("#domicileDistrictId").append("<option value=''>请选择</option>");
            })
        }
        function  selectAreaCityDomicile(){
            var domicileCityId = $("#domicileCityId").val();
            if(domicileCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":domicileCityId},function(v,e,p){
                $("#domicileDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#domicileDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            })
        }

        function selectAreaLive(){
            var orgProvinceId = $("#liveProvinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#liveCityId option").remove();
                $("#liveCityId").append("<option value=''>请选择</option>");
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#liveCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
                $("#liveDistrictId option").remove();
                $("#liveDistrictId").append("<option value=''>请选择</option>");
            })
        }
        function  selectAreaCityLive(){
            var orgCityId = $("#liveCityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#liveDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#liveDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            })
        }

        function selectAreaUnit(){
            var orgProvinceId = $("#unitProvinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#unitCityId option").remove();
                $("#unitCityId").append("<option value=''>请选择</option>");
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#unitCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
                $("#unitDistrictId option").remove();
                $("#unitDistrictId").append("<option value=''>请选择</option>");
            })
        }
        function  selectAreaCityUnit(){
            var orgCityId = $("#unitCityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#unitDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#unitDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            })
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/infoNewUpdate" method="post">
        <input type="hidden" name="id" value="${caseInfo.id}">
        <input type="hidden" id="shenId">
        <input type="hidden" id="shiId">
        <input type="hidden" id="quId">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">案件编号</th>
                    <td width="80%">
                        <input type="text" id="caseNo" name="caseNo" value="${caseInfo.caseNo}" style="width: 400px;" class="form-control" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">事故发生地</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "accidentProvinceId" name="accidentProvinceId" style="width: 90px;" onchange="selectArea()" class="form-control">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseEntrustInput.accidentProvinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                            <%--<div id="div_city"></div>--%>
                            <%--<div id="div_district"></div>--%>
                        </div>
                        <div class="form-group">
                            市 <select id = "accidentCityId" name="accidentCityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                            <c:forEach items="${cityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseEntrustInput.accidentCityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "accidentDistrictId" name="accidentDistrictId" style="width: 90px;" class="form-control">
                            <c:forEach items="${districtApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseEntrustInput.accidentDistrictId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="accidentProvince" name="accidentProvince" value="">
                    <input type="hidden" id="accidentCity" name="accidentCity" value="">
                    <input type="hidden" id="accidentDistrict" name="accidentDistrict" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">事故详细地址</th>
                    <td  width="80%">
                        <input type="text" id="accidentAddress" name="accidentAddress" value="${caseEntrustInput.accidentAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active">处理交警队</th>
                    <td width="80%">
                        <input type="text" id="policeTeam" name="policeTeam" value="${caseInfo.extend2.policeTeam}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">委托人</th>
                    <td width="80%">
                        <input type="text" id="injuredPerson" name="injuredPerson" value="${caseEntrustInput.injuredPerson}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系电话</th>
                    <td width="80%">
                        <input type="text" id="injuredTel" name="injuredTel" value="${caseEntrustInput.injuredTel}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">身份证号</th>
                    <td width="80%">
                        <input type="text" id="idCard" name="idCard" value="${caseInfo.extend2.idCard}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">性别</th>
                    <td width="80%">
                        <select name="sex" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.sex==1}">selected="selected" </c:if>>男</option>
                            <option value="2" <c:if test="${caseInfo.extend2.sex==2}">selected="selected" </c:if>>女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">年龄</th>
                    <td width="80%">
                        <input type="text" id="age" name="age" value="${caseInfo.extend2.age}" style="width: 350px; display: inline-block!important" class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周岁
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">婚姻情况</th>
                    <td width="80%">
                        <select name="maritalStatus" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.maritalStatus==1}">selected="selected" </c:if>>已婚</option>
                            <option value="2" <c:if test="${caseInfo.extend2.maritalStatus==2}">selected="selected" </c:if>>离异</option>
                            <option value="3" <c:if test="${caseInfo.extend2.maritalStatus==3}">selected="selected" </c:if>>丧偶</option>
                            <option value="4" <c:if test="${caseInfo.extend2.maritalStatus==4}">selected="selected" </c:if>>未婚</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">工作单位</th>
                    <td width="80%">
                        <input type="text" id="workUnit" name="workUnit" value="${caseInfo.extend2.workUnit}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">入职时间</th>
                    <td width="80%">
                        <input type="text" id = "entryTime" name="entryTime" value="<fmt:formatDate value="${caseInfo.extend2.entryTime}" pattern="yyyy-MM-dd"/>"  style="width: 400px;" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">收入情况</th>
                    <td width="80%">
                        <input type="text" id="wages" name="wages" value="${caseInfo.extend2.wages}" style="width: 350px; display: inline-block!important" class="form-control" >&nbsp;&nbsp;&nbsp;&nbsp;元/月
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否缴税</th>
                    <td width="80%">
                        <select name="taxCertificate" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.taxCertificate==1}">selected="selected" </c:if>>是</option>
                            <option value="2" <c:if test="${caseInfo.extend2.taxCertificate==2}">selected="selected" </c:if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否缴纳社保</th>
                    <td width="80%">
                        <select name="paySocialSecurity" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${caseInfo.extend2.paySocialSecurity==0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${caseInfo.extend2.paySocialSecurity==1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">工资发放形式</th>
                    <td width="80%">
                        <select name="bankInfo" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.bankInfo==1}">selected="selected" </c:if>>打卡</option>
                            <option value="2" <c:if test="${caseInfo.extend2.bankInfo==2}">selected="selected" </c:if>>现金</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active">单位地址</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "unitProvinceId" name="unitProvinceId" style="width: 90px;" onchange="selectAreaUnit()" class="form-control">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.unitProvinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            市 <select id = "unitCityId" name="unitCityId" style="width: 90px;" onclick="selectAreaCityUnit()" class="form-control">
                            <c:forEach items="${unitCityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.unitCityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "unitDistrictId" name="unitDistrictId" style="width: 90px;" class="form-control">
                            <c:forEach items="${unitDistrictApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.unitDistrictId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="unitProvince" name="unitProvince" value="">
                    <input type="hidden" id="unitCity" name="unitCity" value="">
                    <input type="hidden" id="unitDistrict" name="unitDistrict" value="">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">单位详细地址</th>
                    <td  width="80%">
                        <input type="text" id="unitAddress" name="unitAddress" value="${caseInfo.extend2.unitAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active">居住地址</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "liveProvinceId" name="liveProvinceId" style="width: 90px;" onchange="selectAreaLive()" class="form-control">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.liveProvinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            市 <select id = "liveCityId" name="liveCityId" style="width: 90px;" onclick="selectAreaCityLive()" class="form-control">
                            <c:forEach items="${liveCityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.liveCityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "liveDistrictId" name="liveDistrictId" style="width: 90px;" class="form-control">
                            <c:forEach items="${liveDistrictApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.liveDistrictId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="liveProvince" name="liveProvince" value="">
                    <input type="hidden" id="liveCity" name="liveCity" value="">
                    <input type="hidden" id="liveDistrict" name="liveDistrict" value="">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">居住详细地址</th>
                    <td  width="80%">
                        <input type="text" id="liveAddress" name="liveAddress" value="${caseInfo.extend2.liveAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">居住时间</th>
                    <td width="80%">
                        <input type="text" id ="liveTime" name="liveTime" value="${caseInfo.extend2.liveTime}"  style="width: 200px; display: inline-block!important" class="form-control">
                        <select name="liveTimeType" id="liveTimeType" class="form-control" style="width: 100px; display: inline-block">
                            <option value="1" <c:if test="${caseInfo.extend2.liveTimeType==1}">selected="selected" </c:if>>年</option>
                            <option value="2" <c:if test="${caseInfo.extend2.liveTimeType==2}">selected="selected" </c:if>>个月</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">户籍地址</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "domicileProvinceId" name="domicileProvinceId" style="width: 90px;" onchange="selectAreaDomicile()" class="form-control">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.domicileProvinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            市 <select id = "domicileCityId" name="domicileCityId" style="width: 90px;" onclick="selectAreaCityDomicile()" class="form-control">
                            <c:forEach items="${domicileCityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.domicileCityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "domicileDistrictId" name="domicileDistrictId" style="width: 90px;" class="form-control">
                            <c:forEach items="${domicileDistrictApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${caseInfo.extend2.domicileDistrictId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="domicileProvince" name="domicileProvince" value="">
                    <input type="hidden" id="domicileCity" name="domicileCity" value="">
                    <input type="hidden" id="domicileDistrict" name="domicileDistrict" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">户籍详细地址</th>
                    <td  width="80%">
                        <input type="text" id="domicileAddress" name="domicileAddress" value="${caseInfo.extend2.domicileAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">户籍性质</th>
                    <td width="80%">
                        <select name="domicile" id="domicile" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.domicile==1}">selected="selected" </c:if>>农业</option>
                            <option value="2" <c:if test="${caseInfo.extend2.domicile==2}">selected="selected" </c:if>>非农业</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">拆迁/征地等情况</th>
                    <td width="80%">
                        <select name="landExpropriation" id="landExpropriation" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.landExpropriation==1}">selected="selected" </c:if>>有</option>
                            <option value="2" <c:if test="${caseInfo.extend2.landExpropriation==2}">selected="selected" </c:if>>无</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">就诊医院</th>
                    <td width="80%">
                        <input type="text" id="visitingHospital" name="visitingHospital" value="${caseInfo.extend2.visitingHospital}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=4}">
                    <tr>
                        <th width="20%" class="active">受伤部位</th>
                        <td width="80%">
                            <input type="text" id="injuredPartStr" name="injuredPartStr" value="${apply.injuredPartStr}" style="width: 320px;  display: inline-block;" class="form-control" readonly>
                            <input type="hidden" value="${apply.injuredPart}" name="injuredPart" id="injuredPart" />
                            <input type="button" value="选  择" onclick="selectInjuredPart('${caseInfo.id}')"  style="width: 78px;  height: 32px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">治疗方式</th>
                        <td width="80%">
                            <select name="treatmentMethod" class="form-control" style="width: 400px;">
                                <option value="1" <c:if test="${apply.treatmentMethod==1}">selected="selected" </c:if>>门诊</option>
                                <option value="2" <c:if test="${apply.treatmentMethod==2}">selected="selected" </c:if>>急诊门诊</option>
                                <option value="3" <c:if test="${apply.treatmentMethod==3}">selected="selected" </c:if>>住院</option>
                                <option value="4" <c:if test="${apply.treatmentMethod==4}">selected="selected" </c:if>>未就诊</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=3}">
                    <tr>
                        <th width="20%" class="active">伤情诊断</th>
                        <td width="80%">
                            <input type="text" id="injuryDiagnose" name="injuryDiagnose" value="${estimateReport.injuryDiagnose}" style="width: 400px;" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">伤残等级</th>
                        <td width="80%">
                            <input type="text" id="invalidismGradeStr" name="invalidismGradeStr" value="${estimateReport.invalidismGradeStr}" style="width: 320px;  display: inline-block;" class="form-control" readonly>
                            <input type="hidden" value="${estimateReport.invalidismGrade}" name="invalidismGrade" id="invalidismGrade" />
                            <input type="button" value="选  择" onclick="selectInvalidismGrade('${caseInfo.id}','${caseInfo.caseNo}')"  style="width: 78px;  height: 32px;"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=7}">
                    <tr>
                        <th width="20%" class="active">是否已做鉴定</th>
                        <td width="80%">
                            <select name="determineType" class="form-control" style="width: 400px;">
                                <option value="0" <c:if test="${claim.caseMediationClaim.determineType==0}">selected="selected" </c:if>>否</option>
                                <option value="1" <c:if test="${claim.caseMediationClaim.determineType==0}">selected="selected" </c:if>>是</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">肇事方</th>
                        <td width="80%">
                            <input type="text" id="partyName" name="partyName" value="${claim.caseMediationClaim.partyName}" style="width: 400px;" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">肇事方电话</th>
                        <td width="80%">
                            <input type="text" id="partyTel" name="partyTel" value="${claim.caseMediationClaim.partyTel}" style="width: 400px;" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">车牌号码</th>
                        <td width="80%">
                            <input type="text" id="cardNumber" name="cardNumber" value="${claim.caseMediationClaim.cardNumber}" style="width: 400px;" class="form-control">
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th width="20%" class="active">出险时间</th>
                    <td width="80%">
                        <input type="text" id = "accidentTime" name="accidentTime" value="<fmt:formatDate value="${caseEntrustInput.accidentTime}" pattern="yyyy-MM-dd"/>"  style="width: 400px;" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">交强险保险公司</th>
                    <td width="80%">
                        <input type="text" id="insuranceCompany" name="insuranceCompany" value="${caseInfo.extend2.insuranceCompany}" style="width: 320px;  display: inline-block;" class="form-control" readonly>
                        <input type="hidden" id="insuranceCompanyId" name="insuranceCompanyId">
                        <input type="button" value="选  择" onclick="selectInsuranceCompany(1)"  style="width: 78px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">商业险保险公司</th>
                    <td width="80%">
                        <input type="text" id="insuranceCompany2" name="insuranceCompany2" value="${caseInfo.extend2.insuranceCompany2}" style="width: 320px;  display: inline-block;" class="form-control" readonly>
                        <input type="hidden" id="insuranceCompany2Id" name="insuranceCompany2Id">
                        <input type="button" value="选  择" onclick="selectInsuranceCompany(2)"  style="width: 78px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否多车事故</th>
                    <td width="80%">
                        <select name="accidentType" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.accidentType==1}">selected="selected" </c:if>>单方事故</option>
                            <option value="2" <c:if test="${caseInfo.extend2.accidentType==2}">selected="selected" </c:if>>双方事故</option>
                            <option value="3" <c:if test="${caseInfo.extend2.accidentType==3}">selected="selected" </c:if>>多方事故</option>
                        </select>
                    </td>
                </tr>
                <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=4}">
                    <tr>
                        <th width="20%" class="active">伤者交通状态</th>
                        <td width="80%">
                            <c:if test="${apply.myStatus == 1}">
                                <select name="otherTarfficStatus" class="form-control" style="width: 400px;">
                                    <option value="1" <c:if test="${apply.otherTarfficStatus==1}">selected="selected" </c:if>>机动车</option>
                                    <option value="2" <c:if test="${apply.otherTarfficStatus==2}">selected="selected" </c:if>>非机动车</option>
                                    <%--<option value="3" <c:if test="${apply.otherTarfficStatus==3}">selected="selected" </c:if>>行人</option>--%>
                                </select>
                            </c:if>
                            <c:if test="${apply.myStatus == 2}">
                                <select name="myTarfficStatus" class="form-control" style="width: 400px;">
                                    <option value="1" <c:if test="${apply.myTarfficStatus==1}">selected="selected" </c:if>>机动车</option>
                                    <option value="2" <c:if test="${apply.myTarfficStatus==2}">selected="selected" </c:if>>非机动车</option>
                                    <%--<option value="3" <c:if test="${apply.myTarfficStatus==3}">selected="selected" </c:if>>行人</option>--%>
                                </select>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">肇事方交通状态</th>
                        <td width="80%">
                            <c:if test="${apply.myStatus == 2}">
                                <select name="otherTarfficStatus" class="form-control" style="width: 400px;">
                                    <option value="1" <c:if test="${apply.otherTarfficStatus==1}">selected="selected" </c:if>>机动车</option>
                                    <option value="2" <c:if test="${apply.otherTarfficStatus==2}">selected="selected" </c:if>>非机动车</option>
                                    <%--<option value="3" <c:if test="${apply.otherTarfficStatus==3}">selected="selected" </c:if>>行人</option>--%>
                                </select>
                            </c:if>
                            <c:if test="${apply.myStatus == 1}">
                                <select name="myTarfficStatus" class="form-control" style="width: 400px;">
                                    <option value="1" <c:if test="${apply.myTarfficStatus==1}">selected="selected" </c:if>>机动车</option>
                                    <option value="2" <c:if test="${apply.myTarfficStatus==2}">selected="selected" </c:if>>非机动车</option>
                                    <%--<option value="3" <c:if test="${apply.myTarfficStatus==3}">selected="selected" </c:if>>行人</option>--%>
                                </select>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th width="20%" class="active">委托人事故责任</th>
                    <td width="80%">
                        <select name="ourResponsibilities" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseInfo.extend2.ourResponsibilities==1}">selected="selected" </c:if>>全责</option>
                            <option value="2" <c:if test="${caseInfo.extend2.ourResponsibilities==2}">selected="selected" </c:if>>主责</option>
                            <option value="3" <c:if test="${caseInfo.extend2.ourResponsibilities==3}">selected="selected" </c:if>>同责</option>
                            <option value="4" <c:if test="${caseInfo.extend2.ourResponsibilities==4}">selected="selected" </c:if>>次责</option>
                            <option value="5" <c:if test="${caseInfo.extend2.ourResponsibilities==5}">selected="selected" </c:if>>无责</option>
                            <option value="6" <c:if test="${caseInfo.extend2.ourResponsibilities==6}">selected="selected" </c:if>>责任无法认定</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">肇事方赔偿比例</th>
                    <td width="80%">
                        <input type="number" step="0.01" max="100" min="0" id="responsiblePartyPayRatio" name="responsiblePartyPayRatio" value="${caseInfo.extend2.responsiblePartyPayRatio}" style="width: 390px; display: inline-block;" class="form-control">%
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">肇事方有无免责情形</th>
                    <td width="80%">
                        <select name="disclaimerType" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${claim.caseMediationClaim.disclaimerType==0}">selected="selected" </c:if>>无</option>
                            <option value="1" <c:if test="${claim.caseMediationClaim.disclaimerType==1}">selected="selected" </c:if>>有</option>
                        </select>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    $("#div_city").on("click",function(event,id){
        selectArea1(id);
    });

    $("#div_district").on("click",function(event,did){
        selectAreaCity1(did)
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

    var selectInsuranceCompany = function(type){
        openDialog({
            frame:true,
            title:"公司信息",
            height:500,
            width:800,
            url:"${ctx}/case/center/popup?code=insuranceCompany"+"&type="+type
        });
    }
    var selectInjuredPart = function(caseId){
        openDialog({
            frame:true,
            title:"受伤部位信息",
            height:500,
            width:900,
            url:"${ctx}/case/center/popup?code=injuredPart&id="+caseId
        });
    }
    var selectInvalidismGrade = function(caseId,caseNo){
        openDialog({
            frame:true,
            title:"伤残等级信息",
            height:500,
            width:900,
            url:"${ctx}/case/center/popup?code=invalidismGrade&id="+caseId+"&caseNo="+caseNo
        });
    }
</script>
</body>
</html>