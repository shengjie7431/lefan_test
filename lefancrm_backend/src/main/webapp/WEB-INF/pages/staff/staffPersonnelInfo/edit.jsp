<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>


    </script>
    <style>

        xm-select{
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        }

        .lf-select-block {
            /*padding: 0 8px !important;*/
            /*white-space: nowrap;*/
            /*background-color: #3BA9FF;*/
            color: #555!important;
        }
        .unit{
            position: absolute;
            top: 17px;
            right: 40px;
        }
        .modal-footer{
            position: fixed;
            bottom: 0;
            right: 120px;
            border: none!important;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/staff/update" method="post">
        <input type="hidden" name="surveyCode" id="surveyCode" value="${surveyCode}">
        <input type="hidden" name="id" id="id" value="${staffPersonnelInfo.id}">
        <div class="form-group" style="padding-bottom: 100px">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">姓名<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="text" id = "realName" name="realName" value="${staffPersonnelInfo.realName}" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">手机号<span style="color: red;">*</span></th>
                    <td width="70%">
                        <%--<input type="text" id = "userTel" name="userTel" value="${staffPersonnelInfo.userTel}" onblur="selectByInfo(1001,this)" class="form-control" required="required">--%>
                        <input type="text" id = "userTel" name="userTel" value="${staffPersonnelInfo.userTel}" onblur="checkPapers('phone',this)" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">工号<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="text" id = "jobNo" name="jobNo" value="${staffPersonnelInfo.jobNo}" class="form-control" required="required">
                        <%--<input type="text" id = "jobNo" name="jobNo" value="${staffPersonnelInfo.jobNo}" onblur="selectByInfo(1002,this)" class="form-control" required="required">--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">身份证号<span style="color: red;">*</span></th>
                    <td width="70%">
                        <%--<input type="text" id = "idCard" name="idCard" value="${staffPersonnelInfo.idCard}" onblur="selectByInfo(1003,this)" class="form-control" required="required">--%>
                        <input type="text" id = "idCard" name="idCard" value="${staffPersonnelInfo.idCard}"  class="form-control" required="required">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="30%" class="active">事业部<span style="color: red;">*</span></th>--%>
                    <%--<td width="70%">--%>
                        <%--<select class="form-control" required="required" name="businessUnitId" id="businessUnitId" onchange="changeBase('','businessUnit')">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${businessUnits}" var="item">--%>
                                <%--<option value="${item.id}" <c:if test="${staffPersonnelInfo.businessUnitId == item.id}"> selected="selected" </c:if>>${item.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th width="30%" class="active">社保缴纳公司<span style="color: red;">*</span></th>
                    <td width="70%">
                        <select class="form-control" required="required" name="socialSecurityCompanyId" id="socialSecurityCompanyId" >
                            <option value="">请选择</option>
                            <c:forEach items="${companys}" var="item">
                                <option value="${item.id}" <c:if test="${staffPersonnelInfo.socialSecurityCompanyId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构/部门<span style="color: red;">*</span></th>
                    <td width="70%">
<%--                        <select class="form-control" required="required" name="organId" id="organId">--%>
<%--                            <option value="">请选择</option>--%>
<%--                            <c:forEach items="${organs}" var="item">--%>
<%--                                <option value="${item.organId}" <c:if test="${staffPersonnelInfo.organId == item.organId}"> selected="selected" </c:if>>${item.organName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
                        <div id="organs" class="selectMul"></div>
                        <input type="hidden"  value="${staffPersonnelInfo.organId}" id="organId2">
                        <input type="hidden"  value="${staffPersonnelInfo.organId}" name="organId" id="organId">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">成本归属公司<span style="color: #ff0000;">*</span></th>
                    <td width="70%">
                        <select class="form-control" required="required" name="budgetCompanyId" id="budgetCompanyId" readonly style="pointer-events:none">
                            <option value="">请选择</option>
                            <c:forEach items="${staffBudgetCompanyDtoList}" var="item">
                                <option value="${item.id}" <c:if test="${staffPersonnelInfo.budgetCompanyId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">科室</th>
                    <td width="70%">
                        <div id="departments" class="selectMul"></div>
                        <input type="hidden"  value="${staffPersonnelInfo.departmentId}" id="departmentId2">
                        <input type="hidden"  value="${staffPersonnelInfo.departmentId}" name="departmentId" id="departmentId">
                        <%--<select class="form-control" name="departmentId" id="departmentId">--%>
                            <%--&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:forEach items="${departments}" var="item">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<option value="${item.departmentId}" <c:if test="${staffPersonnelInfo.departmentId == item.departmentId}"> selected="selected" </c:if>>${item.departmentName}</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">小组</th>
                    <td width="70%">
                        <div id="teams" class="selectMul"></div>
                        <input type="hidden"  value="${staffPersonnelInfo.teamId}" id="teamId2">
                        <input type="hidden"  value="${staffPersonnelInfo.teamId}" name="teamId" id="teamId">

                        <%--<select class="form-control" name="teamId" id="teamId">--%>

                            <%--&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:forEach items="${teams}" var="item">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<option value="${item.teamId}" <c:if test="${staffPersonnelInfo.teamId == item.teamId}"> selected="selected" </c:if>>${item.teamName}</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">职务称谓</th>
                    <td width="70%">
                        <div id="staffPostAppellationDtoList" class="selectMul"></div>
                        <select class="form-control" name="postAppellationId" id="postAppellationId">
                            <option value="">请选择</option>
                            <c:forEach items="${staffPostAppellationDtoList}" var="item">
                                <option value="${item.id}" <c:if test="${staffPersonnelInfo.postAppellationId == item.id}"> selected="selected" </c:if>>${item.appellationName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">岗位<span style="color: red;">*</span></th>
                    <td width="70%">
                        <div id="jobPosts" class="selectMul"></div>
                        <input type="hidden"  value="${staffPersonnelInfo.jobPostId}" id="jobPostId2">
                        <input type="hidden"  value="${staffPersonnelInfo.jobPostId}" name="jobPostId" id="jobPostId">
                        <%--<select class="form-control" required="required" name="jobPostId" id="jobPostId">--%>

                            <%--&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:forEach items="${jobPosts}" var="item">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<option value="${item.jobPostId}" <c:if test="${staffPersonnelInfo.jobPostId == item.jobPostId}"> selected="selected" </c:if>>${item.jobPostName}</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--</select>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">家庭住址<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="text" id="homeAddress" name="homeAddress" value="${staffPersonnelInfo.homeAddress}"  class="form-control" required="required">
                        <input type="hidden" id="homeLbsX" name="homeLbsX" >
                        <input type="hidden" id="homeLbsY" name="homeLbsY" >
                        <div id="container" style="display:inline-block;width: 100%;height: 200px;"></div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">岗位职级</th>
                    <td width="70%">
                        <div id="staffPostRankDtoList" class="selectMul"></div>
                        <select class="form-control" name="postRankId" id="postRankId">
                            <option value="">请选择</option>
                            <c:forEach items="${staffPostRankDtoList}" var="item">
                                <option value="${item.id}" <c:if test="${staffPersonnelInfo.postRankId == item.id}"> selected="selected" </c:if>>${item.rankName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="slevel" <c:if test="${staffPersonnelInfo.jobPost !='调查员'}"> style="display: none" </c:if>>
                    <th width="30%" class="active">调查员等级</th>
                    <td width="70%">
                        <select class="form-control" name="surveyLevelId" id="surveyLevelId">
                            <option value="">请选择</option>
                            <c:forEach items="${levels}" var="item">
                                <option value="${item.id}" <c:if test="${staffPersonnelInfo.surveyLevelId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">基本工资</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01" id = "basePay" name="basePay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.basePay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">固定绩效</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01" id = "fixedPerfPay" name="fixedPerfPay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.fixedPerfPay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">考核绩效</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01"  id = "assesPerfPay" name="assesPerfPay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.assesPerfPay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">岗位津贴</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01"  id = "managePerfPay" name="managePerfPay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.managePerfPay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">互助考核绩效（按量）</th>
                    <td width="70%" style="position: relative">
                        <input type="number" min="0" step="0.01" id = "managePerfPaySizeHz" name="managePerfPaySizeHz" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.managePerfPaySizeHz}"  class="form-control"><div class="unit">元/每件</div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">保险考核绩效（按量）</th>
                    <td width="70%" style="position: relative">
                        <input type="number" min="0" step="0.01" id = "managePerfPaySize" name="managePerfPaySize" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.managePerfPaySize}"  class="form-control"><div class="unit">元/每件</div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">驻外补贴</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01"  id = "travelAllowancePay" name="travelAllowancePay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.travelAllowancePay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">养老保险基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.0001"  id = "pensionBase" name="pensionBase" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.pensionBase}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">养老保险公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "pensionCompanyRate" name="pensionCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.pensionCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">养老保险个人比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "pensionPersonalRate" name="pensionPersonalRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.pensionPersonalRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">医疗保险基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.0001"  id = "medicalBase" name="medicalBase" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.medicalBase}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">医疗保险公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "medicalCompanyRate" name="medicalCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.medicalCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">医疗保险个人比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "medicalPersonalRate" name="medicalPersonalRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.medicalPersonalRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">失业保险基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.0001"  id = "upmBase" name="upmBase" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.upmBase}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">失业保险公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "upmCompanyRate" name="upmCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.upmCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">失业保险个人比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "upmPersonalRate" name="upmPersonalRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.upmPersonalRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">工伤保险基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.0001"  id = "isaBase" name="isaBase" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.isaBase}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">工伤保险公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "isaCompanyRate" name="isaCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.isaCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">生育保险基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.0001"  id = "birthBase" name="birthBase" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.birthBase}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">生育保险公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "birthCompanyRate" name="birthCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.birthCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">公积金基数</th>
                    <td width="70%">
                        <input type="number" min="0" step="0.01"  id = "fundPay" name="fundPay" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.fundPay}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">公积金公司比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "fundPayCompanyRate" name="fundPayCompanyRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.fundPayCompanyRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">公积金个人比例</th>
                    <td width="70%">
                        <input type="number" min="0" max="1" step="0.0001"  id = "fundPayPersonalRate" name="fundPayPersonalRate" onblur="checkPapers('proportion',this)" value="${staffPersonnelInfo.fundPayPersonalRate}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">社保公积金缴纳地</th>
                    <td width="70%">
                        <input type="text" id = "payAddress" name="payAddress" value="${staffPersonnelInfo.payAddress}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%">入职时间<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input id="entryTime" name="entryTime" type="text" class="form-control" style="cursor: pointer" required="required"
                               value="<fmt:formatDate value="${staffPersonnelInfo.entryTime}" pattern="yyyy-MM-dd"/>"
                               <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',dchanging:cDayFunc, Mchanging:cMonthFunc, ychanging:cYearFunc, dchanged:cDayFunc, Mchanged:cMonthFunc, ychanged:cYearFunc})" --%>
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickedFunc})"
                               readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%">转正时间</th>
                    <td width="70%">
                        <input id="regularTime" name="regularTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.regularTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%">离职时间</th>
                    <td width="70%">
                        <input id="quitTime" name="quitTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.quitTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">状态<span style="color: red;">*</span></th>
                    <td width="70%">
                        <select name="staffState" class="form-control" required="required" onclick="changeQuitCost(this)">
                            <option value="1" <c:if test="${staffPersonnelInfo.staffState==1}">selected="selected" </c:if>>试用期员工</option>
                            <%--<option value="2" <c:if test="${staffPersonnelInfo.staffState==2}">selected="selected" </c:if>>调整人员</option>--%>
                            <option value="3" <c:if test="${staffPersonnelInfo.staffState==3}">selected="selected" </c:if>>离职（待结算）</option>
                            <option value="4" <c:if test="${staffPersonnelInfo.staffState==4}">selected="selected" </c:if>>转正人员</option>
                            <option value="5" <c:if test="${staffPersonnelInfo.staffState==5}">selected="selected" </c:if>>在职</option>
                            <option value="6" <c:if test="${staffPersonnelInfo.staffState==6}">selected="selected" </c:if>>已离职</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">离职成本</th>
                    <td width="70%">
                        <input type="number" id = "quitCost" name="quitCost" onblur="checkPapers('money',this)" value="${staffPersonnelInfo.quitCost}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">关系<span style="color: red;">*</span></th>
                    <td width="70%">
                        <select name="relation" class="form-control" required="required">
                            <option value="1" <c:if test="${staffPersonnelInfo.relation==1}">selected="selected" </c:if>>全职</option>
                            <option value="2" <c:if test="${staffPersonnelInfo.relation==2}">selected="selected" </c:if>>兼职</option>
                            <option value="3" <c:if test="${staffPersonnelInfo.relation==3}">selected="selected" </c:if>>退休返聘</option>
                            <option value="4" <c:if test="${staffPersonnelInfo.relation==4}">selected="selected" </c:if>>实习生</option>
                            <option value="5" <c:if test="${staffPersonnelInfo.relation==5}">selected="selected" </c:if>>合伙</option>
                            <option value="6" <c:if test="${staffPersonnelInfo.relation==6}">selected="selected" </c:if>>合伙+兼职</option>
                            <option value="7" <c:if test="${staffPersonnelInfo.relation==7}">selected="selected" </c:if>>合伙(发固定绩效)</option>
                        </select>
                    </td>
                </tr>


                <tr>
                    <th width="30%" class="active">分机号</th>
                    <td width="70%">
                        <input type="text" id = "extTel" name="extTel" value="${staffPersonnelInfo.extTel}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">办公地点</th>
                    <td width="70%">
                        <input type="text" id = "officePlace" name="officePlace" value="${staffPersonnelInfo.officePlace}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">备注</th>
                    <td width="70%">
                        <input type="text" id = "remark" name="remark" value="${staffPersonnelInfo.remark}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">试用期</th>
                    <td width="70%">
                        <input type="text" id = "trialTime" name="trialTime" value="${staffPersonnelInfo.trialTime}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">岗位职别</th>
                    <td width="70%">
                        <input type="text" id = "jobLevel" name="jobLevel" value="${staffPersonnelInfo.jobLevel}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">学历</th>
                    <td width="70%">
                        <input type="text" id = "education" name="education" value="${staffPersonnelInfo.education}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">毕业院校</th>
                    <td width="70%">
                        <input type="text" id = "graduationSchool" name="graduationSchool" value="${staffPersonnelInfo.graduationSchool}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">毕业时间</th>
                    <td width="70%">
                        <input id="graduationTime" name="graduationTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.graduationTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">所学专业</th>
                    <td width="70%">
                        <input type="text" id = "major" name="major" value="${staffPersonnelInfo.major}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡号</th>
                    <td width="70%">
                        <input type="text" id = "bankNo" name="bankNo" value="${staffPersonnelInfo.bankNo}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">开户行</th>
                    <td width="70%">
                        <input type="text" id = "bankName" name="bankName" value="${staffPersonnelInfo.bankName}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">合同公司</th>
                    <td width="70%">
                        <input type="text" id = "contractCompany" name="contractCompany" value="${staffPersonnelInfo.contractCompany}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">合同类型</th>
                    <td width="70%">
                        <input type="text" id = "contractType" name="contractType" value="${staffPersonnelInfo.contractType}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">首次合同起始日</th>
                    <td width="70%">
                        <input id="firstContractBeginTime" name="firstContractBeginTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.firstContractBeginTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">首次合同到期日</th>
                    <td width="70%">
                        <input id="firstContractEndTime" name="firstContractEndTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.firstContractEndTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">现合同起始日</th>
                    <td width="70%">
                        <input id="nowContractBeginTime" name="nowContractBeginTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.nowContractBeginTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">现合同到期日</th>
                    <td width="70%">
                        <input id="nowContractEndTime" name="nowContractEndTime" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.nowContractEndTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">合同期限</th>
                    <td width="70%">
                        <input type="text" id = "contractTerm" name="contractTerm" value="${staffPersonnelInfo.contractTerm}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">续约次数</th>
                    <td width="70%">
                        <input type="text" id = "renewNum" name="renewNum" value="${staffPersonnelInfo.renewNum}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">紧急联系人姓名</th>
                    <td width="70%">
                        <input type="text" id = "emergencyContactName" name="emergencyContactName" value="${staffPersonnelInfo.emergencyContactName}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">紧急联系人关系</th>
                    <td width="70%">
                        <input type="text" id = "emergencyContactRelation" name="emergencyContactRelation" value="${staffPersonnelInfo.emergencyContactRelation}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">紧急联系人电话</th>
                    <td width="70%">
                        <input type="text" id = "emergencyContactTel" name="emergencyContactTel" value="${staffPersonnelInfo.emergencyContactTel}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">姓名（家人）</th>
                    <td width="70%">
                        <input type="text" id = "familyName" name="familyName" value="${staffPersonnelInfo.familyName}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">关系（家人）</th>
                    <td width="70%">
                        <input type="text" id = "familyRelation" name="familyRelation" value="${staffPersonnelInfo.familyRelation}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">性别（家人）</th>
                    <td width="70%">
                        <input type="text" id = "familySex" name="familySex" value="${staffPersonnelInfo.familySex}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">生日（家人）</th>
                    <td width="70%">
                        <input id="familyBirthday" name="familyBirthday" type="text" class="form-control" style="cursor: pointer"
                               value="<fmt:formatDate value="${staffPersonnelInfo.familyBirthday}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">电话（家人）</th>
                    <td width="70%">
                        <input type="text" id = "familyTel" name="familyTel" value="${staffPersonnelInfo.familyTel}"  class="form-control" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">身份证姓名</th>
                    <td width="70%">
                        <input type="text" id = "familyIdcardName" name="familyIdcardName" value="${staffPersonnelInfo.familyIdcardName}"  class="form-control" autocomplete="off">
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" style="margin-right: -120px;" name="btnUpload" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>

