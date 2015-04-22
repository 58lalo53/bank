<%-- 
    Document   : doWithdraw
    Created on : Apr 4, 2015, 5:37:23 PM
    Author     : 58lalo53
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>... Bank</h1>
        <c:choose>
            <c:when test="${sessionScope.cust.username!=null}">
                <%@include file="loginMenu.jspf" %>
            </c:when>
        </c:choose>
                <form method="POST" action="/bank/doWithdraw">
                    <table>
                        <tr>
                            <td>Select account: </td><td><select name="accId">
                                    
                                    <c:forEach var="acc" items="${accounts}">
                                        <c:choose>
                                        <c:when test="${sessionScope.cust.username!=null}">
                                            <option value="${acc.id}">Acc: ${acc.accNum}&nbsp Bal: ${acc.balance}</option>
                                        </c:when>
                                    <c:otherwise>
                                        <option value="null">No accounts</option>
                                    </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </td></tr>
                            <tr><td>Amount: </td><td><input type="text" name="amount"/></td></tr>
                            <tr><td>Description: </td><td><textarea name="description"></textarea></td></tr>
                            <tr><td><input type="submit" value="Complete Withdraw"</td></tr>
                    </table>
                </form>
            
        
    </body>
</html>
