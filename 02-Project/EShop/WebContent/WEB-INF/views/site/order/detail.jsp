<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>ORDER DETAIL</h2>
${message}
<div>
<div class="row">
	<div class="form-group col-md-6">
		<label>Order Id</label>
		<div class="form-control">${order.id}</div>
	</div>
	
	<div class="form-group col-md-6">
		<label>Customer</label>
		<div class="form-control">${order.customer.fullname}</div>
	</div>
</div>
	<div class="form-group">
		<label>Order Date</label>
		<div class="form-control">${order.orderDate}</div>
	</div>
	
	<div class="form-group">
		<label>Required Date</label>
		<div class="form-control">${order.requireDate}</div>
	</div>
	
	<div class="form-group">
		<label>Receiver</label>
		<div class="form-control">${order.receiver}</div>
	</div>
	
	<div class="form-group">
		<label>Receiver Address</label>
		<div class="form-control">${order.address}</div>
	</div>
	
	<div class="form-group">
		<label>Total Amount</label>
		<div class="form-control">${order.amount}</div>
	</div>
	
	<div class="form-group">
		<label>Notes</label>
		<div class="form-control">${order.description}</div>
	</div>
</div>
<fieldset>
	<legend>Chi tiet don hang</legend>
	<table class="table">
	<tr>
		<th>Name</th>
		<th>Unit Price</th>
		<th>Discount</th>
		<th>Quantity</th>
		<th>Amount</th>
	</tr>
	<c:forEach var="d" items="${order.orderDetails}">
	<tr>
		<td>${d.product.name}</td>
		<td>${d.unitPrice}</td>
		<td>${d.discount * 100}%</td>
		<td>${d.quantity}</td>
		<td>${d.unitPrice*d.quantity*(1-d.discount)}</td>
	</tr>
	</c:forEach>
	</table>
</fieldset>

