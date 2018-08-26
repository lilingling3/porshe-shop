<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/account.css")}">
    <title>我的账户</title>
</head>
<body>
<#include "../include/header.ftl">
<div class="wrapper account" id="account" v-cloak>
    <section :class="showEdit?'account-info person-edit section-one':'account-info person-show section-one'">
        <div class="account-title">
            <div class="account-icon ">
                <img src="${image("mobile/common/person.png")}"/>
            </div>
            <p class="bold">个人资料</p>
            <div class="operate">
                <span class="edit" @touchstart="edit" onclick="_hmt.push(['_trackEvent', '编辑个人信息_mobile', 'click', 'source=${source!}'])">编辑</span>
                <span class="cancle" @touchstart="cancle" onclick="_hmt.push(['_trackEvent', '取消编辑个人信息_mobile', 'click', 'source=${source!}'])">取消</span>
                <span class="save" @touchstart="save" onclick="_hmt.push(['_trackEvent', '保存个人信息_mobile', 'click', 'source=${source!}'])">保存</span>
            </div>
        </div>
        <section class="person-info">
            <div class="clearfix">
                <span class="col-sm-3"><i>*</i>称<span class="hid-opacity">隐藏</span>谓:</span>
                <div class="col-sm-9 chengwei">
                    <vm-select :options="genderList" @select='selectGender' v-model='initGenderData'></vm-select>
                </div>
                <span class="col-sm-9 value">{{genderValue}}</span>
            </div>
            <div class="person-name clearfix">
                <span class="col-sm-3 name-edit"><i>*</i>姓:</span>
                <span class="col-sm-3 name-show"><i>*</i>姓<span class="hid-opacity">隐藏</span>名:</span>
                <input class="col-sm-3" type='text' v-model="lastName"/>
                <span class="col-sm-3 text-center"><i>*</i>名:</span>
                <input class="col-sm-3" type='text' v-model="firstName"/>
                <span class="col-sm-9 value">{{lastName}}{{firstName}}</span>
            </div>
            <div class="clearfix  email-box">
                <span class="col-sm-3"><i class="g-hid">*</i>电子邮箱:</span>
                <input class="col-sm-9" type='text' v-model="email"/>
                <span class="col-sm-9 value"  style="word-break: break-all;">{{email}}</span>
            </div>
            <div class="clearfix">
                <span class="col-sm-3"><i class="g-hid">*</i>邮寄地址:</span>
                <div class="col-sm-9 address">
                    <div class="col-sm-5">
                        <vm-select :options="provinceList" @select='selectProvince' v-model='initProvinceData' :first="firstProvince"></vm-select>
                    </div>
                    <div class="col-sm-2">&nbsp;</div>
                    <div class="col-sm-5">
                        <vm-select :options="cityList" @select='selectCity' v-model='initCityData' :first="firstCity"></vm-select>
                    </div>
                    <input class="col-sm-12" type='text' v-model="address" placeholder="详细地址"/>
                </div>
                <span class="col-sm-9 value">{{provinceName}}{{cityName}}</span><br>
                <p class="col-sm-3 value">&nbsp;</p>
                <span class="col-sm-9 value"  style="word-break: break-all;" >{{address}}</span>
            </div>
            <div class="clearfix">
                <span class='col-sm-3'><i class="g-hid">*</i>邮政编码:</span>
                <input class="col-sm-9" type='text' maxlength="6" v-model="postalCode" placeholder="邮政编码"/>
                <span class="col-sm-9 value">{{postalCode}}</span>
            </div>
        </section>
    </section>
    <!--  -->
    <section class="order-box">
        <div class="order-title ">
            <div class="account-icon">
                <img  src="${image("mobile/common/gouwuche.png")}" class=" justify-center"/>
            </div>
            <p class="bold justify-center">您的意向单</p>
        </div>
        <!-- 没有订单显示 begin -->
    <#if orders??&&(orders?length<=2)>
        <div class="no-order">
            <img src="${image("mobile/account/no-order.jpg")}">
            <div class="btn-box"><span class="btn" @touchstart="toReservation" onclick="_hmt.push(['_trackEvent', '支付购车意向金_mobile', 'click', 'source=${source!}'])">支付购车意向金</span></div>
        </div>
        <!-- 没有订单显示 end -->
    <#else>

        <!-- 订单显示 begin -->
        <!-- 一个order一个状态，后端接的时候用一个做循环即可 -->
        <div class="my-order">
            <section class="order" v-for="order in orders">
                <div  v-if="order.status == 'refundSuccess'" class="mask"></div>
                <p class="bold">
                    保时捷 Taycan 购车意向金
                    <span >20,000 RMB</span>
                </p>

                <div class="order-status">
                    <p v-if="order.status == 'paySuccess'">
                        已支付 <span class="btn" @touchstart="goRefund(order.orderSn)" onclick="_hmt.push(['_trackEvent', '申请线下退款_mobile', 'click', 'source=${source!}'])">申请线下退款</span>
                    </p>
                    <p v-if="order.status == 'waitingPay'">
                        待支付
                        <span class="btn" @touchstart="goPay(order.orderSn)"  onclick="_hmt.push(['_trackEvent', '立即支付_mobile', 'click', 'source=${source!}'])">立即支付</span>
                        <u class="delete" @touchstart="openOrCloseDialog(order.orderSn)" onclick="_hmt.push(['_trackEvent', '删除意向单_mobile', 'click', 'source=${source!}'])">删除意向单</u>
                    </p>
                    <p v-if="order.status == 'refund'">
                        退款申请中
                    </p>
                    <p v-if="order.status == 'refundSuccess'">
                        退款成功
                    </p>
                    <p v-if="order.status == 'payFailure'">
                        支付失败
                    </p>
                    <p>编号:{{order.orderSn}} <span>日期:{{order.createDate}}</span></p>
                </div>
                <div class="order-info">
                    <p><span>购&nbsp;车&nbsp;人&nbsp;姓&nbsp;&nbsp;名:</span><span>{{order.consignee}}</span><span class="s-btn btn"
                                                                                @touchstart="goInstentionList(order.orderSn)"  onclick="_hmt.push(['_trackEvent', '查看购车意向单_mobile', 'click', 'source=${source!}'])"
                                                                                v-if="order.status=='paySuccess' || order.status=='refund'"> 查看购车意向单</span>
                    </p>
                    <p><span>购车人身份证:</span><span>{{order.idCard}}</span></p>
                    <p><span>所&nbsp;&nbsp;&nbsp;在&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;区:</span><span>{{order.province}} {{order.city}}</span></p>
                    <p class="clearfix"><span>保&nbsp;时&nbsp;捷&nbsp;中&nbsp;心:</span><span class="p-center">{{order.dealerName}}</span></p>
                    <p class="clearfix"><span></span><span class="p-center">{{order.companyCn}}</span></p>
                    <p v-if="order.paymentTime"><span>支&nbsp;&nbsp;&nbsp;付&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;间:</span><span>{{order.paymentTime}}</span></p>
                </div>
            </section>
        </div>
    </#if>
    </section>
    <div class="commonInput" v-if="isOpenDialog" v-cloak  @touchmove="handleTouchMove">
        <div class="mask-wrap"></div>
        <div class="mask-unfinish mask-affirm">
            <div class="dialog-content">
                <p class="dialog-title">温馨提示</p>
                <p class="dialog-detail">删除意向单后不可恢复记录，是否确认继续</p>
            </div>
            <div class="footer-mask">
                <span  class="btn"    @touchstart="deleteOrder" onclick="_hmt.push(['_trackEvent', '确认删除购车意向单_mobile', 'click', 'source=${source!}'])">确&nbsp;认</span>
                <span class="btn" @touchstart="openOrCloseDialog" onclick="_hmt.push(['_trackEvent', '取消删除购车意向单_mobile', 'click', 'source=${source!}'])">取&nbsp;消</span>
            </div>
            <img src="${image("pc/register/close.png")}" @touchstart="openOrCloseDialog" onclick="_hmt.push(['_trackEvent', '取消删除购车意向单_mobile', 'click', 'source=${source!}'])" class="close_mask">
        </div>
    </div>
</div>
<#include "../include/footer.ftl">

<script>
    var user = ${user};
    var orders = ${orders};
    var intention = 'improve'
</script>
<script src="${js("my_select.js")}"></script>
<script src="${js("register_data.js")}"></script>
<script src="${js("account.js")}"></script>
</body>
</html>