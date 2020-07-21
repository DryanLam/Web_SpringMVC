<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<h2>${title}</h2>

<table class="table table-default">
<tr>
	<th>GROUP</th>
	<th>TONG GT</th>
	<th>TONG SL</th>
	<th>GIA TN</th>
	<th>GIA CN</th>
	<th>GIA TB</th>
</tr>
<c:forEach var="a" items="${items}">
<tr>
	<td>${a[0]}</td>
	<td><fmt:formatNumber value="${a[1]}" type="currency"/></td>
	<td>${a[2]}</td>
	<td><fmt:formatNumber value="${a[3]}" type="currency"/></td>
	<td><fmt:formatNumber value="${a[4]}" type="currency"/></td>
	<td><fmt:formatNumber value="${a[5]}" type="currency"/></td>
</tr>
</c:forEach>
</table>
