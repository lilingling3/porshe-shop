<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/register.css")}">
<#if intention == "subscription">
    <title>订阅 Taycan 资讯</title>
<#else>
    <title>完善个人信息</title>
</#if>

    <style>
        <#if intention == "subscription">
        .registerContent {
            background: rgba(51, 51, 51, .9);
        }
        <#else>
       .registerContent {
           background: rgba(0, 0, 0, .75);
       }
        </#if>
    </style>

</head>
<#--style="height: calc(100% - 1.54032rem)"-->
<body>
<#include "../include/header.ftl">

<div class="registerWrap" id="register-form" v-cloak style="height: calc(100% - 1.54032rem)">
    <div :class="classWrap" style="background-image: url(${image("mobile/register/"+intention+".jpg")})">
        <div :class="isSeeDetailPage?'noBannerTop':'banner'" style="position: relative">
    <#if intention == "reservation">
        <div class="seeOrder" @click="seeDetailPage" onclick="_hmt.push(['_trackEvent', '打开两种方式让保时捷Taycan更近一步_mobile', 'click', 'source=${source!}'])">
            <img  class="justify-center" src="${image("mobile/register/toLeft.png")}">
            <span>两种方式让保时捷 Taycan 更近一步</span>
        </div>
    </#if>
            <div class="subscriptionTitle">
        <#if intention == "subscription">
            <div class="leftInfo">
                <img src="${image("mobile/register/register_icon.png")}" style="padding: 0 5px">
            </div>
            <div class="rightInfo">
                <span><b>订阅 Taycan 资讯</b></span>
                <span>为确保您能第一时间获取 Taycan 的精彩资讯，请务必准确填写以下信息</span>
            </div>
        <#else>
            <div class="leftInfo">
                <img src="${image("mobile/register/user.png")}">
            </div>
            <div class="rightInfo">
                <span>完善个人信息</span>
                <span style="width: 300px">请务必准确填写以下个人信息，以便我们能及时为您提供服务。</span>
            </div>
        </#if>

            </div>
            <div class="registerContent">
                    <div class="spaceLine">
                        <i class="star snowflakes">*</i>称<span style="opacity:0 ">隐藏</span>谓:&nbsp;
                        <div class="infoCall">
                            <vm-select :options="genderList" @select='selectGender'
                                       v-model='initGenderData'></vm-select>
                        </div>

                    </div>

                    <div class="spaceLine marginTop">
                        <i class="star snowflakes">*</i>姓:<span style="opacity: 0">隐藏的</span>&nbsp;
                        <input type="text" class="infoName lastname" v-model="lastName" @focus="lastNameFocus">
                        <i class="star snowflakes" style="margin-left: .1rem;">*</i>名:
                        <input type="text" class="infoName" v-model="firstName" @focus="firstNameFocus">
                    </div>

                    <div class="spaceLine marginTop" style="margin-left: .281804rem">
                        电子邮箱:&nbsp;
                        <input type="text" class="emailInput paddingTop" v-model="email" @focus="emailFocus" style="padding-right: 10px">
                    </div>

                    <div class="spaceLine marginTop" style="margin-left: .281804rem">
                        邮寄地址:&nbsp;
                        <div class="infoName" style=" width: 2.5rem;">
                            <vm-select :options="provinceList" @select='selectProvince' v-model='initProvinceData'
                                       :first="firstProvince"></vm-select>
                        </div>
                        &nbsp;<div class="infoName" style="width: 2.5rem;margin-left: .4rem;">
                        <vm-select :options="cityList" @select='selectCity' v-model='initCityData'
                                   :first="firstCity"></vm-select>
                    </div>

                        <span style="opacity: 0"> 邮寄地址:&nbsp;&nbsp;</span><input type="text" name="adressDetail"
                                                                                 class="emailInput marginTop specalInput paddingTop"
                                                                                 placeholder="详细地址" v-model="address" style="padding-right: 10px">
                    </div>
                    <div class="spaceLine marginTop" style="margin-left: .281804rem">
                        邮政编码:&nbsp;
                        <input type="text" class="emailInput paddingTop" maxlength="6" v-model="postalCode" placeholder="邮政编码">
                    </div>
                    <div class="spaceLine marginTop" style="position: relative;left: 10px">
                        <i @click="readedPolicy" class="bingo registerBingo" v-show="isReadPolicy"
                           style="background-image: url(${image("mobile/common/bingo.png")})" onclick="_hmt.push(['_trackEvent', '同意隐私政策_mobile', 'click', 'source=${source!}'])"></i>
                        <i @click="readedPolicy" class="circle registerCircle" onclick="_hmt.push(['_trackEvent', '同意隐私政策_mobile', 'click', 'source=${source!}'])"></i>
                        <span :class="!readPolicyAndSubmit?'error':'colorBlack'" style="font-size: 12px">我已阅读并了解<u
                                @touchstart="openMask"
                                :class="!readPolicyAndSubmit?'error':'policy'" onclick="_hmt.push(['_trackEvent', '打开隐私政策_mobile', 'click', 'source=${source!}'])">隐私政策</u></span>
                    </div>
                <#if intention == "subscription">
                       <div class="subscriptionSubmit">
                           <a @touchstart="skip('/register/success')" style="text-decoration: none" onclick="_hmt.push(['_trackEvent', '订阅资讯_mobile', 'click', 'source=${source!}'])">订阅资讯</a>
                       </div>
                <#else>
                      <div class="reservationSubmit">
                          <a class="onlineOrder" @touchstart="skip('/order/agreement')"
                             style="text-decoration: none" onclick="_hmt.push(['_trackEvent', '在线支付_mobile', 'click', 'source=${source!}'])">在线支付</a>
                          <span style="font-size: 16px" class="shopOrder">或<u @touchstart="skip('/leads')" style="text-decoration: underline" onclick="_hmt.push(['_trackEvent', '到店支付_mobile', 'click', 'source=${source!}'])">到店支付</u></span>
                      </div>
                </#if>
            </div>
        </div>
  <#if intention == "reservation">
    <div :class="isSeeDetailPage?'newBoxSpecial newBoxBanner':'newBoxSpecial newBoxBanner newBoxTrans'">
        <img src="${image("mobile/register/seeDetail.png")}" class='seeDetail-img' id="seeDetailImg"/>
        <div class="backOrder" @click="toBackPage" id="backOrder" onclick="_hmt.push(['_trackEvent', '返回完善个人信息_mobile', 'click', 'source=${source!}'])">
            <img src="${image("mobile/register/right_icon.png")}"/>
        </div>
    </div>
  </#if>

        <div :class="isShowPolicy?'commonInput':'hidMask'" @touchmove="handleTouchMove">
            <#include "../include/policy.ftl">
        </div>
        <div :class="isHasOrder?'commonInput':'hidMask'" @touchmove="handleTouchMove">
            <div class="mask-wrap"></div>
            <div class="mask-unfinish order-mask">
                <p>温馨提示</p>
                <p style="font-size: 14px">每个账户或身份证只可在线支付一辆 Taycan 的购车意向金，您可查看您已有的在线意向单。如您有意愿支付多辆 Taycan 的购车意向金，请关闭此对话框，选择到店支付。</p>
                <div class="order-mask-footer">
                    <#--<a @touchstart="toShopOrderPage">到店支付</a>-->
                    <a @touchstart="toAccoutPage" class="seeMyOrder" onclick="_hmt.push(['_trackEvent', '查看我的意向单_mobile', 'click', 'source=${source!}'])">查看我的意向单</a>
                </div>

                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭已有在线意向单提醒_mobile', 'click', 'source=${source!}'])">
            </div>
        </div>
    </div>
    <div v-show="!isSeeDetailPage">
    <#include "../include/footer.ftl">
    </div>
</div>


<script>
    var user = ${user};
    var intention = "${intention}";//reservation 预定   subscription 订阅

</script>
<script src="${js("my_select.js")}"></script>
<script src="${js("register_data.js")}"></script>
<script src="${js("register.js")}"></script>
</body>
</html>