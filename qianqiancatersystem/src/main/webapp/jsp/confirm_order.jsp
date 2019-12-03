<%--
  User: QIANG
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--引入统一标签--%>
    <%@include file="headtag.jsp" %>
    <title>确认订单——千乾餐饮</title>
    <script>
        $(function () {
            $.get("${pageContext.request.contextPath }/order/showBasket", function (data) {
                if (data.code == 100) {
                    //请求成功 遍历每一项
                    var foodItems = data.extend.foodItems;
                    //如果菜篮子是空的则提示
                    if (foodItems.length == 0) {
                        $("#container").empty().append("<img src='../img/food_basket_empty.png' class='img-responsive'/>");
                        return;
                    }
                    //拼接表格显示
                    var itemTr = "";
                    for (var i = 0; i < foodItems.length; i++) {
                        itemTr += '<tr>\n' +
                            '    <td><img src="${pageContext.request.contextPath }/' + foodItems[i].cuisine.image + '" alt=""></td>\n' +
                            '    <td>' + foodItems[i].cuisine.cname + '</td>\n' +
                            '    <td>' + foodItems[i].cuisine.price + '</td>\n' +
                            '    <td>' + foodItems[i].count + '</td>\n' +
                            '    <td>' + foodItems[i].subtotal + '</td>\n' +
                            '</tr>';
                    }
                    //将拼接好的Html显示到页面
                    $("#food-basket-table>tbody").empty().html(itemTr);
                    //显示总金额
                    $("#total-money").text(data.extend.totalMoney);
                }
            });
        });
    </script>
</head>
<body>
<%--引入页眉--%>
<%@include file="header.jsp" %>
<%--内容区--%>
<div class="container">
    <div class="row col-md-offset-5">
        <h2>确认订单</h2>
    </div>
    <div class="row">
        <table class="table table-bordered order-table">
            <thead>
            <tr class="success">
                <th>菜品</th>
                <th>菜品名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <%--<tr>
                <td><img src="${pageContext.request.contextPath }/img/cuisine/thumb/1.jpg" alt=""></td>
                <td>蒜香糖醋虾仁</td>
                <td>99.00</td>
                <td>1</td>
                <td>99.00</td>
            </tr>--%>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="input-address" class="col-sm-2 control-label">送餐地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input-address" placeholder="地址" name="address">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-name" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input-name" placeholder="姓名" name="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-phone" class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input-phone" placeholder="手机号" name="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">订单备注</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="3" placeholder="口味，偏好等"></textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <span>总金额：￥ <span id="total-money">0.0</span></span>
            <button class="btn btn-danger btn-lg">去支付</button>
        </div>
    </div>
</div>

<%--引入页脚--%>
<%@include file="footer.jsp" %>
</body>
</html>
