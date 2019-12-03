<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录——千乾餐饮</title>

    <!-- Bootstrap -->
    <link href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-1.12.4.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/qianqiancater.css"/>
    <script src="../js/public-utils.js"></script>
    <script type="text/javascript">
        $(function () {

            //去掉登录信息和导航栏首页高亮
            $("#login_info").remove();
            $("#bs-example-navbar-collapse-1>ul>li").removeClass("active");

            //获取验证码
            function getCheckCode(){
                var date = new Date().getTime();
                $("#check_code").prop("src", "${pageContext.request.contextPath}/checkCodeServlet?" + date);
            }
            //点击换一张验证码
            $("#check_code_link").click(function () {
                getCheckCode();
                return false;
            });

            //选择校验对象
            var $username = $("#input_username");
            var $password = $("#input_password");
            var $check_code = $("#input_check_code");

            //前端校验用户名
            function checkUsername() {
                var username = $username.val();
                var reg_username = /^\w{5,20}$/;
                return setClassForHint(reg_username.test(username), $username);
            }

            //校验密码
            function checkPassword() {
                var password = $password.val();
                var reg_password = /^\S{6,16}$/;
                return setClassForHint(reg_password.test(password), $password);
            }

            //验证码非空
            function checkCheckCode() {
                var check_code = $check_code.val();
                return setClassForHint(/^\w+$/.test(check_code), $check_code);
            }

            //其他项目失去焦点校验
            $username.blur(checkUsername);
            $password.blur(checkPassword);
            $check_code.blur(checkCheckCode);

            //提交操作
            $("#submit_btn").click(function () {
                if (checkUsername() && checkPassword() && checkCheckCode()) {
                    //校验全部通过,发送post请求到后端提交
                    $.ajax({
                        url: "${pageContext.request.contextPath}/user/login",
                        data: $("#login_form").serialize(),
                        type: "POST",
                        success: function (result) {
                            if (result.code == 100) {
                                // console.log("成功");
                                location.href = "index.jsp";
                            } else {
                                // console.log("失败");
                                alert(result.extend.hint);
                                //刷新验证码
                                getCheckCode();
                            }
                        }
                    });
                    return false;//取消默认行为
                } else {
                    //否则不提交
                    return false;
                }
            });
        });
    </script>

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 头部 加载 -->
<jsp:include page="header.jsp"></jsp:include>

<!-- 主体内容 -->
<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-4  visible-xs">
            <h3>登录</h3>
        </div>
        <div class="col-md-4 col-sm-4 hidden-xs">
            <img src="../img/login_pic.png" class="img-responsive">
        </div>
        <div class="col-md-5 col-sm-5">
            <!-- 登录的表单 -->
            <form id="login_form">
                <div class="form-group">
                    <label class="control-label" for="input_username">用户名</label>
                    <input type="text" class="form-control" id="input_username" placeholder="username" name="username">
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_password">密码</label>
                    <input type="password" class="form-control" id="input_password" placeholder="Password"
                           name="password">
                </div>

                <div class="form-group">
                    <label class="control-label" for="input_check_code">验证码</label>
                    <input type="text" class="form-control" id="input_check_code" placeholder="验证码" name="checkCode">
                    <img id="check_code" src="${pageContext.request.contextPath}/checkCodeServlet" class="img-responsive check_code">
                    <a id="check_code_link" href="#">看不清？换一张</a>
                </div>
                <button id="submit_btn" type="submit" class="btn btn-warning btn-lg">登录</button>
            </form>
        </div>
        <div class="col-md-3 col-sm-3">
            没有账号？<a href="register.jsp">去注册</a><br/>
            忘记账号？忘记密码？<a href="#">去找回</a>
        </div>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
