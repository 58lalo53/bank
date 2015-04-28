<%-- 
    Document   : transactions
    Created on : Apr 24, 2015, 12:08:23 PM
    Author     : Eduardo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank Transactions</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
        <h3>Transactions</h3>
        <div id="nav">
            <c:choose>
                <c:when test="${sessionScope.cust.username!=null}">
                    <c:import url = "/WEB-INF/loginMenu.jspf"/>
                </c:when>
            </c:choose>
        </div>
        <h5>${flash}</h5>
        <table>
            <th>Date</th><th>Type</th><th>Amount</th><th>Balance</th><th>Description</th>
        <c:forEach var="trans" items="${trans}">    <tr><td>${trans.getTimeStamp()}</td><td>${trans.type}</td><td>${trans.amount}</td><td>${trans.balance}</td><td>${trans.description}</td></tr></c:forEach>
        </table>
        </div>
    </body>
</html>