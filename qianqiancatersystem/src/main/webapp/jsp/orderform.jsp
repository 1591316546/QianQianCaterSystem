<%--
  User: QIANG
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--引入统一标签--%>
    <%@include file="headtag.jsp" %>
    <title>我的订单——千乾餐饮</title>
</head>
<body>
<%--引入页眉--%>
<%@include file="header.jsp" %>
<%--内容区--%>
<div class="container">
    <div class="row col-md-offset-5">
        <h2>我的订单</h2>
    </div>
    <div class="row">
        <table class="table table-bordered order-table">
            <thead>
            <tr class="info">
                <th colspan="2">订单编号：123</th>
                <th colspan="3">订单状态：未支付</th>
            </tr>
            <tr class="success">
                <th>菜品</th>
                <th>菜品名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><img src="${pageContext.request.contextPath }/img/cuisine/thumb/1.jpg" alt=""></td>
                <td>蒜香糖醋虾仁</td>
                <td>99.00</td>
                <td>1</td>
                <td>99.00</td>
            </tr>
            <tr>
                <td><img src="${pageContext.request.contextPath }/img/cuisine/thumb/1.jpg" alt=""></td>
                <td>蒜香糖醋虾仁</td>
                <td>99.00</td>
                <td>1</td>
                <td>99.00</td>
            </tr>
            </tbody>
            <tfoot>
                <tr  class="info">
                    <th colspan="3">下单时间：2019-12-02 00:00:00</th>
                    <th colspan="2">总金额：￥99.00</th>
                </tr>
            </tfoot>
        </table>
    </div>
    <div class="row col-md-offset-3 ">
        <nav aria-label="Page navigation">
            <ul id="pageBtn" class="pagination">
                <li>
                    <a href="#" aria-label="首页">
                        <span aria-hidden="true">首页</span>
                    </a>
                </li>
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
                <li>
                    <a href="#" aria-label="末页">
                        <span aria-hidden="true">末页</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="row col-md-offset-3">
        <p>共<span id="totalRecords">0</span>条记录，总<span id="totalPages">0</span>页，当前第<span id="currentPage">0</span>页
        </p>
    </div>
</div>

<%--引入页脚--%>
<%@include file="footer.jsp" %>
</body>
</html>

