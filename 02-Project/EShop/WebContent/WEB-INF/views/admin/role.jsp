<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>ROLE MANAGER</h2>

<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#edit">Edit</a></li>
  <li><a data-toggle="tab" href="#list">List</a></li>
</ul>

<div class="tab-content">
  <div id="edit" class="tab-pane fade in active">
  <!-- FORM -->
  	${message}
    <form:form action="admin/role/index.htm" modelAttribute="role">
		<div class="form-group">
			<label>Id</label>
			<form:input path="id" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Name</label>
			<form:input path="name" cssClass="form-control"/>
		</div>
		
		<div>
			<button class="btn btn-default" data-action="insert">Insert</button>
			<button class="btn btn-default" data-action="update">Update</button>
			<button class="btn btn-default" data-action="delete">Delete</button>
			<button class="btn btn-default" data-action="index">Reset</button>
		</div>
	</form:form>
  <!-- /FORM -->
  </div>
  <div id="list" class="tab-pane fade">
  <!-- TABLE -->
    <table class="table">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th></th>
	</tr>
	<c:forEach var="itm" items="${items}">
	<tr>
		<td>${itm.id}</td>
		<td>${itm.name}</td>
		<td>
			<a href="admin/role/edit/${itm.id}.htm">Edit</a>
		</td>
	</tr>
	</c:forEach>
	</table>
  <!-- /TABLE -->
  </div>
</div>

<script>
$(function(){
	$("button[data-action]").click(function(){
		action = $(this).attr("data-action");
		this.form.action = "admin/role/"+action+".htm"; 
	});
})
</script>
