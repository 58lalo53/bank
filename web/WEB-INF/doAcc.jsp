

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>... Bank</title>
    </head>
    <body>
        <h1>... Bank</h1>
        <c:choose>
            <c:when test = "${sessionScope.cust.username!= null}">
                <%@include file="loginMenu.jspf" %>
            </c:when>
        </c:choose>
        <h3>Hello ${sessionScope.cust.toString()}</h3>
        <p>Your Account Number is <c:out value="${sessionScope.acc.getAccNum()}">${sessionScope.acc.getAccNum()}</c:out></p>
        <a href="/bank/home">Return to Home Page</a>
        <p>${sessionScope.account.getBalance()}</p>
    </body>
</html>
