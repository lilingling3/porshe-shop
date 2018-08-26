<!DOCTYPE html>
<html lang="en" >
<head>
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/home.css")}">
    <title>首页</title>
</head>
<body >
 <#include "../include/header.ftl">
<div class="wrapper" id="navigation">
    <img @click="toSubscription" src="${image("mobile/home/home1.png")}"  onclick="_hmt.push(['_trackEvent', '订阅Taycan资讯_mobile', 'click', 'source=${source!}'])"/>
    <img @click="toReservation" src="${image("mobile/home/home2.png")}"  onclick="_hmt.push(['_trackEvent', '支付购车意向金_mobile', 'click', 'source=${source!}'])"/>
    <img @click="toAccount" src="${image("mobile/home/home3.png")}"  onclick="_hmt.push(['_trackEvent', '我的账户_mobile', 'click', 'source=${source!}'])"/>
</div>
 <#include "../include/footer.ftl">
</body>
<script src="${js("home.js")}"></script>
</html>
</html>