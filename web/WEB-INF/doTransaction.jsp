<%-- 
    Document   : doTransaction
    Created on : Apr 27, 2015, 12:19:17 PM
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <h3>${flash}</h3>
            <div id="nav">
                <c:choose>
                    <c:when test="${sessionScope.cust.username!=null}">
                        <%@include file="loginMenu.jspf" %>
                    </c:when>
                </c:choose>
            </div>
        <form method="POST" action="/bank/doTransaction">
            <table>
                            <tr><td>Type: </td><td><select name="type">
                                        <option value="deposit">Deposit</option>
                                        <option value="withdraw">Withdraw</option>
                        </select> </td></tr>
                <tr><td>Select Account: </td><td><select name="accId">
                    <c:forEach var="acc" items="${accounts}">
                        <c:choose>
                            <c:when test="${sessionScope.cust.username!=null}">
                                <option value="${acc.id}">Acc: ${acc.accNum}&nbspBal:&nbsp${acc.balance}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="null">No accounts available</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </td></tr>
                <tr><td>Amount: </td><td><input type="text" name="amount"></td></tr>
                <tr><td>Description: </td><td><textarea name="description" rows="3" cols="80"></textarea></td></tr>
                <tr><td><input type="submit" value="Make Deposit"></td></tr>
            </table>
        </form>
        </div>
    </body>
    </body>
</html>
