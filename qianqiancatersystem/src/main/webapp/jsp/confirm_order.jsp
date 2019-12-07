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
                    $("#orderfrom-table>tbody").empty().html(itemTr);
                    //显示总金额
                    $("#total-money").text(data.extend.totalMoney);
                }
            });

            //点击确认订单提交数据
            $("#btn-confirm-order").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/order/confirmOrder",
                    type: "POST",
                    data: $("#form-user-info").serialize(), //将表单中的字段序列化
                    success: function (result) {
                        console.log(result);
                        if (result.code == 100) {
                            //处理成功
                            //跳转到第三方支付页面
                            window.open(result.extend.url);
                            //打开模态框选择支付结果
                            $('#result-of-pay').modal({
                                backdrop: 'static'
                            })
                        }else {
                            alert(result.msg);
                        }
                    }
                });
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
        <table id="orderfrom-table" class="table table-bordered order-table">
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
        <div class="col-md-2 col-md-offset-10">
            <span>总金额：￥ <span id="total-money">0.0</span></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <form id="form-user-info" class="form-horizontal">
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
                        <textarea class="form-control" rows="3" placeholder="口味，偏好等" name="remark"></textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-md-offset-10">
            <button id="btn-confirm-order" class="btn btn-danger btn-lg">确认下单</button>
        </div>
    </div>
</div>

<%--引入页脚--%>
<%@include file="footer.jsp" %>


<%--支付情况选择模态框--%>
<div class="modal fade" id="result-of-pay" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">温馨提示</h4>
            </div>
            <div class="modal-body">
                <p>支付完成前，不要关闭此支付验证窗口！</p>
                <p>支付完成后，请根据支付情况点击下面对应的按钮。</p>
            </div>
            <div class="modal-footer">
                <a href="${pageContext.request.contextPath}/jsp/orderform.jsp" class="btn btn-default">遇到问题</a>
                <a href="${pageContext.request.contextPath}/jsp/orderform.jsp" class="btn btn-primary">支付成功</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
