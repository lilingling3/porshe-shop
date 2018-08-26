<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>在线支付</title>
    <#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/pay.css")}">
    <script type="text/javascript" src="https://cdn.boldseas.com/js/util/qrcode.min.js"></script>

</head>
<body>

<div style="display: none">
</div>

<div class="page-box login" id="vpay" v-cloak>
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
                        <p class="xinxi">请选择支付方式，完成您的购车意向金付款</p>
                    </div>
                </div>
                <div class="orderbox blue_round" style="background-image:url('${image("pc/pay/pay_bg.jpg")}');">
                    <div class="onetab">
                        <div style="line-height: 26px;">
                            <span style="position:relative;top: 2px;">阅读并确认购车意向书</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top: 36px;">
                        <div style="line-height: 26px;">
                            <span>完善购车人信息</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top: 35px;">
                        <div style="line-height: 26px;">
                            <span>在线支付购车意向金</span>
                        </div>
                        <div class="payMoney" style="padding: 30px 0 5px 0;">
                            <div class=";payCompayInfo" style="margin: 0 20px">
                                <p>收款方：${order.companyCn}</p>
                                <p class="dealerBox">（${order.dealerName}）</p>
                                <p style="padding: 10px 0 ">
                                    支付金额： <span class="bold" style="font-size: 20px;vertical-align: middle">20,000</span> RMB
                                </p>
                            </div>
                            <div class="payStyle" style="padding-top: 30px;">
                                <p style="padding-bottom: 20px;margin: 0 20px">请选择一种支付方式</p>
                                <ul style="margin-left: 15px;" class="clearfix">
                                    <li @click="alipay" onclick="_hmt.push(['_trackEvent', '支付宝_pc', 'click', 'source=${source!}'])">
                                        <img src="${image("pc/pay/alipay.png")}" alt=""/>
                                        <span>支付宝</span>
                                    </li>
                                    <li @click="wechatPay" onclick="_hmt.push(['_trackEvent', '微信支付_pc', 'click', 'source=${source!}'])">
                                        <img src="${image("pc/pay/wechat.png")}" alt=""/>
                                        <span>微信</span>
                                    </li>
                                    <li @click="quickPassPay" onclick="_hmt.push(['_trackEvent', '云闪付_pc', 'click', 'source=${source!}'])">
                                        <img src="${image("pc/pay/quick_pass.png")}" alt=""/>
                                        <span>云闪付</span>
                                    </li>
                                    <li @click="unionPay"  onclick="_hmt.push(['_trackEvent', '银联_pc', 'click', 'source=${source!}'])">
                                        <img src="${image("pc/pay/union_pay.png")}" alt=""/>
                                        <span>银联</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="onetab">
                        <div style="line-height: 26px;">
                            <span>完成在线支付</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div :class="isShowQrCode?'commonInput':'hidMask'">

            <div class="mask-wrap"></div>
            <div class="mask-unfinish mask-item">
                <div class="qrcodeWrp">
                    <div id="qrcode"
                         style="width:200px; height:200px; display: block;margin: 0 auto"></div>
                    <p style="padding-top: 10px;">请使用手机{{payStyle}}扫描二维码进行支付</p>
                    <div class="qrcodeTip">
                        <span>温馨提示：</span>
                        <span>请确保您所关联的银行卡账户</span>
                        <span>支持¥ 20,000 金额的线上交易</span>
                    </div>
                <#--<p style="font-size: 12px">支付成功后该页面会自动跳转，如未跳转请点击<a style="color:#36b3ca " @click="reloadPage">刷新</a></p>-->
                    <div class="payQestions">
                        <a onclick="toCommonQestion('${source!}')">常见问题</a>
                        <a @click="chooseOtherPayStyle" onclick="_hmt.push(['_trackEvent', '选择其他支付方式_pc', 'click', 'source=${source!}'])">选择其他支付方式</a>
                    </div>
                </div>
                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭微信/支付宝/云闪付弹框_pc', 'click', 'source=${source!}'])">
            </div>
        </div>

        <div :class="isShowUnionPay?'commonInput':'hidMask'">

            <div class="mask-wrap"></div>
            <div class="mask-unfinish mask-union">
                <div class="unionWrp">
                    请在第三方支付页面完成付款，付款完成前请不要关闭窗口
                </div>
                <div class="union-footer">
                    <a @click="paySuccess" onclick="_hmt.push(['_trackEvent', '我已付款成功_pc', 'click', 'source=${source!}'])">我已付款成功</a>
                    <a @click="repeatPay" onclick="_hmt.push(['_trackEvent', '付款遇到问题，重新支付_pc', 'click', 'source=${source!}'])">付款遇到问题，重新支付</a>
                    <a class="hoverCursor" onclick="toCommonQestion('${source!}')"> 常见问题</a>
                </div>
                <img src="${image("pc/register/close.png")}" @click="closeMaskUnion" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭银联支付弹框_pc', 'click', 'source=${source!}'])">
            </div>
        </div>
    </div>
    <div class="questions justify-center hoverCursor" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
        <img src="${image("pc/login/question.png")}"/>
        常见问题
    </div>
    <#include "../include/footer.ftl">
</div>
<script type="text/javascript">
    var orderSn = "${order.orderSn}";
</script>
<script src="${js("pay.js")}"></script>
<script src="${js("header.js")}"></script>
</body>
</html>
