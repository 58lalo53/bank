<%-- 
    Document   : doDeposite
    Created on : Apr 4, 2015, 5:36:35 PM
    Author     : 58lalo53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>... Bank</title>
    </head>
    <body>
        <h1>... Bank</h1>
        <h3>${flash}</h3>

        <form method="POST" action="/bank/doDeposit">
            <table>
                <th><td>Select Account: </td></th>
                    <c:forEach var="acc" items="${accounts}">
                        <c:choose>
                            <c:when test="${sessionScope.cust.username!=null}">
                            <tr><td><input type="radio" name="accId" value="${acc.id}"/></td><td>${acc.accNum}<br/>
                                    Balance: <input type="text" name="balance" value="$${acc.balance}" readonly/><br/></td></tr>
                            </c:when>
                            <c:otherwise>
                                <tr><td><input type="radio"/>No accounts available</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
            </table>
            <table>
                <tr><td>Amount: </td><td><input type="text" name="amount"></td></tr>
                <tr><td>Description: </td><td><textarea name="description" rows="3" cols="80"></textarea></td></tr>
                <tr><td><input type="submit" value="Make Deposit"></td></tr>
            </table>
        </form>
    </body>
</html>
