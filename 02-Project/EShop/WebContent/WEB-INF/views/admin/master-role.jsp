<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form action="admin/master-role/index.htm" method="post">
	<div class="form-group">
		<label>Master</label>
		<select name="masterId">
			<c:forEach var="m" items="${masters}">
				<option value="${m.id}">${m.fullName}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label>Roles</label>
		<br>
		<c:forEach var="r" items="${roles}">
		<label>
			<input type="checkbox" name="roleId" value="${r.id}">
			${r.name}
		</label>
		<br>
		</c:forEach>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Save</button>
	</div>
</form>
<script>
$(function(){
	$("button").click(function(){
		if($("form :checked").length == 1){
			alert("Vui lòng chọn ít nhất một vai trò !");
			return false;	
		}
	});
	
	$("select").change(function(){
		var mid = $(this).val();
		$.ajax({
			url:"admin/master-role/get-roles.htm",
			data:{masterId: mid},
			dataType:"json",
			success:function(response){
				$(":checkbox").prop("checked", false);
				$(response).each(function(index, item){
					$(":checkbox[value="+item+"]").prop("checked", true);
				});
			}
		});
	});
	
	$("select").change();
});
</script>














