<pre>
尊敬的${dealer!}保时捷中心，

附件为截至${date!}在线完成付款的保时捷 Taycan 预订客户名单，请您查看。
<table border="1" cellspacing="0">
<tr><td>姓名</td><td>电话</td><td>订单号</td><td>时间</td></tr>
<#list orderList as p>
<tr><td>${p.consignee!}</td><td>${p.phone!}</td><td>${p.orderSn!}</td><td>${p.createDate!}</td></tr>
</#list>
</table>
如有疑问请发送邮件至 J1 预售网站支持团队 xxxx@boldseas.com，感谢您的配合。

Best Regards，

【保时捷中国】
<pre>