<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form action="admin/role-action/index.htm" method="post">
	<div class="form-group row">
		<label>Role</label>
		<select name="roleId">
			<c:forEach var="r" items="${roles}">
				<option value="${r.id}">${r.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group row">
		<label>Actions</label>
		<br>
		<c:forEach var="a" items="${actions}">
		<div class="col-md-3">
			<label class="checkbox-inline">
				<input type="checkbox" name="actionId" value="${a.id}">
				${a.description}
			</label>
		</div>
		</c:forEach>
	</div>
	<div class="form-group row">
		<button class="btn btn-default">Save</button>
	</div>
</form>
<script>
$(function(){
	$("button").click(function(){
		if($("form :checked").length == 1){
			alert("Vui lòng chọn ít nhất một action !");
			return false;	
		}
	});
	
	$("select").change(function(){
		var rid = $(this).val();
		$.ajax({
			url:"admin/role-action/get-actions.htm",
			data:{roleId: rid},
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














