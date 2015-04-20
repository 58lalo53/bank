<%-- 
    Document   : NewAccount
    Created on : Apr 3, 2015, 12:21:37 PM
    Author     : 58lalo53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>... Bank Register</title>
    </head>
    <body>
        <h1>... Bank</h1>
        <c:choose>
            <c:when test="${sessionScope.cust.username!=null}">
                <%@include file="loginMenu.jspf" %>
            </c:when>
        </c:choose>
        <h3>${flash}</h3>
        <form method="POST" action="/bank/newAcc">
            <table>
                <tr>

                </tr>
                <tr>
                    <td>Type of Account: </td><td><select name="type">
                            <option value="checking">Checking Account</option>
                            <option value="savings">Savings Account</option>
                            <option value="credit">Credit Card</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Description (optional): </td><td><textarea name="description" rows="3" cols="80" ></textarea>
                </tr>
                <tr>
                    
                    <td><input type="submit" value="Create Account"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
