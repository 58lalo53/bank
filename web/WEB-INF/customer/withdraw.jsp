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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
        <h3>${flash}</h3>

            <%@include file="menuAcc.jspf"%>

            <c:choose>
            <c:when test="${trans!=null}">
                <p>You have successfully withdrawn $<c:out value="${trans.amount}"/></p>
            </c:when>
            <c:otherwise>
                <p>To make a withdrawal click <a href="/bank/doWithdraw">here</a>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>
