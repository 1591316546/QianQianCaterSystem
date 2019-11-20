<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>注册成功——千乾餐饮</title>

    <!-- Bootstrap -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-1.12.4.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/qianqiancater.css"/>
    <script type="text/javascript">
        $(function () {
            $("#bs-example-navbar-collapse-1>ul>li").removeClass("active");
        });
    </script>

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 头部 异步加载 -->
<jsp:include page="header.jsp"></jsp:include>
<!-- 主体内容 -->
<div class="container">
    <div class="row">
        <br>
        <br>
        <br>
        <p>您已经注册成功，我们给您的邮箱中发了一封邮件，请在邮箱中点击激活链接激活账户。您可能需要<a href="http://www.hao123.com/mail">去登录邮箱。</a></p>
        <br>
        <br>
        <br>
        <br>

    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
