<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script src="${ctx}/js/progress.js"></script>
    <style>
        .list-group-item{
            width: 100%;
        }

        .pro {
            width: 500px;
            margin: 10px auto;

        }

        #bar-warp{
            width:500px;
            height:30px;
            border:1px solid green;
        }
        #bar{
            width:0px;
            height:30px;
            background:green;
        }

        .file-add {
            width: 148px;
            height: 148px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 74px;
            margin-top: 34px;
            display: inline-block;
            background: #aaa;
            height: 80px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 80px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }

        .icon-delete{
            position: absolute;
            top: 1px;
            right: 1px;
            width: 17px;
            height: 17px;
            background: red;
            border-radius: 50%;
        }

        .del-file{
            /*float: right;*/
            /*display: block;*/
            /*position: relative;*/
            /*top: -120px;*/
            /*left: 0;*/
            /*cursor: pointer;*/
            /*background-color: red;*/
            /*color: white;*/
            /*width: 14px;*/



            position: absolute;
            top: 2px;
            right: 2px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAQG0lEQVR4Xu2dCZAc1XnH//+eEVgHOOAysRSMjQEhEQiWhTHY2BYCabdHCO3MeqUCmThUUiQYUrYDxgQSLAJ24fsom4SQckHKkgXr7ZZW2u7VIkWLIKJCkDG3YuP4PrAJhyNWkTQ7X+r17C57zfQxfc1sd5VKKs173/mb1z393vse0YKXrF55Mo7SFqOiLQJkEYBTQBwLwRyQs52/of6NYxz3Bf8LYAjEECBDAA5C8HsQz0NkP4T7AXmOW/p/0mrhYrM7JKtXz0F+eDmAdgAXgDg7Op/k/xwYKA+B0g/t1d3sfuRgdPqil9yUAEjnykWoaDpAlfQPgjw6+lBNq+EgRAZB2ChXbPbueD4hOwKrbRoApHPFaRjOXwFiLcjTA3scaUd5BsB9qGATt9g/ilRVSMJTDYB06KdAw+UQrgVxZkg+xyRGHofgPhw58h1u3/mzmJT6VpNKAKSknwfBrSBX+vYolR2kF8QG9tiPp828VAEgpVUXAvL3AC5MW6DCsUcslPlp9lqPhSOvcSmpAEA62pdB4+0A39e4S00hYSdQuYlG/38mbW2iAEhX4S0o42vOg91Mu0QE5LdQGbqeWwZfScr9RAAQQEOxcC0gt4E8NinnU6FX8CJQuZ5m/71J2BM7ANKpL4HgHoB/koTDKda5F4IraVo/iNPG2ACQZcvyOG7ObSBugBoBsmu6CByGyM0w7S9RvaCO4YoFAOfd/KxcD8AlMfjUCir2oHxoLXt3vRC1M5EDIB3tV0HjlwHOjdqZlpIvUA+GV9C0tkfpV2QASNf5szH8B5sAdkTpQMvLFnyTpnVtVH5GAoB0LZuH8pxdIM6NyvAZJVfERn5eB7u7D4ftd+gASJf+ZpSxC+RZYRs7o+UJHkI5185t29R6hdCuUAGQkn4ilKHk20OzMBP0egQE+5A/fBG7d74aVlhCA0AubTsV+dwggD8Ky7hMzjQREDyNvCxnt/27MOITCgBSapsP0R4DuSAMozIZLhFwIBg6n92DBxqNVcMASFfb8SjnHgGxsFFjsv4+IiDyMH722+Xct++Ij15TmjYEgPNTr3ycSn6E6/Aaca/V+0ovDLtIoBLU08AAyNKls3DSCQMglwVVnvULIQKCb9O0rggqKTgARf3bINcHVZz1CzECIn9H0/5MEImBAJBi+0dA7Z4gCrM+kUSgAsgHaNj/7le6bwCchZrkk6CzuSK70hOB3yB3eJHfdwS+AKhO6c5+AuQZ6fE7s+T1CIhFw17lJyL+ACjpdwK82o+CrG3MERB8jKb1da9aPQMgxUK7swMmu9IfAZYXs2dgvxdDPQEgXV1HYfjAjwCe6EVo1ibxCOylYXlaYe0NgKL+GZA3Je5WZoD3CIh8mKa90a2DKwBSLCwE5BmQeTdh2ecpioBabZwfOtltvsADAPpekOenyLXMFM8RkG/QsP+6XvO6AEhJXwdws2d9WcP0RUB4Fs2+p2sZVhMAAYiS/gOAp6bPq8wizxEQGDStTv8AZN9+zzFOdUO1Ba2CM7nVfnY6O2uPAEVdPfhlb/xSnV2Pxolspmlf5hkAKbaXQK3Ho/gYmslrED4DyukA3xiDwpBUiNoG/pZUvD8RnD7dtrNpRwApFr6fmkUeghtoWl8YzYgU9fUgvplyEO5COf9J9vaq6mOQYvsFADeBfGtIZPkXI3IvTfvPJnecAoCsaXs/crk9/jVE0UP+mYb9l5Mlj2ww3Z1KCERupGl/borNicdVjqBy8ITJW9GnAlDS7wJ4VRTp9C2zwnO4pW/fdP1SCUGN5I+NXqXCTwGc5DsOYXUQXE3T+qfx4qYCUCy8BOK4sHQ2JCeH+ey2flNLhqwpvAc52ZWSfYcbaFi31vNXioX/SHS3lMjDNO331wRAOvQOaDQbSlqYnUUuomn/W92gViHYCXBemKp9yrqdhqVqG9W9pKi/moKCGG+nYamRyLkmjABSLPSo1z9ujsT4uadZLeloOxeapkaC+CEQ3EHT+lu3mEixcA2Ib7i1i/5zuZmG/dkpAMjKlXMxL/cywFnRG+FHg/v7bCXNgYC53fEuVZOv0rA/4eaNFNvPBxWgmO3WNvrP5Xka9mlTAegoXAYNm6I3IIgGjxCU9PdBOBAPBM2Y/NFx/8hC9jzwwwm3ACkV/gXAnwdJTzx90gRBEye/mqy/omHdNRmAZH+ieKLIBwSAejB8gyexvhp5Tb6uilirJXQpGPYnOSjopmk5pfmch8BqTV42SaVrrxCoqqMVK1wI5B9p2B9140WK+gcB7EiwirmLifISDftN4wBQdXw0Z0hojisJCFol+SMZluF30tzxRHUEKBbuB9HVHMkfdUDupmm7vrEcqT/cD+Co4P61WPKdpOM6mtaXRwDQf9yUVT3EIwSd7W0QrTcQBCLfomm7Phynf9if8hzwHZrW5awu+X7tUPBvR8I9o4SgVZPvjADyPZr2Ukpx1ZmgPJVwGhtT7weCCrd7WuHcysmvAnCIpv0GSkehExq+21gGUtDbKwRr9NXQYNSFQGQjTfvDbl413bA/xaHhBZSSfhPAQHvL3QIU++dhQCCyEaZ9hVut3uZPvjMKLKMU9XtB/mnsyYpKoR8Ics7MZ27MlJmU/Opt4Co1Ajzceid1ePzZVtI/BLDbAcDrsF9quxjIPRAVv/HKlc8rAJ5oydr9XkcCBwJ8CIZ9uVuxJXGSr6m3iymbMQ2IjXrQlVJBHVAwNj0YUFQ6u3mEwIvxLZf86rC3RY0AP0/FsmUvWQjSJgQIWjP5TjD3qIfAF0E6EwMtezUAQQsnX70OflrdAlT16fRNWYZNYwAIWjr51QffXykAYjmbJux8BpIn8q807Y946dvyya8G4aACQM0DNDBT5iWcKWmTATA5EYdnxjNAdbjzNH08PkLOKCCamjtI6nj6aL85ghcVAM05FewnNAGSPyq+JV751o7VjxUAT7b08S4NJL/lIRA8oZ4BVH3Z9/r5QjVN2xCS39IQqK1iUtL7AbY1TVK9Guox+TL6Kjg3bz27u4friW+924FYCoDNANd5jWtTtBP5Gk374262VpM/OhmE+2larnGorjEMe7Wxm6URfa4mwKTlikB6XDGsFoZMng6GfBeGvdZ9LUCatno1AIfIP7C1av/7SH6tVUGe1wS0BATrFQBq4+LeBjhKSdcQkj/2xOd1VVCTQ6AKcEjxojeBR7+YkiwGM0PkizTtT7p1Fi/rAWcSBAfK80b3BaShcIFb/qb/PIrkB4KAA4nUJwgWNfVm9Nc07QVVAEr6IwDPCyorsX5RJn8cBJ5WCF9aOAd5UYWr4i9SESQBIoM07QtHAfg6wLpFhYPoiLSP1+SrXUFe9wLUMtjrHoFmgkDwBZrWDaO7g9NVG8iNHD/JD7olbLINrQYBK+3s6d9RBcApD5P/PQDNLfYp+Ny1GpfjUyP7AVt9JBApIz9vLru7D48ViZKS/ijAd6cgwXVMkOtp2F9yszGS5L/+TOBts6i6HagSduSxbvbG/rnIgzRt58TX1wEo6neA/FTsxnhWmILk+4Wg2HY2oO1JHwRyCw37tkkAFFaAGPCcj1gbpij5Y3573HySRgik8l6a/Y9MBMA5FHKOqhJ6TKy5dVXmMfnOJI00WAjC1ZhJDZoQApHfwrTnj26CmVwo8m4Qf+E3DJG1H1fMqJ6OZGfovELQ3gVq90cWK6+CBV+haf3NaPOJAJQKHwDwoFdZkbcTnkGz77n6yXe2a20LtxiUX888zkMU9edBnuJXeqjtKe9ij/34tACo/0zTTiEaVv1DrTraL4Km7Qw1QIGFuUMgxcI2EJcEVtF4x/00rMXjxUxXLv5zAG9oXFcIEg5rJ3L79l9OJynZYb+Wb/VrCEqx8BSIM0OITEARE+sET3gIHJUoawpnIYcnA2oIt5tasGDan54sNJ3JH4vgtPWDRf0aYO774QbIp7RpvlA1jozRHwJ5gU/xETWvXEOj/86x8JbaPwpoX0z3djbZhEPatezre9m5rTpzBE4d5uR2YQu20rQ6JidpegBSdW91atqps3fUw+Di9P1MrcO9yPdGNt6+LaJvh3exkx7+aj4Ejn3Tivo+kO/yriFrmd4IiEXDXjWdfbXPDexsL0C0vvQ6lVnmOQIVLOEWa9rnj/o/s7JRwHOMU9tQZICmXXPfh8vh0W2rgNz21DqXGeYegcrwe7hlx6O1Gno5Pt4CqbtrylqkMAL30LCurGeXOwAl/UQI1CvM1twincKshWKS4BXkh09h946XGgLA+R1b1G8GeXsohmVC4omAKgJp2ne7KXMdARwA1FTx8XP+C8A73ARmn6cgAoJ9NK1zvFjiCQAHgup8e91DHL0ozNrEEAFWzmZPv6fX+Z4BqEKgfwWg667bGFzMVNSMgHyKhv15rwHyB8DSpbNw0h8+CuKdXhVk7WKNwG4a1nI/Gn0BUB0FCm+D4Nl4Dmf048oMbyt4AfnhM9ye+idHyTcAI7eCdQA3z/CQp8d9EUFFu4Bb+3zv8g4EwMhIkPKTRtOTnxgsuZWGtSGInuAAdHXlUD4wANLXPSeIkVmfOhEQOKd/BY1RYACcUWD16jmYVVYHTiwJakDWr4EIiAzAtHW3cw7qaWgIAAeCrrbjUdb2gjy9AVeyrv4jsBcHZTltu6Ej/xoGoPo80DYfoj0GcoF/P7Ie/iMgzyB38Dx2Dx7w33dij1AAcCAoFhYCMghyfqNGZf3r3vOfxvChi9m764Uw4hQaAA4Ea1a+Fbm82lhychjGZTImR0AeR+7Iheze+WpYsQkVgOozgf5mlKFGgjPCMjKT40RgN47kLuG2beqAj9Cu0AGoQnDxG1E+aheIpaFZOpMFCbbj5aEiBwfLYYchEgAcCFTVkbn5zQlvhQo7XknIu5OGdU1UiiMDYNRg6dCvhkZV1aP1zyUKM0si/wNN1qs6PmGKnSwrcgCc0eDStlOR09SBzWdF6UzLyBbZhTwuY7f9u6h9igUABwJnKvmEzwK4DmRseqMOYKjyRQ6BuJGG/dVQ5dYRFnsipNT+bgjvyX4lTMnKHoBX0uj777iSr/TEDkD1V0JXDsMH1MqiWwHOjdPh1OlS8/iQ62jaG5OwLREAxh4QL12xALlZd4JYk4TzCeusAHIXDuJG2raq0ZjIlSgAYyA45Vy1O2bOUjPpBbFhfKmWRLKf1C2glrMjBR5vaclDrNSqHWIrhnkLt1pPJZXwRH4G+nVWqvUJFAiqaFWzXxUIelCRDdxqP5s2Z1JxC6g9IrT9MYa1dSDXgViYtuDVtUegNmTeh+Ejm9n7wK/SanuqARgfNOnUl6ACBcPa1M42qoogwP0gNtKwf5HWpI+3q2kAmACDU8hKdAgKoDr0krOSCbYcgHAXgH5Uyn3cOvDzZOwIrrUpAZgAg64fi6O5Ahougci50b5gktdGahU9CFT6aexISY3CGQzAdK5L54rTUM4vQg6LIVhUvWXwGEDUhNSc6h/OHis4VS1CNQRiCBA13z4E4SsgfgjIfqDyHKDtb5Zh3Q8O/w83dkMy4BMWvQAAAABJRU5ErkJggg==);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            cursor: pointer;
        }


    </style>
