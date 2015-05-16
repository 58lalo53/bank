<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank Register</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            
                <%@include file="../jspf/menuAcc.jspf" %>
            
            <div id="content">
                <h4 class="flash">${flash}</h4>
                <form method="POST" action="/bank/newAcc">
                    <table class="center">
                    
                <tr>
                    <td>Type of Account: </td><td><select name="type">
                            <option value="checking">Checking Account</option>
                            <option value="savings">Savings Account</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Description (optional): </td><td><textarea name="description" rows="3" cols="80" ></textarea>
                </tr>
                <tr>
                    
                    <td colspan="2" align='center'><input type="submit" value="Create Account"></td>
                </tr>
            </table>
        </form>
            </div>
                <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
