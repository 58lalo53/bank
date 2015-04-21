<%-- 
    Document   : index
    Created on : Apr 1, 2015, 1:05:33 PM
    Author     : 58lalo53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <title>... Bank</title>
    </head>
    <body>
        <div id="warpper">
            <div id="header">
                <h1>Welcome to ... Bank</h1>
            </div>
        <h3>${flash}</h3>
            <div id="nav">
                    <p>
                <c:choose>
                    <c:when test="${sessionScope.cust.username != null}">
                        <%@include file ="loginMenu.jspf" %>
                    </c:when>
                    <c:otherwise>
    <li>
        <ul><a href="/bank/home">Home</a></ul>
        <ul><a href="/bank/login">Login</a></ul>
        <ul><a href="/bank/register">Register</a></ul>

    </li>
                    </c:otherwise>
                </c:choose>
            </p>
            </div>
    <h3>Welcome ${sessionScope.cust.toString()}</h3>
    </div>
    </body>
</html>
