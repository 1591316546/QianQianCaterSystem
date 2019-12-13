<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>菜品详情页——千乾餐饮</title>
    <script type="text/javascript">
        $(function () {
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
                        $("#cuisine-image").prop("src","${pageContext.request.contextPath}/"+cuisine.image);
                    }
                }
            });

            // 按钮控制数量输入框
            $input_num = $("#input_cnum");
            //加的按钮
            $("#addBtn").click(function () {
                var num = $input_num.val();
                //限制最大为10份
                num = Number.parseInt(num);
                num = num + 1;
                if (num > 10){
                    num = 10;
                }
                $input_num.val(num);
            });
            //减号按钮
            $("#subBtn").click(function () {
                var num = $input_num.val();
                //限制最小为1份
                num -=1;
                if (num < 1){
                    num = 1;
                }
                $input_num.val(num);
            });

            //添加到菜篮子操作
            $("#add-to-basket").click(function () {
                //首先检查是否登录了
                $.get("${pageContext.request.contextPath}/user/getLoginInfo",function (data) {
                    if (data.code == 100){
                        //登录了
                        $.ajax({
                            url:"${pageContext.request.contextPath}/order/addToBasket",
                            data:{"cid":cid,"count":$("#input_cnum").val()}, //提交菜的cid 和数量
                            success:function (data) {
                                if (data.code == 100 ){
                                    //加菜篮子成功
                                    alert("成功加入菜篮子！");
                                    location.href="${pageContext.request.contextPath}/jsp/food_basket.jsp";
                                }
                            }
                        });
                    }else {
                        //没有登录
                        alert("您尚未登录，请登录后操作！");
                        location.href="${pageContext.request.contextPath }/jsp/login.jsp";
                    }
                });
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
                <button id="add-to-basket" type="button" class="btn btn-warning btn-lg">加入菜篮子</button>
            </div>
        </div>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>