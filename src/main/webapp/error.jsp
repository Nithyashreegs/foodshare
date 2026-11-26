<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Something went wrong"/>
<%@ include file="header.jsp" %>
<div class="card narrow">
  <h2>That did not work</h2>
  <p>The page you asked for could not be shown. Go back to the dashboard and try again.</p>
  <p class="muted">If it keeps happening, check that MySQL is running and the details in DBConnection.java are correct.</p>
  <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back to dashboard</a>
</div>
<%@ include file="footer.jsp" %>
