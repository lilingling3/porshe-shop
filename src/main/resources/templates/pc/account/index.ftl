<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的账户</title>
<#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/account.css")}">

</head>
<body>
<div class="page-box account" id='register-form'>
<#include "../include/header_page.ftl">
    <div class="account-box clearfix" v-cloak>
        <!-- person-edit 为编辑模式的class 切换为person-show为查看模式的class-->
        <div :class="showEdit?'account-left person-edit':'account-left person-show'">
            <!-- <div class="account-left person-show"> -->
            <div class="account-title ">
                <div class="account-icon">
                    <img src="${image("pc/account/personIcon.png")}" class="justify-center"/>
                </div>
                <p>个人资料</p>
                <div class="operate">
                    <span class="edit" @click="edit" onclick="_hmt.push(['_trackEvent', '编辑个人信息_pc', 'click', 'source=${source!}'])">编辑</span>
                    <span class="cancle" @click="cancle" onclick="_hmt.push(['_trackEvent', '取消编辑个人信息_pc', 'click', 'source=${source!}'])">取消</span>
                    <span class="save" @click="save" onclick="_hmt.push(['_trackEvent', '保存个人信息_pc', 'click', 'source=${source!}'])">保存</span>
                </div>
            </div>
            <section class="person-info">
                <div class="clearfix">
                    <span class="col-sm-3"><i>*</i>称<span class="g-hid">隐藏</span>谓:</span>
                    <div class="col-sm-9" @click="inputClick">
                        <vm-select :options="genderList" @select='selectGender' v-model='initGenderData'></vm-select>
                    </div>
                    <p class="col-sm-9">{{genderValue}}</p>
                </div>
                <div class="person-name clearfix">
                    <span class="col-sm-3 name-edit"><i>*</i>姓:</span>
                    <span class="col-sm-3 name-show"><i>*</i>姓<span class="g-hid">隐藏</span>名:</span>
                    <input class="col-sm-3" type='text' v-model="lastName" @focus="inputClick"/>
                    <span class="col-sm-3 text-center"><i>*</i>名:</span>
                    <input class="col-sm-3" type='text' v-model="firstName" @focus="inputClick"/>
                    <p class="col-sm-9">{{lastName}}{{firstName}}</p>

                </div>
                <div class="clearfix">
                    <span class="col-sm-3"><i class="g-hid">*</i>电子邮箱:</span>
                    <input class="col-sm-9" @focus="inputClick" type='text' v-model="email"/>
                    <p class="col-sm-9 break-all">{{email}}</p>
                </div>
                <div class="clearfix ">
                    <span class="col-sm-3"><i class="g-hid">*</i>邮寄地址:</span>
                    <div class="col-sm-9 address">
                        <div class="col-sm-5" @click="inputClick" style="height: 27px;margin-bottom: 6px;">
                            <vm-select :options="provinceList" @select='selectProvince'
                                       v-model='initProvinceData' :first="firstProvince"></vm-select>

                        </div>
                        <div class="col-sm-2">&nbsp;</div>
                        <div class="col-sm-5" @click="inputClick" style="height: 27px;margin-bottom: 6px;">
                            <vm-select :options="cityList" @select='selectCity' v-model='initCityData' :first="firstCity"></vm-select>

                        </div>
                        <div class="col-sm-3"></div>
                        <input class="col-sm-12" placeholder="详细地址"  @focus="inputClick" type='text' v-model="address"/>
                    </div>
                    <p class="col-sm-9">{{provinceName}} {{cityName}}</p><br>
                    <p class="col-sm-3">&nbsp;</p>
                    <p class="col-sm-9 detail-address break-all">{{address}}</p>
                </div>
                <div class="clearfix">
                    <span class="col-sm-3 "><i class="g-hid">*</i>邮政编码:</span>
                    <input class="col-sm-9" @focus="inputClick" type='text' maxlength="6" v-model="postalCode" placeholder="邮政编码"/>
                    <p class="col-sm-9">{{postalCode}}</p>

                </div>
            </section>
            <p class="error">{{errMsg}}</p>

        </div>

        <div class="account-content">
            <div class="account-title">
                <div class="account-icon">
                    <img src='${image("pc/account/orderIcon.png")}' class="justify-center"/>
                </div>
                <p>您的意向单</p>
            </div>
        <#if orders??&&(orders?length<=2)>
        <#--无订单样式-->
            <section class="noOrder">
                <img src="${image("pc/account/noOrder-bg.png")}">
                <p><span class="btn" @click="toReservation" onclick="_hmt.push(['_trackEvent', '支付购车意向金_pc', 'click', 'source=${source!}'])">支付购车意向金</span></p>
            </section>
        <#else >
        <#--有订单样式-->
            <section class="my-order" v-for="order in orders">
                <div class="order clearfix success">
                    <div class="mask" v-if="order.status == 'refundSuccess'"></div>
                    <div class="order-pic col-sm-7">
                        <img src="${image("pc/account/account1.png")}"/>
                    </div>
                    <div class="order-info col-sm-5">
                        <div class="info-title">
                            <p>编号： {{order.orderSn}} <span class="agreement-btn"
                                                             @click="goInstentionList(order.orderSn)"  onclick="_hmt.push(['_trackEvent', '查看购车意向单_pc', 'click', 'source=${source!}'])"
                                                             v-if="order.status=='paySuccess' || order.status=='refund'">查看购车意向单</span></p>
                            <p>日期： {{order.createDate}}</p>
                        </div>
                        <div class="info">
                            <p><span>购&nbsp;车&nbsp;人&nbsp;姓&nbsp;&nbsp;名:</span><span>{{order.consignee}}</span></p>
                            <p><span>购车人身份证:</span><span>{{order.idCard}}</span></p>
                            <p><span  style="margin-left: 1px">所&nbsp;&nbsp;&nbsp;&nbsp;在&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;区:</span><span>{{order.province}} {{order.city}}</span></p>
                            <p class="clearfix"><span>保&nbsp;时&nbsp;捷&nbsp;中&nbsp;&nbsp;心:</span>
                                <span class="p-center">{{order.dealerName}}</span></p>
                            <p  class="clearfix"><span style="width: 87px"></span><span class="p-center">{{order.companyCn}}</span></p>
                            <p v-if="order.paymentTime"><span>支&nbsp;&nbsp;&nbsp;&nbsp;付&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;间:</span><span>{{order.paymentTime}}</span>
                            </p>
                        </div>
                        <div class="order-statu">
                            <p v-if="order.status == 'paySuccess'">
                                已支付 <span class="btn" @click="goRefund(order.orderSn)"  onclick="_hmt.push(['_trackEvent', '申请线下退款_pc', 'click', 'source=${source!}'])">申请线下退款</span>
                            </p>
                            <p v-if="order.status == 'waitingPay'">
                                待支付
                                <span class="btn" @click="goPay(order.orderSn)"   onclick="_hmt.push(['_trackEvent', '立即支付_pc', 'click', 'source=${source!}'])">立即支付</span>
                                <u class="delete" @click="openOrCloseDialog(order.orderSn)" onclick="_hmt.push(['_trackEvent', '删除意向单_pc', 'click', 'source=${source!}'])">删除意向单</u>
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
                        </div>

                    </div>
                </div>
            </section>
        </#if>


        </div>
    </div>
    <div class="commonInput" v-if="isOpenDialog" v-cloak>
        <div class="mask-wrap"></div>
        <div class="mask-unfinish mask-affirm">
            <div class="dialog-content">
                <p class="dialog-title">温馨提示</p>
                <p class="dialog-detail">删除意向单后不可恢复记录，是否确认继续</p>
            </div>
            <div class="footer-mask">
                <span  class="btn"    @click="deleteOrder" onclick="_hmt.push(['_trackEvent', '确认删除购车意向单_pc', 'click', 'source=${source!}'])">确&nbsp;认</span>
                <span class="btn" @click="openOrCloseDialog" onclick="_hmt.push(['_trackEvent', '取消删除购车意向单_pc', 'click', 'source=${source!}'])">取&nbsp;消</span>
            </div>
            <img src="${image("pc/register/close.png")}" @click="openOrCloseDialog"  onclick="_hmt.push(['_trackEvent', '取消删除购车意向单_pc', 'click', 'source=${source!}'])" class="close_mask">
        </div>
    </div>
</div>
<div class="questions justify-center hoverCursor" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
    <img src="${image("pc/login/question.png")}"/>
    常见问题
</div>
<#include "../include/footer.ftl">
</div>

</body>
<script>
    var user = ${user};
    var orders = ${orders};
    var intention = 'improve';
</script>
<script src="${js("header.js")}"></script>
<script src="${js("my_select.js")}"></script>
<script src="${js("register_data.js")}"></script>
<script src="${js("account.js")}"></script>
</html>




