<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/register_success.css")}">

</head>
<body>
<#include "../include/header.ftl">
<div class="registerSuccessWrap">
    <div class="banner" style="background-image: url(${image("mobile/register/subscription.jpg")})"
         id="register-success">
        <div class="subWrap">
            <img src="${image("mobile/register/sub.png")}" style="display: inline-block; width: 25px;">
        </div>

        <div class="content-wrap">
            <div class="subText">
                <p>感谢您关注保时捷 Taycan</p>
                <p>我们将持续为您奉上最精彩资讯，敬请期待</p>
            </div>
            <div class="toOrderSubmit">
                <a class="payBtn">
                    <img src="${image("pc/pay/pay_icon.png")}">
                    <span @touchstart="order" onclick="_hmt.push(['_trackEvent', '支付购车意向金_mobile', 'click', 'source=${source!}'])">支付购车意向金</span>
                </a>
            </div>
        </div>

    </div>
</div>
<#include "../include/footer.ftl">
<script>
</script>
<script src="${js("register_success.js")}"></script>
<script src="${js("register_success.js")}"></script>
</body>
</html>