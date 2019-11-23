<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>商品菜品列表——千乾餐饮</title>
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
        <h2>商品列表</h2>
    </div>
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
        <%--菜的名称和描述--%>
        <div class="col-md-8">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
        </div>
        <%--菜的价格--%>
        <div class="col-md-2">
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row"><a>点击查看详情</a></div>
        </div>
    </div>
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
        <%--菜的名称和描述--%>
        <div class="col-md-8">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
        </div>
        <%--菜的价格--%>
        <div class="col-md-2">
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row"><a>点击查看详情</a></div>
        </div>
    </div>
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
        <%--菜的名称和描述--%>
        <div class="col-md-8">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
        </div>
        <%--菜的价格--%>
        <div class="col-md-2">
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row"><a>点击查看详情</a></div>
        </div>
    </div>
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
        <%--菜的名称和描述--%>
        <div class="col-md-8">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
        </div>
        <%--菜的价格--%>
        <div class="col-md-2">
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row"><a>点击查看详情</a></div>
        </div>
    </div>
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
        <%--菜的名称和描述--%>
        <div class="col-md-8">
            <div class="row"><h3>菜的名字</h3></div>
            <div class="row"><p>菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述菜的描述</p></div>
        </div>
        <%--菜的价格--%>
        <div class="col-md-2">
            <div class="row price-font"><strong>￥ 99</strong></div>
            <div class="row"><a>点击查看详情</a></div>
        </div>
    </div>

    <div class="row col-md-offset-3 ">
        <nav aria-label="Page navigation">
            <ul class="pagination pagination-lg">
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
    <div class="row col-md-offset-4">
        <p>共<span>500</span>条记录，总<span>100</span>页，当前第<span>5</span>页</p>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
