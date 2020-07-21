<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<table class="table table-default">
<tr>
	<th>Mã</th>
	<th>Tên</th>
	<th>Đơn giá</th>
	<th>Đơn vị</th>
	<th>Giảm giá</th>
	<th>Số lượng</th>
	<th>Loại</th>
	<th>Nhãn hiệu</th>
	<th></th>
</tr>
<c:forEach var="p" items="${items}">
<tr>
	<td>${p.id}</td>
	<td>${p.name}</td>
	<td>${p.unitPrice}</td>
	<td>${p.unitBrief}</td>
	<td>${p.discount}</td>
	<td>${p.quantity}</td>
	<td>${p.category.name}</td>
	<td>${p.supplier.name}</td>
	<td>
		<a href="admin/product/edit/${p.id}.htm">Edit</a>
	</td>
</tr>
</c:forEach>
</table>