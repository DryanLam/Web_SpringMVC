<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>ĐĂNG NHẬP</h2>
${message}
<form action="admin/home/login.htm" method="post">
	<div class="form-group">
		<label>Tên Đăng Nhâp</label>
		<input name="id" class="form-control">
	</div>
	<div class="form-group">
		<label>Mật khẩu</label>
		<input name="password" class="form-control">
	</div>
	<div class="form-group">
		<button class="btn btn-default">Đăng nhập</button>
	</div>
</form>