</head>
<body>
<div class="main">
    <div class="row">
        <div class="panel panel-info col-sm-2">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul class="list-group">
                            <input type="button" class="list-group-item" onclick="onBack('${surveyInfoId}',null,'')" value="全部目录(${surveyCaseFiles.size()})">
                            <c:forEach items="${fileCatalogs}" var="e">
                                <c:if test="${e.size > 0}">
                                    <input type="button" class="list-group-item" onclick="onBack('${surveyInfoId}','${e.id}','${e.catalogName}')" value="${e.catalogName}(${e.size.intValue()})">
                                </c:if>
                                <c:if test="${e.size == 0}">
                                    <input type="button" class="list-group-item" onclick="onBack('${surveyInfoId}','${e.id}','${e.catalogName}')" value="${e.catalogName}">
                                </c:if>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <form id="uploadForm" method="post" action="${ctx}/survey/case/fileMidOK">
            <input type="hidden" name="files" id="files" value="" />
        </form>
        <form method="post" action = "${ctx}/survey/download" id="editFrom">
            <div class="col-sm-10 " id="result">
                <div class="row panel panel-info" id="uploadDiv" style="display: none">
                    <div>
                        <input type="hidden" id="surveyId" value="${surveyId}">
                        <input type="hidden" id="surveyInfoId" value="${surveyInfoId}">
                        <input type="hidden" id="catalogId" value="${catalogId}">
                        <input type="hidden" id="fileNum" value="">
                    </div>
                    <div style="display: none;">
                        <input type="hidden" id="img" name="img" value="" />
                        <div id="materialImgsDiv"></div><br/>
                        <%--<div id="deleteDiv" style="display: none">--%>
                        <%--<input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>--%>
                        <%--</div>--%>
                    </div>
                    <div id="div_pro" style="display: flex;flex-wrap: wrap;justify-content: space-between;width: 96%;padding: 10px 2%;    max-height: 300px;    overflow: auto;    margin-bottom: 20px;">

                        <%--<div class="pro"></div>--%>

                    </div>
                    <div>
                        <div class="file-add" onclick="$('#materialFileupload').click();">
                            <div class="icon-add"></div>
                        </div>
                        <%--<input type="file" name="file" id="file" onchange="upPic()" multiple>--%>
                        <input style="display: none;" id="materialFileupload" type="file" name="file" onclick="clearParams()" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=${surveyCno}"><br>
                    </div>
                    <div style="width: 90%;" id="div_file_show"></div>
                    <div style="display: none;" class="col-sm-1 col-sm-offset-9" id="uploadButton"><button id="btnUpload" type="button" onclick="uploadImg()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认上传</button></div>
                    <%--<div>--%>
                    <%--<div id="bar-warp">--%>
                    <%--<div id="bar"></div>--%>
                    <%--<span id="precent"></span><br/>--%>
                    <%--<div id="succ"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="row panel panel-info">
                    <div class="col-sm-12 row" id="f" style="margin:5px 0;display: flex;flex-wrap: wrap;">
                        <c:forEach items="${surveyCaseFiles}" var="file" varStatus="st">
                            <div class="col-sm-3">
                                <c:if test="${file.fileType == 1}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/word.png" />
                                    <br/><a href="${file.commonFile.filePath}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                        <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                <c:if test="${file.fileType == 2}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${file.commonFile.filePath}" />
                                    <br/><a href="${ctx}/survey/case/downloadImg?filePath=${file.commonFile.filePath}&fileName=${file.commonFile.fileName}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                    <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                <c:if test="${file.fileType == 3}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/rar.jpg" /></a>
                                    <br/><a href="${file.commonFile.filePath}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                    <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                <c:if test="${file.fileType == 4}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/excel.png" />
                                    <br/><a href="${file.commonFile.filePath}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                    <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                <c:if test="${file.fileType == 5}"><a href="${file.commonFile.filePath}" target="_blank"><img width="100" height="100" src="${ctx}/img/pdf.jpg" /></a>
                                    <br/><a href="${file.commonFile.filePath}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                    <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                <c:if test="${file.fileType == 6}"><img style="cursor: pointer" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')" src="${ctx}/img/txt.png" />
                                    <br/><a href="${file.commonFile.filePath}"><span style="width: 100%;max-width: 100%;word-wrap:break-word">${file.commonFile.fileName}</span></a><br>
                                        <span style="color: #75bff9">由${file.createBy}于${file.upLoadTime}上传</span>
                                    <span class="del-file" data-file-id="${file.id}"></span>
                                </c:if>
                                    <%--<img id="infImg" width="100" height="100" onclick="openImgs(${st.index},${file.fileType},'${file.commonFile.filePath}')"--%>
                                    <%--<c:if test="${file.fileType == 1}">src="${ctx}/img/word.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 3}">src="${ctx}/img/rar.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 4}">src="${ctx}/img/excel.png"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 5}">src="${ctx}/img/pdf.jpg"</c:if>--%>
                                    <%--<c:if test="${file.fileType == 2}">src="${file.commonFile.filePath}"</c:if>--%>
                                    <%--/>--%>
                                <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <%--<input type="checkbox" name="files" value="${file.commonFile.filePath}">--%>
                            </div>
                        </c:forEach>
                    </div>
                    <%--FTP导出，需要压缩打包，暂未实现--%>
                    <%--<c:if test="${surveyCaseFiles.size() > 0 && surveyCaseFiles != null}">--%>
                    <%--<div class="row">--%>
                    <%--<div class="col-sm-1 col-sm-offset-9" id="btnSubmit"><button type="submit"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 下载单证</button></div>--%>
                    <%--</div>--%>
                    <%--</c:if>--%>
                </div>
                <div style="display: none" id="div_files_paths">
                    <c:forEach items="${surveyCaseFiles}" var="file" varStatus="st">
                        <span data-file-path="${file.commonFile.filePath}"></span>
                    </c:forEach>
                </div>
                <input type="button" class="btn-primary" value="一键下载" onclick="allDown()" />
            </div>
        </form>
    </div>
