<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank Transfer</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            
            <div id="nav">
                <%@include file="loginMenu.jspf"%>
            </div>
        <div id="content">
             
            <h4 class="flash">${flash}</h4>

            <h3>Make a Transfer</h3>
        <form method="POST" action="/bank/doTransfer">
            <table class="center">
                <tr><td>From: </td><td><select name="facc">
                            <c:forEach var="accs" items="${accounts}">
                                
                                    
                                        <option value="${accs.id}">Acc: ${accs.accNum}&nbsp Bal: $${accs.balance}</option>
                                    
                            </c:forEach>
                        </select></td></tr>
                <tr><td>To: </td><td><select name="tacc">
                            <c:forEach var="accs" items="${accounts}">
                                               
                                        <option value="${accs.id}">Acc: ${accs.accNum}&nbsp Bal: $${accs.balance}</option>
                                    
                            </c:forEach>
                        </select></td></tr>
                <tr><td>Amount</td><td><input type="text" name="amount" required></td></tr>
                
                <tr><td colspan="2" align='center'><input type="submit" value="Make Transfer"></td></tr>
            </table>
            <input type="hidden" name="type" value="transfer"/>
        </form>
        </div>
                <%@include file="footer.jspf" %>
        </div>
    </body>
</html>
