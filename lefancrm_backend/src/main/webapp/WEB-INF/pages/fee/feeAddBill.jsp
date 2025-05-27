<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css?v=1">
    <style>
        body{
            overflow: auto!important;
        }
        .main{
            width: 94%;
            margin: 20px auto;

        }

        .tips{
            color: red;
        }

        .billBar{
            width: 100%;
            padding: 10px 1%;
            margin: 10px auto;
            border: 1px solid #eee;
        }
        .billTtle{
            display: flex;
            align-items: center;
            padding: 6px 0;
            font-weight: bold;
        }
        .icon-about{
            margin: 0 5px;
            display: inline-block;
            width: 14px;
            height: 14px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAADVElEQVRoQ92aj23VMBDGrxNQJgAmaJkAmIB2AsoEwATQCYAJoBNAJ4BOQDsBdIMyAegX2ZGfn+3YdxcicZKlVkrO33f/7bwD8ZPnInIsIk+DysPwP/9ei8hdWPzNuvTY+sCgBICAPglLo+qriLAgA8Fh0RAA+CsReS0i/O0hgP8gIh9HiYwSeBE28gKek/8lIu9E5KLXKr0EAPwtiele/drnyJFnPd7oIUBiAn4tq9dIElaQgExVlgicicgnrRkd3oPEGxH5XNPVIrA1+BTzyxqJGgHC5oeDBb1UVMOpRIBY/7lBzC+RhcSjPLFLBLA8HtAKTel7WDEBY1em6RGa95TK0fc4fTcnYIn736G5VRMubPwwxPMTJYmdfEgJWEIH8MxAzZKXAWaEYBQZFZodXphGj5QAHfDtqLbwfLVKNPRhMMBowuk8dOyZgMX6N42cQW8EWuKiDdk5oaMHtIoAdRomyhRgPnpgabxEcqdiKdeT1yMBbTwChtIGwFRIZAa/VHiGZ3P5owxbqt1JJKBVkudRxFLTVyrb9Bwq06gQRvdRSG3+Mvp2eL4W/yUCV8lpLd3OYrxTCFiqTy+oWpm15ABGOIcAiaVtKrixVPvjuZhNWj3CYjx0X1oJLEVeCzxxT/xb5AoC1tmnBYCOWfKQ1wnvGgKWJGqBn7tl9pAX+EntWgRuK6XRFXwkgIuPLIFYeJfrEa5dUnEHLyI3ayVxabyA0HtnQ01JbCmjNTzcJuRzj7Vklvaayugaiv8VgamRWUaJrT0wjRLIWqXUOeR31NEkDz3G6TVBtnTvjNOWA81WBHYONJbzaYkAjYzcimOEdwklfJil7rwO9TmJ0iHfs2HuHeoBgBfY5IFDTJTKaOmYqdkK73KO2LtWQZlXLnCjzBeXVLym3urFVtzMw9X5LQSfpHJCGuvvHWFrl7vaCycNqN535sRNX2hdrzPLaG7NegGNPFc92f23HziidUhqYncrTyzeeC99I4MIJWuLcOq68e4hEHsEJLxPbrU8oNpwNbP49b6XQBpSnB88ml1tBEH/0keS+d1RAtEbzDYsr9wgXMg11qLVe8poT4lj9Ig/9MDdo2QATVjGH3wMAY8ANR6okYMMCc+KHzZizhDT+c9tAG6Wv0rXwk541R35AAAAAElFTkSuQmCC);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        .files {
            margin-top: 20px;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            overflow: auto;
        }

        .files .file {
            width: 100px;
            height: 100px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .files div.file-add {
            width: 100px;
            height: 100px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 48px;
            margin-top: 19px;
            display: inline-block;
            background: #aaa;
            height: 60px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 60px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }
        .file-title{
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            background-color: rgba(187,187,187,0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .file-del{
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
        }
        .example{
            width: 100%;
        }
        .example-title{
            font-size: 20px;
            padding: 15px 0;
        }
        .example-img{
            text-align: center;
        }
        .example-img-info{
            padding: 10px 0;
            width: 100%;
            text-align: center;
            font-size: 12px;
            font-weight: bold;
        }
        /*.example-img img{*/
            /*width: 70%;*/
            /*height: 200px;*/
        /*}*/
        .lf-btns{
            padding-top: 20px;
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 160px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
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
        .display-none{
            display: none;
        }
    </style>

</head>
<body>
    <input hidden id="id" value="${id}">
    <input hidden id="btnCode" value="${btnCode}">
    <input hidden id="cityinDrivingMoney" value="${cityinDrivingMoney}">
    <input hidden id="medicalHistoryMoney" value="${medicalHistoryMoney}">
    <input hidden id="accommodatioMoney" value="${accommodatioMoney}">
    <div class="main">
        <c:if test="${btnCode == 'add'}">
        <div class="tips">
            请注意：寄送的纸质单据及报销单要符合要求，比如要和SAAS系统的项目和金额一致、发票抬头正确、发票真实有效、报销单填写内容与金额正确等等，如发现不符合要求的，一率退回。由此影响时效由调查员自行承担。谢谢大家的配合，如有问题可以咨询财务部。
        </div>
        </c:if>
        <div class="billBars">
            <div class="billBar ${cityinDrivingMoney == '0' ? 'display-none' : ''}">
                <div class="billTtle">
                    <div>交通费:</div>
                    <div class="icon-about" title="交通费=市内交通费+跨地市交通费（汽车、火车、飞机）+跨地市交通费（自驾)"></div>
                    <div>${cityinDrivingMoney} 元</div>
                </div>
                <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                <div class="billTtle">请上传交通费凭证（请确保凭证中的总额加起来等于${cityinDrivingMoney} 元）：</div>
                </c:if>
                <ul class='files' id="jq21" data-id="1">
                    <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                    <div class="file-add uploadFile" lay-data="{id:1}" >
                        <div class="icon-add v-p-upload"  ></div>
                    </div>
                    </c:if>
                    <%--<li class="file" id="1">--%>
                        <%--<img src="img/test1.jpg" class="image" id="1"></img>--%>
                        <%--<div class="file-title"></div>--%>
                        <%--<div class="file-del"></div>--%>
                    <%--</li>--%>
                </ul>
            </div>
            <div class="billBar ${medicalHistoryMoney == '0' ? 'display-none' : ''}">
                <div class="billTtle">
                    <div>病案调阅费:</div>
                    <div class="icon-about" title="病案调阅费=病史费（含复印费）+住院排查费用+门诊排查费用+体检报告打印费+其他费用"></div>
                    <div>${medicalHistoryMoney} 元</div>
                </div>
                <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                <div class="billTtle">请上传病案调阅费凭证（请确保凭证中的总额加起来等于${medicalHistoryMoney} 元）：</div>
                </c:if>
                <ul class='files' id="jq22" data-id="2">
                    <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                    <div class="file-add uploadFile" lay-data="{id:2}" >
                        <div class="icon-add v-p-upload" ></div>
                    </div>
                    </c:if>
                    <%--<li class="file" id="1">--%>
                    <%--<img src="img/test1.jpg" class="image" id="1"></img>--%>
                    <%--<div class="file-title"></div>--%>
                    <%--<div class="file-del"></div>--%>
                    <%--</li>--%>
                </ul>
            </div>
            <div class="billBar ${accommodatioMoney == '0' ? 'display-none' : ''}">
                <div class="billTtle">
                    <div>住宿费:</div>
                    <div class="icon-about" title="住宿费"></div>
                    <div>${accommodatioMoney} 元</div>
                </div>
                <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                <div class="billTtle">请上传住宿费凭证（请确保凭证中的总额加起来等于${accommodatioMoney} 元）：</div>
                </c:if>
                <ul class='files' id="jq23" data-id="3">
                    <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
                    <div class="file-add uploadFile" lay-data="{id:3}" >
                        <div class="icon-add v-p-upload"  ></div>
                    </div>
                    </c:if>
                    <%--<li class="file" id="1">--%>
                    <%--<img src="img/test1.jpg" class="image" id="1"></img>--%>
                    <%--<div class="file-title"></div>--%>
                    <%--<div class="file-del"></div>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
        <button type="button" class="layui-none" id="uploadFile"></button>
        <c:if test="${btnCode == 'add' || btnCode == 'billUpd'}">
            <div class="example">
                <div class="example-title">
                    如何填写费用报销单？
                </div>
                <div class="example-img">
                    <img src="${ctx}/img/bill-example.png"></img>
                </div>
                <div class="example-img-info">费用报销单填写模板</div>
            </div>
            <div class="rejectionType lf-btns layui-inline" style="justify-content: center">
                <div class="lf-btn " data-type="1">取消</div>
                <div class="lf-btn active" data-type="2">提交机构审核</div>
            </div>
        </c:if>

    </div>
</div>
    <script src="${ctx}/js/jquery-3.4.1.js"></script>
    <script src="${ctx}/js/viewer.min.js"></script>
    <script src="${ctx}/js/layui/layui.js"></script>
    <script>
        layui.use(['upload', 'layer'], function () {
            layer = layui.layer
            var upload = layui.upload;
            var uploadFile = upload.render({
                elem: '.uploadFile',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                data: {
                    'modelType' : 'feeAddBill',
                    'id': $("#id").val()
                },
                multiple: true,
                accept: 'images', //只能上传图片
                acceptMime: 'image/*',
                before: function (obj) {
                    layer.load();
                },
                done: function (res) {
                    console.log("res:::"+JSON.stringify(res))
                    fileLen = $('.files[data-id="'+this.id+'"] .file').length
                    layer.closeAll('loading')
                    if (res.success == 'true'){
                        putSessionFiles(this.id,res.surveyFile);
                    }else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone:function(obj){
                    console.log(obj)
                    var name = $('.files[data-id="'+this.id+'"]').attr('id')
                    if (fileLen){
                        viewer.destroy()
                    }

                    viewer = new Viewer(document.getElementById(name));
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
        });
        if ($('#btnCode').val() == 'view' || $('#btnCode').val() == 'billUpd'){
            var params = {
                id: $('#id').val(),
                operateType: 'reNew',
                btnCode: 'checkInvoice',
            }

            $.ajax({
                url: '${ctx}/fee/operate',
                type: 'post',
                data: params,
                success: function(res){
                    res =JSON.parse(res)
                   if (res.isSuccess){
                       var list = res.results
                       var trafficFiles = [],consultFiles = [],accnewFiles = []
                       list.map(function (cur) {
                           if (cur.fileCode == 'traffic'){
                               trafficFiles.push(cur.fileUrl)
                               initFiles(1,cur.fileUrl,cur.id)

                           }else  if (cur.fileCode == 'consult'){
                               consultFiles.push(cur.fileUrl)
                               initFiles(2,cur.fileUrl,cur.id)
                           }else  if (cur.fileCode == 'accnew'){
                               accnewFiles.push(cur.fileUrl)
                               initFiles(3,cur.fileUrl,cur.id)
                           }



                       })


                       viewer = new Viewer(document.getElementById('jq21'));
                       viewer = new Viewer(document.getElementById('jq22'));
                       viewer = new Viewer(document.getElementById('jq23'));
                   }
                }
            })
        }
        $('body').on('click','.file-del',function(){
            var _this = $(this)
            var name = $(this).parents('.files').attr('id')

            layer.confirm('确定删除？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                _this.parent().detach()
                viewer = new Viewer(document.getElementById(name));
                layer.closeAll()
            }, function(){

            });
        })
        $('body').on('click','.file',function(){
            // var name = $(this).parents('.files').attr('id')
            // viewer = new Viewer(document.getElementById(name));
            // viewer.show()
        })
        $('body').on('click', '.viewer-close', function () {
            // if ($('#btnCode').val() == 'add' || $('#btnCode').val() == 'billUpd'){
            //     var _this = $(this)
            //     _this.parents('.viewer-container').detach()
            //     viewer.destroy()
            // }
        })
        $('.lf-btn').click(function () {
            var _this = $(this)
            if(_this.hasClass('active')){
                // _this.addClass('poi-no')
                // setTimeout(function () {
                //     _this.removeClass('poi-no')
                // },3000)
                var  trafficFile = [],
                    consultFile =[],
                    accnewFile =[]
                $('#jq21 .file').map(function (i,cur) {
                    var path = $(cur).find('img:first-of-type').attr('src')
                    trafficFile.push(path)
                })
                if (Number($('#cityinDrivingMoney').val()) >0 && !trafficFile.length){
                    layer.msg('请上传交通费凭证',{
                        time: 1500,
                        icon: 5
                    })
                    return;

                }
                $('#jq22 .file').map(function (i,cur) {
                    var path = $(cur).find('img:first-of-type').attr('src')
                    consultFile.push(path)
                })

                if (Number($('#medicalHistoryMoney').val()) >0 && !consultFile.length){
                    layer.msg('请上传病案调阅费凭证',{
                        time: 1500,
                        icon: 5
                    })
                    return;
                }
                $('#jq23 .file').map(function (i,cur) {
                    var path = $(cur).find('img:first-of-type').attr('src')
                    accnewFile.push(path)
                })
                if (Number($('#accommodatioMoney').val()) >0 && !accnewFile.length){
                    layer.msg('请上传住宿费凭证',{
                        time: 1500,
                        icon: 5
                    })
                    return;

                }
                var params = {
                    id: $('#id').val(),
                    operateType: 'reNew',
                    btnCode: 'org_submit',
                    trafficFile: trafficFile.length ? JSON.stringify(trafficFile) : '',
                    consultFile:consultFile.length ? JSON.stringify(consultFile) : '',
                    accnewFile: accnewFile.length ? JSON.stringify(accnewFile) : ''
                }
                console.log(params)

                layer.confirm('确认提交？', {
                    btn: ['确认','取消'] //按钮
                }, function(){
                    $.ajax({
                        url: '${ctx}/fee/operate',
                        type: 'post',
                        data: params,
                        success: function(res){
                            res = JSON.parse(res)
                            if (res.isSuccess){
                                layer.msg('操作成功',{
                                    time: 1500,
                                    icon: 1
                                },function () {
                                    parent.reload();
                                })

                            }else {
                                layer.msg('操作失败',{
                                    time: 1500,
                                    icon: 5
                                })
                            }
                        }
                    })
                    layer.closeAll()
                }, function(){

                });


            }else {
                var closeBtn = $("#diglog_close_btn",window.parent.document);
                closeBtn.click();
            }
        })

        function  putSessionFiles(id,surveyFile) {
            var _html = ' <li class="file">\n' +
                '                        <img src="'+surveyFile.filePath+'" class="image"></img>\n' +
                    '<div class="file-title">'+surveyFile.fileName+'</div>\n' +
                '                        <div class="file-del"></div>'
                '                    </li>'
            $('.files[data-id="'+id+'"]').append(_html)
        }
        function  initFiles(id,filePath,imgId) {
            var btnCode = $("#btnCode").val();
            var _html = ' <li class="file">\n' +
                '                        <img src="'+filePath+'" class="image"></img>\n' ;
                    if('billUpd' == btnCode){
                        _html+=       '<div class="file-title"></div>\n' +
                        '                        <div class="file-del"></div>'
                    }
            _html+= '                    </li>'
            $('.files[data-id="'+id+'"]').append(_html)
        }
    </script>
</body>
</html>