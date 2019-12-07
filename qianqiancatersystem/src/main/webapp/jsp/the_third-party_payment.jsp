<%--
  User: QIANG
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="headtag.jsp"%>
    <title>第三方支付平台</title>
    <script>
        $(function () {
            var oid = getQueryVariable("oid");
            $.get("${pageContext.request.contextPath}/order/paySuccess?oid="+oid,function (result) {
                if (result.code == 100){
                    setTimeout(function () {
                        $("#erweima").empty().html("<h3>付款成功，正在跳转回商家……</h3>");
                        $("#yinhanglogo").empty();
                    },3000);
                    setTimeout(function () {
                        window.close();
                    },5000);
                }else {
                    setTimeout(function () {
                        $("#erweima").empty().html("<h3>发生错误，付款失败！</h3>");
                        $("#yinhanglogo").empty();
                    },3000);
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <img src="../img/zhifubaoshouyintai.png" alt="">
    <div id="erweima" class="row">
        <img src="../img/shoukuanma.png" alt="">
    </div>
    <div id="yinhanglogo" class="row">
        <img src="../img/yinhanglogo.png" alt="">
    </div>
</div>
</body>
</html>
