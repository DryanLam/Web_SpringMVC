<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>SHOPPING CART</h2>

<form action="cart/update.htm" method="post">
	<table class="table">
	<tr>
		<th>Tên</th>
		<th>Đơn giá</th>
		<th>Chiết khấu</th>
		<th>Số lượng</th>
		<th>Thành giá</th>
		<th></th>
	</tr>
	<c:set var="cart" value="${sessionScope['scopedTarget.cart']}"/>
	<c:forEach var="p" items="${cart.items}">
	<tr>
		<td>${p.name}</td>
		<td>${p.unitPrice}</td>
		<td>${p.discount * 100}%</td>
		<td><input name="${p.id}" value="${p.quantity}" size="3" type="number"></td>
		<td>${p.unitPrice*p.quantity*(1-p.discount)}</td>
		<td>
			<a class="remove-from-cart" data-id="${p.id}" 
				href="javascript:;">Hủy</a>
		</td>
	</tr>
	</c:forEach>
	</table>
	<div>
		<button class="btn btn-default">Cập nhật</button>
		<a href="cart/clear.htm" class="btn btn-default">Xóa</a>
		<a href="order/checkout.htm" class="btn btn-default">Checkout</a>
	</div>
</form>
<script>
$(function(){
	$(".remove-from-cart").click(function(){
		var pid = $(this).attr("data-id");
		$.ajax({
			url:"cart/remove.htm",
			data:{id: pid},
			dataType:"json",
			success: function(response) {
				$(".nn-cart #count").html(response.count + " items");
				$(".nn-cart #amount").html("$ " + response.amount);
			}
		});	
		$(this).parents("tr").hide(500);
	});
});
</script>
