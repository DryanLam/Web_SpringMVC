<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<ul class="nav navbar-nav">
    <li><a href=""><s:message code="menu.home"/></a></li>
    <li><a href="">Giới thiệu</a></li>
    <li><a href="">Liên hệ</a></li>
    <li><a href="">Góp ý</a></li>
    <li><a href="">Hỏi đáp</a></li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tài khoản <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <c:choose>
            	<c:when test="${empty sessionScope.user}">
            		<li><a href="account/login.htm">Đăng nhập</a></li>
		            <li><a href="account/forgot.htm">Quên mật khẩu</a></li>
		            <li><a href="account/register.htm">Đăng ký thành viên</a></li>
            	</c:when>
            	<c:otherwise>
            		<li><a href="account/logoff.htm">Đăng xuất</a></li>
		            <li><a href="account/change.htm">Đổi mật khẩu</a></li>
		            <li><a href="account/edit.htm">Cập nhật hồ sơ</a></li>
		            <li class="divider"></li>
		            <li><a href="order/list.htm">Đơn hàng</a></li>
		            <li><a href="order/products.htm">Hàng đã mua</a></li>	
            	</c:otherwise>
            </c:choose>
        </ul>
    </li>
</ul>
<ul class="nav navbar-nav navbar-right">
    <li><a href="javascript:;" data-lang="vi">Tiếng Việt</a></li>
    <li><a href="javascript:;" data-lang="en">English</a></li>
</ul>

<script>
$(function(){
	$("a[data-lang]").click(function(){
		lang = $(this).attr("data-lang");
		$.ajax({
			url:"home/set-lang.htm",
			data:{lang: lang},
			success:function(response){
				location.reload();
			}
		})
	});
});
</script>








