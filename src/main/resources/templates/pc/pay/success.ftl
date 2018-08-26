<!DOCTYPE html>
<html lang="en">
<head>
    <title>在线支付成功</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/pay_success.css")}">
</head>
<body>

<div class="page-box login" id="pay_success" v-cloak>
    <#include "../include/header_page.ftl">
    <div class="common">
        <img class='bg imgMinWithHeight' src="${image("pc/shop-order/order_pic.jpg")}"/>

        <div class="content-box" style="width:478px;">
            <div class="login-content" id="orderInfo" style="height: 100%">
                <div class="wanshan">
                    <div class="border_left">
                        <img src="${image("pc/pay/pay_icon.png")}" alt="" style="width:100%;">
                    </div>
                    <div class="wan_info">
                        <p style="font-size:20px;">在线支付</p>
                    </div>
                </div>
                <div class="orderbox blue_round" style="background-image:url('${image("pc/pay/pay_success.jpg")}">
                    <div class="onetab">
                        <div style="line-height: 26px;position: relative;top: -3px;">
                            <span>阅读并确认购车意向书</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top:30px;">
                        <div style="line-height: 26px;">
                            <span>完善购车人信息</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top:35px">
                        <div style="line-height: 26px;">
                            <span>在线支付购车意向金</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top:34px;">
                        <div style="line-height: 26px;">
                            <span>完成在线支付</span>
                        </div>
                        <div class="successText">
                            <p>恭喜您，您已支付成功</p>
                            <p>编号：${order.orderSn}</p>
                            <p>支付金额：20,000 RMB</p>
                            <p>支付方式：${order.payTypeString}</p>
                            <a @click="toAccoutPage" onclick="_hmt.push(['_trackEvent', '查看我的意向单_pc', 'click', 'source=${source!}'])">查看我的意向单</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#include "../include/footer.ftl">
</div>
<div class="questions justify-center hoverCursor" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
    <img src="${image("pc/login/question.png")}"/>
    常见问题
</div>
<script src="${js("pay_success.js")}"></script>
<script src="${js("header.js")}"></script>
<script src="${js("browser_back_forbidden.js")}"></script>
</body>
</html>
