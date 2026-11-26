<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Log in"/>
<%@ include file="header.jsp" %>
<div class="card narrow">
  <h2>Log in</h2>
  <c:if test="${not empty error}"><div class="alert"><c:out value="${error}"/></div></c:if>
  <form action="${pageContext.request.contextPath}/login" method="post">
    <label>Email
      <%-- the lastEmail cookie (set by LoginServlet) pre-fills this --%>
      <input type="email" name="email" required value="<c:out value='${cookie.lastEmail.value}'/>">
    </label>
    <label>Password
      <input type="password" name="password" required>
    </label>
    <button class="btn" type="submit">Log in</button>
  </form>
  <p class="muted">New here? <a href="${pageContext.request.contextPath}/register">Create an account</a></p>
</div>
<%@ include file="footer.jsp" %>
