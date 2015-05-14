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
            <div id="nav">
                <c:choose>
                    <c:when test="${sessionScope.cust.username!=null}">
                        <%@include file="menuAcc.jspf" %>
                    </c:when>
                </c:choose>
            </div>
            <div id="content">
        <h3>${flash}</h3>
        <c:choose>
            <c:when test="${trans!=null}">
                You successfully deposited $<c:out value="${trans.getAmount()}"/>
            </c:when>
            <c:otherwise>
                To make a deposit, click <a href="/bank/doDeposit">here</a>
            </c:otherwise>
        </c:choose>
            </div>
        </div>
    </body>
</html>
