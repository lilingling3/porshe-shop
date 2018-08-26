<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>到店支付</title>
<#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/shopOrder.css")}">
</head>


<body>
<div class="page-box arrival shop-order">
<#include "../include/header_page.ftl">
    <div class="common" id="leads_main" v-cloak>
        <img src="${image("pc/shop-order/shop-order-bg.png")}" class="bg imgMinWithHeight"/>
        <div class="content-box arrival-box">
            <div class="arrival-title">
                <div class="arrival-icon">
                    <img class="justify-center" src="${image("pc/shop-order/arrivalIcon.png")}"/>
                </div>
                <p>到店支付</p>
                <p>请选择您意向的保时捷中心，稍后我们的工作人员</br>将通过您注册的手机号与您联系。</p>
            </div>
            <div class="timezone" style="background-image: url(${image("pc/shop-order/line.png")};">
                <div class="time">
                    <div>
                        <p>选择保时捷中心</p>
                        <section class="dealer-section">
                            <p>请选择区域</p>
                            <div class="province">
                                <label for="province"><i class="snowflakes">*</i>省/市:</label>
                                <!-- select -begin -->
                                <div class="my-select">
                                    <p class='select-value' @click="showProvinceOptions">
                                        <span v-show="!selectedProvince.hasOwnProperty('nameCn')">请选择</span>
                                        <span v-show="selectedProvince.hasOwnProperty('nameCn')">{{selectedProvince.nameCn}}</span>
                                        <img src="${image("mobile/common/down.png")}"/>
                                    </p>
                                    <div v-bind:class="isShowProvinceOptions?'select-option':'select-option hid'">
                                        <ul @click="closeProvinceOptions">
                                            <li @click="selectProvince(-1)" v-bind:class="selectedProvince===-1?'selected':''">请选择</li>
                                            <li v-for="province in provinces" v-bind:value="province"
                                                v-bind:class="selectedProvince===province?'selected':''"
                                                @click="selectProvince(province)">
                                                {{province.nameCn}}
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                            </div>
                            <div class="city">
                                <label for="city"><i class="snowflakes">*</i>市/区:</label>
                                <!-- select -begin -->
                                <div class="my-select">
                                    <p class='select-value' @click="showCityOptions">
                                        <span v-show="!selectedCity.hasOwnProperty('nameCn')">请选择</span>
                                        <span v-show="selectedCity.hasOwnProperty('nameCn')">{{selectedCity.nameCn}}</span>
                                        <img src="${image("mobile/common/down.png")}"/>
                                    </p>
                                    <div v-cloak v-bind:class="isShowCityOptions?'select-option':'select-option hid'">
                                        <ul @click="closeCityOptions">
                                            <li @click="selectCity(-1)" v-bind:class="selectedCity===-1?'selected':''">请选择</li>
                                            <li v-for="city in cities" v-bind:value="city"
                                                v-bind:class="selectedCity===city?'selected':''"
                                                @click="selectCity(city)">
                                                {{city.nameCn}}
                                            </li>
                                        </ul>

                                    </div>
                                </div>

                            </div>
                        </section>
                    </div>
                </div>
                <div class="time" style="top: 295px;">
                    <div>
                        <p>保时捷中心电话邀约</p>

                    </div>
                </div>
                <div class="time" style="top: 355px;">
                    <div>
                        <p>到店签署购车意向单</p>
                    </div>
                </div>
                <div class="time" style="top: 415px;">
                    <div>
                        <p>到店支付购车意向金</p>
                    </div>
                </div>
                <div class="time" style="top: 475px;">
                    <div>
                        <p>完成到店支付</p>
                    </div>
                </div>
            </div>

        </div>
        <div class="dealer-box" v-show="dealers.length > 0">
            <p>请选择保时捷中心</p>
            <div>
                <ul>
                    <li class="dealer-option" v-for="dealer in dealers">
                        <p>
                            <span @click="selectDealer(dealer)">
                                <i class="bingo" v-show="dealer.isSelected===true"
                                   style="background-image: url(${image("pc/generate/bingo.png")};"></i>
                                <i class="circle"></i>
                            </span>
                            {{dealer.nameCn}}
                        </p>
                        <p>{{dealer.addressCn}}</p>
                    </li>
                </ul>
            </div>
            <p class="warn-info clearfix"><i>*</i><span>如您勾选多家保时捷中心，将会有多家保时捷中心分别与您取得联系</span></p>
            <p id="errorMessage" class="error-msg">{{errorMessage}}<span style="opacity:0">隐藏</span></p>
            <p class="login-btn" @click="submit"  onclick="_hmt.push(['_trackEvent', '提交到店支付_pc', 'click', 'source=${source!}'])"><span>提交</span></p>
        </div>
    </div>

<#include "../include/footer.ftl">
</div>
<div class="questions" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
    <img src="${image("pc/login/question.png")}"/>
    常见问题
</div>

</body>
<script src="${js("leads.js")}"></script>
<script src="${js("header.js")}"></script>
<script>
    document.addEventListener('click', function(e) {
        if(e.target.className.indexOf('select-value')<0 ){
            shopOrderVm.isShowProvinceOptions = false;
            shopOrderVm.isShowCityOptions = false;
        }
    });
</script>
</html>