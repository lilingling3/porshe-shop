<!DOCTYPE html>
<html lang="en" >
<head>
    <#include "../include/link.ftl">
    <link rel="stylesheet" href="${css("mobile/initiate_refund.css")}">
    <title>申请退款</title>
</head>
<body >
<#include "../include/header.ftl">
<div class="wrapper initiate-refund" style="background-image:  url(${image("mobile/order/tuikuan-bg.png")})" v-cloak>
    <p class="back" style="background-image: url(${image("mobile/common/back-grey.png")})" @click="account" onclick="_hmt.push(['_trackEvent', '返回我的账户_mobile', 'click', 'source=${source!}'])">
        返回我的账户</p>
    <#--退款理由  begin-->
    <section class="refund" v-if="!refundSuccess">
        <p >退款理由</p>
        <p>为进一步提升我们的服务质量，请告知我们您申请退款的原因(可多选)</p>
        <ul class="list">
            <li @click="choose('buyOtherCar',buyOtherCar)">
                <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="buyOtherCar"></i>
                <i class="circle"></i>
                <span >已购其它车型</span>
            </li>
            <li @click="choose('waittingLongTime',waittingLongTime)">
                <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="waittingLongTime"></i>
                <i class="circle"></i>
                <span >等待期过长</span>
            </li>
            <li @click="choose('postponePlan',postponePlan)">
                <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="postponePlan"></i>
                <i class="circle"></i>
                <span >暂缓购车计划</span>
            </li>
            <li @click="choose('userInfoChange',userInfoChange)">
                <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="userInfoChange"></i>
                <i class="circle"></i>
                <span >变更购车人信息</span>
            </li>
            <li @click="choose('pickCarCityChange',pickCarCityChange)">
                <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="pickCarCityChange"></i>
                <i class="circle"></i>
                <span >变更提车城市/保时捷中心</span>
            </li>
            <li class="other">
                <div @click="choose('other',other)">
                    <i class="bingo" style="background-image: url(${image("mobile/common/bingo.png")});" v-if="other"></i>
                    <i class="circle"></i>
                    <span>如有其它原因请留下您的反馈</span>
                </div>
                <textarea v-model = 'detail' @focus="selectOther"></textarea>
            </li>
        </ul>
        <div class="btn-box">
            <span class="btn" @click="account" onclick="_hmt.push(['_trackEvent', '取消申请退款_mobile', 'click', 'source=${source!}'])">取消</span>
            <span class="btn" @click="submit" onclick="_hmt.push(['_trackEvent', '确认申请退款_mobile', 'click', 'source=${source!}'])">确认申请退款</span>
        </div>
    </section>
<#--退款理由  end-->
<#--退款成功  begin-->

    <section class="refundSuccess" v-if="refundSuccess">
        <p>您的退款申请已提交</p>
        <p>{{order.dealerName}}将会通过您注册的手机号码 {{order.phone}} 与您尽快取得联系，请注意接听电话</p>
        <div class="btn-box"><span class="btn" @click="account" onclick="_hmt.push(['_trackEvent', '查看我的意向单状态_mobile', 'click', 'source=${source!}'])">查看我的意向单状态</span></div>
    </section>
<#--退款成功  end-->

</div>
<#include "../include/footer.ftl">
<script>
    var order = ${order};
</script>
<script src="${js("refund.js")}"></script>
</body>
</html>