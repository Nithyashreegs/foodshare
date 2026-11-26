<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Post surplus food"/>
<%@ include file="header.jsp" %>
<div class="card narrow">
  <h2>Post surplus food</h2>
  <c:if test="${not empty error}"><div class="alert"><c:out value="${error}"/></div></c:if>
  <form action="${pageContext.request.contextPath}/add-food" method="post">
    <label>What food is it?
      <input type="text" name="foodName" placeholder="e.g. Veg biryani" required value="<c:out value='${form.foodName}'/>">
    </label>
    <label>Quantity
      <input type="text" name="quantity" placeholder="e.g. 20 plates" required value="<c:out value='${form.quantity}'/>">
    </label>
    <label>Pickup address
      <input type="text" name="location" placeholder="Street, area, landmark" required value="<c:out value='${form.location}'/>">
    </label>
    <label>Safe to eat until
      <input type="datetime-local" name="expiry" required>
    </label>
    <div class="row">
      <button class="btn" type="submit">Post food</button>
      <a class="btn ghost" href="${pageContext.request.contextPath}/dashboard">Cancel</a>
    </div>
  </form>
</div>
<%@ include file="footer.jsp" %>
