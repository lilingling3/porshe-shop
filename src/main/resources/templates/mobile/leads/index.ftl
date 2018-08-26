<!DOCTYPE html>
<html lang="en">
<head>
   <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/shop_order.css")}">
    <title>到店支付</title>
</head>
<body>
 <#include "../include/header.ftl">
<section class="wrapper shop-order" style="background-image: url(${image("mobile/shop-order/bg.png")})">
    <div class="page-title">
        <img class="title-icon justify-center" src="${image("mobile/shop-order/weizhi.png")})"/>
        <div class="title-info">
            <h1>到店支付</h1>
            <p>请选择您意向的保时捷中心，稍后我们的工作人员将通过您注册的手机号与您联系。</p>
        </div>
    </div>

    <div class="content" id="leads_main" v-cloak>
        <div class="line">
            <h1><span class="dot justify-center" style="background-image: url(${image("mobile/common/1.png")})"></span>选择保时捷中心
                <i style="background-image: url(${image("mobile/common/dot.png")})"></i>
                <img src="${image("mobile/common/right.png")}" class="justify-center"/>
            </h1>
        </div>
        <div class="form">
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
                <!-- select -end -->
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

                <section :class="selectedCity!=-1?'dealer-box':'hid'" >
                    <p>请选择保时捷中心</p>
                    <ul>
                        <li class="dealer-option" v-for="dealer in dealers">
                            <p @click="selectDealer(dealer)">
                                <i class="bingo" v-show="dealer.isSelected===true" style="background-image: url(${image("mobile/common/bingo.png")});"></i>
                                <i class="circle"></i>
                                {{dealer.nameCn}}
                            </p>
                            <p>{{dealer.addressCn}}</p>
                        </li>
                    </ul>
                    <p class='warm-info' ><i class="snowflakes">*</i>如您勾选多家保时捷中心，将会有多家保时捷中心分别与您取得联系</p>
                    <div class='btn-box'><span class="btn"  @touchstart="submit"  onclick="_hmt.push(['_trackEvent', '提交到店支付_mobile', 'click', 'source=${source!}'])">提交</span></div>
                </section>
        </div>
    </div>

</section>
 <#include "../include/footer.ftl">
<script src="${js("leads.js")}"></script>
<script>
    // alert( window.screen.height);

    document.addEventListener('click', function(e) {
        if(e.target.className.indexOf('select-value')<0 ){
            shopOrderVm.isShowProvinceOptions = false;
            shopOrderVm.isShowCityOptions = false;
        }
    });
</script>
</body>
</html>