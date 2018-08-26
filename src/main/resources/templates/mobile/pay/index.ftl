<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/pay.css")}">
</head>
<body>
<#include "../include/header.ftl">
<div class="payWrap" id="vpay">
    <div class="banner" style="background-image: url(${image("mobile/common/porsche_bg.png")})">
        <div class="subscriptionTitle">
            <div class="leftInfo">
                <img src="${image("mobile/pay/pay_icon.png")}">
            </div>
            <div class="rightInfo">
                <span>在线支付</span>
                <span>请选择支付方式，完成您的购车意向金付款</span>
            </div>
        </div>

        <div class="agreementConent">
            <div class="agreementHeader">
                <div class="flowLine" style="background-image: url(${image('mobile/common/dot.png')})"></div>
                <div class="flowIdot">
                    <span class="dot justify-center"
                          style="background-image: url(${image("mobile/common/3.png")})"></span>在线支付购车意向金
                </div>
                <div class="flowLine long" style="background-image: url(${image('mobile/common/dot.png')})"></div>
                <img src="${image('mobile/order/right_agreement.png')}" class="rightArrow"/>
            </div>
            <div class="payInfo">
                <p class="payCompay clearfix"><span style="width: inherit;">收&nbsp;&nbsp;款&nbsp;&nbsp;方：</span><span>${order.companyCn}</span></p>
                <p class="payDealer">（${order.dealerName}）</p>
                <p class="payTotalMoney">
                    支付金额：
                    <span style="font-size: 20px" class="bold">
                        20,000
                        <span style="font-size: 14px">RMB</span>
                    </span>
                </p>
                <p class="payLine">请选择一种支付方式</p>
                <ul class="payStyleWrap">
                    <li v-on:click="wapAliPay" onclick="_hmt.push(['_trackEvent', '支付宝_mobile', 'click', 'source=${source!}'])">
                        <img src="${image("mobile/pay/alipay-wap.png")}" class="iconLeft"/>
                        <span>支付宝</span>
                        <img src="${image('mobile/order/right_agreement.png')}" class="iconRight"/>
                    </li>
                    <#--<li v-on:click="wapQuickPassPay" style="height: 1.3rem;line-height: 1.3rem">-->
                        <#--<img src="${image("mobile/pay/quickpass-wap.png")}" class="iconLeft"/>-->
                        <#--<span>云闪付</span>-->
                        <#--<img src="${image('mobile/order/right_agreement.png')}" class="iconRight"/>-->
                    <#--</li>-->
                    <li v-on:click="wapUnionPay" onclick="_hmt.push(['_trackEvent', '银联_mobile', 'click', 'source=${source!}'])">
                        <img src="${image("mobile/pay/unionpay-wap.png")}" class="iconLeft">
                        <span>银联</span>
                        <img src="${image('mobile/order/right_agreement.png')}" class="iconRight"/>
                    </li>
                </ul>


                </ul>
            </div>
        </div>
    </div>
</div>
<#include "../include/footer.ftl">
<script>
    var orderSn = "${order.orderSn}";
</script>
<script src="${js("pay.js")}"></script>
</body>
</html>