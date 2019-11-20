<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户注册——千乾餐饮</title>

    <!-- Bootstrap -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-1.12.4.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/qianqiancater.css"/>
    <script src="js/public-utils.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#login_info").remove();
            $("#bs-example-navbar-collapse-1>ul>li").removeClass("active");

            //获取验证码
            $("#check_code_link").click(function () {
                var date = new Date().getTime();
                $("#check_code").prop("src", "checkCodeServlet?" + date);
                return false;
            });

            //校验用户输入
            //选择校验对象
            var $username = $("#input_username");
            var $password = $("#input_password");
            var $password2 = $("#input_password2");
            var $nickname = $("#input_nickname");
            var $email = $("#input_email");
            var $check_code = $("#input_check_code");

            //前端初步校验用户名
            function checkUsername() {
                var username = $username.val();
                var reg_username = /^\w{5,20}$/;

                // if (reg_username.test(username)) {
                //     //前端校验通过，发Ajax请求
                //     $.post("user/checkUsernameUnique", {"username": $username.val()}, function (result) {
                //         // console.log(result);
                //         if (result.code == 100) {
                //             //后台校验通过,设置正确的提示样式
                //             $username.siblings("span").html(result.extend.hint);
                //             return setClassForHint(true, $username);
                //         } else {
                //             //后台校验失败,设置提示信息
                //             $username.siblings("span").html(result.extend.hint);
                //             return setClassForHint(false, $username);
                //         }
                //     });
                // } else {
                //     //前端校验不通过
                //     $username.siblings("span").html("用户名由8-20位数字、字母或下划线组成");
                //     return setClassForHint(false, $username);
                // }
                if (reg_username.test(username)) {
                    return  true;
                }else {
                    return false;
                }
            }

            //校验密码
            function checkPassword() {
                var password = $password.val();
                var reg_password = /^\S{6,16}$/;
                return setClassForHint(reg_password.test(password), $password);
            }

            //校验确认密码
            function checkPassword2() {
                var password = $password.val();
                var password2 = $password2.val();
                return setClassForHint(password === password2, $password2);
            }

            //校验昵称
            function checkNickname() {
                var nickname = $nickname.val();
                return setClassForHint(/^\S*$/.test(nickname), $nickname);
            }

            //校验邮箱非空
            function checkEmail() {
                var email = $email.val();
                var reg_email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                return setClassForHint(reg_email.test(email), $email);
            }

            //验证码非空
            function checkCheckCode() {
                var check_code = $check_code.val();
                return setClassForHint(/^\w+$/.test(check_code), $check_code);
            }

            //用户名检查唯一
            $username.blur(function () {
                //用户名必须唯一，请求后端检查
                if (checkUsername()){
                    //必须前端校验通过
                    $.post("user/checkUsernameUnique", {"username": $username.val()}, function (result) {
                        // console.log(result);
                        if (result.code == 100) {
                            //后台校验通过,设置正确标识
                            $username.siblings("span").html(result.extend.hint);
                            setClassForHint(true, $username);
                            $username.attr("unique_status","yes");
                        } else {
                            //后台校验失败,设置提示信息
                            $username.siblings("span").html(result.extend.hint);
                            setClassForHint(false, $username);
                            $username.attr("unique_status","no");
                        }
                    });
                }else {
                    //前端校验不通过
                    $username.siblings("span").html("用户名由8-20位数字、字母或下划线组成");
                    setClassForHint(false, $username);
                }
            });

            //其他项目失去焦点校验
            $password.blur(checkPassword);
            $password2.blur(checkPassword2);
            $nickname.blur(checkNickname);
            $email.blur(checkEmail);
            $check_code.blur(checkCheckCode);

            //提交操作
            $("#submit_btn").click(function () {
                // alert($username.attr("unique_status"));
                //检查用户名
                if($username.attr("unique_status") ==undefined || $username.attr("unique_status")== "no"){
                    alert("请修改用户名");
                    return false;
                }
                //检查其他项目
                if (checkPassword() && checkNickname() && checkEmail() && checkCheckCode()) {
                    //校验全部通过,发送post请求到后端提交
                    $.ajax({
                        url:"user/register",
                        data:$("#register_form").serialize(),
                        type:"POST",
                        success: function(result) {
                            if (result.code == 100) {
                                // console.log("成功");
                                location.href="register_ok.jsp";
                            } else {
                                // console.log("失败");
                                alert(result.extend.hint);
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
    <script src="js/jquery-1.12.4.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/qianqiancater.css"/>

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 头部  -->
<jsp:include page="header.jsp"></jsp:include>

<!-- 主体内容 -->
<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-4  visible-xs">
            <h3>用户注册</h3>
        </div>
        <div class="col-md-4 col-sm-4  hidden-xs">
            <img src="img/register_pic.png" class="img-responsive">
        </div>
        <div class="col-md-5 col-sm-5">
            <!-- 注册的表单 -->
            <form id="register_form">
                <div class="form-group">
                    <label class="control-label" for="input_username">登录名</label>
                    <input type="text" class="form-control" id="input_username" placeholder="username" name="username">
                    <span class="help-block">用户名由8-20位数字、字母或下划线组成</span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_password">密码</label>
                    <input type="password" class="form-control" id="input_password" placeholder="Password" name="pswd">
                    <span class="help-block">密码由6-16位数字、字母或其他符号组成</span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_password2">再次输入密码</label>
                    <input type="password" class="form-control" id="input_password2" placeholder="Password"
                           name="pswd2">
                    <span class="help-block">保持和上面输入的密码相同</span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_nickname">昵称</label>
                    <input type="text" class="form-control" id="input_nickname" placeholder="昵称" name="nickname">
                    <span class="help-block">昵称可以是中文、字母、数字、符号等</span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_email">邮箱</label>
                    <input type="email" class="form-control" id="input_email" placeholder="xxx@xx.xx" name="email">
                    <span class="help-block">必填项，用于验证账号，找回密码等</span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="input_email">性别</label>
                    <div class="radio">
                        <label class="radio-inline">
                            <input type="radio" name="gender" id="radio_male" checked="checked" value="male"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" id="radio_female" value="female"> 女
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label" for="input_check_code">验证码</label>
                    <input type="text" class="form-control" id="input_check_code" placeholder="验证码" name="checkCode">
                    <img id="check_code" src="checkCodeServlet" class="img-responsive check_code">
                    <a id="check_code_link" href="#">看不清？换一张</a>
                </div>
                <button id="submit_btn" type="submit" class="btn btn-warning btn-lg">注册</button>
            </form>
        </div>
        <div class="col-md-3 col-sm-3">
            已有账号，<a href="login.jsp">去登录</a>
        </div>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
