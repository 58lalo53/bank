<%-- 
    Document   : index
    Created on : Mar 31, 2015, 3:43:44 PM
    Author     : 58lalo53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <title>... Bank</title>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Welcome to ... Bank</h1>
            </div>

                <%@include file = "noLoginMenu.jspf" %>
       
        <div id="content">
            <h4 class="flash"><c:out value="${flash}"/></h4>
        <form method="POST" action="/bank/login">
                
            <table class="center">
                    <tr>
                        <td>User Name: </td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Login"/></td>
                    </tr>
                </table>
            </form>
        </div>
            <%@include file="footer.jspf" %>
            
        </div>
    </body>
</html>
