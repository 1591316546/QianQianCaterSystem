<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>商品菜品列表——千乾餐饮</title>
    <script type="text/javascript">
        var categoryId; // 接收菜品类别id参数
        var cname;      // 接收模糊搜索的菜品名字参数
        $(function () {
            categoryId = getQueryVariable("categoryId");
            cname = getQueryVariable("cname");

            // console.log(categoryId);
            //console.log(cname);
            //改变导航栏对应的按钮高亮显示
            if (categoryId){
                //选择器
                var expr = '#header-navbar>ul>li[categoryId='+ categoryId+']';
                $(expr).addClass("active");
            }
            // else {
            //     $("#header-navbar>ul>li").removeClass("active");
            // }
            //请求第一页的数据
            getPage(1);

        });

        //获取想要的页
        function getPage(pageNum) {
            if (categoryId){
                $.ajax({
                    url: "${pageContext.request.contextPath}/cuisine/getCuisineByCategory",
                    type: "GET",
                    data: {"categoryId":categoryId, "pageSize": 3, "currentPage": pageNum},
                    success: function (data) {
                        //获得成功结果
                        if (data.code == 100) {
                            display(data);
                        }
                    }
                });
            }else if (cname){
                cname = window.decodeURI(cname);
                $.ajax({
                    url: "${pageContext.request.contextPath}/cuisine/searchCuisines",
                    type: "GET",
                    data: {"cname":cname, "pageSize": 3, "currentPage": pageNum},
                    success: function (data) {
                        //获得成功结果
                        if (data.code == 100) {
                            display(data);
                        }
                    }
                });
            }


        }

        //拼装数据显示到页面
        function display(data) {
            //获得页对象
            var page = data.extend.page;
            //总记录数
            var totalRecordsNum = page.totalRecords;
            $("#totalRecords").text(totalRecordsNum);
            //总页数
            var totalPages = page.totalPages;
            $("#totalPages").text(totalPages);
            //当前页
            var currentPage = page.currentPage;
            $("#currentPage").text(currentPage);
            // console.log(totalRecordsNum);
            // console.log(totalPages);
            // console.log(currentPage);
            //上一页计算
            var prePage = currentPage - 1;
            if (prePage < 1) {
                prePage = 1;
            }
            //计算下一页
            var nextPage = currentPage + 1;
            if (nextPage > totalPages) {
                nextPage = totalPages;
            }

            //计算页码范围，最多显示5个页码，尽量保持当前页码在中间
            if (totalPages < 5){
                //少于5页
                beginPage = 1;
                endPage = totalPages;
            }else if (currentPage <= 3){//多于5页 ，小于3页
                beginPage = 1;
                endPage = beginPage + 4;
            }else if(currentPage >= totalPages-2 ){ //最后两页
                endPage = totalPages;
                beginPage = endPage - 4;
            }else {                                //中间的普通页码
                var beginPage = currentPage - 2;
                if (beginPage < 1){
                    beginPage = 1;
                }
                var endPage = currentPage + 2;
                if (endPage > totalPages){
                    endPage = totalPages;
                }
            }


            //拼装首页前一页页码按钮
            var pageBtnHtml = '<li>\n' +
                '    <a href="javascript:getPage(1);" aria-label="首页">\n' +
                '        <span aria-hidden="true">首页</span>\n' +
                '    </a>\n' +
                '</li>\n' +
                '<li>\n' +
                '    <a href="javascript:getPage(' + prePage + ');" aria-label="Previous">\n' +
                '        <span aria-hidden="true">上一页</span>\n' +
                '    </a>\n' +
                '</li>\n';
            //拼中间的页码
            for (var i = beginPage; i <= endPage; i++) {
                //当前页特殊显示
                if (i == currentPage) {
                    pageBtnHtml += '<li class="active"><a href="javascript:getPage(' + i + ');">' + i + '</a></li>';
                } else {
                    pageBtnHtml += '<li><a href="javascript:getPage(' + i + ');">' + i + '</a></li>';
                }
            }
            //拼下一页末页
            pageBtnHtml += '<li>\n' +
                '    <a href="javascript:getPage(' + nextPage + ');" aria-label="Next">\n' +
                '        <span aria-hidden="true">下一页</span>\n' +
                '    </a>\n' +
                '</li>\n' +
                '<li>\n' +
                '    <a href="javascript:getPage(' + totalPages + ');" aria-label="末页">\n' +
                '        <span aria-hidden="true">末页</span>\n' +
                '    </a>\n' +
                '</li>';

            //将拼装好的按钮添加到页面
            $("#pageBtn").html(pageBtnHtml);

            var datalist = page.dataList;
            //拼装菜品列表html
            var datalistStr = '';
            for (var i = 0; i < datalist.length; i++) {
                //注意以下运算符是 +=
                datalistStr += '<div class="row list-item">\n' +
                    '    <%--图片--%>\n' +
                    '    <div class="col-md-2"><img src="${pageContext.request.contextPath}/' + datalist[i].image + '" class="img-responsive" alt="菜的图片"/></div>\n' +
                    '    <%--菜的名称和描述--%>\n' +
                    '    <div class="col-md-8">\n' +
                    '        <div class="row"><h3>' + datalist[i].cname + '</h3></div>\n' +
                    '        <div class="row"><p>' + datalist[i].description + '</p></div>\n' +
                    '    </div>\n' +
                    '    <%--菜的价格--%>\n' +
                    '    <div class="col-md-2">\n' +
                    '        <div class="row price-font"><strong>￥ ' + datalist[i].price + '</strong></div>\n' +
                    '        <div class="row"><a href="${pageContext.request.contextPath}/jsp/cuisine_detail.jsp?cid=' + datalist[i].cid + '">点击查看详情</a></div>\n' +
                    '    </div>\n' +
                    '</div>';
            }
            //将拼装好的菜品列表添加到页面上
            $("#cuisinelist").empty().html(datalistStr);
        }

    </script>
</head>
<body>

<!-- 头部 引入 -->
<jsp:include page="header.jsp"></jsp:include>

<div id="cuisinelist" class="container">
    <%--显示菜品列表的地方--%>
    <div class="row">
        <p class="text-center">没有任何东西哦</p>
    </div>
</div>
<div class="container">
    <div class="row col-md-offset-3 ">
        <nav aria-label="Page navigation">
            <ul id="pageBtn" class="pagination">
                <%--<li>
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
                </li>--%>
            </ul>
        </nav>
    </div>
    <div class="row col-md-offset-3">
        <p>共<span id="totalRecords">0</span>条记录，总<span id="totalPages">0</span>页，当前第<span id="currentPage">0</span>页
        </p>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
