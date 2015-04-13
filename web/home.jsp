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
        <title>... Bank</title>
    </head>
    <body>
        <h1>Welcome to ... Bank</h1>
                    <p>
                <c:choose>
                    <c:when test="${sessionScope.cust.username != null}">
                    <li>
        <ul><a href="eindex?action=accounts">Accounts</a></ul>
        <ul><a href="eindex?action=doDeposit">Deposit</a></ul>
        <ul><a href="eindex?action=withdraw">Withdraw</a></ul>
        <ul><a href="eindex?action=transactions">Transactions</a></ul>
        <ul><a href="eindex?action=logout">Logout</a></ul>
                    </li>
                    </c:when>
                    <c:otherwise>
    <li>
        <ul><a href="eindex?action=home">Home</a></ul>
        <ul><a href="eindex?action=login">Login</a></ul>
        <ul><a href="eindex?action=register">Register</a></ul>

    </li>
                    </c:otherwise>
                </c:choose>
            </p>

    <h3>Welcome ${sessionScope.cust.toString()}</h3>
    </body>
</html>
