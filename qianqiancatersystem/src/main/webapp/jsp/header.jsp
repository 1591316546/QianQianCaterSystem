<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="container-fluid">
        <!-- logo-->
        <div class="row">
            <div class="col-md-3 col-md-offset-1">
                <img src="../img/header_logo.gif" class="img-responsive">
            </div>
            <div class="col-md-4">
                <!--  搜索框  -->
                <div id="search-bar" class="input-group">
                    <input type="text" class="form-control" placeholder="寻找美食">
                    <span class="input-group-btn">
					<button class="btn" type="button">搜索</button>
				</span>
                </div><!-- /input-group -->
            </div>
            <div class="col-md-4">
                <div id="login_info">
                    <a href="${pageContext.request.contextPath }/jsp/login.jsp">立即登录</a>
                    <a href="${pageContext.request.contextPath }/jsp/register.jsp">注册</a>
                </div>
            </div>
        </div>

        <!-- 导航条 -->
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container">
                    <!-- 小屏设备折叠时显示的导航条 -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#header-navbar"
                                aria-expanded="false">
                            <span class="sr-only">切换按钮</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand">千乾餐饮</a>
                    </div>

                    <!--导航栏的按钮 -->
                    <div class="collapse navbar-collapse" id="header-navbar">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/jsp/index.jsp">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/cuisine_list.jsp?categoryId=1">精品炒菜</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/jsp/cuisine_list.jsp?categoryId=2">劲道面条</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/jsp/cuisine_list.jsp?categoryId=3">美味汤羹</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/jsp/cuisine_list.jsp?categoryId=4">米饭盖饭</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/jsp/cuisine_list.jsp?categoryId=5">饮品酒水</a>
                            </li>
                            <li><a href="#">宴席预定</a></li>
                            <li><a href="#">联系我们</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
    </div>

    <script>
        $(function () {
            //显示登录信息
            $.get("${pageContext.request.contextPath}/user/getLoginInfo", function (data) {
                if (data.code == 100) {
                    $("#login_info").empty();
                    $span = $("<span>").html("欢迎回来，");
                    $a_nickname = $("<a>").html(data.extend.nickname).prop("href", "personal_data.jsp");
                    $a_quit = $("<a>").html("退出登录").prop("href", "#").prop("id", "a-logout");
                    $a_shoppingcart = $("<a>").html("我的菜篮子").prop("href", "shoppingcart.jsp");
                    $a_orderform = $("<a>").html("我的订单").prop("href", "orderform.jsp");
                    $("#login_info").append($span).append($a_nickname).append($a_quit).append($a_shoppingcart).append($a_orderform);

                    //给退出登录按钮绑定点击
                    $("#a-logout").click(function () {
                        $.get("${pageContext.request.contextPath}/user/logout", function (msg) {
                            if (msg.code == 100) {
                                //退出登录,刷新页面到首页
                                location.href = "${pageContext.request.contextPath}/jsp/index.jsp";
                            }
                        });
                        return false;
                    });
                }
            });
            //搜索功能
            $("#search-bar button").click(function () {
                var cname = $("#search-bar>input").val();
                //获取搜索框的内容跳转
                location.href = "${pageContext.request.contextPath}/jsp/cuisine_list.jsp?cname=" + cname;
            });
        });
    </script>
</header>