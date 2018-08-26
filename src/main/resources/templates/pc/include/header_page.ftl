
<header class="header">
    <hr/>
    <div class="align-center logo" onclick="toHome('${source!}')">
    <#include "./logo.ftl">
    </div>
    <div class="header-right">
    <#if isLogin?c=='true'>
        <a class="toPorsche"  href="https://www.porsche.com/china/zh" onclick="_hmt.push(['_trackEvent', '进入保时捷中国官网_pc', 'click', 'source=${source!}'])">
            进入保时捷中国官网<img class="justify-center" src="${image("pc/header/right_arrow.png")}"/>
        </a>
        <a class="toHome" href="/home?source=${source!}" onclick="_hmt.push(['_trackEvent', '返回首页_pc', 'click', 'source=${source!}'])">
            返回首页<img class="justify-center" src="${image("pc/header/right_arrow.png")}"/>
        </a>
        <div class="other-function">

            <a class="toAccount" href="/account" onclick="_hmt.push(['_trackEvent', '我的账户_pc', 'click', 'source=${source!}'])">
                <img src="${image("pc/header/user.png")}" style="width: 20px;margin: -3px 5px 0 0">我的账户
            </a>

            <a class="quit hoverCursor" id="quit" onclick="_hmt.push(['_trackEvent', '退出_pc', 'click', 'source=${source!}']);logout('${source!}')">
                退出&nbsp;<img src="${image("pc/header/quit.png")}" style="width: 8px;margin: -2px 0 0 10px;"/>
            </a>

        </div>
    <#else>
        <a class="toPorsche no-login"  href="https://www.porsche.com/china/zh" onclick="_hmt.push(['_trackEvent', '进入保时捷中国官网_pc', 'click', 'source=${source!}'])">
            进入保时捷中国官网<img class="justify-center" src="${image("pc/header/right_arrow.png")}"/>
        </a>
        <div class="other-function">
            <a class="toLogin" href="/login?source=${source!}" onclick="_hmt.push(['_trackEvent', '登录/注册_pc', 'click', 'source=${source!}'])">
                <img src="${image("pc/header/user.png")}" style="width: 20px;margin: -3px 5px 0 0"/>登录/注册
            </a>
        </div>
    </#if>
    </div>
</header>
