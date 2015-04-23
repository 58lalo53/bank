<%-- 
    Document   : Withdraw
    Created on : Apr 4, 2015, 5:37:35 PM
    Author     : 58lalo53
--%>

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
        <h3>${flash}</h3>
        <c:when test="${sessionScope.cust.username!=null}">
            <c:import url="loginMenu.jspf"/>
        </c:when>
        
        <c:choose>
            <c:when test="${trans!=null}">
                <p>You have successfully withdrawn ${trans.amount}</p>
            </c:when>
            <c:otherwise>
                <p>To make a withdrawal click <a href="/bank/doWithdraw">here</a>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
