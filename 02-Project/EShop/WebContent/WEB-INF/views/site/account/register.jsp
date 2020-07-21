<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>ĐĂNG KÝ TÀI KHOẢN</h2>
${message}
<form:form action="account/register.htm" modelAttribute="user"
	enctype="multipart/form-data">
	<div class="form-group">
		<label>Tên đăng nhập</label>
		<form:input path="id" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Mật khẩu</label>
		<form:input path="password" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Họ Tên</label>
		<form:input path="fullname" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Địa chỉ Email</label>
		<form:input path="email" class="form-control"/>
	</div>
	<!-- <div class="form-group">
		<label>Hình</label>
		<input type="file" name="filePhoto">
	</div> -->
	<form:hidden path="activated"/>

	<div class="form-group">
		<button class="btn btn-default">Đăng ký</button>
	</div>
</form:form>

