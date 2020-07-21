<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>ORDER LIST</h2>

<table class="table">
	<tr>
		<th>Id</th>
		<th>Customer</th>
		<th>Order Date</th>
		<th>Address</th>
		<th>Amount</th>
		<th></th>
	</tr>
	<c:forEach var="o" items="${orders}">
	<tr>
		<td>${o.id}</td>
		<td>${o.customer.fullname}</td>
		<td>${o.orderDate}</td>
		<td>${o.address}</td>
		<td>${o.amount}</td>
		<td>
			<a href="order/detail/${o.id}.htm">Detail</a>
		</td>
	</tr>
	</c:forEach>
	</table>
