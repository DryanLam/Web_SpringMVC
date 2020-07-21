<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>MASTER MANAGER</h2>

<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#edit">Edit</a></li>
  <li><a data-toggle="tab" href="#list">List</a></li>
</ul>

<div class="tab-content">
  <div id="edit" class="tab-pane fade in active">
  <!-- FORM -->
  	${message}
    <form:form action="admin/master/index.htm" modelAttribute="master">
		<div class="form-group">
			<label>Id</label>
			<form:input path="id" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Password</label>
			<form:input path="password" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Full Name</label>
			<form:input path="fullName" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Email Address</label>
			<form:input path="email" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>Mobile</label>
			<form:input path="mobile" cssClass="form-control"/>
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
		<th>Password</th>
		<th>Full Name</th>
		<th>Email</th>
		<th>Mobile</th>
		<th></th>
	</tr>
	<c:forEach var="itm" items="${items}">
	<tr>
		<td>${itm.id}</td>
		<td>${itm.password}</td>
		<td>${itm.fullName}</td>
		<td>${itm.email}</td>
		<td>${itm.mobile}</td>
		<td>
			<a href="admin/master/edit/${itm.id}.htm">Edit</a>
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
		this.form.action = "admin/master/"+action+".htm"; 
	});
})
</script>
