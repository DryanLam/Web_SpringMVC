<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<h2>PRODUCT DETAIL</h2>

<div class="row">
	<img class="col-md-5" src="images/products/${prod.image}">
	<ul class="col-md-5">
		<li>Name: ${prod.name}</li>
		<li>Unit Price: ${prod.unitPrice}</li>
		<li>Discount: ${prod.discount}</li>
		<li>Category: ${prod.category.nameVN}</li>
		<li>Supplier: ${prod.supplier.name}</li>
	</ul>
</div>
<div style="text-align: justify;">${prod.description}</div>

<fieldset class="nn-small">
	<legend>Hàng cùng loại</legend>
	<c:forEach var="p" items="${prod.category.products}">
		<a href="product/detail/${p.id}.htm">
			<img src="images/products/${p.image}">
		</a>
	</c:forEach>
</fieldset>

<fieldset class="nn-small">
	<legend>Hàng hãng</legend>
	<c:forEach var="p" items="${prod.supplier.products}">
		<a href="product/detail/${p.id}.htm">
			<img src="images/products/${p.image}">
		</a>
	</c:forEach>
</fieldset>

<style>
.nn-small img{
	width: 60px;
	height: 60px;
	border-radius: 5px;
	box-shadow:0 0 5px gray;
	margin: 3px; 
}
.nn-small img:hover{
	box-shadow:0 0 5px red;
}
</style>





