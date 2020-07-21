<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
	<c:when test="${fn:startsWith(param.view, 'admin')}">
		<jsp:include page="admin/layout.jsp"/>
	</c:when>
	<c:when test="${fn:startsWith(param.view, 'ajax')}">
		<jsp:include page="ajax/blank_layout.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:include page="site/layout.jsp"/>
	</c:otherwise>
</c:choose>