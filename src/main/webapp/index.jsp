<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.user}">
    <c:redirect url="/dashboard"/>
</c:if>

<c:set var="pageTitle" value="Share surplus food"/>

<%@ include file="header.jsp" %>

<section class="hero">
    <h1>Good food should be eaten, not binned.</h1>

    <p>
        Restaurants, hostels, canteens and families post what they could not finish.
        NGOs and people nearby claim it before it spoils.
    </p>

    <div class="row">
        <a class="btn" href="${pageContext.request.contextPath}/register">Create account</a>
        <a class="btn ghost" href="${pageContext.request.contextPath}/login">Log in</a>
    </div>
</section>

<section class="steps">
    <div>
        <h3>Donors post</h3>
        <p>Add the dish, quantity, pickup place and the time it stays safe to eat.</p>
    </div>

    <div>
        <h3>Receivers request</h3>
        <p>Browse what is available right now and send a request in one click.</p>
    </div>

    <div>
        <h3>Pickup is confirmed</h3>
        <p>The donor accepts, the receiver collects, and the post is marked as picked up.</p>
    </div>
</section>

<%@ include file="footer.jsp" %>