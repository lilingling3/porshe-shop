<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>完善您的购车信息</title>
<#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/generate.css")}">
</head>
<body>
<div class="page-box login">
<#include "../include/header_page.ftl">
    <div class="common" id="orderInfo" v-cloak>
        <img class='imgMinWithHeight bg' src="${image("pc/shop-order/order_pic.jpg")}"/>

        <div class="content-box" style="width:520px">
            <div class="login-content generateWrap">
                <div class="wanshan">
                    <div class="border_left">
                        <img src="${image("pc/generate/ownuser.png")}" alt="" style="width:100%;">
                    </div>
                    <div class="wan_info">
                        <p style="font-size:20px;">完善您的购车信息</p>
                        <p class="xinxi">为保障您的权益，请务必准确填写以下个人信息，以便我们能及时为您提供服务。</p>
                    </div>
                </div>
                <div class="orderbox blue_round"  style="background-image:url('${image("pc/generate/order_line.jpg")}');">
                    <div class="onetab">
                        <div style="line-height: 26px;">
                            <#--<span class="tabnum"><i>1</i></span>-->
                            <span>阅读并确认购车意向书</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top:30px;">
                        <div style="line-height: 26px;">
                            <span>完善购车人信息<font style="font-size:12px;">（每个账户或身份证只可在线支付一辆 Taycan 的购车意向金）</font></span>
                        </div>
                        <div class="userbox">
                            <div class="username tipPosition">
                                <label for="" class="muststar">*</label>
                                <label for="" class="iteminfo">
                                    <span style="letter-spacing: 14px">购车人</span> <br/>
                                    <span>姓<i style='opacity: 0'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;</i>名：</span>
                                </label>
                                <input type="text" name="phone" class="user_input" v-model="orderInfo.consignee"
                                       @focus="focusInput">
                                <label for="" class="tiptap"
                                       v-on:mouseenter="mouseenterinfoTip"
                                       v-on:mouseleave="mouseleaveinfoTip">
                                    <img src="${image("pc/generate/tip.png")}" alt="">
                                </label>
                                <label class="iteminfoTip" v-show="userinfoTip" style="left: -38px;">
                                    <p class="iteminfo_mission" style="width: 320px;">为保障您的权益，请务必准确填写身份证上的姓名</p>
                                    <i class="iteminfo_mission_idot" style="left:400px"></i>
                                </label>
                            </div>
                            <div class="username tipPosition">
                                <label for="" class="muststar">*</label>
                                <label for="" class="iteminfo"><span style="letter-spacing: 14px;">购车人</span><br/>身份证号码：</label>
                                <input type="text" name="" class="user_input" v-model="orderInfo.idCard" maxlength="18"
                                       @focus="focusInput">
                                <label for="" class="tiptap"
                                       v-on:mouseenter="mouseenterUsernameTip"
                                       v-on:mouseleave="mouseleaveUsernameTip">
                                    <img src="${image("pc/generate/tip.png")}" alt="">
                                </label>
                                <label class="usernameTip" v-show="usernameTip">
                                    <p class="username_mission">为保障您的权益，请务必准确填写身份证号码</p>
                                    <i class="username_mission_idot"></i>
                                </label>
                            </div>
                            <div class="username">
                                <label for="" class="muststar">*</label>
                                <label for="" class="iteminfo" style="letter-spacing: 3px;" >所在地区：</label>
                                <div class="user_select provinceSelect fontColor">
                                    <vm-select :options="provinceList" @select='selectProvince'
                                               v-model='initProvinceData' :first="firstProvince"></vm-select>
                                </div>
                                <div class="user_select citySelect fontColor">
                                    <vm-select :options="cityList" @select='selectCity' v-model='initCityData'
                                               :first="firstCity"></vm-select>
                                </div>
                            </div>
                            <div class="username" style="margin-bottom:0px;">
                                <label for="" class="muststar">*</label>
                                <label for="" class="iteminfo">保时捷中心：</label>
                                <div class="user_input selectWrap dealerSelect fontColor" style="margin-left: 0px;width: 252px;">
                                    <vm-select :options="dealerList" @select='selectDealer' v-model='initDealerData'
                                               :first="firstDealer"></vm-select>
                                </div>
                            </div>
                            <p class="address">{{selectedDealer.addressCn}}<span style="opacity:0">隐藏</span></p>

                            <div class="username" style="margin-top:20px;">
                                <label for="" class="muststar"></label>
                                <label for="" class="iteminfo">意向金：</label>
                                <label for="" class="yixiangjin">
                                    <span class="bold" style="font-size: 18px">20,000 </span>
                                    <span style="font-size: 14px">RMB</span>
                                </label>
                            </div>
                            <p class="errorTxt">{{errMsg}}<span style="opacity:0">隐藏</span></p>
                            <div class="username">
                                <label for="" class="muststar"></label>
                                <label for="" class="iteminfo"></label>
                                <a class="xiadan_btn" v-on:click="toAffirm" onclick="_hmt.push(['_trackEvent', '完善购车信息提交_pc', 'click', 'source=${source!}'])">确认提交</a>
                            </div>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top: -8px;">
                        <div style="line-height: 26px;">
                            <span>在线支付购车意向金</span>
                        </div>
                    </div>
                    <div class="onetab" style="margin-top:35px;">
                        <div style="line-height: 26px;">
                            <span>完成在线支付</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="commonInput" v-if="isAffirm">
            <div class="mask-wrap"></div>
            <div class="mask-unfinish mask-affirm">
                <div style="padding: 20px 15px 30px;">
                    <p>温馨提示</p>
                    <p>距离 Taycan 实车发布可能还需等待较长时间，请您知悉。</p>
                </div>
                <div class="footer-mask" style="padding: 10px 0;background: #ededed;border-radius:0 0 5px 5px;">
                    <a @click="submitOrder" onclick="_hmt.push(['_trackEvent', '我已知悉_pc', 'click', 'source=${source!}'])">我已知悉</a>
                </div>
                <img src="${image("pc/register/close.png")}" @click="closeMask" class="close_mask" onclick="_hmt.push(['_trackEvent', '关闭我已知悉_pc', 'click', 'source=${source!}'])">
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
<script src="${js("my_select.js")}"></script>
<script src="${js("order.js")}"></script>
<script src="${js("header.js")}"></script>
</html>