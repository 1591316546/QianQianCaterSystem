<%--
  User: QIANG
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--引入统一标签--%>
    <%@include file="headtag.jsp" %>
    <title>我的订单——千乾餐饮</title>
    <script>
        //对日期类扩展使其能格式化日期
        Date.prototype.format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ?
                        (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }

        $(function () {
            // var pageSize = 3;
            // var currentPage = 1;
            //页面加载完成展示首页数据
            getPage(1);
        });

        function getPage(i) {
            $.ajax({
                url: "${pageContext.request.contextPath}/order/getOrderFromList",
                data: {"pageSize": 2, "currentPage": i},
                success: function (data) {
                    if (data.code == 100) {
                        //获取数据成功 展示数据
                        display(data);
                    } else {
                        //弹出错误提示
                        alert(data.msg);
                    }
                }
            });
        }

        /**
         * 展示数据
         * @param data
         */
        function display(data) {
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

            //订单数据数组
            var orderArray = page.dataList;

            //遍历数组拼接订单表格HTML
            var orderTableListHtml = "";
            for (var i = 0; i < orderArray.length; i++) {
                //支付状态
                var orderStatus = '';
                switch (orderArray[i].status) {
                    case 0:
                        orderStatus = "未支付";
                        break;
                    case 1:
                        orderStatus = "已支付";
                        break;
                    case 2:
                        orderStatus = "已接单";
                        break;
                    case 3:
                        orderStatus = "已完成";
                        break;
                    case 4:
                        orderStatus = "已退款";
                        break;
                }

                //1.拼接表格头
                orderTableListHtml += '<table class="table table-bordered order-table">\n' +
                    '<thead>\n' +
                    '<tr class="info">\n' +
                    '    <th colspan="2">订单编号：' + orderArray[i].oid + '</th>\n' +
                    '    <th colspan="3">订单状态：' + orderStatus +'</th>\n' +
                    '</tr>\n' +
                    '<tr class="success">\n' +
                    '    <th>菜品</th>\n' +
                    '    <th>菜品名称</th>\n' +
                    '    <th>单价</th>\n' +
                    '    <th>数量</th>\n' +
                    '    <th>小计</th>\n' +
                    '</tr>\n' +
                    '</thead>\n' +
                    '<tbody>';
                //2.拼接表格每一行内容
                var orderItem = orderArray[i].orderItemList;
                for (var j= 0; j<orderItem.length;j++){
                    var cuisine = orderItem[j].cuisine;
                    orderTableListHtml += '<tr>\n' +
                        '    <td><img src="${pageContext.request.contextPath }/'+cuisine.image+'" alt=""></td>\n' +
                        '    <td>'+ cuisine.cname +'</td>\n' +
                        '    <td>'+ cuisine.price +'</td>\n' +
                        '    <td>'+ orderItem[j].count +'</td>\n' +
                        '    <td>'+ orderItem[j].subtotal +'</td>\n' +
                        '</tr>';
                }

                //3.拼接表格脚
                orderTableListHtml += '</tbody>\n' +
                    '    <tfoot>\n' +
                    '    <tr class="info">\n' +
                    '        <th colspan="3">下单时间：' + new Date(orderArray[i].orderTime).format("yyyy-MM-dd hh:mm:ss") + '</th>\n' +
                    '        <th colspan="2">总金额：￥' + orderArray[i].totalMoney + '</th>\n' +
                    '    </tr>\n' +
                    '    </tfoot>\n' +
                    '</table>';

            }
            //添加到页面显示
            $("#div-orderList").html(orderTableListHtml);
        }
    </script>
</head>
<body>
<%--引入页眉--%>
<%@include file="header.jsp" %>
<%--内容区--%>
<div class="container">
    <div class="row col-md-offset-5">
        <h2>我的订单</h2>
    </div>
    <div id="div-orderList" class="row">
        <%--<table class="table table-bordered order-table">
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
            <tr class="info">
                <th colspan="3">下单时间：2019-12-02 00:00:00</th>
                <th colspan="2">总金额：￥99.00</th>
            </tr>
            </tfoot>
        </table>--%>
    </div>
    <div class="row col-md-offset-3 ">
        <nav aria-label="Page navigation">
            <ul id="pageBtn" class="pagination">

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

