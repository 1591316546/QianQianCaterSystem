<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>菜品详情页——千乾餐饮</title>
    <script type="text/javascript">
        $(function () {


        });

    </script>
</head>
<body>

<!-- 头部 引入 -->
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row col-md-offset-5">
        <h2>商品详情</h2>
    </div>
    <div class="row">
        <div class="col-md-6"><img src="img/cai/cai_big_pic.png" class="img-responsive" alt="菜的图片"/></div>
        <div class="col-md-6">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row">
                <span>数量:</span>
                <input type="text" value="1">
                <input type="button" class="btn" value="-">
                <input type="button"  class="btn" value="+">

            </div>
            <div class="row">
                <button type="button" class="btn btn-warning btn-lg">加入购物车</button>
            </div>
        </div>
    </div>
    <div class="row"></div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>