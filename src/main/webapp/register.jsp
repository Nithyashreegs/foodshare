<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Create account"/>
<%@ include file="header.jsp" %>
<div class="card narrow">
  <h2>Create your account</h2>
  <c:if test="${not empty error}"><div class="alert"><c:out value="${error}"/></div></c:if>
  <div id="jsError" class="alert" style="display:none"></div>
  <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validateForm()">
    <label>Full name or organisation
      <input type="text" name="name" required value="<c:out value='${form.name}'/>">
    </label>
    <label>Email
      <input type="email" name="email" required value="<c:out value='${form.email}'/>">
    </label>
    <label>Password (min 6 characters)
      <input type="password" name="password" id="password" required>
    </label>
    <label>Phone (10 digits)
      <input type="text" name="phone" id="phone" required value="<c:out value='${form.phone}'/>">
    </label>
    <label>City
      <input type="text" name="city" required value="<c:out value='${form.city}'/>">
    </label>
    <label>I want to
      <select name="role" required>
        <option value="">Choose one</option>
        <option value="DONOR" <c:if test="${form.role == 'DONOR'}">selected</c:if>>Donate surplus food</option>
        <option value="RECEIVER" <c:if test="${form.role == 'RECEIVER'}">selected</c:if>>Receive food (NGO or individual)</option>
      </select>
    </label>
    <button class="btn" type="submit">Create account</button>
  </form>
  <p class="muted">Already registered? <a href="${pageContext.request.contextPath}/login">Log in</a></p>
</div>
<script>
  // Client-side validation (Module 1: JavaScript)
  function validateForm() {
    var msg = "";
    if (document.getElementById("password").value.length < 6) msg = "Password must be at least 6 characters.";
    else if (!/^\d{10}$/.test(document.getElementById("phone").value)) msg = "Phone number must be exactly 10 digits.";
    var box = document.getElementById("jsError");
    box.style.display = msg ? "block" : "none";
    box.textContent = msg;
    return msg === "";
  }
</script>
<%@ include file="footer.jsp" %>
