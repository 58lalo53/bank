
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
        <div id="wrapper">
            <div id="header">
                <h1>Welcome to ... Bank</h1>
            </div>
        <h5 class="flash">${flash}</h5>
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
        <h3>Welcome ${sessionScope.cust.fname}&nbsp${sessionScope.cust.lname}</h3>
        <p><a href="/bank/editCust">Edit personal info.</a></p>
    </div>
    </body>
</html>
