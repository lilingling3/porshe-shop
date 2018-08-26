<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<#if intention == "subscription">
    <title>订阅 Taycan 资讯</title>
<#else>
    <title>完善个人信息</title>
</#if>
<#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/register.css")}">
</head>
<body>

<div class="page-box orderWrap" id="register-form" v-cloak>
<#include "../include/header_page.ftl">

    <div class="common">
        <#if intention == "subscription">
        <img src="${image("pc/register/subscription.jpg")}" class="bg imgMinWithHeight"/>
        <#else>
        <img src="${image("pc/register/reservation.png")}" class="bg imgMinWithHeight"/>
        </#if>
        <div class="content-box" style="height: 100%;width: 500px;min-height: 500px;overflow: hidden">
            <section style="height: 100%">
                <div class="orderInfo" style="height: 100%">
                <#if intention == "subscription">
                <#--subscription-->
                    <div class="orderInfoHeader">
                        <img src="${image("pc/register/subscription_icon.png")}" style="display: inline-block;"
                             class="icon">
                        <span>订阅 Taycan 资讯</span>
                        <span style="width: 290px">为确保您能第一时间获取 Taycan 的精彩资讯，请务必准确填写以下信息。</span>
                    </div>
                <#else>
                <#--reservation-->
                    <div class="orderInfoHeader">
                        <img src="${image("pc/register/reservation_icon.png")}" style="display: inline-block"
                             class="icon">
                        <span>完善个人信息</span>
                        <span style="width: 300px">请务必准确填写以下个人信息，以便我们能及时为您提供服务。</span>
                    </div>
                </#if>
                <#--<i style="display: inline-block;width: 26px"></i>-->
                    <div class="orderInfoForm">
                            <div class="orderInfoCenter">
                                <div class="spaceLine" style="padding-top: 0">
                                    <i class="star starColor">*</i>称<i style="opacity: 0">称谓</i>谓：
                                    <div class="infoCall fontColor">
                                        <vm-select :options="genderList" @select='selectGender'
                                                   v-model='initGenderData' ></vm-select>
                                    </div>
                                    <img src="${image("pc/register/select_down.png")}" class="selectDown">
                                </div>

                                <div class="spaceLine">
                                    <i class="star starColor">*</i>姓：<i style="opacity: 0">称呼姓</i>
                                    <input type="text" name="lastName" class="infoName fontColor" @focus="lastNameFocus"
                                           v-model="lastName" style="margin:0 12px 0 0"/>
                                    <i class="star starColor">*</i>名：
                                    <input type="text" name="firstName" class="infoName fontColor" @focus="firstNameFocus"
                                           v-model="firstName"/>

                                </div>

                                <div class="spaceLine" style="position: relative">
                                    &nbsp;&nbsp;电子邮箱：
                                    <input type="text" name="email" class="email fontColor" v-model="email" @focus="emailFocus"/>
                                    <img src="${image("/pc/generate/tip.png")}" class="info starColor hoverCursor"
                                         v-on:mouseenter="mouseenterEventEmail"
                                         v-on:mouseleave="mouseleaveEventEmail" style="margin-left: 4px">
                                    <p v-show="emailTip" class="tip_mission">以便为您奉上 Taycan 最精彩资讯</p>
                                    <i v-show="emailTip" class="tip_mission_idot"></i>
                                </div>

                                <div class="spaceLine" style="position: relative">
                                    &nbsp;&nbsp;邮寄地址：
                                    <div class="adressInfo fontColor" style="margin-right: 10px;">
                                        <vm-select :options="provinceList" @select='selectProvince'
                                                   v-model='initProvinceData' :first="firstProvince"></vm-select>
                                    </div>
                                    <div class="adressInfo fontColor" >
                                        <vm-select :options="cityList" @select='selectCity'
                                                   v-model='initCityData' :first="firstCity"></vm-select>
                                    </div>
                                    <i style="opacity: 0;display: inline-block;">&nbsp;&nbsp;电子邮箱：</i>
                                    <input type="text" name="adressDetail" class="email adressDetail fontColor" v-model="address"
                                           placeholder="详细地址" @focus="emailFocus"/>
                                    <img src="${image("/pc/generate/tip.png")}" class="info starColor hoverCursor"
                                         v-on:mouseenter="mouseenterEventAdress"
                                         v-on:mouseleave="mouseleaveEventAdress" style="left: 5px;">
                                    <p v-show="adressTip" class="adress_mission">以便为您奉上 Taycan 最精彩资讯</p>
                                    <i v-show="adressTip" class="adress_mission_idot"></i>
                                </div>
                                <div class="spaceLine" style="padding-bottom: 10px">
                                    &nbsp;&nbsp;邮政编码：
                                    <input type="text" name="email" class="email fontColor" v-model="postalCode" maxlength="6" @focus="emailFocus" placeholder="邮政编码"/>
                                </div>
                                <div class="spaceLine" class="circleWrap"  style="position:relative;">
                                    &nbsp;&nbsp; <i @click="readedPolicy" class="bingo" v-show="isReadPolicy"
                                       style="background-image: url(${image("mobile/common/bingo.png")})" onclick="_hmt.push(['_trackEvent', '同意隐私政策_pc', 'click', 'source=${source!}'])"></i>
                                    <i @click="readedPolicy" class="circle" onclick="_hmt.push(['_trackEvent', '同意隐私政策_pc', 'click', 'source=${source!}'])"></i>
                                    <span :class="!readPolicyAndSubmit?'error':'colorBlack'">我已阅读并了解<u class="policy" @click="openMask"
                                                                                                       onclick="_hmt.push(['_trackEvent', '打开隐私政策_pc', 'click', 'source=${source!}'])">隐私政策</u></span>
                                </div>
                                <p class="registerMsg">{{errMsg}}<span style="opacity:0">隐藏</span></p>
                            <#if intention == "subscription">
                                <div class="submitWrap subscriptionSubmit" style="padding:40px 0px 65px 0px">
                                    <a @click="skip('/register/success')" class="btnSelf" onclick="_hmt.push(['_trackEvent', '订阅资讯_pc', 'click', 'source=${source!}'])">订阅资讯</a>
                                </div>
                            <#else>
                                <div class="submitWrap reservationSubmit"
                                     style="padding-top: 30px;box-sizing: border-box;">
                                    <a @click="skip('/order/agreement')" class="payOnline" onclick="_hmt.push(['_trackEvent', '在线支付_pc', 'click', 'source=${source!}'])">在线支付</a>
                                    <span style="text-align: center;text-decoration: none;box-sizing: border-box;padding-top: 20px;display: block;">
                                        或<a @click="skip('/leads')" class="shopOrderD" style="display: inline" onclick="_hmt.push(['_trackEvent', '到店支付_pc', 'click', 'source=${source!}'])">到店支付</a>
                                    </span>

                                </div>
                            </#if>
                            </div>
                    </div>
                </div>
            </section>
        </div>

        <div :class="isShowPolicy?'commonInput':'hidMask'">
            <div class="mask-wrap"></div>
            <div class="mask-unfinish mask-item" id="order_mask_wrap" style="padding: 30px">
                <div style="text-align: left;line-height: 20px;font-size: 13px">
                    <p class="title-plicy">隐私政策</p>
                    <p>
                        保时捷中心将采取一切必要且合理的措施确保客户的个人信息在现有数据保护相关法律下的安全。客户理解并同意，保时捷中心可以为了以下目的收集、使用、存储、处理客户的个人信息，包括但不限于客户姓名、联系方式（如电子邮件、电话/手机号码和地址）以及护照号码和驾驶证详情：
                        <br>
                        (1) 与客户进行与产品、服务和活动有关的沟通交流；
                        <br>
                        (2) 为客户提供保时捷驾驶体验；
                        <br>
                        (3) 改善客户对保时捷的产品、服务和营销工作。
                        客户理解并同意：保时捷中心可能会基于前述目的将客户的个人信息用于中国境内或境外的远程传输或储存；可能会与保时捷中国及其关联方、商业伙伴或在中国境内或境外的第三方服务商分享客户的个人信息以便提供更优质的服务。
                    </p>
                </div>
                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭隐私政策_pc', 'click', 'source=${source!}'])">
            </div>
        </div>

        <div :class="isHasOrder?'commonInput':'hidMask'">
            <div class="mask-wrap"></div>
            <div class="mask-unfinish order-mask">
                <p>温馨提示</p>
                <p>每个账户或身份证只可在线支付一辆 Taycan 的购车意向金，您可查看您已有的在线意向单。如您有意愿支付多辆 Taycan 的购车意向金，请关闭此对话框，选择到店支付。</p>
                <div class="order-mask-footer">
                    <#--<a @click="toShopOrderPage">到店支付</a>-->
                    <a @click="toAccoutPage" class="seeMyOrder" onclick="_hmt.push(['_trackEvent', '查看我的意向单_pc', 'click', 'source=${source!}'])">查看我的意向单</a>
                </div>

                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭已有在线意向单提醒_pc', 'click', 'source=${source!}'])">
            </div>
        </div>
        <div class="questions justify-center hoverCursor" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
            <img src="${image("pc/register/question.png")}">
            常见问题
        </div>
    </div>
<#include "../include/footer.ftl">

</div>
<script>
    var user = ${user};
    var intention = "${intention}";//reservation 预定(默认)   subscription 订阅
</script>
</body>
<script src="${js("header.js")}"></script>
<script src="${js("my_select.js")}"></script>
<script src="${js("register_data.js")}"></script>
<script src="${js("register.js")}"></script>
</html>