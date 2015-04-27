<%-- 
    Document   : Deposite
    Created on : Apr 4, 2015, 5:36:50 PM
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
    <c:choose>
        <c:when test="${sessionScope.cust.username!=null}">
            <c:import url = "loginMenu.jspf" />
        </c:when>
    </c:choose>
        <h3>${flash}</h3>
        <c:choose>
            <c:when test="${trans!=null}">
                You successfully deposited $<c:out value="${trans.getAmount()}"/>
            </c:when>
            <c:otherwise>
                To make a deposit, click <a href="/bank/doDeposit">here</a>
            </c:otherwise>
        </c:choose>
        
        
    </body>
</html>
