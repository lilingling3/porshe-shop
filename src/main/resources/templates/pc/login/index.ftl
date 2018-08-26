<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <#--<meta name="viewport"-->
          <#--content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("common/swiper.min.css")}">
    <link rel="stylesheet" href="${css("pc/login.css")}">
    <script src="${js("common/swiper.min.js")}"></script>
</head>
<body>
<div class="page-box login" v-cloak>
    <#include "../include/header_page.ftl">
    <div class="common" style="min-width: 1280px">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="${image("pc/login/login1.jpg")}"/></div>
                <div class="swiper-slide"><img src="${image("pc/login/login2.jpg")}"/></div>
                <div class="swiper-slide"><img src="${image("pc/login/login3.png")}"/></div>

            </div>
        </div>

        <div class="content-box">
            <div class="login-content">
                <img src="${image("pc/login/tx.png")}" class="align-center photo"/>
                <div class="phoneBox">
                    <label for="phone">手机号码:</label>
                    <input type="text" name="phone" v-model="phone" placeholder="手机账号为登录账号，注册后不可修改"
                           @click="phoneInputClick"/>
                </div>
                <div class="verificationCode">
                    <label for="verificationCode">验&nbsp;&nbsp;证&nbsp;&nbsp;码:</label>
                    <input type="number" name="verificationCode" v-model="verificationCode"
                           @click="verificationCodeInputClick"/>

                    <span v-show="show" @click="getVerificationCode" onclick="_hmt.push(['_trackEvent', '获取验证码_pc', 'click', 'source=${source!}'])">获取验证码</span>
                    <span v-show="!show" style="background: #000">{{countdown}}秒后可重发</span>
                </div>

                <div class="info">
                    <p>
                        <i @click="readedPolicy" class="bingo" v-show="isReadPolicy"  onclick="_hmt.push(['_trackEvent', '同意隐私政策_pc', 'click', 'source=${source!}'])"
                           style="background-image: url(${image("mobile/common/bingo.png")})"></i>
                        <i @click="readedPolicy" class="circle"></i>
                        <span :class="isReadPolicyError?'error':''">我已阅读并了解<u class="policy" @click="openMask" onclick="_hmt.push(['_trackEvent', '打开隐私政策_pc', 'click', 'source=${source!}'])">隐私政策</u></span>
                    </p>
                    <p>温馨提示：首次登录的手机号将自动为您创建账户</p>
                </div>
                <i v-cloak><i class="error-msg">{{errorMsg}}{{phoneErrorMsg}}</i></i>
                <p class="login-btn">
                    <span @click="login" class="btn" onclick="_hmt.push(['_trackEvent', '登陆_pc', 'click', 'source=${source!}'])">登&nbsp;&nbsp;录</span>
                </p>
            </div>
        </div>

        <div :class="isShowPolicy?'commonInput':'hidMask'">
            <div class="mask-wrap"></div>
            <div class="mask-unfinish mask-item" id="order_mask_wrap">
                <div style="text-align: left;line-height: 20px">
                    <p style="text-align: center;font-size: 15px;margin-bottom: 5px;">隐私政策</p>
                    <p>
                        保时捷中心将采取一切必要且合理的措施确保客户的个人信息在现有数据保护相关法律下的安全。客户理解并同意，保时捷中心可以为了以下目的收集、使用、存储、处理客户的个人信息，包括但不限于客户姓名、联系方式（如电子邮件、电话/手机号码和地址）以及护照号码和驾驶证详情：
                        <br>
                        (1) 与客户进行与产品、服务和活动有关的沟通交流；
                        <br>
                        (2) 为客户提供保时捷驾驶体验；
                        <br>
                        (3) 改善客户对保时捷的产品、服务和营销工作。
                        <br>
                        客户理解并同意：保时捷中心可能会基于前述目的将客户的个人信息用于中国境内或境外的远程传输或储存；可能会与保时捷中国及其关联方、商业伙伴或在中国境内或境外的第三方服务商分享客户的个人信息以便提供更优质的服务。
                    </p>
                </div>
                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭隐私政策_pc', 'click', 'source=${source!}'])">
            </div>
        </div>
    </div>
 <#include "../include/footer.ftl">
</div>
</body>
<script>
    var source = '${source!}'
</script>
<script src="${js("header.js")}"></script>
<script src="${js("login.js")}"></script>
</html>