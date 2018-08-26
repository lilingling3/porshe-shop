<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>申请退款</title>
<#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/initiate_refund.css")}">
</head>
<body>
<div class=" initiate-refund">
<#include "../include/header_page.ftl">

<#--<img class='bg' src="${image("pc/account/refundbg.png")}"/>-->
    <div class="initiate-refund-box" v-cloak>
        <img class='bg' src= "${image("pc/account/refund-bg.jpg")}"/>
        <div class="back">
            <p>
                <img src= "${image("pc/account/arrow-left.png")}"/>
                <span @click="account" onclick="_hmt.push(['_trackEvent', '返回我的账户_pc', 'click', 'source=${source!}'])">返回我的账户</span>
            </p>
        </div>

        <div class="wrapper " v-if="!refundSuccess">

            <p class="title">退款理由</p>
            <p style="margin-bottom: 30px">为进一步提升我们的服务质量，请告知我们您申请退款的原因（可多选）</p>

            <div class="refund-list clearfix">
                <p class="col-sm-4" @click="choose('buyOtherCar',buyOtherCar)">
                    <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};"  v-if="buyOtherCar"></i>
                    <i class="circle"></i>
                    <span>已购其它车型</span>
                </p>
                <p class="col-sm-4" @click="choose('waittingLongTime',waittingLongTime)">
                    <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};" v-if="waittingLongTime"></i>
                    <i class="circle"></i>
                    <span>等待期过长</span>
                </p>
                <p class="col-sm-4" @click="choose('postponePlan',postponePlan)">
                    <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};" v-if="postponePlan"></i>
                    <i class="circle"></i>
                    <span>暂缓购车计划</span>
                </p>

                <p class="col-sm-4" @click="choose('userInfoChange',userInfoChange)">
                    <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};" v-if="userInfoChange"></i>
                    <i class="circle"></i>
                    <span>变更购车人信息</span>
                </p>

                <p class="col-sm-4" @click="choose('pickCarCityChange',pickCarCityChange)">
                    <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};" v-if="pickCarCityChange"></i>
                    <i class="circle"></i>
                    <span>变更提车城市/保时捷中心</span>
                </p>

            </div>
            <div class="other-refund" >
               <p @click="choose('other',other)"> <i class="bingo"  style="background-image: url(${image("pc/generate/bingo.png")};" v-if="other"></i>
                <i class="circle"></i>
                <span>如有其它原因请留下您的反馈</span>
                </p>
                <textarea v-model = 'detail' @focus="selectOther"></textarea>
            </div>
            <p class="error"><span>{{errorMessage}}</span><span style="opacity:0">隐藏</span></p>

            <div class="operate">
                <span class="btn" @click="account"  onclick="_hmt.push(['_trackEvent', '取消申请退款_pc', 'click', 'source=${source!}'])">取消</span>
                <span class="btn" @click="submit"  onclick="_hmt.push(['_trackEvent', '确认申请退款_pc', 'click', 'source=${source!}'])">确认申请退款</span>
            </div>
        </div>
        <div class="wrapper" v-if="refundSuccess">
            <p class="title">您的退款申请已提交</p>
            <span>{{order.dealerName}}将会通过您注册的手机号码 {{order.phone}} 与您尽快取得联系，请注意接听电话。</span>
            <div class="operate">
                <span class="btn" @click="account" onclick="_hmt.push(['_trackEvent', '查看我的意向单状态_pc', 'click', 'source=${source!}'])">查看我的意向单状态</span>
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
    var order = ${order};
</script>
<script src="${js("refund.js")}"></script>
<script src="${js("header.js")}"></script>
</html>




