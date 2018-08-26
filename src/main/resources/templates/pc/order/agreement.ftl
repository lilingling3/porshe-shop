<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>购车意向书</title>
     <#include "../include/header.ftl">
    <link rel="stylesheet" href="${css("pc/order_agreement.css")}">
</head>


<body>
<div class="page-box arrival shop-order" id="order_agreement" v-cloak>
   <#include "../include/header_page.ftl">
    <div class="common">
        <img src="${image("pc/shop-order/order_pic.jpg")}" class="imgMinWithHeight bg"/>
        <div class="content-box arrival-box" style="width: 500px">
            <div class="arrival-title">
                <div class="arrival-icon">
                    <img src="${image("pc/generate/ownuser.png")}"/>
                </div>
                <p>购车意向书</p>
                <p>为保障您的权益，请仔细阅读以下法律条款</p>
            </div>
            <div class="timezone" style="background-image: url(${image("pc/generate/line-bg.png")};">
                <div class="time">
                    <div>
                        <p style="font-size: 14px"> 阅读并确认购车意向书</p>
                        <div class="agreement" @click="showAgreement">
                            <span style="font-size: 13px">保时捷电动汽车购买意向书</span>
                            <img src="${image("/pc/register/right_icon.png")}">
                        </div>

                    </div>
                </div>
                <div class="time" style="top: 296px;">
                    <div>
                        <p style="font-size: 14px">完善购车人信息<font style="font-size:12px;"></br>（每个账户或身份证只可在线支付一辆 Taycan 的购车意向金）</font></p>
                    </div>
                </div>
                <div class="time" style="top: 356px;">
                    <div>
                        <p style="font-size: 14px">在线支付购车意向金</p>
                    </div>
                </div>
                <div class="time" style="top: 416px;">
                    <div>
                        <p style="font-size: 14px">完成在线支付</p>
                    </div>
                </div>
            </div>

        </div>
        <div class="dealer-box" v-show="isShowAgreement">
            <h2 style="font-size: 20px">保时捷电动汽车购买意向书<br/>条款与条件</h2>
            <div class="agreementWrap list">
                本《保时捷汽车购买意向书条款与条件》(以下称“<b>本意向书</b>”)由保时捷(中国)汽车销售有限公司(以下 称“<b>保时捷中国</b>”)的授权经销商(以下称“<b>保时捷中心</b>”)与客户共同签订。通过达成本意向书，客户向
                保时捷中心表明其购买保时捷首款量产纯电动汽车(以下称“<b>保时捷车辆</b>”)的意向并同意根据本意向书的 规定向保时捷中心支付购买意向金作为其购买意向的凭证。<u><b>为保护客户的法律权益，请客户特此确认其已认真
                阅读并理解本意向书的全部内容。</b></u>
                <h1><i>1、</i>[<b>意向书性质</b>]</h1>
                <p>客户与保时捷中心之间在本意向书项下表达的客户购买保时捷车辆的意向无合同约束力。客户无义务因签署 本意向书而必须向保时捷中心购买该保时捷车辆，保时捷中心无义务因签署本意向书而必须向客户供应该
                    保时捷车辆。客户可在本意向书生效后随时撤销其购买保时捷车辆的意向，保时捷中心在收到客户撤销其 购买意向后，有义务退还客户已支付的购车意向金。<u><b>本意向书不构成针对该保时捷车辆的买卖合同，亦不
                        构成对该保时捷车辆品名、外观、型号、配置规格、生产批次、建议零售价、预计交付时间、安全注意 事项和风险警示以及售后服务的任何承诺。</b></u>当且仅当保时捷中心通知客户该保时捷车辆已在中国上市且
                    可供客户购买，而客户表示愿意购买该保时捷车辆的情况下，该保时捷车辆的买卖关系将根据双方另行 达成的具有法律约束力的汽车销售合同(以下称“<b>汽车销售合同</b>”)予以确立。
                </p>
                <h1><i>2、</i>[<b>意向书生效</b>]</h1>

                <p>本意向书项下的每台保时捷车辆的购车意向金为人民币 20,000 元整(以下称“<b>购车意向金</b>”)。客户可通过线上或线下两种方式向保时捷中心表达并确认其购车意向:</p>

                <section>
                    2.1 线上方式:若客户选择通过线上方式与保时捷中心达成本意向书，客户可登陆网站地址为 www.porsche-online-preorder.com.cn
                    的指定网站(以下称“<b>在线系统</b>”)并选定保时捷中心，通过点击“我已阅读并同意以上条款”确认本意向书内容，并在线向保时捷中心支付购车意向金，在线
                    系统自动生成购车意向单(以下称“<b>购车意向单</b>”)。本意向书自以下条件均已满足时生效:
                    &nbsp;( 1 )客户点击确认同意本意向书内容;
                    &nbsp;( 2 )保时捷中心已收到客户的购车意向金;和( 3 )在线系统已自动生成购车意向单。
                </section>
                <section>2.2 线下方式:若客户选择通过线下方式与保时捷中心达成本意向书，客户可前来保时捷中心签订书面 意向书并向保时捷中心支付购车意向金。本意向书自以下条件均已满足时生效:( 1 )客户已签署
                    本意向书;( 2
                    )保时捷中心已收到客户的购车意向金。意向书生效后，保时捷中心向客户开具 购车意向金收据。
                    <br/>
                    <br/>
                    若客户与保时捷中心就该保时捷车辆达成汽车销售合同，购车意向金的本金(不包括利息和/或其他孳息) 可根据客户要求自动抵扣汽车销售合同项下保时捷车辆价款的一部分。<u><b>客户理解并同意:保时捷中心无 义务将购车意向金存入单独的账户或监管账户、亦无义务向客户支付任何利息和/或其他孳息。该购车意向 金并非定金，并且无论如何也不应被解释为定金或具有任何担保性质的付款。</b></u>
                </section>
                <h1><i>3、</i>[<b>对价</b>]</h1>
                <p>本意向书有效期内，客户享受以下服务: 客户可以获得有关保时捷车辆的最新资讯和信息，同时客户将有机会被邀请参加与保时捷汽车有关的线下 活动。</p>
                <h1><i>4、</i>[<b>无保证义务</b>]</h1>
                <p>本意向书由保时捷中心与客户自愿达成，本意向书的任何条款均不构成亦不得被解释为保时捷中心与 保时捷中国存在任何代理关系或保时捷中心可对保时捷中国产生任何法律约束力。<u><b>保时捷中心对本意向
                    书项下保时捷车辆不作任何承诺或保证，尤其是，保时捷中心并非保时捷车辆的制造商，无法保证保时捷 车辆是否将最终被投放市场，亦无法保证客户在同等条件下优先购买到该保时捷车辆。</b></u></p>
                <h1><i>5、</i>[<b>保时捷车辆信息</b>]</h1>
                <p><u><b>鉴于本意向书生效时保时捷车辆的制造商尚未确定保时捷车辆的品名、外观、型号、配置规格、生产 批次、建议零售价、预计交付时间、安全注意事项和风险警示以及售后服务等信息(以下合称“车辆
                    信息”)，客户从在线系统或其他渠道获得的任何车辆信息(包括但不限于任何广告或宣传资料)仅作为 参考，保时捷中心对该车辆信息的准确性和可靠性不承担任何形式的法律责任。具体的车辆信息以日后
                    双方达成的汽车销售合同(若发生)规定的内容为准。</b></u></p>
                <h1><i>6、</i>[<b>不可转让性</b>]</h1>
                <p> 未经保时捷中心的书面许可，客户不应向任何第三方转让本意向书项下的权利和义务。</p>
                <h1><i>7、</i>[<b>意向书有效期</b>]</h1>
                <p>本意向书自生效之日起至汽车销售合同签订之日或依本意向书第 8 条解除之日(以先发生者为准)止持续 有效(以下称“<b>有效期</b>”)。</p>
                <h1><i>8、</i>[<b>意向书解除</b>]</h1>
                <p>客户可随时书面通知保时捷中心解除本意向书。保时捷中心可在下列任一情形发生时解除本意向书: ( 1 )保时捷中心不再作为保时捷中国的授权经销商;( 2 )保时捷中心未取得销售该保时捷汽车的授权
                    或其他必要许可;或( 3 )客户未在保时捷中心书面通知的期限内与保时捷中心签署汽车销售合同。</p>
                <h1><i>9、</i>[<b>购车意向金返还</b>]</h1>
                <p>本意向书根据第 8 条的规定解除后，保时捷中心应凭客户出具的购车意向单或购车意向金收据无息返还 购车意向金。</p>
                <h1 class="two-num">10、[<b>个人信息保护</b>] </h1>
                <p>保时捷中心将采取一切必要且合理的措施确保客户的个人信息在现有数据保护相关法律下的安全。客户
                    理解并同意，保时捷中心可以为了以下目的收集、使用、存储、处理客户的个人信息，包括但不限于客户
                    姓名、联系方式(如电子邮件、电话/手机号码和地址)以及护照号码和驾驶证详情:( 1 )与客户进行 与产品、服务和活动有关的沟通交流;( 2 )为客户提供保时捷驾驶体验;( 3 )改善客户对保时捷的
                    产品、服务和营销工作。客户理解并同意:保时捷中心可能会基于前述目的将客户的个人信息用于中国 境内或境外的远程传输或储存;可能会与保时捷中国及其关联方、商业伙伴或在中国境内或境外的第三方
                    服务商分享客户的个人信息以便提供更优质的服务。</p>
                <h1 class="two-num">11、[<b>适用法律及争议解决</b>] </h1>
                <p><u><b>本意向书受中华人民共和国法律管辖。凡因本购车意向书引起的或与其相关的任何争议，应由双方协商 解决，协商不成的，该等争议须提交至上海仲裁委员会通过仲裁解决。</b></u></p>
                <h1 class="two-num">12、[<b>完整性</b>]</h1>
                <p> 若本意向书的任何规定违反适用的法律规定或无法依据适用法律强制执行，则该类规定应被视为不存在， 且本意向书的其他规定不会因该类规定而受任何影响</p>

            </div>
            <a class="agreementBtn" @click="toAgreement" :class="isAgreePolicy?'agreeBtn':'grayBtn'" onclick="_hmt.push(['_trackEvent', '同意购车意向书_pc', 'click', 'source=${source!}'])">我已阅读并同意以上条款</a>
            <span v-cloak :class="isAgreePolicy?'hidMask':'showSpan'">{{countdown}}秒后可点击</span>

        </div>
    </div>
    <div class="questions" id="toCommonQestion" onclick="toCommonQestion('${source!}')">
        <img src="${image("pc/login/question.png")}"/>
        常见问题
    </div>
      <#include "../include/footer.ftl">
</div>
</body>
<script src="${js("order_agreement.js")}"></script>
<script src="${js("header.js")}"></script>
</html>