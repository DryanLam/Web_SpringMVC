<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<base href="/EShop/">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chu</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <link href="css/jquery-ui.min.css" rel="stylesheet" />
    <script src="js/jquery-ui.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="js/bootstrap.min.js"></script>
    <link href="css/eshop.css" rel="stylesheet" />
</head>
<body>
    <div class="container">
        <header class="nn-header row">
            <h1 class="nn-company">MODERN STORE</h1>
            <h4 class="nn-slogan">The Shopping Center Online</h4>
        </header>
        <div class="navbar navbar-inverse row">
            <div class="navbar-collapse collapse">
            <div class="ntt">
				<!--Menu-->
                <jsp:include page="menu.jsp"/>
				<!--Menu-->
            </div></div>
        </div>
        <div class="nn-sheet row">
            <article class="col-md-9">
                <div class="nn-body">
                	<c:set var="page">../${param.view}</c:set>
                	<jsp:include page="${page}"/>
				</div>
            </article>
            <aside class="col-md-3">
            <div class="row">
				<br/>
                <jsp:include page="cart-info.jsp"/>
                <jsp:include page="search.jsp"/>
                <jsp:include page="category.jsp"/>
                <jsp:include page="supplier.jsp"/>
                <jsp:include page="special.jsp"/>
			</div>
            </aside>
        </div>
		<footer class="ntt row" style="text-align: center">
			<p>DungLam &copy; 2016. All rights reserved.<br>
			   Email: tandunglam@gmail.com<br>
			   Java Project Report
			</p>
		</footer>
	</div>
</body>
</html>