<!doctype html>
<html lang="en">
<head>
    <title>首页</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#include "../include/header.ftl">
    <link rel="stylesheet" type="text/css" href="${css("pc/home.css")}">
</head>
<body>
<div class="common home" id="navigation">
    <#include "../include/header_page.ftl">
    <img src="${image("pc/home/home_bg.png")}" style="display: block;width: 100%;min-width: 1280px;">
    <div class="homeBtn" id="homeBtn" style="min-width: 1280px;">
        <img src="${image("pc/home/home-bg.jpg")}">
        <div class="subscription_wrap"  @click="toSubscription"  onclick="_hmt.push(['_trackEvent', '订阅Taycan资讯_pc', 'click', 'source=${source!}'])">
        </div>
        <div class="center" @click="toReservation"  onclick="_hmt.push(['_trackEvent', '支付购车意向金_pc', 'click', 'source=${source!}'])">
            <div class="reservation_wrap"></div>
        </div>
        <div  @click="toAccount" class="account_wrap" onclick="_hmt.push(['_trackEvent', '我的账户_pc', 'click', 'source=${source!}'])"></div>

    </div>

</div>
<div class="questions justify-center hoverCursor" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
    <img src="${image("pc/login/question.png")}"/>
    常见问题
</div>

<#include "../include/footer.ftl">
</body>
<script>
    getBtnWidth();
    window.onresize = getBtnWidth;

    function getBtnWidth() {
        var homeW = document.getElementById('homeBtn').offsetWidth;
        var homeWRate = parseFloat(homeW / 2526);
        var changeW = parseFloat(homeWRate * 318);
        document.getElementById('homeBtn').style.height = changeW + 'px';
    }
</script>
<script src="${js("home.js")}"></script>
<script src="${js("header.js")}"></script>
</html>