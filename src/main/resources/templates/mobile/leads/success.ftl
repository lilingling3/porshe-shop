<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/shop_order_success.css")}">
    <title>预订成功</title>
</head>
<body>
 <#include "../include/header.ftl">
<section class="wrapper" style="background-image: url(${image("mobile/shop-order/bg.png")})">
    <div class="page-title">
        <img class="title-icon justify-center" src="${image("mobile/shop-order/weizhi.png")})"/>
        <div class="title-info">
            <h1>到店支付</h1>
            <p>您已选择意向的保时捷中心，稍后我们的工作人员将通过您注册的手机号码与您联系。</p>
        </div>
    </div>

    <div class="content">
        <img src="${image("mobile/shop-order/success-bg.png")})">
        <div class="success-info">
            <h1>恭喜您，您的预约已成功提交</h1>
            <#list leads.leadsDealers as dealer>
            <p>${dealer.dealerName}</p>
            </#list>
            <p>会通过您注册的手机号码与您取得联系，敬请期待!</p>
        </div>
    </div>
</section>
 <#include "../include/footer.ftl">

</body>
</html>