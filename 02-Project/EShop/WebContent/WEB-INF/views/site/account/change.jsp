<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>CHANGE PASSWORD</h2>
${message}
<form action="account/change.htm" method="post">
	<div class="form-group">
		<label>Tên Đăng Nhập</label>
		<input name="id" class="form-control">
	</div>
	<div class="form-group">
		<label>Mật khẩu hiện tại</label>
		<input name="password" class="form-control">
	</div>
	<div class="form-group">
		<label>Mật khẩu mới</label>
		<input name="password1" class="form-control">
	</div>
	<div class="form-group">
		<label>Nhập lại mật khẩu mới</label>
		<input name="password2" class="form-control">
	</div>
	<div class="form-group">
		<button class="btn btn-default">Thay đổi mật khẩu</button>
	</div>
</form>

