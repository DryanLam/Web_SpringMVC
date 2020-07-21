<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h2><s:message code="login.subject"/></h2>
${message}
<form action="account/login.htm" method="post">
	<div class="form-group">
		<label><s:message code="login.id"/></label>
		<input name="id" class="form-control" value="${cookie.uid.value}">
	</div>
	<div class="form-group">
		<label><s:message code="login.pw"/></label>
		<input name="password" class="form-control" value="${cookie.upw.value}">
	</div>
	<div class="form-group">
		<label>
			<input name="remember" type="checkbox" value="true">
			<s:message code="login.rm"/>
		</label>
	</div>
	<div class="form-group">
		<button class="btn btn-default"><s:message code="login.bt"/></button>
	</div>
</form>

