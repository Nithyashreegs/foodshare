<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${pageTitle}"/> | SharePlate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/">SharePlate</a>

    <nav>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <span class="who">
                    <c:out value="${sessionScope.user.name}"/>
                    (<c:out value="${sessionScope.user.role}"/>)
                </span>
                <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">Log in</a>
                <a class="btn small" href="${pageContext.request.contextPath}/register">Create account</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

<main class="wrap">

<c:if test="${not empty param.msg}">
    <div class="flash">
        <c:out value="${param.msg}"/>
    </div>
</c:if>