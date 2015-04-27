<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/bank/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>... Bank</title>
    </head>
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
        <form method="POST" action="/bank/doDeposit">
            <table>
                <tr><td>Type: </td><td><input type="text" name="type" value="deposit" readonly></td></tr>
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
</html>
