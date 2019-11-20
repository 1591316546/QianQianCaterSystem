<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>千乾餐饮</title>

		<!-- Bootstrap -->
		<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="js/jquery-1.12.4.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/qianqiancater.css" />
		<script type="text/javascript">
			$(function() {
				//插入页顶广告条
				// $("<img>").prop("src","img/header_ad_pic.gif").addClass("img-responsive").appendTo($("#header_ad_pic"));
				// $("#header_ad_pic").slideDown(slow,linear);
				/* 轮播图时间切换间隔 */
				$('.carousel').carousel({
					interval: 2000
				});
			});
			// window.onload = function(){
			// 	console.log("加载完成");
			// };
		</script>
		<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
		<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
		<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
	</head>
	<body>
		<!-- 广告 -->
		<!-- 广告条 -->
		<div id="header_ad_pic" class="row">
			<img src="img/header_ad_pic.gif" class="img-responsive">
		</div>
		<!-- 头部 加载 -->
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
							<img src="img/lunbotu1.png" alt="">
						</div>
						<div class="item">
							<img src="img/lunbo_2.png" alt="">
						</div>
						<div class="item">
							<img src="img/lunbo_3.png" alt="">
						</div>
						<div class="item">
							<img src="img/lunbo4.png" alt="">
						</div>
						<div class="item">
							<img src="img/lunbo5.png" alt="">
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
			<div class="row">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
							<p><span style="color: red;">￥99</span></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src="img/cai/cai_pic1.png" alt="...">
						<div class="caption">
							<h4>菜品名称</h4>
							<p>菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述菜品描述</p>
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
					<img src="img/huanjing1.png" class="img-responsive">
				</div>
				<div class="col-md-4">
					<img src="img/huanjing2.png" class="img-responsive">
				</div>
				<div class="col-md-4">
					<img src="img/huanjing3.png" class="img-responsive">
				</div>
			</div>
		</div>

		<!-- 页脚 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>