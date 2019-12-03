<%--
  User: QIANG
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--引入统一标签--%>
    <%@include file="headtag.jsp" %>
    <title>我的菜篮子——千乾餐饮</title>
    <script>
        $(function () {
            $.get("${pageContext.request.contextPath }/order/showBasket",function (data) {
                if (data.code == 100){
                    //请求成功 遍历每一项
                    var foodItems = data.extend.foodItems;
                    //如果菜篮子是空的则提示
                    if (foodItems.length == 0){
                        $("#container").empty().append("<img src='../img/food_basket_empty.png' class='img-responsive'/>");
                        return;
                    }
                    //拼接表格显示
                    var itemTr = "";
                    for (var i= 0; i<foodItems.length;i++){
                        itemTr += '<tr>\n' +
                            '    <td><img src="${pageContext.request.contextPath }/'+foodItems[i].cuisine.image+'" alt=""></td>\n' +
                            '    <td>'+foodItems[i].cuisine.cname+'</td>\n' +
                            '    <td>'+foodItems[i].cuisine.price+'</td>\n' +
                            '    <td>'+ foodItems[i].count +'</td>\n' +
                            '    <td>'+ foodItems[i].subtotal +'</td>\n' +
                            '    <td><a href="javascript:deleteItem('+ foodItems[i].cuisine.cid +');">删除</a></td>\n' +
                            '</tr>';
                    }
                    //将拼接好的Html显示到页面
                    $("#food-basket-table>tbody").empty().html(itemTr);
                    //显示总金额
                    $("#total-money").text(data.extend.totalMoney);
                }
            });
        });
        //点击删除菜品项的方法
        function deleteItem(cid) {
            $.get("${pageContext.request.contextPath }/order/deleteFoodItem",{"cid":cid},function (data) {
                if (data.code ==100){
                    //刷新页面
                    location.reload();
                }
            });
        }
        //清空菜篮子
        function clearBasket() {
            $.get("${pageContext.request.contextPath }/order/clearFoodBasket",function (data) {
                if (data.code ==100){
                    //刷新页面
                    location.reload();
                }
            });
        }
    </script>
</head>
<body>
<%--引入页眉--%>
<%@include file="header.jsp" %>
<%--内容区--%>
<div id="container" class="container">
    <div class="row col-md-offset-5">
        <h2>我的菜篮子</h2>
    </div>
    <div class="row">
        <table id="food-basket-table" class="table table-bordered order-table">
            <thead>
            <tr class="success">
                <th>菜品</th>
                <th>菜品名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%--<tr>
                <td><img src="${pageContext.request.contextPath }/img/cuisine/thumb/1.jpg" alt=""></td>
                <td>蒜香糖醋虾仁</td>
                <td>99.00</td>
                <td>1</td>
                <td>99.00</td>
                <td><a href="">删除</a></td>
            </tr>
            <tr>
                <td><img src="${pageContext.request.contextPath }/img/cuisine/thumb/1.jpg" alt=""></td>
                <td>蒜香糖醋虾仁</td>
                <td>99.00</td>
                <td>1</td>
                <td>99.00</td>
                <td><a href="">删除</a></td>
            </tr>--%>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <span>总金额：￥ <span id="total-money">0.0</span></span>
            <a href="javascript:clearBasket()">清空菜篮子</a>
            <a href="confirm_order.jsp" class="btn btn-danger btn-lg">去结算</a>
        </div>
    </div>
</div>

<%--引入页脚--%>
<%@include file="footer.jsp" %>
</body>
</html>
