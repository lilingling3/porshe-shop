<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>404</title>
	<#include "../include/header.ftl">
</head>
<body class="error-page">
	<#include "../include/header_page.ftl">
	<figure class="error-img">
		<img class="align-justify-center " src="${image("pc/common/error.png")}">
	</figure>
	<#include "../include/footer.ftl">
</body>
<script>
	_hmt.push(['_trackEvent', '错误页_pc', 'click', 'source=${source!}']);
</script>
</html>