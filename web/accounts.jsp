<%-- 
    Document   : accounts
    Created on : Apr 5, 2015, 5:32:03 PM
    Author     : 58lalo53
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>... Bank</title>
    </head>
    <body>
        
        <h1>... Bank</h1>
        <h2>${flash}</h2>
        <c:choose>
            <c:when test = "${sessionScope.cust.username!= null}">
                <li>
                    <ul><a href="eindex?action=home">Home</a></ul>
                    <ul><a href="eindex?action=accounts">Accounts</a></ul>
                    <ul><a href="eindex?action=transactions">Transactions</a></ul>
                    <ul><a href="eindex?action=logout">Logout</a></ul>
                    </li>
            </c:when>
        </c:choose>
        <h3>${flash}</h3>
        <p>To open a new Account <a href="eindex?action=newAcc">click here</a>.</p>
                    <c:forEach var="acc" items="${accounts}">
                        <p>
                            <c:choose>
                            <c:when test= "${sessionScope.cust.username!= null}">
                        ${acc.type}<br/>
                        Created on :${acc.timeStamp}<br/>
                        Account Number :${acc.accNum}
                            </c:when>    
                            
                        <c:otherwise>
            
        You don't have any opened accounts. To open one <a href="eindex?action=newAcc">click here</a>.
                        </c:otherwise>
                    </c:choose>
            </p> 
                </c:forEach>
                    </body>
</html>
