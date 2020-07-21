<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- Slideshow -->
	<script src="Slideshow/js/modernizr.custom.63321.js"></script>
    <script src="Slideshow/js/jquery.catslider.js"></script>
    <link href="Slideshow/css/catslider.css" rel="stylesheet" />

    <style>
        .mi-slider {
            height: 330px;
        }
        .mi-slider ul li img {
            height: 200px;
        }
    </style>
    <script>
        $(function () {
            showCatSlider('.mi-slider', 5000);
        });
    </script>
    <div class="mi-slider">
    <c:forEach var="s" items="${sups}">
        <ul>
        <c:forEach var="p" items="${s.products}" end="2">
            <li>
                <a href="product/detail/${p.id}.htm">
                    <img src="images/products/${p.image}" />
                    <h4>${p.unitPrice} USD</h4>
                </a>
            </li>
        </c:forEach>
        </ul>
     </c:forEach>   
        <nav>
        <c:forEach var="s" items="${sups}">
            <a href="#">${s.name}</a>
        </c:forEach>
        </nav>
    </div>

<!-- Special -->
<c:forEach var="p" items="${prods}">
<div class="col-md-4 nn-box2">
	<div class="panel panel-default">
		<div class="panel-heading">${p.name}</div>
		<div class="panel-body">
			<a href="product/detail/${p.id}.htm">
				<img class="${p.id}" src="images/products/${p.image}">
			</a>
		</div>
		<div class="panel-footer" style="height: 35px; padding-top: 5px;">
			<div class="pull-right">
				<img src="images/icons/Letter.png">
				<img src="images/icons/Heart.png">
				<img class="add-to-cart" data-id="${p.id}" src="images/icons/Add to basket.png">
			</div>
			
			<span class="pull-left" style="font-size: 18px;">$ ${p.unitPrice}</span>
		</div>
	</div>
	<c:choose>
		<c:when test="${p.special}">
			<img class="nn-icon" src="images/special_icon.gif">	
		</c:when>
		<c:when test="${p.latest}">
			<img class="nn-icon" src="images/new_icon.gif">	
		</c:when>
		<c:when test="${p.discount > 0}">
			<img class="nn-icon" src="images/promo_icon.gif">	
		</c:when>
	</c:choose>
</div>
</c:forEach>

<style>
.nn-icon{
	position: absolute;
	top: 5px;
	right: 5px;
}
.nn-box2{
	padding: 5px;
	margin: 0px;
	font-size: 12px;
	text-align: center;
	position: relative;
}
.nn-box2>.panel-default{
	padding: 0px;
	margin: 0px;
}
.nn-box2 .panel-body img{
	height: 150px;
	max-width: 99%;
}
</style>

<script>
$(function(){
	$(".add-to-cart").click(function(){
		var pid = $(this).attr("data-id");
		$.ajax({
			url:"cart/add.htm",
			data:{id: pid},
			dataType:"json",
			success: function(response) {
				$(".nn-cart #count").html(response.count + " items");
				$(".nn-cart #amount").html("$ " + response.amount);
			}
		});	
		var src = $("."+pid).attr("src");
		var cartFlyCss = ".cart-fly{background: url("+src+");background-size:100% 100%; }";
		$("#cart-fly-css").html(cartFlyCss);
		var options = {to:".cart-image", className:"cart-fly"};
		$("."+pid).effect("transfer", options, 1000);
	});
});
</script>
<style id="cart-fly-css"></style>