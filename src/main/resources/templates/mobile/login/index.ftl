<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>
      <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("common/swiper.min.css")}">
    <link rel="stylesheet" href="${css("mobile/login.css")}">
    <script src="${js("common/swiper.min.js")}"></script>
</head>
<body>
<#include "../include/header.ftl">
<div class="login" v-cloak>
    <div class="wrapper">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" style="background-image: url(${image("mobile/login/login1.png")})"></div>
                <div class="swiper-slide" style="background-image: url(${image("mobile/login/login2.png")})"></div>
                <div class="swiper-slide" style="background-image: url(${image("mobile/login/login3.png")})"></div>
            </div>
        </div>

        <div class="login-form">

            <p><img src="${image("mobile/login/tx.png")}"/></p>
            <div class="phoneBox">
                <label for="phone">手机号码：</label>
                <input type="text" name="phone" v-model="phone" placeholder="手机账号为登录账号，注册后不可修改">
            </div>
            <div class="verificationCode">
                <label for="verificationCode">验&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;码：</label>
                <p>
                    <input type="number" name="verificationCode" v-model="verificationCode">
                    <span v-show="show" @touchstart="getVerificationCode"  onclick="_hmt.push(['_trackEvent', '获取验证码_mobile', 'click', 'source=${source!}'])">获取验证码</span>
                    <span v-show="!show"  style="background: #000">{{countdown}}秒后可重发</span>
                </p>
            </div>
            <div class="info">
                <p>
                    <i @click="readedPolicy" class="bingo" v-show="isReadPolicy"  onclick="_hmt.push(['_trackEvent', '同意隐私政策_mobile', 'click', 'source=${source!}'])"
                       style="background-image: url(${image("mobile/common/bingo.png")})"></i>
                    <i @click="readedPolicy" class="circle"></i>
                    <span :class="isReadPolicyError?'error':''">我已阅读并了解<u class="policy" @touchstart="openMask" onclick="_hmt.push(['_trackEvent', '打开隐私政策_mobile', 'click', 'source=${source!}'])">隐私政策</u>
                    </span>
                </p>

                <p>温馨提示：首次登录的手机号将自动为您创建账户</p>
            </div>
            <div class="btn-box"><span class="btn" @touchstart="login" onclick="_hmt.push(['_trackEvent', '登陆_mobile', 'click', 'source=${source!}'])">登&nbsp;&nbsp;录</span></div>
        </div>
    </div>

    <div :class="isShowPolicy?'commonInput':'hidMask'" @touchmove="handleTouchMove">
   <#include "../include/policy.ftl">
    </div>
 <#include "../include/footer.ftl">
</div>
<script>
    var source = "${source!}";
    var alertMsg = alertMsg;
</script>
<script src="${js("login.js")}"></script>
</body>
</html>