<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<c:set var="cart" value="${sessionScope['scopedTarget.cart']}"/>

<div class="panel panel-default nn-cart">
    <div class="panel-body">
        <img class="col-md-5 cart-image" src="images/shoppingcart.gif" />
        <ul class="col-md-7">
            <li id="count">${cart.count} <s:message code="cart.items"/></li>
            <li id="amount">$ ${cart.amount}</li>
            <li><a href="cart/index.htm"><s:message code="cart.view"/></a></li>
        </ul>
    </div>
</div>