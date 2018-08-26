<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>到店支付</title>
     <#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/shopOrderSuccess.css")}">
</head>

<body>
<div class="page-box arrival shop-order" id="shopOrderSuccess">
   <#include "../include/header_page.ftl">
    <div class="common">
        <img src="${image("pc/shop-order/shop-order-bg.png")}" class="bg imgMinWithHeight"/>
        <div class="content-box arrival-box" style="width:500px;">
            <div class="arrival-title">
                <div class="arrival-icon">
                    <img src="${image("pc/shop-order/arrivalIcon.png")}"/>
                </div>
                <p>到店支付</p>
                <p>您已选择意向的保时捷中心，稍后我们的工作人员将通过您注册的手机号码与您联系。</p>
            </div>
            <div class="timezone" style="background-image: url(${image("pc/shop-order/line.png")};">
                <div class="time">
                    <div>
                        <p>选择保时捷中心</p>
                        <div class="SuccessText">
                            <p>恭喜您，您的预约已成功提交</p>
                            <#list leads.leadsDealers as dealer>
                                <p>${dealer.dealerName}</p>
                            </#list>
                            <p>会通过您注册的手机号码与您取得联系，敬请期待！</p>
                        </div>
                    </div>

                </div>
                <div class="time" style="top: 296px;">
                    <div>
                        <p>保时捷中心电话邀约</p>

                    </div>
                </div>
                <div class="time" style="top: 356px;">
                    <div>
                        <p>到店签署购车意向单</p>
                    </div>
                </div>
                <div class="time" style="top: 416px;">
                    <div>
                        <p>到店支付购车意向金</p>
                    </div>
                </div>
                <div class="time" style="top: 476px;">
                    <div>
                        <p>完成到店支付</p>
                    </div>
                </div>
            </div>

        </div>

    </div>

      <#include "../include/footer.ftl">
</div>
<div class="questions justify-center" onclick="toCommonQestion('${source!}')">
    <img src="${image("pc/login/question.png")}"/>
    常见问题
</div>

</body>
<script src="${js("shopOrderSuccess.js")}"></script>
<script src="${js("header.js")}"></script>
</html>