<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>CATEGORY MANAGER</h2>

<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#edit">Edit</a></li>
  <li><a data-toggle="tab" href="#list">List</a></li>
</ul>

<div class="tab-content">
  <div id="edit" class="tab-pane fade in active">
  <!-- FORM -->
  	${message}
    <form:form action="admin/product/index.htm" modelAttribute="product"
		enctype="multipart/form-data">
		
		<div class="row">
			<div class="form-group col-md-4">
				<label for="id">Mã hàng hóa</label>
				<form:input path="id" cssClass="form-control"/>
			</div>
			
			<div class="form-group col-md-4">
				<label for="name">Tên hàng hóa</label>
				<form:input path="name" cssClass="form-control"/>
			</div>
		
			<div class="form-group col-md-4">
				<label for="unitPrice">Đơn giá</label>
				<form:input path="unitPrice" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="row">	
			<div class="form-group col-md-4">
				<label for="unitBrief">Đơn vị tính</label>
				<form:input path="unitBrief" cssClass="form-control"/>
			</div>

			<div class="form-group col-md-4">
				<label for="discount">Giảm giá</label>
				<form:input path="discount" cssClass="form-control"/>
			</div>
			
			<div class="form-group col-md-4">
				<label for="quantity">Số lượng</label>
				<form:input path="quantity" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-4">
				<label for="productDate">Ngày nhập</label>
				<form:input path="productDate" cssClass="form-control nn-date"/>
			</div>

			<div class="form-group col-md-4">
				<label for="supplier.id">Nhãn hiệu</label>
				<form:select path="supplier.id" 
					items="${suppliers}" itemValue="id" itemLabel="name" cssClass="form-control"/>
			</div>

			<div class="form-group col-md-4">
				<label for="category.id">Loại</label>
				<form:select path="category.id" 
					items="${categories}" itemValue="id" itemLabel="name" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-4">
				<label>Đặc điểm</label>
				<div>
					<label><form:checkbox path="available"/> Available</label>
					<label><form:checkbox path="special"/> Special</label>
					<label><form:checkbox path="latest"/> latest</label>
				</div>
			</div>
			
			<div class="form-group col-md-4">
				<label>Hình ảnh</label>
				<input type="file" name="fileImage">
				<form:hidden path="image"/>
			</div>
			
			<div class="form-group col-md-4">
				<label>So luot xem</label>
				<form:input path="views" cssClass="form-control nn-date"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label for="description">Mô tả</label>
				<form:textarea path="description" rows="5" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<button class="btn btn-default" data-action="insert">Thêm mới</button>
				<button class="btn btn-default" data-action="update">Cập nhật</button>
				<button class="btn btn-default" data-action="delete">Xóa</button>
				<button class="btn btn-default" data-action="index">Nhập lại</button>
			</div>
		</div>
		
	</form:form>
  <!-- /FORM -->
  </div>
  <div id="list" class="tab-pane fade">
  <!-- TABLE -->
    <div id="gridview"></div>
    <div>
    	<ul class="pager">
	    	<li><a href="javascript:;">First</a></li>
	    	<li><a href="javascript:;">Prev</a></li>
	    	<li><a href="javascript:;">Next</a></li>
	    	<li><a href="javascript:;">Last</a></li>
    	</ul>
    </div>
  <!-- /TABLE -->
  </div>
</div>

<script>
$(function(){
	$pageNo = 0;
	$pageCount = ${pageCount};
	function loadPage(){
		$.ajax({
			url:"admin/product/loadpage.htm",
			data:{pageNo:$pageNo},
			success:function(response){
				$("#gridview").html(response);
			}
		});
	}
	loadPage();
	
	$(".pager>li:eq(0)").click(function(){
		$pageNo = 0;
		loadPage();
	});
	$(".pager>li:eq(1)").click(function(){
		if($pageNo > 0){
			$pageNo -= 1;
			loadPage();	
		}
	});
	$(".pager>li:eq(2)").click(function(){
		if($pageNo < $pageCount-1){
			$pageNo += 1;
			loadPage();	
		}
	});
	$(".pager>li:eq(3)").click(function(){
		$pageNo = $pageCount-1;
		loadPage();
	});
	
	$("button[data-action]").click(function(){
		action = $(this).attr("data-action");
		this.form.action = "admin/product/"+action+".htm"; 
	});
})
</script>






