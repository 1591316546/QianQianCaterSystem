<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="headtag.jsp"></jsp:include>
    <title>千乾餐饮</title>


    <script type="text/javascript">
        $(function () {
            //点亮导航栏的首页按钮
            $("#header-navbar>ul>li").first().addClass("active");
            /* 轮播图时间切换间隔 */
            $('.carousel').carousel({
                interval: 2000
            });

            //请求特色菜的数据
            $.get("${pageContext.request.contextPath}/cuisine/getSpecialCuisines",function (data) {
                var cuisineList = data.extend.cuisines;
                // 拼接Html
                var cuisineListHtml = "";
                for (var i=0;i<8; i++){
                    cuisineListHtml += '<div class="col-sm-6 col-md-3" cid="'+ cuisineList[i].cid +'">\n' +
                        '    <div class="thumbnail">\n' +
                        '        <img src="${pageContext.request.contextPath}/'+ cuisineList[i].image +'" alt="...">\n' +
                        '        <div class="caption">\n' +
                        '            <h4>'+ cuisineList[i].cname +'</h4>\n' +
                        '            <p class="description-info">'+ cuisineList[i].description+'</p>\n' +
                        '            <p><span style="color: red;">￥ '+ cuisineList[i].price+'</span></p>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>';
                }
                //添加到页面上
                $("#cuisine-list").html(cuisineListHtml);
                //给每个缩略图添加点击事件
                $("#cuisine-list>div").click(function () {
                    location.href = "${pageContext.request.contextPath}/jsp/cuisine_detail.jsp?cid="+$(this).attr("cid");
                });
            });
        });
    </script>

</head>
<body>
<!-- 广告条 -->
<div class="container-fluid">
    <div id="header_ad_pic" class="row">
        <img src="../img/header_ad_pic.gif" class="img-responsive">
    </div>
</div>
<!-- 头部 引入 -->
<jsp:include page="header.jsp"></jsp:include>

<!-- 轮播图广告 -->
<div class="container-fluid">
    <div class="row">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- 小圆点指示器 -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="../img/lunbotu1.png" alt="">
                </div>
                <div class="item">
                    <img src="../img/lunbo_2.png" alt="">
                </div>
                <div class="item">
                    <img src="../img/lunbo_3.png" alt="">
                </div>
                <div class="item">
                    <img src="../img/lunbo4.png" alt="">
                </div>
                <div class="item">
                    <img src="../img/lunbo5.png" alt="">
                </div>
            </div>

            <!-- 控制按钮 -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</div>

<!-- 主体内容 -->
<div class="container">
    <div class="row specialty">
        <div class="col-md-12">
            <h3>特色菜品</h3>
        </div>
    </div>
    <%--特色菜列表--%>
    <div id="cuisine-list" class="row">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic1.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic2.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic3.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic4.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic5.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic6.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic7.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="../img/cai/cai_pic8.png" alt="...">
                <div class="caption">
                    <h4>菜品名称</h4>
                    <p>菜品描述菜品描述菜品描述菜品描述菜品描述品描述菜品描述菜品描述菜品描述菜品描述</p>
                    <p><span style="color: red;">￥99</span></p>
                </div>
            </div>
        </div>
    </div>

    <div class="row specialty">
        <div class="col-md-12">
            <h3>优雅环境</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="../img/huanjing1.png" class="img-responsive">
        </div>
        <div class="col-md-4">
            <img src="../img/huanjing2.png" class="img-responsive">
        </div>
        <div class="col-md-4">
            <img src="../img/huanjing3.png" class="img-responsive">
        </div>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
