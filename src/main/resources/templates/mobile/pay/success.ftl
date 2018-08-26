<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/pay_success.css")}">
</head>
<body>
<#include "../include/header.ftl">
<div class="paySuccessWrap" id="pay_success" style="background-image: url(${image("mobile/common/porsche_bg.png")})">
    <div class="banner" >
        <div class="subscriptionTitle">
            <div class="leftInfo">
                <img src="${image("mobile/pay/pay_icon.png")}">
            </div>
            <div class="rightInfo">
                <span>在线支付</span>
            </div>
        </div>

        <div class="successContent">
            <div class="agreementHeader">
                <div class="flowLine" style="background-image: url(${image('mobile/common/dot.png')})"></div>
                <div class="flowIdot">
                    <span class="dot justify-center" style="background-image: url(${image("mobile/common/4.png")})"></span>完成在线支付
                </div>

            </div>
            <div class="successInfo">
                <p style="font-size: 20px;margin-bottom: 5px;">恭喜您，您已支付成功</p>
                <div class="detaiInfo" style="font-size: 14px;">
                    <p>编号：${order.orderSn}</p>
                    <p>支付金额：20,000 RMB</p>
                    <p>支付方式：${order.payTypeString}</p>
                </div>

                <a @touchstart="toAccoutPage" onclick="_hmt.push(['_trackEvent', '查看我的意向单_mobile', 'click', 'source=${source!}'])">查看我的意向单</a>
            </div>
        </div>
    </div>
</div>
<#include "../include/footer.ftl">
<script src="${js("pay_success.js")}"></script>
</body>
</html>