</div>
</div><!--main end-->
</div>

<%--<a id="temp-a" href="http://localhost:8081/backend/survey/case/downloadImg?filePath=http://119.3.48.62:6077/sftp/files//test//ddr/cno/cwt681567765311809/material/2.jpg&fileName=2.jpg" target="_blank">下载</a>--%>

<div id="dialogId"></div>


</body>

<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>


<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    var onlinePreview = function(path){
        var last = path.lastIndexOf(".");
        var ext = path.substr(last + 1);
        if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx' || ext == 'pdf'){
            path = "https://view.officeapps.live.com/op/view.aspx?src=" + path;
        }
        openDialog({
            frame:true,
            title:"预览",
            height:600,
            width:800,
            url:path
        });
    }

    var upPic = function(){
//        var pic=document.getElementsByTagName('input')[0].files[0];
        var files = document.getElementById("file").files;
        var size = 0;
        var html = "";
        for(var i = 0 ; i < files.length ; i ++){
            var f = files[i];
            console.log(f);
            size += f.size;
//            html += "<div>" + f.name + "(" + (f.size/1024/1024).toFixed(1) +"M)<span style='color:green;' id='fileProcess_'" + i + "></span></div>"
            html += "<div id='show_div_'"+ i +">" + f.name + "(" + (f.size/1024/1024).toFixed(1) +"M)<span id='span'_" + i + ">sdfsdfs</span></div>"
        }
        alert($("#span_0").text);
        $("#div_file_show").html(html);
        var size = size / 1024 + 5;
        var secSize = 2;
        for(var i = 0 ; i < files.length ; i ++){
            var file = document.getElementById("file").files[i];
            var fd=new FormData();
            var xhr=new XMLHttpRequest();
            xhr.open('post','${ctx}/sftp/survey/uploadSftp?modelType=material&surveyCno=${surveyCno}',true);
            xhr.onreadystatechange=function (){
                //readystate为4表示请求已完成并就绪
                if(this.readyState==4){
//                    document.getElementById('precent').innerHTML=this.responseText;
                    //在进度条下方插入百分比
                    $("#show_div_" + i).html("上传完成");
                }
            }

            xhr.upload.onprogress=function (ev){
                console.log(ev.loaded,ev.total);
                //如果ev.lengthComputable为true就可以开始计算上传进度
                //上传进度 = 100* ev.loaded/ev.total
                if(ev.lengthComputable){
                    var precent=100 * ev.loaded/ev.total;
                    if(precent > 95){
                        $("#show_div_" + i).html("95%");
                    }else{
                        $("#show_div_" + i).html(precent + "%");
                    }
//                    console.log(precent);
                    //更改进度条，及百分比
//                    document.getElementById('bar').style.width=precent+'%';
//                    document.getElementById('precent').innerHTML=Math.floor(precent)+'%';
                }
            }
            fd.append('file',file);
            xhr.send(fd);
        }
    }


    function onBack(surveyInfoId,catalogId){
        if(files.length > 0){
            alert("附件上传未确认，请确认");return;
        }
        ajaxSubmit("${ctx}/survey/case/fileMid",{"surveyInfoId":surveyInfoId,"catalogId":catalogId==null?null:Number(catalogId),"viewType":"treeClick"},function(v,e,p){
            if(e.data.code=='0000'){
                var files = e.data.results.surveyCaseFiles;
                var vl = "";
                var htmlFiles = "";
                for(var i = 0; i < files.length; i++){
                    var json = files[i];
                    vl += "<div class='col-sm-3'>";
                    var ext = json.fileType;
                    if(ext == 1){
                        vl += "<img src='${ctx}/img/word.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"
                            +json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'>删除</span>"
                    }else if(ext == 2){
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }else if(ext == 3){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/rar.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }else if(ext == 4){
                        vl += "<img src='${ctx}/img/excel.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }else if(ext == 5){
                        vl += "<a href='" + json.filePath + "' target='_blank'><img width='100' height='100' src='${ctx}/img/pdf.jpg'/></a><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }else if(ext == 6) {
                        vl += "<img src='${ctx}/img/txt.png' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }else{//都按照图片处理
                        vl += "<img src='" + json.filePath + "' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/><br/><span style='width: 100%;max-width: 100%;word-wrap:break-word'>"+json.commonFile.fileName+"</span><br><span style='color: #75bff9'>由"+json.createBy+"于"+json.upLoadTime+"上传</span><span class='del-file' data-file-id='"+json.id+"'></span>"
                    }
//                    vl += "<img src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+","+json.fileType+",\""+json.filePath+"\")'/>";
//                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='files' value='"+json.filePath+"'>";
                    vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    vl += " </div>";

                    htmlFiles += "<span data-file-path='"+json.filePath+"'></span>"
                }
                $("#catalogId").val(catalogId);
                $("#materialImgsDiv").html("");
                $("#f").html(vl);

                $("#div_files_paths").html(htmlFiles);

                //不是具体分类时，上传div隐藏
                if(catalogId ==null){
                    $("#uploadDiv").hide();
                }else{
                    $("#uploadDiv").show();
                }
            }else{
            }

        })
    }

    function sleep(d){
        for(var t = Date.now() ; Date.now() - t <= d;);
    }
    function download1(src){
        var $a = document.createElement("a");
        $a.setAttribute("href",src);
        $a.setAttribute("download","");
        var evObj = document.createElement("MouseEvents");
        evObj.initMouseEvent('click',true,false,window,0,0,0,0,0,false,false,false,false,0,null);
        $a.dispatchEvent(evObj);
    }

    function download(name, href) {
        var a = document.createElement("a"), //创建a标签
            e = document.createEvent("MouseEvents"); //创建鼠标事件对象
        e.initEvent("click", false, false); //初始化事件对象
        a.href = href; //设置下载地址
        a.download = name; //设置下载文件名
        a.dispatchEvent(e); //给指定的元素，执行事件click事件
    }


    var downloadIamge = function(imgsrc, name) {//下载图片地址和图片名
        var image = new Image();
        // 解决跨域 Canvas 污染问题
        image.setAttribute("crossOrigin", "anonymous");
        image.onload = function() {
            var canvas = document.createElement("canvas");
            canvas.width = image.width;
            canvas.height = image.height;
            var context = canvas.getContext("2d");
            context.drawImage(image, 0, 0, image.width, image.height);
            var url = canvas.toDataURL("image/png"); //得到图片的base64编码数据
            var a = document.createElement("a"); // 生成一个a元素
            var event = new MouseEvent("click"); // 创建一个单击事件
            a.download = name || "photo"; // 设置图片名称
            a.href = url; // 将生成的URL设置为a.href属性
            a.dispatchEvent(event); // 触发a的单击事件
        };
        image.src = imgsrc;
    }

    function allDown(){
        var spans = $("#div_files_paths").children("span");
        if (spans) {
            var caseFiles = []
            spans.map(function(){
                var _this = $(this);
                var path = _this.attr("data-file-path");
                caseFiles.push(path);

                // downloadIamge(path,"1.jpg");

                // sleep(500);
                // download(path);


                // aLink.href = path;
                // aLink.setAttribute("target","_blank");
                // aLink.click();

                // $("#temp-a").attr("href",path);
                // document.getElementById("temp-a").click();
                // $("#temp-a")[0].click();

                // var frame = $('<iframe style="display: none;" class="multi-download"></iframe>');
                // frame.attr('src', path);
                // $(document.body).append(frame);
                // setTimeout(function() {
                //   frame.remove();
                // }, 1000);

                // var aLink = document.createElement('a');
                // var evt = document.createEvent('MouseEvents');
                // // var evt = document.createEvent("HTMLEvents")
                // evt.initEvent('click', true, false);
                // // evt.initEvent("click", false, false)//initEvent 不加后两个参数在FF下会报错, 感谢 Barret Lee 的反馈
                // aLink.download = "111.jpg";
                // aLink.href = path;
                // aLink.target = "_blank";
                // aLink.dispatchEvent(evt)

                //  var iframe = document.createElement("iframe");
                // iframe.style.display = "none"; // 防止影响页面
                // iframe.style.height = 0; // 防止影响页面
                // iframe.src = path;
                // document.body.appendChild(iframe); // 这一行必须，iframe挂在到dom树上才会发请求
                // // 5分钟之后删除（onload方法对于下载链接不起作用，就先抠脚一下吧）
                // setTimeout(function(){
                //     iframe.remove();
                // }, 5 * 60 * 1000);
            })

            $.ajax({
                url: "${ctx}/sftp/survey/downSftp",
                data: {
                    uploadType:'caseFiles',
                    files : JSON.stringify(caseFiles)
                },
                success: function(e,res) {
                    var data = res.data;
                    if (data.files){
                        var files = data.files;
                        for (var i = 0 ; i < files.length ; i ++){
                             var iframe = document.createElement("iframe");
                            iframe.style.display = "none"; // 防止影响页面
                            iframe.style.height = 0; // 防止影响页面
                            iframe.src = files[i];
                            document.body.appendChild(iframe); // 这一行必须，iframe挂在到dom树上才会发请求
                            // 5分钟之后删除（onload方法对于下载链接不起作用，就先抠脚一下吧）
                            setTimeout(function(){
                                iframe.remove();
                            }, 5 * 60 * 1000);
                        }
                    }

                    // var aLink = document.createElement('a');
                    // aLink.href = path
                    // aLink.dispatchEvent(new MouseEvent('click', {
                    //     bubbles: true,
                    //     cancelable: true,
                    //     view: window
                    // }));
                },
                error : function(data){
                    console.log(data);
                }
            });


        }
    }



    var files = [];
    var filesCopy =[];


    $("#uploadForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });

    $("#f").on("click", ".del-file", function () {
        var r = confirm("确定删除");
        if (r){
            var _this = $(this);
            var fileId = parseInt(_this.attr("data-file-id"));
            $.ajax({
                url:'${ctx}/survey/case/operate?fileId=' + fileId + "&btnCode=del-file",
                type:"Get",
                success:function(res,param){
                    reload();
                }
            });
        }
    })

    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            files = [];
            // alert("上传成功");
            onBack($("#surveyInfoId").val(),Number($("#catalogId").val()));
            reload();
        }else{
            alert(apiRsp.msg);return;
        }
    }
    function uploadImg(){
        if(files.length == 0){
            alert("请选择文件");return;
        }
        $("#btnUpload").attr("disabled","true");
        $("#files").val(JSON.stringify(files));
//        successNum = 0;
//        step = 0;
        clearInterval(aa);
        clearInterval(bb);
        $("#uploadForm").submit();

//        $(this).find(":submit").attr("disabled","true");
        <%--$.ajax({--%>
        <%--url:'${ctx}/survey/case/fileMidOK?files=' + JSON.stringify(files),--%>
        <%--type:"Get",--%>
        <%--success:function(res,param){--%>
        <%--files = [];--%>
        <%--alert("上传成功");--%>
        <%--onBack($("#surveyInfoId").val(),$("#catalogId").val());--%>
        <%--}--%>
        <%--});--%>
    }

    function random(lower, upper) {
        return Math.floor(Math.random() * (upper - lower+1)) + lower;
    }

    //材料凭证
    var value = "";
    var successNum = 0;
    var step = 0;
    var bb = null;
    var aa = null;
    function clearParams(){
        files = []
        successNum=0
        step = 0
    }
    var domNum = random(80,90);
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            domNum = random(80,90);
            var r  = data.result;
            if(r.success == 'false'){
                alert(r.message);
                $("#div_pro").html("");
                return;
            }
            var file = r.surveyFile;
            if($("#catalogId").val() == null || !$("#catalogId").val()){
                $("#catalogId").val(8);
                $("#catalogName").val("调查资料");
            }
            var item = {
                "surveyId" : $("#surveyId").val(),
                "surveyInfoId" : $("#surveyInfoId").val(),
                "catalogId" : Number($("#catalogId").val()),
                "catalogName" : $("#catalogName").val(),
                "fileName" : file.fileName,
                "filePath" : file.filePath
            };
            files.push(item);
            var fileNum = $("#fileNum").val();
            $("#materialImgsDiv").html("");
