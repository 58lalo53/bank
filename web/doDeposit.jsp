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

        <form method="POST" action="eindex">
            <input type="hidden" name="action" value="doDeposit">
            <table>
                <tr><td>Select Account: </td> <td><select name="accId">
                            <c:forEach var="acc" items="${accounts}">
                            <c:choose>
                                <c:when test="${sessionScope.cust.username!=null}">
                                    
                                    <option value="${acc.accNum}">${acc.type} ${acc.accNum}</option>
                                </c:when>

                                    <c:otherwise>
                                        <option value="no">No option available"/></option>
                                </c:otherwise>
                            </c:choose>
                                    </c:forEach>
                        </td></tr>
                <tr><td>Current Balance: </td><td><input type="text" name="balace" value="${acc.balance}" readonly></td></tr>
                <tr><td>Amount: </td><td><input type="text" name="amount"></td></tr>
                <tr><td><input type="submit" value="Make Deposit"></td></tr>
            </table>
        </form>
    </body>
</html>
