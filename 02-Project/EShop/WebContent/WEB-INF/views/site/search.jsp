<%@ page pageEncoding="utf-8"%>
<div class="panel panel-default">
    <div class="panel-body">
        <form action="product/list-by-keywords.htm" method="post">
        	<div class="input-group">
	        	<input name="keywords" value="${param.keywords}" class="form-control">
	        	<div class="input-group-addon">
	        		<span class="glyphicon glyphicon-search"></span>
	        	</div>
        	</div>
        </form>
    </div>
</div>