<input type="hidden" value='${params.departmentsJson}' id="departmentsJson">
<input type="hidden" value='${params.teamsJson}' id="teamsJson">
<input type="hidden" value='${params.jobPostsJson}' id="jobPostsJson">
<input type="hidden" value='${params.organsJson}' id="organsJson">
<input type="hidden" value='${type}' id="oType">


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=1.0&type=webgl&ak=Odbi0Om0OfcFia4cNzKfFoHIX55IhHTe"></script>
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })


    layui.use(['jquery','layer'],function() {
        var $ = layui.jquery,
            layer = layui.layer

        $('#homeAddress').blur(function (e) {
            var text = $(this).val()
            if (text){
                setMap(text)
            }else{
               alert('请输入家庭住址！')
                $('#homeLbsX').val('')
                $('#homeLbsY').val('')
            }
        })
        if ($('#homeAddress').val()){
            setMap($('#homeAddress').val())
        }
        function setMap(text){
            var text = text || ''
            var map = new BMapGL.Map("container");
            map.centerAndZoom(new BMapGL.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
            map.enableScrollWheelZoom(true);
            var myGeo = new BMapGL.Geocoder();
            $.ajax({
                url: 'http://api.map.baidu.com/geocoding/v3/?address='+text+'&output=json&ak=Odbi0Om0OfcFia4cNzKfFoHIX55IhHTe&callback=showLocation',
                dataType: 'jsonp',
                jsonp:'callback',//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
                jsonpCallback:"callback",
                success: function (res) {
                    console.log(res)
                    if (res.status == 0){
                        $('#homeLbsX').val(res.result.location.lng)
                        $('#homeLbsY').val(res.result.location.lat)
                        map.centerAndZoom(res.result.location, 16);
                        map.addOverlay(new BMapGL.Marker(res.result.location, {title: text}))
                    }else{
                        alert('输入地址不正确，请重新输入！')
                        $('#homeLbsX').val('')
                        $('#homeLbsY').val('')
                    }

                }
            })
        }
        function  showLocation(e) {
            console.log(e)
        }
    })

    layui.use('xmSelect',function () {
        var smSelect = layui.xmSelect

        var demo1 = xmSelect.render({
            el: '#departments',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            clickClose: true,
            // size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                if (arr.length){
                    $('#departmentId').val(arr[0].value)
                }else {
                    $('#departmentId').val('')
                }
            },
        })


        var demoOrgan = xmSelect.render({
            el: '#organs',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            clickClose: true,
            // size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                if (arr.length){
                    $('#organId').val(arr[0].value)
                }else {
                    $('#organId').val('')
                }
                if (!arr[0].budgetId){
                    $("#budgetCompanyId").val("");
                }
                $("#budgetCompanyId").val(arr[0].budgetId);
            },
        })

        var demo2 = xmSelect.render({
            el: '#teams',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            // size: 'small',
            toolbar: {
                show: true
            },
            clickClose: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                if (arr.length){
                    $('#teamId').val(arr[0].value)
                }else {
                    $('#teamId').val('')
                }
            },
        })

        var demo3 = xmSelect.render({
            el: '#jobPosts',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            // size: 'small',
            toolbar: {
                show: true
            },
            clickClose: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                if (arr.length){
                    $('#jobPostId').val(arr[0].value)
                    if(arr[0].name == '调查员'){
                        $('.slevel').show()
                    }else{
                        $('.slevel').hide()
                    }
                }else {
                    $('#jobPostId').val('')
//                    $('.slevel').hide()
                }
            },
        })

        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                }
                if (selected) {
                    Object.assign(param, {selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }


        function filterJsonOrg(demo, newJson, id, name,budgetId,budgetName, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var _budgetId = budgetId ? cur[budgetId] : cur.budgetId
                var _budgetName = budgetName ? cur[budgetName] : cur.budgetName
                var param = {
                    name: _name,
                    value: _id,
                    budgetId : _budgetId,
                    budgetName : _budgetName
                }
                if (selected) {
                    Object.assign(param, {selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }

        var departmentsJson = $("#departmentsJson").val();
        departmentsJson = JSON.parse(departmentsJson);
        filterJson(demo1, departmentsJson, 'id', 'name', false, false)

        var organsJson = $("#organsJson").val();
        organsJson = JSON.parse(organsJson);
        filterJsonOrg(demoOrgan, organsJson, 'organId', 'organName','companyId','companyName', false, false)

        var teamsJson = $("#teamsJson").val();
        teamsJson = JSON.parse(teamsJson);
        filterJson(demo2, teamsJson, 'id', 'name', false, false)

        var jobPostsJson = $("#jobPostsJson").val();
        jobPostsJson = JSON.parse(jobPostsJson);
        filterJson(demo3, jobPostsJson, 'id', 'name', false, false)

        demo1.setValue([$('#departmentId2').val()])
        demoOrgan.setValue([$('#organId2').val()])
        demo2.setValue([$('#teamId2').val()])
        demo3.setValue([$('#jobPostId2').val()])

        $('#departmentId').val($('#departmentId2').val())
        $('#organId').val($('#organId2').val())
        $('#teamId').val($('#teamId2').val())
        $('#jobPostId').val($('#jobPostId2').val())

    })
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        var staffState=$("[name='staffState']").val();
        if (!$('#jobPostId').val()){
            alert("请选择岗位!");
            return false;
        }
        if (!$('#budgetCompanyId').val()){
            alert("成本归属公司为空!");
            return false;
        }
        if(staffState == 3 || staffState == 6){
            var quitTime=$("[name='quitTime']").val();
            if(quitTime == null || quitTime == '' || quitTime == undefined){
                alert("请填写离职时间!");
                return false;
            }
            var quitCost=$("[name='quitCost']").val();
            if(quitCost == null || quitCost == '' || quitCost == undefined){
                alert("请填写离职成本!");
                return false;
            }
        }
        //$("#content").text(editor1.html());
        $("[name=btnUpload]").attr("disabled","disabled");
        ajaxFormSubmit(this,updateObj,null,null,null);
        setTimeout(function(){
            $("[name=btnUpload]").removeAttr("disabled");
        },3000)
        event.preventDefault();
        function updateObj(){
            if ($('#oType').val() == 'edit') {
                window.parent.updateObj()
                closeDialog()
            }else {
                reloadParent()
            }

        }
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

    function changeBase(id,surveyCode){
        if(surveyCode == "businessUnit"){
            changeBusinessUnit(id,surveyCode);
        }/*else if(surveyCode == "company"){
            changeCompany(id,surveyCode);
        }*/else if(surveyCode == "staffBudgetCompany"){
            changeBudgetCompany(id,surveyCode);
        }
        /*else if(surveyCode == "department"){
            changeDepartment(id,surveyCode);
        }else if(surveyCode == "team"){
            changeTeam(id,surveyCode);
        }*/
    }

    function changeBusinessUnit(id,surveyCode){
        var businessUnitId = "";
        if(id!=null && id!=""){
            businessUnitId = id;
        }else{
            businessUnitId = $("#businessUnitId").val();
        }
        if(!businessUnitId){
            return;
        }
        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"businessUnitId":businessUnitId,surveyCode:surveyCode,btnCode:1000}
        }).done(
                function(e) {
                    $("#companyId option").remove();
                    $("#companyId").append("<option value=''>请选择公司</option>");
                    for(var i = 0; i < e.results.length; i++){
                        var val = e.results[i];
                        $("#companyId").append("<option value='" + val.companyId + "'>" + val.companyName + "</option>");
                    }
//                    changeOrgan(e.results[0].id,'organ');
                   /* $("#organId option").remove();
                    $("#organId").append("<option value=''>请选择机构</option>");
                    $("#departmentId option").remove();
                    $("#departmentId").append("<option value=''>请选择部门</option>");
                    $("#jobPostId option").remove();
                    $("#jobPostId").append("<option value=''>请选择岗位</option>");*/
                }
        );

    }

    function changeCompany(id,surveyCode){
        var companyId = "";
        if(id!=null && id!=""){
            companyId = id;
        }else{
            companyId = $("#socialSecurityCompanyId").val();
        }
        if(!companyId){
            return;
        }
        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"companyId":companyId,surveyCode:surveyCode,btnCode:1000}
        }).done(
            function(e) {
                $("#organId option").remove();
                $("#organId").append("<option value=''>请选择机构</option>");
                for(var i = 0; i < e.results.length; i++){
                    var val = e.results[i];
                    $("#organId").append("<option value='" + val.organId + "'>" + val.organName + "</option>");
                }
//                changeOrgan(e.results[0].id,'organ');
               /* $("#departmentId option").remove();
                $("#departmentId").append("<option value=''>请选择部门</option>");
                $("#jobPostId option").remove();
                $("#jobPostId").append("<option value=''>请选择岗位</option>");*/
            }
        );

    }

    function changeBudgetCompany(id,surveyCode){
        var budgetCompanyId = "";
        if(id!=null && id!=""){
            budgetCompanyId = id;
        }else{
            budgetCompanyId = $("#budgetCompanyId").val();
        }
        if(!budgetCompanyId){
            return;
        }
        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"budgetCompanyId":budgetCompanyId,surveyCode:surveyCode,btnCode:1000}
        }).done(
            function(e) {
                $("#organId option").remove();
                $("#organId").append("<option value=''>请选择机构</option>");
                for(var i = 0; i < e.results.length; i++){
                    var val = e.results[i];
                    $("#organId").append("<option value='" + val.organId + "'>" + val.organName + "</option>");
                }
//                changeOrgan(e.results[0].id,'organ');
                /* $("#departmentId option").remove();
                 $("#departmentId").append("<option value=''>请选择部门</option>");
                 $("#jobPostId option").remove();
                 $("#jobPostId").append("<option value=''>请选择岗位</option>");*/
            }
        );

    }

    function changeOrgan(id,surveyCode){
        var companyId = $("#companyId").val();
        var organId = "";
        if(id!=null && id!=""){
            organId = id;
        }else{
            organId = $("#organId").val();
        }
        if(!organId){
            return;
        }
        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"companyId":companyId,"organId":organId,surveyCode:surveyCode,btnCode:1000}
        }).done(
            function(e) {
                $("#departmentId option").remove();
                $("#departmentId").append("<option value=''>请选择部门</option>");
                for(var i = 0; i < e.results.length; i++){
                    var val = e.results[i];
                    $("#departmentId").append("<option value='" + val.departmentId + "'>" + val.departmentName + "</option>");
                }
//                changeDepartment(e.results[0].departmentId,'department');
                $("#jobPostId option").remove();
                $("#jobPostId").append("<option value=''>请选择岗位</option>");
            }
        );
    }

    function changeDepartment(id,surveyCode){
        var companyId = $("#companyId").val();
        var organId = $("#organId").val();
        var departmentId = "";
        if(id!=null && id!=""){
            departmentId = id;
        }else{
            departmentId = $("#departmentId").val();
        }
        if(!departmentId){return;}

        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"companyId":companyId,"organId":organId,"departmentId":departmentId,surveyCode:surveyCode,btnCode:1000}
        }).done(
            function(e) {
                $("#teamId option").remove();
                $("#teamId").append("<option value=''>请选择小组</option>");
                for(var i = 0; i < e.results.length; i++){
                    var val = e.results[i];
                    $("#teamId").append("<option value='" + val.teamId + "'>" + val.teamName + "</option>");
                }
            }
        );
    }

    function changeTeam(id,surveyCode){
        var companyId = $("#companyId").val();
        var organId = $("#organId").val();
        var departmentId = $("#departmentId").val();
        var teamId = "";
        if(id!=null && id!=""){
            teamId = id;
        }else{
            teamId = $("#teamId").val();
        }
        if(!teamId){return;}

        $.ajax({
            url:"${ctx}/staff/selectStaffInfoByRelationId",
            data:{"companyId":companyId,"organId":organId,"departmentId":departmentId,"teamId":teamId,surveyCode:surveyCode,btnCode:1000}
        }).done(
                function(e) {
                    $("#jobPostId option").remove();
                    $("#jobPostId").append("<option value=''>请选择岗位</option>");
                    for(var i = 0; i < e.results.length; i++){
                        var val = e.results[i];
                        $("#jobPostId").append("<option value='" + val.jobPostId + "'>" + val.jobPostName + "</option>");
                    }
                }
        );
    }

    //判断手机号、身份证、工号不可重复
    function selectByInfo(btnCode,obj){
        var result = obj.value;
        //验证身份证号
        if(result ==null || result==""){
            return;
        }
        if(btnCode ==1003){
            if(!checkPapers('identity', obj.value)){
                return;
            }
        }
        ajaxSubmit("${ctx}/staff/selectStaffInfoByRelationId",{"surveyCode":"personnelInfo","btnCode":btnCode,"info":result},function(v,e,p){
            if(e.data.results != null){
                var message = "";
                if(btnCode ==1001){
                    message ="手机号";
                }else if(btnCode ==1002){
                    message ="工号";
                }else if(btnCode ==1003){
                    message ="身份证号码";
                }
                alert(message+"不可重复！");
                $("[name=btnUpload]").attr("disabled","disabled");
                return;
            }else{
                $("[name=btnUpload]").removeAttr("disabled");
            }
        });
    }


    //验证身份证号
    /*var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['moneyorMinus', /^(\-|\+)?[0-9]+(.[0-9]{1,2})?$/]
        ])
        if (map.get(parama).test(paramb)) {
            return true;
        } else {
            alert("身份证格式错误！");
            return false;
        }
    }*/

    function checkPapers(parama,obj){
        var result = obj.value;
        if(result ==null || result==""){
            return;
        }
        if(parama=='proportion'){
            if(result>1){
                alert("比例不可大于1");
            }
        }
        check(parama, result);
    }

    var check = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,4,5,6,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(.[0-9]{1,2})?$/],
            ['proportion', /^[0-1]+(.[0-9]{1,4})?$/],
            ['moneyorMinus', /^(\-|\+)?[0-9]+(.[0-9]{1,2})?$/],
            ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
        ])
        if (map.get(parama).test(paramb)) {
            return true;
        } else {
            alert("格式错误！");
            return false;
        }
    }

    function cDayFunc(){
        cFunc('d');
    }
    function cMonthFunc(){
        cFunc('M');
    }
    function cYearFunc(){
        cFunc('y');
    }
    function cFunc(who){
        var str,p,c = $dp.cal;
        if(who=='y'){
            str='年份';
            p='y';
        }
        else if(who=='M'){
            str='月份';
            p='M';
        }
        else if(who=='d'){
            str='日期';
            p='d';
        }
        console.log(str+'发生改变了!\n$dp.cal.date.'+p+'='+c.date[p]+'\n$dp.cal.newdate.'+p+'='+c.newdate[p]);
    }
    function pickedFunc() {
        var newDate = $dp.cal.getNewDateStr()
        var _date = new Date(addSix(newDate))
        var regularTime = _date.getTime() - 24 * 60 * 60 * 1000
        var regularDate = dateFormat(regularTime, 'yyyy-MM-dd')
        $('#regularTime').val(regularDate)
    }
    function addSix(_date) {
        var date = new Date(_date);
        var str = ''
        var year = date.getFullYear(); //年
        var month = date.getMonth() + 7; //月 +6个月  因为js里month从0开始，所以要加1
        if (month > 12) {
            year++;
            month -= 12;
        }
        if (month < 10) {
            month = "0" + month;
        }
        var date2 = new Date(year, month, 0); //新的年月
        var day1 = date.getDate();
        var day2 = date2.getDate();
        if (day1 > day2) { //防止+6月后没有31天
            day1 = day2;
        }
        str = year + '-' +
            PrefixInteger(month,2) + '-' +
            PrefixInteger(day1,2);
        return str

    }
    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }
    function dateFormat(time, format) {
        var t = new Date(time);
        var format = format || 'yyyy-MM-dd'
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    };

    //    function changeQuitCost(obj){
//        var result = obj.value;
//        if(result == 3 || result ==6){
//            $("#quitCost").attr("required", "required")
//        }else{
//            $("#quitCost").removeAttr("required")
//        }
//    }
</script>
</body>
</html>