//            $("#materialImgsDiv").append("<div class='col-sm-3'><img width='80' height='80' src='" + file.filePath + "' /></div>");
            $("#materialImgsDiv").append("<div class='col-sm-3'>共" + fileNum + "个文件已完成:" + files.length + "上传&nbsp;&nbsp;&nbsp;</div>");
            value += file.filePath + ","
            $("#img").val(value);
            $("#materialImgsDiv").show();
            $("#div_pro").show();
//            pro.update(parseInt((files.length / fileNum * 100)))


            step = 0;

            if($("#fileNum").val() == successNum){
                uploadImg();
                return;
            } else {
                aa = setInterval(function(){
                    if($("#fileNum").val() == successNum){
                        return;
                    }
                    if (step >= domNum){
                        clearInterval(aa)
                        console.log("done" + step + '---' + successNum);
                    } else {
                        step = step + 5
                    }
                    $('#asd' + successNum).css({
                        'width': step + '%',
                        'backgroundColor':'#c0e2a0'
                    })
                    $('#asd' + successNum).text(step + "%");

                },1500);
            }
        },
        progress : function (e,data){
        },
        progressall : function(e,data){
            var progress = parseInt(data.loaded / data.total * 100, 10);

            if(progress > 70){
                progress = random(80,90);
            }

            if($("#fileNum").val() == successNum){
                return;
            } else {
                bb = setInterval(function(){
                    console.log("progress:"+$("#fileNum").val() + "[file]" + successNum);
                    if($("#fileNum").val() == successNum){
                        return;
                    }
                    if (step >= domNum){
                        clearInterval(bb)
                        console.log("progressall" + step + '---' + domNum);
                    } else {
                        step = step + 5
                    }
                    $('#asd' + successNum).css({
                        'width': step + '%',
                        'backgroundColor':'#c0e2a0'
                    })
                    $('#asd' + successNum).text("上传中" + step + "%");

                },1500);
            }


//            $("#asda" + successNum).html("<span style='display: inline-block;'>正在上传</span>");
//            $("#asda" + successNum).append("<span style='width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right'>" +
//            "<span id='asd"+successNum+"' style='background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;'>"+progress+"%</span></span>" );

//            $('#asd' + successNum).css({
//                'width': progress + '%',
//                'backgroundColor':'#c0e2a0'
//            })
//            $('#asd' + successNum).text("上传中"+progress + "%");
        },
        success : function (e,data){
//            $("#asda" + successNum).html("<span  style='display: inline-block;'>上传完成</span>");
//            $("#asda" + successNum).append("<span style='width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right'>" +
//            "<span id='asd"+successNum+"' style='background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;'>100%</span></span>" );
//            $('#asd' + successNum).css('width', progress + '%');
            console.log("success",e,data);

            $('#asd' + successNum).css({
                'width': '100%',
                'backgroundColor':'#c0e2a0'
            })
            $('#asd' + successNum).text("100%");

            successNum ++ ;
            if($("#fileNum").val()  == successNum){
                $("#btnUpload").attr("disabled",false);
            }
        },
        fail : function(e,data){
            console.log("fail:",e,data);
            $("#div_pro").html("");
            alert("上传失败，请重新上传");

        }
    });

    var uploadFile = $("#materialFileupload");
    uploadFile.on("change",upload);
    function upload(){
        $("#btnUpload").attr("disabled",true);
        var f = this.files;
        $("#fileNum").val(f.length);
        var html = "";
        for(var i = 0 ; i < f.length ; i ++){

            html += '<div style="width:45%;overflow: hidden;padding: 10px;font-size: 12px;border: 1px solid #eee;margin-bottom: 10px;"> <span style="display:inline-block;overflow: hidden; width: 98%;text-overflow: ellipsis;white-space: nowrap;">'  + f[i].name + '</span>' + '<span id="asda'+i+'" style="width:100%;position:relative;height: 33px;display:block"><span style="display: inline-block;">上传进度</span><span style="width:100%;background-color:#eee;height:16px;display: inline-block;"><span id="asd'+i+'" style="background-color:#eee;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;">0%</span></span></span></div>'

//            html += '<div style="width:45%;overflow: hidden;padding: 10px;font-size: 12px;border: 1px solid #eee;">' +
//                    '<span style="display:inline-block;overflow: hidden; width: 98%;text-overflow: ellipsis;white-space: nowrap;">' + f[i].name + '</span>' +
//                    '<span id="asda'+i+'" style="width:100%;position:relative;height: 33px;display:block">' +
//                    '<span style="width:100%;background-color:#eee;height:16px;display: inline-block;text-align: right"><span id="asd'+i+' style="background-color:#c0e2a0;display:block;height:16px;display: inline-block;position:absolute;left:0;bottom:0;text-align:right;color:#fff;">0%</span></span></span></div>';
        }
        $("#div_pro").html(html);
    }

    var pro = new progress({
        width : 500,//进度条宽度
        height: 30,//进度条高度
        bgColor : "#3E4E5E",//背景颜色
        proColor : "#009988",//前景颜色
        fontColor : "#FFFFFF",//显示字体颜色
        val : 0,//默认值
        text:"当前进度为#*val*#%",//显示文字信息
        showPresent : true,
        completeCallback:function(val){
            console.log('已完成');
        },
        changeCallback:function(val){
            console.log('当前进度为'+val+'%');
        }
    });
    //    document.getElementsByClassName('pro')[0].appendChild(pro.getBody());

    function formatData(time,str){
        Date.prototype.Format = function(fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "HH+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if(/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                        .substr(4 - RegExp.$1.length));
            for(var k in o)
                if(new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1,
                            (RegExp.$1.length == 1) ? (o[k]) :
                                    (("00" + o[k])
                                            .substr(("" + o[k]).length)));
            return fmt;
        }
        return(new Date(time)).Format(str)
    }

    function openImgs(index,ext,filePath){
        var surveyInfoId = $("#surveyInfoId").val();
        var catalogId = $("#catalogId").val();
        if(ext == 2){
            openDialog({
                frame:true,
                title:"查看图片",
                height:500,
                width:750,
                url:"${ctx}/survey/case/fileMidView?index="+index+"&surveyInfoId="+surveyInfoId+"&catalogId="+catalogId
            });
        }else if(ext == 1){//word
            onlinePreview(filePath)
        }else if(ext == 3){//rar

        }else if(ext == 4){//excel
            onlinePreview(filePath)
        }else if(ext == 5){//pdf
            onlinePreview(filePath)
        }else if(ext == 6){//pdf
            onlinePreview(filePath)
        }
    }
</script>

</html>