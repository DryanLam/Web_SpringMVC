<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>REGISTRATION</h2>
${message}
<form:form action="account/update.htm" modelAttribute="user"
	enctype="multipart/form-data">
	<form:hidden path="password"/>
	
	<div class="form-group">
		<label>Tên Đăng Nhập</label>
		<form:input path="id" class="form-control" readonly="true"/>
	</div>
	<div class="form-group">
		<label>Họ và Tên</label>
		<form:input path="fullname" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Địa chỉ Email</label>
		<form:input path="email" class="form-control"/>
	</div>
	<div class="form-group">
		<label>Hình (${user.photo})</label>
		<input type="file" name="filePhoto">
		<form:hidden path="photo"/>
		<img src="images/customers/${user.photo}" width="150">
	</div>
	<form:hidden path="activated"/>

	<div class="form-group">
		<button class="btn btn-default">Cập nhật</button>
	</div>
</form:form>

