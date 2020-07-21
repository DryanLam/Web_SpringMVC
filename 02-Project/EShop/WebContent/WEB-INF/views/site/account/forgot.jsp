<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>FORGOT PASSWORD</h2>
${message}
<form action="account/forgot.htm" method="post">
	<div class="form-group">
		<label>User Name</label>
		<input name="id" class="form-control">
	</div>
	<div class="form-group">
		<label>Email Address</label>
		<input name="email" class="form-control">
	</div>
	<div class="form-group">
		<button class="btn btn-default">Get Password</button>
	</div>
</form>

