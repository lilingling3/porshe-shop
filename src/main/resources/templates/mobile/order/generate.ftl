<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/generate.css")}">
</head>
<body>
<#include "../include/header.ftl">
<div class="generateWrap" id="orderInfo" v-cloak>
    <div class="banner" style="background-image: url(${image("mobile/common/porsche_bg.png")})">
        <div class="subscriptionTitle">
            <div class="leftInfo">
                <img src="${image("mobile/register/user.png")}">
            </div>
            <div class="rightInfo">
                <span>完善您的购车信息</span>
                <span>为保障您的权益，请务必准确填写以下个人信息，以便我们能及时为您提供服务。</span>
            </div>
        </div>

        <div class="agreementConent">
            <div class="agreementHeader">
                <div class="flowLine" style="background-image: url(${image('mobile/common/dot.png')})"></div>
                <div class="flowIdot">

                      <span class="dot justify-center" style="background-image: url(${image("mobile/common/2.png")})"></span>
                    完善购车人信息
                </div>
                <div class="flowLine long" style="background-image: url(${image('mobile/common/dot.png')})"></div>
                <img src="${image('mobile/order/right_agreement.png')}" class="rightArrow"/>
                <span class="titleSmall">（每个账户或身份证只可在线支付一辆 Taycan 的购车意向金）</span>
            </div>
            <div class="buyCarInfo">
                <div class="spaceLine">
                    <label for="username">
                        <i class="star snowflakes">*</i>
                        <div class="usernameLable">
                            <span>购<i style="opacity: 0">份</i>车<i style="opacity: 0">份</i>人</span>
                            <span>姓<i style="opacity: 0">份证号</i>名:</span>
                        </div>

                    </label>
                    <input type="text" name="username" class="usernameInput" v-model="orderInfo.consignee">
                </div>

                <div class="spaceLine">
                    <label for="idCard">
                        <i class="star snowflakes">*</i>
                        <div class="usernameLable">
                            <span>购<i style="opacity: 0">份</i>车<i style="opacity: 0">份</i>人</span>
                            <span>身份证号码:</span>
                        </div>

                    </label>
                    <input type="text" name="idCard" class="usernameInput" v-model="orderInfo.idCard" maxlength="18">
                </div>

                <div class="spaceLine clearfix">
                    <label class="areaLable">
                    <i class="areaStar snowflakes">*</i>所&nbsp;&nbsp;在&nbsp;地&nbsp;&nbsp;区:
                    </label>
                    <div class="area-box clearfix">
                    <div class="provinceSelect fontColor col-sm-5" style="width: 45%">
                        <vm-select :options="provinceList" @select='selectProvince' v-model='initProvinceData' :first="firstProvince"></vm-select>
                    </div>
                    <div class="citySelect fontColor col-sm-5" style="width: 45%">
                        <vm-select :options="cityList" @select='selectCity' v-model='initCityData' :first="firstCity"></vm-select>
                    </div>
                    </div>
                </div>

                <div class="porsheCenter clearfix">
                    <label class="areaLable">
                        <i class="areaStar snowflakes" >*</i><span>保时捷中心:</span>
                    </label>
                    <div class="centerSelect fontColor">
                        <vm-select :options="dealerList" @select='selectDealer' v-model='initDealerData' :first="firstDealer"></vm-select>
                    </div>
                    <p class="textCenter">{{selectedDealer.addressCn}}</p>
                </div>

                <div class="payMoney">
                    <i class="areaStar snowflakes" style="opacity: 0;margin-right: 0;">*</i>
                    <span>意<i style="opacity: 0">隐</i>向<i style="opacity: 0">隐</i>金:</span>&nbsp;
                    <span class="bold money boldStyle">20,000 <span class="boldStyle" style="font-size: 14px">RMB<span>
                </div>
                <a class="toSumit" @touchstart="toAffirm" onclick="_hmt.push(['_trackEvent', '完善购车信息提交_mobile', 'click', 'source=${source!}'])">确认提交</a>
            </div>
        </div>
    </div>

    <div class="commonInput" v-if="isAffirm">
        <div class="mask-wrap"></div>
        <div class="mask-unfinish mask-affirm">
            <div style="padding:20px 0px 35px;box-sizing: border-box">
                <p style="font-size: 14px">温馨提示</p>
                <p>距离 Taycan 实车发布可能还需等待较长时间，请您知悉。</p>
            </div>
            <div class="footer-mask" style="padding: 10px 0;background: #ededed;border-radius: 0 0 5px 5px;">
                <a @touchstart="submitOrder" onclick="_hmt.push(['_trackEvent', '我已知悉_mobile', 'click', 'source=${source!}'])">我已知悉</a>
            </div>
            <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭我已知悉_mobile', 'click', 'source=${source!}'])">
        </div>
    </div>
</div>
<#include "../include/footer.ftl">
<script src="${js("my_select.js")}"></script>
<script src="${js("order.js")}"></script>
</body>
</html>