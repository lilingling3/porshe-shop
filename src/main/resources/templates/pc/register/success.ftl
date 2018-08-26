<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>订阅成功</title>
    <link rel="stylesheet" href="${css("pc/register_success.css")}">
    <style>

        #register-success .payBtn .btnText:before{
            content: '';
            display: inline-block;
            width: 25px;
            height: 100%;
            background: url("${image("pc/pay/pay_icon.png")}") no-repeat;
            background-size: contain;
            vertical-align: middle;
            position: relative;
            right: 10px;
            top: 2px;
        }

        #register-success .payBtn:hover .btnText:before{
            content: '';
            display: inline-block;
            width: 25px;
            height: 100%;;
            background: url("${image("pc/pay/shopCar.jpg")}") no-repeat;
            background-size:contain;
            vertical-align: middle;
            position: relative;
            right: 10px;
            top: 2px;
        }

    </style>
   <#include "../include/header.ftl">
</head>
<body>
<div class="page-box orderWrap" id="register-success" >
     <#include "../include/header_page.ftl">

    <div class="common">
        <img src="${image("pc/register/subscription.jpg")}" class="bg imgMinWithHeight"/>

        <div class="content-box" style="height: 100%;width: 500px;min-height: 500px;overflow: hidden;background: rgba(45, 45, 45, .9)">
            <section style="height: 100%" >
                <div class="orderInfo" style="height: 100%">
                    <img src="${image("pc/register/subscription_icon.png")}" class="subscription_icon">
                    <p>感谢您关注保时捷 Taycan</p>
                    <p>我们将持续为您奉上最精彩资讯，敬请期待</p>
                    <a class="payBtn" @click="order" onclick="_hmt.push(['_trackEvent', '支付购车意向金_pc', 'click', 'source=${source!}'])">
                        <span class="btnText">支付购车意向金</span>
                    </a>
                </div>
            </section>
        </div>
    </div>

    <div class="questions justify-center" onclick="toCommonQestion('${source!}')">
        <img src="${image("pc/register/question.png")}">
        常见问题
    </div>
     <#include "../include/footer.ftl">

</div>
</body>
<script>

</script>
<script src="${js("register_success.js")}"></script>
<script src="${js("header.js")}"></script>
</html>