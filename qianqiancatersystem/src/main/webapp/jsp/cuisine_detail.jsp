<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>菜品详情页——千乾餐饮</title>
    <script type="text/javascript">
        $(function () {
            //取消导航条首页标签高亮
            $("#header-navbar>ul>li").removeClass("active");
            //获取参数
            var cid = getQueryVariable("cid");
            //获取对应的菜的详情
            $.ajax({
                url:"${pageContext.request.contextPath}/cuisine/getCuisineById",
                data:{"cid":cid},
                success:function (data) {
                    if (data.code == 100){
                        var cuisine = data.extend.cuisine;
                        $("#cname").text(cuisine.cname);
                        $("#desc").text(cuisine.description);
                        $("#price").text("￥ "+cuisine.price);
                    }
                }
            });

            //获取菜的图片
            $.ajax({
                url:"${pageContext.request.contextPath}/cuisine/getCuisineBigImg",
                data:{"cid":cid},
                success:function (data) {
                    $("#cuisine-image").prop("src","${pageContext.request.contextPath}/"+data.extend.imgPath);
                }
            });

            // 按钮控制数量输入框
            $input_num = $("#input_cnum");
            var num = $input_num.val();
            //加的按钮
            $("#addBtn").click(function () {
                //限制最大为10份
                num += 1;
                if (num > 10){
                    num = 10;
                }
                $input_num.val(num);
            });
            //减号按钮
            $("#subBtn").click(function () {
                //限制最小为1份
                num -=1;
                if (num < 1){
                    num = 1;
                }
                $input_num.val(num);
            });

        });

    </script>
</head>
<body>

<!-- 头部 引入 -->
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row col-md-offset-5">
        <h2>菜品详情</h2>
    </div>
    <div class="row">
        <div class="col-md-6"><img id="cuisine-image" class="img-responsive"/></div>
        <div class="col-md-6">
            <%--菜名--%>
            <div class="row"><h3 id="cname"></h3></div>
                <%--描述--%>
            <div class="row"><p id="desc"></p></div>
                <%--价格--%>
            <div class="row price-font"><strong id="price">￥ 0</strong></div>
                <%--数量选择--%>
            <div class="row">
                <span>数量:</span>
                <input id="subBtn" type="button" class="btn" value="-">
                <input id="input_cnum" type="text" value="1" disabled="disabled">
                <input id="addBtn" type="button"  class="btn" value="+">
            </div>
                <%--提交按钮--%>
            <div class="row">
                <button type="button" class="btn btn-warning btn-lg">加入菜篮子</button>
            </div>
        </div>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>