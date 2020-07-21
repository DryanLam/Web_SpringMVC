<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<base href="/EShop/">
	
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Website Administrative Tool</title>

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>

    <link href="css/jquery-ui.min.css" rel="stylesheet" />
    <script src="js/jquery-ui.min.js"></script>

    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="js/bootstrap.min.js"></script>

    <link href="css/eshop.css" rel="stylesheet" />
    
    <script type="text/javascript">
    $(function(){
    	$(".calendar").datepicker({
        	dateFormat:'mm/dd/yy'
        });	
    });
    </script>
</head>
<body>
    <div class="container">
        <div class="navbar navbar-inverse row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="/" class="navbar-brand">Trang chủ</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Quản lý <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="admin/product/index.htm">Hàng hóa</a></li>
                            <li><a href="admin/supplier/index.htm">Nhà cung cấp</a></li>
                            <li><a href="admin/category/index.htm">Chủng loại</a></li>
                            <li><a href="admin/customer/index.htm">Khách hàng</a></li>
                            <li><a href="admin/order/index.htm">Hóa đơn</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Thống kê <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class="media-heading">Kiểm kê hàng tồn</li>
                            <li><a href="admin/inventory/by-category.htm">Theo loại</a></li>
                            <li><a href="admin/inventory/by-supplier.htm">Theo hãng</a></li>
                            <li class="media-heading">Thống kê doanh số</li>
                            <li><a href="admin/revenue/by-product.htm">Theo từng mặt hàng</a></li>
                            <li><a href="admin/revenue/by-category.htm">Theo từng loại hàng</a></li>
                            <li><a href="admin/revenue/by-supplier.htm">Theo từng hãng</a></li>
                            <li><a href="admin/revenue/by-customer.htm">Theo từng khách hàng</a></li>
                            <li class="divider"></li>
                            <li><a href="admin/revenue/by-year.htm">Theo từng năm</a></li>
                            <li><a href="admin/revenue/by-quarter.htm">Theo từng quí</a></li>
                            <li><a href="admin/revenue/by-month.htm">Theo từng tháng</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">Tài khoản <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="admin/home/logoff.htm">Đăng xuất</a></li>
                            <li><a href="admin/home/change.htm">Đổi mật khẩu</a></li>
                            <li class="divider"></li>
                            <li><a href="admin/master/index.htm">Quản lý tài khoản</a></li>
                            <li><a href="admin/role/index.htm">Quản lý vai trò</a></li>
                            <li><a href="admin/master-role/index.htm">Phân vai</a></li>
                            <li><a href="admin/role-action/index.htm">Phân quyền</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>

        <div class="nn-sheet row">
            <article class="nn-body">
                <c:set var="page">../${param.view}</c:set>
                <jsp:include page="${page}"/>
            </article>
        </div>

        <footer class="row" style="text-align:center">
            <p>Nhất Nghệ &copy; 08-2016. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>