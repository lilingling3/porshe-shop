<header class="header">
    <img src="${image("mobile/common/logo.png")}" class="logoImg"/>
    <div class="menu-wrap" >
        <#if isLogin?c == 'true'>
            <a @touchstart="menuOpenQestion" style="text-decoration: none" onclick="_hmt.push(['_trackEvent', '常见问题_mobile', 'click', 'source=${source!}'])">常见问题</a>
              <span></span>
        </#if>

        <a @touchstart="menuOpenClick" style="text-decoration: none" onclick="_hmt.push(['_trackEvent', '导航_mobile', 'click', 'source=${source!}'])">导航</a>
    </div>

    <div v-cloak v-bind:class="openMenuShow?'newBox':'newBox  newBoxTrans'"  @touchmove="handleTouchMove">
        <ul>
            <li class="txt-center title" >
                <img @click="menuCloseClick" src="${image("mobile/common/left.png")}"  onclick="_hmt.push(['_trackEvent', '关闭导航_mobile', 'click', 'source=${source!}'])"/>
                <div @click="menuCloseClick"  onclick="_hmt.push(['_trackEvent', '关闭导航_mobile', 'click', 'source=${source!}'])"></div>
                <span> 导航</span>
            </li>
            <li class="txt-left" >
                <img src="${image("mobile/common/right.png")}"/>

                <p><a href="https://www.porsche.com/china/zh" onclick="_hmt.push(['_trackEvent', '进入保时捷中国官网_mobile', 'click', 'source=${source!}'])">进入保时捷中国官网</a></p>
            </li>
            <li class="txt-left">
                <img src="${image("mobile/common/zixun.png")}"/>
                <p><a href="/register/subscription" onclick="_hmt.push(['_trackEvent', '订阅Taycan资讯_mobile', 'click', 'source=${source!}'])">订阅 Taycan 资讯</a></p>
            </li>
            <li class="txt-left">
                <img src="${image("mobile/common/gouwuche.png")}"/>

                <p><a href="/register/reservation" onclick="_hmt.push(['_trackEvent', '支付购车意向金_mobile', 'click', 'source=${source!}'])">支付购车意向金</a></p>
            </li>
        <#if isLogin?c=='true'>
            <li class="txt-left">
                <img src="${image("mobile/common/person.png")}"/>

                <p><a href="/account" onclick="_hmt.push(['_trackEvent', '我的账户_mobile', 'click', 'source=${source!}'])">我的账户</a><span onclick="_hmt.push(['_trackEvent', '退出_mobile', 'click', 'source=${source!}']);logout('${source!}')" >退出</span></p>
            </li>
        <#else>
            <li class="txt-left" >
                <img src="${image("mobile/common/person.png")}"/>

                <p><a href="/login?source=${source!}" onclick="_hmt.push(['_trackEvent', '登录/注册_mobile', 'click', 'source=${source!}'])">登录/注册</a></p>
            </li>
        </#if>
        </ul>
        <#include "../include/footer.ftl">
    </div>

    <div v-bind:class="openQestion?'specialPosition questionBox':'questionBox questionBoxTrans'">
        <ul>
            <li class="txt-center title questionLi">
                常见问题
            </li>
        </ul>
        <div class="questions" id="question">
            <p class="back" @click="closeQestion"  onclick="_hmt.push(['_trackEvent', '关闭常见问题_mobile', 'click', 'source=${source!}'])">
               <img src="${image("mobile/common/back-grey.png")}" class="backImg">
                <span class="backTxt">返回</span>
            </p>

            <div class="questionsWrap">
                <ul>
                    <li>
                        <div class="leftW">
                            <span class="numberW">1</span>
                            <span class="questionsLine">可以有哪些提交购车意向方式？</span>
                        </div>
                        <div class='twoLine'>
                            <p>您可通过线上或线下两种方式向保时捷（中国）汽车销售有限公司（以下称“<b>保时捷中国</b>”）的授权经销商（以下称“<b>保时捷中心</b>”）表达并确认其购车意向：
                            </p>

                            <p style="margin-top: 10px">1）线上方式：若您选择通过线上方式与保时捷中心达成本意向书，您可登陆网站地址为 www.porsche-online-preorder.com.cn 的指定网站（以下称“<b>在线系统</b>”）并选定保时捷中心，并确认购车意向；或
                            </p>
                            <p style="margin-top: 10px">2）线下方式：若您选择通过线下方式与保时捷中心达成本意向书，您可前来保时捷中心签订书面意向书并向保时捷中心支付购车意向金。
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">2</span>
                            <span class="questionsLine">保时捷中心有 Taycan 实车展示吗？</span>
                        </div>
                        <div class='twoLine'>保时捷中心暂无 Taycan 实车展示。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">3</span>
                            <span class="questionsLine">可以提交两辆车以上的购车意向吗？</span>
                        </div>
                        <div class='twoLine'>如果您通过在线系统提交购车意向，则您所填写的身份证号对应的注册用户只能提交一次购车意向，但线下方式提交购车意向的数量向并无限制。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">4</span>
                            <span class="questionsLine">购车意向是否可以转让？</span>
                        </div>
                        <div class='twoLine'>如您已经收到在线系统生成的购车意向单，或与保时捷中心签署书面的购车意向书，若未经保时捷中心的书面许可，您不应向任何第三方转让意向书项下的权利和义务。</div>
                    </li>

                    <li>
                        <div class="leftW">
                            <span class="numberW">5</span>
                            <span class="questionsLine">可以选择哪些在线付款方式？</span>
                        </div>
                        <div class='twoLine'>如果您通过线上方式提交购车意向，您可以通过微信支付、支付宝、云闪付（暂时只支持 PC 端）或银联的方式在线支付意向金。需要提醒的是，因技术原因，您的打开在线系统的浏览方式可能会导致部分在线支付方式产生不同程度的变化。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">6</span>
                            <span class="questionsLine">我的微信/支付宝/信用卡单日交易上限只有 10,000 元，怎么办？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">
                            <p>如果您的信用卡设置了交易上限，请联系银行解除限定，或更换其他满足上限额条件的支付方式进行支付。如调整限定后仍无法支付，可联系支付平台客服热线。
                            </p>
                            <p style="margin-top: 10px">支付宝客服电话：95188
                            </p>
                            <p style="margin-top: 10px">微信支付客服电话：95017
                            </p>
                            <p style="margin-top: 10px">银联卡/云闪付客服电话：95516
                            </p>
                            <p style="margin-top: 10px">若您的问题仍无法得到解决，或您有其他在线支付的问题，可联系保时捷中国客户中心（400-820-5911）。</p>
                            </div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">7</span>
                            <span class="questionsLine">如何处理通过在线系统支付意向金时遇到的支付失败的情形？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">如果您在支付过程中，系统弹出包含错误信息的商户支付失败提示，建议您按照问题 6 中的联系方式联系支付平台客服热线进行咨询，若您的问题仍无法得到解决，建议您通过保时捷中国客户中心（400-820-5911）与保时捷中国取得联系。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">8</span>
                            <span class="questionsLine">如何申请退还已经支付的意向金？</span>
                        </div>
                        <div class='twoLine'>如您通过线上系统支付购车意向金，请您再次登入线上系统后进入我的账户，点击“申请线下退款”按钮后，系统将记录您的退款申请，并将由您选择的保时捷中心致电您注册时留下的手机号码，协助您完成退款程序。如果您通过线下支付购车意向金，请您与保时捷中心联系，并由保时捷中心协助您完成退款程序。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">9</span>
                            <span class="questionsLine">我是否可以更改我在线上系统上注册账户时填写的手机号码？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">您在线上系统注册的用户所绑定的手机号码系唯一且不可更改。如有疑问，请联系保时捷中国客户中心（400-820-5911）。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">10</span>
                            <span class="questionsLine">我支付了购车意向金是否就意味着我一定可以购买到保时捷 Taycan？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">根据您在线上系统中已阅读并同意或线下与保时捷中心签署的《保时捷汽车购买意向书条款与条件》第 1 条及第 4 条的规定，您提交的购车意向书不构成针对保时捷 Taycan 的买卖合同，亦不构成对保时捷 Taycan 品名、外观、型号、配置规格、生产批次、建议零售价、预计交付时间、安全注意事项和风险警示以及售后服务的任何承诺。保时捷中心对您购车意向所指的保时捷 Taycan 不作任何承诺或保证，尤其是，保时捷中心并非保时捷 Taycan 的制造商，无法保证保时捷 Taycan 是否将最终被投放市场，亦无法保证您在同等条件下优先购买到保时捷 Taycan。当且仅当保时捷中心通知客户该保时捷车辆已在中国上市且可供客户购买，而客户表示愿意购买该保时捷车辆的情况下，该保时捷车辆的买卖关系将根据双方另行达成的具有法律约束力的汽车销售合同（以下称“汽车销售合同”）予以确立。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">11</span>
                            <span class="questionsLine">我支付了意向金，可否索要收据？</span>
                        </div>
                        <div class='twoLine'>您可以与提交购车意向的保时捷中心联系，由该保时捷中心为您提供收据。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">12</span>
                            <span class="questionsLine">在线上系统选择具体保时捷中心时，是否所有的授权保时捷中心均可以选择？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">A: 本购买意向征集活动（“<b>活动</b>”）系保时捷中心自愿报名参加，如您在线上系统选择具体保时捷中心时未能找到某一家保时捷中心，则该保时捷中心可能并不参与本次的活动。如您就保时捷中心的选择有任何疑问，可以联系保时捷中国客户中心（400-820-5911）咨询。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">13</span>
                            <span class="questionsLine">我支付的购车意向金对应有哪些服务？</span>
                        </div>
                        <div class='twoLine'>根据您在线上系统中已阅读并同意或线下与保时捷中心签署的《保时捷汽车购买意向书条款与条件》第3条的规定，本购车意向书有效期内，您享受的服务包括：可以获得有关保时捷车辆的最新资讯和信息，同时您将有机会被邀请参加与保时捷汽车有关的线下活动。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">14</span>
                            <span class="questionsLine">所退还的购车意向金是否包含利息以及退还意向金的时间？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;" >根据您在线上系统中已阅读并同意或线下与保时捷中心签署的《保时捷汽车购买意向书条款与条件》第 9 条的规定，退还给您的购车意向金不含有任何形式的利息。关于退还意向金的时间，请您咨询保时捷中心。</div>
                    </li>
                    <li>
                        <div class="leftW">
                            <span class="numberW">15</span>
                            <span class="questionsLine"> 实际购车时，购车人和线上系统中提交的购车意向的人是否必须是同一个人？</span>
                        </div>
                        <div class='twoLine' style="padding-top: 30px;">是的。请您在填写购车意向信息时，使用真实的购车人信息。如果您留存在线上系统中的购车意向信息与实际购车人不一致，根据您在线上系统中已阅读并同意或线下与保时捷中心签署的《保时捷汽车购买意向书条款与条件》第 6 条的规定，需要事先获得保时捷中心的书面许可。</div>
                    </li>
                </ul>
            </div>

        </div>
  <#include "../include/footer.ftl">
    </div>
</header>
<script>
    var source = "${source!}";
</script>
<script src="${js("header.js")}"></script>

