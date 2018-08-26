<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/question.css")}">
</head>
<body>
<#include "../include/header.ftl">
<div class="questions" id="question">
    <p class="back" style="background-image: url(${image("mobile/common/back-grey.png")})"
    @clck="toBackPage">
    返回
    </p >
    <div  class="questionsWrap">
        <ul>
            <li>
                <div class="leftW">
                    <span class="numberW">1</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">2</span>
                    <span>保时捷中心有实车展示吗？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 目前处于概念车阶段，各地的保时捷中心暂无实车展示</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">3</span>
                    <span>可以预订多辆车吗？</span>
                </div>
                <div class='twoLine'>一个账户（手机号码 + 身份证号码）只能在线预订一辆车；到店预订不受数量限制</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">4</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>

            <li>
                <div class="leftW">
                    <span class="numberW">5</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">6</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">7</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">8</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">9</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>
            <li>
                <div class="leftW">
                    <span class="numberW">10</span>
                    <span>可以有哪些预订方式？</span>
                </div>
                <div class='twoLine'>保时捷 Taycan 接受在线预订和到店预订两种方式</div>
            </li>

        </ul>
    </div>

</div>
<#include "../include/footer.ftl">

</body>
<script src="${js("question.js")}"></script>
</html>