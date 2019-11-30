<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%--引入统一头部标签--%>
    <jsp:include page="headtag.jsp"></jsp:include>

    <title>商品菜品列表——千乾餐饮</title>
    <script type="text/javascript">
        $(function () {
            var categoryId = getQueryVariable("categoryId");
            // console.log(categoryId);
            //当前页码
            var currentPage = 1;
            //请求第一页的数据
            getCuisineData(categoryId, currentPage);
        });

        //从后台请求数据并显示到页面上
        function getCuisineData(categoryId, currentPage) {
            $.ajax({
                url: "${pageContext.request.contextPath}/cuisine/getCuisineByCategory",
                type: "GET",
                data: {"categoryId": categoryId, "pageSize": 3, "currentPage": currentPage},
                success: function (data) {
                    //获得成功结果
                    if (data.code == 100) {
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
                            '    <a href="javascript:getCuisineData(' + categoryId + ',1);" aria-label="首页">\n' +
                            '        <span aria-hidden="true">首页</span>\n' +
                            '    </a>\n' +
                            '</li>\n' +
                            '<li>\n' +
                            '    <a href="javascript:getCuisineData(' + categoryId + ',' + prePage + ');" aria-label="Previous">\n' +
                            '        <span aria-hidden="true">上一页</span>\n' +
                            '    </a>\n' +
                            '</li>\n';
                        //拼中间的页码
                        for (var i = beginPage; i <= endPage; i++) {
                            //当前页特殊显示
                            if (i == currentPage) {
                                pageBtnHtml += '<li class="active"><a href="javascript:getCuisineData(' + categoryId + ',' + i + ');">' + i + '</a></li>';
                            } else {
                                pageBtnHtml += '<li><a href="javascript:getCuisineData(' + categoryId + ',' + i + ');">' + i + '</a></li>';
                            }
                        }
                        //拼下一页末页
                        pageBtnHtml += '<li>\n' +
                            '    <a href="javascript:getCuisineData(' + categoryId + ',' + nextPage + ');" aria-label="Next">\n' +
                            '        <span aria-hidden="true">下一页</span>\n' +
                            '    </a>\n' +
                            '</li>\n' +
                            '<li>\n' +
                            '    <a href="javascript:getCuisineData(' + categoryId + ',' + totalPages + ');" aria-label="末页">\n' +
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
                }
            });
        }

    </script>
</head>
<body>

<!-- 头部 引入 -->
<jsp:include page="header.jsp"></jsp:include>

<div id="cuisinelist" class="container">
    <div class="row list-item">
        <%--图片--%>
        <div class="col-md-2"><img src="../img/cai/cai_pic2.png" class="img-responsive" alt="菜的图片"/></div>
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
    <div class="row col-md-offset-4">
        <p>共<span id="totalRecords">500</span>条记录，总<span id="totalPages">100</span>页，当前第<span id="currentPage">5</span>页
        </p>
    </div>
</div>

<!-- 页脚 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
