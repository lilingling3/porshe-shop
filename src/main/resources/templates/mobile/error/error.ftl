<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>404</title>
     <#include "../include/link.ftl">
</head>
<body class="error-page">
<#include "../include/header.ftl">
<figure class="error-img">
    <img class="align-justify-center " src="${image("mobile/common/error.png")}">
</figure>

<#include "../include/footer.ftl">
</body>
<script>
_hmt.push(['_trackEvent', '错误页_mobile', 'click', 'source=${source!}']);
</script>
</html>