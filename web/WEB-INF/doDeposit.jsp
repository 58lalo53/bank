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
            <div id="nav">
                <c:choose>
                    <c:when test="${sessionScope.cust.username!=null}">
                        <%@include file="loginMenu.jspf" %>
                    </c:when>
                </c:choose>
            </div>
            <div id="content">
                <h3>Make a Deposit</h3>
                <h4>${flash}</h4>

        <form method="POST" action="/bank/doDeposit">
            <table class="center">
                
                <tr><td>Select Account: </td><td><select name="accId">
                    <c:forEach var="acc" items="${accounts}">
                        <c:choose>
                            <c:when test="${sessionScope.cust.username!=null}">
                                <option value="${acc.id}">Acc: ${acc.accNum}&nbspBal:&nbsp$${acc.balance}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="null">No accounts available</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </td></tr>
                <tr><td>Amount: </td><td><input type="text" name="amount">${aflash}</td></tr>
                <tr><td>Description: </td><td><textarea name="description" rows="3" cols="80"></textarea></td></tr>
                <tr><td colspan="2" align='center'><input type="submit" value="Make Deposit"></td></tr>
            </table>
                <input type="hidden" name="type" value="deposit">
        </form>
            </div>
                <%@include file="footer.jspf" %>
        </div>
    </body>
</html>
