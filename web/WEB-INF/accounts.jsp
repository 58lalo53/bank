<%-- 
    Document   : accounts
    Created on : Apr 5, 2015, 5:32:03 PM
    Author     : 58lalo53
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <title>... Bank</title>
    </head>
    <body>
        <div id="warpper">
            <div id ="header">
                <h1>... Bank</h1>
            </div>
           
        <h2>${flash}</h2>
        <div id="nav">
        <c:choose>
            <c:when test = "${sessionScope.cust.username!= null}">
                <%@include file="loginMenu.jspf"%>
            </c:when>
        </c:choose>
        </div>
        <h3>${flash}</h3>
        <p>To open a new Account <a href="/bank/newAcc">click here</a>.</p>
                    <c:forEach var="acc" items="${accounts}">
                        <div class="account">
                        
                            <c:choose>
                            <c:when test= "${sessionScope.cust.username!= null}">
                                <a href="/bank/transactions">
                                    <span class="accNum">Account Number:&nbsp<c:out value="${acc.accNum}"></c:out></span></a><br/>
                                <span class="accType">${acc.type}</span><br/>
                                <span class="balance">Balance:&nbsp$${acc.getBalance()}</span><br/>
                                <span class="joinDate">Opened on:&nbsp<fmt:formatDate type ="both" dateStyle="long" timeStyle="long" value="${acc.timeStamp}"/></span>
                                
                            </c:when>    
                            
                        <c:otherwise>
            
        You don't have any opened accounts. To open one <a href="/bank/newAcc">click here</a>.
                        </c:otherwise>
                    </c:choose>
            
                        </div>
                </c:forEach>
        </div>
                    </body>
</html>
