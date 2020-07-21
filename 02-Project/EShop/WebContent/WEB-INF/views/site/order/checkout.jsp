<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>CHECKOUT</h2>
${message}
<form:form action="order/purchase.htm" modelAttribute="order">
	<form:hidden path="id"/>
	
	<div class="form-group">
		<label>Customer</label>
		<form:input path="customer.id" class="form-control" readonly="true"/>
	</div>
	
	<div class="form-group">
		<label>Order Date</label>
		<form:input path="orderDate" class="form-control" readonly="true"/>
	</div>
	
	<div class="form-group">
		<label>Required Date</label>
		<form:input path="requireDate" class="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Receiver</label>
		<form:input path="receiver" class="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Receiver Address</label>
		<form:input path="address" class="form-control"/>
	</div>
	
	<div class="form-group">
		<label>Total Amount</label>
		<form:input path="amount" class="form-control" readonly="true"/>
	</div>
	
	<div class="form-group">
		<label>Notes</label>
		<form:textarea path="description" class="form-control" rows="4"/>
	</div>

	<div class="form-group">
		<button class="btn btn-default">Purchase</button>
	</div>
</form:form>
<fieldset>
	<legend>Hàng đã chọn</legend>
	<table class="table">
	<tr>
		<th>Tên</th>
		<th>Đơn giá</th>
		<th>Chiết khấu</th>
		<th>Số lượng</th>
		<th>Thành giá</th>
	</tr>
	<c:set var="cart" value="${sessionScope['scopedTarget.cart']}"/>
	<c:forEach var="p" items="${cart.items}">
	<tr>
		<td>${p.name}</td>
		<td>${p.unitPrice}</td>
		<td>${p.discount * 100}%</td>
		<td>${p.quantity}</td>
		<td>${p.unitPrice*p.quantity*(1-p.discount)}</td>
	</tr>
	</c:forEach>
	</table>
</fieldset>

