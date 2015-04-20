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
                <%@include file="loginMenu.jspf"%>
            </c:when>
        </c:choose>
        <h3>${flash}</h3>
        <p>To open a new Account <a href="/bank/newAcc">click here</a>.</p>
                    <c:forEach var="acc" items="${accounts}">
                        <p>
                            <c:choose>
                            <c:when test= "${sessionScope.cust.username!= null}">
                        ${acc.type}<br/>
                        Created on:&nbsp${acc.timeStamp}<br/>
                        Account Number:&nbsp${acc.accNum}<br/>
                        Balance:&nbsp$${acc.balance}
                            </c:when>    
                            
                        <c:otherwise>
            
        You don't have any opened accounts. To open one <a href="/bank/newAcc">click here</a>.
                        </c:otherwise>
                    </c:choose>
            </p> 
                </c:forEach>
                    </body>
</html>
